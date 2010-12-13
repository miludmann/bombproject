package game;

public class threadTime extends Thread {

	private terrorist unitT;
	
	public threadTime(terrorist t){
		this.unitT = t;
	}
	
    public void run() {
    	
    	long remainingTime;
    	boolean isPlanted;
    	
    	while ( true )
    	{
			try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
			remainingTime = unitT.getTimeLeft() - (System.currentTimeMillis() - unitT.getTimeStart())/1000;
	   		unitT.getGw().getTime().setText(String.valueOf(remainingTime));
	   		
	   		isPlanted = unitT.isHasPlacedBomb();
	   		if (isPlanted)
	   			unitT.getGw().getBombStatus().setText("Bombe planted !");
	   		else
	   			unitT.getGw().getBombStatus().setText("Sector Clear");
    	}
    }	
}
