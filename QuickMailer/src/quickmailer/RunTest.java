package quickmailer;

import gui.tree.Folder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import storage.datamanager.QuickmailerData;
import mail.Mail;
import mail.MailAccount;
import mail.MailService;

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
		
		storage.datamanager.QuickmailerData settings = new QuickmailerData();
		
		mail.MailAccount mailaccount = new MailAccount("quickmailerffhs@gmail.com","ffhs12345","smtp.gmail.com",587,"pop.gmail.com",995);
		mail.MailAccount mailaccount2 = new MailAccount("quickmailer2222ffhs@gmail.com","ffhs12345","smtp.asd.com",587,"pop.asd.com",995);
		
		Folder testFolder = new Folder("Meine Mails");
		for (int i = 0; i < 10; i++) {
			Mail mailTemp = new Mail("Meine 1 from" + i, "to" + i, "subject" + i, "body" + i);
			testFolder.addMail(mailTemp);
		}
		
		Folder testFolder2 = new Folder("Deine Mails");
		for (int i = 0; i < 10; i++) {
			Mail mailTemp = new Mail("Deine 2 from" + i, "to" + i, "subject" + i, "body" + i);
			testFolder2.addMail(mailTemp);
		}	
		
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
	    storage.datamanager.QuickmailerData bookstore2 = (QuickmailerData) um.unmarshal(new FileReader("./bookstore-jaxb.xml"));
	    ArrayList<MailAccount> list = bookstore2.getMailAccounts();
	    for (MailAccount mailAccount : list) {
	      System.out.println("MailAccount: " + mailAccount.getEmailadress() + " host"
	          + mailAccount.getPop3Host());
	      
			System.out.println("DefaultFolder: " + mailAccount.getDefaultFolder());

	    }
	}

	
	
	public void receiveMail(){
		//mailcomobj = new mail.MailCom();
		mail.MailAccount mailaccount = new mail.MailAccount("quickmailerffhs@gmail.com","ffhs12345","smtp.gmail.com",587,"pop.gmail.com",995);


		mail.ReceiveMail receiveMailObj = new mail.ReceiveMail(mailaccount);

//		receiveMailObj.getMessageCount();
		
	}
	
	public void sendtestmail() {
		mailcomobj = new mail.MailService();
		mail.MailAccount mailaccount = new mail.MailAccount("quickmailerffhs@gmail.com","ffhs12345","smtp.gmail.com",587,"pop.gmail.com",995);
		mail.Mail mailobj = new mail.Mail("quickmailerffhs@gmail.com","quickmailerffhs@gmail.com","TestMail","Test text from supermailclient");
		//mailcomobj.sendEmail(mailobj);
		
	}

}
