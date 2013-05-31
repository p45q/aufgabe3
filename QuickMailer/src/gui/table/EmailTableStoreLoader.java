package gui.table;

import gui.tree.Folder;
import java.util.ArrayList;


import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import storage.StorageService;
import storage.datamanager.QuickmailerData;

import mail.Mail;
import mail.MailAccount;
import mail.ReceiveMail;

public class EmailTableStoreLoader extends SwingWorker<ModelEmailTable, Mail>{
	
	private final Folder mailFolder;
	private final ModelEmailTable mailTableModel;
	private final JLabel progressLabel;
	private MailAccount mailAccount;
	
	public EmailTableStoreLoader(Folder folder, ModelEmailTable mailTableModel, JLabel progressLabel) {
		this.mailFolder = folder;
		this.mailTableModel = mailTableModel;
		this.progressLabel = progressLabel;
	}
	
	public void setMailAccount(MailAccount mailAccount)
	{
		this.mailAccount = mailAccount;
	}
	
	private void getNewMails() throws Exception {
		ReceiveMail receiveMailObj = new ReceiveMail(mailAccount);
		
		ArrayList<Mail> mailList = receiveMailObj.getAllMessages();
	System.out.println("mailList: " + mailList);
		addMailstoTable(mailList);
		
		for (Mail mail : mailList) {
			mailAccount.getDefaultFolder().addMail(mail);
		}
		
		StorageService.getInstance().saveQuickmailerData();

		updateProgress("Posteingang wurde erfolgreich aktualisiert, " + mailList.size() +  " neue E-Mails");
	}
	
	private void getMailFromFolder() {
		mailTableModel.clearTable();
	
		ArrayList<Mail> mailList = mailFolder.getMailList();
		addMailstoTable(mailList);
		
		updateProgress("Order <" + mailFolder.getLabel() + "> wurde erfolgreich geladen");
	}
	
	
	private void addMailstoTable(ArrayList<Mail> mailList)
	{		
		for(Mail mailObj : mailList){
		    mailTableModel.addMail(mailObj);
		}
	}

	@Override
	protected ModelEmailTable doInBackground() throws Exception {

		if(mailFolder == null)
		{
			updateProgress("Posteingang wird aktualisiert...");
			getNewMails();
		}
		else
		{
			updateProgress("Ordner <" + mailFolder.getLabel() + "> wird geladen...");
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