public class Sj02if5{
  public static void main(String[] args){
    int num1, num2, num3, maxNum, minNum;
    
    if(args.length == 0){
      System.out.println("����� �μ��� �Է����ּ���!!");
      System.exit(0);
    }
    num1 = Integer.parseInt(args[0]);
    num2 = Integer.parseInt(args[1]);
    
    num3 = num1 + num2;
    System.out.println("�Էµ� �μ��� " + num1 + "�� " + num2 + "�Դϴ�.");
    System.out.println("�Էµ� �μ��� ���� " + num3 + "�Դϴ�.");
    
    if(num1 > num2){
      maxNum = num1;
    }
    else{
      maxNum = num2;
    }
    System.out.println("�ΰ��� �μ� �� ū���� " + maxNum + "�Դϴ�.");
    
    minNum = (num1 < num2) ? num1 : num2;
    System.out.println("�ΰ��� �μ� �� ���� ���� " + minNum + "�Դϴ�.");
  }
}
