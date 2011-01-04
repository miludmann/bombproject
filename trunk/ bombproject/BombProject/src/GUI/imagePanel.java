package GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class imagePanel extends JPanel {
	
	private BufferedImage img = null;
  
	public imagePanel()
	{
		super();
	}

	public void update(Graphics g)
	{
		g.drawImage(getImg(), 0, 0, null);
		repaint();
	}
  
	public void paint (Graphics g)
	{
		update(g);
	}
	public void setImg(BufferedImage img) {
		this.img = img;
		validate();
		repaint();
	}
	
	public BufferedImage
	getImg() {
		return img;
	}
}
