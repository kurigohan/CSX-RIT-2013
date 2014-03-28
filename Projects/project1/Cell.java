
/**
 * The Cell class represent a generic cell object on the battleship board.
 * 
 * @author Andy Nguyen
 */

public abstract class Cell {

   /**
    * Constants used to represent result from fire command.
    */
    public static final int SAME = 3;
    public static final int SUNK = 2;
    public static final int HIT = 1;
    public static final int MISS = 0;

    /**
     * Message to display when a missle hits.
     */
    public static final String DEFAULTDISPLAY = "-";

   /**
    * String to display on the board to represent the cell.
    */
    protected String display = "";

   /**
    * Indicate if the cell is hit.
    */
    protected boolean cellHit = false;

    /**
     * @return      boolean value indicating if cell has been hit
     */
    public boolean isHit() {
        return cellHit;
    }

   /**
     * Simulate cell hit by changing the cell display.
     * 
     * @return      returns an int indicating result of hit
     */
    public abstract int hit();

    /**
     * @return      string representing the cell
     */
    public abstract String toString();

}


/*
 * Cell.java
 * 
 * Version: 
 *     $Id: Cell.java,v 1.8 2013/09/22 00:23:33 agn3691 Exp agn3691 $
 * 
 * Revisions: 
 *     $Log: Cell.java,v $
 *     Revision 1.8  2013/09/22 00:23:33  agn3691
 *     Exception comments
 *
 *     Revision 1.7  2013/09/21 22:44:30  agn3691
 *     More comments.
 *
 *     Revision 1.6  2013/09/21 22:15:24  agn3691
 *     Comments.
 *
 *     Revision 1.5  2013/09/21 07:10:57  agn3691
 *     Added additional class variables to be used by ShipCell and WaterCell.
 *
 *     Revision 1.4  2013/09/21 02:07:55  agn3691
 *     Comments
 *
 *     Revision 1.3  2013/09/19 23:23:13  agn3691
 *     Converted class to abstract. Removed implemented methods.
 *
 *     Revision 1.2  2013/09/17 16:32:15  agn3691
 *     Added methods to track if cell is hit.
 *
 *     Revision 1.1  2013/09/15 20:44:19  agn3691
 *     Initial revision
 *
 */
