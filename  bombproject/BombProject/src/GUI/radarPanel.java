package GUI;

import java.awt.Container;

import javax.swing.BoxLayout;

import widgets.InfraredRadar;
import widgets.ObstacleRadar;

@SuppressWarnings("serial")
public class radarPanel extends Container {
	
	private InfraredRadar irRadar;
	private ObstacleRadar oRadar;
	
	public radarPanel(){
		
		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(layout);
		
		setIrRadar(new InfraredRadar());
		setORadar(new ObstacleRadar());
		
		this.add(getIrRadar());
		this.add(getORadar());
	}

	public void setIrRadar(InfraredRadar irRadar) {
		this.irRadar = irRadar;
	}

	public InfraredRadar getIrRadar() {
		return irRadar;
	}

	public void setORadar(ObstacleRadar oRadar) {
		this.oRadar = oRadar;
	}

	public ObstacleRadar getORadar() {
		return oRadar;
	}

}
