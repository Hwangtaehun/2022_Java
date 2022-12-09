interface Sj04Interface53a{
    static final int MAX = 99;
    void aPlay();
}

interface Sj04Interface53b{
    int MIN = 11;
    void bPlay();
}

class Sj04Class53a implements Sj04Interface53a, Sj04Interface53b{
    Sj04Class53a(){
    }

    public void aPlay(){
        System.out.println(" Sj04Class53a의 aPlay실행 MAX =" + MAX + " MIN =" + MIN);
    }

    public void bPlay(){
        System.out.println(" Sj04Class53a의 bPlay실행 MAX =" + MAX + " MIN =" + MIN);
    }

    public void cPlay(){
        System.out.println(" Sj04Class53a의 cPlay실행 MAX =" + MAX + " MIN =" + MIN);
    }
}

public class Sj04TestDrive53{
    public static void main(String[] args){
        Sj04Class53a sj1 = new Sj04Class53a();
        System.out.println("\n Sj04Class53a 객체생성 후 실행 ");
        sj1.aPlay();
        sj1.bPlay();
        sj1.cPlay();

        System.out.println("\n Sj05Interface81에 Sj05Class81 객체생성 대입 수 실행 ");
        Sj04Interface53a sj2 = new Sj04Class53a();
        sj2.aPlay();
        // sj2.bPlay();
        // sj2.cPlay();
    }
}