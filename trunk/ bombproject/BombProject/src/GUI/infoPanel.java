package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class infoPanel extends JPanel {
	
	private JLabel info1;
	private JLabel info2;
	
	public infoPanel(){
		
		BoxLayout mainLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		FlowLayout defaultLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
		this.setLayout(mainLayout);

		setInfo1(new JLabel());
		getInfo1().setLayout(defaultLayout);
		add(info1);
		
		setInfo2(new JLabel());
		getInfo2().setLayout(defaultLayout);
		add(info2);
		
		getInfo1().setText("WAITING FOR");
		getInfo2().setText("GAME TO START");
		
		getInfo1().setAlignmentX(CENTER_ALIGNMENT);
		getInfo2().setAlignmentX(CENTER_ALIGNMENT);
		
		getInfo1().setFont(new Font("Serif", Font.BOLD, 20));
		getInfo2().setFont(new Font("Serif", Font.BOLD, 30));

	}

	public void setInfo1(JLabel info) {
		this.info1 = info;
	}

	public JLabel getInfo1() {
		return info1;
	}
	
	public void changeColor(Color c){
		getInfo2().setForeground(c);
	}

	public void setInfo2(JLabel info2) {
		this.info2 = info2;
	}

	public JLabel getInfo2() {
		return info2;
	}
	
	
	public void changeInfos(boolean isFinished,
							boolean isTerrorist,
							boolean isPlanted,
							boolean isDefused){
		
		String display = "";

		if(!isFinished)
		{
			if(isTerrorist)
			{
				if(isPlanted)
				{
					display+="Bomb planted !";
				}
				else
				{
					display+="Plant the Bomb !";
				}
			}
			else
			{
				if(!isPlanted)
				{
					display+="Sector Clear !";
				}
				else
				{
					display+="Defuse the Bomb !";
				}
			}
		}
		else
		{
			if(isTerrorist)
			{
				if(isPlanted)
				{
					if(isDefused)
					{
						display+="You lost: Bomb Defused";
						changeColor(Color.MAGENTA);
					}
					else
					{
						display+="You won: Target Exploded";
						changeColor(Color.BLUE);
					}
				}
				else
				{
					display+="You lost: Target Safe";
					changeColor(Color.MAGENTA);
				}
			}
			else
			{
				if(isPlanted)
				{
					if(isDefused)
					{
						display+="You won: Bomb Defused";
						changeColor(Color.BLUE);
					}
					else
					{
						display+="You lost: Target Exploded";
						changeColor(Color.MAGENTA);
					}
				}
				else
				{
					display+="You won: Target Safe";
					changeColor(Color.BLUE);
				}
			}
		}
			
		getInfo2().setText(display);
	}
	
	public void showSide(boolean isTerrorist){
		getInfo1().setText((isTerrorist?"":"Counter ")+"Terrorist");
	}
}
