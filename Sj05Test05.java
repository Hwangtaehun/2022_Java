import java.util.*;
public class Sj05Test05{
  public static void main(String[] args){
    ArrayList aList = new ArrayList();
    aList.add(new Integer(1));
    aList.add(3.3);
    aList.add("sejong");
    aList.add("sejong");
    System.out.println("중복내용 추가 후 aList 요소의 개수 = " + aList.size());
    System.out.println("현재aList 내용 = " + aList);
    for(int i = 0; i < aList.size(); i++)
      System.out.println("aList의 " + i + " 번째 요소는 " + aList.get(i));
      
    ArrayList<String> bList = new ArrayList<>();
    bList.add("세종");
    bList.add("컴퓨터");
    bList.add(0, "학원");
    System.out.println("현재bList 내용 = " + bList);
    
    ArrayList<Object> cList = new ArrayList<>();
    cList.add(new Integer(123));
    cList.add(5.5);
    cList.add("Sejong");
    cList.add('A');
    System.out.println("현재cList 내용 = " + cList);
    
    Object obj1 = cList.get(2);
    Object obj2 = cList.get(3);
    if(obj1 instanceof String){
      System.out.println("obj1 = String Type");
      String str = (String)obj1;
      System.out.println("str = " + str);
    }
    if(obj2 instanceof Character){
      System.out.println("obj2 = Character Type");
      char ch = ((Character) obj2).charValue();
      System.out.println("ch=" + ch);
    }
    else{
      System.out.println("obj2 = Character Type 아님");
    }
    System.out.println("Sejong이 있는지 : " + cList.contains("Sejong"));
    
    System.out.println("Sejong의 위치 : " + cList.indexOf("Sejong"));
    System.out.println("cList가 비어있는지 : " + cList.isEmpty());
    cList.remove("Sejong");
    System.out.println("현재cList 내용 = " + cList);
    cList.removeAll(cList);
    System.out.println("cList가 비어있는지 : " + cList.isEmpty());
    Collections.sort(bList);
    System.out.println("Sort된 bList 내용 = " + bList);
  }
}
