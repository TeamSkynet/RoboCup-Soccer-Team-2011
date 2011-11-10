import java.net.SocketException;
import java.net.UnknownHostException;

/**
* @file Game.java
* @author Joel Tanzi*
*/

/**@class Game
* This serves as a main class to assemble the RoboCup team and
* set them into action for the match.
*
*/

public class Game {

	public static void main(String args[]) throws Exception
	{
	
	
		//Instantiate each player client
		Player p1 = new Player();
		Player p2 = new Player();
		Player p3 = new Player();
		Player p4 = new Player();
		Player p5 = new Player();
		Player p6 = new Player();
		Player p7 = new Player();
		Player p8 = new Player();
		Goalie g9 = new Goalie();
		Player p10 = new Player();
		Player p11 = new Player();
		
		//Initialize each player
		p1.initPlayer();
		p2.initPlayer();
		p3.initPlayer();
		p4.initPlayer();
		p5.initPlayer();
		p6.initPlayer();
		p7.initPlayer();
		p8.initPlayer();
		g9.initGoalie();
		p10.initPlayer();
		p11.initPlayer();
		
		//Position players on field, and orient them accordingly based on what side they are on
		if(p1.getMem().side.compareTo("r") == 0) {

			p1.move(-5, -25);
			Thread.sleep(100);
			p1.turn(180);
			Thread.sleep(100);

			p2.move(-5, -10);
			Thread.sleep(100);
			p2.turn(180);
			Thread.sleep(100);

			p3.move(-5, 10);
			Thread.sleep(100);
			p3.turn(180);
			Thread.sleep(100);

			p4.move(-5, 25);
			Thread.sleep(100);
			p4.turn(180);
			Thread.sleep(100);

			p5.move(-15, 0);
			Thread.sleep(100);
			p5.turn(180);
			Thread.sleep(100);

			p6.move(-30, -25);
			Thread.sleep(100);
			p6.turn(180);
			Thread.sleep(100);

			p7.move(-30, 0);
			Thread.sleep(100);
			p7.turn(180);
			Thread.sleep(100);

			p8.move(-30, 25);
			Thread.sleep(100);
			p8.turn(180);
			Thread.sleep(100);

			g9.move(-40, 0);
			Thread.sleep(100);

		}
		else {

			p1.move(-5, -25);
			Thread.sleep(100);

			p2.move(-5, -10);
			Thread.sleep(100);

			p3.move(-5, 10);
			Thread.sleep(100);

			p4.move(-5, 25);
			Thread.sleep(100);

			p5.move(-15, 0);
			Thread.sleep(100);

			p6.move(-30, -25);
			Thread.sleep(100);

			p7.move(-30, 0);
			Thread.sleep(100);

			p8.move(-30, 25);
			Thread.sleep(100);

			g9.move(-40, 0);
			Thread.sleep(100);

		}
		
		//Begin soccer match behaviors
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();
		p6.start();
		p7.start();
		p8.start();
		g9.start();
		p10.start();
		p11.start();
	}
}