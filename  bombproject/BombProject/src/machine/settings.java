package machine;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class settings {
	
	public static int port = 15000;
	public static String IPServer = "localhost";
	
	public static String streamT = "NULL";
	public static String streamAT = "NULL";
		
	public static long timeGame = 40;
	public static long timeBomb = 10;
	
	public static void init() throws UnsupportedEncodingException, FileNotFoundException
	{
		try{
		    FileInputStream fstream = new FileInputStream("C:\\settings.txt");
		    DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    
	        String strLine;
	        String[] strSplit;

	        strLine = br.readLine();
	        strSplit = strLine.split("[ ]+");
	        port = Integer.parseInt(strSplit[1]);
	        
	        strLine = br.readLine();
	        strSplit = strLine.split("[ ]+");
	        IPServer = strSplit[1];

	        strLine = br.readLine();
	        strSplit = strLine.split("[ ]+");
	        streamT = strSplit[1];

	        strLine = br.readLine();
	        strSplit = strLine.split("[ ]+");
	        streamAT = strSplit[1];

	        strLine = br.readLine();
	        strSplit = strLine.split("[ ]+");
	        timeGame = Long.parseLong(strSplit[1]);

	        strLine = br.readLine();
	        strSplit = strLine.split("[ ]+");
	        timeBomb = Long.parseLong(strSplit[1]);
	        
		    in.close();
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
	}
}
