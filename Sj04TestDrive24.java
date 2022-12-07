class Sj04Class24{
  private String name = "이름";
  private int age = 11;
  private int score = 11;
  
  public Sj04Class24(){
    System.out.println("인수없는 생성자 실행됨");
    this.name = "인수 없는 생성자";
    this.age = 22;
    this.score = 22;
  }
  public Sj04Class24(String name){
    System.out.println("이름 전달 받는 생성자 실행됨");
    this.name = name;
  }
  public Sj04Class24(int age){
    System.out.println("나이 전달 받는 생성자 실행됨");
    this.age = age;
  }
  public Sj04Class24(int age, int score){
    System.out.println("나이, 점수 전달 받는 생성자 실행됨");
    this.age = age;
    this.score = score;
  }
  public void print(){
    System.out.println("name=" + name + ", age=" + age + ", score=" + score + "\n");
  }
}

public class Sj04TestDrive24{
  public static void main(String[] args){
    Sj04Class24 sj1 = new Sj04Class24();
    sj1.print();
    Sj04Class24 sj2 = new Sj04Class24("세종");
    sj2.print();
    Sj04Class24 sj3 = new Sj04Class24(77);
    sj3.print();
    Sj04Class24 sj4 = new Sj04Class24(33, 77);
    sj4.print();
    }
}
