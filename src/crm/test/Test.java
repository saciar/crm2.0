package crm.test;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import crm.client.managers.PresupuestosManager;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Number;

import net.sourceforge.barbecue.BarcodeException;

public class Test {
	
	//private static PartidoManager manager = PartidoManager.instance();
	private static PresupuestosManager manager = PresupuestosManager.instance();
	
	public static void main(String[] args){

		try {manager.buscarPresupuesto(3543);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		/*try {
			long time = System.currentTimeMillis();			
			Object[] res = VistaFamiliaServicioIdiomaManager.instance().getDescripcionByServicio("12");
			System.out.println("Tiempo de query: "+ (System.currentTimeMillis()-time)+" seg.");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}*/
		
		/*CodigoBarra c = new CodigoBarra();
		JFrame frame = new JFrame("Proyector de Datos y Video OPTIMA 759 DPL (3200 ANSI)");
		JPanel p = c.usingBarbecueAsSwingComponent("23");
		frame.getContentPane().add(p);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setVisible(true);*/
		/*CodigoBarra c = new CodigoBarra();
		try {
			c.outputtingBarcodeAsPNG("0123456789");
			c.imprimir("0123456789");
		} catch (BarcodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*JFrame frame = new JFrame("Prueba");
		JPanel p = new JPanel();	
		
		JList list = new JList();	
		JList list2 = new JList();
		
		Partido[] partidos = null;
		try {
			long time = System.currentTimeMillis();			
			 partidos = manager.findByProvinciaId("2");
			System.out.println("Tiempo de query: "+ (System.currentTimeMillis()-time));
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
		String[] nombres= new String[partidos.length];
		for (int i = 0; i < partidos.length; i++) {	
			nombres[i] = (partidos[i].getDescripcion());

		}
		
		list.setListData(nombres);
		
		Object[] partidos2 = null;
		try {
			long time2 = System.currentTimeMillis();			
			 
			partidos2 =manager.findNamesByProvinciaId("2");
			System.out.println("Tiempo de query mio: "+ (System.currentTimeMillis()-time2));
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
		String[] nombres2= new String[partidos2.length];
		for (int i = 0; i < partidos2.length; i++) {	
			Object[] categ = (Object[])partidos2[i];
			
			nombres2[i] = (categ[1].toString());
		}		
		
		list2.setListData(nombres2);
		
		JScrollPane scroll = new JScrollPane(list);
		JScrollPane scroll2 = new JScrollPane(list2);
		
		p.add(scroll);
		p.add(scroll2);
		
		frame.getContentPane().add(p);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setVisible(true);*/
		
	}

	
}
