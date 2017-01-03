/*----------------------------------------------------------------
 *  Author:        Tyler Landle
 *  Written:       12/28/2016
 *  Last updated:  12/28/2016
 *
 *  Compilation:   javac Percolation.java
 *  Execution:     java Percolation
 *  
 *  Creates a percolation grid and opens it by utilizing an object
 *  array. Can acccurately tell whether a system percolates depending
 *  on the spaces open.
 *  
 *
 *  % java Percolation
 *  Does it percolate? true
 *
 *----------------------------------------------------------------*/


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private WeightedQuickUnionUF objects;
    private boolean[] spaceOpen;
    private int size;
    private int rows;
    private int cols;
    private int rowLength;
    private int topSite;
    private int bottomSite;
    
    // Creates an n-by-n grid with all sites blocked. Rows and Columns saved to
    // private variables. Grid initilialized as a boolean in order to determine
    // whether spaces are open or not.
    public Percolation(int n)
    {
        if(n <= 0)
        {
            throw new IllegalArgumentException("N must be greater than 0");
        }
        size = (n*n);
        rows = n;
        rowLength = n;
        cols = n;
        objects = new WeightedQuickUnionUF(size+2);
        spaceOpen = new boolean[size]; 
        topSite = size;
        bottomSite = size + 1;
    }
    
    // Opens site (row,col) if the site is not open already. First, finds the 
    // object number(index) of the object intended to be opened. After, it will
    // check whether the object is open, and if so it will connect it to 
    // neighboring sites, if those sites are open. Will throw index out of bounds
    // if any of these are out of bounds.
    public void open(int row, int col)
    {
        // Initializing neighboring spaces variables
        int topNeighborNum, bottomNeighborNum, leftNeighborNum, rightNeighborNum;
        
        
        int objectNum = getObjectNumber(row, col);
        
        if (!isOpen(row,col) && objectNum != -1)
        {
            spaceOpen[objectNum] = true;
            if (row == 1)
            {
                objects.union(objectNum, topSite);
            }
            if (row == rows)
            {
                objects.union(objectNum, bottomSite);
            }
            
            topNeighborNum = getObjectNumber(row-1, col);
            bottomNeighborNum = getObjectNumber(row+1, col);
            leftNeighborNum = getObjectNumber(row, col-1);
            rightNeighborNum = getObjectNumber(row, col+1);
            
            if (topNeighborNum != -1 && spaceOpen[topNeighborNum] == true)
            {
                objects.union(objectNum, topNeighborNum);
            }
            
            if (bottomNeighborNum != -1 && spaceOpen[bottomNeighborNum] == true)
            {
                objects.union(objectNum, bottomNeighborNum);
            }
            
            if (leftNeighborNum != -1 && spaceOpen[leftNeighborNum] == true)
            {
                objects.union(objectNum, leftNeighborNum);
            }
            
            if (rightNeighborNum != -1 && spaceOpen[rightNeighborNum] == true)
            {
                objects.union(objectNum, rightNeighborNum);
            }
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }
    
    // Private method to finds object number if it is within the n-by-n grid. 
    // If not, it will return -1.
    private int getObjectNumber(int i, int j)
    {
        if (i > 0 && i <= rows && j > 0 && j <= cols)
        {
            int num = ((i-1)*rowLength) + (j-1);
            return num;
        }
        else
        {
            return -1;
        }
    }
    
    // Checks if the site is open, if the site is on the n-by-n grid.
    public boolean isOpen(int row, int col)
    {
        int n = getObjectNumber(row,col);
        if (n != -1)
        {
            return spaceOpen[n];
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }
    
    // Checks if the site is full by testing if it is connected to the top
    // virtual site object.
    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if (row > 0 && row <= rows && col > 0 && col <= cols)
        {
            int n = getObjectNumber(row, col);
            return objects.connected(topSite, n);
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }
    
    public int numberOfOpenSites()
    {
        int numOpenSites = 0;
        for (int i = 0; i < size; i++)
        {
            if (spaceOpen[i] == true)
            {
                ++numOpenSites;
            }
        }
        return numOpenSites;
    }
    // Checks if the system percolates by checking if the bottom and top sites
    // are connected.
    public boolean percolates()
    {
        return objects.connected(topSite, bottomSite);
    }
    
    // Simple test client that creates a percolating system. Can delete some
    // lines in order to make sure it is checking correctly.
    public static void main(String[] args)
    {
        boolean b;
        Percolation p = new Percolation(4);
        p.open(1, 1);
        p.open(2, 1);
        p.open(2, 2);
        p.open(3, 2);
        p.open(3, 3);
        p.open(4, 3);
        b = p.percolates();
        System.out.println("Does it percolate? " +b);
        int n = p.numberOfOpenSites();
        System.out.println(" Open Sites: " +n);
    }
}