/**
 * The TestCommand class tests the Command class methods.
 * 
 * @author Andy Nguyen
 */

import java.io.*;
public class TestCommand {
    private static final String file = "input.txt";
    private static final int BOARDSIZE = 10;
    public static void main( String args[] ) throws IOException{

        // set output destination and input source
        PrintWriter out = new PrintWriter( System.out, true ); 
        Board board = new Board( BOARDSIZE );
        board.initialize( file );
        Command cmd = new Command( board, out );

        cmd.execute("board"); // print board
        System.out.println();
        cmd.execute("ships "); // print ship placements
        System.out.println();
        cmd.execute(""); // no input
        System.out.println();
        cmd.execute("fire a Z"); // out of bounds coordinate
        System.out.println();
        cmd.execute("Fire A a"); // case sensitivity and miss result
        System.out.println();
        cmd.execute("fire f c"); // hit result
        System.out.println();
        cmd.execute("fire F D"); // sunk result
        System.out.println();
        cmd.execute("ships"); 
        System.out.println();
        cmd.execute("stats"); // print stats
        System.out.println();
        cmd.execute("help"); // help menu
        System.out.println();
        cmd.execute("abcab"); // illegal command
        System.out.println(); 
        cmd.execute("quit"); // quit program

    }


}