package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import storage.StorageService;
import storage.datamanager.QuickmailerData;

import mail.MailAccount;
import gui.tree.MailFolder;
import gui.tree.FolderTree;
import gui.tree.AccountFolder;
public class RenameFolder extends JFrame{
	private JTextField folderName;
	private MailFolder selectedFolder;
	private AccountFolder selectedAccount;
	private JLabel progressLabel;

	private JButton saveButton;
	

	public RenameFolder(MailFolder selectedFolder, AccountFolder selectedAccount){
		super("Folder");
		this.selectedFolder = selectedFolder;
		this.selectedAccount = selectedAccount;

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
		if(selectedFolder != null)
		{
			folderName.setText(selectedFolder.getLabel());
		}

		// actionpanel for buttons
		JPanel actionPanel = new JPanel(new FlowLayout(0));
		// button save
		saveButton = new JButton("Save");
		actionPanel.add(saveButton);
	
		formWrapper.add(actionPanel);
		 
		mainWrapper.add(formWrapper, BorderLayout.WEST);
		
		progressLabel = new JLabel();
		progressLabel.setPreferredSize(new Dimension(10, 15));

		mainWrapper.add(progressLabel, BorderLayout.PAGE_END);
		
		saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	Boolean formError = false;
                if(selectedFolder != null) {
                	if(selectedFolder.setLabel(folderName.getText())){
                		setVisible(false); 
    					dispose();
                	}
                	else {
                		formError = true;
                		progressLabel.setText("This folder is restricted, can't change Label");
                	}
                }
                else {
                	MailFolder newFolder = new MailFolder(folderName.getText());
                	
                	selectedAccount.getMailAccount().addFolder(newFolder);
                }
                
                if(!formError) {
                	setVisible(false); 
    				dispose();
                 
                    StorageService.getInstance().saveQuickmailerData();
                    
                    FolderTree.getInstance().reloadTree();	
                }  
            }
        });
		return mainWrapper;
	}
}
