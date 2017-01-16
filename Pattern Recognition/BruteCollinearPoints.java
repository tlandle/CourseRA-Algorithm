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
import java.util.ArrayList;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> segmentList = new ArrayList<LineSegment>();
    
    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        // Just creates a copy array to work off of.
        Point[] pointsArray = Arrays.copyOf(points, points.length);
        
        // Checks for duplicate points
        for (int i = 0; i < points.length - 1 ; i++)
        {
            for (int j = i+1; j < points.length; j++)
            {
                if(points[i].compareTo(points[j]) == 0)
                {
                    throw new IllegalArgumentException();
                }
            }
        }
        
        Arrays.sort(pointsArray);
        
        // Searches for all sets of 4 sequential points, and adds them to line
        // segment array list if they all have the same slope.
        for (int i = 0; i < pointsArray.length - 3 ; i++)
        {
            for (int j = i + 1; j < pointsArray.length -2 ; j++)
            {
                for (int k = j + 1; k < pointsArray.length - 1 ; k++)
                {
                    for (int m = k + 1; m < pointsArray.length ; m++)
                    { 
                        if (pointsArray[i].slopeTo(pointsArray[j]) == pointsArray[i].slopeTo(pointsArray[k]) &&
                           pointsArray[i].slopeTo(pointsArray[j]) == pointsArray[i].slopeTo(pointsArray[m]))
                        {
                            segmentList.add(new LineSegment(pointsArray[i], pointsArray[m]));
                        }
                    }
                }
            }
        }   
    }
    
    
    public int numberOfSegments()        // the number of line segments
    {
        return segmentList.size();
    }
    
    
    public LineSegment[] segments()                // the line segments
    {
        return segmentList.toArray(new LineSegment[segmentList.size()]);
    }
}