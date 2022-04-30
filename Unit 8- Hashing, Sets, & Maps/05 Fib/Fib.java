// Name:Jason Xu
// Date:3/13/19

import java.util.*;

interface Fibber
{
   public abstract int fib(int n);
}

public class Fib
{
   public static final int FIBsubN = 40;
   public static void main(String[] args)
   {
      System.out.println("Recursive");
      calculate(new Fib1(), FIBsubN);
      System.out.println("Iterative, stored in an array");
      calculate(new Fib2(FIBsubN + 1), FIBsubN);
      System.out.println("Recursive, stored in an arrayList");
      calculate(new Fib3(), FIBsubN);
      System.out.println("Recursive, stored in a hashMap");
      calculate(new Fib4(), FIBsubN);		
   }
      
   public static void calculate(Fibber fibber, int n)
   {
      long start = System.nanoTime();
      int f = fibber.fib(n);
      long finish = System.nanoTime();
      long time = finish - start;
      
      System.out.print("fib(" + n + ") = " + f);
      System.out.println(" (" + time + "nanoseconds)");		
      System.out.println();		
   }
}
    
class Fib1 implements Fibber
{
   public Fib1()    
   {
   }
   
   public int fib(int n)
   {
      if(n == 1 || n == 2)
         return 1;
      else
         return fib(n - 1) + fib(n - 2);
   }
}
   
class Fib2 implements Fibber
{
   private int[] array;
   
   public Fib2(int n)
   {
      array=new int[n+1];
   }
   
   public int fib(int n)
   {
      for(int x=1;x<=n;x++){
         if(x==1||x==2)
            array[x]=1;
         else
            array[x]=array[x-1]+array[x-2];
      }
      return array[n];
   }
   
   public int[] getArray()   //nice to have
   {
      return array;
   }
}
   
class Fib3 implements Fibber
{
   private ArrayList<Integer> myFibList;
   
   public Fib3()
   {
      myFibList=new ArrayList<Integer>();
   }
   
   public int fib(int n)
   {
      if(myFibList.size()>n&&myFibList.get(n-1)!=null)
         return myFibList.get(n-1);
      if(myFibList.size()>n&&myFibList.get(n-2)!=null&&myFibList.get(n-3)!=null){
            myFibList.add(myFibList.get(n-2)+myFibList.get(n-3));
            return myFibList.get(n-1);
      }
      if(n == 1 || n == 2){
         if(myFibList.size()<=n||myFibList.get(n)==null)
            myFibList.add(1);
         return 1;
         }
      else{
         myFibList.add(fib(n - 1) + fib(n - 2));
         return myFibList.get(n-1);
      }
   }
   
   public ArrayList<Integer> getArrayList()   //nice to have
   {
      return myFibList;
   }
}

class Fib4 implements Fibber
{
   private Map<Integer, Integer> myFibMap;
   
   public Fib4()
   {
      myFibMap=new TreeMap<Integer, Integer>();
   }
   
   public int fib(int n)
   {
      if(n==1||n==2){
         if(!myFibMap.containsKey(n))
            myFibMap.put(n,1);
         return 1;
      }
      if(myFibMap.containsKey(n))
         return myFibMap.get(n);
      if(myFibMap.containsKey(n-1)&&myFibMap.containsKey(n-2)){
         myFibMap.put(n,myFibMap.get(n-1)+myFibMap.get(n-2));
         return myFibMap.get(n);
      }
      else{
         myFibMap.put(n,fib(n-1)+fib(n-2));
         return myFibMap.get(n);
      }
   }
   
   public Map<Integer, Integer> getMap()  //nice to have
   {
      return myFibMap;
   }
}
	
   
   
   /*
    Recursive
    fib(42) = 267914296 (3276558048 nanoseconds)
    
    Iterative, stored in an array
    fib(42) = 267914296 (4988 nanoseconds)
    
    Recursive, stored in an arrayList
    fib(42) = 267914296 (64025 nanoseconds)
    
    Recursive, stored in a hashMap
    fib(42) = 267914296 (177793 nanoseconds)
    
   	*/
