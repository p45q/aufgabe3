package ch.ffhs.ftoop.quickmailer;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ch.ffhs.ftoop.quickmailer.gui.tree.MailFolder;
import ch.ffhs.ftoop.quickmailer.mail.Mail;
import ch.ffhs.ftoop.quickmailer.mail.MailAccount;
import ch.ffhs.ftoop.quickmailer.mail.MailService;
import ch.ffhs.ftoop.quickmailer.storage.datamanager.QuickmailerData;

// Only for test purposes
public class RunTest {
	MailService mailcomobj;
	
	/**
	 * @param args
	 * @throws JAXBException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, JAXBException {
		// TODO Auto-generated method stub
		RunTest runtest = new RunTest();
//		runtest.sendtestmail();
		
		runtest.testStorage();
	//	runtest.receiveMail();

	}
	
	public void testStorage() throws JAXBException, FileNotFoundException{
		//mailcomobj = new mail.MailCom();
		
		ch.ffhs.ftoop.quickmailer.storage.datamanager.QuickmailerData settings = new QuickmailerData();
		
		ch.ffhs.ftoop.quickmailer.mail.MailAccount mailaccount = new MailAccount("quickmailerffhs@gmail.com","ffhs12345","smtp.gmail.com",587,"pop.gmail.com",995);
		ch.ffhs.ftoop.quickmailer.mail.MailAccount mailaccount2 = new MailAccount("quickmailer2222ffhs@gmail.com","ffhs12345","smtp.asd.com",587,"pop.asd.com",995);
		
		MailFolder testFolder = new MailFolder("Bitch Mails");
		for (int i = 0; i < 10; i++) {
			Mail mailTemp = new Mail("Meine 1 from" + i, "to" + i, "subject" + i, "body" + i);
			//testFolder.addMail(mailTemp);
		}
		
		MailFolder testFolder2 = new MailFolder("My Privat");
		for (int i = 0; i < 10; i++) {
			Mail mailTemp = new Mail("Deine 2 from" + i, "to" + i, "subject" + i, "body" + i);
			testFolder2.addMail(mailTemp);
		}	
		System.out.println("MAILS: " + testFolder.getMailList());
		mailaccount.addFolder(testFolder);
		mailaccount2.addFolder(testFolder2);
		
		settings.addMailAccount(mailaccount);
		settings.addMailAccount(mailaccount2);
	
		
	    JAXBContext context = JAXBContext.newInstance(QuickmailerData.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	    m.marshal(settings, System.out);

	    m.marshal(settings, new File( "./quickmailer-data.xml"));

	    System.out.println();
	    System.out.println("Output from our XML File: ");
	    Unmarshaller um = context.createUnmarshaller();
	    ch.ffhs.ftoop.quickmailer.storage.datamanager.QuickmailerData bookstore2 = (QuickmailerData) um.unmarshal(new FileReader("./quickmailer-data.xml"));
	    ArrayList<MailAccount> list = bookstore2.getMailAccounts();
	    for (MailAccount mailAccount : list) {
	      System.out.println("MailAccount: " + mailAccount.getEmailadress() + " host"
	          + mailAccount.getPop3Host());
	      
			System.out.println("DefaultFolder: " + mailAccount.getInboxFolder());

	    }
	}

	
	
	public void receiveMail(){
		//mailcomobj = new mail.MailCom();
		ch.ffhs.ftoop.quickmailer.mail.MailAccount mailaccount = new ch.ffhs.ftoop.quickmailer.mail.MailAccount("quickmailerffhs@gmail.com","ffhs12345","smtp.gmail.com",587,"pop.gmail.com",995);


		ch.ffhs.ftoop.quickmailer.mail.ReceiveMail receiveMailObj = new ch.ffhs.ftoop.quickmailer.mail.ReceiveMail(mailaccount);

//		receiveMailObj.getMessageCount();
		
	}
	
	public void sendtestmail() {
		mailcomobj = new ch.ffhs.ftoop.quickmailer.mail.MailService();
		ch.ffhs.ftoop.quickmailer.mail.MailAccount mailaccount = new ch.ffhs.ftoop.quickmailer.mail.MailAccount("quickmailerffhs@gmail.com","ffhs12345","smtp.gmail.com",587,"pop.gmail.com",995);
		ch.ffhs.ftoop.quickmailer.mail.Mail mailobj = new ch.ffhs.ftoop.quickmailer.mail.Mail("quickmailerffhs@gmail.com","quickmailerffhs@gmail.com","TestMail","Test text from supermailclient");
		//mailcomobj.sendEmail(mailobj);
		
	}

}
