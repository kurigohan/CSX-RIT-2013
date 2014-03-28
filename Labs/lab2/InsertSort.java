/*
 * InsertSort.java
 *
 * Version:
 *    $Id: InsertSort.java,v 1.3 2000/11/28 13:33:25 cs2 Exp agn3691 $
 *
 * Revisions:
 *    $Log: InsertSort.java,v $
 *    Revision 1.3  2000/11/28 13:33:25  cs2
 *    Defined symbolic constants for the number of
 *    numbers to sort (ARRAY_SIZE) and for the maximum
 *    random number that will be generated (MAX_NUMBER)
 *
 *    Revision 1.2  2000/11/28 13:31:36  cs2
 *    Added import statement for Random class
 *
 *    Revision 1.1  2000/11/28 13:31:10  cs2
 *    Initial revision
 *
 */

import java.util.Random;

/**
 * Sort an array using an insertion sort.
 */

public class InsertSort {

    /**
     * The main method.
     *
     * @param args[] the command line arguments
     */

    public static void main( String args[] ) {
        final int ARRAY_SIZE = 10;
        final int MAX_NUMBER = 100;

	int data[] = new int[ ARRAY_SIZE ];
        int tmp = 0;
        Random randomNumbers = new Random();

	// Sort ARRAY_SIZE random numbers between 0 and MAX_NUMBER

	for ( int i = 0; i < data.length; i++ ) {

            // Add the newest number to the end of the array

	    data[ i ] = randomNumbers.nextInt( MAX_NUMBER );

	    // Starting at the bottom of the array, move the last number
            // up until it is in the correct position.

            for ( int j = i - 1; j >= 0 && data[ j + 1 ] < data[ j ]; j-- ) {
                tmp = data[ j ];
                data[ j ] = data[ j + 1];
                data[ j + 1 ] = tmp;
            }
	}

	// Print out the sorted array

	for ( int i = 0; i < data.length; i++ )
	    System.out.println( data[ i ] );
    }
    
} // InsertSort
