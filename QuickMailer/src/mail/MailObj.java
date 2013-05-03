package mail;

public class MailObj {
	String from;
	String to;
	String subject;
	String body;
	MailAccount mailaccount;
	public MailObj(String from, String to, String subject, String body, MailAccount mailaccount) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
		this.mailaccount = mailaccount;
	}

}
