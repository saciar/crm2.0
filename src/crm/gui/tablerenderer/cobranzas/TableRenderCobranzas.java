package crm.gui.tablerenderer.cobranzas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.salas.cellsEditors.ResaltadorCellEditor;

public class TableRenderCobranzas extends TableRender{
	
	private String ocurrencia;
	
	public TableRenderCobranzas() {
		this(new ArrayList());
	}
	
	public TableRenderCobranzas(List rows){
		super(new CobranzasTableModel(rows));
				
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){		
		setupNumero(table.getColumnModel().getColumn(
				CobranzasTableModel.COLUMNA_NUMERO));
		setupRazonSocialFac(table.getColumnModel().getColumn(
				CobranzasTableModel.COLUMNA_RAZON_SOCIAL_FAC));
		setupRazonSocial(table.getColumnModel().getColumn(
				CobranzasTableModel.COLUMNA_RAZON_SOCIAL));
		setupFactura(table.getColumnModel().getColumn(
				CobranzasTableModel.COLUMNA_NRO_FACTURA));
		setupFacturaAdelanto(table.getColumnModel().getColumn(
				CobranzasTableModel.COLUMNA_NRO_FACTURA_ADELANTO));
		setupFacturaAdicionales(table.getColumnModel().getColumn(
				CobranzasTableModel.COLUMNA_NRO_FACTURA_ADICIONAL));
		setupFechaFacturado(table.getColumnModel().getColumn(
				CobranzasTableModel.COLUMNA_FECHA_FACTURADO));
		setupImporteTotal(table.getColumnModel().getColumn(
				CobranzasTableModel.COLUMNA_IMPORTE_TOTAL));
		setupTipo(table.getColumnModel().getColumn(
				CobranzasTableModel.COLUMNA_TIPO_ICONO));
	}
	
	private void setupTipo(TableColumn column) {
		column.setPreferredWidth(60);
		column.setMaxWidth(60);
		column.setWidth(60);
		column.setCellRenderer(new TieneAgendaCellRender());
	}
	
	private void setupFechaFacturado(TableColumn column) {
		column.setPreferredWidth(150);
		column.setMaxWidth(150);
		column.setWidth(150);	
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

	private void setupFactura(TableColumn column) {
		column.setPreferredWidth(75);
		column.setMaxWidth(75);
		column.setWidth(75);	
		if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));
	}
	
	private void setupFacturaAdelanto(TableColumn column) {
		column.setPreferredWidth(75);
		column.setMaxWidth(75);
		column.setWidth(75);	
		if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));
	}
	
	private void setupFacturaAdicionales(TableColumn column) {
		column.setPreferredWidth(75);
		column.setMaxWidth(75);
		column.setWidth(75);	
		if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));
	}
	
	private void setupRazonSocial(TableColumn column) {
		if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));
	}
	
	private void setupRazonSocialFac(TableColumn column) {
		if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));
	}

	private void setupNumero(TableColumn column) {
		column.setPreferredWidth(40);
		column.setMaxWidth(40);
		column.setWidth(40);
		if(ocurrencia != null)
			column.setCellRenderer(new ResaltadorCellEditor(ocurrencia));
	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public CobranzasItem getSelectedItem() {

		// obtengo el modelo de la tabla
		CobranzasTableModel model = (CobranzasTableModel) getTable().getModel();

		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
		int selrow = getTable().getSelectedRow();
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			selrow = table.convertRowIndexToModel(getTable().getSelectedRow());

		// si no hay nada seleccionado, asumo la primera linea.
		if (selrow < 0)
			selrow = 0;

		// obtengo el item de la tabla
		CobranzasItem item = model.getRow(selrow);

		return item;
	}
	
	public CobranzasItem[] getSelectedItems() {

//		 obtengo el modelo de la tabla
		CobranzasTableModel model = (CobranzasTableModel) getTable().getModel();

		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
		int[] selrow = getTable().getSelectedRows();

		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win")){
			for(int i=0;i<selrow.length;i++){			
				selrow[i] = table.convertRowIndexToModel(selrow[i]);
			}
		}

		// obtengo el item de la tabla
		CobranzasItem[] items = model.getRows(selrow);

		return items;
	}
	
	public ArrayList<CobranzasItem> getArraySelectedItems() {

//		 obtengo el modelo de la tabla
		CobranzasTableModel model = (CobranzasTableModel) getTable().getModel();

		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
		int[] selrow = getTable().getSelectedRows();

		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win")){
			for(int i=0;i<selrow.length;i++){			
				selrow[i] = table.convertRowIndexToModel(selrow[i]);
			}
		}

		// obtengo el item de la tabla
		ArrayList<CobranzasItem> items = model.getArrayRows(selrow);

		return items;
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
