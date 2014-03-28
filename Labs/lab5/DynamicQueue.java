/**
 * DynamicQueue is a Queue that has a dynamic size so it will never be full.
 *
 * @author Andy Nguyen
 */

public class DynamicQueue implements Queue{

    QueueNode head;
    QueueNode tail;

    /**
     * Main method used to test DynamicQueue methods.
     *
     *@param      args      String array of command arguments
     */

    public static void main( String args[] ) throws QueueException{

        class UnitTest {
            String PASS = "PASSED";
            String FAIL = "FAILED";
            Integer testData = new Integer( 1 );
            private void run() {
                System.out.println("DynamicQueue Test Results: ");
                System.out.println("==================");
                System.out.println( "testEmpty: " + testEmpty() );
                System.out.println( "testFull: " + testFull() );
                System.out.println( "testEnqueue: " + testEnqueue() );
                System.out.println( "testDequeue " + testDequeue() );
                System.out.println( "testFront: " + testFront() );
                System.out.println( "testRear: " + testRear() );
            }

            private String testEnqueue( ) {
                String passed = PASS;
                try {
                    DynamicQueue test = new DynamicQueue();
                    test.enqueue( testData );
                    for( int i = 0; i < 10; ++i ) {
                        test.enqueue( i ) ;
                    }
                }
                catch( QueueException e ){
                    passed = FAIL;
                }
                return passed;
            }


            private String testDequeue() {
                DynamicQueue test = new DynamicQueue( testData );

                // dequeue a non empty DynamicQueue
                try {
                    test.dequeue();
                }
                catch( QueueException e ) {
                    return FAIL;
                }
                // dequeue an empty DynamicQueue
                try {
                    test.dequeue();
                    return FAIL;
                }
                catch( QueueException e ) { }

                return PASS;
            }


            private String testFront( ) {
                String passed = PASS;
                DynamicQueue test = new DynamicQueue( testData );

                try {            
                    if( test.front() != testData) { // check front is equal to testData
                        passed = FAIL;
                    }
                }
                catch( QueueException e ){
                    passed = FAIL;
                }

                // test front() with an empty DynamicQueue
                try{
                    test = new DynamicQueue();
                    test.front();
                    passed = FAIL;
                }
                catch( QueueException e ) {}

                return passed;
            }


            private String testRear( ) {
                String passed = PASS;
                DynamicQueue test = new DynamicQueue( testData );
                try {
                    test.rear();
                    if( test.rear() != testData) { // check rear is equal to testData
                        passed = FAIL;
                    }
                }
                catch( QueueException e ){
                    passed = FAIL;
                }


                // test empty() with an empty DynamicQueue
                try{
                    test = new DynamicQueue();
                    test.rear();
                    passed = FAIL;
                }
                catch( QueueException e ) {}

                
                return passed;
            }

            private String testEmpty() {
                DynamicQueue test = new DynamicQueue();
                if( test.empty() ) {
                    return PASS;
                }
                return FAIL;
            }

            private String testFull() {
                DynamicQueue test = new DynamicQueue( testData );
                if( !test.full() ) {
                    return PASS;
                }
                return FAIL;
            }

        }

        try{
            // user test mode if args given
            if( args.length > 0 ) {
                DynamicQueue test = new DynamicQueue();
                for( String arg : args ) {
                    test.enqueue( arg );
                    System.out.println("Enqueued: " + test.rear() );
                }
            System.out.println( "Front: " + test.front() );
            System.out.println( "End: " + test.rear() );
            }
            else {
                // use automatic tests if no args
                UnitTest tester = new UnitTest();
                tester.run();
                /*
                final int MAXINT = 10; // numbers of intergers to add to the DynamicQueue
                DynamicQueue testQueue = new DynamicQueue();
                System.out.println( "DynamicQueue created.");
                System.out.println( "Full: " + testQueue.full() );
                System.out.println( "Empty: " + testQueue.empty() );

                System.out.println( "===============" );
                System.out.println( "Adding Integers..." );
                // enqueue  integers 
                for( int i = 1; i < MAXINT; ++i ) {
                    testQueue.enqueue( i );
                    System.out.println( testQueue.rear() );
                }
                System.out.println( "Full: " + testQueue.full() );
                System.out.println( "Empty: " + testQueue.empty() );
                System.out.println( "Front: " + testQueue.front() );
                System.out.println( "Rear: " + testQueue.rear() );*/
            }
        }
        catch( QueueException e ){
            System.err.println( e.getMessage() );
        }
    }


    /**
     *  Default constructor for the DynamicQueue.
     */
    public DynamicQueue() {
        head =  null;
        tail = null;
    }

    /**
     *  Constructor for the DynamicQueue.
     * @param      data      object to start the Dynamic Queue with
     */
    public DynamicQueue( Object data ) {
        head = new QueueNode( data );
        tail = head;
    }

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
     * @param data Object to put into the queue.
     * @exception QueueException is thrown if the queue is full.
     */
    public void enqueue( Object data ) throws QueueException {

        if( full() ) {
            throw new QueueException( "enqueue(): queue full" );
        }

        QueueNode newNode = new QueueNode( data );

        if( empty() ) {
            head = tail = newNode;
        }
        else {
            tail.setNext( newNode ); 
            tail = newNode;
        }

    }

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
     * @exception QueueException is thrown if the queue is empty.
     */
    public void dequeue() throws QueueException {
        if( empty() ) {
            throw new QueueException( "dequeue(): queue empty" );
        }
        head.setNext( null );
        head = head.getNext();
    }

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
    public Object front() throws QueueException {
        if( empty() ) {
            throw new QueueException( "front(): queue empty" );
        }
        return head.getData();
    }

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
    public Object rear() throws QueueException{
        if( empty() ) {
            throw new QueueException( "rear(): queue empty" );
        }
        return tail.getData();
    }

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
    public boolean empty() {
        if( head == null || tail == null ) {
            return true;
        }
        return false;
    }
    
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
    public boolean full() {
        // DynamicQueue should never be never full.
        return false;
    }

}


/*
 * DynamicQueue.java
 * 
 * Version: 
 *     $Id: DynamicQueue.java,v 1.6 2013/09/28 00:57:13 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: DynamicQueue.java,v $
 *     Revision 1.6  2013/09/28 00:57:13  andy
 *     Changed main to use UnitTest class
 *
 *     Revision 1.5  2013/09/27 23:58:06  andy
 *     Changed error handling
 *     Changed main
 *
 *     Revision 1.4  2013/09/27 23:38:54  andy
 *     Added main method for testing
 *     Changed front and rear methods to return the Object instead of the QueueNode
 *
 *     Revision 1.3  2013/09/27 23:19:11  andy
 *     Changed dequeue method and implemented constructor.
 *
 *     Revision 1.2  2013/09/27 22:39:16  andy
 *     Implemented enqueue and dequeue methods.
 *
 *     Revision 1.1  2013/09/27 22:33:41  andy
 *     Initial revision
 *
 */
