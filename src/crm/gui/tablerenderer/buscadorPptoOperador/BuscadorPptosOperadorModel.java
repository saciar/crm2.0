package crm.gui.tablerenderer.buscadorPptoOperador;

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

public class BuscadorPptosOperadorModel implements TableModel,Comparable, Printable {
	public static final int COLUMNA_NUMERO= 0;
	public static final int COLUMNA_LUGAR = 1;
	public static final int COLUMNA_NOMBRE_EVENTO = 2;
	
	private static final String[] colnames = new String[]{ 
		"Nro de OS","Lugar","Nombre del evento"};
	
	protected transient Vector listeners;
	private List<BuscadorPptosOperadorItem> rows;
	
	public BuscadorPptosOperadorModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public BuscadorPptosOperadorModel(List<BuscadorPptosOperadorItem> prows){
		this();
		
		for (BuscadorPptosOperadorItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new BuscadorPptosOperadorItem());
	}
	
	public void addRow(BuscadorPptosOperadorItem item){
		rows.add(item);
	}
	
	public void addRow(BuscadorPptosOperadorItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}

	public BuscadorPptosOperadorItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (BuscadorPptosOperadorItem)rows.get(idx);
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
		
		BuscadorPptosOperadorItem item = (BuscadorPptosOperadorItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_NUMERO:
			return item.getNumeroPpto();		
		case COLUMNA_LUGAR:
			return item.getLugarEvento();
		case COLUMNA_NOMBRE_EVENTO:
			return item.getNombreEvento();
		default:
			return null;
		}
	}

	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		BuscadorPptosOperadorItem item = (BuscadorPptosOperadorItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_NUMERO:
			item.setNumeroPpto((Long)aValue);
		case COLUMNA_LUGAR:
			item.setLugarEvento(sValue);
			break;
		case COLUMNA_NOMBRE_EVENTO:
			item.setNombreEvento(sValue);
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

	public List<BuscadorPptosOperadorItem> getRows() {
		return rows;
	}

	public void setRows(List<BuscadorPptosOperadorItem> rows) {
		this.rows = rows;
	}
	
	public int print (Graphics g, PageFormat f, int pageIndex) 
	   {
	      if (pageIndex == 0) 
	      {
	         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
	        // g.drawString("Hola mundo", 100,100);
	         for (int i=0; i<rows.size();i++) {
	        	 BuscadorPptosOperadorItem item = rows.get(i);
	        	 g.drawString(item.getNumeroPpto()+"  ||  "+item.getLugarEvento()+"   ||  "+item.getNombreEvento(), 50,(i+1)*20+100);
	 		}
	         return PAGE_EXISTS;
	      }
	      else
	         return NO_SUCH_PAGE;
	   }

}
