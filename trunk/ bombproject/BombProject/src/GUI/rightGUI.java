package GUI;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class rightGUI extends JPanel {
	
	private JPanel infoPanel;
	private JPanel timePanel;
	
	public rightGUI(){
		
		BoxLayout mainLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(mainLayout);
				
		this.setInfoPanel(new infoPanel());
		this.add(this.getInfoPanel());
		
		this.setTimePanel(new timePanel());
		this.add(this.getTimePanel());
	}

	public void setTimePanel(JPanel timePanel) {
		this.timePanel = timePanel;
	}

	public JPanel getTimePanel() {
		return timePanel;
	}

	public void setInfoPanel(JPanel infoPanel) {
		this.infoPanel = infoPanel;
	}

	public JPanel getInfoPanel() {
		return infoPanel;
	}

}
