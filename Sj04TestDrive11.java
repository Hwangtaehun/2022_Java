class Sj04Class11{
  String name;
  int kor;
  int eng;
  int mat;
  int tot;
  void compScore(){
    tot = kor + eng + mat;
  }
}

public class Sj04TestDrive11{
  public static void main(String[] args){
    Sj04Class11 hak;
    hak = new Sj04Class11();
    hak.name = "세종";
    hak.kor = 90;
    hak.eng = 70;
    hak.mat = 80;
    hak.compScore();
    System.out.println("\n첫 번째 만든 객체");
    System.out.println("name = " + hak.name);
    System.out.println("tot = " + hak.tot);
    
    hak = new Sj04Class11();
    System.out.println("\n두 번째 만든 객체");
    System.out.println("name = " + hak.name);
    System.out.println("tot = " + hak.tot);
  }
}
