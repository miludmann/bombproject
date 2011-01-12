package GUI;

import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class leftGUI extends Container {
	
	private JPanel streamPanel;
	private JPanel mapPanel;
	
	public leftGUI(){
		
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);

		
		this.setStreamPanel(new JPanel());
		this.setMapPanel(new mapPanel());
		
		this.add(this.getStreamPanel());
		this.add(this.getMapPanel());
	    
	}

	public void setStreamPanel(JPanel streamPanel) {
		this.streamPanel = streamPanel;
	}

	public JPanel getStreamPanel() {
		return streamPanel;
	}

	public void setMapPanel(JPanel mapPanel) {
		this.mapPanel = mapPanel;
	}

	public JPanel getMapPanel() {
		return mapPanel;
	}

}
