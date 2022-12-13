class GenericClass<T>{
  private T value;
  public void setValue(T value){
    this.value = value;
  }
  public T getValue(){
    return value;
  }
}

public class Sj05Test04{
  public static void main(String[] args){
    GenericClass <Integer> aa = new GenericClass<Integer>();
    aa.setValue(123);
    System.out.println("Integer : " + aa.getValue());
    
    GenericClass <Double> bb = new GenericClass<>();
    Double d = new Double(123.45);
    bb.setValue(d);
    System.out.println("Double : " + bb.getValue());
    GenericClass <Character> cc = new GenericClass<>();
    cc.setValue('A');
    //cc.setValue(123);
    //cc.setValue(d);
    System.out.println("Character : " + cc.getValue());
    //GenericClass <int> cc = new GenericClass<int>();
    GenericClass <? extends Object> dd;
    dd = aa;
    //cc = aa;
    System.out.println("dd : " + dd.getValue());
  }
}
