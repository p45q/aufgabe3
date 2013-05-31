package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mail.Mail;
import mail.MailService;


public class MailAccountForm extends JFrame{
	private Mail MailObj;

	private JLabel labelTo;
	private JLabel labelSubject;
	private JButton sendButton;
	private JTextField textSubject;
	private JTextField textto;
	private JTextArea textArea;
	private boolean isreply;

	public MailAccountForm(Mail selectedMailObj){
		super("Email Verfassen");
		MailObj = selectedMailObj;
		setContentPane(createContentPane());
		addListeners();
	}
	
	private JPanel createContentPane() {
		if(MailObj.getTo()!= null)
		{
			isreply = true;
		}
		
		JPanel mainWrapper = new JPanel();
		mainWrapper.setLayout(new BoxLayout(mainWrapper, BoxLayout.PAGE_AXIS));
		JPanel firstWrapper = new JPanel();
		firstWrapper.setLayout(new BorderLayout());
		// actionpanel erstellen
		JPanel actionPanel = new JPanel(new FlowLayout(0));
		sendButton = new JButton("Senden");
		actionPanel.add(sendButton);
	

		
		textArea = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(true);
		if(isreply){
			textArea.setText("\n\n Originale Nachricht: \n " +  MailObj.getBody());
		}
		

		JPanel infopane = new JPanel();
		infopane.setLayout(new BoxLayout(infopane, BoxLayout.Y_AXIS));

		JPanel panelto = new JPanel();
		panelto.setLayout(new FlowLayout());
		labelTo = new JLabel("An: ");
		panelto.add(labelTo);
		textto = new JTextField(MailObj.getFrom(),30);
		panelto.add(textto);
		infopane.add(panelto);
		
		
		JPanel panelsubject = new JPanel();
		panelsubject.setLayout(new FlowLayout());
		labelSubject = new JLabel("Betreff: ");
		panelsubject.add(labelSubject);
		if(isreply){
			textSubject = new JTextField("AW: "+ MailObj.getSubject(),30);
		}
		else
		{
			textSubject = new JTextField(30);
		}
		panelsubject.add(textSubject);
		infopane.add(panelsubject);
		
		
		
		
	
		
		
		
		
		
		JPanel goleft = new JPanel();
		goleft.setLayout(new BorderLayout());
		goleft.add(infopane, BorderLayout.WEST);

				
		// alles zusammenführen
		firstWrapper.add(goleft,BorderLayout.NORTH);
		firstWrapper.add(textArea, BorderLayout.CENTER);
		mainWrapper.add(actionPanel, BorderLayout.LINE_START);
		mainWrapper.add(firstWrapper, BorderLayout.CENTER);

		return mainWrapper;

		
		
	}
	
	
private void addListeners() {

	sendButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e){
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					MailService mailcomobj = new mail.MailService();
					mail.MailAccount mailaccount = new mail.MailAccount("quickmailerffhs@gmail.com","ffhs12345","smtp.gmail.com",587,"pop.gmail.com",995);
					Mail mailobj1 = new Mail("quickmailerffhs@gmail.com",textto.getText(),textSubject.getText(),textArea.getText());
					mailcomobj.sendMail(mailobj1, mailaccount);
				}
			});
			
		};
		
	});
}
}
