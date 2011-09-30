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
		RoboClient rc2 = new RoboClient();
		RoboClient rc3 = new RoboClient();

		Memory mem1 = new Memory();
		Memory mem2 = new Memory();
		Memory mem3 = new Memory();
		Parser p = new Parser();
		

		//Set up connection to RoboCup server
		rc1.dsock = new DatagramSocket();
		rc2.dsock = new DatagramSocket();
		rc3.dsock = new DatagramSocket();
		
		//Instantiate test class
		
		rc1.init();
		rc2.init();
		rc3.init();
		rc1.move(-10, 0);
		rc2.move(-10, 10);
		rc3.move(-10, -10);

		while(true) {
			p.Parse(rc1.receive(), mem1);
			p.Parse(rc2.receive(), mem2);
			p.Parse(rc3.receive(), mem3);
			
			
			rc1.dash(50);
			if(mem1.isObjVisible("ball")) {
				ObjBall ball = mem1.getBall();
				
				if(ball.getDirection() != 0)
					rc1.turn(ball.getDirection());
				else if((ball.getDistance() <= 0.7) && (mem1.isObjVisible("player"))) {
					ObjPlayer teammate = mem1.getPlayer();
					rc1.kick(50.0, teammate.getDirection());
					rc1.turn(ball.getDirection());
				}
				else if((ball.getDistance() <= 0.7) && (mem1.isObjVisible("goal"))) {
					ObjGoal goal = mem1.getGoal();
					rc1.kick(50.0, goal.getDirection());
					rc1.turn(ball.getDirection());
				}
				else if(ball.getDistance() <= 0.7) {
					rc1.kick(50.0, 0);
					rc1.turn(ball.getDirection());
				}
				else
					rc1.dash(30);
			}
			else {
				rc1.turn(20);
				rc1.dash(30);
			}

			
			rc2.dash(50);
			if(mem2.isObjVisible("ball")) {
				ObjBall ball = mem2.getBall();
				
				if(ball.getDirection() != 0)
					rc2.turn(ball.getDirection());
				else if((ball.getDistance() <= 0.7) && (mem2.isObjVisible("player"))) {
					ObjPlayer teammate = mem2.getPlayer();
					rc2.kick(50.0, teammate.getDirection());
					rc2.turn(ball.getDirection());
				}
				else if((ball.getDistance() <= 0.7) && (mem2.isObjVisible("goal"))) {
					ObjGoal goal = mem2.getGoal();
					rc2.kick(50.0, goal.getDirection());
					rc2.turn(ball.getDirection());
				}
				else if(ball.getDistance() <= 0.7) {
					rc2.kick(50.0, 0);
					rc2.turn(ball.getDirection());
				}
				else
					rc2.dash(30);
			}
			else {
				rc2.turn(20);
				rc2.dash(30);
			}
			
			rc3.dash(50);
			if(mem3.isObjVisible("ball")) {
				ObjBall ball = mem3.getBall();
				
				if(ball.getDirection() != 0)
					rc3.turn(ball.getDirection());
				else if((ball.getDistance() <= 0.7) && (mem3.isObjVisible("player"))) {
					ObjPlayer teammate = mem3.getPlayer();
					rc3.kick(50.0, teammate.getDirection());
					rc3.turn(ball.getDirection());
				}
				else if((ball.getDistance() <= 0.7) && (mem3.isObjVisible("goal"))) {
					ObjGoal goal = mem3.getGoal();
					rc3.kick(50.0, goal.getDirection());
					rc3.turn(ball.getDirection());
				}
				else if(ball.getDistance() <= 0.7) {
					rc3.kick(50.0, 0);
					rc3.turn(ball.getDirection());
				}
				else
					rc3.dash(30);
			}
			else {
				rc3.turn(20);
				rc3.dash(30);
			}
				
				
				
			

		}	

	
	}
}