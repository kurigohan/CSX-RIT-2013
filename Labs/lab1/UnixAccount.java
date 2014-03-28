/*
 * UnixAccount.java
 * Andy Nguyen
 * 8/29/13
 * Lab 1
 */

import java.util.Scanner;

class UnixAccount{

    // Main method for program.
    public static void main( String args[] ) {
    String input = ""; //user input
    String account = ""; //account name
	Scanner scan = new Scanner (System.in); // input scanner 

	System.out.print ( "Enter First Name, Middle Name, Last Name, and SSN: " );
	input = scan.nextLine();
	System.out.println();
	
	//Split string at spaces 
	String[] params = input.split(" ");
	//Loop through split string array and get the first char of each element
	for(int i=0;i<3;++i){
		account += params[i].charAt(0);
	}

	String lastFour = ""; //Temporary hold last four digits of SSN
	char digit; //Current digit
	//Loop through SSN and get last four digits
	for(int i=params[3].length()-1; i>0 && lastFour.length()<4 ;--i){
		digit = params[3].charAt(i);
		if(Character.isDigit(digit))
			lastFour = digit+lastFour; //add digit to front to preserve order
	}
	account += lastFour;
	account = account.toLowerCase(); //Convert account name to lowercase
	// Display the results
	System.out.println( "Unix Account Name: " + account);
    }

} // UnixAccount
