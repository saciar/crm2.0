package crm.gui.tablerenderer.cobranzas.agenda;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.cobranzas.agenda.AgendaCobranzasItem;
import crm.gui.tablerenderer.cobranzas.agenda.AgendaCobranzasTableModel;

public class TableRenderAgendaCobranzas extends TableRender{
	
	public TableRenderAgendaCobranzas() {
		this(new ArrayList());
	}
	
	public TableRenderAgendaCobranzas(List rows){
		super(new AgendaCobranzasTableModel(rows));
				
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){		
		setupTipo(table.getColumnModel().getColumn(
				AgendaCobranzasTableModel.COLUMNA_TIPO_ICONO));
		setupEstado(table.getColumnModel().getColumn(
				AgendaCobranzasTableModel.COLUMNA_ESTADO));
		setupTarea(table.getColumnModel().getColumn(
				AgendaCobranzasTableModel.COLUMNA_TAREA));
		setupFactura(table.getColumnModel().getColumn(
				AgendaCobranzasTableModel.COLUMNA_FACTURA));
		setupMonto(table.getColumnModel().getColumn(
				AgendaCobranzasTableModel.COLUMNA_MONTO));
		setupFecha(table.getColumnModel().getColumn(
				AgendaCobranzasTableModel.COLUMNA_FECHA));
	}

	private void setupFecha(TableColumn column) {
		column.setPreferredWidth(150);
		column.setMaxWidth(150);
		column.setWidth(150);	
	}
	
	private void setupTarea(TableColumn column) {
	}
	
	private void setupMonto(TableColumn column) {
		column.setPreferredWidth(100);
		column.setMaxWidth(100);
		column.setWidth(100);
		//column.setCellRenderer(new MontoCellRender());
	}
	
	private void setupFactura(TableColumn column) {
		column.setPreferredWidth(100);
		column.setMaxWidth(100);
		column.setWidth(100);
		//column.setCellRenderer(new MontoCellRender());
	}
	
	private void setupEstado(TableColumn column) {
		column.setPreferredWidth(40);
		column.setMaxWidth(40);
		column.setWidth(40);
		column.setCellRenderer(new TareaCompletadaCellRenderer());
	}

	private void setupTipo(TableColumn column) {
		column.setPreferredWidth(40);
		column.setMaxWidth(40);
		column.setWidth(40);		
		column.setCellRenderer(new TipoCellRenderer());
	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public AgendaCobranzasItem getSelectedItem() {

		// obtengo el modelo de la tabla
		AgendaCobranzasTableModel model = (AgendaCobranzasTableModel) getTable().getModel();

		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
		int selrow = getTable().getSelectedRow();
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			selrow = table.convertRowIndexToModel(getTable().getSelectedRow());

		// si no hay nada seleccionado, asumo la primera linea.
		if (selrow < 0)
			selrow = 0;

		// obtengo el item de la tabla
		AgendaCobranzasItem item = model.getRow(selrow);

		return item;
	}
	
	public AgendaCobranzasItem[] getSelectedItems() {

//		 obtengo el modelo de la tabla
		AgendaCobranzasTableModel model = (AgendaCobranzasTableModel) getTable().getModel();

		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
		int[] selrow = getTable().getSelectedRows();

		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win")){
			for(int i=0;i<selrow.length;i++){			
				selrow[i] = table.convertRowIndexToModel(selrow[i]);
			}
		}

		// obtengo el item de la tabla
		AgendaCobranzasItem[] items = model.getRows(selrow);

		return items;
	}
}
