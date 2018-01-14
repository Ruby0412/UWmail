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
 * Construct email, include date and messageId, etc.
 *
 * <p>Bugs: (a list of bugs and other problems)
 *
 * @author (your name)
 */
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Email{
  //TODO private member variables
	private Date date = null;
	private String messageID = "";
	private String subject = "";
	private String from ="";
	private String to = "";
	private String inReplyTo= "";
	
	private ListADT<String> body,references;
	

	/**
	 * Constructs a email
	 * 
	 * @param date   the email's date
	 * @param messageID the email's ID
	 * @param  subject the email's subject
	 * @param from the email's senter
	 * @param to the email's receiver
	 * @param body the content
	 */
  public Email(Date date, String messageID, String subject, String from, String to, ListADT<String> body) {
    //TODO implement constructor
	  this.date = date;
	  this.messageID = messageID;
	  this.subject = subject;
	  this.from = from;
	  this.to = to;
	  this.body = body;
	  this.references =new DoublyLinkedList<String>();
	  
	  
  }
  /**
	 * Constructs a email
	 * @param date   the email's date
	 * @param messageID the email's ID
	 * @param  subject the email's subject
	 * @param from the email's senter
	 * @param to the email's receiver
	 * @param body the content
	 * @param inReplyTo the email is reply to reply to
	 *  @param references the reference of the list
	 */
  public Email(Date date, String messageID, String subject, String from, String to, ListADT<String> body, String inReplyTo, ListADT<String> references) {
    //TODO implement constructor
	  this.date = date;
	  this.messageID =  messageID;
	  this.subject = subject;
	  this.from = from;
	  this.to = to;
	  this.body = body;
	  this.inReplyTo = inReplyTo;
	  this.references = references;
	  
	  
  }
 
  /**
   * get the date of email
   * 
   * @return the String of date  
   */
  public String getDate() {
	 Date today = new Date();
	 DateFormat dateformat = new SimpleDateFormat("MMM d", Locale.US);
	 DateFormat datewithoutime = new SimpleDateFormat("yyyyMMdd", Locale.US);
	 if (datewithoutime.format(this.date).equals(datewithoutime.format(today)))
	 {
		 dateformat = new SimpleDateFormat("h:mm a", Locale.US);
	 }
	 
	 return dateformat.format(date);
	 
	 
    //TODO 
  }

  /**
   * get the messageID of email
   * 
   * @return the String of messageID  
   */
  public String getMessageID() {
	  return this.messageID;
    //TODO 
  }

  /**
   * get the messageID of email
   * 
   * @return the String of messageID  
   */
  public String getSubject() {
	  return this.subject;
    //TODO 
  }

  /**
   * get the senter of email
   * 
   * @return the String of senter's email
   */
  public String getFrom() {
	  return this.from;
    //TODO 
  }

  /**
   * get the receiver's email
   * 
   * @return the String of  receiver's email 
   */
  public String getTo() {
	  return this.to;
    //TODO 
  }

  /**
   * get the body of the email
   * 
   * @return the listnode 
   */
  public ListADT<String> getBody() {
	  return this.body;
    //TODO 
  }

  /**
   * get the the senter's email
   * 
   * @return the String of senter's email 
   */
  public String getInReplyTo() {
	  return this.inReplyTo;
    //TODO 
  }

  /**
   * get the reference
   * 
   * @return the String of reference 
   */
  public ListADT<String> getReferences() {
	  return this.references;
    //TODO 
  }
} 