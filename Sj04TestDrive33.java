class Sj04Class33{
  Sj04Class33(){
    System.out.println("33 �μ� ���� ������ �����");
  }
  Sj04Class33(int x){
    System.out.println("33 �μ� �ִ� ������ �����" + x);
  }
  void print(String msg){
    System.out.println(msg + " ������ ���\n");
  }
}

class Sj04Class33a extends Sj04Class33{
  Sj04Class33a(){
    System.out.println("33a �μ� ���� ������ �����");
  }
  Sj04Class33a(int x){
    System.out.println("33a �μ� �ִ� ������ ����� " + x);
  }
  void print33a(){
    System.out.println("print33a ����");
  }
}

class Sj04Class33b extends Sj04Class33{
  Sj04Class33b(){
    super(888);
    System.out.println("33b �μ� ���� ������ �����");
  }
  Sj04Class33b(int x){
    super(999);
    System.out.println("33b �μ� �ִ� ������ ����� " + x);
  }
  void print33b(){
    System.out.println("print33b ����");
  }
}

class Sj04Class33c extends Sj04Class33a{
  Sj04Class33c(){
    System.out.println("33c �μ� ���� ������ �����");
  }
  void print33c(){
    System.out.println("print33c ����");
  }
}

public class Sj04TestDrive33{
  public static void main(String[] args){
    Sj04Class33a sj1 = new Sj04Class33a();
    sj1.print("33a �μ� ���� ");
    
    Sj04Class33a sj2 = new Sj04Class33a(55);
    sj2.print("33a �μ� �ִ� ");
    
    Sj04Class33b sj3 = new Sj04Class33b();
    sj3.print("33b �μ� ���� ");
    
    Sj04Class33b sj4 = new Sj04Class33b(77);
    sj4.print("33b �μ� �ִ� ");
    
    Sj04Class33c sj5 = new Sj04Class33c();
    sj5.print33c();
    sj5.print33a();
    sj5.print("33c �μ� ���� ");
  }
}
