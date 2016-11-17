
public class TetrisMain
{

	public static void main(String[] args)
	{
		Grafica a= new Grafica();
		MyThread t=new MyThread(a);
		t.start();
	
	}

}
