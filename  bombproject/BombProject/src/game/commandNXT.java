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
			if ( (!getUnit().isBombPlanted()) && getUnit().isTerrorist() )
			{
				long timeRef = System.currentTimeMillis();
				
				getUnit().setBombPlanted(true);
				getUnit().setTimeLeft(40);
				getUnit().setTimeStart(timeRef);
				
				System.out.println("Plant the bomb !");

				getUnit().getServ().getTs().sendMsgClient("timeStart " +timeRef);
				getUnit().getServ().getTs().sendMsgClient("timeLeft 40");
				getUnit().getServ().getTs().sendMsgClient("bombPlanted true");
			}
			break;
			
		case 'k':
			// Defuse Bomb
			if ( getUnit().isBombPlanted() && !getUnit().isBombDefused() && !getUnit().isTerrorist() )
			{
				
				getUnit().setBombDefused(true);
				
				System.out.println("Defuse the bomb !");
				
				System.out.println(getUnit().getCl().toString());

				getUnit().getCl().getTc().sendMsgServer("bombDefused true");
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
