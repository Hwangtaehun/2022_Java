class Sj04Class42{
  int aa;
  int bb;
  static int ss;
  
  Sj04Class42(){
    aa++;
    ss++;
  }
  
  void print(){
    System.out.println("aa = " + aa + "\tss = " + ss);
  }
}

public class Sj04TestDrive42{
  public static void main(String[] args){
    Sj04Class42 sj1 = new Sj04Class42();
    sj1.print();
    
    Sj04Class42 sj2 = new Sj04Class42();
    sj2.print();
    
    Sj04Class42 sj3 = new Sj04Class42();
    sj3.print();
  }
}
