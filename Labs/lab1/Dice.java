/*
 * Dice.java
 * Andy Nguyen
 * 8/29/13
 * Lab 1
 */

import java.util.Scanner;

/**
 * This program computes the percentage of the surface area that is
 * white and the percentage of the sureface area that is black on a
 * standard 6 sided die.  The program will prompt the user for the
 * length of one side of the die and the radius of the dots.  Input
 * entered by the user is assumed to be valid (i.e. it is not checked).
 *
 * @author     Paul Tymann
 * @author     Trudy Howles tmh@cs.rit.edu
 *
 */

class Dice {

    /**
     * Main method for program.
     *
     * @param  args command line arguments (ignored)
     */

    public static void main( String args[] ) {
	double length;              		// length of die 
	double radius;              		// radius of a dot
	Scanner scan = new Scanner (System.in); // input scanner 

	// Get the length and radius

	System.out.print ( "Enter length of one side of the die: " );
	length = scan.nextDouble();
	System.out.println();
	System.out.print ( "Enter the radius of one dot on the die: " );
	radius = scan.nextDouble();
	System.out.println();

      	Rectangle side = new Rectangle(length, length);
	Circle dot = new Circle(radius);
	double blackArea = dot.getArea() * 21;
	double whiteArea = side.getArea() * 6 - blackArea;
	double totalArea = blackArea + whiteArea;
	
	// Display the results

        // NOTE: -- you will have to replace the strings "WHITE_AREA" and
        //          "BLACK_AREA" below with expressions that return the 
        //          black and white areas.

	System.out.println( "White Area: " + whiteArea/totalArea + "%" );
	System.out.println();

	System.out.println( "Black Area: " + blackArea/totalArea + "%" );
	System.out.println();
    }

} // Dice
