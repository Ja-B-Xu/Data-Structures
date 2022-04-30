//name:Jason Xu    
//date:10/26/18
import java.io.*;      //the File 
import java.util.*;    //the Scanner 
public class Searches_Driver
{
   private static int amount=1325;
   public static void main(String[] args) throws Exception
   {
      String[] apple = input("declaration.txt");
      Arrays.sort(apple);  
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("Enter a word: ");
         String target = sc.next();   //Liberty  
         if(target.equals("-1") )
            break;
         Searches.reset();
         int found = Searches.linear(apple, target);
         if(found == -1)
            System.out.println(target + " was not found by the linear search.");
         else
            System.out.println("Linear Search found it at location "+found+" in " + Searches.getLinearCount()+" comparisons.");
         int found2 = Searches.binary(apple, target); 
         if(found2 == -1)
            System.out.println(target + " was not found by the binary search.");
         else
            System.out.println("Binary Search found it at location "+found2+" in " + Searches.getBinaryCount()+" comparisons.");
      }
   }
   public static String[] input(String filename) throws Exception
   {
      Scanner infile = new Scanner( new File(filename) );
      String[] array = new String[amount];
      for (int k =0; k<amount; k++)    
         array[k] = infile.next();
      infile.close();
      return array;
   }
}
/////////////////////////////////////////////////////////
class Searches
{
   private static int linearCount = 0;
   private static int binaryCount = 0;      
   public static int getLinearCount()
   {
      return linearCount;
   }
   public static int getBinaryCount()
   {
      return binaryCount;
   }
   public static void reset()
   {
      linearCount = 0;
      binaryCount = 0;
   }
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static int linear(Comparable[] array, Comparable target)
   { 
      boolean check=false;
      int x;
      for(x=0;x<array.length;x++){
         if(array[x].equals(target)){
            linearCount=x+1;
            return x;
         }
      }
      return -1;
   }
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static int binary(Comparable[] array, Comparable target)
   {
      int val=binaryhelper(array,target,0,1324);
      return val;
   }
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   private static int binaryhelper(Comparable[] array, Comparable target, int start, int end)
   {
      Comparable avg=array[(start+end)/2];
      if(start>end)
         return -1;
      if(avg.compareTo(target)==0){
         binaryCount++;
         return (start+end)/2;
      }
      if(avg.compareTo(target)>0){
         binaryCount++;
         return binaryhelper(array,target,start,(start+end)/2-1);
      }
      if(avg.compareTo(target)<0){
         binaryCount++;
         return binaryhelper(array,target,(start+end)/2+1,end);
      }
      
      return -1;
   }
}