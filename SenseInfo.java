import java.net.*;
import java.util.*;
import java.io.*;
public class SenseInfo {
	
	public SenseInfo() {
	}
	
	public SenseInfo(String name) {
		this.senseName = name;
	}
	
	public SenseInfo(String name, int C) {
		this.senseName = name;
		this.setCount(C);
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	private String senseName;
	private int count;
	
}

class Stamina extends SenseInfo {
	public Stamina(Double stamina, double E) {
		super("Stamina");
		this.stamina = stamina;
		this.effort = E;
	}
	
	private double stamina;
	private double effort;
}

class Speed extends SenseInfo {
	public Speed(double a, double d) {
		super("Speed");
		this.amount = a;
		this.direction = d;
	}
	
	private double amount;
	private double direction;
}

class HeadAngle extends SenseInfo {
	public HeadAngle() {
		super("HeadAngle");
	}
	
	private double angle;
}

class Focus extends SenseInfo {
	public Focus(String side, int unum, int C) {
		super("Focus");
		this.targetSide = side;
		this.targetUnum = unum;
		this.setCount(C);
	}
	
	private String targetSide;
	private int targetUnum;
}

class Tackles extends SenseInfo {
	public Tackles(int E, int C) {
		super("Tackles");
		this.expires = E;
		this.setCount(C);
	}
	
	public int expires;
}
