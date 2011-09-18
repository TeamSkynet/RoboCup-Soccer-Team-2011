import java.io.*;
import java.net.*;
import java.util.*;

public class RoboClient {  
	DatagramSocket dsock;
	String hostname = new String("127.0.0.1");
	int port = 6000;
	String command, reply;
	private static final int SIZE = 1024;
	private static final String TEAM = "Team_Skynet";
	private static final String VERSION = "15";
	
	byte[] buffer = new byte[SIZE];
		
	//Send method
	//Sends a command to the server as a datagram
	//Takes String as argument, in form of message to send to server
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
	
	//Receive packets from server
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
	

	//Player commands	
	//Uses send method to send each command as a Robocup expression
	
	//Send command to initialize team
	//Takes two Strings as arguments, one for team name, other for version of RoboCup server used
	public void init() throws UnknownHostException {
		send("(init " + TEAM + " (version " + VERSION + "))");
		
		try {
			receive();	
		} catch(Exception ex) {
			System.out.println(ex);
		}


	}
	
	//Send dash command
	//Takes double as argument for power
	public void dash(double power) throws Exception {
		send("(dash " + Double.toString(power) + ")");
	}
	
	//Send kick command
	//Takes doubles for arguments, one for power, one for angle of kick
	public void kick(double power, double dir) throws UnknownHostException {
		
		send("(kick " + Double.toString(power) +  Double.toString(dir) + ")");
	}
	
	//Send turn command
	//Takes double as argument for turn moment
	public void turn(double moment) throws UnknownHostException{
		
		send("(turn " + Double.toString(moment) + ")");
	}
	
	//Send move command (can only be used prior to kickoff)
	//Takes two integers as arguments for x and y coordinates to teleport to
	public void move(int x, int y) throws UnknownHostException {
		send("(move " + Integer.toString(x) + " " + Integer.toString(y) + ")");
	}
	

	
}
