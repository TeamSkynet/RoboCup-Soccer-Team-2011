import java.net.SocketException;
import java.net.UnknownHostException;


public class GoalieAgent {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws UnknownHostException 
	 * @throws SocketException 
	 */
	public static void main(String[] args) throws InterruptedException, SocketException, UnknownHostException {
		Goalie g = new Goalie();
		
		g.initGoalie();
		
		g.move(-40, 0);
		Thread.sleep(100);
		
		while (true) {
			g.receiveInput();
			
			if (g.getMem().timeCheck(g.getTime())) {
				g.setTime(g.getMem().ObjMem.getTime());
				
			g.followBall();
			}
		}
	}

}
