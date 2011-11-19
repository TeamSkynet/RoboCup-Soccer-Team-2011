import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/** @file Goalie.java
* Class file for Goalie class
* @author Joel Tanzi
* @date 11 October 2011
* @version 1.3 
*/

/** @class Goalie
* The Goalie class inherits from the Player class.  The Goalie is a specialized
* type of Player that may catch the ball under certain conditions and defends the goal
* from the opposing team. 
*/
public class Goalie extends Player {

	/**
	 * 
	 */
	public Goalie() {
		super();
		// TODO Auto-generated constructor stub
	}

	 /**
	 * Initializes the Player with the RoboCup server as a goalie.
	 * @pre A RoboCup server is available.
	 * @post The Player has been initialized to the correct team as a goalie.
	 */
	public void initGoalie() throws SocketException, UnknownHostException {

		rc.dsock = new DatagramSocket();
		rc.initGoalie(getParser(), getMem());		
	}

	 /**
	 * Causes the Goalie to catch the ball.
	 * @pre Playmode is play-on, ball is within goalkeeper zone and in the catchable area.
	 * @post The Goalie has caught the ball.
	 */
	public void catchball(double d) throws UnknownHostException{
		rc.catchball(d);
		ballCaught = true;
	}

	
	/**
	 * Turns goalie toward the ball
	 * @post The goalie will turn in the direction of the ball
	 */
	public void followBall() {


		try {
			if(!getMem().isObjVisible("ball")) {
				turn(45);
				return;
			}
			if(getMem().isObjVisible("ball")) {
				ObjBall ball = getMem().getBall();
				
				//getBtwBallAndGoal(ball);

				if((ball.getDirection() > 5.0) || (ball.getDirection() < -5.0)) {
					turn(ball.getDirection() * (1 + (5 * getMem().getAmountOfSpeed())));
				}
				if(ballInGoalzone(ball)){
					defendGoal(ball);
				}
			}

		} catch (UnknownHostException e) {
			System.out.println("Error in Goalie.followBall()");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * A method to determine whether the ball is in the penalty box
	 * 
	 * @param ball the ObjBall to follow
	 * @pre this must be called with an ObjBall
	 * @post true if ball is in penalty box, false if it's not
	 * @return boolean
	 */
	public boolean ballInGoalzone(ObjBall ball) {
		
		if(ball == null)
			return false;
		
		
		Pos ballPos = mh.getPos(ball.getDistance(), getDirection() + ball.getDirection());
		ballPos = mh.vAdd(getPosition(), ballPos);
		/*
		System.out.println("Ball polar: (" + ball.getDistance() + ", " + ball.getDirection() + ")");
		System.out.println("Ball position: (" + ballPos.x + ", " + ballPos.y + ")");
		System.out.println("****************************************");
		System.out.println("");
		System.out.println("");
		*/
		
		if(((ballPos.x <= -36) && (ballPos.x >= -52.5)) && ((-20.16 <= ballPos.y) && (ballPos.y <= 20.16)))
			return true;
		else
			return false;			
	}
	
	
	/**
	 * Returns true or false depending on whether the ball is within the catchable range
	 * of the goalie.
	 * @pre The ball is visible to the goalie
	 * @post The ball is determined to catchable or not.
	 * @return boolean True if catchable, false if not.
	 */
	public boolean catchable() {
		
		boolean catchable = false;
		
		//Test for visibility
		if (getMem().isObjVisible("ball") && ballInGoalzone(getMem().getBall())) {
			
			//Test for moment range
			if (getMem().getBall().getDirection()> -180 && getMem().getBall().getDirection() < 180) {
				
				//Test for catchable distance
				if (getMem().getBall().getDistance() < 2.0) {
					catchable = true;
				}
			}
		}
		
		return catchable;
	}
	


	/**
	 * Causes the goalie to act to intercept the ball as it approaches the goal.
	 * @param ObjBall representing the ball in play.
	 * @throws UnknownHostException 
	 * @throws InterruptedException 
	 * @pre The ball has entered the goal zone.
	 * @post The ball has been caught by the goalie, or the goalie has missed the ball.
	 */
	public void defendGoal(ObjBall ball) throws UnknownHostException, InterruptedException {

		//Move to catchable range of ball
		if (!catchable()) {
			getAction().gotoPoint(mh.getNextBallPoint(ball));			
		}
		else {
			//If ball is in catchable area, catch it
			//System.out.println("catchable");
			if (!ballCaught) {
				catchball(getMem().getBall().getDirection());
				Thread.sleep(100);
				ballCaught = true;	
			}
			
			kickToPlayer(closestPlayer());
			Thread.sleep(100);	
		}
	} //end method
	
		
	/**
	 * Moves goalie between the ball and the goal (under construction)
	 * @param ball An ObjBall.
	 * @pre Ball is visible to the goalie.
	 * @post The goalie has moved to a point on the line between the ball and the goal.
	 */
	public void getBtwBallAndGoal(ObjBall ball) {
		
		Pos ballPos = mh.getPos(ball.getDistance(), getDirection() + ball.getDirection());
		ballPos = mh.vAdd(getPosition(), ballPos);
		Pos goalPos = getMem().getOwnGoalPos();
		Pos newPos = new Pos();
		boolean between = false;
		
		double slope = (goalPos.y - ballPos.y)/(goalPos.x - ballPos.x);
		double x_p = 0.66 * (goalPos.x - ballPos.x) + ballPos.x;
		double y_int = ballPos.y - ballPos.x * slope;
		double y_p = slope * x_p + y_int;
		newPos.x = x_p;
		newPos.y = y_p;
		
		if (ball.getDirChng() > 20 & !between) {
			getAction().gotoPoint(newPos);
			between = true;
		}		
	}

	/**
	 * Returns the closest player to the goalie on the same team.
	 * @post The closest player to the goalie has been determined.
	 * @return ObjPlayer
	 * @throws InterruptedException 
	 * @throws UnknownHostException 
	 */
	public ObjPlayer closestPlayer() throws UnknownHostException, InterruptedException {
		ObjPlayer closestPlayer = new ObjPlayer();
		double distance = 0;

		//Loop through arraylist of ObjPlayers
		for (int i = 0; i < getMem().getPlayers().size(); ++i) {

			if (!getMem().getPlayers().isEmpty()) {  
				if (distance == 0 && getMem().getPlayers().get(i).getTeam() == rc.getTeam()) {
					distance = getMem().getPlayers().get(i).getDistance();
				}
				else {

					//Test if this player is closer than the previous one
					if (distance > getMem().getPlayers().get(i).getDistance() && getMem().getPlayers().get(i).getTeam() == rc.getTeam()) {
						distance = getMem().getPlayers().get(i).getDistance();
						closestPlayer = getMem().getPlayers().get(i);
					}
				}
			}
			else {  //No players in goalie's sight, so turn to another point to check again
				turn(30);
				
				if (!getMem().getPlayers().isEmpty()) {  
					if (distance == 0 && getMem().getPlayers().get(i).getTeam() == rc.getTeam()) {
						distance = getMem().getPlayers().get(i).getDistance();
					}
					else {
						//Test if this player is closer than the previous one
						if (distance > getMem().getPlayers().get(i).getDistance() && getMem().getPlayers().get(i).getTeam() == rc.getTeam()) {
							distance = getMem().getPlayers().get(i).getDistance();
							closestPlayer = getMem().getPlayers().get(i);
						}
					}
				}
				
			}
		}		
		return closestPlayer;
	}

	/**
	 * Causes goalie to kick the ball to a specific player.
	 * @pre A player is in sight of the goalie.
	 * @post The goalie has kicked the ball to the player passed to the function.
	 * @param player An ObjPlayer representing the player to receive the ball.
	 */
	public void kickToPlayer(ObjPlayer player) {

		if(getMem().isObjVisible("ball")) {
			ObjBall ball = getMem().getBall();
			getAction().kickToPoint(ball, mh.getPos(new Polar(player.getDistance(), player.getDirection())));
			//ballCaught = false;
		}
	}
	
	/**
	 * Causes the goalie to kick the ball out of bounds
	 * @pre Goalie has control of the ball
	 * @post Ball has been kicked out of bounds
	 */
	public void kickBallOutOfBounds() {
		try {
			//Locate closest flag on a boundary line
			ObjFlag kickFlag = new ObjFlag();
			kickFlag = getMem().getClosestBoundary();
			//System.out.println("Flag name: " + kickFlag.getFlagName());
			
			//Test to ensure the flag is within a kickable range, and
			// is not dangerously close to the goal, and kick it if allowable
			if (kickFlag.getDistance() < 25 && kickFlag.getFlagName() != "flt10" && kickFlag.getFlagName() != "fl0"
				&& kickFlag.getFlagName() != "flb10" && kickFlag.getFlagName() != "frt10" 
					&& kickFlag.getFlagName() != "fr0" && kickFlag.getFlagName() != "frb10") {
				kick(90,kickFlag.getDirection());
				//ballCaught = false;
				Thread.sleep(100);
			}			
			else {  //Turn to a new position and check flag again
				turn(-30);
				Thread.sleep(100);
				
				//Kick if the boundary flag is now reachable
				kickFlag = getMem().getClosestBoundary();
				if (kickFlag.getDistance() < 25 && kickFlag.getFlagName() != "flt10" && kickFlag.getFlagName() != "fl0"
					&& kickFlag.getFlagName() != "flb10" && kickFlag.getFlagName() != "frt10" 
						&& kickFlag.getFlagName() != "fr0" && kickFlag.getFlagName() != "frb10") {
					kick(90,kickFlag.getDirection());
					//ballCaught = false;
					Thread.sleep(100);
				}	
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Run method for Goalie's individual thread
	public void run() {
		
		//System.out.println("Goalie");
		
		while(true) {
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(getMem().timeCheck(getTime())) {
				setTime(getMem().ObjMem.getTime());
				System.out.println("in time check");
				followBall();			
			}			
		} 		
	}	
	
	public boolean ballTurn = false;
	public MathHelp mh = new MathHelp();
	boolean ballCaught = false;


} //end class

