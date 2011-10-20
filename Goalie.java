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

} //end class
