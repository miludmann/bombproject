package widgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class InfraredRadar extends JPanel {

	private static final long serialVersionUID = 3561474001673677964L;
	protected BufferedImage m_imgSensorTop = null;
	
	protected static final int m_startAngleDegrees = 225;
	protected static final int m_beamWidthDegrees = -30;
	
	protected static final int m_sensorImgWidth = 42;
	protected static final int m_sensorImgHeight = 80;
	
	protected static final int m_directionOffColor = 70;
	protected static final int m_directionOnColor = 255;
	
	protected int[] m_sectorValues;
	protected int[] m_sensorValues;
	
	public InfraredRadar()
	{
		setNativeLookAndFeel();
		setBackground(Color.GRAY);
	
		m_sectorValues = new int[9]; //Representing zone 1 to 9
		m_sensorValues = new int[5]; //Representing the values of the 5 available sensors
		
		//this.setSize(400, 400);
		this.setDoubleBuffered(true);
		
		// Lazy loaded and cached
		if (m_imgSensorTop == null) {
			try {
				m_imgSensorTop = ImageIO.read(new File("gfx\\IRSeekerTop.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Clearing the direction
		setDirection(0);
	}
	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		clear(g);
		
		setBackground(Color.WHITE);
		
		try {		
		
			int sensorLeft = (this.getWidth()-m_sensorImgWidth)/2; 
			int sensorTop = (this.getHeight()-m_sensorImgHeight)/2; 
			
			for(int i=0; i<9; i++)
				drawSegment(i, m_sectorValues[i], g2d);	
		
			for(int i=0; i<5; i++)
				drawSensor(i, m_sensorValues[i], g2d);	
			
			g2d.drawImage(m_imgSensorTop ,sensorLeft, sensorTop, sensorLeft+m_sensorImgWidth, sensorTop+m_sensorImgHeight, 0, 0, m_imgSensorTop.getWidth(), m_imgSensorTop.getHeight(), null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void drawSegment(int segmentNumber, int intencity, Graphics2D g2d)
	{
		g2d.setPaint(new Color(intencity, 0, 0));
		
		int angleOfSet = m_startAngleDegrees + (segmentNumber * m_beamWidthDegrees);
		g2d.fillArc(10,	 10, this.getWidth()-20, this.getHeight()-20, angleOfSet, m_beamWidthDegrees);
	}
	
	protected void drawSensor(int sensor, int intencity, Graphics2D g2d)
	{
		int sensorWidth = getWidth()/20;
		int sensorHeight = getHeight()/20;
		
		int sensorLeft = (int)Math.round((float)getWidth()/100f*29.5f);
		int sensorTop = (int)Math.round((float)getHeight()/100f*76f);
		
		int sensorOffset = (int)Math.round((float)getWidth()/100*9);
		
		g2d.setPaint(new Color(0, 0, 0));
		g2d.fillOval(sensorLeft + (sensorOffset*sensor),sensorTop, sensorWidth, sensorHeight);
		
		g2d.setPaint(new Color(intencity, 0, 0));
		g2d.fillOval(sensorLeft+2 +(sensorOffset*sensor), sensorTop+2, sensorWidth-4, sensorHeight-4);
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
	
	public void setDirection(int direction)
	{
		if(direction < 0 || direction > 9) //Direction range is only 1-9 
			return;
			
		for(int i=0; i<m_sectorValues.length; i++) {
			m_sectorValues[i] = (i+1==direction?m_directionOnColor:m_directionOffColor);
		}
		
		invalidate(); //indicate repaint required
		repaint();
	}
	
	public void setSensor(int sensor, int intensity)
	{
		if(sensor < 0 || sensor > 4 || intensity < 0 || intensity > 255) //Sensor range is only 0-4, intensity range 0-255 
			return;
		
		m_sensorValues[sensor] = intensity;
		
		invalidate(); //indicate repaint required
		repaint();
	}
}