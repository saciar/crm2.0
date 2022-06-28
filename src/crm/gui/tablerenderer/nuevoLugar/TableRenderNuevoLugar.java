package crm.gui.tablerenderer.nuevoLugar;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.nuevoLugar.NuevoLugarItem;
import crm.gui.tablerenderer.nuevoLugar.NuevoLugarTableModel;

public class TableRenderNuevoLugar extends TableRender{
	
	public TableRenderNuevoLugar() {
		this(new ArrayList());
	}
	
	public TableRenderNuevoLugar(List rows){
		super(new NuevoLugarTableModel(rows));
				
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){
		int i = 0;
		
		setUpNroSalaColumn(table.getColumnModel().getColumn(NuevoLugarTableModel.COLUMNA_NUMERO_SALA));
		setUpNombreSalaColumn(table.getColumnModel().getColumn(NuevoLugarTableModel.COLUMNA_NOMBRE_SALA));
	}
	
	public void setUpNroSalaColumn(TableColumn column) {
		column.setPreferredWidth(75);
		column.setMaxWidth(75);
		column.setWidth(75);

	}
	
	public void setUpNombreSalaColumn(TableColumn column) {
		

	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public NuevoLugarItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		NuevoLugarTableModel model = (NuevoLugarTableModel)getTable().getModel();
		
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
		NuevoLugarItem item = model.getRow(selrow);
		
		// asigno los datos
		if (item == null){
			System.out.println("TableRenderSeguimientp::getSelectedItem() - item para row "+selrow+" es null");
		}
		
		return item;
	}

}
