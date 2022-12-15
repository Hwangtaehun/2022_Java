import java.util.*;
public class Sj05Test09{
  public static void main(String[] args){
    HashMap<String, Integer> hMap = new HashMap<>();
    hMap.put("k1", 11);
    hMap.put("k3", 44);
    hMap.put("k2", 22);
    hMap.put("k3", 33);
    hMap.put("k4", null);
    
    System.out.println("get(\"k3\") : " + hMap.get("k3"));
    System.out.println("key : " + hMap.keySet());
    System.out.println("value : " + hMap.values());
    hMap.remove("k4");
    System.out.println("key=value : " + hMap);
    System.out.println("size : " + hMap.size());
    
    Iterator<String> it = hMap.keySet().iterator();
    while(it.hasNext()){
      String key = it.next();
      System.out.println( key + " = " + hMap.get(key));
    }
    
    Set<String> set = hMap.keySet();
    for(String key : set){
      System.out.println( key + " : " + hMap.get(key));
    }
    
    Hashtable<String, String> hTable = new Hashtable<String, String>();
    hTable.put("k1", "세종");
    hTable.put("k2", "컴퓨터");
    hTable.put("k3", "학원");
    //hTable.put("k4", null);
    System.out.println("hTable = " + hTable);
  }
}
