
package storage.datamanager.adapters;


import gui.tree.MailFolder;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import mail.MailAccount;

import storage.datamanager.adapters.models.AdaptedFolder;
import storage.datamanager.adapters.models.AdaptedMailAccount;
public class FolderAdapter extends XmlAdapter<AdaptedFolder, MailFolder> {
		
		@Override
	    public MailFolder unmarshal(AdaptedFolder adaptedFolder) throws Exception {
	        MailFolder restoredFolder = new MailFolder(adaptedFolder.getLabel(), adaptedFolder.isRestricted());
	        
	        restoredFolder.setMailsList(adaptedFolder.getMailList());
	        
	        return restoredFolder;
	    }

	    @Override
	    public AdaptedFolder marshal(MailFolder folder) throws Exception {
	    	AdaptedFolder adaptedFolder = new AdaptedFolder();
	    	
	    	adaptedFolder.setLabel(folder.getLabel());
	    	adaptedFolder.setMailsList(folder.getMailList());
	    	adaptedFolder.setRestricted(folder.isRestricted());
	   	
	    	return adaptedFolder;
	    }


}
