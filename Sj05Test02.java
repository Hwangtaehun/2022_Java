class Sj05ClassA{
  int kor;
  int eng;
  public Sj05ClassA(){}
}

class Sj05ClassB{
  int kor;
  int eng;
  public Sj05ClassB(){}
  public Sj05ClassB(int kor, int eng){
    this.kor = kor;
    this.eng = eng;
  }
  public String toString(){
    String str;
    str = "kor=" + kor + ", eng=" + eng;
    return str;
  }
  public boolean equals(Object obj){
    Sj05ClassB sj = (Sj05ClassB)obj;
    if(kor == sj.kor && eng == sj.eng)
      return true;
    else
      return false;
  }
}

public class Sj05Test02{
  public static void main(String[] args){
    Sj05ClassA sj1 = new Sj05ClassA();
    System.out.println("sj1.getClass() = " + sj1.getClass());
    System.out.println("sj1.getClass().getName() = " + sj1.getClass().getName());
    System.out.println("sj1.hashCode() = " + sj1.hashCode());
    System.out.println("sj1.toString() = " + sj1.toString());
    System.out.println("sj1            = " + sj1);
    
    Sj05ClassA sj2 = new Sj05ClassA();
    System.out.println("sj2.toString() = "+ sj2.toString());
    
    int a = 7, b = 7;
    if( a == b)
      System.out.println("== �� \t: a�� b�� ����.");
    else
      System.out.println("== �� \t: a�� b�� �ٸ���.");
    
    if( sj1 == sj2)
      System.out.println("== �� \t: sj1�� sj2�� ����.");
    else
      System.out.println("== �� \t: sj1�� sj2�� �ٸ���.");
      
    if( sj1.equals(sj2))
      System.out.println("equals()�� : sj1�� sj2�� ����.");
    else
      System.out.println("equals()�� : sj1�� sj2�� �ٸ���.");
      
    sj1 = sj2;
     if( sj1.equals(sj2))
      System.out.println("sj1 = sj2 �� : sj1�� sj2�� ����.");
    else
      System.out.println("sj1 = sj2 �� : sj1�� sj2�� �ٸ���.");
      
    Sj05ClassB sj3 = new Sj05ClassB(55, 66);
    Sj05ClassB sj4 = new Sj05ClassB(55, 77);
    
    System.out.println("sj3.toString() : " + sj3.toString());
    System.out.println("sj3            : " + sj3);
    
     if( sj3 == sj4)
      System.out.println("equals()�� : sj3�� sj4�� ����.");
    else
      System.out.println("equals()�� : sj3�� sj4�� �ٸ���.");
    
    sj3.eng = 77;
    System.out.println("sj3:" + sj3 + ", sj4:" + sj4);
    
    if( sj3.equals(sj4))
      System.out.println("equals()�� : sj3�� sj4�� ����.");
    else
      System.out.println("equals()�� : sj3�� sj4�� �ٸ���.");
  }
}
