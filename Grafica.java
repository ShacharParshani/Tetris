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
	private JFrame f=new JFrame();
	private JLabel l=new JLabel();
	private int x=190;
	private int y=100;
	private int xChange=0;
	private Shape s;
	private int[][] screen=new int[18][10]; ; //מטריצת הלוח
	private final int shapeSize=30;
	private int score=0;
	private JLabel lScore=new JLabel();
	




	public Grafica()
	{
		this.add(l);
		this.add(lScore);
		addKeyListener(this);
		this.setFocusable(true);
		f.add(this);
		f.setSize(1000,900);
		f.setVisible(true);
		restartScrean();
	}

	public void restartScrean() { //איפוס מטריצת מסך
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
		for (int i=0;i<18;i++) //ציור מטריצת מסך
		{
			for(int j=0;j<10;j++)
			{
				g.setColor(s.numTOColor(screen[i][j],this));
				g.fillRect(j*shapeSize+100,i*shapeSize+100,shapeSize,shapeSize); //אורך כל ריבוע בצורה 30 

			}
		}

		for (int i=0;i<4;i++)//מחיקת צורה במיקום קודם
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

		for (int i=0;i<4;i++)//ציור צורה במיקום חדש
		{
			for(int j=0;j<4;j++)
			{
				if(s.type[i][j]!=0)
				{
					g.setColor(s.color);
					g.fillRect(j*shapeSize+x,i*shapeSize+y,shapeSize, shapeSize); //אורך כל ריבוע בצורה 30 

				}
			}
		}
		y=y+10; //הצורה יורדת 10 יחידות למטה
		
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

	public boolean ifStoop() //בודק האם צורה צריכה לעצור כי הגיעה לקצה או כי נתקלה בצורה אחרת
	{
		if(y>=(640-s.height*shapeSize))//הצורה הגיעה לקצה המסך
			return true;
		for(int j=0;j<s.width;j++)
			for(int i=s.height-1;i>=0;i--)
			{
				if(s.type[i][j]!=0)
				{
					if(screen[(y-100)/shapeSize+i+1][(x-100)/shapeSize+j]!=0)//הצורה נתקלה בצורה אחרת
						return true;
				}
			}
		return false;
	}

	public boolean ifRight() //בודק האם צורה יכולה לזוז ימינה (ולא תתקע בקצה המסך או בצורה אחרת)
	{
		if(x>=(400-s.width*shapeSize))//הצורה הגיעה לקצה המסך
			return false;
		for(int i=0;i<s.height;i++)
			for(int j=s.width-1;j>=0;j--)
			{
				if(s.type[i][j]!=0)
				{
					if(screen[(y-100)/shapeSize+i][(x-100)/shapeSize+j+1]!=0)//הצורה נתקלה בצורה אחרת
						return false;
				}
			}
		return true;
	}
	public boolean ifLeft() //בודק האם צורה יכולה לזוז שמאלה (ולא תתקע בקצה המסך או בצורה אחרת)
	{
		if(x<=100)//הצורה הגיעה לקצה המסך
			return false;
		for(int i=0;i<s.height;i++)
			for(int j=0;j<s.width;j++)
			{
				if(s.type[i][j]!=0)
				{
					if(screen[(y-100)/shapeSize+i][(x-100)/shapeSize+j-1]!=0)//הצורה נתקלה בצורה אחרת
						return false;
				}
			}
		return true;
	}


	public void lineIsFull ()
	{
		boolean f=true;
		int countEx; //מונה כמה מקומות בשורה במטריצה לא ריקים
		int lastLine=0;

		for(int i=17;i>=0&&f;i--)
		{
			countEx=0;
			for(int j=0;j<10;j++)
				if(screen[i][j]!=0)
					countEx++;
			if(countEx==0)//השורה ריקה כלומר לא יכולות להיות מעליה שורות לא ריקות 
			{
				f=false;
				lastLine=i;
			}
		}

		if(f==true) //כל השורות מלאות-השחקן נפסל
			endGame();

		for(int i=17;i>=lastLine;i--)
		{
			countEx=0;
			for(int j=0;j<10;j++)
				if(screen[i][j]!=0)
					countEx++;
			if(countEx==10)//שורה מלאה
				for(int k=i-1;k>=lastLine;k--)
					for(int l=0;l<10;l++)
						screen[k+1][l]= screen[k][l];
		}


	}

	public void endGame ()
	{
		restartScrean();
	}
	public void addToScore(int add)// מגדילה את הניקוד לפי המספר שקיבלה 
	{
		score=score+add;
		lScore.setText(""+score);

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








}

