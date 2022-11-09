public class Sj01Operator3
{
    public static void main( String[] args)
    {
        int a, b, c;
        System.out.println("관계연산자 : 5>6 = " + (5>6));
        System.out.println("관계연산자 : 5 != 6 " + (5!=6));

        System.out.println("논리연산자 : true && true = " + (true && true));
        System.out.println("논리연산자 : true && flase = " + (true && false));

        a = 3;
        b = 5;
        c = (a < b) ? a : b;
        System.out.println("조건연산자 : (3 > 5)? 3 : 5 =" + ((3 > 5)? 3 : 5));
        System.out.println("조건연산자 : c = " + c);
    }
}