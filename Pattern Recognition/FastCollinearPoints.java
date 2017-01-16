/*----------------------------------------------------------------
 *  Author:        Tyler Landle
 *  Written:       01/15/2016
 *  Last updated:  01/15/2016
 *
 *  Compilation:   javac FastCollinearPoints.java
 *  Execution:     java FastCollinearPoints
 *  
 *  
 *
 *  % java FastCollinearPoints
 * 
 *
 *----------------------------------------------------------------*/

import java.util.Arrays;
import java.util.ArrayList;


public class FastCollinearPoints {
    private ArrayList<LineSegment> segmentsList = new ArrayList<LineSegment>();
    
    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
        
        Point[] pointsCopy = points.clone();
        
        
        // Checks for duplicate points
        for (int i = 0; i < points.length ; i++)
        {
            for (int j = i+1; j < points.length - 1; j++)
            {
                if(points[i].compareTo(points[j]) == 0)
                {
                    throw new IllegalArgumentException();
                }
            }
        }
        
        // Sort Array, and then search for sequential line segments of 4 or over.
        // After this, add this to the array list of line segments.
        // Some issues here, does not work for longer segments
        Arrays.sort(pointsCopy);
        for( int i = 0; i < pointsCopy.length; i++)
        {
            Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());
            for(int end = 2, p = 0, start = 1; end < pointsCopy.length; end++)
            {
                while(end < pointsCopy.length)
                {
                    if(pointsCopy[p].slopeTo(pointsCopy[start]) == pointsCopy[p].slopeTo(pointsCopy[end]))
                    {
                        end++;
                    }
                    else
                    {
                        break;
                    }
                }
                if (end - start >= 3 && pointsCopy[p].compareTo(pointsCopy[start]) < 0)
                {
                    segmentsList.add(new LineSegment(pointsCopy[p], pointsCopy[end - 1]));
                }
                
                start = end;
                
            }
        }
    }
    
    
   // Returns total number of line segments
   public int numberOfSegments()        // the number of line segments
   {
       return segmentsList.size();
   }
   
   // Returns the line segments that have been added.
   public LineSegment[] segments()                // the line segments
   {
       return segmentsList.toArray(new LineSegment[segmentsList.size()]);
   }
}