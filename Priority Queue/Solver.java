import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Stack;

public class Solver {
    private MinPQ<Node> pq;
    private MinPQ<Node> pqTwin;
    private boolean solveable;
    private boolean twinSolveable;
    private Stack<Board> solution;
    private int moves;
    
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        solveable=false;
        pq = new MinPQ<Node>();
        pqTwin= new MinPQ<Node>();
        solution = new Stack<Board>();
        moves = 0;
        
        Node initialNode = new Node(initial, null);
        pq.insert(initialNode);
        
        Node initialTwinNode = new Node(initial.twin(), null);
        pqTwin.insert(initialTwinNode);
        
        while(!solveable && !twinSolveable)
        {
            Node node = pq.delMin();
            solution.push(node.board);
            
            Node twinNode = pqTwin.delMin();
            
            if(node.board.isGoal())
            {
                solveable = true;
                moves = node.moves;
            }
            else if(twinNode.board.isGoal())
            {
                twinSolveable = true;
                moves = -1;
                solution = null;
            }
            else
            {
                addNeighbors(node, pq);
                addNeighbors(twinNode, pqTwin);
            }
            
        }
        
    }
    
    private void addNeighbors(Node node, MinPQ<Node> pq)
    {
        for(Board board: node.board.neighbors())
        {
            if(node.previousNode == null || (!board.equals(node.previousNode.board)))
            {
                pq.insert(new Node(board, node));
            }
        }
    }
                                       
    
    private class Node implements Comparable<Node>{
        private Board board;
        private int moves;
        private Node previousNode;
        private int manhattan;
        private int priority;
        
        public Node(Board board, Node previousNode)
        {
            this.board = board;
            this.previousNode = previousNode;
            if (this.previousNode == null) 
            {
                moves = 0;
            }
            else 
            {
                moves = previousNode.moves + 1;
            }
            this.manhattan = this.board.manhattan();
            this.priority = this.moves + this.manhattan;
            assert( (previousNode == null) || (this.priority >= previousNode.priority) );
        }
        
        public int compareTo(Node that)
        {
            if(this.priority > that.priority)
            {
                return 1;
            }
            else if(this.priority < that.priority)
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
              
    }
    
    public boolean isSolvable()            // is the initial board solvable?
    {
        return solveable;
    }
    
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        return moves;
    }
    
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        return solution;
    }
    
    
    public static void main(String[] args) // solve a slider puzzle (given below)
    {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        // solve the puzzle
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}