import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class MathHelpTest {

	public ObjBall ball = new ObjBall();
	public Pos p1;
	public Pos p2;
	public MathHelp m = new MathHelp();
	
	@Before 
	public void setUp() {
		
		ball.setDistance(10.0);
		ball.setDirection(-30.0);
		ball.setDistChng(0.2);
		ball.setDirChng(5.0);
		
		
		p1 = new Pos(10, -5);
		p2 = new Pos(-2, 3);
	}
	
	
	@Test
	public void testGetNextBallPoint() {
		assertEquals("getNextBallPoint failed at distance r.", 10.16, m.getNextBallPoint(ball).r, 0.1);
		assertEquals("getNextBallPoint failed at direction t.", -29.35, m.getNextBallPoint(ball).t, 0.1);
	}
	
	@Test
	public void testvAdd() {
		assertEquals("vAdd failed at x.", 8.0, m.vAdd(p1, p2).x, 0.0);
		assertEquals("vAdd failed at y.", -2.0, m.vAdd(p1, p2).y, 0.0);
	}
	
	@Test
	public void testgetPolar() {
		assertEquals("get Polar failed at r.", 11.1, m.getPolar(p1).r, 0.1);
		assertEquals("get Polar failed at t.", -26.5, m.getPolar(p1).t, 0.1);
	}

}
