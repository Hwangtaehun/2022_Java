public class Sj03Array13
{
  public static void main(String[] args)
  {
    int i;
    int [] arr;
    arr = new int[10];
    System.out.print("\narr 배열의 내용(값 대입 전) : ");
    for(i = 0; i < 10; i++){
        System.out.print(" " + arr[i]);
    }
    for(i = 0; i < 10; i ++){
        arr[i] = i + 1;
    }
    System.out.print("\narr 배열의 내용(값 대입 후) : ");
    for(i = 0; i < 10; i++){
        System.out.print(" " + arr[i]);
    }
    System.out.println("\narr배열 요소 수 = " + arr.length);

    int [] arr1 = {11, 22, 33, 44, 55, 66};
    System.out.println("\narr1 배열 초기화 후 요소의 수 = " + arr1.length);
    System.out.print("arr1 배열의 내용 : ");
    for(i = 0;i < arr1.length; i++){
        System.out.print(" " + arr1[i]);
    }

    arr1 = arr;
    System.out.println("\n\narr1 = arr 실행후 후 arr 요소의 수 = " + arr.length);
    System.out.println("arr1 = arr 실행후 후 arr1 요소의 수 = " + arr1.length);
    arr[2] = 333;
    System.out.print("arr1 배열의 내용 : ");
    for(i = 0;i < arr1.length; i++){
        System.out.print(" " + arr1[i]);
    }
  }
}
