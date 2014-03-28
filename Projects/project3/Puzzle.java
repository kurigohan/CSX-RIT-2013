/**
 * Puzzle interface that must be implemented by puzzles that use the Solver.
 *
 * @author Andy Nguyen
 */

import java.util.ArrayList;

public interface Puzzle<E> {
    /**
     * Get the starting config for the puzzle.
     *
     * @return the starting config
     */
    public E getStart();

    /**
     * Check if the config matches the goal config.
     * 
     * @return true if config matches goal, false otherwise
     */
    public boolean isGoal( E config );

    /**
     * For an incoming config, generate and return all direct neighbors to this config.
     *
     * @return the list of neighbors of the config
     */
    public ArrayList<E> getNeighbors( E config );

}

/*
 * Puzzle.java
 * 
 * Version: 
 *     $Id: Puzzle.java,v 1.2 2013/11/22 18:07:32 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: Puzzle.java,v $
 *     Revision 1.2  2013/11/22 18:07:32  andy
 *     Changed data types to generic
 *
 *     Revision 1.1  2013/11/22 18:01:31  andy
 *     Initial revision
 *
 *
 */ 