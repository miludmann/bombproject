package GUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;

import widgets.WirecutWidget;

@SuppressWarnings("serial")
public class defusePanel extends Container {
	
	private WirecutWidget combinaison;
	
	public defusePanel(){
		
		FlowLayout defaultLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
		this.setLayout(defaultLayout);

		setCombinaison(new WirecutWidget());
		this.add(getCombinaison());
		this.getCombinaison().setMaximumSize(new Dimension(400, 200));
		
	}

	public void setCombinaison(WirecutWidget combinaison) {
		this.combinaison = combinaison;
	}

	public WirecutWidget getCombinaison() {
		return combinaison;
	}


}
