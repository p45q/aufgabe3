package storage.datamanager.adapters.models;

import gui.tree.MailFolder;

import java.util.concurrent.CopyOnWriteArrayList;

public class AdaptedMailAccount {

	private String emailadress;
	private String password;
	private String smtpHost;
	private Integer smtpPort;
	private String pop3Host;
	private Integer pop3Port;
	 
	private CopyOnWriteArrayList<MailFolder> folders;

	private static final String DEFAULT_FOLDERLABEL = "Posteingang";

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
	
	public void removeFolder(MailFolder folder) {
		if(!folder.isRestricted()) {
			folders.remove(folder);
		}
	}
	
	public MailFolder getDefaultFolder() {
		for (MailFolder folder : folders){
			if(folder.getLabel().equals(DEFAULT_FOLDERLABEL)){
				return folder;
			}
		}
		return null;
	}
}
