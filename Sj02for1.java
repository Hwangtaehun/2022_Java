public class Sj02for1
{
  public static void main(String[] args)
  {
    int i = 0;
    for(i = 1; i <= 3; i++){
      System.out.print("���� ����ó��");
      System.out.println("�п�");
    }
    System.out.println();
    
    for(i = 1; i <= 3; i++){
      System.out.println("Loop ���� �� ���� i�� ����" + i);
    }
    System.out.println("Loop Ż�� �� ���� i�� ����" + i + "\n");
    
    for(i = 1; i <= 3; i += 2){
      System.out.println("Loop ���� �� ���� i�� ����" + i);
    }
    System.out.println("Loop Ż�� �� ���� i�� ����" + i + "\n");
    
    for(i = 3; i >= 1; i-- ){
      System.out.println("Loop ���� �� ���� i�� ����" + i);
    }
    System.out.println("Loop Ż�� �� ���� i�� ����" + i + "\n");
  }
}
