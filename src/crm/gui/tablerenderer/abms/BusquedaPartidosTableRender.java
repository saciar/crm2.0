package crm.gui.tablerenderer.abms;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;

public class BusquedaPartidosTableRender extends TableRender{
	
	public BusquedaPartidosTableRender() {
		this(new ArrayList());
	}
	
	public BusquedaPartidosTableRender(List rows){
		super(new BusquedaPartidosTableModel(rows));
				
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){		
		setUpCodigoColumn(table.getColumnModel().getColumn(BusquedaPartidosTableModel.COLUMNA_CODIGO));
		setUpFantasiaColumn(table.getColumnModel().getColumn(BusquedaPartidosTableModel.COLUMNA_PAIS));		
		setUpRazonColumn(table.getColumnModel().getColumn(BusquedaPartidosTableModel.COLUMNA_PROVINCIA));
		setUpRazonColumn(table.getColumnModel().getColumn(BusquedaPartidosTableModel.COLUMNA_PARTIDO));
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
	
	public BusquedaPartidosItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		BusquedaPartidosTableModel model = (BusquedaPartidosTableModel)getTable().getModel();
		
		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
		//int selrow = table.convertRowIndexToModel(getTable().getSelectedRow());
		
//		 obtengo el numero de linea seleccionada de la vista
		int selrow = getTable().getSelectedRow();
		
		//obtengo el numero de linea seleccionada del modelo y no de la vista
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			selrow = table.convertRowIndexToModel(getTable().getSelectedRow());
		
		// si no hay nada seleccionado, asumo la primera linea.
		if (selrow < 0)
			selrow = 0;
		
		// obtengo el item de la tabla
		BusquedaPartidosItem item = (BusquedaPartidosItem)model.getRow(selrow);
		
		return item;
	}

}
