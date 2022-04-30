//Name: Jason Xu              
//Date: 4/10/19
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode root=new TreeNode(null);
      while(huffLines.hasNextLine()){
         String val=huffLines.nextLine();
         String[] chars=val.split("");
         String letter=chars[0];
         TreeNode current=root;
         for(int pos=1;pos<chars.length;pos++){
            current=process(current,Integer.parseInt(chars[pos]));
         }
         current.setValue(letter);
      }
      return root;
   }
   private static TreeNode process(TreeNode tNode, int binary){
      TreeNode t=null;
      if(binary==0){
         if(tNode.getLeft()==null){
            t=new TreeNode(null);
            tNode.setLeft(t);
         }
         else
            t=tNode.getLeft();
      }
      else{
         if(tNode.getRight()==null){
            t=new TreeNode(null);
            tNode.setRight(t);
         }
         else
            t=tNode.getRight();
      }
      return t;
   }
   public static String dehuff(String text, TreeNode root)
   {
      TreeNode pointer=root;
      String message="";
      for(String num:text.split("")){
         if(num.equals("0"))
            pointer=pointer.getLeft();
         else
            pointer=pointer.getRight();
         if(pointer.getValue()!=null){
            message+=pointer.getValue();
            pointer=root;
         }

      }
      return message;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
