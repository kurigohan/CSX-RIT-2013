/**
 * Puzzle interface that must be implemented by puzzles that use the solver.
 */

import java.util.*;

public interface Puzzle {
    // Get goal config for the puzzle.
    public int getStart();

    // For an incoming config, generate and return all direct neighbors to this config.
    public int getGoal();

    // Get starting config for the puzzle.
    public ArrayList<Integer> getNeighbors( int config );

}