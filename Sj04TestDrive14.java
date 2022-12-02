public class Sj04TestDrive14{
  String name = "Sejong";
  int kor;
  int eng;
  int mat;
  int tot;
  void compScore(){
    tot = kor + eng + mat;
  }
  
  public static void main(String[] args){
    Sj04TestDrive14 ss = new Sj04TestDrive14();
    
    System.out.println("객체 생성 후 name = " + ss.name);
    ss.name = "세종";
    ss.kor = 90;
    ss.eng = 70;
    ss.mat = 80;
    ss.compScore();
    System.out.println("값 대입 후 name = " + ss.name);
    System.out.println("tot = " + ss.name);
  }
}
