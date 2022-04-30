//Name:Jason Xu 
//Date:10/5/28
//
// 
//
// License Information:
//   This class is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation.
//
//   This class is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.

import edu.kzoo.grid.BoundedGrid;
import edu.kzoo.grid.Grid;
import edu.kzoo.grid.Location;
import edu.kzoo.grid.display.GridDisplay;
import java.util.*;

/**
 *  Environment-Based Applications:<br>
 *
 *    The NQueens class implements the N Queens problem.
 *
 *  @author Your Name (based on a template provided by Alyce Brady)
 *  @version 1 September 2002
 **/

public class NQueens
{
    // Instance Variables: Encapsulated data for EACH NQueens problem
    private Grid board;
    private GridDisplay display;

  // constructor

    /** Constructs an object that solves the N Queens Problem.
     *    @param n the number of queens to be placed on an
     *              <code>n</code> x <code>n</code> board
     *    @param d an object that knows how to display an 
     *              <code>n</code> x <code>n</code> board and
     *              the queens on it
     **/
    public NQueens(int n, GridDisplay d)
    {
        Scanner in=new Scanner(System.in);
        System.out.println("Size of board:");
        int input =Integer.parseInt(in.nextLine());
        board = new BoundedGrid(input, input);
        display = d;
        display.setGrid(board);
        display.showGrid();
    }

  // methods

    /** Returns the number of queens to be placed on the board. **/
    public int numQueens()
    {
        return board.numRows();   // replace this with something more useful
    }

    /** Solves (or attempts to solve) the N Queens Problem. **/
    public boolean solve()
    {
        if(placeQueen(0)){
         display.showGrid();
         return true;
        }
        else{
        System.out.println("Solution not found");
        return false;
        }
    }

    /** Attempts to place the <code>q</code>th queen on the board.
     *  (Precondition: <code>0 <= q < numQueens()</code>)
     *    @param q index of next queen to place
     **/
    private boolean placeQueen(int q)
    {
        // Queen q is placed in row q.  The only question is
        // which column she will be in.  Try them in turn.
        // Whenever we find a column that could work, put her
        // there and see if we can place the rest of the queens.
        if(q==numQueens()){
         return true;
        }
        int x;
        for(x=0;x<numQueens();x++){
           if(locationIsOK(new Location(q,x))){
              addQueen(new Location(q,x));
              display.showGrid();
              if(placeQueen(q+1)){
                 return true;
              }
              else{
                 removeQueen(new Location(q,x));
                 display.showGrid();
              }
              //break;
           }
        }
        removeQueen(new Location(q,x));
        display.showGrid();
        return false;
    }

    /** Determines whether a queen can be placed at the specified
     *  location.
     *    @param loc  the location to test
     **/
    private boolean locationIsOK(Location loc)
    {
        // Verify that another queen can't attack this location.
        // (Only queens in previous rows have been placed.)
        int y=loc.col();
        int diff=loc.row()-loc.col();
        int diff2=loc.col()-loc.row();
        int sum=loc.col()+loc.row();
        for(int x=loc.row();x>=0;x--){
           if(board.objectAt(new Location(x,y))!=null||board.objectAt(new Location(x+diff,x))!=null||board.objectAt(new Location(x,x+diff2))!=null||board.objectAt(new Location(sum-x,x))!=null||board.objectAt(new Location(x,sum-x))!=null){
              return false;
           }
        }
        return true;
    }

    /** Adds a queen to the specified location.
     *    @param loc  the location where the queen should be placed
     **/
    private void addQueen(Location loc)
    {
        new Queen(board, loc);      // queens add themselves to the board
    }

    /** Removes a queen from the specified location.
     *    @param loc  the location where the queen should be removed
     **/
    private void removeQueen(Location loc)
    {
        board.remove(loc);
    }

}
