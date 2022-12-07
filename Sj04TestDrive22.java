class Sj04Class22{
  private String name;
  private int age;
  private int score;
  
  public Sj04Class22(){
    System.out.println("积己磊 角青凳");
    name = "积己磊";
    age = 11;
    score = 11;
  }
  
  void setValue(String name, int age){
    this.name = name;
    this.age = age;
  }
  void print(){
    System.out.println("name = " + name + ", age = " + age + ", score = " + score);
  }
}

public class Sj04TestDrive22{
  public static void main(String[] args){
    Sj04Class22 sj1 = new Sj04Class22();
    sj1.print();
    
    Sj04Class22 sj2 = new Sj04Class22();
    sj2.setValue("技辆", 33);
    sj2.print();
  }
}
