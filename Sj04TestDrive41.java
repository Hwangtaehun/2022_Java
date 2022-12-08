class Sj04Class41{
  public Sj04Class41(){
  }
  
  int aa;
  static int ss;
  
  void print(){
    System.out.println("aa = " + aa + "\tss = " + ss);
  }
}

public class Sj04TestDrive41{
  public static void main(String[] args){
    Sj04Class41.ss = 100;
    //Sj05Class41.aa = 200;
    System.out.println("Sj04Class41.s = " + Sj04Class41.ss);
    
    Sj04Class41 sj = new Sj04Class41();
    sj.aa = 200;
    sj.ss++;
    sj.print();
    
    sj.aa++;
    Sj04Class41.ss++;
    sj.print();
  }
}
