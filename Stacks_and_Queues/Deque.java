/*----------------------------------------------------------------
 *  Author:        Tyler Landle
 *  Written:       01/03/2016
 *  Last updated:  01/03/2016
 *
 *  Compilation:   javac Deque.java
 *  Execution:     java Deque
 *  
 *  This program runs a deque, or double queue. It enables 
 *  generic addition of items(any data type) to a queue which you can
 *  add and remove items from the front or end of the queue.
 *
 *  % java Deque
 * 
 *
 *----------------------------------------------------------------*/

import java.util.Iterator;
import java.util.NoSuchElementException;



public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int listSize;
    
    private class Node
    {
        Item item;
        Node next;
        Node previous;
    }
    
    public Deque()
    {
        listSize = 0;
    }
    
    public boolean isEmpty()
    {
        return (listSize == 0);
    }
    
    public int size()
    {
        return listSize;
    }
    
    public void addFirst(Item item)
    {
        if(item == null)
        {
            throw new NullPointerException();
        }
        
        if(isEmpty())
        {
            first = new Node();
            first.item = item;
            last = first;
        }
        else
        {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;  
            oldfirst.previous = first; 
        }
        listSize++;
    }
    
    public void addLast(Item item)
    {
        if(item == null)
        {
            throw new NullPointerException();
        }
        
        if(isEmpty())
        {
            last = new Node();
            last.item= item;
            first = last;
        }
        else
        {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.previous = oldlast;
            oldlast.next = last;
        }
        listSize++;
    }
    
    public Item removeFirst()
    {
        if(isEmpty())
        {
            throw new NoSuchElementException();
        }
        
        Item item = first.item;
        if( size() == 1)
        {
            first= null;
            last = null;
        }
        else
        {
        first = first.next;
        first.previous = null;
        }
        
        listSize--;
        return item;
    }
    
    public Item removeLast()
    {
        if(isEmpty())
        {
            throw new NoSuchElementException();
        }
        
        Item item = last.item;
        if( size() == 1 )
        {
            first= null;
            last = null;
        }
        else
        {
        last = last.previous;
        last.next = null;
        }
        
        listSize--;
        return item;
    }
    
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item>{
        private Node current = first;
        
        public boolean hasNext()
        {
            return current != null;
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
        public Item next()
        {
            if(current == null)
            {
                throw new NoSuchElementException();
            }
            
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    public static void main(String[] args)
    {
        Deque<Integer> dq = new Deque<Integer>();

        System.out.println("size: " + dq.size());

        dq.addFirst(1);
        dq.addFirst(2);
        dq.addFirst(3);
        dq.addFirst(4);
        dq.addFirst(5);
        
        System.out.println("size: " + dq.size());
        
        dq.removeLast();

        dq.removeFirst();
        dq.removeFirst();
        System.out.println("size: " + dq.size());

        dq.removeLast();
        dq.removeLast();

        dq.addFirst(1);
        dq.addLast(2);

        dq.addFirst(3);
        dq.addLast(4);

        System.out.println("size: " + dq.size());

        Iterator<Integer> itr = dq.iterator();

        //System.out.println(itr.);
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        //System.out.println(itr.next());
    }
    
}