import java.net.*;
import java.util.*;
import java.io.*;

public class SenseMemory {
	public SenseMemory(ArrayList<SenseInfo> newSenseArray, int t) {
		this.SenseArray = newSenseArray;
		this.time = t;
	}
	
	public void addInfo(SenseInfo newInfo) {
		SenseArray.add(newInfo);
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
	
	public int getSize() {
		return SenseArray.size();
	}
	
	public SenseInfo getSense(int index) {
		return SenseArray.get(index);
	}

	public ArrayList<SenseInfo> SenseArray;
	private int time;
	
	
}
