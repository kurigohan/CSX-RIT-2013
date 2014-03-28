/*
 * Dice.java
 * Andy Nguyen
 * 8/29/13
 * Lab 1
 */
import java.util.Scanner;

class Cylinder{

    // Main method for program.
    public static void main( String args[] ) {
	double height;              		// height of cylinder 
	double radius;              		// radius of a cylinder
	Scanner scan = new Scanner (System.in); // input scanner 

	// Get the height and radius

	System.out.print ( "Enter cylinder height: " );
	height = scan.nextDouble();
	System.out.println();
	System.out.print ( "Enter cylinder radius: " );
	radius = scan.nextDouble();
	System.out.println();

	Circle base = new Circle(radius);
	Rectangle side = new Rectangle(height, 2*radius);
	double surfaceArea = base.getArea()*2 + side.getArea() * Math.PI;


	// Display the results
	System.out.println( "The area of a cylinder with height " + height + " and radius " + radius + " is:");
	System.out.println(surfaceArea);
    }

} // Cylinder
