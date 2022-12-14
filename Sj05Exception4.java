class Sj05class4{
    void test1() throws Exception{
        try{
            test2();
        }
        catch(Exception e){
            System.out.println("test1()의 Error Msg = " + e.getMessage());
            throw e;
            //System.out.println("test 실행 끝");
        }
    }

    void test2() throws Exception{
         System.out.println("test2 시작");
         throw new Exception("내가발생시킨 Exception");
         //System.out.println("test2 실행 끝"); 
    }
}

public class Sj05Exception4{
    public static void main(String[] args){
        Sj05class4 sj = new Sj05class4();
        try{
            sj.test1();
        }
        catch(Exception e){
            System.out.println("main()의 Error Msg = " + e.getMessage());
            e.printStackTrace();

            for(int i = 0 ; i < e.getStackTrace().length; i++){
                System.out.println("Error Msg = " + e.getStackTrace()[i]);
            }
        }
    }
}