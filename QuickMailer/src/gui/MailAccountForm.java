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
	
	
	private JButton sendButton;
	private JTextField textSubject;
	private JTextField textto;
	private JTextArea textArea;
	private boolean isreply;

	public MailAccountForm(MailAccount editMailAccount){
		super("MailAccount");
		this.editMailAccount = editMailAccount;
		
		setContentPane(createContentPane());
	//	addListeners();
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
	
}
