import static org.junit.Assert.*;

import java.net.SocketException;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

//Note: The Soccer Server must be started PRIOR to running these tests

public class PlayerTest {
	
	Player p1 = new Player();
	Pos place = new Pos();

	@Before 
	public void setUp() {
		try {
			p1.initPlayer(-5, -25);
			Thread.sleep(100);
			p1.turn(35);
			Thread.sleep(100);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void testGetDirection() {
		assertEquals("GetDirection failure", 35, p1.getDirection(), 4);
	}


	@Test
	public void testGetPosition() {
		place.x = -5;
		place.y = -25;
		assertEquals("GetPosition failure", place.x, p1.getPosition().x, 1);
		assertEquals("GetPosition failure", place.y, p1.getPosition().y, 1);
		
	}

}
