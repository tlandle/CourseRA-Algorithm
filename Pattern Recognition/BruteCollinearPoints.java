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

public class BruteCollinearPoints {
    private LineSegment[] segments;
    
    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
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
        
        for (int i = 0; i < points.length ; i++)
        {
            for (int j = i + 1; j < points.length -1; j++)
            {
                for (int k = j + 1; j < points.length -2 ; k++)
                {
                    for (int m = 0; m < points.length -3 ; m++)
                    { 
                        if(points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) &&
                           points[i].slopeTo(points[j]) == points[i].slopeTo(points[m]))
                        {
                            segments[segmentIndex++] = new LineSegment(points[i], points[m]);
                        }
                    }
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
        return Arrays.copyOf(segments, numberOfSegments());
    }
}