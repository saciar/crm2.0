package crm.client.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import crm.client.report.PresupuestoExcelReport;
import crm.gui.pantalla.solapa.MainPanelComercial;
import crm.gui.pantalla.solapa.SalaModel;
import crm.libraries.report.Presupuesto;
import crm.libraries.report.PresupuestoSala;
import crm.libraries.report.PresupuestoServicio;

public class PresupuestoExcel {

	private final static int FILA_TITULO = 3;
	private final static int FILA_FECHAS = 4;
	private final static int FILA_PRIMER_SALA = 6;
	private final static int FILA_PRIMER_FAMILIA =8;
	private final static int FILA_PRIMER_SERVICIO = 9;
	private final static int FILA_PRIMER_TOTAL = 11;
	private final static int FILA_PRIMER_FIRMA = 39;
	
	Presupuesto presupuesto; 
	
	private static XSSFWorkbook book;
	
	private CellStyle eventoStyle;
	private CellStyle fechaStyle;
	private CellStyle salaStyle;
	private CellStyle familiaStyle;
	private CellStyle servicioStyle;
	private CellStyle serviciosTotalStyle;
	private CellStyle totalStyle;
	
	private XSSFSheet sheetDetalle;
	private XSSFSheet sheetGeneral;
	
	private double total=0.0d;
		
	public static void main(String[] args){
		//new PresupuestoExcel(46930); //cardiologia
		new PresupuestoExcel(47996);
	}
	
	public PresupuestoExcel(long nroppto ) {
		getPresupuesto(nroppto);
		abrirExcel();
		createGeneralTab();
		createDetalleTab();
	}
	
	private void getPresupuesto(long nroPpto){
		try {
			presupuesto = PresupuestoExcelReport.instance().findByNroPpto(nroPpto)[0];
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void abrirExcel(){

		try {
			FileInputStream fiel = new FileInputStream("src/crm/gui/template/presupuesto.xlsx");
			book = new XSSFWorkbook(fiel); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/********************************************* Tab general ******************************************************/
	private void createGeneralTab(){

	}
	
	/********************************************* Tab detalle ******************************************************/
	
	private void createDetalleTab(){
		sheetDetalle = book.getSheetAt(0);
		getStyles();
		addEvento();
		int puntero = addSalas();
		addTotal(puntero);
		guardarExcel();
	}
	
	private void getStyles(){
		Row filaTitulo = sheetDetalle.getRow(FILA_TITULO); // esta es la fila donde se ubica el nombre de evento
		Cell celdaTitulo = filaTitulo.getCell(0); // y a partir de la columna A
		eventoStyle = celdaTitulo.getCellStyle();
		eventoStyle.setWrapText(false);
		
		Row filaFecha = sheetDetalle.getRow(FILA_FECHAS); // esta es la fila donde se ubica el nombre de evento
		Cell celdaFecha = filaFecha.getCell(0); // y a partir de la columna A
		fechaStyle = celdaFecha.getCellStyle();
		fechaStyle.setWrapText(false);
		
		Row filaSala = sheetDetalle.getRow(FILA_PRIMER_SALA);			
		Cell celdaSala = filaSala.getCell(0);
		salaStyle = celdaSala.getCellStyle();
		salaStyle.setWrapText(false);
		
		Row filaFamilia = sheetDetalle.getRow(FILA_PRIMER_FAMILIA);			
		Cell celdaFamilia = filaFamilia.getCell(0);
		familiaStyle = celdaFamilia.getCellStyle();
		familiaStyle.setWrapText(false);

		Row filaServicio = sheetDetalle.getRow(FILA_PRIMER_SERVICIO);			
		Cell celdaServicio = filaServicio.getCell(0);
		servicioStyle = celdaServicio.getCellStyle();
		servicioStyle.setWrapText(false);
				
		Cell celdaServicioTotal = filaServicio.getCell(1);
		serviciosTotalStyle = celdaServicioTotal.getCellStyle();		
		serviciosTotalStyle.setWrapText(false);
		
		Row filaTotal = sheetDetalle.getRow(FILA_PRIMER_TOTAL);
		Cell celdaTotal = filaTotal.getCell(0);
		totalStyle = celdaTotal.getCellStyle();
		CreationHelper createHelper2 = book.getCreationHelper();
		totalStyle.setDataFormat(createHelper2.createDataFormat().getFormat("$ #.##"));
		totalStyle.setWrapText(false);
	}

	private void addEvento(){
		String titulo = presupuesto.getEvento();
		crearCeldaCombinada(FILA_TITULO, titulo,eventoStyle,33.75f);
		crearCeldaCombinada(FILA_FECHAS,presupuesto.getPeriodo(), fechaStyle, 26.25f);
	}
	
	private int addTotal(int puntero){

		crearCeldaDoble(puntero,"TOTAL GENERAL",totalStyle,26.25f);
		Row filaTotalGral = sheetDetalle.getRow(puntero);			
		Cell celdaTotalGral = filaTotalGral.getCell(1);
		celdaTotalGral.setCellValue(total);
		
		crearCeldaDoble(puntero,"IVA 21%",totalStyle,26.25f);
		Row filaIva = sheetDetalle.getRow(puntero+1);			
		Cell celdaIva = filaIva.getCell(1);
		celdaIva.setCellValue(total*0.21d);
		
		crearCeldaDoble(puntero,"TOTAL CON IVA",totalStyle,26.25f);
		Row filaConIva = sheetDetalle.getRow(puntero+2);			
		Cell celdaConIva = filaConIva.getCell(1);
		celdaConIva.setCellValue(total + (total*0.21d));
		
		return puntero;

	}
	
	private int addSalas(){		
		int puntero = FILA_PRIMER_SALA; //esta es la fila donde se ubica la primera sala 
		PresupuestoSala[] salas = presupuesto.getSalas();			
		for(int i=0;i<salas.length;i++){
			PresupuestoSala sala = salas[i];
			crearCeldaCombinada(puntero, sala.getNombreSala(),salaStyle, 26.25f);
			xssfShiftRows(sheetDetalle,puntero+1,sheetDetalle.getLastRowNum(),1);
			puntero++; // bajo dos filas para que haya un espacio en blanco entre la sala y la primer super familia
			puntero = addServicios(puntero, sala);			
		}		
		System.out.println("el puntero esta en: "+puntero);
		crearCeldaSimple(puntero+1, "",0.0d,servicioStyle, 0.25f);
		crearCeldaSimple(puntero+2, "",0.0d,servicioStyle, 0.25f);
		puntero += 4;
		return puntero;
	}
	
	private ArrayList<String> getSuperFamilias(PresupuestoServicio[] servicios ){
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0; i<servicios.length;i++){
			boolean esta= false;
			for(int j=0;j<result.size();j++){
				if(servicios[i].getSuperFamilia().equals(result.get(j))){	
					esta = true;
					break;
				}
			}
			if(!esta){
				result.add(servicios[i].getSuperFamilia());
			}
		}
		return result;
	}
	
	private int addServicios(int puntero, PresupuestoSala sala){
		PresupuestoServicio[] servicios = sala.getServicios();
		ArrayList<String> supFamilias = getSuperFamilias(servicios);
		Iterator it = supFamilias.iterator();
		while(it.hasNext()){
			String sup =(String)it.next();
			crearCeldaDoble(puntero,sup,familiaStyle,26.25f);
			int puntero_fam = puntero; //guardo la posicion de la super familia asi puedo despues poner el total de la misma

			xssfShiftRows(sheetDetalle,puntero+1,sheetDetalle.getLastRowNum(),1);
			puntero++;			
			double total_fam=0.0d;
			for(int i=0;i<servicios.length;i++){
				PresupuestoServicio servicio = servicios[i];
				if(servicio.getSuperFamilia().equals(sup)){
					crearCeldaSimple(puntero, servicio.getCantidad()+" "+servicio.getServicio(),servicio.getImporte(),servicioStyle, 26.25f);
					total_fam += servicio.getImporte();
					total += servicio.getImporte();
					if(puntero != FILA_PRIMER_SERVICIO){
						xssfShiftRows(sheetDetalle,puntero+1,sheetDetalle.getLastRowNum(),1);
					}
					puntero++;
				}
			}
			Row filaFamilia = sheetDetalle.getRow(puntero_fam);			
			Cell celdaFamilia = filaFamilia.getCell(1);
			CreationHelper createHelper = book.getCreationHelper();
			celdaFamilia.getCellStyle().setDataFormat(createHelper.createDataFormat().getFormat("$ #.##"));
			celdaFamilia.setCellValue(total_fam);
		}
		
		return puntero;
	}

	private void crearCeldaSimple(int fila, String descripcion, Double importe, CellStyle style, float altura){
		Row row = sheetDetalle.createRow(fila);	
		row.setHeightInPoints(altura);
		
		Cell cell = CellUtil.createCell(row, 0, descripcion,style);
		Cell cell2 = CellUtil.createCell(row, 1, String.valueOf(importe), serviciosTotalStyle);
		
		CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
	}
	
	
	private void crearCeldaCombinada(int fila, String nombreSala, CellStyle style, float altura){
		Row row = sheetDetalle.createRow(fila);	
		row.setHeightInPoints(altura);
		
		CellRangeAddress cellRangeAddress = new CellRangeAddress(fila, fila, 0, 1);
		sheetDetalle.addMergedRegion(cellRangeAddress);		
		
		Cell cell = CellUtil.createCell(row, 0, nombreSala,style);
		
		CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
	}
	
	private void crearCeldaDoble(int fila, String nombreSala, CellStyle style, float altura){
		Row row = sheetDetalle.createRow(fila);	
		row.setHeightInPoints(altura);
				
		Cell cell = CellUtil.createCell(row, 0, nombreSala,style);
		Cell cell2 = CellUtil.createCell(row, 1, "Total", style);
				

		CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
		//CellUtil.setVerticalAlignment(cell2, VerticalAlignment.CENTER);

	}
	
	public static void xssfShiftRows(XSSFSheet sh, int firstRow, int lastRow, int shiftN) {
	    int firstShiftedRow = firstRow + shiftN;
	    int lastShiftedRow = lastRow + shiftN;
	    sh.shiftRows(firstRow, lastRow, shiftN, true, true);
	    /*
	     * This code is a workaround for the bug
	     * https://bz.apache.org/bugzilla/show_bug.cgi?id=57423
	     * In the sheet xml the row references are updated like this:
	     * <row r="139"
	     * but the cell references are incorrect
	     * <c r="A138" s="1"/>
	     *
	     * The number in the row 139 must match the number in the cell A139.
	     * This code manually updates these links.
	     */
	               for (int nRow = firstShiftedRow; nRow <= lastShiftedRow; nRow++)  {
	    final Row row = sh.getRow(nRow);
	    if (row != null) {
	    String msg = "Row[rownum=" + row.getRowNum()
	    + "] contains cell(s) included in a multi-cell array formula.  "
	    + "You cannot change part of an array.";
	    for (Cell c : row) {
	        ((XSSFCell) c).updateCellReferencesForShifting(msg);
	    }
	  }
	}
	}
	
	private void guardarExcel(){
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("C:/Users/Sergio/Desktop/presupuesto.xlsx");
			book.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
