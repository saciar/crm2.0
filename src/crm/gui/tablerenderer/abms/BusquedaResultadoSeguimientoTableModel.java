package crm.gui.tablerenderer.abms;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BusquedaResultadoSeguimientoTableModel implements TableModel,Comparable {
	public static final int COLUMNA_CODIGO= 0;
	public static final int COLUMNA_ACCION = 1;
	public static final int COLUMNA_RESULTADO = 2;
	
	private static final String[] colnames = new String[]{ 
		"Código","Nombre de la acción","Nombre del resultado"};
	
	protected transient Vector listeners;
	private List<BusquedaResultadoSeguimientoItem> rows;
	
	public BusquedaResultadoSeguimientoTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public BusquedaResultadoSeguimientoTableModel(List<BusquedaResultadoSeguimientoItem> prows){
		this();
		
		for (BusquedaResultadoSeguimientoItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new BusquedaResultadoSeguimientoItem());
	}
	
	public void addRow(BusquedaResultadoSeguimientoItem item){
		rows.add(item);
	}
	
	public void addRow(BusquedaResultadoSeguimientoItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param item : item a remover.
	 */
	public void removeRow(BusquedaResultadoSeguimientoItem item){
		rows.remove(item);
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param idx : numero de fila a remover. 
	 */
	public void removeRow(int idx){
		rows.remove(idx);
	}
	
	public BusquedaResultadoSeguimientoItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (BusquedaResultadoSeguimientoItem)rows.get(idx);
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
		
		BusquedaResultadoSeguimientoItem item = (BusquedaResultadoSeguimientoItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_CODIGO:
			return item.getIdResultado();		
		case COLUMNA_ACCION:
			return item.getNombreAccion();
		case COLUMNA_RESULTADO:
			return item.getNombreResultado();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		BusquedaResultadoSeguimientoItem item = (BusquedaResultadoSeguimientoItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_CODIGO:
			item.setIdResultado(sValue);
			break;
		case COLUMNA_ACCION:
			item.setNombreAccion(sValue);
			break;
		case COLUMNA_RESULTADO:
			item.setNombreResultado(sValue);
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

	public List<BusquedaResultadoSeguimientoItem> getRows() {
		return rows;
	}

	public void setRows(List<BusquedaResultadoSeguimientoItem> rows) {
		this.rows = rows;
	}
}
