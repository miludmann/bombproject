package game;

public class commandNXT {
	
	private static terrorist unitT;
	
	public commandNXT(terrorist t) {
		setUnitT(t);
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
			if ( ! getUnitT().isHasPlacedBomb() )
			{
				getUnitT().setHasPlacedBomb(true);
				getUnitT().setTimeLeft(40);
				getUnitT().setTimeStart(System.currentTimeMillis());
				System.out.println("Plant the bomb !");
			}
			break;
		
		}
	}

	public static void commandPressedTerrorist(char c){

	}

	public void setUnitT(terrorist unitT) {
		this.unitT = unitT;
	}

	public static terrorist getUnitT() {
		return unitT;
	}

}
