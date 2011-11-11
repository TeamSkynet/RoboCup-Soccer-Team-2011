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
public class Player extends Thread {
	
	protected RoboClient rc = new RoboClient();
	private Memory m = new Memory();
	private ObjInfo i = new ObjInfo();
	private Parser p = new Parser();
	//private Brain b = new Brain();
	private Action a = new Action(m, rc);
	private int time = 0;
	public boolean wait = true;
	
	
	public Player() {
		
	}
	
	public Player(String team){
		this.rc.setTeam(team);
	}

	/**
	 * @param rc
	 * @param m
	 * @param i
	 * @param p
	 * @param b
	 * @param time
	 */
	public Player(RoboClient rc, Memory m, ObjInfo i, Parser p, Brain b,
			int time) {
		super();
		this.rc = rc;
		this.m = m;
		this.i = i;
		this.p = p;
		//this.b = b;
		this.time = time;
	}


	/**
	 * @param b the b to set
	 */
	public void setBrain(Brain b) {
		//this.b = b;
	}

	public Action getAction() {
		return a;
	}
	
	public void setAction(Action a) {
		this.a = a;
	}
	
	/**
	 * @return The RoboClient object for this Player.
	 */
	public RoboClient getRoboClient() {
		return rc;
	}

	/**
	 * @param rc The RoboClient to set.
	 */
	public void setRoboclient(RoboClient rc) {
		this.rc = rc;
	}

	/**
	 * @return The Memory for this Player.
	 */
	public Memory getMem() {
		return m;
	}

	/**
	 * @param m The Memory to set.
	 */
	public void setMem(Memory m) {
		this.m = m;
	}

	/**
	 * @return The ObjInfo for this Player.
	 */
	public ObjInfo getObjInfo() {
		return i;
	}

	/**
	 * @param i The ObjInfo to set.
	 */
	public void setObjInfo(ObjInfo i) {
		this.i = i;
	}

	/**
	 * @return The Parser for this Player.
	 */
	public Parser getParser() {
		return p;
	}

	/**
	 * Sets the parser for the player.
	 * @param p The Parser to set.
	 */
	public void setParser(Parser p) {
		this.p = p;
	}

	/**
	 * Returns the current player time.
	 * @return the time
	 */
	public int getTime() {
		return time;
	}


	/**
	 * Sets the current time for the player.
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}
	/**
	 * Returns the direction of the player
	 */
	public double getDirection() {
		return(getMem().getDirection());
	}
	
	/**
	 * Returns the current absolute coordinates of the player.
	 * @return Pos
	 */
	public Pos getPosition() {
		return(getMem().getPosition());
	}

	/**
	 * Initializes the Player with the RoboCup server.
	 * @pre A RoboCup server is available.
	 * @post The Player has been initialized to the correct team.
	 */
	public void initPlayer() throws SocketException, UnknownHostException {
		
		rc.dsock = new DatagramSocket();
		rc.init(getParser(), getMem());
		
		try {
			move(-10, 0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("unused")
		Brain b = new Brain(this);
	}
	
	
	 /**
	 * Receives worldstate data from the RoboCup server.
	 * @pre A RoboCup server is available.
	 * @post The current worldstate has been stored in the Memory.
	 */
	public void receiveInput() throws InterruptedException  {		
		p.Parse(rc.receive(), m);
	}
	
	 /**
	 * Teleports the Player to the specified coordinates.
	 * @param x x-coordinate of the point to move the player to.
	 * @param y y-coordinate of the point to move the player to.
	 * @throws InterruptedException 
	 * @pre Playmode is before-kickoff, goal-scored, free-kick.
	 * @post The Player has been moved to the correct position.
	 */
	public void move(double x, double y) throws UnknownHostException, InterruptedException {
		rc.move(x, y);
	}
	
	 /**
	 * Causes Player to kick the ball.
	 * @param dir The direction in which to kick the ball in the form of a decimal value. 
	 * representing the angle in degrees in relation go the player.
	 * @param power The power with which to kick the ball in the form of a decimal value.
	 * @throws InterruptedException 
	 * @pre Playmode is play_on, ball is in kickable range.
	 * @post The ball has been kicked in the specified direction and power.
	 */
	public void kick(double power, double dir) throws UnknownHostException, InterruptedException {
		rc.kick(power, dir);
	}
	
	 /**
	 * Causes Player to dash.
	 * @param power The power with which to dash in the form of a decimal value.
	 * @throws Exception 
	 * @pre Play mode is play_on.
	 * @post The player has dashed at the given power.
	 */	
	public void dash(double power) throws Exception {
		rc.dash(power);
	}
	
	 /**
	 * Causes Player to turn according to a specified turn moment.
	 * @param moment The turn angle in degrees. 
	 * @throws InterruptedException 
	 * @pre Playmode is play_on, ball is in kickable range.
	 * @post The ball has been kicked in the specified direction and power.
	 */
	public void turn(double moment) throws UnknownHostException, InterruptedException {
		rc.turn(moment);
	}
	
	 /**
	 * Causes Player to say the given message.  It has a limitation of 512 characters by default.
	 * @param message The string to be spoken by the player. 
	 * @throws InterruptedException 
	 * @pre None
	 * @post The player has spoken the message.
	 */	
	public void say(String message) throws UnknownHostException, InterruptedException {
		rc.say(message);
	}
	
	/*
	 * Marks opposing players for defense
	 */
	public void markOpponent(String team, String number) {
		//b.setMarked_team(team);
		//b.setMarked_unum(number);
	}
	
	/*
	 * Follows opposing players on defense
	 * 
	 */
	public void runDefense() throws UnknownHostException, InterruptedException {
		//b.setDefensive();
		
		while (closestOpponent() == null){
			turn(30);
		}
		System.out.println("Closest Opponent: " + closestOpponent().getTeam() + " " + closestOpponent().getuNum());
		a.gotoPoint(getMem().m.getNextOpponentPoint(closestOpponent()));
		
		/*if (m.isObjVisible("player")) {
			markOpponent(m.getPlayer().getTeam(), Integer.toString(m.getPlayer().getuNum()));
			System.out.println("Marked Player " + b.getMarked_team() + " " + b.getMarked_unum());
		}		*/
	}
	
	/**
	 * Returns the closest opponent to the player
	 * @pre Players are in sight of the goalie.
	 * @post The closest opponent to the player has been determined.
	 * @return ObjPlayer
	 * @throws InterruptedException 
	 * @throws UnknownHostException 
	 */
	public ObjPlayer closestOpponent() throws UnknownHostException, InterruptedException {
		ObjPlayer closestOpponent = new ObjPlayer();
		double distance = 0;

		//Loop through arraylist of ObjPlayers
		for (int i = 0; i < getMem().getPlayers().size(); ++i) {

			if (!getMem().getPlayers().isEmpty()) {  
				if (distance == 0 && getMem().getPlayers().get(i).getTeam() != rc.getTeam()) {
					distance = getMem().getPlayers().get(i).getDistance();
					closestOpponent = getMem().getPlayers().get(i);
				}
				else {

					//Test if this player is closer than the previous one
					if (distance > getMem().getPlayers().get(i).getDistance() && getMem().getPlayers().get(i).getTeam() != rc.getTeam()) {
						distance = getMem().getPlayers().get(i).getDistance();
						closestOpponent = getMem().getPlayers().get(i);
					}
				}
			}
			else {  //No other players in player's sight, so turn to another point to check again
				turn(30);
				
				if (!getMem().getPlayers().isEmpty()) {  
					if (distance == 0 && getMem().getPlayers().get(i).getTeam() != rc.getTeam()) {
						distance = getMem().getPlayers().get(i).getDistance();
						closestOpponent = getMem().getPlayers().get(i);
					}
					else {
						//Test if this player is closer than the previous one
						if (distance > getMem().getPlayers().get(i).getDistance() && getMem().getPlayers().get(i).getTeam() != rc.getTeam()) {
							distance = getMem().getPlayers().get(i).getDistance();
							closestOpponent = getMem().getPlayers().get(i);
						}
					}
				}
				
			}
		}		
		return closestOpponent;
	}
	
	/**
	 * The Player thread run method. It makes decisions for the player.
	 * 
	 * @post Player will act on decisions made.
	 */
	public void run() {
		
		System.out.println("Player");
		
		while(true) {
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(getMem().timeCheck(getTime())) {
				setTime(getMem().ObjMem.getTime());
				
				try {
					getAction().findBall();
				} catch (UnknownHostException e) {
					System.out.println("Error in Brain.run findBall");
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}			
		} 		
	}	
}