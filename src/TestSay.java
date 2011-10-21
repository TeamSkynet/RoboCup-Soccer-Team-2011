
public class TestSay {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		Player p1 = new Player();
		Player p2 = new Player();
		
		p1.initPlayer();
		p2.initPlayer();
		
		p1.move(-5, -25);
		Thread.sleep(100);
		
		p2.move(-5, -10);
		Thread.sleep(100);

		while(true) {
			p1.receiveInput();
			p2.receiveInput();
			
			p1.say("Player1, hello!");
			Thread.sleep(100);
			p2.say("Player2, back at you!");
			Thread.sleep(100);
		}
	}

}
