/**
 * FindString class is used to read a file and search the lines for a specific String.
 *
 * @author Andy Nguyen
 */

import java.io.*;

public class FindString {
    /**
     * Main method.
     *
     * @param      args      command arguments
     */
     public static void main( String args[] ) throws IOException {
        String inputFile = "";
        String searchString = "";
        OutputStream destination = null;

        if( args.length == 2 ) {
            searchString = args[0];
            inputFile = args[1];
            destination = System.out;
        }
        else if( args.length == 3 ) {
            searchString = args[0];
            inputFile = args[1];
            destination = new FileOutputStream( args[2] );    
        }
        else {
            System.err.println( "Usage: java FindString search-string infile-name outfile-name" );
            System.exit( 1 );
        }

        readFile( searchString, inputFile, destination );

    }

    /**
     * Read file and print lines that contain the string matching the string to search.
     *
     * @param      searchString      String to search for
     * @param      fileName            name of file to read
     * @param      destination         output destination
     */
    private static void readFile( String searchString, String fileName, OutputStream destination ) throws IOException {
        BufferedReader in = null;
        PrintWriter out = null;
        String line = "";
        try {
            in = new BufferedReader( new FileReader( fileName ) );
            out = new PrintWriter( destination );
            while( (line = in.readLine() ) != null ) {
                if(  line.indexOf( searchString ) > -1 ) {
                    out.println( line );
                }
            }
            out.flush();
        }
        catch( IOException e ) {
            System.err.println( e );
        }
        finally {
            if( in != null ) {
                in.close();
            }
            if( out != null ) {
                out.close();
            }
        }

    }

}

/*
 * DynamicQueue.java
 * 
 * Version: 
 *     $Id: FindString.java,v 1.1 2013/09/29 01:45:42 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: FindString.java,v $
 *     Revision 1.3  2013/09/29 01:45:42  andy
 *     Changed readFile  to take output source.
 *
 *     Revision 1.2  2013/09/28 06:47:50  andy
 *     implemented methods.
 *
 *     Revision 1.1  2013/09/28 06:18:34  andy
 *     Initial revision
 *
 *
 */
