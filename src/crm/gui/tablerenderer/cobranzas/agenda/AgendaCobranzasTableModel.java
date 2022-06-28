package crm.gui.tablerenderer.cobranzas.agenda;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import crm.gui.tablerenderer.cobranzas.agenda.AgendaCobranzasItem;

public class AgendaCobranzasTableModel implements TableModel, Comparable, Cloneable {
	public static final int COLUMNA_TIPO_ICONO = 0;
	
	public static final int COLUMNA_ESTADO = 1;
	
	public static final int COLUMNA_TAREA = 2;
	
	public static final int COLUMNA_FACTURA = 3;
	
	public static final int COLUMNA_MONTO = 4;
	
	public static final int COLUMNA_FECHA = 5;


	private static final String[] colnames = new String[] { "Rec.","Est.",
			"Asunto", "Factura", "A cobrar","Vencimiento"};

	protected transient Vector listeners;

	private List rows;

	public AgendaCobranzasTableModel() {
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}

	public AgendaCobranzasTableModel(List<AgendaCobranzasItem> prows) {
		this();

		for (AgendaCobranzasItem item : prows) {
			rows.add(item);
		}
	}

	public void addRow() {
		rows.add(new AgendaCobranzasItem());
	}

	public void addRow(AgendaCobranzasItem item) {
		rows.add(item);
	}

	public void removeRow(AgendaCobranzasItem item) {
		rows.remove(item);
	}

	public List getRows() {
		return this.rows;
	}

	public void clear() {
		this.rows.clear();
	}

	public AgendaCobranzasItem getRow(int idx) {
		if (idx < 0 || idx >= rows.size())
			return null;

		return (AgendaCobranzasItem) rows.get(idx);
	}
	
	public AgendaCobranzasItem[] getRows(int[] idx){
		if(idx.length<=0)
			return null;
		
		AgendaCobranzasItem[] items = new AgendaCobranzasItem[idx.length];
		for(int i=0; i<idx.length; i++){
			items[i]=(AgendaCobranzasItem) rows.get(idx[i]);
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
		case COLUMNA_ESTADO:
			return Boolean.class;
		case COLUMNA_TIPO_ICONO:
			return Integer.class;
		default:
			return String.class;
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex){	
		case COLUMNA_TAREA:	
			return true;
		case COLUMNA_ESTADO:
			return true;
		case COLUMNA_FECHA:
			return true;
		case COLUMNA_MONTO:
			return true;
		default:
			return false;
		}	
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return null;

		AgendaCobranzasItem item = (AgendaCobranzasItem) rows.get(rowIndex);

		switch (columnIndex) {
		case COLUMNA_TIPO_ICONO:
			return item.getTipoIcono();
		case COLUMNA_ESTADO:
			return item.isCompletada();
		case COLUMNA_FECHA:
			return item.getFechaTarea();
		case COLUMNA_TAREA:
			return item.getTarea();
		case COLUMNA_MONTO:
			return item.getMonto();
		case COLUMNA_FACTURA:
			return item.getFactura();	
		default:
			return null;
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		AgendaCobranzasItem item = (AgendaCobranzasItem) rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();
		
		switch (columnIndex) {
		case COLUMNA_TIPO_ICONO:
			item.setTipoIcono(Integer.parseInt(sValue));
			break;
		case COLUMNA_ESTADO:
			item.setCompletada(Boolean.parseBoolean(sValue));
			break;
		case COLUMNA_FECHA:
			item.setFechaTarea(sValue);
			break;
		case COLUMNA_TAREA:
			item.setTarea(sValue);
			break;
		case COLUMNA_MONTO:
			item.setMonto(sValue);
			break;
		case COLUMNA_FACTURA:
			item.setFactura(sValue);
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
	
	public Object clone(){
        Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }


}
