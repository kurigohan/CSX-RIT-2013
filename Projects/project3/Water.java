/**
 * The Water class represents a water puzzle.  The water puzzle poses the following problem:
 *
 * Assume you are at a lake and have a collection of empty jugs that can each hold different 
 * amounts of water. The goal is to get a desired amount of water in any one of the jugs using 
 * the shortest number of steps. A single step consists of  one of the following actions:
 * 
 *  - Fill a single jug up to complete capacity by submerging it in the lake.
 *  - Empty a single jug by dumping all water in the jug into the lake.
 *  - Pour the contents of any one jug into another jug, without exceeding the capacity of the 
 *    jug being poured into.
 *
 * @author Andy Nguyen
 */

import java.util.ArrayList;

public class Water implements Puzzle<ArrayList<Integer>>{

    private int goalAmount = 0;
    private ArrayList<Integer> jugsCapacity = null;

    /**
     * Constructor for Water.
     *
     * @param      myAmount    goal amount
     * @param      myJugs          list of jugs    
     */
    public Water( int myAmount, ArrayList<Integer> myJugs ) {
        goalAmount = myAmount;
        jugsCapacity = myJugs;
    }

    /**
     * @return the starting jugs config (all jugs empty)
     */
    public ArrayList<Integer> getStart(){
        ArrayList<Integer> start = new ArrayList<Integer>( jugsCapacity.size() );

        for( int i = 0; i < jugsCapacity.size(); ++i ) {
            start.add( 0 );
        }
        return  start;
    }

    /**
     * @return true if config contains the goal
     */
    public boolean isGoal( ArrayList<Integer> config ){
        return config.indexOf( goalAmount ) > -1 ? true : false ;
    }

    /**
     * Generate and return all direct neighbors for the given jugs config.  
     * 
     *
     * @param config       puzzle config     
     * @return the list of neighbors
     */
    public ArrayList<ArrayList<Integer>> getNeighbors( ArrayList<Integer> config ) {
        // list of neighbor configs
        ArrayList<ArrayList<Integer>> neighbors = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> possibleConfig; 
        int currentJug; 

        for( int i = 0; i < config.size(); ++i ) {
            currentJug = config.get( i );
            if( currentJug > 0 ) {
                 // ****  emptying the jug ****
                // restore the config to the original to the original given config
                possibleConfig = new ArrayList<Integer>( config ); 
                possibleConfig.set( i, 0 );
                neighbors.add( possibleConfig );

                // **** pouring one jug into another jug ****
                int total; // total water amount after pouring
                int limit; // max capacity of the receiving jug
                int receiver; // current capacity of receiving jug
                int pourer; //  capacity of pouring jug

                for( int j = 0; j < config.size(); ++j ) {
                    if( j != i ) { // skip over the current jug
                        pourer = currentJug;
                        // restore the config to the original  given config
                        possibleConfig = new ArrayList<Integer>( config );

                        limit = jugsCapacity.get( j );
                        receiver = possibleConfig.get( j );

                        // check if the jug to fill is full
                        if( receiver < limit ) {
                            total = pourer + receiver;
                            // check if pouring causes jug to exceed its capacity
                            if( total > limit ) {
                                receiver = limit;
                                pourer = total - limit;
                            }
                            else{
                                receiver = total;
                                pourer = 0;
                            }

                            possibleConfig.set( j, receiver );
                            possibleConfig.set( i, pourer );
                            neighbors.add( possibleConfig );
                        } // end inner foor loop
                    }
                } // end outer for loop

            }

            if( currentJug < jugsCapacity.get(i) ) {
                // **** filling the jug ****
                possibleConfig = new ArrayList<Integer>( config );
                possibleConfig.set( i, jugsCapacity.get( i ) );
                neighbors.add( possibleConfig );
            }
        } // end for loop that interates through the config

        return neighbors;
    }

    /**
     * Prints the solution to the puzzle.
     *
     * @param solution      solution to print
     */
    public void printSolution( ArrayList<ArrayList<Integer>> solution ) {
        int stepCount = 0; // step counter
        String str = "";
        if( solution.size() > 0 ) {
            for( ArrayList<Integer> s : solution ) {
                str += "Step " + stepCount++ + ": ";
                for( Integer i : s ) {
                    str += i + " ";
                }
                str += "\n";
            }
            str = str.trim();
        }
        else {
            str = "No solution.";
        }

        System.out.println( str );
    }



    /**
     * @return string containing goalAmount and jugsCapacity.
     */
    public String toString() {
        return "Goal: " + goalAmount + "\nJugs: " +  jugsCapacity;
    }



    /**
     * Main method that gets arguments from the user and solves the puzzle.
     *
     * @param args      command line arguments
     */
    public static void main( String args[] ) throws NumberFormatException{
        if( args.length > 1 ) {
            try{
                int goal = Integer.parseInt( args[0] );

                // create list of jugs from the command arguments
                ArrayList<Integer> jugs = new ArrayList<Integer>( args.length );
                for( int i = 1; i < args.length; ++i ) {
                    jugs.add( Integer.parseInt( args[i] ) );
                }

                Water puzzle = new Water( goal, jugs );
                Solver<ArrayList<Integer>> solver = new Solver<ArrayList<Integer>>( puzzle );
                // find and print solution
                puzzle.printSolution( solver.solve() );


            }
            catch( NumberFormatException e ) {
                System.err.println( "Invalid arguments." );
            }
        }
        else {
            System.err.println( "Usage: java Water amount jug1 jug2 ..." );
        }
    }
}


/*
 * Water.java
 * 
 * Version: 
 *     $Id: Water.java,v 1.8 2013/11/23 01:18:44 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: Water.java,v $
 *     Revision 1.8  2013/11/23 01:18:44  andy
 *     Changed printSolution
 *
 *     Revision 1.7  2013/11/23 00:25:40  andy
 *     Changed some methods for efficiency
 *
 *     Revision 1.6  2013/11/22 22:11:07  andy
 *     Added printSolution and toString method.
 *
 *     Revision 1.5  2013/11/22 21:51:10  andy
 *     implement isGoal method
 *
 *     Revision 1.4  2013/11/22 21:47:20  andy
 *     testing
 *
 *     Revision 1.3  2013/11/22 21:45:07  andy
 *     Implemented methods
 *
 *     Revision 1.2  2013/11/22 19:48:40  andy
 *     Skeleton
 *
 *     Revision 1.1  2013/11/21 20:59:46  andy
 *     Initial revision
 *
 *
 */ 