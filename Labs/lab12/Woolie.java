/**
 * The Woolie class simulates a Woolie crossing the bridge.
 *
 * @author Andy Nguyen
 */

import java.util.*;
public class Woolie implements Runnable {
    // name of the Woolie
    private String name = ""; 
     // city the Woolie is heading to
    private String destination = "";
    // number of secods it takes the Woolie to cross the bridge
    private int crossingTime = 0; 
    // bridge the Woolie is crossing
    private Bridge bridge = null;

    /**
     * Main method. Creates multiple Woolies with different names, corssing 
     * times and destinations for testing.
     *
     * @exception NumberFormatException if invalid number entered for 
     * command line arg
     */
    public static void main( String args[] ) throws NumberFormatException {
        Bridge testBridge = new Bridge();
        // create threads
        Thread w1 = new Thread( new Woolie( "Tom", 10, "Merctan", testBridge ) );
        Thread w2 = new Thread( new Woolie( "Jerry", 8, "Merctan", testBridge ) );
        Thread w3 = new Thread( new Woolie( "Bugs", 4, "Sicstine", testBridge ) );
        Thread w4 = new Thread( new Woolie( "Daffy", 14, "Merctan", testBridge ) );
        // start threads
        w1.start();
        w2.start();
        w3.start();
        w4.start();
    }


    /**
     * Construct a new Woolie object.
     *
     * @param myName                    name of the Woolie
     * @param myCrossingTime       seconds it takes the Woolie to cross the bridge
     * @param myDestination           city the Woolie is heading to
     */
    public Woolie( String myName, int myCrossingTime, String myDestination, 
                                Bridge myBridge ) {
        name = myName;
        crossingTime = myCrossingTime;
        destination = myDestination;
        bridge = myBridge;
    }

    /**
     * Handles the Woolie's crossing of the bridge.
     */
    public void run() {
        System.out.println( name + " has arrived at the bridge.");
        synchronized( bridge ) { // get lock

            bridge.enterBridge();
            
            System.out.println( name + " is starting to cross.");
            
            for( int i = 1; i <= crossingTime; ++i ) {
                try{
                    Thread.sleep( 1000 ); 
                }
                catch( InterruptedException e ) {}
                System.out.println( "\t" + name + " " + i + " seconds." );
            }

            bridge.leaveBridge();
            
            System.out.println( name + " leaves at " + destination + "." );
        
        }
    }
}


/*
 * Woolie.java
 * 
 * Version: 
 *     $Id: Woolie.java,v 1.6 2013/11/15 00:31:04 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: Woolie.java,v $
 *     Revision 1.6  2013/11/15 00:31:04  andy
 *     Using Synchronized statement
 *
 *     Revision 1.5  2013/11/14 23:46:47  andy
 *     Modified constructor to take an additional parameter, bridge.
 *     Modified run to use Bridge class for activity 3.
 *
 *     Revision 1.4  2013/11/14 23:12:13  andy
 *     Modified run method to match required output for lab.
 *
 *     Revision 1.3  2013/11/14 22:32:18  andy
 *     Implemented run method.
 *
 *     Revision 1.2  2013/11/14 22:15:52  andy
 *     Added constructor and comments.
 *
 *     Revision 1.1  2013/11/14 21:57:05  andy
 *     Initial revision
 *
 *
 */