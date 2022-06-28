package crm.gui.tablerenderer.abms;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;

public class BusquedaResultadoSeguimientoTableRender extends TableRender{
	
	public BusquedaResultadoSeguimientoTableRender() {
		this(new ArrayList());
	}
	
	public BusquedaResultadoSeguimientoTableRender(List rows){
		super(new BusquedaResultadoSeguimientoTableModel(rows));
				
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){		
		setUpCodigoColumn(table.getColumnModel().getColumn(BusquedaResultadoSeguimientoTableModel.COLUMNA_CODIGO));
		setUpAccionColumn(table.getColumnModel().getColumn(BusquedaResultadoSeguimientoTableModel.COLUMNA_ACCION));
		setUpResultadoColumn(table.getColumnModel().getColumn(BusquedaResultadoSeguimientoTableModel.COLUMNA_RESULTADO));				
	}
	
	public void setUpCodigoColumn(TableColumn column) {
		column.setPreferredWidth(75);
		column.setMaxWidth(75);
		column.setWidth(75);

	}
	
	public void setUpAccionColumn(TableColumn column) {
		

	}
	
	public void setUpResultadoColumn(TableColumn column) {
		

	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public BusquedaResultadoSeguimientoItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		BusquedaResultadoSeguimientoTableModel model = (BusquedaResultadoSeguimientoTableModel)getTable().getModel();
		
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
		BusquedaResultadoSeguimientoItem item = model.getRow(selrow);
		
		return item;
	}

}
