package gui;

import java.util.ArrayList;


import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import mail.Mail;
import mail.MailAccount;
import mail.ReceiveMail;

public class TableStoreLoader extends SwingWorker<MailTableModel, Mail>{
	
	private final int mailFolder;
	private final MailTableModel mailTableModel;
	private final JLabel progressLabel;
	
	public TableStoreLoader(int folder, MailTableModel mailTableModel, JLabel progressLabel) {
		this.mailFolder = folder;
		this.mailTableModel = mailTableModel;
		this.progressLabel = progressLabel;
	}
	
	public void getNewMails() throws Exception {
		//TODOE:remove
		//testing only
		MailAccount mailaccount = new MailAccount("quickmailerffhs@gmail.com","ffhs12345","smtp.gmail.com",587,"pop.gmail.com",995);
	 
		ReceiveMail receiveMailObj = new ReceiveMail(mailaccount);
		
		ArrayList<Mail> mailList = receiveMailObj.getAllMessages();
	
		for(Mail mailObj : mailList){
		    mailTableModel.addMail(mailObj);
		}
		
		updateProgress("Posteingang wurde erfolgreich aktualisiert. " + receiveMailObj.getMessageCount() +  " neue E-Mails");
	}

	@Override
	protected MailTableModel doInBackground() throws Exception {
		if(true)//mailFolder == "eingang")
		{
			updateProgress("Posteingang wird aktualisiert...");
			getNewMails();
		}
		else
		{
			//ordner auswählen, daten entsprechend laden
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