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
      System.out.println("== 비교 \t: a와 b는 같다.");
    else
      System.out.println("== 비교 \t: a와 b는 다르다.");
    
    if( sj1 == sj2)
      System.out.println("== 비교 \t: sj1와 sj2는 같다.");
    else
      System.out.println("== 비교 \t: sj1와 sj2는 다르다.");
      
    if( sj1.equals(sj2))
      System.out.println("equals()비교 : sj1와 sj2는 같다.");
    else
      System.out.println("equals()비교 : sj1와 sj2는 다르다.");
      
    sj1 = sj2;
     if( sj1.equals(sj2))
      System.out.println("sj1 = sj2 후 : sj1와 sj2는 같다.");
    else
      System.out.println("sj1 = sj2 후 : sj1와 sj2는 다르다.");
      
    Sj05ClassB sj3 = new Sj05ClassB(55, 66);
    Sj05ClassB sj4 = new Sj05ClassB(55, 77);
    
    System.out.println("sj3.toString() : " + sj3.toString());
    System.out.println("sj3            : " + sj3);
    
     if( sj3 == sj4)
      System.out.println("equals()비교 : sj3와 sj4는 같다.");
    else
      System.out.println("equals()비교 : sj3와 sj4는 다르다.");
    
    sj3.eng = 77;
    System.out.println("sj3:" + sj3 + ", sj4:" + sj4);
    
    if( sj3.equals(sj4))
      System.out.println("equals()비교 : sj3와 sj4는 같다.");
    else
      System.out.println("equals()비교 : sj3와 sj4는 다르다.");
  }
}
