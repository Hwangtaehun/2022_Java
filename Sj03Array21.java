public class Sj03Array21
{
  public static void main(String[] args)
  {
    int n = 0, i, j;
    int [][] arr;
    arr = new int[3][3];

    for(i = 0; i < 3; i++){
        for(j = 0;  j < 3; j++){
            n++;
            arr[i][j] = n;
        }
    }
    System.out.println("arr의 내용");
    for(i = 0; i < 3; i++){
        for(j = 0;  j < 3; j++){
            System.out.print(" " + arr[i][j]);
        }
        System.out.println();
    }
    System.out.println("arr.length = "+ arr.length);
    for(i = 0; i < 3; i++){
        System.out.println("arr[" + i + "].length = "+ arr[i].length);
    }
  }
}
