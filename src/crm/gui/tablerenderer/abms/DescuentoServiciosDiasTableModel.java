package crm.gui.tablerenderer.abms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class DescuentoServiciosDiasTableModel implements TableModel,Comparable {
	public static final int COLUMNA_CODIGO= 0;
	public static final int COLUMNA_TITULO = 1;
	public static final int COLUMNA_DIAS = 2;
	public static final int COLUMNA_PORCENTAJE = 3;
	
	private static final String[] colnames = new String[]{ 
		"Código","Nombre corto","Días","Porc.(%)"};
	
	protected transient Vector listeners;
	private List<DescuentoServiciosDiasItem> rows;
	
	public DescuentoServiciosDiasTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public DescuentoServiciosDiasTableModel(List<DescuentoServiciosDiasItem> prows){
		this();
		
		for (DescuentoServiciosDiasItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new DescuentoServiciosDiasItem());
	}
	
	public void addRow(DescuentoServiciosDiasItem item){
		rows.add(item);
	}
	
	public void addRow(DescuentoServiciosDiasItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param item : item a remover.
	 */
	public void removeRow(DescuentoServiciosDiasItem item){
		rows.remove(item);
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param idx : numero de fila a remover. 
	 */
	public void removeRow(int idx){
		rows.remove(idx);
	}
	
	public DescuentoServiciosDiasItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (DescuentoServiciosDiasItem)rows.get(idx);
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
		if (columnIndex == COLUMNA_CODIGO || columnIndex == COLUMNA_TITULO){
			return false;
		}
		return true;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return null;
		
		DescuentoServiciosDiasItem item = (DescuentoServiciosDiasItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_CODIGO:
			return item.getCodigo();		
		case COLUMNA_TITULO:
			return item.getServicio();
		case COLUMNA_DIAS:
			return item.getDias();
		case COLUMNA_PORCENTAJE:
			return item.getPorcentaje();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		DescuentoServiciosDiasItem item = (DescuentoServiciosDiasItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_CODIGO:
			item.setCodigo(sValue);
			break;
		case COLUMNA_TITULO:
			item.setServicio(sValue);
			break;
		case COLUMNA_DIAS:
			item.setDias(sValue);
			break;
		case COLUMNA_PORCENTAJE:
			item.setPorcentaje(sValue);
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

	public List<DescuentoServiciosDiasItem> getRows() {
		return rows;
	}

	public void setRows(List<DescuentoServiciosDiasItem> rows) {
		this.rows = rows;
	}
	
	public boolean isInRows(DescuentoServiciosDiasItem item){
		Iterator it = rows.iterator();
		while(it.hasNext()){
			DescuentoServiciosDiasItem item2 = (DescuentoServiciosDiasItem)it.next();
			if(item2.equals(item)){
				return true;
			}
		}
		return false;
	}

}
