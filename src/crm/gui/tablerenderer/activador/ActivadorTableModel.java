package crm.gui.tablerenderer.activador;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import crm.gui.tablerenderer.activador.ActivadorItem;

public class ActivadorTableModel implements TableModel,Comparable {
	public static final int COLUMNA_NUMERO= 0;
	public static final int COLUMNA_ESTADO = 1;
	public static final int COLUMNA_VENDEDOR = 2;
	public static final int COLUMNA_CLIENTE = 3;
	public static final int COLUMNA_NOMBRE_EVENTO = 4;
	public static final int COLUMNA_ACTIVO = 5;
	
	private static final String[] colnames = new String[]{ 
		"Número","Estado","Vendedor","Cliente","Nombre del evento","Está Abierto?"};
	
	protected transient Vector listeners;
	private List<ActivadorItem> rows;
	
	public ActivadorTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public ActivadorTableModel(List<ActivadorItem> prows){
		this();
		
		for (ActivadorItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new ActivadorItem());
	}
	
	public void addRow(ActivadorItem item){
		rows.add(item);
	}
	
	public void addRow(ActivadorItem item, int pos){
		rows.add(pos, item);
	}
	
	public void removeRow(ActivadorItem item){
		rows.remove(item);
	}
	
	public void removeRow(int pos){
		rows.remove(pos);
	}
	
	public void clear(){
		this.rows.clear();
	}

	public ActivadorItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (ActivadorItem)rows.get(idx);
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
		
		ActivadorItem item = (ActivadorItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_NUMERO:
			return item.getNumeroPpto();		
		case COLUMNA_ESTADO:
			return item.getEstado();
		case COLUMNA_VENDEDOR:
			return item.getVendedor();
		case COLUMNA_CLIENTE:
			return item.getCliente();
		case COLUMNA_NOMBRE_EVENTO:
			return item.getNombreEvento();
		case COLUMNA_ACTIVO:
			return item.getActivo();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		ActivadorItem item = (ActivadorItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_NUMERO:
			item.setNumeroPpto(sValue);
			break;
		case COLUMNA_ESTADO:
			item.setEstado(sValue);
			break;
		case COLUMNA_VENDEDOR:
			item.setVendedor(sValue);
			break;
		case COLUMNA_CLIENTE:
			item.setCliente(sValue);
			break;
		case COLUMNA_NOMBRE_EVENTO:
			item.setNombreEvento(sValue);
			break;
		case COLUMNA_ACTIVO:
			item.setActivo(sValue);
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

	public List<ActivadorItem> getRows() {
		return rows;
	}

	public void setRows(List<ActivadorItem> rows) {
		this.rows = rows;
	}
}
