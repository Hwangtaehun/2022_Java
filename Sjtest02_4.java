class Sjtest02_4
{
  public static void main(String argv[])
  {
    int i, j;
    System.out.printf("%25s\n","************");
    System.out.printf("%25s\n","* �� �� �� *");
    System.out.printf("%25s\n","************");
    System.out.printf("%42s\n","��������");
    for(i = 1; i <= 9; i += 3)
    {
      System.out.printf("----------\t----------\t----------\n");
      System.out.printf("��%d ��\t �� %d ��\t �� %d ��\n",i,i+1,i+2);
      System.out.printf("----------\t----------\t----------\n");
   
    for(j = 1; j <= 9; j++)
    {
      System.out.printf("%d * %d = %2d\t", i, j, i*j);
      System.out.printf("%d * %d = %2d\t", i+1, j, (i+1)*j);
      System.out.printf("%d * %d = %2d\n", i+2, j, (i+2)*j);
    }
    System.out.printf("----------\t----------\t----------\n");
   } 
  }
}
