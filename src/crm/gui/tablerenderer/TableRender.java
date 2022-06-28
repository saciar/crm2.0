package crm.gui.tablerenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.JTable.PrintMode;
import javax.swing.event.TableModelEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.write.Boolean;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.swing.*;
import javax.swing.filechooser.*;


import crm.client.util.DateConverter;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesItem;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesTableModel;
import crm.gui.tablerenderer.salas.SalaServicioItem;
import crm.gui.tablerenderer.salas.SalaServiciosTableModel;

public class TableRender extends JPanel {
	
	protected JTable table;
	
	protected int rows;
	
	private JPopupMenu popup;
	private JFileChooser filechooser;

	
	public TableRender(TableModel model){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(1024, 70));	
    
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			table.setAutoCreateRowSorter(true);
		
		initRowsCount(table.getRowCount());
		initPopUp();
	}
	
	public TableRender(SalaServiciosTableModel model){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(1024, 70));	
		
		initRowsCount(table.getRowCount());
		initPopUp();
	}
	
	public TableRender(){

	}
	
	public void initPopUp(){
		popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Imprimir grilla");
		menuItem.setIcon(new javax.swing.ImageIcon(Main.class.getResource("imagenes/printer.png")));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
	    menuItem.addActionListener(new PopUpMenuImprimirActionListener());
	    popup.add(menuItem);
	    
	    JMenuItem menuItemExport = new JMenuItem("Exportar a XLS");
	    menuItemExport.setIcon(new javax.swing.ImageIcon(Main.class.getResource("imagenes/page_excel.png")));
	    menuItemExport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
	    menuItemExport.addActionListener(new PopUpMenuExportarActionListener());
	    
	    popup.add(menuItemExport);	    
	    
	    filechooser = new JFileChooser();
	    filechooser.addChoosableFileFilter(new XlsFilter());
	    filechooser.setAcceptAllFileFilterUsed(false);

	}
	
	public void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popup.show(e.getComponent(),
                       e.getX(), e.getY());
        }
        
    }
	
	public void refreshTable(){
		
	}
	
	public void initRowsCount(int nro) {
		rows = nro;
	}
	
	public JTable getTable() {
		return table;
	}

	protected void initColumnSizes(/*JTable table, String[] headers, int rowsCount*/) {
		TableModel model = table.getModel();

		TableColumn column = null;
		Component comp = null;
		int headerWidth = 0;
		int cellWidth = 0;
		//Vector longValues = model.longValues;
		TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();

		int cols = table.getColumnCount();
		
		//for (int i = 0; i < rows; i++) {
		for (int i = 0; i < cols; i++) {
			column = table.getColumnModel().getColumn(i);
			
			comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
			headerWidth = comp.getPreferredSize().width;
			comp = table.getDefaultRenderer(model.getColumnClass(i))
					.getTableCellRendererComponent(table, model.getValueAt(0,i) /*longValues.get(i)*/, false, false, 0, i);
			cellWidth = comp.getPreferredSize().width;
			column.setPreferredWidth(Math.max(headerWidth, cellWidth));
		}
	}
	
	public static final int TYPE_STRING= 0;
	public static final int TYPE_LONG= 1;
	public static final int TYPE_INTEGER= 2;
	public static final int TYPE_DOUBLE= 3;
	public static final int TYPE_BOOLEAN= 4;
	public static final int TYPE_EVENTO= 5;
	public static final int TYPE_VENDEDOR= 6;
	private String comentariosXLS="";
	
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
				else if(table.getModel().getColumnClass(j).equals(java.lang.Boolean.class))
					tipo=TYPE_BOOLEAN;
				for (int i=1; i<=table.getModel().getRowCount(); i++) {
					if(tipo==TYPE_STRING){
						String data = (String)table.getModel().getValueAt(i-1,j);
						if(data!=null && data.startsWith("$")){
							if(table.getModel().getClass().equals(BuscadorReportesTableModel.class)){
								BuscadorReportesItem item = ((BuscadorReportesTableModel)table.getModel()).getRow(i-1);
								Number temp = new Number(j, i,item.getTotal());
								sheet.addCell(temp);
							}
							else if(table.getModel().getClass().equals(SalaServiciosTableModel.class)){
								SalaServicioItem item = ((SalaServiciosTableModel)table.getModel()).getRow(i-1);	
								Number temp = new Number(j, i,item.getTotal());
								sheet.addCell(temp);
							}
							
						}
						else{
							Label labelData = new Label(j, i,(String)table.getModel().getValueAt(i-1,j));						
							sheet.addCell(labelData);
						}
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
				}
			}
			WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 10);
			WritableCellFormat arial10format = new WritableCellFormat (arial10font); 
			Label labelData = new Label(0, table.getModel().getRowCount()+2,"Filtrado por: "+comentariosXLS, arial10format);			
			
			sheet.addCell(labelData);
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public String getComentariosXLS() {
		return comentariosXLS;
	}

	public void setComentariosXLS(String comentariosXLS) {
		this.comentariosXLS = comentariosXLS;
	}

	private String mensajeImpresion;
	
	private class PopUpMenuImprimirActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {	        
			try {
	        	MessageFormat format = new MessageFormat(mensajeImpresion);
	        	MessageFormat format2 = new MessageFormat("Page {0}");
			    if (getTable().print(PrintMode.FIT_WIDTH,format, format2, true,null,true)) {
			        System.err.println("impresion correcta");
			    }
			} catch (java.awt.print.PrinterException ex) {
			    System.err.format("Cannot print %s%n", ex.getMessage());
			}
		}
	}	
	
	public void openSavePopUp(){
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
	
	private class PopUpMenuExportarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			openSavePopUp();			
		}
		
	}

	public String getMensajeImpresion() {
		return mensajeImpresion;
	}

	public void setMensajeImpresion(String mensajeImpresion) {
		this.mensajeImpresion = mensajeImpresion;
	}

	public class XlsFilter extends FileFilter {

	    //Accept all directories and all xls files.
	    public boolean accept(File f) {
	        if (f.isDirectory()) {
	            return true;
	        }

	        String extension = Utils.getExtension(f);
	        if (extension != null) {
	            if (extension.equals(Utils.xls)) {
	                    return true;
	            } else {
	                return false;
	            }
	        }

	        return false;
	    }

	    //The description of this filter
	    public String getDescription() {
	        return "Excel (*.xls)";
	    }
	}
	
}

