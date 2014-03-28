
 /**
 * DoctorsOffice.java
 *
 *  $Id: DoctorsOffice.java,v 1.9 2013/11/12 04:21:28 andy Exp andy $
 *
 *  Revisions:
 *  $Log: DoctorsOffice.java,v $
 *  Revision 1.9  2013/11/12 04:21:28  andy
 *  Added exception for removePatient()
 *
 *  Revision 1.8  2013/11/12 04:18:46  andy
 *  Implemented removePatient() and listInactive() for activity 4
 *
 *  Revision 1.7  2013/11/12 03:57:41  andy
 *  Implemented listByAge() for activity 3
 *
 *  Revision 1.6  2013/11/12 03:40:14  andy
 *  Implemented listByName for activity 2
 *
 *  Revision 1.5  2013/11/12 03:05:50  andy
 *  comments
 *
 *  Revision 1.4  2013/11/12 03:04:00  andy
 *  Modified printMedicationDetail to fit requirements.
 *
 *  Revision 1.3  2013/11/12 02:59:07  andy
 *  Added main method for testing.
 *
 *  Revision 1.2  2013/11/12 02:50:26  andy
 *  Implented add methods and print methods.
 *
 *  Revision 1.1  2013/11/07 22:45:28  andy
 *  Initial revision
 *
 *
 *
 *
 *
 **/

import java.util.*;

/**
 * A simple class to simulate the operation of a doctor's office
 * for tracking patients and medication.
 *
 * This class assigns patient identification numbers.  ID numbers start
 * at 1000 and increment by one for each new Patient object added to
 * the database.
 *
 * This class maintains two databases:  One for the current Patients
 * (the master database) and one for the inactive patients. 
 *
 * @author      Andy Nguyen
 * @author      Trudy Howles tmh@cs.rit.edu
 *
**/

public class DoctorsOffice {
    private String officeName = ""; // office name
    private int nextPatientId = 1000; // next patientId to be assigned
    // database for store Patient objects. The patientId is used as the key.
    private Map<Integer, Patient> database = 
                                                new LinkedHashMap<Integer, Patient>();
    private Map<Integer, Patient> inactive = 
                                                new LinkedHashMap<Integer, Patient>();                    



    public static void main( String args[] ) throws NoSuchPatientException {

        try {
            DoctorsOffice testOffice = new DoctorsOffice( "Test" );

            // activity 1 test
            System.out.println( "---------- Activity 1: Add/Print Patient ----------" );
            System.out.println( "New patient added" );
            int patientId = testOffice.addPatient( "john", "doe", 22 );
            System.out.println( "Patient ID: " + patientId );
            testOffice.addMedication( patientId, "advil", false );
            testOffice.addMedication( patientId, "pain killers", true );
            testOffice.printMedicationDetail( patientId );

            System.out.println( "New patient added" );
            patientId = testOffice.addPatient( "deer", "doe", 21 );
            System.out.println( "Patient ID: " + patientId );
            testOffice.printMedicationDetail( patientId );

           // System.out.println();
            //testOffice.printMedicationDetail( 0 );

            // activity 2 test (name comparator)
            System.out.println();
            System.out.println( "---------- Activity 2: List by name ----------" );
            testOffice.addPatient( "joe" , "smith", 34 );
            testOffice.addPatient( "Dan",  "Thomas", 40 );
            testOffice.addPatient( "Nick", "Anderson", 60 );
            testOffice.listByName();

            // activity 3 test (age comparator) 
            System.out.println( "---------- Activity 3: List by age ----------" );
            testOffice.listByAge();

            // activity 4 test (inactive patients) 
            System.out.println("---------- Activity 4: Remove Patients ----------" );
            testOffice.removePatient( 1001 );
            System.out.println( "Patient 1001 removed." );
            testOffice.removePatient( 1003 );
            System.out.println( "Patient 1003 removed. " );
            System.out.println( "==== Active Patients ====" );
            testOffice.listByName();
            System.out.println( "==== Inactive Patients ====" );
            testOffice.listInactive();

            testOffice.removePatient( 0 );
        }
        catch( NoSuchPatientException e ) {
            System.err.println( e );
        }
    }


    /**
     *  Constructor for a DoctorsOffice object.
     *
     *  @param name             Name of this Dr's Office
     *
     **/

    public DoctorsOffice (String name) {
        officeName = name;

    }

    /**
     * Add a new patient to the office.  The identification
     * number is uniquely generated and is returned when the
     * Patient object is added to the database.  ID numbers
     * start at 1000 and increment by one for each new Patient
     * added.
     *
     *  @param firstName     first name of this patient 
     *  @param lastName      last name of this patient 
     *  @param age           age of this patient
     *  @return the ID number assigned to this Patient
     **/

    public int addPatient (String firstName, String lastName, int age) {
        int patientId = nextPatientId++; // increment nextPatientId

        database.put( patientId, new Patient( lastName, firstName, age ) );

        return patientId;

    }

    /**
     * Remove this patient from the master database.  Removed patients are
     * archived in an "inactive" database which maintains Patron
     * objects in the order in which they were removed from the master
     * database.
     * 
     * If throwing an exception, use "removePatient()" as the message.
     * 
     *  @param patientNo	Patient number assigned
     *  @exception              throws a NoSuchPatientException
     *                          if this patient does not exist
     **/

    public void removePatient ( int patientNo ) 
            throws NoSuchPatientException {
        if( database.containsKey( patientNo ) ) {
            Patient patient = database.get( patientNo );
            database.remove( patientNo );
            inactive.put( patientNo, patient );
        }
        else {
            throw new NoSuchPatientException( "removePatient()" );
        }
    }


    /**
     *  Add a new medication for this patient.
     *
     * If throwing an exception, use "addMedication()" as the message.
     *
     *  @param patientNo	Patient number
     *  @param medicationName	Name of this medication
     *  @param isGeneric	True if a generic drug
     *  @exception              throws NoSuchPatientException if 
     *                          this patient ID does not exist.
     **/

    public void addMedication(int patientNo, String medicationName,
	boolean isGeneric ) throws NoSuchPatientException {

        // check if patient exists
        if( database.containsKey( patientNo ) ) {
            Patient patient = database.get( patientNo ); 
            patient.recordNewMed( medicationName, isGeneric );
            database.put( patientNo, patient );
        }
        else{
            throw new NoSuchPatientException( "addMedication()" );
        }

    }

    /**
     * Print the medication detail for this patient. Print
     * the patient's full name (lastname COMMA SPACE firstName)
     * then each medication (each one on a new line).  To print
     * the medications, simply call your toString() method in
     * the Medication class.  
     *
     * If this patient has no medication history, print "No Medications
     * Prescribed".  
     *
     * If throwing an exception, use "printMedicationDetail()" as the message.
     *
     *  @param patientNo	Patient number
     *  @exception              throws NoSuchPatientException 
     *                          if patient does not exist.
     **/

    public void printMedicationDetail (int patientNo) 
	throws NoSuchPatientException {
        // check if patient exists
        if( database.containsKey( patientNo ) ) {
            Patient patient = database.get( patientNo );

            if( patient.getNumberOfMeds() > 0 ) {
                System.out.println( patient );
            }
            else {
                System.out.println( patient.getName() );
                System.out.println( "No Medications Prescribed" );
            }
        }
        else{
            throw new NoSuchPatientException( "printMedicationDetail()" );
        }

    }


    /**
     * Print all patients ordered by last name, then first name if
     * you encounter two patients with the same last name.
     *
     * To print the Patient objects, simply call your toString() method
     * in the Patient class.
     *
     **/

    public void listByName() {
        // create list from database values
        List<Patient> patients = new ArrayList<Patient>( database.values() );
        // sort the list using the PatientComparator class
        Collections.sort( patients, new PatientComparator() );

        // print the names
        for ( Patient p : patients ) {
            System.out.println( p );
        }

    }


    /**
     * Print all patients ordered by age.
     *
     * To print the Patient objects, simply call your toString() method
     * in the Patient class.
     *
     **/

    public void listByAge() {
        Comparator<Patient> ageComparator = new Comparator<Patient>() {
            public int compare( Patient p1, Patient p2 ) {
                return p1.getAge() - p2.getAge();
            }
        };

        List<Patient> patients = new ArrayList<Patient>( database.values() );
        Collections.sort( patients, ageComparator );

        for( Patient p : patients ) {
            System.out.println( p );
        }

    }

    /**
     * Print all inactive patients in the order in which they were added to
     * the inactive archive.
     *
     *
     * To print the Patient objects, simply call your toString() method
     * in the Patient class.
     *
     **/

    public void listInactive() {
        for( Patient p : inactive.values() ) {
            System.out.println( p );
        }

    }



} 

        

