package crm.gui.tablerenderer.abms;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;

public class DescuentoServiciosDiasTableRender extends TableRender{
	
	public DescuentoServiciosDiasTableRender() {
		this(new ArrayList());
	}
	
	public DescuentoServiciosDiasTableRender(List rows){
		super(new DescuentoServiciosDiasTableModel(rows));
				
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){		
		setUpCodigoColumn(table.getColumnModel().getColumn(DescuentoServiciosDiasTableModel.COLUMNA_CODIGO));
		setUpTituloColumn(table.getColumnModel().getColumn(DescuentoServiciosDiasTableModel.COLUMNA_TITULO));		
		setUpDiasColumn(table.getColumnModel().getColumn(DescuentoServiciosDiasTableModel.COLUMNA_DIAS));
		setUpPorcentajeColumn(table.getColumnModel().getColumn(DescuentoServiciosDiasTableModel.COLUMNA_PORCENTAJE));
	}
	
	public void setUpCodigoColumn(TableColumn column) {
		column.setPreferredWidth(75);
		column.setMaxWidth(75);
		column.setWidth(75);

	}
	
	public void setUpTituloColumn(TableColumn column) {
		

	}
	
	public void setUpDiasColumn(TableColumn column) {
		column.setPreferredWidth(75);
		column.setMaxWidth(75);
		column.setWidth(75);

	}
	
	public void setUpPorcentajeColumn(TableColumn column) {
		column.setPreferredWidth(75);
		column.setMaxWidth(75);
		column.setWidth(75);		
	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}
	
	public DescuentoServiciosDiasItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		DescuentoServiciosDiasTableModel model = (DescuentoServiciosDiasTableModel)getTable().getModel();
		
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
		DescuentoServiciosDiasItem item = model.getRow(selrow);
		
		return item;
	}
	

}
