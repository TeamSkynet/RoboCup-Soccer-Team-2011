/**
 * @file Pos.java
 * 
 * The Position vector for Cartesian Coordinates
 * 
 * @author Grant Hays
 * @date 10/11/11
 * @version 1
 * 
 */



/**
 * @class Pos
 * 
 * This class holds the information for Cartesian coordinate versions of positions of
 * players and objects
 *
 */
public class Pos {

	/**
	 * Default constructor
	 * 
	 * @post initializes x and y to 0 and name to space, so as not to have a 
	 * pointer error
	 * 
	 */
	public Pos() {
		name = " ";
		x = 0.0;
		y = 0.0;
	}
	
	/**
	 * Constructor with name
	 * 
	 * This is a constructor for coordinates that are given a name. It is mostly used for 
	 * the positions of the flags in the Field class
	 * 
	 * @param name The name associated with the Pos, for easier searching
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public Pos(String name, double x, double y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructor with no name
	 * 
	 * This is a constructor for positions that aren't given a name. Used for positions
	 * that change often.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public Pos(double x, double y) {
		this.x = x;
		this.y = y;
		name = " ";
	}
	
	public void print(String a) {
		System.out.println(a + " (" + x + ", " + y + ")");
	}
	
	public void print() {
		System.out.println("(" + x + ", " + y + ")");
	}
	
	public String name;
	public double x;
	public double y;
}
