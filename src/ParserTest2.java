import java.net.*;
import java.util.*;
import java.io.*;

public class ParserTest2 {

	BufferedReader readin = new BufferedReader(new InputStreamReader(System.in));
	String command = new String();
	
	public static void main(String args[]) throws Exception
	{
		//Instantiate each player client
		RoboClient rc1 = new RoboClient();

		Memory mem1 = new Memory();
		Parser p = new Parser();
		Action a = new Action(mem1);

		//Set up connection to RoboCup server
		rc1.dsock = new DatagramSocket();
		
		//Instantiate test class
		ParserTest2 tc = new ParserTest2();
		
		rc1.init(p, mem1);
		rc1.move(-10, 0);

		while(true) {
			p.Parse(rc1.receive(), mem1);
			a.findBall(rc1);
			Thread.sleep(150);
				
		}

			

	
	}
}
