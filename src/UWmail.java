import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Integer;
import java.lang.NumberFormatException;

public class UWmail {
	private static UWmailDB uwmailDB = new UWmailDB();
	private static final Scanner stdin = new Scanner(System.in);

	public static void main(String args[]) {
		if (args.length != 1) {
			System.out.println("Usage: java UWmail [EMAIL_ZIP_FILE]");
			System.exit(0);
		}

		loadEmails(args[0]);

		displayInbox();
	}

	private static void loadEmails(String fileName) {
		// read data from zip files 
				// checkreadable
				SecurityManager checkFile = new SecurityManager(); 
				try (ZipFile zf = new ZipFile(fileName)) {
					Enumeration<? extends ZipEntry> entries = zf.entries();
					// read per txt file
					while(entries.hasMoreElements()) {
						ZipEntry ze = entries.nextElement();
						// an array to store all information about the txt file
						ArrayList<String[]> inputHeader = new ArrayList<String[]>(); 
						if(ze.getName().endsWith(".txt")) {
							InputStream in = zf.getInputStream(ze);
							Scanner sc = new Scanner(in);
							// input data 
							try {
								// emailBody for storing content as ListADT
								ListADT<String> emailBody = 
										new DoublyLinkedList<String>(); 
								while(sc.hasNextLine()) {
									String line = sc.nextLine();
									// key part 
									if (line.contains(": ")) {
										String[] keyValue = {line.split(": ")[0], line.substring(line.indexOf(": ")+2)}; 
										inputHeader.add(keyValue); 
									}
									else {
										throw new MalformedEmailException(); 
									}
									// check if the rest of the file are email body
									if (line.contains("To:") && 
											!(line.contains("In-Reply-To:"))) {
										while (sc.hasNextLine()) {
											emailBody.add(sc.nextLine()); 
										}
									}
								}
								// check if file misses some keys or value part 
								String[] key1 = {"Date", "Message-ID", 
										"Subject", "From", "To"}; 
								String[] key2 = {"In-Reply-To", "References", 
										"Date", "Message-ID", "Subject", "From", 
								"To"}; 
								for (int i = 0; i < inputHeader.size();i++) {
									if (inputHeader.get(0)[0].equals("Date")) {
										if (inputHeader.get(i).length == 0 || 
												inputHeader.size() < 5 ) {
											throw new MalformedEmailException(); 
										}
										if (!(inputHeader.get(i)[0].equals(key1[i]))) {
											throw new MalformedEmailException(); 
										}
									}
									else if (inputHeader.get(0)[0].equals
											("In-Reply-To")) {
										if (inputHeader.get(i).length == 0 || 
												inputHeader.size() < 7 ) {
											throw new MalformedEmailException(); 
										}
										if (!(inputHeader.get(i)[0].equals(key2[i]))) {
											throw new MalformedEmailException(); 
										}
									}
									else {
										throw new MalformedEmailException(); 
									}
								}
								// since all key and value are there, input data now
								if (inputHeader.get(0)[0].equals("Date")) {
									// convert string to date object 
									DateFormat d = new SimpleDateFormat
											("EEE, dd MMM yyyy kk:mm:ss Z");
									Date date = d.parse(inputHeader.get(0)[1]); 
									// create an email ready for DB
									Email newEmail = new Email(
											date, 
											inputHeader.get(1)[1], 
											inputHeader.get(2)[1], 
											inputHeader.get(3)[1], 
											inputHeader.get(4)[1], 
											emailBody); 
									uwmailDB.addEmail(newEmail);
								}
								else {
									// convert string to date object 
									DateFormat d = new SimpleDateFormat
											("EEE, dd MMM yyyy kk:mm:ss Z");
									Date date = d.parse(inputHeader.get(2)[1]); 
									// convert reference strings into ListADT
									String[] refString = 
											inputHeader.get(1)[1].split(","); 
									ListADT<String> references = 
											new DoublyLinkedList<String>(); 
									for (int i = 0; i<refString.length;i++) {
										references.add(refString[i]);
									} 
									// create an email ready for DB
									Email newEmail = new Email(
											date, 
											inputHeader.get(3)[1], 
											inputHeader.get(4)[1], 
											inputHeader.get(5)[1], 
											inputHeader.get(6)[1], 
											emailBody, 
											inputHeader.get(0)[1], 
											references); 
									uwmailDB.addEmail(newEmail);
								}
							} catch (MalformedEmailException e) {
								continue;
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
					}
				} catch (ZipException e) {
					System.out.println("A .zip format error has occurred for the file.");
					System.exit(1);
				} catch (IOException e) {
					System.out.println("An I/O error has occurred for the file.");
					System.exit(1);
				} catch (SecurityException e) {
					System.out.println("File <FileNameHere.zip> not found.");
					System.exit(1);
				}
			}


	private static void displayInbox() {
		boolean done = false;
		// TODO: print out the inbox here, according to the guidelines in the
		// problem
		//
		if(uwmailDB.getInbox().size()==0){
			System.out.println("No conversations to show.");
		}else{
			System.out.println("Inbox:");
			System.out.println("--------------------------------------------------------------------------------");
			for(int i=0;i<uwmailDB.getInbox().size();i++){
				System.out.print("["+i+"]"+" ");
				System.out.print(uwmailDB.getInbox().get(i).get(0).getSubject()+" ");
				System.out.println("("+uwmailDB.getInbox().get(i).get(uwmailDB.getInbox().get(i).size()-1).getDate()+")");
			}
		}

		while (!done) {
			System.out.print("Enter option ([#]Open conversation, [T]rash, "
					+ "[Q]uit): ");
			String input = stdin.nextLine();

			if (input.length() > 0) {

				int val = 0;
				boolean isNum = true;

				try {
					val = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					isNum = false;
				}

				if (isNum) {
					if (val < 0) {
						System.out.println("The value can't be negative!");
						continue;
					} else if (val >= uwmailDB.size()) {
						System.out.println("Not a valid number!");
						continue;
					} else {
						displayConversation(val);
						continue;
					}

				}

				if (input.length() > 1) {
					System.out.println("Invalid command!");
					continue;
				}

				switch (input.charAt(0)) {
				case 'T':
				case 't':
					displayTrash();
					break;

				case 'Q':
				case 'q':
					System.out.println("Quitting...");
					done = true;
					break;

				default:
					System.out.println("Invalid command!");
					break;
				}
			}
		}
		System.exit(0);
	}

	private static void displayTrash() {
		boolean done = false;
		// TODO: print out the trash here according to the problem
		// specifications
		//
		System.out.println("Trash:");
		System.out
				.println("--------------------------------------------------------------------------------");
		if (uwmailDB.getTrash().size() != 0) {
			for(int i=0;i<uwmailDB.getTrash().size();i++){
				System.out.print("["+i+"]"+" ");
				System.out.print(uwmailDB.getTrash().get(i).get(0).getSubject()+" ");
				System.out.println("("+uwmailDB.getTrash().get(i).get(uwmailDB.getTrash().get(i).size()-1).getDate()+")");
			}
		}
		else {
			System.out.println("No conversations to show.");
		}

		while (!done) {
			System.out.print("Enter option ([I]nbox, [Q]uit): ");
			String input = stdin.nextLine();

			if (input.length() > 0) {
				if (input.length() > 1) {
					System.out.println("Invalid command!");
					continue;
				}

				switch (input.charAt(0)) {
				case 'I':
				case 'i':
					displayInbox();
					break;

				case 'Q':
				case 'q':
					System.out.println("Quitting...");
					done = true;
					break;

				default:
					System.out.println("Invalid command!");
					break;
				}
			}
		}
		System.exit(0);
	}

	private static void displayConversation(int val) {
		// TODO: Check whether val is valid as a conversation number. If not,
		// take
		// the user back to the inbox view and continue processing commands.
		//
		if(val<0||val>uwmailDB.getInbox().size()-1){
			displayInbox();
		}
		Conversation selectConv=uwmailDB.getInbox().get(val);
		boolean done = false;
		//TODO: Print the conversation here, according to the problem specifications
		//
		System.out.println("SUBJECT: "+selectConv.get(0).getSubject());
		System.out.println("--------------------------------------------------------------------------------");
		for(int i=0;i<selectConv.size();i++){
			Email tmp=selectConv.get(i);
			if(i==selectConv.getCurrent()){
				System.out.println("From: "+tmp.getFrom());
				System.out.println("To: "+tmp.getTo());
				System.out.println(tmp.getDate());
				System.out.println("");
				DoublyLinkedList<String> bodyMsg=(DoublyLinkedList<String>)tmp.getBody();
				for(int j=0;j<bodyMsg.size();j++){
					System.out.println(bodyMsg.get(j));
				}
				System.out.println("--------------------------------------------------------------------------------");
			}else{
				System.out.println(tmp.getFrom()+" | "+((DoublyLinkedList<String>)tmp.getBody()).get(0)+" | "+tmp.getDate());
				System.out.println("--------------------------------------------------------------------------------");
			}
		}


		while (!done) {
			System.out
					.print("Enter option ([N]ext email, [P]revious email, "
							+ "[J]Next conversation, [K]Previous\nconversation, [I]nbox, "
							+ "[#]Move to trash, [Q]uit): ");
			String input = stdin.nextLine();

			if (input.length() > 0) {

				if (input.length() > 1) {
					System.out.println("Invalid command!");
					continue;
				}

				switch (input.charAt(0)) {
				case 'P':
				case 'p':
					// TODO: for this conversation, move the current email
					// pointer back
					// using Conversation.moveCurrentBack().
					//
					uwmailDB.getInbox().get(val).moveCurrentBack();
					displayConversation(val);
					break;
				case 'N':
				case 'n':
					// TODO: for this conversation, move the current email
					// pointer
					// forward using Conversation.moveCurrentForward().
					//
					uwmailDB.getInbox().get(val).moveCurrentForward();
					displayConversation(val);
					break;
				case 'J':
				case 'j':
					// TODO: Display the next conversation
					if ((int) (val + 1) == uwmailDB.getInbox().size()) {
						displayInbox();
					} else
						displayConversation(val + 1);
					break;

				case 'K':
				case 'k':
					// TODO: Display the previous conversation
					if (val == 0) {
						displayInbox();
					} else
						displayConversation(val - 1);
					break;

				case 'I':
				case 'i':
					displayInbox();
					return;

				case 'Q':
				case 'q':
					System.out.println("Quitting...");
					done = true;
					break;

				case '#':
					// TODO: add delete conversation functionality. This
					// conversation
					// should be moved to the trash when # is entered, and you
					// should
					// take the user back to the inbox and continue processing
					// input.
					//
					uwmailDB.deleteConversation(val);
					displayInbox();
					return;

				default:
					System.out.println("Invalid command!");
					break;
				}
			}
		}
		System.exit(0);
	}

}
