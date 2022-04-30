// Name:Jason Xu
// Date:2/8/2019

import java.util.*;

public class TreeLab
{
   public static TreeNode root = null;
   public static String s = "XCOMPUTERSCIENCE";
   //public static String s = "XThomasJeffersonHighSchool"; 
   //public static String s = "XAComputerScienceTreeHasItsRootAtTheTop";
   //public static String s = "XA";   //comment out lines 44-46 below
   //public static String s = "XAF";  //comment out lines 44-46 below
   //public static String s = "XAFP";  //comment out lines 44-46 below
   //public static String s = "XDFZM";  //comment out lines 44-46 below 
   
   public static void main(String[] args)
   {
      root = buildTree( root, s );
      System.out.print( display( root, 0) );
   
      System.out.print("\nPreorder: " + preorderTraverse(root));
      System.out.print("\nInorder: " + inorderTraverse(root));
      System.out.print("\nPostorder: " + postorderTraverse(root));
   
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Only children = " + countOnlys(root));
      System.out.println("Grandparents = " + countGrandParents(root));
   
      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Longest path = " + longestPath(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
   
      System.out.println("\nBy Level: ");
      System.out.println(displayLevelOrder(root));
   }
 
   public static TreeNode buildTree(TreeNode root, String s)
   {
      root = new TreeNode("" + s.charAt(1), null, null);
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, 
            (int)(1 + Math.log(pos) / Math.log(2)));
      insert(root, "A", 17, 5); 
      insert(root, "B", 18, 5); 
      insert(root, "C", 37, 6); //B's right child
      return root;
   }

   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
      {
         if((pos & (1 << k)) == 0)
            p = p.getLeft();
         else
            p = p.getRight();
      }
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   }
   
   private static String display(TreeNode t, int level)
   {
      // turn your head towards left shoulder visualize tree
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
   
   public static String preorderTraverse(TreeNode t)
   { 
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public static String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());  
      toReturn += t.getValue() + " ";              
      toReturn += inorderTraverse(t.getRight());  
      return toReturn;
         
   }
   
   public static String postorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += postorderTraverse(t.getLeft());  
      toReturn += postorderTraverse(t.getRight());
      toReturn += t.getValue() + " ";                
      return toReturn;
   }
   
   public static int countNodes(TreeNode t)
   {
      int total=1;
      if(t==null)
         return 0;
      total+=countNodes(t.getLeft());
      total+=countNodes(t.getRight());
      return total;
      
   }
   
   public static int countLeaves(TreeNode t)
   {
      int total=0;
      if(t==null)
         return -1;
      if(countLeaves(t.getLeft())==-1&&countLeaves(t.getRight())==-1)
         total+=3;
      else if(countLeaves(t.getLeft())==-1||countLeaves(t.getRight())==-1)
         total+=1;
      total+=countLeaves(t.getLeft());
      total+=countLeaves(t.getRight());
      return total;
   }
   
   /*  there are clever ways and hard ways to count grandparents  */ 
   public static int countGrandParents(TreeNode t)
   {
      if(t==null)
         return 0;
      int count=0;
      if(isGParent(t))
         count++;
      return count+countGrandParents(t.getLeft())+countGrandParents(t.getRight());
      
   }
   
   public static boolean isGParent(TreeNode t){
      if(t.getLeft()!=null){
         if(t.getLeft().getLeft()!=null||t.getLeft().getRight()!=null)
            return true;
      }
      if(t.getRight()!=null){
         if(t.getRight().getLeft()!=null||t.getRight().getRight()!=null)
            return true;
      }
      return false;
   }
   
   public static int countOnlys(TreeNode t)
   {
      int count=0;
      if(t==null)
         return 0;
      if((t.getLeft()==null&&t.getRight()!=null)||(t.getLeft()!=null&&t.getRight()==null))
         count++;
      return count+countOnlys(t.getLeft())+countOnlys(t.getRight());
      
   }
   
   /* Returns the max of the heights on both sides of the tree
	 */
   public static int height(TreeNode t)
   {
      if(t==null)
         return -1;
      return Math.max(1+height(t.getLeft()),1+height(t.getRight()));
   }
   
   /* Returns the max of sum of heights on both sides of tree
	 */   
   public static int longestPath(TreeNode t)
   {
      if(t==null)
         return 0;
      int x= height(t.getLeft())+height(t.getLeft());
      x=Math.max(x,longestPath(t.getLeft()));
      x=Math.max(x,longestPath(t.getRight()));
      return x;
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object min(TreeNode t)
   {
      if(t==null)
         return null;
      Object o=t.getValue();
      if(t.getLeft()!=null){
         Object a=min(t.getLeft());
         if(((Comparable)(o)).compareTo(((Comparable)a))>0)
            o=min(t.getLeft());
      }
      if(t.getRight()!=null){
         Object b=min(t.getRight());
         if(((Comparable)(o)).compareTo(((Comparable)b))>0)
            o=min(t.getRight());
      }
      return o;
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object max(TreeNode t)
   {
      if(t==null)
         return null;
      Object o=t.getValue();
      if(t.getLeft()!=null){
         Object a=max(t.getLeft());
         if(((Comparable)(o)).compareTo(((Comparable)a))<0)
            o=max(t.getLeft());
      }
      if(t.getRight()!=null){
         Object b=max(t.getRight());
         if(((Comparable)(o)).compareTo(((Comparable)b))<0)
            o=max(t.getRight());
      }
      return o;
   }
      
   /* This method is not recursive.  Use a local queue
    * to store the children of the current TreeNode.
    */
   public static String displayLevelOrder(TreeNode t)
   {
      Queue<TreeNode> q = new LinkedList<TreeNode>();
      String s="";
      int total=countNodes(t);
      for(int x=0;x<total;x++){
         if(t!=null){
            q.add(t.getLeft());
            q.add(t.getRight());
            s+=((String)t.getValue());
         }
         t=q.remove();
         
      }
      while(!q.isEmpty()){
         TreeNode val=q.remove();
         if(val!=null)
            s+=(String)(val.getValue());
      }
      return s;
   }
}

/***************************************************
    ----jGRASP exec: java TreeLab
 		  	E
 		E
 			C
 	M
 			N
 		T
 			E
 C
 			I
 		U
 			C
 	O
 			S
 					C
 				B
 		P
 				A
 			R
 
 Preorder: C O P R A S B C U C I M T E N E C E 
 Inorder: R A P B C S O C U I C E T N M C E E 
 Postorder: A R C B S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Only children = 3
 Grandparents = 5
 
 Height of tree = 5
 Longest path = 8
 Min = A
 Max = U
 
 By Level: 
 COMPUTERSCIENCEABC    
 *******************************************************/
