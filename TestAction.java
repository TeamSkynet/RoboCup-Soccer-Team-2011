import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramSocket;


public class TestAction {

	BufferedReader readin = new BufferedReader(new InputStreamReader(System.in));
	String command = new String();
	
	public static void main(String args[]) throws Exception
	{
		RoboClient rc = new RoboClient();
		Memory mem = new Memory();
		Parser p = new Parser();
		//@SuppressWarnings("unused")
		
		@SuppressWarnings("unused")
		MathHelp m = new MathHelp();
		

		rc.dsock = new DatagramSocket();
		rc.init(p, mem);
		p.initParse(rc.receive(), mem);
		Action a = new Action(mem, rc, p);
		rc.move(-10, 0);

		@SuppressWarnings("unused")
		Pos local = new Pos(-10, 0);
		
		int time = 0;
		
		
		while(true) {
			p.Parse(rc.receive(), mem);
			
			
			if(mem.timeCheck(time)) {
				time = mem.ObjMem.getTime();
					
				a.findBall();
			
			
			}
			
		} 
		
	}

}
