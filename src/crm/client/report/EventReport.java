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
import crm.gui.Main;
import crm.libraries.report.Evento;
import crm.libraries.report.EventoAccesorio;
import crm.libraries.report.EventoOperador;
import crm.libraries.report.EventoSala;
import crm.libraries.report.EventoServicio;
import crm.services.report.sei.EventReportSEI;

public class EventReport extends CongressReportService implements EventReportSEI {
	private static final String REPORT_EVENTOS_NAME = "jasper/eventos.jasper";
	private static final String REPORT_EVENTOS_SALAS_NAME = "jasper/eventos_salas.jasper";
	private static final String REPORT_EVENTOS_SERVICIOS_NAME = "jasper/eventos_servicios.jasper";
	private static final String REPORT_EVENTOS_OPERADORES_NAME = "jasper/eventos_operadores.jasper";
	
	private EventReportSEI stub;
	private static EventReport instance;
	
	public EventReport() throws WSIFException{
		super("EventReportSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Evento", Class.forName("crm.libraries.report.Evento"));
			addType("EventoSala", Class.forName("crm.libraries.report.EventoSala"));
			addType("EventoServicio", Class.forName("crm.libraries.report.EventoServicio"));
			addType("EventoAccesorio", Class.forName("crm.libraries.report.EventoAccesorio"));
			addType("EventoOperador", Class.forName("crm.libraries.report.EventoOperador"));
			
			/*addType("ArrayOfEvento", Class.forName("crm.client.deserializer.ArrayOfEvento"));
			addType("ArrayOfEventoSala", Class.forName("crm.client.deserializer.ArrayOfEventoSala"));
			addType("ArrayOfEventoServicio", Class.forName("crm.client.deserializer.ArrayOfEventoServicio"));
			addType("ArrayOfEventoAccesorio", Class.forName("crm.client.deserializer.ArrayOfEventoAccesorio"));*/
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (EventReportSEI)service.getStub(EventReportSEI.class);
	}

	

	public static EventReport instance() {
		try {
			if (instance == null) {
				instance = new EventReport();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

	public Evento[] findByDay(int day, int month, int year) throws RemoteException {
		return stub.findByDay(day, month, year);
	}
	
	public Evento[] findByDayAndVendedor(int day, int month, int year, long codVend) throws RemoteException {
		return stub.findByDayAndVendedor(day, month, year, codVend);
	}
	
	public Evento[] findByDayAndUC(int day, int month, int year, long codUC) throws RemoteException {
		return stub.findByDayAndUC(day, month, year, codUC);
	}
	
	public Evento[] findByWeek(int week, int year) throws RemoteException {
		return stub.findByWeek(week, year);
	}
	
	public Evento[] findByWeekAndVendedor(int week, int year, long codVendedor) throws RemoteException {
		return stub.findByWeekAndVendedor(week, year, codVendedor);
	}
	
	public Evento[] findByWeekAndUC(int week, int year, long codUC) throws RemoteException {
		return stub.findByWeekAndUC(week, year,codUC);
	}
	
	/**
	 * Crea un reporte por week.
	 * 
	 * @param week
	 * @param year
	 * @return
	 * @throws RemoteException
	 * @throws JRException
	 */
	public JasperPrint createWeekReport(int week, int year) throws RemoteException, JRException {

		Evento[] eventos = findByWeek(week,year);
		String subTitle = createWeekTitle(week,year);
		String title = "Week";
		
		return createReport(eventos, title, subTitle, year);
	}
	
	/**
	 * Crea un reporte por week por vendedor.
	 * 
	 * @param week
	 * @param year
	 * @return
	 * @throws RemoteException
	 * @throws JRException
	 */
	public JasperPrint createWeekReportVend(int week, int year, long codVendedor) throws RemoteException, JRException {

		Evento[] eventos = findByWeekAndVendedor(week,year, codVendedor);
		String subTitle = createWeekTitle(week,year);
		String title = "Week";
		
		return createReport(eventos, title, subTitle, year);
	}
	
	/**
	 * Crea un reporte por week por vendedor.
	 * 
	 * @param week
	 * @param year
	 * @return
	 * @throws RemoteException
	 * @throws JRException
	 */
	public JasperPrint createWeekReportUC(int week, int year, long codUC) throws RemoteException, JRException {

		Evento[] eventos = findByWeekAndUC(week,year, codUC);
		String subTitle = createWeekTitle(week,year);
		String title = "Week";
		
		return createReport(eventos, title, subTitle, year);
	}
	
	/**
	 * Crea un reporte diario
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 * @throws RemoteException
	 * @throws JRException
	 */
	public JasperPrint createDailyReport(int day, int month, int year) throws RemoteException, JRException {

		Evento[] eventos = findByDay(day, month, year);
		String subTitle = createDailyTitle(day, month, year);
		String title = "Daily";
		
		return createReport(eventos, title, subTitle, year);
	}
	
	/**
	 * Crea un reporte diario por Vendedor
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 * @throws RemoteException
	 * @throws JRException
	 */
	public JasperPrint createDailyReportVend(int day, int month, int year, long codVend) throws RemoteException, JRException {

		Evento[] eventos = findByDayAndVendedor(day, month, year,codVend);
		String subTitle = createDailyTitle(day, month, year);
		String title = "Daily";
		
		return createReport(eventos, title, subTitle, year);
	}
	
	/**
	 * Crea un reporte diario por UC
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 * @throws RemoteException
	 * @throws JRException
	 */
	public JasperPrint createDailyReportUC(int day, int month, int year, long codUC) throws RemoteException, JRException {

		Evento[] eventos = findByDayAndUC(day, month, year, codUC);
		String subTitle = createDailyTitle(day, month, year);
		String title = "Daily";
		
		return createReport(eventos, title, subTitle, year);
	}
	
	/**
	 * Crea un reporte de eventos
	 * 
	 * @param eventos el listado de eventos que se iterará
	 * @param subTitle el titulo que recibirá el reporte, puede ser week o day
	 * @param year el año del reporte
	 * @return
	 * @throws RemoteException
	 * @throws JRException
	 */
	@SuppressWarnings("unchecked")
	private JasperPrint createReport(Evento[] eventos, String title, String subTitle, int year) throws RemoteException, JRException {

		// 1- cargar los reporte desde los .jasper			
		JasperReport eventReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_EVENTOS_NAME));
		JasperReport salasReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_EVENTOS_SALAS_NAME));
		JasperReport serviciosReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_EVENTOS_SERVICIOS_NAME));
		JasperReport operadoresReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_EVENTOS_OPERADORES_NAME));
		
		// 2- create a map of parameters to pass to the report.
		Map parameters = new HashMap();
		parameters.put("SUBREPORT_SALAS",salasReport);
		parameters.put("SUBREPORT_SERVICIOS",serviciosReport);
		parameters.put("SUBREPORT_OPERADORES", operadoresReport);
		
		parameters.put("REPORT_TITLE",title);
		parameters.put("REPORT_SUBTITLE",subTitle);
		parameters.put("REPORT_YEAR",new Integer(year));
		parameters.put("REPORT_UPDATE",new Date());
		parameters.put("REPORT_IMAGE_URL",Main.class.getResource("imagenes/logo-crn_blancoPpto.png").toString());
		
		// 3- create JasperPrint using fillReport() method
		JasperPrint jasperPrint = JasperFillManager.fillReport(eventReport, parameters, EventReportDSBuilder.toJRMap(eventos));
		
		return jasperPrint;
	}
	
	/**
	 * Crea el titulo para un reporte por week
	 * 
	 * @param week
	 * @param year
	 * @return
	 */
	public String createWeekTitle(int week, int year){
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR, year);
		cal.setFirstDayOfWeek(Calendar.SUNDAY);
		cal.setMinimalDaysInFirstWeek(1);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		Date startDate = cal.getTime();
		
		cal.set(Calendar.WEEK_OF_YEAR, week+1);
		Date endDate = cal.getTime();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("WEEK N°: ");
		sb.append(week);
		sb.append(" (semana del ");
		sb.append(DateConverter.convertDateToString(startDate,"d/M"));
		sb.append(" al ");
		sb.append(DateConverter.convertDateToString(endDate,"d/M"));
		sb.append(")");

		return sb.toString();
	}
	
	
	/**
	 * Crea el titulo de un reporte diario.
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 */
	String createDailyTitle(int day, int month, int year){
		Calendar cal = new GregorianCalendar();
		
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, day);
		
		Date date = cal.getTime();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("DAILY: ");
		sb.append(DateConverter.convertDateToString(date,"EEEEE dd MMMMM yyyy"));
		sb.append("     WEEK: ");
		sb.append(cal.get(Calendar.WEEK_OF_YEAR));

		return sb.toString();
	}
}


/**
 * Prepara el data source a partir de un array de eventos
 * 
 * @author hernux
 */
class EventReportDSBuilder {
	
	/**
	 * Crea el datasource para el reporte de eventos
	 * 
	 * @param eventos
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JRMapArrayDataSource toJRMap(Evento[] eventos){
		Object[] data = new Object[eventos.length];
		
		for (int i=0;i<eventos.length;i++) {
			Evento evento = eventos[i];
			Map map = new HashMap();
			
			map.put("nombreFantasia",evento.getNombreFantasia());
			map.put("nombreEvento",evento.getNombreEvento());
			map.put("numeroPresupuesto",evento.getNumeroPresupuesto());
			map.put("lugarEvento",evento.getLugarEvento());
			map.put("tipoEvento",evento.getTipoEvento());
			map.put("fechaInicialEvento",getDatefromString(evento.getFechaInicialEvento()));
			map.put("cantidadSalas",evento.getCantidadSalas());
			map.put("fechaInstalacion",getDatefromString(evento.getFechaInstalacion()));
			map.put("unidad",evento.getUnidad());
			map.put("vendedor",evento.getVendedor());
			map.put("origen",evento.getOrigen());
			if (evento.getSinPrecio() != 0)
				map.put("sinPrecio","Si");
			else map.put("sinPrecio","No");
			if(evento.getPrecioModificado() != 0)
				map.put("precioModificado", "Si");
			else map.put("precioModificado", "No");
			if (evento.getSubcontrataciones() != 0)
				map.put("subcontrataciones","Si");
			else map.put("subcontrataciones","No");
			map.put("monto",evento.getMonto());
			if(evento.getCobrado())
				map.put("cobrado","Si");
			else map.put("cobrado", "No");
			if(evento.isFacturado())
				map.put("facturado","Si");
			else map.put("facturado","No");
			
			if(evento.isConfirmado())
				map.put("confirmado","Si");
			else map.put("confirmado","No");
			if(evento.isOs())
				map.put("OS","Si");
			else map.put("OS","No");
			if(evento.isOf())
				map.put("OF","Si");
			else map.put("OF","No");
			if(evento.isAdelanto())
				map.put("adelanto","Si");
			else map.put("adelanto","No");
			if(evento.isAdelantado())
				map.put("adelantado","Si");
			else map.put("adelantado","No");			
			
			map.put("fechaFinalEvento",getDatefromString(evento.getFechaFinalEvento()));
			if(evento.isNuevo())
				map.put("nuevoCliente", "Si");
			else map.put("nuevoCliente", "No");
			
			map.put("data_source_salas",toJRMap(evento.getSalas()));
			map.put("data_source_operadores", toJRMap(evento.getOperadores()));
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
	@SuppressWarnings("unchecked")
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
	}
	
	/**
	 * Crea el datasource para el reporte de servicios
	 * 
	 * @param servicios
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static JRMapArrayDataSource toJRMap(EventoServicio[] servicios){
		Object[] data = new Object[servicios.length];
		
		for (int i=0;i<servicios.length;i++) {
			EventoServicio servicio = servicios[i];
			Map map = new HashMap();
			
			map.put("servicioId",servicio.getServicioId());
			map.put("cantidad",servicio.getCantidad());
			map.put("servicio",servicio.getServicio());
			map.put("dias",servicio.getDias());
			
			StringBuffer accesorios = new StringBuffer();
			
			for (int j=0;j < servicio.getAccesorios().length;j++){
				EventoAccesorio accesorio = servicio.getAccesorios()[j];
				accesorios.append(accesorio.getCantidad());
				accesorios.append(" ");
				accesorios.append(accesorio.getAccesorio());
				
				if (j+1 < servicio.getAccesorios().length)
					accesorios.append(", ");
			}
			
			map.put("accesorios", accesorios.toString());
			
			data[i] = map;
		}
		
		return new JRMapArrayDataSource(data);
	}
	
	@SuppressWarnings("unchecked")
	private static JRMapArrayDataSource toJRMap(EventoOperador[] operadores){
		Object[] data = new Object[operadores.length];
		
		for (int i=0;i<operadores.length;i++) {
			EventoOperador operador = operadores[i];
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