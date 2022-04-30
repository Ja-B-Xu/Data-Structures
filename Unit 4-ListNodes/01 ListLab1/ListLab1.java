// Name:Jason Xu
// Date:11/9/18
import java.util.*;
public class ListLab1
{
   public static void main(String[] args)
   {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer",
         	new ListNode("science",
         		new ListNode("java",
         			new ListNode("coffee", head)
         		)
         	)
         );
      print(head);
      print(head);
      
      /**** uncomment the code below for ListLab1 Extension  ****/
      
   	System.out.println("First = " + first(head));
      System.out.println("Second = " + second(head));
      ListNode p = pointerToLast(head);
      System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
      ListNode c = copyOfLast(head);
      System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);
      print(rest(head));
   		
      Scanner in = new Scanner(System.in);
      System.out.print("Insert what? ");
      String x = in.next();
      head = insertFirst(head, x);
      head = insertLast(head, x);
      print(head);
   }
   public static void print(ListNode head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }
   /* ----------------------------------------------------------------------- */
   public static ListNode copyNode(ListNode arg){
      ListNode l = new ListNode(arg.getValue(), arg.getNext());
      return l;
   }
   public static ListNode copyList(ListNode arg){
      if(arg.getNext()==null)
         return copyNode(arg);
      return new ListNode(arg.getValue(),copyList(arg.getNext()));
   }
   public static ListNode rest(ListNode h){
      return copyList(h.getNext());
   }
   public static Object first(ListNode head){
      if(head==null){
         return null;
      }
      return head.getValue();
   }
   public static Object second(ListNode head){
      return first(rest(head));
   }
   public static ListNode pointerToLast(ListNode head){
      if(head==null)
         return null;
      while(head.getNext()!=null)
         head=head.getNext();
      return head;
   }
   public static ListNode copyOfLast(ListNode head){
      return copyNode(pointerToLast(head));
   }
   public static ListNode insertFirst(ListNode head, Object arg){
      return new ListNode(arg, head);
   }
   public static ListNode insertLast(ListNode head, Object arg){
      ListNode l=pointerToLast(head);
      l.setNext( new ListNode(arg,null));
      return head;
   }

      
      
}

/*****************************************
 
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 First = computer
 Second = science
 Pointer to Last = hello at ListNode@18767ad
 Copy of Last =    hello at ListNode@a7bdcd
 Insert what? abc
 [abc, computer, science, java, coffee, nonsense, boo, foo, hello, abc]
 

  **********************************************/
