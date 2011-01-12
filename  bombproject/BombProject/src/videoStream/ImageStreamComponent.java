package videoStream;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import GUI.TestGUISmall;

public class ImageStreamComponent extends javax.swing.JComponent {

	private static final long serialVersionUID = -1004205453448744394L;

	protected BufferedImage m_image;
	protected BufferedImage m_imageNoConnection;
	
	protected URL m_url;
	
	protected RefreshHandler m_refreshHandler = new RefreshHandler();
	protected Thread m_thread;
	
	public ImageStreamComponent(String url)
	{
		// Lazy loaded and cached
		if (m_imageNoConnection == null) {
			try {
				m_imageNoConnection = ImageIO.read(new File("gfx\\no_connection.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		try {
		
			m_url = new URL("http://" + url + "/shot.jpg");
			m_image = m_imageNoConnection;
			//m_image = ImageIO.read(m_url);
			
		    //Dimension size = new Dimension(m_image.getWidth(null), m_image.getHeight(null));
			Dimension size = new Dimension(800, 480);
		    setPreferredSize(size);
		    setMinimumSize(size);
		    setMaximumSize(size);
		    setSize(size);
		    setLayout(null);
		    
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} //catch (IOException e) {
//			e.printStackTrace();
//		}
		
		//m_thread.setDaemon(true);
		m_thread = new Thread(m_refreshHandler);
		m_thread.setDaemon(true);
		m_thread.start();
	}
	
	public void Kill() {
		m_refreshHandler.Kill();
	}
	
	
	@Override
	public void paint(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		super.paint(g2d);		
		
		setBackground(Color.WHITE);
		
		BufferedImage img = m_image;
		g2d.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), 0, 0, img.getWidth(), img.getHeight(),  null);
	}
	
	final class RefreshHandler implements Runnable {

		protected volatile boolean m_running = true; 
		
		@Override
		public void run() {
			while(m_running) {
				try {
					m_image = ImageIO.read(m_url);
					repaint();
				} catch (NullPointerException e) {
					//Will most times throw a nullpointer exception when closing the application.
				} catch (IIOException e) {
					System.out.println("Warning no connection to stream");
					m_image = m_imageNoConnection;
				} catch (Exception e) {
					System.out.println("Error reading image from URL");
					m_image = m_imageNoConnection;
				}	
			}
		}
		
		public void Kill() {
			m_running = false;
		}
	}
}
