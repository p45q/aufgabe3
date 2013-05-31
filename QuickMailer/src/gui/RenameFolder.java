package gui;

import java.awt.BorderLayout;
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
import gui.tree.Folder;
import gui.tree.FolderTree;
import gui.tree.AccountFolder;
public class RenameFolder extends JFrame{
	private JTextField folderName;
	private Folder selectedFolder;
	private AccountFolder selectedAccount;
	
	private JButton saveButton;
	private JButton deleteButton;
	

	public RenameFolder(Folder selectedFolder, AccountFolder selectedAccount){
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
		saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(selectedFolder != null) {
                	selectedFolder.setLabel(folderName.getText());                		
                }
                else {
                	Folder newFolder = new Folder(folderName.getText());
                	
                	selectedAccount.getMailAccount().addFolder(newFolder);
                }
             
                StorageService.getInstance().saveQuickmailerData();
                
                FolderTree.getInstance().reloadTree();
                
            }
        });
		return mainWrapper;
	}
}
