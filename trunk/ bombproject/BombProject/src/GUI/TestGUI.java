package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import videoStream.ImageStreamComponent;
import widgets.InfraredRadar;
import widgets.ObstacleRadar;
import widgets.WirecutWidget;

public class TestGUI {
	
	protected final static String IP_PORT_FOR_CAMERA = "10.0.0.119:8000";
	
	protected JFrame m_frame;
	
	protected JPanel horisontalPanel;
	protected JPanel verticalPanelLeft; 
	protected JPanel verticalPanelRight; 
	
	protected static String title = "Lego Strike";
	
	protected timePanel m_timePanel;
	protected WirecutWidget m_wireCut;
	protected InfraredRadar m_irRadar;
	protected ObstacleRadar m_obstacleRadar;

	protected ImageStreamComponent m_imageStreamPanel;
	protected mapPanel m_mapPanel;
	
	protected Random m_rand;
	
	public static void main(String args[]) {
		try	{
			TestGUI m_instance = new TestGUI();
			m_instance.run(args);
		} catch (Throwable t) {
			t.printStackTrace();
			System.err.println("Error: " + t.getMessage());
			System.exit(1);
		}
	}
	
	public void run(String[] args)  {
		
		//Setting frame 
		m_frame = new JFrame(title);
		m_frame.setSize(1920, 1200);
		m_frame.setResizable(false);
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		m_rand = new Random();
		
		//Setting right vertical panel
		horisontalPanel = new JPanel();
		horisontalPanel.setBackground(Color.WHITE);
		BoxLayout layoutHorisontal = new BoxLayout(horisontalPanel, BoxLayout.X_AXIS);
		horisontalPanel.setLayout(layoutHorisontal);
		
		
		//Setting right vertical panel
		verticalPanelRight = new JPanel();
		verticalPanelRight.setBackground(Color.WHITE);
		BoxLayout layoutRight = new BoxLayout(verticalPanelRight, BoxLayout.Y_AXIS);
		verticalPanelRight.setLayout(layoutRight);
		
		m_timePanel = new timePanel();
		m_timePanel.setTimeDisplayed(new JLabel("10:00"));
		m_timePanel.setMaximumSize(new Dimension(400, 100));
		verticalPanelRight.add(m_timePanel);
		
		m_obstacleRadar = new ObstacleRadar();
		m_obstacleRadar.setMaximumSize(new Dimension(400, 400));
		m_obstacleRadar.setCmPixelRatio(0.5d);
		verticalPanelRight.add(m_obstacleRadar);

		m_irRadar = new InfraredRadar();
		m_irRadar.setMaximumSize(new Dimension(400, 400));
		verticalPanelRight.add(m_irRadar);
		
		m_wireCut = new WirecutWidget();
		m_wireCut.setWireColorEnabled(true);
		m_wireCut.setSequence("bggyrrrygg");
		m_wireCut.setMaximumSize(new Dimension(400, 200));
		verticalPanelRight.add(m_wireCut);
		
		//Setting left vertical panel
		verticalPanelLeft = new JPanel();
		BoxLayout layoutLeft = new BoxLayout(verticalPanelLeft, BoxLayout.Y_AXIS);
		verticalPanelLeft.setLayout(layoutLeft);
		
		m_imageStreamPanel = new ImageStreamComponent(IP_PORT_FOR_CAMERA);
		m_imageStreamPanel.setMaximumSize(new Dimension(1520, 600));
		verticalPanelLeft.add(m_imageStreamPanel);
		
		m_mapPanel = new mapPanel();
		m_mapPanel.setMaximumSize(new Dimension(1520, 600));
		m_mapPanel.setBorder(BorderFactory.createBevelBorder(10));
		verticalPanelLeft.add(m_mapPanel);
		
		horisontalPanel.add(verticalPanelLeft);
		horisontalPanel.add(verticalPanelRight);
		
		m_frame.add(horisontalPanel);
	    m_frame.setResizable(true);
	
	    WindowListener listener = new WindowAdapter() {
	      public void windowClosing(WindowEvent w) {
	    	  //m_imageStreamComponent.Kill();
	      }
	    };
	    
	    m_frame.addWindowListener(listener);
		//m_frame.pack();
	    m_frame.setVisible(true);
	    
	    m_obstacleRadar.setShowCompass(true);
	    
	    while(true)
	    {
	    	
	    	for(int i=0; i<m_wireCut.getSequenceLength(); i++)
	    	{
	    		m_wireCut.setCut(i);
	    		
	    		//m_wireCut.nextWireColorCut('b');
	    		
	    		try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		
	    	}
	    	
	    	for(int i=0; i<5; i++)
	    	{
	    		for(int j=0; j<256; j++)
	    		{
	    			m_irRadar.setSensor(i, j);
	    			//m_frame.repaint();
	    			
	    			try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					int x = (int)Math.round((m_rand.nextDouble()*510d)-255d);
					int y = (int)Math.round((m_rand.nextDouble()*510d)-255d);
					
					m_obstacleRadar.addObstacle(x, y);
	    		}
	    	}
	    	
		    for(int i=0; i<10; i++)
		    {
		    	m_irRadar.setDirection(i);
		    	
		    	try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
		    
		    for(int i=0; i<5; i++)
		    	m_irRadar.setSensor(i, 0);
		    
		    m_irRadar.setDirection(0);
		    
		    
		    for(int i=0; i<361; i++)
		    {
		    	m_obstacleRadar.setCompassAngle(i);
		    	
		    	try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
	    	
	    }
	}
}
