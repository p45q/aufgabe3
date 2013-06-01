package ch.ffhs.ftoop.quickmailer.gui;

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
import javax.swing.JTextField;

import ch.ffhs.ftoop.quickmailer.gui.tree.AccountFolder;
import ch.ffhs.ftoop.quickmailer.gui.tree.FolderTree;
import ch.ffhs.ftoop.quickmailer.gui.tree.MailFolder;
import ch.ffhs.ftoop.quickmailer.storage.StorageService;

/**
 * FolderFrame Class
 * 
 * Popup to rename and create folders
 * 
 */
public class FolderFrame extends JFrame {
	private JTextField folderName;
	private MailFolder selectedFolder;
	private AccountFolder selectedAccount;

	private JButton saveButton;

	public FolderFrame(MailFolder selectedFolder, AccountFolder selectedAccount) {
		super("Folder");
		this.selectedFolder = selectedFolder;
		this.selectedAccount = selectedAccount;

		setContentPane(createContentPane());
	}

	private JPanel createContentPane() {
		JPanel mainWrapper = new JPanel(new BorderLayout());
		JPanel formWrapper = new JPanel(new GridLayout(0, 2));

		// Folder Name
		formWrapper.add(new JLabel("Folder Name:"));
		folderName = new JTextField(20);
		formWrapper.add(folderName);
		if (selectedFolder != null) {
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
				if (selectedFolder != null) {
					selectedFolder.setLabel(folderName.getText());
					setVisible(false);
					dispose();
				} else {
					MailFolder newFolder = new MailFolder(folderName.getText());

					selectedAccount.getMailAccount().addFolder(newFolder);
				}

				setVisible(false);
				dispose();

				StorageService.getInstance().saveQuickmailerData();

				FolderTree.getInstance().reloadTree();
			}
		});
		return mainWrapper;
	}
}
