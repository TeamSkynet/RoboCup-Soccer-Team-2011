import java.io.*;
import java.net.*;
import java.util.*;


public class TestRoboClient {

	BufferedReader readin = new BufferedReader(new InputStreamReader(System.in));
	String command = new String();
	
	public static void main(String args[]) throws Exception
	{
		//Instantiate each player client
		RoboClient rc1 = new RoboClient();
		RoboClient rc2 = new RoboClient();
		RoboClient rc3 = new RoboClient();
		RoboClient rc4 = new RoboClient();
		RoboClient rc5 = new RoboClient();
		RoboClient rc6 = new RoboClient();
		RoboClient rc7 = new RoboClient();
		RoboClient rc8 = new RoboClient();
		RoboClient rc9 = new RoboClient();
		
		//Set up connection to RoboCup server
		rc1.dsock = new DatagramSocket();
		rc2.dsock = new DatagramSocket();
		rc3.dsock = new DatagramSocket();
		
		//Instantiate test class
		TestRoboClient tc = new TestRoboClient();
		
		rc1.init();
		rc2.init();
		rc3.init();
		rc1.move(10, -10);
		rc2.move(20, 20);
		rc3.move(30,30);
		
		while(true)
		{
			//System.out.print("$");
			//tc.command = tc.readin.readLine();
			//rc.send(tc.command);
			rc1.dash(100);
			rc1.kick(20,20);
			rc1.turn(90);
			rc1.receive();
			rc2.dash(100);
			rc2.kick(20,20);
			rc2.turn(90);
			rc2.receive();
			rc3.dash(100);
			rc3.kick(20,20);
			rc3.turn(90);
			rc3.receive();
		}
	}

}