/**
 * The ShipCell class represents a water cell object on the battleship board.
 * 
 * @author Andy Nguyen
 */

public class ShipCell extends Cell {

   /**
    * The Ship object associated with the cell.
    */
    private Ship ship = null;

    /**
     *  Indicates if the cell should be hidden or not.
     */
    private static boolean hidden = true;

    /**
     * A constructor for a ShipCell object. It accepts the x and y coordinates for the cell.
     * 
     * @param      myShip      the ship associated with the cell
     */
    public ShipCell( Ship myShip ) {
        ship = myShip;
        display = DEFAULTDISPLAY;
    }

   /**
     * Simulate cell hit by changing the cell display.
     * 
     * @return      returns an int indicating result of hit
     */
    public int hit() {
        if( cellHit ) {
            return SAME;
        }

        display = "X";
        cellHit = true;
        ship.hit();

        if( ship.isSunk() ) {
            return SUNK;
        }

        return HIT;
    }

   /**
     * @param      hide      set cell display to hidden
     */
    public static void setHidden( boolean hide ) {
        hidden = hide;
    }


    /**
     * @return      string representing the cell
     */
    public String toString() {
        if( hidden ){
            return display;
        }
        else {
            return ship.getMarker();
        }
    }


}

/*
 * ShipCell.java
 * 
 * Version: 
 *     $Id: ShipCell.java,v 1.10 2013/09/22 00:25:41 agn3691 Exp agn3691 $
 * 
 * Revisions: 
 *     $Log: ShipCell.java,v $
 *     Revision 1.10  2013/09/22 00:25:41  agn3691
 *     comments
 *
 *     Revision 1.9  2013/09/21 22:45:30  agn3691
 *     More comments.
 *
 *     Revision 1.8  2013/09/21 22:13:31  agn3691
 *     More comments.
 *
 *     Revision 1.7  2013/09/21 22:11:14  agn3691
 *     Added comments for methods.
 *
 *     Revision 1.6  2013/09/21 18:41:37  agn3691
 *     Turned some message strings to constants.
 *
 *     Revision 1.4  2013/09/21 02:13:10  agn3691
 *     Implemented methods
 *
 *     Revision 1.3  2013/09/19 23:26:25  agn3691
 *     Implemented abstract methods from Cell class.
 *
 *     Revision 1.2  2013/09/17 16:33:37  agn3691
 *     Added method setDisplay.
 *
 *
 */