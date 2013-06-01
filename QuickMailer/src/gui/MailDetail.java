package gui;


import gui.tree.AccountFolder;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;


import mail.Mail;
import mail.MailAccount;

// Mail detail on doubleclick
public class MailDetail extends JFrame{
	/**
	 * 
	 */


	private Mail MailObj;

	private JLabel labelFrom;
	private JLabel labelTo;
	private JLabel labelSubject;
	private JLabel labelReceivedate;
	private JButton replyButton;
	private JButton forewardButton;
	private AccountFolder selectedMailAccount;
	public MailDetail(Mail selectedMailObj, AccountFolder selectedMailAccount){
		super(selectedMailObj.getSubject());
		MailObj = selectedMailObj;
		this.selectedMailAccount = selectedMailAccount;
		setContentPane(createContentPane());
		addListeners();
	}
	
	private void addListeners() {
		replyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFrame f = new MailFrame(MailObj, selectedMailAccount.getMailAccount(), 1);
				f.setVisible(true);
			}
        });
		forewardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = new MailFrame(MailObj, selectedMailAccount.getMailAccount(), 2);
				f.setVisible(true);
			}
        });
	}
	
	
	
	private JPanel createContentPane() {

		JPanel mainWrapper = new JPanel();
		mainWrapper.setLayout(new BoxLayout(mainWrapper, BoxLayout.PAGE_AXIS));
		JPanel firstWrapper = new JPanel();
		firstWrapper.setLayout(new BorderLayout());
		// actionpanel erstellen
		JPanel actionPanel = new JPanel(new FlowLayout(0));
		replyButton = new JButton("Reply");
		actionPanel.add(replyButton);
		forewardButton = new JButton("Forward");
		actionPanel.add(forewardButton);



		
		textArea = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);
		textArea.setText(MailObj.getBody());
		


		
		
		
		

		JPanel infopane = new JPanel();
		infopane.setLayout(new BoxLayout(infopane, BoxLayout.Y_AXIS));

		
		labelSubject = new JLabel("Betreff: "+ MailObj.getSubject());
		infopane.add(labelSubject);
		
		
		labelFrom = new JLabel("From: " + MailObj.getFrom());
		infopane.add(labelFrom);
		labelTo = new JLabel("TO: "+MailObj.getTo());
		infopane.add(labelTo);
		
		labelReceivedate = new JLabel("Erhalten um: " + MailObj.getFormatedReceiveDate());
		infopane.add(labelReceivedate);
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
	
	
	private JTextArea textArea = new JTextArea(
		 
	);
}
