package Thread;

public class Sj81Thread3 {
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		RunnableThread1 td1 = new RunnableThread1(5, "Thread1");
		RunnableThread1 td2 = new RunnableThread1(5, "Thread1");
		
		Thread t1 = new Thread(td1);
		Thread t2 = new Thread(td2, "ThreadName2");
		
		t1.start();
		t2.start();
		
		System.out.println("main Thread Name = " + Thread.currentThread().getName());
		System.out.println("t1 Thread Name = " + t1.getName());
		System.out.println("t2 Thread Name = " + t2.getName());
		Thread.sleep(100);
		System.out.println("Sj81Thread3 main() ³¡");
	}
}

class RunnableThread1 implements Runnable{
	private String strName;
	private int lastCount;
	
	RunnableThread1(int n, String str){
		strName = str;
		lastCount = n;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 1; i <= lastCount; i++) {
			System.out.println(strName + " = " + i);
			try {
				if(strName.equals("Thread1")) {
					Thread.sleep(2);
				}
				else {
					Thread.sleep(1);
				}
			}catch(InterruptedException e) {}
		}
	}
	
}