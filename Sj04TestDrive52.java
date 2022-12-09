interface Sj04Interface52{
    static final int MAX = 99;
    int MIN = 11;
    
    abstract void aPlay();
    void bPlay();
    // void bPlay(){
    //     System.out.println("Interface내에는 Method 본체는 구혈할 수 없습니다.");
    // }
}

class Sj04Class52b implements Sj04Interface52{

    Sj04Class52b(){
    }

    public void aPlay(){
        // MIN = 333;
        System.out.println("Sj04Class52b의 aPlay실행 MAX =" + MAX);
    }
    public void bPlay(){
        System.out.println("Sj04Class52b의 bPlay실행 MIN =" + MIN);
    }
    public void cPlay(){
        System.out.println("Sj04Class52b의 cPlay실행");
    }
}

public class Sj04TestDrive52{
  public static void main(String[] args){
    // Sj04Interface52 sj = new Sj04Interface52();

    Sj04Class52b sj1 = new Sj04Class52b();
    System.out.println("Sj05Class52b 객체생성 후 실행");
    sj1.aPlay();
    sj1.bPlay();
    sj1.cPlay();

    Sj04Interface52 sj2 = new Sj04Class52b();
    System.out.println("선조 Reference에 자손 객체 대입 후 ");
    sj2.aPlay();
    sj2.bPlay();
    //sj2.cPlay();
  }
}
