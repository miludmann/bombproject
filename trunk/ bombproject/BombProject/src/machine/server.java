package machine;

import game.nxtBrick;
import game.player;
import game.terrorist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;

public class server {
	
	private boolean isReady;
	private boolean getStarted;
	private int port;
	private static threadServer ts;
	private player unit;
	public nxtBrick brick;

	
	public server(int port){
		setPort(port);
		setReady(false);
		setGetStarted(false);
		
		
		if( settings.activateBT )
		{
			setBrick(new nxtBrick(this,
								  settings.nameBrickBomb,
								  settings.macBrickBomb));
		}
		
		
		
    	if ( settings.serverOnly )
    	{
	    	unit = new terrorist(this);
    	}
    	else
    	{
    		ts = new threadServer(this);
        	ts.start();
        	
			System.out.println("Waiting for client");
			
			JFrame infoConnect = new JFrame();
			infoConnect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			infoConnect.setSize(200, 100);
			infoConnect.setLocationRelativeTo(null); // Center the window
			infoConnect.setVisible(true);
			
			JButton initiateGame = new JButton();
			infoConnect.add(initiateGame);
			initiateGame.setEnabled(false);
	
			int progress = 0;
	    	do
	    	{
	    		
	    		initiateGame.setText(" Waiting for client " + repeat(". ", progress));
	    		progress=(progress>5)?0:progress+1;
	    		
				try {
					Thread.currentThread().sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	while(!isReady);
	    	
	    	unit = new terrorist(this);
	    	
			initiateGame.setText("Start Game");
			initiateGame.setEnabled(true);
			infoConnect.requestFocus();
	
			initiateGame.addActionListener(
				    new ActionListener() {
				        public void actionPerformed(ActionEvent e) {
				        	setGetStarted(true);
				        }
				    }
				);
	
	    	do
	    	{
				try {
					Thread.currentThread().sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	while(!getStarted);
			
			infoConnect.setVisible(false);
			
			getUnit().getGg().requestFocus();
    	}
		
		startGame();
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		
		settings.init();
    	
    	server gameServer = new server(settings.port);

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String outputLine;
		
		do{
			outputLine = stdIn.readLine();
			
		    if (outputLine != null)
		    	ts.sendMsgClient(outputLine);
	    }
		while(!outputLine.equals("quit"));
    }
	

	public nxtBrick getBrick() {
		return brick;
	}

	public void setBrick(nxtBrick brick) {
		this.brick = brick;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public void setUnit(player p) {
		unit = p;
	}

	public player getUnit() {
		return unit;
	}

	public void setTs(threadServer t) {
		ts = t;
	}

	public threadServer getTs() {
		return ts;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public boolean isReady() {
		return isReady;
	}
	
	public static String repeat(String str, int times){
		   StringBuilder ret = new StringBuilder();
		   for(int i = 0;i < times;i++) ret.append(str);
		   return ret.toString();
		}

	public void setGetStarted(boolean getStarted) {
		this.getStarted = getStarted;
	}

	public boolean isGetStarted() {
		return getStarted;
	}
	
    private void startGame() {
    	
    	System.out.println("START GAME");
    	
    	getUnit().setTimeStart(System.currentTimeMillis());
    	getUnit().setTimeLeft(settings.timeGame);
    	getUnit().setMovable(true);
    	
    	getTs().sendMsgClient("timeLeft " + Long.toString(settings.timeGame));
    	getTs().sendMsgClient("movable true");
    	
		String time = Long.toString(settings.timeGame);
		
		for ( int i = time.length(); i < 4; i++)
			time = "0" + time;
		
		if( settings.activateBT )
			getBrick().sendMessage("GT" + time);
		
    	
	}
    
    public void interpretBomb(String s){
    	// This is where we interpret the messages from the bomb
    	//System.out.println("Server <- Bomb : " + s);
    	
		
		String[] splitStr = s.split(" ");
		int len = splitStr.length;

		switch (len){
		
		case 1:
			if(s.equals("defusable"))
			{
		    	getTs().sendMsgClient("defusable");
			}
			if(s.equals("defused"))
			{				
				// Set the time left to 0 and bomb defused for the server
				System.out.println("Bomb defused !");
				
				getUnit().setBombDefused(true);
		    	getUnit().setTimeLeft(0);
				
				// Set the time left to 0 and bomb defused for the client
		    	getTs().sendMsgClient("defused");
			}
			if(s.equals("BOOM"))
			{
				// Set the time left to 0 for the server
		    	getUnit().setTimeLeft(0);
				
				// Set the time left to 0 for the client
		    	getTs().sendMsgClient("timeLeft 0");
			}

			break;
			
		case 2:
			if (splitStr[0].equalsIgnoreCase("NT"))
			{
				// Set the time left to 0 for the server
		    	getUnit().setTimeLeft(0);
				
				// Set the time left to 0 for the client
		    	getTs().sendMsgClient("timeLeft 0");
			}
			if (splitStr[0].equalsIgnoreCase("DS"))
			{
				getUnit().getGg().getM_wireCut().setWireColorEnabled(true);
				getUnit().getGg().getM_wireCut().setSequence(splitStr[1]);
				
				getTs().sendMsgClient("DS " + splitStr[1]);
			}
			if (splitStr[0].equalsIgnoreCase("GT"))
			{
				long arg1 = Long.parseLong(splitStr[1]);
				
				getUnit().setTimeLeft(arg1);
				getTs().sendMsgClient("timeleft " + splitStr[1]);
			}
		}
    }
}
