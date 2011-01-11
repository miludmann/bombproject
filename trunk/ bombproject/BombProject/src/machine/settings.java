package machine;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class settings {
	
	public static boolean serverOnly = false;
	public static boolean activateVideo = false;
	public static boolean activateBT = true;
	
	public static int port = 15000;
	public static String IPServer = "localhost";
	
	public static String streamT = "192.168.1.2:8080";
	public static String streamAT = "192.168.2.103:8000";

	public static long timeGame = 200;
	public static long timeBomb = 5;
	
	public static String nameBrickTerrorist = "Cutie";
	public static String macBrickTerrorist = "001653006046";
	
	public static String nameBrickCounterTerrorist = "NXT";
	public static String macBrickCounterTerrorist = "001653099CE9";
	
	public static String nameBrickBomb = "Bomb";
	public static String macBrickBomb = "00165308D0DD";
	
	
	
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
