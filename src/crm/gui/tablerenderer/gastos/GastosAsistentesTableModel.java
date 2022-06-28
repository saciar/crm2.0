package crm.gui.tablerenderer.gastos;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;

public class GastosAsistentesTableModel implements TableModel,Comparable {
	public static final int COLUMNA_ASISTENTE = 0;
	public static final int COLUMNA_CARGO = 1;
	public static final int COLUMNA_JORNADA = 2;	
	public static final int COLUMNA_COSTO = 3;
	
	private static final String[] colnames = new String[]{ "Asistente","Cargo","Jornada","Costo"};
	
	protected transient Vector listeners;
	private List rows;

	public GastosAsistentesTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public GastosAsistentesTableModel(List<GastosAsistentesItem> prows){
		this();
		
		for (GastosAsistentesItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new GastosAsistentesItem());
	}
	public void addRow(GastosAsistentesItem item){
		rows.add(item);
	}
	
	public void removeRow(GastosAsistentesItem item){
		rows.remove(item);
	}
	
	public List getRows(){
		return this.rows;
	}
	
	public void clear(){
		this.rows.clear();
	}

	public GastosAsistentesItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (GastosAsistentesItem)rows.get(idx);
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
		switch (columnIndex){		
		//case COLUMNA_COSTO:
		//	return Double.class;	
		default: 
			return String.class;
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return null;
		
		GastosAsistentesItem item = (GastosAsistentesItem)rows.get(rowIndex);
		
		switch (columnIndex){		
		case COLUMNA_ASISTENTE:
			return item.getAsistente();
		case COLUMNA_CARGO:
			return item.getCargo();
		case COLUMNA_JORNADA:
			return item.getJornada();			
		case COLUMNA_COSTO:
			return item.getCostoFormateado();
		default:
			return null;
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		GastosAsistentesItem item = (GastosAsistentesItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_ASISTENTE:
		    item.setAsistente(sValue);
		    break;
		case COLUMNA_CARGO:
		    item.setCargo(sValue);
		    break;
		case COLUMNA_JORNADA:
		    item.setJornada(sValue);
		    break;		    
		case COLUMNA_COSTO:
			item.setCosto(getDouble(sValue));
			break;
		}
		

	}

	private double getDouble(String val){
		if (StringUtils.isBlank(val))
			return 0;
		try {
			return Double.parseDouble(val);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	private int getInt(String val){
		if (StringUtils.isBlank(val))
			return 0;
		try {
			return Integer.parseInt(val);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
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

}
