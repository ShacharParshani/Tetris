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
	JPanel p2=new JPanel();
	JLabel l=new JLabel("טטריס");
	JLabel l3=new JLabel("                       ");
	JButton[]arrB=new JButton[180];



	public Grafica()
	{
		p2.setLayout(new GridLayout(18,10));
		for(int i=0;i<180;i++)
		{
			arrB[i]=new JButton("   ");
			p2.add(arrB[i]);
		}
		f.setSize(1000,900);
		f.setVisible(true);
		this.setBackground(Color.GREEN);
		f.add(this);
		this.add(l);

	}

	public void paintComponent (Graphics g)
	{
		g.drawLine(100, 100, 100, 640);
		g.drawLine(100, 100, 400, 100);
		g.drawLine(400, 100, 400, 640);
		g.drawLine(100, 640, 400, 640);
		Shape s1=new Shape('T');
		for (int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(s1.type[i][j]!=0)
				{
					g.setColor(s1.color);
					g.fillRect(j*30+190,i*30+100,30, 30);
				}
			}
		}
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
