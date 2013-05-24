package storage;

import java.util.ArrayList;

import gui.Folder;
import mail.MailAccount;

public class TreeStorage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public void getTree()
	{
		MailAccount mailaccount = new MailAccount("quickmailerffhs@gmail.com","ffhs12345","smtp.gmail.com",587,"pop.gmail.com",995);

		Folder posteingang = new Folder("Posteingang", true);
		mailaccount.addFolder(posteingang);
		
		Folder postausgang = new Folder("Postausgang", true);
		mailaccount.addFolder(postausgang);
		
		ArrayList<Folder> accountFolders = mailaccount.getFolders();
		
		
		
	}

}
