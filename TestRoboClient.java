import java.io.*;
import java.net.*;
import java.util.*;


public class TestRoboClient {

	BufferedReader readin = new BufferedReader(new InputStreamReader(System.in));
	String command = new String();
	
	public static void main(String args[]) throws Exception
	{
		RoboClient rc = new RoboClient();
		rc.dsock = new DatagramSocket();
		TestRoboClient tc = new TestRoboClient();
		
		rc.init();
		rc.move(10, -10);
		
		while(true)
		{
			//System.out.print("$");
			//tc.command = tc.readin.readLine();
			//rc.send(tc.command);
			rc.dash(100);
			rc.kick(20,20);
			rc.turn(90);
			rc.receive();
		}
	}

}