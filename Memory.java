import java.net.*;
import java.util.*;
<<<<<<< HEAD
import java.io.*;
=======

>>>>>>> 8f7dfe5c8b9eb29cbdde644ef0f548f448dec07b
/**
 * @author Grant Hays
 * The Memory class stores instances of ObjMemory and SenseMemory and supplies
 * methods to access their innards.
 */
public class Memory {
	/**
	 * The default constructor for the Memory.
	 * 
	 * This creates new, empty ArrayList for the ObjMemory and SenseMemory, initiates
	 * the time at 0 for both, and creates an ObjMemory and SenseMemory with the new
	 * ArrayLists and time as parameters.
	 */
	public Memory() {
		ArrayList<ObjInfo> newObjArray = new ArrayList<ObjInfo>();
		ArrayList<SenseInfo> newSenArray = new ArrayList<SenseInfo>();
		int time = 0;
		ObjMem = new ObjMemory(newObjArray, time);
		SenMem = new SenseMemory(newSenArray, time);
	}
	
	/**
	 * The ObjInfo getter
	 * 
	 * This fetches the ObjInfo at index i of the ArrayList ObjArray in ObjMemory, 
	 * and returns it as an ObjInfo.
	 * 
	 * @param i the index number of the location of the desired ObjInfo in ObjArray
	 * @return ObjInfo the ObjInfo at location i of the ObjArray
	 */
	// Get Object
	public ObjInfo getObj(int i) {
		return ObjMem.getObj(i);
	}
	
	/**
	 * The ObjMemory size
	 * 
	 * A getter to quickly retrieve the number of ObjInfo in ObjMemory
	 * 
	 * @return size of ObjMemory
	 */
	public int getObjMemorySize() {
		return ObjMem.getSize();
	}
	
	/**
	 * The SenseMemory size
	 * 
	 * A getter to quickly retrieve the number of SenseInfo in SenseMemory.
	 * 
	 * @return size of ObjMemory
	 */
	public int getSenseMemorySize() {
		return SenMem.getSize();
	}
	
	/**
	 * Is this ObjInfo visible?
	 * 
	 * @param name the ObjName of the ObjInfo we're detecting visibility of
	 * @return true if the ball is in the ObjMemory, false if it is not or if the the ObjMemory is empty
	 */
	public boolean isObjVisible(String name) {
		if(ObjMem.getSize() == 0)
			return false;
		else {
			for(int i = 0; i < ObjMem.getSize(); i++) {
				if(getObj(i).getObjName().compareTo(name) == 0)
					return true;
			}
			return false;
		}
	}
	
	/**
	 * The Ball Getter
	 * 
	 * ****** Make sure you check visibility first! *******
	 * If you don't, you will get a null object, and nobody wants that.
	 * 
	 * @return ObjBall containing the ball
	 */
	public ObjBall getBall() {
		for(int i = 0; i < ObjMem.getSize(); i++) {
			if(getObj(i).getObjName().compareTo("ball") == 0)
				return (ObjBall) getObj(i);
		}
		return null;
	}
	
	/**
	 * The Flag Getter
	 * 
	 * 
	 * ****** Make sure you check visibility first! *******
	 * If you don't, you will get a null object, and nobody wants that.
	 * 
	 * If you're looking for a specific flag, this is you're guy. You need to
	 * pass in the FlagName (i.e. flb30) into it, and out pops the ObjFlag
	 * with that FlagName attached to it.
	 * 
	 * @param name
	 * @return ObjFlag containing the FlagName you input
	 */
	public ObjFlag getFlag(String name) {
		ObjFlag newFlag = new ObjFlag();
<<<<<<< HEAD
		for(int i = 0; i < ObjMem.getSize(); i++) {
			if(getObj(i).getObjName().compareTo("flag") == 0)
				newFlag = (ObjFlag) getObj(i);
				if(newFlag.getFlagName().compareTo(name) == 0)
					return newFlag;
		}
		return null;
	}
	
	/**
	 * The Goal Getter
	 * 
	 * ****** Make sure you check visibility first! ******* 
	 * If you don't, you will get a null object, and nobody wants that.
	 * 
	 * This will get the ObjGoal closest to you.
	 * 
	 * @return ObjGoal containing the goal closest to you
	 */
	public ObjGoal getGoal() {
		for(int i = 0; i < ObjMem.getSize(); i++) {
			if(getObj(i).getObjName().compareTo("goal") == 0)
				return (ObjGoal) getObj(i);
		}
		return null;
	}
	
	/**
	 * The Player Getter
	 * 
	 * ****** Make sure you check visibility first! ******* 
	 * If you don't, you will get a null object, and nobody wants that.
	 * 
	 * This will get the ObjPlayer of the first player you see.
	 * 
	 * @return ObjPlayer
	 */
	public ObjPlayer getPlayer() {
		for(int i = 0; i < ObjMem.getSize(); i++) {
			if(getObj(i).getObjName().compareTo("player") == 0)
				return (ObjPlayer) getObj(i);
=======
		for(int i = 0; i < ObjMem.getSize(); i++) {
			if(getObj(i).getObjName().compareTo("flag") == 0)
				newFlag = (ObjFlag) getObj(i);
				if(newFlag.getFlagName().compareTo(name) == 0)
					return newFlag;
		}
		return null;
	}
	
	/**
	 * The Goal Getter
	 * 
	 * ****** Make sure you check visibility first! ******* 
	 * If you don't, you will get a null object, and nobody wants that.
	 * 
	 * This will get the ObjGoal closest to you.
	 * 
	 * @return ObjGoal containing the goal closest to you
	 */
	public ObjGoal getGoal() {
		for(int i = 0; i < ObjMem.getSize(); i++) {
			if(getObj(i).getObjName().compareTo("goal") == 0)
				return (ObjGoal) getObj(i);
		}
		return null;
	}
	
	/**
	 * The Player Getter
	 * 
	 * ****** Make sure you check visibility first! ******* 
	 * If you don't, you will get a null object, and nobody wants that.
	 * 
	 * This will get the ObjPlayer of the first player you see.
	 * 
	 * @return ObjPlayer
	 */
	public ObjPlayer getPlayer() {
		for(int i = 0; i < ObjMem.getSize(); i++) {
			if(getObj(i).getObjName().compareTo("player") == 0)
				return (ObjPlayer) getObj(i);
		}
		return null;
	}
	
	/**
	 * ****** Make sure you check visibility first! *******
	 * If you don't, you will get a null object, and nobody wants that.
	 * 
	 * This will get the ObjLine of the first line you see.
	 * 
	 * @return ObjLine
	 */
	public ObjLine getLine() {
		for(int i = 0; i < ObjMem.getSize(); i++) {
			if(getObj(i).getObjName().compareTo("line") == 0)
				return (ObjLine) getObj(i);
>>>>>>> 8f7dfe5c8b9eb29cbdde644ef0f548f448dec07b
		}
		return null;
	}
	
<<<<<<< HEAD
	/**
	 * ****** Make sure you check visibility first! *******
	 * If you don't, you will get a null object, and nobody wants that.
	 * 
	 * This will get the ObjLine of the first line you see.
	 * 
	 * @return ObjLine
	 */
	public ObjLine getLine() {
		for(int i = 0; i < ObjMem.getSize(); i++) {
			if(getObj(i).getObjName().compareTo("line") == 0)
				return (ObjLine) getObj(i);
		}
		return null;
	}
	

	
	
// ******************* SenseMemory *******************
	
	
// ***************** Class Variables *****************
	
	
=======

	
	
	
	
	
	
// ***************** Class Variables *****************
	
	
>>>>>>> 8f7dfe5c8b9eb29cbdde644ef0f548f448dec07b
	/**
	 * The memory that stores all parsed ObjInfo
	 */
	public ObjMemory ObjMem;
	/**
	 * The memory that stores all parsed SenseInfo
	 */
	public SenseMemory SenMem;
}



