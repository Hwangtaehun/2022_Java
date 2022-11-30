public class Sj02dowhile{
    public static void main(String argv[]){
        int sum = 0;
        int i = 0;

        do{
            i++;
            sum += i;
        }while(i < 100);
        System.out.println("1 - 100 합 = " + sum);

        int n1, n2, n3;
        n1 = 1;
        n2 = 0;
        System.out.println("1 - 21까지 Fibonacci 수 출력 ");
        do{
            n3 = n1 + n2;
            System.out.print(n3 + " , ");
            n1 = n2;
            n2 = n3;
        }while(n3 < 21);
        System.out.println();
    }
}