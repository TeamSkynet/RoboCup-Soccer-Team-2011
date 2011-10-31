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
		rc.initGoalie();		
	}
	
	 /**
	 * Causes the Goalie to catch the ball.
	 * @pre Playmode is play-on, ball is within goalkeeper zone and in the catchable area.
	 * @post The Goalie has caught the ball.
	 */
	public void catchball(int dir) throws UnknownHostException{
		rc.catchball(dir);
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
					//
					// I think you'd probably go to defendGoal() after this. I was thinking
					// you should probably pass in the ball to defendGoal, too, so you can use
					// something like getNextBallPoint(ObjBall ball) in MathHelp
					//
					System.out.println("I see you BALL");
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
	
	public void defendGoal(ObjBall ball) {
		
		
		
		//If ball in goal zone
		
		//Determine ball position in relation to goalie
		
		
		//Move if needed
		
		//If ball is in catchable area
		
		//Catch ball
		
	}
	
	public boolean ballTurn = false;
	public MathHelp mh = new MathHelp();

} //end class
