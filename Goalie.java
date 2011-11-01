import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/** @file Goalie.java
* Class file for Goalie class
* @author Joel Tanzi
* @date 11 October 2011
* @version 1.0 
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
	}
	
	public void followBall() {
		
		
		try {
			if(!getMem().isObjVisible("ball") && !ballTurn) {
				getRoboClient().turn(45);
				ballTurn = true;
			}
			else if(!getMem().isObjVisible("ball") && ballTurn) {
				getRoboClient().turn(-45);
				ballTurn = false;
			}
			
			if(getMem().isObjVisible("ball")) {
				ObjBall ball = getMem().getBall();
				
				if((ball.getDirection() > 5.0) || (ball.getDirection() < -5.0)) {
					getRoboClient().turn(ball.getDirection() * (1 + (5 * getMem().getAmountOfSpeed())));
				}
					
				if(ballInGoalzone(ball)) {
					
					defendGoal(ball);
					//
					// I think you'd probably go to defendGoal() after this. I was thinking
					// you should probably pass in the ball to defendGoal, too, so you can use
					// something like getNextBallPoint(ObjBall ball) in MathHelp
					//
					//System.out.println("I see you BALL");
				}
			}
			
		} catch (UnknownHostException e) {
			System.out.println("Error in Goalie.followBall()");
			e.printStackTrace();
		}
	}
	
	public boolean ballInGoalzone(ObjBall ball) {
		try {
			
			ObjFlag PFlag = getMem().getClosestPenaltyFlag();
			
			if(PFlag == null) {
				getRoboClient().turn(-1 * getMem().getDirectionOfSpeed());
				System.out.println("No Penalty flag in sight");
				return false;
			}
			else {
				
				Pos toFlag = mh.getPos(PFlag.getDistance(), PFlag.getDirection());
				Pos ballPos = mh.getPos(ball.getDistance(), ball.getDirection());
				
				if(toFlag.x >= ballPos.x)
					return true;
				else
					return false;
				
				
			}
			
		} catch (UnknownHostException e) {
			System.out.println("Error in Goalie.ballInGoalZone()");
			e.printStackTrace();
		}		
		
		return false;
	}
	
	/**
	 * Causes the goalie to act to intercept the ball as it approaches the goal.
	 * @param ObjBall representing the ball in play.
	 * @throws UnknownHostException 
	 * @pre The ball has entered the goal zone.
	 * @post The ball has been caught by the goalie, or the goalie has missed the ball.
	 */
	public void defendGoal(ObjBall ball) throws UnknownHostException {				

		//Move to catchable range of ball
		getAction().gotoPoint(mh.getNextBallPoint(ball));
		
		//If ball is in catchable area, catch it
		if (catchable()){
			catchball(getMem().getBall().getDirection());
		}		
	}

	/**
	 * Returns the closest player to the goalie as an ObjPlayer object from the memory
	 * of the current worldstate.
	 * @pre Players are in sight of the goalie.
	 * @post The closest player to the goalie has been determined.
	 * @return ObjPlayer
	 */
	public ObjPlayer closestPlayer() {
		ObjPlayer closestPlayer = new ObjPlayer();
		double distance = 0;
		
		//Loop through arraylist of ObjPlayers
		for (int i = 0; i < getMem().getPlayers().size(); ++i) {
			
			if (!getMem().getPlayers().isEmpty()) {  
				if (distance == 0) {
					distance = getMem().getPlayers().get(i).getDistance();
				}
				else {
					
					//Test if this player is closer than the previous one
					if (distance > getMem().getPlayers().get(i).getDistance()) {
						distance = getMem().getPlayers().get(i).getDistance();
						closestPlayer = getMem().getPlayers().get(i);
					}
				}
			}
		}		
		return closestPlayer;
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
		if (getMem().isObjVisible("ball")) {
			
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
	
	public boolean ballTurn = false;
	public MathHelp mh = new MathHelp();


} //end class

