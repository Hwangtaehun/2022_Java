public class Sjtest02_7{
    public static void main(String [] args)
    {
        int a, b, kun, jak, na, lcm, gcm;
        a = args[0];
        b = args[1];

        if(a > b)
        {
            kun = a; 
            jak = b;
        }
        else
        {
            kun = b; 
            jak = a;
        }
        na = kun % jak;
        while(na != 0)
        {
            kun = jak;
            jak = na;
            na = kun % jak;
        }
        gcm = jak;
        lcm = a * b / gcm;
        System.out.println("최대공약수 = " + gcm + "최대공배수 = " + lcm);
    }
}