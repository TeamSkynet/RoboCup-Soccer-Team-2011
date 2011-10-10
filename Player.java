import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;


/** @file Player.java
* Class file for Player class
* @author Joel Tanzi
* @date 11 October 2011
* @version 1.0 
*/

/** @class Player
* The Player class defines all objects and methods used for the 
* Player within the RoboCup match.  The Player establishes a connection
* to the server, initializes itself on the team, and 
* performs all actions related to a RoboCup soccer player such as
* (but not limited to) kicking, dashing, dribbling, passing and scoring. 
* The Player class has a Memory for storing the current RoboCup worldstate.
* It reacts to stimuli based on strategies provided by the Brain (TBD). 
*/
public class Player {

	RoboClient rc = new RoboClient();
	Memory m = new Memory();
	ObjInfo i = new ObjInfo();
	Parser p = new Parser();
	
	//Connect to server and initialize player
	public void initPlayer() throws SocketException, UnknownHostException {
		
		rc.dsock = new DatagramSocket();
		rc.init();
	}
	
	public void initGoalie() throws SocketException, UnknownHostException {
		
		rc.dsock = new DatagramSocket();
		rc.initGoalie();
		
	}
	
	public void receiveInput() throws InterruptedException  {
		
		p.Parse(rc.receive(), m);
		Thread.sleep(100);
	}
	
	
}
