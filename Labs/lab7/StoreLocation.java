/**
 * Lab 7
 * StoreLocation takes a set of locations given in ints and calculates the optimal postion for a new building.
 *
 * @author Andy Nguyen
 */

import java.io.*;
import java.util.*;

public class StoreLocation {
    private int[ ] storeLocations;

    /**
     * Main method
     */
    public static void main(String[ ] args) throws IOException
    {
        String fileName;
        StoreLocation s = null;
        if( args.length >= 1 ) {
            fileName = args[0];
            ArrayList<int[]> testLocations = StoreLocation.readFile( fileName );

            for( int[] i : testLocations ) {
                s = new StoreLocation( i );
                System.out.println( s );
                System.out.println("--- Midpoint ---");
                System.out.println("optimal position:  " + s.midpoint());
                System.out.println("sum of distances customers must travel: " + 
                                                 s.totalTravelDistance( s.midpoint()));
                System.out.println("--- Median ---");
                System.out.println("optimal position:  " + s.median());
                System.out.println("sum of distances customers must travel: " + 
                                                 s.totalTravelDistance( s.median()));
                System.out.println("--- Average ---");
                System.out.println("optimal position:  " + s.average());
                System.out.println("sum of distances customers must travel: " + 
                                                 s.totalTravelDistance( s.average()));
                System.out.println();
            }


        }
        else{
            System.err.println( "Usage: StoreLocation test-file" );
            System.exit( 1 );
        }




        /*
        int[] test = { 120, 70, 170, 200, 50};
        StoreLocation s = new StoreLocation( test );

        System.out.println( s );

        System.out.println( "Midpoint: " + s.midpoint() );
        System.out.println( "Median: " + s.median() );
        System.out.println( "Average: " + s.average() );
        */
    }

    public String toString() {
        String str = "";
        for ( int i = 0; i < storeLocations.length; i++ ) {
            str += storeLocations[i] + " ";
        }
        return str;
    }

    /**
     * Default constructor
     */
    public StoreLocation() {
        storeLocations = null;
    }

    /**
     * Constructor that accepts an array of ints
     *
     * @param      distances      array of building distances from state street      
     */
    public StoreLocation( int[] distances) {
        storeLocations = distances;
        sort();
    }


    /**
     * @return the midpoint of the smallest and largest distance
     */
    public int midpoint() {
        if( storeLocations == null || storeLocations.length == 0 ) {
            return -1;
        }

            return ( storeLocations[0] + storeLocations[storeLocations.length-1] ) / 2;
    }

    /**
     * Get the middle distance from the sorted array, storeDisances.
     * If there are an even number of elements, then the median will be 
     * the midpoint between the middle 2 elements.
     *
     * @return the median of the distances
     */
    public int median() {

        if( storeLocations == null || storeLocations.length == 0 ) {
            return -1;
        }

        int length = storeLocations.length;
        if( length-- % 2 == 0 ) { // even number of stores
            return  ( storeLocations[ length/2 ] + storeLocations[ length/2+1 ] ) / 2 ;
        }
        else { // odd number of stores
            return storeLocations[ length/2  ];
        }
    }

    /**
     * @return the average of all the distances in storeLocations
     */
    public int average( ) {
        if( storeLocations == null || storeLocations.length == 0 ) {
            return -1;
        }

        int total = 0;
        for( int dist : storeLocations ) {
            total += dist;
        }
        return total / storeLocations.length;
    }

    /**
     * Sort the storeLocations array from smallest to largest.
     */
    public void sort() {
        if( storeLocations != null || storeLocations.length > 1 ) {
            mergesort( 0, storeLocations.length - 1 );
        }
    }

    /**
     * Perform a merge sort on storeLocations array.
     * This is done by recursively splitting the array in half
     * and then merging the sorted subarrays together. 
     * The subarrays are managed and created by 
     *  manipulating the index of storeLocations.
     *
     * @param      start      start of the array to sort
     * @param       end       end of the array to sort
     */
    public void mergesort( int start, int end )
    {
         int mid;

         if( start < end )
         {
              mid = ( start + end ) / 2;
              mergesort( start, mid );
              mergesort( mid + 1,  end );
              merge( start, mid, end );
         }
    }


    /**
     * Merge two sub arrays.
     * 
     * @param      start      start of the first sub array
     * @param      mid       end of first subarray ( where the array was split )
     * @param      end       end of 2nd subarray  
     */
    public void merge( int start, int mid, int end )
    {      
        int merged[] = new int[ end + 1 ]; // array containing merged sub arrays
        int leftStart, leftEnd, rightStart, rightEnd, i, k;

        i = start; 
        // start and end index of the "left" sub array
        leftStart = start;
        leftEnd = mid;

        // start and end index of the "right" sub array
        rightStart = mid + 1;
        rightEnd = end;

        // compare first element in left array with first element in right array
        // the smaller element is added to merged repeat until either array 
        // becomes "empty"
        while( ( leftStart <= leftEnd ) && ( rightStart <= rightEnd ) ) {
            if( storeLocations[ leftStart ] <= storeLocations[ rightStart ] ) {
                merged[ i++ ] = storeLocations[ leftStart++ ];
            }
            else
            {
                merged[ i++ ] = storeLocations[ rightStart++ ];
            }
        }

        // add any remaining elements to merged
         while( leftStart <= leftEnd ) {
            merged[ i++ ] = storeLocations[ leftStart++ ];

         }
        while( rightStart <= rightEnd ) {
            merged[ i++ ] = storeLocations[ rightStart++ ];

         }
         
         // copy elements of merged to storeLocations
         for( k = start; k <= end; ++k ) { 
            storeLocations[ k ] = merged[ k ];
        }
    }

    /**
     * Calculate the sum of distances customers must travel from each location in 
     * storeLocations to the given location
     * 
     * @param       loc      destination
     * @return the sum of distances traveled from locations in storeLocations  to
     * given location
     */
    public int totalTravelDistance( int loc ) {
        if( storeLocations == null) {
            return -1;
        }
        int totalDist = 0;
        for( int i : storeLocations ) {
            totalDist += Math.abs( i - loc );
        }
        return totalDist;
    }


    /**
     * Read input file for locations
     *
     * @param      fileName      name of the input file
     * @return arraylist of arrays of locations
     */
    public static ArrayList<int[]> readFile( String fileName ) throws IOException, NumberFormatException {
        String[] strLocations = null; // set of locations as a string
        int[] intLocations = null; // set of locations as a number
        ArrayList<int[]> allLocations = new ArrayList<int[]>(); // all sets of locations
        String line; 
        BufferedReader br = null;

        try{
            br =  new BufferedReader ( new FileReader( fileName ) ) ; 
            while ( (line = br.readLine()) != null ) {
                line = line.trim().replaceAll("\\s{2,}", " "); // remove excess spaces
                strLocations = line.split(" "); // split the line into an array of strings

                intLocations = new int[ strLocations.length ]; 

                // convert the array of string locations to array of int locations
                for( int i = 0; i < strLocations.length; ++i ) {
                    intLocations[ i ] = Integer.parseInt( strLocations[ i ] );
                }
                for( int i : intLocations ) {
                   // System.out.print( i + " ");
                }
                allLocations.add( intLocations ); 
              //  System.out.println();
            }
        }
        catch( IOException e ) {
            System.err.println( "Could not open file." );
            System.exit( -1 );
        }
        catch( NumberFormatException e ){
            System.err.println( "Line contains invalid number." );
            System.exit( -1 );
        }
        finally {
            if ( br != null ) {
                br.close(); // close the file
            }
        }


        return allLocations;
    }
}



/*
 * StoreLocation.java
 * 
 * Version: 
 *     $Id: StoreLocation.java,v 1.9 2013/10/16 03:49:16 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: StoreLocation.java,v $
 *     Revision 1.9  2013/10/16 03:49:16  andy
 *     fixed bug with median method
 *     formatted test output
 *
 *     Revision 1.8  2013/10/16 03:09:05  andy
 *     Implemented readfile method
 *
 *     Revision 1.7  2013/10/16 02:23:55  andy
 *     fixed merge method
 *
 *     Revision 1.6  2013/10/16 02:22:40  andy
 *     Test statements for mergesort
 *
 *     Revision 1.5  2013/10/16 01:23:46  andy
 *     Implemented mergesort
 *
 *     Revision 1.4  2013/10/15 22:24:52  andy
 *     Added statements in main method to test midpoint, median, average
 *
 *     Revision 1.3  2013/10/15 20:49:50  andy
 *     Implemented average and midpoint methods.
 *
 *     Revision 1.2  2013/10/14 20:52:19  andy
 *     Skeleton
 *
 *
 */