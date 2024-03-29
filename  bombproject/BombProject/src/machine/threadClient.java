package machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class threadClient extends Thread {
	
	private client gameClient;
	private PrintWriter out;
	
	public threadClient(client cl){
		gameClient = cl;
	}
	
	
	public void run() {
		Socket kkSocket = null;
        out = null;
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
        String fromServer;
        
        sendMsgServer("clientOK");

        try {
			while ((fromServer = in.readLine()) != null) {
			    System.out.println("CLIENT: String received: -" + fromServer +"-");
			    
				if ( null != gameClient.getUnit() )
					gameClient.getUnit().interpret(fromServer);
			    
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
		} catch (SocketException e) {
			System.out.println("Error reading from socket:" + e.getMessage());
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

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public PrintWriter getOut() {
		return out;
	}
	
	public void sendMsgServer(String s){
		getOut().println(s);
	}
	
	
}
