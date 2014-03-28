/**
 * The Command class represents a battleship game controller object. 
 * It takes a string interprets it as a game command.
 * 
 * @author Andy Nguyen
 */

import java.io.PrintWriter;
import java.math.BigDecimal;
public class Command {

    /**
     * Messages for each result from the fire command.
     */
    public static final String MISSMSG = "Miss!";
    public static final String HITMSG = "Hit!";
    public static final String SUNKMSG = "Sunk!";
    public static final String SAMEMSG = "Coordinates previously fired upon.";
    public static final String ILLEGALMSG = "Illegal coordinates.";

    /**
     * Number of arguments needed to execute fire command.
     */
    private static final int FIREARGS = 3;
    /**
     * Message to display when all ships have been sunk.
     */
    public static final String GAMEOVERMSG = "You win!";

    /**
     * Indicates whether the program should quit.
     */
    private static boolean exit = false;

    /**
     * Board to perform commands on.
     */
    private Board board = null;

    /**
     * Writes output
     */
    private PrintWriter out = null;
    /**
     * Number of missles fired.
     */
    private int misslesFired = 0;
    /**
     * Number of ship cells hit.
     */
    private int numberOfHits = 0;

    /**
     * A constructor for a Command object.
     * 
     * @param      myBoard      battleship board
     * @param      myOut        Output writer
     */
    public Command(Board myBoard, PrintWriter myOut ) {
        board = myBoard;
        out = myOut;
    }

    public enum Cmd {
        BOARD, SHIPS, FIRE, STATS, HELP, QUIT
    }

    /**
     * Execute the given command string.
     * 
     * @param      myCmd      command string to execute
     *
     * @exception IllegalArgumentException thrown if illegal command entered.
     */
    public void execute( String myCmd ) throws IllegalArgumentException {

        try{
            // replace excess space in the command and split it at spaces into 
            // string tokens for processing
            String[] cmdArgs = myCmd.trim().replaceAll("\\s{2,}", " ").split(" ");
            Cmd cmd = Cmd.valueOf( cmdArgs[0].toUpperCase() );
            switch( cmd ) {
                case BOARD: 
                    printBoard(); 
                    break;

                case SHIPS:
                    printShipPlacement(); 
                    break;

                case FIRE: 
                    // check if theres enough args to execute fire command
                    if( cmdArgs.length == FIREARGS ) {
                        fire( cmdArgs[1], cmdArgs[2] );

                    }
                    else {
                        out.println("Illegal coordinates.");
                    }
                    break;

                case STATS:
                    printStats();
                    break;

                case HELP:
                    printHelp();
                    break;

                case QUIT:
                    quit();
                    break;

                default: break;
            }

        }
        catch( IllegalArgumentException e ) {
            out.println( "Illegal command." ); 
        }
    }

    /**
     * @return      exit flag indicating if program should terminate
     */
    public boolean getExitFlag() {
        return exit;
    }


    /**
     * Fire at the cell at the given coordinates.
     * 
     * @param      row      row coordinate
     * @param      col      column coordinate
     */
    public void fire( String row, String col ) {
        int result = board.fireAtCell( row, col );

        switch( result ) {
            case Cell.MISS: 
                ++misslesFired;
                out.println(MISSMSG);
                printBoard();
                break;
            case Cell.HIT:
                ++misslesFired;
                ++numberOfHits;
                out.println(HITMSG);
                printBoard();
                break;
            case Cell.SUNK:
                ++misslesFired;
                ++numberOfHits;
                out.println(HITMSG);
                out.println(SUNKMSG);  
                printBoard();  
                // check if all ships sunk
                if( board.getShipCount() <= 0 ) {
                    out.println( GAMEOVERMSG );
                    printStats();
                    quit();
                }       
                break;
            case Cell.SAME:
                ++misslesFired;
                out.println(SAMEMSG);
                printBoard();
                break;
            case Board.ILLEGAL: 
                out.println(ILLEGALMSG);
                break;
            default: break;
        }
   
   
    }

    /**
     * Output the battleship game statistics.
     */
    public void printStats() {
        double hitRatio = 0.0; // hit percentage rate
        BigDecimal roundRatio = null; // used to round hitRatio

        // make sure missles were fired so it doesn't divide by 0
        if( misslesFired > 0 ) {
            // calculate hit ratio
            hitRatio = 100.0 * ( (double) numberOfHits / misslesFired );
            // instantiate BigDecimal using hitRatio
            roundRatio = new BigDecimal( Double.toString(hitRatio) ).setScale( 8,
                 BigDecimal.ROUND_CEILING );
            // convert roundRatio back to double to remove excess trailing zeros
            hitRatio = Double.parseDouble( roundRatio.toString() );
        }

        out.println( "Number of missiles fired: " + misslesFired );
        out.println( "Number of hits: " + numberOfHits );
        out.println( "Number of misses: " + ( misslesFired - numberOfHits ) );
        out.println( "Number of ships sunk: " + board.getShipsSunk() );
        out.println( "Hit ratio: " + hitRatio + "%" );

    }

    /**
     * Output the help menu with possible game commands.
     */
    public void printHelp() {
        out.println( "Possible commands:" );
        out.println( "board - displays the user's board" );
        out.println( "ships - displays the placement of the ships" );
        out.println( "fire r c - fires a missile at the cell at [r,c]" );
        out.println( "stats - prints out the game statistics" );
        out.println( "quit - exits the game" );
    }

    /**
     * Output the board with the ship placements.
     */
    public void printShipPlacement() {
        out.println(board.getShipPlacement());
    }

    /**
     * Output the board.
     */
    public void printBoard() {
        out.println(board);
    }

    /**
     * Set the exit flag to true to indicate to the main class that the program
     * should exit.
     */
    public void quit() {
        exit = true;
    }
}


/*
 * Command.java
 * 
 * Version: 
 *     $Id: Command.java,v 1.6 2013/09/22 00:24:06 agn3691 Exp agn3691 $
 * 
 * Revisions: 
 *     $Log: Command.java,v $
 *     Revision 1.6  2013/09/22 00:24:06  agn3691
 *     Changed execute method to exit on game win.
 *
 *     Revision 1.5  2013/09/21 22:44:45  agn3691
 *     More comments.
 *
 *     Revision 1.4  2013/09/21 21:20:09  agn3691
 *     Added comments for the methods.
 *     Add message constants.
 *     Changed execute implementation.
 *
 *     Revision 1.3  2013/09/21 18:42:05  agn3691
 *     Implemented methods for executing Stats and Help command.
 *     Added variables for tracking stats.
 *
 *     Revision 1.2  2013/09/21 07:13:28  agn3691
 *     Implemented execute command. Currently has FIRE, SHIP, QUIT, and BOARD.
 *
 *     Revision 1.1  2013/09/21 04:11:30  agn3691
 *     Initial revision
 *
 *
 */