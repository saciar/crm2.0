package crm.gui.pantalla.reportes;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;

import crm.client.managers.PresupuestosManager;
import crm.client.managers.SmsManager;
import crm.client.util.DateConverter;
import crm.client.util.DateDiff;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.JXDatePicker;
import crm.gui.pantalla.PantallaEmergenteGastoOperativo;
import crm.gui.pantalla.PantallaEmergenteGastoOperativoGral;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesRentabilidadItem;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesRentabilidadTableRender;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesRentabilidadTableModel;
import crm.gui.tablerenderer.gerencia.rentabilidad.real.ReporteHorasTableItem;

public class TabRentabilidad {

	private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;

    private javax.swing.JScrollPane jScrollPane1;

    private JXDatePicker jTextField1;
    private JXDatePicker jTextField2;
    
    private BuscadorReportesRentabilidadTableRender jTable1;
    ReportBuilder reportBuilder;
    
    private BuscadorReportes owner;
        
    private Map<Long,StringBuffer> todosDetallesReal = new LinkedHashMap<Long,StringBuffer>();
    
    private Map<Long,StringBuffer> todosDetallesPres = new LinkedHashMap<Long,StringBuffer>();
    
    private StringBuffer detallesGral = new StringBuffer();
    private double totalRealHsExtra100=0.0;
    private double totalRealHsExtra50=0.0;
    private double totalRealHs=0.0;
    private double totalRealArmado=0.0;
    private double totalRealOperacion=0.0;
    private double totalRealEquipos=0.0;
    private double totalPresHsExtra100=0.0;
    private double totalPresHsExtra50=0.0;
    private double totalPresHs=0.0;
    private double totalPresArmado=0.0;
    private double totalPresOperacion=0.0;
    private double totalPresEquipos=0.0;
    
    public TabRentabilidad(BuscadorReportes o){

    owner = o;	
    	
    jPanel1 = new javax.swing.JPanel();
    jPanel3 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jTextField1 = new JXDatePicker();
    jLabel2 = new javax.swing.JLabel();
    jTextField2 = new JXDatePicker();
    jPanel4 = new javax.swing.JPanel();

    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jPanel5 = new javax.swing.JPanel();
    jButton4 = new javax.swing.JButton();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    jButton5 = new javax.swing.JButton();
    
    jTable1 = new BuscadorReportesRentabilidadTableRender();
    jTable1.getTable().addMouseListener(new TabalMouseListener());
    
    jScrollPane1 = new JScrollPane();
    reportBuilder = new ReportBuilder();
    }
	
	public javax.swing.JPanel getJPanel1() {
		return jPanel1;
	}

	public void setJPanel1(javax.swing.JPanel panel1) {
		jPanel1 = panel1;
	}

	public void init_components(){
		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rango de Fechas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        jLabel1.setText("Desde");

        jLabel2.setText("Hasta");       
        
        jScrollPane1.setViewportView(jTable1.getTable());
        
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(588, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabla", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N
        
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Cant. registros");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Total $");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 526, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(147, 147, 147))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        jButton4.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));
        jButton4.setText("Buscar");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new BuscarActionListener());
        
        jButton1.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        jButton1.setText("Ver Reporte");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new VerReporteActionListener());
        
        jButton2.setIcon(new javax.swing.ImageIcon(getUrlImagen("page_excel.png")));
        jButton2.setText("Exportar");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new ExportarActionListener());
        
        jButton3.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        jButton3.setText("Salir");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new SalirActionListener());
		
        jButton5.setIcon(new javax.swing.ImageIcon(getUrlImagen("calculator.png")));
        jButton5.setText("Detalle Total");
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new CalcularActionListener());
        
        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton3))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton4))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jButton5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jButton4)
                .addGap(66, 66, 66)
                .addComponent(jButton1)
                .addGap(67, 67, 67)
                .addComponent(jButton2)
                .addGap(62, 62, 62)
                .addComponent(jButton3)
                .addGap(62, 62, 62)
                .addComponent(jButton5)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

	}
	
	public URL getUrlImagen(String imagen){

		URL url =Main.class.getResource("imagenes/"+imagen); 

		return url;
	}

	private class VerReporteActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {

			//reportBuilder.viewReport((BuscadorReportesRentabilidadTableModel)jTable1.getTable().getModel(),"Presupuestos del "+DateConverter.convertDateToString(jTextField1.getDate(),"EEEEE dd MMMMM yyyy")+" hasta  "+DateConverter.convertDateToString(jTextField2.getDate(),"EEEEE dd MMMMM yyyy"));

		}

	}

	private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			owner.dispose();			
		}

	}

	private class ExportarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {

				jTable1.openSavePopUp();
		}

	}
	
	private double round(double value, int decimalDigits) {
		
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(decimalDigits, BigDecimal.ROUND_UP);
	    return bd.doubleValue();
	}
	
	private Object[] ordenarHorarios(Object[] array)throws Exception{
		int i, j;

		Object[] temp;

		for (i = 1; i < array.length; i++){
			
			for (j = 0; j < array.length - i; j++) {
				Object[] p = (Object[]) array;
				Object[] horarioDato = (Object[]) p[j];
				Object[] horarioDato2 = (Object[]) p[j+1];
				
				Date fecha = DateConverter.convertStringToDate((String) horarioDato[0], "yyyy-MM-dd");
				Date fecha2 = DateConverter.convertStringToDate((String) horarioDato2[0], "yyyy-MM-dd");
				
				if (fecha2.before(fecha)) {
					temp = (Object[]) p[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		return array;

	}
	
	final double MILLSECS_PER_HOUR = 60 * 60 * 1000;
	private double getHoraExtraAl100(Date fechaEntrada, Date fechaSalida){
        double result=0.0;
        
        Calendar entrada = Calendar.getInstance();
        entrada.setTime(fechaEntrada);
        Calendar salida = Calendar.getInstance();
        salida.setTime(fechaSalida);
        //LA ENTRADA ES SABADO
        if(entrada.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
            Calendar sabadoMediodia = Calendar.getInstance();
            sabadoMediodia.setTime(fechaEntrada);
            sabadoMediodia.set(Calendar.HOUR_OF_DAY, 13);
            sabadoMediodia.set(Calendar.MINUTE, 0);
            sabadoMediodia.set(Calendar.SECOND, 0);
            sabadoMediodia.getTime();
            Calendar domingoNoche = Calendar.getInstance();
            domingoNoche.setTime(fechaEntrada);
            domingoNoche.add(Calendar.DAY_OF_MONTH, 1);
            domingoNoche.set(Calendar.HOUR_OF_DAY, 23);
            domingoNoche.set(Calendar.MINUTE, 59);
            domingoNoche.set(Calendar.SECOND, 59);
            domingoNoche.getTime();
            if(entrada.before(sabadoMediodia)){
                if(salida.after(sabadoMediodia)){
                    if(salida.before(domingoNoche)){
                        //CASO 2 --------|--(--*---)-|---------|-------
                        result=(salida.getTimeInMillis()-sabadoMediodia.getTimeInMillis())/MILLSECS_PER_HOUR;
                    }
                    else{
                        //CASO 6  --------|--(--*----|---------|--)----
                        result=(domingoNoche.getTimeInMillis()-sabadoMediodia.getTimeInMillis())/MILLSECS_PER_HOUR;
                    }
                }
                else{
                    //CASO 1  --------|-(--)-*----|---------|-------
                    result=0;
                }                
            }
            else{
                if(salida.before(domingoNoche)){
                    //CASO 3  --------|----*-(--)-|---------|-------
                    result=(salida.getTimeInMillis()-entrada.getTimeInMillis())/MILLSECS_PER_HOUR;
                }
                else{
                    //CASO 5  --------|----*---(-|---------|----)---
                    result=(domingoNoche.getTimeInMillis()-entrada.getTimeInMillis())/MILLSECS_PER_HOUR;
                }
            }
        }
        //LA ENTRADA ES DOMINGO
        else if(entrada.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
            Calendar domingoNoche = Calendar.getInstance();
            domingoNoche.setTime(fechaEntrada);
            domingoNoche.set(Calendar.HOUR_OF_DAY, 23);
            domingoNoche.set(Calendar.MINUTE, 59);
            domingoNoche.set(Calendar.SECOND, 59);
            if(salida.before(domingoNoche)){
                //CASO 1 --------|----*----|---(---)---|-------
                result=(salida.getTimeInMillis()-entrada.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
            else{
                //CASO 2 --------|----*----|---(------|---)----
                result=(domingoNoche.getTimeInMillis()-entrada.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
        }
        // LA ENTRADA NO ES SABADO NI DOMINGO PERO LA SALIDA ES SABADO
        else if(entrada.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY && entrada.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY && salida.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
            Calendar sabadoMediodia = Calendar.getInstance();
            sabadoMediodia.setTime(fechaSalida);
            sabadoMediodia.set(Calendar.HOUR_OF_DAY, 13);
            sabadoMediodia.set(Calendar.MINUTE, 0);
            sabadoMediodia.set(Calendar.SECOND, 0);
            if(salida.after(sabadoMediodia)){
                //CASO 1 -----(---|----*--)--|---------|-------
                result=(salida.getTimeInMillis()-sabadoMediodia.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
        }
        // LA ENTRADA NO ES SABADO NI DOMINGO PERO LA SALIDA ES DOMINGO
        else if(entrada.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY && entrada.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY && salida.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
            Calendar domingoNoche = Calendar.getInstance();
            domingoNoche.setTime(fechaSalida);
            domingoNoche.set(Calendar.HOUR_OF_DAY, 23);
            domingoNoche.set(Calendar.MINUTE, 59);
            
            Calendar sabadoMediodia = Calendar.getInstance();
            sabadoMediodia.setTime(fechaSalida);
            sabadoMediodia.add(Calendar.DAY_OF_MONTH, -1);
            sabadoMediodia.set(Calendar.HOUR_OF_DAY, 13);
            sabadoMediodia.set(Calendar.MINUTE, 0);
            sabadoMediodia.set(Calendar.SECOND, 0);
            
            domingoNoche.set(Calendar.SECOND, 59);
            if(salida.before(domingoNoche)){
                //CASO 2 -----(---|----*----|----)-----|-------
                result=(sabadoMediodia.getTimeInMillis()-salida.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
            else{
                //CASO 3 -----(---|----*----|---------|----)---
                result=(domingoNoche.getTimeInMillis()-sabadoMediodia.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
        }
            
        return result;
    }
	
	public double redondear(double numero) {       
        return Math.rint(numero * 10) / 10;
    }
    
    public String darFormatoHs(double numero){
        double entero=(int)numero;
        double decimal = redondear(numero - entero);
        String result = String.valueOf((int)entero)+":"+String.valueOf((int)(decimal *60));
        return result;
    }
	
    private double[] horasPresupuestadas(Object[] horarios, String fechaDesde, String fechaHasta, long hsPruebas ){
    	double totalComun=0.0;
	    double totalExtra=0.0;
	    double totalExtra100=0.0;
		double[] total = new double[3];

		ReporteHorasTableItem item = new ReporteHorasTableItem();
		
		for (int i = 0; i < horarios.length; i++) {
			Object[] p = (Object[]) horarios;
			Object[] horarioDato = (Object[]) p[i];
			long dif=0;
			double extra100;
			try {
			if((String) horarioDato[0] != null && (String) horarioDato[1] !=null && (String) horarioDato[2]!=null){
				dif = DateDiff.calcularHorasTotales((String) horarioDato[0]+" "+ (String) horarioDato[1]+":00",
					(String) horarioDato[0]+" "+  (String) horarioDato[2]+":00");
				extra100 = getHoraExtraAl100(DateConverter.convertStringToDate((String) horarioDato[0]+" "+ (String) horarioDato[1]+":00", "yyyy-MM-dd hh:mm:ss"), DateConverter.convertStringToDate((String) horarioDato[0]+" "+  (String) horarioDato[2]+":00", "yyyy-MM-dd hh:mm:ss"));
			}
			else{
				extra100 = getHoraExtraAl100(DateConverter.convertStringToDate((String)horarioDato[0]+" 00:00:00", "yyyy-MM-dd hh:mm:ss"), DateConverter.convertStringToDate((String) horarioDato[0]+" 00:00:00", "yyyy-MM-dd hh:mm:ss"));
			}
			
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				extra100=0;
			}
			//si es el primer dia
			if (i == horarios.length-1) {
				
				if(extra100>0){
                    item.setHsExtras100(darFormatoHs(redondear(extra100)));
                    item.setHsExtrasDecimales100(redondear(extra100));
                    item.setHsExtras(darFormatoHs(redondear(dif + hsPruebas - extra100)));
                    item.setHsExtrasDecimales(redondear(dif + hsPruebas - extra100));
                }
				else if (dif + hsPruebas <= 9) {
                    item.setHsNormales(darFormatoHs(redondear(dif + hsPruebas)));
                    item.setHsNormalesDecimales(redondear(dif + hsPruebas));                                               
                    item.setHsExtras("0:00");
                    item.setHsExtrasDecimales(0.00);
                } else {
                        item.setHsNormales("9:00");
                        item.setHsNormalesDecimales(9.00);
                        item.setHsExtras(darFormatoHs(redondear(dif + hsPruebas - 9)));
                        item.setHsExtrasDecimales(redondear(dif + hsPruebas - 9));
                }
			}
			else{
				if(extra100>0){
                    item.setHsExtras100(darFormatoHs(redondear(extra100)));
                    item.setHsExtrasDecimales100(redondear(extra100));
                    item.setHsExtras(darFormatoHs(redondear(dif - extra100)));
                    item.setHsExtrasDecimales(redondear(dif - extra100));
                }
				else if (dif  <= 9) {
                    item.setHsNormales(darFormatoHs(redondear(dif )));
                    item.setHsNormalesDecimales(redondear(dif ));                                               
                    item.setHsExtras("0:00");
                    item.setHsExtrasDecimales(0.00);
                } else {
                        item.setHsNormales("9:00");
                        item.setHsNormalesDecimales(9.00);
                        item.setHsExtras(darFormatoHs(redondear(dif - 9)));
                        item.setHsExtrasDecimales(redondear(dif - 9));
                }
			}
			
		}
		try {							
			DateDiff dateDiff = DateDiff.calcularFechas(DateConverter.convertStringToDate(fechaDesde, "yyyy-MM-dd"), DateConverter.convertStringToDate(fechaHasta, "yyyy-MM-dd"));
    		int diasDeSala = dateDiff.getDayOnly()+1;
    		if(horarios.length<diasDeSala && horarios.length!=0){
    			int noCargado = horarios.length-diasDeSala;    		
    			item.setHsNormales(darFormatoHs(redondear(noCargado*9)));
                item.setHsNormalesDecimales(redondear(noCargado*9));
    		}
    		else if (horarios.length==0){    			
    			item.setHsNormales(darFormatoHs(redondear(diasDeSala*9)));
                item.setHsNormalesDecimales(redondear(diasDeSala*9));
    		}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			item.setHsNormales(darFormatoHs(redondear(0)));
            item.setHsNormalesDecimales(redondear(0));
            
		}
		
		totalComun += redondear(item.getHsNormalesDecimales());
        totalExtra += redondear(item.getHsExtrasDecimales());
        totalExtra100 += redondear(item.getHsExtrasDecimales100());
        total[0]=totalComun;
        total[1]=totalExtra;
        total[2]=totalExtra100;

		return total;
    }
    
	private double[] horasReales(Long nroppto){
		double totalComun=0.0;
	    double totalExtra=0.0;
	    double totalExtra100=0.0;

		double[] total = new double[4];
		SimpleDateFormat formatoVisible = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Object[] result = null;
		try {
			result = SmsManager.instance().buscarSmsPorNroPptoLiqTodos(nroppto);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < result.length; i++) {
            Object[] sms = (Object[]) result[i];
            ReporteHorasTableItem item = new ReporteHorasTableItem();
            if((Long) sms[0] ==1)
                item.setOs((String)sms[2]);
            else
                item.setOs(((Long) sms[0]).toString());

            String fechaEntrada = formatoVisible.format(((GregorianCalendar) sms[1]).getTime());                
            item.setDesde(fechaEntrada);
            item.setNombre((String) sms[5]);

            String fechaSalida = "";
            if ((GregorianCalendar) sms[6] != null) {
                fechaSalida = formatoVisible.format(((GregorianCalendar) sms[6]).getTime());
                item.setHasta(fechaSalida);
                double df = (double) ((double) (((GregorianCalendar) sms[6]).getTimeInMillis() - ((GregorianCalendar) sms[1]).getTimeInMillis()) / MILLSECS_PER_HOUR);
                double extra100 = getHoraExtraAl100(((GregorianCalendar) sms[1]).getTime(), ((GregorianCalendar) sms[6]).getTime());
                if(extra100>0){
                    item.setHsExtras100(darFormatoHs(redondear(extra100)));
                    item.setHsExtrasDecimales100(redondear(extra100));
                    item.setHsExtras(darFormatoHs(redondear(df - extra100)));
                    item.setHsExtrasDecimales(redondear(df - extra100));
                }
                else if (df <= 9) {
                    item.setHsNormales(darFormatoHs(redondear(df)));
                    item.setHsNormalesDecimales(redondear(df));                                               
                    item.setHsExtras("0:00");
                    item.setHsExtrasDecimales(0.00);
                } else {
                        item.setHsNormales("9:00");
                        item.setHsNormalesDecimales(9.00);
                        item.setHsExtras(darFormatoHs(redondear(df - 9)));
                        item.setHsExtrasDecimales(redondear(df - 9));
                }
            } else {
                item.setHasta("");
                double df = 9.0d;
                item.setHsNormales(darFormatoHs(redondear(df)));
                item.setHsNormalesDecimales(redondear(df));
                item.setHsExtras("0:00");
                item.setHsExtrasDecimales(0.00);

            }
            totalComun += redondear(item.getHsNormalesDecimales());
            totalExtra += redondear(item.getHsExtrasDecimales());
            totalExtra100 += redondear(item.getHsExtrasDecimales100());
            total[0]=totalComun;
            total[1]=totalExtra;
            total[2]=totalExtra100;
            //}
        }
		return total;
	}
	
	private double calcularCOReal(String nroppto){
		try {
			StringBuffer detalle =  new StringBuffer();
			
			Object[] presupuestos = null;

			presupuestos = PresupuestosManager.instance().buscarParaReportesRentabilidadCostos(nroppto);

			double armado = 0.0;
			double operacion = 0.0;
			double cantPersonasArm = 0.0;
			double cantPersonasOp = 0.0;
			double totalCostoEquipo =0.0;

			double hsExtras=0;
			double hsExtrasAl50=0;
			double hsNormales=0;
			long hsPruebas =0;
			
			long codServicio=0;
			int cantidad=0;
			String fechaDesde="";
			String fechaHasta="";
			double armadoXServ=0d;
			double costoHsHombreXServ=0d;				
			double operacionXServ=0d;
			double costoUnitXServ=0d;
			double duracionXServ=0d;
			double horasFijas=0d;				
			double costoHsHombreNormal=0d;
			double costoHsHombreExtra=0d;
			double costoHsHombreExtraAl50=0d;
			double horasPruebasXDefault=0d;
			String fechaPrueba="";				
			String fechaDesarme="";			
			
			if(presupuestos != null){
				//System.out.println("-----------------------------------PPTO "+nroppto+" REAL--------------------------------------------------------------------------");
				detalle.append("------------------------Presupuesto "+nroppto+" REAL------------------------------\n");
				detalle.append("-----------------------------------------------------------------------------\n");
				for(int i=0; i<presupuestos.length; i++){
					Object[] p = (Object[]) presupuestos;
					Object[] presupuestoDato = (Object[])p[i];
					
					codServicio = (Long)presupuestoDato[0];
					cantidad = (Integer)presupuestoDato[2];
					fechaDesde = (String)presupuestoDato[3];
						fechaHasta = (String)presupuestoDato[4];
					armadoXServ = (Double)presupuestoDato[5];
					costoHsHombreXServ = (Double)presupuestoDato[6];
					operacionXServ = (Double)presupuestoDato[7];
					costoUnitXServ = (Double)presupuestoDato[8];
					duracionXServ = (Double)presupuestoDato[9];
					horasFijas = (Double)presupuestoDato[10]; //     horas de carga de camion, transalado, etc = 5.83
					costoHsHombreNormal = (Double)presupuestoDato[11]; //    valor de la hora normal trabajada = 54.55
					costoHsHombreExtra = (Double)presupuestoDato[12]; //    valor de la hora extra al 100 trabajada = 178.95
					costoHsHombreExtraAl50 = 134.21d;					// valor de la hora extra al 50 trabajada= 134.21
					horasPruebasXDefault = (Double)presupuestoDato[13]; //  cant de horas de pruebas si no tiene cargada horas de pruebas
					fechaPrueba = (String)presupuestoDato[14];
					fechaDesarme = (String)presupuestoDato[15];
					
					//ARMADO
					//System.out.println("Servicio cod. "+codServicio+"------------------------------");
					detalle.append("------------------------Servicio cod. "+codServicio+"------------------------------\n");
					//System.out.println("-----------------------------------------------------------");
					//System.out.println("Cant.item: "+cantidad+"\n");
					detalle.append("Cant.item: "+cantidad+"\n");
					//System.out.println("Campo Armado: "+armadoXServ);
					detalle.append("Campo Armado: "+armadoXServ+"\n");
					double personaNecesarias = cantidad * armadoXServ;
					//System.out.println("personas necesarias armado: "+personaNecesarias);
					detalle.append("personas necesarias armado: "+personaNecesarias+"\n");
					cantPersonasArm+=personaNecesarias;

					//OPERACION
					//System.out.println("Campo operacion: "+operacionXServ);
					detalle.append("Campo operacion: "+operacionXServ+"\n");
					double personasNecesariasOp = cantidad * operacionXServ;
					//System.out.println("personas necesarias Operacion: "+personasNecesariasOp);
					detalle.append("personas necesarias Operacion: "+personasNecesariasOp+"\n");
					cantPersonasOp+=personasNecesariasOp;					
					
					//COSTO EQUIPO
					hsPruebas = DateDiff.calcularHorasTotales(fechaPrueba, fechaDesde);
					if(hsPruebas<=0){
						hsPruebas = ((Double)horasPruebasXDefault).longValue();
					}
					
					long cantHorasTotales = DateDiff.calcularHorasTotales(fechaDesde, fechaHasta);
					//System.out.println("Cant. de horas totales de equipo funcionando: "+cantHorasTotales);
					detalle.append("Cant. de horas totales de equipo funcionando: "+cantHorasTotales+"\n");
					double valCURedondeado=0;
					if(duracionXServ != 0 ){
						//System.out.println("Costo unitario x hora: "+(Double)presupuestoDato[8] / (Double)presupuestoDato[9]);
						valCURedondeado= (costoUnitXServ / duracionXServ) * (cantHorasTotales+horasFijas+hsPruebas);
					}
					else{
						//System.out.println("Costo unitario x hora: 0.0");
						valCURedondeado=0;
					}
					//System.out.println("Costo unitario: "+round(valCURedondeado,2));
					detalle.append("Costo unitario: "+round(valCURedondeado,2)+"\n\n");
					totalCostoEquipo+=round(valCURedondeado,2);
					
				}
											
				double[] hs = horasReales(Long.valueOf(nroppto));
				
				double cantHorasNormalesOp =+ hs[0];
				double cantHorasExtrasOpAl50 =+ hs[1];
				double cantHorasExtrasOp =+ hs[2];
				
				//System.out.println("---------------------------------------------------------------------------------------");
				detalle.append("---------------------------------------------------------------------------------------\n");
				//System.out.println("Cant. de horas de operacion normales totales: "+cantHorasNormalesOp);
				detalle.append("Cant. de horas de operacion normales totales: "+cantHorasNormalesOp+"\n");
				//System.out.println("Cant. de horas de operacion extras al 50% totales: "+cantHorasExtrasOpAl50);
				detalle.append("Cant. de horas de operacion extras al 50% totales: "+cantHorasExtrasOpAl50+"\n");
				//System.out.println("Cant. de horas de operacion extras al 100% totales: "+cantHorasExtrasOp);
				detalle.append("Cant. de horas de operacion extras al 100% totales: "+cantHorasExtrasOp+"\n");
				
				
				//System.out.println("-----------------------------------------------------------");
				//System.out.println("-----------------------------------------------------------");
				armado=round(cantPersonasArm,0) * costoHsHombreNormal * horasFijas;
				
				
				hsNormales = round(cantHorasNormalesOp * costoHsHombreNormal,2);
				hsExtras = round(cantHorasExtrasOp * costoHsHombreExtra,2);						
				//hsExtrasAl50 = round(cantHorasExtrasOpAl50 * (costoHsHombreNormal * 1.5),2);
				hsExtrasAl50 = round(cantHorasExtrasOpAl50 * costoHsHombreExtraAl50,2);				
				
				operacion = round(hsExtras + hsExtrasAl50 + hsNormales,2);
				
				//System.out.println("Cant de $ de hs extras: $"+hsExtras);
				detalle.append("Cant de $ de hs extras: $"+hsExtras+"\n");
				totalRealHsExtra100+=hsExtras;
				//System.out.println("Cant de $ de hs extras al 50%: $"+hsExtrasAl50);
				detalle.append("Cant de $ de hs extras al 50%: $"+hsExtrasAl50+"\n");
				totalRealHsExtra50+=hsExtrasAl50;
				//System.out.println("Cant de $ de hs normales: $"+hsNormales+"\n");
				detalle.append("Cant de $ de hs normales: $"+hsNormales+"\n\n");
				totalRealHs+=hsNormales;
				//System.out.println("---------------------------------------------------------------------------------------");
				detalle.append("---------------------------------------------------------------------------------------\n");
				//System.out.println("-------------------------------------TOTALES-------------------------------------------");
				detalle.append("-------------------------------------TOTALES-------------------------------------------\n");
				
				/*System.out.println("PERSONAS NECESARIAS ARMADO: "+round(cantPersonasArm,0));
				detalle.append("PERSONAS NECESARIAS ARMADO: "+round(cantPersonasArm,0)+"\n");
				System.out.println("PERSONAS NECESARIAS OP: "+cantPersonasOp);
				detalle.append("PERSONAS NECESARIAS OP: "+cantPersonasOp+"\n");*/
				//System.out.println("TOTAL ARMADO EVENTO: "+armado);
				detalle.append("TOTAL ARMADO EVENTO: "+armado+"\n");
				//System.out.println("TOTAL OPERACION EVENTO: "+operacion);
				detalle.append("TOTAL OPERACION EVENTO: "+operacion+"\n");
				//System.out.println("TOTAL COSTO EQUIPOS: "+totalCostoEquipo);
				detalle.append("TOTAL COSTO EQUIPOS: "+totalCostoEquipo+"\n");
				
				//System.out.println("-------------------------------------------------------------------------------------");
				detalle.append("-----------------------------------------------------------------------------------------\n");
				
				double total = armado + operacion + totalCostoEquipo;
				//System.out.println("TOTAL --------> "+total);
				detalle.append("TOTAL --------> "+total+"\n");
				
				todosDetallesReal.put(Long.valueOf(nroppto), detalle);
				
				return total;
			}
			else
				return -1.0d;
		}catch (RemoteException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			return -1.0d;
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			//System.out.println("numero de presupuesto: "+nroppto);
			exc.printStackTrace();
			return -1.0d;
		}
	}	
	
	private double calcularCO(String nroppto){
		try {
			StringBuffer detalle =  new StringBuffer();
			
			Object[] presupuestos = null;
			Object[] horarios = null;
			Object[] horariosSinOrd = null;
			presupuestos = PresupuestosManager.instance().buscarParaReportesRentabilidadCostos(nroppto);
			horariosSinOrd = PresupuestosManager.instance().buscarHorariosCostos(nroppto);
			horarios = ordenarHorarios(horariosSinOrd);
			double armado = 0.0;
			double operacion = 0.0;
			double cantPersonasArm = 0.0;
			double cantPersonasOp = 0.0;
			double totalCostoEquipo =0.0;

			double hsExtras=0;
			double hsExtrasAl50=0;
			double hsNormales=0;
			long hsPruebas =0;
			
			long codServicio=0;
			int cantidad=0;
			String fechaDesde="";
			String fechaHasta="";
			double armadoXServ=0d;
			double costoHsHombreXServ=0d;				
			double operacionXServ=0d;
			double costoUnitXServ=0d;
			double duracionXServ=0d;
			double horasFijas=0d;				
			double costoHsHombreNormal=0d;
			double costoHsHombreExtra=0d;
			double horasPruebasXDefault=0d;
			String fechaPrueba="";				
			String fechaDesarme="";			
			
			if(presupuestos != null){
				//System.out.println("-----------------------------------PPTO "+nroppto+"--------------------------------------------------------------------------");
				detalle.append("------------------------Presupuesto "+nroppto+" Presupuestado------------------------------\n");
				detalle.append("-----------------------------------------------------------------------------\n");
				for(int i=0; i<presupuestos.length; i++){
					Object[] p = (Object[]) presupuestos;
					Object[] presupuestoDato = (Object[])p[i];
					
					codServicio = (Long)presupuestoDato[0];
					cantidad = (Integer)presupuestoDato[2];
					fechaDesde = (String)presupuestoDato[3];
						fechaHasta = (String)presupuestoDato[4];
					armadoXServ = (Double)presupuestoDato[5];
					costoHsHombreXServ = (Double)presupuestoDato[6];
					operacionXServ = (Double)presupuestoDato[7];
					costoUnitXServ = (Double)presupuestoDato[8];
					duracionXServ = (Double)presupuestoDato[9];
					horasFijas = (Double)presupuestoDato[10]; //     horas de carga de camion, transalado, etc = 5.83
					costoHsHombreNormal = (Double)presupuestoDato[11]; //    valor de la hora normal trabajada = 54.55
					costoHsHombreExtra = (Double)presupuestoDato[12]; //    valor de la hora extra trabajada = 109.1
					horasPruebasXDefault = (Double)presupuestoDato[13]; //  cant de horas de pruebas si no tiene cargada horas de pruebas
					fechaPrueba = (String)presupuestoDato[14];
					fechaDesarme = (String)presupuestoDato[15];
					
					//ARMADO
					//System.out.println("Servicio cod. "+codServicio+"------------------------------");
					detalle.append("------------------------Servicio cod. "+codServicio+"------------------------------\n");
					//System.out.println("-----------------------------------------------------------");
					//System.out.println("Cant.item: "+cantidad+"\n");
					detalle.append("Cant.item: "+cantidad+"\n");
					//System.out.println("Campo Armado: "+armadoXServ);
					detalle.append("Campo Armado: "+armadoXServ+"\n");
					//double valRedondeado = round((Integer)presupuestoDato[2] * (Double)presupuestoDato[5],0);
					double personaNecesarias = cantidad * armadoXServ;
					//System.out.println("personas necesarias armado: "+personaNecesarias);
					detalle.append("personas necesarias armado: "+personaNecesarias+"\n");
					cantPersonasArm+=personaNecesarias;

					//OPERACION
					//System.out.println("Campo operacion: "+operacionXServ);
					detalle.append("Campo operacion: "+operacionXServ+"\n");
					double personasNecesariasOp = cantidad * operacionXServ;
					//System.out.println("personas necesarias Operacion: "+personasNecesariasOp);
					detalle.append("personas necesarias Operacion: "+personasNecesariasOp+"\n");
					cantPersonasOp+=personasNecesariasOp;						
					
					//COSTO EQUIPO
					hsPruebas = DateDiff.calcularHorasTotales(fechaPrueba, fechaDesde);
					if(hsPruebas<=0){
						hsPruebas = ((Double)horasPruebasXDefault).longValue();
					}
					
					long cantHorasTotales = DateDiff.calcularHorasTotales(fechaDesde, fechaHasta);
					//System.out.println("Cant. de horas totales de equipo funcionando: "+cantHorasTotales);
					detalle.append("Cant. de horas totales de equipo funcionando: "+cantHorasTotales+"\n");
					double valCURedondeado=0;
					if(duracionXServ != 0 ){						
						valCURedondeado= (costoUnitXServ / duracionXServ) * (cantHorasTotales+horasFijas+hsPruebas);
					}
					else{
						valCURedondeado=0;
					}
					//System.out.println("Costo unitario: "+round(valCURedondeado,2));
					detalle.append("Costo unitario: "+round(valCURedondeado,2)+"\n\n");
					totalCostoEquipo+=round(valCURedondeado,2);
					
				}
				 
				double[] hs = horasPresupuestadas(horarios,fechaDesde,fechaHasta,hsPruebas);
				
				double cantHorasNormalesOp =+ hs[0];
				double cantHorasExtrasOp =+ hs[2];
				double cantHorasExtrasOpAl50 =+ hs[1];
				
				//System.out.println("---------------------------------------------------------------------------------------");
				detalle.append("---------------------------------------------------------------------------------------\n");
				//System.out.println("Cant. de horas de operacion normales totales: "+cantHorasNormalesOp);
				detalle.append("Cant. de horas de operacion normales totales: "+cantHorasNormalesOp+"\n");
				//System.out.println("Cant. de horas de operacion extras al 50% totales: "+cantHorasExtrasOpAl50);
				detalle.append("Cant. de horas de operacion extras al 50% totales: "+cantHorasExtrasOpAl50+"\n");
				//System.out.println("Cant. de horas de operacion extras totales: "+cantHorasExtrasOp);
				detalle.append("Cant. de horas de operacion extras totales: "+cantHorasExtrasOp+"\n");
				
				
				//System.out.println("-----------------------------------------------------------");
				//System.out.println("-----------------------------------------------------------");
				armado=round(cantPersonasArm,0) * costoHsHombreNormal * horasFijas;
				
				
				hsNormales = cantHorasNormalesOp * costoHsHombreNormal;
				hsExtras = cantHorasExtrasOp * costoHsHombreExtra;						
				hsExtrasAl50 = cantHorasExtrasOpAl50 * costoHsHombreExtra/2;
				
				operacion = round(cantPersonasOp,0) * (hsExtras + hsExtrasAl50 + hsNormales);
				
				//System.out.println("Cant de $ de hs extras: $"+hsExtras);
				detalle.append("Cant de $ de hs extras: $"+hsExtras+"\n");
				totalPresHsExtra100+=hsExtras;
				//System.out.println("Cant de $ de hs extras al 50%: $"+hsExtrasAl50);
				detalle.append("Cant de $ de hs extras al 50%: $"+hsExtrasAl50+"\n");
				totalPresHsExtra50+=hsExtrasAl50;
				//System.out.println("Cant de $ de hs normales: $"+hsNormales+"\n");
				detalle.append("Cant de $ de hs normales: $"+hsNormales+"\n\n");
				totalPresHs+=hsNormales;
				//System.out.println("---------------------------------------------------------------------------------------");
				detalle.append("---------------------------------------------------------------------------------------\n");
				///System.out.println("-------------------------------------TOTALES-------------------------------------------");
				detalle.append("-------------------------------------TOTALES-------------------------------------------\n");
				
				//System.out.println("PERSONAS NECESARIAS ARMADO: "+round(cantPersonasArm,0));
				detalle.append("PERSONAS NECESARIAS ARMADO: "+round(cantPersonasArm,0)+"\n");
				//System.out.println("PERSONAS NECESARIAS OP: "+cantPersonasOp);
				detalle.append("PERSONAS NECESARIAS OP: "+cantPersonasOp+"\n");
				//System.out.println("TOTAL ARMADO EVENTO: "+armado);
				detalle.append("TOTAL ARMADO EVENTO: "+armado+"\n");
				//System.out.println("TOTAL OPERACION EVENTO: "+operacion);
				detalle.append("TOTAL OPERACION EVENTO: "+operacion+"\n");
				//System.out.println("TOTAL COSTO EQUIPOS: "+totalCostoEquipo);
				detalle.append("TOTAL COSTO EQUIPOS: "+totalCostoEquipo+"\n");
				
				//System.out.println("-------------------------------------------------------------------------------------");
				detalle.append("-----------------------------------------------------------------------------------------\n");
				
				double total = armado + operacion + totalCostoEquipo;
				//System.out.println("TOTAL --------> "+total);
				detalle.append("TOTAL --------> "+total+"\n");
				
				todosDetallesPres.put(Long.valueOf(nroppto), detalle);
				
				return total;
			}
			else
				return -1.0d;
		}catch (RemoteException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			return -1.0d;
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			//System.out.println("numero de presupuesto: "+nroppto);
			exc.printStackTrace();
			return -1.0d;
		}
	}	
	
	private class CalcularActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			PantallaEmergenteGastoOperativoGral pantalla = new PantallaEmergenteGastoOperativoGral(Main.getVentana(),TabRentabilidad.this);

				pantalla.setObservacionesText(detallesGral.toString());
				pantalla.setVisible(true);
				
		}
		
	}
	
	public void resetDetallesGrales(){
		detallesGral=new StringBuffer();
		totalRealHsExtra100=0.0;
	    totalRealHsExtra50=0.0;
	    totalRealHs=0.0;
	    totalRealArmado=0.0;
	    totalRealOperacion=0.0;
	    totalRealEquipos=0.0;
	    totalPresHsExtra100=0.0;
	    totalPresHsExtra50=0.0;
	    totalPresHs=0.0;
	    totalPresArmado=0.0;
	    totalPresOperacion=0.0;
	    totalPresEquipos=0.0;
	}
	
	public BuscadorReportesRentabilidadItem getSelectedPpto(){
		JTable table = jTable1.getTable();
		//SalaServiciosTableModel model = (SalaServiciosTableModel)table.getModel();
		BuscadorReportesRentabilidadTableModel model =(BuscadorReportesRentabilidadTableModel)table.getModel();
		if (table.getSelectedRow()>=0){
			BuscadorReportesRentabilidadItem item = model.getRow(table.getSelectedRow());
			return item;
		}
		else{
			Util.alertMsg(Main.getVentana(), "Seleccione un item de la tabla de presupuestos");
			return null;
		}
	}

	private class BuscarActionListener implements ActionListener{
		private String codCliente;
		private String codClienteFact;
		private String fechaDesde;
		private String fechaHasta;
		private String codLugar;
		private String[] codVendedores;
		private String codCondPago;
		private String estado;
		private String codTipoEvento;
		private String codServicio;

		private void setCriteria(){

			fechaDesde = DateConverter.convertDateToString(jTextField1.getDate(), "yyyy-MM-dd");
			fechaHasta = DateConverter.convertDateToString(jTextField2.getDate(), "yyyy-MM-dd");
			
			jTable1.setComentariosXLS("Fecha desde "+fechaDesde+" hasta "+fechaHasta);

			if(owner.getTabCriterios().getCliente_check().isSelected() && owner.getTabCriterios().getClienteElegido() != null){
				codCliente = owner.getTabCriterios().getClienteElegido().getCodigo();
				
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Cliente: "+owner.getTabCriterios().getClienteElegido().getEmpresa());
			}
			else
				codCliente=null;
			if(owner.getTabCriterios().getLugar_check().isSelected() && owner.getTabCriterios().getLugarElegido()!=null){
				codLugar= owner.getTabCriterios().getLugarElegido().getCodigo();
				
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Lugar: "+owner.getTabCriterios().getLugarElegido().getNombreLugar());
			}
			else
				codLugar=null;
			if(owner.getTabCriterios().getEstado_check().isSelected()){
				estado= (String)owner.getTabCriterios().getEstados().getSelectedItem();
				
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Estado: "+estado);
			}
			else
				estado=null;
			if(owner.getTabCriterios().getVendedores_check().isSelected()){
				codVendedores=owner.getTabCriterios().getListaVendedores().searchForeignsArray();
				
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Vendedores: ");
				for(int i=0; i<owner.getTabCriterios().getListaVendedores().getSelectedValues().length;i++){
					if(i!=0)
						jTable1.setComentariosXLS(jTable1.getComentariosXLS()+",");
					jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" "+(String)owner.getTabCriterios().getListaVendedores().getSelectedValues()[i]);
				}
			}
			else
				codVendedores =null;
			if(owner.getTabCriterios().getCondiciones_check().isSelected()){
				codCondPago = owner.getTabCriterios().getCondicionesPago().searchForeign();
				
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Cond. de pago: "+(String)owner.getTabCriterios().getCondicionesPago().getSelectedItem());
			}
			else
				codCondPago=null;
			if(owner.getTabCriterios().getServicios_check().isSelected()){
				codServicio=owner.getTabCriterios().getServicioElegido();
				
				//jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Servicio: "+jLabel7.getText());
			}
			else
				codServicio= null;		

		}
		
		
		
		private void buscarReportes(){
			try {
				Object[] presupuestos = null;
				BuscadorReportesRentabilidadTableModel model = new BuscadorReportesRentabilidadTableModel();		
				StringBuffer vendedores = new StringBuffer();			
				resetDetallesGrales();
				setCriteria();
				if(codVendedores != null){
					for(int j=0;j<codVendedores.length;j++){
						vendedores.append(codVendedores[j]);
						if(j<codVendedores.length-1)
							vendedores.append(",");
					}
				}
				presupuestos = PresupuestosManager.instance().buscarParaReportesRentabilidad(codCliente, codClienteFact,fechaDesde,fechaHasta,codLugar,codCondPago,estado,codTipoEvento,codServicio,vendedores.toString());

				double tot=0;
				int cant=0;
				if(presupuestos != null){
					for(int i=0; i<presupuestos.length; i++){
						long l=System.currentTimeMillis();
						BuscadorReportesRentabilidadItem item = new BuscadorReportesRentabilidadItem();

						Object[] p = (Object[]) presupuestos;
						Object[] presupuestoDato = (Object[])p[i];

						cant++;
						item.setNumeroPpto((Long)(presupuestoDato[0]));
						item.setCostoOperativo(calcularCO(String.valueOf(presupuestoDato[0])));
						item.setCostoOperativoReal(calcularCOReal(String.valueOf(presupuestoDato[0])));
						if(presupuestoDato[5] != null)
							item.setGastosSubcontratados(((Double)presupuestoDato[5]).doubleValue());
						else{
							item.setGastosSubcontratados(0);
						}
						item.setOtros(((Double)presupuestoDato[6]).doubleValue());		
						item.setComisionLugar(((Double)presupuestoDato[7]).doubleValue());
						item.setComisionTerceros(((Double)presupuestoDato[8]).doubleValue());
						item.setTotalFacturado(((Double)presupuestoDato[3]).doubleValue());
						item.setComisionComercial(((Double)presupuestoDato[9]).doubleValue());
						item.setRegalias(((Double)presupuestoDato[10]).doubleValue());
						item.setNombreEvento((String)presupuestoDato[2]);
						item.setVendedor((String)presupuestoDato[1]);
						double totalGastos = item.getGastosSubcontratados()+item.getOtros()+item.getCostoOperativo();
						double totalGastosReal = item.getGastosSubcontratados()+item.getOtros()+item.getCostoOperativoReal();
						double totalComisions = item.getComisionComercial()+item.getComisionLugar()+item.getComisionTerceros()+item.getRegalias();
						item.setRentabilidad(item.getTotalFacturado()-(totalGastos+totalComisions));
						item.setRentabilidadReal(item.getTotalFacturado()-(totalGastosReal+totalComisions));
						if(item.getTotalFacturado()!=0){
							item.setMargen(round(item.getRentabilidad()/item.getTotalFacturado(),2));
							item.setMargenReal(round(item.getRentabilidadReal()/item.getTotalFacturado(),2));
						}
						else{
							item.setMargen(-1000);
							item.setMargenReal(-1000);
						}
						tot=tot+(Double)presupuestoDato[3];
						model.addRow(item);
						jLabel3.setText("Cant. registros: "+cant);
						jLabel4.setText("Total: "+getTotalFormateado(tot));
						System.out.println("Tiempo en ppto "+item.getNumeroPpto()+":"+((System.currentTimeMillis()-l)/1000)+" seg.");

					}	
					detallesGral.append("TOTALES \n");
					detallesGral.append("Real:\n");
					detallesGral.append("	Total hs extras al 100%: "+totalRealHsExtra100+"\n");
					detallesGral.append("	Total hs extras al 50%: "+totalRealHsExtra50+"\n");
					detallesGral.append("	Total hs normales: "+totalRealHs+"\n\n");
					detallesGral.append("Presupuestado:\n");
					detallesGral.append("	Total hs extras al 100%: "+totalPresHsExtra100+"\n");
					detallesGral.append("	Total hs extras al 50%: "+totalPresHsExtra50+"\n");
					detallesGral.append("	Total hs normales: "+totalPresHs+"\n\n");

				}
				jTable1.getTable().setModel(model);
				jTable1.refreshTable();
				if(model.getRowCount() <= 0){
					Util.alertMsg(Main.getVentana(), "No se encontraron presupuestos");					
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void actionPerformed(ActionEvent arg0) {

				
				buscarReportes();
			}
		}
	
	public String getTotalFormateado(double tot) {
		return getCurrencyFormat().format(tot);
	}
	
	private NumberFormat getCurrencyFormat() {
		NumberFormat currencyFormat;
		Locale l = new Locale("es","AR");
		currencyFormat = NumberFormat.getCurrencyInstance(l);
		return currencyFormat;
	}
	
	private class TabalMouseListener implements MouseListener{
		
		private JPopupMenu popup;
		
		public TabalMouseListener() {
			popup = new JPopupMenu();
			JMenuItem menuItem = new JMenuItem("Ver calculo de costo operativo presupuestado");
			menuItem.setIcon(new javax.swing.ImageIcon(Main.class.getResource("imagenes/arrow_rotate_anticlockwise.png")));
			menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
		    menuItem.addActionListener(new PopUpMenuPresupuestadoActionListener());
		    popup.add(menuItem);
		    
		    JMenuItem menuItem2 = new JMenuItem("Ver calculo de costo operativo real");
			menuItem2.setIcon(new javax.swing.ImageIcon(Main.class.getResource("imagenes/arrow_rotate_anticlockwise.png")));
			menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
		    menuItem2.addActionListener(new PopUpMenuRealActionListener());
		    popup.add(menuItem2);
		}

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent e) {
						
		}

		public void mouseReleased(MouseEvent e) {
			if (e.isPopupTrigger()) {
				int rowselected =jTable1.getTable().rowAtPoint(new Point(e.getX(), e.getY()));				
				if(jTable1.getTable().isRowSelected(rowselected)){
					jTable1.getTable().getSelectionModel().setSelectionInterval(rowselected, rowselected);
					popup.show(e.getComponent(),
		                       e.getX(), e.getY());
				}	            
	        }
			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	private class PopUpMenuPresupuestadoActionListener implements ActionListener{
	
		public void actionPerformed(ActionEvent arg0) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){				
						if(jTable1.getSelectedItem() != null){
							PantallaEmergenteGastoOperativo pantallaPres = new PantallaEmergenteGastoOperativo(Main.getVentana(),"Detalle de Costo Operativo PRESUPUESTADO");
							pantallaPres.setObservacionesText(todosDetallesPres.get(getSelectedPpto().getNumeroPpto()).toString());
							pantallaPres.setVisible(true);
						}		
				}
			});	
		}
		
	}
	
	private class PopUpMenuRealActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){				
						if(jTable1.getSelectedItem() != null){
							PantallaEmergenteGastoOperativo pantallaPres = new PantallaEmergenteGastoOperativo(Main.getVentana(), "Detalle de Costo Operativo REAL");
							pantallaPres.setObservacionesText(todosDetallesReal.get(getSelectedPpto().getNumeroPpto()).toString());
							pantallaPres.setVisible(true);
						}		
				}
			});	
		}
		
	}

}
