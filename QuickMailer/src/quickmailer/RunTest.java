package quickmailer;

public class RunTest {
	mail.MailCom mailcomobj;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RunTest runtest = new RunTest();
		runtest.sendtestmail();
	}
	public void sendtestmail(){
		mailcomobj = new mail.MailCom();
		mail.MailAccount mailaccount = new mail.MailAccount("quickmailerffhs@gmail.com","ffhs12345","smtp.gmail.com",587,"jada",342);
		mail.MailObj mailobj = new mail.MailObj("quickmailerffhs@gmail.com","baumannthierry@gmail.com","TestMail","Test text from supermailclient",mailaccount);
		System.out.println(mailcomobj.sendEmail(mailobj));
		
	}

}
