import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Goalie extends Player {
		
	/**
	 * 
	 */
	public Goalie() {
		super();
		// TODO Auto-generated constructor stub
	}

	 /**
	 * Initializes the Player with the RoboCup server as a goalie.
	 * @pre A RoboCup server is available.
	 * @post The Player has been initialized to the correct team as a goalie.
	 */
	public void initGoalie() throws SocketException, UnknownHostException {
		
		rc.dsock = new DatagramSocket();
		rc.initGoalie();
		
	}
	public void catchball(int dir) throws UnknownHostException{
		rc.catchball(dir);
	}

} //end class
