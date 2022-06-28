package crm.gui.tablerenderer.gastos;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;

public class GastosSubContratacionesGeneralTableModel implements TableModel,Comparable {
	public static final int COLUMNA_DETALLE = 0;
	public static final int COLUMNA_PROVEEDOR = 1;
	public static final int COLUMNA_COSTO = 2;
	public static final int COLUMNA_PRECIO = 3;
	public static final int COLUMNA_NETO = 4;
	
	private static final String[] colnames = new String[]{ 
		"Detalle","Proveedor","Costo","Precio", "Neto"
	};
	
	protected transient Vector listeners;
	private List rows;

	public GastosSubContratacionesGeneralTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public GastosSubContratacionesGeneralTableModel(List<GastosSubContratacionesGeneralItem> prows){
		this();
		
		for (GastosSubContratacionesGeneralItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new GastosSubContratacionesGeneralItem());
	}
	public void addRow(GastosSubContratacionesGeneralItem item){
		rows.add(item);
	}
	
	public void removeRow(GastosSubContratacionesGeneralItem item){
		rows.remove(item);
	}
	
	public List getRows(){
		return this.rows;
	}
	
	public void clear(){
		this.rows.clear();
	}

	public GastosSubContratacionesGeneralItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (GastosSubContratacionesGeneralItem)rows.get(idx);
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
		/*case COLUMNA_COSTO:
			return Double.class;
		case COLUMNA_PRECIO:
			return Double.class;
		case COLUMNA_NETO:
			return Double.class;*/			
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
		
		GastosSubContratacionesGeneralItem item = (GastosSubContratacionesGeneralItem)rows.get(rowIndex);
		
		switch (columnIndex){		
		case COLUMNA_DETALLE:
			return item.getDetalle();
		case COLUMNA_PROVEEDOR:
			return item.getProveedor();
		case COLUMNA_COSTO:
			return item.getCostoFormateado();
		case COLUMNA_PRECIO:
			return item.getPrecioFormateado();
		case COLUMNA_NETO:
			return item.getNetoFormateado();
		default:
			return null;
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		GastosSubContratacionesGeneralItem item = (GastosSubContratacionesGeneralItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_DETALLE:
			item.setDetalle(sValue);
			break;
		case COLUMNA_PROVEEDOR:
			item.setProveedor(sValue);
			break;
		case COLUMNA_COSTO:
			item.setCosto(getDouble(sValue));
			//calculo el neto
			//calculateNeto(item);
			break;
		case COLUMNA_PRECIO:
			item.setPrecio(getDouble(sValue));
			break;
		case COLUMNA_NETO:
			item.setNeto(getDouble(sValue));
			break;
		}
		

	}
	
	private void calculateNeto(GastosSubContratacionesGeneralItem item){
		item.setNeto( item.getPrecio() - item.getCosto());		
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
