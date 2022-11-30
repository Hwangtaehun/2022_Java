public class Sj01Operator1
{
  public static void main(String[] args)
  {
    int a, b, c, d, na;
    float av;
    a = 100;
    b = 100;
    c = (int)45.67;
    System.out.println(" Casting 후 c = " + c);
    c = a + b;
    //av = (float)c / 3.0;
    av = (float)c / 3.0f;
    
    System.out.println("합은 " + c + "입니다.");
    System.out.println("평균은 " + av + "입니다.");
    
    d = -a;
    na = 11 % 3;
    System.out.println("부호 연산자 : -a = " + d);
    System.out.println("나머지 연산자 : 11 % 3 = " + na);
  }
}
