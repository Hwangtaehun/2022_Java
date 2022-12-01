public class Sjtest3_3
{
  public static void main(String[] args)
  {
    int num, weight;
    num = Integer.parseInt(args[0]);
    while(true){
        if(num == 0)
            break;
        weight = 2;
        while(weight <= num)
        {
            weight *= 2;
        }
        weight /= 2;
        System.out.printf("\n 10 진수 %7d의 2진수는 ---> ", num);
        while(weight != 0)
        {
            System.out.printf("%2d", num/weight);
            num %= weight;
            weight /= 2;
        }
        System.out.printf("\n\n");
    }
  }
}
