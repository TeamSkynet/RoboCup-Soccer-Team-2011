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


//Set up connection to RoboCup server
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


//Initialize all players, and move to correct positions
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

//Continuous loop during program execution
while(true) {
p1.receiveInput();
p2.receiveInput();
p3.receiveInput();
p4.receiveInput();
p5.receiveInput();
p6.receiveInput();
p7.receiveInput();
p8.receiveInput();
g9.receiveInput();
p10.receiveInput();
p11.receiveInput();
} //end while

}

}