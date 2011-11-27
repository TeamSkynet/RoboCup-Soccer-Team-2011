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
		g.initGoalie(-40, 0);
		g.start();
	}

}
