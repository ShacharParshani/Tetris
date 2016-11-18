import java.awt.Color;
import java.util.Random;
public class Shape
{
	Color color; //��� �����
	int [][] type=new int[4][4]; //���� 4 �� 4 ����� ���� ����� ����� �� �����:0 �� �����,������ �1 �7 �����
	int width; //���� �����- ����� ������� (�� �� ������ ��� ����)
	int height; //���� �����- ����� ������� (�� �� ������ ��� �����)
	


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
	public static void randomShape(Grafica g)
	{
		final String alphabet = "STZILJO";
		final int N = alphabet.length();
		Random r = new Random();
		char c=alphabet.charAt(r.nextInt(N));
		g.s=new Shape(c);

	}

}
