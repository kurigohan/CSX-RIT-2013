/**
 * The ManyThreads class creates and starts a number of threads specified by 
 * a command line argument.
 *
 * @author Andy Nguyen
 */

import java.util.*;
 
public class ManyThreads {
    int threadTotal = 0; // total number of threads
    
    /**
     * Main method for test ManyThreads. Creates a ManyThreads object
     * with the given command line argument. The argument is the number of 
     * threads the ManyThreads object should create.
     *
     * @exception NumberFormatException if invalid number entered for 
     * command line arg
     */
    public static void main( String args[] ) throws NumberFormatException {
        try{
            if( args.length > 0 ) {

                ManyThreads test = new ManyThreads( Integer.parseInt( args[0] ) );
                test.createThreads();
            }
            else {
                System.err.println( "Usage:  java ManyThreads number-of-threads");
            }
        }
        catch( NumberFormatException e ) {
            System.err.println( "ManyThreads:  Invalid number" );
        }
    }

    /**
     * Contructs a new ManyThreads object.
     *
     * @param total       the number of threads to create and start
     */
    public ManyThreads( int total ) {
        threadTotal = total;
    }

    /**
     * Creates a number of threads equal to the threadTotal and starts them.
     */
    public void createThreads() {
        
        for( int i = 0; i < threadTotal; ++i ) {
            // annoymous Thread class
            new Thread( ) {
                private int id; // thread identifier

                public Thread init( int identifier ) {
                    id = identifier;
                    // return instance of this class with id set to identifier
                    return this; 
                }

                public void run() {
                    System.out.println( "Hello I am thread " + id );
                }
            }.init( i ).start(); // initialize with id and then start
        } // end for loop
    }

}


/*
 * ManyThreads.java
 * 
 * Version: 
 *     $Id: ManyThreads.java,v 1.3.1.1 2013/11/14 22:42:05 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: ManyThreads.java,v $
 *     Revision 1.3.1.1  2013/11/14 22:42:05  andy
 *     Comments.
 *
 *     Revision 1.3  2013/11/14 21:54:42  andy
 *     Fixed output.
 *
 *     Revision 1.2  2013/11/14 21:53:29  andy
 *     Implemented methods.
 *
 *     Revision 1.1  2013/11/14 21:50:57  andy
 *     Initial revision
 *
 *
 */