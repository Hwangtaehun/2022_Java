public class Sj02switch1{
  public static void main(String[] args)
  {
    int num1;
    if(args.length == 0)
    {
      System.out.println(" ����� �μ��� �Է��ϼ���!! ");
      System.exit(0);
    }
    num1 = Integer.parseInt(args[0]);
    switch(num1)
    {
      case 1:
        System.out.println("�Էµ� ���� ");
        System.out.println(" 1 �Դϴ�.");
        break;
      case 3:
        System.out.println("�Էµ� ���� ");
        System.out.println(" 3 �Դϴ�.");
        break;
      default:
        System.out.println("�Էµ� ���� ");
        System.out.println(" 1 �� 3�̿��� ���Դϴ�.");
    }
  }
}
