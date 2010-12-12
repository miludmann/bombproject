package machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class client {
	
	private int port;
	private String host;
	private static threadClient tc;
	
	public client(int port, String host){
		setPort(port);
		setHost(host);
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
}
