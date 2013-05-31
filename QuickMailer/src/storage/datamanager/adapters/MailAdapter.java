package storage.datamanager.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import storage.datamanager.adapters.models.AdaptedMail;

import mail.Mail;

public class MailAdapter extends XmlAdapter<AdaptedMail, Mail> {

	
	    @Override
	    public Mail unmarshal(AdaptedMail adaptedMail) throws Exception {
	        return new Mail(adaptedMail.getFrom(), adaptedMail.getTo(), adaptedMail.getSubject(), adaptedMail.getBody());
	    }

	    @Override
	    public AdaptedMail marshal(Mail mail) throws Exception {
	    	AdaptedMail adaptedMail = new AdaptedMail();
	    	
	    	adaptedMail.setFrom(mail.getFrom());
	    	adaptedMail.setTo(mail.getTo());
	    	adaptedMail.setSubject(mail.getSubject());
	    	adaptedMail.setBody(mail.getBody());
	    	adaptedMail.setSubject(mail.getSubject());
	  //  	adaptedMail.setMailaccount(mail.getMailaccount());	    	
	        return adaptedMail;
	    }

}
