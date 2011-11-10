import java.net.SocketException;
import java.net.UnknownHostException;


public class StrikerAgent {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws UnknownHostException 
	 * @throws SocketException 
	 */
	public static void main(String[] args) throws UnknownHostException, InterruptedException, SocketException {
		Player p = new Player();
		
		p.initPlayer();
		
		p.move(-5, -10);
		Thread.sleep(100);
		
		while (true) {
			p.receiveInput();
			
			if (p.getMem().timeCheck(p.getTime())) {
				p.setTime(p.getMem().ObjMem.getTime());
				
			p.getAction().findBall();
			
			}
		}
	}
}
