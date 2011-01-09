package GUI;

import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class rightGUI extends Container {
	
	private infoPanel infoPanel;
	private timePanel timePanel;
	private defusePanel defusePanel;
	private radarPanel radarPanel;
	
	public rightGUI(){
		
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
				
		this.setInfoPanel(new infoPanel());
		this.setTimePanel(new timePanel());
		this.setRadarPanel(new radarPanel());
		this.setDefusePanel(new defusePanel());
		
		this.add(this.getInfoPanel());
		this.add(this.getTimePanel());
		this.add(this.getRadarPanel());
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

	public void setRadarPanel(radarPanel radarPanel) {
		this.radarPanel = radarPanel;
	}

	public radarPanel getRadarPanel() {
		return radarPanel;
	}

}
