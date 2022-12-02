public class Sj04Class12{
  String name;
  int kor;
  int eng;
  int mat;
  int tot;
  void compScore(){
    tot = kor + eng + mat;
    }
    
  void print(String str){
    int tot;
    float av;
    
    tot = 333;
    av = (float)this.tot / 3.0f;
    System.out.println(str);
    System.out.println("kor = " + kor + ", eng = " + eng + ", mat = " + mat);
    System.out.println("tot = " + tot + ", this.tot = " + this.tot + ", av = " + av);
  }
}
