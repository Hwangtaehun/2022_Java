package Sj04b;

public class Sj04Class35{
  private String name = "Sj04Class35";
  protected int kor;
  public int eng;
         int mat;
  Sj04Class35(){
  }
  private void privateMethod(){
    System.out.println("�ٸ� Package���ִ� Class�� privateMethod() �����1");
  }
  protected void protectedMethod(){
    System.out.println("�ٸ� Package���ִ� Class�� protectedMethod() �����");
  }
  void defaultMethod(){
    System.out.println("�ٸ� Package���ִ� Class�� defaultMethod() �����");
  }
  public void publicMethod(){
    System.out.println("�ٸ� Package���ִ� Class�� publicMethod() �����");
  }
  void printValue(){
    System.out.print("Sj04Class35�� name = " + name);
    System.out.println(" kor = " + kor + " eng = " + eng + " mat = " + mat + "\n");
  }
}
