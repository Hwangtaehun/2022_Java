class Sj05MyException extends Exception{
    public Sj05MyException(){
        super("�μ����� ������");
    }
    public Sj05MyException(String str){
        super(str);
    }
}

class Sj05Class5{
    Sj05Class5(){}

    void test1() throws Sj05MyException{
        System.out.println("Test1()���� �μ� ���� Sj05MyException ��ü ����");
        throw new Sj05MyException();
    }

    void test2() throws Sj05MyException{
        System.out.println("Test2()���� Error �߻���Ŵ");
        throw new Sj05MyException("�μ��ִ� ������");
    }
}

public class Sj05Exception5{
    public static void main(String[] args){
        Sj05Class5 sj = new Sj05Class5();
        try{
            sj.test1();
        }
        catch(Sj05MyException e){
            System.out.println("main()�� Error Msg = " + e.getMessage());
        }
        //Thread.sleep(1000);
        try{
            Thread.sleep(1000);
            sj.test2();
        }
        catch(Sj05MyException e){
            System.out.println("main()�� Error Msg = " + e.getMessage());
            //e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}