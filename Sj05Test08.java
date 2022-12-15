import java.util.*;
public class Sj05Test08{
  public static void main(String[] args){
    Stack <String> stack = new Stack<>();
    stack.push("aa");
    stack.push("bb");
    stack.push("cc");
    stack.push("dd");
    System.out.println("pop : " + stack.pop());
    System.out.println("peek : " + stack.peek());
    System.out.println("stack : " + stack);
    while(!stack.isEmpty()){
      System.out.println("pop : " + stack.pop());
    }
    
    Queue<String> queue = new LinkedList<>();
    queue.offer("aaa");
    queue.offer("bbb");
    queue.offer("ccc");
    queue.offer("ddd");
    System.out.println("queue : " + queue);
    System.out.println("poll : " + queue.poll());
    System.out.println("peek : " + queue.peek());
    System.out.println("queue2 : " + queue);
    
    LinkedList<String> list = new LinkedList<>();
    list.add("aa");
    list.add("bb");
    list.add("cc");
    list.addLast("bb");
    list.addFirst("ee");
    System.out.println("list : " + list);
    System.out.println("getFirst : " + list.getFirst());
    System.out.println("get(2) : " + list.get(2));
    System.out.println("pollFirst : " + list.pollFirst());
    System.out.println("remove : " + list.removeFirstOccurrence("bb"));
    System.out.println("list : " + list);
  }
}
