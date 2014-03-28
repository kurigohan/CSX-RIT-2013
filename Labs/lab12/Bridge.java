/**
 * The Bridge class simulates the bridge troll the controlls the passage of Woolies
 * across the bridge. Only one Woolie can be on the bridge at a time. Waiting
 * Woolies are selected at random and allowed to cross when the bridge is free.
 *
 * @author Andy Nguyen
 */

public class Bridge {
    
    /**
     * Constructor for the Bridge class.
     */
    public Bridge() {}

    /**
     * Request permission to enter the bridge.
     */
    public void enterBridge() {}

    /**
     * Notify the bridge troll that a Woolie is leaving the bridge.
     */
    public void leaveBridge() {}

}


/*
 * Bridge.java
 * 
 * Version: 
 *     $Id: Bridge.java,v 1.4 2013/11/15 00:31:41 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: Bridge.java,v $
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