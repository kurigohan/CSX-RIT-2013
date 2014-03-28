/**
 * DFSGraph represents a depth first search graph.
 *
 * @author Andy Nguyen
 */

import java.util.*;

public class DFSGraph implements Graph {
    Map<String, List<String>> graph;

    /*
        Main method which is used to test the class methods
    */
    public static void main( String args[] ) {
        String start, finish;
        if( args.length > 1 ){
            start = args[0];
            finish = args[1];

            DFSGraph test = new DFSGraph();
            // Test add and toString method
            test.add("A", "B");
            test.add("B", "A");

            test.add("A", "C");
            test.add("C", "A");

            test.add("C", "F");
            test.add("F", "C");

            test.add("B", "D");
            test.add("D", "B");

            test.add("A", "G");
            test.add("G", "A");

            test.add("G", "F");
            test.add("F", "G");

            test.add("F", "E");
            test.add("E", "F");

            test.add("A", "A");
            System.out.println( test );

           System.out.println( test.findPath( start ,  finish) );
        }
        else{
            System.err.println("Usage: DFSGraph start finish");
        }

    }

    /*  
        Default constructor for the DFSGraph. 
    */
   public DFSGraph( )  {
        graph = new HashMap<String, List<String>> (); 
   }


    /*
        Routine to find a path from "node" to "finish". The visited list
        keeps track of the nodes in the path. Returns true if a path is
        found, false otherwise.
    */
    public List<String> visitDFS( String node, List<String> visited, String finish ) {
        List<String> path = new ArrayList<String>();

        if( node.equals( finish ) ) {
            path.add( node );
            return path;
        }
        //check if node exists in graph
        if( graph.containsKey( node ) ) {
            // get list of neighbors
            List<String> neighbors = graph.get( node );
            //interate through neighbors and check if neighbor in visited
            for( String n : neighbors ) {
                if( visited.contains( n ) == false ) {
                        visited.add( n );
                        List<String> subpath = visitDFS( n, visited, finish );

                        // if valid subpath returned, add node to subpath and return 
                        // the new subpath
                        if( subpath.size() > 0 ){
                            subpath.add( 0, node );
                            return subpath;
                        }
                } // end if visited contains n
            } // end for
        } // end if neighbors.size > 0

        return path;

    }

    /*
        Find a path from "source" to "destination". Returns a comma 
        separated String listing each of the nodes in the path.
    */
    public String findPath( String start, String finish )  {
        List<String> visited = new ArrayList<String>();
        String pathStr = "" ;
        visited.add( start );   // add starting node to visited
        List<String> path = visitDFS( start, visited, finish ) ;
        if( path.size() > 0 ) {
            for( String node :  path ) {
                pathStr += node + ", ";
            }
            pathStr = pathStr.substring( 0, pathStr.length() - 2 );
        }
        else{
            pathStr = "None";
        }
        return pathStr;
    }


    /*
        Add "source" and "destination" nodes to the graph. Note that
        this is an undirected graph, so add the nodes in both directions.
    */
    public void add( String key, String value )  {
        List<String> neighbors = null; // list of neighbors for the node

        // if key exists in the hashmap, get the list and add value to it 
        if( graph.containsKey( key ) ){
            neighbors = graph.get( key );
            neighbors.add( value );
        }
        else { // otherwise create a new list containing value
            neighbors = new ArrayList<String> ();
            neighbors.add( value );
        }            

        graph.put( key, neighbors ); // add neighbors list to the graph 


        // if key exists in the hashmap, get the list and add value to it 
        if( graph.containsKey( value ) ){
            neighbors = graph.get( value );
            neighbors.add( key );
        }
        else { // otherwise create a new list containing value
            neighbors = new ArrayList<String> ();
            neighbors.add( key );
        }          
        graph.put( value, neighbors );  
    }



    /*
        Converts a graph to a string representation consisting of the node,
        followed by a ':' followed by a ' ' followed by a comma separated
        list of neighbors.
    */
    public String toString( )  {
        String str = "";
        String node;
        for( Map.Entry<String, List<String>> entry : graph.entrySet() ) {
            node = entry.getKey();
            str += node + ": ";
            for( String neighbor : entry.getValue() ) {
                str += neighbor + ", ";
            }
            // remove trailing comma and add new line
            str = str.substring( 0, str.length() - 2 ) + "\n";
        }
    
        return str;
    }

}


/*
 * DFSGraph.java
 * 
 * Version: 
 *     $Id: DFSGraph.java,v 1.7 2013/10/30 01:01:59 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: DFSGraph.java,v $
 *     Revision 1.7  2013/10/30 01:01:59  andy
 *     Changed add method.
 *
 *     Revision 1.6  2013/10/29 17:46:37  andy
 *     Comments
 *
 *     Revision 1.5  2013/10/29 16:36:02  andy
 *     Fixed print format
 *
 *     Revision 1.4  2013/10/29 16:25:33  andy
 *     changed visitDFS to return path from start to finish
 *
 *     Revision 1.3  2013/10/29 15:57:38  andy
 *     implemented visitDFS with boolean return type
 *
 *     Revision 1.2  2013/10/29 14:46:32  andy
 *     Implemented add and toString method.
 *
 *     Revision 1.1  2013/10/29 13:44:20  andy
 *     Initial revision
 *
 *
 */