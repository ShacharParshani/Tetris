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
import javax.swing.JTextField;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;


public class Grafica extends JPanel implements KeyListener {
	JFrame f=new JFrame();
	JLabel l=new JLabel("טטריס");JButton[]arrB=new JButton[180];
	int x=190;
	int y=100;
	boolean fLimit=true;
	int xChange=0;




	public Grafica()
	{
		this.add(l);
		addKeyListener(this);
		this.setFocusable(true);
		f.add(this);
		f.setSize(1000,900);
		f.setVisible(true);
	}

	public void paintComponent (Graphics g)
	{
		int iLong=0;
		boolean ifExistent=false;
		Shape s1=new Shape('L');

		for (int i=0;i<4&&fLimit;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(s1.type[i][j]!=0)
				{
					g.setColor(this.getBackground());
					g.fillRect(j*30+x-xChange,i*30+y-10,30, 30);
				}
			}
		}
		xChange=0;
		g.setColor(Color.BLACK);
		g.drawLine(100, 100, 100, 640);
		g.drawLine(100, 100, 400, 100);
		g.drawLine(400, 100, 400, 640);
		g.drawLine(100, 640, 400, 640);

		for (int i=0;i<4&&fLimit;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(s1.type[i][j]!=0)
				{
					g.setColor(s1.color);
					g.fillRect(j*30+x,i*30+y,30, 30);
					ifExistent=true; //בודק האם הצורה קיימית בשורה כלשהי
				}
			}
			if(ifExistent)
				iLong++;
			ifExistent=false;
		}

		if(y>=(640-iLong*30))
			fLimit=false;
		y=y+10; //הצורה יורדת 10 יחידות למטה


	}

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(x<=400)
			{
				x=x+30;
				xChange=30;
			}
		}

		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(x>=100)
			x=x-30;
			xChange=-30;
		}
		repaint();



	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}








}