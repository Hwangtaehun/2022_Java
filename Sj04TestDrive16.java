class Sj04Class16
{
  private String name = "Sejong";
  private int kor;
  public int eng;
  int mat;
  private int tot;
  private float av;
  
  void compScore(){
    tot = kor + eng + mat;
    av = (float)tot/ 3.0f;
  }
  
  public void print(){
    System.out.println("kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", tot=" + tot);
  }
  // setter(mutator)
  public void setScore(int k, int eng, int mat)
  {
    kor = k;
    this.eng = eng;
    this.mat = mat;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  //getter(accessor)
  public String getDecision()
  {
    String result;
    if(av >= 60.0)
      result = "합격";
    else
      result = "불합격";
    return result;
  }
  public String getName(){
    return name;
  }
  public float getAverage(){
    return av;
  }
}
public class Sj04TestDrive16{
  public static void main(String[] args){
      Sj04Class16 hak1;
      hak1 = new Sj04Class16();
      
      System.out.println("setter 사용 전 name = " + hak1.getName());
      hak1.setName("세종");
      System.out.println("setter 사용 후 name = " + hak1.getName());
      
      //hak1.kor = 50;
      hak1.eng = 60;
      hak1.mat = 70;
      hak1.setScore(50, 60, 70);
      
      hak1.compScore();
      //System.out.println("hak1의 이름은 " + hak1.name);
      System.out.println("hak1의 이름은 " + hak1.getName());
      
      hak1.print();
      System.out.println(hak1.getName() + "씨는 평균" + hak1.getAverage() + "으로 " + hak1.getDecision() + "입니다.");
      
      Sj04Class16 hak2 = new Sj04Class16();
      hak2.setName("컴퓨터");
      hak2.setScore(50, 40, 30);
      hak2.compScore();
      String name = hak2.getName();
      float av = hak2.getAverage();
      String result = hak2.getDecision();
      System.out.println(name + "씨는 평균 " + av + "으로 " + result + "입니다.");
  }
}