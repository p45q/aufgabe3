package storage;

import gui.tree.Folder;

import java.util.ArrayList;

import storage.datamanager.DataManagerUnmarshal;

import mail.MailAccount;

public class StorageService {
	private static ArrayList<MailAccount> mailAccounts;
	
	private static final String QUICKMAIL_XML = "./quickmailer-data.xml";

	public StorageService()
	{
		mailAccounts = new ArrayList<MailAccount>();
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
		return mailAccounts.get(0);
	}
	
}
