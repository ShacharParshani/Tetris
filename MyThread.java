public class MyThread extends Thread{

    //constructor
	    Grafica g;
	    boolean f=true;
      
	          

	   public MyThread(Grafica g) {
		super();
		this.g = g;
	}



	//the code to executed in the thread
   public void run(){
	   while(f)
	   {
		   try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  g.repaint();
	   }
     
   }


}