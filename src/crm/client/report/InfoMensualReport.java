package crm.client.report;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.apache.wsif.WSIFException;

import crm.libraries.report.InfoMensual;
import crm.libraries.report.InfoMensualCliente;
import crm.services.report.sei.InfoMensualReportSEI;

public class InfoMensualReport  extends CongressReportService implements InfoMensualReportSEI {
	private static final String REPORT_INFO_NAME = "jasper/infoMensual.jasper";
	private static final String REPORT_CLIENTES_NUEVOSC_NAME = "jasper/infoSemanalClientesNuevosC.jasper";
	private static final String REPORT_CLIENTES_NUEVOSNC_NAME = "jasper/infoSemanalClientesNuevosNC.jasper";
	private static final String REPORT_CLIENTES_VIEJOSC_NAME = "jasper/infoSemanalClientesViejosC.jasper";
	private static final String REPORT_CLIENTES_VIEJOSNC_NAME = "jasper/infoSemanalClientesViejosNC.jasper";
	private static final String REPORT_TOP10_EVENTOS = "jasper/top10Eventos.jasper";
	private static final String REPORT_TOP10_FACTURADO = "jasper/top10Facturado.jasper";
	
	private InfoMensualReportSEI stub;
	private static InfoMensualReport instance;
	
	public InfoMensualReport() throws WSIFException{
		super("InfoMensualReportSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("InfoMensual", Class.forName("crm.libraries.report.InfoMensual"));
			addType("InfoMensualCliente", Class.forName("crm.libraries.report.InfoMensualCliente"));			
			
			/*addType("ArrayOfInfoSemanalCliente", Class.forName("crm.client.deserializer.ArrayOfInfoSemanalCliente"));
			addType("ArrayOfEventoSala", Class.forName("crm.client.deserializer.ArrayOfEventoSala"));
			addType("ArrayOfEventoServicio", Class.forName("crm.client.deserializer.ArrayOfEventoServicio"));
			addType("ArrayOfEventoAccesorio", Class.forName("crm.client.deserializer.ArrayOfEventoAccesorio"));*/
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (InfoMensualReportSEI)service.getStub(InfoMensualReportSEI.class);
	}

	

	public static InfoMensualReport instance() {
		try {
			if (instance == null) {
				instance = new InfoMensualReport();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

	public InfoMensual findByMonth(int month, int year) throws RemoteException {
		return stub.findByMonth(month, year);
	}
	
	public JasperPrint createWeekReport(int month, int year) throws RemoteException, JRException {
		InfoMensual eventos = findByMonth(month,year);
		
		return createReport(eventos, month, year);
	}
	
	@SuppressWarnings("unchecked")
	private JasperPrint createReport(InfoMensual eventos, int month, int year) throws RemoteException, JRException {

		// 1- cargar los reporte desde los .jasper			
		JasperReport infoReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_INFO_NAME));
		JasperReport clienteNCReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_CLIENTES_NUEVOSC_NAME));
		JasperReport clienteNNCReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_CLIENTES_NUEVOSNC_NAME));
		JasperReport clienteVCReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_CLIENTES_VIEJOSC_NAME));
		JasperReport clienteVNCReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_CLIENTES_VIEJOSNC_NAME));
		JasperReport top10Eventos = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_TOP10_EVENTOS));
		JasperReport top10Facturado = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_TOP10_FACTURADO));
		
		// 2- create a map of parameters to pass to the report.
		Map parameters = new HashMap();
		parameters.put("SUBREPORT_NCLIENTES_CONFIRMADOS",clienteNCReport);
		parameters.put("SUBREPORT_NCLIENTES_NOCONFIRMADOS",clienteNNCReport);
		parameters.put("SUBREPORT_VCLIENTES_CONFIRMADOS", clienteVCReport);
		parameters.put("SUBREPORT_VCLIENTES_NOCONFIRMADOS", clienteVNCReport);
		parameters.put("SUBREPORT_TOP10_EVENTOS", top10Eventos);
		parameters.put("SUBREPORT_TOP10_FACTURADO", top10Facturado);
		
		parameters.put("REPORT_MONTH",new Integer(month));
		//parameters.put("REPORT_SUBTITLE",subTitle);
		parameters.put("REPORT_YEAR",new Integer(year));
		parameters.put("REPORT_UPDATE",new Date());
		//parameters.put("REPORT_IMAGE_URL",REPORT_IMAGE_URL);
		
		// 3- create JasperPrint using fillReport() method
		JasperPrint jasperPrint = JasperFillManager.fillReport(infoReport, parameters, InfoMensualDSBuilder.toJRMap(eventos));
		
		return jasperPrint;
	}
}
class InfoMensualDSBuilder{
	
	@SuppressWarnings("unchecked")
	public static JRMapArrayDataSource toJRMap(InfoMensual eventos){
		Object[] data = new Object[1];
		
			InfoMensual evento = eventos;
			Map map = new HashMap();
			
			map.put("cantidadTotal",evento.getCantidadTotal());
			map.put("cantidadConfirmados",evento.getCantidadConfirmados());
			map.put("cantidadNoConfirmados",evento.getCantidadNoConfirmados());
			map.put("totalConfirmados",evento.getTotalConfirmados());
			map.put("totalNoConfirmados",evento.getTotalNoConfirmados());
			
			map.put("data_source_nclientes_confirmados",toJRMap(evento.getClientesNuevosConfirmados()));
			map.put("data_source_nclientes_noconfirmados",toJRMap(evento.getClientesNuevosNoConfirmados()));
			map.put("data_source_vclientes_confirmados",toJRMap(evento.getClientesViejosConfirmados()));
			map.put("data_source_vclientes_noconfirmados",toJRMap(evento.getClientesViejosNoConfirmados()));
			
			map.put("data_source_top10_eventos",toJRMapTop(evento.getTop10ClientesCantEventos()));
			map.put("data_source_top10_facturado",toJRMapTop(evento.getTop10ClientesFacturado()));
			
			data[0] = map;
		
		return new JRMapArrayDataSource(data);
	}
	
	@SuppressWarnings("unchecked")
	public static JRMapArrayDataSource toJRMap(InfoMensualCliente[] clientes){
		Object[] data = new Object[clientes.length];
		
		for (int i=0;i<clientes.length;i++) {
			InfoMensualCliente cliente = clientes[i];
			Map map = new HashMap();
			if(cliente != null){
				map.put("nombre",cliente.getNombre());
				map.put("facturado",cliente.getFacturacion());
				map.put("cantidad",cliente.getCantClientes());
			}
			
			data[i] = map;
		}
		
		return new JRMapArrayDataSource(data);
	}
	
	@SuppressWarnings("unchecked")
	public static JRMapArrayDataSource toJRMapTop(InfoMensualCliente[] clientes){
		Object[] data = new Object[clientes.length];
		
		for (int i=0;i<clientes.length;i++) {
			InfoMensualCliente cliente = clientes[i];
			Map map = new HashMap();
			if(cliente != null){
				map.put("nombre",(i+1)+"º)  "+cliente.getNombre());
				map.put("facturado",cliente.getFacturacion());
				map.put("cantidad",cliente.getCantClientes());
			}
			
			data[i] = map;
		}
		
		return new JRMapArrayDataSource(data);
	}
}
