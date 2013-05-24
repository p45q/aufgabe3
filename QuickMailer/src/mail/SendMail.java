package mail;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.SwingWorker;
public class SendMail extends SwingWorker{
	private Mail mailobj;
	public SendMail(Mail mailobj)
	{
		this.mailobj = mailobj;
		
	}
	

	@Override
	protected Object doInBackground() throws MessagingException {
		
    	MailAccount mailaccount = mailobj.getMailaccount();
    	// TODO get from account
    	
        Properties props=new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session=Session.getInstance(props);
        Transport transport=session.getTransport("smtp");
        transport.connect(mailaccount.getSmtpHost(), mailaccount.getSmtpPort(), mailaccount.getEmailadress(), mailaccount.getPassword());
        
        Address[] addresses=InternetAddress.parse(mailobj.getTo());
        
        Message message=new MimeMessage(session);
        message.setFrom(new InternetAddress(mailaccount.getEmailadress()));
        message.setRecipients(Message.RecipientType.TO, addresses);
        message.setSubject(mailobj.getSubject());
        
        message.setText(mailobj.getBody());
        
        transport.sendMessage(message, addresses);
        
        transport.close();
        return "Message sent";
	}
}