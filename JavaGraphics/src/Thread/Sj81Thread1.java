package Thread;

public class Sj81Thread1 extends Thread{
	private String strName;
	private int lastCount;
	public static void main(String[] args) //throws InterruptedException
	{
		// TODO Auto-generated method stub
		Sj81Thread1 td1 = new Sj81Thread1(5, "myThread");
		Sj81Thread1 td2 = new Sj81Thread1();
		td1.start();
		td2.start();
		System.out.println("td1 Thread Name = " + td1.getName());
		System.out.println("td2 Thread Name = " + td2.getName());
		//sleep(100);
		/*try {
			sleep(100);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		System.out.println("Sj8Thread1 main() ³¡");
	}
	
	Sj81Thread1(){
		strName = getName();
		lastCount = 10;
	}
	
	Sj81Thread1(int n, String str){
		super(str);
		strName = str;
		lastCount = n;
	}
	
	public void run() {
		for(int i = 1; i <= lastCount; i++) {
			System.out.println(strName + "=" + i);
			try {
				if(strName.equals("myThread")) {
					sleep(2);
				}
				else {
					sleep(1);
				}
			}catch(InterruptedException e) {}
		}
	}
}