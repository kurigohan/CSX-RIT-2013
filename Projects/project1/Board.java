/**
 * The Board class represents the board for a battleship game.
 * 
 * @author Andy Nguyen
 */

import java.io.*;
import java.util.*;

public class Board {
   /**
     * Illegal coodinates status code.
     */
    public static final int ILLEGAL = -1;

    /**
     * Alphabet used to translate letter to number using String indexOf.
     */
    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Size of the board.
     */
    private int size = 0;

    /**
     * Cells on the board.
     */
    private Cell[][] cells = null;

    /**
     * Initial number of ships on the board.
     */
    private int shipCount = 0;


    /**
     * Number of ships sunk.
     */
    private int shipsSunk = 0;

    /**
     * Ship placement of the board (the board initial display).
     */
    private String shipPlacement = "";

    /**
     * The minimum and maximum size of the board.
     */
    public static final int MINSIZE = 5;
    public static final int MAXSIZE = 26;


    /**
     * A constructor for a Board object.
     * 
     * @param      mySize      number of rows/columns on the board
     */
    public Board( int mySize ) {

        size = mySize;
        cells = new Cell[size][size];        // instantiate cells array 

        fillBoard();
    }

    /**
     * Fill the board with WaterCell objects.
     */
    private void fillBoard() {
        for( int i = 0; i < size; ++i ) {
            for( int j = 0; j < size; ++j ) {
                cells[i][j] = new WaterCell();
            }
        }
    }

    /**
     * Get the ship count (number of ships on the board)
     */
    public int getShipCount() {
        return shipCount;
    }

    /**
     * Get the number of ships sunk.
     */
    public int getShipsSunk(){
        return shipsSunk;
    }

    /**
     * Get the ship placement.
     * 
     * @return      string containing the board with ship placements
     */
    public String getShipPlacement() {
        return shipPlacement;
    }


    /**
     * Convert letter coordinate to a number.
     * 
     * @return      coordinate as an int
     */
    public int convertLetter( String c ) {
        return ALPHABET.indexOf( c );
    }

    /**
     * Add a cell to the board.
     */
    public void addCell( String y, String x, Cell c ) {
        cells[convertLetter(y)][convertLetter(x)] = c;
    }

    /**
     * Fire at the given coordinates. An int is returned to indicate the result.
     * -1 -- illegal coordinates
     * 0 -- missed
     * 1 -- hit
     * 2 -- ship was sunk
     * 3 -- cell was previously fired upon
     * 
     * @param      row      row coordinate
     * @param      col      column coordinate
     *
     * @return      int representing result of the fire command
     */
    public int fireAtCell( String row, String col ) {
        int y = convertLetter( row.toUpperCase() ),
            x = convertLetter( col.toUpperCase() );
        // make sure coordinates are in bounds
        if( y > -1 && y < size && x > -1 && x < size ) {
            int result = cells[y][x].hit();

            if( result == Cell.SUNK ){
                --shipCount;
                ++shipsSunk;
            }

            return result;
        }
        else {
            return ILLEGAL;
        }
    }

    /**
     * Initialize the board by reading a file containing ship coordinates and 
     * adding the ships to the board. 
     * 
     * @param      fileName      name of the file to read to get ship coordinates
     *
     * @exception IOException thrown if file could not be opened.
     */
    public void initialize( String fileName ) throws IOException, 
        NumberFormatException{

        String[] coordinates = null;
        String line = "";
        char letter = 'A'; // letter of the ship being added
        int shipsInFile = 0;
        BufferedReader br = null;

        try {
            // open the file to read
            br =  new BufferedReader ( new FileReader( fileName ) ) ;
            shipsInFile = Integer.parseInt(br.readLine());
            //System.out.println(shipCount);
            // read each character in the file
            while ( (line = br.readLine()) != null ) {
                line = line.trim().replaceAll("\\s{2,}", " ");
                //System.out.println( line );
                coordinates = line.split(" ");
                if( coordinates.length == 4 ) {
                    addShip( coordinates, letter++ );
                    ++shipCount;
                }
                else{
                    System.err.println("Line: " + line + " has an invalid " +
                        "number of coordinates. Line skipped.\n");
                }
            }

            if( shipCount != shipsInFile ) {
                System.err.println("WARNING: The number of ships added to the" +
                    " board does not equal the number of ships specified in" + 
                    " the file.\n");
            }

            ShipCell.setHidden( false );     // unhide ship cells
            shipPlacement = this.toString(); // store initial display state
            ShipCell.setHidden( true );      // hide ship cells
        } 
        catch ( IOException e ) {
            System.err.println( "Cannot open file " + fileName + "." );
        }
        catch ( NumberFormatException e ) {
            System.err.println( "Could not read ship count. The first line of" +
                " the file must be a single integer.");
            System.exit(1);
        }
        catch( ArrayIndexOutOfBoundsException e ) { 
            System.err.println( "Overlapping or out-of-bounds ships in file " +
                fileName + "." );
            System.exit(1);
        }

        finally {
            if ( br != null ) {
                br.close(); // close the file
            }
        }
    }

    /**
     * Add a ship to the board given a String array of coordinates. The String
     * array must contain 4 coordinates; [yStart] [xStart] [yEnd] [xEnd]
     * 
     * @param      coordinates      String array containing coordinates
     * @param      letter           letter to mark the ship
     *
     * @exception ArrayIndexOutOfBoundsException thrown if invalid coordinates
     */
    private void addShip( String[] coordinates, char letter ) throws 
        ArrayIndexOutOfBoundsException {  

        try {
            // convert coordinates to numbers
            int yStart = convertLetter(coordinates[0].toUpperCase()), 
                xStart = convertLetter(coordinates[1].toUpperCase()), 
                yEnd = convertLetter(coordinates[2].toUpperCase()),
                xEnd = convertLetter(coordinates[3].toUpperCase());            

            // make sure xStart <= xEnd and yStart <= yEnd so size calculation  
            // and while loop works 
            if( xStart > xEnd ) {
                int temp = xStart;
                xStart = xEnd;
                xEnd = temp;
            }

            if( yStart > yEnd ) {
                int temp = yStart;
                yStart = yEnd;
                yEnd = temp;
            }

            // Calculate number of cells the ship will occupy (its hp).
            // Since a ship can only be vertical or horizontal, either x 
            // diff or y diff will be zero, thus adding them together
            // will account for both vertical or horizontal orientations.
            int shipSize = 1 + ( xEnd - xStart ) + ( yEnd - yStart );

            Ship ship = new Ship( Character.toString(letter), shipSize );

            while( yStart < yEnd || xStart < xEnd ) {

                cells[yStart][xStart] = new ShipCell( ship );

                if( yStart < yEnd ) {
                    ++yStart;
                }

                if( xStart < xEnd ) {
                    ++xStart;
                }
            } // end while
            if( cells[yEnd][xEnd] instanceof ShipCell ) {
                throw new ArrayIndexOutOfBoundsException();
            }
            else {
                cells[yEnd][xEnd] = new ShipCell( ship );
            }
        } 

        catch( ArrayIndexOutOfBoundsException e ) { 
            throw e; // throw the exception to the method caller
        }
    }


    /**
     * @return      string representing the board
     */
    public String toString() {
        String display = "  ";
        char colLetter = 'A', rowLetter = 'A';

        // add column titles
        for ( int i = 0; i < size; ++i ) {
            display += colLetter++ + " ";
        }

        for( int i = 0; i < size; ++i ) {
            display += "\n";
            display += rowLetter++; // add row title
            // add cells
            for( int j = 0; j < size; ++j) {
                display += " " + cells[i][j];
            }

        }
        return display;
    }
}

/*
 * Board.java
 * 
 * Version: 
 *     $Id: Board.java,v 1.12 2013/09/22 00:23:21 agn3691 Exp agn3691 $
 * 
 * Revisions: 
 *     $Log: Board.java,v $
 *     Revision 1.12  2013/09/22 00:23:21  agn3691
 *     Exception comments
 *
 *     Revision 1.11  2013/09/21 22:44:16  agn3691
 *     More comments.
 *
 *     Revision 1.10  2013/09/21 21:38:02  agn3691
 *     Added comments for all the methods.
 *     Changed FireAtCell method to return an int instead of a String to represent the result for the fire command.
 *
 *     Revision 1.9  2013/09/21 18:43:33  agn3691
 *     Added variable to track ships sunk.
 *     Modified fireAtCell method to return ship sunk message when necessary.
 * 
 *     Revision 1.8  2013/09/21 07:11:44  agn3691
 *     Added fireAtCell method and a new class variable, shipPlacement.
 *
 *     Revision 1.7  2013/09/21 03:58:28  agn3691
 *     Added error checking
 *
 *     Revision 1.6  2013/09/21 02:10:13  agn3691
 *     Fixed initialize method to properly add ship cells.
 *
 *     Revision 1.5  2013/09/19 23:25:31  agn3691
 *     Added initialize method for reading file and adding ships.
 *
 *     Revision 1.4  2013/09/18 20:36:53  agn3691
 *     Added method to fill board with water cells
 *     Added toString to print board
 *
 *     Revision 1.3  2013/09/18 19:23:20  agn3691
 *     Added createHashMap and convertChar method for converting chars to valid int coordinates.
 *
 *     Revision 1.2  2013/09/17 16:30:12  agn3691
 *     Modified constructor to take a Outstream object. PrintWriter writes to the passed OutStream object.
 *
 *     Revision 1.1  2013/09/15 20:45:09  agn3691
 *     Initial revision
 *
 */