package crm.client.report2;

import org.apache.wsif.WSIFException;

import crm.client.report.CongressReportService;

public class OsReport extends CongressReportService{

	public OsReport(String portType) {
		super(portType);
		// TODO Auto-generated constructor stub
	}

	private static OsReport instance;

	@Override
	protected void registerTypes() throws WSIFException {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
