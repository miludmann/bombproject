package widgets;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WidgetTester {

	protected WidgetTester m_instance;
	private JFrame m_frame;
	
	protected final JPanel buttonPanel = new JPanel(); 
	protected static String title = "Widget Test";
	
	protected InfraredRadar m_irRadar;
	protected ObstacleRadar m_obstacleRadar;

	public static void main(String args[]) {
		try	{
			WidgetTester m_instance = new WidgetTester();
			m_instance.run(args);
		} catch (Throwable t) {
			t.printStackTrace();
			System.err.println("Error: " + t.getMessage());
			System.exit(1);
		}
	}
	
	public void run(String[] args)  {
		
		m_frame = new JFrame(title);
		m_frame.setSize(1600, 900);
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		Container contentPane = m_frame.getContentPane();
		BoxLayout layout = new BoxLayout(contentPane, BoxLayout.X_AXIS);
		contentPane.setLayout(layout);
		
		m_irRadar = new InfraredRadar();
		//m_irRadar.setMinimumSize(new Dimension(200,200));
		contentPane.add(m_irRadar);
		
		m_obstacleRadar = new ObstacleRadar();
		//m_obstacleRadar.setMinimumSize(new Dimension(200,200));
		contentPane.add(m_obstacleRadar);
		
		//Dimension d = new Dimension(m_imageStreamPanel.getWidth(), m_imageStreamPanel.getHeight()+20);
	    //m_frame.setSize(d);
	    m_frame.setResizable(true);
	
	    WindowListener listener = new WindowAdapter() {
	      public void windowClosing(WindowEvent w) {
	    	  //m_imageStreamComponent.Kill();
	      }
	    };
	    
	    m_frame.addWindowListener(listener);
		//m_frame.pack();
	    m_frame.setVisible(true);
	    
	    
	    while(true)
	    {
	    	
	    	for(int i=0; i<5; i++)
	    	{
	    		for(int j=0; j<256; j++)
	    		{
	    			m_irRadar.setSensor(i, j);
	    			m_frame.repaint();
	    			
	    			try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    		}
	    	}
	    	
		    for(int i=0; i<10; i++)
		    {
		    	m_irRadar.setDirection(i);
		    	m_frame.repaint();
		    	
		    	try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
		    
		    for(int i=0; i<5; i++)
		    	m_irRadar.setSensor(i, 0);
		    
		    m_irRadar.setDirection(0);
	    	m_frame.repaint();
	    }
	}
}