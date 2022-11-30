public class Sj02for4{
    public static void main(String[] args){
        int i, j;
        System.out.println("다중 for의 제어 변수 값을 출력");
        for(i = 1 ; i <= 3; i++){
            System.out.println("i Loop의 시작 ");
            for(j = 1; j <= 2; j++){
                System.out.println("i = " + i + "\t j = " + j);
            }
            System.out.println("i Loop의 끝 ");
        }
        System.out.println("\n삼각형그리기");
        for(i = 1 ; i <= 4 ; i++){
            for(j = 1; j <= i ; j++)
            {
                System.out.print("*");
            }
            System.out.println();
        }
        for(i = 3 ; i >= 1 ; i--){
            for(j = 1; j <= i ; j++)
            {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    
}