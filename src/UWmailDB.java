public class UWmailDB {
	// TODO private member variables

	public static ListADT<Conversation> inbox = new DoublyLinkedList<Conversation>();
	public static ListADT<Conversation> trash = new DoublyLinkedList<Conversation>();

	public UWmailDB() {
	}

	public int size() {
		// TODO
		return inbox.size();
	}

	// Pre-condition: e will be added to a conversation for which it is the
	// oldest email.
	// In other words, you can simply add it to the beginning of the list, if
	// the list
	// is sorted in chronological order.
	// Also, the messageID of e is guaranteed to be included in the References
	// field
	// of all emails in the conversation that it belongs in.

	public void addEmail(Email e) {
		// TODO

		if(e.getInReplyTo()==null){
			// an index to trace the existing converations
			int currIndex1=0;
			// get senderID
			String senderId=e.getMessageID();
			Conversation currConv=null;
			do{
				// an existing conversation
				currConv=inbox.get(currIndex1);
				// the existing email 
				Email recentEmail=currConv.get(0);
				if (recentEmail.getInReplyTo()!=null){
					if(recentEmail.getInReplyTo().equals(senderId)){
						currConv.add(e);
						return;
					}  
				}
				currIndex1++;
			}while(currIndex1!=inbox.size());
			// this only happens when there is an email without any response
			currConv = new Conversation(e); 
			inbox.add(currConv);
		} 
		// add an reply email to an existing conversation 
		else{
			if (inbox.size()!=0) {
				// an index to trace the existing converations
				int currIndex1=0;
				// get senderID
				String senderId=e.getMessageID();
				Conversation currConv=null;
				do{
					// an existing conversation
					currConv=inbox.get(currIndex1);
					// the existing email 
					Email recentEmail=currConv.get(0);
					if (recentEmail.getInReplyTo()!=null){
						if(recentEmail.getInReplyTo().equals(senderId)){
							currConv.add(e);
							return;
						}  
					}
					currIndex1++;
				}while(currIndex1!=inbox.size());
				currConv = new Conversation(e); 
				inbox.add(currConv);
			}
			else {
				Conversation newConv=new Conversation(e);
				inbox.add(newConv);	 
			}
		}
	}

	public ListADT<Conversation> getInbox() {
		// TODO
		return inbox;
	}

	public ListADT<Conversation> getTrash() {
		// TODO
		return trash;
	}

	public void deleteConversation(int idx) {
		// TODO
		trash.add(0,inbox.remove(idx));
	}

}
