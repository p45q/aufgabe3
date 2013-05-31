
package storage.datamanager.adapters;


import gui.tree.Folder;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import mail.MailAccount;

import storage.datamanager.adapters.models.AdaptedFolder;
import storage.datamanager.adapters.models.AdaptedMailAccount;
public class FolderAdapter extends XmlAdapter<AdaptedFolder, Folder> {
		
		@Override
	    public Folder unmarshal(AdaptedFolder adaptedFolder) throws Exception {
	        Folder restoredFolder = new Folder(adaptedFolder.getLabel(), adaptedFolder.isRestricted());
	        
	        restoredFolder.setMailsList(adaptedFolder.getMailList());
	        
	        return restoredFolder;
	    }

	    @Override
	    public AdaptedFolder marshal(Folder folder) throws Exception {
	    	AdaptedFolder adaptedFolder = new AdaptedFolder();
	    	
	    	adaptedFolder.setLabel(folder.getLabel());
	    	adaptedFolder.setMailsList(folder.getMailList());
	    	adaptedFolder.setRestricted(folder.isRestricted());
	   	
	    	return adaptedFolder;
	    }


}
