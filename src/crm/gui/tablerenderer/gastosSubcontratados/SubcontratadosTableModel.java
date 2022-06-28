package crm.gui.tablerenderer.gastosSubcontratados;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;

import crm.client.helper.UserRolesUtil;
import crm.gui.pantalla.Pantalla;
import crm.gui.pantalla.PantallaInterface;
import crm.gui.tablerenderer.gastos.GastosSubContratacionesSalasItem;

public class SubcontratadosTableModel extends Observable implements TableModel,Comparable {
	public static final int COLUMNA_CANTIDAD = 0;
	public static final int COLUMNA_SERVICIO = 1;
	public static final int COLUMNA_PROVEEDOR = 2;
	public static final int COLUMNA_SALA = 3;
	public static final int COLUMNA_COSTO = 4;
	public static final int COLUMNA_PRECIO = 5;
	public static final int COLUMNA_NETO = 6;
	private PantallaInterface m_interface;
	
	private static final String[] colnames = new String[]{ 
		"Cant.","Detalle","Proveedor","Sala","Costo","Precio", "Neto"
	};
	
	protected transient Vector listeners;
	private List rows;

	public SubcontratadosTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
		m_interface = new Pantalla();
	}
	
	public SubcontratadosTableModel(List<GastosSubContratacionesSalasItem> prows){
		this();
		
		for (GastosSubContratacionesSalasItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new GastosSubContratacionesSalasItem());
	}
	public void addRow(GastosSubContratacionesSalasItem item){
		if(!rows.contains(item)){
			rows.add(item);
		}
	}
	
	public void removeRow(GastosSubContratacionesSalasItem item){
		rows.remove(item);
	}
	
	public void removeRow(int idx){
		rows.remove(idx);
	}
	
	
	public List getRows(){
		return this.rows;
	}
	
	public void clear(){
		this.rows.clear();
	}

	public GastosSubContratacionesSalasItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (GastosSubContratacionesSalasItem)rows.get(idx);
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
		
		GastosSubContratacionesSalasItem item = (GastosSubContratacionesSalasItem)rows.get(rowIndex);
		boolean isColdUser = isColdUser(); 
		
		switch (columnIndex){		
		case COLUMNA_CANTIDAD:
			return item.getCantidad();
		case COLUMNA_SERVICIO:
			return item.getServicio();
		case COLUMNA_PROVEEDOR:
			return item.getProveedor();
		case COLUMNA_SALA:
			return item.getSala();
		case COLUMNA_COSTO:
			return item.getCostoFormateado();
		case COLUMNA_PRECIO:
			/*if(isColdUser){
				return 0;
			}*/
			return item.getPrecioFormateado();
		case COLUMNA_NETO:
			/*if(isColdUser){
				return 0;
			}*/			
			return item.getNetoFormateado();
		default:
			return null;
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		GastosSubContratacionesSalasItem item = (GastosSubContratacionesSalasItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_CANTIDAD:
		    item.setCantidad(getInt(sValue));
		    break;
		case COLUMNA_SERVICIO:
			item.setServicio(sValue);
			break;
		case COLUMNA_PROVEEDOR:
			item.setProveedor(sValue);
			break;
		case COLUMNA_SALA:
			item.setSala(sValue);
			break;
		case COLUMNA_COSTO:
			item.setCosto((Double)aValue);
			break;
		case COLUMNA_PRECIO:
			item.setPrecio(getDouble(sValue));
			break;
		case COLUMNA_NETO:
			item.setNeto(getDouble(sValue));
			break;
		}
		

	}

	private double getDouble(String val){
		if (StringUtils.isBlank(val))
			return 0;
		try {
			return Double.parseDouble(val);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	private int getInt(String val){
		if (StringUtils.isBlank(val))
			return 0;
		try {
			return Integer.parseInt(val);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
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

	private boolean isColdUser(){
		return UserRolesUtil.isCold(m_interface.getUsuario());
	}
}
