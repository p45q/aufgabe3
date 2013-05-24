package gui;

import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import mail.Mail;

public class MailTableModel implements TableModel{
	private Vector<Mail> mails = new Vector<Mail>();
	private Vector<TableModelListener> listeners = new Vector<TableModelListener>();
 
	public void addMail(Mail mailObj){
		int index = mails.size();
		//neues mailobj zur vecot liste hinzufügen
		mails.add(mailObj);
 
		// zuerst ein event, "neue row an der stelle index" herstellen
		TableModelEvent e = new TableModelEvent(this, index, index, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
 
		// nun das event verschicken
		for( int i = 0, n = listeners.size(); i<n; i++ ){
			((TableModelListener)listeners.get(i)).tableChanged(e);
		}
	}
 
	// die anzahl columns
	public int getColumnCount() {
		return 3;
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
			default: return null;
		}
	}
 
	// eine angabe, welchen typ von objekten in den columns angezeigt werden soll
	public Class getColumnClass(int columnIndex) {
		switch( columnIndex ){
			case 0: return String.class;
			case 1: return String.class;
			case 2: return String.class;
			default: return null;
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