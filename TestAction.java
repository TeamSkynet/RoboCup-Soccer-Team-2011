public class TestAction {

	public static void main(String args[]) throws Exception
	{
		Player p = new Player();
		p.initPlayer(-10,0);

		
		
		while(true) {
			p.receiveInput();
			
			if(p.getMem().timeCheck(p.getTime())) {
				p.setTime(p.getMem().ObjMem.getTime());
				
				p.getAction().findBall();
				
			
			}
			
		} 
		
	}

}