/**
 * @file SenseMemory.java
 * 
 * Container for parsed (sense_body) information
 * 
 * @author Grant Hays
 * @date 09/10/11
 * @version 1
 */

import java.net.*;
import java.util.*;
import java.io.*;

/**
 * @class SenseMemory
 * 
 * This holds all the usable information parsed from the (sense_body)
 * message sent from the server. It holds information about a Player's
 * stamina, speed, and head direction angle, as well as the time parsed.
 * 
 */
public class SenseMemory {
	
	/**
	 * Default constructor
	 * 
	 * @post initializes time to 0
	 */
	public SenseMemory() {
		time = 0;
	}
	
	/**
	 * Constructor with time
	 * 
	 * @param time The time the information was parsed, as told by the server.
	 * @post A new SenseMemory with updated time
	 */
	public SenseMemory(int time) {
		this.time = time;
	}
	
	/**
	 * The time getter
	 * @returns the time that the SenseMemory was parsed
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * The time setter
	 * @param t the time hat the SenseMemory was parsed
	 */
	public void setTime(int t) {
		this.time = t;
	}
	
	/**
	 * Time setter from the unparsed message sent by server
	 * @param seeOrSense A String array with the split first argument of a (see) message from
	 * the server
	 */
	public void setTime(String[] seeOrSense) {
		this.time = Integer.parseInt(seeOrSense[1]);
	}
	
	// Stamina information
	public double stamina;
	public double recovery;
	public double effort;
	
	// Speed information
	public double amountOfSpeed;
	public double directionOfSpeed;
	
	// Direction head is facing relative to the body
	public double headDirection;
	
	// The time the (sense_body) message was parsed
	private int time;
	
	
}
