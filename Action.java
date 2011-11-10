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
			}
			rc.dash(m.getDashPower(m.getPos(go), mem.getAmountOfSpeed(), mem.getDirectionOfSpeed(), mem.getEffort(), mem.getStamina()));
		
			
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
			else if((ball.getDistance() < 20.0) && (ball.getDistance() > 0.7)){
				interceptBall(ball);
			}
			else if(ball.getDistance() <= 0.7)  {
				dribbleToGoal(ball);
				
			}
			
		}
		else
			rc.turn(30);
	}
		
	
	
	private void interceptBall(ObjBall ball) throws UnknownHostException, InterruptedException {
		Polar p = m.getNextBallPoint(ball);
		if(stayInBounds())
			gotoPoint(p);
	}
	
	private boolean stayInBounds() {
		if(mem.side.compareTo("l") == 0) {
			if((mem.getPlayMode().compareTo("play_on") != 0) && (mem.getPlayMode().compareTo("kick_off_l") != 0)) {
				return false;
			}
			else
				return true;
		}
		else {
			if((mem.getPlayMode().compareTo("play_on") != 0) && (mem.getPlayMode().compareTo("kick_off_r") != 0)) {
				return false;
			}
			else
				return true;
		}
		
	}
	
	
	
	private void kickToGoal(ObjBall ball) {
		ObjGoal goal = mem.getOppGoal();
		if(goal != null) {
			try {
				rc.kick(100, goal.getDirection() - mem.getDirection());
			} catch (UnknownHostException e) {
				System.out.println("Error at action.kickToGoal() kick 1");
				e.printStackTrace();
			}
		}
		else {
			try {
				rc.kick(100, mem.getDirection());
			} catch (UnknownHostException e) {
				System.out.println("Error at action.kickToGoal() turn");
				e.printStackTrace();
			}
			
		}
	}
	
	
	public void kickToPoint(ObjBall ball, Polar p) {
		
		if(ball.getDistance() <= 0.7) {
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
	
	
	
	public void dribbleToGoal(ObjBall ball) {
		if(stayInBounds()) {
			ObjGoal goal = mem.getOppGoal();
			
			if((goal != null) && ((goal.getDistance() - 18) > 1.0)) {
					kickToPoint(ball, new Polar(20.0, (goal.getDirection() - ball.getDirection())));
				
			}
			else if((goal != null) && ((goal.getDistance() - 18) <= 1.0)) {
				kickToGoal(ball);
			}
			else if(goal == null) {
				try {
					rc.kick(10.0, mem.getNullGoalAngle());
				} catch (UnknownHostException e) {
					System.out.println("Error in Action.dribbleToGoal() at null goal turn");
					e.printStackTrace();
				}
			}
		}
	}
	
	public MathHelp m = new MathHelp();
	public Memory mem;
	public RoboClient rc;
	public Polar OppGoal;
	public boolean atGoal;
}