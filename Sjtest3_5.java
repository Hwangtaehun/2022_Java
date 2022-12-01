public class Sjtest3_5
{
  public static void main(String[] args)
  {
    final int MAX = 5;
    int i, j, n = 0, x = 0, y = MAX/2, bx, by;
    int[][] arr;
    arr = new int[MAX][];
    arr[0] = new int[MAX];
    arr[1] = new int[MAX];
    arr[2] = new int[MAX];
    arr[3] = new int[MAX];
    arr[4] = new int[MAX];

    while(true){
        n++;
        arr[x][y] = n;

        bx = x;
        by = y;

        if(n >= MAX*MAX)
            break;
        
        if(x > 0)
            x--;
        else
            x = MAX - 1;

        if(y < MAX - 1)
            y++;
        else
            y = 0;
        
        if(arr[x][y] != 0)
        {
            x = bx + 1;
            y = by;
        }
    }
    for(i = 0; i < MAX; i++)
    {
        for(j = 0; j < MAX; j++)
        {
            System.out.printf("%3d", arr[i][j]);
        }
        System.out.printf("\n");
    }
  }
}
