package crm.client.pantalla.cobranzas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JTable.PrintMode;

import org.apache.commons.lang.StringUtils;

import crm.client.formatter.BigDecimalFormatter;
import crm.client.formatter.EmptyNumberFormatter;
import crm.client.managers.AgendaTareasCobranzasManager;
import crm.client.managers.ClienteContactoCobranzaManager;
import crm.client.managers.ClienteContactoManager;
import crm.client.managers.ClienteFacturacionManager;
import crm.client.managers.ClienteManager;
import crm.client.managers.AgendaPptoManager;
import crm.client.managers.LugarEventoManager;
import crm.client.managers.PresupuestosManager;
import crm.client.managers.SalaLugarManager;
import crm.client.managers.VendedorManager;
import crm.client.util.DateConverter;
import crm.client.util.DateDiff;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.UsuariosUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMClienteContactoCobranzaComboBox;
import crm.gui.components.ABMTipoCobradoresComboBox;
import crm.gui.components.ABMTipoFacturaComboBox;
import crm.gui.components.ABMTipoReciboComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.components.GradientButton;
import crm.gui.components.JXDatePicker;
import crm.gui.components.PanelImagen;
import crm.gui.custom.DoubleVerifier;
import crm.gui.pantalla.Pantalla;
import crm.gui.pantalla.PantallaBienvenidaAdministracion;
import crm.gui.pantalla.PantallaBienvenidaCobranzas;
import crm.gui.pantalla.PantallaCobranzas;
import crm.gui.pantalla.PantallaEmergente;
import crm.gui.pantalla.PantallaInterface;
import crm.gui.pantalla.PantallaNuevoContactoCliente;
import crm.gui.pantalla.PantallaNuevoContactoPago;
import crm.gui.tablerenderer.cobranzas.CobranzasItem;
import crm.gui.tablerenderer.cobranzas.agenda.AgendaCobranzasItem;
import crm.gui.tablerenderer.cobranzas.agenda.AgendaCobranzasTableModel;
import crm.gui.tablerenderer.cobranzas.agenda.TableRenderAgendaCobranzas;
import crm.libraries.abm.entities.AgendaTareasCobranzas;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.ClienteContactoCobranza;
import crm.libraries.abm.entities.ClienteFacturacion;
import crm.libraries.abm.entities.AgendaPpto;
import crm.libraries.abm.entities.LugarEvento;
import crm.libraries.util.MessageUtil;
import crm.services.sei.EstadoEventoManagerSEI;

public class PantallaAgendaCobranzas extends PantallaEmergente {
	
	private JXDatePicker FechaTarea;
    private GradientButton btnGrabar;
    private GradientButton btnSalir;
    private GradientButton btnEditar;
    private GradientButton btnnuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JCheckBox checkAnticipoCobrado;
    private javax.swing.JCheckBox checkCobrado;
    private javax.swing.JCheckBox checkCobradoConfirmar;
    private javax.swing.JCheckBox checkConAnticipo;
    private javax.swing.JLabel nombreCliente;
    private ABMClienteContactoCobranzaComboBox cmbContactoPago;
    private ABMTipoCobradoresComboBox cmbTipoCobrador;
    private javax.swing.JLabel comercial;
    private javax.swing.JLabel contactoCliente;
    private JXDatePicker fechaEvento;
    private JXDatePicker fechaFactura;
    private javax.swing.JLabel formaPago;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private TableRenderAgendaCobranzas jTable1;
    private javax.swing.JLabel lugarEvento;
    private javax.swing.JLabel nroFacturaAdelanto;
    private javax.swing.JLabel nroFacturaAdicionales;
    private javax.swing.JLabel nroFactura;
    private javax.swing.JFormattedTextField nuevaTarea;
    private javax.swing.JPanel panelVista;
    private javax.swing.JFormattedTextField txtDireccionPago;
    private javax.swing.JLabel txtEmailContacto;
    private javax.swing.JFormattedTextField txtHorariosPago;
    private javax.swing.JLabel txtTelContacto;
    private javax.swing.JToggleButton btnRecordatorio;
    private javax.swing.JToggleButton btnAcordado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JTextField jTextField1;
    private ABMTipoFacturaComboBox jComboBox1;
    private ABMTipoReciboComboBox jComboBox2;
    private double totalEvento;
    
    private Cliente cliente;    
    private ClienteContactoCobranza contactoElegido;
    private long nroPpto;
    
    private PanelImagen middlePanel;
    
    private CobranzasItem[] cobranzasItems;
    private int puntero = 0;
	private GradientButton btnSiguiente;
	private GradientButton btnAnterior;
	private JLabel jLabel10;
	
	private JPopupMenu popup;
	
	private boolean isRecordatorio = false;
	private boolean isAcordado = false;
    
	private PantallaCobranzas pantallaBienvenida;
	
	public PantallaAgendaCobranzas(PantallaCobranzas pant) {
		super("Agenda de cobro",Main.getVentana());
		pantallaBienvenida=pant;
	}
	
    public void setupMiddle() {
        middlePanel = new PanelImagen();
        panelVista = new javax.swing.JPanel();
        try {
            panelVista = new PanelImagen("Abstract_4220.jpg");
        } catch (Exception e) {
            System.out.println("Error");
            panelVista = new PanelImagen();
        }
        java.awt.GridBagConstraints gridBagConstraints;


        panelVista = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        nroFactura = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        fechaFactura = new JXDatePicker();
        jLabel16 = new javax.swing.JLabel();
        nroFacturaAdelanto = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        nroFacturaAdicionales = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        contactoCliente = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fechaEvento = new JXDatePicker();
        jLabel6 = new javax.swing.JLabel();
        comercial = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lugarEvento = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTable1 = new TableRenderAgendaCobranzas();
        jLabel25 = new javax.swing.JLabel();
        nuevaTarea = new javax.swing.JFormattedTextField();
        FechaTarea = new JXDatePicker();
        btnRecordatorio = new javax.swing.JToggleButton();
        btnAcordado = new javax.swing.JToggleButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTelContacto = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtEmailContacto = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtDireccionPago = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        txtHorariosPago = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        cmbTipoCobrador = new ABMTipoCobradoresComboBox();
        jLabel23 = new javax.swing.JLabel();
        formaPago = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        cmbContactoPago = new ABMClienteContactoCobranzaComboBox();
        btnEditar = new GradientButton("", Color.blue);
        btnnuevo = new GradientButton("", Color.blue);
        jSeparator4 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        checkConAnticipo = new javax.swing.JCheckBox();
        checkAnticipoCobrado = new javax.swing.JCheckBox();
        checkCobradoConfirmar = new javax.swing.JCheckBox();
        checkCobrado = new javax.swing.JCheckBox();
        jLabel28 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        nombreCliente = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        btnGrabar = new GradientButton("", Color.blue);
        btnSalir = new GradientButton("", Color.blue);
        btnSiguiente = new GradientButton("", Color.blue);
        btnAnterior = new GradientButton("", Color.blue);
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField1 = new JFormattedTextField();//CustomTextField.getDecimalInstance(8,2);

        jLabel27 = new javax.swing.JLabel();
        jComboBox1 = new ABMTipoFacturaComboBox();
        jComboBox2 = new ABMTipoReciboComboBox();

        panelVista.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("Factura");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel15.setText("Fecha de factura");

        fechaFactura.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel16.setText("Factura Adicionales");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel14.setText("Factura Adelanto");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Contacto del cliente");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Fecha evento");

        fechaEvento.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Comercial");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Lugar de evento");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Agenda de Cobranzas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0)));

        //tareas.setViewportView(jTable1);

        jLabel25.setText("Ingrese una nueva Tarea");

        btnRecordatorio.setIcon(new javax.swing.ImageIcon(getUrlImagen("bell_add.png")));

        //btnRecordatorio.setIcon(new javax.swing.ImageIcon(getUrlImagen("bell_add_pressed.png")));

        btnAcordado.setIcon(new javax.swing.ImageIcon(getUrlImagen("money_add.png")));

        //btnAcordado.setIcon(new javax.swing.ImageIcon(getUrlImagen("money_add_pressed.png")));

        popup = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Imprimir grilla");
        menuItem.setIcon(new javax.swing.ImageIcon(getUrlImagen("printer.png")));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
        menuItem.addActionListener(new PopUpMenuActionListener());
        popup.add(menuItem);
        JMenuItem menuBorrar = new JMenuItem("Borrar tarea");
        menuBorrar.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        menuBorrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
        menuBorrar.addActionListener(new PopUpMenuBorrarActionListener());
        popup.add(menuBorrar);

        MouseListener popupListener = new PopupListener();
        jTable1.getTable().addMouseListener(popupListener);

        jLabel9.setText("Fecha");

        jLabel7.setText("Monto a cobrar");
        jFormattedTextField1.setText("0.00");
        jFormattedTextField1.setVisible(false);
        jFormattedTextField1.setInputVerifier(new DoubleVerifier());
        jLabel7.setVisible(false);

        jLabel27.setText("Factura");
        jLabel27.setVisible(false);


        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup().addContainerGap().add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(org.jdesktop.layout.GroupLayout.TRAILING, jTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE).add(jPanel4Layout.createSequentialGroup().add(btnAcordado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(1, 1, 1).add(btnRecordatorio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jLabel25).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(nuevaTarea, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 224, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jLabel7).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 83, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jLabel27).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jComboBox1, 0, 99, Short.MAX_VALUE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(FechaTarea, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 132, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))).addContainerGap()));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jPanel4Layout.createSequentialGroup().add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(jLabel25).add(btnRecordatorio).add(btnAcordado).add(FechaTarea, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(jLabel9).add(nuevaTarea, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(jLabel7).add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(jLabel27).add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE).addContainerGap()));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Contacto de pago");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel18.setText("Tel.");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel19.setText("Email");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel20.setText("Direcci\u00f3n de Pago");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel21.setText("Horarios");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel22.setText("Tipo de Cobrador");

        cmbTipoCobrador.loadItems();

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel23.setText("Forma de Pago");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel24.setText("Observaciones");

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        btnEditar.setText("Editar contacto");
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        //btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditar.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));

        btnnuevo.setText("Nuevo contacto");
        btnnuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        //btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnnuevo.setIcon(new javax.swing.ImageIcon(getUrlImagen("money_add.png")));

        org.jdesktop.layout.GroupLayout panelVistaLayout = new org.jdesktop.layout.GroupLayout(panelVista);
        panelVista.setLayout(panelVistaLayout);
        panelVistaLayout.setHorizontalGroup(
                panelVistaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(panelVistaLayout.createSequentialGroup().addContainerGap().add(panelVistaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE).add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE).add(panelVistaLayout.createSequentialGroup().add(jLabel8).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(lugarEvento, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)).add(panelVistaLayout.createSequentialGroup().add(jLabel3).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(contactoCliente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 323, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(60, 60, 60).add(jLabel5).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(fechaEvento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 147, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(22, 22, 22).add(jLabel6).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(comercial, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)).add(panelVistaLayout.createSequentialGroup().add(jLabel11).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(cmbContactoPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 236, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(btnEditar).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(btnnuevo).add(51, 51, 51).add(jLabel18).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(txtTelContacto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 98, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jLabel19).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(txtEmailContacto, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)).add(panelVistaLayout.createSequentialGroup().add(jLabel20).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(txtDireccionPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 417, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jLabel21).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(txtHorariosPago, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)).add(jSeparator4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE).add(panelVistaLayout.createSequentialGroup().add(jLabel22).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(cmbTipoCobrador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 242, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(55, 55, 55).add(jLabel23).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(formaPago, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)).add(panelVistaLayout.createSequentialGroup().add(jLabel24).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)).add(panelVistaLayout.createSequentialGroup().add(jLabel13).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(nroFactura, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jLabel15).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(fechaFactura, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 147, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jLabel14).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(nroFacturaAdelanto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jLabel16).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(nroFacturaAdicionales, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))).addContainerGap()));
        panelVistaLayout.setVerticalGroup(
                panelVistaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(panelVistaLayout.createSequentialGroup().addContainerGap().add(panelVistaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(jLabel13).add(nroFactura).add(jLabel15).add(fechaFactura, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(jLabel14).add(nroFacturaAdelanto).add(jLabel16).add(nroFacturaAdicionales)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(panelVistaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(jLabel3).add(contactoCliente).add(jLabel5).add(fechaEvento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(jLabel6).add(comercial)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(panelVistaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(jLabel8).add(lugarEvento)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(panelVistaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(jLabel11).add(cmbContactoPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(btnEditar).add(btnnuevo).add(jLabel18).add(txtTelContacto).add(jLabel19).add(txtEmailContacto)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(panelVistaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(txtDireccionPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(jLabel21).add(jLabel20).add(txtHorariosPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jSeparator4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(panelVistaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(jLabel22).add(cmbTipoCobrador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(jLabel23).add(formaPago)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(panelVistaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jLabel24).add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Estado de cobranza del Presupuesto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        checkConAnticipo.setText("Con Anticipo a confirmar ");
        checkConAnticipo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkConAnticipo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 3, 0);
        jPanel1.add(checkConAnticipo, gridBagConstraints);

        checkAnticipoCobrado.setText("Anticipo Cobrado ");
        checkAnticipoCobrado.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkAnticipoCobrado.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(checkAnticipoCobrado, gridBagConstraints);

        checkCobradoConfirmar.setText("Cobrado a Confirmar ");
        checkCobradoConfirmar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkCobradoConfirmar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 3, 0);
        jPanel1.add(checkCobradoConfirmar, gridBagConstraints);

        checkCobrado.setText("Cobrado ");
        checkCobrado.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkCobrado.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(checkCobrado, gridBagConstraints);

        /*jLabel28.setText("Nro. recibo    ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jLabel28, gridBagConstraints);

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 111;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jTextField1, gridBagConstraints);*/

        jLabel28.setText("Tipo y Nro de recibo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 1, 3);
        jPanel1.add(jLabel28, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        //gridBagConstraints.ipadx = 63;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 108, 0, 0);
        jPanel1.add(jComboBox2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 72;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanel1.add(jTextField1, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Cliente:");

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel26.setText("Saldo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jPanel5.add(jLabel26, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Adelanto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 34;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jPanel5.add(jLabel1, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("TOTAL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jPanel5.add(jLabel4, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel17.setText("Sin IVA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        jPanel5.add(jLabel17, gridBagConstraints);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel29.setText("Con Iva");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        jPanel5.add(jLabel29, gridBagConstraints);

        jLabel30.setText("$0.0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel5.add(jLabel30, gridBagConstraints);

        jLabel31.setText("$0.0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel5.add(jLabel31, gridBagConstraints);

        jLabel32.setText("$0.0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel5.add(jLabel32, gridBagConstraints);

        jLabel33.setText("$0.0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        jPanel5.add(jLabel33, gridBagConstraints);

        jLabel34.setText("$0.0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanel5.add(jLabel34, gridBagConstraints);

        jLabel35.setText("$0.0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        jPanel5.add(jLabel35, gridBagConstraints);

        jSeparator3.setPreferredSize(new java.awt.Dimension(250, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        jPanel5.add(jSeparator3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridLayout());

        btnGrabar.setText("Grabar");
        btnGrabar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGrabar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGrabar.setIcon(new javax.swing.ImageIcon(getUrlImagen("disk.png")));
        jPanel2.add(btnGrabar);

        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.setIcon(new javax.swing.ImageIcon(getUrlImagen("door_out.png")));
        jPanel2.add(btnSalir);

        btnSiguiente.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_side_expand.png")));

        btnAnterior.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_side_contract.png")));
        btnAnterior.setEnabled(false);

        jLabel10.setText("Navegador");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(middlePanel);
        middlePanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING).add(org.jdesktop.layout.GroupLayout.LEADING, panelVista, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup().add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 438, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 294, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)).add(layout.createSequentialGroup().add(jLabel12).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(nombreCliente, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE).add(128, 128, 128).add(jLabel10).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(btnAnterior).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(btnSiguiente))).addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(jLabel12).add(nombreCliente).add(btnSiguiente).add(btnAnterior).add(jLabel10)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(panelVista, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false).add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 114, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        this.getContentPane().add(middlePanel);
        this.pack();
        createListeners();
    }
	
	private void loadEstados(){
		try{
			PresupuestosManager manager = PresupuestosManager.instance();
			Object[] estado = manager.buscarEstadoActual(nroPpto);
			
			for(int j=0; j<estado.length; j++){				
				Object[] estados = (Object[])estado[j];
				if((Integer)estados[11] == 0 ){
					checkConAnticipo.setEnabled(false);
					checkAnticipoCobrado.setEnabled(false);
				}
				if((Integer)estados[13] == 1){
					checkConAnticipo.setEnabled(false);
					checkConAnticipo.setSelected(true);
					String date = manager.getFechaByNroPptoAndStateAndUser(nroPpto, Integer.parseInt(EstadoEventoManagerSEI.CODIGO_ESTADO_ADELANTO_A_COBRAR));
					checkConAnticipo.setText("Con Anticipo a confirmar "+date.substring(0,10));
				}
				if((Integer)estados[14] == 1){
					checkAnticipoCobrado.setEnabled(false);
					checkAnticipoCobrado.setSelected(true);
					String date = manager.getFechaByNroPptoAndStateAndUser(nroPpto, Integer.parseInt(EstadoEventoManagerSEI.CODIGO_ESTADO_A_COBRAR));
					checkAnticipoCobrado.setText("Anticipo Cobrado "+date.substring(0,10));
				}
				if((Integer)estados[12] == 1){
					checkCobradoConfirmar.setEnabled(false);
					btnAcordado.setEnabled(true);
					checkCobradoConfirmar.setSelected(true);
					String date = manager.getFechaByNroPptoAndStateAndUser(nroPpto, Integer.parseInt(EstadoEventoManagerSEI.CODIGO_ESTADO_A_COBRAR));
					checkCobradoConfirmar.setText("Con Saldo a Confirmar "+date.substring(0,10));
					//jTextField1.setEnabled(false);					
				}
				if((Integer)estados[9] == 1){
					checkCobrado.setEnabled(false);
					checkCobrado.setSelected(true);
					String date = manager.getFechaByNroPptoAndStateAndUser(nroPpto, Integer.parseInt(EstadoEventoManagerSEI.CODIGO_ESTADO_COBRADO));
					checkCobrado.setText("Saldo Cobrado "+date.substring(0,10));
				}
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void loadContacto(){
    	if(contactoElegido != null){
    		if(contactoElegido.getTelefono1() != null){
    			txtTelContacto.setText(contactoElegido.getTelefono1());
    		}
    		if(contactoElegido.getEmail() != null){
    			txtEmailContacto.setText(contactoElegido.getEmail());
    		}
    	}
    }
	
	private void loadTareas(String nroPpto){
		AgendaTareasCobranzas[] agendas;
		try {
			agendas = AgendaTareasCobranzasManager.instance().findByField("numeroPresupuesto",nroPpto);
			
			AgendaCobranzasTableModel model = (AgendaCobranzasTableModel)jTable1.getTable().getModel();				
			for(int i=0; i<agendas.length;i++){
				AgendaCobranzasItem item = new AgendaCobranzasItem();
				AgendaTareasCobranzas agenda = agendas[i];
				item.setCodigo(agenda.getCodigo());
				item.setFechaTarea(agenda.getFechaVencimiento());
				item.setNumeroDePresupuesto(Long.parseLong(agenda.getNumeroPresupuesto()));
				item.setTarea(agenda.getAsunto());
				
				if(!StringUtils.isBlank(agenda.getMonto())){
					item.setMonto(agenda.getMonto());
				}
				
				if(agenda.getAlerta()==0)
					item.setTipoIcono(0);
				else if(agenda.getAlerta()==1)
					item.setTipoIcono(1);
				else 
					item.setTipoIcono(2);
				if(agenda.getCompleta().equals("S"))
					item.setCompletada(true);
				else
					item.setCompletada(false);
				
				if(agenda.getFactura() != null){
					if(agenda.getFactura()==1){
						item.setFactura(nroFactura.getText());
					}else if(agenda.getFactura()==2){
						item.setFactura(nroFacturaAdelanto.getText());
					}else if(agenda.getFactura()==3){
						item.setFactura(nroFacturaAdicionales.getText());
					}
				}
				
				
				model.addRow(item);
				
			}
			jTable1.refreshTable();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    private void loadAgenda(String nroPpto) {
    	if(nroPpto != null){
    		try {		
    			
    			AgendaPpto agenda = AgendaPptoManager.instance().getDataById(nroPpto);
    			
    			if(agenda != null){
    				if(agenda.getCodCobrador() != null && !agenda.getCodCobrador().equals("0")){
    					cmbTipoCobrador.setForeign(agenda.getCodCobrador());
    				}
    				if(agenda.getCodContacto() != null && !agenda.getCodContacto().equals("0")){
    					cmbContactoPago.setForeign(agenda.getCodContacto());
    				}
    				if(!StringUtils.isBlank(agenda.getDireccionPago())){
    					txtDireccionPago.setText(agenda.getDireccionPago());
    				}
    				if(!StringUtils.isBlank(agenda.getHorarioPago())){
    					txtHorariosPago.setText(agenda.getHorarioPago());
    				}
    				if(!StringUtils.isBlank(agenda.getNumeroRecibo())){
    					jTextField1.setText(agenda.getNumeroRecibo());
    				}
    				jComboBox2.loadItems();
    				if(!StringUtils.isBlank(agenda.getCodTipoRecibo())){
    					jComboBox2.setForeign(agenda.getCodTipoRecibo());
    				}
    			}
    			
    			loadEstados();
    			
    			loadTareas(nroPpto);    			   			
    			
    		} catch (RemoteException e) {
    			e.printStackTrace();
    		}
    	}
    	else{
    		((AgendaCobranzasTableModel)jTable1.getTable().getModel()).clear();
    		jTable1.refreshTable();    		
    	}

	}  
    
	private void createListeners(){
		btnSalir.addActionListener(new SalirAccion());
		nuevaTarea.addKeyListener(new NuevaTareaKeyListener());
		btnRecordatorio.addActionListener(new RecordatorioActionListener());
		btnAcordado.addActionListener(new AcordadoActionListener());
		btnGrabar.addActionListener(new GrabarActionListener());
		btnEditar.addActionListener(new EditarClienteContactoAction());
		btnnuevo.addActionListener(new NewContactoAction());
		cmbContactoPago.addActionListener(new SelectContactoAction());
		
		checkConAnticipo.addActionListener( new AnticipoAConfirmarActionListener());
		checkAnticipoCobrado.addActionListener( new AnticipoCobradoActionListener());
		checkCobradoConfirmar.addActionListener( new SaldoAConfirmarActionListener());
		checkCobrado.addActionListener( new SaldoCobradoActionListener());
		
		btnSiguiente.addActionListener(new SiguienteActionListener());
		btnAnterior.addActionListener(new AnteriorActionListener());
	}
	
	private void clearTareas(){
		
		nuevaTarea.setText("");
		
		jLabel7.setVisible(false);
		jFormattedTextField1.setVisible(false);
		jFormattedTextField1.setText("0");
		
		jLabel27.setVisible(false);
		jComboBox1.setForeign("0");
		jComboBox1.setVisible(false);
		
		btnAcordado.setSelected(false);
		btnRecordatorio.setSelected(false);
		
		isAcordado = false;
		isRecordatorio = false;
	}
	
	private void clearData(){
		jLabel2.setText("Estado de cobranza del Presupuesto ");
		nroPpto = 0;
		nombreCliente.setText("");
		nroFactura.setText("");
		nroFacturaAdelanto.setText("");
		nroFacturaAdicionales.setText("");

		fechaFactura.setDate(new Date());
		fechaEvento.setDate(new Date());			

		contactoCliente.setText("");
		lugarEvento.setText("");
		comercial.setText("");		
		formaPago.setText("");
		jTextArea1.setText("");
		jLabel35.setText("0.00");
		jLabel31.setText("0.00");
		jLabel33.setText("0.00");
		
		jLabel34.setText("0.00");
		jLabel30.setText("0.00");
		jLabel32.setText("0.00");
		
		cmbContactoPago.loadItemsForCliente("0");		
			
		txtTelContacto.setText("");

		txtDireccionPago.setText("");
		txtHorariosPago.setText("");
					
		cmbTipoCobrador.setForeign(null);
		cmbContactoPago.setForeign(null);
		
		checkConAnticipo.setText("Con Anticipo a confirmar ");
		checkAnticipoCobrado.setText("Anticipo Cobrado ");
		checkCobradoConfirmar.setText("Cobrado a Confirmar ");
		checkCobrado.setText("Cobrado ");
		
		btnAcordado.setEnabled(false);
		
		checkConAnticipo.setEnabled(true);
		checkAnticipoCobrado.setEnabled(true);
		checkCobradoConfirmar.setEnabled(true);
		checkCobrado.setEnabled(true);
		
		checkConAnticipo.setSelected(false);
		checkAnticipoCobrado.setSelected(false);
		checkCobradoConfirmar.setSelected(false);
		checkCobrado.setSelected(false);
		
		jComboBox1.removeAllItems();
		
		clearTareas();
		
		loadAgenda(null);

	}
	
	public void loadData(CobranzasItem it){
		jLabel2.setText("Estado de cobranza del Presupuesto "+it.getNumeroDePresupuesto());
		nroPpto = it.getNumeroDePresupuesto();
		nombreCliente.setText(it.getClienteFacturacion());		
		nroFactura.setText(it.getFactura());
		nroFacturaAdelanto.setText(it.getFacturaAdelanto());
		nroFacturaAdicionales.setText(it.getFacturaAdicional());
		
		jComboBox1.loadItems();
		jComboBox1.setVisible(false);
		
		if(StringUtils.isBlank(it.getFactura())){
			jComboBox1.removeItem("Factura");
		}
		if(StringUtils.isBlank(it.getFacturaAdelanto())){
			jComboBox1.removeItem("Factura de adelanto");
		}
		if(StringUtils.isBlank(it.getFacturaAdicional())){
			jComboBox1.removeItem("Factura de adicionales");
		}
		
		try {
			fechaFactura.setDate(DateConverter.convertStringToDate(it.getFechaFacturado(),"yyyy-MM-dd"));
			fechaEvento.setDate(DateConverter.convertStringToDate(it.getFechaConfirmacion(),"yyyy-MM-dd"));			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		contactoCliente.setText(it.getContacto());
		lugarEvento.setText(it.getLugar());
		comercial.setText(it.getComercial());		
		formaPago.setText(it.getCondicionPago());
		if(it.getObservaciones() != null)
			jTextArea1.setText(it.getObservaciones());
		else
			jTextArea1.setText("");
		totalEvento = Math.rint(((Double.parseDouble(it.getImporteTotal())*1.21))*100)/100;
		jLabel35.setText(getTotalFormateado(Math.rint(((Double.parseDouble(it.getImporteTotal())*1.21))*100)/100));
		jLabel31.setText(getTotalFormateado(Math.rint(((Double.parseDouble(it.getAdelanto())*1.21))*100)/100));
		jLabel33.setText(getTotalFormateado(Math.rint(((Double.parseDouble(it.getImporteTotal())*1.21)-(Double.parseDouble(it.getAdelanto())*1.21))*100)/100));
		
		jLabel34.setText(getTotalFormateado(Double.parseDouble(it.getImporteTotal())));
		jLabel30.setText(getTotalFormateado(Double.parseDouble(it.getAdelanto())));
		jLabel32.setText(getTotalFormateado((Double.parseDouble(it.getImporteTotal()))-(Double.parseDouble(it.getAdelanto()))));
		
		try {
			cliente = ClienteManager.instance().getClienteById(it.getCodClienteFacturacion());
			
			cmbContactoPago.loadItemsForCliente(it.getCodClienteFacturacion());		
			
			txtTelContacto.setText(cliente.getPagoTelefono());

			ClienteFacturacion cf = ClienteFacturacionManager.instance().getClienteFacturacionById(it.getCodClienteFacturacion());
			if(cf != null){
				txtDireccionPago.setText(cf.getDomicilioPago());
				txtHorariosPago.setText(cf.getDiaHoraPago());
			}
			else{
				txtDireccionPago.setText("");
				txtHorariosPago.setText("");
			}				
			loadAgenda(String.valueOf(nroPpto));
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Devuelve el tot en formato $###...,##
	 * @param tot : valor a formatear.
	 * @return Valor formateado.
	 */
	public String getTotalFormateado(double tot) {
		return getCurrencyFormat().format(tot);		
	}
	
	/*Devuelve el formato $###...,##*/
	private NumberFormat getCurrencyFormat() {
		NumberFormat currencyFormat;
		Locale l = new Locale("es","AR");
		currencyFormat = NumberFormat.getCurrencyInstance(l);
		return currencyFormat;
	}
	
	private void setAllCompleted(){
		AgendaCobranzasTableModel model = (AgendaCobranzasTableModel)jTable1.getTable().getModel();
		for(int i=0;i<model.getRowCount(); i++){
			AgendaCobranzasItem item = model.getRow(i);			
			item.setCompletada(true);
		}		
		jTable1.refreshTable();  

	}
	
	private void recoverCompleted(){		
		AgendaCobranzasTableModel model = (AgendaCobranzasTableModel)jTable1.getTable().getModel();
		for(int i=0;i<model.getRowCount(); i++){
			AgendaCobranzasItem item = model.getRow(i);			
			item.setCompletada(false);
		}		
		jTable1.refreshTable();  
	}
	
	//------------------------------------------------------ACCIONES----------------------------------------------------------
	
	private class SalirAccion implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(Main.getVentana(),"�Seguro que desea salir de la Agenda de llamados?","Salir")){

				/*PantallaCobranzas2 pantallaCobranzas = new PantallaCobranzas2();	
				pantallaCobranzas.initComponent();
		    	pantallaCobranzas.initLayout();
				//pantallaCobranzas.loadTableRows();			
				cambiarPantallaA(pantallaCobranzas);
			
			Main.getVentana().setExtendedState(JFrame.MAXIMIZED_BOTH);*/
			dispose();
			}
		}

	}
	
	private class RecordatorioActionListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if(btnRecordatorio.isSelected()){
				isRecordatorio = true;
				btnAcordado.setSelected(false);
				isAcordado = false;
				jFormattedTextField1.setVisible(false);
				jLabel7.setVisible(false);
				jLabel27.setVisible(false);
				jComboBox1.setVisible(false);
			}
			else{
				isRecordatorio = false;
			}
		}
		
	}
	
	private class AcordadoActionListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if(btnAcordado.isSelected()){
				isAcordado = true;
				btnRecordatorio.setSelected(false);
				isRecordatorio = false;
				jFormattedTextField1.setVisible(true);
				jLabel7.setVisible(true);
				jLabel27.setVisible(true);
				jComboBox1.setVisible(true);
			}
			else{
				isAcordado = false;
				jFormattedTextField1.setVisible(false);
				jLabel7.setVisible(false);
				jLabel27.setVisible(false);
				jComboBox1.setVisible(false);
			}
		}
		
	}
	
	private class NuevaTareaKeyListener implements KeyListener{

		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				AgendaCobranzasTableModel model = (AgendaCobranzasTableModel)jTable1.getTable().getModel();
				AgendaCobranzasItem item = new AgendaCobranzasItem();
				item.setFechaTarea(DateConverter.convertDateToString(FechaTarea.getDate(),"yyyy-MM-dd"));
				item.setCompletada(false);
				item.setTarea(nuevaTarea.getText());
				item.setCompletada(false);
				//if(jFormattedTextField1.getText() != )
				if (isRecordatorio){
					item.setTipoIcono(0);
					item.setMonto("");
				}
				else if(isAcordado){
					item.setTipoIcono(2);
					item.setMonto(jFormattedTextField1.getText());
					item.setTarea(nuevaTarea.getText() + " ($ "+jFormattedTextField1.getText()+")");
					if(jComboBox1.searchForeign() != null){
						if(jComboBox1.searchForeign().equals("1"))
							item.setFactura(nroFactura.getText());
						else if(jComboBox1.searchForeign().equals("2"))
							item.setFactura(nroFacturaAdelanto.getText());
						else if(jComboBox1.searchForeign().equals("3"))
							item.setFactura(nroFacturaAdicionales.getText());
						item.setTipoFactura(Integer.parseInt(jComboBox1.searchForeign()));
					}
				}
					else{
						item.setTipoIcono(1);
						item.setMonto("");
					}
				model.addRow(item);		
				clearTareas();
				jTable1.refreshTable();
			}
			
		}
		
	}
	
	private class AnticipoAConfirmarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(checkConAnticipo.isSelected()){
				checkConAnticipo.setText("Con Anticipo a confirmar "+DateConverter.convertDateToString(new Date(),"yyyy-MM-dd"));
				btnAcordado.setEnabled(true);
			}
			else{
				checkConAnticipo.setText("Con Anticipo a confirmar ");
				btnAcordado.setEnabled(false);
			}
		}
		
	}
	
	private class AnticipoCobradoActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(checkAnticipoCobrado.isSelected()){
				checkAnticipoCobrado.setText("Anticipo Cobrado "+DateConverter.convertDateToString(new Date(),"yyyy-MM-dd"));
				btnAcordado.setEnabled(true);
			}
			else{
				checkAnticipoCobrado.setText("Anticipo Cobrado ");
				btnAcordado.setEnabled(false);
			}
			
		}
		
	}
	
	private class SaldoAConfirmarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(checkCobradoConfirmar.isSelected()){
				checkCobradoConfirmar.setText("Con Saldo a Confirmar "+DateConverter.convertDateToString(new Date(),"yyyy-MM-dd"));
				btnAcordado.setEnabled(true);
			}
			else{
				checkCobradoConfirmar.setText("Con Saldo a Confirmar ");
				btnAcordado.setEnabled(false);
			}
		}
		
	}
	
	private class SaldoCobradoActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(checkCobrado.isSelected()){
				checkCobrado.setText("Saldo Cobrado "+DateConverter.convertDateToString(new Date(),"yyyy-MM-dd"));
				btnAcordado.setEnabled(true);
				if (MessageUtil.showYesNoMessage(Main.getVentana(),"Se marcaran todas las tareas como completadas. �Desea continuar?","Atenci�n")){
					setAllCompleted();
				}
			}
			else{
				checkCobrado.setText("Saldo Cobrado ");
				btnAcordado.setEnabled(false);
				recoverCompleted();
			}
				
			
		}
		
	}
	
	private class GrabarActionListener implements ActionListener{
		
		private AgendaTareasCobranzas createAgenda(AgendaCobranzasItem item, AgendaTareasCobranzasManager manager) throws RemoteException{
			AgendaTareasCobranzas entity = new AgendaTareasCobranzas();
			
			if(item.getCodigo() != null){
				entity = manager.getAgendaById(item.getCodigo());
				entity.setActivo("S");
			}
			
			if(item.getTipoIcono()==0)
				entity.setAlerta(0);
			else if(item.getTipoIcono()==1)
				entity.setAlerta(1);
			else entity.setAlerta(2);
			entity.setAsunto(item.getTarea());	
			entity.setActivo("S");
			entity.setFechaIngreso(DateConverter.convertDateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
			entity.setFechaVencimiento(item.getFechaTarea());
			entity.setNumeroPresupuesto(String.valueOf(nroPpto));
			//entity.setMonto(jFormattedTextField1.getText());
			if(!StringUtils.isBlank(item.getMonto()))
				entity.setMonto(item.getMonto());
			else
				entity.setMonto("0.00");
			if(item.isCompletada())
				entity.setCompleta("S");
			else
				entity.setCompleta("N");
			
			if(item.getTipoFactura() > 0)
				//entity.setFactura(Integer.valueOf(jComboBox1.searchForeign()));
				entity.setFactura(item.getTipoFactura());
			else
				entity.setFactura(null);
			
			return entity;
		}
		
		private AgendaPpto createAgenda(){
			AgendaPpto entity = new AgendaPpto();

				if(String.valueOf(jFormattedTextField1.getText())!= null){
					entity.setCodigo(String.valueOf(nroPpto));
					entity.setCodCobrador(cmbTipoCobrador.searchForeign());
					entity.setCodContacto(cmbContactoPago.searchForeign());
					entity.setDireccionPago(txtDireccionPago.getText());
					entity.setHorarioPago(txtHorariosPago.getText());
					entity.setNumeroRecibo(jTextField1.getText());
					entity.setCodTipoRecibo(jComboBox2.searchForeign());
				}
				else{
					entity=null;
				}

			return entity;
		}
		
		private void setEstados() throws RemoteException{
			PresupuestosManager manager = PresupuestosManager.instance();
			
			if(checkConAnticipo.isSelected() && checkConAnticipo.isEnabled()){					
				manager.setAnticipoAConfirmar(String.valueOf(nroPpto),pantallaBienvenida.getUser().getCodigo());				
			}
			if(checkAnticipoCobrado.isSelected() && checkAnticipoCobrado.isEnabled()){					
				manager.setAnticipoCobrado(String.valueOf(nroPpto),pantallaBienvenida.getUser().getCodigo());				
			}
			if(checkCobradoConfirmar.isSelected() && checkCobradoConfirmar.isEnabled()){					
				manager.setAsCobradoAConfirmar(String.valueOf(nroPpto),pantallaBienvenida.getUser().getCodigo());				
			}
			if(checkCobrado.isSelected() && checkCobrado.isEnabled()){					
				manager.setAsCobrado(String.valueOf(nroPpto),pantallaBienvenida.getUser().getCodigo());				
			}
		}
		
		private double sumMontos(){
			double sum = 0d;
			AgendaCobranzasTableModel model = (AgendaCobranzasTableModel)jTable1.getTable().getModel();
			for(int i=0;i<model.getRowCount(); i++){
				AgendaCobranzasItem item = model.getRow(i);	
				if(!StringUtils.isBlank(item.getMonto())){
					sum+=Double.parseDouble(item.getMonto());
				}
			}
			return sum;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			AgendaCobranzasTableModel model = (AgendaCobranzasTableModel)jTable1.getTable().getModel();
			
			AgendaTareasCobranzasManager manager = AgendaTareasCobranzasManager.instance();
			AgendaPpto c = createAgenda();
			if (c != null && sumMontos()<=totalEvento){				
				try {		
					setEstados();
					AgendaPptoManager.instance().update(c);
				} catch (RemoteException e1) {
					Util.errMsg(Main.getVentana(),"Error al guardar el tipo de cobrador.",e1);					
				}				
				for(int i=0;i<model.getRowCount(); i++){
					AgendaCobranzasItem item = model.getRow(i);	
					try {								
						AgendaTareasCobranzas entity = new AgendaTareasCobranzas();	
						entity = createAgenda(item, manager);
						String cod = manager.update(entity);
						model.getRow(i).setCodigo(cod);	
						if(i==model.getRowCount()-1)
							Util.alertMsg(Main.getVentana(),"La agenda se guard� con �xito");
					} catch (RemoteException e) {
						if(item.getTarea().length()>25)
							Util.errMsg(Main.getVentana(),"Error al guardar el item "+item.getTarea().substring(0,25)+"...",e);
						else
							Util.errMsg(Main.getVentana(),"Error al guardar el item "+item.getTarea(),e);
					}
				}
			}
			else if (cmbTipoCobrador.searchForeign() == null){
				Util.errMsg(Main.getVentana(),"Seleccione un Tipo de cobrador",null);
			}
			else if(cmbContactoPago.searchForeign() == null){
				Util.errMsg(Main.getVentana(),"Seleccione un Contacto de pago",null);
			}
			else if(sumMontos()>totalEvento){
				Util.errMsg(Main.getVentana(),"El valor a cobrar ingresado es mayor al verdadero valor a cobrar",null);
			}
			else{
				Util.errMsg(Main.getVentana(),"Seleccione un Contacto de pago",null);
			}
		}
		
	}
	
	private class NewContactoAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (cliente != null){
				PantallaNuevoContactoPago p = new PantallaNuevoContactoPago(Main.getVentana(), cliente);
				p.init();				
				p.setVisible(true);
				//setEmptyForm();
				cmbContactoPago.loadItemsForCliente(cliente.getCodigo());
				if(p.getCodContactoElegido() != null){
					cmbContactoPago.setForeign(p.getCodContactoElegido());
					contactoElegido = p.getContacto();
				}
			}
			else MessageUtil.showErrorMessage(Main.getVentana(),"Primero debe seleccionar un cliente");
			
		}
		
	}
	
	private class SelectContactoAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			String codigo = cmbContactoPago.searchForeign();
			
			if (StringUtils.isNotBlank(codigo) && !codigo.equals("0")){
				try {
					contactoElegido = ClienteContactoCobranzaManager.instance().getClienteContactoById(codigo);					
					loadContacto();
					
				} catch (RemoteException e) {
					Util.errMsg(Main.getVentana(),"Error al cargar datos externos ",e);
					return;
				}
			}
			else{
				contactoElegido = new ClienteContactoCobranza();
			}
			
		}
		
	}
	
	private class EditarClienteContactoAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (contactoElegido !=null && !StringUtils.isBlank(contactoElegido.getCodigo())){
				final String codClienteContacto = contactoElegido.getCodigo();
				/*ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_NEW_ENTITY);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				new Thread(new Runnable() {				
					public void run() {*/
						PantallaNuevoContactoPago pantallaEditar = new PantallaNuevoContactoPago(Main.getVentana(), cliente);
						pantallaEditar.init();
						pantallaEditar.setEditMode(codClienteContacto);
						pantallaEditar.setVisible(true);
						if(pantallaEditar.getCodContactoElegido() != null){
							cmbContactoPago.loadItemsForCliente(cliente.getCodigo());
							cmbContactoPago.setForeign(pantallaEditar.getCodContactoElegido());
							contactoElegido = pantallaEditar.getContacto();
						}
						/*ProgressDialogUtil.closeProcessDialog();
					}				
				}).start();*/

			}
			else Util.errMsg(Main.getVentana(),"Primero debe seleccionar un contacto de pago del cliente",null);
		}
	}
	
	private class SiguienteActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(cobranzasItems.length>1){
				//if(puntero<cobranzasItems.length-1){
					btnAnterior.setEnabled(true);					
					puntero++;
					if(puntero<cobranzasItems.length){
						clearData();					
						loadData(cobranzasItems[puntero]);
						if(puntero==cobranzasItems.length-1)
							btnSiguiente.setEnabled(false);
					}
					else{
						
						puntero=cobranzasItems.length-1;
					}
				
			}
		}
		
	}
	
	private class AnteriorActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(cobranzasItems.length>1){
				//if(puntero>0){
					btnSiguiente.setEnabled(true);
					puntero--;
					if(puntero>-1){
						clearData();						
						loadData(cobranzasItems[puntero]);
						if(puntero==0)
							btnAnterior.setEnabled(false);
					}
					else{						
						puntero=0;
					}
				
			}
		}
		
	}
	
	private class PopUpMenuActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {	        
	        try {
	        	MessageFormat format = new MessageFormat("Tareas a realizar del ppto "+nroPpto);
	        	MessageFormat format2 = new MessageFormat("Page {0}");
			    if (! jTable1.getTable().print(PrintMode.FIT_WIDTH,format, format2)) {
			        System.err.println("User cancelled printing");
			    }
			} catch (java.awt.print.PrinterException ex) {
			    System.err.format("Cannot print %s%n", ex.getMessage());
			}
	    }
	}
	
	private class PopUpMenuBorrarActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (MessageUtil.showYesNoMessage(Main.getVentana(),"�Seguro que desea eliminar esta tares?","Salir")){
				AgendaCobranzasTableModel model = (AgendaCobranzasTableModel)jTable1.getTable().getModel();
				if(jTable1.getSelectedItem() != null){				
					try {
						if(jTable1.getSelectedItem().getCodigo()!= null)
							AgendaTareasCobranzasManager.instance().remove(jTable1.getSelectedItem().getCodigo());
						model.removeRow(jTable1.getSelectedItem());
						jTable1.refreshTable();
					} catch (RemoteException e1) {
						Util.errMsg(Main.getVentana(),"Error al guardar el tipo de cobrador.",e1);					
					}					
				}
			}
	    }
	}
	
	class PopupListener extends MouseAdapter {
	    public void mousePressed(MouseEvent e) {
	        maybeShowPopup(e);
	    }

	    public void mouseReleased(MouseEvent e) {
	        maybeShowPopup(e);
	    }

	    private void maybeShowPopup(MouseEvent e) {
	        if (e.isPopupTrigger()) {
	            popup.show(e.getComponent(),
	                       e.getX(), e.getY());
	        }
	    }
	}
	
	public void setCobranzasItems(CobranzasItem[] cobranzasItems) {
		this.cobranzasItems = cobranzasItems;
	}
}
