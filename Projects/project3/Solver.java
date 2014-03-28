/**
 * The Solver class solves a puzzle using a BFS. 
 *
 * @author Andy Nguyen
 */

import java.util.ArrayList;
import java.util.HashSet;

public class Solver<E> {
    Puzzle<E> puzzle = null; // puzzle to solve

    /**
     * Contructor for the Solver. Takes a puzzle.
     *
     * @param myPuzzle      puzzle to solve
     */
    public Solver( Puzzle<E> myPuzzle ) {
        puzzle = myPuzzle;
    }

    /**
     * Finds the solution to the Puzzle.
     * 
     * @return ArrayList containing the solution
     */
    public ArrayList<E> solve() {
        ArrayList<E> solution = new ArrayList<E>();
        // check for valid puzzle
        if( puzzle == null ) {
            return solution;
        }

        E start = puzzle.getStart(); 

        boolean found = puzzle.isGoal( start ) ; // indiciates if goal reached (solution found)
    
        ArrayList<E> nextConfig = null; // next config
        ArrayList<E> current = new ArrayList<E>(); // current config
        current.add( start );

        // list of paths that need to be visited
        ArrayList<ArrayList<E>> queue = new ArrayList<ArrayList<E>>(); 
        queue.add( current );

        HashSet<E> visited = new HashSet<E>(); // set of visited configs
        visited.add( start );

        while( queue.size() > 0 && found == false ) {
            current = queue.remove( 0 ); // dequeue the front element
            // loop through each neighbor of the last element in current
            for( E neighbor : puzzle.getNeighbors( current.get( current.size() - 1 ) ) )  {
                // check if neighbor already visited
                if( visited.contains( neighbor ) == false ) {
                    // create the next config that contains current + the neighbor of current
                    nextConfig = new ArrayList<E>( current ); 
                    nextConfig.add( neighbor ); 

                    // check if the neighbor is the goal
                    if( puzzle.isGoal( neighbor ) ) {
                        current = nextConfig;
                        found = true;
                        break; // break out of the for loop
                    }
                    else { // if goal not found, enqueue the next config and continue the loop
                        queue.add( nextConfig );
                        visited.add( neighbor );
                    }
               } 
            } // end for loop
        } // end while loop

        if( found ) {
            return solution = current;
        }
        else{ 
            return solution;
        }
    }

}


/*
 * Solver.java
 * 
 * Version: 
 *     $Id: Solver.java,v 1.5 2013/11/22 19:31:20 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: Solver.java,v $
 *     Revision 1.5  2013/11/22 19:31:20  andy
 *     Added hashset to track visited configs in findSolution
 *
 *     Revision 1.4  2013/11/22 19:21:28  andy
 *     Removed redundent code in findSolution
 *
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