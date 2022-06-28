package crm.client.report;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.wsif.WSIFException;

import crm.client.managers.EstadoEventoManager;
import crm.client.managers.LugarEventoManager;
import crm.client.managers.UnidadComercialManager;
import crm.client.managers.VendedorManager;
import crm.client.util.DateConverter;
import crm.gui.Main;
import crm.gui.components.DatePanel;
import crm.libraries.abm.entities.EstadoEvento;
import crm.libraries.abm.entities.LugarEvento;
import crm.libraries.abm.entities.UnidadComercial;
import crm.libraries.abm.entities.Vendedor;
import crm.libraries.report.SubcontratadosGerencia;
import crm.services.report.sei.SubcontratadosGerenciaReportSEI;

public class SubcontratadosGerenciaReport extends CongressReportService implements SubcontratadosGerenciaReportSEI {
	private static final String REPORT_PPTOS_GCIA_NAME = "jasper/SubcontratadosGerencia.jasper";
	
	private SubcontratadosGerenciaReportSEI stub;
	private static SubcontratadosGerenciaReport instance;
	
	public SubcontratadosGerenciaReport() throws WSIFException{
		super("SubcontratadosGerenciaReportSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("SubcontratadosGerencia", Class.forName("crm.libraries.report.SubcontratadosGerencia"));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (SubcontratadosGerenciaReportSEI)service.getStub(SubcontratadosGerenciaReportSEI.class);
	}
	
	public static SubcontratadosGerenciaReport instance() {
		try {
			if (instance == null) {
				instance = new SubcontratadosGerenciaReport();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public JasperPrint createSubcontratadosGciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long isPropio, long isSubcont, int type) throws RemoteException, JRException {
		
		int day1 = Integer.parseInt(dateInicio.getDay());
		int month1 = Integer.parseInt(dateInicio.getMonth());
		int year1 = Integer.parseInt(dateInicio.getYear());
		
		int day2 = Integer.parseInt(dateFin.getDay());
		int month2 = Integer.parseInt(dateFin.getMonth());
		int year2 = Integer.parseInt(dateFin.getYear());
		
		SubcontratadosGerencia[] presupuestos = null;
		if(type == ReportBuilder.REPORT_PPTO_GERENCIA){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, 0, 0, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, 0, 0, isSubcont);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, 0, isPropio, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, 0, isPropio, isSubcont);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_UC){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, codUC, 0, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_UC_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, codUC, 0, isSubcont);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, codUC, isPropio, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, codUC, isPropio, isSubcont);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, 0, 0, 0);
		}		
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, 0, 0, isSubcont);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, 0, isPropio, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, 0, isPropio, isSubcont);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUC, 0, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUC, 0, isSubcont);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUC, isPropio, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUC, isPropio, isSubcont);
		}
		
		int cantTotalPpto = findTotalPptos(day1, month1, year1, day2, month2, year2);
		
		String subTitle = createSubcontratadosSubTitle(dateInicio, dateFin, cantTotalPpto);
		String title = "Todos los presupuestos";	
		
		return createReport(presupuestos, title, subTitle, year1, type,codVendedor, codUC, isPropio, isSubcont, cantTotalPpto);
	}
	
	String createSubcontratadosSubTitle(DatePanel dateInicio, DatePanel dateFin, int pptos){
		
		int day1 = Integer.parseInt(dateInicio.getDay());
		int month1 = Integer.parseInt(dateInicio.getMonth());
		int year1 = Integer.parseInt(dateInicio.getYear());
		
		int day2 = Integer.parseInt(dateFin.getDay());
		int month2 = Integer.parseInt(dateFin.getMonth());
		int year2 = Integer.parseInt(dateFin.getYear());
		
		Calendar cal = new GregorianCalendar();
		
		cal.set(Calendar.YEAR, year1);
		cal.set(Calendar.MONTH, month1-1);
		cal.set(Calendar.DATE, day1);
		
		Date startDate = cal.getTime();
		
		cal.set(Calendar.YEAR, year2);
		cal.set(Calendar.MONTH, month2-1);
		cal.set(Calendar.DATE, day2);
		
		Date endDate = cal.getTime();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(pptos+" presupuestos desde el ");
		sb.append(DateConverter.convertDateToString(startDate,"EEEEE dd MMMMM yyyy"));
		sb.append(" hasta el ");
		sb.append(DateConverter.convertDateToString(endDate,"EEEEE dd MMMMM yyyy"));

		return sb.toString();
	}
	
	String createSubcontratadosTitle(DatePanel dateInicio, DatePanel dateFin){
		
		int day1 = Integer.parseInt(dateInicio.getDay());
		int month1 = Integer.parseInt(dateInicio.getMonth());
		int year1 = Integer.parseInt(dateInicio.getYear());
		
		int day2 = Integer.parseInt(dateFin.getDay());
		int month2 = Integer.parseInt(dateFin.getMonth());
		int year2 = Integer.parseInt(dateFin.getYear());
		
		Calendar cal = new GregorianCalendar();
		
		cal.set(Calendar.YEAR, year1);
		cal.set(Calendar.MONTH, month1-1);
		cal.set(Calendar.DATE, day1);
		
		Date startDate = cal.getTime();
		
		cal.set(Calendar.YEAR, year2);
		cal.set(Calendar.MONTH, month2-1);
		cal.set(Calendar.DATE, day2);
		
		Date endDate = cal.getTime();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("Desde: ");
		sb.append(DateConverter.convertDateToString(startDate,"EEEEE dd MMMMM yyyy"));
		sb.append(" - Hasta: ");
		sb.append(DateConverter.convertDateToString(endDate,"EEEEE dd MMMMM yyyy"));

		return sb.toString();
	}
	
	private boolean isUCUsed(int type){
		return type == ReportBuilder.REPORT_PPTO_GERENCIA_UC ||
				type == ReportBuilder.REPORT_PPTO_GERENCIA_UC_ESTADO ||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR ||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO ||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC ||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_ESTADO ||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR ||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO;
	}
	
	private boolean isLugarUsed(int type){
		return type == ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR ||
				type == ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_ESTADO ||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR ||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO ||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR ||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO;
	}
	
	private boolean isEstadoUsed(int type){
		return type == ReportBuilder.REPORT_PPTO_GERENCIA_ESTADO||
				type == ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_ESTADO ||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_UC_ESTADO ||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO ||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_VEN_ESTADO ||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO ||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_ESTADO||
				type ==	ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO;
	}
	
	private JasperPrint createReport(SubcontratadosGerencia[] eventos, String title, String subTitle, int year, 
														int type, long codVendedor, long codUc, long codLugar, long codEstado, int totalPpto) throws RemoteException, JRException {

		// 1- cargar los reporte desde los .jasper			
		JasperReport eventReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_PPTOS_GCIA_NAME));
		
		// 2- create a map of parameters to pass to the report.
		Map parameters = new HashMap();
		
		parameters.put("REPORT_TITLE",title);
		parameters.put("REPORT_SUBTITLE",subTitle);
		parameters.put("REPORT_UPDATE",new Date());
		parameters.put("REPORT_IMAGE_URL",Main.class.getResource("imagenes/logo-crn_blancoPpto.png").toString());
		parameters.put("TOTAL_PPTO",totalPpto);
		if(type >= ReportBuilder.REPORT_PPTO_GERENCIA_VEN){
			parameters.put("CHECK_VEND","X");
			Vendedor v = VendedorManager.instance().getVendedorById(String.valueOf(codVendedor));
			parameters.put("VENDEDOR",v.getApellidoYNombre());
		}
		if(isUCUsed(type)){
			parameters.put("CHECK_UC","X");
			UnidadComercial u = UnidadComercialManager.instance().getUnidadComercialById(String.valueOf(codUc));
			parameters.put("UC",u.getDescripcion());
		}
		/*if(isLugarUsed(type)){
			parameters.put("CHECK_LUGAR","X");
			LugarEvento l = LugarEventoManager.instance().getLugarEventoById(String.valueOf(codLugar));
			parameters.put("LUGAREVENTO", l.getNombreLugar());
		}	
		EstadoEvento e = null;
		if(isEstadoUsed(type)){
			e =EstadoEventoManager.instance().getEstadoEventoById(String.valueOf(codEstado));
			parameters.put("CHECK_ESTADO","X");			
			parameters.put("ESTADO", e.getDescripcion().equals("Nuevo")?"Pendiente":e.getDescripcion());
		}
		// 3- create JasperPrint using fillReport() method
		JasperPrint jasperPrint = null;
		if(e != null)
			jasperPrint = JasperFillManager.fillReport(eventReport, parameters, PptoSubGerenciaReportDSBuilder.toJRMap(eventos,e.getDescripcion().equals("Nuevo")?"Pendiente":e.getDescripcion()));
		else*/
		JasperPrint jasperPrint = JasperFillManager.fillReport(eventReport, parameters, PptoSubGerenciaReportDSBuilder.toJRMap(eventos,null));
		
		return jasperPrint;
	}

	public SubcontratadosGerencia[] findByRangeDate(int day1, int month1, int year1, int day2, int month2, int year2, long codVendedor, long codUC, long codLugar, long codEstado) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUC, codLugar, codEstado);
	}

	public int findTotalPptos(int day1, int month1, int year1, 
			int day2, int month2, int year2) throws RemoteException {
		return stub.findTotalPptos(day1, month1, year1, day2, month2, year2);
	}
	
	
}

class PptoSubGerenciaReportDSBuilder{
	
	private static double totalVenta;
	private static double totalCompra;
	
	@SuppressWarnings("unchecked")
	public static JRMapArrayDataSource toJRMap(SubcontratadosGerencia[] eventos, String estadoElegido){
		Object[] data = new Object[eventos.length];
		
		for (int i=0;i<eventos.length;i++) {
			SubcontratadosGerencia evento = eventos[i];
			Map map = new HashMap();
			
			map.put("cliente",evento.getCliente());
			map.put("orden",evento.getNroPpto());
			map.put("comercial",evento.getVendedor());
			map.put("desde",evento.getFechaInicio());
			map.put("hasta",evento.getFechaFin());
			map.put("servicio",evento.getServicio());
			map.put("propio",evento.getPropio());
			map.put("precioVenta",evento.getPrecioVenta());
			map.put("precioCompra",evento.getPrecioCompra());
			map.put("totalPptos",eventos.length);
			totalVenta += evento.getPrecioVenta();
			totalCompra += evento.getPrecioCompra();
			
			if(i == eventos.length-1){
				map.put("totalVenta",totalVenta);
				totalVenta = 0.0;
				map.put("totalCompra",totalCompra);
				totalCompra = 0.0;
			}
			
			
			data[i] = map;
			
		}
		return new JRMapArrayDataSource(data);
	}

}
