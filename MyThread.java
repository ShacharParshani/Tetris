public class MyThread extends Thread{

	//constructor
	private Grafica g;
	private boolean f=true;
	private int time=100;


	public MyThread(Grafica g) {
		super();
		this.g = g;
		
	}


	public void run(){
		while(f)
		{
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.sety(g.gety()+10); //הצורה יורדת 10 יחידות למטה
			g.repaint();
			
		}

	}
	











	public boolean isF() {
		return f;
	}



	public void setF(boolean f) {
		this.f = f;
	}


	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}
	













}