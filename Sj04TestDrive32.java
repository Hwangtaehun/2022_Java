class Sj04Class32{
    String name;
    int kor;
    int eng;
    int mat;
    void setName(String name){
        this.name = name;
    }
    void setScore(int k, int e, int m){
        kor = k;
        eng = e;
        mat = m;
    }
    void print(){
        System.out.println("선조의 name = " + name);
    }
}

class Sj04Class32a extends Sj04Class32{
    int tot;

    void setName(String name){
        this.name = "자손의 setName : " + name;
    }
    void compScore(){
        tot = kor + eng + mat;
    }
    void print(){
        System.out.println("자손 32a의 name = " + name + ", tot = " + tot);
    }
}

class Sj04Class32b extends Sj04Class32{
    double av;
    int kor = 999;

    void compScore(){
        av = (kor + eng + mat)/3.0;
    }
    void print(){
        System.out.println("자손 32b의 name = " + name + ", av = " + av);
    }
    void printA(){
        int kor = 888;
        System.out.println("자손의 printA에서 선조의 print 호출");
        super.print();
        System.out.println("자손의 printA에서 자신의 print 호출");
        print();
        System.out.println("kor = "+kor+", this.kor = "+this.kor+", super.kor = " + super.kor);
    }
}

public class Sj04TestDrive32{
    public static void main(String[] args){
        Sj04Class32a sj1 = new Sj04Class32a();
        sj1.setName("세종");
        sj1.setScore(55, 66, 77);
        sj1.compScore();
        sj1.print();

        Sj04Class32b sj2 = new Sj04Class32b();
        sj2.setName("정보처리");
        sj2.setScore(55, 66, 77);
        sj2.compScore();
        sj2.print();
        sj2.printA();
    }
}