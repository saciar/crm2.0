package crm.test.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import net.sf.jasperreports.engine.JasperCompileManager;
import crm.client.managers.PresupuestosManager;
import crm.client.report.AdelantoReport;
import crm.client.report.ListaPreciosReport;
import crm.client.report.OrdenFacturacionReport;
import crm.client.report.OrdenServicioReport;
import crm.client.report.PresupuestoReport;
import crm.client.report.ReportBuilder;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.CustomTextField;
import crm.gui.components.DatePanel;

public class TestReports {
	private static final String reportsDir = "C:/workspace/CongressCrmClient/jasper";
	private static final String outputDir = "C:/workspace/services/src/crm/services/jasper";
	
	public static void main(String[] args){
		TestReports test = new TestReports();
		
		test.test();
		//test.compileReports();
	}
	
	public void test(){		
		//ReportBuilder.previewPresupuestosGerenciaReport(29,05,2007,31,05,2007);
		//ReportBuilder.previewWeekReport(22,2007);
		//ReportBuilder.previewDailyReport(29,06,2007);
		/*DatePanel d1 = CustomTextField.getDateInstance();
		DatePanel d2 = CustomTextField.getDateInstance();
		d1.setDate("2007-06-01");
		d2.setDate("2007-06-30");
		ReportBuilder.previewPresupuestosCobradosGerenciaReport(d1,d2);*/
		/*ReportBuilder.previewPresupuestoReport(58,16, 1, 1,
				1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1.00);*/
		
		/*try {
			PresupuestoReport.instance().sendEmail("saciar@congressrental.com","sergio");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*try{
				AdelantoReport.instance().savePdfFile(1767);
				AdelantoReport.instance().sendOFByEmail(1767, "28");
		} catch (Exception e) {
			Util.errMsg(Main.getVentana(),"Se ha producido un error al enviar orden de facturación del adelanto",e);
		}*/
		
		/*AudioFormat audioFormat;
		AudioInputStream audioInputStream;
		SourceDataLine sourceDataLine;
		boolean stopPlayback = false;
		
		File soundFile = new File("C:/Documents and Settings/saciar/Mis documentos/bkp_db/pedro.wav");
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			
			audioFormat = audioInputStream.getFormat();
			
			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
			sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);		
			
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();
			
			byte tempBuffer[] = new byte[10000];
			int cnt;

			while((cnt = audioInputStream.read(tempBuffer,0,tempBuffer.length)) != -1
					&& stopPlayback == false){
				if(cnt > 0){
					sourceDataLine.write(
							tempBuffer, 0, cnt);
				}
			}
			sourceDataLine.drain();
			sourceDataLine.close();
			
			stopPlayback = false;
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (LineUnavailableException e) {		
			e.printStackTrace();
		}*/		

	}
	
	
	
	private void compileReports() {
		try {
			JasperCompileManager.compileReportToFile(reportsDir+"/of.xml",outputDir+"/of.jasper");
			JasperCompileManager.compileReportToFile(reportsDir+"/of_salas.xml",outputDir+"/of_salas.jasper");
			JasperCompileManager.compileReportToFile(reportsDir+"/of_servicios.xml",outputDir+"/of_servicios.jasper");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}

