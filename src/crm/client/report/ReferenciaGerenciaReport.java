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

import crm.client.managers.PrtPptoCondPagoManager;
import crm.client.managers.TipoContactoManager;
import crm.client.managers.UnidadComercialManager;
import crm.client.managers.VendedorManager;
import crm.client.util.DateConverter;
import crm.gui.Main;
import crm.gui.components.DatePanel;
import crm.libraries.abm.entities.PrtPptoCondPago;
import crm.libraries.abm.entities.TipoContacto;
import crm.libraries.abm.entities.UnidadComercial;
import crm.libraries.abm.entities.Vendedor;
import crm.libraries.report.ReferenciaGerencia;
import crm.services.report.sei.ReferenciaGerenciaReportSEI;;

public class ReferenciaGerenciaReport extends CongressReportService implements ReferenciaGerenciaReportSEI {
	private static final String REPORT_PPTOS_GCIA_NAME = "jasper/ReferenciaGerencia.jasper";
	
	private ReferenciaGerenciaReportSEI stub;
	private static ReferenciaGerenciaReport instance;
	
	public ReferenciaGerenciaReport() throws WSIFException{
		super("ReferenciaGerenciaReportSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("ReferenciaGerencia", Class.forName("crm.libraries.report.ReferenciaGerencia"));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (ReferenciaGerenciaReportSEI)service.getStub(ReferenciaGerenciaReportSEI.class);
	}
	
	public static ReferenciaGerenciaReport instance() {
		try {
			if (instance == null) {
				instance = new ReferenciaGerenciaReport();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

	/*public PresupuestosGerencia[] findByRangeDate(int day1, int month1, int year1, int day2, int month2, int year2, 
											long codVendedor, long codUc, long codLugar, long codEstado) throws RemoteException {
		return stub.findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUc, codLugar, codEstado);
	}*/
	
	/*public JasperPrint createPresupuestosGciaReport(int day1, int month1, int year1, int day2, int month2, int year2) throws RemoteException, JRException {

		PresupuestosGerencia[] presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, 0,0,0);
		String subTitle = createPresupuestosTitle(day1, month1, year1, day2, month2, year2);
		String title = "Todos los presupuestos";
		
		return createReport(presupuestos, title, subTitle, year1);
	}*/
	
	public JasperPrint createPresupuestosGciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codLugar, int type) throws RemoteException, JRException {
		
		int day1 = Integer.parseInt(dateInicio.getDay());
		int month1 = Integer.parseInt(dateInicio.getMonth());
		int year1 = Integer.parseInt(dateInicio.getYear());
		
		int day2 = Integer.parseInt(dateFin.getDay());
		int month2 = Integer.parseInt(dateFin.getMonth());
		int year2 = Integer.parseInt(dateFin.getYear());
		
		ReferenciaGerencia[] presupuestos = null;
		if(type == ReportBuilder.REPORT_PPTO_GERENCIA){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, 0, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, 0, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, 0, codLugar);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, 0, codLugar);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_UC){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, codUC, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_UC_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, codUC, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, codUC, codLugar);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, codUC, codLugar);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, 0, 0);
		}		
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, 0, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, 0, codLugar);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, 0, codLugar);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUC, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUC, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUC, codLugar);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUC, codLugar);
		}
		
		int cantTotalPpto = findTotalPptos(day1, month1, year1, day2, month2, year2);
		
		String subTitle = createPresupuestosSubTitle(dateInicio, dateFin, cantTotalPpto);
		String title = "Todos los presupuestos";	
		
		return createReport(presupuestos, title, subTitle, year1, type,codVendedor, codUC, codLugar, cantTotalPpto);
	}
	
	String createPresupuestosSubTitle(DatePanel dateInicio, DatePanel dateFin, int pptos){
		
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
	
	String createPresupuestosTitle(DatePanel dateInicio, DatePanel dateFin){
		
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
	
	private JasperPrint createReport(ReferenciaGerencia[] eventos, String title, String subTitle, int year, 
														int type, long codVendedor, long codUc, long codCondPago, int totalPpto) throws RemoteException, JRException {

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
		if(isLugarUsed(type)){
			parameters.put("CHECK_CONDPAGO","X");
			TipoContacto c = TipoContactoManager.instance().getTipoContactoById(String.valueOf(codCondPago));
			parameters.put("CONDPAGO", c.getDescripcion());
		}	
		// 3- create JasperPrint using fillReport() method
		JasperPrint jasperPrint = null;
		
		jasperPrint = JasperFillManager.fillReport(eventReport, parameters, ReferenciaGerenciaReportDSBuilder.toJRMap(eventos, codCondPago));
		
		return jasperPrint;
	}

	public ReferenciaGerencia[] findByRangeDate(int day1, int month1, int year1, int day2, int month2, int year2, long codVendedor, long codUC, long codLugar) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUC, codLugar);
	}

	public int findTotalPptos(int day1, int month1, int year1, 
			int day2, int month2, int year2) throws RemoteException {
		return stub.findTotalPptos(day1, month1, year1, day2, month2, year2);
	}
	
	
}

class ReferenciaGerenciaReportDSBuilder{
	
	private static double totalFacturado;
	
	@SuppressWarnings("unchecked")
	public static JRMapArrayDataSource toJRMap(ReferenciaGerencia[] eventos, long codReferencia){
		Object[] data = new Object[eventos.length];
		int totalPptos = 0;
		int totalOtros = 0;
		int j=0;
		for (int i=0;i<eventos.length;i++) {			
			ReferenciaGerencia evento = eventos[i];
			if(evento.getCodReferencia() == codReferencia){
				Map map = new HashMap();
				
				map.put("cliente",evento.getCliente());
				map.put("evento",evento.getEvento());
				map.put("orden",evento.getNroPpto());
				map.put("lugar",evento.getLugar());
				map.put("comercial",evento.getVendedor());
				map.put("totalEvento",evento.getTotal());
				
				totalPptos++;
				map.put("desde",evento.getFechaInicio());
				map.put("hasta",evento.getFechaFin());
				map.put("estadoEvento",evento.getEstado());
				totalFacturado += evento.getTotal();
				map.put("totalPptos",totalPptos);
				map.put("cantBuscados",totalPptos);
				map.put("cantPptos", eventos.length);
				map.put("condicion", evento.getReferencia());
				map.put("codCondPago", codReferencia);
				
				if(j == eventos.length-1){
					map.put("total",totalFacturado);
					totalFacturado = 0.0;					
				}
								
				data[j] = map;
				j++;
			}
			else {
				Map map = new HashMap();
				
				totalOtros++;
				map.put("estadoEvento","Otros");
				map.put("totalPptos",totalPptos);
				map.put("cantBuscados",totalOtros);
				map.put("cantPptos", eventos.length);
				map.put("condicion", evento.getReferencia());
				map.put("codCondPago", codReferencia);
				
				if(j == eventos.length-1){
					map.put("total",totalFacturado);
					totalFacturado = 0.0;					
				}
				
				data[j] = map;
				j++;
			}
			
		}
		System.out.println("Total estado "+ totalPptos);
		System.out.println("Total otros "+totalOtros);
		return new JRMapArrayDataSource(data);
	}

}
