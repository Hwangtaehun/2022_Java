class Sjtest02_3
{
  public static void main(String argv[])
  {
    char i, j;
    
    System.out.println("巩力 2-3 1)");
    for(i = 0; i < 26; i++)
    {
      for(j = 'A'; j <= 'Z'; j++)
      {
        System.out.print(j);
      }
      System.out.println();
    }
    
    System.out.println("巩力 2-3 2)");
    for(i = 0; i < 26; i++)
    {
      for(j = 'A'; j <= 'A'+i; j++)
      {
        System.out.print(j);
      }
      System.out.println();
    }
    
    System.out.println("巩力 2-3 3)");
    for(i = 0; i < 26; i++)
    {
      for(j = 1; j < 26 - i; j++)
      {
        System.out.print(" ");
      }
      for(j = 'A'; j <= 'A'+i; j++)
      {
        System.out.print(j);
      }
      System.out.println();
    }
    
    System.out.println("巩力 2-3 4)");
    for(i = 0; i < 13; i++)
    {
      for(j = 1; j < 13 - i; j++)
      {
        System.out.print(" ");
      }
      for(j = 'A'; j <= 'A'+2*i; j++)
      {
        System.out.print(j);
      }
      System.out.println();
    }
    
    System.out.println("巩力 2-3 5)");
    for(i = 'A'; i <= 'Z'; i++)
    {
      for(j = i; j <= 'Z'; j++)
      {
        System.out.print(j);
      }
      for(j = 'A'; j < i; j++)
      {
        System.out.print(j);
      }
      System.out.println();
    }
    
    System.out.println("巩力 2-3 6)");
    int a, b, c;
    char ch = 'A';
    for(a = 1; a <= 3; a++)
    {
      for(b = 1; b < 4-a; b++)
      {
        System.out.print(" ");
      }
      for(b = 1; b < a*2; b++)
      {
        System.out.print(ch);
        ch++;
      }
      System.out.println();
    }
    c = 1;
    for(a = 2; a >= 1; a--)
    {
      for(b = 0; b < c; b++)
      {
        System.out.print(" ");
      }
      for(b = 1; b < a * 2; b++)
      {
        System.out.print(ch);
        ch++;
      }
      System.out.println();
      c++;
    }
  }
}
