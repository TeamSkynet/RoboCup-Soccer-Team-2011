import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramSocket;




public class Game {

	BufferedReader readin = new BufferedReader(new InputStreamReader(System.in));
	String command = new String();
	
	public static void main(String args[]) throws Exception
	{
		//Instantiate each player client
		//RoboClient[] rc = new Roboclient[9];
		RoboClient rc1 = new RoboClient();
		RoboClient rc2 = new RoboClient();
		RoboClient rc3 = new RoboClient();
		RoboClient rc4 = new RoboClient();
		RoboClient rc5 = new RoboClient();
		RoboClient rc6 = new RoboClient();
		RoboClient rc7 = new RoboClient();
		RoboClient rc8 = new RoboClient();
		RoboClient rc9 = new RoboClient();
		RoboClient rc10 = new RoboClient();
		RoboClient rc11 = new RoboClient();
		
		Memory newMem = new Memory();
		Parser p = new Parser();

		//Set up connection to RoboCup server
		rc1.dsock = new DatagramSocket();
		rc2.dsock = new DatagramSocket();
		rc3.dsock = new DatagramSocket();
		rc4.dsock = new DatagramSocket();
		rc5.dsock = new DatagramSocket();
		rc6.dsock = new DatagramSocket();
		rc7.dsock = new DatagramSocket();
		rc8.dsock = new DatagramSocket();
		rc9.dsock = new DatagramSocket();
		rc10.dsock = new DatagramSocket();
		rc11.dsock = new DatagramSocket();
		
		//Instantiate Game class
		@SuppressWarnings("unused")
		Game g = new Game();
		
		//Initialize all players, and move to correct positions
		rc1.init(p, newMem);
		rc1.move(-5, -25);
		Thread.sleep(100);
		rc2.init(p, newMem);
		rc2.move(-5, -10);
		Thread.sleep(100);
		rc3.init(p, newMem);
		rc3.move(-5, 10);
		Thread.sleep(100);
		rc4.init(p, newMem);
		rc4.move(-5, 25);
		Thread.sleep(100);
		rc5.init(p, newMem);
		rc5.move(-15, 0);
		Thread.sleep(100);
		rc6.init(p, newMem);
		rc6.move(-30, -25);
		Thread.sleep(100);
		rc7.init(p, newMem);
		rc7.move(-30, 0);
		Thread.sleep(100);
		rc8.init(p, newMem);
		rc8.move(-30, 25);
		Thread.sleep(100);
		rc9.init(p, newMem);
		rc9.move(-40, 0);
		Thread.sleep(100);
		rc10.init(p, newMem);
		//rc10.move(10, -10);
		Thread.sleep(100);
		rc11.init(p, newMem);
		//rc11.move(-40, 0);
		

		while(true) {
			p.Parse(rc1.receive(), newMem);
			p.Parse(rc2.receive(), newMem);
			p.Parse(rc3.receive(), newMem);
			p.Parse(rc4.receive(), newMem);
			p.Parse(rc5.receive(), newMem);
			p.Parse(rc6.receive(), newMem);
			p.Parse(rc7.receive(), newMem);
			p.Parse(rc8.receive(), newMem);
			p.Parse(rc9.receive(), newMem);
		}
		
	}

}