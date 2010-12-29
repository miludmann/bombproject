package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import machine.client;
import machine.server;

public class player{
	
	private server serv;
	private client cl;

	private gameWindow gw;
	private commandNXT cmdNXT;
	private boolean movable;
	private boolean isTerrorist;
	private boolean bombPlanted;
	private boolean bombDefused;
	private long timeStart;
	private long timeLeft;
	
	public player(boolean isT){
		setServ(null);
		setCl(null);
		
		setGw(new gameWindow());
		setCmdNXT(new commandNXT(this));
		setMovable(false);
		setTerrorist(isT);
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

	public void setTimeLeft(long l) {
		this.timeLeft = l;
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
		
		String[] splitStr = s.split(" ");
		int len = splitStr.length;

		switch (len){
		
		case 1:
			if(s.equals("defuse"))
			{
				setBombDefused(true);
				setTimeLeft(0);
				setTimeStart(System.currentTimeMillis());
			}
			break;
			
		case 2:
			if (splitStr[0].equalsIgnoreCase("timeStart"))
			{
				setTimeStart(Long.parseLong(splitStr[1]));
			}
			if (splitStr[0].equalsIgnoreCase("timeLeft"))
			{
				setTimeLeft(Long.parseLong(splitStr[1]));
			}
		}
	}

	public void setServ(server serv) {
		this.serv = serv;
	}

	public server getServ() {
		return serv;
	}

	public void setCl(client cl) {
		this.cl = cl;
	}

	public client getCl() {
		return cl;
	}
}
