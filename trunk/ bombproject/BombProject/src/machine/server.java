package machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class server {
	
	private int port;
	private static threadServer ts;
	
	public server(int port){
		setPort(port);
	}
	
    public static void main(String[] args) throws IOException {
    	
    	server gameServer = new server(15000);
    	ts = new threadServer(gameServer);
    	ts.start();
    	
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
}
