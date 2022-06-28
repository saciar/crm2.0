package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang.StringUtils;

import crm.client.report.ReportBuilder;
import crm.client.util.DateConverter;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMEstadosComboBox;
import crm.gui.components.ABMLugarEvtComboBox;
import crm.gui.components.ABMTipoContactoComboBox;
import crm.gui.components.ABMUnidadesComercialesComboBox;
import crm.gui.components.ABMVendedoresComboBox;
import crm.gui.components.CondicionPagoComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.components.DatePanel;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;

public class BuscadorReportesGerencia extends PantallaEmergente{
	
	private javax.swing.ButtonGroup weeklyDailyGroup;
	private javax.swing.ButtonGroup otrosGroup;
	private javax.swing.ButtonGroup subcontGroup;
    private javax.swing.JCheckBox check_estado_ppto;
    private javax.swing.JCheckBox check_estados_porc;
    private javax.swing.JCheckBox check_lugar_ppto;
    private javax.swing.JCheckBox check_uc_diary;    
    private javax.swing.JCheckBox check_uc_ppto;
    private javax.swing.JCheckBox check_fechaCreacion_ppto;
    private javax.swing.JCheckBox check_uc_sub;
    private javax.swing.JCheckBox check_uc_week;
    private javax.swing.JCheckBox check_vend_ppto;
    private javax.swing.JCheckBox check_vendedor_diary;    
    private javax.swing.JCheckBox check_vendedor_sub;
    private javax.swing.JCheckBox check_vendedor_week;
    private JComboBox cmb_meses_otros;
    private ABMEstadosComboBox cmb_estados_porc;
    private ABMEstadosComboBox cmb_estados_ppto;
    private ABMLugarEvtComboBox cmb_lugar_ppto;
    private ABMUnidadesComercialesComboBox cmb_uc_ppto;
    private ABMUnidadesComercialesComboBox cmb_uc_sub;
    private ABMVendedoresComboBox cmb_vend_sub;
    private ABMVendedoresComboBox cmb_vendedor_ppto;
    private JComboBox cmb_simple_ppto;
    private DatePanel dteFechaDaily;
    private DatePanel dteFechaDiary;    
    private DatePanel dteFechaFinPorc;
    private DatePanel dteFechaFinCobrados;
    private DatePanel dteFechaFinSub;
    private DatePanel dteFechaHastaPpto;
    private DatePanel dteFechaInicioPorc;
    private DatePanel dteFechaInicioCobrados;
    private DatePanel dteFechaInicioPpto;
    private DatePanel dteFechaCreacion;
    private DatePanel dteFechaInicioSub;
    private DatePanel dteFechaMesAnio;
    private javax.swing.JButton exit;
    private javax.swing.JLabel lbl_title;    
    private javax.swing.JLabel lbl_desde_porc;
    private javax.swing.JLabel lbl_hasta_porc;
    private javax.swing.JLabel lbl_filtrar_porc;
    private javax.swing.JLabel lbl_selecRango_porc;
    private javax.swing.JLabel lbl_selecEstado_porc;
    private javax.swing.JLabel lbl_desde_cobrados;
    private javax.swing.JLabel lbl_hasta_cobrados;
    private javax.swing.JLabel lbl_selecRango_cobrados;
    private javax.swing.JLabel lbl_selecRango_sub;
    private javax.swing.JLabel lbl_desde_sub;
    private javax.swing.JLabel lbl_hasta_sub;
    private javax.swing.JLabel lbl_filtrar_sub;
    private javax.swing.JLabel lbl_selecRango_ppto;
    private javax.swing.JLabel lbl_selecVend_sub;
    private javax.swing.JLabel lbl_selecUC_sub;
    private javax.swing.JLabel lbl_selecTipo_sub;    
    private javax.swing.JLabel lbl_desde_ppto;
    private javax.swing.JLabel lbl_seleccione_otros;
    private javax.swing.JLabel lbl_ingrese_mesAnio;
    private javax.swing.JLabel lbl_ingrese_date;
    private javax.swing.JLabel lbl_lugar_otros;
    private javax.swing.JLabel lbl_seleccione_wd;
    private javax.swing.JLabel lbl_hasta_ppto;
    private javax.swing.JLabel lbl_ingrese_daily;
    private javax.swing.JLabel lbl_ingrese_week;
    private javax.swing.JLabel lbl_filtrarVend_wd;
    private javax.swing.JLabel lbl_filtrarUC_wd;
    private javax.swing.JLabel lbl_filtrar_ppto;
    private javax.swing.JLabel lbl_selecVend_ppto;
    private javax.swing.JLabel lbl_selecUC_ppto;
    private javax.swing.JLabel lbl_selecLugar_ppto;
    private javax.swing.JLabel lbl_selecEstado_ppto;
    private javax.swing.JLabel lbl_selecFechaCreacion_ppto;
    private javax.swing.JLabel lbl_selectSimple_ppto;
    private javax.swing.JPanel pnl_ppto;
    private javax.swing.JPanel pnl_porcentajes;
    private javax.swing.JPanel pnl_cobrados;
    private javax.swing.JPanel pnl_subcontratados;    
    private javax.swing.JPanel pnl_otros;
    private javax.swing.JPanel pnl_WeeklyDaily;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator_otros2;
    private javax.swing.JSeparator jSeparator_otros3;
    private javax.swing.JSeparator jSeparator_wd1;
    private javax.swing.JSeparator jSeparator_wd2;
    private javax.swing.JSeparator jSeparator_ppto;
    private javax.swing.JSeparator jSeparator_ppto2;
    private javax.swing.JSeparator jSeparator_porc;
    private javax.swing.JSeparator jSeparator_cobrados;
    private javax.swing.JSeparator jSeparator_sub1;    
    private javax.swing.JSeparator jSeparator_otros1;
    private javax.swing.JSeparator jSeparator_sub2;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JLabel lbl_Ingrese_week;
    private javax.swing.JRadioButton rd_daily;
    private javax.swing.JRadioButton rd_diary_planner;
    private javax.swing.JRadioButton rd_info_mensual;
    private javax.swing.JRadioButton rd_inform_semanal;
    private javax.swing.JRadioButton rd_lista_precios;
    private javax.swing.JRadioButton rd_sub_propios;
    private javax.swing.JRadioButton rd_sub_subcontratado;
    private javax.swing.JRadioButton rd_sub_ambos;
    private javax.swing.JRadioButton rd_weekly;
    private javax.swing.JLabel tipoPptos2;
    private javax.swing.JLabel tipoPptos3;    
    private javax.swing.JTextField txt_week;
    private javax.swing.JFormattedTextField txt_week_otros;
    private GradientButton viewReport; 
    private javax.swing.JLabel lbl_vendedor_wd1;
    private javax.swing.JLabel lbl_UC_wd1;
    private javax.swing.JLabel lbl_vendedor_wd2;
    private javax.swing.JLabel lbl_UC_wd2;
    private ABMVendedoresComboBox cmb_vendedores_weekly;
    private ABMUnidadesComercialesComboBox cmb_UC_weekly;
    private ABMVendedoresComboBox cmb_vendedores_daily;
    private ABMUnidadesComercialesComboBox cmb_UC_daily;
    
    private javax.swing.JPanel pnl_limites;
    private javax.swing.ButtonGroup montosGroup;
    private javax.swing.JRadioButton rd_montos_mayores;
    private javax.swing.JRadioButton rd_montos_menores;
    private javax.swing.JTextField txt_limite;
    private javax.swing.JCheckBox check_vendedor_lim;
    private javax.swing.JCheckBox check_uc_lim;
    private javax.swing.JSeparator jSeparator_lim2;
    private javax.swing.JSeparator jSeparator_lim1;
    private javax.swing.JLabel lbl_selecRango_lim;
    private javax.swing.JLabel lbl_desde_lim;
    private javax.swing.JLabel lbl_hasta_lim;
    private javax.swing.JLabel lbl_filtrar_lim;
    private javax.swing.JLabel lbl_selecVend_lim;
    private javax.swing.JLabel lbl_selecUC_lim;
    private javax.swing.JLabel lbl_selecMonto_lim;
    private DatePanel dteFechaInicioLim;
    private DatePanel dteFechaFinLim;
    private ABMVendedoresComboBox cmb_vend_lim;
    private ABMUnidadesComercialesComboBox cmb_uc_lim;
    
    private javax.swing.JPanel pnl_limite_Estado;
    private javax.swing.ButtonGroup montosGroupLimEstado;
    private javax.swing.ButtonGroup fechasGroup;
    private javax.swing.JRadioButton rd_montos_mayores_le;
    private javax.swing.JRadioButton rd_montos_menores_le;
    private javax.swing.JTextField txt_limite_le;
    private javax.swing.JSeparator jSeparator_le;
    private javax.swing.JSeparator jSeparator_le2;
    private javax.swing.JSeparator jSeparator_le3;
    private javax.swing.JRadioButton rd_estados_menores_le;
    private javax.swing.JRadioButton rd_estados_mayores_le;
    private javax.swing.JLabel lbl_selecRango_le;
    private javax.swing.JLabel lbl_desde_le;
    private javax.swing.JLabel lbl_hasta_le;
    private javax.swing.JLabel lbl_filtrar_le;
    private javax.swing.JLabel lbl_vendedor_le;
    private javax.swing.JLabel lbl_UC_le;
    private javax.swing.JLabel lbl_selecMonto_le;
    private javax.swing.JLabel lbl_selecEstado_le;
    private ABMEstadosComboBox cmb_estados_le;
    private DatePanel dteFechaInicio_le;
    private DatePanel dteFechaFin_le;
    private ABMVendedoresComboBox cmb_vend_le;
    private javax.swing.JCheckBox check_vendedor_le;
    private javax.swing.JCheckBox check_uc_le;
    private ABMUnidadesComercialesComboBox cmb_uc_le;
    
    private javax.swing.JPanel pnl_condPago;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator17;
    private DatePanel dteFechaInicioCondPago;
    private DatePanel dteFechaFinCondPago;
    private ABMVendedoresComboBox cmb_vend_condPago;
    private javax.swing.JCheckBox check_vendedor_condPago;
    private ABMUnidadesComercialesComboBox cmb_uc_condPago;
    private javax.swing.JCheckBox check_uc_condPago;
    private CondicionPagoComboBox cmb_condPago;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel64;
    
    private javax.swing.JPanel pnl_referencia;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator20;
    private DatePanel dteFechaInicioCondPago1;
    private DatePanel dteFechaFinCondPago1;
    private ABMVendedoresComboBox cmb_vend_condPago1;
    private javax.swing.JCheckBox check_vendedor_condPago1;
    private ABMUnidadesComercialesComboBox cmb_uc_condPago1;
    private javax.swing.JCheckBox check_uc_condPago1;
    private ABMTipoContactoComboBox cmb_condPago1;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    
    public BuscadorReportesGerencia (Frame owner){
		super("Buscador de reportes",owner);
		this.setModal(false);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setEnabled(true);
        createMenuBar();
             
    }
    
    private void createMenuBar(){
    	jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
		
        jMenuBar1.setSize(this.getWidth(),this.getHeight());
		jMenu1.setMnemonic('A');
        jMenu1.setText("Archivo");
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        
        jMenuItem1.setMnemonic('R');
        jMenuItem1.setText("Refrescar esta pantalla");
        jMenuItem1.addActionListener(new RefreshAction());
        jMenu1.add(jMenuItem1);
        jMenuItem2.setMnemonic('C');
        jMenuItem2.setText("Cerrar");
        jMenuItem2.addActionListener(new ExitAction());
        jMenu1.add(jMenuItem2);
        
        jMenuBar1.add(jMenu1);
		
        this.setJMenuBar(jMenuBar1);   
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
		
		weeklyDailyGroup = new javax.swing.ButtonGroup();
		otrosGroup = new javax.swing.ButtonGroup();
		subcontGroup = new javax.swing.ButtonGroup();
		montosGroup = new javax.swing.ButtonGroup();
		montosGroupLimEstado = new javax.swing.ButtonGroup();
		fechasGroup = new javax.swing.ButtonGroup();
        lbl_title = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        tabbedPane = new javax.swing.JTabbedPane();
        
        pnl_otros = new javax.swing.JPanel();
        tipoPptos2 = new javax.swing.JLabel();
        lbl_seleccione_otros = new javax.swing.JLabel();
        rd_inform_semanal = new javax.swing.JRadioButton();
        lbl_Ingrese_week = new javax.swing.JLabel();
        txt_week_otros = new javax.swing.JFormattedTextField();
        jSeparator_otros1 = new javax.swing.JSeparator();
        rd_info_mensual = new javax.swing.JRadioButton();
        lbl_ingrese_mesAnio = new javax.swing.JLabel();
        dteFechaMesAnio = CustomTextField.getDateInstance();
        dteFechaMesAnio.setEnabledDay(false);
        jSeparator_otros2 = new javax.swing.JSeparator();
        rd_diary_planner = new javax.swing.JRadioButton();
        lbl_ingrese_date = new javax.swing.JLabel();
        dteFechaDiary = CustomTextField.getDateInstance();
        jSeparator_otros3 = new javax.swing.JSeparator();
        rd_lista_precios = new javax.swing.JRadioButton();
        lbl_lugar_otros = new javax.swing.JLabel();
        cmb_meses_otros = new JComboBox();
        
        pnl_ppto = new javax.swing.JPanel();
        lbl_selecRango_ppto = new javax.swing.JLabel();
        dteFechaInicioPpto = CustomTextField.getDateInstance();
        lbl_desde_ppto = new javax.swing.JLabel();
        lbl_hasta_ppto = new javax.swing.JLabel();
        dteFechaHastaPpto = CustomTextField.getDateInstance();
        lbl_filtrar_ppto = new javax.swing.JLabel();
        check_vend_ppto = new javax.swing.JCheckBox();
        lbl_selecVend_ppto = new javax.swing.JLabel();
        cmb_vendedor_ppto = new ABMVendedoresComboBox();
        check_uc_ppto = new javax.swing.JCheckBox();
        lbl_selecUC_ppto = new javax.swing.JLabel();
        cmb_uc_ppto = new ABMUnidadesComercialesComboBox();
        check_lugar_ppto = new javax.swing.JCheckBox();
        lbl_selecLugar_ppto = new javax.swing.JLabel();
        cmb_lugar_ppto = new ABMLugarEvtComboBox();
        jSeparator_ppto = new javax.swing.JSeparator();
        check_estado_ppto = new javax.swing.JCheckBox();
        lbl_selecEstado_ppto = new javax.swing.JLabel();
        cmb_estados_ppto = new ABMEstadosComboBox();
        lbl_selecFechaCreacion_ppto = new javax.swing.JLabel();
        check_fechaCreacion_ppto = new javax.swing.JCheckBox();
        dteFechaCreacion = CustomTextField.getDateInstance();
        lbl_selectSimple_ppto = new javax.swing.JLabel();
        jSeparator_ppto2 = new javax.swing.JSeparator();
        cmb_simple_ppto = new javax.swing.JComboBox();        
        
        pnl_porcentajes = new javax.swing.JPanel();
        lbl_selecRango_porc = new javax.swing.JLabel();
        lbl_desde_porc = new javax.swing.JLabel();
        dteFechaInicioPorc = CustomTextField.getDateInstance();
        lbl_hasta_porc = new javax.swing.JLabel();
        dteFechaFinPorc = CustomTextField.getDateInstance();
        jSeparator_porc = new javax.swing.JSeparator();
        lbl_filtrar_porc = new javax.swing.JLabel();
        check_estados_porc = new javax.swing.JCheckBox();
        lbl_selecEstado_porc = new javax.swing.JLabel();
        cmb_estados_porc = new ABMEstadosComboBox();
        
        pnl_subcontratados = new javax.swing.JPanel();
        lbl_selecRango_sub = new javax.swing.JLabel();
        lbl_desde_sub = new javax.swing.JLabel();
        dteFechaInicioSub = CustomTextField.getDateInstance();
        lbl_hasta_sub = new javax.swing.JLabel();
        dteFechaFinSub = CustomTextField.getDateInstance();
        jSeparator_sub1 = new javax.swing.JSeparator();
        lbl_filtrar_sub = new javax.swing.JLabel();
        check_vendedor_sub = new javax.swing.JCheckBox();
        check_uc_sub = new javax.swing.JCheckBox();
        lbl_selecVend_sub = new javax.swing.JLabel();
        cmb_vend_sub = new ABMVendedoresComboBox();
        lbl_selecUC_sub = new javax.swing.JLabel();
        cmb_uc_sub = new ABMUnidadesComercialesComboBox();
        lbl_selecTipo_sub = new javax.swing.JLabel();
        rd_sub_propios = new javax.swing.JRadioButton();
        rd_sub_subcontratado = new javax.swing.JRadioButton();
        rd_sub_ambos = new javax.swing.JRadioButton();
        jSeparator_sub2 = new javax.swing.JSeparator();
        
        pnl_limites = new javax.swing.JPanel();
        lbl_selecRango_lim = new javax.swing.JLabel();
        lbl_desde_lim = new javax.swing.JLabel();
        dteFechaInicioLim = CustomTextField.getDateInstance();
        lbl_hasta_lim = new javax.swing.JLabel();
        dteFechaFinLim = CustomTextField.getDateInstance();
        jSeparator_lim1 = new javax.swing.JSeparator();
        lbl_filtrar_lim = new javax.swing.JLabel();
        check_vendedor_lim = new javax.swing.JCheckBox();
        lbl_selecVend_lim = new javax.swing.JLabel();
        cmb_vend_lim = new ABMVendedoresComboBox();
        check_uc_lim = new javax.swing.JCheckBox();
        lbl_selecUC_lim = new javax.swing.JLabel();
        cmb_uc_lim = new ABMUnidadesComercialesComboBox();
        lbl_selecMonto_lim = new javax.swing.JLabel();
        txt_limite = CustomTextField.getDecimalInstance(10,2);
        rd_montos_mayores = new javax.swing.JRadioButton();
        rd_montos_menores = new javax.swing.JRadioButton();
        jSeparator_lim2 = new javax.swing.JSeparator();
        
        pnl_WeeklyDaily = new javax.swing.JPanel();
        tipoPptos3 = new javax.swing.JLabel();
        lbl_seleccione_wd = new javax.swing.JLabel();
        jSeparator_wd1 = new javax.swing.JSeparator();
        rd_daily = new javax.swing.JRadioButton();
        lbl_ingrese_daily = new javax.swing.JLabel();
        dteFechaDaily = CustomTextField.getDateInstance();
        jSeparator_wd2 = new javax.swing.JSeparator();
        rd_weekly = new javax.swing.JRadioButton();
        lbl_ingrese_week = new javax.swing.JLabel();
        txt_week = new javax.swing.JTextField();
        lbl_filtrarVend_wd = new javax.swing.JLabel();
        check_vendedor_diary = new javax.swing.JCheckBox();
        check_uc_diary = new javax.swing.JCheckBox();
        lbl_filtrarUC_wd = new javax.swing.JLabel();
        check_vendedor_week = new javax.swing.JCheckBox();
        check_uc_week = new javax.swing.JCheckBox();
        lbl_vendedor_wd1 = new javax.swing.JLabel();
        cmb_vendedores_weekly = new ABMVendedoresComboBox();
        lbl_UC_wd1 = new javax.swing.JLabel();
        cmb_UC_weekly = new ABMUnidadesComercialesComboBox();
        lbl_vendedor_wd2 = new javax.swing.JLabel();
        cmb_vendedores_daily = new ABMVendedoresComboBox();
        lbl_UC_wd2 = new javax.swing.JLabel();
        cmb_UC_daily = new ABMUnidadesComercialesComboBox();
        
        pnl_limite_Estado = new javax.swing.JPanel();
        lbl_selecRango_le = new javax.swing.JLabel();
        lbl_desde_le = new javax.swing.JLabel();
        dteFechaInicio_le = CustomTextField.getDateInstance();
        lbl_hasta_le = new javax.swing.JLabel();
        dteFechaFin_le = CustomTextField.getDateInstance();
        jSeparator_le = new javax.swing.JSeparator();
        lbl_filtrar_le = new javax.swing.JLabel();
        check_vendedor_le = new javax.swing.JCheckBox();
        lbl_vendedor_le = new javax.swing.JLabel();
        cmb_vend_le = new ABMVendedoresComboBox();
        check_uc_le = new javax.swing.JCheckBox();
        lbl_UC_le = new javax.swing.JLabel();
        cmb_uc_le = new ABMUnidadesComercialesComboBox();
        lbl_selecMonto_le = new javax.swing.JLabel();
        txt_limite_le = CustomTextField.getDecimalInstance(10,2);
        rd_montos_mayores_le = new javax.swing.JRadioButton();
        rd_montos_menores_le = new javax.swing.JRadioButton();
        jSeparator_le2 = new javax.swing.JSeparator();
        lbl_selecEstado_le = new javax.swing.JLabel();
        cmb_estados_le = new ABMEstadosComboBox();
        rd_estados_menores_le = new javax.swing.JRadioButton();
        rd_estados_mayores_le = new javax.swing.JRadioButton();
        jSeparator_le3 = new javax.swing.JSeparator();
        
        pnl_condPago = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        dteFechaInicioCondPago = CustomTextField.getDateInstance();
        jLabel59 = new javax.swing.JLabel();
        dteFechaFinCondPago = CustomTextField.getDateInstance();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel60 = new javax.swing.JLabel();
        check_vendedor_condPago = new javax.swing.JCheckBox();
        jLabel61 = new javax.swing.JLabel();
        cmb_vend_condPago = new ABMVendedoresComboBox();
        check_uc_condPago = new javax.swing.JCheckBox();
        jLabel62 = new javax.swing.JLabel();
        cmb_uc_condPago = new ABMUnidadesComercialesComboBox();
        jLabel64 = new javax.swing.JLabel();
        cmb_condPago = new CondicionPagoComboBox();
        jSeparator13 = new javax.swing.JSeparator();       
        
        pnl_cobrados = new javax.swing.JPanel();
        lbl_selecRango_cobrados = new javax.swing.JLabel();
        lbl_desde_cobrados = new javax.swing.JLabel();
        dteFechaInicioCobrados = CustomTextField.getDateInstance();
        lbl_hasta_cobrados = new javax.swing.JLabel();
        dteFechaFinCobrados = CustomTextField.getDateInstance();
        jSeparator_cobrados= new javax.swing.JSeparator();
        
        pnl_referencia = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        dteFechaInicioCondPago1 = CustomTextField.getDateInstance();
        jLabel69 = new javax.swing.JLabel();
        dteFechaFinCondPago1 = CustomTextField.getDateInstance();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel70 = new javax.swing.JLabel();
        check_vendedor_condPago1 = new javax.swing.JCheckBox();
        jLabel71 = new javax.swing.JLabel();
        cmb_vend_condPago1 = new ABMVendedoresComboBox();
        check_uc_condPago1 = new javax.swing.JCheckBox();
        jLabel72 = new javax.swing.JLabel();
        cmb_uc_condPago1 = new ABMUnidadesComercialesComboBox();
        jLabel73 = new javax.swing.JLabel();
        cmb_condPago1 = new ABMTipoContactoComboBox();
        jSeparator20 = new javax.swing.JSeparator();
        
        viewReport = new GradientButton("", Color.blue);
        exit = new GradientButton("", Color.blue);              

        lbl_title.setFont(new java.awt.Font("Tahoma", 1, 14));
        lbl_title.setText("Reportes");

        lbl_selecRango_ppto.setText("Seleccione un rango de fechas para ver informaci\u00f3n de presupuestos");

        lbl_desde_ppto.setText("Desde");

        lbl_hasta_ppto.setText("Hasta");

        lbl_filtrar_ppto.setText("Filtrar por:");

        check_vend_ppto.setText("Vendedor");
        check_vend_ppto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_vend_ppto.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_selecVend_ppto.setText("Seleccione el vendedor");

        check_uc_ppto.setText("Unidad comercial");
        check_uc_ppto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_uc_ppto.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_selecUC_ppto.setText("Seleccione la unidad comercial");

        check_lugar_ppto.setText("Lugar de evento");
        check_lugar_ppto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_lugar_ppto.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_selecLugar_ppto.setText("Seleccione el lugar de evento");

        check_estado_ppto.setText("Estado del presupuesto");
        check_estado_ppto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_estado_ppto.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_selecEstado_ppto.setText("Seleccione un estado");
        
        check_fechaCreacion_ppto.setText("Fecha de creaci�n");
        check_fechaCreacion_ppto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_fechaCreacion_ppto.setMargin(new java.awt.Insets(0, 0, 0, 0));
        
        lbl_selecFechaCreacion_ppto.setText("Creado el d�a");
        
        lbl_selectSimple_ppto.setText("Seleccione los eventos que quiere visualizar segun");

        cmb_simple_ppto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Eventos completos dentro del rango seleccionado", "Eventos incompletos dentro del rango seleccionado" }));
        
        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(pnl_ppto);
        pnl_ppto.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jSeparator_ppto2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                        .add(jSeparator_ppto, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                        .add(lbl_selecRango_ppto)
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(10, 10, 10)
                            .add(lbl_desde_ppto)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(dteFechaInicioPpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(lbl_hasta_ppto)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(dteFechaHastaPpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(lbl_filtrar_ppto)
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(33, 33, 33)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(check_vend_ppto)
                                .add(check_uc_ppto)
                                .add(check_lugar_ppto)
                                .add(check_estado_ppto)
                                .add(check_fechaCreacion_ppto))
                            .add(32, 32, 32)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(lbl_selecUC_ppto)
                                .add(lbl_selecVend_ppto)
                                .add(lbl_selecEstado_ppto)
                                .add(lbl_selecLugar_ppto)
                                .add(lbl_selecFechaCreacion_ppto))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(dteFechaCreacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(cmb_vendedor_ppto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, cmb_estados_ppto, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, cmb_lugar_ppto, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, cmb_uc_ppto, 0, 228, Short.MAX_VALUE))))
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(lbl_selectSimple_ppto)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(cmb_simple_ppto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 330, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(lbl_selecRango_ppto)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(lbl_desde_ppto)
                        .add(dteFechaInicioPpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(lbl_hasta_ppto)
                        .add(dteFechaHastaPpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator_ppto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(lbl_filtrar_ppto)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(check_vend_ppto)
                        .add(cmb_vendedor_ppto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(lbl_selecVend_ppto))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(check_uc_ppto)
                        .add(lbl_selecUC_ppto)
                        .add(cmb_uc_ppto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(cmb_lugar_ppto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(lbl_selecLugar_ppto)
                        .add(check_lugar_ppto))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(check_estado_ppto)
                        .add(lbl_selecEstado_ppto)
                        .add(cmb_estados_ppto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(check_fechaCreacion_ppto)
                            .add(lbl_selecFechaCreacion_ppto))
                        .add(dteFechaCreacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator_ppto2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(lbl_selectSimple_ppto)
                        .add(cmb_simple_ppto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(81, Short.MAX_VALUE))
            );
        tabbedPane.addTab("Presupuestos", pnl_ppto);

        lbl_selecRango_porc.setText("Seleccione un rango de fechas para ver informaci\u00f3n de presupuestos");

        lbl_desde_porc.setText("Desde");

        lbl_hasta_porc.setText("Hasta");

        lbl_filtrar_porc.setText("Filtrar por:");

        check_estados_porc.setText("Estado del presupuesto");
        check_estados_porc.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_estados_porc.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_selecEstado_porc.setText("Seleccione un estado");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(pnl_porcentajes);
        pnl_porcentajes.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator_porc)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(lbl_desde_porc)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(dteFechaInicioPorc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lbl_hasta_porc)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(dteFechaFinPorc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(lbl_selecRango_porc)
                            .add(lbl_filtrar_porc)))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createSequentialGroup()
                        .add(44, 44, 44)
                        .add(check_estados_porc)
                        .add(55, 55, 55)
                        .add(lbl_selecEstado_porc)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cmb_estados_porc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 228, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(lbl_selecRango_porc)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lbl_desde_porc)
                    .add(dteFechaInicioPorc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lbl_hasta_porc)
                    .add(dteFechaFinPorc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator_porc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lbl_filtrar_porc)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(check_estados_porc)
                    .add(lbl_selecEstado_porc)
                    .add(cmb_estados_porc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        tabbedPane.addTab("Porcentajes", pnl_porcentajes);

        lbl_selecRango_sub.setText("Seleccione un rango de fechas para ver informaci\u00f3n de presupuestos");

        lbl_desde_sub.setText("Desde");

        lbl_hasta_sub.setText("Hasta");
        
        lbl_filtrar_sub.setText("Filtrar por:");

        check_vendedor_sub.setText("Vendedor");
        check_vendedor_sub.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_vendedor_sub.setMargin(new java.awt.Insets(0, 0, 0, 0));

        check_uc_sub.setText("Unidad comercial");
        check_uc_sub.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_uc_sub.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_selecVend_sub.setText("Seleccione el vendedor");       

        lbl_selecUC_sub.setText("Seleccione la unidad comercial");       

        lbl_selecTipo_sub.setText("Seleccione el tipo de subcontrataciones que desea visualizar");

        subcontGroup.add(rd_sub_propios);
        rd_sub_propios.setText("Subcontrataciones de servicios propios ");
        rd_sub_propios.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rd_sub_propios.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rd_sub_propios.setSelected(true);
        
        subcontGroup.add(rd_sub_subcontratado);
        rd_sub_subcontratado.setText("Subcontrataciones de servicios de familia subcontratado");
        rd_sub_subcontratado.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rd_sub_subcontratado.setMargin(new java.awt.Insets(0, 0, 0, 0));
        
        subcontGroup.add(rd_sub_ambos);
        rd_sub_ambos.setText("Ambos casos");
        rd_sub_ambos.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rd_sub_ambos.setMargin(new java.awt.Insets(0, 0, 0, 0));
        
        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(pnl_subcontratados);
        pnl_subcontratados.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(lbl_desde_sub)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(dteFechaInicioSub, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lbl_hasta_sub)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(dteFechaFinSub, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(lbl_selecRango_sub)))
                    .add(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, lbl_filtrar_sub)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3Layout.createSequentialGroup()
                                .add(33, 33, 33)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(check_vendedor_sub)
                                    .add(check_uc_sub))
                                .add(68, 68, 68)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(lbl_selecVend_sub)
                                    .add(lbl_selecUC_sub))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(cmb_uc_sub, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(cmb_vend_sub, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 220, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                    .add(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(lbl_selecTipo_sub)
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(rd_sub_subcontratado)
                                    .add(rd_sub_propios)
                                    .add(rd_sub_ambos)))))
                    .add(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jSeparator_sub2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
                    .add(jSeparator_sub1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(lbl_selecRango_sub)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lbl_desde_sub)
                    .add(dteFechaInicioSub, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lbl_hasta_sub)
                    .add(dteFechaFinSub, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator_sub1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lbl_selecTipo_sub)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(rd_sub_propios)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(rd_sub_subcontratado)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(rd_sub_ambos)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator_sub2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 16, Short.MAX_VALUE)
                .add(lbl_filtrar_sub)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(check_vendedor_sub)
                    .add(lbl_selecVend_sub)
                    .add(cmb_vend_sub, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(check_uc_sub)
                    .add(lbl_selecUC_sub)
                    .add(cmb_uc_sub, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        tabbedPane.addTab("Subcontratados", pnl_subcontratados);

        lbl_selecRango_lim.setText("Seleccione un rango de fechas para ver informaci\u00f3n de presupuestos");

        lbl_desde_lim.setText("Desde");

        lbl_hasta_lim.setText("Hasta");

        lbl_filtrar_lim.setText("Filtrar por:");

        check_vendedor_lim.setText("Vendedor");
        check_vendedor_lim.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_vendedor_lim.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_selecVend_lim.setText("Seleccione el vendedor");       

        check_uc_lim.setText("Unidad comercial");
        check_uc_lim.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_uc_lim.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_selecUC_lim.setText("Seleccione la unidad comercial");       

        lbl_selecMonto_lim.setText("Seleccione limite de monto $");
        
        montosGroup.add(rd_montos_mayores);
        rd_montos_mayores.setText("Montos mayores o iguales");
        rd_montos_mayores.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rd_montos_mayores.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rd_montos_mayores.setSelected(true);

        montosGroup.add(rd_montos_menores);
        rd_montos_menores.setText("Montos menores");
        rd_montos_menores.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rd_montos_menores.setMargin(new java.awt.Insets(0, 0, 0, 0));
        
        txt_limite.setText("0.00");
        
        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(pnl_limites);
        pnl_limites.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel4Layout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(lbl_desde_lim)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(dteFechaInicioLim, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lbl_hasta_lim)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(dteFechaFinLim, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(lbl_selecRango_lim)))
                    .add(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(lbl_selecMonto_lim)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txt_limite, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(rd_montos_menores)
                            .add(rd_montos_mayores)))
                    .add(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jSeparator_lim2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
                    .add(jSeparator_lim1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                    .add(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, lbl_filtrar_lim)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel4Layout.createSequentialGroup()
                                .add(30, 30, 30)
                                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jPanel4Layout.createSequentialGroup()
                                        .add(check_uc_lim)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(lbl_selecUC_lim))
                                    .add(jPanel4Layout.createSequentialGroup()
                                        .add(check_vendedor_lim)
                                        .add(139, 139, 139)
                                        .add(lbl_selecVend_lim)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(cmb_uc_lim, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(cmb_vend_lim, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(lbl_selecRango_lim)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lbl_desde_lim)
                    .add(dteFechaInicioLim, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lbl_hasta_lim)
                    .add(dteFechaFinLim, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator_lim1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lbl_selecMonto_lim)
                    .add(txt_limite, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(rd_montos_mayores))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(rd_montos_menores)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator_lim2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lbl_filtrar_lim)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(check_vendedor_lim)
                    .add(lbl_selecVend_lim)
                    .add(cmb_vend_lim, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(check_uc_lim)
                    .add(lbl_selecUC_lim)
                    .add(cmb_uc_lim, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        tabbedPane.addTab("Limites ", pnl_limites);

        tipoPptos3.setFont(new java.awt.Font("Tahoma", 1, 14));

        lbl_seleccione_wd.setText("Seleccione un tipo de reporte a visualizar");
        
        weeklyDailyGroup.add(rd_daily);
        rd_daily.setText("Daily");
        rd_daily.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rd_daily.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rd_daily.setSelected(true);
        
        lbl_ingrese_daily.setText("Ingrese fecha:");
        
        weeklyDailyGroup.add(rd_weekly);
        rd_weekly.setText("Weekly");
        rd_weekly.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rd_weekly.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_ingrese_week.setText("Ingrese nro de week:");
        lbl_ingrese_week.setEnabled(false);
        
        lbl_filtrarVend_wd.setText("Filtrar por:");
        
       // check_vendedor_diary.setText("Vendedor");
       // check_vendedor_diary.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
       // check_vendedor_diary.setMargin(new java.awt.Insets(0, 0, 0, 0));

      //  check_uc_diary.setText("Unidad comercial");
      //  check_uc_diary.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
      //  check_uc_diary.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_filtrarUC_wd.setText("Filtrar por:");       
        lbl_filtrarUC_wd.setEnabled(false);
        
        lbl_vendedor_wd1.setText("Vendedor");       
        
        lbl_UC_wd1.setText("Unidad comercial");       
        
        lbl_vendedor_wd2.setText("Vendedor");        
        lbl_vendedor_wd2.setEnabled(false);
        
        lbl_UC_wd2.setText("Unidad comercial");       
        lbl_UC_wd2.setEnabled(false);
        
        //cmb_vendedores_daily.setEnabled(false);
        //cmb_UC_daily.setEnabled(false);
        txt_week.setEnabled(false);
        
        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(pnl_WeeklyDaily);
        pnl_WeeklyDaily.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSeparator_wd1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(lbl_seleccione_wd)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(tipoPptos3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(74, 74, 74)
                        .add(lbl_filtrarVend_wd)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lbl_vendedor_wd1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cmb_vendedores_weekly, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 146, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lbl_UC_wd1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cmb_UC_weekly, 0, 162, Short.MAX_VALUE))
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(19, 19, 19)
                        .add(rd_weekly)
                        .add(84, 84, 84)
                        .add(lbl_ingrese_week)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txt_week, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(74, 74, 74)
                        .add(lbl_filtrarUC_wd)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lbl_vendedor_wd2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cmb_vendedores_daily, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 146, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lbl_UC_wd2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cmb_UC_daily, 0, 162, Short.MAX_VALUE))
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(19, 19, 19)
                        .add(rd_daily)
                        .add(94, 94, 94)
                        .add(lbl_ingrese_daily)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dteFechaDaily, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jSeparator_wd2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tipoPptos3)
                    .add(lbl_seleccione_wd))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator_wd1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(rd_daily)
                        .add(lbl_ingrese_daily))
                    .add(dteFechaDaily, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(8, 8, 8)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lbl_filtrarVend_wd)
                    .add(lbl_vendedor_wd1)
                    .add(cmb_vendedores_weekly, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lbl_UC_wd1)
                    .add(cmb_UC_weekly, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator_wd2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(rd_weekly)
                    .add(lbl_ingrese_week)
                    .add(txt_week, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(lbl_filtrarUC_wd)
                        .add(lbl_vendedor_wd2))
                    .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(cmb_vendedores_daily, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(lbl_UC_wd2)
                        .add(cmb_UC_daily, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        tabbedPane.addTab("Week & Daily", pnl_WeeklyDaily);

        tipoPptos2.setFont(new java.awt.Font("Tahoma", 1, 14));

        lbl_seleccione_otros.setText("Seleccione un tipo de reporte a visualizar");
        
        otrosGroup.add(rd_inform_semanal);
        rd_inform_semanal.setText("Informaci\u00f3n semanal");
        rd_inform_semanal.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rd_inform_semanal.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rd_inform_semanal.setSelected(true);

        lbl_Ingrese_week.setText("Ingrese nro de week:");
        
        otrosGroup.add(rd_info_mensual);
        rd_info_mensual.setText("Informaci\u00f3n mensual");
        rd_info_mensual.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rd_info_mensual.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_ingrese_mesAnio.setText("Ingrese mes y a\u00f1o:");
        
        otrosGroup.add(rd_diary_planner);
        rd_diary_planner.setText("Diary planner");
        rd_diary_planner.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rd_diary_planner.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_ingrese_date.setText("Ingrese fecha:");
        
        otrosGroup.add(rd_lista_precios);
        rd_lista_precios.setText("Lista de precios");
        rd_lista_precios.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rd_lista_precios.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_lugar_otros.setText("Seleccione un lugar");
        
        cmb_meses_otros.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril",
        		"Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
        
        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(pnl_otros);
        pnl_otros.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(lbl_seleccione_otros)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(tipoPptos2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(rd_inform_semanal)
                        .add(44, 44, 44)
                        .add(lbl_Ingrese_week)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txt_week_otros, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 204, Short.MAX_VALUE))
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(19, 19, 19)
                        .add(rd_diary_planner)
                        .add(94, 94, 94)
                        .add(lbl_ingrese_date)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dteFechaDiary, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(rd_lista_precios)
                        .add(85, 85, 85)
                        .add(lbl_lugar_otros)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cmb_meses_otros, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 163, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator_otros2)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator_otros3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
                    .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator_otros1)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel5Layout.createSequentialGroup()
                            .add(20, 20, 20)
                            .add(rd_info_mensual)
                            .add(56, 56, 56)
                            .add(lbl_ingrese_mesAnio)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(dteFechaMesAnio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tipoPptos2)
                    .add(lbl_seleccione_otros))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lbl_Ingrese_week)
                    .add(txt_week_otros, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(rd_inform_semanal))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator_otros1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(rd_info_mensual)
                        .add(lbl_ingrese_mesAnio))
                    .add(dteFechaMesAnio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator_otros2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(rd_diary_planner)
                        .add(lbl_ingrese_date))
                    .add(dteFechaDiary, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator_otros3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(rd_lista_precios)
                    .add(lbl_lugar_otros)
                    .add(cmb_meses_otros, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        tabbedPane.addTab("Otros", pnl_otros);
        
        lbl_selecRango_le.setText("Seleccione un rango de fechas para ver informaci\u00f3n de presupuestos");

        lbl_desde_le.setText("Desde");

        lbl_hasta_le.setText("Hasta");

        lbl_filtrar_le.setText("Filtrar por:");

        check_vendedor_le.setText("Vendedor");
        check_vendedor_le.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_vendedor_le.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_vendedor_le.setText("Seleccione el vendedor");        

        check_uc_le.setText("Unidad comercial");
        check_uc_le.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_uc_le.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_UC_le.setText("Seleccione la unidad comercial");        

        lbl_selecMonto_le.setText("Seleccione limite de monto $");
        
        montosGroupLimEstado.add(rd_montos_mayores_le);
        rd_montos_mayores_le.setText("Montos mayores");
        rd_montos_mayores_le.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rd_montos_mayores_le.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rd_montos_mayores_le.setSelected(true);
        
        montosGroupLimEstado.add(rd_montos_menores_le);
        rd_montos_menores_le.setText("Montos menores");
        rd_montos_menores_le.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rd_montos_menores_le.setMargin(new java.awt.Insets(0, 0, 0, 0));

        lbl_selecEstado_le.setText("Seleccione un estado");        
        
        fechasGroup.add(rd_estados_menores_le);
        rd_estados_menores_le.setText("Fecha de cambio de estado menor a fecha inicial");
        rd_estados_menores_le.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rd_estados_menores_le.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rd_estados_menores_le.setSelected(true);
        
        fechasGroup.add(rd_estados_mayores_le);
        rd_estados_mayores_le.setText("Fecha de cambio de estado mayor o igual a fecha inicial");
        rd_estados_mayores_le.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rd_estados_mayores_le.setMargin(new java.awt.Insets(0, 0, 0, 0));
        
        txt_limite_le.setText("0.00");
        
        org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(pnl_limite_Estado);
        pnl_limite_Estado.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jSeparator_le, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(lbl_desde_le)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dteFechaInicio_le, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lbl_hasta_le)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dteFechaFin_le, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(lbl_selecRango_le)
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(lbl_selecEstado_le)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cmb_estados_le, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 187, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(rd_estados_mayores_le)
                            .add(rd_estados_menores_le)))
                    .add(jSeparator_le3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .add(lbl_filtrar_le)
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(30, 30, 30)
                        .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPanel8Layout.createSequentialGroup()
                                .add(check_uc_le)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(lbl_UC_le))
                            .add(jPanel8Layout.createSequentialGroup()
                                .add(check_vendedor_le)
                                .add(139, 139, 139)
                                .add(lbl_vendedor_le)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(cmb_uc_le, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(cmb_vend_le, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jSeparator_le2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(lbl_selecMonto_le)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txt_limite_le, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(rd_montos_menores_le)
                            .add(rd_montos_mayores_le))
                        .add(104, 104, 104)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(lbl_selecRango_le)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lbl_desde_le)
                    .add(dteFechaInicio_le, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lbl_hasta_le)
                    .add(dteFechaFin_le, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator_le, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lbl_selecEstado_le)
                    .add(cmb_estados_le, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(rd_estados_menores_le)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(rd_estados_mayores_le)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator_le3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lbl_selecMonto_le)
                    .add(txt_limite_le, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(rd_montos_mayores_le))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(rd_montos_menores_le)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator_le2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lbl_filtrar_le)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(check_vendedor_le)
                    .add(lbl_vendedor_le)
                    .add(cmb_vend_le, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(check_uc_le)
                    .add(lbl_UC_le)
                    .add(cmb_uc_le, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        tabbedPane.addTab("Estado + limites", pnl_limite_Estado);
        
        jLabel57.setText("Seleccione un rango de fechas para ver informaci\u00f3n de presupuestos");

        jLabel58.setText("Desde");

        jLabel59.setText("Hasta");

        jLabel60.setText("Filtrar por:");

        check_vendedor_condPago.setText("Vendedor");
        check_vendedor_condPago.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_vendedor_condPago.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel61.setText("Seleccione el vendedor");        

        check_uc_condPago.setText("Unidad comercial");
        check_uc_condPago.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_uc_condPago.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel62.setText("Seleccione la unidad comercial");       

        jLabel64.setText("Seleccione una condici\u00f3n de pago");       

        org.jdesktop.layout.GroupLayout pnl_condPagoLayout = new org.jdesktop.layout.GroupLayout(pnl_condPago);
        pnl_condPago.setLayout(pnl_condPagoLayout);
        pnl_condPagoLayout.setHorizontalGroup(
            pnl_condPagoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnl_condPagoLayout.createSequentialGroup()
                .addContainerGap()
                .add(pnl_condPagoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jSeparator17, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .add(pnl_condPagoLayout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jLabel58)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dteFechaInicioCondPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel59)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dteFechaFinCondPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jLabel57)
                    .add(pnl_condPagoLayout.createSequentialGroup()
                        .add(jLabel64)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cmb_condPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 187, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jSeparator13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .add(jLabel60)
                    .add(pnl_condPagoLayout.createSequentialGroup()
                        .add(30, 30, 30)
                        .add(pnl_condPagoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(pnl_condPagoLayout.createSequentialGroup()
                                .add(check_uc_condPago)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jLabel62))
                            .add(pnl_condPagoLayout.createSequentialGroup()
                                .add(check_vendedor_condPago)
                                .add(139, 139, 139)
                                .add(jLabel61)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pnl_condPagoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(cmb_uc_condPago, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(cmb_vend_condPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnl_condPagoLayout.setVerticalGroup(
            pnl_condPagoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnl_condPagoLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel57)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnl_condPagoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel58)
                    .add(dteFechaInicioCondPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel59)
                    .add(dteFechaFinCondPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnl_condPagoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel64)
                    .add(cmb_condPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel60)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnl_condPagoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(check_vendedor_condPago)
                    .add(jLabel61)
                    .add(cmb_vend_condPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnl_condPagoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(check_uc_condPago)
                    .add(jLabel62)
                    .add(cmb_uc_condPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(169, Short.MAX_VALUE))
        );
        tabbedPane.addTab("Condiciones de Pago", pnl_condPago);
        
        
        
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
        tabbedPane.addTab("Presupuestos Confirmados", pnl_cobrados);
        
        jLabel63.setText("Seleccione un rango de fechas para ver informaci\u00f3n de presupuestos");

        jLabel65.setText("Desde");
        
        jLabel69.setText("Hasta");
        
        jLabel70.setText("Filtrar por:");

        check_vendedor_condPago1.setText("Vendedor");
        check_vendedor_condPago1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_vendedor_condPago1.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel71.setText("Seleccione el vendedor");       

        check_uc_condPago1.setText("Unidad comercial");
        check_uc_condPago1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        check_uc_condPago1.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel72.setText("Seleccione la unidad comercial");       
        
        jLabel73.setText("Seleccione una referencia via");       

        org.jdesktop.layout.GroupLayout pnl_referenciaLayout = new org.jdesktop.layout.GroupLayout(pnl_referencia);
        pnl_referencia.setLayout(pnl_referenciaLayout);
        pnl_referenciaLayout.setHorizontalGroup(
            pnl_referenciaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnl_referenciaLayout.createSequentialGroup()
                .addContainerGap()
                .add(pnl_referenciaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jSeparator18, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .add(pnl_referenciaLayout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jLabel65)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dteFechaInicioCondPago1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel69)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dteFechaFinCondPago1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jLabel63)
                    .add(pnl_referenciaLayout.createSequentialGroup()
                        .add(jLabel73)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cmb_condPago1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 187, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jSeparator20, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .add(jLabel70)
                    .add(pnl_referenciaLayout.createSequentialGroup()
                        .add(30, 30, 30)
                        .add(pnl_referenciaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(pnl_referenciaLayout.createSequentialGroup()
                                .add(check_uc_condPago1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jLabel72))
                            .add(pnl_referenciaLayout.createSequentialGroup()
                                .add(check_vendedor_condPago1)
                                .add(139, 139, 139)
                                .add(jLabel71)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pnl_referenciaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(cmb_uc_condPago1, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(cmb_vend_condPago1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnl_referenciaLayout.setVerticalGroup(
            pnl_referenciaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnl_referenciaLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel63)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnl_referenciaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel65)
                    .add(dteFechaInicioCondPago1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel69)
                    .add(dteFechaFinCondPago1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnl_referenciaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel73)
                    .add(cmb_condPago1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel70)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnl_referenciaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(check_vendedor_condPago1)
                    .add(jLabel71)
                    .add(cmb_vend_condPago1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnl_referenciaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(check_uc_condPago1)
                    .add(jLabel72)
                    .add(cmb_uc_condPago1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        tabbedPane.addTab("Referencia", pnl_referencia);        
        
        viewReport.setText("Ver reporte");
        viewReport.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        viewReport.setMnemonic('v');
        viewReport.setToolTipText("Click para ver el reporte");        
              
        exit.setText("Salir");
        exit.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        exit.setMnemonic('s');
        exit.setToolTipText("Click para salir");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(lbl_title))
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE))
                    .addContainerGap())
                .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(284, Short.MAX_VALUE)
                    .add(viewReport)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(exit)
                    .add(281, 281, 281))
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(tabbedPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(lbl_title)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(tabbedPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(viewReport)
                        .add(exit))
                    .addContainerGap())
            );
        
        this.getContentPane().add(panel);
        this.pack();
        
        createListeners();
        loadAndInitComponents();
        updatePosition();
    }
    
    public void updatePosition(){
    	
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	
    	this.setLocation((screenSize.width - this.getWidth())/2,
    			(screenSize.height - this.getHeight())/2);
    	
    }
    
    private void createListeners(){
    	exit.addActionListener(new ExitAction());
		viewReport.addActionListener(new ViewReportAction());
		rd_daily.addChangeListener(new ChangeDailyButton());
		rd_weekly.addChangeListener(new ChangeWeekButton());
		
		check_estado_ppto.addChangeListener(new ChangeCheckButton());
	    check_estados_porc.addChangeListener(new ChangeCheckButton());
	    check_lugar_ppto.addChangeListener(new ChangeCheckButton());
	    check_uc_diary.addChangeListener(new ChangeCheckButton());    
	    check_uc_ppto.addChangeListener(new ChangeCheckButton());
	    check_fechaCreacion_ppto.addChangeListener(new ChangeCheckButton());
	    check_uc_sub.addChangeListener(new ChangeCheckButton());
	    check_uc_week.addChangeListener(new ChangeCheckButton());
	    check_vend_ppto.addChangeListener(new ChangeCheckButton());
	    check_vendedor_diary.addChangeListener(new ChangeCheckButton());
	    check_vendedor_sub.addChangeListener(new ChangeCheckButton());
	    check_vendedor_week.addChangeListener(new ChangeCheckButton());
	    check_vendedor_le.addChangeListener(new ChangeCheckButton());
	    check_uc_le.addChangeListener(new ChangeCheckButton());
	    check_vendedor_condPago.addChangeListener(new ChangeCheckButton());	    
	    check_uc_condPago.addChangeListener(new ChangeCheckButton());
	    check_vendedor_lim.addChangeListener(new ChangeCheckButton());
	    check_uc_lim.addChangeListener(new ChangeCheckButton());
	}
	
	private void loadAndInitComponents(){		
		cmb_estados_ppto.loadItems();
		cmb_lugar_ppto.loadItems();
		cmb_vendedor_ppto.loadItems();
		cmb_uc_ppto.loadItems();
		
		cmb_estados_ppto.setEnabled(false);
		cmb_lugar_ppto.setEnabled(false);
		cmb_vendedor_ppto.setEnabled(false);
		cmb_uc_ppto.setEnabled(false);
		dteFechaCreacion.setEnabled(false);
		
		cmb_estados_porc.setEnabled(false);
		
		cmb_uc_condPago.setEnabled(false);
		cmb_vend_condPago.setEnabled(false);
		
		cmb_estados_le.setEnabled(false);
		cmb_uc_le.setEnabled(false);
		cmb_vend_le.setEnabled(false);
		
		cmb_uc_sub.setEnabled(false);
		cmb_vend_sub.setEnabled(false);
		
		cmb_uc_lim.setEnabled(false);
		cmb_vend_lim.setEnabled(false);
		
		cmb_vendedores_daily.setEnabled(false);
        cmb_UC_daily.setEnabled(false);
        //cmb_UC_weekly.setEnabled(false);
        //cmb_vendedores_weekly.setEnabled(false);
		
		tabbedPane.addChangeListener(new ChangeTabListener());		
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
    
	private long getCodVendedor_le(){
		if(cmb_vend_le.searchForeign()!= null){
			return Long.parseLong(cmb_vend_le.searchForeign());
		}
		else
			return 0;
	}
	
	private long getCodUC_le(){
		if(cmb_uc_le.searchForeign()!= null){
			return Long.parseLong(cmb_uc_le.searchForeign());
		}
		else
			return 0;
	}
	
	private long getCodVendedorLim(){
		if(cmb_vend_lim.searchForeign()!= null){
			return Long.parseLong(cmb_vend_lim.searchForeign());
		}
		else
			return 0;
	}
	
	private long getCodUCLim(){
		if(cmb_uc_lim.searchForeign()!= null){
			return Long.parseLong(cmb_uc_lim.searchForeign());
		}
		else
			return 0;
	}
	
	private long getCodVendedorSub(){
		if(cmb_vend_sub.searchForeign()!= null){
			return Long.parseLong(cmb_vend_sub.searchForeign());
		}
		else
			return 0;
	}
	
	private long getCodUCSub(){
		if(cmb_uc_sub.searchForeign()!= null){
			return Long.parseLong(cmb_uc_sub.searchForeign());
		}
		else
			return 0;
	}
	
	private long getCodEstadoLE(){
		if(cmb_estados_le.searchForeign()!= null){
			return Long.parseLong(cmb_estados_le.searchForeign());
		}
		else
			return 0;
	}
	
	private long getCodVendedorCondPago(){
		if(cmb_vend_condPago.searchForeign()!= null){
			return Long.parseLong(cmb_vend_condPago.searchForeign());
		}
		else
			return 0;
	}
	
	private long getCodUCCondPago(){
		if(cmb_uc_condPago.searchForeign()!= null){
			return Long.parseLong(cmb_uc_condPago.searchForeign());
		}
		else
			return 0;
	}
	
	private long getCodCondPago(){
		if(cmb_condPago.searchForeign()!= null){
			return Long.parseLong(cmb_condPago.searchForeign());
		}
		else
			return 0;
	}
	
	private long getCodVendedorReferencia(){
		if(cmb_vend_condPago1.searchForeign()!= null){
			return Long.parseLong(cmb_vend_condPago1.searchForeign());
		}
		else
			return 0;
	}
	
	private long getCodUCReferencia(){
		if(cmb_uc_condPago1.searchForeign()!= null){
			return Long.parseLong(cmb_uc_condPago1.searchForeign());
		}
		else
			return 0;
	}
	
	private long getCodReferencia(){
		if(cmb_condPago1.searchForeign()!= null){
			return Long.parseLong(cmb_condPago1.searchForeign());
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
			
			if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_ppto)){
				new Thread(new Runnable() {	
    				public void run() {	
				if(cmb_estados_ppto.isEmpty())
					cmb_estados_ppto.loadItems();
    				}
    			}).start();
				new Thread(new Runnable() {	
    				public void run() {	
				if(cmb_lugar_ppto.isEmpty())
					cmb_lugar_ppto.loadItems();
    				}
    			}).start();
				new Thread(new Runnable() {	
    				public void run() {	
				if(cmb_vendedor_ppto.isEmpty())
					cmb_vendedor_ppto.loadItems();
    				}
    			}).start();
				new Thread(new Runnable() {	
    				public void run() {	
				if(cmb_uc_ppto.isEmpty())
					cmb_uc_ppto.loadItems();
    				}
    			}).start();
    		}
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_WeeklyDaily)){    			
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_vendedores_daily.isEmpty())
    				cmb_vendedores_daily.loadItems();
    				}
    			}).start(); 
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_UC_daily.isEmpty())
    				cmb_UC_daily.loadItems();
    				}
    			}).start(); 
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_vendedores_weekly.isEmpty())
    				cmb_vendedores_weekly.loadItems();
    				}
    			}).start(); 
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_UC_weekly.isEmpty())
    				cmb_UC_weekly.loadItems();
    				}
    			}).start();     			
    		}
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_porcentajes)){
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_estados_porc.isEmpty())
    				cmb_estados_porc.loadItems();
    				}
    			}).start(); 
    		}
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_subcontratados)){
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_uc_sub.isEmpty())
    				cmb_uc_sub.loadItems();
    				}
    			}).start(); 
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_vend_sub.isEmpty())
    				cmb_vend_sub.loadItems();
    				}
    			}).start(); 
    		}
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_limites)){
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_uc_lim.isEmpty())
    				cmb_uc_lim.loadItems();	
    				}
    			}).start(); 
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_vend_lim.isEmpty())
    				cmb_vend_lim.loadItems();
    				}
    			}).start(); 
    		}
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_limite_Estado)){
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_uc_le.isEmpty())
    				cmb_uc_le.loadItems();
    				}
    			}).start(); 
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_vend_le.isEmpty())
    				cmb_vend_le.loadItems();
    				}
    			}).start(); 
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_estados_le.isEmpty())
    				cmb_estados_le.loadItems();
    				}
    			}).start(); 
    		}
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_condPago)){
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_condPago.isEmpty())
    				cmb_condPago.loadItems();
    				}
    			}).start(); 
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_vend_condPago.isEmpty())
    				cmb_vend_condPago.loadItems();
    				}
    			}).start(); 
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_uc_condPago.isEmpty())
    				cmb_uc_condPago.loadItems();
    				}
    			}).start(); 
    		}	
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_referencia)){
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_condPago1.isEmpty())
    				cmb_condPago1.loadItems();
    				}
    			}).start(); 
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_vend_condPago1.isEmpty())
    				cmb_vend_condPago1.loadItems();
    				}
    			}).start(); 
    			new Thread(new Runnable() {	
    				public void run() {	
    			if(cmb_uc_condPago1.isEmpty())
    				cmb_uc_condPago1.loadItems();
    				}
    			}).start(); 
    		}
		}
    	
    }
    
    private class RefreshAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_DATA);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {	
					if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_ppto)){
						cmb_estados_ppto.loadItems();
						cmb_lugar_ppto.loadItems();
						cmb_vendedor_ppto.loadItems();
						cmb_uc_ppto.loadItems();
					}
					/*else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_otros)){
						cmb_lugares_otros.loadItems();
					}*/
					else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_WeeklyDaily)){
						cmb_vendedores_weekly.loadItems();
						cmb_UC_weekly.loadItems();
						cmb_vendedores_daily.loadItems();
						cmb_UC_daily.loadItems();
					}
					else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_porcentajes)){
						cmb_estados_porc.loadItems();
					}
					else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_subcontratados)){
						cmb_uc_sub.loadItems();
						cmb_vend_sub.loadItems();
					}
					else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_limites)){
						cmb_uc_lim.loadItems();		
						cmb_vend_lim.loadItems();
					}
					else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_limite_Estado)){
						cmb_uc_le.loadItems();
						cmb_vend_le.loadItems();
						cmb_estados_le.loadItems();
					}
					else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_condPago)){
						cmb_condPago.loadItems();
						cmb_vend_condPago.loadItems();
						cmb_uc_condPago.loadItems();
					}
					ProgressDialogUtil.closeProcessDialog();
				}
			}).start(); 
		}
    	
    }

    private class ViewReportAction implements ActionListener{
    	
    	private int getTypeSubcontratado(){
    		if(check_vendedor_sub.isSelected() && cmb_vend_sub.searchForeign()!= null &&
    				check_uc_sub.isSelected() && cmb_uc_sub.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO;
    		if(!check_vendedor_sub.isSelected() &&
    				check_uc_sub.isSelected() && cmb_uc_sub.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO;
    		if(check_vendedor_sub.isSelected() && cmb_vend_sub.searchForeign()!= null &&
    				!check_uc_sub.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO;
    		if(check_vendedor_sub.isSelected() && cmb_vend_sub.searchForeign()== null){
    			Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar un vendedor", null);
    			return -1;
    		}
    		if(check_uc_sub.isSelected() && cmb_uc_sub.searchForeign()== null){
    			Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar una unidad comercial", null);
    			return -1;
    		}
    		return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_ESTADO;
    	}
    	
    	private int getTypeLimites(){
    		if(check_vendedor_lim.isSelected() && cmb_vend_lim.searchForeign()!= null &&
    				check_uc_lim.isSelected() && cmb_uc_lim.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO;
    		if(!check_vendedor_lim.isSelected() &&
    				check_uc_lim.isSelected() && cmb_uc_lim.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO;
    		if(check_vendedor_lim.isSelected() && cmb_vend_lim.searchForeign()!= null &&
    				!check_uc_lim.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO;
    		if(check_vendedor_lim.isSelected() && cmb_vend_lim.searchForeign()== null){
    			Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar un vendedor", null);
    			return -1;
    		}
    		if(check_uc_lim.isSelected() && cmb_uc_lim.searchForeign()== null){
    			Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar una unidad comercial", null);
    			return -1;
    		}
    		return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_ESTADO;
    	}
    	
    	private int getTypeLimitesYEstado(){
    		if(cmb_estados_le.searchForeign() == null){
    			Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar un estado", null);
    			return -1;
    		}   
    		if(check_vendedor_le.isSelected() && cmb_vend_le.searchForeign()!= null &&
    				check_uc_le.isSelected() && cmb_uc_le.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO;
    		if(!check_vendedor_le.isSelected() &&
    				check_uc_le.isSelected() && cmb_uc_le.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO;
    		if(check_vendedor_le.isSelected() && cmb_vend_le.searchForeign()!= null &&
    				!check_uc_le.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO;
    		if(check_vendedor_le.isSelected() && cmb_vend_le.searchForeign()== null){
    			Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar un vendedor", null);
    			return -1;
    		}
    		if(check_uc_le.isSelected() && cmb_uc_le.searchForeign()== null){
    			Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar una unidad comercial", null);
    			return -1;
    		}    		 			
    		return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_ESTADO;
    	}
    	
    	private int getTypePorcentaje(){
    		if(check_estados_porc.isSelected() && cmb_estados_porc.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_ESTADO;
    		if(check_estados_porc.isSelected() && cmb_estados_porc.searchForeign()== null){
    			Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar un estado", null);
    			return -1;
    		}
    		
    		return ReportBuilder.REPORT_PPTO_GERENCIA;
    	}
    	
    	private int getType(){
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO;   			
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_ESTADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				!check_estado_ppto.isSelected() && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() && 
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_ESTADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() && 
    				!check_lugar_ppto.isSelected() && 
    				!check_estado_ppto.isSelected() && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN;
    		
    		if(!check_vend_ppto.isSelected() &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO;
    		
    		if(!check_vend_ppto.isSelected() && 
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR;
    		
    		if(!check_vend_ppto.isSelected() &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_ESTADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				!check_estado_ppto.isSelected() && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_ESTADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() && 
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_ESTADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() && 
    				!check_estado_ppto.isSelected() &&!check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA;
    		
    		//////////////////////////////////////////////////////////////////////////////////
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null &&
    				check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO_CREADO;   			
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_CREADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_ESTADO_CREADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				!check_estado_ppto.isSelected()&& check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_CREADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() && 
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null&& check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO_CREADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_CREADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_ESTADO_CREADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() && 
    				!check_lugar_ppto.isSelected() && 
    				!check_estado_ppto.isSelected() && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_CREADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO_CREADO;
    		
    		if(!check_vend_ppto.isSelected() && 
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_CREADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_ESTADO_CREADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				!check_estado_ppto.isSelected() && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_CREADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_ESTADO_CREADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_CREADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() && 
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_ESTADO_CREADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() && 
    				!check_estado_ppto.isSelected() && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_CREADO;
    		
    		//////////////////////////////////////////////////////////////////////////////////
    		
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
    	
    	private int getTypeParaAndrea(){
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO;   			
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_ESTADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				!check_estado_ppto.isSelected() && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() && 
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_ESTADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() && 
    				!check_lugar_ppto.isSelected() && 
    				!check_estado_ppto.isSelected() && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN;
    		
    		if(!check_vend_ppto.isSelected() &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO;
    		
    		if(!check_vend_ppto.isSelected() && 
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR;
    		
    		if(!check_vend_ppto.isSelected() &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_ESTADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				!check_estado_ppto.isSelected() && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_ESTADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() && 
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && !check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_ESTADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() && 
    				!check_estado_ppto.isSelected() &&!check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA;
    		
    		//////////////////////////////////////////////////////////////////////////////////
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null &&
    				check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_ESTADO_CREADO;   			
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR_CREADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_ESTADO_CREADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				!check_estado_ppto.isSelected()&& check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_CREADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() && 
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null&& check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_ESTADO_CREADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR_CREADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_ESTADO_CREADO;
    		
    		if(check_vend_ppto.isSelected() && cmb_vendedor_ppto.searchForeign()!= null &&
    				!check_uc_ppto.isSelected() && 
    				!check_lugar_ppto.isSelected() && 
    				!check_estado_ppto.isSelected() && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_CREADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_ESTADO_CREADO;
    		
    		if(!check_vend_ppto.isSelected() && 
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR_CREADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_ESTADO_CREADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				check_uc_ppto.isSelected() && cmb_uc_ppto.searchForeign()!= null &&
    				!check_lugar_ppto.isSelected() &&
    				!check_estado_ppto.isSelected() && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_CREADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_ESTADO_CREADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				check_lugar_ppto.isSelected() && cmb_lugar_ppto.searchForeign()!= null &&
    				!check_estado_ppto.isSelected() && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR_CREADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() && 
    				check_estado_ppto.isSelected() && cmb_estados_ppto.searchForeign()!= null && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_ESTADO_CREADO;
    		
    		if(!check_vend_ppto.isSelected() &&
    				!check_uc_ppto.isSelected() &&
    				!check_lugar_ppto.isSelected() && 
    				!check_estado_ppto.isSelected() && check_fechaCreacion_ppto.isSelected())
    			return ReportBuilder.REPORT_PPTO_GERENCIA_CREADO;
    		
    		//////////////////////////////////////////////////////////////////////////////////
    		
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
    	
    	private int getTypeCondPago(){
    		if(check_vendedor_condPago.isSelected() && cmb_vend_condPago.searchForeign()!= null &&
    				check_uc_condPago.isSelected() && cmb_uc_condPago.searchForeign()!= null &&
    				cmb_condPago.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR;
    		if(check_vendedor_condPago.isSelected() && cmb_vend_condPago.searchForeign()!= null &&
    				!check_uc_condPago.isSelected() &&
    				cmb_condPago.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR;
    		if(!check_vendedor_condPago.isSelected() &&
    				check_uc_condPago.isSelected() && cmb_uc_condPago.searchForeign()!= null &&
    				cmb_condPago.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR;
    		if(cmb_condPago.searchForeign()== null){
    			Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar una condici�n de pago", null);
    			return -1;
    		}
    		
    		return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR;
    	}

    	private int getTypeRefencia(){
    		if(check_vendedor_condPago1.isSelected() && cmb_vend_condPago1.searchForeign()!= null &&
    				check_uc_condPago1.isSelected() && cmb_uc_condPago1.searchForeign()!= null &&
    				cmb_condPago1.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_UC_LUGAR;
    		if(check_vendedor_condPago1.isSelected() && cmb_vend_condPago1.searchForeign()!= null &&
    				!check_uc_condPago1.isSelected() &&
    				cmb_condPago1.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_VEN_LUGAR;
    		if(!check_vendedor_condPago1.isSelected() &&
    				check_uc_condPago1.isSelected() && cmb_uc_condPago1.searchForeign()!= null &&
    				cmb_condPago1.searchForeign()!= null)
    			return ReportBuilder.REPORT_PPTO_GERENCIA_UC_LUGAR;
    		if(cmb_condPago1.searchForeign()== null){
    			Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Debe seleccionar una referencia", null);
    			return -1;
    		}
    		
    		return ReportBuilder.REPORT_PPTO_GERENCIA_LUGAR;
    	}
    	
    	private void selectPptos(){
    		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {		
					int simple = cmb_simple_ppto.getSelectedIndex();
					//if(x.isSelected())
						//type = getTypeParaAndrea();
					int type = getType();					
					if(type >= 0)
						ReportBuilder.previewPresupuestosGerenciaReport(dteFechaInicioPpto,dteFechaHastaPpto, getCodVendedorPpto(), 
														getCodUCPpto(), getCodLugarPpto(), getCodEstadoPpto(), dteFechaCreacion, type, "fechaInicialEvento", simple);
					
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
						ReportBuilder.previewPorcentajeGerenciaReport(dteFechaInicioPorc,dteFechaFinPorc, 0, 
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
							ReportBuilder.previewSubcontratadoGerenciaReport(dteFechaInicioSub,dteFechaFinSub, getCodVendedorSub(), 
								getCodUCSub(), 0, 0, type);
						else if(rd_sub_propios.isSelected())
							ReportBuilder.previewSubcontratadoGerenciaReport(dteFechaInicioSub,dteFechaFinSub, getCodVendedorSub(), 
									getCodUCSub(), 1, 0, type);
						else if(rd_sub_subcontratado.isSelected())
							ReportBuilder.previewSubcontratadoGerenciaReport(dteFechaInicioSub,dteFechaFinSub, getCodVendedorSub(), 
									getCodUCSub(), 0, 1, type);
							
					
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
							ReportBuilder.previewLimitesGerenciaReport(dteFechaInicioLim,dteFechaFinLim, getCodVendedorLim(), 
								getCodUCLim(), 1, Double.parseDouble(txt_limite.getText()), type);
						else if(rd_montos_menores.isSelected())
							ReportBuilder.previewLimitesGerenciaReport(dteFechaInicioLim,dteFechaFinLim, getCodVendedorLim(), 
									getCodUCLim(), 0, Double.parseDouble(txt_limite.getText()), type);							
					
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
						int nroWeek = Integer.parseInt(txt_week_otros.getText());
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
						int dia = Integer.parseInt(dteFechaDiary.getDay());
						int mes = Integer.parseInt(dteFechaDiary.getMonth());
						int anio = Integer.parseInt(dteFechaDiary.getYear());
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
						int mes = Integer.parseInt(dteFechaMesAnio.getMonth());
						int anio = Integer.parseInt(dteFechaMesAnio.getYear());
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
						int codLugar = cmb_meses_otros.getSelectedIndex()+1;

							ReportBuilder.previewListaPreciosReport(DateConverter.convertDateToString(new Date(), "yyyy-MM-dd"));
							//dispose();

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
						int dia = Integer.parseInt(dteFechaDaily.getDay());
						int mes = Integer.parseInt(dteFechaDaily.getMonth());
						int anio = Integer.parseInt(dteFechaDaily.getYear());
						if(check_uc_diary.isSelected())
							ReportBuilder.previewDailyReportUC(dia, mes, anio, Long.parseLong(cmb_UC_weekly.searchForeign()));
						else if(check_vendedor_diary.isSelected())
							ReportBuilder.previewDailyReportVend(dia, mes, anio, Long.parseLong(cmb_vendedores_weekly.searchForeign()));
						else ReportBuilder.previewDailyReport(dia, mes, anio);
					}
					
					if(rd_weekly.isSelected() && !StringUtils.isBlank(txt_week.getText())){						
						if(check_uc_week.isSelected()) 
							ReportBuilder.previewWeekReportUC(Integer.parseInt(txt_week.getText()),Integer.parseInt(getCurrentYear()), Long.parseLong(cmb_UC_daily.searchForeign()));
						else if(check_vendedor_week.isSelected())
							ReportBuilder.previewWeekReportVend(Integer.parseInt(txt_week.getText()),Integer.parseInt(getCurrentYear()), Long.parseLong(cmb_vendedores_daily.searchForeign()) );						
						else ReportBuilder.previewWeekReport(Integer.parseInt(txt_week.getText()),Integer.parseInt(getCurrentYear()));
					}
					else if(rd_weekly.isSelected() && StringUtils.isBlank(txt_week.getText())){
						Util.errMsg(ProgressDialogUtil.getProcessDialog(), "Primero debe ingresar un week v�lido", null);
					}
					ProgressDialogUtil.closeProcessDialog();
				}
			}).start();
    	}
    	
    	private void selectLimitesYEstados(){
    		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {					
					int type = getTypeLimitesYEstado();					
					if(type >= 0)
						if(rd_montos_mayores_le.isSelected()){
							if(rd_estados_mayores_le.isSelected())
								ReportBuilder.previewLimitesYEstadosGerenciaReport(dteFechaInicio_le,dteFechaFin_le, getCodVendedor_le(), 
									getCodUC_le(), 1, Double.parseDouble(txt_limite_le.getText()),1,getCodEstadoLE(), type);
							else ReportBuilder.previewLimitesYEstadosGerenciaReport(dteFechaInicio_le,dteFechaFin_le, getCodVendedor_le(), 
									getCodUC_le(), 1, Double.parseDouble(txt_limite_le.getText()),0,getCodEstadoLE(), type);
						}
						else if(rd_montos_menores_le.isSelected()){
							if(rd_estados_mayores_le.isSelected())
								ReportBuilder.previewLimitesYEstadosGerenciaReport(dteFechaInicio_le,dteFechaFin_le, getCodVendedor_le(), 
									getCodUC_le(), 0, Double.parseDouble(txt_limite_le.getText()),1,getCodEstadoLE(), type);
							else ReportBuilder.previewLimitesYEstadosGerenciaReport(dteFechaInicio_le,dteFechaFin_le, getCodVendedor_le(), 
									getCodUC_le(), 0, Double.parseDouble(txt_limite_le.getText()),0,getCodEstadoLE(), type);
						}
					
					ProgressDialogUtil.closeProcessDialog();
				}
			}).start();
    	}
    	
    	private void selectCondPago(){
    		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {					
					int type = getTypeCondPago();					
					if(type >= 0)
						ReportBuilder.previewCondPagoGerenciaReport(dteFechaInicioCondPago,dteFechaFinCondPago, getCodVendedorCondPago(), 
														getCodUCCondPago(), getCodCondPago(), type);
					
					ProgressDialogUtil.closeProcessDialog();
				}
			}).start(); 
    	}
    	
    	private void selectReferencia(){
    		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {					
					int type = getTypeRefencia();					
					if(type >= 0)
						ReportBuilder.previewReferenciaGerenciaReport(dteFechaInicioCondPago1,dteFechaFinCondPago1, getCodVendedorReferencia(), 
								getCodUCReferencia(), getCodReferencia(), type);
					
					ProgressDialogUtil.closeProcessDialog();
				}
			}).start(); 
    	}
    	
    	private void selectCobrados(){
    		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {					
					//ReportBuilder.previewPresupuestosCobradosGerenciaReport(dteFechaInicioCobrados,dteFechaFinCobrados);
					ReportBuilder.previewPresupuestosConfirmadosGerenciaReport(dteFechaInicioCobrados,dteFechaFinCobrados);
					ProgressDialogUtil.closeProcessDialog();
				}
			}).start(); 
    	}
    	
    	public void actionPerformed(ActionEvent arg0) {
    		if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_ppto)){
    			selectPptos();
    		}
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_otros)){
    			selectOtros();
    		}
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_WeeklyDaily)){
    			selectWeekAndDaily();
    		}
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_porcentajes)){
    			selectPorcentaje();
    		}
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_subcontratados)){
    			selectSubcontratado();
    		}
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_limites)){
    			selectLimites();
    		}
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_limite_Estado)){
    			selectLimitesYEstados();
    		}
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_condPago)){
    			selectCondPago();    			
    		}
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_cobrados)){
    			selectCobrados();
    		}
    		else if(((JPanel)tabbedPane.getSelectedComponent()).equals(pnl_referencia)){
    			selectReferencia();
    		}
    	}
    	
    }
    
    private class ChangeDailyButton implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(rd_daily.isSelected()){				
				cmb_vendedores_weekly.setEnabled(true);
		        cmb_UC_weekly.setEnabled(true);
		        dteFechaDaily.setEnabled(true);
		        lbl_ingrese_daily.setEnabled(true);		        
		        lbl_filtrarVend_wd.setEnabled(true);
		        lbl_vendedor_wd1.setEnabled(true);
		        lbl_UC_wd1.setEnabled(true);
			}
			else{
				cmb_vendedores_weekly.setEnabled(false);
		        cmb_UC_weekly.setEnabled(false);
		        dteFechaDaily.setEnabled(false);		        
		        lbl_ingrese_daily.setEnabled(false);		        
		        lbl_filtrarVend_wd.setEnabled(false);
		        lbl_vendedor_wd1.setEnabled(false);
		        lbl_UC_wd1.setEnabled(false);
			}	
		}
		
	}
	
	private class ChangeWeekButton implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(rd_weekly.isSelected()){				
				cmb_vendedores_daily.setEnabled(true);
		        cmb_UC_daily.setEnabled(true);
		        txt_week.setEnabled(true);
		        lbl_ingrese_week.setEnabled(true);
		        lbl_vendedor_wd2.setEnabled(true);
		        lbl_UC_wd2.setEnabled(true);
		        lbl_filtrarUC_wd.setEnabled(true);
			}
			else{
				cmb_vendedores_daily.setEnabled(false);
		        cmb_UC_daily.setEnabled(false);
		        txt_week.setEnabled(false);
		        lbl_ingrese_week.setEnabled(false);
		        lbl_vendedor_wd2.setEnabled(false);
		        lbl_UC_wd2.setEnabled(false);
		        lbl_filtrarUC_wd.setEnabled(false);
			}	
		}
		
	}
	
	private class ChangeCheckButton implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_estado_ppto) && check_estado_ppto.isSelected()){
				cmb_estados_ppto.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_estado_ppto) && !check_estado_ppto.isSelected())){
				cmb_estados_ppto.setEnabled(false);
			}
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_lugar_ppto) && check_lugar_ppto.isSelected()){
				cmb_lugar_ppto.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_lugar_ppto) && !check_lugar_ppto.isSelected())){
				cmb_lugar_ppto.setEnabled(false);
			}
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_uc_ppto) && check_uc_ppto.isSelected()){
				cmb_uc_ppto.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_uc_ppto) && !check_uc_ppto.isSelected())){
				cmb_uc_ppto.setEnabled(false);
			}
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_vend_ppto) && check_vend_ppto.isSelected()){
				cmb_vendedor_ppto.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_vend_ppto) && !check_vend_ppto.isSelected())){
				cmb_vendedor_ppto.setEnabled(false);
			}
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_fechaCreacion_ppto) && check_fechaCreacion_ppto.isSelected()){
				dteFechaCreacion.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_fechaCreacion_ppto) && !check_fechaCreacion_ppto.isSelected())){
				dteFechaCreacion.setEnabled(false);
			}
			
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_estados_porc) && check_estados_porc.isSelected()){
				cmb_estados_porc.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_estados_porc) && !check_estados_porc.isSelected())){
				cmb_estados_porc.setEnabled(false);
			}		
			
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_uc_sub) && check_uc_sub.isSelected()){
				cmb_uc_sub.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_uc_sub) && !check_uc_sub.isSelected())){
				cmb_uc_sub.setEnabled(false);
			}
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_vendedor_sub) && check_vendedor_sub.isSelected()){
				cmb_vend_sub.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_vendedor_sub) && !check_vendedor_sub.isSelected())){
				cmb_vend_sub.setEnabled(false);
			}
			
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_uc_lim) && check_uc_lim.isSelected()){
				cmb_uc_lim.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_uc_lim) && !check_uc_lim.isSelected())){
				cmb_uc_lim.setEnabled(false);
			}
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_vendedor_lim) && check_vendedor_lim.isSelected()){
				cmb_vend_lim.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_vendedor_lim) && !check_vendedor_lim.isSelected())){
				cmb_vend_lim.setEnabled(false);
			}
			
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_uc_condPago) && check_uc_condPago.isSelected()){
				cmb_uc_condPago.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_uc_condPago) && !check_uc_condPago.isSelected())){
				cmb_uc_condPago.setEnabled(false);
			}
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_vendedor_condPago) && check_vendedor_condPago.isSelected()){
				cmb_vend_condPago.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_vendedor_condPago) && !check_vendedor_condPago.isSelected())){
				cmb_vend_condPago.setEnabled(false);
			}
			
			/*if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_uc_diary) && check_uc_diary.isSelected()){
				cmb_UC_daily.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_uc_diary) && !check_uc_diary.isSelected())){
				cmb_UC_daily.setEnabled(false);
			}
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_vendedor_diary) && check_vendedor_diary.isSelected()){
				cmb_vendedores_daily.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_vendedor_diary) && !check_vendedor_diary.isSelected())){
				cmb_vendedores_daily.setEnabled(false);
			}
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_uc_week) && check_uc_week.isSelected()){
				cmb_UC_weekly.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_uc_week) && !check_uc_week.isSelected())){
				cmb_UC_weekly.setEnabled(false);
			}
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_vendedor_week) && check_vendedor_week.isSelected()){
				cmb_vendedores_weekly.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_vendedor_week) && !check_vendedor_week.isSelected())){
				cmb_vendedores_weekly.setEnabled(false);
			}*/
			
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_uc_le) && check_uc_le.isSelected()){
				cmb_uc_le.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_uc_le) && !check_uc_le.isSelected())){
				cmb_uc_le.setEnabled(false);
			}
			if(((javax.swing.JCheckBox)arg0.getSource()).equals(check_vendedor_le) && check_vendedor_le.isSelected()){
				cmb_vend_le.setEnabled(true);
			}
			else if((((javax.swing.JCheckBox)arg0.getSource()).equals(check_vendedor_le) && !check_vendedor_le.isSelected())){
				cmb_vend_le.setEnabled(false);
			}
			
			
		}
		
	}
	
}
