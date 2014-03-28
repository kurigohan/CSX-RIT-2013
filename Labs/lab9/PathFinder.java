/**
 * PathFinder is the driver program for DFSGraph.java
 * @author Andy Nguyen
 */

import java.io.*;

public class PathFinder {

    /**
     *  Main method that runs the program
     */
    public static void main( String args[] ) throws IOException {
        if( args.length > 0 ) {
            String fileName = args[0];
            DFSGraph graph = readFile( fileName );
            System.out.println( "The graph is: " );
            System.out.println( graph );
            prompt( graph );
        }
        else{
            System.err.println( "Usage: java PathFinder fileName" );
        }
    }

    /**
     * Prompt user for input. 
     */
    private static void prompt( DFSGraph graph ) throws IOException {
        try{
            BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
            String source = null, destination = null, path = null;
            System.out.println("Enter source (or 'quit') and destination when prompted." );
            while( true ) {
                System.out.println();
                System.out.print( "Source: " );
                source = br.readLine();

                if( source.toLowerCase().equals( "quit" ) ) {
                    break;
                }

                System.out.print( "Destination: ");
                destination = br.readLine();

                System.out.println();
                System.out.println( source + " to " + destination + " is: ");
                System.out.println( graph.findPath( source, destination ) );

                System.out.println();
            }
        }
        catch( IOException e ) {
            System.err.println( e );
            System.exit( -1 );
        }
    }

    /**
     *  Read a text file and create and return a DFSGraph object from the data.
     *
     * @return DFSGraph object 
     */
    private static DFSGraph readFile( String fileName ) throws IOException {
        String line; 
        BufferedReader br = null;
        DFSGraph graph = null;
        String[] nodes = null;
        try{
            br =  new BufferedReader ( new FileReader( fileName ) ) ; 
            graph  = new DFSGraph(); 

            while ( ( line = br.readLine() ) != null ) {
               // System.out.println( line );
                line = line.trim().replaceAll("\\s{2,}", " "); // remove excess spaces
                nodes = line.split(" "); // split the line into an array of strings
                //  Add source and destination node ( source -> destination )
                graph.add( nodes[0], nodes[1] );

            }
        }
        catch( IOException e ) {
            System.err.println( "Could not open file." );
            System.exit( -1 );
        }
        finally {
            if ( br != null ) {
                br.close(); // close the file
            }
        }

        return graph;

    }
}

/*
 * PathFinder.java
 * 
 * Version: 
 *     $Id: PathFinder.java,v 1.3 2013/10/30 01:01:23 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: PathFinder.java,v $
 *     Revision 1.3  2013/10/30 01:01:23  andy
 *     Changed readFile method
 *
 *     Revision 1.2  2013/10/29 17:42:23  andy
 *     Comments
 *
 *     Revision 1.1  2013/10/29 17:37:01  andy
 *     Initial revision
 *
 */