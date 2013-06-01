package mail;


import gui.tree.MailFolder;

import java.util.concurrent.CopyOnWriteArrayList;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import storage.datamanager.adapters.MailAccountAdapter;

@XmlJavaTypeAdapter(MailAccountAdapter.class)
public class MailAccount {
	private String emailadress;
	private String password;
	private String smtpHost;
	private Integer smtpPort;
	private String pop3Host;
	private Integer pop3Port;
	private CopyOnWriteArrayList<MailFolder> folders;

	private final Integer DEFAULTPOP3PORT = 123;
	private final Integer DEFAULTSMTPPORT = 123;
	
	private static final String L_INBOX = "Inbox";
	private static final String L_OUTBOX = "Inbox";

	@SuppressWarnings("serial")
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

		folders = new CopyOnWriteArrayList<MailFolder>(){ {
	        add(new MailFolder("Inbox", true));
	        add(new MailFolder("Outbox", true));
	    }};

		
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
	
	public CopyOnWriteArrayList<MailFolder> getFolders() {
		return folders;
	}
	
	public void setFolders(CopyOnWriteArrayList<MailFolder> folders) {
		this.folders = folders;
	}
	
	public void addFolder(MailFolder folder) {
		folders.add(folder);
	}
	
	public Boolean removeFolder(MailFolder folder) {
		if(!folder.isRestricted()) {
			folders.remove(folder);
			
			return true;
		}
		
		return false;
	}
	
	public Boolean updateFolder(MailFolder newFolder) {
		if(!newFolder.isRestricted()) {
			folders.add(folders.indexOf(newFolder), newFolder);
			
			return true;
		}
		
		return false;
	}
	
	public MailFolder getInboxFolder() {
		return searchFolder(L_INBOX);
	}
	
	public MailFolder getOutboxFolder() {
		return searchFolder(L_OUTBOX);
	}
	
	private MailFolder searchFolder(String searchString) {
		for (MailFolder folder : folders){
			if(folder.getLabel().equals(searchString)){
				return folder;
			}
		}
		
		return null;
	}
}
