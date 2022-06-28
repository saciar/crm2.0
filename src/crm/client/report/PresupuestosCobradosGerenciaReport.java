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

import crm.client.util.DateConverter;
import crm.gui.Main;
import crm.gui.components.DatePanel;
import crm.libraries.report.PresupuestosCobradosGerencia;
import crm.services.report.sei.PresupuestosCobradosGerenciaReportSEI;

public class PresupuestosCobradosGerenciaReport extends CongressReportService implements PresupuestosCobradosGerenciaReportSEI {
	private static final String REPORT_PPTOS_GCIA_NAME = "jasper/PresupuestosCobradosGerencia.jasper";
	
	private PresupuestosCobradosGerenciaReportSEI stub;
	private static PresupuestosCobradosGerenciaReport instance;
	
	public PresupuestosCobradosGerenciaReport() throws WSIFException{
		super("PresupuestosCobradosGerenciaReportSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("PresupuestosCobradosGerencia", Class.forName("crm.libraries.report.PresupuestosCobradosGerencia"));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PresupuestosCobradosGerenciaReportSEI)service.getStub(PresupuestosCobradosGerenciaReportSEI.class);
	}
	
	public static PresupuestosCobradosGerenciaReport instance() {
		try {
			if (instance == null) {
				instance = new PresupuestosCobradosGerenciaReport();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

public JasperPrint createPresupuestosGciaReport2(DatePanel dateInicio, DatePanel dateFin) throws RemoteException, JRException {
		
		int day1 = Integer.parseInt(dateInicio.getDay());
		int month1 = Integer.parseInt(dateInicio.getMonth());
		int year1 = Integer.parseInt(dateInicio.getYear());
		
		int day2 = Integer.parseInt(dateFin.getDay());
		int month2 = Integer.parseInt(dateFin.getMonth());
		int year2 = Integer.parseInt(dateFin.getYear());
		
		PresupuestosCobradosGerencia[] presupuestos = findByRangeDate(day1, month1, year1, day2, month2, year2);

		String subTitle = createPresupuestosTitle(dateInicio, dateFin);
		String title = "Todos los presupuestos";
		
		return createReport(presupuestos, title, subTitle, year1);
	}
			
	public String createPresupuestosTitle(DatePanel dateInicio, DatePanel dateFin){
		
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
	
	private JasperPrint createReport(PresupuestosCobradosGerencia[] eventos, String title, String subTitle, int year) throws RemoteException, JRException {

		// 1- cargar los reporte desde los .jasper			
		JasperReport eventReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_PPTOS_GCIA_NAME));
		
		// 2- create a map of parameters to pass to the report.
		Map parameters = new HashMap();
		
		parameters.put("REPORT_TITLE",title);
		parameters.put("REPORT_SUBTITLE",subTitle);
		parameters.put("REPORT_UPDATE",new Date());
		parameters.put("REPORT_IMAGE_URL",Main.class.getResource("imagenes/logo-crn_blancoPpto.png").toString());

		// 3- create JasperPrint using fillReport() method
		JasperPrint jasperPrint = JasperFillManager.fillReport(eventReport, parameters, PptoCobradosGerenciaReportDSBuilder.toJRMap(eventos));
		
		return jasperPrint;
	}

	public PresupuestosCobradosGerencia[] findByRangeDate(int day1, int month1, int year1, int day2, int month2, int year2) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.findByRangeDate(day1, month1, year1, day2, month2, year2);
	}	
	
}

class PptoCobradosGerenciaReportDSBuilder{
	
	private static double totalFacturado;
	
	public static JRMapArrayDataSource toJRMap(PresupuestosCobradosGerencia[] eventos){
		Object[] data = new Object[eventos.length];
		
		for (int i=0;i<eventos.length;i++) {
			PresupuestosCobradosGerencia evento = eventos[i];
			Map map = new HashMap();
			
			map.put("cliente",evento.getCliente());
			map.put("evento",evento.getEvento());
			map.put("orden",evento.getNroPpto());
			map.put("comercial",evento.getVendedor());
			map.put("totalEvento",evento.getTotal());
			map.put("totalPptos",eventos.length);
			totalFacturado += evento.getTotal();
			
			if(i == eventos.length-1){
				map.put("total",totalFacturado);
				totalFacturado = 0.0;
			}
			
			
			data[i] = map;
			
		}
		return new JRMapArrayDataSource(data);
	}

}
