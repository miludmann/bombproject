package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class gameWindow extends JFrame{
	
	private JPanel j1, j2, j3, j4, j5, j6;
	private JLabel bombStatus;
	private JLabel timeM1, timeM2, timeMS, timeS1, timeS2;
	
	private leftGUI lGUI;
	private rightGUI rGUI;
	
	public gameWindow(){
	
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //BoxLayout defaultLayout = new BoxLayout(this, BoxLayout.X_AXIS);
	    FlowLayout defaultLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
	    
	    this.setLayout(defaultLayout);
	    
	    this.setLGUI(new leftGUI());
	    this.add(getLGUI());
	    
	    this.setRGUI(new rightGUI());
	    this.add(this.getRGUI());
	    
	    
	    /*
	    j1 = new JPanel();
		//j1.setBackground(Color.CYAN);
	    j1.setBounds(0, 0, 800, 480);
	    
	    j2 = new JPanel();
		j2.setBackground(Color.GREEN);
	    j2.setBounds(800, 0, 350, 350);

	    j3 = new JPanel();
		//j3.setBackground(Color.RED);
	    j3.setBounds(1150, 0, 130, 350);
	    
	    j4 = new JPanel();
		j4.setBackground(Color.BLUE);
	    j4.setBounds(800, 350, 480, 130);


	    j5 = new JPanel();
	    j5.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		j5.setBackground(Color.DARK_GRAY);
	    j5.setBounds(0, 480, 800, 320);
	    
	    JLabel labelImg = new JLabel();
	    ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "/src/GUI/map.png");
	    
	    labelImg.setIcon(img);
	    j5.add(labelImg);
	    
	    
	    
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
	    
	    Font fTest = new Font("Consolas", Font.PLAIN, 72);

	    timeM1.setFont(fTest);
	    timeM2.setFont(fTest);
	    timeS1.setFont(fTest);
	    timeS2.setFont(fTest);
	    
	    changeColorFont(Color.GREEN);
	    
	    Font fStatus = new Font("Times New Roman", Font.BOLD, 14);
	    bombStatus = new JLabel();
	    bombStatus.setFont(fStatus);
	    j2.add(bombStatus);
	   	
	   	*/
	    
	    this.pack();
	    this.setLocationRelativeTo(null); // Center the window
	    this.setVisible(true);
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

	
	public void changeColorFont(Color c) {
		timeM1.setForeground(c);
		timeM2.setForeground(c);
		timeS1.setForeground(c);
		timeS2.setForeground(c);
	}
	
	public Color getColorFont() {
		return timeM1.getForeground();
	}

	public void setLGUI(leftGUI lGUI) {
		this.lGUI = lGUI;
	}

	public leftGUI getLGUI() {
		return lGUI;
	}

	public void setRGUI(rightGUI rGUI) {
		this.rGUI = rGUI;
	}

	public rightGUI getRGUI() {
		return rGUI;
	}
}
