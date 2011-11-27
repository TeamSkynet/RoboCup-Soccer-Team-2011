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

	public boolean inFullBackZone(){
		if (getMem().getPosition().x < -10) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void runDefense() throws UnknownHostException, InterruptedException {
		if (!inFullBackZone()) {
			getAction().goHome();
		}
		
		getAction().FullBack_findBall();
	}
	
	public void run() {
		while(true) {
			try {
				receiveInput();
			} catch (InterruptedException e) {
				System.out.println("Interrupt error in FullBack.run");
				e.printStackTrace();
			}
			//System.out.println(getTime());
		}
	}	
}
