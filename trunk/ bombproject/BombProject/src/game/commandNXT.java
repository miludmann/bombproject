package game;

import machine.settings;
import MessageComponent.LIMessage;
import MessageComponent.LIMessageType;

public class commandNXT {
	
	private static player unitP;
	private boolean isTerrorist;
	public static nxtBrick brick;

	
	public commandNXT(player t) {

		setUnit(t);
		setTerrorist(t.isTerrorist());
		
				
		if(getUnit().isTerrorist())
		{
			setBrick(new nxtBrick(this,
								  settings.nameBrickTerrorist,
								  settings.macBrickTerrorist));
		}
		else
		{
			setBrick(new nxtBrick(this,
								  settings.nameBrickCounterTerrorist,
								  settings.macBrickCounterTerrorist));
		}
		
		
	}

	public static void commandReleasedTerrorist(char c){
		
		if(!getUnit().isMovable())
			return;
		switch(c) {
		case 'z':
			stop();
			break;
		case 's':
			stop();
			break;
		case 'q':
			stop();
			break;
		case 'd':
			stop();
			break;
		}
	}


	public static void commandTypedTerrorist(char c){
		
		if(!getUnit().isMovable())
			return;
		
		switch(c) {
		case 'z':
			goForward();
			break;
		case 's':
			goBackward();
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
	
	
	private static void stop() {
		// Stop
		nxtBrick.getMF().SendMessage(new LIMessage(LIMessageType.Command, "stop"));
		System.out.println("Go right");	
	}

	private static void goRight() {
		// Go right
		nxtBrick.getMF().SendMessage(new LIMessage(LIMessageType.Command, "right"));
		System.out.println("Go right");		
	}

	private static void goLeft() {
		// Go left
		nxtBrick.getMF().SendMessage(new LIMessage(LIMessageType.Command, "left"));
		System.out.println("Go left");		
	}

	private static void goBackward() {
		// Go backward
		nxtBrick.getMF().SendMessage(new LIMessage(LIMessageType.Command, "backward"));
		System.out.println("Go backward");		
	}

	private static void goForward() {
		// Go forward
		nxtBrick.getMF().SendMessage(new LIMessage(LIMessageType.Command, "forward"));
		System.out.println("Go forward");		
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

	public void setBrick(nxtBrick brick) {
		commandNXT.brick = brick;
	}

	public nxtBrick getBrick() {
		return brick;
	}

}
