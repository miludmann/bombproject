package game;

public class commandNXT {
	
	private static player unitP;
	private boolean isTerrorist;
	
	public commandNXT(player t) {
		setUnit(t);
		setTerrorist(t.isTerrorist());
	}

	public static void commandReleasedTerrorist(char c){
	
	}
	
	public static void commandTypedTerrorist(char c){
		switch(c) {
		case 'z':
			// Go forwards
			System.out.println("Go forwards");
			break;
		case 's':
			// Go backwards
			System.out.println("Go backwards");
			break;
		case 'q':
			// Go left
			System.out.println("Go left");
			break;
		case 'd':
			// Go right
			System.out.println("Go right");
			break;
		case 'p':
			// Plant Bomb
			if ( ! getUnit().isBombPlanted() )
			{
				long timeRef = System.currentTimeMillis();
				
				getUnit().setBombPlanted(true);
				getUnit().setTimeLeft(40);
				getUnit().setTimeStart(System.currentTimeMillis());
				
				
				System.out.println("Plant the bomb !");
			}
			break;
		}
	}

	public static void commandPressedTerrorist(char c){

	}

	public void setUnit(player p) {
		unitP = p;
	}

	public static player getUnit() {
		return unitP;
	}

	public void setTerrorist(boolean isTerrorist) {
		this.isTerrorist = isTerrorist;
	}

	public boolean isTerrorist() {
		return isTerrorist;
	}

}
