interface Sj04Interface52{
    static final int MAX = 99;
    int MIN = 11;
    
    abstract void aPlay();
    void bPlay();
    // void bPlay(){
    //     System.out.println("Interface������ Method ��ü�� ������ �� �����ϴ�.");
    // }
}

class Sj04Class52b implements Sj04Interface52{

    Sj04Class52b(){
    }

    public void aPlay(){
        // MIN = 333;
        System.out.println("Sj04Class52b�� aPlay���� MAX =" + MAX);
    }
    public void bPlay(){
        System.out.println("Sj04Class52b�� bPlay���� MIN =" + MIN);
    }
    public void cPlay(){
        System.out.println("Sj04Class52b�� cPlay����");
    }
}

public class Sj04TestDrive52{
  public static void main(String[] args){
    // Sj04Interface52 sj = new Sj04Interface52();

    Sj04Class52b sj1 = new Sj04Class52b();
    System.out.println("Sj05Class52b ��ü���� �� ����");
    sj1.aPlay();
    sj1.bPlay();
    sj1.cPlay();

    Sj04Interface52 sj2 = new Sj04Class52b();
    System.out.println("���� Reference�� �ڼ� ��ü ���� �� ");
    sj2.aPlay();
    sj2.bPlay();
    //sj2.cPlay();
  }
}
