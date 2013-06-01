package ch.ffhs.ftoop.quickmailer.gui.tree;

import java.util.ArrayList;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.ffhs.ftoop.quickmailer.mail.Mail;
import ch.ffhs.ftoop.quickmailer.storage.datamanager.adapters.FolderAdapter;



/**
 * Class MailFolder
 * 
 * Used to store Folders
 * 
 */
@XmlJavaTypeAdapter(FolderAdapter.class)
public class MailFolder implements TreeFolders {

	private String label;
	private Boolean restricted;
	private ArrayList<Mail> mailList;

	public MailFolder(String label) {
		this(label, false);

	}

	public MailFolder(String label, Boolean restricted) {
		this.label = label;
		this.restricted = restricted;
		this.mailList = new ArrayList<Mail>();
	}

	public String getLabel() {
		return label;
	}

	public Boolean setLabel(String label) {
		if (!restricted) {
			this.label = label;
			return true;
		}

		return false;
	}

	public ArrayList<Mail> getMailList() {
		return mailList;
	}

	public void setMailsList(ArrayList<Mail> mailList) {
		this.mailList = mailList;
	}

	public Boolean isRestricted() {
		return restricted;
	}

	public void addMail(Mail mail) {
		mailList.add(mail);
	}

	public void removeMail(Mail mail) {
		mailList.remove(mail);
	}

	public void setRestricted(Boolean restricted) {
		this.restricted = restricted;
	}
}
