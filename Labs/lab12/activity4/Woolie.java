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
    // Bridge to cross
    private Bridge bridge = null;

    /**
     * Main method. Creates multiple Woolies with different names, corssing 
     * times and destinations for testing.
     *
     * @exception NumberFormatException if invalid number entered for 
     * command line arg
     */
    public static void main( String args[] ) throws NumberFormatException {
        // create bridge
        Bridge testBridge = new Bridge( 3 );
        // create threads
        Thread w1 = new Thread( new Woolie( "Tom", 3, "Merctan", testBridge ) );
        Thread w2 = new Thread( new Woolie( "Jerry", 5, "Merctan", testBridge ) );
        Thread w3 = new Thread( new Woolie( "Bugs", 7, "Sicstine", testBridge ) );
        Thread w4 = new Thread( new Woolie( "Daffy", 10, "Merctan", testBridge ) );
        Thread w5 = new Thread( new Woolie( "Andrew", 9, "Merctan", testBridge ) );
        Thread w6 = new Thread( new Woolie( "Clinton", 3, "Merctan", testBridge ) );
        Thread w7 = new Thread( new Woolie( "Nicole", 4, "Sicstine", testBridge ) );
        Thread w8 = new Thread( new Woolie( "Christina", 6, "Merctan", testBridge ) );
        Thread w9 = new Thread( new Woolie( "Bobby", 6, "Sicstine", testBridge ) );
        Thread w10 = new Thread( new Woolie( "Juan", 8, "Merctan", testBridge ) );


        /* Test with same corssing time
        Thread w1 = new Thread( new Woolie( "Tom", 9, "Merctan", testBridge ) );
        Thread w2 = new Thread( new Woolie( "Jerry", 9, "Merctan", testBridge ) );
        Thread w3 = new Thread( new Woolie( "Bugs", 9, "Sicstine", testBridge ) );
        Thread w4 = new Thread( new Woolie( "Daffy", 9, "Merctan", testBridge ) );
        Thread w5 = new Thread( new Woolie( "Andrew", 9, "Merctan", testBridge ) );
        Thread w6 = new Thread( new Woolie( "Clinton", 9, "Merctan", testBridge ) );
        Thread w7 = new Thread( new Woolie( "Nicole", 9, "Sicstine", testBridge ) );
        Thread w8 = new Thread( new Woolie( "Christina", 9, "Merctan", testBridge ) );
        Thread w9 = new Thread( new Woolie( "Bobby", 9, "Sicstine", testBridge ) );
        Thread w10 = new Thread( new Woolie( "Juan", 9, "Merctan", testBridge ) );
        */
        
        // start threads
        w1.start();
        w2.start();
        w3.start();
        w4.start();
        w5.start();
        w6.start();
        w7.start();
        w8.start();
        w9.start();
        w10.start();
    }


    /**
     * Construct a new Woolie object.
     *
     * @param myName                    name of the Woolie
     * @param myCrossingTime       seconds it takes the Woolie to cross the bridge
     * @param myDestination           city the Woolie is heading to
     */
    public Woolie( String myName, int myCrossingTime, String myDestination, 
                                    Bridge myBridge) {
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

        bridge.enterBridge( this ); //request permission to start crossing

        // simulate bridge crossing
        for( int i = 0; i < crossingTime;  ++i ) {
            if( i == 0 ) {
                System.out.println( name + " is starting to cross.");
            }
            else {
                System.out.println( "\t" + name + " " + i + " seconds." );
            }
            // pass time
            try{
                Thread.sleep( 1000 ); 
            }
            catch( InterruptedException e ) {}

        }

        System.out.println( name + " leaves at " + destination + "." );
        bridge.leaveBridge();  // notify Bridge that it is done crossing


    }

    /**
     * @return name of the Woolie
     */
    public String toString() {
        return name;

    }
}


/*
 * Woolie.java
 * 
 * Version: 
 *     $Id: Woolie.java,v 1.8 2013/11/20 04:27:00 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: Woolie.java,v $
 *     Revision 1.8  2013/11/20 04:27:00  andy
 *     Modified for Activity 4
 *
 *     Revision 1.5  2013/11/20 02:21:01  andy
 *     Modified to use Bridge.
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