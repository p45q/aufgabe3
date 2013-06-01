package ch.ffhs.ftoop.quickmailer.gui.tree;

import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.SwingWorker;

import ch.ffhs.ftoop.quickmailer.mail.MailAccount;
import ch.ffhs.ftoop.quickmailer.storage.StorageService;



/**
 * Class TreeLoader
 * 
 * reloads tree, gets data from storageservice
 */
public class TreeLoader extends SwingWorker<JTree, MailFolder> {

	private final FolderTree tree;
	private final Boolean reloadTree;

	public TreeLoader(FolderTree tree, Boolean reloadTree) {
		this.tree = tree;
		this.reloadTree = reloadTree;
	}

	@Override
	protected JTree doInBackground() throws Exception {
		if (reloadTree) {
			StorageService storageObj = StorageService.getInstance();

			ArrayList<MailAccount> mailAccounts = storageObj.getMailAccounts();
			for (MailAccount account : mailAccounts) {
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