/**
 * HashDriver tests the functions of HashTable.
 *
 * @author Andy Nguyen
 */

import java.io.*;
public class HashDriver {

    /**
     * Main method that runs the program
     */
    public static void main( String args[] ) throws IOException, NumberFormatException{

        if( args.length > 0 ) {
            try{
                int size = 100000;
                String fileName = args[0];
                HashTable testTable;

                // use simple hash function
                System.out.println( "--- Simple Hash ---" );
                testTable = new HashTable( size, Hash.Htype.SIMPLE);
                readFile( fileName, testTable );
                printResult( testTable );

                System.out.println();

                // use custom hash function
                System.out.println( "--- Custom Hash ---" );
                testTable = new HashTable( size, Hash.Htype.CUSTOM );
                readFile( fileName, testTable );
                printResult( testTable );
                
                System.out.println();

                // rehash
                int rehashSize = 89;
                System.out.println("--- Rehash Test ( custom hash )---");
                testTable = new HashTable( rehashSize, Hash.Htype.CUSTOM );
                System.out.println("initial table size: " + rehashSize );
                readFile( fileName, testTable );
                printResult( testTable );
            }
            catch( NumberFormatException e ) {
                System.err.println("Invalid argument.");
            }
        }
        else{
            System.err.println( "Usage: java HashDriver fileName" );
        }

    }

    /**
     * Print information related to the HashTable
     */
    private static void printResult( HashTable table ) {
        System.out.println( "imbalance: " + table.imbalance() );
        System.out.println( "table size: " + table.getTableSize() );
        System.out.println( "chain count: " + table.getChainCount() );
        System.out.println( "entry count: " + table.getEntryCount() );
        System.out.println( "load factor: " + table.getLoad() );
    }

    /**
     *  Read a text file and fills a HashTable with the data.
     */
    private static void readFile( String fileName , HashTable table ) throws IOException {
        String line; 
        BufferedReader br = null;
        if( table != null ) {
            try{
                br =  new BufferedReader ( new FileReader( fileName ) ) ; 

                while ( ( line = br.readLine() ) != null ) {
                   // System.out.println( line );
                    line = line.trim().replaceAll("\\s{2,}", " "); // remove excess spaces
                    for( String s : line.split(" ") ) {
                        table.put( s );
                        //System.out.println( s );
                    }
                }
            }
            catch( IOException e ) {
                System.err.println( "Could not open file." );
                System.exit( -1 );
            }
            finally {
                if ( br != null ) {
                    br.close(); // close the file
                }
            }

        }
    }
    
}






/*
 * HashDriver.java
 * 
 * Version: 
 *     $Id: HashDriver.java,v 1.3 2013/11/06 02:49:45 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: HashDriver.java,v $
 *     Revision 1.3  2013/11/06 02:49:45  andy
 *     Comments.
 *
 *     Revision 1.2  2013/11/06 02:48:23  andy
 *     Implemented methods
 *
 *     Revision 1.1  2013/11/05 15:34:58  andy
 *     Initial revision
 *
 *
 */