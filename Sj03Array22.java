public class Sj03Array22
{
  public static void main(String[] args)
  {
    int n, i, j;
    int [][] pp;
    pp = new int[3][];

    pp[0] = new int[3];
    pp[1] = new int[6];
    pp[2] = new int[4];
    n=0;
    for(i=0; i<pp.length;i++){
        for(j=0; j<pp[i].length;j++){
            n++;
            pp[i][j] = n;
        }
    }
    System.out.println("\npp의 내용");
    for(i=0; i<pp.length;i++){
        for(j=0; j<pp[i].length;j++){
           System.out.printf("%5d", pp[i][j]);
        }
        System.out.println();
    }

    int [][] qq = {{1,2,3}, {4,5,6,7}, {8,9}};
    System.out.println("\n2차원 배열 초기값 대입");
    for(i = 0; i < qq.length; i++){
        for(j = 0; j < qq[i].length; j++){
            System.out.printf("%5d", qq[i][j]);
        }
        System.out.println();
    }

    String str2 = String.format("%,d",10000000);
    System.out.println("str=" + str2);
    // String str3 = String.format("%tc",new Date());
    // System.out.println("str=" + str3);
    System.out.printf("%d, %6.2f\n", 123, 123.456);
  }
}
