package quickmailer;

import gui.quickmailgui;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame f = new quickmailgui();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // wichtig!
		f.setSize(600, 400); // oder: f.pack();
		f.setVisible(true);
	}

}
