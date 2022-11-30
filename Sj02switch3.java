class Sj02switch3{
  public static void main(String argv[]){
    int data;
    if(argv.length == 0){
      System.out.println(" 명령줄 인수를 입력하세요 !! ");
      System.exit(0);
    }
    data = Integer.parseInt(argv[0]);
    switch(data/10){
      case 10:
      case 9:
        System.out.println("A 학점입니다.");
        break;
      case 8:
        System.out.println("B 학점입니다.");
        break;
      case 7:
        System.out.println("C 학점입니다.");
        break;
      case 6:
        System.out.println("D 학점입니다.");
        break;
      default:
        System.out.println("D 학점입니다.");
        break;
    }
  }
}
