/**
 * PatientComparator  implements the Comparator interfece to compare two Patient objects
 * using getName()
 * 
 * @author Andy Nguyen
 */

import java.util.*;

 public class PatientComparator  implements Comparator<Patient> {

    /**
     * Main method to test the PatientComparator
     */
    public static void main(String args[] ) {
        PatientComparator pc = new PatientComparator();
        // Compare same patients
        pc.testCompare( new Patient( "doe", "john", 21 ), new Patient( "doe", "john", 21 ) );
        // Compare same patients with different letter case
        pc.testCompare( new Patient( "DOE", "JOHN", 21 ), new Patient( "doe", "john", 21 ) );
        // Compare different patients
        pc.testCompare( new Patient( "nguyen", "andy", 21 ), new Patient( "doe", "john", 42 ) );
        // Compare patients with same last name but different first name
        pc.testCompare( new Patient( "nguyen", "andy", 21 ), new Patient( "nguyen", "ashley", 19 ) );
    }

    /**
     * Test the compare method.
     *
     * @param        p1       patient
     * @param        p2       patient to compare to
     */
    public void testCompare ( Patient p1, Patient p2 ) {
        System.out.print( "Compare " + p1.getName() + " to " + p2.getName() + ": " );
        System.out.println( compare( p1, p2 ) );
    }


    /**
     * Compares the names of two patients
     *
     * @param        p1       patient
     * @param        p2       patient to compare to
     */
    public int compare( Patient p1, Patient p2 ) throws ClassCastException {
        if( p1 instanceof Patient == false || p2 instanceof Patient == false ) {
            throw new ClassCastException();
        }

        return p1.getName().toLowerCase().compareTo( p2.getName().toLowerCase() );
    }

 }



/*
 * PatientComparator.java
 * 
 * Version: 
 *     $Id: PatientComparator.java,v 1.4 2013/11/12 03:40:32 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: PatientComparator.java,v $
 *     Revision 1.4  2013/11/12 03:40:32  andy
 *     Comments
 *
 *     Revision 1.3  2013/11/12 02:13:06  andy
 *     Added main method.
 *
 *     Revision 1.2  2013/11/08 00:12:24  andy
 *     Skeleton
 *
 *     Revision 1.1  2013/11/08 00:08:41  andy
 *     Initial revision
 *
 *
 */