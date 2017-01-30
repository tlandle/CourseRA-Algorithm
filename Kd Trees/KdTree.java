/*----------------------------------------------------------------
 *  Author:        Tyler Landle
 *  Written:       01/03/2016
 *  Last updated:  01/03/2016
 *
 *  Compilation:   javac PointSet.java
 *  Execution:     java PointSet
 *  
 *  
 *
 *  % java PointSet
 * 
 *
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
    
    private Node root;
    private SET<Point2D> pointsInRect;
    private int size;
    
    
   
    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        
        public Node(Point2D p, RectHV rect, Node lb, Node rt)
        {
            this.p = p;
            this.rect = rect;
            this.lb = lb;
            this.rt = rt;
        }
    }
    
   public KdTree()                               // construct an empty set of points 
   {
       root = null;
   }
   
   public boolean isEmpty()                      // is the set empty? 
   {
       return (size == 0);
   }
   
   public int size()                         // number of points in the set 
   {
       return size;
   }
   
   public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
   {
       if(p == null)
       {
           throw new IllegalArgumentException();
       }
       root = insert(root, point);
   }
   
   private Node insert(Node x, Point2D p)
   {
       if(x == null)
       {
           return new Node(p, 
       }
       
   }
   
   public boolean contains(Point2D p)            // does the set contain point p? 
   {
       
   }
   
   public void draw()                         // draw all points to standard draw 
   {
   }
   
   public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle 
   {
       SET<Point2D> pointsIter = new SET<Point2D>();
       findNodesInRange();
       return pointsIter;
   }
   
   public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty 
   {
   }
   

   public static void main(String[] args)                  // unit testing of the methods (optional) 
}