public class Sj02switch1{
  public static void main(String[] args)
  {
    int num1;
    if(args.length == 0)
    {
      System.out.println(" 명령줄 인수를 입력하세요!! ");
      System.exit(0);
    }
    num1 = Integer.parseInt(args[0]);
    switch(num1)
    {
      case 1:
        System.out.println("입력된 수는 ");
        System.out.println(" 1 입니다.");
        break;
      case 3:
        System.out.println("입력된 수는 ");
        System.out.println(" 3 입니다.");
        break;
      default:
        System.out.println("입력된 수는 ");
        System.out.println(" 1 과 3이외의 수입니다.");
    }
  }
}
