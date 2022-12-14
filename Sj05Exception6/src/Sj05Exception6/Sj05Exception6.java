package Sj05Exception6;

public class Sj05Exception6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "123a456";
        Sj05Class6 sj = new Sj05Class6();
        for(int i = 0; i < str.length(); i++){
            try{
                sj.isNumber(str.charAt(i), i);
            }
            catch(Sj05MyException2 e){
                System.out.println(e.getMessage());
            }
        }
	}

}
