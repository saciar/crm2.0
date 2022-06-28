package crm.gui.tablerenderer.abms;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BusquedaServiciosTableModel implements TableModel,Comparable {
	public static final int COLUMNA_CODIGO= 0;
	public static final int COLUMNA_TITULO = 1;
	public static final int COLUMNA_DESCRIPCION = 2;
	public static final int COLUMNA_PRECIO = 3;
	
	private static final String[] colnames = new String[]{ 
		"Código","Nombre corto","Descripción","Precio"};
	
	protected transient Vector listeners;
	private List<BusquedaServiciosItem> rows;
	
	public BusquedaServiciosTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public BusquedaServiciosTableModel(List<BusquedaServiciosItem> prows){
		this();
		
		for (BusquedaServiciosItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new BusquedaServiciosItem());
	}
	
	public void addRow(BusquedaServiciosItem item){
		rows.add(item);
	}
	
	public void addRow(BusquedaServiciosItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param item : item a remover.
	 */
	public void removeRow(BusquedaServiciosItem item){
		rows.remove(item);
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param idx : numero de fila a remover. 
	 */
	public void removeRow(int idx){
		rows.remove(idx);
	}
	
	public BusquedaServiciosItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (BusquedaServiciosItem)rows.get(idx);
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
		
		BusquedaServiciosItem item = (BusquedaServiciosItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_CODIGO:
			return item.getCodigo();		
		case COLUMNA_TITULO:
			return item.getTitulo();
		case COLUMNA_DESCRIPCION:
			return item.getDescripcion();
		case COLUMNA_PRECIO:
			return item.getPrecio();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		BusquedaServiciosItem item = (BusquedaServiciosItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_CODIGO:
			item.setCodigo(sValue);
			break;
		case COLUMNA_TITULO:
			item.setTitulo(sValue);
			break;
		case COLUMNA_DESCRIPCION:
			item.setDescripcion(sValue);
			break;
		case COLUMNA_PRECIO:
			item.setPrecio(sValue);
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

	public List<BusquedaServiciosItem> getRows() {
		return rows;
	}
	
	public BusquedaServiciosItem[] getRows(int[] idx){
		if(idx.length<=0)
			return null;
		
		BusquedaServiciosItem[] items = new BusquedaServiciosItem[idx.length];
		for(int i=0; i<idx.length; i++){
			items[i]=(BusquedaServiciosItem) rows.get(idx[i]);
		}
		
		if(items == null){
			return null;
		}
		return items;
	}

	public void setRows(List<BusquedaServiciosItem> rows) {
		this.rows = rows;
	}

}
