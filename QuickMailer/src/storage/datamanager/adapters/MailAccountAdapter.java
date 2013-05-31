package storage.datamanager.adapters;


import javax.xml.bind.annotation.adapters.XmlAdapter;

import mail.MailAccount;

import storage.datamanager.adapters.models.AdaptedMailAccount;

public class MailAccountAdapter extends XmlAdapter<AdaptedMailAccount, MailAccount> {

		
	    @Override
	    public MailAccount unmarshal(AdaptedMailAccount adaptedMailAaccount) throws Exception {
	        MailAccount restoredMailAccount = new MailAccount(adaptedMailAaccount.getEmailadress(), adaptedMailAaccount.getPassword(), adaptedMailAaccount.getSmtpHost(), adaptedMailAaccount.getSmtpPort(), adaptedMailAaccount.getPop3Host(), adaptedMailAaccount.getPop3Port());
	        restoredMailAccount.setFolders(adaptedMailAaccount.getFolders());
	        
	        return restoredMailAccount;
	    }

	    @Override
	    public AdaptedMailAccount marshal(MailAccount mailAccount) throws Exception {
	    	AdaptedMailAccount adaptedMailAccount = new AdaptedMailAccount();
	    	
	    	adaptedMailAccount.setEmailadress(mailAccount.getEmailadress());
	    	adaptedMailAccount.setPassword(mailAccount.getPassword());
	    	adaptedMailAccount.setSmtpHost(mailAccount.getSmtpHost());
	    	adaptedMailAccount.setSmtpPort(mailAccount.getSmtpPort());
	    	adaptedMailAccount.setPop3Host(mailAccount.getPop3Host());
	    	adaptedMailAccount.setPop3Port(mailAccount.getPop3Port());
	    	adaptedMailAccount.setFolders(mailAccount.getFolders());	    	
	    	
	    	return adaptedMailAccount;
	    }

}
