package videoStream;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ImageStreamTester {

	public static final String IP = "10.0.0.106:8000"; // <<<<< Set IP and port from application here.
	
	protected ImageStreamTester m_instance;
	private JFrame m_frame;
	
	protected final JPanel buttonPanel = new JPanel(); 

	protected ImageStreamComponent m_imageStreamComponent = new ImageStreamComponent(IP);
	protected URL m_url;
	
	protected static String title = "Image Stream Test";

	public static void main(String args[]) {
		try	{
			ImageStreamTester m_instance = new ImageStreamTester();
			m_instance.run(args);
		} catch (Throwable t) {
			t.printStackTrace();
			System.err.println("Error: " + t.getMessage());
			System.exit(1);
		}
	}
	
	public void run(String[] args)  {
		
		m_frame = new JFrame(title);
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //Dimension d = new Dimension(m_imageStreamPanel.getWidth(), m_imageStreamPanel.getHeight()+20);
	    //m_frame.setSize(d);
	    m_frame.setResizable(false);
	
	    WindowListener listener = new WindowAdapter() {
	      public void windowClosing(WindowEvent w) {
	    	  m_imageStreamComponent.Kill();
	      }
	    };
	    
	    m_frame.addWindowListener(listener);
		m_frame.getContentPane().add(new JScrollPane(m_imageStreamComponent), BorderLayout.CENTER);
	    m_frame.pack();
	    m_frame.setVisible(true);
	}
}