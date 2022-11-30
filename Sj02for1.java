public class Sj02for1
{
  public static void main(String[] args)
  {
    int i = 0;
    for(i = 1; i <= 3; i++){
      System.out.print("세종 정보처리");
      System.out.println("학원");
    }
    System.out.println();
    
    for(i = 1; i <= 3; i++){
      System.out.println("Loop 실행 중 변수 i의 값은" + i);
    }
    System.out.println("Loop 탈출 후 변수 i의 값은" + i + "\n");
    
    for(i = 1; i <= 3; i += 2){
      System.out.println("Loop 실행 중 변수 i의 값은" + i);
    }
    System.out.println("Loop 탈출 후 변수 i의 값은" + i + "\n");
    
    for(i = 3; i >= 1; i-- ){
      System.out.println("Loop 실행 중 변수 i의 값은" + i);
    }
    System.out.println("Loop 탈출 후 변수 i의 값은" + i + "\n");
  }
}
