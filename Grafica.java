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
	int xChange=0;
	Shape s;
	int[][] screen=new int[18][10]; ; //מטריצת הלוח




	public Grafica()
	{
		this.add(l);
		addKeyListener(this);
		this.setFocusable(true);
		f.add(this);
		f.setSize(1000,900);
		f.setVisible(true);
		for (int i=0;i<18;i++) //איפוס מטריצת מסך
		{
			for(int j=0;j<10;j++)
			{
				screen[i][j]=0;
			}
		}
	}

	public void paintComponent (Graphics g)
	{
		for (int i=0;i<18;i++) //ציור מטריצת מסך
		{
			for(int j=0;j<10;j++)
			{
				g.setColor(s.numTOColor(screen[i][j],this));
				g.fillRect(j*30+100,i*30+100,30, 30); //אורך כל ריבוע בצורה 30 

			}
		}

		for (int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(s.type[i][j]!=0)
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

		for (int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(s.type[i][j]!=0)
				{
					g.setColor(s.color);
					g.fillRect(j*30+x,i*30+y,30, 30); //אורך כל ריבוע בצורה 30 

				}
			}
		}
		y=y+10; //הצורה יורדת 10 יחידות למטה

		if(this.ifStoop())
		{
			this.addToScreen();
			s.randomShape(this);
			x=190;
			y=100;
			xChange=0;
		}



	}

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(x<400-s.width*30)
			{
				x=x+30;
				xChange=30;
			}
		}

		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(x>100)
			{
				x=x-30;
				xChange=-30;
			}
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
	public void addToScreen()
	{
		for (int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(s.type[i][j]!=0)
				{
					screen[(i*30+y-100)/30][(j*30+x-100)/30]=s.type[i][j];
				}
			}
		}
	}

	public boolean ifStoop() //בודק האם צורה צריכה לעצור כי הגיעה לקצה או כי נתקלה בצורה אחרת
	{
		System.out.println(s.height);
		if(y>=(640-s.height*30))//הצורה הגיעה לקצה המסך
			return true;
		for(int j=0;j<s.width;j++)
			for(int i=s.height-1;i>=0;i--)
			{
				if(s.type[i][j]!=0)
				{
					System.out.println(y+" "+x+" "+i+" "+j);
					if(screen[(y-100)/30+i+1][(x-100)/30+j]!=0)
						return true;
				}
			}
		return false;
	}

	/*public void lineIsFull ()
	{
		f=true;
		for(int i=0;i<18;i++)

	}*/







}