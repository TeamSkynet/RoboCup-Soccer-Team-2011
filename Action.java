/**
* @file Action.java
*
* Actions for the player to use
*
* @author Grant Hays
* @date 11/9/11
* @version 3.0
*/

import java.net.UnknownHostException;

/**
* @class Action
*
* This class holds basic actions for the player to perform, such as ball searching and
* intercepting, dashing to points, finding the ball and points and getting their coordinates.
*
*/
public class Action {
	
	
	/**
	* Default constructor
	*/
	public Action() {
		
	}
	
	
	/**
	* Constructor with parameters
	*
	* @param mem The Memory containing all the parsed information from the server
	* @param rc The RoboClient that is the player's connection to the server
	*
	* @pre Both a full memory and initialized RoboClient must be passed in to avoid any
	* errors
	* @post A new set of actions will be available for the player to call on
	*/
	public Action(Memory mem, RoboClient rc) {
		this.mem = mem;
		this.rc = rc;
	}
	
	/**
	* This sets the Memory for the action to use. This is important as the
	* Memory is constantly changing, and must be updated at every step.
	* @param mem The player's Memory
	* @pre The Memory should be the most up to date
	* @post The actions that require a Memory will be able to pull from it
	*/
	public void setMem(Memory mem) {
		this.mem = mem;
	}
	
	public double getTurn(Polar go) {
		double angle = go.t - mem.getDirection();
		if(angle > 180)
			angle -= 360;
		else if(angle < -180)
			angle += 360;
		else if(Math.abs(angle) == 180)
			angle = 180;
		
		return(angle  * (1 + (5*mem.getAmountOfSpeed())));
	}
	
	/**
	* This tells the player to turn and run to a point
	*
	* @param go The Polar coordinates of the final position, with
	* the player's position as an origin
	*
	* @pre The player must have a valid position on the field passed in
	* @post If the player is not facing the direction of the final position, s/he will
	* first turn toward it. If the player is approximately facing the position, s/he
	* will dash toward the direction of the position.
	*/
	public void gotoPoint(Polar go) {
		
		try {
			if((go.t > 5.0) || (go.t < -5.0)) {
				rc.turn(go.t * (1 + (5*mem.getAmountOfSpeed())));
			}
			rc.dash(m.getDashPower(m.getPos(go), mem.getAmountOfSpeed(), mem.getDirection(), mem.getEffort(), mem.getStamina()));
		
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	* A cartesian wrapper for the gotoPoint with Polar coordinate
	*
	* @param p The Cartesian Pos of position to go to
	*
	* @pre The player must have a valid position on the field passed in
	*
	* @post First, the Pos will be converted to a Polar coordinateIf the player is
	* not facing the direction of the final position, s/he will turn toward it.
	* If the player is approximately facing the position, s/he will dash toward the
	* direction of the position.
	*/
	public void gotoPoint(Pos p) {
		/*
		if((Math.abs(p.x - mem.getPosition().x) >= 0.1) && (Math.abs(p.y - mem.getPosition().y) >= 0.1)) {
			gotoPoint(mem.getAbsPolar(p));
			mem.getPosition().print("Position: ");
		*/
		Polar go = mem.getAbsPolar(p);
		if(go.r >= 0.1) {
			try {
				if((go.t - mem.getDirection()) > 5.0 || (go.t - mem.getDirection()) < -5.0) {
					rc.turn((go.t - mem.getDirection()) * (1 + (5*mem.getAmountOfSpeed())));
				}
				rc.dash(m.getDashPower(m.getPos(go), mem.getAmountOfSpeed(), mem.getDirection(), mem.getEffort(), mem.getStamina()));
			
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Take the Player back to his home
	 * 
	 * @pre The player's home should be set at initialization
	 * @post The player will be at his home point 
	 * 
	 * @return true if the player is in the near vicinity of his home, false if he's not there yet
	 */
	public boolean goHome() {
		Pos pt = mem.getPosition();
		
		if(((pt.x - mem.home.x) < 1.0) && ((pt.x - mem.home.x) > -1) && ((pt.y - mem.home.y) < 1) && ((pt.y - mem.home.y) > -1))
			return true;
		else {
			gotoPoint(mem.home);
			return false;
		}
	}
	
	/**
	* A method to find the ball on the field. If it's not in view, the player turns
	* until he finds it. If the ball is too far, he dashes to get to it. If the ball
	* is within 20 distance, he intercepts the ball.
	*
	* @throws UnknownHostException
	* @throws InterruptedException
	*/
	public void findBall() throws UnknownHostException, InterruptedException {
		if(mem.isObjVisible("ball")) {
			ObjBall ball = mem.getBall();
			if((ball.getDistance() > 20) && (ball.getDirection() > 5.0 || ball.getDirection() < -5.0)) {
				rc.turn(ball.getDirection());
			}
			else if((ball.getDistance() < 20.0) && (ball.getDistance() > 0.7)){
				interceptBall(ball);
			}
			else if(ball.getDistance() <= 0.7)  {
				dribbleToGoal(ball);
				
			}
			
		}
		else
			rc.turn(30);
	}
		
	
	/**
	* This method goes to the position that the ball will be in at time t+1 and kicks
	* it if it is within 0.5 distance.
	* @param ball
	* @throws UnknownHostException
	* @throws InterruptedException
	*
	* @pre A ball must be present and passed
	* @post The player (should) go to the point where the ball is and kick it
	*/
	private void interceptBall(ObjBall ball) throws UnknownHostException, InterruptedException {
		Polar p = m.getNextBallPoint(ball);
		//m.getNextBallPoint(ball).print("GetNextBall Polar: ");
		//m.getPos(m.getNextBallPoint(ball)).print("GetNextBall Pos: ");
		if(stayInBounds())
			gotoPoint(p);
	}
	
	/**
	 * This will attempt to keep the player from making any sudden moves while the play
	 * mode is one in which he can't get to the ball. This keeps them from going out of
	 * bounds for a ball.
	 * 
	 * @pre The Memory.side should not be null
	 * @return True if the player is able to play, false if he is not
	 */
	private boolean stayInBounds() {
		if(mem.side.compareTo("l") == 0) {
			if((mem.getPlayMode().compareTo("play_on") != 0) && (mem.getPlayMode().compareTo("kick_off_l") != 0)) {
				return false;
			}
			else
				return true;
		}
		else {
			if((mem.getPlayMode().compareTo("play_on") != 0) && (mem.getPlayMode().compareTo("kick_off_r") != 0)) {
				return false;
			}
			else
				return true;
		}
		
	}
	
	
	/**
	 * If the goal is within sight, this will kick the ball to it with maximum power. If
	 * the goal is not in sight, this will kick towards the direction of the goal with 
	 * maximum power.
	 * 
	 * @param ball
	 * 
	 * @pre The ball passed in should not be null
	 * @post The ball will be kicked in the direction of the goal
	 * 
	 */
	private void kickToGoal(ObjBall ball) {
		ObjGoal goal = mem.getOppGoal();
		if(goal != null) {
			try {
				rc.kick(100, goal.getDirection() - mem.getDirection());
			} catch (UnknownHostException e) {
				System.out.println("Error at action.kickToGoal() kick 1");
				e.printStackTrace();
			}
		}
		else {
			try {
				rc.kick(100, mem.getDirection());
			} catch (UnknownHostException e) {
				System.out.println("Error at action.kickToGoal() turn");
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Kicks ball to a certain Polar point
	 * 
	 * @param ball
	 * @param p The Polar coordinate to kick the ball to
	 * 
	 * @pre The ball passed in should not be null and p should be within the field from the player
	 * @post The ball will be kicked to the vicinity of the point
	 * 
	 */
	public void kickToPoint(ObjBall ball, Polar p) {
		
		if(ball.getDistance() <= 0.7) {
			try {
				rc.kick(m.getKickPower(p, mem.getAmountOfSpeed(), mem.getDirection(), ball.getDistance(), ball.getDirection()), p.t);
			} catch (UnknownHostException e) {
				System.out.println("Error in Action.kickToPoint");
				e.printStackTrace();
			}
		}

		
	}
	
	/**
	 * A Pos wrapper for the kickToPoint
	 * @param ball 
	 * @param p the Pos of the coordinate to kick the ball to
	 */
	public void kickToPoint(ObjBall ball, Pos p) {
		kickToPoint(ball, m.getPolar(p));
	}
	
	/**
	 * This dribbles the ball in the direction of the goal until it's 18 feet outside of the 
	 * goal, when it kicks the ball with maximum power into the goal.
	 * 
	 * @param ball
	 * 
	 * @pre The ball should not be null
	 * @post This will result in a dribble and a shoot
	 */
	public void dribbleToGoal(ObjBall ball) {
		if(stayInBounds()) {
			ObjGoal goal = mem.getOppGoal();
			
			if((goal != null) && ((goal.getDistance() - 18) > 1.0)) {
					kickToPoint(ball, new Polar(10.0, (goal.getDirection() - ball.getDirection())));
				
			}
			else if((goal != null) && ((goal.getDistance() - 18) <= 1.0)) {
				kickToGoal(ball);
			}
			else if(goal == null) {
				try {
					rc.kick(10.0, mem.getNullGoalAngle());
				} catch (UnknownHostException e) {
					System.out.println("Error in Action.dribbleToGoal() at null goal turn");
					e.printStackTrace();
				}
			}
		}
	}
	
	public MathHelp m = new MathHelp();
	public Memory mem;
	public RoboClient rc;
	public Polar OppGoal;
	public boolean atGoal;
}