abstract class Sj04Class51{
  void aPlay(){
    System.out.println(" Sj04Class51의 aPlay실행 ");
  }
  abstract void bPlay();
}

class Sj04Class51a extends Sj04Class51{
  void bPlay(){
    System.out.println(" Sj04Class51a의 bPlay실행 ");
  }
  void cPlay(){
    System.out.println(" Sj04Class51a의 cPlay실행 ");
  }
}

class Sj04Class51b extends Sj04Class51{
  void aPlay(){
    System.out.println(" Sj04Class51b의 aPlay실행 ");
  }
  void bPlay(){
    System.out.println(" Sj04Class51b의 bPlay실행 ");
  }
}

public class Sj04TestDrive51{
  public static void main(String[] args){
    //Sj04Class51 sj = new Sj04Class51();
    Sj04Class51 sj;
    Sj04Class51a sj1 = new Sj04Class51a();
    Sj04Class51b sj2 = new Sj04Class51b();
    
    System.out.println("\n Sj04Class51a 객체생성 후 실행");
    sj1.aPlay();
    sj1.bPlay();
    sj1.cPlay();

    System.out.println("\n Sj04Class51에 Sj04Class51a 객체대입 후 실행");
    sj = sj1;
    sj.aPlay();
    sj.bPlay();
    //sj.cPlay();

    System.out.println("\n Sj04Class51에 Sj04Class51b 객체대입 후 실행");
    sj = sj2;
    sj.aPlay();
    sj.bPlay();
  }
}
