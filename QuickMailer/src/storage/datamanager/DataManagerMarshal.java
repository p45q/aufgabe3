package storage.datamanager;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class DataManagerMarshal {
private final String outputXml;
	
	public DataManagerMarshal(String outputXml)
	{
		this.outputXml = outputXml;
	}
	
	public void marshalSettings(QuickmailerData settings) {
   		try {
			// JAXB context erstellen und initialisierung des marshaller
		   JAXBContext jaxbContext = JAXBContext.newInstance(QuickmailerData.class);
		   Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		   // für eine besser formatierte ausgabe
		   jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		   // als xml in outputXml speichern
		   jaxbMarshaller.marshal(settings, new File(outputXml));
		   
   		} catch (JAXBException e) {
   			e.printStackTrace();
   		}
	}
}
