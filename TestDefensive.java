
public class TestDefensive {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		Player p1 = new Player();
		Player p2 = new Player();
		
		p1.initPlayer();
		p2.initPlayer();
		
		p1.move(-5, -25);
		Thread.sleep(100);
		
		p2.move(-5, -10);
		Thread.sleep(100);		
		
		p1.turn(270);

		while(true) {
			p1.receiveInput();
			p2.receiveInput();
			
			if (p1.getMem().timeCheck(p1.getTime())) {
				p1.setTime(p1.getMem().ObjMem.getTime());
				
				/*if (!p1.getMem().getPlayers().isEmpty()){
					for (int i = 0; i < p1.getMem().getPlayers().size(); i++) {
						System.out.println("Player: " + p1.getMem().getPlayers().get(i).getTeam() +
								p1.getMem().getPlayers().get(i).getuNum());
								
					}
					*/
				}
			}




		}
	}
