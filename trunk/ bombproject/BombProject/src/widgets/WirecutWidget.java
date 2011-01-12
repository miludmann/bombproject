package widgets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class WirecutWidget extends JPanel {

	private static final long serialVersionUID = 3561474001673677964L;
	protected final static int WIRE_CELL_WIDTH = 40; 
	
	protected BufferedImage m_imgWires = null;
	protected BufferedImage m_imgWiresCut = null;
	
	protected ArrayList<Wire> m_sequence;
	protected int m_currentSeqIndex;
	
	protected boolean m_showColor;
	
	public WirecutWidget()
	{
		setNativeLookAndFeel();
		setBackground(Color.GRAY);
			
		this.setSize(600, 200);
		this.setDoubleBuffered(true);
		
		m_sequence = new ArrayList<Wire>();
		m_currentSeqIndex = 0;
		
		// Lazy loaded and cached
		if (m_imgWires == null || m_imgWiresCut == null) {
			try {
				m_imgWires = ImageIO.read(new File("gfx\\wires.png"));
				m_imgWiresCut = ImageIO.read(new File("gfx\\wiresCut.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Clearing the sequence
		m_showColor = false;
		setSequence("");
	}
	
	public void setSequence(String seq) {
		reset();
		
		for(int i=0; i<seq.length(); i++)
		{
			WireColor wireColor = getWireColorFromSeq(seq.charAt(i));
			m_sequence.add(new Wire(wireColor));
		}
		
		invalidate();
		repaint();
	}
	
	public int getSequenceLength() {
		return m_sequence.size();
	}
	
	public void setCut(int seqNumber)
	{
		if(seqNumber <= m_sequence.size()-1)
			m_sequence.get(seqNumber).setCut(true);
		
		invalidate();
		repaint();		
	}
	
	public void setWireColorEnabled(boolean enabled)
	{
		m_showColor = enabled;
	}
	
	public void reset()
	{
		m_sequence.clear();
		m_currentSeqIndex = 0;
		
		invalidate();
		repaint();
	}
	
	protected void DrawWire(Graphics2D g2d, int location, WireColor wire, boolean isCut)
	{
		// Find cutting column
		int xCutStart = getWireCutZone(wire);
		int yCutStart = 50;  //To center the cut
		int cutWidth = 36;
		
		g2d.drawImage((isCut?m_imgWiresCut:m_imgWires), location * WIRE_CELL_WIDTH, 0, (location * WIRE_CELL_WIDTH) + cutWidth, 200, xCutStart, yCutStart, xCutStart + cutWidth, yCutStart + this.getHeight(), null);
	}

	public void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		clear(g);
		
		setBackground(Color.WHITE);
		
		try {		
		
			for(int i=0; i<m_sequence.size(); i++)
			{
				Wire wire = m_sequence.get(i);
				DrawWire(g2d, i, (m_showColor?wire.getColor():WireColor.White), wire.getCut());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	protected enum WireColor
	{
		Red,
		Green,
		Blue,
		Yellow,
		White
	}
	
	protected WireColor getWireColorFromSeq(char c)
	{
		switch(c)
		{
			case 'r':
				return WireColor.Red;
			case 'g':
				return WireColor.Green;
			case 'b':
				return WireColor.Blue;
			case 'y':
				return WireColor.Yellow;
			case 'w':
				return WireColor.White;
			default:
				System.out.println("Illegal char for wire. Returning white as unknow color");
				return WireColor.White;
		}
	}
	
	protected int getWireCutZone(WireColor wc)
	{
		switch(wc)
		{
			case White:
				return 0;
			case Blue:
				return 50;
			case Green:
				return 100;
			case Yellow:
				return 150;
			case Red:
				return 200;
			default:
				System.out.println("Illegal wire color for wire. Returning white as cut zone");
				return 0;
		}
	}
	
	private class Wire
	{
		private WireColor m_color;
		private boolean m_isCut;
		
		Wire(WireColor color)
		{
			m_color = color;
			m_isCut = false;
		}
		
		public void setCut(boolean isCut)
		{
			m_isCut = isCut;
		}
		
		public boolean getCut()
		{
			return m_isCut;
		}
		
		public WireColor getColor()
		{
			return m_color;
		}
	}
	
	public void nextWireColorCut(char nextColor)
	{
		Wire nextWireToCut = m_sequence.get(m_currentSeqIndex);
		if(nextWireToCut.getColor() == getWireColorFromSeq(nextColor))
		{
			nextWireToCut.setCut(true);
			m_currentSeqIndex++;
			
			invalidate();
			repaint();
		}
		else
		{
			//TODO: Play failed sound.
			reset();
		}
	}
}