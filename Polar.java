/**
 * @class Polar
 * 
 * A container for polar coordinates. It holds distance (r) and direction (t) of an
 * object with respect to the player.
 * 
 * @author Grant Hays
 * @date 10/14/ll
 * @version 1 
 *
 */
public class Polar {

	/**
	 * Default constructor
	 * 
	 * @post initializes distance and angle to 0.0
	 */
	public Polar() {
		r = 0.0;
		t = 0.0;
	}
	
	/**
	 * Constructor with parameters
	 * 
	 * @param r The length of the distance to the object
	 * @param t The angle of the object from the players line of sight
	 */
	public Polar(double r, double t) {
		this.r = r;
		this.t = t;
	}
	
	public void print(String a) {
		System.out.println(a + " (" + r + ", " + t + ")");
	}
	
	public void print() {
		System.out.println("(" + r + ", " + t + ")");
	}
	

	public double r;
	public double t;
}
