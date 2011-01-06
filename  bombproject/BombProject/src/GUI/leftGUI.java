package GUI;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class leftGUI extends JPanel{
	
	private JPanel streamPanel;
	private JPanel mapPanel;
	
	public leftGUI(){
		
		BoxLayout mainLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		FlowLayout defaultLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);

		this.setLayout(mainLayout);
		
		this.setStreamPanel(new JPanel());
		this.setMapPanel(new JPanel());
		this.getStreamPanel().setLayout(defaultLayout);
		this.getMapPanel().setLayout(defaultLayout);
		
		this.add(this.getStreamPanel());
		this.add(this.getMapPanel());
		
	    JLabel labelImg = new JLabel();
	    ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "/src/GUI/map.png");
	    
	    labelImg.setIcon(img);
	    getMapPanel().add(labelImg);

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
