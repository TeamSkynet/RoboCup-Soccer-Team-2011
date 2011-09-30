import java.net.*;
import java.util.*;
import java.io.*;
//*******************************************
//			- Parser Class -				*
//											*
// This does the parsing of incoming see or	*
// sense packets and stores information in	*
// ObjMemory or SenseMemory, respectively	*
//											*
//*******************************************
public class Parser {

	public Parser() {
	
	}

	
	
	public void Parse(String inputPacket, Memory InfoMem) {
		// Remove outer parentheses
		inputPacket = inputPacket.substring(1, inputPacket.length() - 1);
		
		// Split inputPacket into tokens by "(" and ")" delimiters
		String[] splitPacket = (inputPacket.split("[()]"));
		
		// Parse the first element into packet type and time
		String[] seeOrSense = (splitPacket[0].split(" "));
		

		
		// Call parse method based on packet type in position [0] (either see or sense_body)
		if(seeOrSense[0].compareTo("see") == 0) { 
			ArrayList<ObjInfo> seeArray = new ArrayList<ObjInfo>();
			// Input time (at [1] position)
			int time = Integer.parseInt(seeOrSense[1]);
			seeParse(seeArray, splitPacket);
			ObjMemory newObjMem = new ObjMemory(seeArray, time);
			InfoMem.ObjMem = newObjMem;
		}
		else if(seeOrSense[0].compareTo("sense_body") == 0) {
			ArrayList<SenseInfo> senArray = new ArrayList<SenseInfo>();
			// Input time (at [1] position)
			int time = Integer.parseInt(seeOrSense[1]);
			senseParse(senArray, splitPacket);
			SenseMemory newSenMem = new SenseMemory(senArray, time);
			InfoMem.SenMem = newSenMem;
		}
		
}
	
	

	// The (see) packet type parser
	private void seeParse(ArrayList<ObjInfo> seeArray, String[] splitPacket) {
		
		for(int i = 2; i < splitPacket.length; i += 4)
		{
			
		//************* ObjName *************
			// Split up the ObjName
			String[] splitName = (splitPacket[i].split(" "));
			String[] splitInfo = (splitPacket[i+1].split(" "));
			
			// Determine type of object:
			// - Flag -
			if(splitName[0].compareTo("f") == 0) {
				ObjFlag newFlag = new ObjFlag(splitPacket[i].replaceAll(" ", ""));
				seeFlagParse(splitName, splitInfo, newFlag);
				seeArray.add(newFlag);
			}
			// - Ball -
			else if(splitName[0].compareTo("b") == 0) {
				ObjBall newBall = new ObjBall();
				seeBallParse(splitInfo, newBall);
				seeArray.add(newBall);
			}
			
			// - Player -
			else if(splitName[0].compareTo("p") == 0) {
				ObjPlayer newPlayer = new ObjPlayer();
				seePlayerParse(splitName, splitInfo, newPlayer);
				seeArray.add(newPlayer);
			}
			
			// - Goal -
			else if(splitName[0].compareTo("g") == 0) {
				ObjGoal newGoal = new ObjGoal();
				seeGoalParse(splitName, splitInfo, newGoal);
				seeArray.add(newGoal);
			}
			
			// - Line -
			else if(splitName[0].compareTo("l") == 0) {
				ObjLine newLine = new ObjLine();
				seeLineParse(splitName, splitInfo, newLine);
				seeArray.add(newLine);
			}
		}
		
	}
	
	



	private void seeLineParse(String[] splitName, String[] splitInfo, ObjLine newLine) {
		// Set line's side of field
		newLine.setSide(splitName[1]);
		
		// Set line's Distance, Direction, DistChng, and DirChng
		if(splitInfo.length == 3) {
			newLine.setDistance(Double.valueOf(splitInfo[1]));
			newLine.setDirection(Double.valueOf(splitInfo[2]));
		}
		else {
			newLine.setDistance(Double.valueOf(splitInfo[1]));
			newLine.setDirection(Double.valueOf(splitInfo[2]));
			newLine.setDistChng(Double.valueOf(splitInfo[3]));
			newLine.setDirChng(Double.valueOf(splitInfo[4]));
		}
		
	}


	private void seeFlagParse(String[] splitName, String[] splitInfo, ObjFlag newFlag) {
		
		if(splitName[1].compareTo("c") == 0) {
			newFlag.setFlagType("c");
			newFlag.setX_pos("c");
			if(splitName.length == 3)
				newFlag.setY_pos(splitName[2]);
			else
				newFlag.setY_pos("c");
		}
		else if(splitName[1].compareTo("p") == 0) {
			newFlag.setFlagType("p");
			newFlag.setX_pos(splitName[2]);
			newFlag.setY_pos(splitName[3]);
		}
		else if(splitName[1].compareTo("g") == 0) {
			newFlag.setFlagType("g");
			newFlag.setX_pos(splitName[2]);
			newFlag.setY_pos(splitName[3]);
		}
		else if((splitName.length == 3) && (splitName[2].compareTo("0") != 0)) {
			newFlag.setFlagType("l");
			newFlag.setX_pos(splitName[1]);
			newFlag.setY_pos(splitName[2]);
		}
		else if((splitName.length == 3) && (splitName[2].compareTo("0") == 0)) {
			newFlag.setFlagType("b");
			
			if((splitName[1].compareTo("l") == 0) || (splitName[1].compareTo("r") == 0)) {
				newFlag.setX_pos(splitName[1]);
				newFlag.setY_pos("c");
				newFlag.setYard(splitName[2]);
			}
			else {
				newFlag.setX_pos(splitName[1]);
				newFlag.setY_pos("c");
				newFlag.setYard(splitName[2]);
			}
		}
		else {
			newFlag.setFlagType("b");
			
			if((splitName[1].compareTo("l") == 0) || (splitName[1].compareTo("r") == 0)) {
				newFlag.setX_pos(splitName[1]);
				newFlag.setY_pos(splitName[2]);
				newFlag.setYard(splitName[3]);
			}
			else {
				newFlag.setY_pos(splitName[1]);
				newFlag.setX_pos(splitName[2]);
				newFlag.setYard(splitName[3]);
			}
		}
		
		// Input info by determining how much info is available
			if(splitInfo.length == 3) {
				newFlag.setDistance(Double.valueOf(splitInfo[1]));
				newFlag.setDirection(Double.valueOf(splitInfo[2]));
			}
			else {
				newFlag.setDistance(Double.valueOf(splitInfo[1]));
				newFlag.setDirection(Double.valueOf(splitInfo[2]));
				newFlag.setDistChng(Double.valueOf(splitInfo[3]));
				newFlag.setDirChng(Double.valueOf(splitInfo[4]));
			}
	}
	
	private void seeBallParse(String[] splitInfo, ObjBall newBall) {
		
		// Input info by determining how much info is available
		if(splitInfo.length == 3) {
			newBall.setDistance(Double.valueOf(splitInfo[1]));
			newBall.setDirection(Double.valueOf(splitInfo[2]));
		}
		else {
			newBall.setDistance(Double.valueOf(splitInfo[1]));
			newBall.setDirection(Double.valueOf(splitInfo[2]));
			newBall.setDistChng(Double.valueOf(splitInfo[3]));
			newBall.setDirChng(Double.valueOf(splitInfo[4]));
		}
	}

	private void seePlayerParse(String[] splitName, String[] splitInfo, ObjPlayer newPlayer) {
		
		if(splitName.length == 2) {
			newPlayer.setTeam(splitName[1]);
		}
		else if(splitName.length == 3) {
			newPlayer.setTeam(splitName[1]);
			newPlayer.setuNum(Integer.parseInt(splitName[2]));
		}
		else if(splitName.length == 4) {
			newPlayer.setTeam(splitName[1]);
			newPlayer.setuNum(Integer.parseInt(splitName[2]));
			newPlayer.setGoalie(true);
		}
		
		if(splitInfo.length == 3) {
			newPlayer.setDistance(Double.valueOf(splitInfo[1]));
			newPlayer.setDirection(Double.valueOf(splitInfo[2]));
		}
		else if(splitInfo.length == 5) {
			newPlayer.setDistance(Double.valueOf(splitInfo[1]));
			newPlayer.setDirection(Double.valueOf(splitInfo[2]));
			newPlayer.setDistChng(Double.valueOf(splitInfo[3]));
			newPlayer.setDirChng(Double.valueOf(splitInfo[4]));
		}
		else if(splitInfo.length == 7) {
			newPlayer.setDistance(Double.valueOf(splitInfo[1]));
			newPlayer.setDirection(Double.valueOf(splitInfo[2]));
			newPlayer.setDistChng(Double.valueOf(splitInfo[3]));
			newPlayer.setDirChng(Double.valueOf(splitInfo[4]));
			newPlayer.setHeadDir((Double.valueOf(splitInfo[5])));
			newPlayer.setBodyDir((Double.valueOf(splitInfo[6])));
		}
		
	}

	private void seeGoalParse(String[] splitName, String[] splitInfo, ObjGoal newGoal) {
		
		// Set goal's side of field
		newGoal.setSide(splitName[1]);
		
		// Set goal's Distance, Direction, DistChng, and DirChng
		if(splitInfo.length == 3) {
			newGoal.setDistance(Double.valueOf(splitInfo[1]));
			newGoal.setDirection(Double.valueOf(splitInfo[2]));
		}
		else {
			newGoal.setDistance(Double.valueOf(splitInfo[1]));
			newGoal.setDirection(Double.valueOf(splitInfo[2]));
			newGoal.setDistChng(Double.valueOf(splitInfo[3]));
			newGoal.setDirChng(Double.valueOf(splitInfo[4]));
		}
		
	}
	
	private void senseParse(ArrayList<SenseInfo> senseArray, String[] splitPacket) {
		// input Stamina
		String[] splitStamina = splitPacket[3].split(" ");
		Stamina newStamina = new Stamina(Double.valueOf(splitStamina[1]), Double.valueOf(splitStamina[2]));
		senseArray.add(newStamina);
		
		// input Speed
		String[] splitSpeed = splitPacket[5].split(" ");
		Speed newSpeed = new Speed(Double.valueOf(splitSpeed[1]), Double.valueOf(splitSpeed[2]));
		senseArray.add(newSpeed);
		
		for(int i = 7; i < 24; i += 2) {
			String[] splitSense = splitPacket[i].split(" ");
			SenseInfo newSense = new SenseInfo(splitSense[0], Integer.parseInt(splitSense[1]));
			senseArray.add(newSense);
		}
		
		String[] splitFocusTarget = splitPacket[36].split(" ");
		String[] splitFocusCount = splitPacket[38].split(" ");
		if(splitFocusTarget[1].compareTo("none") != 0){
			Focus newFocus = new Focus(splitFocusTarget[1], Integer.parseInt(splitFocusTarget[2]), Integer.parseInt(splitFocusCount[1]));
			senseArray.add(newFocus);
		}
		
		String[] splitTackleExpires = splitPacket[42].split(" ");
		String[] splitTackleCount = splitPacket[44].split(" ");
		Tackles newTackles = new Tackles(Integer.parseInt(splitTackleExpires[1]), Integer.parseInt(splitTackleCount[1]));
		senseArray.add(newTackles);
		
	}
	
	public String input;
	
}
