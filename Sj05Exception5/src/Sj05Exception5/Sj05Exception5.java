package Sj05Exception5;

public class Sj05Exception5 {

	public static void main(String[] args) {
		Sj05Class5 sj = new Sj05Class5();
        try{
            sj.test1();
        }
        catch(Sj05MyException e){
            System.out.println("main()의 Error Msg = " + e.getMessage());
        }
        //Thread.sleep(1000);
        try{
            Thread.sleep(1000);
            sj.test2();
        }
        catch(Sj05MyException e){
            System.out.println("main()의 Error Msg = " + e.getMessage());
            //e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
	}

}
