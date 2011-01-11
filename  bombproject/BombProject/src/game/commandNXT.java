package game;

import machine.settings;

public class commandNXT {
	
	private static player unitP;
	private boolean isTerrorist;
	public static nxtBrick brick;

	
	public commandNXT(player t) {

		setUnit(t);
		setTerrorist(t.isTerrorist());
		
		
		if(getUnit().isTerrorist())
		{
			if( settings.activateBT )
				setBrick(new nxtBrick(this,
									  settings.nameBrickTerrorist,
									  settings.macBrickTerrorist));
		}
		else
		{
			if( settings.activateBT )
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
		case 'w':
			stop();
			break;
		case 'x':
			stop();
			break;
		case 'c':
			stop();
			break;
		case 'v':
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
		case 'l':
			decreaseSpeed();
			break;
		case 'm':
			increaseSpeed();
			break;
		case 'w':
			fwdL();
			break;
		case 'x':
			fwdR();
			break;
		case 'c':
			rwdL();
			break;
		case 'v':
			rwdR();
			break;
		case 't':
			test();
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
	
	public static void test(){
		// test
	}
	
	private static void fwdL(){
		if( settings.activateBT )
			getBrick().sendMessage("fwdL");
	}
	
	private static void fwdR(){
		if( settings.activateBT )
			getBrick().sendMessage("fwdR");
	}
	
	private static void rwdL(){
		if( settings.activateBT )
			getBrick().sendMessage("rwdL");
	}
	
	private static void rwdR(){
		if( settings.activateBT )
			getBrick().sendMessage("rwdR");
	}
	

	private static void increaseSpeed(){
		// increase speed
		if( settings.activateBT )
			getBrick().sendMessage("increaseSpeed");
		System.out.println("Increase Speed");	
	}

	private static void decreaseSpeed(){
		// decrease speed
		if( settings.activateBT )
			getBrick().sendMessage("decreaseSpeed");
		System.out.println("Decrease Speed");	
	}

	
	private static void sendDefuseColor(String string) {
		// send the color to the Bomb
		if ( ! getUnit().isTerrorist() /*&& getUnit().isBombDefusable()*/ )
		{
			getUnit().getGw().getRGUI().getDefusePanel().getCombinaison().setText("");
			getUnit().getCl().getTc().sendMsgServer("SC " + string);
		}
	}

	private static void plantBomb() {
		// Plant Bomb
		if ( (!getUnit().isBombPlanted()) && getUnit().isTerrorist() )
		{
			// plant the bomb for the server
			getUnit().setBombPlanted(true);
			//getUnit().setTimeLeft(settings.timeBomb);
			
			// plant the bomb for the client 
			getUnit().getServ().getTs().sendMsgClient("bombPlanted true");
			//getUnit().getServ().getTs().sendMsgClient("timeLeft " + settings.timeBomb);
			
			// activate the bomb
			
			String time = Long.toString(settings.timeBomb);
			
			for ( int i = time.length(); i < 4; i++)
				time = "0" + time;
			
			if( settings.activateBT )
				getUnit().getServ().getBrick().sendMessage("PL");
		}		
	}
	
	
	public static void stop() {
		// Stop
		if( settings.activateBT )
			getBrick().sendMessage("stop");
		System.out.println("Stop");	
	}

	private static void goRight() {
		// Go right
		if( settings.activateBT )
			getBrick().sendMessage("right");
		System.out.println("Go right");		
	}

	private static void goLeft() {
		// Go left
		if( settings.activateBT )
			getBrick().sendMessage("left");
		System.out.println("Go left");		
	}

	private static void goBackward() {
		// Go backward
		if( settings.activateBT )
			getBrick().sendMessage("backward");
		System.out.println("Go backward");		
	}

	private static void goForward() {
		// Go forward
		if( settings.activateBT )
			getBrick().sendMessage("forward");
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

	public static nxtBrick getBrick() {
		return brick;
	}

}
