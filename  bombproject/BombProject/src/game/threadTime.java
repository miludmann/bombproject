package game;

import java.awt.Color;

public class threadTime extends Thread {

	private player unit;
	public static String[] seqTime = {"-", "\\", "|", "/"};
	
	public threadTime(player t){
		this.unit = t;
	}
	
    public void run() {
    	
    	String strAnim;
    	long remainingTime;
    	boolean isPlanted, isDefused;
    	
    	while ( ! unit.isMovable() )
    	{
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	while ( unit.isMovable() )
    	{
			try {
				Thread.currentThread().sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
			remainingTime = unit.getTimeLeft() - (System.currentTimeMillis() - unit.getTimeStart())/1000;
	   		
	   		isPlanted = unit.isBombPlanted();
	   		isDefused = unit.isBombDefused();
	   		
	   		
	   		if (isPlanted)
	   		{
	   			if ( unit.getGw().getColorFont().equals(Color.GREEN))
	   				unit.getGw().changeColorFont(Color.RED);
	   			
	   			if(isDefused)
		   			unit.getGw().getBombStatus().setText("Bomb Defused !");
	   			else
	   				unit.getGw().getBombStatus().setText("Bomb planted !");
	   		}
	   		else
	   			unit.getGw().getBombStatus().setText("Sector Clear");
	   		
	   		
			
			unit.getGw().getTimeM1().setText(" " + String.valueOf(remainingTime/60));
			unit.getGw().getTimeS1().setText(" " + String.valueOf((remainingTime%60)/10));
			unit.getGw().getTimeS2().setText(" " + String.valueOf((remainingTime%60)%10));
			
			if ( remainingTime < 1 )
			{
				unit.setMovable(false);
				unit.getGw().getTimeS2().setText(" 0");
				break;
			}
			
			strAnim = unit.getGw().getTimeM2().getText();
			
			
			if(!strAnim.equals(""))
			{
				for (int i=0; i<seqTime.length; i++)
				{
					if(strAnim.equals(" " + seqTime[i]))
					{
						unit.getGw().getTimeM2().setText(" " + seqTime[(i+1)%seqTime.length]);
						break;
					}
				}
			}
			else
			{
				unit.getGw().getTimeM2().setText(" " + seqTime[0]);
			}
    	}
    	
		
		
		// Wait one second....
		// in case of delays
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
   		isPlanted = unit.isBombPlanted();
   		isDefused = unit.isBombDefused();
   		
		
		// And check the winner
		// (... and display it !)
   		
   		if (unit.isTerrorist())
   		{
   			if(isPlanted)
   			{
   				if(isDefused)
   					unit.getGw().getBombStatus().setText("YOU LOST: Bomb defused");
   				else
   					unit.getGw().getBombStatus().setText("YOU WON: Target destroyed");
   			}
   			else
   				unit.getGw().getBombStatus().setText("YOU LOST: Target safe");
   		}
   		else
   		{
   			if(isPlanted)
   			{
				if(isDefused)
   					unit.getGw().getBombStatus().setText("YOU WON: Bomb defused");
   				else
   					unit.getGw().getBombStatus().setText("YOU LOST: Target destroyed");
   			}
   			else
   				unit.getGw().getBombStatus().setText("YOU WON: target safe");
   		}   		
	}	
}
