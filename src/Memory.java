import java.util.ArrayList;


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
		int time = 0;
		ObjMem = new ObjMemory(newObjArray, time);
		SenMem = new SenseMemory();
	}
	
	public void setField(String side) {
		f = new Field(side);
		OppGoal = getFlagPos("g" + side);
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
	
	public ArrayList<ObjPlayer> getPlayers() {
		ArrayList<ObjPlayer> players = new ArrayList<ObjPlayer>();
		for(int i = 0; i< ObjMem.getSize(); i++) {
			if(getObj(i).getObjName().compareTo("player") == 0) {
				players.add((ObjPlayer) getObj(i));
			}				
		}
		return players;
	}
	
	
	/**
	 * ****** Make sure you check visibility first! *******
	 * If you don't, you will get a null object, and nobody wants that.
	 * 
	 * This will get the ObjLine closest to the player.
	 * 
	 * @return ObjLine
	 */
	public ObjLine getClosestLine() {
		ObjLine line = new ObjLine();
		ObjLine closestLine = null;
		double dist = 100.0;
		
		for(int i = 0; i < getObjMemorySize(); i++) {
			if(getObj(i).getObjName().compareTo("line") == 0) {
				line = (ObjLine) getObj(i);
				if(line.getDistance() < dist) {
					closestLine = line;
				}
			}
		}
		
		return closestLine;
	}

	
	public boolean timeCheck(int t) {
		if(t < ObjMem.getTime())
			return true;
		else
			return false;
	}
	
	public void setLocation(double x, double y) {
		this.pos.x = x;
		this.pos.y = y;
	}
	
	public ObjFlag getClosestFlag() {
		boolean first = false;
		ObjFlag flag = new ObjFlag();
		for(int i = 0; i < ObjMem.getSize(); i++) {
			if(getObj(i).getObjName().compareTo("flag") == 0) {
				
				if(!first)
					flag = (ObjFlag) getObj(i);
				else {
					if(getObj(i).getDistance() < flag.getDistance())
						flag = (ObjFlag) getObj(i);
				}
				
			}
		}
		
		return flag;
	}
	
	public ObjFlag getClosestBoundary() {
		ObjFlag flag = new ObjFlag();
		ObjFlag closestFlag = null;
		double dist = 100.0;
		
		for(int i = 0; i < getObjMemorySize(); i++) {
			if(getObj(i).getObjName().compareTo("flag") == 0) {
				flag = (ObjFlag) getObj(i);
				if((flag.getFlagType().compareTo("b") == 0) && (flag.getDistance() < dist)) {
					closestFlag = flag;
					dist = flag.getDistance();
				}
			}
		}
		
		return closestFlag;
	}
	
	public Pos getFlagPos(String flagName) {
		for(int i = 0; i < f.posList.size(); i++) {
			if(f.posList.get(i).name.compareTo(flagName) == 0)
				return f.posList.get(i);
		}
		
		return null;
		
	}
	

	
// ******************* SenseMemory *******************
	public double getStamina(){
		return SenMem.stamina;
	}
	
	public double getRecovery(){
		return SenMem.recovery;
	}
	
	public double getEffort() {
		return SenMem.effort;
	}
	
	public double getAmountOfSpeed() {
		return SenMem.amountOfSpeed;
	}
	
	public double getDirectionOfSpeed() {
		if(SenMem.directionOfSpeed == 0.0)
			return 0.0;
		else
			return (-1 * SenMem.directionOfSpeed);
	}
	
	public double getHeadDirection() {
		return SenMem.headDirection;
	}
	
// ****************** Hear Memory ********************
	
	public String getPlayMode() {
		return playMode;
	}
	
// ***************** Class Variables *****************
	
	public Field f;
	public Pos pos;
	/**
	 * The memory that stores all parsed ObjInfo
	 */
	public ObjMemory ObjMem;
	/**
	 * The memory that stores all parsed SenseInfo
	 */
	public SenseMemory SenMem;
	/**
	 * The play mode as told by the referee
	 */
	public String playMode;
	public String side;
	public Double uNum;
	public Pos OppGoal;

	
}



