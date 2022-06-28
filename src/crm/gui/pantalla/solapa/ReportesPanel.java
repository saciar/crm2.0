package crm.gui.pantalla.solapa;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import org.apache.commons.lang.StringUtils;

import crm.client.excel.PresupuestoExcel;
import crm.client.managers.PrtPptoCancelacionManager;
import crm.client.managers.PrtPptoCondPagoManager;
import crm.client.managers.PrtPptoFPagoManager;
import crm.client.managers.PrtPptoHeaderManager;
import crm.client.managers.PrtPptoPeriodoManager;
import crm.client.managers.PrtPptoTipoPresupuestoManager;
import crm.client.managers.PrtPptoValidezManager;
import crm.client.managers.ServicioDescripcionManager;
import crm.client.managers.ServicioIdiomaManager;
import crm.client.managers.VendedorUsuarioManager;
import crm.client.report.PresupuestoReport;
import crm.client.report.ReportBuilder;
import crm.client.util.DateConverter;
import crm.client.util.DateDiff;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.client.validacion.ErrorList;
import crm.gui.Main;
import crm.gui.components.ABMComboBox;
import crm.gui.components.CancelacionComboBox;
import crm.gui.components.CondicionPagoComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.components.FormaPagoComboBox;
import crm.gui.components.GradientButton;
import crm.gui.components.HeaderComboBox;
import crm.gui.components.MonedaExtranjeraComboBox;
import crm.gui.components.PeriodoComboBox;
import crm.gui.components.TipoPresupuestoComboBox;
import crm.gui.components.ValidezComboBox;
import crm.gui.docx4j.FormateadorWord2;
import crm.gui.pantalla.PantallaEmergenteCotizacion;
import crm.libraries.abm.entities.Ppto_Sala;
import crm.libraries.abm.entities.Ppto_Sala_Servicio;
import crm.libraries.abm.entities.Ppto_Sala_Servicio_Desc_Detallada;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.PrtPptoCancelacion;
import crm.libraries.abm.entities.PrtPptoCondPago;
import crm.libraries.abm.entities.PrtPptoFPago;
import crm.libraries.abm.entities.PrtPptoHeader;
import crm.libraries.abm.entities.PrtPptoPeriodo;
import crm.libraries.abm.entities.PrtPptoTipoPresupuesto;
import crm.libraries.abm.entities.PrtPptoValidez;
import crm.libraries.abm.entities.ServicioDescripcion;
import crm.libraries.util.MessageUtil;

public class ReportesPanel extends PanelGeneral implements PanelInterface{
	private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox copiaCliente;
    private javax.swing.JButton enviarPresupuesto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JRadioButton ofCheck;
    private javax.swing.JRadioButton osCheck;
    private javax.swing.JRadioButton presupuestoCheck;
    private javax.swing.JRadioButton ofAdelantoCheck;
    private javax.swing.JLabel simbolo;
    private javax.swing.JFormattedTextField valorCotizado;
    private javax.swing.JButton verCotizacion;
    private javax.swing.JButton verReporte;
    private javax.swing.JButton verReporteWord;
    private javax.swing.JTextArea vistaPrevia;
    private HeaderComboBox headerCombo;
	private TipoPresupuestoComboBox tipoPptoCombo;
	private PeriodoComboBox periodoCombo;
	private FormaPagoComboBox formaPagoCombo;
	private ValidezComboBox validezCombo;
	private MonedaExtranjeraComboBox monedaCombo;
	private CancelacionComboBox cancelacionCombo;
	private CondicionPagoComboBox condicionPagoCombo;
    
	private javax.swing.JCheckBox verDescripciones;
	
    private JPanel panel;
    private Presupuesto presupuesto;
    private MainPanelComercial main;    
    
	public static final String MONEDA_DOLAR = "u$s";
	public static final String MONEDA_EURO = "€";
	public static final String MONEDA_PESO = "$";
	public static final String COD_MONEDA_PESOS = "1";
    
    public ReportesPanel(JPanel pan){
    	panel = pan;
    }
    
    public ReportesPanel(){

    }
    
    public void setMainPanel(MainPanelComercial m){
    	main = m;
    }
    
    public void init(){
    	    	
    	buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        presupuestoCheck = new javax.swing.JRadioButton();
        osCheck = new javax.swing.JRadioButton();
        ofCheck = new javax.swing.JRadioButton();
        verReporte = new GradientButton("",Color.BLUE);
        verReporteWord = new GradientButton("",Color.BLUE);
        enviarPresupuesto = new GradientButton("",Color.BLUE);
        copiaCliente = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        headerCombo = new HeaderComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        condicionPagoCombo = new CondicionPagoComboBox();
        jLabel6 = new javax.swing.JLabel();
        formaPagoCombo = new FormaPagoComboBox();
        jLabel7 = new javax.swing.JLabel();
        validezCombo = new ValidezComboBox();
        jLabel8 = new javax.swing.JLabel();
        tipoPptoCombo = new TipoPresupuestoComboBox();
        jLabel9 = new javax.swing.JLabel();
        periodoCombo = new PeriodoComboBox();
        jLabel10 = new javax.swing.JLabel();
        cancelacionCombo = new CancelacionComboBox();
        jLabel11 = new javax.swing.JLabel();
        monedaCombo = new MonedaExtranjeraComboBox();
        verCotizacion = new GradientButton("",Color.BLUE);
        jScrollPane1 = new javax.swing.JScrollPane();
        vistaPrevia = new javax.swing.JTextArea();
        simbolo = new javax.swing.JLabel();
        valorCotizado = CustomTextField.getDecimalInstance(10,2);
        
        jSeparator3 = new javax.swing.JSeparator();
        verDescripciones = new javax.swing.JCheckBox();
        ofAdelantoCheck = new javax.swing.JRadioButton();
        
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Seleccione un tipo de reporte");

        buttonGroup1.add(presupuestoCheck);
        presupuestoCheck.setFont(new java.awt.Font("Tahoma", 1, 11));
        presupuestoCheck.setText("Presupuesto");
        presupuestoCheck.setToolTipText("");
        presupuestoCheck.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        presupuestoCheck.setMargin(new java.awt.Insets(0, 0, 0, 0));
        presupuestoCheck.setSelected(true);
        presupuestoCheck.setBackground(new Color(210,209,205));
        //------------Ver en metodo setPresupuesto y verActionListener y descomentar en caso de querer volver a sqcar reporte presupuesto desde aca----------------------------
        presupuestoCheck.setEnabled(true);

        buttonGroup1.add(osCheck);
        osCheck.setFont(new java.awt.Font("Tahoma", 1, 11));
        osCheck.setText("Orden de servicio");
        osCheck.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        osCheck.setMargin(new java.awt.Insets(0, 0, 0, 0));
        osCheck.setBackground(new Color(213,212,207));

        buttonGroup1.add(ofCheck);
        ofCheck.setFont(new java.awt.Font("Tahoma", 1, 11));
        ofCheck.setText("Orden de facturaci\u00f3n");
        ofCheck.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ofCheck.setMargin(new java.awt.Insets(0, 0, 0, 0));
        ofCheck.setBackground(new Color(206,204,205));
        
        buttonGroup1.add(ofAdelantoCheck);
        ofAdelantoCheck.setFont(new java.awt.Font("Tahoma", 1, 11));
        ofAdelantoCheck.setText("Orden de facturaci\u00f3n de adelanto");
        ofAdelantoCheck.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ofAdelantoCheck.setMargin(new java.awt.Insets(0, 0, 0, 0));

        verReporte.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        System.out.println(getUrlImagen("magnifier.png").getPath());
        verReporte.setText("Ver reporte");
        
        verReporteWord.setIcon(new javax.swing.ImageIcon(getUrlImagen("page_word.png")));
        verReporteWord.setText("Exportar a .DOC");

        enviarPresupuesto.setIcon(new javax.swing.ImageIcon(getUrlImagen("email_go.png")));
        enviarPresupuesto.setText("Enviarme presupuesto");

        copiaCliente.setText("Enviarme con copia al contacto del cliente");
        copiaCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        copiaCliente.setMargin(new java.awt.Insets(0, 0, 0, 0));
        
        verCotizacion.setIcon(new javax.swing.ImageIcon(getUrlImagen("money_dollar.png")));
        verCotizacion.setText("Ver cotizaci\u00f3n online");
        
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Seleccione los componentes del presupuesto");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Encabezado del presupuesto");        

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Vista previa del componente seleccionado");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Condici\u00f3n de pago");       

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Forma de pago");        

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Validez");       

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Tipo de presupuesto");        

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Per\u00edodo del presupuesto");    

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Cancelaci\u00f3n");        

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Moneda del presupuesto");
        
        vistaPrevia.setLineWrap(true);
        vistaPrevia.setMargin(new Insets(0,10,0,10));
		vistaPrevia.setEditable(false);
        vistaPrevia.setText("Elija un componente");
        jScrollPane1.setViewportView(vistaPrevia);

        valorCotizado.setText("1");
        valorCotizado.setVisible(false);
        simbolo = new JLabel("");
        
        verDescripciones.setText("Ver las descripciones detalladas de los servicios presupuestados");
        verDescripciones.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        verDescripciones.setMargin(new java.awt.Insets(0, 0, 0, 0));
        verDescripciones.setSelected(true);
        
        initializeCombos();
        
        createListeners();
    }
    
    public void initLayout(){
    	org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                        .add(jLabel1)
                        .add(layout.createSequentialGroup()
                            .add(presupuestoCheck)
                            .add(103, 103, 103)
                            .add(osCheck)
                            .add(94, 94, 94)
                            .add(ofCheck)
                            .add(76, 76, 76)
                            .add(ofAdelantoCheck)
                            .add(0, 0, Short.MAX_VALUE))
                        //.add(verReporte)
                        .add(layout.createSequentialGroup()
                            .add(verReporte)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(verReporteWord))
                        .add(layout.createSequentialGroup()
                            .add(enviarPresupuesto)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(copiaCliente))
                        .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel2)
                                .add(layout.createSequentialGroup()
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jLabel3)
                                        .add(jLabel5)
                                        .add(jLabel6)
                                        .add(jLabel7)
                                        .add(jLabel8)
                                        .add(jLabel9)
                                        .add(jLabel10)
                                        .add(jLabel11)
                                        .add(verCotizacion))
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                            .add(6, 6, 6)
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                                .add(monedaCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(tipoPptoCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(validezCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(formaPagoCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(condicionPagoCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(headerCombo, 0, 269, Short.MAX_VALUE)
                                                .add(periodoCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(cancelacionCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .add(layout.createSequentialGroup()
                                            .add(28, 28, 28)
                                            .add(simbolo)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(valorCotizado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                            .add(28, 28, 28)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(jLabel4)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 116, Short.MAX_VALUE))
                                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)))
                        .add(verDescripciones))
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
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(presupuestoCheck)
                        .add(osCheck)
                        .add(ofCheck)
                        .add(ofAdelantoCheck))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    //.add(verReporte)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(verReporte)
                        .add(verReporteWord))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(enviarPresupuesto)
                        .add(copiaCliente))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel2)
                        .add(jLabel4))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel3)
                                .add(headerCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel5)
                                .add(condicionPagoCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel6)
                                .add(formaPagoCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(validezCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(jLabel7))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel8)
                                .add(tipoPptoCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel9)
                                .add(periodoCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel10)
                                .add(cancelacionCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel11)
                                .add(monedaCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(verCotizacion)
                                .add(simbolo)
                                .add(valorCotizado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(jScrollPane1))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(verDescripciones)
                    .addContainerGap())
            );
    }
    
private void initializeCombos(){		
		
		setPresupuestoMode(true);
		
		headerCombo.loadItems();
		formaPagoCombo.loadItems();
		condicionPagoCombo.loadItems();
		cancelacionCombo.loadItems();
		validezCombo.loadItems();
		tipoPptoCombo.loadItems();
		periodoCombo.loadItems();
		monedaCombo.loadItems();
		monedaCombo.setSelectedIndex(2);
	}
	
	private void setPresupuestoMode(boolean b){
		headerCombo.setEnabled(b);
		formaPagoCombo.setEnabled(b);
		condicionPagoCombo.setEnabled(b);
		cancelacionCombo.setEnabled(b);		
		validezCombo.setEnabled(b);
		tipoPptoCombo.setEnabled(b);
		periodoCombo.setEnabled(b);
		monedaCombo.setEnabled(b);
		valorCotizado.setEnabled(b);
		verDescripciones.setEnabled(b);
	}
	
	private void createListeners(){
		
		presupuestoCheck.addActionListener(new PresupActionListener());
        osCheck.addActionListener(new OsActionListener());      
        ofCheck.addActionListener(new OfActionListener());
        ofAdelantoCheck.addActionListener(new OfAdelantoActionListener());
        verReporte.addActionListener(new VerActionListener());
        //verReporteWord.addActionListener(new VerWordActionListener());
        verReporteWord.addActionListener(new GuardarExcelActionListener());
		enviarPresupuesto.addActionListener(new EnviarPresupuestoListener());
		headerCombo.addActionListener(new HeaderActionListener());
		formaPagoCombo.addActionListener(new FormaPagoActionListener());
		validezCombo.addActionListener(new ValidezActionListener());
		tipoPptoCombo.addActionListener(new TipoPptoActionListener());
		periodoCombo.addActionListener(new PeriodoActionListener());
		verCotizacion.addActionListener(new VerCotizacionActionListener());
		monedaCombo.addActionListener(new MonedaComboActionListener());
		condicionPagoCombo.addActionListener(new CondicionPagoActionListener());
		cancelacionCombo.addActionListener(new CancelacionActionListener());
	}
	
	public ErrorList validateRequiredFields() {

        return null;
    }
	
	public void setPresupuesto(final Presupuesto p) {
    	SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				Main.getVentana().getGlassPane().start();
				Thread performer = new Thread(new Runnable(){
					public void run(){
						presupuestoCheck.setEnabled(true);
						osCheck.setEnabled(false);
						ofCheck.setEnabled(false);
						ofAdelantoCheck.setEnabled(false);
						if(p.getNumeroDePresupuesto() == 0){
							
							setPresupuestoMode(false);
						}
						else{
							presupuestoCheck.setEnabled(true);
							setPresupuestoMode(true);
							if(p.getEstadoActual() != null && p.getEstadoActual().getOf()==1)
								ofCheck.setEnabled(true);
							if(p.getEstadoActual() != null && p.getEstadoActual().getOs()==1)
								osCheck.setEnabled(true);
							if(p.getEstadoActual() != null && p.getEstadoActual().getAdelanto()==1)
								ofAdelantoCheck.setEnabled(true);
							presupuesto = p;
							if(p.getEncabezadoPpto() != null && p.getEncabezadoPpto().getCodigo().equals("0"))
								headerCombo.setForeign(p.getEncabezadoPpto().getCodigo());
							else
								headerCombo.setForeign("1");
							if(p.getFormaPagoPpto() != null && p.getFormaPagoPpto().getCodigo().equals("0"))
								formaPagoCombo.setForeign(p.getFormaPagoPpto().getCodigo());
							else
								formaPagoCombo.setForeign("1");
							if(p.getValidezPpto() != null && p.getValidezPpto().getCodigo().equals("0"))
								validezCombo.setForeign(p.getValidezPpto().getCodigo());
							else
								validezCombo.setForeign("4");
							if(p.getTipoPpto() != null && p.getTipoPpto().getCodigo().equals("0"))
								tipoPptoCombo.setForeign(p.getTipoPpto().getCodigo());
							else
								tipoPptoCombo.setForeign("2");
							if(p.getPeriodoPpto() != null && p.getPeriodoPpto().getCodigo().equals("0"))
								periodoCombo.setForeign(p.getPeriodoPpto().getCodigo());
							else
								periodoCombo.setForeign("1");
							if(p.getMoneda() != null)
								monedaCombo.setForeign(p.getMoneda().getCodigo());
							if(p.getCotizacion() != null)
								valorCotizado.setText(String.valueOf(p.getCotizacion().doubleValue()));
							if(p.getCondPagoPpto() != null && p.getCondPagoPpto().getCodigo().equals("0"))
								condicionPagoCombo.setForeign(p.getCondPagoPpto().getCodigo());
							else
								condicionPagoCombo.setForeign("1");
							if(p.getCancelacionPpto() != null && p.getCancelacionPpto().getCodigo().equals("0"))
								cancelacionCombo.setForeign(p.getCancelacionPpto().getCodigo());
							else
								cancelacionCombo.setForeign("16");
								
						}
						Main.getVentana().getGlassPane().stop();
					}
				},"Cargar reportes");
				performer.start();
			}
		});
	}
	
	private boolean allValid(ABMComboBox[] combos, JFormattedTextField valor){
		boolean resp = true;
		for (int i = 0; i < combos.length; i++) {
			if(combos[i].getSelectedIndex() == 0)
				resp = false;
		}
		if(valor.getText().equals("") || valor == null)
			resp = false;
		return resp;
	}
	
	private void changeEstadoEnvio(boolean b){
		enviarPresupuesto.setEnabled(b);
		copiaCliente.setEnabled(b);
	}
	
	public String getCodigoEncabezado(){
		if (headerCombo!= null)
			return headerCombo.searchForeign();
		return null;
	}
	
	public String getCodigoValidez(){
		if (validezCombo!= null)
			return validezCombo.searchForeign();
		return null;
	}
	
	public String getCodigoTipoPpto(){
		if (tipoPptoCombo!= null)
			return tipoPptoCombo.searchForeign();
		return null;
	}
	
	public String getCodigoPeriodo(){
		if (periodoCombo!= null)
			return periodoCombo.searchForeign();
		return null;
	}
	
	public String getCodigoFPago(){
		if (formaPagoCombo!= null)
			return formaPagoCombo.searchForeign();
		return null;
	}
	
	public String getCodigoCondPago(){
		if (condicionPagoCombo!= null)
			return condicionPagoCombo.searchForeign();
		return null;
	}
	
	public String getCodigoCancelacion(){
		if (cancelacionCombo!= null)
			return cancelacionCombo.searchForeign();
		return null;
	}
	
	public String getCodigoMoneda(){
		if (monedaCombo!=null){
			return monedaCombo.searchForeign();
		}
		return null;
	}
	
	/*public void entraSolapa(){
		this.setPresupuesto(this.pantallaNuevo.getFooter().getPresupuesto());
	}*/
	
	public String getCotizacion(){
		if(valorCotizado != null){
			return valorCotizado.getText();
		}
		return null;
	}
	
	public void setFormaDePagoValue(String value){
		if(!StringUtils.isBlank(value)){
			formaPagoCombo.setForeign(value);
		}
	}
	
	public void setCondicionDePagoValue(String value){
		if(!StringUtils.isBlank(value)){
			condicionPagoCombo.setForeign(value);
		}
	}
	
	//-----ESCUCHADORES ACCIONES----------------------------------------------------------
	private class HeaderActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(headerCombo.searchForeign() != null){
				try{
					PrtPptoHeader header = PrtPptoHeaderManager.instance().getById(headerCombo.searchForeign());
					vistaPrevia.setText(header.getDescripcion());
				}
				catch(RemoteException e){
					Util.errMsg(Main.getVentana(), "Error al cargar datos externos",e);
				}
			}
			else vistaPrevia.setText("Elija un componente");
		}
		
	}
	
	private class FormaPagoActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(formaPagoCombo.searchForeign() != null){
				try{
					PrtPptoFPago fPago = PrtPptoFPagoManager.instance().getById(formaPagoCombo.searchForeign());
					vistaPrevia.setText(fPago.getDescripcion());
					if(main.getPanelFacturacion() != null)
						main.getPanelFacturacion().setFormaDePagoValue(formaPagoCombo.searchForeign());
				}
				catch(RemoteException e){
					Util.errMsg(Main.getVentana(), "Error al cargar datos externos",e);
				}				
			}
			else vistaPrevia.setText("Elija un componente");
		}
		
	}
	
	private class ValidezActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(validezCombo.searchForeign() != null){
				try{
					PrtPptoValidez validez = PrtPptoValidezManager.instance().getById(validezCombo.searchForeign());
					vistaPrevia.setText(validez.getDescripcion());
				}
				catch(RemoteException e){
					Util.errMsg(Main.getVentana(), "Error al cargar datos externos",e);
				}
			}
			else vistaPrevia.setText("Elija un componente");
		}
		
	}
	
	
	private class TipoPptoActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(tipoPptoCombo.searchForeign() != null){
				try{
					PrtPptoTipoPresupuesto tipo = PrtPptoTipoPresupuestoManager.instance().getById(tipoPptoCombo.searchForeign());
					vistaPrevia.setText(tipo.getDescripcion());
				}
				catch(RemoteException e){
					Util.errMsg(Main.getVentana(), "Error al cargar datos externos",e);
				}
			}
			else vistaPrevia.setText("Elija un componente");
		}
		
	}
	
	private class PeriodoActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(periodoCombo.searchForeign() != null){
				try{
					//PrtPptoPeriodo tipo = PrtPptoPeriodoManager.instance().getById(periodoCombo.searchForeign());
					if(periodoCombo.searchForeign().equals("1")){						
						vistaPrevia.setText("del "+presupuesto.getFechaDeInicio()+" al "+ presupuesto.getFechaDeFinalizacion());
						//periodoElegido = "del "+presupuesto.getFechaDeInicio()+" al "+ presupuesto.getFechaDeFinalizacion();
					}
					else{
						PrtPptoPeriodo tipo = PrtPptoPeriodoManager.instance().getById(periodoCombo.searchForeign());
						vistaPrevia.setText(tipo.getDescripcion());						
						//periodoElegido = tipo.getDescripcion();
					}
						
				}
				catch(RemoteException e){
					Util.errMsg(Main.getVentana(), "Error al cargar datos externos",e);
				}
			}
			else vistaPrevia.setText("Elija un componente");
		}
	}
	
	private class CondicionPagoActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(condicionPagoCombo.searchForeign() != null){
				try{
					PrtPptoCondPago condPago = PrtPptoCondPagoManager.instance().getById(condicionPagoCombo.searchForeign());
					vistaPrevia.setText(condPago.getDescripcion());
					if(main.getPanelFacturacion() != null)
						main.getPanelFacturacion().setCondicionDePagoValue(condicionPagoCombo.searchForeign());
				}
				catch(RemoteException e){
					Util.errMsg(Main.getVentana(), "Error al cargar datos externos",e);
				}				
			}
			else vistaPrevia.setText("Elija un componente");
		}
		
	}
	
	private class CancelacionActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(cancelacionCombo.searchForeign() != null){
				try{
					PrtPptoCancelacion cancelacion = PrtPptoCancelacionManager.instance().getById(cancelacionCombo.searchForeign());
					vistaPrevia.setText(cancelacion.getDescripcion());
				}
				catch(RemoteException e){
					Util.errMsg(Main.getVentana(), "Error al cargar datos externos",e);
				}				
			}
			else vistaPrevia.setText("Elija un componente");
		}
	}
	
	private class GuardarExcelActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			PresupuestoExcel pptoXls = new PresupuestoExcel(main.getNumeroPpto());			
		}
		
	}
	
	private class VerWordActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			ServicioIdiomaManager servIdiomaManager = ServicioIdiomaManager.instance();
			Double total = 0.0;
			boolean opcionesValidas = allValid(new ABMComboBox[]{headerCombo,formaPagoCombo,validezCombo, tipoPptoCombo, periodoCombo, monedaCombo, cancelacionCombo, condicionPagoCombo}, valorCotizado);

			if(opcionesValidas){
			FormateadorWord2 f = new FormateadorWord2();
			f.setColor("990000");
			f.setNroPpto(String.valueOf(presupuesto.getNumeroDePresupuesto()));
			//f.addNropPpto();
			//f.addFecha();
			f.agregarParrafoColor("Presupuesto "+String.valueOf(presupuesto.getNumeroDePresupuesto()), "44");
			DateFormat dformat = DateFormat.getDateInstance(DateFormat.FULL);
			f.agregarParrafoFecha("Buenos Aires, "+dformat.format(new Date()),"24");
			try {
				PrtPptoHeader header = PrtPptoHeaderManager.instance().getById(headerCombo.searchForeign());
				PrtPptoTipoPresupuesto tipoPpto = PrtPptoTipoPresupuestoManager.instance().getById(tipoPptoCombo.searchForeign());
				PrtPptoPeriodo p = PrtPptoPeriodoManager.instance().getById(periodoCombo.searchForeign());
				PrtPptoValidez val = PrtPptoValidezManager.instance().getById(validezCombo.searchForeign());
				PrtPptoCancelacion canc = PrtPptoCancelacionManager.instance().getById(cancelacionCombo.searchForeign());
				PrtPptoCondPago cond = PrtPptoCondPagoManager.instance().getById(condicionPagoCombo.searchForeign());
				String periodo="";
				if(periodoCombo.searchForeign().equals("1")){
					DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL);
					Date di =  DateConverter.convertStringToDate(presupuesto.getFechaDeInicio(), "yyyy-mm-dd");
					Date df =  DateConverter.convertStringToDate(presupuesto.getFechaDeFinalizacion(), "yyyy-mm-dd");
					if(presupuesto.getFechaDeInicio().equals(presupuesto.getFechaDeFinalizacion()))
						periodo = "el "+df4.format(di);
					else
						periodo = "del "+df4.format(di)+" al "+df4.format(df);
				}
				else{
					periodo = p.getDescripcion(); 
				}
				f.agregarParrafoMultilinea(new String[]{"De mi consideración: ",header.getDescripcion()+", envio "+tipoPpto.getDescripcion()+" para el evento a realizarse "+periodo+" en "+presupuesto.getLugarDelEvento().getNombreLugar()});
				
				for(Ppto_Sala sala: presupuesto.getSalas()){
					f.agregarParrafoColor(sala.getSala().getDescripcion(),"34");
					DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL);
					Date di =  DateConverter.convertStringToDate(sala.getFechaDeInicio(), "yyyy-mm-dd");
					Date df =  DateConverter.convertStringToDate(sala.getFechaDeFinalizacion(), "yyyy-mm-dd");
					if(di.equals(df))
						f.agregarParrafoNegrita("El "+df4.format(df));
					else f.agregarParrafoNegrita("Del "+df4.format(di)+" al "+df4.format(df));
					Object[] servicios=sala.getServiciosArray();
					
					for (int i = 0; i < servicios.length; i++) {
						Ppto_Sala_Servicio serv = (Ppto_Sala_Servicio) servicios[i];
						String nombreServ="";
						if(serv.getDetalle()!=null){
							nombreServ=serv.getDetalle();
						} else{
							try {
								nombreServ=servIdiomaManager.getDescripcionServicio(serv.getServicio().getCodigo(),"1");
							} catch (RemoteException e) {
								e.printStackTrace();
							}
						}
						f.agregarParrafoNegrita(nombreServ);
						total=total+Double.parseDouble(serv.getPrecioDescuento());
						
						if(verDescripciones.isSelected()){
							Set<Ppto_Sala_Servicio_Desc_Detallada> serviciosDetallado = serv.getDescripcionDetallada();
							if(serviciosDetallado !=null && serviciosDetallado.size()>0){
								String[] detalles = new String[serviciosDetallado.size()];
								int cont= 0;
								for(Ppto_Sala_Servicio_Desc_Detallada desc: serviciosDetallado){
									detalles[cont]=desc.getDescripcion();
									
									if(cont== serviciosDetallado.size()-1){
										if(Integer.parseInt(serv.getDias()) > 1)
											detalles[cont]="Precio por "+serv.getDias()+" días: "+serv.getPrecioDescuento();
										else
											detalles[cont]="Precio por un día: "+serv.getPrecioDescuento();
									}
									cont=cont+1;
								}
								
								f.agregarParrafoMultilinea(detalles);
							}
							else{
								ServicioDescripcion[] descripciones= ServicioDescripcionManager.instance().findByServicio(serv.getServicio().getCodigo(), "1");
								String[] detalles = new String[descripciones.length];
								int cont = 0;
								for(int h=0; h<descripciones.length; h++){
									detalles[h] = "• "+descripciones[h].getDescripcion();
									
									if(cont ==descripciones.length-1){
										if(Integer.parseInt(serv.getDias()) > 1)
											detalles[cont]="Precio por "+serv.getDias()+" días: "+serv.getPrecioDescuento();
										else
											detalles[cont]="Precio por un día: "+serv.getPrecioDescuento();
									}
									cont=cont+1;
								}								
								f.agregarParrafoMultilinea(detalles);
							}
						}
						
					}
				}
				
				//f.crearTabla(presupuesto.getSalas());
				
				f.agregarParrafoGrande("TOTAL: $"+total,"40");
				f.agregarParrafo("Este presupuesto no incluye IVA");
				f.agregarParrafoNegrita("INSTALACION y DESARME ");
				f.agregarParrafoMultilinea(new String[]{"En el momento de la confirmación del presente presupuesto, se combinará el horario de instalación y desarme.",
								"Este presupuesto podrá sufrir modificaciones dependiendo del horario de instalación y/o de pruebas requeridas, lo mismo que si se realizara o armara un feriado o fin de semana."});
				f.agregarParrafoNegrita("CONDICIONES DE CONTRATACIÓN");
				f.agregarParrafoMultilinea(new String[]{"Validez del presupuesto: ",val.getDescripcion()});
				f.agregarParrafoMultilinea(new String[]{"Cancelación de los servicios: ",canc.getDescripcion()});
				f.agregarParrafoMultilinea(new String[]{"Instalación: ","Los equipos se instalan por personal técnico de nuestra empresa. Es responsabilidad de vuestra empresa informar al predio donde se llevará a cabo el evento: los datos de nuestra empresa y el tipo de servicio a prestar. Rogamos tener presente esta recomendación para evitar inconvenientes en la instalación y durante la prestación de los servicios."});
				f.agregarParrafoMultilinea(new String[]{"Seguridad: ","Vuestra empresa será responsable de la seguridad del lugar donde se realizará el evento.  Esto tendrá vigencia desde el momento en que ingresan los equipos hasta la finalización del evento y retiro de los mismos por personal de nuestra empresa."});
				f.agregarParrafoMultilinea(new String[]{"Faltante de equipos: ","Cuando la seguridad estuviera a cargo del cliente, el faltante de equipamiento estará a cargo del cliente.","Los equipamientos que sean manipulados por el cliente y que sufrieran deterioro o pérdida, tendrán que ser repuestos por el cliente o bien, abonar su precio de mercado."});
				f.agregarParrafoMultilinea(new String[]{"Conidiciones de pago: ",cond.getDescripcion()});
				f.agregarParrafoMultilinea(new String[]{"Conidiciones de reserva: ","La confirmación del evento por parte de CRN dependerá de la disponibilidad de equipamiento al momento de la aceptación del presupuesto por parte del cliente.","Para la reserva del equipamiento es necesario enviar una nota con la aceptación de este presupuesto y/o la Orden de Compra correspondiente."});
				f.agregarParrafoMultilinea(new String[]{"Aclaraciones técnicas: ","Equipamiento:El presupuesto no incluye los medios de elevación necesarios para colgar el equipamiento."});
				f.agregarParrafoMultilinea(new String[]{"Presentaciones: ","En caso de utilizar una aplicación que no corresponda a los programas standard de Office, el cliente deberá proveer la aplicación necesaria para realizar su presentación.","El material con formato Mac debe ser transformado a PC. Este paso puede obviarse si el disertante trae su propia Mac, la cual se conectará directamente al proyector ."});
				f.agregarParrafoMultilinea(new String[]{"Responsabilidad: ","Las grabaciones del material que se proyecten en el evento, se realizarán  bajo exclusiva responsabilidad y autorización del cliente, asumiendo éste todos los derechos y obligaciones en forma personal, frente a los expositores y/o miembros participantes del evento y/o cualquier otro tercero por la omisión, reproducción y distribución de dicho material con posterioridad al evento.",
											"Queda aclarado que nuestra empresa deslinda todo tipo de responsabilidad al respecto.","Todo reclamo con respecto a cuestiones técnica de la grabación, deberá ser realizado dentro de las 72hs. de entregado el material."});
				f.agregarParrafo("Congress Rental, no adopta ninguna política de confidencialidad con el objeto de proteger la privacidad de la información de sus clientes. En consecuencia; sus autoridades, empleados, agentes y/o subcontratistas no se hacen responsables de la información confidencial que pudiere manipularse, divulgarse, utilizarse y/o revelarse a otras personas físicas y/o jurídicas,  con motivo y en ocasión de los  eventos para los cuales sus servicios son contratados.");
				f.agregarParrafo("Instalado el equipamiento de acuerdo al layout aprobado por el cliente, cualquier tipo de modificación de último momento tendrá un costo adicional que será comunicado en el momento y facturado al finalizar el evento. Estos cambios estarán sujetos a la aprobación del responsable técnico de CRN, quien evaluará la viabilidad de realizarlos a tiempo.");
				f.agregarParrafo("Si este presupuesto no concuerda exactamente con las necesidades de equipamiento para vuestro evento, o cree que no responde a vuestras expectativas, por favor no deje de llamarnos para hacer una revisión del mismo.");
				f.agregarParrafo("A la espera de vuestra respuesta, quedo a su disposición para cualquier consulta adicional.");
				f.agregarParrafoNegrita("Coordialmente, "+presupuesto.getVendedor().getVendedor().getApellidoYNombre());
				f.guardarDoc();			
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			}
			else{
				MessageUtil.showErrorMessage("Seleccione los componentes\npara armar el presupuesto");
			}
		}
	}
	
	private class VerActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(presupuesto != null){				
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						Main.getVentana().getGlassPane().start();
						Thread performer = new Thread(new Runnable(){
							public void run(){
								if(ofCheck.isSelected()){
						
											ReportBuilder.previewOFReport(presupuesto.getNumeroDePresupuesto());							
				
								}	
								else if(ofAdelantoCheck.isSelected()){

										ReportBuilder.previewOFAdelantoReport(presupuesto.getNumeroDePresupuesto());

								}
								else if(osCheck.isSelected()){

										ReportBuilder.previewOSReport(presupuesto.getNumeroDePresupuesto());

								}
								else if(presupuestoCheck.isSelected()){
									boolean opcionesValidas = allValid(new ABMComboBox[]{headerCombo,formaPagoCombo,validezCombo, tipoPptoCombo, periodoCombo, monedaCombo, cancelacionCombo, condicionPagoCombo}, valorCotizado);

									if(opcionesValidas){
										final long codHeader = Long.valueOf(headerCombo.searchForeign());
										final long codFooter = 1;//Long.valueOf(footerCombo.searchForeign());
										final long codCancel = Long.valueOf(cancelacionCombo.searchForeign());
										final long codInstalac = 1;// Long.valueOf(instalacionCombo.searchForeign());
										final long codFirma = Long.parseLong(presupuesto.getVendedor().getVendedor().getCodUsuario());						
										final long codFormaPago = Long.valueOf(formaPagoCombo.searchForeign());
										final long codCondPago = Long.valueOf(condicionPagoCombo.searchForeign());
										final long codValidez = Long.valueOf(validezCombo.searchForeign());
										final long codSeguridad = 1;
										final long codPersonal = 1;
										final long codCondReserva = 1;
										final long codTipoPresupuesto = Long.valueOf(tipoPptoCombo.searchForeign());
										final long codPeriodo = Long.valueOf(periodoCombo.searchForeign());
										final long codMoneda = Long.valueOf(monedaCombo.searchForeign());
										final double cotiz = Double.valueOf(valorCotizado.getText());

												if(verDescripciones.isSelected())
													ReportBuilder.previewPresupuestoReport(presupuesto.getNumeroDePresupuesto(),codCancel, codHeader, codFooter,
														codInstalac, codValidez, codFormaPago, codCondPago, codFirma, codSeguridad, codPersonal, codCondReserva, codTipoPresupuesto, codPeriodo, codMoneda, cotiz);
												else
													ReportBuilder.previewPresupuestoResumReport(presupuesto.getNumeroDePresupuesto(),codCancel, codHeader, codFooter,
															codInstalac, codValidez, codFormaPago, codCondPago, codFirma, codSeguridad, codPersonal, codCondReserva, codTipoPresupuesto, codPeriodo, codMoneda, cotiz);

									}
									else{
										MessageUtil.showErrorMessage("Seleccione los componentes\npara armar el presupuesto");
									}
								}
								Main.getVentana().getGlassPane().stop();
							}
						},"armando reportes");
						performer.start();
					}
				});
			}
			
		}	
	}
	
	private class EnviarPresupuestoListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(presupuesto != null){
				boolean opcionesValidas = allValid(new ABMComboBox[]{headerCombo,formaPagoCombo,validezCombo, tipoPptoCombo, periodoCombo, monedaCombo, condicionPagoCombo, cancelacionCombo}, valorCotizado);
						/*, footerCombo, instalacionCombo,
						formaPagoCombo, firmaCombo, cancelacionCombo, validezCombo}); */
				if(opcionesValidas){
					final long codHeader = Long.valueOf(headerCombo.searchForeign());
					final long codFooter = 1;//Long.valueOf(footerCombo.searchForeign());
					final long codCancel = Long.valueOf(cancelacionCombo.searchForeign());
					final long codInstalac = 1;//Long.valueOf(instalacionCombo.searchForeign());
					final long codFirma = Long.parseLong(presupuesto.getVendedor().getVendedor().getCodUsuario());
					final long codFormaPago = Long.valueOf(formaPagoCombo.searchForeign());
					final long codCondPago = Long.valueOf(condicionPagoCombo.searchForeign());
					final long codValidez = Long.valueOf(validezCombo.searchForeign());
					final long codSeguridad = 1;
					final long codPersonal = 1;
					final long codCondReserva = 1;
					final long codTipoPresupuesto = Long.valueOf(tipoPptoCombo.searchForeign());
					final long codPeriodo = Long.valueOf(periodoCombo.searchForeign());
					final long codMoneda = Long.valueOf(monedaCombo.searchForeign());
					final double cotiz = Double.valueOf(valorCotizado.getText());
					/*ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
					ProgressDialogUtil.launchProcessDialog(Main.getVentana());
					Thread hilo = new Thread(new Runnable() {	
						public void run() {
							try{
								PresupuestoReport.instance().savePdfFile(presupuesto.getNumeroDePresupuesto(),codCancel, codHeader, codFooter,
										codInstalac, codValidez, codFormaPago, codCondPago, codFirma, codSeguridad, codPersonal, codCondReserva, codTipoPresupuesto, codPeriodo, codMoneda, cotiz);
								String codUsuario = VendedorUsuarioManager.instance().getCodigoUsuario(presupuesto.getVendedor().getVendedor().getCodigo());
								if(copiaCliente.isSelected()){
									PresupuestoReport.instance().sendPresupByEmail(presupuesto.getNumeroDePresupuesto(),
											codUsuario, presupuesto.getContacto().getCodContacto());
									Util.alertMsg(Main.getVentana(), "El presupuesto con copia al cliente se envió con éxito");
								}
								else
									PresupuestoReport.instance().sendPresupByEmail(presupuesto.getNumeroDePresupuesto(),
											codUsuario, "");
								Util.alertMsg(Main.getVentana(), "El presupuesto se envió con éxito");
							}
							catch(RemoteException e){
								e.printStackTrace();
								MessageUtil.showErrorMessage("Error al enviar el presupuesto");
							}
							finally{
								ProgressDialogUtil.closeProcessDialog();
							}
						}				
					});
					hilo.start();*/
					SwingUtilities.invokeLater(new Runnable(){
						public void run(){
							Main.getVentana().getGlassPane().start();
							Thread performer = new Thread(new Runnable(){
								public void run(){
									try{
										PresupuestoReport.instance().savePdfFile(presupuesto.getNumeroDePresupuesto(),codCancel, codHeader, codFooter,
												codInstalac, codValidez, codFormaPago, codCondPago, codFirma, codSeguridad, codPersonal, codCondReserva, codTipoPresupuesto, codPeriodo, codMoneda, cotiz);
										String codUsuario = VendedorUsuarioManager.instance().getCodigoUsuario(presupuesto.getVendedor().getVendedor().getCodigo());
										if(copiaCliente.isSelected()){
											PresupuestoReport.instance().sendPresupByEmail(presupuesto.getNumeroDePresupuesto(),
													codUsuario, presupuesto.getContacto().getCodContacto());
											Util.alertMsg(Main.getVentana(), "El presupuesto con copia al cliente se envió con éxito");
										}
										else
											PresupuestoReport.instance().sendPresupByEmail(presupuesto.getNumeroDePresupuesto(),
													codUsuario, "");
										Util.alertMsg(Main.getVentana(), "El presupuesto se envió con éxito");
									}
									catch(RemoteException e){
										e.printStackTrace();
										MessageUtil.showErrorMessage("Error al enviar el presupuesto");
									}
									finally{
										Main.getVentana().getGlassPane().stop();
									}
									
								}
							},"enviando ppto");
							performer.start();
						}
					});
				}
				else{
					MessageUtil.showErrorMessage("Seleccione los componentes\npara armar el presupuesto");
				}
			}			
		}
		
	}
	
	private class PresupActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			setPresupuestoMode(true);
			changeEstadoEnvio(true);
		}
		
	}
	
	private class OfActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			setPresupuestoMode(false);
			changeEstadoEnvio(false);
		}
		
	}
	
	private class OfAdelantoActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			setPresupuestoMode(false);
			changeEstadoEnvio(false);
		}
		
	}
	
	private class OsActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			setPresupuestoMode(false);
			changeEstadoEnvio(false);
		}
		
	}
	
	private class VerCotizacionActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			PantallaEmergenteCotizacion p = new PantallaEmergenteCotizacion(Main.getVentana());
			p.init();
			p.setVisible(true);			
		}
		
	}
	
	private class MonedaComboActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			//if(monedaCombo.searchForeign() != null && monedaCombo.searchForeign().equals("1")){
			if(((MonedaExtranjeraComboBox)arg0.getSource()).getSelectedIndex() == 1){
				simbolo.setText("Ingrese el valor del "+MONEDA_DOLAR+" en $ ");
				valorCotizado.setVisible(true);
			}
			else //if(monedaCombo.searchForeign() != null && monedaCombo.searchForeign().equals("2")){
				if(((MonedaExtranjeraComboBox)arg0.getSource()).getSelectedIndex() == 2){
				simbolo.setText("");
				valorCotizado.setVisible(false);
				valorCotizado.setText("1");
			}
			else //if(monedaCombo.searchForeign() != null && monedaCombo.searchForeign().equals("3")){
				if(((MonedaExtranjeraComboBox)arg0.getSource()).getSelectedIndex() == 3){
				simbolo.setText("Ingrese el valor del "+MONEDA_EURO+" en $ ");
				valorCotizado.setVisible(true);
			}
			//updateUI();
		}
		
	}
}
