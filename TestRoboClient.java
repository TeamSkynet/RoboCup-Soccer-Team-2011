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

		Memory newMem = new Memory();
		ObjInfo newInfo = new ObjInfo();
		Parser p = new Parser();

		//Set up connection to RoboCup server
		rc1.dsock = new DatagramSocket();
		
		//Instantiate test class
		TestRoboClient tc = new TestRoboClient();
		
		rc1.init();
		rc1.move(10, -10);

		while(true) {
			p.Parse(rc1.receive(), newMem);
			if(newMem.ObjMem.getSize() > 0){
				for(int i = 0; i < newMem.ObjMem.getSize(); i++) {
					System.out.println(newMem.getObj(i).getObjName());
				}
			}
		}

	}

}