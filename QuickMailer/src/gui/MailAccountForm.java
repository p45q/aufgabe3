package gui;
import gui.tree.FolderTree;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


import storage.StorageService;

import mail.MailAccount;


public class MailAccountForm extends JFrame{
	private MailAccount editMailAccount;
	private FolderTree folderTree;

	private JTextField mailAdress;
	private JTextField password;
	private JTextField smtpHost;
	private JTextField smtpPort;
	private JTextField pop3Host;
	private JTextField pop3Port;
	
	private JButton saveButton;
	private JLabel progressLabel;
	
	public MailAccountForm(MailAccount editMailAccount, FolderTree folderTree){
		super("MailAccount");
		this.editMailAccount = editMailAccount;
		this.folderTree = folderTree;
		
		setContentPane(createContentPane());
		
		if(editMailAccount != null) {
			setEditContent();
		}
		
		addListeners();

		setResizable(false);
	}
	
	private void setEditContent() {
		mailAdress.setText(editMailAccount.getEmailadress());
		password.setText(editMailAccount.getPassword());
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
		smtpHost = new JTextField(40);
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
		
		mainWrapper.add(actionPanel, BorderLayout.PAGE_START);
		
		mainWrapper.setBorder(new EmptyBorder(10, 10, 10, 10) );

		progressLabel = new JLabel();
		progressLabel.setPreferredSize(new Dimension(10, 15));

		mainWrapper.add(progressLabel, BorderLayout.PAGE_END);

		mainWrapper.add(formWrapper, BorderLayout.WEST);
		return mainWrapper;
	}

	public Boolean checkInputFields() {
		try {
			try {
				Integer.parseInt(smtpPort.getText());
			} catch (NumberFormatException e) {
				progressLabel.setText("Smtp-Port should only contain numbers");
				return false;
			}
			
			try {
			 	Integer.parseInt(pop3Port.getText());
			} catch (NumberFormatException e) {
				progressLabel.setText("Pop3-Port should only contain numbers");
				return false;
			}
			
			if(smtpHost.getText().isEmpty() || pop3Host.getText().isEmpty()  || 
				mailAdress.getText().isEmpty()  || password.getText().isEmpty() )  {
				progressLabel.setText("Please check your input, all fields are required");
				return false;
			}
			
		} catch (NullPointerException ne) {
			progressLabel.setText("Please check your input, all fields are required");
			return false;
		}
		
		return true;
  }
	private void addListeners() {
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				if(checkInputFields()) {
					StorageService storageObj = StorageService.getInstance();
					
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
						
						storageObj.addMailAccount(newMailAccount);
						folderTree.reloadTree();
					}
	
					storageObj.saveQuickmailerData();
				
					setVisible(false); 
					dispose(); 
				}	
			};	
		});
	}
	
}
