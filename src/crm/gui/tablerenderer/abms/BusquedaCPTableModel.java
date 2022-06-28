package crm.gui.tablerenderer.abms;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BusquedaCPTableModel implements TableModel,Comparable {
	public static final int COLUMNA_PAIS= 4;
	public static final int COLUMNA_PROVINCIA = 3;
	public static final int COLUMNA_PARTIDO = 2;
	public static final int COLUMNA_LOCALIDAD = 1;
	public static final int COLUMNA_CP = 0;

	private static final String[] colnames = new String[]{ 
		"CP","Localidad","Partido","Provincia","País"};
	
	protected transient Vector listeners;
	private List<BusquedaCPItem> rows;
	
	public BusquedaCPTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public BusquedaCPTableModel(List<BusquedaCPItem> prows){
		this();
		
		for (BusquedaCPItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new BusquedaCPItem());
	}
	
	public void addRow(BusquedaCPItem item){
		rows.add(item);
	}
	
	public void addRow(BusquedaCPItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param item : item a remover.
	 */
	public void removeRow(BusquedaCPItem item){
		rows.remove(item);
	}

	public BusquedaCPItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (BusquedaCPItem)rows.get(idx);
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
		
		BusquedaCPItem item = (BusquedaCPItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_PAIS:
			return item.getPais();		
		case COLUMNA_PROVINCIA:
			return item.getProvincia();
		case COLUMNA_PARTIDO:
			return item.getPartido();
		case COLUMNA_LOCALIDAD:
			return item.getLocalidad();
		case COLUMNA_CP:
			return item.getCodigo();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		BusquedaCPItem item = (BusquedaCPItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_PAIS:
			item.setPais(sValue);
			break;
		case COLUMNA_PROVINCIA:
			item.setProvincia(sValue);
			break;
		case COLUMNA_PARTIDO:
			item.setPartido(sValue);
			break;
		case COLUMNA_LOCALIDAD:
			item.setLocalidad(sValue);
			break;
		case COLUMNA_CP:
			item.setCodigo(sValue);
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

	public List<BusquedaCPItem> getRows() {
		return rows;
	}

	public void setRows(List<BusquedaCPItem> rows) {
		this.rows = rows;
	}

}
