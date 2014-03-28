

public class StoreLocationTester {
    

    public static ArrayList<int[]> readFile( String fileName ) throws IOException, NumberFormatException {
        String[] strLocations = null; // set of locations as a string
        int[] intLocations = null; // set of locations as a number
        ArrayList<int[]> allLocations = new ArrayList<int[]>(); // all sets of locations
        String line; 
        BufferedReader br = null;

        try{
            br =  new BufferedReader ( new FileReader( fileName ) ) ; 
            while ( (line = br.readLine()) != null ) {
                line = line.trim().replaceAll("\\s{2,}", " ");
                strLocations = line.split(" ");

                intLocations = new int[ strLocations.length ];
                for( int i = 0; i < strLocations.length; ++i ) {
                    intLocations[ i ] = Integer.parseInt( strLocations[ i ] );
                }
                for( int i : intLocations ) {
                    System.out.println( i + " ");
                }
                allLocations.add( intLocations );

            }
        }
        catch( IOException e ) {
            System.err.println( "Could not open file." );
            System.exit( -1 );
        }
        catch( NumberFormatException e ){
            System.err.println( "Line contains invalid number." );
            System.exit( -1 );
        }
        finally {
            if ( br != null ) {
                br.close(); // close the file
            }
        }


        return allLocations;
    }

}



/*
 * StoreLocationTester.java
 * 
 * Version: 
 *     $Id$
 * 
 * Revisions: 
 *     $Log$
 *
 *
 */