package crm.gui.tablerenderer.buscadorPptoOperador;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;

public class TableRenderPptoOperador extends TableRender{
	
	public TableRenderPptoOperador() {
		this(new ArrayList());
	}
	
	public TableRenderPptoOperador(List rows){
		super(new BuscadorPptosOperadorModel(rows));
		
		initialize();
		//JScrollPane scrollPane = new JScrollPane(table);
		//add(scrollPane);
	}
	
	private void initialize(){		
		setUpNroPptoColumn(table.getColumnModel().getColumn(BuscadorPptosOperadorModel.COLUMNA_NUMERO));
		setUpLugarColumn(table.getColumnModel().getColumn(BuscadorPptosOperadorModel.COLUMNA_LUGAR));
		//setUpEventoColumn(table.getColumnModel().getColumn(BuscadorPptosOperadorModel.COLUMNA_NOMBRE_EVENTO));		
	}
	
	public void setUpNroPptoColumn(TableColumn column) {
		column.setPreferredWidth(100);
		column.setMaxWidth(100);
		column.setWidth(100);

	}

	public void setUpEventoColumn(TableColumn column) {
		column.setPreferredWidth(250);
		column.setMaxWidth(250);
		column.setWidth(250);

	}
	
	public void setUpLugarColumn(TableColumn column) {
		column.setPreferredWidth(200);
		column.setMaxWidth(200);
		column.setWidth(200);

	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		initialize();

	}

	public BuscadorPptosOperadorItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		BuscadorPptosOperadorModel model = (BuscadorPptosOperadorModel)getTable().getModel();
		BuscadorPptosOperadorItem item = null;
		// obtengo el numero de linea seleccionada de la vista
		int selrow = getTable().getSelectedRow();
		
		if(selrow != -1){
			//obtengo el numero de linea seleccionada del modelo y no de la vista
			if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
				selrow = table.convertRowIndexToModel(getTable().getSelectedRow());
			
			// si no hay nada seleccionado, asumo la primera linea.
			if (selrow < 0)
				selrow = 0;
			
			// obtengo el item de la tabla
			item = model.getRow(selrow);
		}

		return item;
	}

}
