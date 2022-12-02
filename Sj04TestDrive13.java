class Sj04Class131{
  int value1;
  
  void modifyValue(int val1){
    value1 = val1;
    val1 = 777;
  }
}

class Sj04Class132{
  void modifyValue(Sj04Class131 ref1){
    ref1.value1 = 33;
  }
}

public class Sj04TestDrive13{
  public static void main(String[] args){
    Sj04Class131 ref1 = new Sj04Class131();
    Sj04Class132 ref2 = new Sj04Class132();
    
    int val1 = 100;
    ref1.value1 = 200;
    System.out.println("초기값 대입 후 val1 = " + val1 + " ref1.value1 = " + ref1.value1);
    
    ref1.modifyValue(val1);
    System.out.println("int 값 대입 후 val1 = " + val1 + " ref1.value1 = " + ref1.value1);
    
    ref2.modifyValue(ref1);
    System.out.println("Reference 전달 후 val1 = " + val1 + " ref1.value1 = " + ref1.value1);
  }
}
