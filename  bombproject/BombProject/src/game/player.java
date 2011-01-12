package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;

import machine.client;
import machine.server;
import machine.settings;
import videoStream.ImageStreamComponent;
import GUI.gameWindow;

public class player{
	
	private server serv;
	private client cl;

	private gameWindow gw;
	private commandNXT cmdNXT;
	private boolean movable;
	private boolean isTerrorist;
	private boolean bombPlanted;
	private boolean bombDefused;
	private boolean bombDefusable;
	private long timeStart;
	private long timeLeft;
	private JComponent vid, vid2;
	

	
	public player(boolean isT){
		setServ(null);
		setCl(null);
		
		setMovable(false);
		setTerrorist(isT);
		setBombPlanted(false);
		
		setGw(new gameWindow());
		setCmdNXT(new commandNXT(this));
		setTimeStart(System.currentTimeMillis());
		setTimeLeft(0);
		
		
		addVideo();
		
		threadGame tt = new threadGame(this);
    	tt.start();
		

		gw.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent e) {
				// TODO: Do something for the keyReleased event
				getCmdNXT().commandReleasedTerrorist(e.getKeyChar());
			}

			public void keyTyped(KeyEvent e) {
			// TODO: Do something for the keyTyped event
				getCmdNXT().commandTypedTerrorist(e.getKeyChar());
			}

			public void keyPressed(KeyEvent e) {
			// TODO: Do something for the keyPressed event
				getCmdNXT().commandPressedTerrorist(e.getKeyChar());
			}
			});
				
	}
	
	public void addVideo(){
		if ( settings.activateVideo )
		{
			// add the video on the window
			setVid(null);
	
			if(isTerrorist())
			{
				if ( ! settings.streamT.equals("NULL") )
					setVid(new ImageStreamComponent(settings.streamT));
				/*
				if ( ! settings.streamAT.equals("NULL") )
					setVid2(new ImageStreamComponent(settings.streamAT));
				*/
			}
			else
			{
				if ( ! settings.streamT.equals("NULL") )
					setVid(new ImageStreamComponent(settings.streamAT));
				
				/*
				if ( ! settings.streamAT.equals("NULL") )
					setVid2(new ImageStreamComponent(settings.streamT));
				*/
			}
	
			if(getVid() != null)
				getGw().getLGUI().getStreamPanel().add(vid);
	
			//End of adding the video part
		}
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
		this.setTimeStart(System.currentTimeMillis());
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
				setTimeStart(System.currentTimeMillis());
			}
			if (splitStr[0].equalsIgnoreCase("bombPlanted"))
			{
				setBombPlanted(Boolean.parseBoolean(splitStr[1]));
			}
			if (splitStr[0].equalsIgnoreCase("defused"))
			{
				setBombDefused(true);
			}
			if (splitStr[0].equalsIgnoreCase("defusable"))
			{
				setBombDefusable(true);
			}
			if (splitStr[0].equalsIgnoreCase("movable"))
			{
				setMovable(Boolean.parseBoolean(splitStr[1]));
			}
			if (splitStr[0].equalsIgnoreCase("SC"))
			{
				if( settings.activateBT )
					getServ().getBrick().sendMessage("SC" + splitStr[1]);
			}
			if (splitStr[0].equalsIgnoreCase("DS"))
			{
				getGw().getRGUI().getDefusePanel().getCombinaison().setWireColorEnabled(true);
				getGw().getRGUI().getDefusePanel().getCombinaison().setSequence(splitStr[1]);
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

	public void setVid(JComponent vid) {
		this.vid = vid;
	}

	public JComponent getVid() {
		return vid;
	}

	public void setBombDefusable(boolean bombDefusable) {
		this.bombDefusable = bombDefusable;
	}

	public boolean isBombDefusable() {
		return bombDefusable;
	}

	public void setVid2(JComponent vid2) {
		this.vid2 = vid2;
	}

	public JComponent getVid2() {
		return vid2;
	}
	
	public void interpretBrick(String s) throws InterruptedException{
    	// This is where we interpret the messages from the player's brick
    	System.out.println("Player <- Brick : " + s);
    	
		
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

			if (splitStr[0].equalsIgnoreCase("IRD"))
			{
				int arg1 = Integer.parseInt(splitStr[1]);

				getGw().getRGUI().getRadarPanel().getIrRadar().setDirection(arg1);
				
			}
			if (splitStr[0].equalsIgnoreCase("CP"))
			{
				double arg1 = Double.parseDouble(splitStr[1]);

				getGw().getRGUI().getRadarPanel().getORadar().setCompassAngle(arg1);
			}
			
			break;
			
		case 3:
			try
			{
				if (splitStr[0].equalsIgnoreCase("IRV"))
				{
					int arg1 = Integer.parseInt(splitStr[1]);
					int arg2 = Integer.parseInt(splitStr[2]);
					
					getGw().getRGUI().getRadarPanel().getIrRadar().setSensor(arg1, arg2);
				}
				
				if (splitStr[0].equalsIgnoreCase("RC"))
				{
					int arg1 = Integer.parseInt(splitStr[1]);
					int arg2 = Integer.parseInt(splitStr[2]);
					
					getGw().getRGUI().getRadarPanel().getORadar().addObstacle(arg1, arg2);
				}
				
			} catch(Exception e) {
				System.err.println("Player IRV msg: " + e.getMessage());
			}
			
			break;
			
		}
	}

}
