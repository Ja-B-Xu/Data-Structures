 //name:Jason Xu
 //date:10/27/18
import java.util.*;
import java.io.*;
public class SelectionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
     //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      Selection.sort(array);
      print(array);
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
      System.out.println();
      
      //Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      Selection.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
   public static void print(double[] a)
   {
      for(double d: a)         //for-each
         System.out.print(d+" ");
      System.out.println();
   }
   public static void print(Object[] papaya)
   {
      for(Object abc : papaya)     //for-each
         System.out.print(abc+" ");
   }
   public static boolean isAscending(double[] a)
   {
      int len=a.length;
      for(int x=1;x<len;x++){
         if(a[x]<a[x-1])
            return false;
      }
      return true;
   }
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static boolean isAscending(Comparable[] a)
   {
      int len=a.length;
      for(int x=1;x<len;x++){
         if(a[x].compareTo(a[x-1])<0)
            return false;
      }
      return true;
   }
}
//*********************************************
//name:Jason Xu    
//date:10/27/18
class Selection
{
   public static void sort(double[] array)
   {
      int len=array.length;
      for(int x=0;x<len;x++){
         swap(array,findMax(array,len-x-1),len-x-1);
      }
   }
   private static int findMax(double[] array, int upper)//"upper" controls where the inner
   																//loop of the Selection Sort ends
   {
      double max=array[0];
      int maxPos=0;
      for(int x=0;x<upper+1;x++){
         if(array[x]>=max){
            max=array[x];
            maxPos=x;
         }
      }
      return maxPos;
   }
   private static void swap(double[] array, int a, int b)
   {
      double temp;
      temp=array[a];
      array[a]=array[b];
      array[b]=temp;
   }   	
   
	/*******  for Comparables ********************/
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
    public static void sort(Comparable[] array)
   {
      int len=array.length;
      for(int x=0;x<len;x++){
         swap(array,findMax(array,len-x-1),len-x-1);
      }
   }
   @SuppressWarnings("unchecked")
    public static int findMax(Comparable[] array, int upper)
   {
      Comparable max=array[0];
      int maxPos=0;
      for(int x=0;x<upper+1;x++){
         if(array[x].compareTo(max)>0){
            max=array[x];
            maxPos=x;
         }
      }
      return maxPos;
   }
   public static void swap(Object[] array, int a, int b)
   {
      Object temp=array[a];
      array[a]=array[b];
      array[b]=temp;
   }
}

