package crm.gui.tablerenderer.horarios;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class HorariosTableModel implements TableModel {
	public static final int COLUMNA_FECHA= 0;
	public static final int COLUMNA_HORA_DESDE = 1;
	public static final int COLUMNA_HORA_HASTA = 2;
	
	private static final String[] colnames = new String[]{ 
		"Fecha","Hora Desde","Hora Hasta"};
	
	protected transient Vector listeners;
	private List<HorariosItem> rows;
	
	public HorariosTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public HorariosTableModel(List<HorariosItem> prows){
		this();
		
		for (HorariosItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new HorariosItem());
	}
	
	public void addRow(HorariosItem item){
		rows.add(item);
	}
	
	public void addRow(HorariosItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}

	public HorariosItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (HorariosItem)rows.get(idx);
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
	
	@SuppressWarnings("unchecked")
	public Class getColumnClass(int columnIndex) {
		if(columnIndex==1 || columnIndex==2)
			return Integer.class;
		else
			return String.class;

	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex==1 || columnIndex==2)
			return true;
		else
			return false;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return null;
		
		HorariosItem item = (HorariosItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_FECHA:
			return item.getFecha();		
		case COLUMNA_HORA_DESDE:
			return item.getHoraDesde();
		case COLUMNA_HORA_HASTA:
			return item.getHoraHasta();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		HorariosItem item = (HorariosItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_FECHA:
			item.setFecha(sValue);
			break;
		case COLUMNA_HORA_DESDE:
			item.setHoraDesde(sValue);
			break;
		case COLUMNA_HORA_HASTA:
			item.setHoraHasta(sValue);
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

	public List<HorariosItem> getRows() {
		return rows;
	}

	public void setRows(List<HorariosItem> rows) {
		this.rows = rows;
	}

}
