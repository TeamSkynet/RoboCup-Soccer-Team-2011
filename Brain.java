
public class Brain {

	private Mode currentMode = new Mode();	
	
	/**
	 * @return the currentMode
	 */
	public Mode getCurrentMode() {
		return currentMode;
	}

	public setDefensive(Mode m) {
		this.currentMode = "Defensive";
	}
	
	public setOffensive(Mode m) {
		this.currentMode = "Offensive";
	}
}
