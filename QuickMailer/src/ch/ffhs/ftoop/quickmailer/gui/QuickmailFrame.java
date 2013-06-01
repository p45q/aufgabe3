package ch.ffhs.ftoop.quickmailer.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.tree.TreePath;

import ch.ffhs.ftoop.quickmailer.gui.table.EmailTableStoreLoader;
import ch.ffhs.ftoop.quickmailer.gui.table.ModelEmailTable;
import ch.ffhs.ftoop.quickmailer.gui.tree.AccountFolder;
import ch.ffhs.ftoop.quickmailer.gui.tree.FolderTree;
import ch.ffhs.ftoop.quickmailer.gui.tree.MailFolder;
import ch.ffhs.ftoop.quickmailer.gui.tree.TreeLoader;
import ch.ffhs.ftoop.quickmailer.mail.Mail;
import ch.ffhs.ftoop.quickmailer.storage.StorageService;



/**
 * QuickmailFrame Class
 * 
 * Main Window
 * 
 */
@SuppressWarnings("serial")
public class QuickmailFrame extends JFrame {
	private StorageService storageObj;

	private JButton newmailButton;
	private JButton replyButton;
	private JButton forewardButton;
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
	private JComboBox moveToFolderCombo;

	private final String L_SELECTACCOUNT = "Please select your e-mail account";
	private final String L_SELECTFOLDER = "Please select your folder";
	private final String L_SELECTMAIL = "Please select e-mail";

	public QuickmailFrame() {
		super("QuickMailer");
		storageObj = StorageService.getInstance();

		setContentPane(createContentPane());

		buildMenu();

		addListeners();

	}

	private void addListeners() {
		newFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (folderTree.getSelectedAccount() != null) {
					JFrame f = new FolderFrame(null, folderTree
							.getSelectedAccount());
					f.pack();
					f.setVisible(true);
				} else {
					updateProgress(L_SELECTACCOUNT);
				}
			}
		});

		newMail.addActionListener(new NewMailButtonListener());

		newMailAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame f = new MailAccountForm(null, folderTree);
				f.pack();
				f.setVisible(true);
			}
		});

		editAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccountFolder selectedAccount = folderTree.getSelectedAccount();
				if (selectedAccount != null) {
					JFrame f = new MailAccountForm(selectedAccount
							.getMailAccount(), folderTree);
					f.pack();
					f.setVisible(true);
				} else {
					updateProgress(L_SELECTACCOUNT);
				}
			}
		});
		removeAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccountFolder selectedAccount = folderTree.getSelectedAccount();
				if (selectedAccount != null) {
					StorageService.getInstance().removeMailAccount(
							selectedAccount.getMailAccount());
					folderTree.reloadTree();
				} else {
					updateProgress(L_SELECTACCOUNT);
				}
			}
		});
		editFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (folderTree.getSelectedFolder() != null) {
					JFrame f = new FolderFrame(folderTree.getSelectedFolder(),
							folderTree.getSelectedAccount());
					f.pack();
					f.setVisible(true);
				} else {
					updateProgress(L_SELECTFOLDER);
				}
			}
		});
		removeFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (folderTree.getSelectedFolder() != null) {
					folderTree.getSelectedAccount().getMailAccount()
							.removeFolder(folderTree.getSelectedFolder());
					folderTree.reloadTree();

					storageObj.saveQuickmailerData();
				} else {
					updateProgress(L_SELECTFOLDER);
				}
			}
		});

		getnewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AccountFolder selectedAccount = folderTree.getSelectedAccount();
				if (selectedAccount != null) {
					EmailTableStoreLoader tableStoreLoader = new EmailTableStoreLoader(
							folderTree.getSelectedFolder(), mailTableModel,
							progressLabel, true);
					tableStoreLoader.setMailAccount(selectedAccount
							.getMailAccount());
					tableStoreLoader.execute();
				} else {
					updateProgress(L_SELECTACCOUNT);
				}
			};
		});

		newmailButton.addActionListener(new NewMailButtonListener());

		replyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AccountFolder selectedAccount = folderTree.getSelectedAccount();

				if (selectedAccount != null) {
					if (mailTable.getSelectedRowCount() > 0) {
						for (int c : mailTable.getSelectedRows()) {
							Mail selectedMailObj = mailTableModel
									.getMailObjAt(c);

							JFrame f = new MailFrame(selectedMailObj,
									selectedAccount.getMailAccount(), 1);
							f.setVisible(true);
							break;
						}
					} else {
						updateProgress(L_SELECTMAIL);
					}
				} else {
					updateProgress(L_SELECTACCOUNT);
				}
			};
		});

		forewardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AccountFolder selectedAccount = folderTree.getSelectedAccount();

				if (selectedAccount != null) {
					if (mailTable.getSelectedRowCount() > 0) {
						for (int c : mailTable.getSelectedRows()) {
							Mail selectedMailObj = mailTableModel
									.getMailObjAt(c);

							JFrame f = new MailFrame(selectedMailObj,
									selectedAccount.getMailAccount(), 2);
							f.setVisible(true);
							break;
						}
					} else {
						updateProgress(L_SELECTMAIL);
					}
				} else {
					updateProgress(L_SELECTACCOUNT);
				}
			};
		});
	}

	private JPanel createContentPane() {
		JPanel mainWrapper = new JPanel(new BorderLayout());
		JPanel rightCol = new JPanel(new BorderLayout(15, 0));
		JPanel leftCol = new JPanel(new BorderLayout());

		folderTree = new FolderTree();
		folderTree.getSelectionModel().addTreeSelectionListener(
				new TreeSelection());

		new TreeLoader(folderTree, true).execute();

		JScrollPane treeScroll = new JScrollPane(folderTree);
		treeScroll.setPreferredSize(new Dimension(300, 200));

		leftCol.add(treeScroll, BorderLayout.WEST);

		// actionpanel erstellen
		JPanel actionPanel = new JPanel(new FlowLayout(0));

		getnewButton = new JButton("Get new");
		actionPanel.add(getnewButton);
		newmailButton = new JButton("Compose");
		actionPanel.add(newmailButton);
		replyButton = new JButton("Reply");
		actionPanel.add(replyButton);
		forewardButton = new JButton("Forward");
		actionPanel.add(forewardButton);

		moveToFolderCombo = new JComboBox();
		moveToFolderCombo.setRenderer(new FolderComboRenderer());
		moveToFolderCombo.addItemListener(new FolderComboListener());

		actionPanel.add(moveToFolderCombo);

		mainWrapper.add(actionPanel, BorderLayout.PAGE_START);

		// mail tabelle erstellen
		// tablemodel für mails
		mailTableModel = new ModelEmailTable();

		// JTable mit model initialisieren
		mailTable = new JTable(mailTableModel);
		mailTable.getSelectionModel().addListSelectionListener(
				new RowListener());
		mailTable.addMouseListener(new MouseAdapter());

		// progresslabel
		progressLabel = new JLabel();
		rightCol.add(progressLabel, BorderLayout.PAGE_END);

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
	}

	private void buildMenu() {
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
	}

	private void updateProgress(final String progress) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				progressLabel.setText(progress);
			}
		});
	}

	/**
	 * private Class RowListener
	 * 
	 * Listens to table selection and writes body of selected mail to preview
	 * textfield
	 * 
	 */
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

	/**
	 * private Class NewMailButtonListener
	 * 
	 * handels action for newMail-buttons, opens new frame
	 * 
	 */
	private class NewMailButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			AccountFolder selectedAccount = folderTree.getSelectedAccount();

			if (selectedAccount != null) {
				JFrame f = new MailFrame(null, selectedAccount.getMailAccount());
				f.setVisible(true);
			} else {
				updateProgress(L_SELECTACCOUNT);
			}
		}
	};

	/**
	 * private Class TreeSelection
	 * 
	 * listens to tree selection, starts table-store-loader to update table data
	 * 
	 */
	private class TreeSelection implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent e) {
			TreePath path = e.getPath();
			int pathCount = path.getPathCount();

			for (int i = 0; i < pathCount; i++) {
				if (path.getPathComponent(i) instanceof MailFolder) {
					MailFolder selectedfolder = (MailFolder) path
							.getPathComponent(i);

					// update movetofolder combo, get folders from seleted
					// account
					moveToFolderCombo.removeAllItems();
					moveToFolderCombo.addItem(new String("Move to Folder"));
					if (folderTree.getSelectedAccount() != null) {
						for (MailFolder folder : folderTree
								.getSelectedAccount().getFolders()) {
							moveToFolderCombo.addItem(folder);
						}
					}

					new EmailTableStoreLoader(selectedfolder, mailTableModel,
							progressLabel, false).execute();

					break;
				}
			}
		}
	};

	/**
	 * private Class MouseAdapter
	 * 
	 * 
	 */
	private class MouseAdapter implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				for (int c : mailTable.getSelectedRows()) {
					Mail selectedMailObj = mailTableModel.getMailObjAt(c);
					mailPreview.setText(selectedMailObj.getBody());

					JFrame f = new MailDetail(selectedMailObj,
							folderTree.getSelectedAccount());

					f.setSize(1200, 800); // oder: f.pack();
					f.setVisible(true);

					break;
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

	/**
	 * private Class FolderComboListener
	 * 
	 * moves mail to selected folder, checks if mail and target folder are selected
	 */
	private class FolderComboListener implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED
					&& moveToFolderCombo.getSelectedIndex() > 0
					&& event.getItem() instanceof MailFolder) {
				if (mailTable.getSelectedRowCount() > 0) {
					for (int c : mailTable.getSelectedRows()) {
						Mail selectedMailObj = mailTableModel.getMailObjAt(c);
						MailFolder currentFolder = folderTree
								.getSelectedFolder();
						MailFolder targetFolder = (MailFolder) event.getItem();

						currentFolder.removeMail(selectedMailObj);
						targetFolder.addMail(selectedMailObj);

						new EmailTableStoreLoader(currentFolder,
								mailTableModel, progressLabel, false).execute();

						StorageService.getInstance().saveQuickmailerData();
					}
				} else {
					updateProgress(L_SELECTMAIL);
				}

				moveToFolderCombo.setSelectedIndex(0);
			}
		}
	}

	/**
	 * private Class FolderComboRenderer
	 * 
	 * 
	 */
	private class FolderComboRenderer extends BasicComboBoxRenderer {
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected,
					cellHasFocus);

			if (value instanceof MailFolder) {
				MailFolder folder = (MailFolder) value;
				setText(folder.getLabel());
			}
			return this;
		}
	}

}