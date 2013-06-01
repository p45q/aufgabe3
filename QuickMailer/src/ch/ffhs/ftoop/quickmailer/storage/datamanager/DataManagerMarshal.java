package ch.ffhs.ftoop.quickmailer.storage.datamanager;

import java.io.File;

import javax.swing.SwingWorker;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class DataManagerMarshal extends SwingWorker {
	private final String outputXml;
	private final QuickmailerData quickmailerData;

	public DataManagerMarshal(String outputXml, QuickmailerData quickmailerData) {
		this.quickmailerData = quickmailerData;
		this.outputXml = outputXml;
	}

	public void marshalQuickmailerData(QuickmailerData settings) {
		try {
			// JAXB context erstellen und initialisierung des marshaller
			JAXBContext jaxbContext = JAXBContext
					.newInstance(QuickmailerData.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// für eine besser formatierte ausgabe
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			// als xml in outputXml speichern
			jaxbMarshaller.marshal(settings, new File(outputXml));

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Object doInBackground() throws Exception {

		marshalQuickmailerData(quickmailerData);

		return null;
	}
}
