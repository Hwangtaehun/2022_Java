import java.util.*;
public class Sj05Test06{
  public static void main(String[] args){
    HashSet <String>hSet = new HashSet<>();
    System.out.println("���� �� hSet ����� ���� = " + hSet.size());
    hSet.add("aa");
    hSet.add("bb");
    hSet.add("sejong");
    hSet.add("����");
    System.out.println("add 4������ �� hSet ����� ���� = " + hSet.size());
    hSet.add("sejong");
    System.out.println("�ߺ����� �߰� �� hSet ����� ���� = " + hSet.size());
    hSet.remove("bb");
    System.out.println("������ hSet ���� = " + hSet);
    
    Iterator<String> it = hSet.iterator();
    it.next();
    it.remove();
    while(it.hasNext()){
      System.out.println(it.next());
    }
    
    Object oo[] = hSet.toArray();
    for(int i = 0; i < oo.length; i++)
      System.out.println(i + " ��° ��Ҵ� " + oo[i]);
      
    for(Object obj : hSet){
      System.out.println((String)obj);
    }
    
    for(String data : hSet){
      System.out.println(data);
    }
  }
}
