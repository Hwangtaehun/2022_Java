class Sj02for3{
  public static void main(String argv[]){
    int b = 100;
    
    System.out.println("프로그램 시작");
    
    for(int a = 1; a <= 10; a++){
      System.out.println("Loop 앞부분, 변수 a의 값은" + a);
      
      if(a == 2){
        System.out.println("continue 실행");
        continue;
      }
      if(a == 4){
        System.out.println("break 실행");
        break;
      }
      System.out.println("Loop의 마지막 부분");
    }
    //b = a + 100;
    System.out.println("프로그램 종료");
  }
}
