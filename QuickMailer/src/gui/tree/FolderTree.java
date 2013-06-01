package gui.tree;



import mail.MailAccount;

import gui.MailAccountForm;
import gui.table.EmailTableStoreLoader;

import java.awt.Component;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import storage.StorageService;
// Left Tree
public class FolderTree extends JTree {
  protected DefaultMutableTreeNode rootNode;
  protected ModelFolderTree treeModel;
  protected JTree tree;
  private static FolderTree instance = null;


  public FolderTree() {
	treeModel = new ModelFolderTree("mails");
    setRootVisible(false);
    setCellRenderer(new FolderCellRenderer(getCellRenderer()));
	setModel(treeModel);
	setShowsRootHandles(true);
    getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    
    instance = this;
    // Wurzel aufklappen 
    //expandAll();
  }



	public static FolderTree getInstance()
	{
		if(instance == null) {
			instance = new FolderTree();
		}
	    
		return instance;
	}
	
	
  private void expandAll() {
	  for (int i = 0; i < getRowCount(); i++) {
	         collapseRow(i);
	}
  }
  
  public void addAcount(MailAccount mailAccount) {
	  AccountFolder accountFolder = new AccountFolder(mailAccount);

	  CopyOnWriteArrayList<MailFolder> subFolders = mailAccount.getFolders();
	  
	  treeModel.addMailAccountFolder(accountFolder);

	  for(MailFolder folder : subFolders)
	  {
		  treeModel.addFolder(accountFolder, folder);	
	  }	
	  // Wurzel aufklappen 
	  expandAll();
  }
  
  public void reloadTree()
  {
	  treeModel.clean();
	  new TreeLoader(this, true).execute();
  }
  
  public AccountFolder getSelectedAccount() {
	  if(getSelectionCount() > 0 ) {
		  if(getSelectionPath().getPathComponent(1) instanceof AccountFolder) {			  
			  return (AccountFolder) getSelectionPath().getPathComponent(1);
			}
	  }
	  
	  return null;
  }
  
  public int getIndexOfAccount(AccountFolder selectedAccount) {
	  if(selectedAccount == null) {
		  selectedAccount = getSelectedAccount();
	  }
	  
	  return treeModel.getIndexOfAccount(selectedAccount);
  }
  
  public MailFolder getSelectedFolder() {
	  if(getSelectionCount() > 0 ) {
		  TreePath path = getSelectionPath();
		  
          for (int i = 0; i < path.getPathCount(); i++) {
        	  if(path.getPathComponent(i) instanceof MailFolder) {
        		  return (MailFolder) path.getPathComponent(i);
              }
          }
	  }
	  
	  return null;
  }
  
  static class FolderCellRenderer implements TreeCellRenderer {
	    TreeCellRenderer renderer; 

	    public FolderCellRenderer(TreeCellRenderer renderer) {
	      this.renderer = renderer;
	    }

	    // label aus folder auslesen und zurückgeben
	    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
	        String newValue = "";
	        
	        if((value != null) && (value instanceof TreeFolders) ) {
	        	newValue = ((TreeFolders) value).getLabel();
	        }
	        
	        return renderer.getTreeCellRendererComponent(tree, newValue, selected, expanded, leaf, row, hasFocus);
	    }
  	}
}

