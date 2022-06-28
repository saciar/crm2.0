package crm.client.report;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.wsif.WSIFException;

import crm.libraries.report.InfoSemanal;
import crm.libraries.report.InfoSemanalCliente;
import crm.services.report.sei.InfoSemanalReportSEI;;

public class InfoSemanalReport extends CongressReportService implements InfoSemanalReportSEI {
	private static final String REPORT_INFO_NAME = "jasper/infoSemanal.jasper";
	private static final String REPORT_CLIENTES_NUEVOSC_NAME = "jasper/infoSemanalClientesNuevosC.jasper";
	private static final String REPORT_CLIENTES_NUEVOSNC_NAME = "jasper/infoSemanalClientesNuevosNC.jasper";
	private static final String REPORT_CLIENTES_VIEJOSC_NAME = "jasper/infoSemanalClientesViejosC.jasper";
	private static final String REPORT_CLIENTES_VIEJOSNC_NAME = "jasper/infoSemanalClientesViejosNC.jasper";
	
	private InfoSemanalReportSEI stub;
	private static InfoSemanalReport instance;
	
	public InfoSemanalReport() throws WSIFException{
		super("InfoSemanalReportSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("InfoSemanal", Class.forName("crm.libraries.report.InfoSemanal"));
			addType("InfoSemanalCliente", Class.forName("crm.libraries.report.InfoSemanalCliente"));			
			
			/*addType("ArrayOfInfoSemanalCliente", Class.forName("crm.client.deserializer.ArrayOfInfoSemanalCliente"));
			addType("ArrayOfEventoSala", Class.forName("crm.client.deserializer.ArrayOfEventoSala"));
			addType("ArrayOfEventoServicio", Class.forName("crm.client.deserializer.ArrayOfEventoServicio"));
			addType("ArrayOfEventoAccesorio", Class.forName("crm.client.deserializer.ArrayOfEventoAccesorio"));*/
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (InfoSemanalReportSEI)service.getStub(InfoSemanalReportSEI.class);
	}

	

	public static InfoSemanalReport instance() {
		try {
			if (instance == null) {
				instance = new InfoSemanalReport();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

	public InfoSemanal findByWeek(int week, int year) throws RemoteException {
		return stub.findByWeek(week, year);
	}
	
	public JasperPrint createWeekReport(int week, int year) throws RemoteException, JRException {
		InfoSemanal eventos = findByWeek(week,year);
		
		return createReport(eventos, week);
	}
	
	@SuppressWarnings("unchecked")
	private JasperPrint createReport(InfoSemanal eventos, int week) throws RemoteException, JRException {

		// 1- cargar los reporte desde los .jasper			
		JasperReport infoReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_INFO_NAME));
		JasperReport clienteNCReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_CLIENTES_NUEVOSC_NAME));
		JasperReport clienteNNCReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_CLIENTES_NUEVOSNC_NAME));
		JasperReport clienteVCReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_CLIENTES_VIEJOSC_NAME));
		JasperReport clienteVNCReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_CLIENTES_VIEJOSNC_NAME));
		
		// 2- create a map of parameters to pass to the report.
		Map parameters = new HashMap();
		parameters.put("SUBREPORT_NCLIENTES_CONFIRMADOS",clienteNCReport);
		parameters.put("SUBREPORT_NCLIENTES_NOCONFIRMADOS",clienteNNCReport);
		parameters.put("SUBREPORT_VCLIENTES_CONFIRMADOS", clienteVCReport);
		parameters.put("SUBREPORT_VCLIENTES_NOCONFIRMADOS", clienteVNCReport);
		
		parameters.put("REPORT_WEEK",new Integer(week));
		//parameters.put("REPORT_SUBTITLE",subTitle);
		//parameters.put("REPORT_YEAR",new Integer(year));
		parameters.put("REPORT_UPDATE",new Date());
		//parameters.put("REPORT_IMAGE_URL",REPORT_IMAGE_URL);
		
		// 3- create JasperPrint using fillReport() method
		JasperPrint jasperPrint = JasperFillManager.fillReport(infoReport, parameters, InfoSemanalDSBuilder.toJRMap(eventos));
		
		return jasperPrint;
	}
}
class InfoSemanalDSBuilder{
	
	@SuppressWarnings("unchecked")
	public static JRMapArrayDataSource toJRMap(InfoSemanal eventos){
		Object[] data = new Object[1];
		
			InfoSemanal evento = eventos;
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
			data[0] = map;
		
		return new JRMapArrayDataSource(data);
	}
	
	@SuppressWarnings("unchecked")
	public static JRMapArrayDataSource toJRMap(InfoSemanalCliente[] clientes){
		Object[] data = new Object[clientes.length];
		
		for (int i=0;i<clientes.length;i++) {
			InfoSemanalCliente cliente = clientes[i];
			Map map = new HashMap();
			if(cliente != null)
				map.put("nombre",cliente.getNombre());
			//else map.put("nombre",null);
			
			data[i] = map;
		}
		
		return new JRMapArrayDataSource(data);
	}
}
