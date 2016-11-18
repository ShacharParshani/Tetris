import java.util.Random;


public class TetrisMain
{

	public static void main(String[] args)
	{
		Grafica g= new Grafica();
		g.s.randomShape(g);
		MyThread t=new MyThread(g);
		t.start();
			
	}
	

}
