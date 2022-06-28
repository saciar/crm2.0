package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.UnidadComercialManager;
import crm.client.managers.VendedorManager;
import crm.client.report.ReportBuilder;
import crm.client.util.DateConverter;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMEstadosComboBox;
import crm.gui.components.ABMLugarEvtComboBox;
import crm.gui.components.ABMOrdenamientoComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.components.DatePanel;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;

public class BuscadorReportesComerciales2 extends JDialog{
	
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.ButtonGroup buttonGroup2;
	private javax.swing.ButtonGroup buttonGroup3;
	private javax.swing.ButtonGroup buttonGroup4;
	private javax.swing.ButtonGroup buttonGroup5;
	private javax.swing.ButtonGroup buttonGroup6;
	private javax.swing.ButtonGroup buttonGroup7;
	private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.JCheckBox check_estado_ppto;
    private javax.swing.JCheckBox check_estados_porc;
    private javax.swing.JCheckBox check_lugar_ppto;
    private javax.swing.JRadioButton check_uc_diary;
    private javax.swing.JRadioButton rd_uc_lim;
    private javax.swing.JCheckBox check_creado_ppto;
    private javax.swing.JRadioButton rd_uc_sub;
    private javax.swing.JRadioButton check_uc_week;
    private javax.swing.JRadioButton check_vendedor_diary;
    private javax.swing.JRadioButton rd_vendedor_lim;
    private javax.swing.JRadioButton rd_vendedor_sub;
    private javax.swing.JRadioButton check_vendedor_week;
    private ABMOrdenamientoComboBox jComboBox1;
    private ABMEstadosComboBox cmb_estados_porc;
    private ABMEstadosComboBox cmb_estados_ppto;
    private ABMLugarEvtComboBox cmb_lugar_ppto;
    private DatePanel dteFecha10;
    private DatePanel dteFecha9;
    private DatePanel dteFechaFinLim;
    private DatePanel dteFechaFinPorc;
    private DatePanel dteFechaFinSub;
    private DatePanel dteFechaHastaPpto;
    private DatePanel dteFechaInicioLim;
    private DatePanel dteFechaInicioPorc;
    private DatePanel dteFechaInicioPpto;
    private DatePanel dteFechaInicioSub;
    private DatePanel dteFechaMesAnio1;
    private DatePanel dteFechaCreacionPpto;
    private javax.swing.JButton exit1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rd_daily;
    private javax.swing.JRadioButton rd_diary_planner;
    private javax.swing.JRadioButton rd_info_mensual;
    private javax.swing.JRadioButton rd_inform_semanal;
    private javax.swing.JRadioButton rd_lista_precios;
    private javax.swing.JRadioButton rd_montos_mayores;
    private javax.swing.JRadioButton rd_montos_menores;
    private javax.swing.JRadioButton rd_sub_propios;
    private javax.swing.JRadioButton rd_sub_subcontratado;
    private javax.swing.JRadioButton rd_sub_ambos;
    private javax.swing.JRadioButton rd_weekly;
    private javax.swing.JLabel tipoPptos2;
    private javax.swing.JLabel tipoPptos3;
    private javax.swing.JTextField tx_limite;
    private javax.swing.JTextField tx_week;
    private javax.swing.JFormattedTextField txtWeek1;
    private GradientButton viewReport1; 
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JRadioButton rd_vend_ppto;
    private javax.swing.JRadioButton rd_uc_ppto;
    private long codVendedor;
    private long codUC;
    
    public BuscadorReportesComerciales2 (Frame owner){
		super(owner);
		this.setTitle("Buscador de reportes");
		this.setModal(false);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setEnabled(true);
    }
    
    public BuscadorReportesComerciales2 (Frame owner, long codVendedor, long codUC){
		super(owner);
		this.setTitle("Buscador de reportes");
		this.setModal(false);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setEnabled(true);
        this.codVendedor = codVendedor;
        this.codUC = codUC;
    }
    
    public void init(){
		//**************Crecion de panel contenedor con imagen de fondo**********************
		PanelImagen panel = null;
		try{
			panel = new PanelImagen("WorldLight.jpg", false);
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
		
		buttonGroup1 = new javax.swing.ButtonGroup();
		buttonGroup2 = new javax.swing.ButtonGroup();
		buttonGroup3 = new javax.swing.ButtonGroup();
		buttonGroup4 = new javax.swing.ButtonGroup();
		buttonGroup5 = new javax.swing.ButtonGroup();
		buttonGroup6 = new javax.swing.ButtonGroup();
		buttonGroup7 = new javax.swing.ButtonGroup();
		buttonGroup8 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        
        jPanel5 = new javax.swing.JPanel();
        tipoPptos2 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        rd_inform_semanal = new javax.swing.JRadioButton();
        txtWeek1 = new javax.swing.JFormattedTextField();
        jSeparator6 = new javax.swing.JSeparator();
        rd_info_mensual = new javax.swing.JRadioButton();
        dteFechaMesAnio1 = CustomTextField.getDateInstance();
        dteFechaMesAnio1.setEnabledDay(false);
        rd_diary_planner = new javax.swing.JRadioButton();
        dteFecha9 = CustomTextField.getDateInstance();
        rd_lista_precios = new javax.swing.JRadioButton();
        
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dteFechaInicioPpto = CustomTextField.getDateInstance();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dteFechaHastaPpto = CustomTextField.getDateInstance();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        check_lugar_ppto = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        cmb_lugar_ppto = new ABMLugarEvtComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        check_estado_ppto = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        cmb_estados_ppto = new ABMEstadosComboBox();
        rd_vend_ppto = new javax.swing.JRadioButton();
        rd_uc_ppto = new javax.swing.JRadioButton();
        jSeparator13 = new javax.swing.JSeparator();
        check_creado_ppto = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        dteFechaCreacionPpto = CustomTextField.getDateInstance();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox1 = new ABMOrdenamientoComboBox();
        
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dteFechaInicioPorc = CustomTextField.getDateInstance();
        jLabel12 = new javax.swing.JLabel();
        dteFechaFinPorc = CustomTextField.getDateInstance();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        check_estados_porc = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        cmb_estados_porc = new ABMEstadosComboBox();
        
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        dteFechaInicioSub = CustomTextField.getDateInstance();
        jLabel18 = new javax.swing.JLabel();
        dteFechaFinSub = CustomTextField.getDateInstance();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        rd_vendedor_sub = new javax.swing.JRadioButton();
        rd_uc_sub = new javax.swing.JRadioButton();        
        jLabel22 = new javax.swing.JLabel();
        rd_sub_propios = new javax.swing.JRadioButton();
        rd_sub_subcontratado = new javax.swing.JRadioButton();
        rd_sub_ambos = new javax.swing.JRadioButton();
        jSeparator8 = new javax.swing.JSeparator();
        
        jPanel4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        dteFechaInicioLim = CustomTextField.getDateInstance();
        jLabel25 = new javax.swing.JLabel();
        dteFechaFinLim = CustomTextField.getDateInstance();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        rd_vendedor_lim = new javax.swing.JRadioButton();
        rd_uc_lim = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        tx_limite = CustomTextField.getDecimalInstance(10,2);
        rd_montos_mayores = new javax.swing.JRadioButton();
        rd_montos_menores = new javax.swing.JRadioButton();
        jSeparator7 = new javax.swing.JSeparator();
        
        jPanel6 = new javax.swing.JPanel();
        tipoPptos3 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        rd_daily = new javax.swing.JRadioButton();
        jLabel40 = new javax.swing.JLabel();
        dteFecha10 = CustomTextField.getDateInstance();
        jSeparator14 = new javax.swing.JSeparator();
        rd_weekly = new javax.swing.JRadioButton();
        jLabel42 = new javax.swing.JLabel();
        tx_week = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        check_vendedor_diary = new javax.swing.JRadioButton();
        check_uc_diary = new javax.swing.JRadioButton();
        jLabel44 = new javax.swing.JLabel();
        check_vendedor_week = new javax.swing.JRadioButton();
        check_uc_week = new javax.swing.JRadioButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        
        viewReport1 = new GradientButton("", Color.blue);
        exit1 = new GradientButton("", Color.blue);      

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Reportes");

        jLabel2.setText("Seleccione un rango de fechas para ver informaci\u00f3n de presupuestos");

        jLabel3.setText("Desde");

        jLabel4.setText("Hasta");

        jLabel5.setText("Seleccione buscar por:");
        
        jLabel6.setText("Filtrar por:");
        
        check_lugar_ppto.setText("Lugar de evento");
        check_lugar_ppto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_lugar_ppto.setMargin(new java.awt.Insets(0, 0, 0, 0));
        
        buttonGroup5.add(rd_vend_ppto);
        try {
        	rd_vend_ppto.setText("Vendedor: "+(VendedorManager.instance().getVendedorById(String.valueOf(codVendedor))).getApellidoYNombre());
        	rd_vend_ppto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            rd_vend_ppto.setMargin(new java.awt.Insets(0, 0, 0, 0));
            rd_vend_ppto.setSelected(true);
		} catch (RemoteException e) {			
			Util.errMsg(BuscadorReportesComerciales2.this,"Error al cargar datos del vendedor",e);
		}        

        buttonGroup5.add(rd_uc_ppto);
        try {
        	rd_uc_ppto.setText("Unidad comercial: "+(UnidadComercialManager.instance().getUnidadComercialById(String.valueOf(codUC))).getDescripcion());
        	rd_uc_ppto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            rd_uc_ppto.setMargin(new java.awt.Insets(0, 0, 0, 0));
		} catch (RemoteException e) {			
			Util.errMsg(BuscadorReportesComerciales2.this,"Error al cargar datos de la unidad comercial",e);
		}       
        
        jLabel8.setText("Seleccione el lugar de evento");

        check_estado_ppto.setText("Estado del presupuesto");
        check_estado_ppto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_estado_ppto.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel9.setText("Seleccione un estado");
        
        check_creado_ppto.setText("Fecha de Creaci\u00f3n");
        check_creado_ppto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_creado_ppto.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel7.setText("Seleccione la fecha");
        
        jLabel20.setText("Ordenar por:");

        jLabel21.setText("Seleccione el criterio de ordenamiento");
        
        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE))
                        .add(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel2)
                                .add(jPanel1Layout.createSequentialGroup()
                                    .add(10, 10, 10)
                                    .add(jLabel3)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(dteFechaInicioPpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel4)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(dteFechaHastaPpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(jLabel5)
                                .add(jPanel1Layout.createSequentialGroup()
                                    .add(33, 33, 33)
                                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(rd_vend_ppto)
                                        .add(rd_uc_ppto)))))
                        .add(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jLabel6))
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(43, 43, 43)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(check_lugar_ppto)
                                .add(check_estado_ppto)
                                .add(check_creado_ppto))
                            .add(35, 35, 35)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(jLabel8)
                                .add(jLabel9)
                                .add(jLabel7))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(cmb_estados_ppto, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(cmb_lugar_ppto, 0, 224, Short.MAX_VALUE))
                                .add(dteFechaCreacionPpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE))
                        .add(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE))
                        .add(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jLabel20))
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(44, 44, 44)
                            .add(jLabel21)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 210, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jLabel2)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jLabel3)
                        .add(dteFechaInicioPpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel4)
                        .add(dteFechaHastaPpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel5)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(rd_vend_ppto)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(rd_uc_ppto)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel6)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel8)
                        .add(check_lugar_ppto)
                        .add(cmb_lugar_ppto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(check_estado_ppto)
                        .add(jLabel9)
                        .add(cmb_estados_ppto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(check_creado_ppto)
                            .add(jLabel7))
                        .add(dteFechaCreacionPpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel20)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel21)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(64, Short.MAX_VALUE))
            );
            jTabbedPane1.addTab("Presupuestos", jPanel1);

            jLabel14.setText("Seleccione un rango de fechas para ver informaci\u00f3n de presupuestos");

            jLabel11.setText("Desde");

            jLabel12.setText("Hasta");

            jLabel13.setText("Filtrar por:");

            check_estados_porc.setText("Estado del presupuesto");
            check_estados_porc.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            check_estados_porc.setMargin(new java.awt.Insets(0, 0, 0, 0));

            jLabel15.setText("Seleccione un estado");           

            org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel2Layout.createSequentialGroup()
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jPanel2Layout.createSequentialGroup()
                                    .add(10, 10, 10)
                                    .add(jLabel11)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(dteFechaInicioPorc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel12)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(dteFechaFinPorc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(jLabel14)
                                .add(jLabel13)))
                        .add(jPanel2Layout.createSequentialGroup()
                            .add(44, 44, 44)
                            .add(check_estados_porc)
                            .add(55, 55, 55)
                            .add(jLabel15)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(cmb_estados_porc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 228, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)))
                    .add(150, 150, 150))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jLabel14)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jLabel11)
                        .add(dteFechaInicioPorc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel12)
                        .add(dteFechaFinPorc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel13)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(check_estados_porc)
                        .add(jLabel15)
                        .add(cmb_estados_porc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(247, Short.MAX_VALUE))
            );
            jTabbedPane1.addTab("Porcentajes", jPanel2);

            jLabel16.setText("Seleccione un rango de fechas para ver informaci\u00f3n de presupuestos");

            jLabel17.setText("Desde");

            jLabel18.setText("Hasta");

            jLabel22.setText("Seleccione el tipo de subcontrataciones que desea visualizar");

            buttonGroup3.add(rd_sub_propios);
            rd_sub_propios.setText("Subcontrataciones de servicios propios ");
            rd_sub_propios.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            rd_sub_propios.setMargin(new java.awt.Insets(0, 0, 0, 0));
            rd_sub_propios.setSelected(true);

            buttonGroup3.add(rd_sub_subcontratado);
            rd_sub_subcontratado.setText("Subcontrataciones de servicios de familia subcontratado");
            rd_sub_subcontratado.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            rd_sub_subcontratado.setMargin(new java.awt.Insets(0, 0, 0, 0));

            buttonGroup3.add(rd_sub_ambos);
            rd_sub_ambos.setText("Ambos casos");
            rd_sub_ambos.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            rd_sub_ambos.setMargin(new java.awt.Insets(0, 0, 0, 0));

            jLabel19.setText("Filtrar Por:");

            buttonGroup6.add(rd_vendedor_sub);
            rd_vendedor_sub.setText("Vendedor");
            rd_vendedor_sub.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            rd_vendedor_sub.setMargin(new java.awt.Insets(0, 0, 0, 0));
            rd_vendedor_sub.setSelected(true);
            
            buttonGroup6.add(rd_uc_sub);
            rd_uc_sub.setText("Unidad Comercial");
            rd_uc_sub.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            rd_uc_sub.setMargin(new java.awt.Insets(0, 0, 0, 0));

            org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel3Layout.createSequentialGroup()
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jPanel3Layout.createSequentialGroup()
                                    .add(10, 10, 10)
                                    .add(jLabel17)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(dteFechaInicioSub, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel18)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(dteFechaFinSub, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(jLabel16)))
                        .add(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel22)
                                .add(jPanel3Layout.createSequentialGroup()
                                    .add(10, 10, 10)
                                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(rd_sub_subcontratado)
                                        .add(rd_sub_propios)
                                        .add(rd_sub_ambos))))))
                    .add(265, 265, 265))
                .add(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jSeparator8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                    .addContainerGap())
                .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jSeparator4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                    .addContainerGap())
                .add(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jLabel19)
                        .add(jPanel3Layout.createSequentialGroup()
                            .add(10, 10, 10)
                            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(rd_uc_sub)
                                .add(rd_vendedor_sub))))
                    .addContainerGap(594, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jLabel16)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jLabel17)
                        .add(dteFechaInicioSub, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel18)
                        .add(dteFechaFinSub, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel22)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(rd_sub_propios)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(rd_sub_subcontratado)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(rd_sub_ambos)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel19)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(rd_vendedor_sub)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(rd_uc_sub)
                    .addContainerGap(134, Short.MAX_VALUE))
            );
            jTabbedPane1.addTab("Subcontratados", jPanel3);

            jLabel23.setText("Seleccione un rango de fechas para ver informaci\u00f3n de presupuestos");

            jLabel24.setText("Desde");

            jLabel25.setText("Hasta");

            jLabel26.setText("Filtrar por:");

            jLabel10.setText("Seleccione limite de monto $");

            buttonGroup4.add(rd_montos_mayores);
            rd_montos_mayores.setText("Montos mayores");
            rd_montos_mayores.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            rd_montos_mayores.setMargin(new java.awt.Insets(0, 0, 0, 0));
            rd_montos_mayores.setSelected(true);
            
            buttonGroup4.add(rd_montos_menores);
            rd_montos_menores.setText("Montos menores");
            rd_montos_menores.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            rd_montos_menores.setMargin(new java.awt.Insets(0, 0, 0, 0));

            buttonGroup8.add(rd_vendedor_lim);
            rd_vendedor_lim.setText("Vendedor");
            rd_vendedor_lim.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            rd_vendedor_lim.setMargin(new java.awt.Insets(0, 0, 0, 0));
            rd_vendedor_lim.setSelected(true);
            
            buttonGroup8.add(rd_uc_lim);
            rd_uc_lim.setText("Unidad Comercial");
            rd_uc_lim.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            rd_uc_lim.setMargin(new java.awt.Insets(0, 0, 0, 0));

            org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel4Layout.createSequentialGroup()
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jPanel4Layout.createSequentialGroup()
                                    .add(10, 10, 10)
                                    .add(jLabel24)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(dteFechaInicioLim, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel25)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(dteFechaFinLim, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(jLabel23)))
                        .add(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jLabel10)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(tx_limite, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(rd_montos_menores)
                                .add(rd_montos_mayores)))
                        .add(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE))
                        .add(jSeparator5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                        .add(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel4Layout.createSequentialGroup()
                                    .add(10, 10, 10)
                                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(rd_uc_lim)
                                        .add(rd_vendedor_lim)))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel26))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 584, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jLabel23)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jLabel24)
                        .add(dteFechaInicioLim, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel25)
                        .add(dteFechaFinLim, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel10)
                        .add(tx_limite, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(rd_montos_mayores))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(rd_montos_menores)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel26)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(rd_vendedor_lim)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(rd_uc_lim)
                    .addContainerGap(171, Short.MAX_VALUE))
            );
            jTabbedPane1.addTab("Limites ", jPanel4);

            tipoPptos3.setFont(new java.awt.Font("Tahoma", 1, 14));

            jLabel39.setText("Seleccione un tipo de reporte a visualizar");

            buttonGroup1.add(rd_daily);
            rd_daily.setText("Daily");
            rd_daily.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            rd_daily.setMargin(new java.awt.Insets(0, 0, 0, 0));
            rd_daily.setSelected(true);
            
            jLabel40.setText("Ingrese fecha:");

            buttonGroup1.add(rd_weekly);
            rd_weekly.setText("Weekly");
            rd_weekly.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            rd_weekly.setMargin(new java.awt.Insets(0, 0, 0, 0));

            jLabel42.setText("Ingrese nro de week:");

            jLabel43.setText("Filtrar por:");

            jLabel44.setText("Filtrar por:");

            buttonGroup2.add(check_vendedor_diary);
            check_vendedor_diary.setText("Vendedor");
            check_vendedor_diary.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            check_vendedor_diary.setMargin(new java.awt.Insets(0, 0, 0, 0));
            check_vendedor_diary.setSelected(true);
            
            buttonGroup2.add(check_uc_diary);
            check_uc_diary.setText("Unidad Comercial");
            check_uc_diary.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            check_uc_diary.setMargin(new java.awt.Insets(0, 0, 0, 0));

            buttonGroup7.add(check_vendedor_week);
            check_vendedor_week.setText("Vendedor");
            check_vendedor_week.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            check_vendedor_week.setMargin(new java.awt.Insets(0, 0, 0, 0));
            check_vendedor_week.setSelected(true);
            
            buttonGroup7.add(check_uc_week);
            check_uc_week.setText("Unidad Comercial");
            check_uc_week.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            check_uc_week.setMargin(new java.awt.Insets(0, 0, 0, 0));

            org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jSeparator12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                        .add(jSeparator14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                        .add(jPanel6Layout.createSequentialGroup()
                            .add(jLabel39)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(tipoPptos3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanel6Layout.createSequentialGroup()
                            .add(74, 74, 74)
                            .add(jLabel43)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(check_vendedor_diary)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(check_uc_diary))
                        .add(jPanel6Layout.createSequentialGroup()
                            .add(19, 19, 19)
                            .add(rd_weekly)
                            .add(84, 84, 84)
                            .add(jLabel42)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(tx_week, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanel6Layout.createSequentialGroup()
                            .add(74, 74, 74)
                            .add(jLabel44)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(check_vendedor_week)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(check_uc_week))
                        .add(jPanel6Layout.createSequentialGroup()
                            .add(19, 19, 19)
                            .add(rd_daily)
                            .add(94, 94, 94)
                            .add(jLabel40)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(dteFecha10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );
            jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(tipoPptos3)
                        .add(jLabel39))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(rd_daily)
                            .add(jLabel40))
                        .add(dteFecha10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(8, 8, 8)
                    .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel43)
                        .add(check_vendedor_diary)
                        .add(check_uc_diary))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(rd_weekly)
                        .add(jLabel42)
                        .add(tx_week, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel44)
                        .add(check_vendedor_week)
                        .add(check_uc_week))
                    .addContainerGap(210, Short.MAX_VALUE))
            );
            jTabbedPane1.addTab("Week & Daily", jPanel6);

            tipoPptos2.setFont(new java.awt.Font("Tahoma", 1, 14));

            jLabel35.setText("Seleccione un tipo de reporte a visualizar");

            rd_lista_precios.setText("Lista de precios");
            rd_lista_precios.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
            rd_lista_precios.setMargin(new java.awt.Insets(0, 0, 0, 0));
            rd_lista_precios.setSelected(true);

            org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel5Layout.createSequentialGroup()
                    .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel5Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jLabel35)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(tipoPptos2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanel5Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE))
                        .add(jPanel5Layout.createSequentialGroup()
                            .add(28, 28, 28)
                            .add(rd_lista_precios)))
                    .addContainerGap())
            );
            jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(tipoPptos2)
                        .add(jLabel35))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(rd_lista_precios)
                    .addContainerGap(303, Short.MAX_VALUE))
            );
            jTabbedPane1.addTab("Otros", jPanel5);

            viewReport1.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
            viewReport1.setText("Ver Reporte");

            exit1.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
            exit1.setMnemonic('s');
            exit1.setText("Salir");
            exit1.setToolTipText("Click para salir");

            org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
            panel.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(jLabel1))
                            .add(layout.createSequentialGroup()
                                .add(259, 259, 259)
                                .add(viewReport1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(exit1))
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .add(jTabbedPane1, 0, 0, Short.MAX_VALUE))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 728, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(exit1)
                            .add(viewReport1))
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

        this.getContentPane().add(panel);
        this.pack();
        
        createListeners();
        loadAndInitComponents();
        JDialog.setDefaultLookAndFeelDecorated(true);
        updatePosition();
    }
    
    public URL getUrlImagen(String imagen){
	    
    	URL url =Main.class.getResource("imagenes/"+imagen); 

    	return url;
    }
    
    public void updatePosition(){
    	
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	
    	this.setLocation((screenSize.width - this.getWidth())/2,
    			(screenSize.height - this.getHeight())/2);
    	
    }
    
    private void createListeners(){
    	exit1.addActionListener(new ExitAction());
		viewReport1.addActionListener(new ViewReportAction());
		rd_daily.addChangeListener(new ChangeDailyButton());
		rd_weekly.addChangeListener(new ChangeWeekButton());
	}
	
	private void loadAndInitComponents(){
		cmb_estados_ppto.loadItems();
		cmb_lugar_ppto.loadItems();		
		
		cmb_estados_porc.loadItems();
		
		//cmbLugares.loadItems();
		
		/*jComboBox1.loadItems();
		jComboBox2.loadItems();
		jComboBox3.loadItems();
		jComboBox4.loadItems();*/
	}
	
	private String getCurrentYear(){
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
	    
	    String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    
	    sdf.setTimeZone(TimeZone.getDefault());          
	          
	    return (sdf.format(cal.getTime())).substring(6,10);
	}
	
	private long getCodLugarPpto(){
		if(cmb_lugar_ppto.searchForeign()!= null){
			return Long.parseLong(cmb_lugar_ppto.searchForeign());
		}
		else
			return 0;
	}
	
	private long getCodEstadoPpto(){
		if(cmb_estados_ppto.searchForeign()!= null){
			System.out.println("Estado : "+cmb_estados_ppto.searchForeign());
			return Long.parseLong(cmb_estados_ppto.searchForeign());
		}
		else
			return 0;
	}
	
	private long getCodEstadoPorcentaje(){
		if(cmb_estados_porc.searchForeign()!= null){
			System.out.println("Estado : "+cmb_estados_porc.searchForeign());
			return Long.parseLong(cmb_estados_porc.searchForeign());
		}
		else
			return 0;
	}

    //****************************ACCIONES****************************************************
    
    private class ExitAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
		
	}
    
    private class ChangeTabListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			
		}
    	
    }

    private class ViewReportAction implements ActionListener{
    	
    	private int getTypeSubcontratado(){
    		if(rd_vendedor_sub.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO;
    		if(!rd_vendedor_sub.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO;
    		return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_ESTADO;
    	}
    	
    	private int getTypeLimites(){
    		if(rd_vendedor_lim.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO;
    		if(!rd_vendedor_lim.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO;
    		return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_ESTADO;
    	}
    	
    	private int getTypePorcentaje(){
    		if(check_estados_porc.isSelected() && cmb_estados_porc.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_ESTADO;
    		if(check_estados_porc.isSelected() && cmb_estados_porc.searchForeign()== null){
    			Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar un estado", null);
    			return -1;
    		}
    		
    		return ReportBuilder.REPORT_PPTO_GERENCIA_VEN;
    	}
    	
    	private int getType(){
    		if(rd_vend_ppto.isSelected() && 
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null &&
    				!check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO;   			
    		
    		if(rd_vend_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected()&&
    				!check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR;
    		
    		if(rd_vend_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null &&
    				!check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_ESTADO;
    		
    		if(rd_vend_ppto.isSelected() && 
    				!check_lugar_ppto.isSelected() &&
    				!check_estado_ppto.isSelected() &&
    				!check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN;
    		
    		if(!rd_vend_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null &&
    				!check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO;
    		
    		if(!rd_vend_ppto.isSelected() && 
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() &&
    				!check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR;
    		
    		if(!rd_vend_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null &&
    				!check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_ESTADO;
    		
    		if(!rd_vend_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() &&
    				!check_estado_ppto.isSelected() &&
    				!check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC;
    		/*---------------------------------------------------------------------------------*/
    		
    		if(rd_vend_ppto.isSelected() && 
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null &&
    				check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO_CREADO;   			
    		
    		if(rd_vend_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected()&&
    				check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_CREADO;
    		
    		if(rd_vend_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null &&
    				check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_ESTADO_CREADO;
    		
    		if(rd_vend_ppto.isSelected() && 
    				!check_lugar_ppto.isSelected() &&
    				!check_estado_ppto.isSelected() &&
    				check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_CREADO;
    		
    		if(!rd_vend_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null &&
    				check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO_CREADO;
    		
    		if(!rd_vend_ppto.isSelected() && 
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected()&&
    				check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_CREADO;
    		
    		if(!rd_vend_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null &&
    				check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_ESTADO_CREADO;
    		
    		if(!rd_vend_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() &&
    				!check_estado_ppto.isSelected() &&
    				check_creado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_CREADO;
    		
    		if(check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()== null){
    			Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar un lugar", null);
    			return -1;
    		}
    		if(check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()== null){
    			Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar un estado", null);
    			return -1;
    		}
    		
    		return ReportBuilder.REPORT_PPTO_GERENCIA;
    	}
    	
    	private void selectPptos(){
    		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {					
					int type = getType();					
					if(type >= 0)
						ReportBuilder.previewPresupuestosGerenciaReport(dteFechaInicioPpto,dteFechaHastaPpto, codVendedor, 
														codUC, getCodLugarPpto(), getCodEstadoPpto(), dteFechaCreacionPpto, type, jComboBox1.getItem(),1);
					
					ProgressDialogUtil.closeProcessDialog();
				}
			}).start(); 
    	}
    	
    	private void selectPorcentaje(){
    		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {					
					int type = getTypePorcentaje();					
					if(type >= 0)
						ReportBuilder.previewPorcentajeComercialesReport(dteFechaInicioPorc,dteFechaFinPorc, codVendedor, 
														0, 0, getCodEstadoPorcentaje(), type);
					
					ProgressDialogUtil.closeProcessDialog();
				}
			}).start(); 
    	}
    	
    	private void selectSubcontratado(){
    		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {					
					int type = getTypeSubcontratado();					
					if(type >= 0)
						if(rd_sub_ambos.isSelected())
							ReportBuilder.previewSubcontratadoGerenciaReport(dteFechaInicioSub,dteFechaFinSub, codVendedor, 
								codUC, 0, 0, type);
						else if(rd_sub_propios.isSelected())
							ReportBuilder.previewSubcontratadoGerenciaReport(dteFechaInicioSub,dteFechaFinSub, codVendedor, 
									codUC, 1, 0, type);
						else if(rd_sub_subcontratado.isSelected())
							ReportBuilder.previewSubcontratadoGerenciaReport(dteFechaInicioSub,dteFechaFinSub, codVendedor, 
									codUC, 0, 1, type);
							
					
					ProgressDialogUtil.closeProcessDialog();
				}
			}).start();
    	}
    	
    	private void selectLimites(){
    		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {					
					int type = getTypeLimites();					
					if(type >= 0)
						if(rd_montos_mayores.isSelected())
							ReportBuilder.previewLimitesGerenciaReport(dteFechaInicioLim,dteFechaFinLim, codVendedor, 
								codUC, 1, Double.parseDouble(tx_limite.getText()), type);
						else if(rd_montos_menores.isSelected())
							ReportBuilder.previewLimitesGerenciaReport(dteFechaInicioLim,dteFechaFinLim, codVendedor, 
									codUC, 0, Double.parseDouble(tx_limite.getText()), type);							
					
					ProgressDialogUtil.closeProcessDialog();
				}
			}).start();
    	}
    	
    	private void selectOtros(){
    		if (rd_inform_semanal.isSelected()){
				ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				new Thread(new Runnable() {	
					public void run() {
						int nroWeek = Integer.parseInt(txtWeek1.getText());
						Calendar cal = Calendar.getInstance();
						ReportBuilder.previewInfoSemanalReport(nroWeek,cal.get(Calendar.YEAR));
						//dispose();
						ProgressDialogUtil.closeProcessDialog();
				}
				}).start(); 		
			}
			else if(rd_diary_planner.isSelected()){
				ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				new Thread(new Runnable() {	
					public void run() {
						int dia = Integer.parseInt(dteFecha9.getDay());
						int mes = Integer.parseInt(dteFecha9.getMonth());
						int anio = Integer.parseInt(dteFecha9.getYear());
						ReportBuilder.previewDiaryPlannerReport(dia, mes, anio, "todas");
						//dispose();
						ProgressDialogUtil.closeProcessDialog();
				}
				}).start(); 
			}
			else if(rd_info_mensual.isSelected()){
				ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				new Thread(new Runnable() {	
					public void run() {
						int mes = Integer.parseInt(dteFechaMesAnio1.getMonth());
						int anio = Integer.parseInt(dteFechaMesAnio1.getYear());
						ReportBuilder.previewInfoMensualReport(mes, anio);
						//dispose();
						ProgressDialogUtil.closeProcessDialog();
				}
				}).start(); 
			}
			else if(rd_lista_precios.isSelected()){
				ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				new Thread(new Runnable() {	
					public void run() {
						//String codLugar = cmbLugares.searchForeign();
						//if(!codLugar.equals("0") || codLugar != null){
						Calendar c = Calendar.getInstance();
						c.setTime(new Date());
							ReportBuilder.previewListaPreciosReport(DateConverter.convertDateToString(new Date(), "yyyy-MM-dd"));
						//}
						ProgressDialogUtil.closeProcessDialog();
				}
				}).start(); 
				
			}
    	}
    	
    	private void selectWeekAndDaily(){
    		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {					
					if(rd_daily.isSelected()){						
						int dia = Integer.parseInt(dteFecha10.getDay());
						int mes = Integer.parseInt(dteFecha10.getMonth());
						int anio = Integer.parseInt(dteFecha10.getYear());
						if(check_uc_diary.isSelected())
							ReportBuilder.previewDailyReportUC(dia, mes, anio, codUC);
						else if(check_vendedor_diary.isSelected())
							ReportBuilder.previewDailyReportVend(dia, mes, anio, codVendedor);
						else ReportBuilder.previewDailyReport(dia, mes, anio);
					}
					
					if(rd_weekly.isSelected() && !StringUtils.isBlank(tx_week.getText())){						
						if(check_uc_week.isSelected()) 
							ReportBuilder.previewWeekReportUC(Integer.parseInt(tx_week.getText()),Integer.parseInt(getCurrentYear()), codUC);
						else if(check_vendedor_week.isSelected())
							ReportBuilder.previewWeekReportVend(Integer.parseInt(tx_week.getText()),Integer.parseInt(getCurrentYear()), codVendedor );						
						else ReportBuilder.previewWeekReport(Integer.parseInt(tx_week.getText()),Integer.parseInt(getCurrentYear()));
					}
					else if(rd_weekly.isSelected() && StringUtils.isBlank(tx_week.getText())){
						Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Primero debe ingresar un week v�lido", null);
					}
					ProgressDialogUtil.closeProcessDialog();
				}
			}).start();
    	}
    	public void actionPerformed(ActionEvent arg0) {
    		if(((JPanel)jTabbedPane1.getSelectedComponent()).equals(jPanel1)){
    			selectPptos();
    		}
    		else if(((JPanel)jTabbedPane1.getSelectedComponent()).equals(jPanel5)){
    			selectOtros();
    		}
    		else if(((JPanel)jTabbedPane1.getSelectedComponent()).equals(jPanel6)){
    			selectWeekAndDaily();
    		}
    		else if(((JPanel)jTabbedPane1.getSelectedComponent()).equals(jPanel2)){
    			selectPorcentaje();
    		}
    		else if(((JPanel)jTabbedPane1.getSelectedComponent()).equals(jPanel3)){
    			selectSubcontratado();
    		}
    		else if(((JPanel)jTabbedPane1.getSelectedComponent()).equals(jPanel4)){
    			selectLimites();
    		}
    	}
    	
    }
    
    private class ChangeDailyButton implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(rd_daily.isSelected()){				
				check_vendedor_diary.setEnabled(true);
				check_uc_diary.setEnabled(true);
		        dteFecha10.setEnabled(true);
		        jLabel40.setEnabled(true);		        
		        jLabel43.setEnabled(true);
		        jLabel29.setEnabled(true);
		        jLabel30.setEnabled(true);
			}
			else{
				check_vendedor_diary.setEnabled(false);
				check_uc_diary.setEnabled(false);
		        dteFecha10.setEnabled(false);		        
		        jLabel40.setEnabled(false);		        
		        jLabel43.setEnabled(false);
		        jLabel29.setEnabled(false);
		        jLabel30.setEnabled(false);
			}	
		}
		
	}
	
	private class ChangeWeekButton implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(rd_weekly.isSelected()){				
				check_vendedor_week.setEnabled(true);
				check_uc_week.setEnabled(true);
		        tx_week.setEnabled(true);
		        jLabel42.setEnabled(true);
		        jLabel31.setEnabled(true);
		        jLabel32.setEnabled(true);
		        jLabel44.setEnabled(true);
			}
			else{
				check_vendedor_week.setEnabled(false);
				check_uc_week.setEnabled(false);
		        tx_week.setEnabled(false);
		        jLabel42.setEnabled(false);
		        jLabel31.setEnabled(false);
		        jLabel32.setEnabled(false);
		        jLabel44.setEnabled(false);
			}	
		}
		
	}

}
