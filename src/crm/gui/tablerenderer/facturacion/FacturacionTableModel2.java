package crm.gui.tablerenderer.facturacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;

public class FacturacionTableModel2 implements TableModel, Comparable {
	public static final int COLUMNA_NUMERO = 0;
	
	public static final int COLUMNA_ESTADO = 1;
	
	public static final int COLUMNA_VENDEDOR = 2;
	
	public static final int COLUMNA_RAZON_SOCIAL = 3;

	public static final int COLUMNA_FECHA_CONFIRMACION = 4;

	public static final int COLUMNA_IMPORTE_TOTAL = 5;
	
	public static final int COLUMNA_NRO_FACTURA =6;

	private static final String[] colnames = new String[] { "Nro Ppto","Estado", "Vendedor",
			"Razón Social", "Fecha Confirmacion",
			"Importe Total", "Nro factura"};

	protected transient Vector listeners;

	private List rows;

	public FacturacionTableModel2() {
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}

	public FacturacionTableModel2(List<FacturacionItem> prows) {
		this();

		for (FacturacionItem item : prows) {
			rows.add(item);
		}
	}

	public void addRow() {
		rows.add(new FacturacionItem());
	}

	public void addRow(FacturacionItem item) {
		rows.add(item);
	}

	public void removeRow(FacturacionItem item) {
		rows.remove(item);
	}

	public List getRows() {
		return this.rows;
	}

	public void clear() {
		this.rows.clear();
	}

	public FacturacionItem getRow(int idx) {
		if (idx < 0 || idx >= rows.size())
			return null;

		return (FacturacionItem) rows.get(idx);
	}
	
	public FacturacionItem[] getRows(int[] idx){
		if(idx.length<=0)
			return null;
		
		FacturacionItem[] items = new FacturacionItem[idx.length];
		for(int i=0; i<idx.length; i++){
			items[i]=(FacturacionItem) rows.get(idx[i]);
		}
		
		if(items == null){
			return null;
		}
		return items;
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
		switch (columnIndex) {
		case COLUMNA_NUMERO:
			return Long.class;
		default:
			return String.class;
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case COLUMNA_NRO_FACTURA:
			return true;		
		default:
			return false;	
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return null;

		FacturacionItem item = (FacturacionItem) rows.get(rowIndex);

		switch (columnIndex) {
		case COLUMNA_NUMERO:
			return item.getNumeroDePresupuesto();
		case COLUMNA_ESTADO:
			return item.getEstado();
		case COLUMNA_VENDEDOR:
			return item.getVendedor();
		case COLUMNA_RAZON_SOCIAL:
			return item.getRazonSocial();		
		case COLUMNA_FECHA_CONFIRMACION:
			return item.getFechaConfirmacion();
		case COLUMNA_IMPORTE_TOTAL:
			return item.getImporteTotal();
		case COLUMNA_NRO_FACTURA:
			return item.getFacturas();
		default:
			return null;
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {	
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		FacturacionItem item = (FacturacionItem) rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_NUMERO:
		    item.setNumeroDePresupuesto(getLong(sValue));
		    break;
		case COLUMNA_ESTADO:
			item.setEstado(sValue);
			break;
		case COLUMNA_VENDEDOR:
			item.setVendedor(sValue);
			break;
		case COLUMNA_RAZON_SOCIAL:
			item.setRazonSocial(sValue);
			break;
		case COLUMNA_FECHA_CONFIRMACION:
			item.setFechaConfirmacion(sValue);
			break;
		case COLUMNA_IMPORTE_TOTAL:
			item.setImporteTotal(sValue);
			break;
		case COLUMNA_NRO_FACTURA:
			item.setFacturas(sValue);
			break;
		}
	}
	
	private long getLong(String val){
		if (StringUtils.isBlank(val))
			return 0;
		try {
			return Long.parseLong(val);
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
