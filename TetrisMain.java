import java.util.Random;


public class TetrisMain
{

	public static void main(String[] args)
	{
		Grafica g= new Grafica();
		g.getS().randomShape(g);
		MyThread thread=new MyThread(g);
		thread.start();
		
			
	}
	

}
