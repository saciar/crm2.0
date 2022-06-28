package crm.gui.tablerenderer.seguimiento;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SeguimientoTableModel implements TableModel,Comparable {
	public static final int COLUMNA_FECHA= 0;
	public static final int COLUMNA_USUARIO = 1;
	public static final int COLUMNA_ACCION = 2;
	public static final int COLUMNA_RESULTADO = 3;
	
	private static final String[] colnames = new String[]{ 
		"Fecha","Usuario","Acción","Resultado"};
	
	protected transient Vector listeners;
	private List<SeguimientoItem> rows;
	
	public SeguimientoTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public SeguimientoTableModel(List<SeguimientoItem> prows){
		this();
		
		for (SeguimientoItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new SeguimientoItem());
	}
	
	public void addRow(SeguimientoItem item){
		rows.add(item);
	}
	
	public void addRow(SeguimientoItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}

	public SeguimientoItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (SeguimientoItem)rows.get(idx);
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
		
		SeguimientoItem item = (SeguimientoItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_FECHA:
			return item.getFecha();		
		case COLUMNA_USUARIO:
			return item.getUsuario();
		case COLUMNA_ACCION:
			return item.getAccion();
		case COLUMNA_RESULTADO:
			return item.getResultado();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		SeguimientoItem item = (SeguimientoItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_FECHA:
			item.setFecha(sValue);
			break;
		case COLUMNA_USUARIO:
			item.setUsuario(sValue);
			break;
		case COLUMNA_ACCION:
			item.setAccion(sValue);
			break;
		case COLUMNA_RESULTADO:
			item.setResultado(sValue);
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

	public List<SeguimientoItem> getRows() {
		return rows;
	}

	public void setRows(List<SeguimientoItem> rows) {
		this.rows = rows;
	}
}
