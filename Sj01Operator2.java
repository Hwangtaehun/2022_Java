public class Sj01Operator2
{
  public static void main(String[] args)
  {
    int a, b, c, d;
    a = b= 10;
    c = ++a;
    d = b++;
    System.out.println("���� ������ ������ : a = " + a + "\t c = " + c);
    System.out.println("���� ������ ������ : b = " + b + "\t d = " + d);
    a = 3;
    c = ++a + ++a;
    System.out.println("c = ++a + ++a ���� :\t a = " + a + "\t c = " + c);
    c = ++a + ++a + ++a;
    System.out.println("c = ++a + ++a + ++a ���� :\t a = " + a + "\t c = " + c);
    
    a = b = c = d = 10;
    a += 3;
    b -= 3;
    c *= 3;
    d %= 3;
    System.out.print
    
