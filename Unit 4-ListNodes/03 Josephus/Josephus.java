// Name:Jason Xu
// Date:11/19/18

import java.util.*;
import java.io.*;

public class Josephus
{
   private static String WINNER = "Josephus";
   
   public static void main(String[] args) throws FileNotFoundException
   {
      /* run numberCircle first with J_numbers.txt  */
      Scanner sc = new Scanner(System.in);
      System.out.print("How many names (2-20)? ");
      int n = Integer.parseInt(sc.next());
      System.out.print("How many names to count off each time?"  );
      int countOff = Integer.parseInt(sc.next());
      ListNode winningPos = numberCircle(n, countOff, "J_numbers.txt");
      System.out.println(winningPos.getValue() + " wins!");  
     
      /* run josephusCircle next with J_names.txt  */
      System.out.println("\n ****  Now start all over. **** \n");
      System.out.print("Where should "+WINNER+" stand?  ");
      int winPos = Integer.parseInt(sc.next());        
      ListNode theWinner = josephusCircle(n, countOff, "J_names.txt", winPos);
      System.out.println(theWinner.getValue() + " wins!");  
   }
   
   public static ListNode numberCircle(int n, int countOff, String filename) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      p = countingOff(p, countOff, n);
      return p;
   }
   
   public static ListNode josephusCircle(int n, int countOff, String filename, int winPos) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      replaceAt(p, WINNER, winPos);
      p = countingOff(p, countOff, n);
      return p;
   }

   /* reads the names, calls insert(), builds the linked list.
	 */
   public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(f);
      }
      catch (Exception e)
      {
         System.out.println("File not found");
         return null;
      }
      ListNode a=new ListNode(infile.nextLine(),null);
      a.setNext(a);
      ListNode temp=new ListNode(a.getValue(),a.getNext());
      boolean tf=false;
      for(int x=1;x<n;x++){
         a=insert(a,infile.nextLine());
         a.setNext(temp);
         if(tf==false){
            temp.setNext(a);
            tf=true;
         }
      }
      return a.getNext();
   }
   
   /* helper method to build the list.  Creates the node, then
    * inserts it in the circular linked list.
	 */
   public static ListNode insert(ListNode p, Object obj)
   {
      ListNode n=new ListNode(obj,null);
      p.setNext(n);
      return n;
   }
   
   /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
      Ends with one remaining name, who is the winner. 
	 */
   public static ListNode countingOff(ListNode p, int count, int n)
   {
      print(p);
      for(int x=1;x<n;x++){
         p=remove(p,count);
         print(p);
      }  
      return p;
   }
   
   /* removes the node after counting off count-1 nodes.
	 */
   public static ListNode remove(ListNode p, int count)
   {
      for(int y=2;y<count;y++){
         p=p.getNext();
      }
      p.setNext(p.getNext().getNext());
      return p.getNext();
   }
   
   /* prints the circular linked list.
	 */
   public static void print(ListNode p)
   {
      ListNode a=new ListNode(p.getValue(),p.getNext());
      while(a.getNext()!=p){
         System.out.print(a.getValue()+" ");
         a=a.getNext();
      }
      System.out.print(a.getValue());
      System.out.println();
   }
	
   /* replaces the value (the string) at the winning node.
	 */
   public static void replaceAt(ListNode p, Object obj, int pos)
   {
      for(int x=1;x<pos;x++){
         p=p.getNext();
      }
      p.setValue(obj);
   }
}

