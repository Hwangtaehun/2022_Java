class Sjtest02_2{
  public static void main(String argv[])
  {
    int i, j;
    
    System.out.println("문제2-2 1)");
    for(i = 1; i <= 5 ; i++)
    {
      for(j = 1; j <= i; j++)
      {
        System.out.print(" *");
      }
      System.out.println();
    }
    for(i = 4; i >= 1 ; i--)
    {
      for(j = 1; j <= i; j++)
      {
        System.out.print(" *");
      }
      System.out.println();
    }
    
    System.out.println("문제2-2 2)");
    for(i = 1; i <= 5 ; i++)
    {
      for(j = 1; j <= 5 - i; j++)
      {
        System.out.print(" ");
      }
      for(j = 1; j <= i; j++)
      {
        System.out.print("*");
      }
      System.out.println();
    }
    for(i = 4; i >= 1 ; i--)
    {
      for(j = 1; j <= 5 - i; j++)
      {
        System.out.print(" ");
      }
      for(j = 1; j <= i; j++)
      {
        System.out.print("*");
      }
      System.out.println();
    }
    
    System.out.println("문제2-2 3)");
    for(i = 1; i <= 5 ; i++)
    {
      for(j = 1; j <= 5 - i; j++)
      {
        System.out.print(" ");
      }
      for(j = 1; j <= 2*i-1; j++)
      {
        System.out.print("*");
      }
      System.out.println();
    }
    for(i = 4; i >= 1 ; i--)
    {
      for(j = 1; j <= 5 - i; j++)
      {
        System.out.print(" ");
      }
      for(j = 1; j <= 2*i-1; j++)
      {
        System.out.print("*");
      }
      System.out.println();
    }
  }
}
