package crm.gui.tablerenderer.buscadorPptos;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.buscadorPptos.BuscadorPptosItem;
import crm.gui.tablerenderer.buscadorPptos.BuscadorPptosTableModel;

public class TableRenderBuscadorPptos extends TableRender{
	
	public TableRenderBuscadorPptos() {
		this(new ArrayList());
	}
	
	public TableRenderBuscadorPptos(List rows){
		super(new BuscadorPptosTableModel(rows));
		
		initialize();
		//JScrollPane scrollPane = new JScrollPane(table);
		//add(scrollPane);
	}
	
	private void initialize(){		
		setUpNroPptoColumn(table.getColumnModel().getColumn(BuscadorPptosTableModel.COLUMNA_NUMERO));
		setUpEstadoColumn(table.getColumnModel().getColumn(BuscadorPptosTableModel.COLUMNA_ESTADO));		
		setUpVendedorColumn(table.getColumnModel().getColumn(BuscadorPptosTableModel.COLUMNA_VENDEDOR));
		setUpClienteColumn(table.getColumnModel().getColumn(BuscadorPptosTableModel.COLUMNA_CLIENTE));
		setUpEventoColumn(table.getColumnModel().getColumn(BuscadorPptosTableModel.COLUMNA_NOMBRE_EVENTO));
		setUpFechaColumn(table.getColumnModel().getColumn(BuscadorPptosTableModel.COLUMNA_FECHA_INICIO));
	}
	
	public void setUpNroPptoColumn(TableColumn column) {
		column.setPreferredWidth(100);
		column.setMaxWidth(100);
		column.setWidth(100);

	}
	
	public void setUpEstadoColumn(TableColumn column) {
		column.setPreferredWidth(150);
		column.setMaxWidth(150);
		column.setWidth(150);

	}
	
	public void setUpVendedorColumn(TableColumn column) {		
		column.setPreferredWidth(150);
		column.setMaxWidth(150);
		column.setWidth(150);

	}
	
	public void setUpClienteColumn(TableColumn column) {
		column.setPreferredWidth(250);
		column.setMaxWidth(250);
		column.setWidth(250);

	}
	
	public void setUpEventoColumn(TableColumn column) {
		column.setPreferredWidth(250);
		column.setMaxWidth(250);
		column.setWidth(250);

	}
	
	public void setUpFechaColumn(TableColumn column) {
		column.setPreferredWidth(200);
		column.setMaxWidth(200);
		column.setWidth(200);

	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();

	}
	
	/*public static final int TYPE_STRING= 0;
	public static final int TYPE_LONG= 1;
	public static final int TYPE_INTEGER= 2;
	public static final int TYPE_DOUBLE= 2;
	public void exportXLS(File file){
		int tipo=0;	
		
		WritableWorkbook workbook;
		try {
			workbook = Workbook.createWorkbook(file);
			
			WritableSheet sheet = workbook.createSheet("test", 0);		
			for(int j=0;j<table.getModel().getColumnCount();j++){
				Label label = new Label(j, 0,table.getModel().getColumnName(j));
				sheet.addCell(label);
				if(table.getModel().getColumnClass(j).equals(String.class))
					tipo=TYPE_STRING;
				else if(table.getModel().getColumnClass(j).equals(Long.class))
					tipo=TYPE_LONG;
				else if(table.getModel().getColumnClass(j).equals(Integer.class))
					tipo=TYPE_INTEGER;
				else if(table.getModel().getColumnClass(j).equals(Double.class))
					tipo=TYPE_DOUBLE;
				for (int i=1; i<table.getModel().getRowCount(); i++) {
					if(tipo==TYPE_STRING){
						Label labelData = new Label(j, i,(String)table.getModel().getValueAt(i,j));
						sheet.addCell(labelData);
					}
					else if(tipo==TYPE_LONG){
						Number temp = new Number(j, i,(Long)table.getModel().getValueAt(i,j));
						sheet.addCell(temp);
					}
					else if(tipo==TYPE_INTEGER){
						Number temp = new Number(j, i,(Integer)table.getModel().getValueAt(i,j));
						sheet.addCell(temp);
					}
					else if(tipo==TYPE_DOUBLE){
						Number temp = new Number(j, i,(Double)table.getModel().getValueAt(i,j));
						sheet.addCell(temp);
					}
				}
			}
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}*/
	
	public BuscadorPptosItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		BuscadorPptosTableModel model = (BuscadorPptosTableModel)getTable().getModel();
		BuscadorPptosItem item = null;
		// obtengo el numero de linea seleccionada de la vista
		int selrow = getTable().getSelectedRow();
		
		if(selrow != -1){
			//obtengo el numero de linea seleccionada del modelo y no de la vista
			if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
				selrow = table.convertRowIndexToModel(getTable().getSelectedRow());
			
			// si no hay nada seleccionado, asumo la primera linea.
			if (selrow < 0)
				selrow = 0;
			
			// obtengo el item de la tabla
			item = model.getRow(selrow);
		}

		return item;
	}

}
