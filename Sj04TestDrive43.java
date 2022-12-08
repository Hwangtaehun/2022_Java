class Sj04Class43{
    int aa = 0;
    static int ss = 0;

    Sj04Class43(){
    }

    void printA(){
        aa++;
        ss++;
        System.out.println("printA aa = " + aa + "\tss = " + ss);
    }

    static void printS(){
        // aa++;
        ss++;

        // System.out.println("aa = " + aa);
        System.out.println("printS ss = " + ss);
    }
}

public class Sj04TestDrive43{
    public static void main(String[] args){
        Sj04Class43.printS();

        Sj04Class43 sj1 = new Sj04Class43();
        sj1.printA();

        sj1.printS();
        Sj04Class43.printS();
    }
}