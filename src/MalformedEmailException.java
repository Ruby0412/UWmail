///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  UWmail
// File:             UWmail
// Semester:         (CS367) Fall 2015
//
// Author:           Ariel Tan
// CS Login:         mingqi
// Lecturer's Name:  skrentny
// Lab Section:      N/A
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     
// Email:            
// CS Login:         
// Lecturer's Name:  
// Lab Section:      
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * This is a check expection  used when the parsed email does not 
 * conform to the guidelines given
 * 
 */
public class MalformedEmailException extends Exception 
{ 
	public MalformedEmailException () {

     super();
     
	}



public MalformedEmailException (String message) { 
	super(message);
	} 

}
