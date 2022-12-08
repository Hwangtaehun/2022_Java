final class Sj04Class44{
    Sj04Class44(){
    }

    void print() {
        System.out.println("Sj04Class44 print 실행됨 ");
    }
}

// class Sj04Class44a extends Sj04Class44{
//     Sj04Class44a(){
//     }
//     void print(){
//         System.out.println("Sj04Class44a");
//     }
// }

class Sj04Class45{
    int num = 100;
    final int MAX = 100;
    void print(){
        num = 222;
        //MAX = 222;
        System.out.println("SjClass45 print num = " + num + "\n MAX = " + MAX);
    }
    final void finalPrint(){
        num = 333;
        System.out.println("SjClass45 finalPrint num = " + num + "\n MAX = " + MAX);
    }
}

class Sj04Class45a extends Sj04Class45{
    void print(){
        num = 444;
        System.out.println("SjClass45a print num = " + num + "\n MAX = " + MAX);
    }
    // final void finalPrint(){
    //     num = 555;
    // }
}

public class Sj04TestDrive45{
    public static void main(String[] args){
        Sj04Class44 sj1 = new Sj04Class44();
        sj1.print();

        Sj04Class45a sj2 = new Sj04Class45a();
        sj2.print();
        sj2.finalPrint();
    }
}