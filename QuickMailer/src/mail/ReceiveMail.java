package mail;


import java.io.IOException;
import java.util.Properties;

import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import com.sun.mail.pop3.POP3SSLStore;
import com.sun.mail.pop3.POP3Store;

public class ReceiveMail {

	private mail.MailAccount mailAccount;
	private Session session = null;
	private Store store = null;
	private String username, password;
	private Folder folder;
	    
	public ReceiveMail(mail.MailAccount mailAccount)
	{
		this.mailAccount = mailAccount;

	     try {
	    	 connect();
            openFolder("INBOX");

            int totalMessages = getMessageCount();
            int newMessages = getNewMessageCount();
             
            System.out.println("Total messages = " + totalMessages);
            System.out.println("New messages = " + newMessages);
            System.out.println("-------------------------------");
             
            //gmail.printAllMessageEnvelopes();
            getAllMessages();
            closeFolder();
	             
        } catch(Exception e) {
            e.printStackTrace();
           // System.exit(-1);

        }
	}
	
	 public void connect() throws Exception {
		 String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";    
		 Properties pop3Props = new Properties();
	     
		 String pop3Port = Integer.toString(mailAccount.getPop3Port());
		 
		 pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
	     pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
	     pop3Props.setProperty("mail.pop3.port",  pop3Port);
	     pop3Props.setProperty("mail.pop3.socketFactory.port",  pop3Port);
	         
	     URLName url = new URLName("pop3", "pop.gmail.com", mailAccount.getPop3Port(), "", mailAccount.getEmailadress(), mailAccount.getPassword());
	         
	     session = Session.getInstance(pop3Props, null);
	     store = new POP3SSLStore(session, url);
	     store.connect();   
	 }
	     
	 public void openFolder(String folderName) throws Exception {
	    // Open the Folder
	    folder = store.getDefaultFolder();
	     
	    folder = folder.getFolder(folderName);
	     
	    if (folder == null) {
	        throw new Exception("Invalid folder");
	    }
	     
	    // try to open read/write and if that fails try read-only
	    try {
	         
	        folder.open(Folder.READ_WRITE);
	         
	    } catch (MessagingException ex) {
	         
	        folder.open(Folder.READ_ONLY);
	         
	    }
    }
	     
    public void closeFolder() throws Exception {
        folder.close(false);
    }
	     
    public int getMessageCount() throws Exception {
        return folder.getMessageCount();
    }
	     
    public int getNewMessageCount() throws Exception {
        return folder.getNewMessageCount();
    }
     
    public void disconnect() throws Exception {
        store.close();
    }
    
    
    /*   
    public void printAllMessageEnvelopes() throws Exception {
         
        // Attributes & Flags for all messages ..
        Message[] msgs = folder.getMessages();
         
        // Use a suitable FetchProfile
        FetchProfile fp = new FetchProfile();
        fp.add(FetchProfile.Item.ENVELOPE);        
        folder.fetch(msgs, fp);
         
        for (int i = 0; i < msgs.length; i++) {
            System.out.println("--------------------------");
            System.out.println("MESSAGE #" + (i + 1) + ":");
            dumpEnvelope(msgs[i]);
             
        }
         
    }*/
	     
    public void getAllMessages() throws Exception {
      
        // Attributes & Flags for all messages ..
        Message[] msgs = folder.getMessages();
         
        // Use a suitable FetchProfile
        FetchProfile fp = new FetchProfile();
        fp.add(FetchProfile.Item.ENVELOPE);        
        folder.fetch(msgs, fp);
         
        Message[] messages = folder.getMessages();
		for (int i = 0; i < messages.length; i++) {
			Message message = messages[i];
		
			
			System.out.println("==============================");
			System.out.println("Email #" + (i + 1));
			System.out.println("Subject: " + message.getSubject());
			System.out.println("From: " + message.getFrom()[0]);
			System.out.println("Text: " + message.getContent().toString());
		}     
    }
	     
	     /*
	
	
	public static void main(String[] args) {

		String mailPop3Host = "pop.gmail.com";
		String mailStoreType = "pop3";
		String mailUser = "quickmailerffhs@gmail.com";
		String mailPassword = "ffhs12345";
		
			

		receiveEmail2(mailPop3Host, mailStoreType, mailUser, mailPassword);
	}

	

	public static void receiveEmail2(String pop3Host, String storeType, String user, String password2) {


		try {
			
			Properties props = new Properties();

		    
		    String host = "pop.gmail.com";
			String provider = "pop3";
			String username = "quickmailerffhs@gmail.com";
			String password = "ffhs12345";
			
		    Session session = Session.getDefaultInstance(props, null);
		    Store store = session.getStore(provider);
		    store.connect(host, username, password);

		    Folder inbox = store.getFolder("INBOX");
		    if (inbox == null) {
		      System.out.println("No INBOX");
		      System.exit(1);
		    }
		    inbox.open(Folder.READ_ONLY);

		    Message[] messages = inbox.getMessages();
		    for (int i = 0; i < messages.length; i++) {
		      System.out.println("Message " + (i + 1));
		      messages[i].writeTo(System.out);
		    }
		    inbox.close(false);
		    store.close();
		    
		    
		    
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	 
	public static void receiveEmail(String pop3Host, String storeType, String user, String password) {


		try {
			
			
			
			
			
			
			Properties properties = new Properties();
			properties.put("mail.pop3.host", pop3Host);
			Session emailSession = Session.getDefaultInstance(properties);

			POP3Store emailStore = (POP3Store) emailSession.getStore(storeType);
			emailStore.connect(user, password);
			
			Folder emailFolder = emailStore.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			System.out.println(emailStore.getDefaultFolder());
			
			Message[] messages = emailFolder.getMessages();
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				System.out.println("==============================");
				System.out.println("Email #" + (i + 1));
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println("Text: " + message.getContent().toString());
			}

			emailFolder.close(false);
			emailStore.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}*/

}