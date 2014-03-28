
/*
 * Patient.java
 * 
 * Version: 
 *     $Id: Patient.java,v 1.6 2013/11/08 00:06:23 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: Patient.java,v $
 *     Revision 1.6  2013/11/08 00:06:23  andy
 *     Changed main to test automatically instead of taking input first.
 *
 *     Revision 1.5  2013/11/08 00:02:04  andy
 *     Spacing.
 *
 *     Revision 1.4  2013/11/08 00:00:13  andy
 *     Added more test cases.
 *
 *     Revision 1.3  2013/11/07 23:58:54  andy
 *     Fixed formatting.
 *
 *     Revision 1.2  2013/11/07 23:57:32  andy
 *     Implemented methods.
 *
 *     Revision 1.1  2013/11/07 23:25:04  andy
 *     Initial revision
 *
 *
 */


/**
 * A class to simulate a patient in a doctor's office. Objects of this
 * type know the first and last name and age of the patient, and will
 * contain a medication history.
 *
 * The medication history will contain Medication objects and must be
 * maintained in the same order the medication is prescribed.
 * 
 * @author Andy Nguyen
 * @author Lois Rixner
 * @author Trudy Howles tmh@cs.rit.edu
 */

import java.util.*;

public class Patient {
    String firstName = "";
    String lastName = "";
    int age = 0;
    // medication history 
    List<Medication> medRecord = new LinkedList<Medication>();

    /**
     * Main method used to test Patient methods
     */
    public static void main( String args[] ) {

        Patient p = new Patient( "Doe", "John", 21 );

        System.out.println("Patient Information: ");

        // test add
        p.recordNewMed( "MaryJ", false );
        p.recordNewMed( "Painkiller", true );
        p.recordNewMed( "Med A", true );
        p.recordNewMed( "Med B", true );
        System.out.println( "Medication count: " + p.getNumberOfMeds() );
        System.out.println( p );

        // test remove
        System.out.println( "Remove Painkiller.");
        if( p.removeMed( "Painkiller") ) {
            System.out.println( "Painkiller prescription removed." );
        }
        else{
            System.out.println( "Prescription does not exist." );
        }
        System.out.println();
        System.out.println( "Medication count: " + p.getNumberOfMeds() );
        p.printMedicationHistory();
        
        System.out.println();
        System.out.println( "Remove Advil." );
        if( p.removeMed( "Painkiller") ) {
            System.out.println( "Painkiller prescription removed." );
        }
        else{
            System.out.println( "Prescription does not exist." );
        }
        System.out.println();
        System.out.println( "Medication count: " + p.getNumberOfMeds() );
        p.printMedicationHistory();

    }

    /** 
    * Constructor for this object.
    *
    * @param myLast the last name of this patient
    * @param myFirst the first name of this patient
    * @age age - the age of this patient
    */

    public Patient( String myLast, String myFirst, int myAge) {
        lastName = myLast;
        firstName = myFirst;
        age = myAge;
    }


    /**
     * Return the patient's full name in the form: 
     * <br>
     *     last name comma first name.
     * 
     * @return the patient's full name.
     */

    public String getName() {
        return  lastName + " , " + firstName;
    }

    /** 
     * Return this patient's age.
     * 
     * @return the patient's age
     */

    public int getAge() {
        return age;
    }


    /**
    * Get the number of medications in this patient's history.
    *
    * @return the number of medications in this patient's history
    *
    */

    public int getNumberOfMeds() {
        return medRecord.size();
    }
 

    /**
    * Record a new medication for this patient.
    *
    * @param name	the name of this medication 
    * @param generic	boolean true = generic otherwise false
    *
    */

    public void recordNewMed(String name, boolean generic ) { 
        medRecord.add( new Medication( name, generic ) );

    }

    /**
    * Remove this medication for this patient.
    *
    * @param med     The medication to remove for this patient.
    *			If this medication is not found on this patient's
    *			medication list, return false.  Otherwise,
    *			remove the first occurrence of this medication.
    *
    * @return true if this medication was removed, otherwise false
    *
    */

    public boolean removeMed( String med ) {
        ListIterator<Medication> iter = medRecord.listIterator( 0 );

        while( iter.hasNext() ) {
            if( iter.next().getName().equals( med ) ) {
                iter.remove();

                return true;
            }
        }
        return false;
    }



    /**
    * Print all medications in this patient's medication history
    * in the order the medication was prescribed.
    *
    */

    public void printMedicationHistory () {              
        for( Medication m : medRecord ) {
            System.out.println( m );
        }

    }


    /**
     * Produce a printable version of this Patient object.
     * The format must be: the first and last names as formatted in 
     * the getName() method followed by a single space, then the age
     * followed by a new line.
     *
     * Format each medication recorded for this patient.  
     * Format medications by calling your toString() method in the Medication
     * class  and append a new line after each one.
     * Format medications in the order prescribed.
     * 
     * @return a String version of this Patient.
     */

    public String toString() {
        String str = getName() + " " + getAge() + "\n";

        for( Medication m : medRecord ) {
            str += m.toString() + "\n";
        }
        return  str;
     }

} // Patient

