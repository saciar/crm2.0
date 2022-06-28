package crm.gui.tablerenderer.buscaLugares;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.salas.cellsEditors.BuscaClientesCellEditor;

public class BuscaLugaresTableRender extends TableRender{
	
	private String ocurrencia;
	
	public BuscaLugaresTableRender() {
		this(new ArrayList());
	}
	
	public BuscaLugaresTableRender(List rows){
		super(new BuscaLugaresTableModel(rows));
				
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){
		int i = 0;
		
		setUpCodigoColumn(table.getColumnModel().getColumn(BuscaLugaresTableModel.COLUMNA_CODIGO));
		setUpNombreColumn(table.getColumnModel().getColumn(BuscaLugaresTableModel.COLUMNA_LUGAR));
	}
	
	public void setUpCodigoColumn(TableColumn column) {
		column.setPreferredWidth(75);
		column.setMaxWidth(75);
		column.setWidth(75);

	}
	
	public void setUpNombreColumn(TableColumn column) {
		if(ocurrencia != null)
			column.setCellRenderer(new BuscaClientesCellEditor(ocurrencia));

	}
	
	public void refreshTable(){
		JTable t = getTable();
		setUpNombreColumn(table.getColumnModel().getColumn(BuscaLugaresTableModel.COLUMNA_LUGAR));
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public BuscaLugaresItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		BuscaLugaresTableModel model = (BuscaLugaresTableModel)getTable().getModel();
		
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
		BuscaLugaresItem item = model.getRow(selrow);
		
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
