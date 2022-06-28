package crm.gui.tablerenderer.buscadorPptoFacturados;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;

import crm.gui.tablerenderer.cobranzas.TieneAgendaCellRender;
import crm.gui.tablerenderer.salas.cellsEditors.ResaltadorCellEditor;

public class TableRenderPresupuestosFacturados extends TableRender{
	
	//private String ocurrencia;
	
	public TableRenderPresupuestosFacturados() {
		this(new ArrayList());
	}
	
	public TableRenderPresupuestosFacturados(List rows){
		super(new PresupuestosFacturadosTableModel(rows));
				
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){		
		setupNumero(table.getColumnModel().getColumn(
				PresupuestosFacturadosTableModel.COLUMNA_NUMERO));
		setupRazonSocialFac(table.getColumnModel().getColumn(
				PresupuestosFacturadosTableModel.COLUMNA_RAZON_SOCIAL));
		setupRazonSocial(table.getColumnModel().getColumn(
				PresupuestosFacturadosTableModel.COLUMNA_EVENTO));
		setupFactura(table.getColumnModel().getColumn(
				PresupuestosFacturadosTableModel.COLUMNA_NRO_FACTURA));
		setupFacturaAdelanto(table.getColumnModel().getColumn(
				PresupuestosFacturadosTableModel.COLUMNA_NRO_FACTURA_ADELANTO));
		setupFacturadoPor(table.getColumnModel().getColumn(
			PresupuestosFacturadosTableModel.COLUMNA_FACTURADO_PRO));

	}

	private void setupFactura(TableColumn column) {
		column.setPreferredWidth(80);
		column.setMaxWidth(80);
		column.setWidth(80);	
		/*if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));*/
	}
	
	private void setupFacturaAdelanto(TableColumn column) {
		column.setPreferredWidth(80);
		column.setMaxWidth(80);
		column.setWidth(80);	
		/*if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));*/
	}
	
	private void setupFacturadoPor(TableColumn column) {
		column.setPreferredWidth(100);
		column.setMaxWidth(100);
		column.setWidth(100);	
		/*if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));*/
	}

	private void setupRazonSocial(TableColumn column) {
		column.setPreferredWidth(250);
		column.setMaxWidth(250);
		column.setWidth(250);
	}
	
	private void setupRazonSocialFac(TableColumn column) {
		column.setPreferredWidth(250);
		column.setMaxWidth(250);
		column.setWidth(250);
	}

	private void setupNumero(TableColumn column) {
		column.setPreferredWidth(40);
		column.setMaxWidth(40);
		column.setWidth(40);
		/*if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));*/
	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public PrespuestosFacturadosItem getSelectedItem() {

		// obtengo el modelo de la tabla
		PresupuestosFacturadosTableModel model = (PresupuestosFacturadosTableModel) getTable().getModel();

		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
		int selrow = getTable().getSelectedRow();
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			selrow = table.convertRowIndexToModel(getTable().getSelectedRow());

		// si no hay nada seleccionado, asumo la primera linea.
		if (selrow < 0)
			selrow = 0;

		// obtengo el item de la tabla
		PrespuestosFacturadosItem item = model.getRow(selrow);

		return item;
	}
	
	public PrespuestosFacturadosItem[] getSelectedItems() {

//		 obtengo el modelo de la tabla
		PresupuestosFacturadosTableModel model = (PresupuestosFacturadosTableModel) getTable().getModel();

		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
		int[] selrow = getTable().getSelectedRows();

		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win")){
			for(int i=0;i<selrow.length;i++){			
				selrow[i] = table.convertRowIndexToModel(selrow[i]);
			}
		}

		// obtengo el item de la tabla
		PrespuestosFacturadosItem[] items = model.getRows(selrow);

		return items;
	}
	
	public ArrayList<PrespuestosFacturadosItem> getArraySelectedItems() {

//		 obtengo el modelo de la tabla
		PresupuestosFacturadosTableModel model = (PresupuestosFacturadosTableModel) getTable().getModel();

		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
		int[] selrow = getTable().getSelectedRows();

		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win")){
			for(int i=0;i<selrow.length;i++){			
				selrow[i] = table.convertRowIndexToModel(selrow[i]);
			}
		}

		// obtengo el item de la tabla
		ArrayList<PrespuestosFacturadosItem> items = model.getArrayRows(selrow);

		return items;
	}
	
	/**
	 * @return Returns the ocurrencia.
	 *
	public String getOcurrencia() {
		return ocurrencia;
	}

	/**
	 * @param ocurrencia The ocurrencia to set.
	 *
	public void setOcurrencia(String ocurrencia) {
		this.ocurrencia = ocurrencia;
	}*/

}
