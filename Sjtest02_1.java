class Sjtest02_1
{
  public static void main(String argv[])
  {
    System.out.println("문제 2-1 1) 출력");
    int i, j;
    for(i = 1; i <= 4; i++)
    {
      System.out.print(i + " ");
    }
    System.out.println("\n");
    
    System.out.println("문제 2-1 2) 출력");
    for(i = 1; i <= 4; i++)
    {
      for(j = 1; j <= 4; j++)
      {
        System.out.print(j + " ");
      }
      System.out.println();
    }
    System.out.println();
    
    System.out.println("문제 2-1 3) 출력");
    for(i = 1; i <= 4; i++)
    {
      for(j = 1; j <= i; j++)
      {
        System.out.print(j + " ");
      }
      System.out.println();
    }
    System.out.println();
    
    System.out.println("문제 2-1 4) 출력");
    for(i = 1; i <= 4; i++)
    {
      for(j = 1; j <= 5 - i; j++)
      {
        System.out.print(" ");
      }
      for(j = 1; j <= i; j++)
      {
        System.out.print(j);
      }
      System.out.println();
    }
    System.out.println();
    
    System.out.println("문제 2-1 5) 출력");
    for(i = 1; i <= 4; i++)
    {
      for(j = 1; j <= 5 - i; j++)
      {
        System.out.print(" ");
      }
      for(j = 1; j <= i*2-1; j++)
      {
        System.out.print(j);
      }
      System.out.println();
    }
    System.out.println();
  }
}
