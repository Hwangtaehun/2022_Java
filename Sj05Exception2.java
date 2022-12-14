public class Sj05Exception2{
  public static void main(String[] args){
    int k;
    String str[] = {"Sejong", "Computer", "Acadamy"};
    
    for(int i = 0; i < 4; i++){
      try{
        System.out.println("str[" + i + "] = " + str[i]);
      }
      catch(ArrayIndexOutOfBoundsException e){
        System.out.println("첨자범위 오류 발생 i = " + i);
      }
      finally{
        System.out.print("finally 실행 ");
      }
      System.out.println("i = " + i);
    }
    
    try{
      k = 12/0;
    }
    catch(ArithmeticException e){
      System.out.println("연산 오류 발생");
    }
  }
}
