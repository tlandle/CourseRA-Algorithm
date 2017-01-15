/*----------------------------------------------------------------
 *  Author:        Tyler Landle
 *  Written:       01/03/2016
 *  Last updated:  01/03/2016
 *
 *  Compilation:   javac BruteCollinearPoints.java
 *  Execution:     java BruteCollinearPoints
 *  
 *  
 *
 *  % java BruteCollinearPoints
 * 
 *
 *----------------------------------------------------------------*/

import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segments;
    
    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
        int segmentIndex = 0;
        
        // Checks for null array
        if( points == null)
        {
            throw new NullPointerException();
        }
        
        segments = new LineSegment[points.length];
        Arrays.sort(points, 0, (points.length -1));
        
        // Checks for any null points
        for(int i = 0; i < points.length ; i++)
        { 
            if(points[i] == null)
            {
                throw new NullPointerException();
            }
        }
        
        // Checks for duplicate points
        for (int i = 0; i < points.length ; i++)
        {
            for (int j = i+1; j < points.length - 1; j++)
            {
                if(points[i].compareTo(points[j] == 0))
                {
                    throw new IllegalArgumentException();
                }
            }
        }
        
        
    }
    
   public int numberOfSegments()        // the number of line segments
   {
       return segments.length;
   }
   
   public LineSegment[] segments()                // the line segments
   {
       
   }
}