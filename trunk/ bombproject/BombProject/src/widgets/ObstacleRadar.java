package widgets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.UIManager;

public class ObstacleRadar extends JPanel {

	private static final long serialVersionUID = -7984037854881469865L;
	
	protected ArrayList<Obstacle> m_obstacles;
	protected Calendar m_calander;
	
	protected Color m_surfaceColor;
	
	protected double m_cmPixelRatio;
	
	public ObstacleRadar()
	{
		setNativeLookAndFeel();
		setBackground(Color.GRAY);
	
		m_obstacles = new ArrayList<Obstacle>();
		m_calander = Calendar.getInstance();
		
		m_cmPixelRatio = 1; //1 cm = 1 pixel
		m_surfaceColor = new Color(0, 70, 0);
		
		
		this.setSize(400, 400);
		this.setDoubleBuffered(true);
	}
	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		try {		
		
			drawSurface(g2d);
			
			for(int i=0; i<m_obstacles.size(); i++)
				drawObstacle(m_obstacles.get(i), g2d);	
			
			//g2d.drawImage(m_imgSensorTop ,sensorLeft, sensorTop, sensorLeft+m_sensorImgWidth, sensorTop+m_sensorImgHeight, 0, 0, m_imgSensorTop.getWidth(), m_imgSensorTop.getHeight(), null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void drawSurface(Graphics2D g2d)
	{
		g2d.setPaint(m_surfaceColor);
		g2d.fillOval(10, 10, this.getWidth()-20, this.getWidth()-20);
	}
	
	private void drawObstacle(Obstacle obstacle, Graphics2D g2d) {
		g2d.setPaint(new Color(0, 255, 0));
		g2d.drawLine(obstacle.getXCm(), obstacle.getYCm(), obstacle.getXCm(), obstacle.getXCm());
	}
	
//	protected void drawSensor(int sensor, int intencity, Graphics2D g2d)
//	{
//		int sensorWidth = getWidth()/20;
//		int sensorHeight = getHeight()/20;
//		
//		int sensorLeft = (int)Math.round((float)getWidth()/100f*29.5f);
//		int sensorTop = (int)Math.round((float)getHeight()/100f*76f);
//		
//		int sensorOffset = (int)Math.round((float)getWidth()/100*9);
//		
//		g2d.setPaint(new Color(0, 0, 0));
//		g2d.fillOval(sensorLeft + (sensorOffset*sensor),sensorTop, sensorWidth, sensorHeight);
//		
//		g2d.setPaint(new Color(intencity, 0, 0));
//		g2d.fillOval(sensorLeft+2 +(sensorOffset*sensor), sensorTop+2, sensorWidth-4, sensorHeight-4);
//	}

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
	
	public void setCmPixelRatio(double ratio)
	{
		m_cmPixelRatio = ratio;
	
		invalidate();
		repaint();
	}
	
	public int addObstacle(int Xcm, int Ycm)
	{
		Obstacle obstacle = new Obstacle(Xcm, Ycm);
		m_obstacles.add(obstacle);
		
		invalidate();
		repaint();
		
		return m_obstacles.size();
	}
	
	private class Obstacle
	{
		protected int m_xCm;
		protected int m_yCm;
		protected boolean m_firstDraw;
		protected Date m_timeStamp;
		
		Obstacle(int xCm, int yCm) {
			m_xCm = xCm;
			m_yCm = yCm;
			m_firstDraw = true;
			m_timeStamp = m_calander.getTime(); 
		}
		
		public int getXCm()
		{
			return m_xCm;
		}
		
		public int getYCm()
		{
			return m_yCm;
		}
		
		public boolean getFirstDraw()
		{
			return m_firstDraw;
		}
		
		public Date getTimeStamp()
		{
			return m_timeStamp;
		}		
	}
}
