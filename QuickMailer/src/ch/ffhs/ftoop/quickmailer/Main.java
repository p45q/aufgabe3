package ch.ffhs.ftoop.quickmailer;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ch.ffhs.ftoop.quickmailer.gui.QuickmailFrame;
import ch.ffhs.ftoop.quickmailer.storage.StorageService;

/**
 * Class Main
 * 
 * Main method to start Application
 * 
 */
public class Main {

	/**
	 * @param args
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		StorageService storageObj = StorageService.getInstance();
		storageObj.loadMailAccounts();

		JFrame f = new QuickmailFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // wichtig!
		f.setSize(1200, 800);
		f.setVisible(true);

	}
}
