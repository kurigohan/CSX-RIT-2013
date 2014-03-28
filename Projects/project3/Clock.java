/**
 * The Clock class represents a clock puzzle as described below: 
 *
 * The clock only has an hour hand, where N is the number of hours, which can 
 * be turned forward or backward one hour at a time (called a step). There are 
 * three parameters  associated with the clock:
 *
 *  - The number of hours, an integer N
 *  - The start time, an integer from 1..N
 *  - The goal time, an integer from 1..N
 *
 * The goal is to find the shortest path from the start time to the goal time.
 *
 * @author Andy Nguyen
 */

import java.util.ArrayList;
import java.util.HashSet;

public class Clock implements Puzzle<Integer> {
    
    private int start = 0; // start config
    private int goal = 0; /// goal config
    private int hours = 0; // hours on the clock


    /**
     * Constructor that takes hours, start config, and goal config.
     *
     * @param      myHours    hours on the clock
     * @param      myGoal      goal config    
     * @param      myStart      start config
     */
    public Clock( int myHours, int myStart, int myGoal ) {
        hours = myHours;
        start = myStart;
        goal = myGoal;
    }

    /**
     * @return start config
     */
    public Integer getStart() {
        return start;
    }

    /**
     * @return true if config = goal
     */
    public boolean isGoal( Integer config ) {
        return config == goal;
    }

    /**
     * Generate and return all direct neighbors for the given config.  
     *
     * @param config       puzzle config     
     * @return array list of neighbors
     */
    public ArrayList<Integer> getNeighbors( Integer config ) {

        int forward, backward; // neighbors
        // create list of neighbors
        ArrayList<Integer> neighbors = new ArrayList<Integer>();

        // an empty list will be returned if start/goal is not <= hours
        if( start <= hours && goal <= hours ) {
            if( config == hours ) {
                forward = 1;
                backward = config - 1;
            }
            else if( config == 1 ) {
                forward = config + 1;
                backward = hours;
            }
            else {
                forward = config + 1;
                backward = config - 1;
            }

            neighbors.add( forward );
            neighbors.add( backward );
        }
        
        return neighbors;
    }


    /**
     * Prints the solution to the puzzle.
     *
     * @param solution      solution to print
     */
    public void printSolution( ArrayList<Integer> solution ) {
        int stepCount = 0; // step counter
        if( solution.size() > 0 ) {
            for( Integer i : solution ) {
                System.out.println( "Step " + stepCount++ + ": " + i );
            }
        }
        else {
            System.out.println( "No solution." );
        }
    }


    /**
     * @return string containing hours, start, and goal.
     */
    public String toString() {
        return "Hours: " + hours + "\nStart: " + start +"\nGoal: " + goal; 
    }



    /**
     * Main method that gets arguments from the user and solves the puzzle.
     *
     * @param args      command line arguments
     */
    public static void main( String args[] ) throws NumberFormatException {
        if( args.length > 2 ) {
            try{
                int clockHours = Integer.parseInt( args[0] );
                int clockStart = Integer.parseInt( args[1] );
                int clockGoal = Integer.parseInt( args[2] );

                // make sure hours, goal, start are valid
                if( (clockHours > 0 && clockStart > 0 && clockGoal > 0) && 
                        (clockHours >= clockStart && clockHours >= clockGoal) ) { 
                    
                    Clock clockPuzzle = new Clock( clockHours, clockStart, clockGoal );
                    Solver<Integer> solver = new Solver<Integer> ( clockPuzzle );

                    clockPuzzle.printSolution( solver.solve() );
                }
                else {
                    System.err.println( "Invalid arguments." );
                }
            }
            catch( NumberFormatException e ) {
                System.err.println( "Invalid arguments." );
            }
        }
        else {
            System.err.println( "Usage: java Clock hours start goal" );
        }
    }

}


/*
 * Clock.java
 * 
 * Version: 
 *     $Id: Clock.java,v 1.7 2013/11/22 22:10:25 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: Clock.java,v $
 *     Revision 1.7  2013/11/22 22:10:25  andy
 *     Added printSolution method (moved from Solver)
 *
 *     Revision 1.6  2013/11/22 20:16:13  andy
 *     Added more detailed comments
 *
 *     Revision 1.5  2013/11/22 19:07:43  andy
 *     Changed main method check if hours > start and goal
 *
 *     Revision 1.4  2013/11/22 18:23:30  andy
 *     Changed methods to match new puzzle interface for part 2
 *
 *     Revision 1.3  2013/11/06 19:41:31  andy
 *     Implemented main method.
 *     Added error checking.
 *
 *     Revision 1.2  2013/11/06 18:41:10  andy
 *     Implemented methods.
 *
 *     Revision 1.1  2013/11/06 04:45:08  andy
 *     Initial revision
 *
 *
 */