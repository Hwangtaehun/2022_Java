public class Sj02if4{
  public static void main(String[] args){
    int data;
    if(args.length > 0){
      data = Integer.parseInt(args[0]);
      if(data % 2 == 0)
        System.out.println("¦�� �Դϴ�.");
      else
        System.out.println("Ȧ�� �Դϴ�.");
        
      if(data % 2 == 0 || data % 3 == 0)
        System.out.println("2�� ��� �Ǵ� 3�� ����Դϴ�.");
      else
        System.out.println("2�� ��� �Ǵ� 3�� ����� �ƴմϴ�.");
        
      if(data >= 65 && data <= 90)
        System.out.println("65�� 90 ������ ���Դϴ�.");
      else
        System.out.println("65�� 90 ������ ���� �ƴմϴ�.");
        
      if(data >= 10 && data < 20)
        System.out.println("10�� �Դϴ�.");
      else if(data >= 20 && data < 30)
        System.out.println("20�� �Դϴ�.");
      else if(data >= 30 && data < 40)
        System.out.println("30�� �Դϴ�.");
      else
        System.out.println("��Ÿ �Դϴ�.");
    }
    else{
      System.out.println("����� �μ��� ���׿�!!");
    }
  }
}
