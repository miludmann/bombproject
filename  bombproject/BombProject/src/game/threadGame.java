package game;

import java.awt.Color;

import GUI.*;

public class threadGame extends Thread {

	private player unit;
	
	public threadGame(player t){
		this.unit = t;
	}
	
    @SuppressWarnings("static-access")
	public void run() {
    	
    	long remainingTime;
    	
    	while ( ! unit.isMovable() )
    	{
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
   		((infoPanel) unit.getGw().getRGUI().getInfoPanel()).showSide(unit.isTerrorist());
    	
    	while ( unit.isMovable() )
    	{
			try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Change the color of the timer when the bomb is dropped
			timePanel tp = (timePanel) unit.getGw().getRGUI().getTimePanel();
    		if ( unit.isBombPlanted() && tp.getColor().equals(Color.GREEN) )
			{
				tp.changeColor(Color.RED);
			}
			
			remainingTime = unit.getTimeLeft() - (System.currentTimeMillis() - unit.getTimeStart())/1000;
	   		
	   		// Update status
	   		((infoPanel) unit.getGw().getRGUI().getInfoPanel()).changeInfos(false, unit.isTerrorist(), unit.isBombPlanted(), unit.isBombDefused());
	   		
	   		// Update remaining time
	   		((timePanel)unit.getGw().getRGUI().getTimePanel()).refreshTime(remainingTime);
	   		
			if ( remainingTime < 1 )
			{
				unit.setMovable(false);
		   		((timePanel)unit.getGw().getRGUI().getTimePanel()).refreshTime(0);
				break;
			}
			
			unit.getGw().repaint();
    	}
    	
		
		// Wait one second....
		// in case of delays
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
		
		// And check the winner
		// (... and display it !)
   		
   		((infoPanel) unit.getGw().getRGUI().getInfoPanel()).changeInfos(true, unit.isTerrorist(), unit.isBombPlanted(), unit.isBombDefused());

	}	
}
