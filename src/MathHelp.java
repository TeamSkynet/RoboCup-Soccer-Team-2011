/**
 * @file MathHelp.java
 * 
 * This has functions of the math I need for calculations.
 * 
 * @author granthays
 * @date 10/09/11
 * @version 135`
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;


public class MathHelp {

	/**
	 * Polar to Cartesian converter
	 * 
	 * @param r the length of the Polar arm
	 * @param t the angle, in degrees, of the arm from the x-axis
	 * @return A new Cartesian Pos converted from the r and t of a Polar vector
	 */
	public Pos getCartesian(double r, double t) {
		
		double x = r * Math.cos(Math.toRadians(t));
		double y = r * Math.sin(Math.toRadians(t));
		
		return(new Pos(x, y));
		
	}
	
	/**
	 * Polar to Cartesian wrapper
	 * 
	 * This allows you to pass a whole polar in, instead of extracting it's r
	 * and t variables and passing them in
	 * 
	 * @param p The polar coordinates you want to convert
	 * @return A new Pos with the Cartesian version of your Polar vector
	 */
	public Pos getPos(Polar p) {
		return(getCartesian(p.r, p.t));
	}
	
	/**
	 * Cartesian to polar converter
	 * 
	 * @param x the x coordinate of the Cartesian vector
	 * @param y the y coordinate of the Cartesian vector
	 * @return A new Polar vector converted from the Cartesian vector
	 */
	public Polar getPolar(double x, double y) {
		
		double r = Math.sqrt(x*x + y*y);
		double t = Math.toDegrees(Math.atan(y/x));
		
		return(new Polar(r, t));
	}
	
	/**
	 * Cartesian to polar wrapper
	 * 
	 * This is just a wrapper, so you can pass in a Pos
	 * instead of extracting it's x and y and passing them in.
	 * 
	 * @param p the Cartesian vector
	 * @return A new Polar vector converted from the Cartesian vector
	 */
	public Polar getPolar(Pos p) {
		return(getPolar(p.x, p.y));
	}
	
	/**
	 * Vector Addition
	 * 
	 * @param p1 first position
	 * @param p2 second position
	 * @return New position with the sum of the two arguments
	 */
	public Pos vAdd(Pos p1, Pos p2) {
		return(new Pos((p1.x + p2.x), (p1.y + p2.y)));
	}
	
	/**
	 * Vector Subtraction
	 * 
	 * @param p2 final position
	 * @param p1 initial position
	 * @return new Pos with the difference between p2 and p1
	 */
	public Pos vSub(Pos p2, Pos p1) {
		return(new Pos((p2.x - p1.x), (p2.y - p1.y)));
	}
	
	/**
	 * Magnitude
	 * Calculates the Magnitude of a vector, same as r in a Polar vector
	 * @param p the Pos of the vector
	 * @return A double containing the magnitude of the vector
	 */
	public double mag(Pos p) {
		return(Math.sqrt(p.x*p.x + p.y*p.y));
	}

	/**
	 * A normalizer
	 * @param p the vector to find the normal of
	 * @return a Pos of the unit vector of p
	 */
	public Pos norm(Pos p) {
		return(norm(mag(p), p));
	}
	

	/**
	 * A normalizer
	 * @param dist the magnitude of the vector
	 * @param a the vector to be normalized
	 * @return a Pos of the unit vector of p
	 */
	public Pos norm(double dist, Pos a) {
		return(new Pos((a.x/dist),(a.y/dist)));
	}
	
}
