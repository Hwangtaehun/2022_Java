public class Sj02while1{
    public static void main(String[] argv){
        int sum = 0;
        int i = 0;

        while(i < 5){
            i++;
            System.out.println("i = " + i);
        }

        i = 0;
        while(sum <= 1000){
            i++;
            sum += i;
        }
        System.out.println("합이 1000 보다 커질 때의 i = " + i + "\t합 = " +  sum);

        i = 0;
        sum = 0;
        while(true){
            i++;
            sum += i;
            if(sum >= 2000)
                break;
        }
        System.out.println("합이 2000 보다 커질 때의 i = " + i + "\t합 = " + sum);
    }
}