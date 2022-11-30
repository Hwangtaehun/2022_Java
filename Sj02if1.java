class Sj02if1
{
  public static void main(String argv[]){
    int cnt;
    cnt = argv.length;
    if(cnt > 0){
      System.out.println("명령 줄 인수의 갯수는 " + cnt + "개 입니다.");
      System.out.println("첫번째 인수는 " + argv[0] + " 입니다.");
      }
      else{
        System.out.println("명령줄 인수가 없네요!!");
      }
  }
}