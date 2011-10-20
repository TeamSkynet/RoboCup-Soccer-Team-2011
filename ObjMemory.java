/**
 * @file ObjMemory.java
 * 
 * A container for ObjInfo's visible to the player after a parse
 * 
 * @author Grant Hays
 * @date 09/03/11
 * @version 1
 * 
 */

import java.net.*;
import java.util.*;
import java.io.*;


/**
 * @class ObjMemory
 * 
 * The ObjMemory saves all the ObjInfo (and it's children) objects 
 * from a parse into ArrayList along with the time parsed. 
 *
 */
class ObjMemory {
	
	/**
	 * Default constructor
	 * 
	 * This initializes the time to 0
	 */
	public ObjMemory() {
		setTime(0);
	}
	
	/**
	 * ObjMemory constructor
	 * 
	 * @param ObjArray the ArrayList containing all the ObjInfos from
	 * the server's parsed (see) message
	 * @param t the time parsed from the server's (see) message
	 * @pre This should only be called inside of the parser. It's merely a way
	 * to store ObjInfos from the (see) message into the greater Memory
	 * class
	 * @post A new ObjMemory containing the list of visible ObjInfos and the
	 * most recent time will be availbe to add to the Memory
	 */
	public ObjMemory(ArrayList<ObjInfo> ObjArray, int t) {
		this.ObjArray = ObjArray;
		this.time = t;
	}
	
	/**
	 * A method to add new ObjInfo to the ObjMemory
	 * 
	 * @param newInfo the ObjInfo to add tot he ObjMemory's ArrayList
	 * @pre A non-null ObjInfo will be passed into the method
	 * @post The newInfo will be added to the ObjArray
	 */
	public void addInfo(ObjInfo newInfo) {
		ObjArray.add(newInfo);
	}
	
	/**
	 * A method to access the time the message was parsed, provided
	 * by the server's (see) message
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * The time setter
	 * 
	 * @param t the time integer from the server's latest (see) parse
	 * @post the time will be set and ready to access
	 */
	public void setTime(int t) {
		this.time = t;
	}
	
	/**
	 * Returns the size of the ObjArray
	 */
	public int getSize() {
		return ObjArray.size();
	}
	
	/**
	 * An accessor of individual ObjInfo
	 * 
	 * @param index the index of the ObjInfo to retrieve
	 * @pre The ObjArray should have at least one ObjInfo in it
	 * @post The ObjInfo at the given index will be returned, this is a
	 * good way to traverse the ObjInfos visible to you
	 */
	public ObjInfo getObj(int index) {
		return ObjArray.get(index);
	}
	
	/**
	 * A method to get an ObjInfo by name
	 * 
	 * @param name the ObjName of the ObjInfo searched for (e.g. "ball")
	 * 
	 * @pre The ObjInfo should be checked for visibility first, 
	 * otherwise you run the risk of getting an empty ObjInfo
	 * @post The first ObjInfo with the name will be returned. Remember, this won't
	 * return all the ObjInfos of an ObjName, if there are multiple.
	 */
	public ObjInfo getObj(String name) {
		String searchName = "";
		ObjInfo foundObj = new ObjInfo();
		int i = 0;
		while(searchName.compareTo(name) != 0) {
			foundObj = getObj(i);
			searchName = foundObj.getObjName();
			i++;
		}
		
		return foundObj;
	}

	// ArrayList to store ObjInfo
	public ArrayList<ObjInfo> ObjArray;
	// The time integer
	private int time;
	
}
