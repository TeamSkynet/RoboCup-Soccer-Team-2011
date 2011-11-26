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
		Pos origin = new Pos(0,0);
		
		p.initPlayer(-5, -10);
		
		while (true) {
			p.receiveInput();
			
			if (p.getMem().timeCheck(p.getTime())) {
				p.setTime(p.getMem().ObjMem.getTime());
				
			p.getAction().findBall();
			
			}
		}
	}
}
