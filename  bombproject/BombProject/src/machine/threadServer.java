package machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class threadServer extends Thread {
	
	private server gameServ;
	
	public threadServer(server serv){
		gameServ = serv;
	}
	
	public void run() {
	    ServerSocket serverSocket = null;
	    try {
	        serverSocket = new ServerSocket(gameServ.getPort());
	    } catch (IOException e) {
	        System.err.println("Could not listen on port: " + gameServ.getPort());
	        System.exit(1);
	    }
	
	    Socket clientSocket = null;
	    
	    try {
	        clientSocket = serverSocket.accept();
	    } catch (IOException e) {
	        System.err.println("Accept failed.");
	        System.exit(1);
	    }
	
	    PrintWriter out = null;
	    BufferedReader in = null;
	    
		try {
			in = new BufferedReader(
					new InputStreamReader(
					clientSocket.getInputStream()));
			out = new PrintWriter(clientSocket.getOutputStream(), true);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	    String inputLine, outputLine;
	    
        out.println("test");
	    
	    try {
			while ((inputLine = in.readLine()) != null) {
				System.out.println("SERVER: I received: " + inputLine);
				
				if (inputLine.equals("quit"))
					break;
				
				outputLine = stdIn.readLine();
			    if (outputLine != null) {
			        out.println(outputLine);
			    }
				
				if (outputLine.equals("quit"))
					break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    System.out.println("SERVER CLOSED");
	    
	    out.close();
	    try {
			in.close();
		    clientSocket.close();
		    serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
