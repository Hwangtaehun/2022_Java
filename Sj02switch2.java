class Sj02switch2{
  public static void main(String args[]){
    char data;
    int len;
    String str = "abcdefg";
    if(args.length == 0){
      System.out.println(" ����� �μ��� �Է��ϼ��� !! ");
      System.exit(0);
    }
    len = str.length();
    System.out.println("str�� ���ڼ��� " + len + "�� �Դϴ�.");
    System.out.println("str�� 2��°���� 4��° ������ " + str.substring(2, 4) + "�� �Դϴ�.");
    System.out.println("str�� 2��°���� �� ������ " + str.substring(2) + "�� �Դϴ�.");
    data = (char)args[0].charAt(0);
    
    switch(data)
    {
      case 'a':
      case 'A':
        System.out.println("ù ���ڴ� a�Ǵ� A ");
        break;
      case 'b':
      case 'B':
        System.out.println("ù ���ڴ� b�Ǵ� B ");
        break;
      case 'c':
      case 'C':
        System.out.println("ù ���ڴ� c�Ǵ� C ");
        break;
      default:
        System.out.println("ù ���ڴ� a, b, c, A, B, C�� �ƴ� ");
    }
  }
}
