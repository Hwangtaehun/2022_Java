public class Sj04TestDrive46{
  public static void main(String[] args){
    System.out.println("Outer class�� outerSs = " + Sj04Class46.outerSs);
    Sj04Class46 sj1 = new Sj04Class46();
    System.out.println("Outer class�� outerAa = " + sj1.outerAa);
    System.out.println("test1 Method���� ȣ��");
    sj1.test1();
    
    Sj04Class46.InnerNon sj2 = new Sj04Class46().new InnerNon();
    System.out.println("\nNon static innerAa = " + sj2.innerAa);
    sj2.prn();
    
    Sj04Class46.InnerStatic sj3 = new Sj04Class46.InnerStatic();
    System.out.println("static InnerinnerBb = " + sj3.innerBb);
    sj3.prn();
  }
}

class Sj04Class46{
  int outerAa = 111;
  static int outerSs = 222;
  
  Sj04Class46(){
  }
  
  public void test1(){
    InnerNon non = new InnerNon();
    InnerStatic sta = new InnerStatic();
    
    non.prn();
    sta.prn();
  }
  public int plus(int x, int y){
    return (x+y);
  }
  
  class InnerNon{
    int innerAa = 33;
    //static int innerSs1 = 44;
    
    InnerNon(){
    }
    
    public void prn(){
      innerAa = plus(33, 55);
      System.out.println("innerNon���� outerSs = " + outerSs);
      System.out.println("innerNon���� outerAa = " + outerAa);
      System.out.println("innerNon���� innerAa = " + innerAa);
    }
  }
  
  static class InnerStatic{
    int innerBb = 44;
    static int innerSs2 = 555;
    
    InnerStatic(){
    }
    
    public void prn(){
      //innerBb = plus(33, 55);
      //System.out.println("innerStatic���� outerAa = " + outerAa);
      System.out.println("innerStatic���� outerSs = " + outerSs);
      System.out.println("innerStatic���� innerBb = " + innerBb);
      System.out.println("innerStatic���� innerSs2 = " + innerSs2);
    }
  }
}
