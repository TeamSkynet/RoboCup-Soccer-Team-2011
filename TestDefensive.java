
public class TestDefensive {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		Player p1 = new Player();
		Player p2 = new Player();
		Goalie g3 = new Goalie();
		
		p1.initPlayer();
		p2.initPlayer();
		g3.initGoalie();
		
		p1.move(-45, -15);
		Thread.sleep(100);
		
		p2.move(-35, 30);
		Thread.sleep(100);
		
		g3.move(-50, 0);
		Thread.sleep(100);

		while(true) {
			p1.receiveInput();
			p2.receiveInput();
			g3.receiveInput();
			
			if (g3.getMem().timeCheck(g3.getTime())) {
				g3.setTime(g3.getMem().ObjMem.getTime());
				//System.out.println("No players?: " + g3.getMem().getPlayers().isEmpty());
				//System.out.println("Closest Player: " + g3.closestPlayer().getuNum());
				
				
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
