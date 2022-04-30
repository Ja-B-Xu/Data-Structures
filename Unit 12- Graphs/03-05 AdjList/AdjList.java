// Name:Jason Xu
// Date:5/22/19
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 * Graphs4: DFS-BFS
 * and Graphs5: EdgeListCities
 */

/* Graphs 3: EdgeList 
 */
interface VertexInterface
{
   String toString(); // Don't use commas in the list.  Example: "C [C D]"
   String getName();
   ArrayList<Vertex> getAdjacencies();
   void addEdge(Vertex v);
} 

class Vertex implements VertexInterface 
{
   private final String name;
   private ArrayList<Vertex> adjacencies;
  
  /* enter your code here  */
   public Vertex(String s){
      name=s;
      adjacencies=new ArrayList<Vertex>();
   }
   public String toString(){
      String adj=name + " [";
      for(Vertex v:adjacencies){
         adj+=v.getName()+", ";
      }
      if(!adj.equals(name+" ["));
         adj=adj.substring(0,adj.length()-2);
      return adj+"]";
   }
   public String getName(){
      return name;
   }
   public ArrayList<Vertex> getAdjacencies(){
      return adjacencies;
   }
   public void addEdge(Vertex v){
      adjacencies.add(v);
   }
}   

interface AdjListInterface 
{ 
   List<Vertex> getVertices();
   Vertex getVertex(int i) ;
   Vertex getVertex(String vertexName);
   Map<String, Integer> getVertexMap();
   void addVertex(String v);
   void addEdge(String source, String target);
   String toString();  //returns all vertices with their edges (omit commas)
}

  
/* Graphs 4: DFS and BFS 
 */
interface DFS_BFS
{
   List<Vertex> depthFirstSearch(String name);
   List<Vertex> depthFirstSearch(Vertex v);
   List<Vertex> breadthFirstSearch(String name);
   List<Vertex> breadthFirstSearch(Vertex v);
   //List<Vertex> depthFirstRecur(String name);
   //List<Vertex> depthFirstRecur(Vertex v);
   //void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/* Graphs 5: Edgelist with Cities 
 */
interface EdgeListWithCities
{
   void graphFromEdgeListData(String fileName) throws FileNotFoundException;
   int edgeCount();
   boolean isReachable(String source, String target);
   boolean isFullyReachable();
}


public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();
  
 /* enter your code here  */
   public AdjList(){
   
   }
   public List<Vertex> getVertices(){
      return vertices;
   }
   public Vertex getVertex(int i){
      return vertices.get(i);
   }
   public Vertex getVertex(String vertexName){
      return vertices.get(nameToIndex.get(vertexName));
   }
   public Map<String, Integer> getVertexMap(){
      return nameToIndex;
   }
   public void addVertex(String v){
      if(!nameToIndex.containsKey(v)){
         vertices.add(new Vertex(v));
         nameToIndex.put(v,vertices.size()-1);
      }
   }
   public void addEdge(String source, String target){
      if(!nameToIndex.containsKey(source)){
         addVertex(source);
      }
      if(!nameToIndex.containsKey(target)){
         addVertex(target);
      }
      int start=nameToIndex.get(source);
      int end=nameToIndex.get(target);
      vertices.get(start).addEdge(vertices.get(end));
   }
   public String toString(){
      String s="";
      for(int x=0;x<vertices.size();x++){
         s+=vertices.get(x)+"\n";
      }
      return s;
   }
   public List<Vertex> depthFirstSearch(String name){
      return depthFirstSearch(vertices.get(nameToIndex.get(name)));
   }
   public List<Vertex> depthFirstSearch(Vertex v){
      List<Vertex> list=new LinkedList<Vertex>();
      Stack<Vertex> stack=new Stack<Vertex>();
      stack.push(v);
      list.add(v);
      Vertex temp=stack.pop();
      for(Vertex edge:temp.getAdjacencies()){
         stack.push(edge);
      }
      while(!stack.isEmpty()){
         temp=stack.pop();
         if(!list.contains(temp)){
            list.add(temp);
            for(Vertex ve:temp.getAdjacencies()){
               stack.push(ve);
            }
         }
      }
      return list;
   }
   public List<Vertex> breadthFirstSearch(String name){
      return breadthFirstSearch(vertices.get(nameToIndex.get(name)));
   }
   public List<Vertex> breadthFirstSearch(Vertex v){
      List<Vertex> list=new LinkedList<Vertex>();
      Queue<Vertex> q=new LinkedList<Vertex>();
      list.add(v);
      for(Vertex edge:v.getAdjacencies()){
         q.add(edge);
      }
      while(!q.isEmpty()){
         Vertex temp=q.remove();
         if(!list.contains(temp)){
            list.add(temp);
            for(Vertex ve:temp.getAdjacencies()){
               q.add(ve);
            }
         }
      } 
      return list; 
   }
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException{
      Scanner sc=new Scanner(new File(fileName));
      while(sc.hasNextLine()){
         String[] sArray=sc.nextLine().split(" ");
         if(!nameToIndex.keySet().contains(sArray[0])){
            Vertex temp=new Vertex(sArray[0]);
            vertices.add(temp);
            nameToIndex.put(sArray[0],vertices.size()-1);
         }
         addEdge(sArray[0],sArray[1]);
      }
   }
   public int edgeCount(){
   int count=0;
   for(Vertex v:vertices){
      count+=v.getAdjacencies().size();
   }
   return count;
   }
   public boolean isReachable(String source, String target){
      return depthFirstSearch(source).contains(getVertex(target));
   }
   public boolean isFullyReachable(){
      for(Vertex v1:vertices){
         for(Vertex v2:vertices){
            if(!isReachable(v1.getName(),v2.getName()))
               return false;
         }
      }
      return true;
   }
}


