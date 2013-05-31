package storage.datamanager;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import mail.MailAccount;

public class DataManagerUnmarshal {

	private final String inputXml;
	
	public DataManagerUnmarshal(String inputXml)
	{
		this.inputXml = inputXml;
	}
	
	public ArrayList<MailAccount> getMailAccounts()
	{
	    QuickmailerData settings = unmarshalData(); 
	    
	    return settings.getMailAccounts();
	}
	
	private QuickmailerData unmarshalData()
	{
		try {
			// JAXB context erstellen und initialisierung des marshaller
			JAXBContext jaxbContext = JAXBContext.newInstance(QuickmailerData.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			// settings objekt aus xml erstellen und zurückgeben
		    return (QuickmailerData) unmarshaller.unmarshal(new FileReader(inputXml));
		   
	    } catch (JAXBException je) {
	    	je.printStackTrace();
	    } catch (IOException ioe) {
	    	ioe.printStackTrace();
	    }
		
		return null;
	}
}
