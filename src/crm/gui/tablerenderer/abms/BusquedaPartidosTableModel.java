package crm.gui.tablerenderer.abms;

import java.util.List;

public class BusquedaPartidosTableModel extends BusquedaTableModel {
	public static final int COLUMNA_CODIGO= 0;
	public static final int COLUMNA_PAIS = 1;
	public static final int COLUMNA_PROVINCIA = 2;
	public static final int COLUMNA_PARTIDO = 3;
	
	/*private static final String[] colnames = new String[]{ 
		"Código","Nombre de fantasía","Razón Social"};*/
	
	//protected transient Vector listeners;
	//private List<BusquedaClientesItem> rows;
	
	public BusquedaPartidosTableModel(){
		//rows = new ArrayList();
		//listeners = new Vector<TableModelListener>();
		colnames=new String[]{ 
				"Código","País","Provincia","Partido"};
	}
	
	public BusquedaPartidosTableModel(List<BusquedaPartidosItem> prows){
		this();
		
		for (BusquedaPartidosItem item : prows) {
			rows.add(item);
		}
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return null;
		
		BusquedaPartidosItem item = (BusquedaPartidosItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_CODIGO:
			return item.getIdPartido();		
		case COLUMNA_PAIS:
			return item.getNombrePais();
		case COLUMNA_PROVINCIA:
			return item.getNombreProvincia();
		case COLUMNA_PARTIDO:
			return item.getNombrePartido();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		BusquedaPartidosItem item = (BusquedaPartidosItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_CODIGO:
			item.setIdPartido(sValue);
			break;
		case COLUMNA_PAIS:
			item.setNombrePais(sValue);
			break;
		case COLUMNA_PROVINCIA:
			item.setNombreProvincia(sValue);
			break;
		case COLUMNA_PARTIDO:
			item.setNombrePartido(sValue);
			break;
		}
	}
}
