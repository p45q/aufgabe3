package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mail.Mail;


public class QuickmailGui extends JFrame {
	private JButton settingsButton;
	private JButton newmailButton;
	private JButton readButton;
	private JButton replyButton;
	private JButton forewardButton;
	private JButton replyallButton;
	private JButton getnewButton;

	private JLabel progressLabel;

	Border blackline;

	private JTable mailTable;
	private JTextArea mailPreview;
	private MailTableModel mailTableModel;

	public QuickmailGui(){
		super("QuickMailer");

		setContentPane(createContentPane());
		
		buildMenu();
		
		addListeners();
	}

	
	private void addListeners() {

		getnewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				new TableStoreLoader(123, mailTableModel, progressLabel).execute();
				
				
				
			};
			
		});

	}

	
	private JPanel createContentPane() {
		JPanel mainWrapper = new JPanel(new BorderLayout());
		JPanel rightCol = new JPanel(new BorderLayout(15,0));
		JPanel leftCol = new JPanel(new BorderLayout());
		
		// tree erstellen
		FolderMailTree mailFolders = new FolderMailTree(200, 200);
		
		JScrollPane treeScroll = new JScrollPane(mailFolders);
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
		mailTableModel = new MailTableModel();
 
		// JTable mit model initialisieren
		mailTable = new JTable(mailTableModel);
		
		mailTable.getSelectionModel().addListSelectionListener(new RowListener());
		mailTable.addMouseListener(new MouseAdapter());
		
		// dummy inhalt TODO: weg von hier...
		for (int i = 0; i < 10; i++) {
			Mail mailTemp = new Mail("id" + i, "from" + i, "to" + i, "subject" + i, "body" + i, null);
			mailTableModel.addMail(mailTemp);
		}		
		
 		JPanel tableWrapper = new JPanel(new BorderLayout());
		tableWrapper.add(mailTable.getTableHeader(), BorderLayout.PAGE_START);
		tableWrapper.add(mailTable, BorderLayout.CENTER);
		
		rightCol.add(tableWrapper, BorderLayout.NORTH);
	
		// progresslabel 
		progressLabel = new JLabel();
		rightCol.add(progressLabel, BorderLayout.PAGE_END);
		
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
    
    private class MouseAdapter implements MouseListener{
    	 public void mouseClicked(MouseEvent e) {
             if (e.getClickCount() == 2) {
            	 for (int c : mailTable.getSelectedRows()) {
     				Mail selectedMailObj = mailTableModel.getMailObjAt(c);
     				mailPreview.setText(selectedMailObj.getBody());
     				System.out.println("Doubleclick"+ selectedMailObj.getBody());
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