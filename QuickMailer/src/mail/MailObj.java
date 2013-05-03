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
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public MailAccount getMailaccount() {
		return mailaccount;
	}
	public void setMailaccount(MailAccount mailaccount) {
		this.mailaccount = mailaccount;
	}

}
