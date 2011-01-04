package game;

import machine.settings;

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
		
		if(!getUnit().isMovable())
			return;
		
		switch(c) {
		case 'z':
			goForward();
			break;
		case 's':
			goBackwards();
			break;
		case 'q':
			goLeft();
			break;
		case 'd':
			goRight();
			break;
		case 'p':
			plantBomb();
			break;
		case 'r':
			sendDefuseColor("r");
			break;
		case 'b':
			sendDefuseColor("b");
			break;
		case 'y':
			sendDefuseColor("y");
			break;
		case 'g':
			sendDefuseColor("g");
			break;
		
		}
	}

	private static void sendDefuseColor(String string) {
		// send the color to the Bomb
		if ( ! getUnit().isTerrorist() && getUnit().isBombDefusable() )
		{
			System.out.println("Defuse color: " + string);
		}
	}

	private static void plantBomb() {
		// Plant Bomb
		if ( (!getUnit().isBombPlanted()) && getUnit().isTerrorist() )
		{
			long timeRef = System.currentTimeMillis();
			
			getUnit().setBombPlanted(true);
			getUnit().setTimeLeft(settings.timeBomb);
			getUnit().setTimeStart(timeRef);
			
			System.out.println("Plant the bomb !");

			getUnit().getServ().getTs().sendMsgClient("timeStart " + timeRef);
			getUnit().getServ().getTs().sendMsgClient("timeLeft " + settings.timeBomb);
			getUnit().getServ().getTs().sendMsgClient("bombPlanted true");
		}		
	}

	private static void goRight() {
		// Go right
		System.out.println("Go right");		
	}

	private static void goLeft() {
		// Go left
		System.out.println("Go left");		
	}

	private static void goBackwards() {
		// Go backwards
		System.out.println("Go backwards");		
	}

	private static void goForward() {
		// Go forwards
		System.out.println("Go forwards");		
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
