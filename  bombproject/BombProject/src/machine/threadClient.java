package machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class threadClient extends Thread {
	
	private client gameClient;
	
	public threadClient(client cl){
		gameClient = cl;
	}
	
	
	public void run() {
      Socket kkSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            kkSocket = new Socket(gameClient.getHost(),
            		gameClient.getPort());
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + gameClient.getHost());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + gameClient.getHost());
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer, fromUser;

        try {
			while ((fromServer = in.readLine()) != null) {
			    System.out.println("CLIENT: I received: " + fromServer);
			    
				if (fromServer.equals("quit"))
					break;
				
				/*
			    fromUser = stdIn.readLine();
			    if (fromUser != null) {
			        out.println(fromUser);
			    }
			    
				if (fromUser.equals("quit"))
					break;
				*/
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    System.out.println("CLIENT CLOSED");
        
        out.close();
        try {
			in.close();
	        stdIn.close();
	        kkSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
