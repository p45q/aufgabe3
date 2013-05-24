package mail;

import java.util.ArrayList;

import gui.Folder;

public class MailAccount {
	private String emailadress;
	private String password;
	private String smtpHost;
	private Integer smtpPort;
	private String pop3Host;
	private Integer pop3Port;
	
	private final Integer DEFAULTPOP3PORT = 123;
	private final Integer DEFAULTSMTPPORT = 123;
	
	private ArrayList<Folder> folders;
	
	public MailAccount(String emailadress, String password, String smtpHost, Integer smtpPort, String pop3Host, Integer pop3Port) {
		if(pop3Port == null) {
			smtpPort = DEFAULTPOP3PORT;
		}
		
		if(smtpPort == null) {
			smtpPort = DEFAULTSMTPPORT;
		}
		
		this.emailadress = emailadress;
		this.password = password;
		this.smtpHost = smtpHost;
		this.smtpPort = smtpPort;
		this.pop3Host = pop3Host;
		this.pop3Port = pop3Port;
	}

	public Integer getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getPop3Host() {
		return pop3Host;
	}

	public void setPop3Host(String pop3Host) {
		this.pop3Host = pop3Host;
	}

	public Integer getPop3Port() {
		return pop3Port;
	}

	public void setPop3Port(Integer pop3Port) {
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
	
	public ArrayList<Folder> getFolders()
	{
		return folders;
	}
	
	public void addFolder(Folder folder)
	{
		folders.add(folder);
	}
	
	public void removeFolder(Folder folder)
	{
		if(!folder.isRestricted()) {
			folders.remove(folder);
		}
	}
}
