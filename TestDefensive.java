
public class TestDefensive {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		Player p1 = new Player();
		Player p2 = new Player();
		Goalie g3 = new Goalie();
		
		p1.initPlayer(-20, -15);
		p2.initPlayer(-20, 15);
		g3.initGoalie(-50, 0);
		

		while(true) {
			p1.receiveInput();
			p2.receiveInput();
			g3.receiveInput();
			
			if (p1.getMem().timeCheck(p1.getTime())) {
				p1.setTime(p1.getMem().ObjMem.getTime());
				
				//p1.turn(180);
				p1.runDefense();
				
				
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
