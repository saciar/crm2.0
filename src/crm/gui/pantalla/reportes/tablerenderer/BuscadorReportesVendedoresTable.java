package crm.gui.pantalla.reportes.tablerenderer;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;

public class BuscadorReportesVendedoresTable extends TableRender{
	
	public BuscadorReportesVendedoresTable() {
		this(new ArrayList());
	}
	
	public BuscadorReportesVendedoresTable(List rows){
		super(new BuscadorReportesVendedoresTableModel(rows));
		
		initialize();
		//JScrollPane scrollPane = new JScrollPane(table);
		//add(scrollPane);
	}
	
	private void initialize(){		
	
		setUpVendedorColumn(table.getColumnModel().getColumn(BuscadorReportesVendedoresTableModel.COLUMNA_VENDEDOR));
		setUpTotalColumn(table.getColumnModel().getColumn(BuscadorReportesVendedoresTableModel.COLUMNA_TOTAL));
		setUpEstadoColumn(table.getColumnModel().getColumn(BuscadorReportesVendedoresTableModel.COLUMNA_CANTIDAD));
		setUpTotalColumn(table.getColumnModel().getColumn(BuscadorReportesVendedoresTableModel.COLUMNA_PROMEDIO));


	}
	
	public void setUpEstadoColumn(TableColumn column) {
		column.setPreferredWidth(90);
		column.setMaxWidth(90);
		column.setWidth(90);

	}
	
	public void setUpVendedorColumn(TableColumn column) {		
		column.setPreferredWidth(100);
		column.setMaxWidth(100);
		column.setWidth(100);

	}
	
	public void setUpTotalColumn(TableColumn column) {
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

	public BuscadorReportesVendedoresItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		BuscadorReportesVendedoresTableModel model = (BuscadorReportesVendedoresTableModel)getTable().getModel();
		
		// obtengo el numero de linea seleccionada de la vista
		int selrow = getTable().getSelectedRow();
		
		//obtengo el numero de linea seleccionada del modelo y no de la vista
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			selrow = table.convertRowIndexToModel(getTable().getSelectedRow());
		
		// si no hay nada seleccionado, asumo la primera linea.
		if (selrow < 0)
			selrow = 0;
		
		// obtengo el item de la tabla
		BuscadorReportesVendedoresItem item = model.getRow(selrow);
		
		// asigno los datos
		if (item == null){
			System.out.println("TableRenderSeguimientp::getSelectedItem() - item para row "+selrow+" es null");
		}
		
		return item;
	}

}
