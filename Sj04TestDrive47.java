public class Sj04TestDrive47{
  public static void main(String[] args){
    Sj04Class47 sj1 = new Sj04Class47();
    sj1.localTest1();
    sj1.localTest2();
  }
}

class AnonymousInner{
  AnonymousInner(){
    System.out.println("AnonymousInner Constructor 실행됨");
  }
}
class Sj04Class47{
  int outerAa = 1111;
  static int outerSs = 2222;
  
  Sj04Class47(){
  }
  
  public int plus(int x, int y){
    return (x + y);
  }
  
  public void localTest1(){
    final int finalFf = 100;
    int localAa = 200;
    System.out.println("localTest1 localAa = " + localAa);
    System.out.println("localTest1 finalFf = " + finalFf);
    
    class LocalInner{
    int localBb = 300;
    LocalInner(){
      System.out.println("LocalInner의 생성자");
    }
    void localTest2(){
      localBb = plus(55, 22);
      //System.out.println("Test1 localAa = " + localAa);
      System.out.println("Test1 outerAa = " + outerAa);
      System.out.println("Test1 outerSs = " + outerSs);
      System.out.println("Test1 localBb = " + localBb);
      System.out.println("Test1 finalFf = " + finalFf);
      }
    }
    LocalInner li = new LocalInner();
    li.localTest2();
  }
  public void localTest2(){
    final int finalFf2 = 300;
    int localAa2 = 400;
    
    new AnonymousInner(){
      int localCc = 300;
      //AnonymousInner(){
        //System.out.println("AnonymousInner의 생성자");
      //}
      void localTest3(){
        localCc = plus(55, 22);
        System.out.println("이름없는 localTest outerAa = " + outerAa);
        System.out.println("이름없는 localTest outerSs = " + outerSs);
        System.out.println("이름없는 localTest localCc = " + localCc);
        System.out.println("이름없는 localTest finalFf = " + finalFf2);
      }
    }.localTest3();
  }
}
