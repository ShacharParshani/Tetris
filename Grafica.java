import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;

import com.sun.glass.ui.Timer;


public class Grafica extends JPanel implements KeyListener {
	private JFrame f=new JFrame();
	private JPanel p=new JPanel();
	private int x=190;
	private int y=100;
	private int xChange=0;
	private Shape s;
	private int[][] screen=new int[18][10]; ; //������ ����
	private final int shapeSize=30;
	private int score=0;
	private String stScore;
	private JLabel lScore=new JLabel("hh");
	private MyThread th;
	private java.util.Timer timer;
	private java.util.Timer timer2;








	public Grafica()
	{
		
		p.add(lScore);
		p.setBackground(Color.LIGHT_GRAY);
		addKeyListener(this);
		this.setFocusable(true);
		this.setSize(500,500);
		this.setLayout(new BorderLayout());
		this.add(p,BorderLayout.EAST);
		f.add(this);
		f.setSize(1000,900);
		f.setVisible(true);
		restartScrean();
		timer= new java.util.Timer();
		timer2= new java.util.Timer();


	}

	public void restartScrean() { //����� ������ ���
		for (int i=0;i<18;i++) 
		{
			for(int j=0;j<10;j++)
			{
				screen[i][j]=0;
			}
		}
	}

	public void paintComponent (Graphics g)
	{
		for (int i=0;i<18;i++) //���� ������ ���
		{
			for(int j=0;j<10;j++)
			{
				g.setColor(s.numTOColor(screen[i][j],this));
				g.fillRect(j*shapeSize+100,i*shapeSize+100,shapeSize,shapeSize); //���� �� ����� ����� 30 

			}
		}

		for (int i=0;i<4;i++)//����� ���� ������ ����
		{
			for(int j=0;j<4;j++)
			{
				if(s.type[i][j]!=0)
				{
					g.setColor(this.getBackground());
					g.fillRect(j*shapeSize+x-xChange,i*shapeSize+y-10,shapeSize, shapeSize);
				}
			}
		}
		xChange=0;
		g.setColor(Color.BLACK);
		g.drawLine(100, 100, 100, 640);
		g.drawLine(100, 100, 400, 100);
		g.drawLine(400, 100, 400, 640);
		g.drawLine(100, 640, 400, 640);

		for (int i=0;i<4;i++)//���� ���� ������ ���
		{
			for(int j=0;j<4;j++)
			{
				if(s.type[i][j]!=0)
				{
					g.setColor(s.color);
					g.fillRect(j*shapeSize+x,i*shapeSize+y,shapeSize, shapeSize); //���� �� ����� ����� 30 

				}
			}
		}


		if(this.ifStoop())
		{
			this.addToScreen();
			this.lineIsFull();
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
			if(this.ifRight())
			{
				x=x+shapeSize;
				xChange=shapeSize;
			}
		}

		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(this.ifLeft())
			{
				x=x-shapeSize;
				xChange=-shapeSize;
			}
		}

		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			s.turnShape(s,this);
		}

		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			th.setTime(20);
			TimerTask task1 = new TimerTask()
			{ 
				public void run() 
				{
					th.setTime(100);
				}
			};
			timer.schedule(task1, 300);


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
					screen[(i*shapeSize+y-100)/shapeSize][(j*shapeSize+x-100)/shapeSize]=s.type[i][j];
				}
			}
		}
	}

	public boolean ifStoop() //���� ��� ���� ����� ����� �� ����� ���� �� �� ����� ����� ����
	{
		if(y>=(640-s.height*shapeSize))//����� ����� ���� ����
		{
			this.addToScore(1);
			return true;
		}
		for(int j=0;j<s.width;j++)
			for(int i=s.height-1;i>=0;i--)
			{
				if(s.type[i][j]!=0)
				{
					if(screen[(y-100)/shapeSize+i+1][(x-100)/shapeSize+j]!=0)//����� ����� ����� ����
					{
						this.addToScore(1);
						return true;
					}
				}
			}
		return false;
	}

	public boolean ifRight() //���� ��� ���� ����� ���� ����� (��� ���� ���� ���� �� ����� ����)
	{
		if(x>=(400-s.width*shapeSize))//����� ����� ���� ����
			return false;
		for(int i=0;i<s.height;i++)
			for(int j=s.width-1;j>=0;j--)
			{
				if(s.type[i][j]!=0)
				{
					if(screen[(y-100)/shapeSize+i][(x-100)/shapeSize+j+1]!=0)//����� ����� ����� ����
						return false;
				}
			}
		return true;
	}
	public boolean ifLeft() //���� ��� ���� ����� ���� ����� (��� ���� ���� ���� �� ����� ����)
	{
		if(x<=100)//����� ����� ���� ����
			return false;
		for(int i=0;i<s.height;i++)
			for(int j=0;j<s.width;j++)
			{
				if(s.type[i][j]!=0)
				{
					if(screen[(y-100)/shapeSize+i][(x-100)/shapeSize+j-1]!=0)//����� ����� ����� ����
						return false;
				}
			}
		return true;
	}


	public void lineIsFull ()
	{
		boolean f=true;
		int countEx; //���� ��� ������ ����� ������� �� �����
		int lastLine=0;

		for(int i=17;i>=0&&f;i--)
		{
			countEx=0;
			for(int j=0;j<10;j++)
				if(screen[i][j]!=0)
					countEx++;
			if(countEx==0)//����� ���� ����� �� ������ ����� ����� ����� �� ����� 
			{
				f=false;
				lastLine=i;
			}
		}

		if(f==true) //�� ������ �����-����� ����
			endGame();

		for(int i=17;i>=lastLine;i--)
		{
			countEx=0;
			for(int j=0;j<10;j++)
				if(screen[i][j]!=0)
					countEx++;
			if(countEx==10)//���� ����
			{
				for(int k=i-1;k>=lastLine;k--)
					for(int l=0;l<10;l++)
						screen[k+1][l]= screen[k][l];
				i=i+1; //����� ��� ��� ����� ���� �����
			}

		}


	}

	public void endGame ()
	{
		restartScrean();
		/*th.setTime(1000);
		TimerTask task2 = new TimerTask()
		{ 
			public void run() 
			{
				th.setTime(100);
			}
		};
		timer2.schedule(task2, 5000);*/

	}

	public void addToScore(int add)// ������ �� ������ ��� ����� ������ 
	{
		score=score+add;
		stScore=Integer.toString(score);
		lScore.setText(stScore);

	}









	public Shape getS() {
		return s;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public int getxChange() {
		return xChange;
	}

	public void setx(int x) {
		this.x = x;
	}

	public void sety(int y) {
		this.y = y;
	}

	public void setxChange(int xChange) {
		this.xChange = xChange;
	}

	public void setS(Shape s) {
		this.s = s;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public MyThread getTh() {
		return th;
	}

	public void setTh(MyThread th) {
		this.th = th;
	}

	public int getShapeSize() {
		return shapeSize;
	}
	









}

