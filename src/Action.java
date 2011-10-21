import java.net.*;
import java.util.*;
import java.io.*;

public class Action {
	
	public Action() {
		
	}
	
	public Action(Memory mem) {
		this.mem = mem;
	}
	
	public void setMem(Memory mem) {
		this.mem = mem;
	}
	
	public boolean isBallInKickRange() {
		if(mem.isObjVisible("ball"))
			if(mem.getBall().getDistance() <= 0.7)
				return true;
			else
				return false;
		else return false;
	}
	
	public void findBall(RoboClient rc) throws Exception {
		if(mem.isObjVisible("ball")) {
			if(mem.getBall().getDirection() > 10 || mem.getBall().getDirection() < -10) {
				rc.turn(mem.getBall().getDirection());
				rc.dash(mem.getBall().getDistance()*2);
			}
			if(!isBallInKickRange()) {
				rc.turn(mem.getBall().getDirection());
				rc.dash(mem.getBall().getDistance()*2);
			}
		}
		else {
			rc.turn(90);
			rc.dash(30);
		}
	}
	
	public boolean isInInterceptDistance() {
		if(mem.isObjVisible("ball")) {
			if(mem.getBall().getDistance() < 10.0)
				return true;
			else return false;
		}
		else return false;
	}
	
	public void interceptBall(RoboClient rc) throws Exception {
		if(isInInterceptDistance()) {
			while(!isBallInKickRange()) {
				rc.turn(mem.getBall().getDirection());
				rc.dash(mem.getBall().getDistance()+5.0);
			}
			rc.kick(20, 0);
		}
	}
	
	public Memory mem;
}
