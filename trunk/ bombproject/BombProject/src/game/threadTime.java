package game;

public class threadTime extends Thread {

	private player unit;
	
	public threadTime(player t){
		this.unit = t;
	}
	
    public void run() {
    	
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
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
			remainingTime = unit.getTimeLeft() - (System.currentTimeMillis() - unit.getTimeStart())/1000;
	   		
	   		isPlanted = unit.isBombPlanted();
	   		isDefused = unit.isBombDefused();
	   		
	   		
	   		if (isPlanted)
	   		{
	   			if(isDefused)
		   			unit.getGw().getBombStatus().setText("Bombe Defused !");
	   			else
	   				unit.getGw().getBombStatus().setText("Bombe planted !");
	   		}
	   		else
	   			unit.getGw().getBombStatus().setText("Sector Clear");
	   		
			if ( remainingTime < 0 )
			{
				unit.setMovable(false);
				break;
			}
			
			unit.getGw().getTimeM1().setText("  " + String.valueOf(remainingTime/60));
			unit.getGw().getTimeS1().setText("  " + String.valueOf((remainingTime%60)/10));
			unit.getGw().getTimeS2().setText("  " + String.valueOf((remainingTime%60)%10));
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
   					unit.getGw().getBombStatus().setText("YOU LOT: Bomb defused");
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
