package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class QuickmailGui extends JFrame {
	private JButton settingsButton;
	private JButton newmailButton;
	private JButton readButton;
	private JButton replyButton;
	private JButton forewardButton;
	private JButton replyallButton;

	Border blackline;


	private JList<String> mailTree;
	private JList<String> mailList;
	public boolean debug = false;

	public QuickmailGui() {
		super("QuickMailer");

		setContentPane(createContentPane());
		addListeners();
	}

	private DefaultListModel<String> getModel(JList<String> list){
		return ((DefaultListModel<String>) list.getModel());
	}

	private void addListeners() {







	}

	private JPanel createContentPane() {
		JPanel content = new JPanel(new BorderLayout(5,5));
		JPanel content1 = new JPanel(new BorderLayout());
		JPanel content2 = new JPanel(new BorderLayout());
		mailTree = new JList<>(new DefaultListModel<String>());
		JScrollPane leftScroll = new JScrollPane(mailTree);
		leftScroll.setPreferredSize(new Dimension(200, 200)); 
		content1.add(leftScroll, BorderLayout.WEST);





		JPanel actionPanel = new JPanel(new FlowLayout(0));
		newmailButton = new JButton("Compose");
		actionPanel.add(newmailButton);
		readButton = new JButton("Read");
		actionPanel.add(readButton);
		replyButton = new JButton("Reply");
		actionPanel.add(replyButton);
		forewardButton = new JButton("Foreward");
		actionPanel.add(forewardButton);
		replyallButton = new JButton("Replyall");
		actionPanel.add(replyallButton);




		TitledBorder black;
		blackline = BorderFactory.createLineBorder(Color.black);
		black = BorderFactory.createTitledBorder(
                blackline);







		mailList = new JList<>(new DefaultListModel<String>());
		JScrollPane rightScroll = new JScrollPane(mailList);
		rightScroll.setPreferredSize(new Dimension(200, 200)); 
		content.add(rightScroll, BorderLayout.CENTER);
		content.add(actionPanel, BorderLayout.NORTH);
		content.setBorder(black);

		content1.add(content, BorderLayout.CENTER);
		content2.add(content1,BorderLayout.CENTER);




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
		return content2;
	}	



}
