import java.net.*;
import java.io.*;

/** @file RoboClient.java
* Class file for RoboClient class
* @author Joel Tanzi
* @date September 20, 2011
* @version 1.2 
*/

	/** @class RoboClient
	* The RoboClient class operates as a client for the RoboCup session.
	* It is mainly designed to be used by the Player class to handle all 
	* client-server communication.  The connection protocol is UDP.
	*/
	public class RoboClient {  
		
		public DatagramSocket dsock;
		private String hostname = new String("127.0.0.1");
		private int port = 6000;
		@SuppressWarnings("unused")
		private String command, reply;
		private static final int SIZE = 1024;
		private String team = "Team_Skynet";
		private static final String VERSION = "15";
		
		@SuppressWarnings("unused")
		private byte[] buffer = new byte[SIZE];
		
		
		/**
		 * 
		 */
		public RoboClient() {
			super();
			// TODO Auto-generated constructor stub
		}
	
		/**
		 * @param port
		 */
		public RoboClient(int port) {
			super();
			this.port = port;
		}
		
	
		/**
		 * @param team
		 */
		public RoboClient(String team) {
			super();
			this.team = team;
		}
		
		/**
		 * @return the team
		 */
		public String getTeam() {
			return team;
		}
		
		/**
		 * @param team the team to set
		 */
		public void setTeam(String team) {
			this.team = team;
		}
		
		/**
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
		
		/**
		 * This function receives a UDP packet from the RoboCup server, and converts it to a String.
		 * @pre The RoboCup server is available.
		 * @post The packet from the RoboCup server has been processed.
		 * @return String
		 */
		public String receive(){
			byte[] buf = new byte[SIZE];
			DatagramPacket rpack = new DatagramPacket(buf, SIZE);
			@SuppressWarnings("unused")
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
		
	
		/**
		 * This function initializes the client with the RoboCup server.
		 * @pre The RoboCup server is hosting connections.
		 * @post The client has been initialized.
		 */
		public void init(Parser p, Memory m) throws UnknownHostException {
			send("(init " + team + " (version " + VERSION + "))");
			
			try {
				p.initParse(receive(), m);
				
			} catch(Exception ex) {
				System.out.println(ex);
			}
	
	
		}
	
		public void changePlayMode(String playmode) throws UnknownHostException {
			send("(change mode " + playmode + ")");
		}
		
		/**
		 * This function initializes the client as a goalie with the RoboCup server.
		 * @param message: none
		 * @pre The RoboCup server is hosting connections.
		 * @post The goalie has been initialized.
		 * @return None
		 */
		public void initGoalie(Parser p, Memory m) throws UnknownHostException {
			send("(init " + team + " (version " + VERSION + ") (goalie))");
			
			try {
				p.initParse(receive(), m);	
			} catch(Exception ex) {
				System.out.println(ex);
			}	
		}
		
		/**
		 * This function sends the dash command to the server.
		 * @param power: a double representing the power of the dash.
		 * @pre The RoboCup server is available, client has been initialized.
		 * @post The player has dashed according to the given power.
		 * @return None
		 */
		public void dash(double power) throws Exception {
			send("(dash " + Double.toString(power) + ")");
		}
		
		/**
		 * This function sends the dash command to the server.
		 * @param power: a double representing the power of the dash.
		 * @param direction: a double representing the direction of the dash
		 * @pre The RoboCup server is available, client has been initialized.
		 * @post The player has dashed according to the given power and direction.
		 * @return None
		 */
		public void dash(double power, double direction) throws Exception {
			send("(dash " + Double.toString(power) + " " + Double.toString(direction) + ")");
		}
		
		/**
		 * This function causes the active player to kick.
		 * @param power: a double representing the power of the kick.
		 * @param dir: a double representing the direction of the kick.
		 * @pre The RoboCup server is available, team has been initialized.
		 * @post The player has kicked according to the given power and direction.
		 * @return None
		 */
		public void kick(double power, double dir) throws UnknownHostException {
			
			send("(kick " + Double.toString(power) +  " " + Double.toString(dir) + ")");
		}
		
		/**
		 * This function causes the active player to turn.
		 * @param moment: a double representing the turning angle in degrees.
		 * @pre The RoboCup server is available, team has been initialized.
		 * @post The player has turned the given number of degrees from original orientation.
		 * @return None
		 */
		public void turn(double moment) throws UnknownHostException{
			
			send("(turn " + Double.toString(moment) + ")");
		}
		
		public void turn_neck(double moment) throws UnknownHostException {
			send("(turn_neck " + Double.toString(moment) + ")");
		}
		
		/**
		 * This function causes the active player to be teleported to a given set of coordinates within the
		 * soccer field.
		 * @param x: an integer value for the x-coordinate to move to.
		 * @param y: an integer value for the y-coordinate to move to.
		 * @pre The RoboCup server is available, team has been initialized, kickoff has not yet occurred.
		 * @post The player has moved to the given coordinates.
		 * @return None
		 */
		public void move(double x, double y) throws UnknownHostException {
			send("(move " + Double.toString(x) + " " + Double.toString(y) + ")");
		}
	
		/**
		 * This function causes the active player to catch the ball.  It can only be used
		 * by a Goalie type player.
		 * @param d An integer value representing the direction from which to catch the ball.
		 * @pre Playmode is play_on or goal_kick, ball is in catchable area.
		 * @post The player has caught the ball.
		 * @throws UnknownHostException 
		 */
		public void catchball(double d) throws UnknownHostException{
			send("(catch " + Double.toString(d) + ")");
		}
		
		/**
		 * This function causes the active player to speak the given message.
		 * @param message A string representing the message to be spoken by the player.
		 * @pre None
		 * @post The player has spoken the message.
		 * @throws UnknownHostException 
		 */	
		public void say(String message)throws UnknownHostException{
			send("(say \"" + message + "\")");		
		}

}//end of class	