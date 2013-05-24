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


public class MailDetail extends JFrame{
	private Mail MailObj;

	private JLabel labelFrom;
	private JLabel labelTo;
	private JLabel labelSubject;
	private JLabel labelReceivedate;
	private JButton replyButton;
	private JButton forewardButton;
	private JButton replyallButton;
	public MailDetail(Mail selectedMailObj){
		super(selectedMailObj.getSubject());
		MailObj = selectedMailObj;
		
		setContentPane(createContentPane());
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
		replyallButton = new JButton("Replyall");
		actionPanel.add(replyallButton);

		
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
