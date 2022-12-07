class Sj04Class25{
  private String name = "이름";
  private int age = 11;
  private int score = 11;
  
  private Sj04Class25(){
    System.out.println("인수없는 생성자 실행됨");
    this.name = "인수없는 생성자";
  }
  Sj04Class25(String name){
    this();
    System.out.println("이름 전달받는 생성자 실행됨");
    this.name = name;
  }
  public Sj04Class25(int age){
    this("나이에서 이름 생성자 호출");
    System.out.println("나이 전달받는 생성자 실행됨");
    this.name = "나이 전달받는 생성자";
  }
  public Sj04Class25(int age, int score){
    this(777);
    System.out.println("나이, 점수 전달받는 생성자 실행됨");
    this.name = "나이, 점수 전달받는 생성자";
  }
  public void print(){
    System.out.println("name = " + name + "\n");
  }
}

public class Sj04TestDrive25{
  public static void main(String[] args){
    //Sj04Class25 sj1 = new Sj04Class25();
    //sj1.print();
    Sj04Class25 sj2 = new Sj04Class25("세종");
    sj2.print();
    Sj04Class25 sj3 = new Sj04Class25(77);
    sj3.print();
    Sj04Class25 sj4 = new Sj04Class25(33, 77);
    sj4.print();
  }
}
