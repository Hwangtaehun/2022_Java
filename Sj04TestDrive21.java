class Sj04Class21{
  int iNum;
  double dNum;
  
  int getAbsoluteValue(int x){
    iNum = ( x < 0 ) ? -x : x;
    return iNum;
  }
  double getAbsoluteValue(double x){
    dNum = (x < 0.0) ? -x : x;
    return dNum;
  }
  void print(){
    System.out.println("print() È£ÃâµÊ");
  }
  void print(int x){
    System.out.println("print(int x) È£ÃâµÊx = " + x);
  }
  void print(int x, int y){
    System.out.println("print(int x, int y) È£ÃâµÊx = " + x + " y = " + y);
  }
  void print(float x){
    System.out.println("print(float x) È£ÃâµÊx = " + x);
  }
  void print(double x){
    System.out.println("print(double x) È£ÃâµÊx = " + x);
  }
}

public class Sj04TestDrive21{
  public static void main(String[] args){
    Sj04Class21 sj1;
    sj1 = new Sj04Class21();
    sj1.print();
    sj1.print(5);
    sj1.print(5, 8);
    sj1.print(7.7f);
    sj1.print(7.7);
    System.out.println("-5ÀÇ Àý´ë °ªÀº " + sj1.getAbsoluteValue(-5));
    System.out.println("-5.5ÀÇ Àý´ë °ªÀº " + sj1.getAbsoluteValue(-5.5));
  }
}
