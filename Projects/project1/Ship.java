/**
 * The Ship class represents a ship object for a battleship game. 
 * 
 * @author Andy Nguyen
 */


public class Ship {
    /**
     * String to display on the board to represent the ship
     */
    private String marker = "";
    /**
     * Amount of hits the ship has taken
     */
    private int damage = 0;
    /**
     * Number of hits the ship can take before sinking (# cells it occupies)
     */
    private int hitpoints = 0;
    /**
     * Indicates if the ship is sunk or not
     */
    private boolean sunk = false;

    /**
     * A constructor for a Ship object.
     * 
     * @param      m       string to display on the board to represent the ship      
     * @param      hp      number of hits the ship can take (hitpoints)
     */
    public Ship( String m, int hp ) {
        marker = m;
        hitpoints = hp;
    }

    /**
     * @return the ship marker string used to indentify the ship
     */
    public String getMarker() {
        return marker;
    }
    

    /**
     * @return number of times the ship has been hit
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @return number of hits the ship can take
     */
    public int getHP() {
        return hitpoints;
    }


    /**
     * @return boolean value indicating is ship is sunk
     */
    public boolean isSunk() {
        return sunk;
    }


    /**
     * Add damage to the ship.
     */
    public void hit() {
        if( damage < hitpoints ) {
            ++damage;
        }

        if( damage >= hitpoints ) {
            sunk = true;
        }
    }

    /**
     * @return string representing the ship
     */
    public String toString() {
        return marker + " " + damage + "/" + hitpoints + " " + sunk; 
    }

}

/*
 * Ship.java
 * 
 * Version: 
 *     $Id: Ship.java,v 1.7 2013/09/22 00:25:04 agn3691 Exp agn3691 $
 * 
 * Revisions: 
 *     $Log: Ship.java,v $
 *     Revision 1.7  2013/09/22 00:25:04  agn3691
 *     Comments.
 *
 *     Revision 1.6  2013/09/21 22:45:12  agn3691
 *     More comments.
 *
 *     Revision 1.5  2013/09/21 18:40:14  agn3691
 *     Comments.
 *
 *     Revision 1.3  2013/09/21 02:06:47  agn3691
 *     Changed marker data type and added comments.
 *
 *     Revision 1.2  2013/09/19 23:26:51  agn3691
 *     Changed method implementations.
 *
 *     Revision 1.1  2013/09/18 20:36:00  agn3691
 *     Initial revision
 *
 *
 */
