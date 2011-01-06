package GUI;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class rightGUI extends JPanel {
	
	private JPanel timePanel;
	private JPanel infoPanel;
	
	public rightGUI(){
		
		BoxLayout mainLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(mainLayout);
		
		this.setTimePanel(new timePanel());
		this.add(this.getTimePanel());
	}

	public void setTimePanel(JPanel timePanel) {
		this.timePanel = timePanel;
	}

	public JPanel getTimePanel() {
		return timePanel;
	}

}
