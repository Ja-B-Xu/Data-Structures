//Name: Jason Xu        
//Date: 4/10/19
import java.util.*;
import java.io.*;
public class Huffman
{
   public static Scanner keyboard = new Scanner(System.in);
   public static void main(String[] args) throws IOException
   {
      //Prompt for two strings 
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat message? ");
      String message = keyboard.nextLine();
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.next();
   
      huffmanize( message, middlePart );
   }
   public static void huffmanize(String message, String middlePart) throws IOException
   {
         //Make a frequency table of the letters
      	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap).     
      	//Use the priority queue of nodes to build the Huffman tree
      	//Process the string letter-by-letter and search the tree for the 
   		//       letter. It's recursive. As you recur, build the path through the tree, 
   		//       where going left is 0 and going right is 1.
         //System.out.println the binary message 
      	//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
         //System.out.println the scheme from the tree--needs a recursive helper method
      	//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")
      HashMap<String, Integer> fTable=new HashMap<String, Integer>();
      PriorityQueue<HuffmanTreeNode> q=new PriorityQueue<HuffmanTreeNode>();
      for(String s:message.split("")){
         if(!fTable.containsKey(s))
            fTable.put(s,1);
         else
            fTable.put(s,fTable.get(s)+1);  
      }
      for(String s:fTable.keySet()){
         q.add(new HuffmanTreeNode(s,fTable.get(s)));
      }
      HuffmanTreeNode root=new HuffmanTreeNode(null,0);
      while(!q.isEmpty()){
         HuffmanTreeNode node1=q.remove();
         if(!q.isEmpty()){
            HuffmanTreeNode node2=q.remove();
            HuffmanTreeNode up=new HuffmanTreeNode("*",node1.getFrequency()+node2.getFrequency());
            up.setLeft(node1);
            up.setRight(node2);
            q.add(up);
         }
         else
            root=node1;
      }
      String binary="";
      HashMap<String, String> letters=new HashMap<String, String>();
      for(String letter:message.split("")){
         String s=buildBinary(root,letter);
         binary+=s;
         letters.put(letter,s);
      }
      System.out.println(binary);
      PrintStream outfile=new PrintStream(new FileOutputStream("message."+middlePart+".txt"));
      outfile.println(binary);
      PrintStream outfile1=new PrintStream(new FileOutputStream("scheme."+middlePart+".txt"));
      for(String let:letters.keySet()){
         System.out.println(let+letters.get(let));
         outfile1.println(let+letters.get(let));
      }
      
   }
   private static String buildBinary(HuffmanTreeNode node, String letter){
      if(node==null)
         return "";
      if(node.getValue().equals(letter))
         return "2";
      String left=buildBinary(node.getLeft(), letter);
      if(!left.equals("")){
         if(left.equals("2"))
            return "0";
         else
            return "0"+left;
      }
      String right=buildBinary(node.getRight(), letter);
      if(!right.equals("")){
         if(right.equals("2"))
            return "1";
         else
            return "1"+right;
      }
      return "";
      
   }

}
	/*
	  * This tree node stores two values.  Look at HuffmanTreeNode's API for some help.
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private Object value; 
   private int frequency;
   private HuffmanTreeNode left, right;
   
   public HuffmanTreeNode(Object initValue, int f)
   { 
      value = initValue; 
      frequency=f;
      left = null; 
      right = null; 
   }
   
   public HuffmanTreeNode(Object initValue, int f, HuffmanTreeNode initLeft, HuffmanTreeNode initRight)
   { 
      value = initValue; 
      frequency=f;
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   public int getFrequency(){
      return frequency;
   }
   
   public HuffmanTreeNode getLeft() 
   { 
      return left; 
   }
   
   public HuffmanTreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(HuffmanTreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(HuffmanTreeNode theNewRight)
   { 
      right = theNewRight;
   }
   public int compareTo(HuffmanTreeNode node){
      if(this.frequency>node.frequency)
         return 1;
      if(this.frequency<node.frequency)
         return -1;
      return 1;
   }
}

