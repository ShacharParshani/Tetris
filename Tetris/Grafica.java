import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Grafica extends JPanel implements KeyListener {
	JFrame f=new JFrame();
	JLabel l=new JLabel("טטריס");JButton[]arrB=new JButton[180];
	int y=100;



	public Grafica()
	{
		this.add(l);
		this.setBackground(Color.RED);
		f.add(this);
		f.setSize(1000,900);
		f.setVisible(true);
		
		

	}

	public void paintComponent (Graphics g)
	{
		Shape s1=new Shape('L');
		for (int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(s1.type[i][j]!=0)
				{
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(j*30+190,i*30+y-10,30, 30);
				}
			}
		}
		g.setColor(Color.BLACK);
		g.drawLine(100, 100, 100, 640);
		g.drawLine(100, 100, 400, 100);
		g.drawLine(400, 100, 400, 640);
		g.drawLine(100, 640, 400, 640);
		
		for (int i=0;i<4;i++)
		{f.add(this);
			for(int j=0;j<4;j++)
			{
				if(s1.type[i][j]!=0)
				{
					g.setColor(s1.color);
					g.fillRect(j*30+190,i*30+y,30, 30);
				}
			}
		}
		y=y+10;
	}

	public void keyPressed(KeyEvent e)
	{
	}

	public void keyReleased(KeyEvent e)
	{
	}

	public void keyTyped(KeyEvent e)
	{
	}






}
