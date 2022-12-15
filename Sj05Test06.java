import java.util.*;
public class Sj05Test06{
  public static void main(String[] args){
    HashSet <String>hSet = new HashSet<>();
    System.out.println("생성 후 hSet 요소의 개수 = " + hSet.size());
    hSet.add("aa");
    hSet.add("bb");
    hSet.add("sejong");
    hSet.add("세종");
    System.out.println("add 4번수행 후 hSet 요소의 개수 = " + hSet.size());
    hSet.add("sejong");
    System.out.println("중복내용 추가 후 hSet 요소의 개수 = " + hSet.size());
    hSet.remove("bb");
    System.out.println("삭제후 hSet 내용 = " + hSet);
    
    Iterator<String> it = hSet.iterator();
    it.next();
    it.remove();
    while(it.hasNext()){
      System.out.println(it.next());
    }
    
    Object oo[] = hSet.toArray();
    for(int i = 0; i < oo.length; i++)
      System.out.println(i + " 번째 요소는 " + oo[i]);
      
    for(Object obj : hSet){
      System.out.println((String)obj);
    }
    
    for(String data : hSet){
      System.out.println(data);
    }
  }
}
