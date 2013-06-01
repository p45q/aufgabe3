package mail;
// Class to send smtp mail
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
	private MailAccount mailAccount;
	
	public SendMail(Mail mailobj, MailAccount mailAccount)
	{
		this.mailobj = mailobj;
		this.mailAccount = mailAccount;
	}
	

	@Override
	protected Object doInBackground() throws MessagingException {
		
    	// TODO get from account
    	
        Properties props=new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session=Session.getInstance(props);
        Transport transport=session.getTransport("smtp");
        transport.connect(mailAccount.getSmtpHost(), mailAccount.getSmtpPort(), mailAccount.getEmailadress(), mailAccount.getPassword());
        
        Address[] addresses=InternetAddress.parse(mailobj.getTo());
        
        Message message=new MimeMessage(session);
        message.setFrom(new InternetAddress(mailAccount.getEmailadress()));
        message.setRecipients(Message.RecipientType.TO, addresses);
        message.setSubject(mailobj.getSubject());
        
        message.setText(mailobj.getBody());
        
        transport.sendMessage(message, addresses);
        
        transport.close();
        
        mailAccount.getOutboxFolder().addMail(mailobj);
       
        return true;
	}
}