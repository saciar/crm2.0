package crm.gui.tablerenderer.abms;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.abms.BusquedaClientesItem;
import crm.gui.tablerenderer.abms.BusquedaClientesTableModel;

public class BusquedaClientesTableRender extends TableRender{
	
	public BusquedaClientesTableRender() {
		this(new ArrayList());
	}
	
	public BusquedaClientesTableRender(List rows){
		super(new BusquedaClientesTableModel(rows));
				
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){		
		setUpCodigoColumn(table.getColumnModel().getColumn(BusquedaClientesTableModel.COLUMNA_CODIGO));
		setUpFantasiaColumn(table.getColumnModel().getColumn(BusquedaClientesTableModel.COLUMNA_FANTASIA));		
		setUpRazonColumn(table.getColumnModel().getColumn(BusquedaClientesTableModel.COLUMNA_RAZON_SOCIAL));		
	}
	
	public void setUpCodigoColumn(TableColumn column) {
		column.setPreferredWidth(75);
		column.setMaxWidth(75);
		column.setWidth(75);

	}
	
	public void setUpFantasiaColumn(TableColumn column) {
		

	}
	
	public void setUpRazonColumn(TableColumn column) {
		

	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public BusquedaClientesItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		BusquedaClientesTableModel model = (BusquedaClientesTableModel)getTable().getModel();
		
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
		BusquedaClientesItem item = (BusquedaClientesItem)model.getRow(selrow);
		
		return item;
	}
}

