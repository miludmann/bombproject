package GUI;

import java.awt.Container;

import javax.swing.BoxLayout;

import widgets.InfraredRadar;

@SuppressWarnings("serial")
public class radarPanel extends Container {
	
	private InfraredRadar irRadar;
	
	public radarPanel(){
		
		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(layout);
		
		setIrRadar(new InfraredRadar());
		
		this.add(getIrRadar());
	}

	public void setIrRadar(InfraredRadar irRadar) {
		this.irRadar = irRadar;
	}

	public InfraredRadar getIrRadar() {
		return irRadar;
	}

}
