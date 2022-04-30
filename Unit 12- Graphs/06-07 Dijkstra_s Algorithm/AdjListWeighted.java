// Name:Jason Xu
// Date:6/3/19
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs6: Dijkstra
 * and Graphs7: Dijkstra with Cities
 */

class Edge 
{
   //public fields not common on AP exam
   public final wVertex target;  
   public final double weight;   
  
   public Edge(wVertex argTarget, double argWeight) 
   {
      target = argTarget;
      weight = argWeight;
   }
}

interface wVertexInterface 
{
   String getName();
   double getMinDistance();
   void setMinDistance(double m);
   wVertex getPrevious();   //for Dijkstra 7
   void setPrevious(wVertex v);  //for Dijkstra 7
   ArrayList<Edge> getAdjacencies();
   void addEdge(wVertex v, double weight);   
   int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   private wVertex previous;  //for building the actual path in Dijkstra 7
   
   /*  enter your code for this class here   */ 
   public wVertex(String name1){
      name=name1;
      adjacencies=new ArrayList<Edge>();
      previous=null;
   }
   public String getName(){
      return name;
   }  
   public wVertex getPrevious(){
      return previous;
   }
   public void setPrevious(wVertex w){
      previous=w;
   }
   public double getMinDistance(){
      return minDistance;
   }
   public void setMinDistance(double m){
      minDistance=m;
   }  
   public ArrayList<Edge> getAdjacencies(){
      return adjacencies;
   }
   public void addEdge(wVertex v, double weight){
      adjacencies.add(new Edge(v,weight));
   }
   public int compareTo(wVertex other){
      return (int)(getMinDistance()-other.getMinDistance());
   }
    
}

interface AdjListWeightedInterface 
{
   List<wVertex> getVertices();
   Map<String, Integer> getNameToIndex();
   wVertex getVertex(int i);   
   wVertex getVertex(String vertexName);
   void addVertex(String v);
   void addEdge(String source, String target, double weight);
   void minimumWeightPath(String vertexName);   //Dijkstra's
}

/* Interface for Graphs 7:  Dijkstra with Cities 
 */

interface AdjListWeightedInterfaceWithCities 
{       
   List<String> getShortestPathTo(wVertex v);
   AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException ;
}



public class AdjListWeighted implements AdjListWeightedInterface, AdjListWeightedInterfaceWithCities
{
   private List<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
   //the constructor is a no-arg constructor 
  
   /*  enter your code for Graphs 6 */ 
   public List<wVertex> getVertices(){
      return vertices;
   }
   public Map<String, Integer> getNameToIndex(){
      return nameToIndex;
   }
   public wVertex getVertex(int i){
      return vertices.get(i);
   }
   public wVertex getVertex(String vertexName){
      return vertices.get(nameToIndex.get(vertexName));
   }
   public void addVertex(String v){
      nameToIndex.put(v,vertices.size());
      vertices.add(new wVertex(v));
   }
   public void addEdge(String source, String target, double weight){
      getVertex(source).addEdge(getVertex(target),weight);
   }
   public void minimumWeightPath(String vertexName){
      PriorityQueue<wVertex> q=new PriorityQueue<wVertex>();
      HashSet<wVertex> solved=new HashSet<wVertex>();
      wVertex v=getVertex(vertexName);
      v.setMinDistance(0.0);
      q.add(v);
      while(!q.isEmpty()){
         wVertex temp=q.remove();
         solved.add(temp);
         double min=Double.POSITIVE_INFINITY;
         wVertex minV=new wVertex("temp");
         for(Edge e:temp.getAdjacencies()){
            if(e.weight<min){
               min=e.weight; 
               minV=e.target;
            }
            boolean found=false;
            for(wVertex wv:q){
               if(wv.equals(e.target)){
                  if(temp.getMinDistance()+e.weight<wv.getMinDistance()){
                     wv.setMinDistance(e.weight+temp.getMinDistance());
                     wv.setPrevious(temp);
                  }
                  found=true;
               }  
            }
            if(!found&&!solved.contains(e.target)){
               e.target.setMinDistance(e.weight+temp.getMinDistance());
               e.target.setPrevious(temp);
               q.add(e.target);
            }
            
         }
      }
   }
      
   
   /*  enter your code for two new methods in Graphs 7 */
   
   public AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException{
      AdjListWeighted alw=new AdjListWeighted();
      Scanner sc1=new Scanner(vertexNames);
      Scanner sc2=new Scanner(edgeListData);
      int count=Integer.parseInt(sc1.nextLine());
      for(int x=0;x<count;x++){
         alw.addVertex(sc1.nextLine().trim());
      }
      while(sc2.hasNextLine()){
         String[] sArray=sc2.nextLine().split(" ");
         if(sArray.length==3)
            alw.addEdge(sArray[0],sArray[1],Double.parseDouble(sArray[2]));
      }
      return alw;
   }
   public List<String> getShortestPathTo(wVertex v){
      List<String> shortest = new ArrayList<String>();
      wVertex temp=v;
      shortest.add(temp.getName());
      while(temp.getPrevious()!=null){
         shortest.add(temp.getPrevious().getName());
         temp=temp.getPrevious();
      }
      Queue<String> reverser=new LinkedList<String>();
      while(!shortest.isEmpty()){
         reverser.add(shortest.remove(shortest.size()-1));
      }
      while(!reverser.isEmpty()){
         shortest.add(reverser.remove());
      }
      return shortest;
   }
   

   
}   


