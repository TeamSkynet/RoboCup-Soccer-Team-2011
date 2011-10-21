import java.io.*;
import java.net.*;

public class TestRoboClient {

	BufferedReader readin = new BufferedReader(new InputStreamReader(System.in));
	String command = new String();
	
	public static void main(String args[]) throws Exception
	{
		//Instantiate each player client
		RoboClient rc1 = new RoboClient();

		Memory newMem = new Memory();
		Parser p = new Parser();

		//Set up connection to RoboCup server
		rc1.dsock = new DatagramSocket();
		
		
		rc1.init(p, newMem);
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