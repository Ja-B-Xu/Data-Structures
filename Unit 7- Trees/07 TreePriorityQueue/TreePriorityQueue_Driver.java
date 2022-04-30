// Name:Jason Xu
// Date:2/27/19
public class TreePriorityQueue_Driver
{
   private static TreePriorityQueue tpq = null;
   public static void main(String[] args)
   {
      tpq = new TreePriorityQueue();
      int[] array = {13, 11, 14, 11, 15, 14, 14};
       //	int[] array = {4, 6, 5, 7}; 
      //  int[] array = {7, 6, 4, 5}; 
         //int[] array = {7, 6, 4, 5, 4, 4};
      //   int[] array = {4, 5, 4, 4, 7, 6, 7, 7};
        
      tpq = build( tpq, array );
      System.out.println( tpq.display() );      
      System.out.println("Peek min: " + tpq.peekMin());
      System.out.println("Removing");
      while( !tpq.isEmpty() )
         System.out.println( "     " + tpq.removeMin() );
   }
   public static TreePriorityQueue build( TreePriorityQueue tpq, int[] array )
   {
      for( int x : array )
         tpq.add(x);
      return tpq;
   }
}

class TreePriorityQueue
{
   private TreeNode root;
   
   public TreePriorityQueue()
   {   root = null;  }
   
   //postcondition:  returns true if the priority queue is empty;
   //					  otherwise, returns false
   public boolean isEmpty()
   {  
      return root == null;
   }
   
   //postcondition:  obj has been added to the priority queue
   public void add(Object obj)
   {	
      root = addHelper((Comparable) obj, root);  
   }
   
   //postcondition:  obj has been added to the subtree rooted at t;
   //					  the updated subtree is returned
   private TreeNode addHelper(Comparable obj, TreeNode t)
   {
      if(t==null){
         t=new TreeNode(new Item(obj));
         return t;
      }
      TreeNode temp=t;
      
      while(!((Item)(t.getValue())).getData().equals(obj)){
         if(((Comparable)((Item)(t.getValue())).getData()).compareTo(obj)<0){
            if(t.getRight()!=null)
               t=t.getRight();
            else{
               t.setRight(new TreeNode(new Item(obj)));
               return temp;
            }
         }
         else{
            if(t.getLeft()!=null)
               t=t.getLeft();
            else{
               t.setLeft(new TreeNode(new Item(obj)));
               return temp;
            }
         }
      }
      ((Item)(t.getValue())).incrementCount();
      return temp;
   }
   			
   					
   //precondition:  the priority queue is not empty
   //postcondition:  a data value of highest priority (smallest value) has been 
   //						removed and returned
   public Object removeMin()
   { 
      
   }
   
   //precondition:   the priority queue is not empty
   //postcondition: a data value of highest priority (smallest value) if returned; 
   //					 the priority queue is unchanged
   public Object peekMin()
   {	
      TreeNode temp=root;
      while(temp.getLeft()!=null)
         temp=temp.getLeft();
      return ((Item)(temp.getValue())).getData();
   }
   public String display()
   {
      return display( root, 0 );
   }
   private String display( TreeNode t, int level )							
   {
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
}
  
class Item
{
   private Comparable data;
   private int count;
   public Item(Comparable d)
   {  
      data=d;
      count=1;
        
   }
   public void incrementCount()
   {	
      count++;
   }
   //precondition:  the count of this item is greater than 0;
   public void decrementCount()
   {  
      count--;
   }
   public int getCount()
   {	
      return count;
   }
   public Comparable getData()
   {  
       return data;
   }
   public String toString()
   {
      return "["+data+", "+count+"]";
   }
}