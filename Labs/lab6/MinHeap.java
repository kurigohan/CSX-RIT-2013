/**
 * MinHeap represents a heap data structure, where the parent
 * node is always has less priority or value than the child node.
 * An ArrayList is used to hold the elements of the heap. By manipulating
 * the index we can easily traverse up and down the heap structure.
 *
 * The following formulas are used to find the location of parent and child nodes in the heap
 * parent: (n - 1) / 2
 * left child: 2n + 1
 * right child: 2n + 2
 *
 * where n is the current index
 *
 * @author Andy Nguyen
 */

import java.lang.*;
import java.util.*;
public class MinHeap implements Heap<Comparable> {

    private ArrayList<Comparable> heap; // the arraylist representing the minheap
    private int size; // number of elements in the heap


    /**
     * Main method to test MinHeap. 
     *
     */
    public static void main( String[] args ) {
        MinHeap mh = new MinHeap();

        // instantiate heap and then add elements one by one
        System.out.println("Insert  one by one\n===============");
        mh.insert( 5 );
        mh.insert( 10 );
        mh.insert( 13 );
        mh.insert( 3 );
        mh.insert( 2 );
        mh.insert( 14 );
        mh.insert( 11 );
        mh.insert( 1 );
        System.out.println(mh);

        // instantiate heap with array
        System.out.println("Insert with array\n===============");
        Integer[] test = new Integer[] {1 , 4 ,5, 2, 10, 33, 11, 6, 0};
        MinHeap mh2 = new MinHeap(test);
        System.out.println( mh2 );
        System.out.println("Remove \n===================");

        // remove 5 elements
        for(int i = 0; i < 5; ++i ) {
            System.out.println("\n" + mh2);
            System.out.println("Removed: " + mh2.remove());
            System.out.println(mh2);
        }

        //test insert after remove
        mh2.insert( 1 );
        mh2.insert( 191 );

        //remove all remaining
        for( int i = mh2.getSize() ; i > 0; --i ) {

            System.out.println("\n" + mh2);
            System.out.println("Removed: " + mh2.remove());
            System.out.println(mh2);
        }

        System.out.println( mh2 );
    }

    /**
     * The default constructor for MinHeap.
     */
    public MinHeap() {
        heap = new ArrayList<Comparable>();
        size = 0;
    }


    /**
     * Constructor for MinHeap that accepts an array of Objects.
     */
   public MinHeap ( Comparable[] elements ) {
        heap = new ArrayList<Comparable>();
        size = 0;
        for( Comparable e : elements ) {
            insert( e );
        }
    }

    /**
     * @return the value at the top of the heap
     */
    public Comparable peek() {
        return heap.get( 0 );
    }   

    /**
     * remove and return the value at the top of the heap
     * @return value at top of the heap
     */
    public Comparable remove() {
        // get the root 
        Comparable top = peek();

        if( size > 1 ) {
            // move the last element to the root
            heap.set( 0, heap.get( --size ) );
            // remove the last element from the heap
            heap.remove( size );
            // sift down so all elements satify minheap conditions
            siftDown();
        }
        else if( size == 1 ) {
            heap.set( 0, null );
        }
        else {
                return null;
        }
        return top;
    }
    /**
     * @return size of the heap
     */
    public int getSize() {
        return size;
    }

    /**
     * insert a new value into the heap
     */
    public void insert( Comparable data ) {
       // System.out.println( "Insert: " + data );
        heap.add( data );
        ++size;
        siftUp();
        // System.out.println( toString() );
    }


    /**
     * Sift up the heap to reorder the nodes.
     */
    private void siftUp() throws ClassCastException {
        int childIndex = size - 1;
        int parentIndex = ( childIndex - 1 ) / 2;
        Comparable child = heap.get( childIndex  );
        Comparable parent = heap.get( parentIndex );
        try {
            while( parentIndex >= 0 ) { 
                if( child.compareTo( parent ) < 0 ) {
                    // if child node less than parent, swap them
                    heap.set( childIndex, parent);
                    heap.set( parentIndex, child );
                    childIndex = parentIndex;
                    parentIndex = ( childIndex - 1) / 2;
                    parent = heap.get( parentIndex );
                }
                else {
                    break;
                }
            } // end while
        }
        catch( ClassCastException e ){
            System.err.println( e );
            System.exit( -1 );
        }
    }

    /**
     * Sift down the heap to reorder the nodes.
     */
    public void siftDown() throws ClassCastException {
        int parentIndex = 0;
        int childIndex = parentIndex * 2 + 1;
        Comparable parent = heap.get( parentIndex );
        Comparable leftChild, rightChild, smallerChild;
        try{
            while( childIndex < size ) {

                leftChild = heap.get( childIndex );
                smallerChild = leftChild; // left child is smaller by default

                if( childIndex + 1 < size ) { // check  if there is a right child
                    rightChild = heap.get( childIndex + 1 );

                    // check if left child is greater than right child
                    if( leftChild.compareTo( rightChild ) > 0 ) {
                        smallerChild = rightChild;
                        ++childIndex;
                    }
                }

                if( parent.compareTo( smallerChild ) > 0 ) {
                    swap( parentIndex, childIndex );
                    parentIndex = childIndex;
                    childIndex = parentIndex * 2 + 1;
                }
                else{
                    break;
                }
            } // end while
        } // end try
        catch( ClassCastException e ) {
            System.err.println( e );
            System.exit( -1 );
        }
    }

    /**
     * Swap the nodes at the given indexes.
     * 
     * @param      pIndex      index of the node 
     * @param      cIndex      index of the node to swap with
     */ 
    private void swap( int pIndex, int cIndex ) throws IndexOutOfBoundsException {
        try{
            Comparable temp = heap.get( pIndex );
            heap.set( pIndex, heap.get( cIndex) );
            heap.set( cIndex, temp );
        }
        catch( IndexOutOfBoundsException e ) {
            System.err.println( e );
            System.exit( -1 );
        }
    }


    /**
     * @return the elements in the heap seperated by spaces.
     *
     */
    public String toString() {
        String str = "";
        for( int i = 0; i < size; ++i ) {
            str += heap.get( i ) + " ";
        }
        return str;
    }
}



/*
 * MinHeap.java
 * 
 * Version: 
 *     $Id: MinHeap.java,v 1.8 2013/10/10 22:23:24 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: MinHeap.java,v $
 *     Revision 1.8  2013/10/10 22:23:24  andy
 *     Modified remove method to check heap size before removing an element.
 *
 *     Revision 1.7  2013/10/10 22:04:49  andy
 *     Implemented siftDown method and added a swap method.
 *
 *     Revision 1.6  2013/10/06 06:27:05  andy
 *     Renamed heapify to siftUp
 *     Added siftDown method to be used with remove
 *
 *     Revision 1.5  2013/10/06 05:49:44  andy
 *     Implemented remove method
 *
 *     Revision 1.4  2013/10/06 05:43:30  andy
 *     Implemented constructor for array of elements
 *     Fixed bug in siftUp method - wasn't checking the root element
 *
 *     Revision 1.3  2013/10/06 05:03:36  andy
 *     siftUp method working correctly
 *     using Comparable type for parameter
 *     added main method
 *
 *     Revision 1.2  2013/10/06 03:42:44  andy
 *     implemented insert and siftUp
 *
 *     Revision 1.1  2013/10/03 22:30:14  andy
 *     Initial revision
 *
 */