package gui;

import java.util.ArrayList;

import mail.Mail;

public class Folder {

	private String label;
	private Boolean restricted;
	
	private ArrayList<Mail> mailsList;
	
	public Folder(String label) {
		this.label = label;
		this.restricted = false;
	}
	
	public Folder(String label, Boolean restricted) {
		this.label = label;
		this.restricted = restricted;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public ArrayList<Mail> getMailsList() {
		return mailsList;
	}

	public void setMailsList(ArrayList<Mail> mailsList) {
		this.mailsList = mailsList;
	}
	
	public Boolean isRestricted(){
		return restricted;
	}


}
