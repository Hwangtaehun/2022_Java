public class Sjtest02_5{
    public static void main(String [] args)
    {
        int i = 0; 
        float sum = 0f;

        System.out.println("문제 2-5 1)");
        while(i < 100)
        {
            sum += (float)i/(float)(i+1);
            i++;
        }
        System.out.println(sum);

        System.out.println("문제 2-5 2)");
        int sw = 1;
        i = 1;
        sum = 0f;
        do{
            sum += (float)sw*(float)i/(float)(i+1);
            sw = -sw;
            i++;
        }while(i < 100);
        System.out.println(sum);

        System.out.println("문제 2-5 3)");
        int a = 0, b = 0, c = 0; 
        while(a < 10)
        {
            a++;
            b += a;
            c += b;
        }
        System.out.println(c);

        System.out.println("문제 2-5 4)");
        a = 0;
        b = 1; 
        c = 0;
        while(a < 7)
        {
            b += a;
            c += b;
            a++;
        }
        System.out.println(c);

        System.out.println("문제 2-5 5)");
        a = 0;
        b = 1; 
        c = 0;
        sw = 1;
        while(a < 7)
        {
            b += a;
            c += (sw * b);
            a++;
            sw = -sw;
        }
        System.out.println(c);

        System.out.println("문제 2-5 6)");
        a = 1;
        b = 1; 
        c = 0;
        while(a < 11)
        {
            b *= a;
            c += b;
            a++;
        }
        System.out.println(c);

        System.out.println("문제 2-5 7)");
        a = 1;
        b = 1; 
        sum = 0;
        while(a < 11)
        {
            b *= a;
            sum += (float)(a-1)/(float)b;
            a++;
        }
        System.out.println(sum);
    }
}