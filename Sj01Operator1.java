public class Sj01Operator1
{
  public static void main(String[] args)
  {
    int a, b, c, d, na;
    float av;
    a = 100;
    b = 100;
    c = (int)45.67;
    System.out.println(" Casting �� c = " + c);
    c = a + b;
    //av = (float)c / 3.0;
    av = (float)c / 3.0f;
    
    System.out.println("���� " + c + "�Դϴ�.");
    System.out.println("����� " + av + "�Դϴ�.");
    
    d = -a;
    na = 11 % 3;
    System.out.println("��ȣ ������ : -a = " + d);
    System.out.println("������ ������ : 11 % 3 = " + na);
  }
}
