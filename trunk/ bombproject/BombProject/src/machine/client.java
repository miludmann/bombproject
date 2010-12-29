package machine;

import game.antiTerrorist;
import game.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class client {
	
	private int port;
	private String host;
	private static threadClient tc;
	private player unit;

	
	public client(int port, String host){
		setPort(port);
		setHost(host);
		
    	unit = new antiTerrorist(this);
	}
    public static void main(String[] args) throws IOException {
    	
    	client gameClient = new client(15000, "localhost");
    	threadClient tc = new threadClient(gameClient);
    	tc.start();
    	
    	
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String outputLine;

		do{
			outputLine = stdIn.readLine();
			
		    if (outputLine != null)
		    	tc.sendMsgServer(outputLine);
	    }
		while(!outputLine.equals("quit"));
    }
    
	public void setPort(int port) {
		this.port = port;
	}
	public int getPort() {
		return port;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getHost() {
		return host;
	}
	public void setUnit(player unit) {
		this.unit = unit;
	}
	public player getUnit() {
		return unit;
	}
	public static void setTc(threadClient tc) {
		client.tc = tc;
	}
	public static threadClient getTc() {
		return tc;
	}
}
