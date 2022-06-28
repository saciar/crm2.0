package crm.gui.tablerenderer.salas;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;


public class SalaServiciosTableModel extends Observable implements TableModel,Comparable {
	public static final int COLUMNA_CANTIDAD = 0;
	public static final int COLUMNA_CODSERVICIO = 1;
	public static final int COLUMNA_FAMILIA = 2;
	public static final int COLUMNA_SERVICIO = 3;
	public static final int COLUMNA_DIAS = 4;
	public static final int COLUMNA_SUBCONTRATADO = 5;
	public static final int COLUMNA_OPCIONAL= 6;
	public static final int COLUMNA_DESCUENTO = 7;
	public static final int COLUMNA_LISTA = 8;
	public static final int COLUMNA_TOTAL = 9;
	
	private String nroOrden;
	private String codigoSala; // codigo de la sala
	private String nombreSala;	// nombre de la sala
	private int porcentajeDto;
	
	private static final String[] colnames = new String[]{ "Cant.", 
		"Cód.","Familia de servicios","Servicios", 
		"Días", "Otro","Opc.", "Variacion","Precio de Lista","Total"};
	
	protected transient Vector listeners;
	private List<SalaServicioItem> rows;
	
	public SalaServiciosTableModel(int p){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
		porcentajeDto =p;
	}
	
	public SalaServiciosTableModel(List<SalaServicioItem> prows, int porc){
		this(porc);
		
		for (SalaServicioItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new SalaServicioItem());
	}
	
	public void addRow(SalaServicioItem item){
		rows.add(item);
	}
	
	public void addRow(SalaServicioItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param item : item a remover.
	 */
	public void removeRow(SalaServicioItem item){
		rows.remove(item);
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param idx : numero de fila a remover. 
	 */
	public void removeRow(int idx){
		rows.remove(idx);
	}
	
	public SalaServicioItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (SalaServicioItem)rows.get(idx);
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
		case COLUMNA_DIAS:		
		case COLUMNA_DESCUENTO:
			return Integer.class;
		
			//return Double.class;
		case COLUMNA_SUBCONTRATADO:
		case COLUMNA_OPCIONAL:	
			return Boolean.class;
		case COLUMNA_CODSERVICIO:				
		case COLUMNA_FAMILIA:
		case COLUMNA_SERVICIO:
		case COLUMNA_TOTAL:
		case COLUMNA_LISTA:
		default:
			return String.class;
		}
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex != COLUMNA_DESCUENTO){
			return false;
		}
		return true;
	}
	
	private boolean isColdUser;
	
	public void setUserCold(boolean u){
		isColdUser=u;
	}
	
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return null;
		
		SalaServicioItem item = rows.get(rowIndex);
		 
		
		switch (columnIndex){		
		case COLUMNA_CANTIDAD:
			return item.getCantidad();
		case COLUMNA_DIAS:
			return item.getDias();
		case COLUMNA_DESCUENTO:
			return item.getDescuento();
		case COLUMNA_CODSERVICIO:
			return item.getServicioCodigo();				
		case COLUMNA_SUBCONTRATADO:
			return item.isSubContratado();
		case COLUMNA_OPCIONAL:
			return item.isOpcional();
		case COLUMNA_TOTAL:			
			if(isColdUser){
				return 0;
			}
			return getCurrencyFormat().format(item.getTotal());
			//return item.getTotal();	
		case COLUMNA_LISTA:			
			if(isColdUser){
				return 0;
			}
			return getCurrencyFormat().format(item.getPrecioLista());	
			//return item.getPrecioLista();
		case COLUMNA_FAMILIA:
			return item.getFamilia();
		case COLUMNA_SERVICIO:
			return item.getServicio();
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
		
		SalaServicioItem item = rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();
		
		switch (columnIndex){		
		case COLUMNA_CANTIDAD:
			item.setCantidad((Integer)aValue);
			break;
		case COLUMNA_DIAS:
			item.setDias((Integer)aValue);
			break;
		case COLUMNA_DESCUENTO:
			if(((Integer)aValue).intValue()>=porcentajeDto){
			item.setDescuento((Integer)aValue);
			setChanged();
		    notifyObservers();}
		
			break;
		case COLUMNA_CODSERVICIO:
			item.setServicioCodigo(sValue);
			break;
		case COLUMNA_SUBCONTRATADO:
			item.setSubContratado((Boolean)aValue);
			break;
		case COLUMNA_OPCIONAL:
			item.setOpcional((Boolean)aValue);
			setChanged();
		    notifyObservers();
			break;
		case COLUMNA_TOTAL:
			item.setTotal((Double)aValue);
			break;
		case COLUMNA_LISTA:
			item.setPrecioLista((Double)aValue);
			break;
		case COLUMNA_FAMILIA:
			item.setFamilia(sValue);
			break;
		case COLUMNA_SERVICIO:
			item.setServicio(sValue);
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

	public List<SalaServicioItem> getRows() {
		return rows;
	}

	public void setRows(List<SalaServicioItem> rows) {
		this.rows = rows;
	}
	
	public String toString() {
		return nombreSala;
	}

	/**
	 * @return Returns the codigoSala.
	 */
	public String getCodigoSala() {
		return codigoSala;
	}

	/**
	 * @param codigoSala The codigoSala to set.
	 */
	public void setCodigoSala(String codigoSala) {
		this.codigoSala = codigoSala;
	}

	public String getNombreSala() {
		return nombreSala;
	}

	/**
	 * @param nombreSala The nombreSala to set.
	 */
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	/**
	 * @return Returns the nroOrden.
	 */
	public String getNroOrden() {
		return nroOrden;
	}

	/**
	 * @param nroOrden The nroOrden to set.
	 */
	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
	}


	public int getMaxOrden(List<SalaServicioItem> rows){
		int max=0;
		Iterator it = rows.iterator();
		while(it.hasNext()){
			String nroOrden = ((SalaServicioItem)it.next()).getNroOrden() ;
			if((nroOrden != null) && (Integer.parseInt(nroOrden) > max)){
				max = Integer.parseInt(nroOrden);
			}
		}
		return max;
	}

	public int getPorcentajeDto() {
		return porcentajeDto;
	}

	public void setPorcentajeDto(int porcentajeDto) {
		this.porcentajeDto = porcentajeDto;
	}

}
