class Sj02if2
{
  public static void main(String [] argv)
  {
    int cnt;
    char data;
    cnt = argv.length;
    if(cnt > 0){
      if(argv[0].equals("abc"))
        System.out.println("ù��° �μ��� abc�Դϴ�.");
      else
        System.out.println("ù��° �μ��� abc�� �ƴմϴ�.");
      data=(char)argv[0].charAt(0);
      if(data == 'a'){
        System.out.println("ù ���ڴ� a ");
        }
      else{
        if(data == 'b')
        {
          System.out.println("ù ���ڴ� b");
        }
        else{
          if(data == 'c'){
            System.out.println("ù ���ڴ� c");
            }
          else{
            System.out.println("ù ���ڴ� a,b,c�� �ƴ� ");
            }
          }
        }
      }
    }
  }