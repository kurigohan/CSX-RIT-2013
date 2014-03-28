/**
 * The Solver class solves a puzzle using a BFS. 
 *
 * @author Andy Nguyen
 */

import java.util.*;

public class Solver {

    Puzzle puzzle = null; // puzzle to solve

    /**
     * Contructor that takes a Puzzle.
     *
     * @param      p      puzzle to solve
     */
    public Solver( Puzzle p ) {
        puzzle = p;
    }

    /**
     * Finds the solution to the puzzle.
     * 
     * @return ArrayList containing the solution
     */
    public ArrayList<Integer> findSolution() {
        
        if( puzzle == null ) {
            return null;
        }

        int start = puzzle.getStart(); 
        int goal = puzzle.getGoal();
        boolean found = false;

        ArrayList<Integer> config = new ArrayList<Integer>();
        config.add( start ); 

        if( start == goal ) {
            return config;
        }


        ArrayList<Integer> nextConfig = null;
        ArrayList<Integer> current = null;
        ArrayList<ArrayList<Integer>> queue = new ArrayList<ArrayList<Integer>>();
        queue.add( config );


        while( queue.size() > 0 && found == false ) {
            current = queue.remove( 0 ); // dequeue the front element
            // loop through each neighbor of the last element in current
            for( int neighbor : puzzle.getNeighbors( current.get( current.size() - 1 ) ) )  {
                // create the next config with the neighbor of current
                nextConfig = new ArrayList<Integer>( current ); 
                nextConfig.add( neighbor ); 

                // check if the neighbor is the goal
                if( neighbor == goal ) {
                    current = nextConfig;
                    found = true;
                    break; // break out of the for loop
                }
                else { // if goal not found, enqueue the next config and continue the loop
                    queue.add( nextConfig );
                }
            } // end for loop
        } // end while loop

        if( found ) {
            return current;
        }
        else{ 
            return null;
        }
    }

    /**
     * Prints a solution in step-by-step format.
     *
     * @param      solution      the solution to print
     */
    public void printSolution( ArrayList<Integer> solution ) {
        int stepCount = 0; // step counter

        if( solution != null || solution.size() > 0 ) {
            for( int s : solution ) {
                System.out.println( "Step " + stepCount++ + ": " + s );
            }
        }
        else {
            System.out.println( "No solution." );
        }
    }

}


/*
 * Solver.java
 * 
 * Version: 
 *     $Id: Solver.java,v 1.3 2013/11/06 20:26:35 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: Solver.java,v $
 *     Revision 1.3  2013/11/06 20:26:35  andy
 *     Added error checks and comments.
 *
 *     Revision 1.2  2013/11/06 19:41:47  andy
 *     Implemented findSolution and printSolution methods.
 *
 *     Revision 1.1  2013/11/06 04:47:00  andy
 *     Initial revision
 *
 *
 */