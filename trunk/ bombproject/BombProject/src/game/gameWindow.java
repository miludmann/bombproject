package game;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class gameWindow extends JFrame{
	
	private JPanel j1, j2, j3, j4, j5, j6;
	private JLabel bombStatus;
	private JLabel timeM1, timeM2, timeMS, timeS1, timeS2;
	
	public gameWindow(){
	
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(1280, 800);
	    this.setLocationRelativeTo(null); // Center the window
	    this.setVisible(true);
	    
	    j1 = new JPanel();
		j1.setBackground(Color.CYAN);
	    j1.setBounds(0, 0, 800, 480);
	    
	    j2 = new JPanel();
		j2.setBackground(Color.GREEN);
	    j2.setBounds(800, 0, 350, 350);
	    
	    j3 = new JPanel();
		j3.setBackground(Color.RED);
	    j3.setBounds(1150, 0, 130, 350);
	    
	    j4 = new JPanel();
		j4.setBackground(Color.BLUE);
	    j4.setBounds(800, 350, 480, 130);
	    
	    j5 = new JPanel();
		j5.setBackground(Color.DARK_GRAY);
	    j5.setBounds(0, 480, 800, 320);
	    
	    j6 = new JPanel();
		j6.setBackground(Color.YELLOW);
	    j6.setBounds(800, 480, 480, 320);
	    
	    add (j1);
	    add (j2);
	    add (j3);
	    add (j4);
	    add (j5);
	    add (j6);
	    
	    j3.setLayout(new BoxLayout(j3, BoxLayout.Y_AXIS));
	    
	    j3.add(timeM1 = new JLabel());
	    j3.add(timeM2 = new JLabel());
	    j3.add(timeS1 = new JLabel());
	    j3.add(timeS2 = new JLabel());
	    
	    Font fTest = new Font("Times New Roman", Font.PLAIN, 70);

	    timeM1.setFont(fTest);
	    timeM2.setFont(fTest);
	    timeS1.setFont(fTest);
	    timeS2.setFont(fTest);
	    
	    timeM2.setText("  ~");

	    
	    Font fStatus = new Font("Times New Roman", Font.BOLD, 14);
	    bombStatus = new JLabel();
	    bombStatus.setFont(fStatus);
	    j2.add(bombStatus);
	    
	    

	}

	public void setJ1(JPanel j1) {
		this.j1 = j1;
	}

	public JPanel getJ1() {
		return j1;
	}

	public void setJ2(JPanel j2) {
		this.j2 = j2;
	}

	public JPanel getJ2() {
		return j2;
	}

	public void setJ3(JPanel j3) {
		this.j3 = j3;
	}

	public JPanel getJ3() {
		return j3;
	}

	public void setJ4(JPanel j4) {
		this.j4 = j4;
	}

	public JPanel getJ4() {
		return j4;
	}

	public void setJ5(JPanel j5) {
		this.j5 = j5;
	}

	public JPanel getJ5() {
		return j5;
	}

	public void setJ6(JPanel j6) {
		this.j6 = j6;
	}

	public JPanel getJ6() {
		return j6;
	}


	public void setBombStatus(JLabel bombStatus) {
		this.bombStatus = bombStatus;
	}

	public JLabel getBombStatus() {
		return bombStatus;
	}

	public void setTimeM1(JLabel timeM1) {
		this.timeM1 = timeM1;
	}

	public JLabel getTimeM1() {
		return timeM1;
	}

	public void setTimeM2(JLabel timeM2) {
		this.timeM2 = timeM2;
	}

	public JLabel getTimeM2() {
		return timeM2;
	}

	public void setTimeMS(JLabel timeMS) {
		this.timeMS = timeMS;
	}

	public JLabel getTimeMS() {
		return timeMS;
	}

	public void setTimeS1(JLabel timeS1) {
		this.timeS1 = timeS1;
	}

	public JLabel getTimeS1() {
		return timeS1;
	}

	public void setTimeS2(JLabel timeS2) {
		this.timeS2 = timeS2;
	}

	public JLabel getTimeS2() {
		return timeS2;
	}
}
