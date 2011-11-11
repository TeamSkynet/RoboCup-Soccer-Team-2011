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
	private Action actions = new Action();
	private String marked_team;
	private String marked_unum;
	public Player p;
	public Memory m;
	
	/**
	 * Default constructor
	 */
	public Brain() {
		super();
	}
	
	public Brain(Player p) {
		this.p = p;
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
	public Brain(Mode currentMode) {
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
	 * The Brain thread run method. It updates the Memory for the Player
	 * 
	 * @post Memory will continuously update 
	 */
	public void run() {

		while (true) {

			try {
				p.receiveInput();
			} catch (InterruptedException e) {
				System.out.println("Error in Brain.run()");
				e.printStackTrace();
			}
		}
	}
}

