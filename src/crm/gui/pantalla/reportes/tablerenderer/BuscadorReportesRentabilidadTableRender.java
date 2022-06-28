package crm.gui.pantalla.reportes.tablerenderer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import jxl.Workbook;
import jxl.write.Boolean;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import crm.gui.Main;
import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.salas.cellsEditors.MargenesRealTableCellRenderer;
import crm.gui.tablerenderer.salas.cellsEditors.MargenesTableCellRederer;

public class BuscadorReportesRentabilidadTableRender extends TableRender{
	
	public BuscadorReportesRentabilidadTableRender() {
		this(new ArrayList());
	}
	
	public BuscadorReportesRentabilidadTableRender(List rows){
		super(new BuscadorReportesRentabilidadTableModel(rows));
		
		initialize();
		//JScrollPane scrollPane = new JScrollPane(table);
		//add(scrollPane);
	}
	
	private void initialize(){		
		setUpEstadoColumn(table.getColumnModel().getColumn(BuscadorReportesRentabilidadTableModel.COLUMNA_NUMERO));
		setUpEstadoColumn(table.getColumnModel().getColumn(BuscadorReportesRentabilidadTableModel.COLUMNA_FACTURADO));		
		setUpEstadoColumn(table.getColumnModel().getColumn(BuscadorReportesRentabilidadTableModel.COLUMNA_CO));
		setUpEstadoColumn(table.getColumnModel().getColumn(BuscadorReportesRentabilidadTableModel.COLUMNA_SUBC));
		setUpEstadoColumn(table.getColumnModel().getColumn(BuscadorReportesRentabilidadTableModel.COLUMNA_OTROS));
		setUpEstadoColumn(table.getColumnModel().getColumn(BuscadorReportesRentabilidadTableModel.COLUMNA_LUGAR));
		setUpEstadoColumn(table.getColumnModel().getColumn(BuscadorReportesRentabilidadTableModel.COLUMNA_TERCEROS));
		setUpEstadoColumn(table.getColumnModel().getColumn(BuscadorReportesRentabilidadTableModel.COLUMNA_COMERCIAL));
		setUpEstadoColumn(table.getColumnModel().getColumn(BuscadorReportesRentabilidadTableModel.COLUMNA_REGALIAS));
		setUpEstadoColumn(table.getColumnModel().getColumn(BuscadorReportesRentabilidadTableModel.COLUMNA_RENTABILIDAD));
		setUpEstadoColumn(table.getColumnModel().getColumn(BuscadorReportesRentabilidadTableModel.COLUMNA_RENTABILIDAD_REAL));
		setUpMargenColumn(table.getColumnModel().getColumn(BuscadorReportesRentabilidadTableModel.COLUMNA_MARGEN_RENTABILIDAD));
		setUpMargenRealColumn(table.getColumnModel().getColumn(BuscadorReportesRentabilidadTableModel.COLUMNA_MARGEN_RENTABILIDAD_REAL));
	}
	
	public void setUpNroPptoColumn(TableColumn column) {
		column.setPreferredWidth(250);
		column.setMaxWidth(250);
		column.setWidth(250);
	}
	
	public void setUpEstadoColumn(TableColumn column) {
		/*column.setPreferredWidth(50);
		column.setMaxWidth(50);
		column.setWidth(50);*/

	}

	public void setUpMargenColumn(TableColumn column) {
		column.setCellRenderer(new MargenesTableCellRederer());
	}
	
	public void setUpMargenRealColumn(TableColumn column){
		column.setCellRenderer(new MargenesRealTableCellRenderer());
	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
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
	
	public BuscadorReportesRentabilidadItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		BuscadorReportesRentabilidadTableModel model = (BuscadorReportesRentabilidadTableModel)getTable().getModel();
		
		// obtengo el numero de linea seleccionada de la vista
		int selrow = getTable().getSelectedRow();
		
		//obtengo el numero de linea seleccionada del modelo y no de la vista
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			selrow = table.convertRowIndexToModel(getTable().getSelectedRow());
		
		// si no hay nada seleccionado, asumo la primera linea.
		if (selrow < 0)
			selrow = 0;
		
		// obtengo el item de la tabla
		BuscadorReportesRentabilidadItem item = model.getRow(selrow);
		
		// asigno los datos
		if (item == null){
			System.out.println("TableRenderSeguimientp::getSelectedItem() - item para row "+selrow+" es null");
		}
		
		return item;
	}
	private JFileChooser filechooser;
	public void openSavePopUp(){
		filechooser = new JFileChooser();
		if(filechooser.showSaveDialog(Main.getVentana()) == JFileChooser.APPROVE_OPTION){
			
			File file =filechooser.getSelectedFile();
			File f2 = new File(file.getParentFile().getPath()+"\\"+filechooser.getSelectedFile().getName()+".xls");
			
			if(filechooser.getFileFilter().accept(file))
				exportXLS(file);
			else {
				exportXLS(f2);
			}

		}
	}

	public void exportXLS(File file){
		int tipo=0;	
		BuscadorReportesRentabilidadTableModel model = (BuscadorReportesRentabilidadTableModel)getTable().getModel();
		WritableWorkbook workbook;
		try {
			workbook = Workbook.createWorkbook(file);
			
			WritableSheet sheet = workbook.createSheet("Rentabilidad", 0);		
			for(int j=0;j<table.getModel().getColumnCount()+2;j++){
				if(j==table.getModel().getColumnCount()){
					Label label = new Label(j, 0,"NombreEvento");
					sheet.addCell(label);
				}
				else if(j==table.getModel().getColumnCount()+1){
					Label label = new Label(j, 0,"Vendedor");
					sheet.addCell(label);
				}
				else{
					Label label = new Label(j, 0,table.getModel().getColumnName(j));
					sheet.addCell(label);
				}
				if(j==table.getModel().getColumnCount())
					tipo=TYPE_EVENTO;
				else if(j==table.getModel().getColumnCount()+1)
					tipo=TYPE_VENDEDOR;
				else if(table.getModel().getColumnClass(j).equals(String.class))
					tipo=TYPE_STRING;
				else if(table.getModel().getColumnClass(j).equals(Long.class))
					tipo=TYPE_LONG;
				else if(table.getModel().getColumnClass(j).equals(Integer.class))
					tipo=TYPE_INTEGER;
				else if(table.getModel().getColumnClass(j).equals(Double.class))
					tipo=TYPE_DOUBLE;
				else if(table.getModel().getColumnClass(j).equals(java.lang.Boolean.class))
					tipo=TYPE_BOOLEAN;
				for (int i=1; i<=table.getModel().getRowCount(); i++) {
					if(tipo==TYPE_STRING){
						Label labelData = new Label(j, i,(String)table.getModel().getValueAt(i-1,j));
						sheet.addCell(labelData);
					}
					else if(tipo==TYPE_LONG){
						Number temp = new Number(j, i,(Long)table.getModel().getValueAt(i-1,j));
						sheet.addCell(temp);
					}
					else if(tipo==TYPE_INTEGER){
						Number temp = new Number(j, i,(Integer)table.getModel().getValueAt(i-1,j));
						sheet.addCell(temp);
					}
					else if(tipo==TYPE_DOUBLE){	
						//String d = (String)table.getModel().getValueAt(i-1,j);
						//Double d2 = Double.valueOf(d.substring(1,d.length()));
						
						Double d2 = (Double)table.getModel().getValueAt(i-1,j);
						
						Number temp = new Number(j, i,d2);
						sheet.addCell(temp);
					}
					else if(tipo==TYPE_BOOLEAN){
						Boolean temp = new Boolean(j, i,(java.lang.Boolean)table.getModel().getValueAt(i-1,j));
						sheet.addCell(temp);
					}
					else if(tipo==TYPE_EVENTO){
						Label labelData = new Label(j, i,((BuscadorReportesRentabilidadItem)model.getRow(i-1)).getNombreEvento());
						sheet.addCell(labelData);
					}
					else if(tipo==TYPE_VENDEDOR){
						Label labelData = new Label(j, i,((BuscadorReportesRentabilidadItem)model.getRow(i-1)).getVendedor());
						sheet.addCell(labelData);
					}
				}
			}
			WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 10);
			WritableCellFormat arial10format = new WritableCellFormat (arial10font); 
			Label labelData = new Label(0, table.getModel().getRowCount()+2,"Filtrado por: "+getComentariosXLS(), arial10format);			
			
			sheet.addCell(labelData);
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
