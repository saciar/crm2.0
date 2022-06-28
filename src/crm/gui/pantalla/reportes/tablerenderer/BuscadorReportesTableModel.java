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

public class BuscadorReportesTableModel implements TableModel,Comparable, Printable {
	public static final int COLUMNA_NUMERO= 0;
	public static final int COLUMNA_ESTADO = 1;
	public static final int COLUMNA_VENDEDOR = 2;
	public static final int COLUMNA_CLIENTE = 3;
	public static final int COLUMNA_LUGAR = 4;
	public static final int COLUMNA_NOMBRE_EVENTO = 5;
	public static final int COLUMNA_FECHA_INICIO = 6;
	public static final int COLUMNA_TIPO_EVENTO = 7;
	public static final int COLUMNA_TOTAL= 8;
	public static final int COLUMNA_UNIDAD_FACT= 9;
	
	private static final String[] colnames = new String[]{ 
		"Nro","Estado","Vendedor","Cliente","Lugar","Nombre del evento","Fecha evento","Tipo Evento", "Total", "Uni. Fact"};
	
	protected transient Vector listeners;
	private List<BuscadorReportesItem> rows;
	
	public BuscadorReportesTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public BuscadorReportesTableModel(List<BuscadorReportesItem> prows){
		this();
		
		for (BuscadorReportesItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new BuscadorReportesItem());
	}
	
	public void addRow(BuscadorReportesItem item){
		rows.add(item);
	}
	
	public void addRow(BuscadorReportesItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}

	public BuscadorReportesItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (BuscadorReportesItem)rows.get(idx);
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
		
		BuscadorReportesItem item = (BuscadorReportesItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_NUMERO:
			return item.getNumeroPpto();		
		case COLUMNA_ESTADO:
			return item.getEstado();
		case COLUMNA_VENDEDOR:
			return item.getVendedor();
		case COLUMNA_CLIENTE:
			return item.getCliente();
		case COLUMNA_LUGAR:
			return item.getLugar();
		case COLUMNA_NOMBRE_EVENTO:
			return item.getNombreEvento();
		case COLUMNA_FECHA_INICIO:
			return item.getFechaInicio();
		case COLUMNA_TIPO_EVENTO:
			return item.getTipoEvento();
		case COLUMNA_TOTAL:
			return getCurrencyFormat().format(item.getTotal());
		case COLUMNA_UNIDAD_FACT:
			return item.getCodLugar();
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
		
		BuscadorReportesItem item = (BuscadorReportesItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_NUMERO:
			item.setNumeroPpto((Long)aValue);
			break;
		case COLUMNA_ESTADO:
			item.setEstado(sValue);
			break;
		case COLUMNA_VENDEDOR:
			item.setVendedor(sValue);
			break;
		case COLUMNA_CLIENTE:
			item.setCliente(sValue);
			break;
		case COLUMNA_LUGAR:
			item.setLugar(sValue);
			break;
		case COLUMNA_NOMBRE_EVENTO:
			item.setNombreEvento(sValue);
			break;
		case COLUMNA_FECHA_INICIO:
			item.setFechaInicio(sValue);
			break;
		case COLUMNA_TIPO_EVENTO:
			item.setTipoEvento(sValue);
			break;
		case COLUMNA_TOTAL:
			item.setTotal((Double)aValue);
			break;
		case COLUMNA_UNIDAD_FACT:
			item.setCodLugar(sValue);
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

	public List<BuscadorReportesItem> getRows() {
		return rows;
	}

	public void setRows(List<BuscadorReportesItem> rows) {
		this.rows = rows;
	}
	
	public int print (Graphics g, PageFormat f, int pageIndex) 
	   {
	      if (pageIndex == 0) 
	      {
	         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
	        // g.drawString("Hola mundo", 100,100);
	         for (int i=0; i<rows.size();i++) {
	        	 BuscadorReportesItem item = rows.get(i);
	        	 g.drawString(item.getNumeroPpto()+"  ||  "+item.getEstado()+"   ||  "+item.getVendedor()+"   ||  "+item.getNombreEvento()+"   ||  "+item.getCliente()+"   ||  "+item.getFechaInicio(), 50,(i+1)*20+100);
	 		}
	         return PAGE_EXISTS;
	      }
	      else
	         return NO_SUCH_PAGE;
	   }

}
