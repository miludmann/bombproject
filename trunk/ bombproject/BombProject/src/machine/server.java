package machine;

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
	
	public server(int port){
		setPort(port);
		setReady(false);
		setGetStarted(false);
		
    	ts = new threadServer(this);
    	ts.start();
    	
		System.out.println("Waiting for client");

		JFrame infoConnect = new JFrame();
		infoConnect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		infoConnect.setSize(200, 100);
		//infoConnect.setLocationRelativeTo(null); // Center the window
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
		
		startGame();
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
    	
    	server gameServer = new server(15000);

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String outputLine;
		
		do{
			outputLine = stdIn.readLine();
			
		    if (outputLine != null)
		    	ts.sendMsgClient(outputLine);
	    }
		while(!outputLine.equals("quit"));
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
    	long timeRef = System.currentTimeMillis();
    	
    	getUnit().setTimeStart(timeRef);
    	getUnit().setTimeLeft(80);
    	getUnit().setMovable(true);
    	
    	getTs().sendMsgClient("timeStart " + Long.toString(timeRef));
    	getTs().sendMsgClient("timeLeft 80");
    	getTs().sendMsgClient("movable true");
    	
	}
}
