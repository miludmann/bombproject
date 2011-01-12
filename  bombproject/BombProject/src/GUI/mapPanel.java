package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class mapPanel extends JPanel {

	private static final long serialVersionUID = -7006370453342665045L;
	protected BufferedImage m_imgMap = null;
	
	public mapPanel()
	{
		setNativeLookAndFeel();
			
		this.setSize(1520, 600);
		this.setDoubleBuffered(true);
		
		// Lazy loaded and cached
		if (m_imgMap == null) {
			try {
				m_imgMap = ImageIO.read(new File("gfx\\map.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		clear(g);

		setBackground(Color.WHITE);
		
		g2d.drawImage(m_imgMap, 10, 10, this.getWidth()-10, this.getHeight()-10, 0, 0, m_imgMap.getWidth(), m_imgMap.getHeight(), null);		
	}
	
	protected void clear(Graphics g) {
		super.paintComponent(g);
	}

	public void setNativeLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
	}
	
}
