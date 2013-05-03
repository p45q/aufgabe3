package mail;

public class MailAccount {
	String emailadress;
	String password;
	String smtpHost;
	int smtpPort;
	String pop3Host;
	int pop3Port;
	
	public MailAccount(String emailadress, String password, String smtpHost, int smtpPort, String pop3Host, int pop3Port) {
		this.emailadress = emailadress;
		this.password = password;
		this.smtpHost = smtpHost;
		this.smtpPort = smtpPort;
		this.pop3Host = pop3Host;
		this.pop3Port = pop3Port;
	}

	public int getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getPop3Host() {
		return pop3Host;
	}

	public void setPop3Host(String pop3Host) {
		this.pop3Host = pop3Host;
	}

	public int getPop3Port() {
		return pop3Port;
	}

	public void setPop3Port(int pop3Port) {
		this.pop3Port = pop3Port;
	}

	public String getEmailadress() {
		return emailadress;
	}

	public void setEmailadress(String emailadress) {
		this.emailadress = emailadress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtphost) {
		this.smtpHost = smtphost;
	}	
}
