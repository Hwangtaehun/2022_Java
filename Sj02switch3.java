class Sj02switch3{
  public static void main(String argv[]){
    int data;
    if(argv.length == 0){
      System.out.println(" ����� �μ��� �Է��ϼ��� !! ");
      System.exit(0);
    }
    data = Integer.parseInt(argv[0]);
    switch(data/10){
      case 10:
      case 9:
        System.out.println("A �����Դϴ�.");
        break;
      case 8:
        System.out.println("B �����Դϴ�.");
        break;
      case 7:
        System.out.println("C �����Դϴ�.");
        break;
      case 6:
        System.out.println("D �����Դϴ�.");
        break;
      default:
        System.out.println("D �����Դϴ�.");
        break;
    }
  }
}
