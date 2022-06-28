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

import crm.client.managers.EstadoEventoManager;
import crm.client.managers.PresupuestosManager;
import crm.client.util.DateConverter;
import crm.gui.Main;
import crm.libraries.report.Adelanto;
import crm.services.report.sei.AdelantoReportSEI;

public class AdelantoReport extends CongressReportService implements AdelantoReportSEI {
	private static final String REPORT_OF_NAME = "jasper/adelanto.jasper";
	
	private AdelantoReportSEI stub;
	private static AdelantoReport instance;
	private Date fechaEmision;	
	
	public AdelantoReport() throws WSIFException{
		super("AdelantoReportSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Adelanto", Class.forName("crm.libraries.report.Adelanto"));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (AdelantoReportSEI)service.getStub(AdelantoReportSEI.class);
	}

	public static AdelantoReport instance() {
		try {
			if (instance == null) {
				instance = new AdelantoReport();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public void sendOFByEmail (long nroPpto, String vendedorName) throws RemoteException{
		stub.sendOFByEmail(nroPpto, vendedorName);
	}
	
	public boolean sendOFByEmail2(long nroPpto, String usuarioId, String destinatario) throws RemoteException{
		return stub.sendOFByEmail2(nroPpto, usuarioId, destinatario);
	}
	
	public Adelanto[] findByNroPpto(long nroPpto) throws RemoteException {
		return stub.findByNroPpto(nroPpto);
	}
	
	public void savePdfFile(long nroPpto) throws RemoteException{
		stub.savePdfFile(nroPpto);
	}
	
	/**
	 * Crea un reporte de orden de facturación.
	 * 
	 * @param nroPpto
	 * @return
	 * @throws RemoteException
	 * @throws JRException
	 */
	public JasperPrint createOFReport(long nroPpto) throws RemoteException, JRException {

		Adelanto[] ordenesFacturacion = findByNroPpto(nroPpto);
		String subTitle = createOFTitle(nroPpto);
		String title = getEstadoOS(nroPpto);
		
		return createReport(ordenesFacturacion, title, subTitle);
	}
	
	public String getEstadoOS (long nroPpto){
		try{
			String fechaOSSt = PresupuestosManager.instance().getFechaByNroPptoAndStateAndUser(nroPpto, 
					(Integer.valueOf(EstadoEventoManager.CODIGO_ESTADO_ADELANTO)).intValue());
			Date fechaOS = AdelantoReportDSBuilder.getDatefromString(fechaOSSt);
			if (PresupuestosManager.instance().isPptoCancelado(nroPpto)){
				String fechaCancSt = PresupuestosManager.instance().getFechaByNroPptoAndStateAndUser(nroPpto, 
						(Integer.valueOf(EstadoEventoManager.CODIGO_ESTADO_CANCELADO)).intValue());
				Date fechaCanc = AdelantoReportDSBuilder.getDatefromString(fechaCancSt);
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
	
	public Date getMaximaFechaActualizacion(long nroPpto, int estado) throws RemoteException{
		String fecha = PresupuestosManager.instance().getMaxFechaByNroPptoAndState(nroPpto, estado);
		return AdelantoReportDSBuilder.getDatefromString(fecha);
	}
	
	/**
	 * Crea un reporte de eventos
	 * 
	 * @param ordenesFacturacion el listado de eventos que se iterará
	 * @param subTitle el titulo que recibirá el reporte, puede ser week o day
	 * @param year el año del reporte
	 * @return
	 * @throws RemoteException
	 * @throws JRException
	 */
	@SuppressWarnings("unchecked")
	private JasperPrint createReport(Adelanto[] ordenesFacturacion, String title, String subTitle) throws RemoteException, JRException {

		// 1- cargar los reporte desde los .jasper			
		JasperReport ofReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_OF_NAME));	
		
		// 2- create a map of parameters to pass to the report.
		Map parameters = new HashMap();
		
		parameters.put("REPORT_TITLE",title);
		parameters.put("REPORT_SUBTITLE", subTitle);
		parameters.put("REPORT_UPDATE",new Date());
		parameters.put("REPORT_IMAGE_URL",Main.class.getResource("imagenes/logo-crn_blancoPpto.png").toString());
		parameters.put("REPORT_DATE", fechaEmision);
		
		// 3- create JasperPrint using fillReport() method
		JasperPrint jasperPrint = JasperFillManager.fillReport(ofReport, parameters, AdelantoReportDSBuilder.toJRMap(ordenesFacturacion));
		
		return jasperPrint;
	}
	
	/**
	 * Crea el titulo para un reporte por week
	 * 
	 * @param week
	 * @param year
	 * @return
	 */
	public String createOFTitle(long nroPpto){
				
		StringBuffer sb = new StringBuffer();
		
		sb.append("ORDEN DE FACTURACION DEL ADELANTO N°: ");
		sb.append(nroPpto);
		
		return sb.toString();
	}
	
	public Date getFechaEmision(){
		return fechaEmision;
	}
	
	public String getStFechaEmision(){
		return DateConverter.convertDateToString(fechaEmision, "yyyy-MM-dd HH:mm:ss");
	}
	
}
/**
 * Prepara el data source a partir de un array de eventos
 * 
 * @author hernux
 */
class AdelantoReportDSBuilder {
	
	/**
	 * Crea el datasource para el reporte de eventos
	 * 
	 * @param ordenes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JRMapArrayDataSource toJRMap(Adelanto[] ordenes){
		Object[] data = new Object[ordenes.length];		
		
		for (int i=0;i<ordenes.length;i++) {
			Adelanto ordenFacturacion = ordenes[i];
			Map map = new HashMap();
			
			map.put("razonSocial",ordenFacturacion.getRazonSocial());
			map.put("nombreEvento",ordenFacturacion.getNombreEvento());
			map.put("numeroOrden",ordenFacturacion.getOrdenFacturacion());
			map.put("lugarEvento",ordenFacturacion.getLugarEvento());
			map.put("fechaInicialEvento",getDatefromString((ordenFacturacion.getFechaInicialEvento())));
			map.put("cantidadSalas",ordenFacturacion.getCantidadSalas());
			map.put("unidad",ordenFacturacion.getUnidad());
			map.put("vendedor",ordenFacturacion.getVendedor());
			map.put("domicilioLugar", ordenFacturacion.getDomicilioLugar());
			map.put("fechaFinalEvento", getDatefromString((ordenFacturacion.getFechaFinalEvento())));
			map.put("telefonosLugar", ordenFacturacion.getTelefonosLugar());
			map.put("contactoCliente", ordenFacturacion.getContactoCliente());
			map.put("nroWeek", calcularWeek(getDatefromString((ordenFacturacion.getFechaInicialEvento()))));
			map.put("razonSocial", ordenFacturacion.getRazonSocial());
			map.put("cuit", ordenFacturacion.getCuit());
			map.put("condIva", ordenFacturacion.getCondIva());
			map.put("domicilioLegal", ordenFacturacion.getDomicilioLegal());
			map.put("condicionPago", ordenFacturacion.getCondicionPago());
			map.put("modoPago", ordenFacturacion.getMedioPago());
			map.put("responsablePago", ordenFacturacion.getResponsablePago());
			map.put("telResponsablePago", ordenFacturacion.getTelefonoRespPago());
			map.put("domicilioPago", ordenFacturacion.getDomicilioPago());
			map.put("diaHoraPago", ordenFacturacion.getDiaHoraPago());
			map.put("codigoProvCrn", ordenFacturacion.getCodProvCrn());
			map.put("nombreFantasia", ordenFacturacion.getNombreFantasia());
			map.put("adelanto", ordenFacturacion.getAdelanto());
			map.put("observaciones", ordenFacturacion.getObservaciones());
			map.put("domicilioFactura", ordenFacturacion.getDomicilioFactura());
			
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
