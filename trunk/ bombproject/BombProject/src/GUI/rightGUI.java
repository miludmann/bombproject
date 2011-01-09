package GUI;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class rightGUI extends JPanel {
	
	private infoPanel infoPanel;
	private timePanel timePanel;
	private defusePanel defusePanel;
	
	public rightGUI(){
		
		BoxLayout mainLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(mainLayout);
				
		this.setInfoPanel(new infoPanel());
		this.add(this.getInfoPanel());
		
		this.setTimePanel(new timePanel());
		this.add(this.getTimePanel());
		
		this.setDefusePanel(new defusePanel());
		this.add(this.getDefusePanel());
	}

	public void setTimePanel(timePanel timePanel) {
		this.timePanel = timePanel;
	}

	public JPanel getTimePanel() {
		return timePanel;
	}

	public void setInfoPanel(infoPanel infoPanel) {
		this.infoPanel = infoPanel;
	}

	public JPanel getInfoPanel() {
		return infoPanel;
	}

	public void setDefusePanel(defusePanel defusePanel) {
		this.defusePanel = defusePanel;
	}

	public defusePanel getDefusePanel() {
		return defusePanel;
	}

}
