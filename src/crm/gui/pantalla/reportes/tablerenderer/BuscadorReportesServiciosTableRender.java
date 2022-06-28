package crm.gui.pantalla.reportes.tablerenderer;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;

public class BuscadorReportesServiciosTableRender extends TableRender{
	
	public BuscadorReportesServiciosTableRender() {
		this(new ArrayList());
	}
	
	public BuscadorReportesServiciosTableRender(List rows){
		super(new BuscadorReportesServiciosTableModel(rows));
		
		initialize();
		//JScrollPane scrollPane = new JScrollPane(table);
		//add(scrollPane);
	}
	
	private void initialize(){		
		setUpNroPptoColumn(table.getColumnModel().getColumn(BuscadorReportesServiciosTableModel.COLUMNA_NUMERO));
		setUpServicioColumn(table.getColumnModel().getColumn(BuscadorReportesServiciosTableModel.COLUMNA_SERVICIO));		
		setUpCantidadColumn(table.getColumnModel().getColumn(BuscadorReportesServiciosTableModel.COLUMNA_CANTIDAD));
		setUpDiasColumn(table.getColumnModel().getColumn(BuscadorReportesServiciosTableModel.COLUMNA_DIAS));
		setUpClienteColumn(table.getColumnModel().getColumn(BuscadorReportesServiciosTableModel.COLUMNA_DTO));
		setUpEventoColumn(table.getColumnModel().getColumn(BuscadorReportesServiciosTableModel.COLUMNA_NOMBRE_EVENTO));
		setUpFechaColumn(table.getColumnModel().getColumn(BuscadorReportesServiciosTableModel.COLUMNA_FECHA_INICIO));
		setUpTotalServicioColumn(table.getColumnModel().getColumn(BuscadorReportesServiciosTableModel.COLUMNA_TOTAL_SERV));
		setUpTotalColumn(table.getColumnModel().getColumn(BuscadorReportesServiciosTableModel.COLUMNA_TOTAL));
	}
	
	public void setUpNroPptoColumn(TableColumn column) {
		column.setPreferredWidth(40);
		column.setMaxWidth(40);
		column.setWidth(40);

	}
	
	public void setUpServicioColumn(TableColumn column) {
		column.setPreferredWidth(260);
		column.setMaxWidth(260);
		column.setWidth(260);

	}
	
	public void setUpCantidadColumn(TableColumn column) {		
		column.setPreferredWidth(50);
		column.setMaxWidth(50);
		column.setWidth(50);

	}
	
	public void setUpDiasColumn(TableColumn column) {		
		column.setPreferredWidth(40);
		column.setMaxWidth(40);
		column.setWidth(40);

	}
	
	public void setUpClienteColumn(TableColumn column) {
		column.setPreferredWidth(50);
		column.setMaxWidth(50);
		column.setWidth(50);

	}
	
	public void setUpEventoColumn(TableColumn column) {
		column.setPreferredWidth(150);
		column.setMaxWidth(150);
		column.setWidth(150);

	}
	
	public void setUpFechaColumn(TableColumn column) {
		column.setPreferredWidth(80);
		column.setMaxWidth(80);
		column.setWidth(80);

	}
	
	public void setUpTotalColumn(TableColumn column) {
		column.setPreferredWidth(75);
		column.setMaxWidth(75);
		column.setWidth(75);

	}
	
	public void setUpTotalServicioColumn(TableColumn column) {
		column.setPreferredWidth(60);
		column.setMaxWidth(60);
		column.setWidth(60);
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
	
	public BuscadorReportesServiciosItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		BuscadorReportesServiciosTableModel model = (BuscadorReportesServiciosTableModel)getTable().getModel();
		
		// obtengo el numero de linea seleccionada de la vista
		int selrow = getTable().getSelectedRow();
		
		//obtengo el numero de linea seleccionada del modelo y no de la vista
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			selrow = table.convertRowIndexToModel(getTable().getSelectedRow());
		
		// si no hay nada seleccionado, asumo la primera linea.
		if (selrow < 0)
			selrow = 0;
		
		// obtengo el item de la tabla
		BuscadorReportesServiciosItem item = model.getRow(selrow);
		
		// asigno los datos
		if (item == null){
			System.out.println("TableRenderSeguimientp::getSelectedItem() - item para row "+selrow+" es null");
		}
		
		return item;
	}

}
