package crm.gui.tablerenderer.alertasCobranzas;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class AlertaCobranzaTableModel implements TableModel, Comparable {
	
	public static final int COLUMNA_ICONO = 0;
	
	public static final int COLUMNA_NUMERO = 1;
	
	public static final int COLUMNA_ASUNTO = 2;
	
	public static final int COLUMNA_CLIENTE= 3;	
	
	public static final int COLUMNA_EVENTO= 4;
	
	public static final int COLUMNA_CREADO = 5;

	private static final String[] colnames = new String[] { "Tipo", "Nro Ppto", "Asunto", "Cliente", "Evento", "Fecha de Creación"};

	protected transient Vector listeners;

	private List rows;

	public AlertaCobranzaTableModel() {
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}

	public AlertaCobranzaTableModel(List<AlertaCobranzaItem> prows) {
		this();

		for (AlertaCobranzaItem item : prows) {
			rows.add(item);
		}
	}

	public void addRow() {
		rows.add(new AlertaCobranzaItem());
	}

	public void addRow(AlertaCobranzaItem item) {
		rows.add(item);
	}

	public void removeRow(AlertaCobranzaItem item) {
		rows.remove(item);
	}

	public List getRows() {
		return this.rows;
	}

	public void clear() {
		this.rows.clear();
	}

	public AlertaCobranzaItem getRow(int idx) {
		if (idx < 0 || idx >= rows.size())
			return null;

		return (AlertaCobranzaItem) rows.get(idx);
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
		case COLUMNA_ICONO:
			return Integer.class;
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

		AlertaCobranzaItem item = (AlertaCobranzaItem) rows.get(rowIndex);

		switch (columnIndex) {
		case COLUMNA_ICONO:
			return item.getTipoIcono();
		case COLUMNA_NUMERO:
			return item.getNumeroPresupuesto();
		case COLUMNA_ASUNTO:
			return item.getAsunto();
		case COLUMNA_CREADO:
			return item.getCreado();
		case COLUMNA_CLIENTE:
			return item.getCliente();
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
