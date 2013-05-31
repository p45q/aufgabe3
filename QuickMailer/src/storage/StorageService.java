package storage;

import gui.tree.Folder;

import java.util.ArrayList;

import storage.datamanager.DataManagerMarshal;
import storage.datamanager.DataManagerUnmarshal;
import storage.datamanager.QuickmailerData;

import mail.MailAccount;

public class StorageService {
	private static ArrayList<MailAccount> mailAccounts;
	
	private static final String QUICKMAIL_XML = "./quickmailer-data.xml";

	public StorageService()
	{
		mailAccounts = new ArrayList<MailAccount>();
		
		loadMailAccounts();
	}
	
	
	
	public ArrayList<MailAccount> getMailAccounts(Boolean reloadData)
	{
		if(reloadData)
		{
			loadMailAccounts();
		}
	
		return mailAccounts;
	}
	
	
	public void loadMailAccounts()
	{
		DataManagerUnmarshal unmashaler = new DataManagerUnmarshal(QUICKMAIL_XML);
		
		mailAccounts = unmashaler.getMailAccounts();
	}
	
	public MailAccount getDefaultAccount()
	{
		if(mailAccounts != null) {
			loadMailAccounts();
		}
		
		return mailAccounts.get(0);			
	}

	
	public void addMailAccount(MailAccount newMailAccount)
	{
		mailAccounts.add(newMailAccount);
		
		saveQuickmailerData();
	}
	
	private void saveQuickmailerData() {
		DataManagerMarshal marshal = new DataManagerMarshal(QUICKMAIL_XML);
		
		QuickmailerData dataObj = new QuickmailerData();
		dataObj.setMailAccounts(mailAccounts);

		marshal.setQuickmailerData(dataObj);
	}
		
}
