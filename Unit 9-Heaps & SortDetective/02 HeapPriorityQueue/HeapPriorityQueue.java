 //Name:Jason Xu
 //Date:4/1/19
 
import java.util.*;


/* implement the API for java.util.PriorityQueue
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public boolean add(E obj)
   {
      if(this.isEmpty())
         myHeap.add(1,obj);
      else{
         myHeap.add(obj);
         heapUp(myHeap.size()-1);
      }
      return true;
   }
   
   public E remove()
   {
      swap(1,myHeap.size()-1);
      E e=myHeap.remove(myHeap.size()-1);
      heapDown(1,myHeap.size()-1);
      return e;
   }
   
   public E peek()
   {
      if(!this.isEmpty())
         return myHeap.get(1);
      return null;
   }
   
   public boolean isEmpty()
   {
     if(myHeap.size()==1)
        return true;
     return false;
   }
   
   private void heapUp(int k)
   {
      if(k/2<1)
         return;
      if(myHeap.get(k).compareTo(myHeap.get(k/2))<0){
         swap(k,k/2);
         heapUp(k/2);
      }
   }
   
   private void swap(int a, int b)
   {
      E temp=myHeap.get(a);
      myHeap.set(a,myHeap.get(b));
      myHeap.set(b,temp);
   }
   
   private void heapDown(int k, int size)
   {
      int lNum=2*k,rNum=2*k+1;
      if(lNum>size)
         return;
      int maxNum=lNum;
      if(rNum<size&&myHeap.get(rNum).compareTo(myHeap.get(lNum))<0)
         maxNum=rNum;
      if(myHeap.get(k).compareTo(myHeap.get(maxNum))<=0)
         return;
      swap(k,maxNum);
      heapDown(maxNum,size);
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
