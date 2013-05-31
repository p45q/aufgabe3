package storage.datamanager;


import java.util.ArrayList;

import mail.MailAccount;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "storage")
public class QuickmailerData {

	// XmLElementWrapper generates a wrapper element around XML representation
	private ArrayList<MailAccount> mailAccountslist;
	

	public QuickmailerData() {
		mailAccountslist = new ArrayList<MailAccount>();

	}
	
	public ArrayList<MailAccount> getMailAccounts() {
		return mailAccountslist;
	}
	
	public void setMailAccounts(ArrayList<MailAccount> mailAccounts) {
		this.mailAccountslist = mailAccounts;
	}
	
	public void addMailAccount(MailAccount mailAccount) {
		this.mailAccountslist.add(mailAccount);
	}

}
