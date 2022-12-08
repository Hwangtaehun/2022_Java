package Sj04b;

import Sj04a.*;

class Sj04Class35a extends Sj04Class35{
  protected int tot;
  void setScore(){
    kor = 11;
    eng = 11;
    mat = 11;
  }
  void print(){
    protectedMethod();
    defaultMethod();
    publicMethod();
  }
}

class Sj04Class36a extends Sj04Class34{
  protected int tot;
  void setScore(){
    kor = 22;
    eng = 22;
  }
  
  void print(){
    protectedMethod();
    publicMethod();
  }
}

public class Sj04TestDrive35{
  public static void main(String[] args){
    Sj04class35a sj21 = new Sj04Class35a();
    
    sj21.kor = 121;
    sj21.eng = 121;
    sj21.mat = 121;
    
    sj21.protectedMethod();
    sj21.defaultMethod();
    sj21.publicMethod();
    sj21.print();
    sj21.printValue();
    
    Sj04Class36a sj22 = new Sj04Class36a();
    
    sj22.eng = 122;
    
    sj22.publicMethod();
    sj22.print();
    sj22.printValue();
  }
}
