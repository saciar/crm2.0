package crm.client.report;

import java.rmi.RemoteException;

import javax.swing.JFrame;

import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.DatePanel;
import crm.gui.components.JXDatePicker;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.view.JasperViewer;

public class ReportBuilder {
	public static final int REPORT_TYPE_WEEK = 1;
	public static final int REPORT_TYPE_DAILY = 2;
	
	public static final int EXPORT_TYPE_PDF = 1;
	public static final int EXPORT_TYPE_HTML = 2;
	public static final int EXPORT_TYPE_RTF = 3;
	public static final int EXPORT_TYPE_TXT = 4;
	
	public static int REPORT_PPTO_GERENCIA =0;
	public static int REPORT_PPTO_GERENCIA_CREADO =1;
	public static int REPORT_PPTO_GERENCIA_ESTADO =2;
	public static int REPORT_PPTO_GERENCIA_ESTADO_CREADO =3;
	public static int REPORT_PPTO_GERENCIA_LUGAR =4;
	public static int REPORT_PPTO_GERENCIA_LUGAR_CREADO =5;
	public static int REPORT_PPTO_GERENCIA_LUGAR_ESTADO =6;
	public static int REPORT_PPTO_GERENCIA_LUGAR_ESTADO_CREADO =7;	
	public static int REPORT_PPTO_GERENCIA_UC =8;
	public static int REPORT_PPTO_GERENCIA_UC_CREADO =9;	
	public static int REPORT_PPTO_GERENCIA_UC_ESTADO =10;
	public static int REPORT_PPTO_GERENCIA_UC_ESTADO_CREADO =11;	
	public static int REPORT_PPTO_GERENCIA_UC_LUGAR =12;
	public static int REPORT_PPTO_GERENCIA_UC_LUGAR_CREADO =13;	
	public static int REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO =14;
	public static int REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO_CREADO =15;	
	public static int REPORT_PPTO_GERENCIA_VEN =16;
	public static int REPORT_PPTO_GERENCIA_VEN_CREADO =17;	
	public static int REPORT_PPTO_GERENCIA_VEN_ESTADO =18;
	public static int REPORT_PPTO_GERENCIA_VEN_ESTADO_CREADO =19;	
	public static int REPORT_PPTO_GERENCIA_VEN_LUGAR =20;
	public static int REPORT_PPTO_GERENCIA_VEN_LUGAR_CREADO =21;
	public static int REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO =22;
	public static int REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO_CREADO =23;
	public static int REPORT_PPTO_GERENCIA_VEN_UC =24;
	public static int REPORT_PPTO_GERENCIA_VEN_UC_CREADO =25;	
	public static int REPORT_PPTO_GERENCIA_VEN_UC_ESTADO =26;
	public static int REPORT_PPTO_GERENCIA_VEN_UC_ESTADO_CREADO =27;
	public static int REPORT_PPTO_GERENCIA_VEN_UC_LUGAR =28;
	public static int REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_CREADO =29;
	public static int REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO =30;
	public static int REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO_CREADO =31;
	
	public static int REPORT_PPTO_GERENCIA_SIMPLE =32;
	public static int REPORT_PPTO_GERENCIA_CREADO_SIMPLE =33;
	public static int REPORT_PPTO_GERENCIA_ESTADO_SIMPLE =34;
	public static int REPORT_PPTO_GERENCIA_ESTADO_CREADO_SIMPLE =35;
	public static int REPORT_PPTO_GERENCIA_LUGAR_SIMPLE =36;
	public static int REPORT_PPTO_GERENCIA_LUGAR_CREADO_SIMPLE =37;
	public static int REPORT_PPTO_GERENCIA_LUGAR_ESTADO_SIMPLE =38;
	public static int REPORT_PPTO_GERENCIA_LUGAR_ESTADO_CREADO_SIMPLE =39;	
	public static int REPORT_PPTO_GERENCIA_UC_SIMPLE =40;
	public static int REPORT_PPTO_GERENCIA_UC_CREADO_SIMPLE =41;	
	public static int REPORT_PPTO_GERENCIA_UC_ESTADO_SIMPLE =42;
	public static int REPORT_PPTO_GERENCIA_UC_ESTADO_CREADO_SIMPLE =43;	
	public static int REPORT_PPTO_GERENCIA_UC_LUGAR_SIMPLE =44;
	public static int REPORT_PPTO_GERENCIA_UC_LUGAR_CREADO_SIMPLE =45;	
	public static int REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO_SIMPLE =46;
	public static int REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO_CREADO_SIMPLE =47;	
	public static int REPORT_PPTO_GERENCIA_VEN_SIMPLE =48;
	public static int REPORT_PPTO_GERENCIA_VEN_CREADO_SIMPLE =49;	
	public static int REPORT_PPTO_GERENCIA_VEN_ESTADO_SIMPLE =50;
	public static int REPORT_PPTO_GERENCIA_VEN_ESTADO_CREADO_SIMPLE =51;	
	public static int REPORT_PPTO_GERENCIA_VEN_LUGAR_SIMPLE =52;
	public static int REPORT_PPTO_GERENCIA_VEN_LUGAR_CREADO_SIMPLE =53;
	public static int REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO_SIMPLE=54;
	public static int REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO_CREADO_SIMPLE =55;
	public static int REPORT_PPTO_GERENCIA_VEN_UC_SIMPLE =56;
	public static int REPORT_PPTO_GERENCIA_VEN_UC_CREADO_SIMPLE =57;	
	public static int REPORT_PPTO_GERENCIA_VEN_UC_ESTADO_SIMPLE =58;
	public static int REPORT_PPTO_GERENCIA_VEN_UC_ESTADO_CREADO_SIMPLE =59;
	public static int REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_SIMPLE =60;
	public static int REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_CREADO_SIMPLE =61;
	public static int REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO_SIMPLE =62;
	public static int REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO_CREADO_SIMPLE =63;
	
	/**
	 * Crea un reporte por una orden de servicio
	 * 
	 * @param nroPpto numero de presupuesto de la oreden de servicio
	 * @return JasperPrint con el reporte creado
	 */
	public static JasperPrint createListaPreciosReport(String fecha){
		try {			
			return ListaPreciosReport.instance().createListaPreciosReport(fecha);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Crea un reporte por una orden de servicio
	 * 
	 * @param nroPpto numero de presupuesto de la oreden de servicio
	 * @return JasperPrint con el reporte creado
	 */
	public static JasperPrint createOSReport(long nroPpto){
		try {			
			return OrdenServicioReport.instance().createOSReport(nroPpto);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * Crea un reporte por un presupuesto
	 * 
	 * @param idCancelacion codigo de cancelacion del presupuesto
	 * @param idHeader codigo de header del reporte
	 * @param idFooter codigo de footer del reporte
	 * @param idInstalacion codigo de instalacion
	 * @param idValidez codigo de validez del presupuesto
	 * @param idFormaPago codigo de forma de pago
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 * @return JasperPrint con el reporte creado
	 */
	public static JasperPrint createPresupuestoReport(long nroPpto, long idCancelacion, long idHeader, long idFooter, long idInstalacion,
			long idValidez, long idFormaPago, long idCondPago, long idFirma, long idSeguridad, long idPersonal, long idCondReserva, long idTipoPresupuesto, 
			long idPeriodo, long idMoneda, double cotizacion){
		try {
			return PresupuestoReport.instance().createPresupuestoReport(nroPpto, idCancelacion, idHeader, idFooter, idInstalacion,
					idValidez, idFormaPago, idCondPago, idFirma, idSeguridad, idPersonal, idCondReserva, idTipoPresupuesto, idPeriodo, idMoneda, cotizacion);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Crea un reporte por un presupuesto sin las descriciones detalladas de los servicios
	 * 
	 * @param idCancelacion codigo de cancelacion del presupuesto
	 * @param idHeader codigo de header del reporte
	 * @param idFooter codigo de footer del reporte
	 * @param idInstalacion codigo de instalacion
	 * @param idValidez codigo de validez del presupuesto
	 * @param idFormaPago codigo de forma de pago
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 * @return JasperPrint con el reporte creado
	 */
	public static JasperPrint createPresupuestoResumReport(long nroPpto, long idCancelacion, long idHeader, long idFooter, long idInstalacion,
			long idValidez, long idFormaPago, long idCondPago, long idFirma, long idSeguridad, long idPersonal, long idCondReserva, long idTipoPresupuesto, 
			long idPeriodo, long idMoneda, double cotizacion){
		try {
			return PresupuestoReport.instance().createPresupuestoResumReport(nroPpto, idCancelacion, idHeader, idFooter, idInstalacion,
					idValidez, idFormaPago, idCondPago, idFirma, idSeguridad, idPersonal, idCondReserva, idTipoPresupuesto, idPeriodo, idMoneda, cotizacion);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Crea un reporte por una orden de facturacion
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 * @return JasperPrint con el reporte creado
	 */
	public static JasperPrint createOCReport(long nroPpto){
		try {
			return OrdenCompraReport.instance().createOCReport(nroPpto);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Crea un reporte por una orden de facturacion
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 * @return JasperPrint con el reporte creado
	 */
	public static JasperPrint createOFReport(long nroPpto){
		try {
			return OrdenFacturacionReport.instance().createOFReport(nroPpto);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Crea un reporte por una orden de facturacion del adelanto
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion del adelanto
	 * @return JasperPrint con el reporte creado
	 */
	public static JasperPrint createOFAdelantoReport(long nroPpto){
		try {
			return AdelantoReport.instance().createOFReport(nroPpto);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Crea un reporte por week.
	 * 
	 * @param week semana del a�o de este reporte
	 * @param year a�o del reporte
	 * @return JasperPrint con el reporte creado
	 */
	public static JasperPrint createDiaryPlannerReport(int day, int month, int year, String tipo) {
		try {
			return DiaryPlannerReport.instance().createDailyReport(day, month, year, tipo);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Crea un reporte por week.
	 * 
	 * @param week semana del a�o de este reporte
	 * @param year a�o del reporte
	 * @return JasperPrint con el reporte creado
	 */
	public static JasperPrint createDiaryPlannerCompleteReport(int day, int month, int year) {
		try {
			return DiaryPlannerReport.instance().createDailyReportComplete(day, month, year);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Crea un reporte por week.
	 * 
	 * @param week semana del a�o de este reporte
	 * @param year a�o del reporte
	 * @return JasperPrint con el reporte creado
	 */
	public static JasperPrint createWeekReport(int week, int year) {
		try {
			return EventReport.instance().createWeekReport(week, year);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de weekly", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de weekly", e);
		}
		
		return null;
	}
	
	/**
	 * Crea un reporte por week por vendedor.
	 * 
	 * @param week semana del a�o de este reporte
	 * @param year a�o del reporte
	 * @return JasperPrint con el reporte creado
	 */
	public static JasperPrint createWeekReportVend(int week, int year, long codVendedor) {
		try {
			return EventReport.instance().createWeekReportVend(week, year, codVendedor);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de weekly", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de weekly", e);
		}
		
		return null;
	}
	
	/**
	 * Crea un reporte por week por uc.
	 * 
	 * @param week semana del a�o de este reporte
	 * @param year a�o del reporte
	 * @return JasperPrint con el reporte creado
	 */
	public static JasperPrint createWeekReportUC(int week, int year, long codUC) {
		try {
			return EventReport.instance().createWeekReportUC(week, year, codUC);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de weekly", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de weekly", e);
		}
		
		return null;
	}
	
	/*public static JasperPrint createPresupuestosGerenciaReport(int day1, int month1, int year1, int day2, int month2, int year2){
		try {
			return PresupuestosGerenciaReport.instance().createPresupuestosGciaReport(day1, month1, year1, day2, month2, year2);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de presupuestos de gerencia", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de presupuestos de gerencia", e);
		}
		
		return null;
	}
	
	public static JasperPrint createPresupuestosGerenciaReportVend(int day1, int month1, int year1, int day2, int month2, int year2, long codVendedor){
		try {
			return PresupuestosGerenciaReport.instance().createPresupuestosGciaReportVend(day1, month1, year1, day2, month2, year2, codVendedor);	
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de presupuestos de gerencia", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de presupuestos de gerencia", e);
		}
		
		return null;
	}*/
	
	public static JasperPrint createPresupuestosGerenciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codLugar, long codEstado, int type, String orden, int simple){
		try {
			return PresupuestosGerenciaReport.instance().createPresupuestosGciaReport(dateInicio, dateFin, codVendedor, codUC, codLugar, codEstado, type, orden, simple);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de presupuestos", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de presupuestos", e);
		}
		
		return null;
	}
	
	public static JasperPrint createPresupuestosCobranzasReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codLugar, long codEstado, int type, String orden){
		try {
			return PresupuestosGerenciaReport.instance().createPresupuestosCobranzasReport(dateInicio, dateFin, codVendedor, codUC, codLugar, codEstado, type, orden);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de presupuestos", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de presupuestos", e);
		}
		
		return null;
	}
	
	public static JasperPrint createPresupuestosCobradosGerenciaReport(DatePanel dateInicio, DatePanel dateFin){
		try {
			return PresupuestosCobradosGerenciaReport.instance().createPresupuestosGciaReport2(dateInicio, dateFin);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de presupuestos cobrados", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de presupuestos cobrados", e);
		}
		
		return null;
	}
	
	public static JasperPrint createPresupuestosConfirmadosGerenciaReport(DatePanel dateInicio, DatePanel dateFin){
		try {
			return PresupuestosConfirmadosGerenciaReport.instance().createPresupuestosGciaReport2(dateInicio, dateFin);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de presupuestos cobrados", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de presupuestos cobrados", e);
		}
		
		return null;
	}
	
	public static JasperPrint createPresupuestosGerenciaReportCreacion(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codLugar, long codEstado, DatePanel dateCreacion, int type, String orden, int simple){
		try {
			return PresupuestosGerenciaReport.instance().createPresupuestosGciaReportCreacion(dateInicio, dateFin, codVendedor, codUC, codLugar, codEstado, dateCreacion, type, orden, simple);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de presupuestos", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de presupuestos", e);
		}
		
		return null;
	}
	
	public static JasperPrint createSubcontratadoGerenciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codLugar, long codEstado, int type){
		try {
			return SubcontratadosGerenciaReport.instance().createSubcontratadosGciaReport(dateInicio, dateFin, codVendedor, codUC, codLugar, codEstado, type);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de subcontratados de gerencia", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de presupuestos de gerencia", e);
		}
		
		return null;
	}
	
	public static JasperPrint createCondPagoGerenciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codCondPago, int type){
		try {
			return CondicionPagoGerenciaReport.instance().createPresupuestosGciaReport(dateInicio, dateFin, codVendedor, codUC, codCondPago, type);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de condiciones de pago de gerencia", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de condiciones de pago de gerencia", e);
		}
		
		return null;
	}
	
	public static JasperPrint createReferenciaGerenciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codReferencia, int type){
		try {
			return ReferenciaGerenciaReport.instance().createPresupuestosGciaReport(dateInicio, dateFin, codVendedor, codUC, codReferencia, type);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de referencia de gerencia", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de referencia de gerencia", e);
		}
		
		return null;
	}
	
	public static JasperPrint createPorcentajeGerenciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codLugar, long codEstado, int type){
		try {
			return PorcentajeGerenciaReport.instance().createPresupuestosGciaReport(dateInicio, dateFin, codVendedor, codUC, codLugar, codEstado, type);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de porcentajes de gerencia", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de presupuestos de gerencia", e);
		}
		
		return null;
	}
	
	public static JasperPrint createPorcentajeComercialesReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codLugar, long codEstado, int type){
		try {
			return PorcentajeComercialesReport.instance().createPresupuestosGciaReport(dateInicio, dateFin, codVendedor, codUC, codLugar, codEstado, type);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de porcentajes de comerciales", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de presupuestos de comerciales", e);
		}
		
		return null;
	}
	
	public static JasperPrint createLimitesGerenciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long mayorMenor, double montoLimite, int type){
		try {
			return LimitesGerenciaReport.instance().createLimitesGciaReport(dateInicio, dateFin, codVendedor, codUC, mayorMenor, montoLimite, type);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de limites de gerencia", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de limites de gerencia", e);
		}
		
		return null;
	}
	
	public static JasperPrint createLimitesYEstadosGerenciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long mayorMenorFechaMonto, double montoLimite, long fechaMayorMenor, long codEstado, int type){
		try {
			return LimitesYEstadoGerenciaReport.instance().createLimitesGciaReport(dateInicio, dateFin, codVendedor, codUC, mayorMenorFechaMonto, montoLimite, fechaMayorMenor, codEstado,type);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al armar el reporte de limites y estado de gerencia", e);
		} catch (JRException e) {
			Util.errMsg(Main.getVentana(), "Error interno al armar el reporte de limites y estado de gerencia", e);
		}
		
		return null;
	}
	
	public static JasperPrint createInfoSemanalReport(int week, int year) {
		try {
			return InfoSemanalReport.instance().createWeekReport(week, year);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static JasperPrint createInfoMensualReport(int month, int year) {
		try {
			return InfoMensualReport.instance().createWeekReport(month, year);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Crea un reporte diario
	 * 
	 * @param day dia del mes para el reporte
	 * @param month mes del a�o
	 * @param year a�o del reporte
	 * @return JasperPrint con el reporte creado
	 * @throws RemoteException
	 * @throws JRException
	 */
	public static JasperPrint createDailyReport(int day, int month, int year) {
		try {
			return EventReport.instance().createDailyReport(day, month, year);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Crea un reporte diario
	 * 
	 * @param day dia del mes para el reporte
	 * @param month mes del a�o
	 * @param year a�o del reporte
	 * @return JasperPrint con el reporte creado
	 * @throws RemoteException
	 * @throws JRException
	 */
	public static JasperPrint createDailyReportVend(int day, int month, int year, long codVend) {
		try {
			return EventReport.instance().createDailyReportVend(day, month, year, codVend);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Crea un reporte diario
	 * 
	 * @param day dia del mes para el reporte
	 * @param month mes del a�o
	 * @param year a�o del reporte
	 * @return JasperPrint con el reporte creado
	 * @throws RemoteException
	 * @throws JRException
	 */
	public static JasperPrint createDailyReportUC(int day, int month, int year, long codUC) {
		try {
			return EventReport.instance().createDailyReportUC(day, month, year, codUC);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Crea una vista previa de una orden de servicio determinada
	 * 
	 * @param nroPpto numero de presupuesto de la orden de servicio
	 */
	public static void previewListaPreciosReport(String fecha){
		JasperPrint jp = createListaPreciosReport(fecha);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle("Lista de precios - Vista Previa");
		jv.setVisible(true);
	}
	
	/**
	 * Crea una vista previa de un presupuesto determinada
	 * 
	 * @param nroPpto numero de presupuesto 
	 */
	public static void previewPresupuestoReport(final long nroPpto, final long idCancelacion,final long idHeader,final long idFooter,final long idInstalacion,
			final long idValidez,final long idFormaPago, final long idCondPago, final long idFirma,final long idSeguridad,final long idPersonal,final long idCondReserva,final long idTipoPresupuesto, 
			final long idPeriodo,final long idMoneda,final double cotizacion){
		
		JasperPrint jp = createPresupuestoReport(nroPpto, idCancelacion, idHeader, idFooter, idInstalacion,
			idValidez, idFormaPago, idCondPago, idFirma, idSeguridad, idPersonal, idCondReserva, idTipoPresupuesto, idPeriodo, idMoneda, cotizacion);
		
		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle(PresupuestoReport.instance().createPresupuestoTitle(nroPpto) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	/**
	 * Crea una vista previa de un presupuesto determinada sin las descripcionesd e los servicios
	 * 
	 * @param nroPpto numero de presupuesto 
	 */
	public static void previewPresupuestoResumReport(final long nroPpto, final long idCancelacion,final long idHeader,final long idFooter,final long idInstalacion,
			final long idValidez,final long idFormaPago, final long idCondPago, final long idFirma,final long idSeguridad,final long idPersonal,final long idCondReserva,final long idTipoPresupuesto, 
			final long idPeriodo,final long idMoneda,final double cotizacion){
		
		JasperPrint jp = createPresupuestoResumReport(nroPpto, idCancelacion, idHeader, idFooter, idInstalacion,
			idValidez, idFormaPago, idCondPago, idFirma, idSeguridad, idPersonal, idCondReserva, idTipoPresupuesto, idPeriodo, idMoneda, cotizacion);
		
		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle(PresupuestoReport.instance().createPresupuestoTitle(nroPpto) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	/**
	 * Crea una vista previa de una orden de servicio determinada
	 * 
	 * @param nroPpto numero de presupuesto de la orden de servicio
	 */
	public static void previewOSReport(long nroPpto){
		JasperPrint jp = createOSReport(nroPpto);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle(OrdenServicioReport.instance().createOSTitle(nroPpto) + " - Vista Previa");
		jv.setVisible(true);
	}

	/**
	 * Crea una vista previa de una orden de servicio determinada
	 * 
	 * @param nroPpto numero de presupuesto de la orden de servicio
	 */
	public static void previewOCReport(long nroPpto){
		JasperPrint jp = createOCReport(nroPpto);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle(OrdenCompraReport.instance().createOCTitle(nroPpto) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	/**
	 * Crea una vista previa de una orden de facturacion determinada
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 */
	public static void previewOFReport(long nroPpto){
		JasperPrint jp = createOFReport(nroPpto);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle(OrdenFacturacionReport.instance().createOFTitle(nroPpto) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	/**
	 * Crea una vista previa de una orden de facturacion del adelanto determinada
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion del adelanto
	 */
	public static void previewOFAdelantoReport(long nroPpto){
		JasperPrint jp = createOFAdelantoReport(nroPpto);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle(AdelantoReport.instance().createOFTitle(nroPpto) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	public static void previewDiaryPlannerReport(int day, int month, int year, String tipo) {
		JasperPrint jp = createDiaryPlannerReport(day, month, year, tipo);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		if(tipo.equals("Todas"))
			jv.setTitle("Diary Planner del "+ day +"-"+month+"-"+year+ " - Todas la unidades");
		else
			jv.setTitle("Diary Planner del "+ day +"-"+month+"-"+year+ " - "+tipo);
		jv.setVisible(true);
	}
	
	public static void previewDiaryPlannerCompleteReport(int day, int month, int year) {
		JasperPrint jp = createDiaryPlannerCompleteReport(day, month, year);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle("Diary Planner del "+ DiaryPlannerReport.instance().createDailyTitle(day, month, year) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	/**
	 * Crea una vista previa de un week determinado 
	 * 
	 * @param week semana del a�o de este reporte
	 * @param year a�o del reporte
	 */
	public static void previewWeekReport(int week, int year){
		JasperPrint jp = createWeekReport(week, year);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle(EventReport.instance().createWeekTitle(week,year) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	/**
	 * Crea una vista previa de un week determinado filtrando por vendedor logueado
	 * 
	 * @param week semana del a�o de este reporte
	 * @param year a�o del reporte
	 */
	public static void previewWeekReportVend(int week, int year, long codVendedor){
		JasperPrint jp = createWeekReportVend(week, year, codVendedor);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle(EventReport.instance().createWeekTitle(week,year) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	/**
	 * Crea una vista previa de un week determinado filtrando por vendedor logueado
	 * 
	 * @param week semana del a�o de este reporte
	 * @param year a�o del reporte
	 */
	public static void previewWeekReportUC(int week, int year, long codUC){
		JasperPrint jp = createWeekReportUC(week, year, codUC);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle(EventReport.instance().createWeekTitle(week,year) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	public static void previewPorcentajeGerenciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codLugar, long codEstado, int type){
		JasperPrint jp = createPorcentajeGerenciaReport(dateInicio, dateFin, codVendedor, codUC, codLugar, codEstado, type);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle("Presupuestos del "+PorcentajeGerenciaReport.instance().createPresupuestosTitle(dateInicio, dateFin) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	public static void previewCondPagoGerenciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codCondPago, int type){
		JasperPrint jp = createCondPagoGerenciaReport(dateInicio, dateFin, codVendedor, codUC, codCondPago, type);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle("Presupuestos del "+CondicionPagoGerenciaReport.instance().createPresupuestosTitle(dateInicio, dateFin) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	public static void previewReferenciaGerenciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codReferencia, int type){
		JasperPrint jp = createReferenciaGerenciaReport(dateInicio, dateFin, codVendedor, codUC, codReferencia, type);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle("Presupuestos del "+CondicionPagoGerenciaReport.instance().createPresupuestosTitle(dateInicio, dateFin) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	public static void previewPorcentajeComercialesReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codLugar, long codEstado, int type){
		JasperPrint jp = createPorcentajeComercialesReport(dateInicio, dateFin, codVendedor, codUC, codLugar, codEstado, type);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle("Presupuestos del "+PorcentajeComercialesReport.instance().createPresupuestosTitle(dateInicio, dateFin) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	public static void previewSubcontratadoGerenciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codLugar, long codEstado, int type){
		JasperPrint jp = createSubcontratadoGerenciaReport(dateInicio, dateFin, codVendedor, codUC, codLugar, codEstado, type);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle("Presupuestos del "+SubcontratadosGerenciaReport.instance().createSubcontratadosTitle(dateInicio, dateFin) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	public static void previewPresupuestosGerenciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codLugar, long codEstado, int type, String orden, int simple){
		JasperPrint jp = createPresupuestosGerenciaReport(dateInicio, dateFin, codVendedor, codUC, codLugar, codEstado, type, orden, simple);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle("Presupuestos del "+PresupuestosGerenciaReport.instance().createPresupuestosTitle(dateInicio, dateFin) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	public static void previewPresupuestosCobranzasReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codLugar, long codEstado, int type, String orden){
		JasperPrint jp = createPresupuestosCobranzasReport(dateInicio, dateFin, codVendedor, codUC, codLugar, codEstado, type, orden);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle("Presupuestos del "+PresupuestosGerenciaReport.instance().createPresupuestosTitle(dateInicio, dateFin) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	public static void previewPresupuestosGerenciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long codLugar, long codEstado, DatePanel dateCreacion, int type, String orden, int simple){
		JasperPrint jp = createPresupuestosGerenciaReportCreacion(dateInicio, dateFin, codVendedor, codUC, codLugar, codEstado, dateCreacion, type, orden, simple);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle("Presupuestos del "+PresupuestosGerenciaReport.instance().createPresupuestosTitle(dateInicio, dateFin) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	public static void previewPresupuestosCobradosGerenciaReport(DatePanel dateInicio, DatePanel dateFin){
		JasperPrint jp = createPresupuestosCobradosGerenciaReport(dateInicio, dateFin);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle("Presupuestos del "+PresupuestosCobradosGerenciaReport.instance().createPresupuestosTitle(dateInicio, dateFin) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	public static void previewPresupuestosConfirmadosGerenciaReport(DatePanel dateInicio, DatePanel dateFin){
		JasperPrint jp = createPresupuestosConfirmadosGerenciaReport(dateInicio, dateFin);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle("Presupuestos del "+PresupuestosConfirmadosGerenciaReport.instance().createPresupuestosTitle(dateInicio, dateFin) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	public static void previewLimitesGerenciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long mayorMenor, double montoLimite, int type){
		JasperPrint jp = createLimitesGerenciaReport(dateInicio, dateFin, codVendedor, codUC, mayorMenor, montoLimite, type);

		JasperViewer jv = new JasperViewer(jp,false);

		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle("Presupuestos del "+LimitesGerenciaReport.instance().createLimitesTitle(dateInicio, dateFin) + " - Vista Previa");
		jv.setVisible(true);
	}

	public static void previewLimitesYEstadosGerenciaReport(DatePanel dateInicio, DatePanel dateFin, long codVendedor, long codUC, long mayorMenorFechaMonto, double montoLimite, long fechaMayorMenor, long codEstado, int type){
		JasperPrint jp = createLimitesYEstadosGerenciaReport(dateInicio, dateFin, codVendedor, codUC, mayorMenorFechaMonto, montoLimite, fechaMayorMenor, codEstado, type);

		JasperViewer jv = new JasperViewer(jp,false);

		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle("Presupuestos del "+LimitesGerenciaReport.instance().createLimitesTitle(dateInicio, dateFin) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	public static void previewInfoSemanalReport(int week, int year){
		JasperPrint jp = createInfoSemanalReport(week, year);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle(EventReport.instance().createWeekTitle(week,year) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	public static void previewInfoMensualReport(int month, int year){
		JasperPrint jp = createInfoMensualReport(month, year);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle(EventReport.instance().createWeekTitle(month,year) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	/**
	 * Crea una vista previa de un dia determinado 
	 * 
	 * @param day dia del mes para el reporte
	 * @param month mes del a�o
	 * @param year a�o del reporte
	 */
	public static void previewDailyReport(int day, int month, int year) {
		JasperPrint jp = createDailyReport(day, month, year);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle(EventReport.instance().createDailyTitle(day, month, year) + " - Vista Previa");
		jv.setVisible(true);
	}
	
	/**
	 * Crea una vista previa de un dia determinado por vendedor
	 * 
	 * @param day dia del mes para el reporte
	 * @param month mes del a�o
	 * @param year a�o del reporte
	 */
	public static void previewDailyReportVend(int day, int month, int year, long codVend) {
		JasperPrint jp = createDailyReportVend(day, month, year, codVend);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle(EventReport.instance().createDailyTitle(day, month, year) + " - Vista Previa");
		jv.setVisible(true);
	}	
	
	/**
	 * Crea una vista previa de un dia determinado por uc 
	 * 
	 * @param day dia del mes para el reporte
	 * @param month mes del a�o
	 * @param year a�o del reporte
	 */
	public static void previewDailyReportUC(int day, int month, int year, long codUC) {
		JasperPrint jp = createDailyReportUC(day, month, year, codUC);

		JasperViewer jv = new JasperViewer(jp,false);
		//jv.setAlwaysOnTop(true);
		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jv.setTitle(EventReport.instance().createDailyTitle(day, month, year) + " - Vista Previa");
		jv.setVisible(true);
	}	
	
	/**
	 * Imprime un reporte diario
	 * 
	 * @param day dia del mes para el reporte
	 * @param month mes del a�o
	 * @param year a�o del reporte
	 * @param withPrintDialog indica si debo mostrar el dialogo de impresion
	 */
	public static void printDailyReport(int day, int month, int year, boolean withPrintDialog){
		JasperPrint jp = createDailyReport(day, month, year);

		try {
			JasperPrintManager.printReport(jp, withPrintDialog);
		} catch (JRException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Imprime un presupuesto
	 * 
	 * @param nroPpto numero de presupuesto de la oreden de facturacion
	 * @param withPrintDialog indica si debo mostrar el dialogo de impresion
	 */
	public static void printPresupuestoReport(long nroPpto, long idCancelacion, long idHeader, long idFooter, long idInstalacion,
			long idValidez, long idFormaPago, long idCondPago, long idFirma, long idSeguridad, long idPersonal, long idCondReserva, long idTipoPresupuesto, 
			long idPeriodo,long idMoneda, double cotizacion, boolean withPrintDialog){
		JasperPrint jp = createPresupuestoReport(nroPpto, idCancelacion, idHeader, idFooter, idInstalacion,
				idValidez, idFormaPago, idCondPago, idFirma,idSeguridad, idPersonal, idCondReserva, idTipoPresupuesto, idPeriodo, idMoneda, cotizacion);

		try {
			JasperPrintManager.printReport(jp, withPrintDialog);
		} catch (JRException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Imprime una orden de facturacion
	 * 
	 * @param nroPpto numero de presupuesto de la oreden de facturacion
	 * @param withPrintDialog indica si debo mostrar el dialogo de impresion
	 */
	public static void printOCReport(long nroPpto, boolean withPrintDialog){
		JasperPrint jp = createOCReport(nroPpto);

		try {
			JasperPrintManager.printReport(jp, withPrintDialog);
		} catch (JRException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Imprime una orden de facturacion
	 * 
	 * @param nroPpto numero de presupuesto de la oreden de facturacion
	 * @param withPrintDialog indica si debo mostrar el dialogo de impresion
	 */
	public static void printOFReport(long nroPpto, boolean withPrintDialog){
		JasperPrint jp = createOFReport(nroPpto);

		try {
			JasperPrintManager.printReport(jp, withPrintDialog);
		} catch (JRException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Imprime una orden de facturacion del adelanto
	 * 
	 * @param nroPpto numero de presupuesto de la oreden de facturacion
	 * @param withPrintDialog indica si debo mostrar el dialogo de impresion
	 */
	public static void printOFAdelantoReport(long nroPpto, boolean withPrintDialog){
		JasperPrint jp = createOFAdelantoReport(nroPpto);

		try {
			JasperPrintManager.printReport(jp, withPrintDialog);
		} catch (JRException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Imprime una orden de servicio
	 * 
	 * @param nroPpto numero de presupuesto de la oreden de servicio
	 * @param withPrintDialog indica si debo mostrar el dialogo de impresion
	 */
	public static void printOSReport(long nroPpto, boolean withPrintDialog){
		JasperPrint jp = createOSReport(nroPpto);

		try {
			JasperPrintManager.printReport(jp, withPrintDialog);
		} catch (JRException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Imprime un reporte week
	 * 
	 * @param week semana del a�o de este reporte
	 * @param year a�o del reporte
	 * @param withPrintDialog indica si debo mostrar el dialogo de impresion
	 */
	public static void printWeekReport(int week, int year, boolean withPrintDialog){
		JasperPrint jp = createWeekReport(week, year);

		try {
			JasperPrintManager.printReport(jp, withPrintDialog);
		} catch (JRException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Exporta este presupuesto a pdf
	 * 
	 * @param nroPpto numero de presupuesto de la orden de servicio
	 * @param output ruta del archivo de destino
	 */
	public static void exportPresupuestoToPdf(long nroPpto, long idCancelacion, long idHeader, long idFooter, long idInstalacion,
			long idValidez, long idFormaPago, long idCondPago, long idFirma, long idSeguridad, long idPersonal, long idCondReserva, long idTipoPresupuesto, 
			long idPeriodo, long idMoneda, double cotizacion, String output){
		exportPresupuestoToFile(nroPpto, idCancelacion, idHeader, idFooter, idInstalacion,
				idValidez, idFormaPago, idCondPago, idFirma, idSeguridad, idPersonal, idCondReserva, idTipoPresupuesto, idPeriodo, idMoneda, cotizacion, output, EXPORT_TYPE_PDF);
	}
	
	/**
	 * Exporta esta orden de servicio a html
	 * 
	 * @param nroPpto numero de presupuesto de la orden de servicio
	 * @param output ruta del archivo de destino
	 */
	public static void exportPresupuestoToHtml(long nroPpto, long idCancelacion, long idHeader, long idFooter, long idInstalacion,
			long idValidez, long idFormaPago, long idCondPago, long idFirma, long idSeguridad, long idPersonal, long idCondReserva, long idTipoPresupuesto, 
			long idPeriodo, long idMoneda, double cotizacion, String output){
		exportPresupuestoToFile(nroPpto, idCancelacion, idHeader, idFooter, idInstalacion,
				idValidez, idFormaPago, idCondPago, idFirma, idSeguridad, idPersonal, idCondReserva, idTipoPresupuesto, idPeriodo, idMoneda, cotizacion, output, EXPORT_TYPE_HTML);
	}
	
	/**
	 * Exporta esta orden de servicio a  rtf
	 * 
	 * @param nroPpto numero de presupuesto de la orden de servicio
	 * @param output ruta del archivo de destino
	 */
	public static void exportPresupuestoToRtf(long nroPpto, long idCancelacion, long idHeader, long idFooter, long idInstalacion,
			long idValidez, long idFormaPago, long idCondPago, long idFirma, long idSeguridad, long idPersonal, long idCondReserva, long idTipoPresupuesto, 
			long idPeriodo, long idMoneda, double cotizacion, String output){
		exportPresupuestoToFile(nroPpto, idCancelacion, idHeader, idFooter, idInstalacion,
				idValidez, idFormaPago, idCondPago, idFirma, idSeguridad, idPersonal, idCondReserva, idTipoPresupuesto, idPeriodo, idMoneda, cotizacion, output, EXPORT_TYPE_TXT);
	}

	/**
	 * Exporta esta orden de servicio a pdf
	 * 
	 * @param nroPpto numero de presupuesto de la orden de servicio
	 * @param output ruta del archivo de destino
	 */
	public static void exportOSToPdf(long nroPpto, String output){
		exportOSToFile(nroPpto, output, EXPORT_TYPE_PDF);
	}
	
	/**
	 * Exporta esta orden de servicio a html
	 * 
	 * @param nroPpto numero de presupuesto de la orden de servicio
	 * @param output ruta del archivo de destino
	 */
	public static void exportOSToHtml(long nroPpto, String output){
		exportOSToFile(nroPpto, output, EXPORT_TYPE_HTML);
	}
	
	/**
	 * Exporta esta orden de servicio a  rtf
	 * 
	 * @param nroPpto numero de presupuesto de la orden de servicio
	 * @param output ruta del archivo de destino
	 */
	public static void exportOSToRtf(long nroPpto, String output){
		exportOSToFile(nroPpto, output, EXPORT_TYPE_RTF);
	}
	
	/**
	 * Exporta esta orden de facturacion a pdf
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 * @param output ruta del archivo de destino
	 */
	public static void exportOCToPdf(long nroPpto, String output){
		exportOCToFile(nroPpto, output, EXPORT_TYPE_PDF);
	}
	
	/**
	 * Exporta esta orden de facturacion a html
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 * @param output ruta del archivo de destino
	 */
	public static void exportOCToHtml(long nroPpto, String output){
		exportOCToFile(nroPpto, output, EXPORT_TYPE_HTML);
	}
	
	/**
	 * Exporta esta orden de facturacion a  rtf
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 * @param output ruta del archivo de destino
	 */
	public static void exportOCToRtf(long nroPpto, String output){
		exportOCToFile(nroPpto, output, EXPORT_TYPE_RTF);
	}
	
	/**
	 * Exporta esta orden de facturacion a pdf
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 * @param output ruta del archivo de destino
	 */
	public static void exportOFToPdf(long nroPpto, String output){
		//exportOFToFile(nroPpto, output, EXPORT_TYPE_PDF);
		exportOFToFile(nroPpto, output, EXPORT_TYPE_PDF);
	}
	
	/**
	 * Exporta esta orden de facturacion a html
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 * @param output ruta del archivo de destino
	 */
	public static void exportOFToHtml(long nroPpto, String output){
		exportOFToFile(nroPpto, output, EXPORT_TYPE_HTML);
	}
	
	/**
	 * Exporta esta orden de facturacion a  rtf
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 * @param output ruta del archivo de destino
	 */
	public static void exportOFToRtf(long nroPpto, String output){
		exportOFToFile(nroPpto, output, EXPORT_TYPE_RTF);
	}
	
	/**
	 * Exporta esta orden de facturacion a pdf
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 * @param output ruta del archivo de destino
	 */
	public static void exportOFAdelantoToPdf(long nroPpto, String output){
		exportOFAdelantoToFile(nroPpto, output, EXPORT_TYPE_PDF);
	}
	
	/**
	 * Exporta esta orden de facturacion a html
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 * @param output ruta del archivo de destino
	 */
	public static void exportOFAdelantoToHtml(long nroPpto, String output){
		exportOFAdelantoToFile(nroPpto, output, EXPORT_TYPE_HTML);
	}
	
	/**
	 * Exporta esta orden de facturacion a  rtf
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 * @param output ruta del archivo de destino
	 */
	public static void exportOFAdelantoToRtf(long nroPpto, String output){
		exportOFAdelantoToFile(nroPpto, output, EXPORT_TYPE_RTF);
	}
	
	/**
	 * Exporta este diario a pdf
	 * 
	 * @param day dia del mes para el reporte
	 * @param month mes del a�o
	 * @param year a�o del reporte
	 * @param output ruta del archivo de destino
	 */
	public static void exportDailyToPdf(int day, int month, int year, String output){
		exportDailyToFile(day, month,year, output, EXPORT_TYPE_PDF);
	}
	
	/**
	 * Exporta este diario a html
	 * 
	 * @param day dia del mes para el reporte
	 * @param month mes del a�o
	 * @param year a�o del reporte
	 * @param output ruta del archivo de destino
	 */
	public static void exportDailyToHtml(int day, int month, int year, String output){
		exportDailyToFile(day, month,year, output, EXPORT_TYPE_HTML);
	}
	
	/**
	 * Exporta este diario a  rtf
	 * 
	 * @param day dia del mes para el reporte
	 * @param month mes del a�o
	 * @param year a�o del reporte
	 * @param output ruta del archivo de destino
	 */
	public static void exportDailyToRtf(int day, int month, int year, String output){
		exportDailyToFile(day, month,year, output, EXPORT_TYPE_RTF);
	}
	
	/**
	 * Exporta este week a pdf
	 * 
	 * @param week semana del a�o de este reporte
	 * @param year a�o del reporte
	 * @param output ruta del archivo de destino
	 */
	public static void exportWeekToPdf(int week, int year, String output){
		exportWeekToFile(week,year, output, EXPORT_TYPE_PDF);
	}
	
	/**
	 * Exporta este week a html
	 * 
	 * @param week semana del a�o de este reporte
	 * @param year a�o del reporte
	 * @param output ruta del archivo de destino
	 */
	public static void exportWeekToHtml(int week, int year, String output){
		exportWeekToFile(week,year, output, EXPORT_TYPE_HTML);
	}
	
	/**
	 * Exporta este week a rtf
	 * 
	 * @param week semana del a�o de este reporte
	 * @param year a�o del reporte
	 * @param output ruta del archivo de destino
	 */
	public static void exportWeekToRtf(int week, int year, String output){
		exportWeekToFile(week,year, output, EXPORT_TYPE_RTF);
	}
	
	/**
	 * Exporta un presupuesto al tipo de archivo indicado
	 * 
	 * @param nroPpto numero de presupuesto de la orden de servicio
	 * @param output ruta del archivo de destino
	 * @param type tipo de archivo de destino, puede ser pdf, html o rtf
	 */
	public static void exportPresupuestoToFile(long nroPpto, long idCancelacion, long idHeader, long idFooter, long idInstalacion,
			long idValidez, long idFormaPago, long idCondPago, long idFirma, long idSeguridad, long idPersonal, long idCondReserva, long idTipoPresupuesto, 
			long idPeriodo,long idMoneda, double cotizacion, String output, int type){
		JasperPrint jp = createPresupuestoReport(nroPpto, idCancelacion, idHeader, idFooter, idInstalacion,
				idValidez, idFormaPago, idCondPago, idFirma, idSeguridad, idPersonal, idCondReserva, idTipoPresupuesto, idPeriodo, idMoneda, cotizacion);
		exportReportToFile(jp, output, type);
	}
	
	/**
	 * Exporta una orden de servicio al tipo de archivo indicado
	 * 
	 * @param nroPpto numero de presupuesto de la orden de servicio
	 * @param output ruta del archivo de destino
	 * @param type tipo de archivo de destino, puede ser pdf, html o rtf
	 */
	public static void exportOSToFile(long nroPpto, String output, int type){
		JasperPrint jp = createOSReport(nroPpto);
		exportReportToFile(jp, output, type);
	}
	
	/**
	 * Exporta una orden de facturacion al tipo de archivo indicado
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 * @param output ruta del archivo de destino
	 * @param type tipo de archivo de destino, puede ser pdf, html o rtf
	 */
	public static void exportOCToFile(long nroPpto, String output, int type){
		JasperPrint jp = createOCReport(nroPpto);
		exportReportToFile(jp, output, type);
	}
	
	/**
	 * Exporta una orden de facturacion al tipo de archivo indicado
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion
	 * @param output ruta del archivo de destino
	 * @param type tipo de archivo de destino, puede ser pdf, html o rtf
	 */
	public static void exportOFToFile(long nroPpto, String output, int type){
		JasperPrint jp = createOFReport(nroPpto);
		exportReportToFile(jp, output, type);
	}
	
	/**
	 * Exporta una orden de facturacion del adelanto al tipo de archivo indicado
	 * 
	 * @param nroPpto numero de presupuesto de la orden de facturacion del adelanto
	 * @param output ruta del archivo de destino
	 * @param type tipo de archivo de destino, puede ser pdf, html o rtf
	 */
	public static void exportOFAdelantoToFile(long nroPpto, String output, int type){
		JasperPrint jp = createOFAdelantoReport(nroPpto);
		exportReportToFile(jp, output, type);
	}
	
	/**
	 * Exporta un reporte diario al tipo de archivo indicado
	 * 
	 * @param day dia del mes para el reporte
	 * @param month mes del a�o
	 * @param year a�o del reporte
	 * @param output ruta del archivo de destino
	 * @param type tipo de archivo de destino, puede ser pdf, html o rtf
	 */
	public static void exportDailyToFile(int day, int month, int year, String output, int type){
		JasperPrint jp = createDailyReport(day, month, year);
		exportReportToFile(jp, output, type);
	}
	
	/**
	 * Exporta un reporte semanal al tipo de archivo indicado
	 * 
	 * @param week semana del a�o de este reporte
	 * @param year a�o del reporte
	 * @param output ruta del archivo de destino
	 * @param type tipo de archivo de destino, puede ser pdf, html o rtf
	 */
	public static void exportWeekToFile(int week, int year, String output, int type){
		JasperPrint jp = createWeekReport(week, year);
		exportReportToFile(jp, output, type);
	}
	
	/**
	 * Exporta un reporte a archivo utilizando el tipo indicado por el usuario.
	 * 
	 * @param jp el reporte que se va a exportar
	 * @param output ruta del archivo de destino
	 * @param type tipo de archivo de destino, puede ser pdf, html o rtf
	 */
	public static void exportReportToFile(JasperPrint jp, String output, int type){
		
		try {
			switch(type){
			case EXPORT_TYPE_PDF:
				JasperExportManager.exportReportToPdfFile(jp, output);
				break;
			case EXPORT_TYPE_HTML:
				JasperExportManager.exportReportToHtmlFile(jp, output);
				break;
			case EXPORT_TYPE_RTF:
				exportReportToRtfFile(jp, output);
				break;
			case EXPORT_TYPE_TXT:
				exportReportToTxtFile(jp, output);
				break;
			default:
				throw new RuntimeException("Tipo de export desconocido, no se que hacer");
			}
		} 
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Exporta a RTF, utilizando el mismo sistema que el Facade JasperExportManager
	 * 
	 * @param jasperPrint el reporte que se va a exportar
	 * @param destFileName ruta del archivo de destino
	 * @throws JRException  
	 */
	private static void exportReportToTxtFile(JasperPrint jasperPrint, String destFileName) throws JRException {
		JRTextExporter exporter = new JRTextExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFileName);
		
		exporter.exportReport();
	}
	
	/**
	 * Exporta a RTF, utilizando el mismo sistema que el Facade JasperExportManager
	 * 
	 * @param jasperPrint el reporte que se va a exportar
	 * @param destFileName ruta del archivo de destino
	 * @throws JRException  
	 */
	private static void exportReportToRtfFile(JasperPrint jasperPrint, String destFileName) throws JRException {
		JRRtfExporter exporter = new JRRtfExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFileName);
		
		exporter.exportReport();
	}
}
