package crm.gui.tablerenderer.abms;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;

public class BusquedaCPTableRender extends TableRender{
	
	public BusquedaCPTableRender() {
		this(new ArrayList());
	}
	
	public BusquedaCPTableRender(List rows){
		super(new BusquedaCPTableModel(rows));
				
		initialize();
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){		
		setUpCPColumn(table.getColumnModel().getColumn(BusquedaCPTableModel.COLUMNA_CP));
		setUpNroPptoColumn(table.getColumnModel().getColumn(BusquedaCPTableModel.COLUMNA_PAIS));
		setUpEstadoColumn(table.getColumnModel().getColumn(BusquedaCPTableModel.COLUMNA_PROVINCIA));		
		setUpVendedorColumn(table.getColumnModel().getColumn(BusquedaCPTableModel.COLUMNA_PARTIDO));
		setUpClienteColumn(table.getColumnModel().getColumn(BusquedaCPTableModel.COLUMNA_LOCALIDAD));
	}
	
	public void setUpCPColumn(TableColumn column) {
		column.setPreferredWidth(200);
		column.setMaxWidth(200);
		column.setWidth(200);

	}
	
	public void setUpNroPptoColumn(TableColumn column) {
		/*column.setPreferredWidth(100);
		column.setMaxWidth(100);
		column.setWidth(100);*/

	}
	
	public void setUpEstadoColumn(TableColumn column) {
		/*column.setPreferredWidth(150);
		column.setMaxWidth(150);
		column.setWidth(150);*/

	}
	
	public void setUpVendedorColumn(TableColumn column) {		
		/*column.setPreferredWidth(150);
		column.setMaxWidth(150);
		column.setWidth(150);*/

	}
	
	public void setUpClienteColumn(TableColumn column) {
		/*column.setPreferredWidth(250);
		column.setMaxWidth(250);
		column.setWidth(250);*/

	}
	
	public void setUpEventoColumn(TableColumn column) {
		/*column.setPreferredWidth(250);
		column.setMaxWidth(250);
		column.setWidth(250);*/

	}
	
	public void setUpFechaColumn(TableColumn column) {
		/*column.setPreferredWidth(200);
		column.setMaxWidth(200);
		column.setWidth(200);*/

	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public BusquedaCPItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		BusquedaCPTableModel model = (BusquedaCPTableModel)getTable().getModel();
		
		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
//		 obtengo el numero de linea seleccionada de la vista
		int selrow = getTable().getSelectedRow();
		
		//obtengo el numero de linea seleccionada del modelo y no de la vista
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			selrow = table.convertRowIndexToModel(getTable().getSelectedRow());
		
		// si no hay nada seleccionado, asumo la primera linea.
		if (selrow < 0)
			selrow = 0;
		
		// obtengo el item de la tabla
		BusquedaCPItem item = model.getRow(selrow);
		
		return item;
	}

}
