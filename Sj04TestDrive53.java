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
        System.out.println(" Sj04Class53a�� aPlay���� MAX =" + MAX + " MIN =" + MIN);
    }

    public void bPlay(){
        System.out.println(" Sj04Class53a�� bPlay���� MAX =" + MAX + " MIN =" + MIN);
    }

    public void cPlay(){
        System.out.println(" Sj04Class53a�� cPlay���� MAX =" + MAX + " MIN =" + MIN);
    }
}

public class Sj04TestDrive53{
    public static void main(String[] args){
        Sj04Class53a sj1 = new Sj04Class53a();
        System.out.println("\n Sj04Class53a ��ü���� �� ���� ");
        sj1.aPlay();
        sj1.bPlay();
        sj1.cPlay();

        System.out.println("\n Sj05Interface81�� Sj05Class81 ��ü���� ���� �� ���� ");
        Sj04Interface53a sj2 = new Sj04Class53a();
        sj2.aPlay();
        // sj2.bPlay();
        // sj2.cPlay();
    }
}