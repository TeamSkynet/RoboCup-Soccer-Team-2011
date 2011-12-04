import java.net.UnknownHostException;



/**
 * @file Brain.java
 * @author Joel *
 */

/**@class Brain
 * The brain serves as a place to store the Player modes, marked players for
 * various functions, and a set of strategies for player actions.
 */
public class GoalieBrain extends Thread {

	private Mode currentMode = new Mode();	
	private Action actions = new Action();
	private String marked_team;
	private String marked_unum;
	public Goalie g;
	public Memory m;
	
	/**
	 * Default constructor
	 */
	public GoalieBrain() {
		super();
	}
	
	public GoalieBrain(Goalie g) {
		this.g = g;
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
	public GoalieBrain(Mode currentMode) {
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
	 * The Brain thread run method. It causes the Goalie to exhibit soccer behaviors.
	 * 
	 * @post Goalie will perform Goalie functions during match.
	 */
	public void run() {
		
		Pos uppergoalkick = new Pos(-47.36, -8.74);
		Pos lowergoalkick = new Pos(-47.36, 8.74);
		
		while (true) {

			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if(g.getMem().timeCheck(g.getTime())) {
				g.setTime(g.getMemTime());
				g.followBall();
				
				/*
				//Defining playmode behaviors for left side team
				if (g.getMem().side.compareTo("l") == 0) {				
					
					if(g.getMem().playMode.compareTo("play_on") == 0) {
						g.followBall();
					}
					else if (g.getMem().playMode.compareTo("goal_kick_l") == 0){
						Polar upper = g.getMem().getAbsPolar(g.getMem().getFlagPos("fplt"));
						try {
							g.turn(g.getAction().getTurn(upper));
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (g.getMem().isObjVisible("ball")) {
							g.getAction().gotoPoint(uppergoalkick);
							try {
								g.kick(60, 0);
							} catch (UnknownHostException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else {
							g.getAction().gotoPoint(lowergoalkick);
							try {
								g.kick(60, 0);
							} catch (UnknownHostException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					*/}					
				}	
				 // end if
				
				//Defining playmode behaviors for right side team
				/*if (p.getMem().side.compareTo("r") == 0) {

					if((p.getMem().playMode.compareTo("before_kick_off") == 0) && p.getTime() > 0) {
						p.move(p.getHome().x, p.getHome().y);
					}
					else if(p.getMem().playMode.compareTo("play_on") == 0) {
						p.getAction().findBall();
					}
					else if(p.getMem().playMode.compareTo("kick_off_r") == 0) {
						if (p.position.compareTo("center_fwd") == 0) {
							p.getAction().kickOff();
						}
					}
					else if (p.getMem().playMode.compareTo("goal_kick_r") == 0){
						p.getAction().goHome();
					}						
					else if (p.getMem().playMode.compareTo("free_kick_l") == 0) {
						p.getAction().goHome();
					}
				} //end if*/
			}
			
		
	}


