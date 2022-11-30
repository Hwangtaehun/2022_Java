class Sj02if2
{
  public static void main(String [] argv)
  {
    int cnt;
    char data;
    cnt = argv.length;
    if(cnt > 0){
      if(argv[0].equals("abc"))
        System.out.println("첫번째 인수는 abc입니다.");
      else
        System.out.println("첫번째 인수는 abc가 아닙니다.");
      data=(char)argv[0].charAt(0);
      if(data == 'a'){
        System.out.println("첫 글자는 a ");
        }
      else{
        if(data == 'b')
        {
          System.out.println("첫 글자는 b");
        }
        else{
          if(data == 'c'){
            System.out.println("첫 글자는 c");
            }
          else{
            System.out.println("첫 글자는 a,b,c가 아님 ");
            }
          }
        }
      }
    }
  }