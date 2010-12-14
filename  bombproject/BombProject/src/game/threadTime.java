package game;

public class threadTime extends Thread {

	private player unit;
	
	public threadTime(player t){
		this.unit = t;
	}
	
    public void run() {
    	
    	long remainingTime;
    	boolean isPlanted;
    	
    	while ( true )
    	{
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
			remainingTime = unit.getTimeLeft() - (System.currentTimeMillis() - unit.getTimeStart())/1000;
	   		unit.getGw().getTime().setText(String.valueOf(remainingTime));
	   		
	   		isPlanted = unit.isBombPlanted();
	   		if (isPlanted)
	   			unit.getGw().getBombStatus().setText("Bombe planted !");
	   		else
	   			unit.getGw().getBombStatus().setText("Sector Clear");
    	}
    }	
}
