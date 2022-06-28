package crm.gui.tablerenderer.historialCliente;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.historialCliente.*;

public class TableRenderHistorialCliente extends TableRender{
	
	public TableRenderHistorialCliente() {
		this(new ArrayList());
	}
	
	public TableRenderHistorialCliente(List rows){
		super(new HistorialClienteTableModel(rows));
				
		initialize();
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){		
		setUpNroPptoColumn(table.getColumnModel().getColumn(HistorialClienteTableModel.COLUMNA_NUMERO));
		setUpVendedorColumn(table.getColumnModel().getColumn(HistorialClienteTableModel.COLUMNA_CREADOR));
		setUpEventoColumn(table.getColumnModel().getColumn(HistorialClienteTableModel.COLUMNA_EVENTO));
		setUpFechaInicioEventoColumn(table.getColumnModel().getColumn(HistorialClienteTableModel.COLUMNA_FECHA_EVENTO));		
		setUpFacturasColumn(table.getColumnModel().getColumn(HistorialClienteTableModel.COLUMNA_NUMERO_FACTURA));
		setUpCTotalColumn(table.getColumnModel().getColumn(HistorialClienteTableModel.COLUMNA_TOTAL));
	}
	
	public void setUpNroPptoColumn(TableColumn column) {
		column.setPreferredWidth(100);
		column.setMaxWidth(100);
		column.setWidth(100);
	}
	
	public void setUpFechaInicioEventoColumn(TableColumn column) {
		column.setPreferredWidth(100);
		column.setMaxWidth(100);
		column.setWidth(100);

	}
	
	public void setUpFacturasColumn(TableColumn column) {		
		/*column.setPreferredWidth(150);
		column.setMaxWidth(150);
		column.setWidth(150);*/

	}
	
	public void setUpCTotalColumn(TableColumn column) {
		column.setPreferredWidth(100);
		column.setMaxWidth(100);
		column.setWidth(100);
	}
	
	public void setUpEventoColumn(TableColumn column) {
		/*column.setPreferredWidth(250);
		column.setMaxWidth(250);
		column.setWidth(250);*/

	}
	
	public void setUpVendedorColumn(TableColumn column) {
		/*column.setPreferredWidth(250);
		column.setMaxWidth(250);
		column.setWidth(250);*/

	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public HistorialClienteItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		HistorialClienteTableModel model = (HistorialClienteTableModel)getTable().getModel();
		
		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
		int selrow = getTable().getSelectedRow();
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			selrow = table.convertRowIndexToModel(getTable().getSelectedRow());
		
		// si no hay nada seleccionado, asumo la primera linea.
		if (selrow < 0)
			selrow = 0;
		
		// obtengo el item de la tabla
		HistorialClienteItem item = model.getRow(selrow);
		
		return item;
	}

}
