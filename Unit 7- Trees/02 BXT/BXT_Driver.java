// Name:Jason Xu
// Date:2/12/19

import java.util.*;

/*  Driver for a binary expression tree class.
 *  Input: a postfix string, space delimited tokens. 
 */
public class BXT_Driver
{
   public static void main(String[] args)
   {
      ArrayList<String> postExp = new ArrayList<String>();
      postExp.add("14 -5 /");
      postExp.add("20.0 3.0 -4.0 + *");
      postExp.add("2 3 + 5 / 4 5 - *");
      postExp.add("5.6");
   
      for( String postfix : postExp )
      {
         System.out.println("Postfix Exp: "  + postfix);
         BXT tree = new BXT();
         tree.buildTree( postfix );
         System.out.println("BXT: "); 
         System.out.println( tree.display() );
         System.out.print("Infix order:  ");
         System.out.println( tree.inorderTraverse() );
         System.out.print("Prefix order:  ");
         System.out.println( tree.preorderTraverse() );
         System.out.print("Evaluates to " + tree.evaluateTree());
         System.out.println( "\n------------------------");
      }
   }
}

/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
class BXT
{
   private TreeNode root;
   
   public BXT()
   {
      root = null;
   }
    
   public void buildTree(String str)
   {
     	String[]sArray=str.split(" ");
      Stack<TreeNode> stk=new Stack<TreeNode>();
      for(String valStr: sArray){
         if(!isOperator(valStr)){
            TreeNode tN=new TreeNode(valStr);
            stk.push(tN);
         }
         else{
            TreeNode tNode=new TreeNode(valStr);
            tNode.setRight(stk.pop());
            tNode.setLeft(stk.pop());
            stk.push(tNode);
         }
      }
      root=stk.pop();
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      String tString=(String)t.getValue();
      if(!isOperator(tString))
         return Double.parseDouble(tString);
      return computeTerm(tString,evaluateNode(t.getLeft()),evaluateNode(t.getRight()));
   }
   
   private double computeTerm(String s, double a, double b)
   {
      if(s.equals("+"))
         return a+b;
      if(s.equals("-"))
         return a-b;
      if(s.equals("*"))
         return a*b;
      if(s.equals("/"))
         return a/b;
      if(s.equals("%"))
         return a%b;
      else
         return Math.pow(a,b);
   }
   
   private boolean isOperator(String s)
   {
      String ops="+-*%/^";
      if(ops.contains(s))
         return true;
      return false;
   }
   
   public String display()
   {
      String dString=display(root,0);
      return dString.substring(0,dString.length()-1);
   }
   
   private String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1);
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1);
      return toRet;
   }
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      if(t==null)
         return "";
      String str="";
      str+=inorderTraverse(t.getLeft());
      str+=(String)t.getValue()+" ";
      str+=inorderTraverse(t.getRight());
      return str;
   }
   
   public String preorderTraverse()
   {
      String str=preorderTraverse(root);
      return str.substring(0,str.length()-1);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      if(root==null)
         return "";
      String str="";
      str+=(String)root.getValue()+" ";
      str+=preorderTraverse(root.getLeft());
      str+=preorderTraverse(root.getRight());
      return str;
   }

}

/***************************************

 Postfix Exp: 14 -5 /
 	-5
 /
 	14
 Infix order:  14 / -5 
 Prefix order:  / 14 -5 
 Evaluates to -2.8
 ------------------------
 Postfix Exp: 20.0 3.0 -4.0 + *
 		-4.0
 	+
 		3.0
 *
 	20.0
 Infix order:  20.0 * 3.0 + -4.0 
 Prefix order:  * 20.0 + 3.0 -4.0 
 Evaluates to -20.0
 ------------------------
 Postfix Exp: 2 3 + 5 / 4 5 - *
 		5
 	-
 		4
 *
 		5
 	/
 			3
 		+
         2
 Infix order:  2 + 3 / 5 * 4 - 5 
 Prefix order:  * / + 2 3 5 - 4 5 
 Evaluates to -1.0
 ------------------------
 Postfix Exp: 5.6
 5.6
 Infix order:  5.6 
 Prefix order:  5.6 
 Evaluates to 5.6
 ------------------------
 
 *******************************************/