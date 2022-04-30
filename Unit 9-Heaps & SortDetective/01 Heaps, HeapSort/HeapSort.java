// Name:Jason Xu
// Date:3/27/19

import java.text.*;
public class HeapSort
{
   public static int SIZE;  //9 or 100
	
   public static void main(String[] args)
   {
      //Part 1: Given a heap, sort it. Do this part first. 
      /*SIZE = 9;  
      double heap[] = {-1,99,80,85,17,30,84,2,16,1};
      
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));*/
      
      //Part 2:  Generate 100 random numbers, make a heap, sort it.
      SIZE = 100;
      double[] heap = new double[SIZE + 1];
      heap = createRandom(heap);
      display(heap);
      makeHeap(heap, SIZE);
      display(heap); 
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array)
   {
      int x=SIZE;
      while(x>0){
         swap(array,1,x);
         x--;
         heapDown(array,1,x);
         heapDown(array,1,x);
      }

      if(array[2] > array[3])   //just an extra swap, if needed.
         swap(array, 2, 3);
   }
  
   public static void swap(double[] array, int a, int b)
   {
      double num= array[a];
      array[a]=array[b];
      array[b]=num;
   
   }
   
   public static void heapDown(double[] array, int k, int size)
   {
      int lNum=2*k,rNum=2*k+1;
      if(lNum>size)
         return;
      int maxNum=lNum;
      if(rNum<size&&array[rNum]>array[lNum])
         maxNum=rNum;
      if(array[k]>=array[maxNum])
         return;
      swap(array,k,maxNum);
      heapDown(array,maxNum,size);
   }
   
   public static boolean isSorted(double[] arr)
   {
      for(int pos=0;pos<arr.length-1;pos++){
         if(arr[pos]>arr[pos+1])
            return false;
      }
      return true;
   }
   
   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {  
      array[0] = -1;   //because it will become a heap
      DecimalFormat df=new DecimalFormat("0.00");
      for(int x=1;x<array.length;x++){
         array[x]=Double.parseDouble(df.format((double)(Math.random()*99+1)));
      }
       
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int size)
   {
      for(int x=size/2;x>0;x--)
         heapDown(array,x,size);
   }
}

