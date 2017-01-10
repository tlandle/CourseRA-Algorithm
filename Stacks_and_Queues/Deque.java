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
    
    // Nodes to define the first and last item in the linked list.
    // Integer to define the list size so it doesn't need to be
    // computed constantly.
    private Node first;
    private Node last;
    private int listSize;
    
    // Private class to define a node. Contains item, and links to next
    // node and previous node.
    private class Node
    {
        Item item;
        Node next;
        Node previous;
    }
    
    // Constructor for deque, just needs to initialize the size to 0.
    public Deque()
    {
        listSize = 0;
    }
    
    // Returns if the list is empty by checking the list size integer value.
    public boolean isEmpty()
    {
        return (listSize == 0);
    }
    
    // Returns the size by checking list size integer value.
    public int size()
    {
        return listSize;
    }
    
    // Adds an item to the beginning of the list. If there is no valid item, 
    // it throws an exception. If the list is empty, it initializes the list
    // and links the last node to the first node(same node). If not, it creates
    // a new item at the front of the list, and links it to the next item.
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
    
    // Adds an item to the end of the list. If the item does not contain any 
    // information, then it throws an exception. If the list is empty, it creates
    // a new node that serves as the first and last node. If not, it adds an item
    // to the end of the list and links it to the old one and null.
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
    
    // Removes first item from the list. If there are no items in the list, it 
    // throws an exception. If there's only one item in the list, it deletes the
    // item and links the nodes to null.
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
    
    // Removes the last item from the list. If the list is empty, it throws an 
    // exception. If there's only one item in the list, resets the links for the
    // first and last nodes to null.
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
    
    // Creates and iterator generic.
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }
    
    // Class to create a deque iterator that provides iteration methods has next,
    // next, and throws an exception for remove.
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
    
    // Test method creates a deque and goes through it.
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

        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        //System.out.println(itr.next());
    }
    
}