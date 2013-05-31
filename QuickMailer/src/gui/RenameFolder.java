package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mail.MailAccount;

public class RenameFolder extends JFrame{
	private JTextField folderName;

	private JButton saveButton;
	private JButton deleteButton;
	

	public RenameFolder(){
		super("Rename Folder");
		
		setContentPane(createContentPane());
	//	addListeners();
	}
	
	private JPanel createContentPane() {
		JPanel mainWrapper = new JPanel(new BorderLayout());
		JPanel formWrapper = new JPanel(new GridLayout(0,2));
		
		// Folder Name
		formWrapper.add(new JLabel("Folder Name:"));
		folderName = new JTextField(20);
		formWrapper.add(folderName);
		

		// actionpanel for buttons
		JPanel actionPanel = new JPanel(new FlowLayout(0));
		// button save
		saveButton = new JButton("Save");
		actionPanel.add(saveButton);
		
	
		formWrapper.add(actionPanel);
		
		mainWrapper.add(formWrapper, BorderLayout.WEST);
		return mainWrapper;
	}
}
