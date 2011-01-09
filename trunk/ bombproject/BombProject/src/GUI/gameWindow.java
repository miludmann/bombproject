package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import widgets.InfraredRadar;

@SuppressWarnings("serial")
public class gameWindow extends JFrame{

	private leftGUI lGUI;
	private rightGUI rGUI;
	
	public gameWindow(){
	
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		this.setSize(1280, 800);
	    
	    Container contentPane = this.getContentPane();
		BoxLayout layout = new BoxLayout(contentPane, BoxLayout.X_AXIS);
		contentPane.setLayout(layout);


		this.setLGUI(new leftGUI());
	    this.setRGUI(new rightGUI());
	    
	    contentPane.add(getLGUI());
	    contentPane.add(getRGUI());


	    /*****/
	    
	    this.pack();
	    this.setLocationRelativeTo(null); // Center the window
	    this.setResizable(true);
	    this.setVisible(true);
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
