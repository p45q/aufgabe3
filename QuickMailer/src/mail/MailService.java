package mail;

import javax.mail.MessagingException;

public class MailService {
	Mail mailobj;
	SendMail sendmailobj;
	public MailService(){
		
	}
	public String sendEmail(Mail mailobj)
	{
		this.sendmailobj = new SendMail();
		this.mailobj = mailobj;
		try {
			return sendmailobj.SendEMail(mailobj);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "Problem....";
	}
}
