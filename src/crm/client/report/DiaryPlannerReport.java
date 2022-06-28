package crm.client.report;

import java.rmi.RemoteException;
import java.text.ParseException;
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
import crm.client.util.DateDiff;
import crm.gui.Main;
import crm.libraries.report.DiaryPlannerOperador;
import crm.libraries.report.DiaryPlannerSala;
import crm.libraries.report.DiaryPlannerServicio;
import crm.services.report.sei.DiaryPlannerReportSEI;
;

public class DiaryPlannerReport extends CongressReportService implements DiaryPlannerReportSEI {
	private static final String REPORT_EVENTOS_NAME = "jasper/week.jasper";
	private static final String REPORT_EVENTOS_SERVICIOS_NAME = "jasper/week_servicios.jasper";
	private static final String REPORT_EVENTOS_OPERADORES_NAME = "jasper/week_operadores.jasper";
	
	private DiaryPlannerReportSEI stub;
	private static DiaryPlannerReport instance;
	private Date fechaElegida;
	
	public DiaryPlannerReport() throws WSIFException{
		super("DiaryPlannerReportSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("DiaryPlannerSala", Class.forName("crm.libraries.report.DiaryPlannerSala"));
			addType("DiaryPlannerServicio", Class.forName("crm.libraries.report.DiaryPlannerServicio"));
			addType("DiaryPlannerOperador", Class.forName("crm.libraries.report.DiaryPlannerOperador"));
			
			/*addType("ArrayOfEvento", Class.forName("crm.client.deserializer.ArrayOfEvento"));
			addType("ArrayOfEventoSala", Class.forName("crm.client.deserializer.ArrayOfEventoSala"));
			addType("ArrayOfEventoServicio", Class.forName("crm.client.deserializer.ArrayOfEventoServicio"));
			addType("ArrayOfEventoAccesorio", Class.forName("crm.client.deserializer.ArrayOfEventoAccesorio"));*/
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (DiaryPlannerReportSEI)service.getStub(DiaryPlannerReportSEI.class);
	}

	

	public static DiaryPlannerReport instance() {
		try {
			if (instance == null) {
				instance = new DiaryPlannerReport();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public DiaryPlannerSala[] findByDay(int day,int month, int year, String tipo) throws RemoteException{
		return stub.findByDay(day, month, year, tipo);
	}
	
	public DiaryPlannerSala[] findByDateRange(int day1,int month1, int year1, int day2,int month2, int year2) throws RemoteException{
		return stub.findByDateRange(day1,month1,year1,day2,month2,year2);
	}
	
	public JasperPrint createDailyReport(int day, int month, int year, String tipo) throws RemoteException, JRException {

		DiaryPlannerSala[] diaries = findByDay(day, month, year, tipo);
		String subTitle = createDailyTitle(day, month, year);
		String title = "Diary Planner";
		
		return createReport(diaries, title, subTitle, year);
		
	}
	
	public JasperPrint createDailyReportComplete(int day, int month, int year) throws RemoteException, JRException {

		DiaryPlannerSala[] diaries = findByDateRange(day, month, year, day+1, month, year);
		String subTitle = createDailyTitle(day, month, year);
		String title = "Diary Planner Completo";
		
		return createReport(diaries, title, subTitle, year);
		
	}
	
	@SuppressWarnings("unchecked")
	private JasperPrint createReport(DiaryPlannerSala[] eventos, String title, String subTitle, int year) throws RemoteException, JRException {

		// 1- cargar los reporte desde los .jasper			
		JasperReport eventReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_EVENTOS_NAME));
		JasperReport serviciosReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_EVENTOS_SERVICIOS_NAME));
		JasperReport operadoresReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_EVENTOS_OPERADORES_NAME));
		
		// 2- create a map of parameters to pass to the report.
		Map parameters = new HashMap();
		parameters.put("SUBREPORT_SERVICIOS",serviciosReport);
		parameters.put("SUBREPORT_OPERADORES", operadoresReport);
		
		parameters.put("REPORT_TITLE",title);
		parameters.put("REPORT_SUBTITLE",subTitle);
		parameters.put("REPORT_YEAR",new Integer(year));
		parameters.put("REPORT_UPDATE",new Date());
		//parameters.put("REPORT_IMAGE_URL",Main.class.getResource("imagenes/logo-crn_blancoPpto.png").toString());
		
		// 3- create JasperPrint using fillReport() method
		JasperPrint jasperPrint = JasperFillManager.fillReport(eventReport, parameters, DiaryReportBuilder.toJRMap(eventos, fechaElegida));
		
		return jasperPrint;
	}
	
	String createDailyTitle(int day, int month, int year){
		Calendar cal = new GregorianCalendar();
		
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, day);
		
		fechaElegida = cal.getTime();
		
		StringBuffer sb = new StringBuffer();

		sb.append(DateConverter.convertDateToString(fechaElegida,"EEEEE dd MMMMM yyyy"));
		sb.append("     Week: ");
		sb.append(cal.get(Calendar.WEEK_OF_YEAR));

		return sb.toString();
	}

}

class DiaryReportBuilder{
	/**
	 * Crea el datasource para el reporte de eventos
	 * 
	 * @param eventos
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JRMapArrayDataSource toJRMap(DiaryPlannerSala[] eventos, Date fechaElegida){
		Object[] data = new Object[eventos.length];
		
		for (int i=0;i<eventos.length;i++) {
			DiaryPlannerSala evento = eventos[i];
			Map map = new HashMap();
			
			map.put("cliente",evento.getNombreFantasia());
			map.put("evento",evento.getNombreEvento());
			map.put("orden",evento.getNumeroPresupuesto());
			map.put("lugar",evento.getLugarEvento());
			map.put("inicio",getDatefromString(evento.getFechaInicialSala()));
			map.put("final",getDatefromString(evento.getFechaFinalSala()));
			map.put("instalacion",getDatefromString(evento.getFechaInstalacion()));
			StringBuffer iniciales = new StringBuffer();
			iniciales.append(evento.getVendedor().substring(0,2));
			int posBlanco = evento.getVendedor().indexOf(" ");
			iniciales.append(evento.getVendedor().substring(posBlanco+1,posBlanco+2));
			map.put("comercial",iniciales.toString());
			map.put("sala", evento.getNombreSala());
			DateDiff dateDiff = DateDiff.calcularFechas(DateConverter.convertDateToString(fechaElegida,"yyyy-MM-dd hh:mm:ss"),evento.getFechaFinalEvento()); 
			map.put("dias",dateDiff.getDayOnly()+1);
			map.put("estado",evento.getEstado());
			
			map.put("total", eventos.length);
			
			map.put("data_source_servicios",toJRMap(evento.getServicios()));
			//map.put("data_source_operadores", toJRMap(evento.getOperadores()));
			data[i] = map;
		}
		
		return new JRMapArrayDataSource(data);
	}
	
	/**
	 * Crea el datasource para el reporte de salas
	 * 
	 * @param salas
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	private static JRMapArrayDataSource toJRMap(EventoSala[] salas){
		Object[] data = new Object[salas.length];
		
		for (int i=0;i<salas.length;i++) {
			EventoSala sala = salas[i];
			Map map = new HashMap();
			
			map.put("salaId",sala.getSalaId());
			map.put("nombreSala",sala.getNombreSala());
			map.put("fechaInicio",getDatefromString(sala.getFechaInicio()));
			map.put("fechaFin",getDatefromString(sala.getFechaFin()));
			//map.put("fechaInstalacion",sala.getFechaInstalacion());
			map.put("totalPersonas",sala.getTotalPersonas());
			
			map.put("data_source_servicios", toJRMap(sala.getServicios()));
			
			data[i] = map;
		}
		
		return new JRMapArrayDataSource(data);
	}*/
	
	/**
	 * Crea el datasource para el reporte de servicios
	 * 
	 * @param servicios
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static JRMapArrayDataSource toJRMap(DiaryPlannerServicio[] servicios){
		Object[] data = new Object[servicios.length];
		
		for (int i=0;i<servicios.length;i++) {
			DiaryPlannerServicio servicio = servicios[i];
			Map map = new HashMap();
			
			map.put("servicioId",servicio.getServicioId());
			map.put("cantidad",servicio.getCantidad());
			map.put("servicio",servicio.getServicio());
			map.put("dias",servicio.getDias());
						
			data[i] = map;
		}
		
		return new JRMapArrayDataSource(data);
	}
	
	@SuppressWarnings("unchecked")
	private static JRMapArrayDataSource toJRMap(DiaryPlannerOperador[] operadores){
		Object[] data = new Object[operadores.length];
		
		for (int i=0;i<operadores.length;i++) {
			DiaryPlannerOperador operador = operadores[i];
			Map map = new HashMap();
			
			map.put("nombreyApellido",operador.getNombreyApellido());
			map.put("puesto",operador.getPuesto());
			
			data[i] = map;
		}
		
		return new JRMapArrayDataSource(data);
	}
	
	private static Date getDatefromString(String date){
		Date d = null;
		try {
			d = DateConverter.convertStringToDate(date, "yyyy-MM-dd hh:mm:ss");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
}
