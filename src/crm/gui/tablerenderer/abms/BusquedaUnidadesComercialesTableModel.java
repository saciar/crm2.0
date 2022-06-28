package crm.gui.tablerenderer.abms;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BusquedaUnidadesComercialesTableModel implements TableModel,Comparable {
	public static final int COLUMNA_CODIGO= 0;
	public static final int COLUMNA_UNIDAD = 1;
	
	private static final String[] colnames = new String[]{ 
		"Código","Nombre de la unidad comercial"};
	
	protected transient Vector listeners;
	private List<BusquedaUnidadesComercialesItem> rows;
	
	public BusquedaUnidadesComercialesTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public BusquedaUnidadesComercialesTableModel(List<BusquedaUnidadesComercialesItem> prows){
		this();
		
		for (BusquedaUnidadesComercialesItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new BusquedaUnidadesComercialesItem());
	}
	
	public void addRow(BusquedaUnidadesComercialesItem item){
		rows.add(item);
	}
	
	public void addRow(BusquedaUnidadesComercialesItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param item : item a remover.
	 */
	public void removeRow(BusquedaUnidadesComercialesItem item){
		rows.remove(item);
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param idx : numero de fila a remover. 
	 */
	public void removeRow(int idx){
		rows.remove(idx);
	}
	
	public BusquedaUnidadesComercialesItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (BusquedaUnidadesComercialesItem)rows.get(idx);
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
		
		BusquedaUnidadesComercialesItem item = (BusquedaUnidadesComercialesItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_CODIGO:
			return item.getCodigo();		
		case COLUMNA_UNIDAD:
			return item.getUnidad();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		BusquedaUnidadesComercialesItem item = (BusquedaUnidadesComercialesItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_CODIGO:
			item.setCodigo(sValue);
			break;
		case COLUMNA_UNIDAD:
			item.setUnidad(sValue);
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

	public List<BusquedaUnidadesComercialesItem> getRows() {
		return rows;
	}

	public void setRows(List<BusquedaUnidadesComercialesItem> rows) {
		this.rows = rows;
	}
	
}
