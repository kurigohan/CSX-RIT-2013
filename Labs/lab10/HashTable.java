/**
 * HashTable takes keys and hashes them to get the index. The key is inserted at that index.
 * Collisions are dealt with with chaining which is implemented using a HashMap.
 *
 * @author Andy Nguyen
 */
import java.util.*;

public class HashTable implements Hash {

    private Htype MODE; // determines which hash function to use
    private Map<String, Integer>[] table; // array of HashMaps that hold key-val pairs
    private int entryCount; // number of elements in the table
    private int chainCount; // number of non-empty chains

    /**
     * Main method used to test methods by passing command arguments
     */
    public static void main( String args[] ) throws NumberFormatException {
        if( args.length > 0 ) {
            try{
                String key = args[ 0 ];
                int size = 100000;
                Htype mode = Htype.SIMPLE; // mode is set to simple by default
                // check for optional size argument
                if( args.length > 1 ) {
                     size = Integer.parseInt( args[1] ); 
                }
                //check for optional mode argument
                if( args.length > 2 ) {
                    if( Integer.parseInt( args[2] ) != 0 ) {
                        mode = Htype.CUSTOM;
                    }
                }

                HashTable test = new HashTable( size, mode );
               // System.out.println( test.simpleHash( key ) ); //test simpleHash
                // test put method
                
                test.put( "yesa", 10 );
                test.put( "seya", 20 );
                test.put( "esya", 30 );
                test.put( "eysa", 20 );
                test.put( "asye", 30 );
                test.put( "ayse", 20 );
                test.put( "ayes", 30 );
                test.put( "WTF", 40 );
                test.put( key );

                System.out.println( "value of key: "  +  test.get( key ) );
                System.out.println( "imbalance: " + test.imbalance() );

            }
            catch( NumberFormatException e ) {
                System.err.println("Invalid argument.");
            }
        }
        else {
            System.err.println("Usage: java HashTable key");
        }
    }

    /**
     * Default constructor. Creates a HashMap of size 100000
     * and sets mode to SIMPLE.
     */
    public HashTable() {
        table = new HashMap[100000];
        MODE = Htype.SIMPLE;
        entryCount = 0;
    }

    /**
     * Constructor that takes size and type
     * @param      size     size of the hashtable
     * @param      type     hashing mode( SIMPLE or CUSTOM )
     */
    public HashTable( int size, Htype  type ) {
        table = new HashMap[ size ];
        MODE = type;
        entryCount = 0;
    }

    /** 
     * Method to put values into the table. It will
     * create the key if it doesn't already exist and set its
     * value to 1. If the key already exists, it will increment
     * the value.
     *
     * @param      key       the string to put into the table
     */
    public void put( String key ) {

        checkLoad(); //check load factor

        int index = getIndex( key );
       // System.out.println( index );
        if( table[ index ] == null ) {
            table[ index ] = new HashMap<String, Integer>();
            ++chainCount;
        }
        if( table[ index ]. containsKey( key ) == false ) {
            ++entryCount;
        }
        table[ index ].put( key, 0 );


       // System.out.println( table[ index ].get( key ) );
    }

    /** 
     * Additional put method, allows you to set
     * the value associated with a key
     *
     * @param      key      the string to put into the table
     * @param      count    int value associated with the key
     */
    public void put( String key, int count ) {

        checkLoad(); //check load factor

        int index = getIndex( key );

        if( table[ index ] == null ) { // create HashMap at index if nothing is there
            table[ index ] = new HashMap<String, Integer>();
            ++chainCount;
        }
        if( table[ index ]. containsKey( key ) == false ) { 
            //increment entryCount if key doesn't exist since a new one will be added
            ++entryCount;
        }
        table[ index ].put( key, count );

        //System.out.println(table[ index ].get(key));
    }

    /**
     * Returns the value associated with 'key' from the table
     * 
     * @param      key      string to get the value of from the table
     * @return the value associated with the key
     */
    public int get( String key ) {
        int index = getIndex( key );
        int value = -1;
        if( table[ index ] != null ) {
            value = table[ index ].get( key );
        }
        return value;

    }


    /**
     * Hashes a key.The each char of the key is converted its ASCII value and then
     * added together.
     * 
     * @param      key      string to hash
     * @return key converted to int value to be used as an index
     */
    public int simpleHash( String key ) {
        int sum = 0;
        // convert each character to its ascii value and add it the asciiSum
        for( int i = 0, len = key.length(); i < len; ++i ) {
            sum += ( int ) key.charAt( i );
        } 

        sum = sum % table.length;

        //System.out.println( "key: " + key );
        //System.out.println( "hash value: " + sum );
        //System.out.println("-------------------------------");

        return sum;
    }

    /**
     * Custom method to hash a key. Processes the key string 4 bytes at a time and
     * treats them as a seperate integer value. The 4-byte integers are then added together and
     * the sum is modded by the size of the table so the result will be in a valid range.
     *
     * @param      key      string to hash
     * @return key converted to int value to be used as an index
     */
    public int customHash( String key ) {
        int intLength = key.length() / 4;
        int sum = 0;

        for (int j = 0; j < intLength; j++) {
            char c[] = key.substring( j * 4, ( j * 4 ) + 4 ).toCharArray();
            int mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[ k ] * mult;
                mult *= 256;
            }
        }

        char c[] = key.substring( intLength * 4 ).toCharArray();
        int mult = 1;

        for (int k = 0; k < c.length; k++ ) {
            sum += c[ k ] * mult;
            mult *= 256;
        }

        sum = Math.abs( sum ) % table.length; // mob by table length to stay within valid range

        //System.out.println( "key: " + key );
        //System.out.println( "hash value: " + sum );
        //System.out.println("-------------------------------");
        return sum;
    }

    /**
     * @return the load factor of the table
     */
    public double getLoad() {
        return (double) chainCount / table.length;
    }

    /**
     * @return the number of chains (filled buckets) in the table
     */
    public int getChainCount() {
        return chainCount;
    }

    /**
     * @return the number of entries/elements in the table
     */
    public int getEntryCount() {
        return entryCount;
    }

    /**
     * @return the size of the table
     */
    public int getTableSize() {
        return table.length;
    }

    /**
     * Compute the imbalance value of the table.
     *
     * @return the imbalance value
     */
    public int imbalance( ) {

        if( chainCount > 0 ) {
            return (int) Math.ceil( (double) entryCount / chainCount ) - 1;
        }
        return 0;
    }

    /**
     * Creates a new table of 2*size + 1 elements and
     * rehashes the current table entries to the new
     * table. Makes the new table the hash table.
     */
    public void rehash( ) {
        int newSize = 2 * table.length + 1;

        Map<String, Integer>[] oldTable = table;
        table = new HashMap[ newSize ];
        entryCount = 0;
        chainCount = 0;
        for( int i = 0, len = oldTable.length; i < len; ++i ) {
            if( oldTable[i] != null ) {
                for( String key : oldTable[i].keySet() ) {
                    put( key, oldTable[i].get( key ) );

                } // end inner for loop
            } // end if table != null
        } // end outer for loop
        System.out.println("REHASHED");
       // System.out.println("New size: " + table.length );

    }
    
    /**
     * @return the string representing all the non-empty chains in the table.
     */
    public String toString() {
        String s = "";

        for( int i = 0, len = table.length; i < len; ++i ) {
            if( table[i] != null ) {
                for( String key : table[i].keySet() ) {
                    s += key +" ";
                } // end inner for loop
                s += "\n";
            } // end if 
        } // end outer for loop
        return s;
    }


        /**
     * Calls the appropriate hashing function to convert the key into
     * an index value.
     * 
     * @param      key      string to retrieve the index of
     * @return index of the key
     */
    private int getIndex( String key ) {
        //System.out.println("Count: " + entryCount );
        int index;
        if( MODE == Htype.CUSTOM ) {
            index = customHash( key );
        }
        else {
            index = simpleHash( key );
        }
        //System.out.println( index );
        return index;
    }

    /**
     * Check the load factor of the table. Rehash the table if the load factor exceeds 
     * the specified max load value.
     */
    private void checkLoad() {
        double MAX_LOAD = 0.75;

        if( table.length > 0 ) {
            double load = getLoad();
            //System.out.println("load: " + load );
            if( load >= MAX_LOAD ) {
                rehash();
            }
        }
    }

}






/*
 * HashTable.java
 * 
 * Version: 
 *     $Id: HashTable.java,v 1.7 2013/11/06 02:48:00 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: HashTable.java,v $
 *     Revision 1.7  2013/11/06 02:48:00  andy
 *     Added variables to track numbers of elements and chains.
 *
 *     Revision 1.6  2013/11/05 21:13:13  andy
 *     Added load check to put methods.
 *
 *     Revision 1.5  2013/11/05 20:48:09  andy
 *     Changed customHash function
 *     Implemented Rehash
 *
 *     Revision 1.4  2013/11/05 17:27:01  andy
 *     Added comments.
 *
 *     Revision 1.3  2013/11/05 17:13:14  andy
 *     Added implemented hash interface
 *
 *     Revision 1.2  2013/11/05 17:12:24  andy
 *     Implemented methods
 *
 *     Revision 1.1  2013/11/05 15:34:21  andy
 *     Initial revision
 *
 *
 */ 