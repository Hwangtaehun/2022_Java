class Sj04Class48{
  Sj04Class48(){
  }
  
  void aPlay(){
    //System.out.println("Sj04Class48�� aPlay����");
    System.out.println("�����Ҹ� ");
  }
  
  //void bPlay(){
    //System.out.println("Sj04Class48�� bPlay");
  //}
}

class Sj04Class48a extends Sj04Class48{
  Sj04Class48a(){
  }
  
  void aPlay(){
    //System.out.println("Sj04Class48a�� aPlay");
    System.out.println("�۸۸�");
  }
  
  void bPlay(){
    System.out.println("Sj04Class48a�� bPlay");
  }
}

class Sj04Class48b extends Sj04Class48{
  Sj04Class48b(){
  }
  void aPlay(){
    //System.out.println("Sj04Class48b�� aPlay");
    System.out.println("�߿�");
  }
  
  void bPlay(){
    System.out.println("Sj04Class48b�� bPlay");
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
    System.out.println("-- sja�� aPlay, bPlay ���� ----- ");
    sja.aPlay();
    sja.bPlay();
    System.out.println("-- sjb�� aPlay, bPlay ���� ----- ");
    sjb.aPlay();
    sjb.bPlay();
    
    System.out.println("-- sj1�� ���� -----");
    sj1.testPlay(sja);
    
    System.out.println("-- sj1�� ���� ----");
    sj1.testPlay(sjb);
    
    System.out.println("\n-- Sj04Class48 ��ü ���� �� aPlay ���� -----");
    Sj04Class48 sj2 = new Sj04Class48();
    sj2.aPlay();
    
    System.out.println("\n-- Sj04Class48�� Sj04Class48a ��ü���� �� ���� -----");
    sj2 = sja;
    sj2.aPlay();
    
    System.out.println("\n-- Sj04Class48�� Sj04Class48b ��ü���� �� ���� -----");
    sj2 = sjb;
    sj2.aPlay();
    sj2.bPlay();
  }
}
