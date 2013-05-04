package quickmailer;

import gui.QuickmailGui;

import javax.swing.JFrame;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame f = new QuickmailGui();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // wichtig!
		f.setSize(600, 400); // oder: f.pack();
		f.setVisible(true);
		
		
	}
}
