package gui.tree;


import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import storage.datamanager.adapters.FolderAdapter;
import storage.datamanager.adapters.MailAccountAdapter;

import storage.datamanager.adapters.FolderAdapter;
import mail.Mail;
@XmlJavaTypeAdapter(FolderAdapter.class)

public class Folder implements TreeFolders{

	private String label;
	private Boolean restricted;
	private ArrayList<Mail> mailList;
	
	public Folder(String label) {
		this(label, false);

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
		System.out.println("REST: " + restricted);
		if(!restricted) this.label = label;
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

	public void setRestricted(Boolean restricted) {
		this.restricted = restricted;
	}
}
