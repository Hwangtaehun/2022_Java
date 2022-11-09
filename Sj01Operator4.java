public class Sj01Operator4
{
    public static void main( String[] args )
    {
        int a, b, c, d;
        a = 3;
        b = 5;
        
        System.out.println("Bit Not 연산자 : ~3 = " + (~a));
        System.out.println("Bit And 연산자 : 3 & 5 = " + (a & b));
        System.out.println("Bit Or 연산자 : 3 | 5 = " + (a | b));
        System.out.println("Bit XOr 연산자 : 3 ^ 5 = " + (a ^ b));

        b = a << 2;
        c = -5 >> 1;
        d = -5 >>> 1;
        System.out.println("Shift 연산자 : 3 << 1 = " + (3 << 1));
        System.out.println("Shift 연산자 : 3 << 2 = " + b);
        System.out.println("Shift 연산자 : -5 >> 1 = " + c);
        System.out.println("Shift 연사자 : -5 >>> 1 = " + d);
    }
}