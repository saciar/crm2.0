package crm.gui.tablerenderer.rentabilidadgerencia;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MargenesTableModel implements TableModel,Comparable, Printable {
	public static final int COLUMNA_NUMERO= 0;
	public static final int COLUMNA_FACTURACION_PPTO = 1;
	public static final int COLUMNA_COMISIONES_PPTO = 2;
	public static final int COLUMNA_COSTO_EQ_P_PPTO = 3;
	public static final int COLUMNA_COSTO_EQ_3_PPTO = 4;
	public static final int COLUMNA_COSTO_MO_PPTO = 5;
	public static final int COLUMNA_MARGEN_PPTO = 6;
	public static final int COLUMNA_FACTURACION_REAL = 7;
	public static final int COLUMNA_COMISIONES_REAL = 8;
	public static final int COLUMNA_COSTO_EQ_P_REAL = 9;
	public static final int COLUMNA_COSTO_EQ_3_REAL = 10;
	public static final int COLUMNA_COSTO_MO_REAL = 11;
	public static final int COLUMNA_MARGEN_REAL = 12;
	
	private static final String[] colnames = new String[]{ 
		"Nro","Facturacion","Comisiones","Costo Eq. propios","Costo Eq. 3ros","Costos Mano Obra", "Margen","Facturacion","Comisiones","Costo Eq. propios","Costo Eq. 3ros","Costos Mano Obra", "Margen"};
	
	protected transient Vector listeners;
	private List<MargenesItem> rows;
	
	public MargenesTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public MargenesTableModel(List<MargenesItem> prows){
		this();
		
		for (MargenesItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new MargenesItem());
	}
	
	public void addRow(MargenesItem item){
		rows.add(item);
	}
	
	public void addRow(MargenesItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}

	public MargenesItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (MargenesItem)rows.get(idx);
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
		switch (columnIndex){	
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
		
		MargenesItem item = (MargenesItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_NUMERO:
			return item.getNroPpto();	
		case COLUMNA_FACTURACION_PPTO:
			return item.getFacturacionPpto();
		case COLUMNA_COMISIONES_PPTO:
			return item.getComisionesPpto();
		case COLUMNA_COSTO_EQ_P_PPTO:
			return item.getCostoEqPropiosPpto();
		case COLUMNA_COSTO_EQ_3_PPTO:
			return item.getCostoEqTercPpto();
		case COLUMNA_COSTO_MO_PPTO:
			return item.getCostoMOPpto();
		case COLUMNA_MARGEN_PPTO:
			return item.getMargenPpto();
			
		case COLUMNA_FACTURACION_REAL:
			return item.getFacturacionReal();
		case COLUMNA_COMISIONES_REAL:
			return item.getComisionesReal();
		case COLUMNA_COSTO_EQ_P_REAL:
			return item.getCostoEqPropiosReal();
		case COLUMNA_COSTO_EQ_3_REAL:
			return item.getCostoEqTercReal();
		case COLUMNA_COSTO_MO_REAL:
			return item.getCostoMOReal();
		case COLUMNA_MARGEN_REAL:
			return item.getMargenReal();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		MargenesItem item = (MargenesItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_NUMERO:
			item.setNroPpto(sValue);
			break;
		case COLUMNA_FACTURACION_PPTO:
			item.setFacturacionPpto(sValue);
			break;
		case COLUMNA_COMISIONES_PPTO:
			item.setComisionesPpto(sValue);
			break;
		case COLUMNA_COSTO_EQ_P_PPTO:
			item.setCostoEqPropiosPpto(sValue);
			break;
		case COLUMNA_COSTO_EQ_3_PPTO:
			item.setCostoEqTercPpto(sValue);
			break;
		case COLUMNA_COSTO_MO_PPTO:
			item.setCostoMOPpto(sValue);
			break;
		case COLUMNA_MARGEN_PPTO:
			item.setMargenPpto(sValue);
			break;
		case COLUMNA_FACTURACION_REAL:
			item.setFacturacionReal(sValue);
			break;
		case COLUMNA_COMISIONES_REAL:
			item.setComisionesReal(sValue);
			break;
		case COLUMNA_COSTO_EQ_P_REAL:
			item.setCostoEqPropiosReal(sValue);
			break;
		case COLUMNA_COSTO_EQ_3_REAL:
			item.setCostoEqTercReal(sValue);
			break;
		case COLUMNA_COSTO_MO_REAL:
			item.setCostoMOReal(sValue);
			break;
		case COLUMNA_MARGEN_REAL:
			item.setMargenReal(sValue);
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

	public List<MargenesItem> getRows() {
		return rows;
	}

	public void setRows(List<MargenesItem> rows) {
		this.rows = rows;
	}
	
	public int print (Graphics g, PageFormat f, int pageIndex) 
	   {
	      if (pageIndex == 0) 
	      {
	         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
	        // g.drawString("Hola mundo", 100,100);
	         for (int i=0; i<rows.size();i++) {
	        	 MargenesItem item = rows.get(i);
	        	 //g.drawString(item.getNroPpto()+"  ||  "+item.getEstado()+"   ||  "+item.getVendedor()+"   ||  "+item.getNombreEvento()+"   ||  "+item.getCliente()+"   ||  "+item.getFechaInicio(), 50,(i+1)*20+100);
	 		}
	         return PAGE_EXISTS;
	      }
	      else
	         return NO_SUCH_PAGE;
	   }

}
