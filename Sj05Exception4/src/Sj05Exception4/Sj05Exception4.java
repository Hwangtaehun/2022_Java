package Sj05Exception4;

public class Sj05Exception4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
