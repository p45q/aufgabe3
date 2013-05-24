package mail;

import javax.mail.MessagingException;

public class MailService {
	public MailService(){
		
	}
	public void sendMail(Mail mailobj)
	{
			new SendMail(mailobj).execute();

	}
}
