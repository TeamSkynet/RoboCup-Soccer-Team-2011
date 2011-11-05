import java.net.UnknownHostException;



public class Action {
	
	public Action() {
		
	}
	
	public Action(Memory mem, RoboClient rc) {
		this.mem = mem;
		this.rc = rc;
	}
	
	public void setMem(Memory mem) {
		this.mem = mem;
	}
	
	public void gotoPoint(Polar go) {
		
		try {
			if(go.t > 5.0 || go.t < -5.0) {
				rc.turn(go.t * (1+(5*mem.getAmountOfSpeed())));
				Thread.sleep(100);
			}
			rc.dash(m.getDashPower(m.getPos(go), mem.getAmountOfSpeed(), mem.getDirectionOfSpeed(), mem.getEffort(), mem.getStamina()));
		
			Thread.sleep(100);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void gotoPoint(Pos p) {
		gotoPoint(m.getPolar(p));
	}
	
	
	public void findBall() throws UnknownHostException, InterruptedException {
		if(mem.isObjVisible("ball")) {
			ObjBall ball = mem.getBall();
			if((ball.getDistance() > 20) && (ball.getDirection() > 5.0 || ball.getDirection() < -5.0)) {
				rc.turn(ball.getDirection());
			}
			else if(ball.getDistance() < 20) {
				interceptBall(ball);
			}
			
		}
		else
			rc.turn(30);
			//Thread.sleep(100);
	}
		
	
	
	private void interceptBall(ObjBall ball) throws UnknownHostException, InterruptedException {
		
		
		Polar p = m.getNextBallPoint(ball);
		stayInBounds();
		gotoPoint(p);
		if(ball.getDistance() <= 0.7) {
			kickToGoal();
			//Thread.sleep(100);
		}
			
		
	}
	
	private void stayInBounds() {
		ObjLine line = mem.getClosestLine();
		if((line.getDistance() < 1.0) && ((line.getDirection() < 5.0) && (line.getDirection() > -5.0))) {
			
			try {
				rc.dash(-100);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	private void kickToGoal() {
		ObjGoal goal = mem.getOppGoal();
		if(goal != null) {
			try {
				rc.kick(50, mem.getDirection() + goal.getDirection());
			} catch (UnknownHostException e) {
				System.out.println("Error at action.kickToGoal() kick 1");
				e.printStackTrace();
			}
		}
		else {
			try {
				rc.kick(50, mem.getDirection());
			} catch (UnknownHostException e) {
				System.out.println("Error at action.kickToGoal() turn");
				e.printStackTrace();
			}
			
		}
		
		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Error at action.kickToGoal() Thread.sleep() 2");
				e.printStackTrace();
			}
		
	}
	
	
	public void kickToPoint(ObjBall ball, Polar p) {
		
		if(ball.getDistance() < 1.885) {
			try {
				rc.kick(m.getKickPower(p, mem.getAmountOfSpeed(), mem.getDirection(), ball.getDistance(), ball.getDirection()), p.t);
			} catch (UnknownHostException e) {
				System.out.println("Error in Action.kickToPoint");
				e.printStackTrace();
			}
		}

		
	}
	
	public void kickToPoint(ObjBall ball, Pos p) {
		kickToPoint(ball, m.getPolar(p));
	}
	
	/*
	public void dribbleToPoint(ObjBall ball, Pos p) {
		
	}
	*/
	public MathHelp m = new MathHelp();
	public Memory mem;
	public RoboClient rc;
	public Polar OppGoal;
}
