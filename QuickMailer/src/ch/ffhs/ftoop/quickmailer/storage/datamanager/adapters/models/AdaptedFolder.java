package ch.ffhs.ftoop.quickmailer.storage.datamanager.adapters.models;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElementWrapper;

import ch.ffhs.ftoop.quickmailer.gui.tree.TreeFolders;
import ch.ffhs.ftoop.quickmailer.mail.Mail;

public class AdaptedFolder implements TreeFolders {

	private String label;
	private Boolean restricted;
	@XmlElementWrapper(name = "mails")
	private ArrayList<Mail> mailList;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public void setRestricted(Boolean restricted) {
		this.restricted = restricted;
	}
}
