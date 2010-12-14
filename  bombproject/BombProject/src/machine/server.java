package machine;

import game.player;

import java.io.IOException;

public class server {
	
	private int port;
	private threadServer ts;
	private player unit;
	
	public server(int port){
		setPort(port);
		
    	ts = new threadServer(this);
    	ts.start();
    	
    	unit = new player(this, true);
	}
	
    @SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
    	
    	server gameServer = new server(15000);


    	/*
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String outputLine;
		
		do{
			outputLine = stdIn.readLine();
			
		    if (outputLine != null)
		    	ts.sendMsgClient(outputLine);
	    }
		while(!outputLine.equals("quit"));
		*/

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
}
