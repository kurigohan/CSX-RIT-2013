/**
 * The TestBoard class tests the Board class used for the battleship game.
 * 
 * @author Andy Nguyen
 */

import java.io.*;
public class TestBoard{
    private final static int BOARDSIZE = 10;

    public static void main( String args[] ) throws IOException {
        Board board = new Board( BOARDSIZE );
        System.out.println( board.getShipPlacement() );

        System.out.println("Test Board.convertLetter: " + testConvertLetter( board ));
        testInitialize(board);

       // board.addCell( "D", "D", new ShipCell(new Ship( "A", 5 )));
       // board.addCell( "A", "B", new ShipCell(new Ship( "B", 5 )));

        testFire(board, "A", "A");
        testFire(board, "Z", "A");
        testFire(board, "I", "D");
        testFire(board, "H", "D");
        testFire(board, "A", "A");

    }

    private static boolean testConvertLetter( Board b ) {
        char c = 'A';
        for( int i = 0; i < 26; ++i ) {
            if( b.convertLetter( Character.toString( c++ ) ) == -1) {
                return false;
            }
        }
        return true;
    }

    private static void testInitialize( Board b ) throws IOException {
        System.out.println("Board initialized.");
        b.initialize("testinput.txt");
    }

    private static void testFire( Board b, String row, String col ) {
        System.out.println("Fire: " + row + " " + col);
        int result = b.fireAtCell( row, col );
        System.out.println("Result: " + result );
        System.out.println( b );
        System.out.println();
    }
    
}

/*
 * TestBoard.java
 * 
 * Version: 
 *     $Id$
 * 
 * Revisions: 
 *     $Log$
 *
 */