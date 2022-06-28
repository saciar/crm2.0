package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang.StringUtils;

import crm.client.report.ReportBuilder;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMEstadosComboBox;
import crm.gui.components.ABMLugarEvtComboBox;
import crm.gui.components.ABMUnidadesComercialesComboBox;
import crm.gui.components.ABMVendedoresComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.components.DatePanel;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;

public class BuscadorReportesCobranzas extends PantallaEmergente{
	
	private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox check_estado_ppto;
    private javax.swing.JCheckBox check_lugar_ppto;
    private javax.swing.JCheckBox check_uc_diary;
    private javax.swing.JCheckBox check_uc_ppto;
    private javax.swing.JCheckBox check_uc_week;
    private javax.swing.JCheckBox check_vend_ppto;
    private javax.swing.JCheckBox check_vendedor_diary;
    private javax.swing.JCheckBox check_vendedor_week;
    private ABMLugarEvtComboBox cmbLugares;
    private ABMEstadosComboBox cmb_estados_ppto;
    private ABMLugarEvtComboBox cmb_lugar_ppto;
    private ABMUnidadesComercialesComboBox cmb_uc_ppto;
    private ABMVendedoresComboBox cmb_vendedor_ppto;
    private DatePanel dteFecha10;
    private DatePanel dteFecha9;
    private DatePanel dteFechaHastaPpto;
    private DatePanel dteFechaInicioPpto;
    private DatePanel dteFechaMesAnio1;
    private javax.swing.JButton exit1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rd_daily;
    private javax.swing.JRadioButton rd_diary_planner;
    private javax.swing.JRadioButton rd_info_mensual;
    private javax.swing.JRadioButton rd_inform_semanal;
    private javax.swing.JRadioButton rd_lista_precios;
    private javax.swing.JRadioButton rd_weekly;
    private javax.swing.JLabel tipoPptos3;
    private javax.swing.JTextField tx_week;
    private javax.swing.JFormattedTextField txtWeek1;
    private javax.swing.JButton viewReport1; 
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private ABMVendedoresComboBox jComboBox1;
    private ABMUnidadesComercialesComboBox jComboBox2;
    private ABMVendedoresComboBox jComboBox3;
    private ABMUnidadesComercialesComboBox jComboBox4;
    private DatePanel dteFechaFinCobrados;
    private DatePanel dteFechaInicioCobrados;
    private javax.swing.JLabel lbl_desde_cobrados;
    private javax.swing.JLabel lbl_hasta_cobrados;
    private javax.swing.JLabel lbl_selecRango_cobrados;
    private javax.swing.JPanel pnl_cobrados;
    private javax.swing.JSeparator jSeparator_cobrados;
    
    public BuscadorReportesCobranzas (Frame owner){
		super("Buscador de reportes", owner);
		this.setModal(false);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setEnabled(true);
    }
    
    public void init(){
		//**************Crecion de panel contenedor con imagen de fondo**********************
		PanelImagen panel = null;
		try{
			panel = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
		
		buttonGroup1 = new javax.swing.ButtonGroup();
		
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        
        jPanel5 = new javax.swing.JPanel();
        rd_inform_semanal = new javax.swing.JRadioButton();
        txtWeek1 = new javax.swing.JFormattedTextField();
        rd_info_mensual = new javax.swing.JRadioButton();
        dteFechaMesAnio1 = CustomTextField.getDateInstance();
        dteFechaMesAnio1.setEnabledDay(false);
        rd_diary_planner = new javax.swing.JRadioButton();
        dteFecha9 = CustomTextField.getDateInstance();
        rd_lista_precios = new javax.swing.JRadioButton();
        cmbLugares = new ABMLugarEvtComboBox();
        
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dteFechaInicioPpto = CustomTextField.getDateInstance();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dteFechaHastaPpto = CustomTextField.getDateInstance();
        jLabel5 = new javax.swing.JLabel();
        check_vend_ppto = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        cmb_vendedor_ppto = new ABMVendedoresComboBox();
        check_uc_ppto = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        cmb_uc_ppto = new ABMUnidadesComercialesComboBox();
        check_lugar_ppto = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        cmb_lugar_ppto = new ABMLugarEvtComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        check_estado_ppto = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        cmb_estados_ppto = new ABMEstadosComboBox();
        
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
        check_vendedor_diary = new javax.swing.JCheckBox();
        check_uc_diary = new javax.swing.JCheckBox();
        jLabel44 = new javax.swing.JLabel();
        check_vendedor_week = new javax.swing.JCheckBox();
        check_uc_week = new javax.swing.JCheckBox();
        jLabel29 = new javax.swing.JLabel();
        jComboBox1 = new ABMVendedoresComboBox();
        jLabel30 = new javax.swing.JLabel();
        jComboBox2 = new ABMUnidadesComercialesComboBox();
        jLabel31 = new javax.swing.JLabel();
        jComboBox3 = new ABMVendedoresComboBox();
        jLabel32 = new javax.swing.JLabel();
        jComboBox4 = new ABMUnidadesComercialesComboBox();
        
        pnl_cobrados = new javax.swing.JPanel();
        lbl_selecRango_cobrados = new javax.swing.JLabel();
        lbl_desde_cobrados = new javax.swing.JLabel();
        dteFechaInicioCobrados = CustomTextField.getDateInstance();
        lbl_hasta_cobrados = new javax.swing.JLabel();
        dteFechaFinCobrados = CustomTextField.getDateInstance();
        jSeparator_cobrados= new javax.swing.JSeparator();
        
        exit1 = new GradientButton("", Color.blue);  
        viewReport1 = new GradientButton("", Color.blue);
        
        viewReport1.setText("Ver reporte");
        viewReport1.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        viewReport1.setMnemonic('v');
        viewReport1.setToolTipText("Click para ver el reporte");        
              
        exit1.setText("Salir");
        exit1.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        exit1.setMnemonic('s');
        exit1.setToolTipText("Click para salir");


        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Reportes");

        jLabel2.setText("Seleccione un rango de fechas para ver informaci\u00f3n de presupuestos");

        jLabel3.setText("Desde");

        jLabel4.setText("Hasta");

        jLabel5.setText("Filtrar por:");

        check_vend_ppto.setText("Vendedor");
        check_vend_ppto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_vend_ppto.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel6.setText("Seleccione el vendedor");

        check_uc_ppto.setText("Unidad comercial");
        check_uc_ppto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_uc_ppto.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel7.setText("Seleccione la unidad comercial");

        check_lugar_ppto.setText("Lugar de evento");
        check_lugar_ppto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_lugar_ppto.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel8.setText("Seleccione el lugar de evento");

        check_estado_ppto.setText("Estado del presupuesto");
        check_estado_ppto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_estado_ppto.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel9.setText("Seleccione un estado");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
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
                            .add(check_vend_ppto)
                            .add(check_uc_ppto)
                            .add(check_lugar_ppto)
                            .add(check_estado_ppto))
                        .add(32, 32, 32)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel7)
                            .add(jLabel6)
                            .add(jLabel8)
                            .add(jLabel9))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(cmb_vendedor_ppto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, cmb_estados_ppto, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, cmb_lugar_ppto, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, cmb_uc_ppto, 0, 228, Short.MAX_VALUE))))
                    .add(jSeparator2))
                .addContainerGap(15, Short.MAX_VALUE))
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
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(check_vend_ppto)
                    .add(cmb_vendedor_ppto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(check_uc_ppto)
                    .add(jLabel7)
                    .add(cmb_uc_ppto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cmb_lugar_ppto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8)
                    .add(check_lugar_ppto))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(check_estado_ppto)
                    .add(jLabel9)
                    .add(cmb_estados_ppto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Presupuestos", jPanel1);

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
        jLabel42.setEnabled(false);
        
        jLabel43.setText("Filtrar por:");
        
       // check_vendedor_diary.setText("Vendedor");
       // check_vendedor_diary.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
       // check_vendedor_diary.setMargin(new java.awt.Insets(0, 0, 0, 0));

      //  check_uc_diary.setText("Unidad comercial");
      //  check_uc_diary.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
      //  check_uc_diary.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel44.setText("Filtrar por:");       
        jLabel44.setEnabled(false);
        
        jLabel29.setText("Vendedor");       
        
        jLabel30.setText("Unidad comercial");       
        
        jLabel31.setText("Vendedor");        
        jLabel31.setEnabled(false);
        
        jLabel32.setText("Unidad comercial");       
        jLabel32.setEnabled(false);
        
        jComboBox3.setEnabled(false);
        jComboBox4.setEnabled(false);
        tx_week.setEnabled(false);
        
        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSeparator12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(jLabel39)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(tipoPptos3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(74, 74, 74)
                        .add(jLabel43)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel29)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 146, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel30)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox2, 0, 162, Short.MAX_VALUE))
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
                        .add(jLabel31)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 146, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel32)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox4, 0, 162, Short.MAX_VALUE))
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(19, 19, 19)
                        .add(rd_daily)
                        .add(94, 94, 94)
                        .add(jLabel40)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dteFecha10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jSeparator14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
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
                    .add(jLabel29)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel30)
                    .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(rd_weekly)
                    .add(jLabel42)
                    .add(tx_week, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel44)
                        .add(jLabel31))
                    .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jComboBox3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel32)
                        .add(jComboBox4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Week & Daily", jPanel6);
        
        lbl_selecRango_cobrados.setText("Seleccione un rango de fechas para ver informaci\u00f3n de presupuestos");

        lbl_desde_cobrados.setText("Desde");

        lbl_hasta_cobrados.setText("Hasta");

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(pnl_cobrados);
        pnl_cobrados.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel7Layout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(lbl_desde_cobrados)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(dteFechaInicioCobrados, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lbl_hasta_cobrados)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(dteFechaFinCobrados, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(lbl_selecRango_cobrados)))
                    .add(jSeparator_cobrados, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(lbl_selecRango_cobrados)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lbl_desde_cobrados)
                    .add(dteFechaInicioCobrados, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lbl_hasta_cobrados)
                    .add(dteFechaFinCobrados, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator_cobrados, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(289, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Presupuestos Cobrados", pnl_cobrados);
        
        viewReport1.setText("Ver reporte");
        viewReport1.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        viewReport1.setMnemonic('v');
        viewReport1.setToolTipText("Click para ver el reporte");        
              
        exit1.setText("Salir");
        exit1.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        exit1.setMnemonic('s');
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
                        .add(237, 237, 237)
                        .add(viewReport1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(exit1))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE))
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
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(viewReport1)
                    .add(exit1))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        this.getContentPane().add(panel);
        this.pack();
        
        createListeners();
        loadAndInitComponents();
        JDialog.setDefaultLookAndFeelDecorated(true);
        updatePosition();
    }
    
    public void updatePosition(){
    	
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	
    	this.setLocation((screenSize.width - this.getWidth())/2,
    			(screenSize.height - this.getHeight())/2);
    	
    }
    
    private void createListeners(){
    	exit1.addActionListener(new ExitAction());
    	//jTabbedPane1.addChangeListener(new ChangeTabListener());
		viewReport1.addActionListener(new ViewReportAction());
		rd_daily.addChangeListener(new ChangeDailyButton());
		rd_weekly.addChangeListener(new ChangeWeekButton());
	}
	
	private void loadAndInitComponents(){
		cmb_estados_ppto.loadItems();
		cmb_lugar_ppto.loadItems();
		cmb_vendedor_ppto.loadItems();
		cmb_uc_ppto.loadItems();		
		
		jComboBox1.loadItems();
		jComboBox2.loadItems();
		jComboBox3.loadItems();
		jComboBox4.loadItems();
	}
	
	private String getCurrentYear(){
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
	    
	    String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    
	    sdf.setTimeZone(TimeZone.getDefault());          
	          
	    return (sdf.format(cal.getTime())).substring(6,10);
	}
	
	private long getCodVendedorPpto(){
		if(cmb_vendedor_ppto.searchForeign()!= null){
			return Long.parseLong(cmb_vendedor_ppto.searchForeign());
		}
		else
			return 0;
	}
	
	private long getCodUCPpto(){
		if(cmb_uc_ppto.searchForeign()!= null){
			return Long.parseLong(cmb_uc_ppto.searchForeign());
		}
		else
			return 0;
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
			return Long.parseLong(cmb_estados_ppto.searchForeign());
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
    	
    	private int getType(){
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO;   			
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_ESTADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				!check_estado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() && 
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_ESTADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() && 
    				!check_lugar_ppto.isSelected() && 
    				!check_estado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN;
    		
    		if(!check_vend_ppto.isSelected() &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO;
    		
    		if(!check_vend_ppto.isSelected() && 
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR;
    		
    		if(!check_vend_ppto.isSelected() &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_ESTADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				!check_estado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_ESTADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() && 
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_ESTADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()== null){
    			Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar un vendedor", null);
    			return -1;
    		}
    		if(check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()== null){
    			Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar una unidad comercial", null);
    			return -1;
    		}
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
					/*int diaInicio = Integer.parseInt(dteFechaInicioPpto.getDay());
					int mesInicio = Integer.parseInt(dteFechaInicioPpto.getMonth());
					int anioInicio = Integer.parseInt(dteFechaInicioPpto.getYear());
					
					int diaFin = Integer.parseInt(dteFechaHastaPpto.getDay());
					int mesFin = Integer.parseInt(dteFechaHastaPpto.getMonth());
					int anioFin = Integer.parseInt(dteFechaHastaPpto.getYear());
					
					if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null){
						ReportBuilder.previewPresupuestosGerenciaReportVend(diaInicio, mesInicio, anioInicio,
							diaFin, mesFin, anioFin,Long.parseLong(cmb_vendedor_ppto.searchForeign()));
					}
					else if(cmb_vendedor_ppto.searchForeign()== null){						
						Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar un vendedor", null);
						}
					if(check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign() != null){
						
					}
					
					else
						ReportBuilder.previewPresupuestosGerenciaReport(diaInicio, mesInicio, anioInicio,
							diaFin, mesFin, anioFin);
					*/
					
					int type = getType();
					
					if(type >= 0)
						ReportBuilder.previewPresupuestosCobranzasReport(dteFechaInicioPpto,dteFechaHastaPpto, getCodVendedorPpto(), 
														getCodUCPpto(), getCodLugarPpto(), getCodEstadoPpto(), type,"fechaCreacion");
					
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
						String codLugar = cmbLugares.searchForeign();
						if(!codLugar.equals("0") || codLugar != null){
							//ReportBuilder.previewListaPreciosReport(Long.parseLong(codLugar));
							//dispose();
						}
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
					/*String codLugar = cmbLugares.searchForeign();
					if(!codLugar.equals("0") || codLugar != null){
						ReportBuilder.previewListaPreciosReport(Long.parseLong(codLugar));
						dispose();
					}*/			
					
					if(rd_daily.isSelected()){						
						int dia = Integer.parseInt(dteFecha10.getDay());
						int mes = Integer.parseInt(dteFecha10.getMonth());
						int anio = Integer.parseInt(dteFecha10.getYear());
						if(check_uc_diary.isSelected())
							ReportBuilder.previewDailyReportUC(dia, mes, anio, Long.parseLong(jComboBox2.searchForeign()));
						else if(check_vendedor_diary.isSelected())
							ReportBuilder.previewDailyReportVend(dia, mes, anio, Long.parseLong(jComboBox1.searchForeign()));
						else ReportBuilder.previewDailyReport(dia, mes, anio);
					}
					
					if(rd_weekly.isSelected() && !StringUtils.isBlank(tx_week.getText())){						
						if(check_uc_week.isSelected()) 
							ReportBuilder.previewWeekReportUC(Integer.parseInt(tx_week.getText()),Integer.parseInt(getCurrentYear()), Long.parseLong(jComboBox4.searchForeign()));
						else if(check_vendedor_week.isSelected())
							ReportBuilder.previewWeekReportVend(Integer.parseInt(tx_week.getText()),Integer.parseInt(getCurrentYear()), Long.parseLong(jComboBox3.searchForeign()) );						
						else ReportBuilder.previewWeekReport(Integer.parseInt(tx_week.getText()),Integer.parseInt(getCurrentYear()));
					}
					else if(rd_weekly.isSelected() && StringUtils.isBlank(tx_week.getText())){
						Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Primero debe ingresar un week v�lido", null);
					}
					ProgressDialogUtil.closeProcessDialog();
				}
			}).start();
    	}
    	
    	private void selectCobrados(){
    		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {					
					ReportBuilder.previewPresupuestosCobradosGerenciaReport(dteFechaInicioCobrados,dteFechaFinCobrados);
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
    		else if(((JPanel)jTabbedPane1.getSelectedComponent()).equals(pnl_cobrados)){
    			selectCobrados();
    		}
    	}
    	
    }
    
    private class ChangeDailyButton implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(rd_daily.isSelected()){				
				jComboBox1.setEnabled(true);
		        jComboBox2.setEnabled(true);
		        dteFecha10.setEnabled(true);
		        jLabel40.setEnabled(true);		        
		        jLabel43.setEnabled(true);
		        jLabel29.setEnabled(true);
		        jLabel30.setEnabled(true);
			}
			else{
				jComboBox1.setEnabled(false);
		        jComboBox2.setEnabled(false);
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
				jComboBox3.setEnabled(true);
		        jComboBox4.setEnabled(true);
		        tx_week.setEnabled(true);
		        jLabel42.setEnabled(true);
		        jLabel31.setEnabled(true);
		        jLabel32.setEnabled(true);
		        jLabel44.setEnabled(true);
			}
			else{
				jComboBox3.setEnabled(false);
		        jComboBox4.setEnabled(false);
		        tx_week.setEnabled(false);
		        jLabel42.setEnabled(false);
		        jLabel31.setEnabled(false);
		        jLabel32.setEnabled(false);
		        jLabel44.setEnabled(false);
			}	
		}
		
	}

}
