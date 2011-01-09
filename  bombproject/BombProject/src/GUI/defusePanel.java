package GUI;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class defusePanel extends JPanel {
	
	private JLabel combinaison;
	
	
	public defusePanel(){
		
		FlowLayout defaultLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
		this.setLayout(defaultLayout);

		setCombinaison(new JLabel());
		this.add(getCombinaison());
		
	}

	public void setCombinaison(JLabel combinaison) {
		this.combinaison = combinaison;
	}

	public JLabel getCombinaison() {
		return combinaison;
	}

}
