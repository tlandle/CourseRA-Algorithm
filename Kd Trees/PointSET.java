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

public class PointSET {
    private SET<Point2D> set;
    private int size;
    
    
   public PointSET()                               // construct an empty set of points 
   {
       set= new SET<Point2D>();
       size = 0;
   }
   
   public boolean isEmpty()                      // is the set empty? 
   {
       return (size == 0);
   }
   
   public int size()                         // number of points in the set 
   {
       return size;
   }
   
   public              void insert(Point2D p)              // add the point to the set (if it is not already in the set)
   {
       if(set.contains(p))
       {
           return;
       }
       else{
           set.add(p);
           size++;
       }
       
   }
   
   public boolean contains(Point2D p)            // does the set contain point p? 
   {
       if(set.contains(p))
       {
           return true;
       }
       else
       {
           return false;
       }
   }
   
   public void draw()                         // draw all points to standard draw 
   {
       for( Point2D point : set)
       {
           point.draw();
       }
   }
   
   public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle 
   {
       SET<Point2D> rangeIter = new SET<Point2D>();
       for(Point2D point : set)
       {
           if(rect.contains(point))
           {
               rangeIter.add(point);
           }
       }
       return rangeIter;
   }
   
   public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty 
   {
       Point2D nearestPoint = null;
       for (Point2D point : set)
       {
           if(nearestPoint == null)
           {
               nearestPoint = point;
           }
           else
           {
               if(p.distanceTo(point) < p.distanceTo(nearestPoint) && !nearestPoint.equals(p))
               {
                   nearestPoint = point;
               }
           }
       }
       return nearestPoint;
   }

   public static void main(String[] args)                  // unit testing of the methods (optional) 
   {
       PointSET pset = new PointSET();
       Point2D p = new Point2D(0.2, 0.3);
       RectHV rect = new RectHV(0.2, 0.2, 0.6, 0.6);
       pset.insert(p);
       for (int i = 0; i < 1000; i++)
           pset.insert(new Point2D(StdRandom.uniform(), StdRandom.uniform()));
       rect.draw();
       StdDraw.circle(p.x(), p.y(), p.distanceTo(pset.nearest(p)));
       pset.draw();
       StdDraw.show(0);
       StdOut.println("Nearest to " + p.toString() + " = " + pset.nearest(p));
       for (Point2D point : pset.range(rect))
           StdOut.println("In Range: " + point.toString());
   }
}