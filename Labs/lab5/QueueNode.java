/*
 * QueueNode.java
 *
 * Version:
 *    $Id: QueueNode.java,v 1.1 2003/09/15 21:41:26 csx Exp csx $
 *
 * Revisions:
 *    $Log: QueueNode.java,v $
 *    Revision 1.1  2003/09/15 21:41:26  csx
 *    Initial revision
 *
 *    Revision 1.1  2003/09/14 17:55:05  tmh
 *    Initial revision
 *
 *
 */

/**
 * A class for creating generic nodes in a linked queue.
 * From a revised LinkedNode class from ptt.
 *
 * @author Paul Tymann
 * @author Trudy Howles 
 */

public class QueueNode {

    private Object data;      // The data in this queue node
    private QueueNode next;   // The next node in the queue;

    /**
     * Create a new queue node.
     */

    public QueueNode () {
        this( null, null );
    }

    /**
     * Create a queue node with the data passed as a parameter and
     * null in the next field.
     *
     * @param data the data to hold in this node.
     */

    public QueueNode ( Object data ) {
        this( data, null );
    }

    /**
     * Create a queue node with the data and link passed as a 
     * parameter.
     *
     * @param data the data to hold in this node.
     * @param next the next node in the queue.
     */

    public QueueNode ( Object data, QueueNode next ) {
        this.data = data;
        this.next = next;
    }

    /**
     * Return the link to the next node.
     *
     * @return the link to the next node.
     */

    public QueueNode getNext() {
        return next;
    }

    /**
     * Return the data stored in this node.
     *
     * @return the data stored in this node.
     */

    public Object getData() {
        return data;
    }

    /**
     * Set the link to the next node.
     *
     * @param next the new next node.
     */

    public void setNext( QueueNode next ) {
        this.next = next;
    }

    /**
     * Set the data stored in this node.
     *
     * @param data the new data in this node.
     */

    public void setData( Object data ) {
        this.data = data;
    }

} 






