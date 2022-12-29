package Thread;

public class Sj81Thread2 {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SjThread2 td1 = new SjThread2(5, "Thread1");
		SjThread2 td2 = new SjThread2(5, "Thread2");
		td1.start();
		td2.start();
		Thread.sleep(100);
		System.out.println("Sj8Thread2 main() ³¡");
	}
}

class SjThread2 extends Thread{
	private String strName;
	private int lastCount;
	
	SjThread2(int n, String str){
		strName = str;
		lastCount = n;
	}
	public void run() {
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