public class Sj03Array32
{
  public static void main(String[] args)
  {
    int i, j;
    String[][] arr;
    arr = new String[3][];
    arr[0] = new String[3];
    arr[1] = new String[3];
    arr[2] = new String[3];

    arr[0][0] = new String("0aaa");
    arr[0][1] = new String("0bbb");
    arr[0][2] = new String("0ccc");

    arr[1][0] = new String("1aaa");
    arr[1][1] = new String("1bbb");
    arr[1][2] = new String("1ccc");

    arr[2][0] = new String("2aaa");
    arr[2][1] = new String("2bbb");
    arr[2][2] = new String("2ccc");

    for(i = 0; i < arr.length; i++){
        for(j = 0; j < arr[i].length; j++){
            System.out.print(" " + arr[i][j]);
        }
        System.out.println();
    }

    String[][]arr1 = {{"aaa", "bbb"}, {"ccc", "ddd", "eee"}};
    System.out.println("\n 배열 초기화후 ");
    for(i = 0; i < arr1.length; i++){
        for(j = 0; j < arr1[i].length; j++){
            System.out.print(" " + arr[i][j]);
        }
        System.out.println();
    }
  }
}
