 //name:Jason Xu  
 //date:10/28/18
import java.util.*;
import java.io.*;
public class InsertionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
     //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      Insertion.sort(array);
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
   
      Insertion.sort(arrayStr);
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

//**********************************************************
 //name:Jason Xu
 //date:10/28/18
class Insertion
{
   public static double[] sort(double[] array)
   {                   
     double val; 
     int i; 

     for (int j = 1; j < array.length; j++)
    {
           val = array[ j ];
           for(i = j - 1;i>shift(array,j,val); i--)
          {
                 array[i+1] = array[i];
          }
         array[i+1] = val;
     }
      return array;
   }
   private static int shift(double[] array, int index, double value)
   {
      int x;
      for(x=index-1;x >= 0 && value<array[x];x--){
         }
      return x;
   }
   @SuppressWarnings("unchecked")
    public static Comparable[] sort(Comparable[] array)
   { 
     Comparable val; 
     int i; 

     for (int j = 1; j < array.length; j++)
    {
           val = array[ j ];
           for(i = j - 1;i>shift(array,j,val); i--)
          {
                 array[i+1] = array[i];
          }
         array[i+1] = val;
     }
      return array;
   }
   @SuppressWarnings("unchecked")
    private static int shift(Comparable[] array, int index, Comparable value)
   {
      int x;
      for(x=index-1;x >= 0 && value.compareTo(array[x])<0;x--){
         }
      return x;
   }
}
