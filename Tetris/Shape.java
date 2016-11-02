import java.awt.Color;
public class Shape
{
	Color color; //צבע הצורה
	int [][] type=new int[4][4]; //מערך דו מדדי "שמצוירת עליו הצורה" 1-התא במערך חלק מהצורה,אחרת-0	


	public Shape (char name) //להכניס צבע
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
			color=Color.cyan;
			for(int i=0;i<4;i++)
			{
				this.type[1][i]=1;
			}
		}
		else
			if(name=='L')
			{
				color=Color.blue;
				this.type[0][0]=2;
				for(int z=0;z<4;z++)
				{
					this.type[1][z]=2;
				}
			}
			else
				if(name=='J')
				{
					color=Color.orange;
					this.type[0][3]=3;
					for(int i=0;i<4;i++)
					{
						this.type[1][i]=3;
					}
				}
				else
					if(name=='O')
					{
						color=Color.yellow;
						this.type[0][1]=4;
						this.type[0][2]=4;
						this.type[1][1]=4;
						this.type[1][2]=4;
					}
					else
						if(name=='S')
						{
							color=Color.green;
							this.type[0][1]=5;
							this.type[0][2]=5;
							this.type[1][0]=5;
							this.type[1][1]=5;
						}
						else
							if(name=='T')
							{
								color=Color.magenta;
								this.type[0][1]=6;
								this.type[1][0]=6;
								this.type[1][1]=6;
								this.type[1][2]=6;
							}
							else
								if(name=='Z')
								{
									color=Color.red;
									this.type[0][0]=7;
									this.type[0][1]=7;
									this.type[1][1]=7;
									this.type[1][2]=7;
								}
								else 
									System.out.println("7");
	}
	



}
