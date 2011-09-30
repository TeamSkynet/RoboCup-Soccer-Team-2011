import java.net.*;
import java.util.*;
import java.io.*;

public class RoboClient {  
	public DatagramSocket dsock;
	private String hostname = new String("127.0.0.1");
	private int port = 6000;
	private String command, reply;
	private static final int SIZE = 1024;
	private static final String TEAM = "Team_Skynet";
	private static final String VERSION = "15";
	
	private byte[] buffer = new byte[SIZE];
		
 /*
 * This function reads in a message string, and sends it to the RoboCup server.
 * It primarily serves as a method to send commands to the server to control server and player actions.
 * @param message: A String.
 * @pre message is a valid String value, the RoboCup server is available.
 * @post The message has been delivered to the RoboCup server.
 * @return None
 */
	public void send(String message) throws UnknownHostException{
		byte[] buf = message.getBytes();
		InetAddress IP = InetAddress.getByName(hostname);
		DatagramPacket spack = new DatagramPacket(buf,buf.length,IP,port);
			
		try {
			dsock.send(spack);
			System.out.println("Sent: " + message);
		} catch(IOException ioex) {
			System.out.println(ioex);
			System.exit(-1);
		} 	
			
	}
	
/*
 * This function receives a UDP packet from the RoboCup server, and converts it to a String.
 * @param none
 * @pre The RoboCup server is available.
 * @post The packet from the RoboCup server has been processed.
 * @return String
 */
	public String receive(){
		byte[] buf = new byte[SIZE];
		DatagramPacket rpack = new DatagramPacket(buf, SIZE);
		String message;
		
		try {
			dsock.receive(rpack);
			message = new String(rpack.getData());
			port = rpack.getPort();
			}

			catch(Exception ex) {
			System.out.println(ex);
		}
		
		return new String(buf);
	}
	

/*
 * This function initializes the soccer team.
 * @param message: none
 * @pre The RoboCup server is available.
 * @post The team has been initialized.
 * @return None
 */
	public void init() throws UnknownHostException {
		send("(init " + TEAM + " (version " + VERSION + "))");
		
		try {
			receive();	
		} catch(Exception ex) {
			System.out.println(ex);
		}


	}
	
/*
 * This function causes the active player to dash.
 * @param power: a double representing the power of the dash.
 * @pre The RoboCup server is available, team has been initialized.
 * @post The player has dashed according to the given power.
 * @return None
 */
	public void dash(double power) throws Exception {
		send("(dash " + Double.toString(power) + ")");
	}
	
/*
 * This function causes the active player to kick.
 * @param power: a double representing the power of the kick.
 * @param dir: a double representing the direction of the kick.
 * @pre The RoboCup server is available, team has been initialized.
 * @post The player has kicked according to the given power and direction.
 * @return None
 */
	public void kick(double power, double dir) throws UnknownHostException {
		
		send("(kick " + Double.toString(power) +  Double.toString(dir) + ")");
	}
	
/*
 * This function causes the active player to turn.
 * @param moment: a double representing the turning angle in degrees.
 * @pre The RoboCup server is available, team has been initialized.
 * @post The player has turned the given number of degrees from original orientation.
 * @return None
 */
	public void turn(double moment) throws UnknownHostException{
		
		send("(turn " + Double.toString(moment) + ")");
	}
	
/*
 * This function causes the active player to be teleported to a given set of coordinates within the
 * soccer field.
 * @param x: an integer value for the x-coordinate to move to.
 * @param y: an integer value for the y-coordinate to move to.
 * @pre The RoboCup server is available, team has been initialized, kickoff has not yet occurred.
 * @post The player has moved to the given coordinates.
 * @return None
 */
	public void move(int x, int y) throws UnknownHostException {
		send("(move " + Integer.toString(x) + " " + Integer.toString(y) + ")");
	}
//end of class	
}
