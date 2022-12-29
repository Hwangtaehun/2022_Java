package Thread;

public class Sj81Thread8 {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TestThread2 test1 = new TestThread2("Thread1");
		TestThread2 test2 = new TestThread2("Thread2");
		test1.start();
		test2.start();
		test2.setPriority(1);
		Thread.sleep(10);
		test1.stopThread();
		Thread.sleep(10);
		test2.stopThread();
		System.out.println("end");
	}
}

class TestThread2 extends Thread{
	private boolean bsw = false;
	private String strName;
	
	TestThread2(){}
	TestThread2(String str){
		strName = str;
		//System.out.println("MAX_PRIORIT = " + MAX_PRIORITY);
		System.out.println("MIN_PRIORIT = " + MIN_PRIORITY);
		//System.out.println("NORM_PRIORIT = " + NORM_PRIORITY);
	}
	
	public void run() {
		System.out.println(strName + " " + Thread.currentThread().getPriority() + " 증감전 공유 변수의 값 = " + CountData.count);
		while(bsw != true) {
			try {
				if(strName.equals("Thread1"))
					CountData.count++;
				else
					CountData.count--;
				CountData.Prn(strName);
				Thread.sleep(1);
			}catch(InterruptedException e) {}
		}
	}
	
	public void stopThread() {
		bsw = true;
	}
	
	class CountData{
		static int count = 0;
		static void Prn(String str) {
			System.out.println(str + " Thread 에서 공유 변수의 값 = " + count);
		}
		CountData(){}
	}
}