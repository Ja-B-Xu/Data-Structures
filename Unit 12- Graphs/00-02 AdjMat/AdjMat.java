// Name:Jason Xu  
// Date:4/24/19
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 * Graph1 WarshallDriver,
 * and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   void addEdge(int source, int target);
   void removeEdge(int source, int target);
   boolean isEdge(int from, int to);
   String toString();   
   int edgeCount();
   List<Integer> getNeighbors(int source);
   //public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      
{
   boolean isEdge(String from, String to);
   Map<String, Integer> getVertices();     
   void readNames(String fileName) throws FileNotFoundException;
   void readGrid(String fileName) throws FileNotFoundException;
   void displayVertices();
   void allPairsReachability();  // Warshall's Algorithm
}

interface Floyd
{
   int getCost(int from, int to);
   int getCost(String from, String to);
   void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix, Warshall
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name-->index (for Warshall & Floyd)
     
   /*  enter your code here  */  
   public AdjMat(int size){
      grid=new int[size][size];
   }
   
   /*
   /
   / Warshall and Floyd methods
   /
   */
   public boolean isEdge(String from, String to){
      if(grid[vertices.get(from)][vertices.get(to)]<9999&&grid[vertices.get(from)][vertices.get(to)]>0)
         return true;
      return false;
   }
   public Map<String, Integer> getVertices(){
      return vertices;
   }
   public void readNames(String filename) throws FileNotFoundException{
      Scanner sc=new Scanner(new File(filename));
      vertices=new TreeMap<String, Integer>();
      sc.nextLine();
      int x=0;
      while(sc.hasNextLine()){
         vertices.put(sc.nextLine(),x);
         x++;
      }
   }
   public void readGrid(String filename) throws FileNotFoundException{
      Scanner sc=new Scanner(new File(filename));
      int size=Integer.parseInt(sc.nextLine());
      for(int x=0;x<size;x++){
         for(int y=0;y<size;y++){
            grid[x][y]=Integer.parseInt(sc.next());
         }
      }
   }
   public void displayVertices(){
      for(String s:vertices.keySet()){
         System.out.println(vertices.get(s)+"-"+s);
      }
      System.out.println();
   }
   public void allPairsReachability(){
      for(int x=0;x<grid.length;x++){
         for(int y=0;y<grid.length;y++){
            if(grid[y][x]==1){
               for(int count=0;count<grid.length;count++){
                  if(grid[y][count]==0&&grid[x][count]==1)
                     grid[y][count]=1;
               }
            }
         }
      }
   }
   public int getCost(int from, int to){
      return grid[from][to];
   }
   public int getCost(String from, String to){
      int start=vertices.get(from);
      int end=vertices.get(to);
      return grid[start][end];
   }
   public void allPairsWeighted(){
      for(int x=0;x<grid.length;x++){
         for(int y=0;y<grid.length;y++){
            if(grid[y][x]<9999&&grid[y][x]>0){
               for(int count=0;count<grid.length;count++){
                  if(isEdge(x,count)){
                     int amount=grid[y][x]+grid[x][count];
                     if(grid[y][count]>amount)
                        grid[y][count]=amount;
                  }
               }
            }
         }
      }
   }
   

   /*
   /
   / AdjacencyMatrix methods
   /
   */
   public void addEdge(int x, int y){
      grid[x][y]=1;
   }
   public boolean isEdge(int x, int y){
      if(grid[x][y]<9999&&grid[x][y]>0)
         return true;
      return false;
   }
   public List<Integer> getNeighbors(int source){
      List<Integer> neighbors=new ArrayList<Integer>();
      for(int y=0;y<grid[source].length;y++){
         if(grid[source][y]==1)
            neighbors.add(y);
      }
      return neighbors;
   }
   public void removeEdge(int x, int y){
      grid[x][y]=0;
   }
   public int edgeCount(){
      int count=0;
      for(int x=0;x<grid.length;x++){
         for(int y=0;y<grid[x].length;y++){
            if(grid[x][y]<9999&&grid[x][y]>0)
               count++;
         }
      }
      return count;
   }
   public String toString(){
      String mat="";
      for(int x=0;x<grid.length;x++){
         for(int y=0;y<grid[x].length;y++){
            mat+=grid[x][y]+"\t";
         }
         mat+="\n";
      }
      return mat;
   }
   
   
}
