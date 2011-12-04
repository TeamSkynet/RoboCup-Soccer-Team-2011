import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/** @file FullBack.java
* Class file for FullBack class
* @author Joel Tanzi
* @date 5 November 2011
* @version 1.0 
*/

/** @class FullBack
* The FullBack class inherits from the Player class.  The FullBack is a specialized
* type of Player that focuses on defensive behaviors such as interfering with opponent scoring.
*/
public class FullBack extends Player{

	/**
	 * 
	 */
	public FullBack() {
		super();
	}

	/**
	 * @param rc
	 * @param m
	 * @param i
	 * @param p
	 * @param time
	 */
	public FullBack(RoboClient rc, Memory m, ObjInfo i, Parser p, int time) {
		super(rc, m, i, p, time);
	}

	/**
	 * @param team
	 */
	public FullBack(String team) {
		super(team);
	}

	 /**
	 * Initializes the Player with the RoboCup server as a goalie.
	 * @pre A RoboCup server is available.
	 * @post The Player has been initialized to the correct team as a goalie.
	 */
	public void initFullBack(double x, double y) throws SocketException, UnknownHostException {

		rc.dsock = new DatagramSocket();
		rc.init(getParser(), getMem());
		getMem().home = new Pos(x, y);
		try {
			move(x,y);
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		@SuppressWarnings("unused")
		FullBackBrain b = new FullBackBrain(this);
	}
	
	
	 /**
	 * Initializes the Player with the RoboCup server as a goalie.
	 * @pre A RoboCup server is available.
	 * @post The Player has been initialized to the correct team as a goalie.
	 */
	public void initFullBack(double x, double y, String pos) throws SocketException, UnknownHostException {
		position = pos;
		rc.dsock = new DatagramSocket();
		rc.init(getParser(), getMem());
		getMem().home = new Pos(x, y);
		try {
			move(x,y);
			Thread.sleep(100);
			if(getMem().side.compareTo("r") == 0) {
				turn(180);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		@SuppressWarnings("unused")
		FullBackBrain b = new FullBackBrain(this);
	}
	
	/**
	 * Returns the closest player to the FullBack on the same team.
	 * @post The closest player to the FullBack has been determined.
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
			else {  //No players in Fullback's sight, so turn to another point to check again
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
	
	/*
	 * Checks to see if the FullBack is on his own side of the field.
	 * @return boolean true if he is, false if he is not.
	 */
	public boolean inFullBackZone(){
		if (getMem().getPosition().x < -10) {
			return true;
		}
		else {
			return false;
		}
	}

	/*
	 * Defines Fullback defensive behavior.
	 * @pre Playmode is play_on.
	 */
	public void runDefense() throws UnknownHostException, InterruptedException {
		if (!inFullBackZone()) {
			getAction().goHome();
		}
		
		getAction().FullBack_findBall();
	}
	
	/*
	 * Defines ongoing FullBack soccer behaviors for his own thread.
	 */
	public void run() {
		while(true) {
			try {
				receiveInput();
			} catch (InterruptedException e) {
				System.out.println("Interrupt error in FullBack.run");
				e.printStackTrace();
			}
			
			if(getMem().current != null) {
				Pos pt = mh.vSub(getMem().current, getMem().home);
				
				if(mh.mag(pt) > 0.5) {
					getMem().isHome = false;
				}
				else
					getMem().isHome = true;
			}
			else 
				System.out.println("Current is null");		
		}
	}	
}
