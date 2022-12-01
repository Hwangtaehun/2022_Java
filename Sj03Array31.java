public class Sj03Array31
{
  public static void main(String[] args)
  {
    String str1 = new String("세종정보처리");
    String str2 = "Sejong Computer";
    System.out.println("str1 = " + str1 + " str1의 길이 = " + str1.length());
    System.out.println("str2 = " + str2 + " str1의 길이 = " + str2.length());

    String[] strArr = new String[3];

    strArr[0] = new String("aaaa");
    strArr[1] = "bbbb";
    strArr[2] = "cccc";

    System.out.println("\nstrArr의 내용");
    for(int i = 0; i < strArr.length; i++){
        System.out.println("strArr[" + i + "] = " + strArr[i]);
    }

    String[] strArr1 = {"xxx", "yyyy", "zz"};
    System.out.println("\nstrArr1의 내용");
    for(int i = 0; i < strArr1.length; i++){
        System.out.print("strArr1[" + i + "] = " + strArr1[i]);
        System.out.println("\t문자열 길이 = " + strArr1[i].length());
    }
  }
}
