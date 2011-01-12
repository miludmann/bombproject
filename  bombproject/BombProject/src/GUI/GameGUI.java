package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import machine.settings;
import videoStream.ImageStreamComponent;
import widgets.InfraredRadar;
import widgets.ObstacleRadar;
import widgets.WirecutWidget;

@SuppressWarnings("serial")
public class GameGUI extends JFrame {
		
	//protected JFrame m_frame;
	
	protected JPanel horisontalPanelTop;
	protected JPanel horisontalPanelButtom;
	protected JPanel verticalPanel;
	protected JPanel verticalInfoPanel;
	
	protected static String title = "Lego Strike";
	
	protected timePanel m_timePanel;
	protected infoPanel m_infoPanel;
	
	protected WirecutWidget m_wireCut;
	protected InfraredRadar m_irRadar;
	protected ObstacleRadar m_obstacleRadar;

	protected ImageStreamComponent m_imageStreamPanel;
	protected mapPanel m_mapPanel;
	
	private boolean isTerrorist;
		
	
	public GameGUI(boolean isTerrorist){
		setTerrorist(isTerrorist);
		this.run();
	}
	
	
	public void run()  {
		
		//Setting frame 
		this.setTitle(title);
		this.setSize(1600, 900);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		
		//Setting vertical panel
		verticalPanel = new JPanel();
		verticalPanel.setBackground(Color.WHITE);
		BoxLayout layoutVertical = new BoxLayout(verticalPanel, BoxLayout.Y_AXIS);
		verticalPanel.setLayout(layoutVertical);
		
		//Setting horisontal panel top
		horisontalPanelTop = new JPanel();
		horisontalPanelTop.setBackground(Color.WHITE);
		BoxLayout layoutHorisontalTop = new BoxLayout(horisontalPanelTop, BoxLayout.X_AXIS);
		horisontalPanelTop.setLayout(layoutHorisontalTop);
		
		//Setting horisontal panel bottom
		horisontalPanelButtom = new JPanel();
		horisontalPanelButtom.setBackground(Color.WHITE);
		BoxLayout layoutHorisontalButtom = new BoxLayout(horisontalPanelButtom, BoxLayout.X_AXIS);
		horisontalPanelButtom.setLayout(layoutHorisontalButtom);
		
		//Setting vertical info panel for the text
		verticalInfoPanel = new JPanel();
		verticalInfoPanel.setBackground(Color.WHITE);
		BoxLayout layoutVerticalInfoPanel = new BoxLayout(verticalInfoPanel, BoxLayout.Y_AXIS);
		verticalInfoPanel.setLayout(layoutVerticalInfoPanel);
				
		//TOP
		m_obstacleRadar = new ObstacleRadar();
		m_obstacleRadar.setMaximumSize(new Dimension(400, 400));
		m_obstacleRadar.setCmPixelRatio(0.7d);
		horisontalPanelTop.add(m_obstacleRadar);

		
		if(this.isTerrorist())
		{
			m_imageStreamPanel = new ImageStreamComponent(settings.streamT);

		}
		else
		{
			m_imageStreamPanel = new ImageStreamComponent(settings.streamAT);

		}
		m_imageStreamPanel.setMaximumSize(new Dimension(800, 450));
		horisontalPanelTop.add(m_imageStreamPanel);
		
		m_irRadar = new InfraredRadar();
		m_irRadar.setMaximumSize(new Dimension(400, 400));
		horisontalPanelTop.add(m_irRadar);
		
		//BOTTON
		m_timePanel = new timePanel();
		m_timePanel.setBackground(Color.WHITE);
		//m_timePanel.setTimeDisplayed(new JLabel("10:00"));
		m_timePanel.setMaximumSize(new Dimension(390, 100));
		verticalInfoPanel.add(m_timePanel);
		
		m_infoPanel = new infoPanel();
		m_infoPanel.setBackground(Color.WHITE);
		//m_infoPanel.changeInfos(false, true, false, false);
		//m_infoPanel.setTimeDisplayed(new JLabel("10:00"));
		m_infoPanel.setMaximumSize(new Dimension(390, 100));
		verticalInfoPanel.add(m_infoPanel);
		horisontalPanelButtom.add(verticalInfoPanel);
		
		m_mapPanel = new mapPanel();
		m_mapPanel.setMaximumSize(new Dimension(800, 450));
		//m_mapPanel.setMaximumSize(new Dimension(1263, 450));
		//m_mapPanel.setBorder(BorderFactory.createBevelBorder(10));
		horisontalPanelButtom.add(m_mapPanel);
		
		m_wireCut = new WirecutWidget();
		m_wireCut.setWireColorEnabled(false);
		m_wireCut.setSequence("wwwwwwwwww");
		m_wireCut.setMaximumSize(new Dimension(390, 450));
		horisontalPanelButtom.add(m_wireCut);
		
		
		//Putting the together
		verticalPanel.add(horisontalPanelTop);
		verticalPanel.add(horisontalPanelButtom);
		
		//Adding to frame
		this.add(verticalPanel);
	    this.setResizable(true);
	
	    WindowListener listener = new WindowAdapter() {
	      public void windowClosing(WindowEvent w) {
	    	  //m_imageStreamComponent.Kill();
	      }
	    };
	    
	    this.addWindowListener(listener);
		//m_frame.pack();
	    this.setVisible(true);
	    
	    m_obstacleRadar.setShowCompass(true);
	}
	
	public timePanel getM_timePanel() {
		return m_timePanel;
	}



	public void setM_timePanel(timePanel panel) {
		m_timePanel = panel;
	}



	public infoPanel getM_infoPanel() {
		return m_infoPanel;
	}



	public void setM_infoPanel(infoPanel panel) {
		m_infoPanel = panel;
	}



	public WirecutWidget getM_wireCut() {
		return m_wireCut;
	}



	public void setM_wireCut(WirecutWidget cut) {
		m_wireCut = cut;
	}



	public InfraredRadar getM_irRadar() {
		return m_irRadar;
	}



	public void setM_irRadar(InfraredRadar radar) {
		m_irRadar = radar;
	}



	public ObstacleRadar getM_obstacleRadar() {
		return m_obstacleRadar;
	}



	public void setM_obstacleRadar(ObstacleRadar radar) {
		m_obstacleRadar = radar;
	}



	public ImageStreamComponent getM_imageStreamPanel() {
		return m_imageStreamPanel;
	}



	public void setM_imageStreamPanel(ImageStreamComponent streamPanel) {
		m_imageStreamPanel = streamPanel;
	}



	public mapPanel getM_mapPanel() {
		return m_mapPanel;
	}



	public void setM_mapPanel(mapPanel panel) {
		m_mapPanel = panel;
	}


	public void setTerrorist(boolean isTerrorist) {
		this.isTerrorist = isTerrorist;
	}


	public boolean isTerrorist() {
		return isTerrorist;
	}
}
