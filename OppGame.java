

/**
* @file Game.java
* @author Joel Tanzi*
* @date 18 September 2011
*/

/**@class Game
* This serves as a main class to assemble the RoboCup team and
* set them into action for the match.
*
*/

public class OppGame {

	static String team = "Team2";
	public static void main(String args[]) throws Exception
	{
	
		//Instantiate each player client
		Player p1 = new Player(team);
		Player p2 = new Player(team);
		Player p3 = new Player(team);
		Player p4 = new Player(team);
		Player p5 = new Player(team);
		FullBack p6 = new FullBack(team);
		FullBack p7 = new FullBack(team);
		FullBack p8 = new FullBack(team);
		Goalie g9 = new Goalie(team);
		

		p1.initPlayer(-5, -25, "far_left_fwd");
		Thread.sleep(100);

		p2.initPlayer(-5, -10, "left_fwd");
		Thread.sleep(100);

		p3.initPlayer(-5, 10, "right_fwd");
		Thread.sleep(100);

		p4.initPlayer(-5, 25, "far_right_fwd");
		Thread.sleep(100);

		p5.initPlayer(-15, 0, "center_fwd");
		Thread.sleep(100);

		p6.initFullBack(-30, -25, "left_fb");
		Thread.sleep(100);

		p7.initFullBack(-30, 0, "center_fb");
		Thread.sleep(100);

		p8.initFullBack(-30, 25, "right_fb");
		Thread.sleep(100);

		g9.initGoalie(-40, 0);
		Thread.sleep(100);	
	
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
	}
}