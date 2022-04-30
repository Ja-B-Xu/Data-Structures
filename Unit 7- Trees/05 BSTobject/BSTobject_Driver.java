// Name:Jason Xu
// Date:2/23/19

import java.util.*;
import java.io.*;

public class BSTobject_Driver
{
   public static BSTobject<String> tree = null;
   public static BSTobject<Widget> treeOfWidgets = null;
   public static int numberOfWidgets = 10;
   public static void main( String[] args ) 
   {
      // Day one 
      tree = new BSTobject<String>();
      tree = build(tree, "T");
      System.out.print(tree);
      System.out.println("Size: " + tree.size());
      System.out.println("Is empty: "+ tree.isEmpty());
    
   		
   	// Day two
   	// Your assignment the second day is to finish the BSTobject class.  
   	// Specifically, prompt the user for a string, put the characters into 
      // a BST, call toString on this tree, and print the size of the tree.
      Scanner sc=new Scanner(System.in);
   	System.out.print("Enter a string: ");
      String s=sc.next();
      BSTobject<String> tree2=new BSTobject<String>();
      tree2=build(tree2,s);
      System.out.print(tree2);
      System.out.println("Size: "+tree2.size());
      System.out.println("");
      // Day two, Widgets			
   	// Next, fill your BST with 10 Widget objects from widgets.txt.  Display 
      // the tree. Then prompt the user to enter cubits and hands.  If the tree 
      // contains that Widget, delete it, of course restoring the BST order. 
      // Display the new tree and its size. If the tree does not contain that 
      // Widget, print "Not found".*/
      File f=new File("widgets.txt");
      treeOfWidgets=new BSTobject<Widget>();
      treeOfWidgets=build(treeOfWidgets, f);
      System.out.println(treeOfWidgets);
      System.out.print("Enter value of cubits: ");
      String cubs=sc.next();
      System.out.print("Enter value of hands: ");
      String hands=sc.next();
      Widget w= new Widget(Integer.parseInt(cubs), Integer.parseInt(hands));
      if(treeOfWidgets.contains(w)){
         treeOfWidgets.delete(w);
         System.out.println(treeOfWidgets);
      }
      else
         System.out.println("Not found.");
   	// Day three -- AVL tree  -----------------------
   }
  
   /* Build the tree for Strings, Day 1
    */
   public static BSTobject<String> build(BSTobject<String> tree, String str)
   {
      String[] sArray=str.split("");
      for(String s:sArray)
         tree.add(s);
      return tree;
   }
   
   /* Build the tree for Widgets, Day 2
    */
   public static BSTobject<Widget> build(BSTobject<Widget> tree, File file)
   {
      Scanner infile = null;
      try{
         infile = new Scanner( file  );
      }
      catch (Exception e)
      {
        System.out.println("File not found.");
      }        
      
      for(int i = 0; i < numberOfWidgets; i++)   
      {
         tree.add(new Widget(Integer.parseInt(infile.nextLine()),Integer.parseInt(infile.nextLine())));
      }
      return tree;
   }
}

interface BSTinterface<E extends Comparable<E>>
{
   public E add( E element );             //returns the object
   public boolean contains( E element );
   public boolean isEmpty();
   public E delete( E element );          //returns the object, not a Node<E>
   public int size();
   public String toString();
}

class BSTobject <E extends Comparable<E>> implements BSTinterface<E>
{ 
   private Node<E> root; 
   private int size;
   public BSTobject(){
      root=null;
      size=0;
   }
   //instance methods
   public E add( E obj )
   {
      root = add( root, obj );
      size++;
      return obj;
   }
   
   //recursive helper method
   private Node<E> add( Node<E> t, E obj )
   {
      if(t==null)
         return new Node<E>(obj);
      if(obj.compareTo(t.getValue()) > 0)
         t.setRight(add(t.getRight(), obj));
      else
         t.setLeft(add(t.getLeft(), obj));
      return t;
   }
   
   /* Implement the interface here.  Use Node<E> as an example,
    * but root is a field. You need add, contains, isEmpty, 
    * delete, size, and toString.  
    */
    public boolean contains(E val){
      Node<E> node=root;
      while(node!=null){
         if(node.getValue().equals(val))
            return true;
         if(node.getValue().compareTo(val)<0)
            node=node.getRight();
         else
            node=node.getLeft();
      }
      return false;
    }
    public boolean isEmpty(){
      if(size==0)
         return true;
      return false;
    }
    public E delete(E val){
      Node<E> current = root;
      Node<E> parent = null;
      while(current !=null)
      {
         int compare = val.compareTo(current.getValue());
         // ------->  your code goes here
         if(compare<0){
            parent=current;
            current=current.getLeft();
         }
         else if(compare>0){
            parent=current;
            current=current.getRight();
         }
         else{
            if(current.getLeft()==null&&current.getRight()==null){
               if(root.equals(current)){
                  root=null;
                  return val;
               }
               else{
                  if(parent.getLeft().equals(current)){
                     parent.setLeft(null);
                     return val;
                  }
                  parent.setRight(null);
                  return val;
               }
            }
            else if(current.getLeft()!=null&&current.getRight()!=null){
               Node<E> temp=current.getLeft();
               while(temp.getRight()!=null){
                  temp=temp.getRight();
               }
               current.setValue(temp.getValue());
               Node<E> tempDad=current.getLeft();
               if(current.getLeft().equals(temp)){
                  current.setLeft(tempDad.getLeft());
                  return val;
               }
               while(!tempDad.getRight().equals(temp)){
                  tempDad=tempDad.getRight();
               }
               if(temp.getLeft()==null){
                  tempDad.setRight(null);
                  return val;
               }
               else{
                  tempDad.setRight(temp.getLeft());
                  return val;
               }
            }
            else{
               if(current.equals(root)){
                  if(current.getLeft()!=null){
                     root=current.getLeft();
                     return val;
                  }
                  else if(current.getRight()!=null){
                     root=current.getRight();
                     return val;
                  }
               }
               else if(current.getLeft()!=null){
                  if(parent.getLeft().equals(current)){
                     parent.setLeft(current.getLeft());
                     return val;
                  }
                  parent.setRight(current.getLeft());
                  return val;
               }
               else{
                  if(parent.getLeft().equals(current)){
                     parent.setLeft(current.getRight());
                     return val;
                  }
                  parent.setRight(current.getRight());
                  return val;
               }

            }
         }

      }
      return val;  //never reached
   }

    public int size(){
      return size;
    }
    public String toString(){
      return display(root, 0);
    }
   private String display(Node<E> t, int level)
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

   /* Private inner class 
    */  
   private class Node<E>
   {
      private E value;
      private Node<E> left, right;
      private Node(E val){
         value=val;
         left=null;
         right=null;
      }
      private Node(E val, Node<E> l, Node<E> r){
         value=val;
         left=l;
         right=r;
      }
      //methods--Use Node<E> as an example. See Quick Reference Guide.
      private E getValue(){
         return value;
      }
      private Node<E> getLeft(){
         return left;
      }
      private Node<E> getRight(){
         return right;
      }  
      private void setValue(E obj){
         value=obj;
      }
      private void setLeft(Node<E> node){
         left=node;
      }
      private void setRight(Node<E> node){
         right=node;
      }
   }
}
