class Sj02switch2{
  public static void main(String args[]){
    char data;
    int len;
    String str = "abcdefg";
    if(args.length == 0){
      System.out.println(" 명령줄 인수를 입력하세요 !! ");
      System.exit(0);
    }
    len = str.length();
    System.out.println("str의 문자수는 " + len + "개 입니다.");
    System.out.println("str의 2번째부터 4번째 까지는 " + str.substring(2, 4) + "개 입니다.");
    System.out.println("str의 2번째부터 끝 까지는 " + str.substring(2) + "개 입니다.");
    data = (char)args[0].charAt(0);
    
    switch(data)
    {
      case 'a':
      case 'A':
        System.out.println("첫 글자는 a또는 A ");
        break;
      case 'b':
      case 'B':
        System.out.println("첫 글자는 b또는 B ");
        break;
      case 'c':
      case 'C':
        System.out.println("첫 글자는 c또는 C ");
        break;
      default:
        System.out.println("첫 글자는 a, b, c, A, B, C가 아님 ");
    }
  }
}
