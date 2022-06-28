package crm.gui.tablerenderer.gastos;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;

public class TableRenderGastosAsistentes extends TableRender {
	
	public TableRenderGastosAsistentes() {
		this(new ArrayList());
	}
	
	public TableRenderGastosAsistentes(List rows) {
		super(new GastosAsistentesTableModel(rows));

		// Set up column sizes.
		initColumnSizes();
		
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}

	
	

	public void cmbProveedoresActionPerformed(ActionEvent evt) {

	}
	
	public GastosAsistentesItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		GastosAsistentesTableModel model = (GastosAsistentesTableModel)getTable().getModel();
		
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
		GastosAsistentesItem item = model.getRow(selrow);	
		
		return item;
	}
	
	
	public void removeSelection(){
		((GastosAsistentesTableModel)getTable().getModel()).removeRow(getSelectedItem());
		getTable().updateUI();
	}

	private void initialize(){
		int i=0;
		
		setUpAsistentes(table.getColumnModel().getColumn(i++));
		
		setUpCargo(table.getColumnModel().getColumn(i++));
		
		setUpJornada(table.getColumnModel().getColumn(i++));
		
		setUpCosto(table.getColumnModel().getColumn(i++));
	}
	
	private void setUpAsistentes(TableColumn asistente){
		
	}
	
	private void setUpCargo(TableColumn cargo){
		
	}
	
	private void setUpJornada(TableColumn jornada){
		jornada.setPreferredWidth(200);
		jornada.setMaxWidth(200);
		jornada.setWidth(200);
	}
	
	private void setUpCosto (TableColumn costo){
		costo.setPreferredWidth(150);
		costo.setMaxWidth(150);
		costo.setWidth(150);
		
	}
	
}
