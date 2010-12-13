package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import machine.server;

public class terrorist{
	
	private server serv;
	private gameWindow gw;
	private commandNXT cmdNXT;
	private boolean bombPlanted;
	private long timeStart;
	private long timeLeft;
	
	public terrorist(server s){
		setServ(s);
		setGw(new gameWindow());
		setCmdNXT(new commandNXT(this));
		setHasPlacedBomb(false);
		setTimeStart(System.currentTimeMillis());
		setTimeLeft(180);
		
		
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
	
	public void setServ(server serv) {
		this.serv = serv;
	}

	public server getServ() {
		return serv;
	}

	public void setGw(gameWindow gw) {
		this.gw = gw;
	}

	public gameWindow getGw() {
		return gw;
	}

	public void setHasPlacedBomb(boolean hasPlacedBomb) {
		this.bombPlanted = hasPlacedBomb;
	}

	public boolean isHasPlacedBomb() {
		return bombPlanted;
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
}
