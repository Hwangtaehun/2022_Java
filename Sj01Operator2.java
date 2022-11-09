public class Sj01Operator2
{
  public static void main(String[] args)
  {
    int a, b, c, d;
    a = b= 10;
    c = ++a;
    d = b++;
    System.out.println("증가 연산자 전위형 : a = " + a + "\t c = " + c);
    System.out.println("증가 연산자 후위형 : b = " + b + "\t d = " + d);
    a = 3;
    c = ++a + ++a;
    System.out.println("c = ++a + ++a 연산 :\t a = " + a + "\t c = " + c);
    c = ++a + ++a + ++a;
    System.out.println("c = ++a + ++a + ++a 연산 :\t a = " + a + "\t c = " + c);
    
    a = b = c = d = 10;
    a += 3;
    b -= 3;
    c *= 3;
    d %= 3;
    System.out.println("복합연산자 : a = " + a + "\t b = " + b);
    System.out.println("복합연산자 : c = " + c + "\t d = " + d);
  }
}   