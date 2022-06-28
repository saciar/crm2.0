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
import crm.libraries.report.EventoAccesorio;
import crm.libraries.report.EventoOperador;
import crm.libraries.report.EventoProveedor;
import crm.libraries.report.OrdenServicio;
import crm.libraries.report.OrdenServicioSala;
import crm.libraries.report.OrdenServicioServicio;
import crm.libraries.report.OrdenServiciosDesconfirmada;
import crm.services.report.sei.OrdenServicioReportSEI;
import crm.services.report.sei.OsDesconfirmadaReportSEI;

public class OsDesconfirmadaReport extends CongressReportService implements OsDesconfirmadaReportSEI {

	private OsDesconfirmadaReportSEI stub;
	private static OsDesconfirmadaReport instance;
	
	public OsDesconfirmadaReport() throws WSIFException{
		super("OsDesconfirmadaReportSEI");
	}

	public static OsDesconfirmadaReport instance() {
		try {
			if (instance == null) {
				instance = new OsDesconfirmadaReport();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public void savePdfFile(long nroPpto) throws RemoteException{
		stub.savePdfFile(nroPpto);
	}
	
	public OrdenServiciosDesconfirmada[] findByNroPpto(long nroPpto) throws RemoteException {
		return stub.findByNroPpto(nroPpto);
	}
	
	public boolean sendOSByEmail2(long nroPpto, String fechaInicial, String fechaFinal, String evento, String usuarioId, String codLugar, String emailDestino) throws RemoteException{
		return stub.sendOSByEmail2(nroPpto, fechaInicial, fechaFinal, evento, usuarioId, codLugar, emailDestino);
	}

	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("OrdenServiciosDesconfirmada", Class.forName("crm.libraries.report.OrdenServiciosDesconfirmada"));
			addType("OrdenServiciosDesconfirmadaSala", Class.forName("crm.libraries.report.OrdenServiciosDesconfirmadaSala"));
			addType("OrdenServiciosDesconfirmadaServicios", Class.forName("crm.libraries.report.OrdenServiciosDesconfirmadaServicios"));
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
		
		stub = (OsDesconfirmadaReportSEI)service.getStub(OsDesconfirmadaReportSEI.class);
	}

}
