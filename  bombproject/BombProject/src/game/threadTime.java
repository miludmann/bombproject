package game;

public class threadTime extends Thread {

	private player unit;
	
	public threadTime(player t){
		this.unit = t;
	}
	
    public void run() {
    	
    	long remainingTime;
    	boolean isPlanted, isDefused;
    	
    	while ( true )
    	{
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
			remainingTime = unit.getTimeLeft() - (System.currentTimeMillis() - unit.getTimeStart())/1000;
	   		
			if(remainingTime <= 0)
				break;
			
			unit.getGw().getTime().setText(String.valueOf(remainingTime));
	   		
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
    	}
    	
		unit.getGw().getTime().setText(String.valueOf(0));

    }	
}
