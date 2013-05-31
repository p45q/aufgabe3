package gui.tree;


import java.util.concurrent.CopyOnWriteArrayList;

import mail.MailAccount;

public class AccountFolder implements TreeFolders {

	private String label;
	private MailAccount mailAccount;
	private CopyOnWriteArrayList<Folder> folders;
	
	public AccountFolder(MailAccount mailAccount) {
		this.label = mailAccount.getEmailadress();
		this.setMailAccount(mailAccount);

		this.folders = new CopyOnWriteArrayList<Folder>();
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public CopyOnWriteArrayList<Folder> getFolders() {
		return folders;
	}

	public void setFolders(CopyOnWriteArrayList<Folder> folders) {
		this.folders = folders;
	}

	public MailAccount getMailAccount() {
		return mailAccount;
	}

	public void setMailAccount(MailAccount mailAccount) {
		this.mailAccount = mailAccount;
	}



}
