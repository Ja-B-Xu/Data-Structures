// Name:Jason Xu
// Date:11/26/18

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   public int size()
   {
      return size;
   
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      if(head.getValue()==null){
         head.setValue(obj);
         size++;
         return;
      }
      DLNode d=new DLNode(head.getValue(), head.getPrev(), head.getNext());
      for(int x=0;x<index;x++)
         d=d.getNext();
      DLNode l=new DLNode(obj, d.getPrev(), d);
      d.getPrev().setNext(l);
      d.setPrev(l);
      size++;
      if(index==0)
         head=l;
                    
   }
   
   /* return obj at position index. 	*/
   public Object get(int index)
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode d=new DLNode(head.getValue(), head.getPrev(), head.getNext());
      for(int x=0;x<index;x++)
         d=d.getNext();
      return d.getValue();
   }
   
   /* replaces obj at position index. 
        returns the obj that was replaced*/
   public Object set(int index, Object obj)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode d=new DLNode(obj, head.getPrev(), head.getNext());
      for(int x=0;x<index;x++)
         d=d.getNext();
      Object o=d.getNext().getPrev().getValue();
      d.getNext().getPrev().setValue(obj);
      return o;
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object of the node that was removed.    */
   public Object remove(int index)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode d=new DLNode(head.getValue(), head.getPrev(), head.getNext());
      for(int x=0;x<index;x++)
         d=d.getNext();
      Object o=d.getNext().getPrev().getValue();
      d.getNext().setPrev(d.getPrev());
      d.getPrev().setNext(d.getNext());
      if(index==0)
         head=d.getNext();
      size--;
      return o;
   }
   
   /* inserts obj at front of list, increases size   */
   public void addFirst(Object obj)
   {
      if(head.getValue()==null){
         head.setValue(obj);
         size++;
         return;
      }
      DLNode d=new DLNode(obj, head.getPrev(), head);
      head.getPrev().setNext(d);
      head.setPrev(d);
      head=d;
      size++;
      
   }
   
   /* appends obj to end of list, increases size    */
   public void addLast(Object obj)
   {
      if(head.getValue()==null){
         head.setValue(obj);
         size++;
         return;
      }
      DLNode d=new DLNode(obj, head.getPrev(), head);
      head.getPrev().setNext(d);
      head.setPrev(d);
      size++;
   }
   
   /* returns the first element in this list  */
   public Object getFirst()
   {
      return head.getValue();
   }
   
   /* returns the last element in this list  */
   public Object getLast()
   {
      return head.getPrev().getValue();
   }
   
   /* returns and removes the first element in this list, or
       returns null if the list is empty  */
   public Object removeFirst()
   {
      if(head.getValue()==null)
         return null;
      head.getPrev().setNext(head.getNext());
      head.getNext().setPrev(head.getPrev());
      Object o=head.getValue();
      head=head.getNext();
      size--;
      return o;
   }
   
   /* returns and removes the last element in this list, or
       returns null if the list is empty  */
   public Object removeLast()
   {
      if(head.getValue()==null)
         return null;
      head.getPrev().getPrev().setNext(head);
      Object o=head.getPrev().getValue();
      head.setPrev(head.getPrev().getPrev());
      size--;
      return o;
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
      String s=new String("[");
      for(int x=0;x<size;x++){
         s+=(head.getValue());
         if(size-x!=1)
            s+=(", ");
         head=head.getNext();
      }  
      s+="]";
      return s;  
   }
}