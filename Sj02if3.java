public class Sj02if3{
  public static void main(String[] args){
    int data;
    
    if(args.length > 0){
    data = Integer.parseInt(args[0]);
    if(data > 0){
      System.out.println("��� �Դϴ�.");
      }
    else if(data < 0){
      System.out.println("���� �Դϴ�.");
      }
    else{
      System.out.println("�� �Դϴ�.");
      }
    if(data >= 90)
      System.out.println("A �����Դϴ�.");
    else if(data >= 80)
      System.out.println("B �����Դϴ�.");
    else if(data >= 70)
      System.out.println("C �����Դϴ�.");
    else if(data >= 60)
      System.out.println("D �����Դϴ�.");
    else
      System.out.println("F �����Դϴ�.");
    }
    else{
      System.out.println("����� �μ��� ���׿�!!");
    }
  }
}
