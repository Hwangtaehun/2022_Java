class Sj04Class25{
  private String name = "�̸�";
  private int age = 11;
  private int score = 11;
  
  private Sj04Class25(){
    System.out.println("�μ����� ������ �����");
    this.name = "�μ����� ������";
  }
  Sj04Class25(String name){
    this();
    System.out.println("�̸� ���޹޴� ������ �����");
    this.name = name;
  }
  public Sj04Class25(int age){
    this("���̿��� �̸� ������ ȣ��");
    System.out.println("���� ���޹޴� ������ �����");
    this.name = "���� ���޹޴� ������";
  }
  public Sj04Class25(int age, int score){
    this(777);
    System.out.println("����, ���� ���޹޴� ������ �����");
    this.name = "����, ���� ���޹޴� ������";
  }
  public void print(){
    System.out.println("name = " + name + "\n");
  }
}

public class Sj04TestDrive25{
  public static void main(String[] args){
    //Sj04Class25 sj1 = new Sj04Class25();
    //sj1.print();
    Sj04Class25 sj2 = new Sj04Class25("����");
    sj2.print();
    Sj04Class25 sj3 = new Sj04Class25(77);
    sj3.print();
    Sj04Class25 sj4 = new Sj04Class25(33, 77);
    sj4.print();
  }
}
