// Name: Jason Xu
// Date:10/19/18
public class WinnerWinner
{
   public static void main(String[] args)
   {
      Board b = null;
      b = new Board(3,4,"W-S-------X-"); 
      b.display();
      System.out.println("Shortest path is " + b.win());  //2
      
      b = new Board(4,3,"S-W-----X-W-"); 
      b.display();
      System.out.println("Shortest path is " + b.win());  //4
      
      b = new Board(3,4,"X-WS--W-W---"); 
      b.display();
      System.out.println("Shortest path is " + b.win());  //7
      
      b = new Board(3,5,"W--WW-X----SWWW"); 
      b.display();
      System.out.println("Shortest path is " + b.win());  //1
      
      b = new Board(3,3,"-SW-W-W-X");     //no path exists
      b.display();
      System.out.println("Shortest path is " + b.win());  //-1
      
      b = new Board(5,7,"-W------W-W-WX--S----W----W-W--W---");     //Example Board 1
      b.display();
      System.out.println("Shortest path is " + b.win());  //5
      
      b = new Board(4,4,"-WX--W-W-WW-S---");     //Example Board -1
      b.display();
      System.out.println("Shortest path is " + b.win());  //5
  
      //what other test cases should you test?
      
   }
}

class Board
{
   private char[][] board;  
   private int maxPath;

   public Board(int rows, int cols, String line)  
   {
      int count=0;
      maxPath=rows*cols;
      board=new char[rows][cols];
      for(int x=0;x<board.length;x++){
         for(int y=0;y<board[0].length;y++){
            board[x][y]=line.charAt(count);
            count++;
         }
      }
   
   
   
   }

	/**	returns the length of the longest possible path in the Board   */
   public int getMaxPath()		
   {  
      return maxPath; 
   }	
   
   public void display()
   {
      if(board==null) 
         return;
      System.out.println();
      for(int a = 0; a<board.length; a++)
      {
         for(int b = 0; b<board[0].length; b++)
         {
            System.out.print(board[a][b]);
         }
         System.out.println();
      }
   }

   /**	
    *  calculates and returns the shortest path from S to X, if it exists   
    *  @param r is the row of "S"
    *  @param c is the column of "S"
    */
   public int check(int r, int c)
   {	
      if(r<0||c<0||r>=board.length||c>=board[0].length||board[r][c]=='W'||board[r][c]=='.'){
         return maxPath;
      }
      if(board[r][c]=='X'){
         return 0;
      }
      if(board[r][c]=='W'||board[r][c]=='/'){
         return maxPath;
      }
      board[r][c]='/';
      int a1=check(r+1,c);
      int a2=check(r-1,c);
      int a3=check(r,c+1);
      int a4=check(r,c-1);
      int a5=Math.min(a1,a2);
      int a6=Math.min(a5,a3);
      int a7=Math.min(a6,a4);
      board[r][c]='-';
      return a7+1;

   
   }	
          
   /**	
    *  precondition:  S and X exist in board
    *  postcondition:  returns either the length of the path
    *                  from S to X, or -1, if no path exists.    
    */
   public int win()
   {
      int val=0;
      for(int x=0;x<board.length;x++){
         for(int y=0;y<board[0].length;y++){
            if(board[x][y]=='S'){
               val=check(x,y);
            }
         }
      }
      if(val==maxPath+1){
         return -1;
      }
      return val;
   }
}





/************************ output ************
 W-S-
 ----
 --X-
 Shortest path is 2
 
 S-W
 ---
 --X
 -W-
 Shortest path is 4
 
 X-WS
 --W-
 W---
 Shortest path is 7
 
 W--WW
 -X---
 -SWWW
 Shortest path is 1
 
 -SW
 -W-
 W-X
 Shortest path is -1
 
 -W-----
 -W-W-WX
 --S----
 W----W-
 W--W---
 Shortest path is 5
 
 -WX-
 -W-W
 -WW-
 S---
 Shortest path is -1 
 ***************************************/