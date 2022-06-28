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

public class BuscadorReportesVendedoresTableModel implements TableModel,Comparable, Printable {

	public static final int COLUMNA_VENDEDOR = 0;
	public static final int COLUMNA_TOTAL = 1;
	public static final int COLUMNA_CANTIDAD = 2;
	public static final int COLUMNA_PROMEDIO = 3;
	
	private static final String[] colnames = new String[]{ 
		"Vendedor","Total","Cantidad","Promedio"};
	
	protected transient Vector listeners;
	private List<BuscadorReportesVendedoresItem> rows;
	
	public BuscadorReportesVendedoresTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public BuscadorReportesVendedoresTableModel(List<BuscadorReportesVendedoresItem> prows){
		this();
		
		for (BuscadorReportesVendedoresItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new BuscadorReportesVendedoresItem());
	}
	
	public void addRow(BuscadorReportesVendedoresItem item){
		rows.add(item);
	}
	
	public void addRow(BuscadorReportesVendedoresItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}

	public BuscadorReportesVendedoresItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (BuscadorReportesVendedoresItem)rows.get(idx);
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
		case COLUMNA_CANTIDAD:
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
		
		BuscadorReportesVendedoresItem item = (BuscadorReportesVendedoresItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_VENDEDOR:
			return item.getVendedor();		
		case COLUMNA_TOTAL:
			return getCurrencyFormat().format(item.getTotal());
		case COLUMNA_CANTIDAD:
			return item.getCantidad();
		case COLUMNA_PROMEDIO:
			return getCurrencyFormat().format(item.getPromedio());
		
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
		
		BuscadorReportesVendedoresItem item = (BuscadorReportesVendedoresItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		

		case COLUMNA_VENDEDOR:
			item.setVendedor(sValue);
			break;
		case COLUMNA_TOTAL:
			item.setTotal((Double)aValue);
			break;
		case COLUMNA_CANTIDAD:
			item.setCantidad((Integer)aValue);
			break;
		case COLUMNA_PROMEDIO:
			item.setPromedio((Double)aValue);
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

	public List<BuscadorReportesVendedoresItem> getRows() {
		return rows;
	}

	public void setRows(List<BuscadorReportesVendedoresItem> rows) {
		this.rows = rows;
	}
	
	public int print (Graphics g, PageFormat f, int pageIndex) 
	   {
	      if (pageIndex == 0) 
	      {
	         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
	        // g.drawString("Hola mundo", 100,100);
	         for (int i=0; i<rows.size();i++) {
	        	 BuscadorReportesVendedoresItem item = rows.get(i);
	        	 g.drawString(item.getVendedor()+"  ||  "+item.getTotal()+"   ||  "+item.getCantidad()+"   ||  "+item.getPromedio(), 50,(i+1)*20+100);
	 		}
	         return PAGE_EXISTS;
	      }
	      else
	         return NO_SUCH_PAGE;
	   }

}
