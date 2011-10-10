import java.net.*;
import java.util.*;
import java.io.*;

public class SenseMemory {
	
	
	public SenseMemory() {
		time = 0;
	}
	
	public SenseMemory(int time) {
		this.time = time;
	}
	
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int t) {
		this.time = t;
	}
	
	public void setTime(String[] seeOrSense) {
		this.time = Integer.parseInt(seeOrSense[1]);
	}
	
	public double stamina;
	public double recovery;
	public double effort;
	
	public double amountOfSpeed;
	public double directionOfSpeed;
	
	public double headDirection;
	
	private int time;
	
	
}
