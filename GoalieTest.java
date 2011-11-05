	import java.io.BufferedReader;
	import java.io.InputStreamReader;



public class GoalieTest {

	BufferedReader readin = new BufferedReader(new InputStreamReader(System.in));
	String command = new String();
	
	public static void main(String args[]) throws Exception
	{
		
		Goalie g9 = new Goalie();
		g9.initGoalie();
		g9.move(-40, 0);
		Thread.sleep(100);
		
		while(true) {
			g9.receiveInput();
			
			if(g9.getMem().timeCheck(g9.getTime())) {
				g9.setTime(g9.getMem().SenMem.getTime());
				
				g9.followBall();
			
			
			}
			
		} 
		
	}

	

}
