package gui.tree;



import mail.MailAccount;

import java.awt.Component;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

public class FolderTree extends JTree {
  protected DefaultMutableTreeNode rootNode;
  protected ModelFolderTree treeModel;
  protected JTree tree;

  public FolderTree() {
	treeModel = new ModelFolderTree("mails");
    setRootVisible(false);
    setCellRenderer(new FolderCellRenderer(getCellRenderer()));
	setModel(treeModel);
	setShowsRootHandles(true);
    getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    
    // Wurzel aufklappen 
    //expandAll();
  }


  private void expandAll() {
	  for (int i = 0; i < getRowCount(); i++) {
	         collapseRow(i);
	}
  }
  
  public void addAcount(MailAccount mailAccount) {
	  AccountFolder accountFolder = new AccountFolder(mailAccount);

	  CopyOnWriteArrayList<Folder> subFolders = mailAccount.getFolders();
	  
	  treeModel.addMailAccountFolder(accountFolder);

	  for(Folder folder : subFolders)
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

