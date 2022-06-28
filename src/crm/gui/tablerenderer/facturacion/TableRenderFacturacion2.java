package crm.gui.tablerenderer.facturacion;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.components.CustomTextField;
import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.salas.cellsEditors.ResaltadorCellEditor;

public class TableRenderFacturacion2 extends TableRender {
	
	private String ocurrencia;
	
	public TableRenderFacturacion2() {
		this(new ArrayList());
	}

	public TableRenderFacturacion2(List rows) {
		super(new FacturacionTableModel2(rows));
		
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);

	}
		// ------------------------------------------------------------------------------------
		private void initialize(){	
		setupNumero(table.getColumnModel().getColumn(
				FacturacionTableModel2.COLUMNA_NUMERO));
		setupEstado(table.getColumnModel().getColumn(
				FacturacionTableModel2.COLUMNA_ESTADO));
		setupVendedor(table.getColumnModel().getColumn(
				FacturacionTableModel2.COLUMNA_VENDEDOR));
		setupRazonSocial(table.getColumnModel().getColumn(
				FacturacionTableModel2.COLUMNA_RAZON_SOCIAL));
		setupFechaConfirmacion(table.getColumnModel().getColumn(
				FacturacionTableModel2.COLUMNA_FECHA_CONFIRMACION));
		setupImporteTotal(table.getColumnModel().getColumn(
				FacturacionTableModel2.COLUMNA_IMPORTE_TOTAL));
		setupFacturado(table.getColumnModel().getColumn(
				FacturacionTableModel2.COLUMNA_NRO_FACTURA));	
	}

	private void setupFechaConfirmacion(TableColumn column) {
		column.setPreferredWidth(175);
		column.setMaxWidth(175);
		column.setWidth(175);	
		if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));
	}
	
	private void setupEstado(TableColumn column) {
		column.setPreferredWidth(100);
		column.setMaxWidth(100);
		column.setWidth(100);
		if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));
	}

	private void setupVendedor(TableColumn column) {
		column.setPreferredWidth(130);
		column.setMaxWidth(130);
		column.setWidth(130);
		if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));
	}
	
	private void setupImporteTotal(TableColumn column) {
		column.setPreferredWidth(80);
		column.setMaxWidth(80);
		column.setWidth(80);
		if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));
	}

	private void setupRazonSocial(TableColumn column) {
		if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));
	}

	private void setupNumero(TableColumn column) {
		column.setPreferredWidth(60);
		column.setMaxWidth(60);
		column.setWidth(60);
		if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));
	}

	private void setupFacturado(TableColumn column) {
		column.setPreferredWidth(125);
		column.setMaxWidth(125);
		column.setWidth(125);
		
		if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));
	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}

	public FacturacionItem getSelectedItem() {

		// obtengo el modelo de la tabla
		FacturacionTableModel2 model = (FacturacionTableModel2) getTable().getModel();

		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
		int selrow = getTable().getSelectedRow();
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			selrow = table.convertRowIndexToModel(getTable().getSelectedRow());

		// si no hay nada seleccionado, asumo la primera linea.
		if (selrow < 0)
			selrow = 0;

		// obtengo el item de la tabla
		FacturacionItem item = model.getRow(selrow);

		return item;
	}
	
	public FacturacionItem[] getSelectedItems() {

		// obtengo el modelo de la tabla
		FacturacionTableModel2 model = (FacturacionTableModel2) getTable().getModel();

		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
		int[] selrow = getTable().getSelectedRows();
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win")){
			for(int i=0;i<selrow.length;i++){			
				selrow[i] = table.convertRowIndexToModel(selrow[i]);
			}
		}

		// obtengo el item de la tabla
		FacturacionItem[] item = model.getRows(selrow);

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
