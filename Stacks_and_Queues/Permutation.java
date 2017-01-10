/*----------------------------------------------------------------
 *  Author:        Tyler Landle
 *  Written:       01/03/2016
 *  Last updated:  01/03/2016
 *
 *  Compilation:   javac Permutation.java
 *  Execution:     java Permutation k < distinct.txt
 *  
 *  This program takes in a file from standard input and a number k
 *  from the command line, and inputs the strings that are in the file.
 *  It outputs k random string outputs.
 *
 *  % java Permutation 3 < distinct.txt
 * 
 *
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdIn;

// Class that takes a file from standard input, and number k items from the 
// command line, and outputs k strings at random from the input file.
public class Permutation {
    public static void main(String[] args)
    {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue(StdIn.readString());
        while(!StdIn.isEmpty())
        {
            rq.enqueue(StdIn.readString());
        }
        
        for(int i=0; i <k ; i++)
        {
        System.out.println(rq.dequeue());
        }
        
    }
}