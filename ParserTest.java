import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ParserTest {

	public String input;
	public String init;
	public Memory m = new Memory();
	Parser p = new Parser();
	
	
	@Before
	public void setUp() throws Exception {
		input = "(see 0 ((f r t) 66.7 -21) ((f r b) 76.7 35) ((f f r b) 64.7 15) ((f r) 63.4 9) ((f f r t) 62.8 3) ((f p r b) 55.1 33) ((f p r c) 47 12) ((f p r t) 47 -12) ((f t r 20) 41.7 -44) ((f t r 30) 49.4 -36) ((f t r 40) 58 -30) ((f t r 50) 66.7 -26) ((f b r 40) 70.1 44) ((f b r 50) 77.5 39) ((f r 0) 68 8) ((f r t 10) 67.4 0) ((f r t 20) 68 -8) ((f r t 30) 70.1 -17) ((f r b 10) 70.1 17) ((f r b 20) 73.7 24) ((f r b 30) 78.3 31) ((l r) 62.8 90))";
		init = "(init l 2 before_kick_off)";
		
	}
	
	@Test
	public void initParseTest() {
		p.initParse(init, m);
		
		assertTrue("Error in initParseTest", m.side.compareTo("l") == 0);
		
	}

	@Test
	public void ParseTest() {
		p.Parse(input, m);
		
		assertEquals("Error in ParseTest.", 66.7, m.getFlag("frt").getDistance(), 0.1);
	}

}
