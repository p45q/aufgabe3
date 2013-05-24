package mail;

import javax.mail.MessagingException;

public class MailCom {
	Mail mailobj;
	SendMail sendmailobj;
	public MailCom(){
		
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
