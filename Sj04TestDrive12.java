public class Sj04TestDrive12{
  public static void main(String[] args){
    Sj04Class12 hak = new Sj04Class12();
    
    System.out.println("�ʱ�ȭ���� Instance ����");
    System.out.println("name = " + hak.name);
    System.out.println("kor = " + hak.kor);
    
    hak.kor = 90;
    hak.eng = 70;
    hak.mat = 80;
    
    hak.compScore();
    hak.print("ù ��° ���� ��ü hak�� ����");
    
    Sj04Class12 hak2;
    hak2 = hak;
    hak2.print("��ü ���� �� hak2�� ����");
  }
}
