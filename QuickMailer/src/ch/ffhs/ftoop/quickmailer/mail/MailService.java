package ch.ffhs.ftoop.quickmailer.mail;

public class MailService {
	public MailService() {

	}

	public void sendMail(Mail mailobj, MailAccount mailAccount) {
		new SendMail(mailobj, mailAccount).execute();

	}
}
