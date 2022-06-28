package crm.gui.tablerenderer.buscaCliente;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.salas.cellsEditors.BuscaClientesCellEditor;

public class BuscaClientesTableRender extends TableRender{
	
	private String ocurrencia;
	
	public BuscaClientesTableRender() {
		this(new ArrayList());
	}
	
	public BuscaClientesTableRender(List rows){
		super(new BuscaClientesTableModel(rows));
				
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){
		int i = 0;
		
		setUpFechaColumn(table.getColumnModel().getColumn(BuscaClientesTableModel.COLUMNA_CODIGO));
		setUpUsuarioColumn(table.getColumnModel().getColumn(BuscaClientesTableModel.COLUMNA_FANTASIA));		
		setUpAccionColumn(table.getColumnModel().getColumn(BuscaClientesTableModel.COLUMNA_RAZON));
	}
	
	public void setUpFechaColumn(TableColumn column) {
		column.setPreferredWidth(75);
		column.setMaxWidth(75);
		column.setWidth(75);

	}
	
	public void setUpUsuarioColumn(TableColumn column) {
		if(ocurrencia != null)
			column.setCellRenderer(new BuscaClientesCellEditor(ocurrencia));

	}
	
	public void setUpAccionColumn(TableColumn column) {
		if(ocurrencia != null)
			column.setCellRenderer(new BuscaClientesCellEditor(ocurrencia));		
	}
	
	public void setUpResultadoColumn(TableColumn column) {
		

	}
	
	public void refreshTable(){
		JTable t = getTable();
		setUpUsuarioColumn(table.getColumnModel().getColumn(BuscaClientesTableModel.COLUMNA_FANTASIA));
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public BuscaClientesItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		BuscaClientesTableModel model = (BuscaClientesTableModel)getTable().getModel();
		
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
		BuscaClientesItem item = model.getRow(selrow);
		
		// asigno los datos
		if (item == null){
			System.out.println("TableRenderSeguimientp::getSelectedItem() - item para row "+selrow+" es null");
		}
		
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
