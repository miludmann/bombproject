package widgets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.UIManager;

public class ObstacleRadar extends JPanel {

	private static final long serialVersionUID = -7984037854881469865L;
	
	protected ArrayList<Obstacle> m_obstacles;
	protected Calendar m_calander;
	
	protected final Color m_surfaceColor = new Color(0, 70, 0);
	protected Color m_scalingColor = new Color(80,255,80);
	protected Color m_compassNeedleColor = new Color(255,255,255);
	
	protected double m_cmPixelRatio;
	protected long m_maxAgeInMillis;
	protected int m_surfaceEdgeBoarder;

	
	protected double m_compassAngle = 0;
	protected boolean m_showCompass = false;
	
	public ObstacleRadar()
	{
		
		setNativeLookAndFeel();
		setBackground(Color.GRAY);
	
		m_obstacles = new ArrayList<Obstacle>();
		m_calander = Calendar.getInstance();
		
		m_surfaceEdgeBoarder = 10;
		m_cmPixelRatio = 1.00; //1 cm = 1 pixel
		m_maxAgeInMillis = 5000;
		
		this.setSize(400, 400);
		this.setDoubleBuffered(true);
	}
	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		clear(g);
		
		try {		
		
			drawSurface(g2d);
			
			for(int i=0; i<m_obstacles.size(); i++)
				drawObstacle(m_obstacles.get(i), g2d);
			
			if(m_showCompass)
				drawCompassNeedle(g2d);
			
			drawScaleing(g2d);
			drawCenter(g2d);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void drawScaleing(Graphics2D g2d) {

		int centerX = this.getWidth()/2;
		int centerY = this.getHeight()/2;
		
		ArrayList<Integer> meterRings = new ArrayList<Integer>();
		
		int ringRadius = 100;
		while(true)
		{
			meterRings.add((int)Math.round(ringRadius * m_cmPixelRatio)); //Radius
			ringRadius+=100;
			
			if(ringRadius*m_cmPixelRatio > (this.getWidth()/2 - m_surfaceEdgeBoarder))
				break;
		}
		
		g2d.setColor(m_scalingColor);
		for(int i=0; i<meterRings.size(); i++)
		{
			g2d.drawOval(centerX-meterRings.get(i), centerY-meterRings.get(i), 2*meterRings.get(i), 2*meterRings.get(i));
		}
	}
	
	private void drawCenter(Graphics2D g2d)
	{
		int centerX = this.getWidth()/2;
		int centerY = this.getHeight()/2;
		
		g2d.setColor(m_scalingColor);
		g2d.drawLine(centerX, centerY-10, centerX-10, centerY);
		g2d.drawLine(centerX-10, centerY, centerX+10, centerY);
		g2d.drawLine(centerX+10, centerY, centerX, centerY-10);
	}

	private void drawSurface(Graphics2D g2d)
	{
		g2d.setPaint(m_surfaceColor);
		g2d.fillOval(10, 10, this.getWidth()-20, this.getHeight()-20);
	}
	
	private void drawCompassNeedle(Graphics2D g2d)
	{
		int centerX = this.getWidth()/2;
		int centerY = this.getHeight()/2;
		
		double distance = this.getWidth()/2-(2*m_surfaceEdgeBoarder);
		double angle = (m_compassAngle-90d)*(180d / Math.PI);
		
		int endpointY = (int)Math.round( (double)centerY + distance * Math.sin( angle ) );
		int endpointX = (int)Math.round( (double)centerX + distance * Math.cos( angle ) );
		
		g2d.setPaint(m_compassNeedleColor);
		g2d.drawLine(centerX, centerY, endpointX, endpointY);
	}
	
	private void drawObstacle(Obstacle obstacle, Graphics2D g2d) {
		
		//Calculate age
		long ageInMillis = System.currentTimeMillis() - obstacle.getAgeInMillis();
		
		int intencity = (int)Math.round( ((((double)m_maxAgeInMillis-(double)ageInMillis)/(double)m_maxAgeInMillis*255d)+m_surfaceColor.getGreen()));
		if(intencity > 255)
			intencity = 255;
		
		int centerX = this.getWidth()/2;
		int centerY = this.getHeight()/2;
		
		//Calculate relative dot
		int obstacleX = (int)Math.round(centerX+(obstacle.getXCm()*m_cmPixelRatio));
		int obstacleY = (int)Math.round(centerY-(obstacle.getYCm()*m_cmPixelRatio));
		
		if(ageInMillis >= m_maxAgeInMillis)
		{
			m_obstacles.remove(obstacle); //to old remove it
			return;
		}
		else
		{
			g2d.setPaint(new Color(0, intencity, 0));
			g2d.fillOval(obstacleX, obstacleY, 4, 4);
		}
		
		//g2d.drawLine(obstacle.getXCm(), obstacle.getYCm(), 1, 1);
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
	
	public void setCmPixelRatio(double ratio)
	{
		m_cmPixelRatio = ratio;
	
		invalidate();
		repaint();
	}
	
	public void setMaxObstacleAgeMillis(int maxAgeMs)
	{
		m_maxAgeInMillis = maxAgeMs;
	}
	
	public void setShowCompass(boolean enabled)
	{
		m_showCompass = enabled;
	}
	
	public void setCompassAngle(double angle)
	{
		m_compassAngle = angle;
		
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
		protected long m_ageInMillis;
		
		Obstacle(int xCm, int yCm) {
			m_xCm = xCm;
			m_yCm = yCm;
			m_firstDraw = true;
			m_ageInMillis = System.currentTimeMillis(); 
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
		
		public long getAgeInMillis()
		{
			return m_ageInMillis;
		}
		
	}
}
