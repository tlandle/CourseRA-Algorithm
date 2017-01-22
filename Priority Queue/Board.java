/*----------------------------------------------------------------
 *  Author:        Tyler Landle
 *  Written:       01/03/2016
 *  Last updated:  01/03/2016
 *
 *  Compilation:   javac Board.java
 *  Execution:     java Board
 *  
 *  
 *
 *  % java Point
 * 
 *
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdOut;
import java.util.Stack;

public class Board {
    private int[][] blocks;
    
    public Board(int[][] blocks)           // construct a board from an n-by-n array of blocks
    {
        this.blocks = new int[blocks.length][blocks.length];
        for (int i = 0; i<blocks.length; i++)
        {
            for(int j=0; j<blocks.length; j++)
            {
                this.blocks[i][j] = blocks[i][j];
            }
        }
    }
                                           // (where blocks[i][j] = block in row i, column j)
    public int dimension()                 // board dimension n
    {
        return blocks.length;
    }
    
    public int hamming()                   // number of blocks out of place
    {
        int hamming = 0;
        for( int rows = 0; rows < blocks.length; rows++)
        {
            for(int cols = 0; cols< blocks.length; cols++)
            {
                if(blocks[rows][cols] != 0 && blocks[rows][cols] != getIntendedBlockValue(rows,cols))
                {
                    hamming+=1;
                }
            }
        }
        return hamming;
    }
    
    
    private int getIntendedBlockValue(int row, int col)
    {
            return ((row*blocks.length) + col + 1);
    }
            
            
    
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int manhattan = 0;
        for( int rows = 0; rows < blocks.length; rows++)
        {
            for(int cols = 0; cols < blocks.length; cols++)
            {
                if(blocks[rows][cols] != 0)
                { 
                    int intendedRow = (blocks[rows][cols] - 1)/blocks.length;
                    int intendedCol = (blocks[rows][cols] - 1) - (intendedRow * blocks.length);
                    manhattan += (Math.abs(intendedRow - rows));
                    manhattan += (Math.abs(intendedCol - cols));
                }
            }
        }
        return manhattan;
           
    }
    
    public boolean isGoal()                // is this board the goal board?
    {
        if(hamming() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
        int [][] twinBoard = new int[blocks.length][blocks.length];
        for (int i = 0; i<blocks.length; i++)
        {
            for(int j=0; j<blocks[i].length; j++)
            {
                twinBoard[i][j] = blocks[i][j];
            }
        }
        
        for(int row = 0; row < blocks.length ; row++)
        {
            for( int col = 0; col < blocks.length - 1; col++)
            {
                if((twinBoard[row][col]  != 0) && (twinBoard[row][col+1] != 0))
                {
                    int temp = twinBoard[row][col];
                    twinBoard[row][col] = twinBoard[row][col + 1];
                    twinBoard[row][col + 1] = temp;
                    return new Board(twinBoard);
                }
            }
        }
        return null;
    }
    
    public boolean equals(Object y)        // does this board equal y?
    {
        if( y == null)
        {
            return false;
        }
        else if(this == y)
        {
            return true;
        }
        else if(y.getClass() != this.getClass())
        {
            return false;
        }
        else
        {
            Board temp = (Board) y;
            if(this.dimension() != temp.dimension())
            {
                return false;
            }
            
            for(int rows = 0; rows < blocks.length; rows++)
            {
                for(int cols = 0; cols < blocks.length; cols++)
                {
                    if(this.blocks[rows][cols] != temp.blocks[rows][cols])
                    {
                        return false;
                    }
                }
            }
            return true;
        }
    }
    
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        int emptySpaceRow = 0;
        int emptySpaceCol = 0;
        
        // Find empty block space row and column
        for(int row = 0; row < blocks.length; row++)
        {
            for(int col = 0; col < blocks.length; col++)
            {
                if(blocks[row][col] == 0)
                { 
                    emptySpaceRow = row;
                    emptySpaceCol = col;
                }
            }
        }
        
        Stack<Board> neighborStack= new Stack<Board>();
        final int[][] neighborShifts= 
        {
            {0, -1},
            {-1, 0},
            {0, 1},
            {1, 0}
        };
        for( int i = 0; i < neighborShifts.length; i++)
        {
            int row = emptySpaceRow + neighborShifts[i][0];
            int col = emptySpaceCol + neighborShifts[i][1];
            if(row >= 0 && row < blocks.length && col >= 0 && col< blocks.length)
            {
                int [][] temp = new int[blocks.length][blocks.length];
                for (int j = 0; j<blocks.length; j++)
                {
                    for(int k=0; k<blocks.length; k++)
                    {
                        temp[j][k] = blocks[j][k];
                    }
                }
                temp[row][col]=blocks[emptySpaceRow][emptySpaceCol];
                temp[emptySpaceRow][emptySpaceCol]=blocks[row][col];
                
                neighborStack.push(new Board(temp));
            }
            
        }
        return neighborStack;
    }
    
    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuilder s = new StringBuilder(dimension() + " \n ");
        
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                s.append(blocks[row][col]);
                s.append(" ");
            }
            
            s.append("\n ");
        }
        
        return s.toString();
    }
    
    
    public static void main(String[] args) // unit tests (not graded)
    {
        int[][] input = new int[][]{{1, 2, 3, 4}, {5, 6, 0, 8}, {9, 10, 11, 12}, {13, 14, 15, 7}};
     Board testBoard = new Board(input);
     
     StdOut.println(testBoard.toString());
     
     Iterable<Board> result = testBoard.neighbors();
     
     for (Board b : result) {
      StdOut.println(b.toString());
     }
    } 
}