package crm.gui.tablerenderer.cobranzas;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class CobranzasTableModel implements TableModel, Comparable {
	public static final int COLUMNA_NUMERO = 0;
	
	public static final int COLUMNA_RAZON_SOCIAL_FAC = 1;
	
	public static final int COLUMNA_RAZON_SOCIAL = 2;
	
	public static final int COLUMNA_NRO_FACTURA = 3;
	
	public static final int COLUMNA_NRO_FACTURA_ADELANTO = 4;
	
	public static final int COLUMNA_NRO_FACTURA_ADICIONAL = 5;

	public static final int COLUMNA_FECHA_FACTURADO = 6;

	public static final int COLUMNA_IMPORTE_TOTAL = 7;	
	
	public static final int COLUMNA_TIPO_ICONO = 8;

	private static final String[] colnames = new String[] { "Numero",
			"Cliente Facturado", "Cliente de Evento", "Factura","Fact.Adelanto","Fact.Adicional",
			"Fecha Facturado", "Importe", "Agenda"};

	protected transient Vector listeners;

	private List rows;

	public CobranzasTableModel() {
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}

	public CobranzasTableModel(List<CobranzasItem> prows) {
		this();

		for (CobranzasItem item : prows) {
			rows.add(item);
		}
	}

	public void addRow() {
		rows.add(new CobranzasItem());
	}

	public void addRow(CobranzasItem item) {
		rows.add(item);
	}

	public void removeRow(CobranzasItem item) {
		rows.remove(item);
	}

	public List getRows() {
		return this.rows;
	}

	public void clear() {
		this.rows.clear();
	}

	public CobranzasItem getRow(int idx) {
		if (idx < 0 || idx >= rows.size())
			return null;

		return (CobranzasItem) rows.get(idx);
	}
	
	public CobranzasItem[] getRows(int[] idx){
		if(idx.length<=0)
			return null;
		
		CobranzasItem[] items = new CobranzasItem[idx.length];
		for(int i=0; i<idx.length; i++){
			items[i]=(CobranzasItem) rows.get(idx[i]);
		}
		
		if(items == null){
			return null;
		}
		return items;
	}
	
	public ArrayList<CobranzasItem> getArrayRows(int[] idx){
		if(idx.length<=0)
			return null;
		
		ArrayList<CobranzasItem> items = new ArrayList<CobranzasItem>();
		for(int i=0; i<idx.length; i++){
			items.add((CobranzasItem) rows.get(idx[i]));
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
		case COLUMNA_TIPO_ICONO:
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

		CobranzasItem item = (CobranzasItem) rows.get(rowIndex);

		switch (columnIndex) {
		case COLUMNA_NUMERO:
			return item.getNumeroDePresupuesto();
		case COLUMNA_RAZON_SOCIAL:
			return item.getRazonSocial();
		case COLUMNA_RAZON_SOCIAL_FAC:
			return item.getClienteFacturacion();	
		case COLUMNA_NRO_FACTURA:
			return item.getFactura();
		case COLUMNA_NRO_FACTURA_ADELANTO:
			return item.getFacturaAdelanto();
		case COLUMNA_NRO_FACTURA_ADICIONAL:
			return item.getFacturaAdicional();
		case COLUMNA_FECHA_FACTURADO:
			return item.getFechaFacturado();
		case COLUMNA_IMPORTE_TOTAL:
			return item.getImporteTotal();
		case COLUMNA_TIPO_ICONO:
			return item.getTipoIcono();
		default:
			return null;
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {	
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		CobranzasItem item = (CobranzasItem) rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();
		
		switch (columnIndex) {			
		case COLUMNA_NUMERO:
			item.setNumeroDePresupuesto(Long.parseLong(sValue));
			break;
		case COLUMNA_RAZON_SOCIAL:
			item.setRazonSocial(sValue);
			break;
		case COLUMNA_RAZON_SOCIAL_FAC:
			item.setClienteFacturacion(sValue);
			break;
		case COLUMNA_NRO_FACTURA:
			item.setFactura(sValue);
			break;
		case COLUMNA_NRO_FACTURA_ADELANTO:
			item.setFacturaAdelanto(sValue);
			break;
		case COLUMNA_NRO_FACTURA_ADICIONAL:
			item.setFacturaAdicional(sValue);
			break;
		case COLUMNA_FECHA_FACTURADO:
			item.setFechaFacturado(sValue);
			break;
		case COLUMNA_IMPORTE_TOTAL:
			item.setImporteTotal(sValue);
			break;
		case COLUMNA_TIPO_ICONO:
			item.setTipoIcono(Integer.parseInt(sValue));
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
