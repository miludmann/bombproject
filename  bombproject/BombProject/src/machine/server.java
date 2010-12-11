package machine;

import java.io.IOException;

public class server {
	
	private int port;
	
	public server(int port){
		setPort(port);
	}
	
    public static void main(String[] args) throws IOException {
    	
    	server gameServer = new server(15000);
    	threadServer ts = new threadServer(gameServer);
    	ts.start();
    }

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}
}
