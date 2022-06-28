package crm.gui.tablerenderer.historialCliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import crm.gui.tablerenderer.historialCliente.HistorialClienteItem;

public class HistorialClienteTableModel implements TableModel, Comparable {
	public static final int COLUMNA_NUMERO = 0;
	
	public static final int COLUMNA_CREADOR = 1;
	
	public static final int COLUMNA_EVENTO = 2;
	
	public static final int COLUMNA_FECHA_EVENTO = 3;
	
	public static final int COLUMNA_NUMERO_FACTURA = 4;
	
	public static final int COLUMNA_NUMERO_FACTURA_2 = 5;

	public static final int COLUMNA_TOTAL = 6;	

	private static final String[] colnames = new String[] { "Nro Presupuesto", "Vendedor", "Evento",
			"Fecha de evento", "Nro Factura","Nro Factura Adel", "Importe"};

	protected transient Vector listeners;

	private List rows;

	public HistorialClienteTableModel() {
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}

	public HistorialClienteTableModel(List<HistorialClienteItem> prows) {
		this();

		for (HistorialClienteItem item : prows) {
			rows.add(item);
		}
	}

	public void addRow() {
		rows.add(new HistorialClienteItem());
	}

	public void addRow(HistorialClienteItem item) {
		rows.add(item);
	}

	public void removeRow(HistorialClienteItem item) {
		rows.remove(item);
	}

	public List getRows() {
		return this.rows;
	}

	public void clear() {
		this.rows.clear();
	}

	public HistorialClienteItem getRow(int idx) {
		if (idx < 0 || idx >= rows.size())
			return null;

		return (HistorialClienteItem) rows.get(idx);
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
		return false;		
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return null;

		HistorialClienteItem item = (HistorialClienteItem) rows.get(rowIndex);

		switch (columnIndex) {
		case COLUMNA_NUMERO:
			return item.getNumeroPresupuesto();
		case COLUMNA_FECHA_EVENTO:
			return item.getFechaEvento();
		case COLUMNA_NUMERO_FACTURA:
			return item.getNumeroFacturas();	
		case COLUMNA_NUMERO_FACTURA_2:
			return item.getNumeroFacturas2();
		case COLUMNA_TOTAL:
			return item.getImporteTotal();
		case COLUMNA_CREADOR:
			return item.getVendedor();
		case COLUMNA_EVENTO:
			return item.getEvento();
		default:
			return null;
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {	
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
