package crm.client.report;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.wsif.WSIFException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import crm.gui.Main;
import crm.libraries.report.ListaPrecios;
import crm.libraries.report.ListaPreciosFamilias;
import crm.libraries.report.ListaPreciosServicios;
import crm.services.report.sei.ListaPreciosReportSEI;

public class ListaPreciosReport extends CongressReportService implements ListaPreciosReportSEI{
	private static final String REPORT_LP_NAME = "jasper/listaPrecios.jasper";
	private static final String REPORT_LP_SERVICIOS_NAME = "jasper/listaPreciosServicios.jasper";
	private static final String REPORT_LP_FAMILIAS_NAME = "jasper/listaPreciosFamilias.jasper";
	
	private ListaPreciosReportSEI stub;
	private static ListaPreciosReport instance;
	
	public ListaPreciosReport() throws WSIFException{
		super("ListaPreciosReportSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("ListaPrecios", Class.forName("crm.libraries.report.ListaPrecios"));
			addType("ListaPreciosFamilias", Class.forName("crm.libraries.report.ListaPreciosFamilias"));
			addType("ListaPreciosServicios", Class.forName("crm.libraries.report.ListaPreciosServicios"));						

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (ListaPreciosReportSEI)service.getStub(ListaPreciosReportSEI.class);
	}

	public static ListaPreciosReport instance() {
		try {
			if (instance == null) {
				instance = new ListaPreciosReport();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public JasperPrint createListaPreciosReport(String fecha) throws RemoteException, JRException {

		ListaPrecios listas = findByFecha(fecha);
		String title = "Lista de precios";
		
		return createReport(listas, title);
	}
	
	@SuppressWarnings("unchecked")
	private JasperPrint createReport(ListaPrecios listas, String title) throws RemoteException, JRException {

		// 1- cargar los reporte desde los .jasper			
		JasperReport listaPreciosReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_LP_NAME));
		JasperReport listaPreciosFamiliasReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_LP_FAMILIAS_NAME));
		JasperReport listaPreciosServiciosReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_LP_SERVICIOS_NAME));		
		
		// 2- create a map of parameters to pass to the report.
		Map parameters = new HashMap();
		parameters.put("SUBREPORT_FAMILIAS",listaPreciosFamiliasReport);
		parameters.put("SUBREPORT_SERVICIOS",listaPreciosServiciosReport);
		
		parameters.put("REPORT_TITLE",title);
		parameters.put("REPORT_UPDATE",new Date());
		parameters.put("REPORT_IMAGE_URL",Main.class.getResource("imagenes/logo-crn_blancoPpto.png").toString());
		
		// 3- create JasperPrint using fillReport() method
		JasperPrint jasperPrint = JasperFillManager.fillReport(listaPreciosReport, parameters, ListasPreciosDSBuilder.toJRMap(listas));
		
		return jasperPrint;
	}

	public ListaPrecios findByMes(int mes) throws RemoteException {
		return stub.findByMes(mes);
	}
	
	public ListaPrecios findByFecha(String fecha) throws RemoteException{
		return stub.findByFecha(fecha);
	}
}

class ListasPreciosDSBuilder{
		
	@SuppressWarnings("unchecked")
	public static JRMapArrayDataSource toJRMap(ListaPrecios listas){
		Object[] data = new Object[1];
		
			ListaPrecios lista = listas;
			Map map = new HashMap();
			
			map.put("mes", lista.getMes());	
			map.put("descuentoMes", 0);
			map.put("data_source_familias", toJRMap(lista.getFamilias()));
			
			data[0] = map;
		
		return new JRMapArrayDataSource(data);
	}
	
	@SuppressWarnings("unchecked")
	private static JRMapArrayDataSource toJRMap(ListaPreciosFamilias[] familias){
		Object[] data = new Object[familias.length];
		
		for (int i=0;i<familias.length;i++) {
			ListaPreciosFamilias familia = familias[i];
			Map map = new HashMap();
			
			map.put("familia",familia.getDescripcion());
			map.put("data_source_servicios", toJRMap(familia.getServicios()));
			
			data[i] = map;
		}
		
		return new JRMapArrayDataSource(data);
	}
	
	@SuppressWarnings("unchecked")
	private static JRMapArrayDataSource toJRMap(ListaPreciosServicios[] servicios){
		Object[] data = new Object[servicios.length];
		
		for (int i=0;i<servicios.length;i++) {
			ListaPreciosServicios servicio = servicios[i];
			Map map = new HashMap();
			
			map.put("servicio",servicio.getServicio());
			map.put("codigo", servicio.getCodigoServicio());
			map.put("dia1", servicio.getUnDia());
			map.put("dia2", servicio.getDosDias());
			map.put("dia3", servicio.getTresDias());
			map.put("dia4", servicio.getCuatroDias());
			map.put("dia5", servicio.getCincoDias());
			
			data[i] = map;
		}
		
		return new JRMapArrayDataSource(data);
	}
}
