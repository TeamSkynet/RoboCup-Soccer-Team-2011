//********************
//*
//* This is an example of how to use the Parser and Memory classes
//*
//*
//* - 10/04/11 - Added a method to check the Memory time against a local time  to fix the over-command-sending problem
//*
//********************


import java.io.*;
import java.net.*;
import java.util.*;

public class ParserTest {

	BufferedReader readin = new BufferedReader(new InputStreamReader(System.in));
	String command = new String();
	
	public static void main(String args[]) throws Exception
	{
		//Instantiate each player client
		RoboClient rc1 = new RoboClient();
		//Instantiate new Memory for player 1
		Memory mem1 = new Memory();
		//Instantiated Parser
		Parser p = new Parser();
		

		//Set up connection to RoboCup server
		rc1.dsock = new DatagramSocket();
		
		//Initiate the player into the field
		rc1.init(p, mem1);
		//Move the player onto the field (right next to the ball, mind you)
		rc1.move(-10, 0);

		
		
		// Set initial local time at 0
		int time = 0;
		
		// Main loop
		while(true) {
			// Call the Parser with the packets from the client and the memory
			p.Parse(rc1.receive(), mem1);
			
			//**********************************************************************************
			// - Test the local time against the Memory's time -
			// 
			// The timeCheck compares the local time to the time parsed into the ObjMemory. It 
			// returns true if the local time is smaller than the time in the memory. This will 
			// make sure that actions are made once per Simulation Step.
			//
			// Every time a timeCheck returns true, the local time needs to be updated with the 
			// Memory's time.
			//**********************************************************************************
			if(mem1.timeCheck(time)) {
				// this is where the local time is updated with the Memory time
				time = mem1.ObjMem.getTime();
				
				// This just shows how the getPlayMode() works.
				System.out.println(mem1.getPlayMode());
				
				// Checking the visibility of the ball
				if(mem1.isObjVisible("ball")) {
					ObjBall ball = mem1.getBall();
					
					// Theoretically, this would turn the player toward the ball, if it's
					// not already facing it. It doesn't really work very well because I
					// haven't completely figured out directions and turning accurately 
					// yet. It sort of works though.
					if(ball.getDirection() != 0) {
						rc1.turn(ball.getDirection());
					}
					// Again, this doesn't really work very well, but it sort of shows how
					// the distance and dash could work. If the player isn't 0.5 meter of
					// the ball, this would run at it. 
					else if (ball.getDistance() > 0.5) {
						rc1.dash(ball.getDistance() * 5.0);
					}
					// At least he kicks it!
					else 
						rc1.kick(100, 0);
				}
			}
			
		}
	}
}