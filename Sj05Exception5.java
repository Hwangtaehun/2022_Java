class Sj05MyException extends Exception{
    public Sj05MyException(){
        super("인수없는 생성자");
    }
    public Sj05MyException(String str){
        super(str);
    }
}

class Sj05Class5{
    Sj05Class5(){}

    void test1() throws Sj05MyException{
        System.out.println("Test1()에서 인수 없는 Sj05MyException 객체 생성");
        throw new Sj05MyException();
    }

    void test2() throws Sj05MyException{
        System.out.println("Test2()에서 Error 발생시킴");
        throw new Sj05MyException("인수있는 생성자");
    }
}

public class Sj05Exception5{
    public static void main(String[] args){
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