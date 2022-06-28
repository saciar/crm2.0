package crm.gui.tablerenderer.alertasCobranzas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.cobranzas.agenda.TipoCellRenderer;

public class AlertaCobranzaTableRender extends TableRender{
	
	public AlertaCobranzaTableRender() {
		this(new ArrayList());
	}
	
	public AlertaCobranzaTableRender(List rows){
		super(new AlertaCobranzaTableModel(rows));
				
		initialize();
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){
		setUpTipoColumn(table.getColumnModel().getColumn(AlertaCobranzaTableModel.COLUMNA_ICONO));
		setUpNroPptoColumn(table.getColumnModel().getColumn(AlertaCobranzaTableModel.COLUMNA_NUMERO));
		setUpVendedorColumn(table.getColumnModel().getColumn(AlertaCobranzaTableModel.COLUMNA_ASUNTO));
		setUpVendedorColumn(table.getColumnModel().getColumn(AlertaCobranzaTableModel.COLUMNA_CLIENTE));
		setUpVendedorColumn(table.getColumnModel().getColumn(AlertaCobranzaTableModel.COLUMNA_EVENTO));
		setUpEventoColumn(table.getColumnModel().getColumn(AlertaCobranzaTableModel.COLUMNA_CREADO));

	}
	
	public void setUpTipoColumn(TableColumn column) {
		column.setPreferredWidth(50);
		column.setMaxWidth(50);
		column.setWidth(50);
		column.setCellRenderer(new TipoCellRenderer());
	}
	
	public void setUpNroPptoColumn(TableColumn column) {
		column.setPreferredWidth(50);
		column.setMaxWidth(50);
		column.setWidth(50);
	}
	
	public void setUpEventoColumn(TableColumn column) {
		column.setPreferredWidth(120);
		column.setMaxWidth(120);
		column.setWidth(120);
	}
	
	public void setUpVendedorColumn(TableColumn column) {

	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public AlertaCobranzaItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		AlertaCobranzaTableModel model = (AlertaCobranzaTableModel)getTable().getModel();
		
		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
		int selrow = getTable().getSelectedRow();
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			selrow = table.convertRowIndexToModel(getTable().getSelectedRow());
		
		// si no hay nada seleccionado, asumo la primera linea.
		if (selrow < 0)
			selrow = 0;
		
		// obtengo el item de la tabla
		AlertaCobranzaItem item = model.getRow(selrow);
		
		return item;
	}

}
