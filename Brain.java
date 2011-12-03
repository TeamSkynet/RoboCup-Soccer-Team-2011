import java.net.UnknownHostException;



/**
 * @file Brain.java
 * @author Joel *
 */

/**@class Brain
 * The brain serves as a place to store the Player modes, marked players for
 * various functions, and a set of strategies for player actions.
 */
public class Brain extends Thread {

	private Mode currentMode = new Mode();
	private String marked_team;
	private String marked_unum;
	public Player p;
	public Memory m;
	

	/**
	 * Default constructor
	 */
	public Brain() {
		
	}

	public Brain(Player p) {
		this.p = p;
		start();
	}
	
	/**
	 * @return the currentMode
	 */
	public Mode getCurrentMode() {
		return currentMode;
	}

	/**
	 * Sets the player mode to defensive
	 */
	public void setDefensive() {
		this.currentMode.setModename("D");
	}
	
	/**
	 * Sets the player mode to be offensive
	 */
	public void setOffensive() {
		this.currentMode.setModename("O");
	}
	
	/**
	 * @return the marked_team
	 */
	public String getMarked_team() {
		return marked_team;
	}

	/**
	 * @param marked_team the marked_team to set
	 */
	public void setMarked_team(String marked_team) {
		this.marked_team = marked_team;
	}

	/**
	 * @return the marked_unum
	 */
	public String getMarked_unum() {
		return marked_unum;
	}

	/**
	 * @param marked_unum the marked_unum to set
	 */
	public void setMarked_unum(String marked_unum) {
		this.marked_unum = marked_unum;
	}
	
	public void run() {

		while(true) {

			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if(p.getMem().timeCheck(p.getTime())) {
				p.setTime(p.getMem().ObjMem.getTime());

				try {
					
					//p.getAction().findBall();
					
					//Defining playmode behaviors for left side team
					if (p.getMem().side.compareTo("l") == 0) {

						if((p.getMem().playMode.compareTo("before_kick_off") == 0) && p.getTime() > 0) {
							p.move(p.getHome().x, p.getHome().y);
						}
						else if(p.getMem().playMode.compareTo("kick_off_l") == 0) {

							p.getAction().findBall();

						}
						else if(p.getMem().playMode.compareTo("play_on") == 0) {
							p.getAction().findBall();
						}
						else if (p.getMem().playMode.compareTo("free_kick_r") == 0) {
							p.getAction().goHome();
						}
					}
					
					//Defining playmode behaviors for left side team
					if (p.getMem().side.compareTo("r") == 0) {

						if((p.getMem().playMode.compareTo("before_kick_off") == 0) && p.getTime() > 0) {
							p.move(p.getHome().x, p.getHome().y);
						}
						else if(p.getMem().playMode.compareTo("kick_off_r") == 0) {
							p.getAction().findBall();
						}
						else if(p.getMem().playMode.compareTo("play_on") == 0) {
							p.getAction().findBall();
						}
						else if (p.getMem().playMode.compareTo("free_kick_l") == 0) {
							p.getAction().goHome();
						}
					}

				} catch (InterruptedException e) {
					System.out.println("Interrupt Error in Brain.run");
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}