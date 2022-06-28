package crm.gui.tablerenderer.buscadorPptoFacturados;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class PresupuestosFacturadosTableModel implements TableModel, Comparable {
	public static final int COLUMNA_NUMERO = 0;
	
	public static final int COLUMNA_RAZON_SOCIAL = 1;
	
	public static final int COLUMNA_EVENTO = 2;
	
	public static final int COLUMNA_NRO_FACTURA = 3;
	
	public static final int COLUMNA_NRO_FACTURA_ADELANTO = 4;
	
	public static final int COLUMNA_FACTURADO_PRO = 5;

	private static final String[] colnames = new String[] { "Ppto",
			"Cliente Facturado", "Nombre Evento", "Factura","Fact.Adelanto","Facturado por"};

	protected transient Vector listeners;

	private List rows;

	public PresupuestosFacturadosTableModel() {
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}

	public PresupuestosFacturadosTableModel(List<PrespuestosFacturadosItem> prows) {
		this();

		for (PrespuestosFacturadosItem item : prows) {
			rows.add(item);
		}
	}

	public void addRow() {
		rows.add(new PrespuestosFacturadosItem());
	}

	public void addRow(PrespuestosFacturadosItem item) {
		rows.add(item);
	}

	public void removeRow(PrespuestosFacturadosItem item) {
		rows.remove(item);
	}

	public List getRows() {
		return this.rows;
	}

	public void clear() {
		this.rows.clear();
	}

	public PrespuestosFacturadosItem getRow(int idx) {
		if (idx < 0 || idx >= rows.size())
			return null;

		return (PrespuestosFacturadosItem) rows.get(idx);
	}
	
	public PrespuestosFacturadosItem[] getRows(int[] idx){
		if(idx.length<=0)
			return null;
		
		PrespuestosFacturadosItem[] items = new PrespuestosFacturadosItem[idx.length];
		for(int i=0; i<idx.length; i++){
			items[i]=(PrespuestosFacturadosItem) rows.get(idx[i]);
		}
		
		if(items == null){
			return null;
		}
		return items;
	}
	
	public ArrayList<PrespuestosFacturadosItem> getArrayRows(int[] idx){
		if(idx.length<=0)
			return null;
		
		ArrayList<PrespuestosFacturadosItem> items = new ArrayList<PrespuestosFacturadosItem>();
		for(int i=0; i<idx.length; i++){
			items.add((PrespuestosFacturadosItem) rows.get(idx[i]));
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
		case COLUMNA_NRO_FACTURA_ADELANTO:
			return true;
			default:
				return false;
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return null;

		PrespuestosFacturadosItem item = (PrespuestosFacturadosItem) rows.get(rowIndex);

		switch (columnIndex) {
		case COLUMNA_NUMERO:
			return item.getNroPpto();
		case COLUMNA_RAZON_SOCIAL:
			return item.getCliente();
		case COLUMNA_EVENTO:
			return item.getNombreEvento();
		case COLUMNA_NRO_FACTURA:
			return item.getNroFactura();
		case COLUMNA_NRO_FACTURA_ADELANTO:
			return item.getNroFacturaAdelanto();
		case COLUMNA_FACTURADO_PRO:
			return item.getFacturadoPor();
		default:
			return null;
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {	
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		PrespuestosFacturadosItem item = (PrespuestosFacturadosItem) rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();
		
		switch (columnIndex) {			
		case COLUMNA_NUMERO:
			item.setNroPpto(Long.parseLong(sValue));
			break;
		case COLUMNA_RAZON_SOCIAL:
			item.setCliente(sValue);
			break;
		case COLUMNA_EVENTO:
			item.setNombreEvento(sValue);
			break;
		case COLUMNA_NRO_FACTURA:
			item.setNroFactura(sValue);
			break;
		case COLUMNA_NRO_FACTURA_ADELANTO:
			item.setNroFacturaAdelanto(sValue);
			break;
		case COLUMNA_FACTURADO_PRO:
			item.setFacturadoPor(sValue);
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

}
