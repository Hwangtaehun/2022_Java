class Sj04Class31{
  String name;
  int kor;
  int eng;
  int mat;
  int tot;

  void setName(String name){
    this.name = name;
  }

  void setScore(int k, int e, int m){
    kor = k;
    eng = e;
    mat = m;
  }
  void print(){
    System.out.println("name = " + name + ", tot = " + tot);
  }
}

class Sj04Class31a extends Sj04Class31{
  void compScore(){
    tot = kor + eng + mat;
  }
}

class Sj04Class31b extends Sj04Class31{
  double av;
  void compScore(){
    av = (kor + eng + mat)/3.0;
  }
  void print(){
    System.out.println("name = " + name + ", av = " + av);
  }
}

public class Sj04TestDrive31{
  public static void main(String[] args){
    Sj04Class31a sj1 = new Sj04Class31a();
    sj1.setName("세종");
    sj1.setScore(55, 66, 77);
    sj1.compScore();
    sj1.print();
    Sj04Class31b sj2 = new Sj04Class31b();
    sj2.setName("정보처리");
    sj2.setScore(55, 66, 77);
    sj2.compScore();
    sj2.print();
  }
}
