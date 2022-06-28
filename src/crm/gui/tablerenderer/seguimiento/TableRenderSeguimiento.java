package crm.gui.tablerenderer.seguimiento;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.seguimiento.SeguimientoTableModel;

public class TableRenderSeguimiento extends TableRender{
	
	public TableRenderSeguimiento() {
		this(new ArrayList());
	}
	
	public TableRenderSeguimiento(List rows){
		super(new SeguimientoTableModel(rows));
				
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){
		int i = 0;
		
		setUpFechaColumn(table.getColumnModel().getColumn(SeguimientoTableModel.COLUMNA_FECHA));
		setUpUsuarioColumn(table.getColumnModel().getColumn(SeguimientoTableModel.COLUMNA_USUARIO));		
		setUpAccionColumn(table.getColumnModel().getColumn(SeguimientoTableModel.COLUMNA_ACCION));
		setUpResultadoColumn(table.getColumnModel().getColumn(SeguimientoTableModel.COLUMNA_RESULTADO));
	}
	
	public void setUpFechaColumn(TableColumn column) {
		column.setPreferredWidth(150);
		column.setMaxWidth(150);
		column.setWidth(150);

	}
	
	public void setUpUsuarioColumn(TableColumn column) {
		

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
	
	public SeguimientoItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		SeguimientoTableModel model = (SeguimientoTableModel)getTable().getModel();
		
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
		SeguimientoItem item = model.getRow(selrow);
		
		// asigno los datos
		if (item == null){
			System.out.println("TableRenderSeguimientp::getSelectedItem() - item para row "+selrow+" es null");
		}
		
		return item;
	}
}
