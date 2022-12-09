class Sj04Class48{
  Sj04Class48(){
  }
  
  void aPlay(){
    //System.out.println("Sj04Class48의 aPlay실행");
    System.out.println("동물소리 ");
  }
  
  //void bPlay(){
    //System.out.println("Sj04Class48의 bPlay");
  //}
}

class Sj04Class48a extends Sj04Class48{
  Sj04Class48a(){
  }
  
  void aPlay(){
    //System.out.println("Sj04Class48a의 aPlay");
    System.out.println("멍멍멍");
  }
  
  void bPlay(){
    System.out.println("Sj04Class48a의 bPlay");
  }
}

class Sj04Class48b extends Sj04Class48{
  Sj04Class48b(){
  }
  void aPlay(){
    //System.out.println("Sj04Class48b의 aPlay");
    System.out.println("야옹");
  }
  
  void bPlay(){
    System.out.println("Sj04Class48b의 bPlay");
  }
}

class Sj04Class48c{
  Sj04Class48c(){
  }
  
  public void testPlay(Sj04Class48 sj)
  {
    sj.aPlay();
    sj.bPlay();
  }
}

public class Sj04TestDrive48{
  public static void main(String[] args){
    Sj04Class48c sj1 = new Sj04Class48c();
    Sj04Class48a sja = new Sj04Class48a();
    Sj04Class48b sjb = new Sj04Class48b();
    System.out.println("-- sja의 aPlay, bPlay 실행 ----- ");
    sja.aPlay();
    sja.bPlay();
    System.out.println("-- sjb의 aPlay, bPlay 실행 ----- ");
    sjb.aPlay();
    sjb.bPlay();
    
    System.out.println("-- sj1을 전달 -----");
    sj1.testPlay(sja);
    
    System.out.println("-- sj1을 전달 ----");
    sj1.testPlay(sjb);
    
    System.out.println("\n-- Sj04Class48 객체 생성 후 aPlay 실행 -----");
    Sj04Class48 sj2 = new Sj04Class48();
    sj2.aPlay();
    
    System.out.println("\n-- Sj04Class48에 Sj04Class48a 객체대입 후 실행 -----");
    sj2 = sja;
    sj2.aPlay();
    
    System.out.println("\n-- Sj04Class48에 Sj04Class48b 객체대입 후 실행 -----");
    sj2 = sjb;
    sj2.aPlay();
    sj2.bPlay();
  }
}
