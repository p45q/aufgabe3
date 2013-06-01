package ch.ffhs.ftoop.quickmailer.storage;

import java.util.ArrayList;

import ch.ffhs.ftoop.quickmailer.mail.MailAccount;
import ch.ffhs.ftoop.quickmailer.storage.datamanager.DataManagerMarshal;
import ch.ffhs.ftoop.quickmailer.storage.datamanager.DataManagerUnmarshal;
import ch.ffhs.ftoop.quickmailer.storage.datamanager.QuickmailerData;

/**
 * Class StorageService
 * 
 * Service to hande loading and saving XML -> saveQuickmailerData
 * 
 */
public class StorageService {
	private static ArrayList<MailAccount> mailAccounts;

	private static final String QUICKMAIL_XML = "./quickmailer-data.xml";
	private static StorageService instance = new StorageService();

	private StorageService() {
		mailAccounts = new ArrayList<MailAccount>();
	}

	public static StorageService getInstance() {
		if (instance == null) {
			instance = new StorageService();
		}

		return instance;
	}

	public ArrayList<MailAccount> getMailAccounts() {
		return mailAccounts;
	}

	public void loadMailAccounts() {
		DataManagerUnmarshal unmashaler = new DataManagerUnmarshal(
				QUICKMAIL_XML);

		mailAccounts = unmashaler.getMailAccounts();
	}

	public MailAccount getDefaultAccount() {
		if (mailAccounts == null) {
			loadMailAccounts();
		}

		return mailAccounts.get(0);
	}

	public void addMailAccount(MailAccount newMailAccount) {
		mailAccounts.add(newMailAccount);

		saveQuickmailerData();
	}

	public void removeMailAccount(MailAccount removeMailAccount) {
		mailAccounts.remove(removeMailAccount);

		saveQuickmailerData();
	}

	public void saveQuickmailerData() {
		QuickmailerData dataObj = new QuickmailerData();
		dataObj.setMailAccounts(mailAccounts);

		new DataManagerMarshal(QUICKMAIL_XML, dataObj).execute();
	}

}
