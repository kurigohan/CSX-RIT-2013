/**
 * Queue.java
 *
 * version:
 *    $Id: Queue.java,v 1.1 2003/09/15 21:41:26 csx Exp csx $
 *
 * revision:
 *    $Log: Queue.java,v $
 *    Revision 1.1  2003/09/15 21:41:26  csx
 *    Initial revision
 *
 *    Revision 1.1  2003/09/14 17:55:05  tmh
 *    Initial revision
 *
 */

/**
 * This defines an interface for a Queue.
 *
 * @author Trudy Howles, based on various prototypes
 *
 */

public interface Queue {

    /**
     * Places the given object at the rear of the Queue.
     *
     * Preconditions:
     *    The queue is not full.
     *
     * Postconditions:
     *    The object has been added to the rear of the queue.
     *    The size of the queue has increased by one.
     *    No other structure of the queue has changed.
     *
     *    If throwing an exception, use the string:
     *    "enqueue(): queue full"
     *
     * @param data Object to put into the queue.
     * @exception QueueException is thrown if the queue is full.
     */
    public void enqueue( Object data ) throws QueueException;

    /**
     *
     * Removes the element at the front of the queue.
     *
     * Preconditions:
     *    The queue is not empty.
     *
     * Postconditions:
     *    The size of the queue has decreased by one.
     *    The item at the front of the queue has been removed.
     *    No other structure of the queue has changed.
     *
     *    If throwing an exception, use the string:
     *    "dequeue(): queue empty"
     *
     * @exception QueueException is thrown if the queue is empty.
     */
    public void dequeue() throws QueueException;

    /**
     * Return the element at the front of the queue.
     *
     * Preconditions:
     *    The queue is not empty.
     *
     * Postconditions:
     *    The queue is unchanged.
     *
     *    If throwing an exception, use the string:
     *    "front(): queue empty"

     *
     * @return the element at the front of the queue.
     * @exception QueueException is thrown if the queue is empty.
     */
    public Object front() throws QueueException;

    /**
     * Return the element at the rear of the queue.
     *
     * Preconditions:
     *    The queue is not empty.
     *
     * Postconditions:
     *    The queue is unchanged.
     *
     *    If throwing an exception, use the string:
     *    "rear(): queue empty"
     *
     * @return the element at the rear of the queue.
     * @exception QueueException is thrown if the queue is empty.
     */
    public Object rear() throws QueueException;

    /**
     * Determine if the queue is empty.
     *
     * Preconditions:
     *    None
     *
     * Postconditions:
     *    The queue is unchanged.
     *
     * @return true if the queue is empty and false otherwise.
     */
    public boolean empty();

    /**
     * Determine if the queue is full.
     *
     * Preconditions:
     *    None.
     *
     * Postconditions:
     *    The queue is unchanged.
     *
     * @return true if the queue is full and false otherwise.
     */
    public boolean full();
}
