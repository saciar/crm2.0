package crm.client.report;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.report.Presupuesto;
import crm.services.report.sei.PresupuestoExcelReportSEI;

public class PresupuestoExcelReport extends CongressReportService implements PresupuestoExcelReportSEI{

	private PresupuestoExcelReportSEI stub;
	private static PresupuestoExcelReport instance;
	
	public PresupuestoExcelReport() throws WSIFException {
		super("PresupuestoExcelReportSEI");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void registerTypes() throws WSIFException {
		try {
			addType("Presupuesto", Class.forName("crm.libraries.report.Presupuesto"));
			addType("PresupuestoSala", Class.forName("crm.libraries.report.PresupuestoSala"));
			addType("PresupuestoServicio", Class.forName("crm.libraries.report.PresupuestoServicio"));
			addType("PresupuestoOpcional", Class.forName("crm.libraries.report.PresupuestoOpcional"));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stub = (PresupuestoExcelReportSEI)service.getStub(PresupuestoExcelReportSEI.class);
	}
	
	public static PresupuestoExcelReport instance(){
		try {
			if (instance == null) {
				instance = new PresupuestoExcelReport();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	@Override
	public Presupuesto[] findByNroPpto(long nroPpto) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.findByNroPpto(nroPpto);
	}
	

}
