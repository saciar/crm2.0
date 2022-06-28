package crm.gui.pantalla.reportes.tablerenderer;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BuscadorReportesRentabilidadTableModel implements TableModel,Comparable, Printable {
	public static final int COLUMNA_NUMERO= 0;
	public static final int COLUMNA_FACTURADO = 1;
	public static final int COLUMNA_CO = 2;
	public static final int COLUMNA_CO_REAL = 3;
	public static final int COLUMNA_SUBC = 4;
	public static final int COLUMNA_OTROS = 5;
	public static final int COLUMNA_LUGAR = 6;
	public static final int COLUMNA_TERCEROS= 7;
	public static final int COLUMNA_COMERCIAL = 8;
	public static final int COLUMNA_REGALIAS= 9;
	public static final int COLUMNA_RENTABILIDAD= 10;	
	public static final int COLUMNA_RENTABILIDAD_REAL= 11;
	public static final int COLUMNA_MARGEN_RENTABILIDAD= 12;
	public static final int COLUMNA_MARGEN_RENTABILIDAD_REAL= 13;
	private static final String[] colnames = new String[]{ 
		"Nro","Facturado","CO presu.","CO real","Subcontrat.","Otros","Lugar", "Terceros","Comercial", "Regalias", "Renta Presu.","Renta real", "Margen presu.","Margen real"};
	
	protected transient Vector listeners;
	private List<BuscadorReportesRentabilidadItem> rows;
	
	public BuscadorReportesRentabilidadTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public BuscadorReportesRentabilidadTableModel(List<BuscadorReportesRentabilidadItem> prows){
		this();
		
		for (BuscadorReportesRentabilidadItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new BuscadorReportesRentabilidadItem());
	}
	
	public void addRow(BuscadorReportesRentabilidadItem item){
		rows.add(item);
	}
	
	public void addRow(BuscadorReportesRentabilidadItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}

	public BuscadorReportesRentabilidadItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (BuscadorReportesRentabilidadItem)rows.get(idx);
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
		case COLUMNA_MARGEN_RENTABILIDAD:
			return Double.class;
		case COLUMNA_MARGEN_RENTABILIDAD_REAL:
			return Double.class;
		case COLUMNA_LUGAR:
			return Double.class;
		case COLUMNA_TERCEROS:
			return Double.class;
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
		
		BuscadorReportesRentabilidadItem item = (BuscadorReportesRentabilidadItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_NUMERO:
			return item.getNumeroPpto();		
		case COLUMNA_FACTURADO:
			return getCurrencyFormat().format(item.getTotalFacturado());
		case COLUMNA_CO:
			return getCurrencyFormat().format(item.getCostoOperativo());
		case COLUMNA_CO_REAL:
			return getCurrencyFormat().format(item.getCostoOperativoReal());
		case COLUMNA_SUBC:
			return getCurrencyFormat().format(item.getGastosSubcontratados());
		case COLUMNA_OTROS:
			return getCurrencyFormat().format(item.getOtros());
		case COLUMNA_LUGAR:
			return item.getComisionLugar();
		case COLUMNA_TERCEROS:
			return item.getComisionTerceros();
		case COLUMNA_COMERCIAL:
			return getCurrencyFormat().format(item.getComisionComercial());
		case COLUMNA_REGALIAS:
			return getCurrencyFormat().format(item.getRegalias());
		case COLUMNA_RENTABILIDAD:
			return getCurrencyFormat().format(item.getRentabilidad());
		case COLUMNA_MARGEN_RENTABILIDAD:
			return item.getMargen()*100;
		case COLUMNA_RENTABILIDAD_REAL:
			return getCurrencyFormat().format(item.getRentabilidadReal());
		case COLUMNA_MARGEN_RENTABILIDAD_REAL:
			return item.getMargenReal()*100;
		default:
			return null;
		}
	}
	
	private static NumberFormat currencyFormat;
	private NumberFormat getCurrencyFormat() {
		if (currencyFormat == null){
			Locale l = new Locale("es","AR");
			currencyFormat = NumberFormat.getCurrencyInstance(l);			
		}
		
		return currencyFormat;
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		BuscadorReportesRentabilidadItem item = (BuscadorReportesRentabilidadItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_NUMERO:
			item.setNumeroPpto((Long)aValue);
			break;
		case COLUMNA_FACTURADO:
			item.setTotalFacturado((Double)aValue);
			break;
		case COLUMNA_CO:
			item.setCostoOperativo((Double)aValue);
			break;
		case COLUMNA_CO_REAL:
			item.setCostoOperativoReal((Double)aValue);
			break;
		case COLUMNA_SUBC:
			item.setGastosSubcontratados((Double)aValue);
			break;
		case COLUMNA_OTROS:
			item.setOtros((Double)aValue);
			break;
		case COLUMNA_LUGAR:
			item.setComisionLugar((Double)aValue);
			break;
		case COLUMNA_TERCEROS:
			item.setComisionTerceros((Double)aValue);
			break;
		case COLUMNA_COMERCIAL:
			item.setComisionComercial((Double)aValue);
			break;
		case COLUMNA_REGALIAS:
			item.setRegalias((Double)aValue);
			break;
		case COLUMNA_RENTABILIDAD:
			item.setRentabilidad((Double)aValue);
			break;
		case COLUMNA_MARGEN_RENTABILIDAD:
			item.setMargen((Double)aValue);
			break;
		case COLUMNA_RENTABILIDAD_REAL:
			item.setRentabilidadReal((Double)aValue);
			break;
		case COLUMNA_MARGEN_RENTABILIDAD_REAL:
			item.setMargenReal((Double)aValue);
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

	public List<BuscadorReportesRentabilidadItem> getRows() {
		return rows;
	}

	public void setRows(List<BuscadorReportesRentabilidadItem> rows) {
		this.rows = rows;
	}
	
	public int print (Graphics g, PageFormat f, int pageIndex) 
	   {
	      if (pageIndex == 0) 
	      {
	         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
	        // g.drawString("Hola mundo", 100,100);
	         for (int i=0; i<rows.size();i++) {
	        	 BuscadorReportesRentabilidadItem item = rows.get(i);
	        	// g.drawString(item.getNumeroPpto()+"  ||  "+item.getEstado()+"   ||  "+item.getVendedor()+"   ||  "+item.getNombreEvento()+"   ||  "+item.getCliente()+"   ||  "+item.getFechaInicio(), 50,(i+1)*20+100);
	 		}
	         return PAGE_EXISTS;
	      }
	      else
	         return NO_SUCH_PAGE;
	   }

}
