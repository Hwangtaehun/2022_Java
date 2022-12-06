public class Sjtest02_6{
    public static void main(String [] args)
    {
        int num1, num2, num3, sum;
        num1 = num2 = 1;
        sum = 2;
        num3 = 0;

        while(num3 < 21)
        {
            num3 = num1 + num2;
            sum += num3;
            num1 = num2;
            num2 = num3;
        }
        System.out.println(sum);
    }
}