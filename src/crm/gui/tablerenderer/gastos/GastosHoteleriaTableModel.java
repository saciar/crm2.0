package crm.gui.tablerenderer.gastos;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;

public class GastosHoteleriaTableModel implements TableModel,Comparable {
	public static final int COLUMNA_DETALLE = 0;
	public static final int COLUMNA_COSTO = 1;
	
	private static final String[] colnames = new String[]{ "Detalle","Costo"};
	
	protected transient Vector listeners;
	private List rows;

	public GastosHoteleriaTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public GastosHoteleriaTableModel(List<GastosHoteleriaItem> prows){
		this();
		
		for (GastosHoteleriaItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new GastosHoteleriaItem());
	}
	public void addRow(GastosHoteleriaItem item){
		rows.add(item);
	}
	
	public void removeRow(GastosHoteleriaItem item){
		rows.remove(item);
	}
	
	public List getRows(){
		return this.rows;
	}
	
	public void clear(){
		this.rows.clear();
	}

	public GastosHoteleriaItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (GastosHoteleriaItem)rows.get(idx);
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
		
		GastosHoteleriaItem item = (GastosHoteleriaItem)rows.get(rowIndex);
		
		switch (columnIndex){		
		case COLUMNA_DETALLE:
			return item.getDetalle();
		case COLUMNA_COSTO:
			return item.getCostoFormateado();
		default:
			return null;
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		GastosHoteleriaItem item = (GastosHoteleriaItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_DETALLE:
		    item.setDetalle(sValue);
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
