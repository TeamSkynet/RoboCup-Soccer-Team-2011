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

	protected RoboClient rc = new RoboClient();
	private Memory m = new Memory();
	private ObjInfo i = new ObjInfo();
	private Parser p = new Parser();
	
	 /**
	 * @return The RoboClient object for this Player.
	 */
	public RoboClient getRc() {
		return rc;
	}

	/**
	 * @param rc The RoboClient to set.
	 */
	public void setRc(RoboClient rc) {
		this.rc = rc;
	}

	/**
	 * @return The Memory for this Player.
	 */
	public Memory getM() {
		return m;
	}

	/**
	 * @param m The Memory to set.
	 */
	public void setM(Memory m) {
		this.m = m;
	}

	/**
	 * @return The ObjInfo for this Player.
	 */
	public ObjInfo getI() {
		return i;
	}

	/**
	 * @param i The ObjInfo to set.
	 */
	public void setI(ObjInfo i) {
		this.i = i;
	}

	/**
	 * @return The Parser for this Player.
	 */
	public Parser getP() {
		return p;
	}

	/**
	 * @param p The Parser to set.
	 */
	public void setP(Parser p) {
		this.p = p;
	}

	/**
	 * Initializes the Player with the RoboCup server.
	 * @pre A RoboCup server is available.
	 * @post The Player has been initialized to the correct team.
	 */
	public void initPlayer() throws SocketException, UnknownHostException {
		
		rc.dsock = new DatagramSocket();
		rc.init();
	}
	
	
	 /**
	 * Receives worldstate data from the RoboCup server.
	 * @pre A RoboCup server is available.
	 * @post The current worldstate has been stored in the Memory.
	 */
	public void receiveInput() throws InterruptedException  {
		
		p.Parse(rc.receive(), m);
		Thread.sleep(100);
	}
	
	 /**
	 * Teleports the Player to the specified coordinates.
	 * @param x x-coordinate of the point to move the player to.
	 * @param y y-coordinate of the point to move the player to.
	 * @pre Playmode is before-kickoff, goal-scored, free-kick.
	 * @post The Player has been moved to the correct position.
	 */
	public void move(int x, int y) throws UnknownHostException {
		rc.move(x, y);
	}
	
	 /**
	 * Causes Player to kick the ball.
	 * @param dir The direction in which to kick the ball in the form of a decimal value. 
	 * representing the angle in degrees in relation go the player.
	 * @param power The power with which to kick the ball in the form of a decimal value.
	 * @pre Playmode is play_on, ball is in kickable range.
	 * @post The ball has been kicked in the specified direction and power.
	 */
	public void kick(double power, double dir) throws UnknownHostException {
		rc.kick(power, dir);	
	}
	
	 /**
	 * Causes Player to turn according to a specified turn moment.
	 * @param moment The turn angle in degrees. 
	 * @pre Playmode is play_on, ball is in kickable range.
	 * @post The ball has been kicked in the specified direction and power.
	 */
	public void turn(double moment) throws UnknownHostException {
		rc.turn(moment);
	}
	
}
