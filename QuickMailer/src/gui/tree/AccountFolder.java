package gui.tree;


import java.util.concurrent.CopyOnWriteArrayList;

import mail.MailAccount;
// Class to use Folders
public class AccountFolder implements TreeFolders {

	private String label;
	private MailAccount mailAccount;
	private CopyOnWriteArrayList<MailFolder> folders;
	
	public AccountFolder(MailAccount mailAccount) {
		this.label = mailAccount.getEmailadress();
		this.setMailAccount(mailAccount);

		this.folders = new CopyOnWriteArrayList<MailFolder>();
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public CopyOnWriteArrayList<MailFolder> getFolders() {
		return folders;
	}

	public void setFolders(CopyOnWriteArrayList<MailFolder> folders) {
		this.folders = folders;
	}

	public MailAccount getMailAccount() {
		return mailAccount;
	}

	public void setMailAccount(MailAccount mailAccount) {
		this.mailAccount = mailAccount;
	}



}
