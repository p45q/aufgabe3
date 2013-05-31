package gui.tree;


import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.SwingWorker;

import mail.MailAccount;

import storage.StorageService;

public class TreeLoader extends SwingWorker<JTree, Folder>{
	
	private final FolderTree tree;
	private final Boolean reloadTree;
	
	public TreeLoader(FolderTree tree, Boolean reloadTree) {
		this.tree = tree;
		this.reloadTree = reloadTree;
	}

	@Override
	protected JTree doInBackground() throws Exception {
		if(reloadTree) {
			StorageService storageObj = StorageService.getInstance();
			
			ArrayList<MailAccount> mailAccounts = storageObj.getMailAccounts();
			for(MailAccount account : mailAccounts) {
				tree.addAcount(account);
			}
		}
		
		expandAll();
		
		return tree;
	}
	
	
	public void expandAll() {
		int row = 0;
		while (row < tree.getRowCount()) {
			tree.expandRow(row);
			row++;
		}
	}
}