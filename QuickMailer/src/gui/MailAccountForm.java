package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import storage.StorageService;

import mail.Mail;
import mail.MailAccount;
import mail.MailService;


public class MailAccountForm extends JFrame{
	private MailAccount editMailAccount;
	
	private JTextField mailAdress;
	private JTextField password;
	private JTextField smtpHost;
	private JTextField smtpPort;
	private JTextField pop3Host;
	private JTextField pop3Port;
	
	private JButton saveButton;
	private JButton deleteButton;

	public MailAccountForm(MailAccount editMailAccount){
		super("MailAccount");
		this.editMailAccount = editMailAccount;
		
		setContentPane(createContentPane());
		
		if(editMailAccount != null) {
			setEditContent();
		}
		
		addListeners();
	}
	
	private void setEditContent() {
		mailAdress.setText(editMailAccount.getEmailadress());
		password.setText(editMailAccount.getEmailadress());
		smtpHost.setText(editMailAccount.getSmtpHost());
		smtpPort.setText(editMailAccount.getSmtpPort().toString());
		pop3Host.setText(editMailAccount.getPop3Host());
		pop3Port.setText(editMailAccount.getPop3Port().toString());
	}
	
	private JPanel createContentPane() {
		JPanel mainWrapper = new JPanel(new BorderLayout());
		JPanel formWrapper = new JPanel(new GridLayout(0,2));
		
		// mail adress
		formWrapper.add(new JLabel("E-Mail adress:"));
		mailAdress = new JTextField(20);
		formWrapper.add(mailAdress);
		
		// password
		formWrapper.add(new JLabel("Password:"));
		password = new JTextField(20);
		formWrapper.add(password);
		
		// smtphost
		formWrapper.add(new JLabel("SMTP-Host:"));
		smtpHost = new JTextField(20);
		formWrapper.add(smtpHost);
		
		// smtpport
		formWrapper.add(new JLabel("SMTP-Port:"));
		smtpPort = new JTextField(20);
		formWrapper.add(smtpPort);

		// pop3host
		formWrapper.add(new JLabel("Pop3-Host:"));
		pop3Host = new JTextField(20);
		formWrapper.add(pop3Host);
		
		//pop3Port
		formWrapper.add(new JLabel("Pop3-Port:"));
		pop3Port = new JTextField(20);
		formWrapper.add(pop3Port);
		
		// actionpanel for buttons
		JPanel actionPanel = new JPanel(new FlowLayout(0));
		// button save
		saveButton = new JButton("Save");
		actionPanel.add(saveButton);
		
		if(editMailAccount != null) {
			// button delte
			deleteButton = new JButton("Delete");
			actionPanel.add(deleteButton);
		}
		formWrapper.add(actionPanel);
		
		mainWrapper.add(formWrapper, BorderLayout.WEST);
		return mainWrapper;
	}
	
	private void addListeners() {
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				
				if(editMailAccount != null) {
					// edit selected mailaccount
					editMailAccount.setEmailadress(mailAdress.getText());
					editMailAccount.setPassword(password.getText());
					editMailAccount.setSmtpHost(smtpHost.getText());
					editMailAccount.setSmtpPort(Integer.parseInt(smtpPort.getText()));
					editMailAccount.setPop3Host(pop3Host.getText());
					editMailAccount.setPop3Port(Integer.parseInt(pop3Port.getText()));
				}
				else {
					// save new mailaccount
					MailAccount newMailAccount = new MailAccount(mailAdress.getText(), password.getText(), smtpHost.getText(), Integer.parseInt(smtpPort.getText()), pop3Host.getText(), Integer.parseInt(pop3Port.getText()));
					
					StorageService storageObj = new StorageService();
					storageObj.addMailAccount(newMailAccount);
				}
					
				
				
				/*SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						MailService mailcomobj = new mail.MailService();
						mail.MailAccount mailaccount = new mail.MailAccount("quickmailerffhs@gmail.com","ffhs12345","smtp.gmail.com",587,"pop.gmail.com",995);
						Mail mailobj1 = new Mail("quickmailerffhs@gmail.com",textto.getText(),textSubject.getText(),textArea.getText());
						mailcomobj.sendMail(mailobj1, mailaccount);
					}
				});*/
			};		
		});
	}
	
}
