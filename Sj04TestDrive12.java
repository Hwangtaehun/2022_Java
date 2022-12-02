public class Sj04TestDrive12{
  public static void main(String[] args){
    Sj04Class12 hak = new Sj04Class12();
    
    System.out.println("초기화전의 Instance 변수");
    System.out.println("name = " + hak.name);
    System.out.println("kor = " + hak.kor);
    
    hak.kor = 90;
    hak.eng = 70;
    hak.mat = 80;
    
    hak.compScore();
    hak.print("첫 번째 만든 객체 hak의 내용");
    
    Sj04Class12 hak2;
    hak2 = hak;
    hak2.print("객체 대입 후 hak2의 내용");
  }
}
