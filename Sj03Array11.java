public class Sj03Array11
{
  public static void main(String[] args)
  {
    int i;
    int []k;
    k = new int[5];
    k[0] = 1;
    k[1] = 2;
    k[2] = 3;
    k[4] = 4;
    System.out.println("k의 배열 요수 수 = " + k.length);
    System.out.print("배열 k 내용 :  ");
    for(i = 0; i < k.length; i++)
    {
        System.out.print(" " + k[i]);
    }
  }
}
