import java.net.*;
import java.util.*;
import java.io.*;

//*******************************************
//			- ObjInfo Class -				*
//											*
// This class implements an container to	*
// store and access information about		*
// game objects.							*
//											*
//*******************************************
class ObjInfo {
	
	// Default constructor
	public ObjInfo() {
	}
	
	// * ObjInfo Constructor *
	// Input:	-String name - name of game Object
	//
	// Output:	-none
	public ObjInfo(String name) {
		setObjName(name);
		setDistance(0.0);
		setDirection(0.0);
		setDistChng(0.0);
		setDirChng(0.0);
	}
	
	// * A method to access ObjInfo name *
	// Input:	-none
	//
	// Output:	-String containing name of object
	public String getObjName() {
		return ObjName;
	}
	
	// * A method to set Object name *
	// Input:	-String name - The name of game Object
	//
	// Output:	-none
	public void setObjName(String name) {
		ObjName = name;
	}

	// * A method to access ObjInfo side *
	// Input:	-none
	//
	// Output:	-String containing side of object (l or r)
	public String getSide() {
		return side;
	}
	
	// * A method to set ObjInfo side *
	// Input:	-String objSide - the side of the object
	//
	// Output:	-none
	public void setSide(String objSide) {
		this.side = objSide;
	}


	// * A method to access ObjInfo distance *
	// Input:	-none
	//
	// Output:	-double containing distance of player from object
	public double getDistance() {
		return distance;
	}

	// * A method to set ObjInfo distance *
	// Input:	-double distance - the distance of player from object
	//
	// Output:	-none
	public void setDistance(double distance) {
		this.distance = distance;
	}

	// * A method to access ObjInfo direction *
	// Input:	-none
	//
	// Output:	-double containing direction of player from object
	public double getDirection() {
		return direction;
	}

	// * A method to set ObjInfo direction *
	// Input:	-double containing direction of player from object
	//
	// Output:	-none
	public void setDirection(double direction) {
		this.direction = direction;
	}

	// * A method to access ObjInfo direction change *
	// Input:	-none
	//
	// Output:	-double containing direction change of player from object
	public double getDistChng() {
		return distChng;
	}

	// * A method to set ObjInfo distance change *
	// Input:	-double containing distance change of player from object
	//
	// Output:	-none
	public void setDistChng(double distChng) {
		this.distChng = distChng;
	}

	// * A method to access ObjInfo direction change *
	// Input:	-none
	//
	// Output:	-double containing direction change of player from object
	public double getDirChng() {
		return dirChng;
	}

	// * A method to set ObjInfo direction *
	// Input:	-double containing distance of player from object
	//
	// Output:	-none
	public void setDirChng(double dirChng) {
		this.dirChng = dirChng;
	}


	private String ObjName;
	private String side;
	private double distance;
	private double direction;
	private double distChng;
	private double dirChng;

}

// class for ball ObjInfo
class ObjBall extends ObjInfo {

	public ObjBall() {
		super("ball");
	}
}

// class for goal ObjInfo
class ObjGoal extends ObjInfo {

	public ObjGoal() {
		super("goal");
	}
}

// class for flag ObjInfo
class ObjFlag extends ObjInfo {
	
	public ObjFlag() {
		
	}

	public ObjFlag(String name) {
		super("flag");
		flagName = name;
	}
	
	public String getFlagType() {
		return flagType;
	}
	public void setFlagType(String flagType) {
		this.flagType = flagType;
	}

	public String getFlagName() {
		return flagName;
	}
	public void setFlagName(String name) {
		this.flagType = name;
	}
	
	public String getX_pos() {
		return x_pos;
	}

	public void setX_pos(String x_pos) {
		this.x_pos = x_pos;
	}

	public String getY_pos() {
		return y_pos;
	}

	public void setY_pos(String y_pos) {
		this.y_pos = y_pos;
	}


	public String getYard() {
		return yard;
	}

	public void setYard(String yard) {
		this.yard = yard;
	}

	private String flagName;
	private String flagType;
	private String x_pos;
	private String y_pos;
	private String yard;
}

// class for player ObjInfo
class ObjPlayer extends ObjInfo {

	public ObjPlayer() {
		super("Player");
	}
	
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}

	public int getuNum() {
		return uNum;
	}

	public void setuNum(int uNum) {
		this.uNum = uNum;
	}

	public boolean isGoalie() {
		return goalie;
	}

	public void setGoalie(boolean goalie) {
		this.goalie = goalie;
	}

	public double getHeadDir() {
		return headDir;
	}

	public void setHeadDir(double headDir) {
		this.headDir = headDir;
	}

	public double getBodyDir() {
		return bodyDir;
	}

	public void setBodyDir(double bodyDir) {
		this.bodyDir = bodyDir;
	}

	private String team;
	private int uNum;
	private boolean goalie;
	private double headDir;
	private double bodyDir;
	
}

// class for line ObjInfo
class ObjLine extends ObjInfo {
	
	public ObjLine() {
		super("line");
	}
}
