package crm.gui.tablerenderer.gastos;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;

public class TableRenderGastosSubContratacionesGeneral extends TableRender {
	
	public TableRenderGastosSubContratacionesGeneral() {
		this(new ArrayList());
	}
	
	public TableRenderGastosSubContratacionesGeneral(List rows) {
		super(new GastosSubContratacionesGeneralTableModel(rows));

		// Set up column sizes.
		initColumnSizes();
		
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}

	
	

	public void cmbProveedoresActionPerformed(ActionEvent evt) {

	}
	
	public GastosSubContratacionesGeneralItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		GastosSubContratacionesGeneralTableModel model = (GastosSubContratacionesGeneralTableModel)getTable().getModel();
		
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
		GastosSubContratacionesGeneralItem item = model.getRow(selrow);	
		
		return item;
	}
	
	
	public void removeSelection(){
		((GastosSubContratacionesGeneralTableModel)getTable().getModel()).removeRow(getSelectedItem());
		getTable().updateUI();
	}

	private void initialize(){
		int i=0;
		
		setUpDetalle(table.getColumnModel().getColumn(i++));
		
		setUpProveedor(table.getColumnModel().getColumn(i++));
		
		setUpCosto(table.getColumnModel().getColumn(i++));
		
		setUpPrecio(table.getColumnModel().getColumn(i++));
		
		setUpNeto(table.getColumnModel().getColumn(i++));
	}
	
	private void setUpDetalle(TableColumn asistente){
		
	}
	
	private void setUpProveedor(TableColumn cargo){
		
	}
	
	private void setUpPrecio(TableColumn precio){
		precio.setPreferredWidth(100);
		precio.setMaxWidth(100);
		precio.setWidth(100);
	}
	
	private void setUpCosto (TableColumn costo){
		costo.setPreferredWidth(100);
		costo.setMaxWidth(100);
		costo.setWidth(100);
	}
	
	private void setUpNeto (TableColumn neto){
		neto.setPreferredWidth(100);
		neto.setMaxWidth(100);
		neto.setWidth(100);
	}
}
