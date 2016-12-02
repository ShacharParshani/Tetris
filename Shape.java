import java.awt.Color;
import java.util.Random;

import com.sun.glass.ui.CommonDialogs.Type;
public class Shape
{
	Color color; //צבע הצורה
	int [][] type=new int[4][4]; //מערך 4 על 4 שמבטא איפה נמצאת הצורה על המערך:0 לא נמצאת,מספרים מ1 ל7 נמצאת
	int width; //רוחב הצורה- במספר ריבועים (על פי הריבוע הכי ימני)
	int height; //גובה הצורה- במספר ריבועים (על פי הריבוע הכי תחתון)



	public Shape (char name)
	{

		for (int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				this.type[i][j]=0;
			}
		}


		if(name=='I')
		{
			width=4;
			height=1;
			color=Color.pink;
			for(int i=0;i<4;i++)
			{
				this.type[0][i]=1;
			}
		}
		else
			if(name=='L')
			{
				width=3;
				height=2;
				color=Color.blue;
				this.type[0][0]=2;
				for(int z=0;z<3;z++)
				{
					this.type[1][z]=2;
				}
			}
			else
				if(name=='J')
				{
					width=3;
					height=2;
					color=Color.orange;
					this.type[0][2]=3;
					for(int i=0;i<3;i++)
					{
						this.type[1][i]=3;
					}
				}
				else
					if(name=='O')
					{
						width=2;
						height=2;
						color=Color.yellow;
						this.type[0][0]=4;
						this.type[0][1]=4;
						this.type[1][0]=4;
						this.type[1][1]=4;
					}
					else
						if(name=='S')
						{
							width=3;
							height=2;
							color=Color.green;
							this.type[0][1]=5;
							this.type[0][2]=5;
							this.type[1][0]=5;
							this.type[1][1]=5;
						}
						else
							if(name=='T')
							{
								width=3;
								height=2;
								color=Color.magenta;
								this.type[0][1]=6;
								this.type[1][0]=6;
								this.type[1][1]=6;
								this.type[1][2]=6;
							}
							else
								if(name=='Z')
								{
									width=3;
									height=2;
									color=Color.red;
									this.type[0][0]=7;
									this.type[0][1]=7;
									this.type[1][1]=7;
									this.type[1][2]=7;
								}

	}
	public static void randomShape(Grafica p)
	{
		final String alphabet = "STZILJO";
		final int N = alphabet.length();
		Random r = new Random();
		char c=alphabet.charAt(r.nextInt(N));
		p.s=new Shape(c);

	}
	public static Color numTOColor(int num,Grafica p)
	{
		if (num==1)
			return Color.pink;
		if (num==2)
			return Color.blue;
		if (num==3)
			return Color.orange;
		if (num==4)
			return Color.yellow;
		if (num==5)
			return Color.green;
		if (num==6)
			return Color.magenta;
		if (num==7)
			return Color.red;
		else
			return p.getBackground();	
	}
	public static void turnShape(Shape s,Grafica p)
	{
		int countEx; //מונה כמה מקומות בשורה במטריצה לא ריקים
		int [] arrTurn= {12,8,4,0,13,9,5,1,14,10,6,2,15,11,7,3};
		int [][] newtype=new int[4][4];
		boolean f=true; //בודק אם השורה הראשונה ריקה, אם לא הלולאה שמעלה למעלה את הצורה תעצור
		
		for(int i=0;i<arrTurn.length;i++)//סיבוב הצורה
		{
			newtype[i/4][i%4]=s.type[arrTurn[i]/4][arrTurn[i]%4];
		}
		if(!p.ifRight())//אם הצורה נתקעת בצורה אחרת מימין או בקצה   
		{
			
		}
		
		s.type=newtype;
		int newHeight=s.width;
		int newWidth=s.height;
		s.width=newWidth;
		s.height=newHeight;
		
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				System.out.print(s.type[i][j]);
		System.out.println(" ");
		
		for(int i=0;i<4&&f;i++)//מעלה את הצורה ללמעלה של המערך type  
		{
			countEx=0;
			for(int j=0;j<4;j++)
				if(s.type[0][j]!=0)
					countEx++;
			if(countEx==0)//שורה ריקה-צריך להעלות את הצורה למעלה
			{
				for(int k=0;k<3;k++)
					for(int l=0;l<4;l++)
						s.type[k][l]=s.type[k+1][l];
				for(int z=0;z<4;z++)
					s.type[3][z]=0;
			}
			else
				f=false;
		} 
		f=true;
		for(int j=0;j<4&&f;j++)//מזיז את הצורה לצד השמאלי של type  
		{
			countEx=0;
			for(int i=0;i<4;i++)
				if(s.type[i][0]!=0)
					countEx++;
			if(countEx==0)//שורה ריקה-צריך להעלות את הצורה למעלה
			{
				for(int k=0;k<4;k++)
					for(int l=0;l<3;l++)
						s.type[k][l]=s.type[k][l+1];
				for(int z=0;z<4;z++)
					s.type[z][3]=0;
			}
			else
				f=false;
		} 
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				System.out.print(s.type[i][j]);
		System.out.println(" ");
		

	}

}
