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
			
			unit.getGw().getTime().setText(String.valueOf(remainingTime));
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
   					unit.getGw().getBombStatus().setText("You lost: bomb defused");
   				else
   					unit.getGw().getBombStatus().setText("You won: target destroyed");
   			}
   			else
   				unit.getGw().getBombStatus().setText("You lost: target safe");
   		}
   		else
   		{
   			if(isPlanted)
   			{
				if(isDefused)
   					unit.getGw().getBombStatus().setText("You won: bomb defused");
   				else
   					unit.getGw().getBombStatus().setText("You lost: target destroyed");
   			}
   			else
   				unit.getGw().getBombStatus().setText("You won: target safe");
   		}   		
	}	
}
