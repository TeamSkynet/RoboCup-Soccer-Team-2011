/**
 * @file Mode.java
 * @author Joel Tanzi*
 * @date 18 October 2011
 * @version 1.0
 */

/**@class Mode
 * The Mode class is a basic data structure to store the parameters for the player modes. 
 */
public class Mode {
	
	private String modename = new String();
	private double timeinmode;
	
	public Mode() {
		this.modename = "";
		this.timeinmode = 0.0;
	}
	
	/**
	 * @param modename
	 * @param timeinmode
	 */
	public Mode(String modename, double timeinmode) {
		super();
		this.modename = modename;
		this.timeinmode = timeinmode;
	}
	/**
	 * @return the modename
	 */
	public String getModename() {
		return modename;
	}
	/**
	 * @param modename the modename to set
	 */
	public void setModename(String modename) {
		this.modename = modename;
	}
	/**
	 * @return the timeinmode
	 */
	public double getTimeinmode() {
		return timeinmode;
	}
	/**
	 * @param timeinmode the timeinmode to set
	 */
	public void setTimeinmode(double timeinmode) {
		this.timeinmode = timeinmode;
	}
	
	
}
