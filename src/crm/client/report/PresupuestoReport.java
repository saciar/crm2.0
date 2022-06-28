package crm.client.report;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.wsif.WSIFException;

import crm.client.managers.PresupuestosManager;
import crm.client.util.DateConverter;
import crm.client.util.Util;
import crm.gui.Main;
import crm.libraries.report.OrdenFacturacion;
import crm.libraries.report.OrdenFacturacionSalas;
import crm.libraries.report.OrdenFacturacionServicio;
import crm.libraries.report.Presupuesto;
import crm.libraries.report.PresupuestoOpcional;
import crm.libraries.report.PresupuestoSala;
import crm.libraries.report.PresupuestoServicio;
import crm.services.report.sei.PresupuestoReportSEI;
import crm.services.sei.ModalidadContratManagerSEI;

public class PresupuestoReport extends CongressReportService implements PresupuestoReportSEI {
	private static final String REPORT_PRESUP_NAME = "jasper/presupuesto.jasper";
	private static final String REPORT_PRESUP_SALAS_NAME = "jasper/presupuesto_salas.jasper";
	private static final String REPORT_PRESUP_SERVICIOS_NAME = "jasper/presupuesto_servicios.jasper";
	private static final String REPORT_PRESUP_OPCIONALES_NAME = "jasper/presupuesto_opcionales.jasper";
	private static final String REPORT_PRESUP_SERVICIOS_NAME_DETALLES = "jasper/presupuesto_servicio_detalles.jasper";
	private static final String REPORT_PRESUP_OPCIONALES_NAME_DETALLES = "jasper/presupuesto_opcionales_detalles.jasper";	
	
	private PresupuestoReportSEI stub;
	private static PresupuestoReport instance;
	
	public PresupuestoReport() throws WSIFException{
		super("PresupuestoReportSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Presupuesto", Class.forName("crm.libraries.report.Presupuesto"));
			addType("PresupuestoSala", Class.forName("crm.libraries.report.PresupuestoSala"));
			addType("PresupuestoServicio", Class.forName("crm.libraries.report.PresupuestoServicio"));
			addType("PresupuestoOpcional", Class.forName("crm.libraries.report.PresupuestoOpcional"));
			
			/*addType("ArrayOfEvento", Class.forName("crm.client.deserializer.ArrayOfEvento"));
			addType("ArrayOfEventoSala", Class.forName("crm.client.deserializer.ArrayOfEventoSala"));
			addType("ArrayOfEventoServicio", Class.forName("crm.client.deserializer.ArrayOfEventoServicio"));
			addType("ArrayOfEventoAccesorio", Class.forName("crm.client.deserializer.ArrayOfEventoAccesorio"));*/
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PresupuestoReportSEI)service.getStub(PresupuestoReportSEI.class);
	}

	

	public static PresupuestoReport instance() {
		try {
			if (instance == null) {
				instance = new PresupuestoReport();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public Presupuesto[] findByNroPpto(long nroPpto, long idCancelacion, long idHeader, long idFooter, long idInstalacion,
			long idValidez, long idFormaPago, long idCondPago, long idFirma, long idSeguridad, long idPersonal, long idCondReserva, long idTipoPresupuesto, 
			long idPeriodo, long idMoneda,double cotizacion) throws RemoteException{
		return stub.findByNroPpto(nroPpto, idCancelacion, idHeader, idFooter, idInstalacion, idValidez, idFormaPago, idCondPago, idFirma, idSeguridad, idPersonal, idCondReserva, 
				idTipoPresupuesto, idPeriodo, idMoneda, cotizacion);
	}
	
	public void savePdfFile(long nroPpto, long idCancelacion, long idHeader, long idFooter, long idInstalacion,
			long idValidez, long idFormaPago, long idCondPago, long idFirma, long idSeguridad, long idPersonal, long idCondReserva, 
			long idTipoPresupuesto, long idPeriodo, long idMoneda, double cotizacion) throws RemoteException{	
		stub.savePdfFile(nroPpto, idCancelacion, idHeader, idFooter, idInstalacion, idValidez, idFormaPago, idCondPago, idFirma, idSeguridad, idPersonal, idCondReserva, idTipoPresupuesto, idPeriodo, idMoneda, cotizacion);
	}
	
	public void saveTxtFile(long nroPpto, long idCancelacion, long idHeader, long idFooter, long idInstalacion,
			long idValidez, long idFormaPago, long idCondPago, long idFirma, long idSeguridad, long idPersonal, long idCondReserva, 
			long idTipoPresupuesto, long idPeriodo, long idMoneda, double cotizacion) throws RemoteException{	
		stub.saveTxtFile(nroPpto, idCancelacion, idHeader, idFooter, idInstalacion, idValidez, idFormaPago, idCondPago, idFirma, idSeguridad, idPersonal, idCondReserva, idTipoPresupuesto, idPeriodo, idMoneda, cotizacion);
	}
	
	public void sendPresupByEmail(long nroPpto, String usuarioId, String codContactoCliente) throws RemoteException{
		stub.sendPresupByEmail(nroPpto, usuarioId, codContactoCliente);
	}
	
	public void sendEmail(String usuarioId, String codContactoCliente) throws RemoteException{
		stub.sendEmail(usuarioId,codContactoCliente);
	}
	
	/**
	 * Crea un reporte de orden de presupuesto.
	 * 
	 * @param nroPpto
	 * @return
	 * @throws RemoteException
	 * @throws JRException
	 */
	public JasperPrint createPresupuestoReport(long nroPpto, long idCancelacion, long idHeader, long idFooter, long idInstalacion,
			long idValidez, long idFormaPago, long idCondPago, long idFirma, long idSeguridad, long idPersonal, long idCondReserva, long idTipoPresupuesto, 
			long idPeriodo, long idMoneda, double cotizacion) throws RemoteException, JRException {

		Presupuesto[] presupuestos = findByNroPpto(nroPpto, idCancelacion, idHeader, idFooter, idInstalacion, idValidez, idFormaPago, idCondPago, idFirma, idSeguridad, idPersonal, 
				idCondReserva, idTipoPresupuesto, idPeriodo, idMoneda, cotizacion);
		
		return createReport(presupuestos);
	}
	
	/**
	 * Crea un reporte de orden de presupuesto sin las descripciones detalladas de los servicios
	 * 
	 * @param nroPpto
	 * @return
	 * @throws RemoteException
	 * @throws JRException
	 */
	public JasperPrint createPresupuestoResumReport(long nroPpto, long idCancelacion, long idHeader, long idFooter, long idInstalacion,
			long idValidez, long idFormaPago, long idCondPago, long idFirma, long idSeguridad, long idPersonal, long idCondReserva, long idTipoPresupuesto, 
			long idPeriodo, long idMoneda, double cotizacion) throws RemoteException, JRException {

		Presupuesto[] presupuestos = findByNroPpto(nroPpto, idCancelacion, idHeader, idFooter, idInstalacion, idValidez, idFormaPago, idCondPago, idFirma, idSeguridad, idPersonal, 
				idCondReserva, idTipoPresupuesto, idPeriodo, idMoneda, cotizacion);
		
		return createReportResum(presupuestos);
	}
	
	/**
	 * Crea un reporte de presupuestos
	 * 
	 * @param presupuestos el listado de eventos que se iterará
	 * @param subTitle el titulo que recibirá el reporte, puede ser week o day
	 * @param year el año del reporte
	 * @return
	 * @throws RemoteException
	 * @throws JRException
	 */
	@SuppressWarnings("unchecked")
	private JasperPrint createReport(Presupuesto[] presupuestos) throws RemoteException, JRException {

		// 1- cargar los reporte desde los .jasper			
		JasperReport presupReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_PRESUP_NAME));
		JasperReport presupSalasReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_PRESUP_SALAS_NAME));
		JasperReport presupServiciosReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_PRESUP_SERVICIOS_NAME));
		JasperReport presupOpcionalesReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_PRESUP_OPCIONALES_NAME));		
		JasperReport presupServiciosDetallesReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_PRESUP_SERVICIOS_NAME_DETALLES));
		JasperReport presupOpcionalesDetallesReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_PRESUP_OPCIONALES_NAME_DETALLES));
		
		// 2- create a map of parameters to pass to the report.
		Map parameters = new HashMap();
		parameters.put("SUBREPORT_SALAS",presupSalasReport);
		parameters.put("SUBREPORT_SERVICIOS",presupServiciosReport);
		parameters.put("SUBREPORT_OPCIONALES",presupOpcionalesReport);
		parameters.put("SUBREPORT_SERVICIOS_DETALLES",presupServiciosDetallesReport);
		parameters.put("SUBREPORT_OPCIONALES_DETALLES",presupOpcionalesDetallesReport);
		
		parameters.put("REPORT_UPDATE",new Date());
		parameters.put("REPORT_IMAGE_URL",Main.class.getResource("imagenes/logo-crn_blancoPpto.png").toString());
		parameters.put("REPORT_FONDO",Main.class.getResource("imagenes/encabezadoPpto.jpg").toString());
		parameters.put("REPORT_IMAGE_BACK",Main.class.getResource("imagenes/Foto_1.jpg").toString());
		
		// 3- create JasperPrint using fillReport() method
		JasperPrint jasperPrint = JasperFillManager.fillReport(presupReport, parameters, PresupReportDSBuilder.toJRMap(presupuestos, true));
		
		return jasperPrint;
	}
	
	/**
	 * Crea un reporte de presupuestos sin descripciones detalladas de servicios
	 * 
	 * @param presupuestos el listado de eventos que se iterará
	 * @param subTitle el titulo que recibirá el reporte, puede ser week o day
	 * @param year el año del reporte
	 * @return
	 * @throws RemoteException
	 * @throws JRException
	 */
	@SuppressWarnings("unchecked")
	private JasperPrint createReportResum(Presupuesto[] presupuestos) throws RemoteException, JRException {

		// 1- cargar los reporte desde los .jasper			
		JasperReport presupReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_PRESUP_NAME));
		JasperReport presupSalasReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_PRESUP_SALAS_NAME));
		JasperReport presupServiciosReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_PRESUP_SERVICIOS_NAME));
		JasperReport presupOpcionalesReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_PRESUP_OPCIONALES_NAME));		
		JasperReport presupServiciosDetallesReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_PRESUP_SERVICIOS_NAME_DETALLES));
		JasperReport presupOpcionalesDetallesReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_PRESUP_OPCIONALES_NAME_DETALLES));
		
		// 2- create a map of parameters to pass to the report.
		Map parameters = new HashMap();
		parameters.put("SUBREPORT_SALAS",presupSalasReport);
		parameters.put("SUBREPORT_SERVICIOS",presupServiciosReport);
		parameters.put("SUBREPORT_OPCIONALES",presupOpcionalesReport);
		parameters.put("SUBREPORT_SERVICIOS_DETALLES",presupServiciosDetallesReport);
		parameters.put("SUBREPORT_OPCIONALES_DETALLES",presupOpcionalesDetallesReport);
		
		parameters.put("REPORT_UPDATE",new Date());
		parameters.put("REPORT_IMAGE_URL",Main.class.getResource("imagenes/logo-crn_blancoPpto.png").toString());
		parameters.put("REPORT_FONDO",Main.class.getResource("imagenes/encabezadoPpto.jpg").toString());
		parameters.put("REPORT_IMAGE_BACK",Main.class.getResource("imagenes/Foto_1.jpg").toString());
		// 3- create JasperPrint using fillReport() method
		JasperPrint jasperPrint = JasperFillManager.fillReport(presupReport, parameters, PresupReportDSBuilder.toJRMap(presupuestos, false));
		
		return jasperPrint;
	}
	
	/**
	 * Crea el titulo para un reporte por week
	 * 
	 * @param week
	 * @param year
	 * @return
	 */
	public String createPresupuestoTitle(long nroPpto){
				
		StringBuffer sb = new StringBuffer();
		
		sb.append("PRESUPUESTO N°: ");
		sb.append(nroPpto);
		
		return sb.toString();
	}
	
}
	/**
	 * Prepara el data source a partir de un array de eventos
	 * 
	 * @author hernux
	 */
class PresupReportDSBuilder {
		
	private static String parrafo1 = "Equipamiento:\n"+
	"El presupuesto no incluye los medios de elevación necesarios para colgar el equipamiento.\n"+
	"\nPresentaciones:\n"+
	"El disertante deberá confeccionar las diapositivas de multmedia en Power Point. En caso de utilizar una aplicación que no corresponda a los programas standard de Office, el cliente deberá proveer la aplicación necesaria para realizar su presentación.\n"+
	"\nRecomendamos para el traspaso de las presentaciones utilizar CD Rom o memorias Flash con conexión USB (Pen Drive).\n"+
	"\nLas presentaciones en Power Point tienen que ser compatibles con Oficce 2003. En caso de que sea una versión superior, el cliente la deberá proveer.\n"+
	"\nSugerimos que las Fuentes (Tipografía) no sean menor a tamaño 24.\n"+
	"\nEl material con formato Macintosh debe ser tranformado a PC. Este paso puede obviarse si el disertante trae su propia Mackintosh, la cual se conectará directamente al Proyector LCD si la resolución lo permite.\n"+ 
	"\nLa resolución standard para todo tipo de gráfico deberá ser 1024 x 768. En caso de tener una resolución mayor, solo podrá ser proyectado mediante interfaces previamente contratadas.";
	
	private static String parrafo2 = "\nSi un CD contuviera videos, estos deben estar formateados en Quick Time, Real Player, Windows Media, CODEC 8.0 (DIVX, Macromedia Flash MX2004).\n"+
	"\nEn el caso de solicitar la reproducción de un DVD para videos comerciales, el mismo deberá estar loopeado (sin fin).\n"+
	"\nPruebas:\n"+
	"El disertante deberá probar previamente su material con nuestros técnicos cuarenta y cinco minutos antes del inicio del evento.\n"+
	"\nResponsabilidad:\n"+
	"Las grabaciones del material en Power Point que se proyecten en el evento, se realizarán a pedido por escrito y bajo exclusiva responsabilidad y autorización del Presidente del Comité Organizador, asumiendo éste todos los derechos y obligaciones en forma personal, frente a los expositores y/o miembros participantes del evento y/o cualquier otro tercero por la omisión, reproducción y distribución de dicho material con posterioridad al evento.\n"+
	"\nQueda aclarado que nuestra empresa deslinda todo tipo de responsabilidad al respecto.\n"+
	"\nTodo reclamo con respecto a cuestiones técnica de la grabación, deberá ser realizado dentro de las 72hs. de entregado el material.\n"+
	"\nCongress Rental, no adopta ninguna política de confidencialidad con el objeto de proteger la privacidad de la información de sus clientes. En consecuencia; sus autoridades, empleados, agentes y/o subcontratistas no se hacen responsables de la información confidencial que pudiere manipularse, divulgarse, utilizarse y/o revelarse a otras personas físicas y/o jurídicas,  con motivo y en ocasión de los  eventos para los cuales sus servicios son contratados.\n"+
	"\nInstalado el equipamiento de acuerdo al layout aprobado por el cliente, cualquier tipo de modificación de último momento tendrá un costo adicional que será comunicado en el momento y facturado al finalizar el evento. Estos cambios estarán sujetos a la aprobación del responsable técnico de CRN, quien evaluará la viabilidad de realizarlos a tiempo.";
	
	private static String faltantes = "Cuando la seguridad estuviera a cargo del cliente, el faltante de equipamiento estará a cargo del cliente.\n"+
	"\nLos equipamientos que sean manipulados por el cliente y que sufrieran deterioro o pérdida, tendrán que ser repuestos por el cliente o bien, abonar su precio de mercado.";	
	
	private static double totalFacturado;
		
		/**
		 * Crea el datasource para el reporte de eventos
		 * 
		 * @param ordenes
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public static JRMapArrayDataSource toJRMap(Presupuesto[] ordenes, boolean verCaracteristicas){
			Object[] data = new Object[ordenes.length];		
			
			for (int i=0;i<ordenes.length;i++) {
				Presupuesto presupuesto = ordenes[i];
				Map map = new HashMap();
				
				//map.put("cancelacion",presupuesto.getCancelacion());
				map.put("cliente",presupuesto.getCliente());
				map.put("contacto",presupuesto.getContactoCliente());
				//map.put("firma",presupuesto.getFirma());
				//map.put("footer",presupuesto.getFooter());
				//map.put("formaPago",presupuesto.getFormaPago());
				//map.put("condicionPago",presupuesto.getCondPago());
				map.put("header",presupuesto.getHeader());
				//map.put("instalacion",presupuesto.getInstalacion());
				map.put("telefonosContacto", presupuesto.getTelContacto());
				map.put("titulo", presupuesto.getTitulo());
				//map.put("validez", presupuesto.getValidez());
				map.put("nroPpto", presupuesto.getNroPpto());
				//map.put("seguridad", presupuesto.getSeguridad());
				//map.put("personal", presupuesto.getPersonal());
				//map.put("condReserva", presupuesto.getCondReserva());
				map.put("tipoPresupuesto", presupuesto.getTipoPresupuesto());
				map.put("periodo", presupuesto.getPeriodo());
				//map.put("simbolo", presupuesto.getSimboloMoneda());
				/*map.put("inst", 
						"En el momento de la confirmación del presente presupuesto, se combinará el horario de \ninstalación y desarme.\n"+ 
						"Nota: Este presupuesto podrá sufrir modificaciones dependiendo del horario de instalación \ny/o de pruebas requeridas."); 						
				map.put("armado",						
						"El armado y desarme del evento se coordinará y/o cotizará en el momento de la confirmación del presupuesto.");
				map.put("produccion", "Si Ud. contrató nuestro departamento de producción, el presupuesto incluye la realización de un boceto " +
						"y una modificación. La realización de otro boceto será cotizada aparte.\n"+
						"\n"+
						"Aprobado el proyecto, cualquier modificación será recotizada.");
				map.put("aclaraciones",parrafo1+parrafo2);
				map.put("faltantes",faltantes);*/
				
				try{
					crm.libraries.abm.entities.Presupuesto p = PresupuestosManager.instance().buscarPresupuesto(presupuesto.getNroPpto());
					map.put("lugar", p.getLugarDelEvento().getNombreLugar());
					map.put("evento", p.getNombreDelEvento());
				}
				catch (RemoteException e){
					e.printStackTrace();
				}
				
				map.put("data_source_salas",toJRMap(presupuesto.getSalas(), presupuesto, verCaracteristicas));//.getCotizacion(), presupuesto.getSimboloMoneda(), presupuesto.getPeriodo()));
				
				//map.put("totalSalas", totalFacturado);
				
				totalFacturado = 0.0;				
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
		private static JRMapArrayDataSource toJRMap(PresupuestoSala[] salas, Presupuesto presupuesto, boolean verCaracteristicas){// double cotiz, String simbolo, String periodo){
			Object[] data = new Object[salas.length];
			
			for (int i=0;i<salas.length;i++) {
				PresupuestoSala sala = salas[i];
				
				Map map = new HashMap();
				map.put("salaId",sala.getSalaId());
				map.put("nombreSala",sala.getNombreSala());
				//map.put("fechaFin",presupuesto.getPeriodo());
				map.put("fechaFin",sala.getFechaFin());
				
				map.put("data_source_servicios", toJRMap(sala.getServicios(), presupuesto.getCotizacion(), presupuesto.getSimboloMoneda(), verCaracteristicas));
				//map.put("data_source_opcionales", toJRMap(sala.getOpcionales(), presupuesto.getCotizacion(), presupuesto.getSimboloMoneda()));
				
				double totalSala = 0d; 
				for (int j=0;j<sala.getServicios().length;j++) {
					PresupuestoServicio servicio = sala.getServicios()[j];
					if(servicio.getModalidad() == Long.parseLong(ModalidadContratManagerSEI.MODALIDAD_INTERNO) ||
							servicio.getModalidad() == Long.parseLong(ModalidadContratManagerSEI.MODALIDAD_EXTERNO)){
						totalSala += Math.rint((servicio.getImporte()/presupuesto.getCotizacion())*100)/100;
						totalFacturado += Math.rint((servicio.getImporte()/presupuesto.getCotizacion())*100)/100;
					}
				}
				map.put("totalSala",totalSala);				
				
				map.put("cancelacion",presupuesto.getCancelacion());
				map.put("firma",presupuesto.getFirma());
				map.put("footer",presupuesto.getFooter());
				map.put("formaPago",presupuesto.getFormaPago());
				map.put("condicionPago",presupuesto.getCondPago());
				map.put("instalacion",presupuesto.getInstalacion());
				map.put("telefonosContacto", presupuesto.getTelContacto());
				map.put("validez", presupuesto.getValidez());
				map.put("seguridad", presupuesto.getSeguridad());
				map.put("personal", presupuesto.getPersonal());
				map.put("condReserva", presupuesto.getCondReserva());
				map.put("simbolo", presupuesto.getSimboloMoneda());
				map.put("inst", 
						"En el momento de la confirmación del presente presupuesto, se combinará el horario de \ninstalación y desarme.\n"+ 
				"Nota: Este presupuesto podrá sufrir modificaciones dependiendo del horario de instalación \ny/o de pruebas requeridas."); 						
				map.put("armado",						
				"El armado y desarme del evento se coordinará y/o cotizará en el momento de la confirmación del presupuesto.");
				map.put("produccion", "Si Ud. contrató nuestro departamento de producción, el presupuesto incluye la realización de un boceto " +
						"y una modificación. La realización de otro boceto será cotizada aparte.\n"+
						"\n"+
				"Aprobado el proyecto, cualquier modificación será recotizada.");
				map.put("aclaraciones",parrafo1+parrafo2);
				map.put("faltantes",faltantes);
				
				map.put("totalSalas", totalFacturado);
				
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
		private static JRMapArrayDataSource toJRMap(PresupuestoServicio[] servicios, double cotiz, String simbolo, boolean verCaracteristicas){
			Object[] data = new Object[servicios.length];
			
			for (int i=0;i<servicios.length;i++) {
				PresupuestoServicio servicio = servicios[i];
				Map map = new HashMap();
				
				map.put("servicioId",servicio.getServicioId());
				map.put("cantidad",servicio.getCantidad());
				map.put("servicio",servicio.getServicio());
				map.put("dias",servicio.getDias());
				map.put("familia",servicio.getFamilia());
				map.put("importe", Math.rint((servicio.getImporte()/cotiz)*100)/100);
				map.put("simbolo", simbolo);
				map.put("modalidad", servicio.getModalidad());
				
				if(verCaracteristicas)
					map.put("data_source_serv_detalles", toJRMap(servicio.getCaracteristicas()));
				else 
					map.put("data_source_serv_detalles", null);
				
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
		/*@SuppressWarnings("unchecked")
		private static JRMapArrayDataSource toJRMap(PresupuestoOpcional[] servicios,double cotizacion, String simbolo){
			Object[] data = new Object[servicios.length];
			
			for (int i=0;i<servicios.length;i++) {
				PresupuestoOpcional servicio = servicios[i];
				Map map = new HashMap();
				
				map.put("servicioId",servicio.getServicioId());
				map.put("cantidad",servicio.getCantidad());
				map.put("numeral",i+1);
				map.put("servicio",servicio.getServicio());
				map.put("dias",servicio.getDias());
				map.put("familia",servicio.getFamilia());
				map.put("importe", servicio.getImporte()/cotizacion);
				map.put("simbolo", simbolo);
				
				map.put("data_source_opc_detalles", toJRMap(servicio.getCaracteristicas()));
				
				data[i] = map;
			}
				
			return new JRMapArrayDataSource(data);
		}*/
		
		@SuppressWarnings("unchecked")
		private static JRMapArrayDataSource toJRMap (String[] caracteristicas){
			Object[] data = new Object[caracteristicas.length];
			
			for (int i=0;i<caracteristicas.length;i++) {
				String caracteristica = caracteristicas[i];
				caracteristica = caracteristica.substring(0,caracteristica.length()-1);
				Map map = new HashMap();
				
				map.put("detalle", caracteristica);
				
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
		
}
