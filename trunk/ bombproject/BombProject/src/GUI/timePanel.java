package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class timePanel extends JPanel{
	
	private JLabel timeDisplayed;
	private String[] seq = {":", " "};
	private int seqBuffer;
	
	public timePanel(){
		
		FlowLayout defaultLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
		this.setLayout(defaultLayout);
		
		this.setTimeDisplayed(new JLabel());
		this.add(getTimeDisplayed());
		this.setSeqBuffer(0);
		
	    Font fCount = new Font("Consolas", Font.PLAIN, 72);

	    getTimeDisplayed().setAlignmentX(CENTER_ALIGNMENT);
	    
	    getTimeDisplayed().setFont(fCount);
	    getTimeDisplayed().setText("0:00");
	    changeColor(Color.GREEN);
	    
	}

	public void setTimeDisplayed(JLabel timeDisplayed) {
		this.timeDisplayed = timeDisplayed;
	}

	public JLabel getTimeDisplayed() {
		return timeDisplayed;
	}
	
	public void refreshTime(long l){
		
		String min, sep, decSec, sec;
				
		min = Long.toString(l/60);
		
		sep = seq[seqBuffer];
		seqBuffer++;
		seqBuffer%=seq.length;
		
		l%=60;
		
		decSec = Long.toString(l/10);
		
		l%=10;

		sec = Long.toString(l);
		
		this.getTimeDisplayed().setText(min+sep+decSec+sec);
	}

	public void setSeqBuffer(int seqBuffer) {
		this.seqBuffer = seqBuffer;
	}

	public int getSeqBuffer() {
		return seqBuffer;
	}
	
	public void changeColor(Color c){
		getTimeDisplayed().setForeground(c);
	}
	
	public Color getColor(){
		return getTimeDisplayed().getForeground();
	}

}
