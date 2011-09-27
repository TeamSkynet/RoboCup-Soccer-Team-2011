import java.io.*;
import java.net.*;
import java.util.*;

public class Memory {
	
	public Memory() {
		ArrayList<ObjInfo> newObjArray = new ArrayList<ObjInfo>();
		ArrayList<SenseInfo> newSenArray = new ArrayList<SenseInfo>();
		int time = 0;
		ObjMem = new ObjMemory(newObjArray, time);
		SenMem = new SenseMemory(newSenArray, time);
	}
	
	// Get Object
	public ObjInfo getObj(int i) {
		return ObjMem.getObj(i);
	}
	
	// Get flag at index
	public ObjFlag getFlag(int i) {
		ObjFlag newFlag = new ObjFlag();
		if(getObj(i).getObjName().compareTo("flag") == 0) {
			newFlag = (ObjFlag) ObjMem.getObj(i);
		}
		return newFlag;
	}
	
	// Get flag at index boolean (this is a better way to do things)
	public boolean getFlag(ObjFlag newFlag, int i) {
		if(i > ObjMem.getSize())
			return false;
		else if(getObj(i).getObjName().compareTo("flag") == 0) {
			newFlag = (ObjFlag) getObj(i);
			return true;
		}
		else {
			return false;
		}
	}
	
	// Get flag by FlagName boolean (this is also pretty good)
	public boolean getFlag(ObjFlag newFlag, String name) {
		int i = 0;
		while(i < ObjMem.getSize()) {
			if((getFlag(newFlag, i)) && (newFlag.getFlagName().compareTo(name) == 0)){
				return true;
			}
			i++;
		}
		return false;
	}
	
	// Get index integer of Flag
	// if flag isn't in array, returns -1
	public int getFlagIndex(String name) {
		ObjFlag newFlag = new ObjFlag();
		int i = 0;
		while(i < ObjMem.getSize()) {
			if((getFlag(newFlag, i)) && (newFlag.getFlagName().compareTo(name) == 0))
				return i;
			i++;
		}
		return -1;
	}
	
	// Return ball object
	public ObjBall getBall() {
		return ball;
	}
	
	// If ball is visible, get ball, if not, get FALSE!
	public boolean getBall(ObjBall newBall) {
		if(isBallVisible) {
			newBall = ball;
			return true;
		}
		else
			return false;
	}
	
	// Get Player by index
	public ObjPlayer getPlayer(int i) {
		ObjPlayer newPlayer = new ObjPlayer();
		if(getObj(i).getObjName().compareTo("player") == 0) {
			newPlayer = (ObjPlayer) getObj(i);
		}
		return newPlayer;
	}
	
	// Get line by index
	public ObjLine getLine(int i) {
		ObjLine newLine = new ObjLine();
		if(getObj(i).getObjName().compareTo("Line") == 0) {
			newLine = (ObjLine) getObj(i);
		}
		return newLine;
	}
	
	// Get goal by index
	public ObjGoal getGoal() {
		ObjGoal newGoal = new ObjGoal();
		int i = 0;
		while(getObj(i).getObjName().compareTo("Goal") != 0) {
			i++;
		}
		newGoal = (ObjGoal) getObj(i);
		return newGoal;
	}
	
	// print all flag information to screen
	public void getAllFlags() {
		ObjFlag newFlag = new ObjFlag();
		for(int i = 0; i < ObjMem.getSize(); i++) {
			if(getFlag(newFlag, i)) {
				newFlag = (ObjFlag) ObjMem.getObj(i);
				System.out.println("FlagName: " + newFlag.getFlagName());
				if(newFlag.getFlagType().compareTo("b") == 0)
					System.out.println("FlagType: boundary");
				else if(newFlag.getFlagType().compareTo("g") == 0)
					System.out.println("FlagType: goal line");
				else if(newFlag.getFlagType().compareTo("c") == 0)
					System.out.println("FlagType: center line");
				else if(newFlag.getFlagType().compareTo("p") == 0)
					System.out.println("FlagType: penalty box");
				System.out.println("Distance: " + newFlag.getDistance());
				System.out.println("Direction: " + newFlag.getDirection());
				System.out.println("x_pos: " + newFlag.getX_pos());
				System.out.println("y_pos: " + newFlag.getY_pos());
				if(newFlag.getFlagType().compareTo("b") == 0)
					System.out.println("Yard: " + newFlag.getYard());
				if(isClose(i))
					System.out.println(newFlag.getFlagName() + " is close.");
				else
					System.out.println(newFlag.getFlagName() + " is not close.");
				System.out.println("");
			}
		}
	}
	
	// tell whether an object is close by index
	public boolean isClose(int i) {
		if(getObj(i).getDistance() < 5.0)
			return true;
		else
			return false;
	}
	
	// get distance by index
	public double getDistance(int i) {
		return getObj(i).getDistance();
	}
	
	
	public double getDistance(String name) {
		int i = 0;
		if(name.charAt(0) == 'f') {
			ObjFlag newFlag = new ObjFlag();
			while((newFlag.getFlagName().compareTo(name) != 0) && i < ObjMem.getSize()) {
				getFlag(newFlag, i);
				i++;
			}
			return newFlag.getDistance();
		}
		else {
			while(getObj(i).getObjName().compareTo(name) != 0) {
				i++;
			}
			return getObj(i).getDistance();
		}
	}
	
	public double getDirection(int i) {
		return getObj(i).getDirection();
	}
	
	public double getDirection(String name) {
		int i = 0;
		if(name.charAt(0) == 'f') {
			ObjFlag newFlag = new ObjFlag();
			while(newFlag.getFlagName().compareTo(name) != 0) {
				getFlag(newFlag, i);
				i++;
			}
			return newFlag.getDirection();
		}
		else {
			while(getObj(i).getObjName().compareTo(name) != 0) {
				i++;
			}
			return getObj(i).getDirection();
		}
	}
	
	public double getDistChng(int i) {
		return getObj(i).getDistChng();
	}
	
	public double getDistChng(String name) {
		int i = 0;
		if(name.charAt(0) == 'f') {
			ObjFlag newFlag = new ObjFlag();
			while(newFlag.getFlagName().compareTo(name) != 0) {
				getFlag(newFlag, i);
				i++;
			}
			return newFlag.getDistChng();
		}
		else {
			while(getObj(i).getObjName().compareTo(name) != 0) {
				i++;
			}
			return getObj(i).getDistChng();
		}
	}
	
	public double getDirChng(int i) {
		return getObj(i).getDirChng();
	}
	
	public double getDirChng(String name) {
		int i = 0;
		if(name.charAt(0) == 'f') {
			ObjFlag newFlag = new ObjFlag();
			while(newFlag.getFlagName().compareTo(name) != 0) {
				getFlag(newFlag, i);
				i++;
			}
			return newFlag.getDirChng();
		}
		else {
			while(getObj(i).getObjName().compareTo(name) != 0) {
				i++;
			}
			return getObj(i).getDirChng();
		}
	}
	
	public ObjBall ball;
	public boolean isBallVisible;
	public ObjMemory ObjMem;
	public SenseMemory SenMem;
}