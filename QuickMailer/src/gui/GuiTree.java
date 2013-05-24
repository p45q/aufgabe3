package gui;


import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import mail.MailAccount;

public class GuiTree extends JTree {
  protected DefaultMutableTreeNode rootNode;
  protected DefaultTreeModel treeModel;
  protected JTree tree;
  private Toolkit toolkit = Toolkit.getDefaultToolkit();

  public GuiTree() {
	rootNode = new DefaultMutableTreeNode("Root Node");
    treeModel = new DefaultTreeModel(rootNode);

    
    
    setModel(treeModel);
    setShowsRootHandles(true);
    
    // Wurzel aufklappen
    //  expandPath(rootNode);
    
    // und so ...
 //   getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    
    
    
    
   /* tree = new JTree(treeModel);
    tree.setEditable(true);
    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    tree.setShowsRootHandles(true);

    JScrollPane scrollPane = new JScrollPane(tree);
    add(scrollPane);
    */
    
    
   // populateTree();
    
  }

  

  public void populateTree() {
	
    String p1Name = new String("Parent 1");
    String p2Name = new String("Parent 2");

    DefaultMutableTreeNode p1, p2;

    p1 = addObject(null, p1Name);
    p2 = addObject(null, p2Name);
  }

  
 

  /** Remove the currently selected node. */
  public void removeCurrentNode() {
    TreePath currentSelection = tree.getSelectionPath();
    if (currentSelection != null) {
      DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection
          .getLastPathComponent());
      MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
      if (parent != null) {
        treeModel.removeNodeFromParent(currentNode);
        return;
      }
    }

    // Either there was no selection, or the root was selected.
    toolkit.beep();
  }

  
  
  
  /** Add child to the currently selected node. */
  public DefaultMutableTreeNode addObject(Object child) {
    DefaultMutableTreeNode parentNode = null;
    TreePath parentPath = tree.getSelectionPath();

    if (parentPath == null) {
      parentNode = rootNode;
    } else {
      parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
    }

    return addObject(parentNode, child, true);
  }

  public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) {
    return addObject(parent, child, false);
  }

  
  

  public void addAcount(MailAccount mailAccount) {
	  
	  DefaultMutableTreeNode accountNode = new DefaultMutableTreeNode(mailAccount.getEmailadress());
	  DefaultTreeModel accountModel = new DefaultTreeModel(accountNode);

	  setModel(accountModel);
 
	 //tree.scrollPathToVisible(new TreePath(childNode.getPath()));
	    
//    return childNode;
  }

  
  public DefaultMutableTreeNode addFolder(DefaultMutableTreeNode parent, Folder folder) {
	    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(folder.getLabel());

	    // It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
	    treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

	    tree.scrollPathToVisible(new TreePath(childNode.getPath()));
	    
	    return childNode;
	  }
  
  
  
  public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

    if (parent == null) {
      parent = rootNode;
    }

    // It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
    treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

    // Make sure the user can see the lovely new node.
    if (shouldBeVisible) {
      tree.scrollPathToVisible(new TreePath(childNode.getPath()));
    }
    return childNode;
  }

}