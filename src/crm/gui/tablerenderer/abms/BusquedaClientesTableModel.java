package crm.gui.tablerenderer.abms;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import crm.gui.tablerenderer.abms.BusquedaClientesItem;

public class BusquedaClientesTableModel extends BusquedaTableModel {
	public static final int COLUMNA_CODIGO= 0;
	public static final int COLUMNA_FANTASIA = 1;
	public static final int COLUMNA_RAZON_SOCIAL = 2;	
	
	/*private static final String[] colnames = new String[]{ 
		"Código","Nombre de fantasía","Razón Social"};*/
	
	//protected transient Vector listeners;
	//private List<BusquedaClientesItem> rows;
	
	public BusquedaClientesTableModel(){
		//rows = new ArrayList();
		//listeners = new Vector<TableModelListener>();
		colnames=new String[]{ 
				"Código","Nombre de fantasía","Razón Social"};
	}
	
	public BusquedaClientesTableModel(List<BusquedaClientesItem> prows){
		this();
		
		for (BusquedaClientesItem item : prows) {
			rows.add(item);
		}
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return null;
		
		BusquedaClientesItem item = (BusquedaClientesItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_CODIGO:
			return item.getCodigo();		
		case COLUMNA_FANTASIA:
			return item.getNombreFantasia();
		case COLUMNA_RAZON_SOCIAL:
			return item.getRazonSocial();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		BusquedaClientesItem item = (BusquedaClientesItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_CODIGO:
			item.setCodigo(sValue);
			break;
		case COLUMNA_FANTASIA:
			item.setNombreFantasia(sValue);
			break;
		case COLUMNA_RAZON_SOCIAL:
			item.setRazonSocial(sValue);
			break;
		}
	}

}
