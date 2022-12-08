package Sj04a;

public class Sj04Class34{
  private String name = "Sj04Class34";
  protected int kor = 34;
  public int eng = 34;
         int mat = 34;
  Sj04Class34(){
  }
  private void privateMethod(){
    System.out.println("다른 Package에있는 Class의 privateMethod() 실행됨1");
  }
  protected void protectedMethod(){
    System.out.println("다른 Package에있는 Class의 protectedMethod() 실행됨");
  }
  void defaultMethod(){
    System.out.println("다른 Package에있는 Class의 defaultMethod() 실행됨");
  }
  public void publicMethod(){
    System.out.println("다른 Package에있는 Class의 publicMethod() 실행됨");
  }
  public void printValue(){
    System.out.print("Sj04Class34의 name = " + name);
    System.out.println(" kor = " + kor + " eng = " + eng + " mat = " + mat);
  }
}
