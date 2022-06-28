package crm.gui.tablerenderer.activador;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.activador.ActivadorTableModel;

public class TableRenderActivador extends TableRender{
	
	public TableRenderActivador() {
		this(new ArrayList());
	}
	
	public TableRenderActivador(List rows){
		super(new ActivadorTableModel(rows));
				
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){		
		setUpNroPptoColumn(table.getColumnModel().getColumn(ActivadorTableModel.COLUMNA_NUMERO));
		setUpEstadoColumn(table.getColumnModel().getColumn(ActivadorTableModel.COLUMNA_ESTADO));		
		setUpVendedorColumn(table.getColumnModel().getColumn(ActivadorTableModel.COLUMNA_VENDEDOR));
		setUpClienteColumn(table.getColumnModel().getColumn(ActivadorTableModel.COLUMNA_CLIENTE));
		setUpEventoColumn(table.getColumnModel().getColumn(ActivadorTableModel.COLUMNA_NOMBRE_EVENTO));
		setUpActivoColumn(table.getColumnModel().getColumn(ActivadorTableModel.COLUMNA_ACTIVO));
	}
	
	public void setUpNroPptoColumn(TableColumn column) {
		column.setPreferredWidth(75);
		column.setMaxWidth(75);
		column.setWidth(75);

	}
	
	public void setUpEstadoColumn(TableColumn column) {
		

	}
	
	public void setUpVendedorColumn(TableColumn column) {
		

	}
	
	public void setUpClienteColumn(TableColumn column) {
		

	}
	
	public void setUpEventoColumn(TableColumn column) {
		

	}
	
	public void setUpActivoColumn(TableColumn column) {
		

	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public ActivadorItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		ActivadorTableModel model = (ActivadorTableModel)getTable().getModel();
		
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
		ActivadorItem item = model.getRow(selrow);
		
		return item;
	}
}
