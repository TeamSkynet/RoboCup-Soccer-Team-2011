import java.net.UnknownHostException;



/**
 * @file Brain.java
 * @author Joel *
 */

/**@class Brain
 * The brain serves as a place to store the Player modes, marked players for
 * various functions, and a set of strategies for player actions.
 */
public class FullBackBrain extends Thread {

	private Mode currentMode = new Mode();	
	private Action actions = new Action();
	private String marked_team;
	private String marked_unum;
	public FullBack f;
	public Memory m;
	
	/**
	 * Default constructor
	 */
	public FullBackBrain() {
		super();
	}
	
	public FullBackBrain(FullBack f) {
		this.f = f;
		start();
	}
	
	/**
	 * @return the actions
	 */
	public Action getActions() {
		return actions;
	}

	/**
	 * @param actions the actions to set
	 */
	public void setActions(Action actions) {
		this.actions = actions;
	}

	/**
	 * Constructor
	 * @param currentMode
	 */
	public FullBackBrain(Mode currentMode) {
		super();
		this.currentMode = currentMode;
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

	/**
	 * The FullBackBrain thread run method. It instructs the FullBack in soccer behaviors
	 * 
	 * @post FullBack will act accordingly during match. 
	 */
	public void run() {

		while (true) {
			//System.out.println("in FullBackBrain");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if(f.getMem().timeCheck(f.getTime())) {
				f.setTime(f.getMemTime());

				try {
					f.runDefense();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}		
		}
	}
}