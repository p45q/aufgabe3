package quickmailer;

import gui.QuickmailGui;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

import storage.StorageService;

public class Main {

	/**
	 * @param args
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		System.out.println("FIRST");

		StorageService storageObj = new StorageService();
		storageObj.loadMailAccounts();
		
		JFrame f = new QuickmailGui(storageObj);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // wichtig!
		f.setSize(1200, 800); // oder: f.pack();
		f.setVisible(true);
		
		
	}
}
