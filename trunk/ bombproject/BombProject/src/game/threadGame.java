package game;

import java.awt.Color;

import GUI.timePanel;

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
    	
   		unit.getGg().getM_infoPanel().showSide(unit.isTerrorist());
    	
    	while ( unit.isMovable() )
    	{
			try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Change the color of the timer when the bomb is dropped
			timePanel tp = unit.getGg().getM_timePanel();
			
    		if ( unit.isBombPlanted() && tp.getColor().equals(Color.GREEN) )
			{
				tp.changeColor(Color.RED);
			}
			
			remainingTime = unit.getTimeLeft() - (System.currentTimeMillis() - unit.getTimeStart())/1000;
	   		
	   		// Update status
	   		unit.getGg().getM_infoPanel().changeInfos(false, unit.isTerrorist(), unit.isBombPlanted(), unit.isBombDefused());
	   		
	   		// Update remaining time
	   		unit.getGg().getM_timePanel().refreshTime(remainingTime);
	   		
	   		
			if ( remainingTime < 1 )
			{
				unit.setMovable(false);
				unit.getCmdNXT().stop();
		   		unit.getGg().getM_timePanel().refreshTime(0);
				break;
			}

			
			/*
			InfraredRadar m_irRadar = unit.getGw().getRGUI().getRadarPanel().getIrRadar();
			ObstacleRadar m_obstacleRadar = unit.getGw().getRGUI().getRadarPanel().getORadar();
			
			long dimTmp = (long) Math.min(m_irRadar.getSize().getHeight(), m_irRadar.getSize().getWidth());
			
			m_irRadar.setSize((int) dimTmp, (int)dimTmp);
			m_obstacleRadar.setSize((int) dimTmp, (int)dimTmp);
			
			m_irRadar.repaint();
			m_obstacleRadar.repaint();
			*/
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
   		
   		unit.getGg().getM_infoPanel().changeInfos(true, unit.isTerrorist(), unit.isBombPlanted(), unit.isBombDefused());
	}	
}
