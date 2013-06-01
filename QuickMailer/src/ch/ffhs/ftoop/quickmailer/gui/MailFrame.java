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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.*;

import ch.ffhs.ftoop.quickmailer.mail.Mail;
import ch.ffhs.ftoop.quickmailer.mail.MailAccount;
import ch.ffhs.ftoop.quickmailer.mail.MailService;


/**
 * MailFrame Class
 * 
 * Window to send mails
 * 
 */
public class MailFrame extends JFrame {
	private Mail selectedMailObj;
	private MailAccount mailAccount;
	private String preffixBody;

	private JLabel labelTo;
	private JLabel labelSubject;
	private JButton sendButton;
	private JTextField textSubject;
	private JTextField textTo;
	private JTextArea textBody;

	public MailFrame(Mail selectedMailObj, MailAccount mailAccount) {
		this(selectedMailObj, mailAccount, 0);
	}

	public MailFrame(Mail selectedMailObj, MailAccount mailAccount,
			int preffixMode) {
		super("New E-Mail");

		this.selectedMailObj = selectedMailObj;
		this.mailAccount = mailAccount;

		switch (preffixMode) {
		// reply
		case 1:
			this.preffixBody = "RE: ";
			setTitle(this.preffixBody + this.selectedMailObj.getSubject());
			break;

		// forward
		case 2:
			this.preffixBody = "FW: ";
			setTitle(this.preffixBody + this.selectedMailObj.getSubject());
			break;

		default:
			this.preffixBody = "";
		}

		setContentPane(createContentPane());

		setSize(600, 800);

		if (selectedMailObj != null) {
			setEditContent();
		}

		addListeners();
	}

	private void setEditContent() {
		textSubject.setText(this.preffixBody + selectedMailObj.getSubject());
		textBody.setText("\n\n Original message: \n "
				+ selectedMailObj.getBody());
		textTo.setText(selectedMailObj.getFrom());
	}

	private JPanel createContentPane() {
		JPanel mainWrapper = new JPanel();
		mainWrapper.setLayout(new BoxLayout(mainWrapper, BoxLayout.PAGE_AXIS));

		JPanel firstWrapper = new JPanel(new BorderLayout());

		// actionpanel erstellen
		JPanel actionPanel = new JPanel(new FlowLayout(0));
		sendButton = new JButton("Send");
		actionPanel.add(sendButton);

		// mail body
		textBody = new JTextArea(5, 20);
		textBody.setEditable(true);

		// info pane
		JPanel infopane = new JPanel();
		infopane.setLayout(new BoxLayout(infopane, BoxLayout.Y_AXIS));
		// panel for to
		JPanel panelto = new JPanel(new FlowLayout());
		labelTo = new JLabel("To:          ");
		panelto.add(labelTo);
		textTo = new JTextField(30);
		panelto.add(textTo);
		// add panelto to infopane
		infopane.add(panelto);
		// panel for subject
		JPanel panelsubject = new JPanel(new FlowLayout());
		labelSubject = new JLabel("Subject: ");
		panelsubject.add(labelSubject);
		textSubject = new JTextField(30);
		panelsubject.add(textSubject);
		// add panelsubject to infopane
		infopane.add(panelsubject);

		JPanel goleft = new JPanel();
		goleft.setLayout(new BorderLayout());
		goleft.add(infopane, BorderLayout.WEST);

		// alles zusammenführen
		firstWrapper.add(goleft, BorderLayout.NORTH);
		firstWrapper.add(textBody, BorderLayout.CENTER);
		mainWrapper.add(actionPanel, BorderLayout.LINE_START);
		mainWrapper.add(firstWrapper, BorderLayout.CENTER);

		mainWrapper.setBorder(new EmptyBorder(10, 10, 10, 10));

		return mainWrapper;
	}

	private void addListeners() {
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						// create new mail
						Mail newMail = new Mail(mailAccount.getEmailadress(),
								textTo.getText(), textSubject.getText(),
								textBody.getText());
						// get mailservice
						MailService mailServiceObj = new MailService();

						mailServiceObj.sendMail(newMail, mailAccount);
						setVisible(false);
						dispose();
					}
				});

			};

		});
	}
}
