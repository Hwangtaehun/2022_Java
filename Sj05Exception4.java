class Sj05class4{
    void test1() throws Exception{
        try{
            test2();
        }
        catch(Exception e){
            System.out.println("test1()�� Error Msg = " + e.getMessage());
            throw e;
            //System.out.println("test ���� ��");
        }
    }

    void test2() throws Exception{
         System.out.println("test2 ����");
         throw new Exception("�����߻���Ų Exception");
         //System.out.println("test2 ���� ��"); 
    }
}

public class Sj05Exception4{
    public static void main(String[] args){
        Sj05class4 sj = new Sj05class4();
        try{
            sj.test1();
        }
        catch(Exception e){
            System.out.println("main()�� Error Msg = " + e.getMessage());
            e.printStackTrace();

            for(int i = 0 ; i < e.getStackTrace().length; i++){
                System.out.println("Error Msg = " + e.getStackTrace()[i]);
            }
        }
    }
}