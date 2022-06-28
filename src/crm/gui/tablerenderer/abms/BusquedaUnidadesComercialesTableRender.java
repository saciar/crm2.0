package crm.gui.tablerenderer.abms;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;

public class BusquedaUnidadesComercialesTableRender extends TableRender{
	
	public BusquedaUnidadesComercialesTableRender() {
		this(new ArrayList());
	}
	
	public BusquedaUnidadesComercialesTableRender(List rows){
		super(new BusquedaUnidadesComercialesTableModel(rows));
				
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){		
		setUpCodigoColumn(table.getColumnModel().getColumn(BusquedaUnidadesComercialesTableModel.COLUMNA_CODIGO));
		setUpSucursalColumn(table.getColumnModel().getColumn(BusquedaUnidadesComercialesTableModel.COLUMNA_UNIDAD));
				
	}
	
	public void setUpCodigoColumn(TableColumn column) {
		column.setPreferredWidth(75);
		column.setMaxWidth(75);
		column.setWidth(75);

	}
	
	public void setUpSucursalColumn(TableColumn column) {
		

	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public BusquedaUnidadesComercialesItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		BusquedaUnidadesComercialesTableModel model = (BusquedaUnidadesComercialesTableModel)getTable().getModel();
		
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
		BusquedaUnidadesComercialesItem item = model.getRow(selrow);
		
		return item;
	}

}
