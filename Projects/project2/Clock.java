/**
 * The Clock class represents a clock puzzle. The Solver class is used to solve the puzzle.
 *
 * @author Andy Nguyen
 */

import java.util.*;

public class Clock implements Puzzle {
    
    private int start = 0; // start config
    private int goal = 0; /// goal config
    private int hours = 0; // hours on the clock

    /**
     * Main method that gets arguments from the user and solves the puzzle.
     */
    public static void main( String args[] ) throws NumberFormatException {
        if( args.length > 2 ) {
            try{
                int clockHours = Integer.parseInt( args[0] );
                int clockStart = Integer.parseInt( args[1] );
                int clockGoal = Integer.parseInt( args[2] );

                // make sure hours, goal, start are valid
                if( clockHours > 0 && clockStart > 0 && clockGoal > 0 ) { 
                    Clock clockPuzzle = new Clock( clockHours, clockStart, clockGoal );
                    Solver solver = new Solver( clockPuzzle );
                    solver.printSolution( solver.findSolution() );
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

    /**
     * Constructor that takes hours, start config, and goal config.
     *
     * @param      hours    hours on the clock
     * @param      goal      goal config    
     * @param      start      start config
     */
    public Clock( int hours, int start, int goal ) {
        this.hours = hours;
        this.start = start;
        this.goal = goal;
    }

    /**
     * @return start config
     */
    public int getStart() {
        return start;
    }

    /**
     * @return goal config 
     */
    public int getGoal() {
        return goal;
    }

    /**
     * @return numbers of hours on the clock
     */
    public int getHours() {
        return hours;
    }

    /**
     * Generate and return all direct neighbors for the given config.  
     *
     * @param      config      
     * @return array list of neighbors
     */
    public ArrayList<Integer> getNeighbors( int config ) {

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
     * @return string containing hours, start, and goal.
     */
    public String toString() {
        return "Hours: " + hours + "\nStart: " + start +"\nGoal: " + goal; 
    }

}


/*
 * Clock.java
 * 
 * Version: 
 *     $Id: Clock.java,v 1.3 2013/11/06 19:41:31 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: Clock.java,v $
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