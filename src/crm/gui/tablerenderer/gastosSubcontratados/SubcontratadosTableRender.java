package crm.gui.tablerenderer.gastosSubcontratados;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.gastos.GastosSubContratacionesSalasItem;
import crm.gui.tablerenderer.salas.SalaServicioItem;
import crm.gui.tablerenderer.salas.cellsEditors.EnterosCellRenderer;
import crm.gui.tablerenderer.salas.cellsEditors.OpcionalCellRenderer;
import crm.gui.tablerenderer.salas.cellsEditors.PorcentajeCellRenderer;
import crm.gui.tablerenderer.salas.cellsEditors.ServicioCellRenderer;
import crm.gui.tablerenderer.salas.cellsEditors.SubcontratadoCellRenderer;
import crm.gui.tablerenderer.salas.cellsEditors.TotalesCellRenderer;

public class SubcontratadosTableRender extends TableRender {
	
	public SubcontratadosTableRender() {
		this(new ArrayList<GastosSubContratacionesSalasItem>());
	}
	
	public SubcontratadosTableRender(List<GastosSubContratacionesSalasItem> rows) {
		
		super(new SubcontratadosTableModel(rows));		

		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		add(scrollPane);	
		
	}
	
	private void initialize(){
		int i = 0;
		
		setUpCantidadColumn(table.getColumnModel().getColumn(i++));

		setUpServicioColumn(table.getColumnModel().getColumn(i++));
		
		setUpProveedorColumn(table.getColumnModel().getColumn(i++));
		
		setUpSalaColumn(table.getColumnModel().getColumn(i++));
		
		setUpDescuentoColumn(table.getColumnModel().getColumn(i++));

		setUpPrecioListaColumn(table.getColumnModel().getColumn(i++));
		
		setUpTotalColumn(table.getColumnModel().getColumn(i++));

	}
	
	public void setUpCantidadColumn(TableColumn cantidadColumn) {

		cantidadColumn.setPreferredWidth(40);
		cantidadColumn.setMaxWidth(40);
		cantidadColumn.setWidth(40);
	}
	
	public void setUpServicioColumn(TableColumn servColumn) {

	}
	
	public void setUpProveedorColumn(TableColumn diasColumn) {
		diasColumn.setCellRenderer(new ProveedorSubcontratadoCellRenderer());
		diasColumn.setPreferredWidth(100);
		diasColumn.setMaxWidth(100);
		diasColumn.setWidth(100);
	}
	
	public void setUpDescuentoColumn(TableColumn diasColumn) {
		diasColumn.setCellRenderer(new CostoSubcontratadoCellRender());
		diasColumn.setPreferredWidth(80);
		diasColumn.setMaxWidth(80);
		diasColumn.setWidth(80);
	}
	
	public void setUpSalaColumn(TableColumn subcontratColumn) {

		subcontratColumn.setPreferredWidth(100);
		subcontratColumn.setMaxWidth(100);
		subcontratColumn.setWidth(100);
	}

	public void setUpTotalColumn(TableColumn totalColumn) {

		totalColumn.setPreferredWidth(80);
		totalColumn.setMaxWidth(80);
		totalColumn.setWidth(80);
	}
	
	public void setUpPrecioListaColumn(TableColumn totalColumn) {

		totalColumn.setPreferredWidth(80);
		totalColumn.setMaxWidth(80);
		totalColumn.setWidth(80);
	}

	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public GastosSubContratacionesSalasItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		SubcontratadosTableModel model = (SubcontratadosTableModel)getTable().getModel();
		
		// obtengo el numero de linea seleccionada
		int selrow = getTable().getSelectedRow();
		
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			selrow = table.convertRowIndexToModel(getTable().getSelectedRow());
		
		// si no hay nada seleccionado, asumo la primera linea.
		if (selrow < 0)
			selrow = 0;
		
		// obtengo el item de la tabla
		GastosSubContratacionesSalasItem item = model.getRow(selrow);
		
		return item;
	}

}
