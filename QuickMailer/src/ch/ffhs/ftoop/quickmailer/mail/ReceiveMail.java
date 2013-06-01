package ch.ffhs.ftoop.quickmailer.mail;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import ch.ffhs.ftoop.quickmailer.gui.tree.MailFolder;

import com.sun.mail.pop3.POP3SSLStore;

/**
 * ReceiveMail class
 * 
 * Class for Pop3 connection
 * 
 */
public class ReceiveMail {

	private MailAccount mailAccount;
	private Session session = null;
	private Store store = null;
	private Folder folder;

	public ReceiveMail(MailAccount mailAccount) {
		this.mailAccount = mailAccount;
	}

	private void connect() throws Exception {
		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties pop3Props = new Properties();

		String pop3Port = Integer.toString(mailAccount.getPop3Port());

		pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
		pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
		pop3Props.setProperty("mail.pop3.port", pop3Port);
		pop3Props.setProperty("mail.pop3.socketFactory.port", pop3Port);

		URLName url = new URLName("pop3", mailAccount.getPop3Host(),
				mailAccount.getPop3Port(), "", mailAccount.getEmailadress(),
				mailAccount.getPassword());
		session = Session.getInstance(pop3Props, null);
		store = new POP3SSLStore(session, url);
		store.connect();
	}

	private void openFolder(String folderName) throws Exception {
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

	private void closeFolder() throws Exception {
		folder.close(false);
	}

	public int getMessageCount() throws Exception {
		return folder.getMessageCount();
	}

	public int getNewMessageCount() throws Exception {
		return folder.getNewMessageCount();
	}

	public ArrayList<Mail> getAllNewMessages() throws Exception {
		ArrayList<Mail> mailList = new ArrayList<Mail>();

		try {
			connect();
			openFolder("INBOX");

			// Attributes & Flags for all messages ..
			Message[] msgs = folder.getMessages();

			// Use a suitable FetchProfile
			FetchProfile fp = new FetchProfile();
			fp.add(FetchProfile.Item.ENVELOPE);
			folder.fetch(msgs, fp);

			Message[] messages = folder.getMessages();

			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				String messageId = null;

				Enumeration<?> headers = message.getAllHeaders();
				while (headers.hasMoreElements()) {
					Header h = (Header) headers.nextElement();
					String mID = h.getName();
					if (mID.contains("Message-ID")) {
						messageId = h.getValue();
					}
				}
				// pop3 shows all mails in the inbox-folder,
				// download mail only once
				if (isNewMail(messageId)) {
					Mail tempMail = new Mail(message.getFrom()[0].toString(),
							mailAccount.getEmailadress(), message.getSubject(),
							message.getContent().toString());
					tempMail.setMessageId(messageId);
					tempMail.setReceiveDate(message.getSentDate());

					mailList.add(tempMail);
				}
			}

			folder.setFlags(messages, new Flags(Flags.Flag.SEEN), true);

			closeFolder();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return mailList;

	}

	private Boolean isNewMail(String messageId) {
		for (MailFolder storedfolder : mailAccount.getFolders()) {
			if (!storedfolder.getLabel().equals(MailAccount.L_SEND)) {
				for (Mail storedMail : storedfolder.getMailList()) {
					if (storedMail.getMessageId().equals(messageId)) {
						return false;
					}
				}
			}
		}

		return true;
	}

}