package crm.client.report;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.services.report.sei.InformeOSReportSEI;
import crm.services.report.sei.OrdenServicioReportSEI;

public class InformeOSReport extends CongressReportService implements InformeOSReportSEI{
	
	private InformeOSReportSEI stub;
	private static InformeOSReport instance;
	
	public InformeOSReport() throws WSIFException{
		super("InformeOSReportSEI");
	}

	protected void registerTypes() throws WSIFException {
		stub = (InformeOSReportSEI)service.getStub(InformeOSReportSEI.class);
	}
	
	public static InformeOSReport instance(){
		try {
			if (instance == null) {
				instance = new InformeOSReport();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

	@Override
	public void createInformeOS(long nroPpto) throws RemoteException {
		stub.createInformeOS(nroPpto);
		
	}
	
	
	
}
