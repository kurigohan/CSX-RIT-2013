/**
 * The Battleship class starts the program to play battleship. It contains the
 * methods to execute game commands and keep track of the game stats. The program
 * is nitialized by specifying an int for the board size and string for the 
 * input file. Once the program as begun, the user is prompted for
 * commands.
 *
 * @author Andy Nguyen
 */

import java.io.*;
import java.util.Scanner;

public class Battleship {

    /**
     * Number of arguments needed to execute fire command.
     */
    private static final int FIREARGS = 3;
    /**
     * Message to display when all ships have been sunk.
     */
    public static final String GAMEOVERMSG = "You win!";

    private static Board board = null;
    private static PrintWriter out = null;
    private static BufferedReader in = null;
    private static Command command = null;

   /**
     * Main method for program.
     *
     * @param      args      command line arguments 
     * @exception IOException 
     */
    public static void main( String args[] ) throws IOException, 
        NumberFormatException  {

        // set output destination and input source
        out = new PrintWriter( System.out, true ); 
        in = new BufferedReader( new InputStreamReader( System.in ) );

        int size = 0;           // size of the game board
        String fileName = "";   // name of the file

        try {
            if( args.length > 1 ) { // check for valid # of command arguments
                size = Integer.parseInt( args[0] );

                if( size < Board.MINSIZE ){
                    System.err.println("Board must be at most " + Board.MINSIZE + 
                        " by " + Board.MINSIZE + ".");
                }
                else if( size > Board.MAXSIZE ){
                    System.err.println("Board must be at most " + Board.MAXSIZE + 
                        " by " + Board.MAXSIZE + ".");
                }
                else {
                    fileName = args[1];
                    board = new Board( size );
                    board.initialize(fileName);

                    // exit program if no ships were added to board
                    if( board.getShipCount() == 0 ) {
                        System.exit(1);
                    }
                    out.println(board);

                    // command object used to execute commands
                    command = new Command( board, out ); 

                    // keep prompting the user for commands until QUIT command
                    // is entered.
                    while( !command.getExitFlag() ) {
                        promptUser();
                    }
                }
            }
            else {
                throw new NumberFormatException();
            }
        }
        catch( NumberFormatException e ) {
            System.err.println("Usage: Battleship N config-file \n" +
                "Usage: java Battleship N config-file");
        }

        System.exit(0); // exit the program
    }

    /**
     * Prompt the user for commands.
     *
     * @exception IOException if line could not be read.
     */
    private static void promptUser() throws IOException{

        out.print("> ");
        out.flush();
        String cmd = in.readLine();
        // make sure cmd is not an empty string
        if( cmd.length() > 0 ) {
            command.execute( cmd );
        }
    }

}

/*
 * Battleship.java
 * 
 * Version: 
 *     $Id: Battleship.java,v 1.11 2013/09/22 00:22:39 agn3691 Exp agn3691 $
 * 
 * Revisions: 
 *     $Log: Battleship.java,v $
 *     Revision 1.11  2013/09/22 00:22:39  agn3691
 *     *** empty log message ***
 *
 *     Revision 1.10  2013/09/21 22:43:53  agn3691
 *     More comments.
 *
 *     Revision 1.9  2013/09/21 21:47:01  agn3691
 *     Added more comments and changed main to use BufferedReader instead of Scanner to read input.
 *
 *     Revision 1.8  2013/09/21 18:39:25  agn3691
 *     Added more error checking.
 *
 *     Revision 1.7  2013/09/21 07:12:44  agn3691
 *     Added and implemented promptUser method.
 *
 *     Revision 1.6  2013/09/21 03:58:57  agn3691
 *     Added some error checking.
 *
 *     Revision 1.5  2013/09/21 02:11:07  agn3691
 *     Comments
 *
 *     Revision 1.3  2013/09/19 23:25:09  agn3691
 *     Moved some methods to Board class.
 *
 *     Revision 1.2  2013/09/18 20:37:41  agn3691
 *     Added PrintWriter object to print output to given source.
 *
 *     Revision 1.1  2013/09/18 19:22:34  agn3691
 *     Initial revision
 *
 *
 */