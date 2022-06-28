package crm.gui.tablerenderer.localidades;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;

public class BuscadorLocalidadesTableRender extends TableRender{
	
	public BuscadorLocalidadesTableRender() {
		this(new ArrayList());
	}
	
	public BuscadorLocalidadesTableRender(List rows){
		super(new BuscadorLocalidadesTableModel(rows));
				
		initialize();
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){		
		setUpNroPptoColumn(table.getColumnModel().getColumn(BuscadorLocalidadesTableModel.COLUMNA_PAIS));
		setUpEstadoColumn(table.getColumnModel().getColumn(BuscadorLocalidadesTableModel.COLUMNA_PROVINCIA));		
		setUpVendedorColumn(table.getColumnModel().getColumn(BuscadorLocalidadesTableModel.COLUMNA_PARTIDO));
		setUpClienteColumn(table.getColumnModel().getColumn(BuscadorLocalidadesTableModel.COLUMNA_LOCALIDAD));
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
	
	public BuscadorLocalidadesItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		BuscadorLocalidadesTableModel model = (BuscadorLocalidadesTableModel)getTable().getModel();
		
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
		BuscadorLocalidadesItem item = model.getRow(selrow);
		
		return item;
	}
}
