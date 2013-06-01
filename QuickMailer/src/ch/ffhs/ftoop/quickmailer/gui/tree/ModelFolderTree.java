package ch.ffhs.ftoop.quickmailer.gui.tree;

import java.util.ArrayList;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * Class ModelFolderTree
 * 
 * Model to form the tree
 * 
 */
public class ModelFolderTree implements TreeModel {
	private String root;
	private ArrayList<AccountFolder> mailAccounts = new ArrayList<AccountFolder>();
	private ArrayList<TreeModelListener> listeners = new ArrayList<TreeModelListener>();

	public ModelFolderTree(String root) {
		this.root = root;
	}

	public Object getRoot() {
		return root;
	}

	private void fireTreeStructureChanged() {
		Object[] o = { root };
		TreeModelEvent e = new TreeModelEvent(this, o);
		for (TreeModelListener l : listeners) {
			l.treeStructureChanged(e);
		}
	}

	public void addMailAccountFolder(AccountFolder accountFolder) {
		mailAccounts.add(accountFolder);
		fireTreeStructureChanged();
	}

	public void addFolder(AccountFolder accountFolder, MailFolder folder) {
		AccountFolder af = mailAccounts
				.get(mailAccounts.indexOf(accountFolder));
		af.getFolders().add(folder);

		fireTreeStructureChanged();
	}

	public void clear() {

		mailAccounts.remove(getRoot());
	}

	public void clean() {
		mailAccounts.clear();
		fireTreeStructureChanged();
	}

	public void removeMailAccountFolder(AccountFolder accountFolder) {
		if (!mailAccounts.remove(accountFolder))
			throw new NullPointerException("MailAccount : " + accountFolder
					+ " not in tree");
		fireTreeStructureChanged();
	}

	public ArrayList<AccountFolder> getMailAccountFolders() {
		return mailAccounts;
	}

	public void setMailAccountFolders(ArrayList<AccountFolder> mailAccounts) {
		this.mailAccounts = mailAccounts;
		fireTreeStructureChanged();
	}

	public Object getChild(Object parent, int index) {
		if (parent == root) {
			return mailAccounts.get(index);
		}
		if (parent instanceof AccountFolder) {
			AccountFolder accountFolder = mailAccounts.get(mailAccounts
					.indexOf(parent));
			return accountFolder.getFolders().get(index);
		}
		throw new IllegalArgumentException(
				"folder not found in tree index");
	}

	public int getChildCount(Object parent) {
		if (parent == root)
			return mailAccounts.size();
		if (parent instanceof AccountFolder) {
			AccountFolder g = mailAccounts.get(mailAccounts.indexOf(parent));
			return g.getFolders().size();
		}
		throw new IllegalArgumentException(
				"account not in tree index");
	}

	public boolean isLeaf(Object node) {
		return node instanceof MailFolder;
	}

	public void valueForPathChanged(TreePath path, Object newValue) {

	}

	public int getIndexOfChild(Object parent, Object child) {
		if (parent == root)
			return mailAccounts.indexOf(child);
		if (parent instanceof AccountFolder)
			return mailAccounts.get(mailAccounts.indexOf(child)).getFolders()
					.size();
		return 0;
	}

	public int getIndexOfAccount(AccountFolder selectedAccount) {
		return mailAccounts.indexOf(selectedAccount);
	}

	public int getIndexOfAccount(int accountIndex, MailFolder selectedFolder) {
		return mailAccounts.get(accountIndex).getMailAccount().getFolders()
				.indexOf(selectedFolder);
	}

	public void addTreeModelListener(TreeModelListener l) {
		listeners.add(l);
	}

	public void removeTreeModelListener(TreeModelListener l) {
		listeners.remove(l);
	}
}
