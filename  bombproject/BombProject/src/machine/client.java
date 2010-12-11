package machine;

import java.io.IOException;

public class client {
	
	private int port;
	private String host;
	
	public client(int port, String host){
		setPort(port);
		setHost(host);
	}
    public static void main(String[] args) throws IOException {
    	
    	client gameClient = new client(15000, "localhost");
    	threadClient sc = new threadClient(gameClient);
    	sc.start();
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
