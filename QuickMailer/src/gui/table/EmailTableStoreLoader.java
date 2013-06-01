package gui.table;

import gui.tree.MailFolder;
import java.util.ArrayList;


import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import storage.StorageService;
import storage.datamanager.QuickmailerData;

import mail.Mail;
import mail.MailAccount;
import mail.ReceiveMail;
// Class to load Mails and update Progress bar
public class EmailTableStoreLoader extends SwingWorker<ModelEmailTable, Mail>{
	
	private final MailFolder selectedFolder;
	private final ModelEmailTable mailTableModel;
	private final JLabel progressLabel;
	private MailAccount mailAccount;
	private Boolean getNew;
	
	public EmailTableStoreLoader(MailFolder selectedFolder, ModelEmailTable mailTableModel, JLabel progressLabel, Boolean getNew) {
		this.selectedFolder = selectedFolder;
		this.mailTableModel = mailTableModel;
		this.progressLabel = progressLabel;
		this.getNew = getNew;
	}
	
	public void setMailAccount(MailAccount mailAccount)
	{
		this.mailAccount = mailAccount;
	}
	
	private void getNewMails() throws Exception {
		ReceiveMail receiveMailObj = new ReceiveMail(mailAccount);
		
		ArrayList<Mail> mailList = receiveMailObj.getAllNewMessages();
		if(mailList ==  null) {
			updateProgress("There is an error with your email account, please check your settings");
		}
		else {
			if(selectedFolder.equals(mailAccount.getInboxFolder())){
				addMailstoTable(mailList);
			}
			
			for (Mail mail : mailList) {
				mailAccount.getInboxFolder().addMail(mail);
			}
			
			StorageService.getInstance().saveQuickmailerData();
	
			updateProgress("Inbox has been updated successfully, " + mailList.size() +  " new e-mails");
		}
	}
	
	private void getMailFromFolder() {
		mailTableModel.clearTable();
	
		ArrayList<Mail> mailList = selectedFolder.getMailList();
		addMailstoTable(mailList);
		
		updateProgress("Folder <" + selectedFolder.getLabel() + "> has been loaded successfully");
	}
	
	
	private void addMailstoTable(ArrayList<Mail> mailList)
	{		
		for(Mail mailObj : mailList){
		    mailTableModel.addMail(mailObj);
		}
	}

	@Override
	protected ModelEmailTable doInBackground() throws Exception {

		if(getNew)
		{
			updateProgress("Loading Inbox...");
			getNewMails();
		}
		else
		{
			updateProgress("Loading folder <" + selectedFolder.getLabel() + "> ...");
			getMailFromFolder();
		}
		
		return mailTableModel;
	}
	
	private void updateProgress(final String progress) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				progressLabel.setText(progress);
			}
		});
	}

}