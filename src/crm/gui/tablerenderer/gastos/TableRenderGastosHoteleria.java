package crm.gui.tablerenderer.gastos;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;

public class TableRenderGastosHoteleria extends TableRender {
	
	public TableRenderGastosHoteleria() {
		this(new ArrayList());
	}
	
	public TableRenderGastosHoteleria(List rows) {
		super(new GastosHoteleriaTableModel(rows));

		// Set up column sizes.
		initColumnSizes();
		
		initialize();
		
		//setupDetalle(table.getColumnModel().getColumn(GastosHoteleriaTableModel.COLUMNA_DETALLE));
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}

	
	

	public void cmbProveedoresActionPerformed(ActionEvent evt) {

	}
	
	private void setupDetalle(TableColumn column){
		column.setPreferredWidth(800);
		column.setMaxWidth(800);
		column.setWidth(800);
	}
	
	public GastosHoteleriaItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		GastosHoteleriaTableModel model = (GastosHoteleriaTableModel)getTable().getModel();
		
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
		GastosHoteleriaItem item = model.getRow(selrow);	
		
		return item;
	}
	
	
	public void removeSelection(){
		((GastosHoteleriaTableModel)getTable().getModel()).removeRow(getSelectedItem());
		getTable().updateUI();
	}

	private void initialize(){
		int i=0;
		
		setUpDetalle(table.getColumnModel().getColumn(i++));
		
		setUpCosto(table.getColumnModel().getColumn(i++));
	}
	
	private void setUpDetalle(TableColumn detalle){
		
	}
	
	private void setUpCosto (TableColumn costo){
		costo.setPreferredWidth(150);
		costo.setMaxWidth(150);
		costo.setWidth(150);
		
	}
}
