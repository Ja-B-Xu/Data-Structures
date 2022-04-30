// Name:Jason Xu
// Date:2/13/19

import java.util.*;

/*  Make an ArrayList of input strings.  Build the Binary 
 *  Search Trees (using TreeNodes) from the letters in the 
 *  string.   Display it as a sideways tree (take the code 
 *  from TreeLab).  Prompt the user for a target and search 
 *  the BST for it.  Display the tree's minimum and maximum 
 *  values. Print the letters in order from smallest to largest.
 */

public class BinarySearchTree
{
   public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      ArrayList<String> str = new ArrayList<String>();
      str.add("MAENIRAC");
      str.add("AMERICAN");
      str.add("AACEIMNR");
      str.add("A");
      str.add("6829301");
   
      for( String s : str )
      {
         System.out.println("String: "  + s);
         TreeNode root = null;
         root = buildTree( root, s );
         System.out.println( display(root, 0) );
         System.out.print("Input target: ");
         String target =  keyboard.next();    //"I"
         boolean itemFound = find(root, target);
         if(itemFound)
            System.out.println("found: " + target);
         else
            System.out.println(target +" not found.");
         
         System.out.println("Min = " + min(root));
         System.out.println("Max = " + max(root));	
      
         System.out.print("In Order: ");
         System.out.println( smallToLarge(root) );
         System.out.println("\n---------------------");
      }
   }
   
   /* @param str string of characters
    */
   public static TreeNode buildTree(TreeNode t, String str)
   {
      for(int x = 0; x < str.length(); x++)
         t = insert(t, "" + str.charAt(x));
      return t;
   }
   
   /* Recursive algorithm to build a BST:  if the node is 
    * null, insert the new node.  Else, if the item is less,
    * set the left node and recur to the left.  Else, if the 
    * item is greater, set the right node and recur to the 
    * right.   
    * @param s one letter to be inserted
    */
   public static TreeNode insert(TreeNode t, String s)
   {  	
      if(t==null)
         return new TreeNode(s);
      if(s.compareTo(t.getValue()+"") > 0)
         t.setRight(insert(t.getRight(), s));
      else
         t.setLeft(insert(t.getLeft(), s));
      return t;
   }
   
   /* Copy the code that is in TreeLab  
    */
   public static String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   /* Iterative algorithm:  create a temporary pointer p at 
    * the root. While p is not null, if the p's value equals 
    * the target, return true. If the target is less than the 
    * p's value, go left, otherwise go right. If the target 
    * is not found, return false. Find the target. Recursive 
    * algorithm:  If the tree is empty, return false.  If the 
    * target is less than the current node value, return the 
    * left subtree.  If the target is greater, return the right 
    * subtree.  Otherwise, return true.   
    */    
   public static boolean find(TreeNode t, Comparable x)
   {
      TreeNode p=t;
      while(p!=null){
         if(((Comparable)p.getValue()).compareTo(x)==0)
            return true;
         else if (((Comparable)p.getValue()).compareTo(x)<=0)
            p=p.getRight();
         else
            p=p.getLeft();
      }
      return false;
   }
      
   /*	Starting at the root, return the min value in the BST.   
    *	Use iteration.   Hint:  look at several BSTs. Where are 
    *	the min values always located?  
    */
   public static Object min(TreeNode t)
   {
      if(t==null)
         return null;
      while(t.getLeft()!=null)
         t=t.getLeft();
      return t.getValue();
   }
      
   /* Starting at the root, return the max value in the BST.  
    * Use recursion!
    */
   public static Object max(TreeNode t)
   {
      if(t==null)
         return null;
      if(t.getRight()==null)
         return t.getValue();
      return max(t.getRight());
   }
   
   public static String smallToLarge(TreeNode t)
   {
      if(t==null)
         return "";
      String str="";
      str+=smallToLarge(t.getLeft());
      str+=(String)t.getValue()+" ";
      str+=smallToLarge(t.getRight());
      return str;
   }
}


/***************************************
 String: MAENIRAC
 		R
 	N
 M
 			I
 		E
 			C
 	A
 		A
 Input target: I
 found: I
 Min = A
 Max = R
 In Order: A A C E I M N R 
 ---------------------
 String: AMERICAN
 		R
 			N
 	M
 			I
 		E
 			C
 A
 	A
 Input target: I
 found: I
 Min = A
 Max = R
 In Order: A A C E I M N R 
 ---------------------
 String: AACEIMNR
 						R
 					N
 				M
 			I
 		E
 	C
 A
 	A
 Input target: i
 i not found.
 Min = A
 Max = R
 In Order: A A C E I M N R 
 ---------------------   
 ************************************/