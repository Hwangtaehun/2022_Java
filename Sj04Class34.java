package Sj04a;

public class Sj04Class34{
  private String name = "Sj04Class34";
  protected int kor = 34;
  public int eng = 34;
         int mat = 34;
  Sj04Class34(){
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
  public void printValue(){
    System.out.print("Sj04Class34�� name = " + name);
    System.out.println(" kor = " + kor + " eng = " + eng + " mat = " + mat);
  }
}
