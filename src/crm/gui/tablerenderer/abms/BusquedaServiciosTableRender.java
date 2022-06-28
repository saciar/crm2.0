package crm.gui.tablerenderer.abms;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.salas.cellsEditors.BuscaClientesCellEditor;

public class BusquedaServiciosTableRender extends TableRender{
	
	private String ocurrencia;
	
	public BusquedaServiciosTableRender() {
		this(new ArrayList());
	}
	
	public BusquedaServiciosTableRender(List rows){
		super(new BusquedaServiciosTableModel(rows));
				
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){		
		setUpCodigoColumn(table.getColumnModel().getColumn(BusquedaServiciosTableModel.COLUMNA_CODIGO));
		setUpTituloColumn(table.getColumnModel().getColumn(BusquedaServiciosTableModel.COLUMNA_TITULO));		
		setUpDescripcionColumn(table.getColumnModel().getColumn(BusquedaServiciosTableModel.COLUMNA_DESCRIPCION));
		setUpPrecioColumn(table.getColumnModel().getColumn(BusquedaServiciosTableModel.COLUMNA_PRECIO));
	}
	
	public void setUpCodigoColumn(TableColumn column) {
		column.setPreferredWidth(75);
		column.setMaxWidth(75);
		column.setWidth(75);

	}
	
	public void setUpTituloColumn(TableColumn column) {
		if(ocurrencia != null)
			column.setCellRenderer(new BuscaClientesCellEditor(ocurrencia));
	}
	
	public void setUpDescripcionColumn(TableColumn column) {
		if(ocurrencia != null)
			column.setCellRenderer(new BuscaClientesCellEditor(ocurrencia));		
	}
	
	public void setUpPrecioColumn(TableColumn column) {
		column.setPreferredWidth(100);
		column.setMaxWidth(100);
		column.setWidth(100);

	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public BusquedaServiciosItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		BusquedaServiciosTableModel model = (BusquedaServiciosTableModel)getTable().getModel();
		
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
		BusquedaServiciosItem item = model.getRow(selrow);
		
		return item;
	}
	
	public BusquedaServiciosItem[] getSelectedItems(){
		
		// obtengo el modelo de la tabla
		BusquedaServiciosTableModel model = (BusquedaServiciosTableModel)getTable().getModel();
		
		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
//		 obtengo el numero de linea seleccionada de la vista
		int[] selrow = getTable().getSelectedRows();

		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win")){
			for(int i=0;i<selrow.length;i++){			
				selrow[i] = table.convertRowIndexToModel(selrow[i]);
			}
		}
		
		// obtengo el item de la tabla
		BusquedaServiciosItem[] item = model.getRows(selrow);
		
		return item;
	}
	
	/**
	 * @return Returns the ocurrencia.
	 */
	public String getOcurrencia() {
		return ocurrencia;
	}

	/**
	 * @param ocurrencia The ocurrencia to set.
	 */
	public void setOcurrencia(String ocurrencia) {
		this.ocurrencia = ocurrencia;
	}
	
}
