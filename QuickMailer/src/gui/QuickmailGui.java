package gui;

import gui.table.ModelEmailTable;
import gui.table.EmailTableStoreLoader;
import gui.tree.AccountFolder;
import gui.tree.Folder;
import gui.tree.FolderTree;
import gui.tree.TreeLoader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import storage.StorageService;

import mail.Mail;
import mail.MailAccount;


@SuppressWarnings("serial")
public class QuickmailGui extends JFrame {
	private JButton newmailButton;
	private JButton readButton;
	private JButton replyButton;
	private JButton forewardButton;
	private JButton replyallButton;
	private JButton getnewButton;

	private JLabel progressLabel;

	private Border blackline;

	private JTable mailTable;
	private JTextArea mailPreview;
	private ModelEmailTable mailTableModel;
	private FolderTree folderTree;
	private JMenuItem newFolder;
	private JMenuItem newMail;
    private JMenuItem newMailAccount;
    private JMenuItem editAccount;
    private JMenuItem removeAccount;
    private JMenuItem editFolder;
    private JMenuItem removeFolder;
	private StorageService storageObj; 

	public QuickmailGui(StorageService storageObj){
		super("QuickMailer");
		this.storageObj = storageObj;

		setContentPane(createContentPane());
		

		buildMenu();
		
		addListeners();
		
	}

	
	private void addListeners() {
		
		
		newFolder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("new folder");
                
                JFrame f = new RenameFolder();
				f.pack();
				f.setVisible(true);
                
                if(folderTree.getSelectionPath().getPathComponent(1) instanceof AccountFolder)
				{
					AccountFolder parrentFolder = (AccountFolder) folderTree.getSelectionPath().getPathComponent(1);
					EmailTableStoreLoader tableStoreLoader = new EmailTableStoreLoader(null, mailTableModel, progressLabel);
					parrentFolder.getMailAccount().addFolder(new Folder("jada"));
				}
                
                folderTree.reloadTree();
                
            }
        });
		newMail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("You have clicked on the new action");
            }
        });
		newMailAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {				
				JFrame f = new MailAccountForm(null);
				f.pack();
				f.setVisible(true);
				
				System.out.println("JUO");
            }
        });
		
	    editAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	
            	AccountFolder selectedAccount = folderTree.getSelectedAccount();
            	if(selectedAccount != null)
   				{   					
   	            	JFrame f = new MailAccountForm(selectedAccount.getMailAccount(), folderTree);
   					f.pack();
   					f.setVisible(true);
   				}
			}
        });
	    removeAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("You have clicked on the new action");
            }
        });
	    editFolder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("You have clicked on the new action");
                JFrame f = new RenameFolder();
				f.pack();
				f.setVisible(true);
            }
        });
	    removeFolder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("You have clicked on the new action");
            }
        });
		
		getnewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				if(folderTree.getSelectionPath().getPathComponent(1) instanceof AccountFolder)
				{
					AccountFolder parrentFolder = (AccountFolder) folderTree.getSelectionPath().getPathComponent(1);
					
					EmailTableStoreLoader tableStoreLoader = new EmailTableStoreLoader(null, mailTableModel, progressLabel);
					tableStoreLoader.setMailAccount(parrentFolder.getMailAccount());
					tableStoreLoader.execute();				
				}
			};
			
		});
		newmailButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				Mail dummyMail = new Mail(null, null, null, null);
				
				
				JFrame f = new SendMail(dummyMail);
				f.setSize(1200, 800); // oder: f.pack();
				f.setVisible(true);
			};
			
		});
		replyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				for (int c : mailTable.getSelectedRows()) {
					Mail selectedMailObj = mailTableModel.getMailObjAt(c);
				
				JFrame f = new SendMail(selectedMailObj);
				f.setSize(1200, 800); // oder: f.pack();
				f.setVisible(true);
				}
			};
			
		});
		

	}
	

	
	private JPanel createContentPane() {
		JPanel mainWrapper = new JPanel(new BorderLayout());
		JPanel rightCol = new JPanel(new BorderLayout(15,0));
		JPanel leftCol = new JPanel(new BorderLayout());
		
		// tree erstellen
		//	FolderMailTree mailFolders = new FolderMailTree(200, 200);
		
		folderTree = new FolderTree();
		folderTree.getSelectionModel().addTreeSelectionListener(new TreeSelection());
        
		new TreeLoader(folderTree, true).execute();

		
		JScrollPane treeScroll = new JScrollPane(folderTree);
		treeScroll.setPreferredSize(new Dimension(200, 200)); 
		
		leftCol.add(treeScroll, BorderLayout.WEST);
		
		// actionpanel erstellen
		JPanel actionPanel = new JPanel(new FlowLayout(0));
		
		getnewButton = new JButton("Get new");
		actionPanel.add(getnewButton);
		newmailButton = new JButton("Compose");
		actionPanel.add(newmailButton);
		readButton = new JButton("Read");
		actionPanel.add(readButton);
		replyButton = new JButton("Reply");
		actionPanel.add(replyButton);
		forewardButton = new JButton("Forward");
		actionPanel.add(forewardButton);
		replyallButton = new JButton("Replyall");
		actionPanel.add(replyallButton);
		
		mainWrapper.add(actionPanel, BorderLayout.PAGE_START);
		
		// mail tabelle erstellen
		// tablemodel für mails 
		mailTableModel = new ModelEmailTable();
 
		// JTable mit model initialisieren
		mailTable = new JTable(mailTableModel);
		
		mailTable.getSelectionModel().addListSelectionListener(new RowListener());
		mailTable.addMouseListener(new MouseAdapter());
		// progresslabel 
				progressLabel = new JLabel();
				rightCol.add(progressLabel, BorderLayout.PAGE_END);
		
		//TEST ****		
		MailAccount defaultAccount = storageObj.getDefaultAccount();
		
		new EmailTableStoreLoader(defaultAccount.getDefaultFolder(), mailTableModel, progressLabel).execute();
		// *************
		
		// dummy inhalt TODO: weg von hier...
		for (int i = 0; i < 10; i++) {
			Mail mailTemp = new Mail("from" + i, "to" + i, "subject" + i, "body" + i);
			mailTableModel.addMail(mailTemp);
		}		
		
 		JPanel tableWrapper = new JPanel(new BorderLayout());
		JScrollPane tableScroll = new JScrollPane(mailTable);

		tableWrapper.add(mailTable.getTableHeader(), BorderLayout.PAGE_START);
		tableWrapper.add(tableScroll, BorderLayout.CENTER);
		
		rightCol.add(tableWrapper, BorderLayout.NORTH);
	
		
		
		// mail preview 
		mailPreview = new JTextArea();
		JScrollPane previewScroll = new JScrollPane(mailPreview);
		previewScroll.setPreferredSize(new Dimension(200, 200)); 
		
		rightCol.add(previewScroll, BorderLayout.CENTER);
		
		// border für rightcol
		TitledBorder black;
		blackline = BorderFactory.createLineBorder(Color.black);
		black = BorderFactory.createTitledBorder(blackline);
		rightCol.setBorder(black);
	
		
		// alles zusammenführen
		leftCol.add(rightCol, BorderLayout.CENTER);
		mainWrapper.add(leftCol, BorderLayout.CENTER);
		
		return mainWrapper;
/*
		
		Panel mailWrapper = new JPanel(new BorderLayout(5,5));
		JPanel treeWrapper = new JPanel(new BorderLayout());
		JPanel mainWrapper = new JPanel(new BorderLayout());
		
		// tree erstllen
		mailTree = new JList(new DefaultListModel());
		JScrollPane treeScroll = new JScrollPane(mailTree);
		treeScroll.setPreferredSize(new Dimension(200, 200)); 
		
		treeWrapper.add(treeScroll, BorderLayout.WEST);


		// mail liste erstellen
		mailList = new JList(new DefaultListModel());
		JScrollPane mailScroll = new JScrollPane(mailList);
		mailScroll.setPreferredSize(new Dimension(200, 200)); 
		
		mailWrapper.add(mailScroll, BorderLayout.CENTER);
	
		// mail detail ansicht
		JList mailView = new JList(new DefaultListModel());
		JScrollPane mailViewScroll = new JScrollPane(mailList);
		mailViewScroll.setPreferredSize(new Dimension(200, 200)); 
		
		mailWrapper.add(mailView, BorderLayout.CENTER);

		
		// quick menu liste erstellen
		JPanel actionPanel = new JPanel(new FlowLayout(0));
		newmailButton = new JButton("Compose");
		actionPanel.add(newmailButton);
		readButton = new JButton("Read");
		actionPanel.add(readButton);
		replyButton = new JButton("Reply");
		actionPanel.add(replyButton);
		forewardButton = new JButton("Forward");
		actionPanel.add(forewardButton);
		replyallButton = new JButton("Replyall");
		actionPanel.add(replyallButton);
		
		mailWrapper.add(actionPanel, BorderLayout.NORTH);
		
		
		TitledBorder black;
		blackline = BorderFactory.createLineBorder(Color.black);
		black = BorderFactory.createTitledBorder(
                blackline);
		mailWrapper.setBorder(black);

		treeWrapper.add(mailWrapper, BorderLayout.CENTER);
		mainWrapper.add(treeWrapper,BorderLayout.CENTER);




		// MENUBAR
		// Creates a menubar for a JFrame
        JMenuBar menuBar = new JMenuBar();
        
        // Add the menubar to the frame
        setJMenuBar(menuBar);
        
        // Define and add two drop down menu to the menubar
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        
        // Create and add simple menu item to one of the drop down menu
        JMenuItem newAction = new JMenuItem("New");
        JMenuItem openAction = new JMenuItem("Open");
        JMenuItem exitAction = new JMenuItem("Exit");
        JMenuItem cutAction = new JMenuItem("Cut");
        JMenuItem copyAction = new JMenuItem("Copy");
        JMenuItem pasteAction = new JMenuItem("Paste");
        
        // Create and add CheckButton as a menu item to one of the drop down
        // menu
        JCheckBoxMenuItem checkAction = new JCheckBoxMenuItem("Check Action");
        // Create and add Radio Buttons as simple menu items to one of the drop
        // down menu
        JRadioButtonMenuItem radioAction1 = new JRadioButtonMenuItem(
                "Radio Button1");
        JRadioButtonMenuItem radioAction2 = new JRadioButtonMenuItem(
                "Radio Button2");
        // Create a ButtonGroup and add both radio Button to it. Only one radio
        // button in a ButtonGroup can be selected at a time.
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioAction1);
        bg.add(radioAction2);
        fileMenu.add(newAction);
        fileMenu.add(openAction);
        fileMenu.add(checkAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);
        editMenu.add(cutAction);
        editMenu.add(copyAction);
        editMenu.add(pasteAction);
        editMenu.addSeparator();
        editMenu.add(radioAction1);
        editMenu.add(radioAction2);
        // Add a listener to the New menu item. actionPerformed() method will
        // invoked, if user triggred this menu item
        newAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("You have clicked on the new action");
            }
        });
		return mainWrapper;*/
	}	
	
	
	/*
	 * private DefaultListModel getModel(JList list){
		return ((DefaultListModel) list.getModel());
	}

	 */
	
	
	private void buildMenu()
	{
		// Creates a menubar for a JFrame
        JMenuBar menuBar = new JMenuBar();
        
        // Add the menubar to the frame
        setJMenuBar(menuBar);
        
        
        // file menu 
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        // file menu children
        newMail = new JMenuItem("New Mail");
        newMailAccount = new JMenuItem("New Mail-Account");
        newFolder = new JMenuItem("New Folder");
        // add children to file menu
        fileMenu.add(newMail);
        fileMenu.add(newMailAccount);
        fileMenu.add(newFolder);
        
        // edit menu
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        // edit menu children
        editAccount = new JMenuItem("Edit Mail-Account");
        removeAccount = new JMenuItem("Remove Mail-Account");
        editFolder = new JMenuItem("Edit Folder");
        removeFolder = new JMenuItem("Remove Folder");
        // add children to edit menu
        editMenu.add(editAccount);
        editMenu.add(removeAccount);
        // add sperator
        editMenu.addSeparator();
        editMenu.add(editFolder);
        editMenu.add(removeFolder);
        
        
        

        /*
        // Create and add simple menu item to one of the drop down menu
        JMenuItem newAction = new JMenuItem("New");
        JMenuItem openAction = new JMenuItem("Open");
        JMenuItem exitAction = new JMenuItem("Exit");
        JMenuItem cutAction = new JMenuItem("Cut");
        JMenuItem copyAction = new JMenuItem("Copy");
        JMenuItem pasteAction = new JMenuItem("Paste");
        
        // Create and add CheckButton as a menu item to one of the drop down
        // menu
        JCheckBoxMenuItem checkAction = new JCheckBoxMenuItem("Check Action");
        // Create and add Radio Buttons as simple menu items to one of the drop
        // down menu
        JRadioButtonMenuItem radioAction1 = new JRadioButtonMenuItem(
                "Radio Button1");
        JRadioButtonMenuItem radioAction2 = new JRadioButtonMenuItem(
                "Radio Button2");
        // Create a ButtonGroup and add both radio Button to it. Only one radio
        // button in a ButtonGroup can be selected at a time.
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioAction1);
        bg.add(radioAction2);
        fileMenu.add(newAction);
        fileMenu.add(openAction);
        fileMenu.add(checkAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);
        editMenu.add(cutAction);
        editMenu.add(copyAction);
        editMenu.add(pasteAction);
        editMenu.addSeparator();
        editMenu.add(radioAction1);
        editMenu.add(radioAction2);
        // Add a listener to the New menu item. actionPerformed() method will
        // invoked, if user triggred this menu item
        newAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("You have clicked on the new action");
            }
        });
        */
	}

	
    private class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
            
            for (int c : mailTable.getSelectedRows()) {
				Mail selectedMailObj = mailTableModel.getMailObjAt(c);
				mailPreview.setText(selectedMailObj.getBody());
			}
        }
    }
    
    
    private class TreeSelection implements TreeSelectionListener {
        public void valueChanged(TreeSelectionEvent e) {
            TreePath path = e.getPath();                
            int pathCount = path.getPathCount();
            
            for (int i = 0; i < pathCount; i++) {
                if(path.getPathComponent(i) instanceof Folder)
                {
//                	AccountFolder parrentFolder = (AccountFolder) path.getParentPath().getLastPathComponent();

                	Folder selectedfolder = (Folder) path.getPathComponent(i);
                	
                	new EmailTableStoreLoader(selectedfolder, mailTableModel, progressLabel).execute();
                }
            }
            
            
			//new TableStoreLoader(new Folder("Postausgang"), mailTableModel, progressLabel).execute();
        }
    };
    
    private class MouseAdapter implements MouseListener{
    	 public void mouseClicked(MouseEvent e) {
             if (e.getClickCount() == 2) {
            	 for (int c : mailTable.getSelectedRows()) {
     				Mail selectedMailObj = mailTableModel.getMailObjAt(c);
     				mailPreview.setText(selectedMailObj.getBody());
     				System.out.println("Doubleclick"+ selectedMailObj.getBody());
     				
     				JFrame f = new MailDetail(selectedMailObj);

     				f.setSize(1200, 800); // oder: f.pack();
     				f.setVisible(true);
     				
     			}
                 
             } 

         }

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    }


}