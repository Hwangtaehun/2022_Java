abstract class Sj04Class51{
  void aPlay(){
    System.out.println(" Sj04Class51�� aPlay���� ");
  }
  abstract void bPlay();
}

class Sj04Class51a extends Sj04Class51{
  void bPlay(){
    System.out.println(" Sj04Class51a�� bPlay���� ");
  }
  void cPlay(){
    System.out.println(" Sj04Class51a�� cPlay���� ");
  }
}

class Sj04Class51b extends Sj04Class51{
  void aPlay(){
    System.out.println(" Sj04Class51b�� aPlay���� ");
  }
  void bPlay(){
    System.out.println(" Sj04Class51b�� bPlay���� ");
  }
}

public class Sj04TestDrive51{
  public static void main(String[] args){
    //Sj04Class51 sj = new Sj04Class51();
    Sj04Class51 sj;
    Sj04Class51a sj1 = new Sj04Class51a();
    Sj04Class51b sj2 = new Sj04Class51b();
    
    System.out.println("\n Sj04Class51a ��ü���� �� ����");
    sj1.aPlay();
    sj1.bPlay();
    sj1.cPlay();

    System.out.println("\n Sj04Class51�� Sj04Class51a ��ü���� �� ����");
    sj = sj1;
    sj.aPlay();
    sj.bPlay();
    //sj.cPlay();

    System.out.println("\n Sj04Class51�� Sj04Class51b ��ü���� �� ����");
    sj = sj2;
    sj.aPlay();
    sj.bPlay();
  }
}
