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

import crm.client.managers.EstadoEventoManager;
import crm.client.managers.PresupuestosManager;
import crm.client.util.DateConverter;
import crm.client.util.Util;
import crm.gui.Main;
import crm.libraries.report.EventoProveedor;
import crm.libraries.report.OrdenServicio;
import crm.libraries.report.EventoAccesorio;
import crm.libraries.report.EventoOperador;
import crm.libraries.report.OrdenServicioSala;
import crm.libraries.report.OrdenServicioServicio;
import crm.services.report.sei.OrdenServicioReportSEI;

public class OrdenServicioReport extends CongressReportService implements OrdenServicioReportSEI {
	private static final String REPORT_OS_NAME = "jasper/os.jasper";
	private static final String REPORT_OS_SALAS_NAME = "jasper/os_salas.jasper";
	private static final String REPORT_OS_SERVICIOS_NAME = "jasper/os_servicios.jasper";
	private static final String REPORT_OS_OPERADORES_NAME = "jasper/eventos_operadores.jasper";
	private static final String REPORT_OS_PROVEEDORES_NAME = "jasper/eventos_proveedores.jasper";
	
	private OrdenServicioReportSEI stub;
	private static OrdenServicioReport instance;
	private Date fechaEmision;
	
	public OrdenServicioReport() throws WSIFException{
		super("OrdenServicioReportSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("OrdenServicio", Class.forName("crm.libraries.report.OrdenServicio"));
			addType("OrdenServicioSala", Class.forName("crm.libraries.report.OrdenServicioSala"));
			addType("OrdenServicioServicio", Class.forName("crm.libraries.report.OrdenServicioServicio"));
			addType("EventoAccesorio", Class.forName("crm.libraries.report.EventoAccesorio"));
			addType("EventoOperador", Class.forName("crm.libraries.report.EventoOperador"));
			addType("EventoProveedor", Class.forName("crm.libraries.report.EventoProveedor"));
			
			/*addType("ArrayOfEvento", Class.forName("crm.client.deserializer.ArrayOfEvento"));
			addType("ArrayOfEventoSala", Class.forName("crm.client.deserializer.ArrayOfEventoSala"));
			addType("ArrayOfEventoServicio", Class.forName("crm.client.deserializer.ArrayOfEventoServicio"));
			addType("ArrayOfEventoAccesorio", Class.forName("crm.client.deserializer.ArrayOfEventoAccesorio"));*/
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (OrdenServicioReportSEI)service.getStub(OrdenServicioReportSEI.class);
	}

	

	public static OrdenServicioReport instance() {
		try {
			if (instance == null) {
				instance = new OrdenServicioReport();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public void savePdfFile(long nroPpto) throws RemoteException{
		stub.savePdfFile(nroPpto);
	}
	
	public OrdenServicio[] findByNroPpto(long nroPpto) throws RemoteException {
		return stub.findByNroPpto(nroPpto);
	}
	
	public boolean sendOSByEmail2(long nroPpto, String fechaInicial, String fechaFinal, String evento, String usuarioId, String codLugar, String emailDestino) throws RemoteException{
		return stub.sendOSByEmail2(nroPpto, fechaInicial, fechaFinal, evento, usuarioId, codLugar, emailDestino);
	}
	
	public boolean sendOSByEmail(long nroPpto, String fechaInicial, String fechaFinal, String evento, String usuarioId, String codLugar) throws RemoteException{
		return stub.sendOSByEmail(nroPpto, fechaInicial, fechaFinal, evento, usuarioId, codLugar);
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
	public JasperPrint createOSReport(long nroPpto) throws RemoteException, JRException {

		OrdenServicio[] eventos = findByNroPpto(nroPpto);
		String subTitle = createOSTitle(nroPpto);
		String title = getEstadoOS(nroPpto);
		
		return createReport(eventos, title, subTitle);
	}
	
	private String getEstadoOS (long nroPpto){
		try{
			String fechaOSSt = PresupuestosManager.instance().getFechaByNroPptoAndStateAndUser(nroPpto, 
					(Integer.valueOf(EstadoEventoManager.CODIGO_ESTADO_ORDEN_SERVICIO)).intValue());
			Date fechaOS = OSReportDSBuilder.getDatefromString(fechaOSSt);
			if (PresupuestosManager.instance().isPptoCancelado(nroPpto)){
				String fechaCancSt = PresupuestosManager.instance().getFechaByNroPptoAndStateAndUser(nroPpto, 
						(Integer.valueOf(EstadoEventoManager.CODIGO_ESTADO_CANCELADO)).intValue());
				Date fechaCanc = OSReportDSBuilder.getDatefromString(fechaCancSt);
				fechaEmision = fechaCanc; 
				return "Cancelada";
			}			
			else if(PresupuestosManager.instance().isPptoActualizado(nroPpto)){				
				Date fechaAct = getMaximaFechaActualizacion(nroPpto, 
						Integer.valueOf(EstadoEventoManager.CODIGO_ESTADO_ACTUALIZADO).intValue());
				if(fechaOS.before(fechaAct)){
					fechaEmision = fechaAct;
					return "Actualizada";
				}
			} 
			fechaEmision = fechaOS;
			return "Original";
		}
		catch (RemoteException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	private Date getMaximaFechaActualizacion(long nroPpto, int estado) throws RemoteException{
		String fecha = PresupuestosManager.instance().getMaxFechaByNroPptoAndState(nroPpto, estado);
		return OSReportDSBuilder.getDatefromString(fecha);
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
	private JasperPrint createReport(OrdenServicio[] eventos, String title, String subTitle) throws RemoteException, JRException {

		// 1- cargar los reporte desde los .jasper			
		JasperReport oSReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_OS_NAME));
		JasperReport oSsalasReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_OS_SALAS_NAME));
		JasperReport oSserviciosReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_OS_SERVICIOS_NAME));
		JasperReport oSoperadoresReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_OS_OPERADORES_NAME));
		JasperReport oSproveedoresReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_OS_PROVEEDORES_NAME));
		
		
		// 2- create a map of parameters to pass to the report.
		Map parameters = new HashMap();
		parameters.put("SUBREPORT_SALAS",oSsalasReport);
		parameters.put("SUBREPORT_SERVICIOS",oSserviciosReport);
		parameters.put("SUBREPORT_OPERADORES", oSoperadoresReport);
		parameters.put("SUBREPORT_PROVEEDORES", oSproveedoresReport);
		
		parameters.put("REPORT_TITLE",title);
		parameters.put("REPORT_SUBTITLE", subTitle);
		parameters.put("REPORT_UPDATE",new Date());
		//parameters.put("REPORT_IMAGE_URL",REPORT_IMAGE_URL);
		parameters.put("REPORT_IMAGE_URL",Main.class.getResource("imagenes/logo-crn_blancoPpto.png").toString());
		parameters.put("REPORT_DATE", fechaEmision);
		
		// 3- create JasperPrint using fillReport() method
		JasperPrint jasperPrint = JasperFillManager.fillReport(oSReport, parameters, OSReportDSBuilder.toJRMap(eventos));
		
		return jasperPrint;
	}
	
	/**
	 * Crea el titulo para un reporte por week
	 * 
	 * @param week
	 * @param year
	 * @return
	 */
	public String createOSTitle(long nroPpto){
				
		StringBuffer sb = new StringBuffer();
		
		sb.append("ORDEN DE SERVICIO N°: ");
		sb.append(nroPpto);
		
		return sb.toString();
	}
	
	public Date getFechaEmision(){
		return fechaEmision;
	}

}

	


/**
 * Prepara el data source a partir de un array de eventos
 * 
 * @author hernux
 */
class OSReportDSBuilder {
	
	/**
	 * Crea el datasource para el reporte de eventos
	 * 
	 * @param ordenes
	 * @return
	 */
	
	private static boolean hasProveeedores = false;
	
	@SuppressWarnings("unchecked")
	public static JRMapArrayDataSource toJRMap(OrdenServicio[] ordenes){
		Object[] data = new Object[ordenes.length];
		
		for (int i=0;i<ordenes.length;i++) {
			OrdenServicio ordenServicio = ordenes[i];
			Map map = new HashMap();
			
			map.put("nombreFantasia",ordenServicio.getNombreFantasia());
			map.put("nombreEvento",ordenServicio.getNombreEvento());
			map.put("numeroOrden",ordenServicio.getOrdenServicio());
			map.put("lugarEvento",ordenServicio.getLugarEvento());
			map.put("tipoEvento",ordenServicio.getTipoEvento());
			map.put("fechaInicialEvento",getDatefromString((ordenServicio.getFechaInicialEvento())));
			map.put("cantidadSalas",ordenServicio.getCantidadSalas());
			map.put("fechaInstalacion",getDatefromString((ordenServicio.getFechaInstalacion())));
			map.put("unidad",ordenServicio.getUnidad());
			map.put("vendedor",ordenServicio.getVendedor());
			map.put("domicilio", ordenServicio.getDomicilio());			
			map.put("fechaFinalEvento", getDatefromString((ordenServicio.getFechaFinalEvento())));
			map.put("ingresoEquipos", ordenServicio.getIngresoEquipos());
			if(ordenServicio.getObservaciones().equals(""))
				map.put("observacionesEvento", "Sin observaciones.");
			else map.put("observacionesEvento", ordenServicio.getObservaciones());
			map.put("responsableEvento", ordenServicio.getResponsableEvento());
			map.put("responsableLugar", ordenServicio.getResponsableLugar());
			map.put("seguridadEquipos", ordenServicio.getSeguridadEquipos());
			map.put("telefonosLugar", ordenServicio.getTelefonosLugar());
			map.put("telefonosRespEvento", ordenServicio.getTelefonosRespEvento());
			map.put("telefonosRespLugar", ordenServicio.getTelefonosRespLugar());
			map.put("tipoLugar", ordenServicio.getTipoLugar());
			map.put("tipoUniforme", ordenServicio.getTipoUniforme());
			map.put("totalPersonas", ordenServicio.getTotalPersonas());
			map.put("nroWeek", calcularWeek(getDatefromString((ordenServicio.getFechaInicialEvento()))));
			map.put("categoria", ordenServicio.getCategoria());
			
			map.put("data_source_salas",toJRMap(ordenServicio.getSalas(),i+1));
			//if(hasProveeedores)
				//map.put("data_source_proveedores", toJRMap(ordenServicio.getProveedores()));
			//map.put("data_source_operadores", toJRMap(ordenServicio.getOperadores()));
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
	private static JRMapArrayDataSource toJRMap(OrdenServicioSala[] salas, int numeroSala){
		Object[] data = new Object[salas.length];
		
		for (int i=0;i<salas.length;i++) {
			OrdenServicioSala sala = salas[i];
			Map map = new HashMap();
			
			map.put("salaId",sala.getSalaId());
			map.put("nombreSala",sala.getNombreSala());
			map.put("fechaInicio",getDatefromString((sala.getFechaInicio())));
			map.put("fechaFin",getDatefromString((sala.getFechaFin())));
			map.put("totalPersonas",sala.getTotalPersonas());
			map.put("fechaDesarme", getDatefromString((sala.getFechaDesarme())));
			map.put("fechaPruebas", getDatefromString((sala.getFechaPruebas())));
			map.put("tipoArmado", sala.getTipoArmado());
			map.put("alto", sala.getAlto());
			map.put("ancho", sala.getAncho());
			map.put("largo", sala.getLargo());
			map.put("capacidad", sala.getCapacidad());
			map.put("totalPersonas",sala.getTotalPersonas());
			map.put("observacionesSala", sala.getObservaciones());
			map.put("numeroSala",numeroSala);
			
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
	private static JRMapArrayDataSource toJRMap(OrdenServicioServicio[] servicios){
		Object[] data = new Object[servicios.length];
		hasProveeedores = false;
		for (int i=0;i<servicios.length;i++) {
			OrdenServicioServicio servicio = servicios[i];
			Map map = new HashMap();
			
			map.put("servicioId",servicio.getServicioId());
			map.put("cantidad",servicio.getCantidad());
			map.put("servicio",servicio.getServicio());
			map.put("dias",servicio.getDias());
			map.put("familia",servicio.getFamilia());
			if(servicio.isSubcontratado()){
				map.put("subcontratado", "Si");
				hasProveeedores = true;
			}
			else map.put("subcontratado", "No"); 
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
	
	@SuppressWarnings("unchecked")
	private static JRMapArrayDataSource toJRMap(EventoProveedor[] proveedores){
		Object[] data = new Object[proveedores.length];
		
		for (int i=0;i<proveedores.length;i++) {
			EventoProveedor proveedor = proveedores[i];
			Map map = new HashMap();
			
			map.put("nombre",proveedor.getProveedor());
			map.put("telefonos",proveedor.getTelefonos());
			map.put("domicilio",proveedor.getDomicilio());		
			
			data[i] = map;
		}
		
		return new JRMapArrayDataSource(data);
	}
	
	public static Date getDatefromString(String date){
		Date d = null;
		try {
			d = DateConverter.convertStringToDate(date, "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	private static long calcularWeek (Date fechaInicio){
		 Calendar cal = Calendar.getInstance();
	        
	        cal.setTime(fechaInicio);
	        cal.setFirstDayOfWeek(Calendar.SUNDAY);
	        cal.setMinimalDaysInFirstWeek(1);
	        return cal.get(Calendar.WEEK_OF_YEAR);
	}

}
