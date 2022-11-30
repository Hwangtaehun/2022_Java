public class Sj02while2{
    public static void main(String[] args){
        int sum = 0;
        int i = 0;

        while(i < 100){
            i++;
            sum += i;
        }
        System.out.println("1 -100 합1 = " + sum);

        i = 0;
        sum = 0;
        while(++i < 100){
            sum += i;
        }
        System.out.println("1 -100 합2 = " + sum);

        i = 0;
        sum = 0;
        while(i++ < 100){
            sum += i;
        }
        System.out.println("1 -100 합3 = " + sum);
    }
}