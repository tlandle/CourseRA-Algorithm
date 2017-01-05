/*----------------------------------------------------------------
 *  Author:        Tyler Landle
 *  Written:       01/03/2016
 *  Last updated:  01/03/2016
 *
 *  Compilation:   javac RandomizedQueue.java
 *  Execution:     java RandomizedQueue
 *  
 *  
 *
 *  % java RandomizedQueue
 * 
 *
 *----------------------------------------------------------------*/

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] array;
    private int N;
    
    
    public RandomizedQueue()
    {
        array = (Item[]) new Object[1];
    }
    
    public boolean isEmpty()
    {
        return (N == 0);
    }
    
    public int size()
    {
        return N;
    }
    
    private void resize(int capacity)
    {
       Item[] temp = (Item[]) new Object[capacity];
       for(int i = 0; i < size(); i++)
       {
           temp[i] = array[i];
       }
       array = temp;  
    }
    
    public void enqueue(Item item)
    {
        if(item == null)
        {
            throw new NullPointerException();
        }
        
        if( N == array.length)
        {
            resize(array.length * 2);
        }
        array[N++] = item;
    }
    
    public Item dequeue()
    {
        if(isEmpty())
        {
            throw new NoSuchElementException();
        }
        
        int randomIndex = randomIndexGenerator(N);
        Item randomItem = array[randomIndex];
        array[randomIndex] = array[N-1];
        array[N-1] = null;
        --N;
        if(N > 0 && N == (array.length/4))
        {
            resize(array.length/2);
        }
        return randomItem;
    }
    
    private int randomIndexGenerator(int numberOfIndexes)
    {
        int index = StdRandom.uniform(numberOfIndexes);
        return index;
    }
    
    public Item sample()
    {
        if(isEmpty())
        {
            throw new NoSuchElementException();
        }
        
        int randomIndex=randomIndexGenerator(N);
        Item randomItem = array[randomIndex];
        return randomItem;
    }
    
    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item>{
        private Item[] items;
        private int j;
        
        public RandomizedQueueIterator()
        {
            j = N;
            items = (Item[]) new Object[N];
            for(int i = 0; i < N; i++)
            {
                items[i] = array[i];
            }
        }
        
        public boolean hasNext()
        {
            return (j != 0);
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
        public Item next()
        {
            if(!hasNext())
            {
                throw new NoSuchElementException();
            }
            
            int randomIndex = randomIndexGenerator(j);
            Item randomItem = items[randomIndex];
            items[randomIndex] = items[j-1];
            items[j-1]=null;
            j--;
            return randomItem;
        }
    }
    
    public static void main(String[] args)
    {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        rq.enqueue(0);
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);
        rq.enqueue(7);
        rq.enqueue(8);
        rq.enqueue(9);



        System.out.println("items: " + rq.N);

        System.out.println(rq.toString());

        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());

        //System.out.println(rq.to`String());
        System.out.println("items: " + rq.N);

        Iterator<Integer> it1 = rq.iterator();
        Iterator<Integer> it2 = rq.iterator();

        while (it1.hasNext()) {
            System.out.println(it1.next());
        }
        System.out.println("\n");
        while (it2.hasNext()) {
            System.out.print(it2.next());
        }
    }
    
}