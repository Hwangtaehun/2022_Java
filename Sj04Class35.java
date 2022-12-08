package Sj04b;

public class Sj04Class35{
  private String name = "Sj04Class35";
  protected int kor;
  public int eng;
         int mat;
  Sj04Class35(){
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
  void printValue(){
    System.out.print("Sj04Class35의 name = " + name);
    System.out.println(" kor = " + kor + " eng = " + eng + " mat = " + mat + "\n");
  }
}
