package crm.gui.tablerenderer.abms;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BusquedaProvinciasTableModel implements TableModel,Comparable {
	public static final int COLUMNA_CODIGO= 0;
	public static final int COLUMNA_PAIS = 1;
	public static final int COLUMNA_PROVINCIA = 2;
	
	private static final String[] colnames = new String[]{ 
		"Código","Nombre del país","Nombre de la provincia"};
	
	protected transient Vector listeners;
	private List<BusquedaProvinciasItem> rows;
	
	public BusquedaProvinciasTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public BusquedaProvinciasTableModel(List<BusquedaProvinciasItem> prows){
		this();
		
		for (BusquedaProvinciasItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new BusquedaProvinciasItem());
	}
	
	public void addRow(BusquedaProvinciasItem item){
		rows.add(item);
	}
	
	public void addRow(BusquedaProvinciasItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param item : item a remover.
	 */
	public void removeRow(BusquedaProvinciasItem item){
		rows.remove(item);
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param idx : numero de fila a remover. 
	 */
	public void removeRow(int idx){
		rows.remove(idx);
	}
	
	public BusquedaProvinciasItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (BusquedaProvinciasItem)rows.get(idx);
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
		if (rowIndex < 0 || rowIndex >= rows.size())
			return null;
		
		BusquedaProvinciasItem item = (BusquedaProvinciasItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_CODIGO:
			return item.getIdProvincia();		
		case COLUMNA_PAIS:
			return item.getNombrePais();
		case COLUMNA_PROVINCIA:
			return item.getNombreProvincia();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		BusquedaProvinciasItem item = (BusquedaProvinciasItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_CODIGO:
			item.setIdProvincia(sValue);
			break;
		case COLUMNA_PAIS:
			item.setNombrePais(sValue);
			break;
		case COLUMNA_PROVINCIA:
			item.setNombreProvincia(sValue);
			break;
		}
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

	public List<BusquedaProvinciasItem> getRows() {
		return rows;
	}

	public void setRows(List<BusquedaProvinciasItem> rows) {
		this.rows = rows;
	}



}
