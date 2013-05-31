package gui.table;

import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import mail.Mail;

public class ModelEmailTable extends AbstractTableModel{
	private CopyOnWriteArrayList<Mail> mails;
	private Vector<TableModelListener> listeners;
 
	public ModelEmailTable()
	{		
		super();
	
		mails = new CopyOnWriteArrayList<Mail>();
		listeners = new Vector<TableModelListener>();
	}
	
	public void addMail(Mail mailObj){
		int index = mails.size();
		
		//neues mailobj zur arrayliste hinzufügen
		mails.add(mailObj);
 
		// zuerst ein event, "neue row an der stelle index" herstellen
		TableModelEvent event = new TableModelEvent(this, index, index, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
		
		// nun das event verschicken
		fireAllListeners(event);
	}
 
	// die anzahl columns
	public int getColumnCount() {
		return 4;
	}
 
	// die anzahl mails
	public int getRowCount() {
		return mails.size();
	}
 
	// die titel der einzelnen columns
	public String getColumnName(int column) {
		switch(column){
			case 0: return "From";
			case 1: return "To";
			case 2: return "Subject";
			case 3: return "Received";
			default: return null;
		}
	}
	
	public Mail getMailObjAt(int rowIndex)
	{
		return (Mail)mails.get(rowIndex);
	}
 
	// der wert der telle (rowIndex, columnIndex)
	public Object getValueAt(int rowIndex, int columnIndex) {
		Mail mailObj = (Mail)mails.get( rowIndex );
 
		switch( columnIndex ){
			case 0: return mailObj.getFrom();
			case 1: return mailObj.getTo();
			case 2: return mailObj.getSubject();
			case 3: return mailObj.getFormatedReceiveDate();
			default: return null;
		}
	}
 
	// eine angabe, welchen typ von objekten in den columns angezeigt werden soll
	public Class<String> getColumnClass(int columnIndex) {
		switch( columnIndex ){
			case 0: return String.class;
			case 1: return String.class;
			case 2: return String.class;
			case 3: return String.class;
			default: return null;
		}	
	}
 
	public void clearTable()
	{
		mails.clear();
		fireAllListeners(null);
	}
	
	private void fireAllListeners(TableModelEvent event)
	{
		for( int i = 0, n = listeners.size(); i<n; i++ ){
			((TableModelListener)listeners.get(i)).tableChanged(event);
		}
	}
	
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}
	
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}
}