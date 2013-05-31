package gui.tree;


import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mail.Mail;
public class Folder implements TreeFolders{

	private String label;
	private Boolean restricted;
	 @XmlElementWrapper(name = "mails")
	private ArrayList<Mail> mailList;
	
	public Folder() {
		
	}
	public Folder(String label) {
		this(label, false);
		this.mailList = new ArrayList<Mail>();

	}
	
	public Folder(String label, Boolean restricted) {
		this.label = label;
		this.restricted = restricted;
		this.mailList = new ArrayList<Mail>();
	}
	
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
	
	public Boolean isRestricted(){
		return restricted;
	}

	public void addMail(Mail mail) {
		mailList.add(mail);
		
	}


}
