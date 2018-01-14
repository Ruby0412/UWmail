///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  UWmail.java
// File:             DoublyLinkedList.java
// Semester:         (course) Fall 2015
//
// Author:           Ariel Tan mtan25@wisc.edu
// CS Login:         mingqi 
// Lecturer's Name:  Jim Skrentny
// Lab Section:      N/A
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     
// Email:            (email address of your programming partner)
// CS Login:         (partner's login name)
// Lecturer's Name:  (name of your partner's lecturer)
// Lab Section:      (your partner's lab section number)
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
 * 
 * a doubly linked list is a linked data structure that consists of
 *  a set of sequentially linked records called nodes. This class contains many 
 *  methods used by doublyLinkedlist.
 *
 * 
 */
import java.util.Iterator;


public class DoublyLinkedList<E> implements ListADT<E> {
	private Listnode<E> head=null;
	private Listnode<E> tail=null;
	private int numItems=0;

	
	/**
	 *  	Adds item to the end of the List. 
	 *
	 * @param (E item) add new item to the last
	 * @return 
	 */
	public void add(E item) {
		//check if the list is empty. 
		if(head==null){
	    	Listnode<E> newnode=new Listnode(item);
			head=newnode;
			tail=newnode;
		}else{
			//Adds item to the end of the List
			Listnode<E> tmp=tail;
			Listnode<E> newItem=new Listnode(item);
			
			tmp.setNext(newItem);
			newItem.setPrev(tmp);
			tail=newItem;
		}
		numItems++;

	}

	
	/**
	 * 
	 * Adds item at position pos in the List, moving the items originally 
	 * in positions pos through size() - 1 one place to the right to make room. 
	 *
	 * @param (pos)  the position want item to be put
	 * @param (item) 
	 */
	public void add(int pos,E item) {
		
		//CHECK if the given pos is valid
		if (pos <0 ||pos > numItems) {
			throw new IndexOutOfBoundsException(); 
		}
		
	//check if the head is null, if it is null then the newnode become head.
	 if (head==null) {
			Listnode<E> newnode = new Listnode<E>(item); 
			head = newnode; 
			tail = newnode; 
		}
		
    //if pos equals to numItem then set this one to the position before head and 
	// after tail
	 if (pos == numItems) {
			Listnode<E> newnode = new Listnode<E>(item); 
			newnode.setPrev(tail);
			tail.setNext(newnode);
			tail = newnode;
		}
		
	 // check if the pos equals to 0, set newnode before head.
	 if (pos == 0) {
			Listnode<E> newnode = new Listnode<E>(item); 
			head.setPrev(newnode);
			newnode.setNext(head);
			head = newnode; 
		}
		
		
		//general situation
		else {
			Listnode<E> curr = head; 
			for (int i = 0; i < pos; i++) {
				curr = curr.getNext(); 	
			}
			Listnode<E> newnode = new Listnode<E>(item); 
			newnode.setPrev(curr.getPrev());
			newnode.setNext(curr);
			curr.getPrev().setNext(newnode);
			curr.setPrev(newnode);
		}
		numItems++;
	}

	/**
	 * 
	 *Returns true iff item is in the List (i.e., there is an item x
	 * in the List such that x.equals(item)) 
	 * @param (item)  item to be checked
	 */
	public boolean contains(E item) {
		boolean whethercontain=false;
		Listnode<E> curr=head;
		
		//check the head is null
		if(head==null){
			return false;
		}
		
		//if there is only one node 
		if(head.getNext()==null){
			if(head.getData().equals(item)){
				return true;
			}
			return false;
		}
		//general situation
		do{
			if(!curr.getData().equals(item)){
				curr=curr.getNext();
			}else{
				whethercontain=true;
				break;
			}
		}while(curr!=null);

		return whethercontain;
	}

	@Override
	
	/**
	 * 
	 *Returns the item at position pos in the List. 
	 *
	 * @param (pos)  get the item in this pos
	 */
	public E get(int pos) {
		Listnode<E> curr=head;
		//check every data to reach the postion 
		for(int i=0;i<pos;i++){
			curr=curr.getNext();
		}
		return curr.getData();

	}

	/**
	 * 
	 *Returns true iff the List is empty.
	 *@return return the boolean whether the list is empty
	 * 
	 */
	public boolean isEmpty() {
		boolean a;
		if (numItems==0) {
			a =true; }
			else {
				a = false;
			}
		return a;
	}

	
	/**
	 * 
	 *Removes and returns the item at position pos in the List, moving the
	 * items originally in positions pos+1 through size() - 1 one place to the 
	 * left to fill in the gap. 
	 *@param (pos) remove the item in this pos
	 * 
	 */
	public E remove(int pos) {
		E newnode; 
		
		//check whether the given position is valid
		if (pos<0 ||pos >= numItems) {
			throw new IllegalArgumentException(); 
		}
		
		
		//if the pos is 0, return newnode
		 if ( pos == 0) {
			newnode = head.getData(); 
			head = head.getNext(); 
			head.setPrev(null);
			numItems--; 
			return newnode; 
		}
		
		//if is the last node, get the last one and return
		if ( pos == numItems-1 ) {
			newnode = tail.getData(); 
			tail.getPrev().setNext(null);
			tail = tail.getPrev();
			numItems--; 
			return newnode; 
		}
	
		//the general case
		else {
			Listnode<E> curr = head; 
			for (int i = 0; i<pos ; i++) {
				curr = curr.getNext(); 
			}
			newnode = curr.getData(); 
			curr.getPrev().setNext(curr.getNext());
			curr.getNext().setPrev(curr.getPrev());
			numItems--; 
			return newnode;
		}
	}

	/**
	 * 
	 *Return the number of items in the List.
	 *
	 * @return numbers of items in the list
	 */
	public int size() {
		return numItems;
	}

	/**
	 * 
	 *Returns an Iterator<E>, per the Iterable<E> interface.
	 *
	 * @return a new iterater
	 */
	@Override
	public Iterator<E> iterator() {
		
		if(head==null){
			throw new IllegalArgumentException();
		}
		return new DoublyLinkedListIterator<E>(head);
	}

}
