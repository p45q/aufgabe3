package ch.ffhs.ftoop.quickmailer.gui;


import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import ch.ffhs.ftoop.quickmailer.gui.tree.AccountFolder;
import ch.ffhs.ftoop.quickmailer.mail.Mail;


/**
 * MailDetail Class
 * 
 * Mail detail/preview on doubleclick
 * 
 */
public class MailDetail extends JFrame {

	private Mail MailObj;

	private JLabel labelFrom;
	private JLabel labelTo;
	private JLabel labelSubject;
	private JLabel labelReceivedate;
	private JTextArea textArea;
	
	private JButton replyButton;
	private JButton forewardButton;
	private AccountFolder selectedMailAccount;

	public MailDetail(Mail selectedMailObj, AccountFolder selectedMailAccount) {
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
				JFrame f = new MailFrame(MailObj, selectedMailAccount
						.getMailAccount(), 1);
				f.setVisible(true);
			}
		});
		forewardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = new MailFrame(MailObj, selectedMailAccount
						.getMailAccount(), 2);
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
		textArea.setEditable(false);
		textArea.setText(MailObj.getBody());

		JPanel infopane = new JPanel();
		infopane.setLayout(new BoxLayout(infopane, BoxLayout.Y_AXIS));

		labelSubject = new JLabel("Subject: " + MailObj.getSubject());
		infopane.add(labelSubject);

		labelFrom = new JLabel("From: " + MailObj.getFrom());
		infopane.add(labelFrom);
		labelTo = new JLabel("To: " + MailObj.getTo());
		infopane.add(labelTo);

		labelReceivedate = new JLabel("Received: "
				+ MailObj.getFormatedReceiveDate());
		infopane.add(labelReceivedate);
		JPanel goleft = new JPanel();
		goleft.setLayout(new BorderLayout());
		goleft.add(infopane, BorderLayout.WEST);

		// alles zusammenführen
		firstWrapper.add(goleft, BorderLayout.NORTH);
		firstWrapper.add(textArea, BorderLayout.CENTER);
		mainWrapper.add(actionPanel, BorderLayout.LINE_START);
		mainWrapper.add(firstWrapper, BorderLayout.CENTER);
		
		mainWrapper.setBorder(new EmptyBorder(10, 10, 10, 10) );

		return mainWrapper;

	}
}
