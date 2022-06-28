package crm.gui.pantalla.reportes;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import crm.client.report.ListaPreciosReport;
import crm.client.util.DateConverter;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesItem;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesServiciosItem;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesServiciosTableModel;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesTableModel;
import crm.libraries.report.SubcontratadosGerencia;

public class ReportBuilder {
	
	private static final String REPORT_PPTOS = "jasper/presupuestos_report.jasper";
	private static final String REPORT_SERVICE = "jasper/servicios_report.jasper";
	private double totalFacturado;	
	
	public void viewReport(BuscadorReportesTableModel eventos, String subtitle){
		try {
			JasperPrint jp = createReportPresupuesto(eventos,"Presupuestos",subtitle);

			JasperViewer jv = new JasperViewer(jp,false);
			//jv.setAlwaysOnTop(true);
			jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
			jv.setTitle("Presupuestos - Vista Previa");
			jv.setVisible(true);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de presupuestos", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de presupuestos", e);
		}
		
	}
	
	public void viewReportServicios(BuscadorReportesServiciosTableModel eventos, String subtitle){
		try {
			JasperPrint jp = createReportServicios(eventos,"Servicios",subtitle);

			JasperViewer jv = new JasperViewer(jp,false);
			//jv.setAlwaysOnTop(true);
			jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
			jv.setTitle("Servicios - Vista Previa");
			jv.setVisible(true);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de servicios", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de servicios", e);
		}
		
	}
	
	public void previewListaPreciosReport(String fecha){
		JasperPrint jp = createListaPreciosReport(fecha);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle("Lista de precios - Vista Previa");
		jv.setVisible(true);
	}
	
	private JasperPrint createReportPresupuesto(BuscadorReportesTableModel eventos, String title, String subTitle) throws RemoteException, JRException {

		// 1- cargar los reporte desde los .jasper			
		JasperReport eventReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_PPTOS));

		// 2- create a map of parameters to pass to the report.
		Map parameters = new HashMap();

		parameters.put("REPORT_TITLE",title);
		parameters.put("REPORT_SUBTITLE",subTitle);
		parameters.put("REPORT_UPDATE",new Date());
		parameters.put("REPORT_IMAGE_URL",Main.class.getResource("imagenes/logo-crn_blancoPpto.png").toString());

		JasperPrint jasperPrint = JasperFillManager.fillReport(eventReport, parameters,presupuestosToJRMap(eventos));

		return jasperPrint;
	}
	
	private JasperPrint createReportServicios(BuscadorReportesServiciosTableModel eventos, String title, String subTitle) throws RemoteException, JRException {

		// 1- cargar los reporte desde los .jasper			
		JasperReport eventReport = (JasperReport)JRLoader.loadObject(getClass().getResourceAsStream(REPORT_SERVICE));

		// 2- create a map of parameters to pass to the report.
		Map parameters = new HashMap();

		parameters.put("REPORT_TITLE",title);
		parameters.put("REPORT_SUBTITLE",subTitle);
		parameters.put("REPORT_UPDATE",new Date());
		parameters.put("REPORT_IMAGE_URL",Main.class.getResource("imagenes/logo-crn_blancoPpto.png").toString());

		JasperPrint jasperPrint = JasperFillManager.fillReport(eventReport, parameters,serviciosToJRMap(eventos));

		return jasperPrint;
	}
	
	public JRMapArrayDataSource presupuestosToJRMap(BuscadorReportesTableModel eventos){
		Object[] data = new Object[eventos.getRowCount()];
		
		for (int i=0;i<eventos.getRowCount();i++) {
			BuscadorReportesItem evento = eventos.getRow(i);
			Map map = new HashMap();
			
			map.put("cliente",evento.getCliente());
			map.put("evento",evento.getNombreEvento());
			map.put("orden",evento.getNumeroPpto());
			map.put("lugar",evento.getLugar());
			map.put("comercial",evento.getVendedor());
			map.put("totalEvento",evento.getTotal());
			map.put("totalPptos",eventos.getRowCount());
			try {
				map.put("desde",DateConverter.convertStringToDate(evento.getFechaInicio(), "yyyy-MM-dd"));
				map.put("hasta",DateConverter.convertStringToDate(evento.getFechaFinal(), "yyyy-MM-dd"));
				map.put("creado",DateConverter.convertStringToDate(evento.getFechaCreacion(), "yyyy-MM-dd"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			map.put("estadoEvento",evento.getEstado());
			
			totalFacturado += evento.getTotal();
			
			if(i == eventos.getRowCount()-1){
				map.put("total",totalFacturado);
				totalFacturado = 0.0;
			}
			
			
			data[i] = map;
			
		}
		return new JRMapArrayDataSource(data);
	}
	
	private static double totalVenta;
	private static double totalCompra;
	
	public JRMapArrayDataSource serviciosToJRMap(BuscadorReportesServiciosTableModel eventos){
		Object[] data = new Object[eventos.getRowCount()];
		
		for (int i=0;i<eventos.getRowCount();i++) {
			BuscadorReportesServiciosItem evento = eventos.getRow(i);
			Map map = new HashMap();
			
			map.put("cliente",evento.getCliente());
			map.put("orden",evento.getNumeroPpto());
			map.put("comercial",evento.getVendedor());
			try {
				map.put("desde",DateConverter.convertStringToDate(evento.getFechaInicio(), "yyyy-MM-dd"));
				map.put("hasta",DateConverter.convertStringToDate(evento.getFechaFin(), "yyyy-MM-dd"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			map.put("servicio",evento.getServicio());
			map.put("cant",evento.getCantidad());
			map.put("precioVenta",evento.getTotalServicio());
			
			map.put("precioCompra",evento.getTotalCompra());
			map.put("totalPptos",eventos.getRowCount());
			totalVenta += evento.getTotalServicio();
			if(evento.getTotalCompra() != null)
				totalCompra += evento.getTotalCompra();
			
			if(i == eventos.getRowCount()-1){
				map.put("totalVenta",totalVenta);
				totalVenta = 0.0;
				map.put("totalCompra",totalCompra);
				totalCompra = 0.0;
			}
			
			
			data[i] = map;
			
		}
		return new JRMapArrayDataSource(data);
	}
	
	public JasperPrint createListaPreciosReport(String fecha){
		try {			
			return ListaPreciosReport.instance().createListaPreciosReport(fecha);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
