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
import crm.libraries.report.PorcentajeGerencia;
import crm.services.report.sei.PorcentajeComercialesReportSEI;

public class PorcentajeComercialesReport extends CongressReportService implements PorcentajeComercialesReportSEI {
	private static final String REPORT_PPTOS_GCIA_NAME = "jasper/PorcentajesGerencia.jasper";
	
	private PorcentajeComercialesReportSEI stub;
	private static PorcentajeComercialesReport instance;
	
	public PorcentajeComercialesReport() throws WSIFException{
		super("PorcentajeComercialesReportSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("PorcentajeGerencia", Class.forName("crm.libraries.report.PorcentajeGerencia"));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PorcentajeComercialesReportSEI)service.getStub(PorcentajeComercialesReportSEI.class);
	}
	
	public static PorcentajeComercialesReport instance() {
		try {
			if (instance == null) {
				instance = new PorcentajeComercialesReport();
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
	
	public JasperPrint createPresupuestosGciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codLugar, long codEstado, int type) throws RemoteException, JRException {
		
		int day1 = Integer.parseInt(dateInicio.getDay());
		int month1 = Integer.parseInt(dateInicio.getMonth());
		int year1 = Integer.parseInt(dateInicio.getYear());
		
		int day2 = Integer.parseInt(dateFin.getDay());
		int month2 = Integer.parseInt(dateFin.getMonth());
		int year2 = Integer.parseInt(dateFin.getYear());
		
		PorcentajeGerencia[] presupuestos = null;
		if(type == ReportBuilder.REPORT_PPTO_GERENCIA){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, 0, 0, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, 0, 0, codEstado);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, 0, codLugar, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, 0, codLugar, codEstado);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_UC){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, codUC, 0, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_UC_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, codUC, 0, codEstado);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, codUC, codLugar, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, 0, codUC, codLugar, codEstado);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, 0, 0, 0);
		}		
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, 0, 0, codEstado);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, 0, codLugar, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, 0, codLugar, codEstado);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUC, 0, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUC, 0, codEstado);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUC, codLugar, 0);
		}
		else if(type == ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO){
			presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUC, codLugar, codEstado);
		}
		
		int cantTotalPpto = findTotalPptos(day1, month1, year1, day2, month2, year2, codVendedor, codUC);
		
		String subTitle = createPresupuestosSubTitle(dateInicio, dateFin, cantTotalPpto);
		String title = "Todos los presupuestos";	
		
		return createReport(presupuestos, title, subTitle, year1, type,codVendedor, codUC, codLugar, codEstado, cantTotalPpto);
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
	
	private JasperPrint createReport(PorcentajeGerencia[] eventos, String title, String subTitle, int year, 
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
		if(isLugarUsed(type)){
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
			jasperPrint = JasperFillManager.fillReport(eventReport, parameters, PorcComercialesReportDSBuilder.toJRMap(eventos,e.getDescripcion().equals("Nuevo")?"Pendiente":e.getDescripcion()));
		else
			jasperPrint = JasperFillManager.fillReport(eventReport, parameters, PorcComercialesReportDSBuilder.toJRMap(eventos,null));
		
		return jasperPrint;
	}

	public PorcentajeGerencia[] findByRangeDate(int day1, int month1, int year1, int day2, int month2, int year2, long codVendedor, long codUC, long codLugar, long codEstado) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.findByRangeDate(day1, month1, year1, day2, month2, year2, codVendedor, codUC, codLugar, codEstado);
	}

	public int findTotalPptos(int day1, int month1, int year1, 
			int day2, int month2, int year2, long codVendedor, long codUc) throws RemoteException {
		return stub.findTotalPptos(day1, month1, year1, day2, month2, year2,codVendedor,codUc);
	}
	
	
}

class PorcComercialesReportDSBuilder{
	
	private static double totalReporte=0;
	
	private static double totalCobrados=0;
	private static double totalFacturados=0;
	private static double totalConfirmados=0;
	private static double totalPendientes=0;
	private static double totalOS=0;
	private static double totalOF=0;
	private static double totalAdelantos=0;
	private static double totalAdelantados=0;
	private static double totalCancelados=0;
	private static double totalRechazados=0;
	
	private static int cantCobrados=0;
	private static int cantFacturados=0;
	private static int cantConfirmados=0;
	private static int cantPendientes=0;
	private static int cantOS=0;
	private static int cantOF=0;
	private static int cantAdelantos=0;
	private static int cantAdelantados=0;
	private static int cantCancelados=0;
	private static int cantRechazados=0;
	
	@SuppressWarnings("unchecked")
	public static JRMapArrayDataSource toJRMap(PorcentajeGerencia[] eventos, String estadoElegido){
		Object[] data = new Object[eventos.length];
		
		for (int i=0;i<eventos.length;i++) {
			PorcentajeGerencia evento = eventos[i];
			Map map = new HashMap();
			
			map.put("cliente",evento.getCliente());
			map.put("evento",evento.getEvento());
			map.put("orden",evento.getNroPpto());
			map.put("lugar",evento.getLugar());
			map.put("comercial",evento.getVendedor());
			map.put("estadoEvento", evento.getEstado());
			map.put("totalEvento",evento.getTotal());
			map.put("totalPptos",eventos.length);
			map.put("desde",evento.getFechaInicio());
			map.put("hasta",evento.getFechaFin());			
			totalReporte+= evento.getTotal();
			
			if(evento.getEstado().equals("Cobrado")){
				totalCobrados+= evento.getTotal();
				cantCobrados++;	
				map.put("cantConfirmados",cantCobrados);
			}
			
			if(evento.getEstado().equals("Facturado")){
				totalFacturados+= evento.getTotal();
				cantFacturados++;	
				map.put("cantConfirmados",cantFacturados);
			}
			
			if(evento.getEstado().equals("Confirmado")){
				totalConfirmados+= evento.getTotal();
				cantConfirmados++;	
				map.put("cantConfirmados",cantConfirmados);
			}
			
			if(evento.getEstado().equals("Pendiente")){
				totalPendientes+= evento.getTotal();
				cantPendientes++;
				map.put("cantConfirmados",cantPendientes);
			}
			
			if(evento.getEstado().equals("Orden de Servicio")){
				totalOS+= evento.getTotal();
				cantOS++;	
				map.put("cantConfirmados",cantOS);
			}
			
			if(evento.getEstado().equals("Orden de Facturacion")){
				totalOF+= evento.getTotal();
				cantOF++;	
				map.put("cantConfirmados",cantOF);
			}
			
			if(evento.getEstado().equals("Con adelanto")){
				totalAdelantos+= evento.getTotal();
				cantAdelantos++;	
				map.put("cantConfirmados",cantAdelantos);
			}
			
			if(evento.getEstado().equals("Adelanto facturado")){
				totalAdelantados+= evento.getTotal();
				cantAdelantados++;	
				map.put("cantConfirmados",cantAdelantados);
			}
			
			if(evento.getEstado().equals("Cancelado")){
				totalCancelados+= evento.getTotal();
				cantCancelados++;	
				map.put("cantConfirmados",cantCancelados);
			}
			
			if(evento.getEstado().equals("Rechazado")){
				totalRechazados+= evento.getTotal();
				cantRechazados++;
				map.put("cantConfirmados",cantRechazados);
			}		
			
			if(i == eventos.length-1){
				if(estadoElegido == null){
					map.put("total",totalReporte);					
					map.put("totalPptosReporte",eventos.length);
				}
				else{
					if(estadoElegido.equals("Cobrado")){
						map.put("total",totalCobrados);
						map.put("totalPptosReporte",cantCobrados);
					}
					
					if(estadoElegido.equals("Facturado")){
						map.put("total",totalFacturados);
						map.put("totalPptosReporte",cantFacturados);
					}
					
					if(estadoElegido.equals("Confirmado")){
						map.put("total",totalConfirmados);
						map.put("totalPptosReporte",cantConfirmados);
					}
					
					if(estadoElegido.equals("Pendiente")){
						map.put("total",totalPendientes);
						map.put("totalPptosReporte",cantPendientes);
					}
					
					if(estadoElegido.equals("Orden de Servicio")){
						map.put("total",totalOS);
						map.put("totalPptosReporte",cantOS);
					}
					
					if(estadoElegido.equals("Orden de Facturacion")){
						map.put("total",totalOF);
						map.put("totalPptosReporte",cantOF);
					}
					
					if(estadoElegido.equals("Con adelanto")){
						map.put("total",totalAdelantos);
						map.put("totalPptosReporte",cantAdelantos);
					}
					
					if(estadoElegido.equals("Adelanto facturado")){
						map.put("total",totalAdelantados);
						map.put("totalPptosReporte",cantAdelantados);
					}
					
					if(estadoElegido.equals("Cancelado")){
						map.put("total",totalCancelados);
						map.put("totalPptosReporte",cantCancelados);
					}
					
					if(estadoElegido.equals("Rechazado")){
						map.put("total",totalRechazados);
						map.put("totalPptosReporte",cantRechazados);
					}
				}
				//map.put("totalReporte", totalFacturado)
				cantCobrados=0;
				cantFacturados=0;
				cantConfirmados=0;
				cantPendientes=0;
				cantOS=0;
				cantOF=0;
				cantAdelantos=0;
				cantAdelantados=0;
				cantCancelados=0;
				cantRechazados=0;
				
				totalReporte = 0;
				
				totalCobrados=0;
				totalFacturados=0;
				totalConfirmados=0;
				totalPendientes=0;
				totalOS=0;
				totalOF=0;
				totalAdelantos=0;
				totalAdelantados=0;
				totalCancelados=0;
				totalRechazados=0;				
				totalPendientes=0;
			}
			
			data[i] = map;
			
		}
		return new JRMapArrayDataSource(data);
	}

}
