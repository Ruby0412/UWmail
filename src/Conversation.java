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
 * The Conversation class represents a single conversation,
 *  composed of at least one email.
 *
 * <p>Bugs: (a list of bugs and other problems)
 *
 * @author (your name)
 */
import java.util.Iterator;


public class Conversation implements Iterable<Email> {
	// TODO private member variables

	private DoublyLinkedList<Email> individualEmail = new DoublyLinkedList<Email>();

	private int curr;

	/**
	 *Constructs a Conversation with the given email as the first 
	 *and only email.
	 *
	 * @param (parameter name) (Describe the first parameter here)
	 * @param (parameter name) (Do the same for each additional parameter)
	 * @return (description of the return value)
	 */
	public Conversation(Email e) {
		// TODO implement constructor
		individualEmail.add(e);
		curr =0;
	}
	
	/**
	 * Returns the current next node.
	 * @return the current next node
	 */
	public int getCurrent() {
		// TODO
		return curr;
	}

	public void moveCurrentBack() {
		// TODO
		if (this.getCurrent() !=0){
			curr--;
		}
	}

	public void moveCurrentForward() {
		// TODO
		if (this.getCurrent() != individualEmail.size()-1) {
			curr++;
		}
	}

	public int size() {
		// TODO
		return individualEmail.size();
	}

	public Email get(int n) {
		// TODO
		return individualEmail.get(n);
	}

	public void add(Email e) {
		// TODO
		individualEmail.add(0, e);
	}

	public Iterator<Email> iterator() {
		// TODO
		return this.iterator();
	}

}
