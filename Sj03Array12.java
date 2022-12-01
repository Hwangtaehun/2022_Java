public class Sj03Array12
{
  public static void main(String[] args)
  {
    int i;
    char [] ca;
    ca = new char[5];
    ca[0]='A';
    ca[1]='B';
    ca[2]='C';
    ca[4]='D';
    System.out.println("ca 배열 요소 수 = " + ca.length);
    System.out.print("문자열 배열 ca 내용 :  ");
    for(i = 0; i < ca.length; i++){
        System.out.print(" " + ca[i]);
    }

    char cb[] = {'a', 'b', 'c', 'd'};
    System.out.println("\ncb 배열 요소 수 = " + cb.length);
    System.out.print("문자열 배열 cb 내용 :  ");
    for(i = 0; i < ca.length; i++){
        System.out.print(" " + cb[i]);
    }
    System.out.println();
  }
}
