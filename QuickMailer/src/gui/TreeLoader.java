package gui;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;

import mail.Mail;
import mail.MailAccount;
import mail.ReceiveMail;

public class TreeLoader extends SwingWorker<JTree, Folder>{
	
	private final GuiTree tree;
	
	public TreeLoader(GuiTree tree) {
		this.tree = tree;

	}

	@Override
	protected JTree doInBackground() throws Exception {
		
		System.out.println(tree + "asdasda");

		MailAccount mailaccount = new MailAccount("quickmailerffhs@gmail.com","ffhs12345","smtp.gmail.com",587,"pop.gmail.com",995);
		
		Folder mailaccFolder = new Folder(mailaccount.getEmailadress(), true);
		DefaultMutableTreeNode parentaccfolder = tree.addFolder(null, mailaccFolder);

		System.out.println("folders2: " + parentaccfolder);
		
		return tree;
		/*
		Folder posteingang = new Folder("Posteingang", true);
		mailaccount.addFolder(posteingang);
		
		Folder postausgang = new Folder("Postausgang", true);
		mailaccount.addFolder(postausgang);

		ArrayList<Folder> accountFolders = mailaccount.getFolders();
		for(Folder child : accountFolders)
		{
			tree.addFolder(parentaccfolder, child);	
		}
		
		System.out.println("folders: " + accountFolders);
		
		return tree;*/
	}

}