class Sj04Class23{
  private String name;
  private int age;
  private int score;
  
  public Sj04Class23(String name, int age, int score)
  {
    System.out.println("생성자 실행됨, name =" +name);
    this.name = name;
    this.age = age;
    this.score = score;
  }
  public void setValue(String name, int age)
  {
    this.name = name;
    this.age = age;
  }
  public void print(){
    System.out.println("name = " + name + ", age = " + age + ", score = " + score);
  }
}

public class Sj04TestDrive23{
  public static void main(String[] args){
    Sj04Class23 sj1 = new Sj04Class23("첫번째", 55, 77);
    sj1.print();
    
    Sj04Class23 sj2 = new Sj04Class23("두번째", 77, 88);
    sj2.print();
    sj2.setValue("세종", 33);
    sj2.print();
  }
}
