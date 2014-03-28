/**
 * The Bridge class simulates the bridge troll the controlls the passage of Woolies
 * across the bridge. Only one Woolie can be on the bridge at a time. Waiting
 * Woolies are selected at random and allowed to cross when the bridge is free.
 *
 * @author Andy Nguyen
 */

import java.util.*;
public class Bridge {
     // max number of woolies that can cross at the same time
    private final int MAX_WOOLIES;
    // number of woolies on the bridge
    private int woolieCount = 0;
    // Woolines waiting to cross
    private List<Woolie> woolieLine = null;


    /**
     * Default constructor for Bridge class.
     */
    public Bridge() {
        MAX_WOOLIES = 3;
        woolieLine = new ArrayList<Woolie>( 3 );
    }

    /**
     * Constructor for the Bridge class.
     */
    public Bridge( int max ) {
        MAX_WOOLIES = max;
        woolieLine = new ArrayList<Woolie>( max );
    }

    /**
     * Request permission to enter the bridge.
     */
    public  void enterBridge( Woolie woolie )  {
        synchronized( woolieLine ) {
            woolieLine.add( woolie );
          //  System.out.println( "End of line: " + woolie );
        }
        if( woolieCount == MAX_WOOLIES ) {
        // wait until there is room on the bridge
            synchronized( woolie ){ 
                try{ 
                //    System.out.println( woolie + " is Waiting.");
                    woolie.wait();
                }
                catch( InterruptedException e ){ }
            }
    }
    else { // Begin crossing right away
        woolieLine.remove(0);
    }
        ++woolieCount; 
    }

    /**
     * Notify the bridge troll that a Woolie is leaving the bridge.
     */
    public synchronized void leaveBridge() {
        if( woolieLine.size() > 0 ) {
            Woolie nextWoolie = woolieLine.remove( 0 ); // remove woolie from line
         //  System.out.println( "Front of line: " + nextWoolie );

            // notify first woolie in line that the bridge is free
            synchronized( nextWoolie ) {
                --woolieCount;
                nextWoolie.notify(); 
            }
        }
    }

}


/*
 * Bridge.java
 * 
 * Version: 
 *     $Id: Bridge.java,v 1.6 2013/11/20 04:31:58 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: Bridge.java,v $
 *     Revision 1.6  2013/11/20 04:31:58  andy
 *     Changed methods for activity 4.
 *
 *     Revision 1.5  2013/11/20 02:48:48  andy
 *     Changed methods to use wait and notifyAll
 *
 *     Revision 1.4  2013/11/15 00:31:41  andy
 *     Test
 *
 *     Revision 1.3  2013/11/14 23:47:34  andy
 *     Added synchronized modifiers for enterBridge and leaveBridge.
 *
 *     Revision 1.2  2013/11/14 23:22:31  andy
 *     Skeleton
 *
 *     Revision 1.1  2013/11/14 23:16:56  andy
 *     Initial revision
 *
 *
 */