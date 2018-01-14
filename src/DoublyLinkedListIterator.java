
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
 * a iterater run through a doublylinklist.
 *
 */
import java.util.Iterator;


public class DoublyLinkedListIterator<E> implements Iterator<E>{
 
    // *** fields ***
    private Listnode<E> head;  // the list we're iterating over
    private int curPos;            // the position of the next item
     
    // *** constructor ***
    public DoublyLinkedListIterator(Listnode head) {
        this.head = head;
        curPos = 0;
    }
 
    // *** methods ***
    /**
     * Returns true if the iteration has more elements.
     *
     * @return true if there is next item
     */
    public boolean hasNext() {
        return this.head.getNext()!=null;
    }
 
    /**
     * Returns the next element in the iteration.
     *
     * @return the next item
     */
    public E next() {
    	head = head.getNext();
        return head.getData();
    }
 
    /**
     * 
     * @Throws an UnsupportedOperationException.
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
