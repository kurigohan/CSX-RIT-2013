
/**
 * The WaterCell class represents a water cell object on the battleship board.
 * 
 * @author Andy Nguyen
 */

public class WaterCell extends Cell {


    /**
     * A constructor for a WaterCell object
     */
    public WaterCell() {
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
        display = "O";
        cellHit = true;
        return MISS;
    }

    /**
     * @return      string representing the cell
     */
    public String toString() {
        return display;
    }

}

/*
 * WaterCell.java
 * 
 * Version: 
 *     $Id: WaterCell.java,v 1.8 2013/09/22 00:25:52 agn3691 Exp agn3691 $
 * 
 * Revisions: 
 *     $Log: WaterCell.java,v $
 *     Revision 1.8  2013/09/22 00:25:52  agn3691
 *     comments
 *
 *     Revision 1.7  2013/09/21 22:45:46  agn3691
 *     More comments
 *
 *     Revision 1.6  2013/09/21 22:13:20  agn3691
 *     Comments.
 *
 *     Revision 1.5  2013/09/21 07:16:45  agn3691
 *     Changed hit() method to return appropriate messages.
 *
 *     Revision 1.4  2013/09/21 02:12:48  agn3691
 *     Implemented methods
 *
 *     Revision 1.3  2013/09/19 23:26:02  agn3691
 *     Implemented abstract methods from Cell class.
 *
 *     Revision 1.2  2013/09/17 16:32:58  agn3691
 *     Added method setDisplay. Fixed bugs.
 *
 *
 */
