public class Sjtest3_2
{
  public static void main(String[] args)
  {
    final int MAX = 10;
    int arr[] = {10, 8, 6, 4, 2, 9, 7, 5, 3, 1};
    int i, j, temp;
    System.out.printf(" sort 전의 배열 내용 : \t");
    for(i = 0; i < MAX; i++)
    {
        System.out.printf("%3d", arr[i]);
    }
    System.out.printf("\n\n");
    for(i = 0; i < MAX - 1; i++)
    {
        for(j = 0; j < MAX-1-i;j++)
        {
            if(arr[j] > arr[j+1])
            {
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }
    System.out.printf(" sort 후의 배열 내용 : \t");
    for(i = 0; i < MAX; i++)
    {
        System.out.printf("%3d", arr[i]);
    }
    System.out.printf("\n\n");
  }
}
