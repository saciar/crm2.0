package crm.gui.tablerenderer.abms;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BusquedaTableModel implements TableModel,Comparable{
	protected transient Vector listeners;
	protected List<BusquedaItem> rows;
	protected String[] colnames;
	public BusquedaTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
		colnames=null;
	}
	
	public BusquedaTableModel(List<BusquedaItem> prows){
		this();
		
		for (BusquedaItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new BusquedaItem());
	}
	
	public void addRow(BusquedaItem item){
		rows.add(item);
	}
	
	public void addRow(BusquedaItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param item : item a remover.
	 */
	public void removeRow(BusquedaItem item){
		rows.remove(item);
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param idx : numero de fila a remover. 
	 */
	public void removeRow(int idx){
		rows.remove(idx);
	}
	
	public BusquedaItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (BusquedaItem)rows.get(idx);
	}
	
	public int getRowCount() {
		return rows.size();
	}
	
	public int getColumnCount() {
		return colnames.length;
	}

	public String getColumnName(int columnIndex) {
		return colnames[columnIndex];
	}

	public Class getColumnClass(int columnIndex) {
		return String.class;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

	}
	
	public void addTableModelListener(TableModelListener l) {
		listeners.addElement(l);
	}

	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}
	
	public int compareTo(Object arg0) {
		return 0;
	}

	public List<BusquedaItem> getRows() {
		return rows;
	}

	public void setRows(List<BusquedaItem> rows) {
		this.rows = rows;
	}
}
