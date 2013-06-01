package storage.datamanager.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import storage.datamanager.adapters.models.AdaptedMail;

import mail.Mail;

public class MailAdapter extends XmlAdapter<AdaptedMail, Mail> {

	
	    @Override
	    public Mail unmarshal(AdaptedMail adaptedMail) throws Exception {
	        Mail mail = new Mail(adaptedMail.getFrom(), adaptedMail.getTo(), adaptedMail.getSubject(), adaptedMail.getBody());
	        mail.setReceiveDate(adaptedMail.getReceiveDate());
	        mail.setSendDate(adaptedMail.getSendDate());
	        mail.setMessageId(adaptedMail.getMessageId());
	        
	        return mail;
	    }

	    @Override
	    public AdaptedMail marshal(Mail mail) throws Exception {
	    	AdaptedMail adaptedMail = new AdaptedMail();
	    	
	    	adaptedMail.setFrom(mail.getFrom());
	    	adaptedMail.setTo(mail.getTo());
	    	adaptedMail.setSubject(mail.getSubject());
	    	adaptedMail.setBody(mail.getBody());
	    	adaptedMail.setSubject(mail.getSubject());
	    	adaptedMail.setMessageId(mail.getMessageId());	    	
	    	adaptedMail.setReceiveDate(mail.getReceiveDate());	    	
	    	adaptedMail.setSendDate(mail.getSendDate());	    	
	    	
	        return adaptedMail;
	    }

}
