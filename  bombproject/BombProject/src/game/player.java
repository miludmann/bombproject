package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class player{
	
	private gameWindow gw;
	private commandNXT cmdNXT;
	private boolean movable;
	private boolean isTerrorist;
	private boolean bombPlanted;
	private boolean bombDefused;
	private long timeStart;
	private long timeLeft;
	
	public player(boolean isT){
		setGw(new gameWindow());
		setCmdNXT(new commandNXT(this));
		setMovable(false);
		setTerrorist(true);
		setBombPlanted(false);
		setTimeStart(System.currentTimeMillis());
		setTimeLeft(0);
		
		
		threadTime tt = new threadTime(this);
    	tt.start();
		

		gw.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent e) {
				// TODO: Do something for the keyReleased event
				commandNXT.commandReleasedTerrorist(e.getKeyChar());
			}

			public void keyTyped(KeyEvent e) {
			// TODO: Do something for the keyTyped event
				commandNXT.commandTypedTerrorist(e.getKeyChar());
			}

			public void keyPressed(KeyEvent e) {
			// TODO: Do something for the keyPressed event
				//serv.getTs().sendMsgClient(String.valueOf(e.getKeyChar()));
				commandNXT.commandPressedTerrorist(e.getKeyChar());
			}
			});
				
	}

	public void setGw(gameWindow gw) {
		this.gw = gw;
	}

	public gameWindow getGw() {
		return gw;
	}

	public void setBombPlanted(boolean b) {
		this.bombPlanted = b;
	}

	public boolean isBombPlanted() {
		return bombPlanted;
	}
	
	public void setBombDefused(boolean b) {
		this.bombDefused = b;
	}

	public boolean isBombDefused() {
		return bombDefused;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	public long getTimeLeft() {
		return timeLeft;
	}

	public void setCmdNXT(commandNXT cmdNXT) {
		this.cmdNXT = cmdNXT;
	}

	public commandNXT getCmdNXT() {
		return cmdNXT;
	}

	public void setTimeStart(long l) {
		this.timeStart = l;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTerrorist(boolean isTerrorist) {
		this.isTerrorist = isTerrorist;
	}

	public boolean isTerrorist() {
		return isTerrorist;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}

	public boolean isMovable() {
		return movable;
	}
	
	public void interpret(String s) {
		if(s.equals("clientOK"))
		{
			gameServ.setReady(true);
		}
		
		if(s.equals("defuse"))
		{
			gameServ.getUnit().setBombDefused(true);
			gameServ.getUnit().setTimeLeft(0);
			gameServ.getUnit().setTimeStart(System.currentTimeMillis());
	}
}
