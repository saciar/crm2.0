package crm.gui.pantalla.solapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TimeZone;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import crm.client.editors.MyMainTreeEditor;
import crm.client.helper.PresupuestoBuilder;
import crm.client.helper.PresupuestoBuilderXML;
import crm.client.helper.UserRolesUtil;
import crm.client.managers.NuevoClienteManager;
import crm.client.managers.PresupuestosManager;
import crm.client.managers.SalaLugarManager;
import crm.client.managers.TxSeguimientoManager;
import crm.client.managers.UnidadAdministrativaManager;
import crm.client.managers.VendedorManager;
import crm.client.report.AdelantoReport;
import crm.client.report.InformeOSReport;
import crm.client.report.OrdenFacturacionReport;
import crm.client.report.OrdenServicioReport;
import crm.client.serializer.SerializerManager;
import crm.client.util.DateConverter;
import crm.client.util.DateDiff;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.ProgressDialogUtil2;
import crm.client.util.Util;
import crm.client.validacion.ErrorList;
import crm.gui.Main;
import crm.gui.components.JXDatePicker;
import crm.gui.components.PanelImagen;
import crm.gui.gastos.CargaGastos;
import crm.gui.pantalla.PantallaBienvenidaAdmin;
import crm.gui.pantalla.PantallaBienvenidaAdministracion;
import crm.gui.pantalla.PantallaBienvenidaCobranzas;
import crm.gui.pantalla.PantallaBienvenidaCold;
import crm.gui.pantalla.PantallaBienvenidaComerciales;
import crm.gui.pantalla.PantallaBienvenidaFacturacion;
import crm.gui.pantalla.PantallaBienvenidaGerencia;
import crm.gui.pantalla.PantallaEmergenteOF;
import crm.gui.pantalla.PantallaEmergenteOS;
import crm.gui.pantalla.PantallaEmergenteSeguimiento;
import crm.gui.pantalla.solapa.gastos.GastosAsistentes;
import crm.gui.pantalla.solapa.gastos.GastosHoteleria;
import crm.gui.pantalla.solapa.gastos.GastosOperadores;
import crm.gui.pantalla.solapa.gastos.GastosRepresentacion;
import crm.gui.pantalla.solapa.gastos.GastosSubcontratacionesGrales;
import crm.gui.pantalla.solapa.gastos.GastosVarios;
import crm.gui.pantalla.solapa.gastos.GastosViaticos;
import crm.gui.pantallas2019.VentanaSeguimientoCreate;
import crm.gui.tablerenderer.horarios.HorariosItem;
import crm.gui.tablerenderer.salas.SalaServicioItem;
import crm.gui.tablerenderer.salas.SalaServiciosTableModel;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.ClienteFacturacion;
import crm.libraries.abm.entities.NuevoCliente;
import crm.libraries.abm.entities.Ppto_Sala;
import crm.libraries.abm.entities.Ppto_Sala_Horario;
import crm.libraries.abm.entities.Ppto_Sala_Servicio;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.SalaLugar;
import crm.libraries.abm.entities.TxSeguimiento;
import crm.libraries.abm.helper.PresupuestoHelper;
import crm.libraries.abm.helper.SalaHelper;
import crm.libraries.util.MessageUtil;
import crm.services.sei.EstadoEventoManagerSEI;

public class MainPanelComercial extends PantallaComercialAbstracta {
	
	PresupuestosManager manager = PresupuestosManager.instance();
	
	private javax.swing.JButton btExit;
	private javax.swing.JButton btRecord;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel logo;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel m_lblRooms;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTree jTree1;
	private javax.swing.JButton m_btnReenviarOFAdelanto;
	private javax.swing.JButton m_btnReenviarOFacturacion;
	private javax.swing.JButton m_btnReenviarOServicio;
	private javax.swing.JCheckBox m_checkCancel;
	private javax.swing.JCheckBox m_checkCobrado;
	private javax.swing.JCheckBox m_checkConfirm;
	private javax.swing.JCheckBox m_checkFact;
	private javax.swing.JCheckBox m_checkFacturado;
	private javax.swing.JCheckBox m_checkOCompra;
	private javax.swing.JCheckBox m_checkRech;
	private javax.swing.JCheckBox m_checkService;
	private javax.swing.JCheckBox m_checkState;
	private javax.swing.JLabel m_lblDays;
	private javax.swing.JLabel m_lblWeek;
	private JXDatePicker m_txtFromDate;
	private JXDatePicker m_txtToDate;
	private javax.swing.JLabel nroPpto;
	private javax.swing.JPanel panelVista;
	private JPanel panelPpal;
	
	private ClientePanel panelCliente;
	private EventoPanel panelEvento;
	private LugarEventoPanel panelLugarEvento;
	private GastosOperadores panelGastosOperador;
	private GastosAsistentes panelGastosAsistentes;
	private SubcontratadosPanel panelGastosSubcontratacionesGrales;
	private GastosRepresentacion panelGastosRepresentacion;
	private GastosHoteleria panelGastosHoteleria;
	private GastosViaticos panelGastosViaticos;
	private GastosVarios panelGastosVarios;
	private FacturacionPanel panelFacturacion;
	private SeguimientoPanel panelSeguimiento;
	private RentabilidadPanel panelRentabilidad;
	private ReportesPanel panelReportes;
	
	private VentanaSeguimientoCreate pantallaEmrgSeguimiento;
	
	private DefaultMutableTreeNode top;
	private DefaultMutableTreeNode salasTree;
	private DefaultTreeModel treeModel;
	
	private ArrayList<SalaPanel> panelesSala;
	
	private Presupuesto presupuesto;
	private double totalEvento;    
	
	private JPopupMenu popup;
	
	private long numeroPpto;
	
	private Timer timer;
	
	public MainPanelComercial(){
		super();	
	}
	
	@Override
	public void init() {//long tim = System.currentTimeMillis();
		
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setBorder(null);
		try{
			panelVista = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panelVista = new PanelImagen();
		}
        
        DefaultTreeCellRenderer renderer3 = new DefaultTreeCellRenderer();
        renderer3.setOpenIcon(new javax.swing.ImageIcon(getUrlImagen("bullet_arrow_right.png")));
        renderer3.setClosedIcon(new javax.swing.ImageIcon(getUrlImagen("bullet_arrow_down.png")));
        renderer3.setLeafIcon(new javax.swing.ImageIcon(getUrlImagen("bullet_red.png")));   
        
        //nodo padre CRM congresss rental
        top = new DefaultMutableTreeNode("CRM Congress Rental");
        
        DefaultMutableTreeNode panelMain = null;
        DefaultMutableTreeNode panelGastos = null;
        DefaultMutableTreeNode partePanel = null;
        
        //nodo de tipo de pantalla main
        panelMain = new DefaultMutableTreeNode("Main");
        top.add(panelMain);
        
        //nodo cliente        
        partePanel = new DefaultMutableTreeNode("Cliente");              
        if(!UserRolesUtil.isCold(getUsuario())){
        	panelMain.add(partePanel);     
        }
        
        //nodo evento
        partePanel = new DefaultMutableTreeNode("Evento");
        if(!UserRolesUtil.isCold(getUsuario())){
        	panelMain.add(partePanel);
        }
        
        //nodo del lugar de evento
        partePanel = new DefaultMutableTreeNode("Lugar de evento");
        panelMain.add(partePanel);
        
        //nodo contacto de las salas
        salasTree = new DefaultMutableTreeNode("Salas seleccionadas");    
        
        panelMain.add(salasTree);
        
        partePanel = new DefaultMutableTreeNode("Facturación");
        if(!UserRolesUtil.isCold(getUsuario())){
        	panelMain.add(partePanel);
        }
        
        partePanel = new DefaultMutableTreeNode("Rentabilidad");
        if(!UserRolesUtil.isCold(getUsuario())){
        	panelMain.add(partePanel);
        }
        
        partePanel = new DefaultMutableTreeNode("Reportes");
        if(!UserRolesUtil.isCold(getUsuario())){
        	panelMain.add(partePanel);
        }
        panelGastos = new DefaultMutableTreeNode("Comisiones y gastos");
        top.add(panelGastos);

        /*partePanel = new DefaultMutableTreeNode("Comisiones a 3ros.");
        panelGastos.add(partePanel);

        partePanel = new DefaultMutableTreeNode("Varios");
        panelGastos.add(partePanel);*/
        
        partePanel = new DefaultMutableTreeNode("De operadores");
        panelGastos.add(partePanel);

        partePanel = new DefaultMutableTreeNode("De subcontrataciones en salas");
        panelGastos.add(partePanel);
        
        partePanel = new DefaultMutableTreeNode("Comisiones a 3ros.");
        panelGastos.add(partePanel);
        
        partePanel = new DefaultMutableTreeNode("De viáticos");
        panelGastos.add(partePanel);
        
        partePanel = new DefaultMutableTreeNode("De hotelería");
        panelGastos.add(partePanel);
        
        partePanel = new DefaultMutableTreeNode("Gastos Varios");
        panelGastos.add(partePanel);
        
        treeModel = new DefaultTreeModel(top);        
        
        jTree1 = new javax.swing.JTree(treeModel);
        jTree1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

	    jTree1.setEditable(false);
        jTree1.setCellRenderer(renderer3);	    
        
        jScrollPane1.setViewportView(jTree1);        

        
        panelVista.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));       
		
        ////////////////////////////////////////////////////////////////////////////////////
        
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        nroPpto = new javax.swing.JLabel();
        m_checkState = new javax.swing.JCheckBox();
        m_checkConfirm = new javax.swing.JCheckBox();
        m_checkService = new javax.swing.JCheckBox();
        m_checkCancel = new javax.swing.JCheckBox();
        m_checkFact = new javax.swing.JCheckBox();
        m_checkRech = new javax.swing.JCheckBox();
        m_checkFacturado = new javax.swing.JCheckBox();
        m_checkOCompra = new javax.swing.JCheckBox();
        m_checkCobrado = new javax.swing.JCheckBox();
        m_btnReenviarOServicio = new javax.swing.JButton();
        m_btnReenviarOFacturacion = new javax.swing.JButton();
        m_btnReenviarOFAdelanto = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        m_txtFromDate = new JXDatePicker();
        m_txtToDate = new JXDatePicker();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btRecord = new javax.swing.JButton();
        btExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();    
        logo = new javax.swing.JLabel();   
        m_lblRooms = new JLabel("0");        
        m_lblWeek = new JLabel("0");
        m_lblDays = new JLabel("0");
        panelVista.setLayout(null);        
        
        panelVista.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        nroPpto.setFont(new java.awt.Font("Tahoma", 1, 11));
        nroPpto.setText("Estado del Presupuesto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 0);
        jPanel1.add(nroPpto, gridBagConstraints);

        m_checkState.setFont(new java.awt.Font("Tahoma", 1, 11));
        m_checkState.setText("Modificado");
        m_checkState.setEnabled(false);
        m_checkState.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_checkState.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 24, 0);
        jPanel1.add(m_checkState, gridBagConstraints);

        m_checkConfirm.setFont(new java.awt.Font("Tahoma", 1, 11));
        m_checkConfirm.setText("Confirmado");
        m_checkConfirm.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_checkConfirm.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 3, 0);
        jPanel1.add(m_checkConfirm, gridBagConstraints);

        m_checkService.setFont(new java.awt.Font("Tahoma", 1, 11));
        m_checkService.setText("Orden de servicio");
        m_checkService.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_checkService.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(m_checkService, gridBagConstraints);

        m_checkCancel.setFont(new java.awt.Font("Tahoma", 1, 11));
        m_checkCancel.setText("Cancelado");
        m_checkCancel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_checkCancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 3, 0);
        jPanel1.add(m_checkCancel, gridBagConstraints);

        m_checkFact.setFont(new java.awt.Font("Tahoma", 1, 11));
        m_checkFact.setText("Orden de facturación                       ");
        m_checkFact.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_checkFact.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        jPanel1.add(m_checkFact, gridBagConstraints);

        m_checkRech.setFont(new java.awt.Font("Tahoma", 1, 11));
        m_checkRech.setText("Rechazado");
        m_checkRech.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_checkRech.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 12, 3, 0);
        jPanel1.add(m_checkRech, gridBagConstraints);

        m_checkFacturado.setFont(new java.awt.Font("Tahoma", 1, 11));
        m_checkFacturado.setText("Facturado");
        m_checkFacturado.setEnabled(false);
        m_checkFacturado.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_checkFacturado.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(m_checkFacturado, gridBagConstraints);

        m_checkOCompra.setFont(new java.awt.Font("Tahoma", 1, 11));
        m_checkOCompra.setText("O/C");
        m_checkOCompra.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_checkOCompra.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 12, 3, 0);
        jPanel1.add(m_checkOCompra, gridBagConstraints);

        m_checkCobrado.setFont(new java.awt.Font("Tahoma", 1, 11));
        m_checkCobrado.setText("Cobrado");
        m_checkCobrado.setEnabled(false);
        m_checkCobrado.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_checkCobrado.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(m_checkCobrado, gridBagConstraints);

        m_btnReenviarOServicio.setText("Reenviar O/S");
        m_btnReenviarOServicio.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 11, 0);
        jPanel1.add(m_btnReenviarOServicio, gridBagConstraints);

        m_btnReenviarOFacturacion.setText("Reenviar O/F");
        m_btnReenviarOFacturacion.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel1.add(m_btnReenviarOFacturacion, gridBagConstraints);

        m_btnReenviarOFAdelanto.setText("Reenviar O/F Adelanto");
        m_btnReenviarOFAdelanto.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(m_btnReenviarOFAdelanto, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Datos del Evento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Desde el");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel2.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 21;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 40);
        jPanel2.add(m_txtFromDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 21;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 40);
        jPanel2.add(m_txtToDate, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Hasta el");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Cantidad de d\u00edas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 11, 7, 0);
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Salas usadas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 11, 15, 0);
        jPanel2.add(jLabel7, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Week nro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 11, 7, 0);
        jPanel2.add(jLabel8, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(m_lblDays, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(m_lblWeek, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(m_lblRooms, gridBagConstraints);
        
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText(" Total del evento ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 45, 0);
        jPanel3.add(jLabel9, gridBagConstraints);

        btRecord.setIcon(new javax.swing.ImageIcon(getUrlImagen("disk.png")));
        btRecord.setText("Cargando salas...");
        btRecord.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btRecord.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 0);
        jPanel3.add(btRecord, gridBagConstraints);

        btExit.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        btExit.setText("Salir del presupuesto");
        btExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 57, 0);
        jPanel3.add(btExit, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        
        int semana = DateDiff.getWeek(m_txtFromDate.getDate());
        m_lblWeek.setText(String.valueOf(semana));
		DateDiff dateDiff = DateDiff.calcularFechas(m_txtFromDate.getDate(), m_txtToDate.getDate());
		m_lblDays.setText(String.valueOf(dateDiff.getDayOnly()+1));		
		     
		logo.setIcon(new javax.swing.ImageIcon(getUrlImagen("LogoCongressPeque.png")));
		logo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		logo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		
        createListeners();    
        //System.out.println("tiempo de carga: "+((System.currentTimeMillis()-tim)/1000));
        createPaneles();
        
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    //.add(org.jdesktop.layout.GroupLayout.LEADING, jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE)
                		.add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 920, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(logo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 312, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(panelVista, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jLabel1)
                .add(logo))
                .add(14, 14, 14)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(panelVista, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addContainerGap())
        );
	}
	
	public void createPaneles(){		
		panelesSala = new ArrayList<SalaPanel>();
		
		panelGastosSubcontratacionesGrales = new SubcontratadosPanel(panelVista);		
		panelGastosSubcontratacionesGrales.init();
		panelGastosSubcontratacionesGrales.setMainPanel(this);
		panelGastosVarios = new GastosVarios(panelVista);
		panelGastosVarios.init();
		panelGastosRepresentacion = new GastosRepresentacion(panelVista);
		panelGastosRepresentacion.init();	
		
	}
	
	private String getNombreVendedor(String codigo){		
		try {
			return VendedorManager.instance().getVendedorById(codigo).getApellidoYNombre();
		} catch (RemoteException e) {
			e.printStackTrace();
			return "";
		}
	}	

	public void refreshSala(){
		
		Iterator<SalaPanel> it = panelesSala.iterator();
		while(it.hasNext()){
			SalaPanel salaPanel = it.next();
				refreshObject(salaPanel.getModel().getNombreSala());
			
		}
	}
	
public void addSalaFromPpto(String sala, String codSala, Ppto_Sala pptoSala){
		
		boolean existeSala = false;
		Iterator<SalaPanel> it = panelesSala.iterator();
		while(it.hasNext()){
			SalaPanel salaPanel = it.next();
			if(codSala.equals(salaPanel.getModel().getCodigoSala())){
				existeSala = true;
				break;
			}
		}
		
		if(!existeSala){
			
			SalaPanel salaPanel = new SalaPanel(panelVista);
			salaPanel.setMainPanel(MainPanelComercial.this);
			salaPanel.init();
			
			if(pptoSala !=null){				
				salaPanel.setPresupuesto(pptoSala);					

				if(pptoSala.getTipoArmado() != null)
					salaPanel.getModel().setCodigoTipoArmado(pptoSala.getTipoArmado().getCodigo());
				if(pptoSala.getObservaciones()!= null)
					salaPanel.getModel().setObservaciones(pptoSala.getObservaciones());

			}
			else{				
				salaPanel.getModel().setFechaDeInicio(getFechaInicio().substring(0,10));
				salaPanel.getModel().setFechaPrueba(getFechaInicio().substring(0,10));
				salaPanel.getModel().setFechaDeFinalizacion(getFechaFinalizacion().substring(0,10));
				salaPanel.getModel().setFechaDesarme(getFechaFinalizacion().substring(0,10));			
				salaPanel.getModel().setTotalDePersonas("0");
				salaPanel.getModel().setHorarios(new ArrayList<HorariosItem>());
			}
			salaPanel.getModel().setCodigoSala(codSala);
			salaPanel.getModel().setNombreSala(sala);
			//salaPanel.getModel().setHorarios(new ArrayList<HorariosItem>());
			panelesSala.add(salaPanel);
			

		}

		
	}

	public void addSala(String sala, String codSala, Ppto_Sala pptoSala, String nombreSalaUnica){
		
		boolean existeSala = false;
		Iterator<SalaPanel> it = panelesSala.iterator();
		while(it.hasNext()){
			SalaPanel salaPanel = it.next();
			if(codSala.equals(salaPanel.getModel().getCodigoSala())){
				existeSala = true;
				break;
			}
		}
		
		if(!existeSala){
			
			SalaPanel salaPanel = new SalaPanel(panelVista);
			salaPanel.setMainPanel(MainPanelComercial.this);
			salaPanel.init();
			
			if(pptoSala !=null){				
				salaPanel.setPresupuesto(pptoSala);					

				if(pptoSala.getTipoArmado() != null)
					salaPanel.getModel().setCodigoTipoArmado(pptoSala.getTipoArmado().getCodigo());
				if(pptoSala.getObservaciones()!= null)
					salaPanel.getModel().setObservaciones(pptoSala.getObservaciones());

			}
			else{				
				salaPanel.getModel().setFechaDeInicio(getFechaInicio().substring(0,10));
				salaPanel.getModel().setFechaPrueba(getFechaInicio().substring(0,10));
				salaPanel.getModel().setFechaDeFinalizacion(getFechaFinalizacion().substring(0,10));
				salaPanel.getModel().setFechaDesarme(getFechaFinalizacion().substring(0,10));			
				salaPanel.getModel().setTotalDePersonas("0");
				salaPanel.getModel().setHorarios(new ArrayList<HorariosItem>());
			}
			salaPanel.getModel().setCodigoSala(codSala);
			salaPanel.getModel().setNombreSala(sala);
			if(nombreSalaUnica!=null)
				salaPanel.getModel().setNombreSalaUnica(nombreSalaUnica);
			//salaPanel.getModel().setHorarios(new ArrayList<HorariosItem>());
			panelesSala.add(salaPanel);
			

		}
		else
			Util.alertMsg(Main.getVentana(), "Esta sala ya ha sido seleccionada");
		
	}
	
	public void modifySala(SalaPanel salavieja, SalaPanel salanueva){
		Iterator<SalaPanel> it = panelesSala.iterator();
		while(it.hasNext()){
			SalaPanel salaPanel = it.next();
			if(salanueva.getModel().getCodigoSala().equals(salaPanel.getModel().getCodigoSala())){		
				Object[] servicios = salaPanel.getModel().getServicios();
				for(int i=0; i<servicios.length;i++){
					
					System.out.println("pase: "+i+" veces y digo:"+((Ppto_Sala_Servicio)servicios[i]).getSala().getSala().getDescripcion());
					
				}
			}			
		}

	}
	
	public void removeSala(String sala, String codSala){
		Iterator<SalaPanel> it = panelesSala.iterator();
		boolean removed = false;
		while(it.hasNext()){
			SalaPanel salaPanel = it.next();
			if(codSala.equals(salaPanel.getModel().getCodigoSala())){
				if(MessageUtil.showYesNoMessage(Main.getVentana(),"¿Está seguro que desea eliminar esta sala?","Eliminar sala")){
					
					panelesSala.remove(salaPanel);
					removeObject(sala);					
					setRoomsCount(getRoomsCount()-1);					
				}
				removed = true;
				break;
			}			
		}
		if(!removed){
			Util.alertMsg(Main.getVentana(), "Esta sala no ha sido removida");
		}
	}
	
	public void removeAllSalas(){
		Iterator<SalaPanel> it = panelesSala.iterator();
		while(it.hasNext()){
			SalaPanel salaPanel = it.next();
			setTotalEvento(0d);
			setRoomsCount(0);
		}
		if(panelesSala.size()>0){
			panelesSala.clear();
		}
		removeAllObject();
	}
	
	public DefaultMutableTreeNode addObject(Object child) {
	    DefaultMutableTreeNode parentNode = null;
	    TreePath parentPath = null;
	    if(!UserRolesUtil.isCold(getUsuario()))
	    	parentPath = jTree1.getPathForRow(5);
	    else
	    	parentPath = jTree1.getPathForRow(3);

	    if (parentPath == null) {
	        //There's no selection. Default to the root node.
	        parentNode = salasTree;
	    } else {
	        parentNode = (DefaultMutableTreeNode)
	                     (parentPath.getLastPathComponent());
	    }

	    return addObject(parentNode, child, true);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
	                                        Object child,
	                                        boolean shouldBeVisible) {
	    DefaultMutableTreeNode childNode =
	            new DefaultMutableTreeNode(child);

	    treeModel.insertNodeInto(childNode, parent,
	                             parent.getChildCount());
	    
	    //Make sure the user can see the lovely new node.
	    if (shouldBeVisible) {
	        jTree1.scrollPathToVisible(new TreePath(childNode.getPath()));
	    }
	    return childNode;
	}
	
	public void removeAllObject() {
	    DefaultMutableTreeNode parentNode = null;
	    TreePath parentPath = jTree1.getPathForRow(5);

	    if (parentPath == null) {
	        parentNode = salasTree;
	    } else {
	        parentNode = (DefaultMutableTreeNode)
	                     (parentPath.getLastPathComponent());
	    }

	    removeAllObject(parentNode, true);
	}
	
	public void removeAllObject(DefaultMutableTreeNode parent,
			boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = parent.getFirstLeaf();
		int t = parent.getChildCount();
		for(int i=0;i<t;i++){	
			DefaultMutableTreeNode childNodeNext = childNode.getNextLeaf();
			treeModel.removeNodeFromParent(childNode);
			childNode = childNodeNext;
		}

	}

	public void removeObject(Object child) {
	    DefaultMutableTreeNode parentNode = null;
	    TreePath parentPath = jTree1.getPathForRow(5);

	    if (parentPath == null) {
	        //There's no selection. Default to the root node.
	        parentNode = salasTree;
	    } else {
	        parentNode = (DefaultMutableTreeNode)
	                     (parentPath.getLastPathComponent());
	    }

	    removeObject(parentNode, child, true);
	}
	
	public void removeObject(DefaultMutableTreeNode parent,
	                                        Object child,
	                                        boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = parent.getFirstLeaf();
		for(int i=0;i<parent.getChildCount();i++){			
			if(((String)childNode.getUserObject()).equals(child)){
				treeModel.removeNodeFromParent(childNode);
				break;
			}
			childNode = childNode.getNextLeaf();
		}
	}
	
	public void refreshObject(Object child) {
	    DefaultMutableTreeNode parentNode = null;
	    TreePath parentPath = jTree1.getPathForRow(5);

	    if (parentPath == null) {
	        parentNode = salasTree;
	    } else {
	        parentNode = (DefaultMutableTreeNode)
	                     (parentPath.getLastPathComponent());
	    }

	    refreshObject(parentNode, child, true);
	}

	public void refreshObject(DefaultMutableTreeNode parent,
	                                        Object child,
	                                        boolean shouldBeVisible) {

		DefaultMutableTreeNode childNode = parent.getFirstLeaf();
		
		treeModel.removeNodeFromParent(childNode);

		childNode = new DefaultMutableTreeNode(child);
		treeModel.insertNodeInto(childNode, parent,
                parent.getChildCount());
	}
	
	public List<SalaServiciosTableModel> getSalasCreated(){		
		Iterator<SalaPanel> it = panelesSala.iterator();
		List<SalaServiciosTableModel> salas = new ArrayList<SalaServiciosTableModel>();
		
		while (it.hasNext()) {
			SalaPanel salaPanel = it.next();			
			SalaServiciosTableModel tm = salaPanel.getTableModel();	
			tm.setCodigoSala(salaPanel.getModel().getCodigoSala());
			tm.setNombreSala(salaPanel.getModel().getNombreSala());
			salas.add(tm);
		}
		
		return salas;
	}
	
	public List<SalaModel> getModelSalasCreated(){		
		Iterator<SalaPanel> it = panelesSala.iterator();
		List<SalaModel> salas = new ArrayList<SalaModel>();
		
		while (it.hasNext()) {
			SalaPanel salaPanel = it.next();		
			SalaModel tm = salaPanel.getModel();	
			salas.add(tm);
		}
		
		return salas;
	}

	//*********************************ACCIONES************************************************
	private class MyTreeSelectionListener implements TreeSelectionListener{

		public void valueChanged(TreeSelectionEvent e) {		
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)
			(e.getPath().getLastPathComponent());
				
			final Object nodeInfo = node.getUserObject();
			
			if (node.isLeaf()) {
				if(((String)nodeInfo).equals("Cliente")){
					
					if(panelCliente == null){								
						panelCliente = new ClientePanel(panelVista);
						panelCliente.init();
						panelCliente.setMainPanel(MainPanelComercial.this);
						panelCliente.setPresupuesto(presupuesto);
					}						
					jTree1.setEditable(false);
					panelVista.removeAll();
					panelCliente.initLayout();
					panelVista.updateUI();						 
				}
				else if(((String)nodeInfo).equals("Evento")){
					if(panelEvento == null){
						panelEvento = new EventoPanel(panelVista);
						panelEvento.init();
						panelEvento.setMainPanel(MainPanelComercial.this);
						panelEvento.setPresupuesto(presupuesto);						
					}
					jTree1.setEditable(false);
					panelVista.removeAll();
					panelEvento.initLayout();					
					panelVista.updateUI();
				}
				else if(((String)nodeInfo).equals("Lugar de evento")){
					if(panelLugarEvento == null){
						panelLugarEvento = new LugarEventoPanel(panelVista);
						panelLugarEvento.init();
						panelLugarEvento.setMainPanel(MainPanelComercial.this);
						panelLugarEvento.setPresupuesto(presupuesto);
					}
					jTree1.setEditable(false);
					panelVista.removeAll();
					panelLugarEvento.initLayout();
					panelLugarEvento.setMainPanel(MainPanelComercial.this);
					panelVista.updateUI();
				}
				else if(((String)nodeInfo).equals("De operadores")){
					if(panelGastosOperador == null){
						panelGastosOperador = new GastosOperadores(panelVista);
						panelGastosOperador.init();
						panelGastosOperador.setPresupuesto(presupuesto);
					}					
					jTree1.setEditable(false);
					panelVista.removeAll();
					panelGastosOperador.initLayout();
					panelVista.updateUI();
				}
				else if(((String)nodeInfo).equals("De asistentes")){
					if(panelGastosAsistentes == null){
						panelGastosAsistentes = new GastosAsistentes(panelVista);
						panelGastosAsistentes.init();
						panelGastosAsistentes.setPresupuesto(presupuesto);
					}						
					jTree1.setEditable(false);
					panelVista.removeAll();
					panelGastosAsistentes.initLayout();
					panelVista.updateUI();
				}
				else if(((String)nodeInfo).equals("Comisiones a 3ros.")){
					if(panelGastosRepresentacion == null){
						panelGastosRepresentacion = new GastosRepresentacion(panelVista);
						panelGastosRepresentacion.init();
						panelGastosRepresentacion.setPresupuesto(presupuesto);
					}
					jTree1.setEditable(false);
					
					/*if(panelLugarEvento == null){
						if(!presupuesto.getLugarDelEvento().getCodigoComision().equals("0")){
							panelGastosRepresentacion.bloquearComision();
						}
						else{
							panelGastosRepresentacion.desbloquearComision();
						}
					}
					else{
						if(!panelLugarEvento.getLugarElegido().getCodigoComision().equals("0")){
							panelGastosRepresentacion.bloquearComision();
						}
						else{
							panelGastosRepresentacion.desbloquearComision();
						}
					}*/
					panelVista.removeAll();
					panelGastosRepresentacion.initLayout();
					panelVista.updateUI();
				}
				else if(((String)nodeInfo).equals("De viáticos")){
					if(panelGastosViaticos == null){
						panelGastosViaticos = new GastosViaticos(panelVista);
						panelGastosViaticos.init();
						panelGastosViaticos.setPresupuesto(presupuesto);
					}
					jTree1.setEditable(false);
					panelVista.removeAll();
					panelGastosViaticos.initLayout();
					panelVista.updateUI();
				}
				else if(((String)nodeInfo).equals("Gastos Varios")){
					jTree1.setEditable(false);
					panelVista.removeAll();
					panelGastosVarios.initLayout();
					panelVista.updateUI();
				}
				else if(((String)nodeInfo).equals("De hotelería")){
					if(panelGastosHoteleria == null){
						panelGastosHoteleria = new GastosHoteleria(panelVista);
						panelGastosHoteleria.init();
						panelGastosHoteleria.setPresupuesto(presupuesto);
					}
					jTree1.setEditable(false);
					panelVista.removeAll();
					panelGastosHoteleria.initLayout();
					panelVista.updateUI();
				}
				else if(((String)nodeInfo).equals("Facturación")){
					if(panelFacturacion == null){
						panelFacturacion = new FacturacionPanel(panelVista);
						panelFacturacion.init();
						panelFacturacion.setMainPanel(MainPanelComercial.this);	
						panelFacturacion.setPresupuesto(presupuesto);
						if(panelCliente != null && presupuesto.getFacturacion()==null)
							panelFacturacion.setDatosFacturacion(panelCliente.getClienteElegido());
					}
					
					jTree1.setEditable(false);
					panelVista.removeAll();
					panelFacturacion.initLayout();
					panelVista.updateUI();
				}
				else if(((String)nodeInfo).equals("Seguimiento")){
					if(panelSeguimiento == null){
						panelSeguimiento = new SeguimientoPanel(panelVista);
						panelSeguimiento.init();
						panelSeguimiento.setPresupuesto(presupuesto);
					}
					jTree1.setEditable(false);
					panelVista.removeAll();
					panelSeguimiento.initLayout();
					panelVista.updateUI();
				}
				else if(((String)nodeInfo).equals("Rentabilidad")){
					if(panelRentabilidad == null){
						panelRentabilidad = new RentabilidadPanel(panelVista);
						panelRentabilidad.init();
						panelRentabilidad.setMainPanel(MainPanelComercial.this);
						panelRentabilidad.setPresupuesto(presupuesto);
					}
					jTree1.setEditable(false);
					panelVista.removeAll();
					panelRentabilidad.initLayout();				
					panelRentabilidad.calculateData();
					panelVista.updateUI();
				}
				else if(((String)nodeInfo).equals("Reportes")){
					if(panelReportes == null){
						panelReportes = new ReportesPanel(panelVista);
						panelReportes.setMainPanel(MainPanelComercial.this);
						panelReportes.init();
						panelReportes.setPresupuesto(presupuesto);
					}
					jTree1.setEditable(false);
					panelVista.removeAll();
					panelReportes.initLayout();
					panelVista.updateUI();
				}
				else if(((String)nodeInfo).equals("De subcontrataciones en salas")){				
					jTree1.setEditable(false);
					panelVista.removeAll();					
					panelGastosSubcontratacionesGrales.initLayout();					
					panelVista.updateUI();
				}	
				
				else if(!((String)nodeInfo).equals("Salas seleccionadas")){					
					SwingUtilities.invokeLater(new Runnable(){
						public void run(){
							Main.getVentana().getGlassPane().start();
							Thread performer = new Thread(new Runnable(){
								public void run(){
									if(!estaCargada(nodeInfo) && panelesSala.size()>0){									
										Ppto_Sala pptoSala = getSalaPptoByName((String)nodeInfo);
										addSala(pptoSala.getSala().getDescripcion(), pptoSala.getSala().getCodigo(), pptoSala,pptoSala.getNombreSalaUnico());							
									}
									Iterator<SalaPanel> it = panelesSala.iterator();
									while(it.hasNext()){
										SalaPanel salaPanel = it.next();
										if(((String)nodeInfo).equals(salaPanel.getModel().getNombreSala())){
											panelVista.removeAll();
											salaPanel.initLayout();	
											if(panelLugarEvento != null){
												if(panelLugarEvento.getLugarElegido() != null){
													jTree1.setEditable(true);
													MyMainTreeEditor editor = new MyMainTreeEditor(panelLugarEvento.getLugarElegido().getCodigo(), salaPanel);
													editor.setMainPanel(MainPanelComercial.this);
													jTree1.setCellEditor(editor);
													salaPanel.getModel().setCodigoLugar(panelLugarEvento.getLugarElegido().getCodigo());
													
												}}
											else{
												if(presupuesto.getLugarDelEvento()!= null){
													jTree1.setEditable(true);
													MyMainTreeEditor editor = new MyMainTreeEditor(presupuesto.getLugarDelEvento().getCodigo(), salaPanel);
													editor.setMainPanel(MainPanelComercial.this);
													jTree1.setCellEditor(editor);
													salaPanel.getModel().setCodigoLugar(presupuesto.getLugarDelEvento().getCodigo());
												}
											}
											panelVista.updateUI();
											break;
										}				
									}
									Main.getVentana().getGlassPane().stop();
								}
							},"Login");
							performer.start();
						}
					});
				}				

			} 
			else {
				jTree1.setEditable(false);
				/**TODO: agregar un cartel del tipo seleccione...*/
			}			
		}	
		
		private boolean estaCargada(Object nodeInfo){
			boolean esta = false;
			Iterator<SalaPanel> it = panelesSala.iterator();
			
			while(it.hasNext()){
				SalaPanel salaPanel = it.next();
				if(((String)nodeInfo).equals(salaPanel.getModel().getNombreSala())){
					esta = true;
				}
			}
			
			return esta;
		}		
		
	}
	
	class CargaSalas implements Runnable{
		
		@Override
		public void run() {

	    	if (presupuesto.getSalas() != null){			
				Object[] salas = presupuesto.getSalasArray();
				btRecord.setEnabled(false);
				for(int i=0; i<salas.length; i++){				
					Ppto_Sala pptosala = (Ppto_Sala)salas[i];	
					if(panelesSala.size()>0){						
						addSalaFromPpto(pptosala.getSala().getDescripcion(), pptosala.getSala().getCodigo(), pptosala);
						addObject(pptosala.getSala().getDescripcion());						
					}
				}
				
				btRecord.setEnabled(true);
				btRecord.setText("Grabar presupuesto");
				panelGastosSubcontratacionesGrales.setPresupuesto(presupuesto);
	    	}				
		}
		
	}
	
	public GastosAsistentes getPanelGastosAsistentes(){
		return panelGastosAsistentes;
	}
	
	public GastosOperadores getPanelGastosOperadores(){
		return panelGastosOperador;
	}
	
	public SubcontratadosPanel getPanelGastosSubcontGral(){
		return panelGastosSubcontratacionesGrales;
	}
	
	public GastosHoteleria getPanelGastosHoteleria() {
		return panelGastosHoteleria;
	}

	public GastosRepresentacion getPanelGastosRepresentacion() {
		return panelGastosRepresentacion;
	}

	public GastosVarios getPanelGastosVarios() {
		return panelGastosVarios;
	}

	public GastosViaticos getPanelGastosViaticos() {
		return panelGastosViaticos;
	}
	
	public ClientePanel getPanelCliente(){
		return panelCliente;
	}	
	
	public ArrayList<SalaPanel> getPanelesSala() {
		return panelesSala;
	}
	
	public EventoPanel getPanelEvento() {
		return panelEvento;
	}

	public FacturacionPanel getPanelFacturacion() {
		return panelFacturacion;
	}

	public LugarEventoPanel getPanelLugarEvento() {
		return panelLugarEvento;
	}

	public JPanel getPanelPpal() {
		return panelPpal;
	}

	public RentabilidadPanel getPanelRentabilidad() {
		return panelRentabilidad;
	}

	public ReportesPanel getPanelReportes() {
		return panelReportes;
	}

	public SeguimientoPanel getPanelSeguimiento() {
		return panelSeguimiento;
	}
	
	private void createListeners(){
		jTree1.addTreeSelectionListener(new MyTreeSelectionListener());
    	btExit.addActionListener(new SalirSinGrabarAction());
    	btRecord.addActionListener(new GrabarPresupuestoAction());
    	m_btnReenviarOFacturacion.addActionListener(new ReenviarOFListener());
    	m_btnReenviarOServicio.addActionListener(new ReenviarOSListener());
    	m_btnReenviarOFAdelanto.addActionListener(new ReenviarOFAdelantoListener());
    	m_txtToDate.addActionListener(new FechaHastaItemListener());
    	m_txtFromDate.addActionListener(new FechaDesdeActionListener());
    	
    	m_checkConfirm.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkConfirmActionPerformed(evt);
            }
        });
    	m_checkCancel.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCancelActionPerformed(evt);
            }
        });
    	m_checkRech.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkRechActionPerformed(evt);
            }
        });
    	m_checkService.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkServiceActionPerformed(evt);
            }
        });
    	m_checkFacturado.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkFacturadoActionPerformed(evt);
            }
        });
    	m_checkCobrado.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCobradoActionPerformed(evt);
            }
        });
    	 m_checkFact.addActionListener(new java.awt.event.ActionListener(){
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 checkFactActionPerformed(evt);
             }
         });

    }
    
    public int getCancelado() {
        return m_checkCancel.isSelected()?1:0;
    }
    public void setCancelado(int i) {
        m_checkCancel.setSelected(i==1);
    }
    public int getCobrado() {
        return m_checkCobrado.isSelected()?1:0;
    }
    public void setCobrado(int i) {
        m_checkCobrado.setSelected(i==1);
    }
    public int getConfirmado() {
        return m_checkConfirm.isSelected()?1:0;
    }
    public void setConfirmado(int i) {
        m_checkConfirm.setSelected(i==1);
    }
    public int getFacturado() {
        return m_checkFacturado.isSelected()?1:0;
    }
    public void setFacturado(int i) {
        m_checkFacturado.setSelected(i==1);
    }
    public int getOrdenDeCompra() {
        return m_checkOCompra.isSelected()?1:0;
    }
    public void setOrdenDeCompra(int i) {
        m_checkOCompra.setSelected(i==1);
    }
    public int getOrdenDeFacturacion() {
        return m_checkFact.isSelected()?1:0;
    }
    public void setOrdenDeFacturacion(int i) {
        m_checkFact.setSelected(i==1);
    }
    public int getOrdenDeServicio() {
        return m_checkService.isSelected()?1:0;
    }
    public void setOrdenDeServicio(int i) {
        m_checkService.setSelected(i==1);
    }
    public int getRechazado() {
        return m_checkRech.isSelected()?1:0;
    }
    public void setRechazado(int i) {
        m_checkRech.setSelected(i==1);
    }
    
    private boolean isNewPresup(Presupuesto p){
    	return p.getEstadoActual() == null || p.getEstadoActual().getNuevo() == 1 ;
    }
    
    private boolean isPresupCobrado(Presupuesto p){
    	if (p.getEstadoActual() == null || p.getEstadoActual().getCobrado() == 0){
    		m_checkCobrado.setText("Cobrado");
    		return false;
    	}
    	else {
    		try{
    			String date = manager.getFechaByNroPptoAndStateAndUser
					(p.getNumeroDePresupuesto(), Integer.parseInt(EstadoEventoManagerSEI.CODIGO_ESTADO_COBRADO));

    			if(date != null)
    				m_checkCobrado.setText("Cobrado "+date.substring(0,10));
    			else m_checkCobrado.setText("Cobrado");
    }
    		catch(RemoteException e){
    			Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
    		}
    		return p.getEstadoActual().getCobrado() == 1 ;
    	}
    }
    
    private boolean isPresupConfirmado(Presupuesto p){
    	if (p.getEstadoActual() == null || p.getEstadoActual().getConfirmado() == 0){    		
    		m_checkConfirm.setText("Confirmado");
    		return false;
    	}
    		
    	else {
    		try{
    			String date = manager.getFechaByNroPptoAndStateAndUser
					(p.getNumeroDePresupuesto(),Integer.parseInt(EstadoEventoManagerSEI.CODIGO_ESTADO_CONFIRMADO));

    			if(date != null)
    				m_checkConfirm.setText("Confirmado "+date.substring(0,10));
    			else m_checkConfirm.setText("Confirmado");
    		}
    		catch(RemoteException e){
    			Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
    		}
    		return p.getEstadoActual().getConfirmado() == 1 ;
    	}
    }
    
    private boolean isPresupCancelado(Presupuesto p){
    	if (p.getEstadoActual() == null || p.getEstadoActual().getCancelado() == 0){
    		m_checkCancel.setText("Cancelado");
    		return false;
    	}
    	else {
    		try{
    			String date = manager.getFechaByNroPptoAndStateAndUser
					(p.getNumeroDePresupuesto(), Integer.parseInt(EstadoEventoManagerSEI.CODIGO_ESTADO_CANCELADO));
    			if(date != null)
    				m_checkCancel.setText("Cancelado "+date.substring(0,10));
    			else m_checkCancel.setText("Cancelado");
    }
    		catch(RemoteException e){
    			Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
    		}
    		return p.getEstadoActual().getCancelado() == 1 ;
    	}
    }
    
    private boolean isPresupRechazado(Presupuesto p){
    	if (p.getEstadoActual() == null || p.getEstadoActual().getRechazado() == 0){
    		m_checkRech.setText("Rechazado");
    		return false;
    	}
    	else {
    		try{
    			String date = manager.getFechaByNroPptoAndStateAndUser
					(p.getNumeroDePresupuesto(), Integer.parseInt(EstadoEventoManagerSEI.CODIGO_ESTADO_RECHAZADO));
    			if(date != null)
    				m_checkRech.setText("Rechazado "+date.substring(0,10));
    			else m_checkRech.setText("Rechazado");;
    }
    		catch(RemoteException e){
    			Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
    		}
    		return p.getEstadoActual().getRechazado()== 1 ;
    	}
    }
    
    private boolean isPresupOCompra(Presupuesto p){
    	if (p.getEstadoActual() == null || p.getEstadoActual().getOc() == 0){
    		m_checkOCompra.setText("O/C");
    		m_btnReenviarOFAdelanto.setEnabled(false);
    		return false;    		
    	}
    	else {
    		try{
    			String date = manager.getFechaByNroPptoAndStateAndUser
					(p.getNumeroDePresupuesto(), Integer.parseInt(EstadoEventoManagerSEI.CODIGO_ESTADO_ORDEN_COMPRA));
    			if(date != null)
    				m_checkOCompra.setText("O/C "+date.substring(0,10));
    			else m_checkOCompra.setText("O/C");
    }
    		catch(RemoteException e){
    			Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
    		}
    		return p.getEstadoActual().getOc() == 1 ;
    	}
    }
    
    private boolean isPresupOServicio(Presupuesto p){
    	if (p.getEstadoActual() == null || p.getEstadoActual().getOs() == 0){    		
    		m_checkService.setText("Orden de servicio");
    		return false;
    	}
    	else {
    		try{
    			String date = manager.getFechaByNroPptoAndStateAndUser
					(p.getNumeroDePresupuesto(), Integer.parseInt(EstadoEventoManagerSEI.CODIGO_ESTADO_ORDEN_SERVICIO));
    			if(date != null)
    				m_checkService.setText("Orden de servicio "+date.substring(0,10));
    			else m_checkService.setText("Orden de servicio");
    }
    		catch(RemoteException e){
    			Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
    		}
    		return p.getEstadoActual().getOs() == 1 ;
    	}
    }
    
    private boolean isPresupOFacturacion(Presupuesto p){
    	if (p.getEstadoActual() == null || p.getEstadoActual().getOf() == 0){
    		m_checkFact.setText("Orden de facturación                       ");
    		m_btnReenviarOFacturacion.setEnabled(false);
    		return false;
    	}
    	else {
    		try{
    			String date = manager.getFechaByNroPptoAndStateAndUser
					(p.getNumeroDePresupuesto(), Integer.parseInt(EstadoEventoManagerSEI.CODIGO_ESTADO_ORDEN_FACTURACION));
    			if(date != null)
    				m_checkFact.setText("Orden de facturación "+date.substring(0,10));
    			else m_checkFact.setText("Orden de facturación                       ");
    }
    		catch(RemoteException e){
    			Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
    		}
    		return p.getEstadoActual().getOf() == 1 ;
    	}
    }
    
    private boolean isPresupFacturado(Presupuesto p){
    	if (p.getEstadoActual() == null || p.getEstadoActual().getFacturado() == 0){
    		m_checkFacturado.setText("Facturado");
    		return false;
    	}
    	else {
    		try{
    			String date = manager.getFechaByNroPptoAndStateAndUser
					(p.getNumeroDePresupuesto(), Integer.parseInt(EstadoEventoManagerSEI.CODIGO_ESTADO_FACTURADO));
    			if(date != null)
    				m_checkFacturado.setText("Facturado "+date.substring(0,10));
    			else m_checkFacturado.setText("Facturado");
    }
    		catch(RemoteException e){
    			Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
    		}
    		return p.getEstadoActual().getFacturado() == 1 ;
    	}
    }
    
    public boolean isPresupACobrar(){
    	if(presupuesto.getEstadoActual() ==null || presupuesto.getEstadoActual().getAcobrar() == 0){
    		return false;
    	}
    	else {
    		return presupuesto.getEstadoActual().getAcobrar() == 1;
    	}
    }
    
    public boolean isPresupAdelantoACobrar(){
    	if(presupuesto.getEstadoActual() ==null || presupuesto.getEstadoActual().getAdelantoacobrar() == 0){
    		return false;
    	}
    	else {
    		return presupuesto.getEstadoActual().getAdelantoacobrar() == 1;
    	}
    }
    
    public boolean isPresupAdelantoCobrado(){
    	if(presupuesto.getEstadoActual() ==null || presupuesto.getEstadoActual().getAdelantocobrado() == 0){
    		return false;
    	}
    	else {
    		return presupuesto.getEstadoActual().getAdelantocobrado() == 1;
    	}
    }
    
    public boolean isPresupConAdicionales(){
    	if(presupuesto.getEstadoActual() ==null || presupuesto.getEstadoActual().getAdicionales() == 0){
    		return false;
    	}
    	else {
    		return presupuesto.getEstadoActual().getAdicionales() == 1;
    	}
    }
    
    public boolean isPresupAdicionalesFacturados(){
    	if(presupuesto.getEstadoActual() ==null || presupuesto.getEstadoActual().getAdicionalesFacturados() == 0){
    		return false;
    	}
    	else {
    		return presupuesto.getEstadoActual().getAdicionalesFacturados() == 1;
    	}
    }
    
    public SalaServicioItem getItemByEditingId(long id){
    	Iterator<SalaPanel> it = panelesSala.iterator();
		while(it.hasNext()){
			SalaPanel salaPanel = it.next();
			SalaServiciosTableModel sala = (SalaServiciosTableModel)salaPanel.getTableModel();
			Iterator it2 = sala.getRows().iterator();
			while(it2.hasNext()){
				SalaServicioItem item = (SalaServicioItem)it2.next();
				if(item.getEditingId() == id){
					return item;
				}
			}
		}		
		return null;
	}
    
    private void setStatesPresupuesto(Presupuesto p) throws RemoteException{   	
    	
    	nroPpto.setText(CreateLinePresupNumber(p));
        if (isNewPresup(p)) {
        	m_checkState.setText("Nuevo");
        	changeAllStatesStateNew();
        }
        else{
        	m_checkState.setText("Modificado");
        	changeAllStatesStateNew();
        }
        
        if (isPresupCobrado(p)){
        	m_checkCobrado.setSelected(true);
        }
        else m_checkCobrado.setSelected(false);
        
        if (isPresupConfirmado(p)){
        	changeAllStatesConfirmEnabled();
        	m_checkConfirm.setSelected(true);
        	m_checkConfirm.setEnabled(false);        	
        }
        else m_checkConfirm.setSelected(false);
        
        if (isPresupCancelado(p)){
        	m_checkCancel.setSelected(true);
        	m_checkCancel.setEnabled(false);
        }
        else m_checkCancel.setSelected(false);
        
        if (isPresupRechazado(p)){
        	m_checkRech.setSelected(true);
        	m_checkRech.setEnabled(false);
        }
        else m_checkRech.setSelected(false);
        
        if (isPresupOServicio(p)){
        	changeAllStatesServiceEnabled();
        	m_checkService.setSelected(true);
        	m_checkService.setEnabled(false);
        }
        else m_checkService.setSelected(false);
        
        if (p.getEstadoActual() == null || p.getEstadoActual().getAdelanto() == 1){
        	if (p.getEstadoActual() == null || p.getEstadoActual().getAdelantado() == 0){
        		m_checkFact.setSelected(false);
            	m_checkFact.setEnabled(false);
        	}
        	else if (p.getEstadoActual() == null || p.getEstadoActual().getAdelantado() == 1){
        		m_checkFact.setSelected(true);
            	m_checkFact.setEnabled(true);
        	}
        }
        
        if (isPresupOFacturacion(p)){
        	m_checkFact.setSelected(true);
        	m_checkFact.setEnabled(false);
        }
        else m_checkFact.setSelected(false);
        
        if (isPresupFacturado(p)){
        	m_checkFacturado.setSelected(true);
        	m_checkFacturado.setEnabled(false);
        }
        else m_checkFacturado.setSelected(false);    
        
        
    }
    
    public Ppto_Sala getSalaPptoByName(String index){
    	Ppto_Sala sala=null;
    	if (presupuesto.getSalas() != null){			
			Object[] salas = presupuesto.getSalasArray();
			
			for(int i=0; i<salas.length; i++){				
				Ppto_Sala pptosala = (Ppto_Sala)salas[i];	
				if(pptosala.getSala().getDescripcion().equals(index)){
					sala =(Ppto_Sala)salas[i];
					break;
				}
			}
			
    	}
    	return sala;
    }
    
    private void calcularTotalPpto(Ppto_Sala sala){
    	
		Set<Ppto_Sala_Servicio> servicios = sala.getServicios();
		if (servicios != null) {
			Iterator it = servicios.iterator();
			while (it.hasNext()) {
				Ppto_Sala_Servicio serv = (Ppto_Sala_Servicio) it.next();
				if (serv.getModalidad().getCodigo().equals("1")|| serv.getModalidad().getCodigo().equals("2"))
					setTotalEvento(Double.parseDouble(serv.getPrecioDescuento()));
			}
		}
		
    }
    
    
    
    /** Carga los estados del presupuesto p en la aplicacion
     * @param p: presupuesto a cargar 
     */
    public void setPresupuesto(Presupuesto p){
    	  	
    	setPantallasToNull();
    	presupuesto = p; 
    	this.checkSaveRoles();
    	if (presupuesto.getSalas() != null){
    		
			Object[] salas = presupuesto.getSalasArray();
			Ppto_Sala primerSala=null;			
			for(int i=0; i<salas.length; i++){				
				Ppto_Sala pptosala = (Ppto_Sala)salas[i];	
				if(i==0)
					primerSala =(Ppto_Sala)salas[i]; 
				//addObject(pptosala.getSala().getDescripcion());				
				setRoomsCount(getRoomsCount()+1);
				calcularTotalPpto(pptosala);
			}
			
			if(primerSala != null){
				addSalaFromPpto(primerSala.getSala().getDescripcion(), primerSala.getSala().getCodigo(), primerSala);
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						Thread performer = new Thread(new CargaSalas(), "CARGA SALAS");
						performer.start();

					}
				});
				
			}
			
    	}
    	else{
    		btRecord.setEnabled(true);
			btRecord.setText("Grabar presupuesto");
    	}
    	
    	try {
			setStatesPresupuesto(presupuesto);

	    	if(presupuesto.getVendedor() != null){
	    		jLabel1.setText("Presupuesto creado por "+getNombreVendedor(presupuesto.getVendedor().getVendedor().getCodigo())+" el día "+convertDateFormat(presupuesto.getVendedor().getFecha()));
	    	}
	    	else{
	    		jLabel1.setText("Presupuesto perteneciente a "+getNombreVendedor(getCodigoVendedor()));
	    	} 
			try {
	        	if(presupuesto.getFechaDeInicio() != null){
	        		Date d = DateConverter.convertStringToDate(presupuesto.getFechaDeInicio(), "yyyy-MM-dd");
	        		m_txtFromDate.setDate(d);
	        		int semana = DateDiff.getWeek(presupuesto.getFechaDeInicio());
	        		m_lblWeek.setText(String.valueOf(semana));
	        		if(presupuesto.getFechaDeFinalizacion() != null){        	
	        			Date d2 = DateConverter.convertStringToDate(presupuesto.getFechaDeFinalizacion(), "yyyy-MM-dd");
	        			m_txtToDate.setDate(d2);
	        		}
	        	}
	        	else{
	        		m_txtFromDate.setDate(new Date());
	        		if(presupuesto.getFechaDeFinalizacion() == null){
	        			m_txtToDate.setDate(new Date());
	        		}
	        	}
	        	
	        } catch (ParseException e) {
	        	e.printStackTrace();
	        }
	        
	        
	        this.checkUserAdmins();

			//panelGastosSubcontratacionesGrales.setPresupuesto(presupuesto);
			panelGastosVarios.setPresupuesto(presupuesto);
			panelGastosRepresentacion.setPresupuesto(presupuesto);
			//createTimer();

		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    private void checkRechActionPerformed(ActionEvent evt){
        if (((JCheckBox)evt.getSource()).isSelected()){
            allDisabledExceptRech();
            m_checkRech.setText("Rechazado "+getCurrentDate());
        }
        else {
            allEnabledExceptRech();
            m_checkRech.setText("Rechazado");
        }
    }
    
    private void checkServiceActionPerformed (ActionEvent evt){
        if (((JCheckBox)evt.getSource()).isSelected()){
        	changeAllStatesServiceEnabled();        	
        	m_checkService.setText("Orden de servicio "+getCurrentDate());
        }
        else{
        	changeAllStatesServiceDisabled();
        	m_checkService.setText("Orden de servicio");
        }
    }
    
    private void checkFactActionPerformed (ActionEvent evt){
        if (((JCheckBox)evt.getSource()).isSelected()){
        	m_btnReenviarOFacturacion.setEnabled(true);
        	m_checkFact.setText("Orden de facturación "+getCurrentDate());
        }
        else {
        	m_checkFacturado.setEnabled(false);
        	m_checkFacturado.setSelected(false);
        	m_btnReenviarOFacturacion.setEnabled(false);
        	m_checkFacturado.setText("Facturado");
        	m_checkFact.setText("Orden de facturación                       ");
        }
    }
    
    private void checkFacturadoActionPerformed (ActionEvent evt){
        if (((JCheckBox)evt.getSource()).isSelected()){
        	m_checkFacturado.setText("Facturado "+getCurrentDate());
        }
        else{
        	m_checkFacturado.setText("Facturado");
        }
    }
    
    private void checkCobradoActionPerformed (ActionEvent evt){
        if (((JCheckBox)evt.getSource()).isSelected()){
        	m_checkCobrado.setText("Cobrado "+this.getCurrentDate());
        }
        else {
        	m_checkCobrado.setText("Cobrado");
        }
    }
    
    
    private void checkConfirmActionPerformed (ActionEvent evt){
        if (((JCheckBox)evt.getSource()).isSelected()){
        	changeAllStatesConfirmEnabled();
        	m_checkConfirm.setText("Confirmado "+ this.getCurrentDate());
        }
        else {
        	changeAllStatesConfirmDisabled();
        	m_checkConfirm.setText("Confirmado");
        }
    }
    
    private void checkCancelActionPerformed (ActionEvent evt){
        if (((JCheckBox)evt.getSource()).isSelected()){
            allDisabledExceptCancel();
            m_checkCancel.setText("Cancelado "+getCurrentDate());
        }
        else {
            allEnabledExceptCancel();
            if (presupuesto.getEstadoActual() != null && presupuesto.getEstadoActual().getConfirmado() == 1){
            	m_checkConfirm.setEnabled(false);
            	if (presupuesto.getEstadoActual() != null && presupuesto.getEstadoActual().getOs() == 1){
            		m_checkService.setEnabled(false); 
            		changeAllStatesServiceEnabled();
            		if (presupuesto.getEstadoActual() != null && presupuesto.getEstadoActual().getOf() == 1){
            			m_checkFact.setEnabled(false);
            			if (isPresupFacturado(presupuesto))
            				m_checkFacturado.setEnabled(false);
            		}
            	}
            }
            if (isPresupOCompra(presupuesto))
				m_checkOCompra.setEnabled(false);
            m_checkCancel.setText("Cancelado");
        }
        
    }
    
    private void allEnabledExceptRech(){    
    	changeAllStatesStateNew();
    }
    
    private void allDisabledExceptRech(){
    	 m_checkConfirm.setEnabled(false);
         m_checkCancel.setEnabled(false);
         m_checkService.setEnabled(false);
         m_checkFact.setEnabled(false);
         m_checkFacturado.setEnabled(false);
         m_checkOCompra.setEnabled(false);
    }
    
    private void allEnabledExceptCancel(){  
    	m_checkConfirm.setEnabled(true);
    	changeAllStatesConfirmEnabled();
    }
    
    private void allDisabledExceptCancel(){
    	m_checkConfirm.setEnabled(false);
        m_checkRech.setEnabled(false);
        m_checkService.setEnabled(false);
        m_checkFact.setEnabled(false);
        m_checkFacturado.setEnabled(false);
        m_checkOCompra.setEnabled(false);
    }
    
    private void changeAllStatesConfirmEnabled(){
    	m_checkCancel.setEnabled(true);
    	m_checkRech.setEnabled(false);
        m_checkService.setEnabled(true);
        if(panelFacturacion != null)
        	if(Double.parseDouble(panelFacturacion.getAdelantoValor()) != 0d && presupuesto.getEstadoActual() == null)
        		panelFacturacion.setCheckAdelantoEnabled(true);
        m_checkFact.setEnabled(false);
        m_checkFacturado.setEnabled(false);        
        m_checkOCompra.setEnabled(true);
    }
    
    private void changeAllStatesConfirmDisabled(){
    	m_checkCancel.setEnabled(false);
    	m_checkRech.setEnabled(true);
        m_checkService.setEnabled(false);
        m_checkFact.setEnabled(false);
        m_checkFacturado.setEnabled(false);
        m_checkOCompra.setEnabled(false);
        m_checkService.setSelected(false);
        m_checkFact.setSelected(false);
        m_btnReenviarOFacturacion.setEnabled(false);
        m_checkOCompra.setSelected(false);
        m_btnReenviarOFAdelanto.setEnabled(false);

        m_checkOCompra.setText("O/C");

        m_checkFact.setText("Orden de facturación                       ");

        m_checkFacturado.setText("Facturado");

        m_checkService.setText("Orden de servicio");
        if(panelFacturacion != null)
        	if(Double.parseDouble(panelFacturacion.getAdelantoValor()) != 0d)
        		panelFacturacion.setCheckAdelantoEnabled(false);
        
    }
    
    private void changeAllStatesServiceEnabled(){
    	if(panelFacturacion != null){
    		if(Double.parseDouble(panelFacturacion.getAdelantoValor()) == 0d || panelFacturacion.getAdelantado() == 1)
    			m_checkFact.setEnabled(true);
    	}
    	else if(presupuesto.getEstadoActual() != null){
    		if(presupuesto.getEstadoActual().getAdelanto()==0 || presupuesto.getEstadoActual().getAdelantado() ==1)
    			m_checkFact.setEnabled(true);
    	}
    	else
    		m_checkFact.setEnabled(true);
    	
    }
    
    private void changeAllStatesServiceDisabled(){
    	changeAllStatesConfirmEnabled();    	
    	m_checkFact.setSelected(false);
    	m_btnReenviarOFacturacion.setEnabled(false);
        m_checkFacturado.setSelected(false);
        m_checkFact.setText("Orden de facturación                       ");
        m_checkFacturado.setText("Facturado");
    }
    
    private void changeAllStatesStateNew(){
    	m_checkConfirm.setEnabled(true);
    	m_checkCancel.setEnabled(false);
    	m_checkRech.setEnabled(true);
        m_checkService.setEnabled(false);
        m_checkFact.setEnabled(false);
        m_checkFacturado.setEnabled(false);
        m_checkOCompra.setEnabled(false);
    }    
    
    private String CreateLinePresupNumber(Presupuesto p){
    	String resp = "";
    	if (p != null && p.getNumeroDePresupuesto()>0){
    		resp += "Estado del presupuesto n° " ;
    		resp += String.valueOf(p.getNumeroDePresupuesto());
    		numeroPpto = p.getNumeroDePresupuesto();
    	}
    	else {
    		resp += "Presupuesto Nuevo";
    		numeroPpto=0;
    	}
    	return resp;
    
    }
    
    
    
    /*
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
    
	public String getCurrentDate() {
	  	  
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
	    
	    String DATE_FORMAT = "yyyy-MM-dd";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    
	    sdf.setTimeZone(TimeZone.getDefault());          
	          
	   return sdf.format(cal.getTime());
    	    
    }
	
	public String getCurrentDateTime() {
	  	  
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
	    
	    String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    
	    sdf.setTimeZone(TimeZone.getDefault());          
	          
	   return sdf.format(cal.getTime());
    	    
    }
	
	public void checkSaveRoles(){
        //si el presupuesto existe, verifico q se pueda editar segun la UC
        if(presupuesto != null && presupuesto.getNumeroDePresupuesto() > 0){
        	try {
				btRecord.setEnabled(manager.canEdit(presupuesto.getNumeroDePresupuesto(),getCodigoUsuario()));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
        }else{
        	btRecord.setEnabled(true);
        }
	}
	
	public void checkUserAdmins(){
		boolean isCold = UserRolesUtil.isCold(getUsuario());	
		if(isCold){
			this.m_checkState.setEnabled(!isCold);	
			this.m_checkConfirm.setEnabled(!isCold);		    
			this.m_checkCancel.setEnabled(!isCold);		    
			this.m_checkRech.setEnabled(!isCold);		   
			this.m_checkService.setEnabled(!isCold);		    
			this.m_checkFact.setEnabled(!isCold);		    
			this.m_checkFacturado.setEnabled(!isCold);		    
			this.m_checkCobrado.setEnabled(!isCold);		    
			this.m_checkOCompra.setEnabled(!isCold);		    
			this.m_btnReenviarOFAdelanto.setEnabled(!isCold);		    
			this.m_btnReenviarOServicio.setEnabled(!isCold);		    
			this.m_btnReenviarOFacturacion.setEnabled(!isCold);	
			this.m_txtFromDate.setEnabled(!isCold);	
			this.m_txtToDate.setEnabled(!isCold);	
			//this.btReset.setVisible(!isCold);
			this.m_lblDays.setEnabled(!isCold);
			//this.m_lblRooms.setEnabled(!isCold);
			this.m_lblWeek.setEnabled(!isCold);
			this.nroPpto.setText("Estado del presupuesto nº " + String.valueOf(presupuesto.getNumeroDePresupuesto()));
			//jLabel3.setVisible(!isCold);
			jLabel9.setVisible(!isCold);
		}
	}
	
	public int getRoomsCount() {
		return Integer.valueOf(m_lblRooms.getText());
	}

	public void setRoomsCount(int rooms) {
		m_lblRooms.setText(String.valueOf(rooms));
	}
	
	public void resetNroPptoYTotalEvt() {
		nroPpto.setText(this.CreateLinePresupNumber(presupuesto));
	}
	
	public boolean isConfirmado(){
		return m_checkConfirm.isSelected();
	}
	
	public void setTotalEvento(double total){
		totalEvento += total;
		jLabel9.setText(" Total del evento "+getTotalFormateado(totalEvento));
	}
	
	public double getTotalEvento(){
		return totalEvento;
	}
	
	private void agregarClienteNuevo(String codCliente)throws RemoteException{
    	if(codCliente != null){
    		NuevoClienteManager clienteManager=NuevoClienteManager.instance();
    		if(clienteManager.isNuevoContacto(codCliente) && 
    				manager.getCantPresupuestosByClientes(Long.parseLong(codCliente)) > 0){
    			NuevoCliente n = clienteManager.getNuevoClienteById(codCliente);
    			n.setNuevo("N");
    			clienteManager.update(n);
    		}
    	}
    }
	
	private void enviarOF(){
		try{
			
			
			if(presupuesto.getEstadoActual().getOf()==1){
				if(presupuesto.getFacturacion().getCodUnidadAdm() == null){
					PantallaEmergenteOF p = new PantallaEmergenteOF(Main.getVentana(),true);
					p.setVisible(true);
					if(p.getDestinatarioElegido() != null){
						PresupuestosManager.instance().setUnidadAdministrativaByNroPpto(presupuesto.getNumeroDePresupuesto().toString(), p.getDestinatarioElegido().getCodigo());
						OrdenFacturacionReport.instance().savePdfFile(presupuesto.getNumeroDePresupuesto());
						if(OrdenFacturacionReport.instance().sendOFByEmail2(presupuesto.getNumeroDePresupuesto(), getCodigoUsuario(),p.getDestinatarioElegido().getEmail())){
							Util.alertMsg(Main.getVentana(),"Se envió con éxito la orden de facturación nro "+presupuesto.getNumeroDePresupuesto());
						}
					}
				}
				else{
					String email = UnidadAdministrativaManager.instance().getUnidadComercialById(presupuesto.getFacturacion().getCodUnidadAdm()).getEmail();
					OrdenFacturacionReport.instance().savePdfFile(presupuesto.getNumeroDePresupuesto());											
					if(OrdenFacturacionReport.instance().sendOFByEmail2(presupuesto.getNumeroDePresupuesto(), getCodigoUsuario(),email)){
						Util.alertMsg(Main.getVentana(),"Se envió con éxito la orden de facturación nro "+presupuesto.getNumeroDePresupuesto());
					}
				}											
				
			}
			else{
				Util.errMsg(Main.getVentana(),"Primero debe grabar el presupuesto para reenviar la Orden", null);
			}

		} catch (Exception e) {
    		Util.errMsg(Main.getVentana(),"Se ha producido un error al enviar orden de facturacion",e);
    	}
	}
	
	private void enviarOFAdelanto(){
		try{
			if(presupuesto.getEstadoActual().getAdelanto()==1){
				if(presupuesto.getFacturacion().getCodUnidadAdm() == null){
					PantallaEmergenteOF p = new PantallaEmergenteOF(Main.getVentana(),true);
					p.setVisible(true);
					if(p.getDestinatarioElegido() != null){
						PresupuestosManager.instance().setUnidadAdministrativaByNroPpto(presupuesto.getNumeroDePresupuesto().toString(), p.getDestinatarioElegido().getCodigo());
						AdelantoReport.instance().savePdfFile(presupuesto.getNumeroDePresupuesto());
						if(AdelantoReport.instance().sendOFByEmail2(presupuesto.getNumeroDePresupuesto(), getCodigoUsuario(),p.getDestinatarioElegido().getEmail())){
							Util.alertMsg(Main.getVentana(),"Se envió con éxito la orden de facturación de Adelanto nro "+presupuesto.getNumeroDePresupuesto());
						}
					}
				}
				else{
					String email = UnidadAdministrativaManager.instance().getUnidadComercialById(presupuesto.getFacturacion().getCodUnidadAdm()).getEmail();
					AdelantoReport.instance().savePdfFile(presupuesto.getNumeroDePresupuesto());											
					if(AdelantoReport.instance().sendOFByEmail2(presupuesto.getNumeroDePresupuesto(), getCodigoUsuario(),email)){
						Util.alertMsg(Main.getVentana(),"Se envió con éxito la orden de facturación de Adelanto nro "+presupuesto.getNumeroDePresupuesto());
					}
				}											
				
			}
			else{
				Util.errMsg(Main.getVentana(),"Primero debe grabar el presupuesto para reenviar la Orden", null);
			}

		} catch (Exception e) {
			Util.errMsg(Main.getVentana(),"Se ha producido un error al enviar orden de facturación del adelanto",e);
		}
	}
	
	private void enviarOS(String codLugar){
    	try{	 	
    		if(m_checkService.isSelected()){
    			PantallaEmergenteOS p = new PantallaEmergenteOS(Main.getVentana(),true);
    			p.setVisible(true);
    			if(p.getDestinatarioElegido() != null){
    				OrdenServicioReport.instance().savePdfFile(presupuesto.getNumeroDePresupuesto());    			
    				if(OrdenServicioReport.instance().sendOSByEmail2(presupuesto.getNumeroDePresupuesto(), convertDateFormat(presupuesto.getFechaDeInicio()), convertDateFormat(presupuesto.getFechaDeFinalizacion()), 
    						presupuesto.getNombreDelEvento(), this.getCodigoUsuario(), codLugar,p.getDestinatarioElegido().getEmail())){
    				
    					Util.alertMsg(Main.getVentana(),"Se envió con éxito la orden de servicio nro "+presupuesto.getNumeroDePresupuesto());
    				}
    				else Util.errMsg(Main.getVentana(),"Se ha producido un error al enviar la orden de servicio", null);
    			}
    		}
   	 		
    	} catch (Exception e) {
    		Util.errMsg(Main.getVentana(),"Se ha producido un error al enviar la orden de servicio",e);
    	}
    }
    
    private void createSeguimiento() throws RemoteException{
    	/*if (presupuesto != null && presupuesto.getNumeroDePresupuesto()>0){
   	 		TxSeguimiento tx = new TxSeguimiento();
        	pantallaEmrgSeguimiento= new PantallaEmergenteSeguimiento(Main.getVentana());
        	pantallaEmrgSeguimiento.setVisible(true);
        	tx.setCodigoSeguimiento(pantallaEmrgSeguimiento.getCodigoAccion());
        	tx.setCodigoSeguimientoRespuesta(pantallaEmrgSeguimiento.getCodigoResultado());
        	tx.setFechaYHora(pantallaEmrgSeguimiento.getFecha());
        	tx.setCodigoUsuario(this.getCodigoUsuario());
        	tx.setObservaciones(pantallaEmrgSeguimiento.getObservaciones());
        	tx.setNumeroPresupuesto(String.valueOf(presupuesto.getNumeroDePresupuesto()));
        	TxSeguimientoManager.instance().update(tx);
        	if(panelSeguimiento!=null)
        		panelSeguimiento.setPresupuesto(presupuesto);        		
   	 	}               	 	
        else {
        	pantallaEmrgSeguimiento = null;                    	
        }*/    
    	if (presupuesto != null && presupuesto.getNumeroDePresupuesto()>0){
   	 		
   	 		
   	 		pantallaEmrgSeguimiento = new VentanaSeguimientoCreate(MainPanelComercial.this);
   	 		pantallaEmrgSeguimiento.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
   	 		pantallaEmrgSeguimiento.setVisible(true);
   	 		
   	 		if(pantallaEmrgSeguimiento.isSeGraba()){	   	 		
	   	 		HashSet<String> res = pantallaEmrgSeguimiento.getResultados();
	   	 		Iterator<String> it = res.iterator();
	   	 		while(it.hasNext()){
	   	 		TxSeguimiento tx = new TxSeguimiento();
	   	 		
	        	tx.setCodigoSeguimiento("8");
	        	tx.setCodigoSeguimientoRespuesta(it.next());
	        	tx.setFechaYHora(getCurrentDateTime());
	        	tx.setCodigoUsuario(this.getCodigoUsuario());
	        	tx.setObservaciones(pantallaEmrgSeguimiento.getObservaciones());
	        	tx.setNumeroPresupuesto(String.valueOf(presupuesto.getNumeroDePresupuesto()));
	        	TxSeguimientoManager.instance().update(tx);
	   	 		}
   	 		}
        	if(panelSeguimiento!=null)
        		panelSeguimiento.setPresupuesto(presupuesto);        		
   	 	}               	 	
        else {
        	pantallaEmrgSeguimiento = null;                    	
        }
    }
    
    public void refreshTotalEvento(){    	
    	nroPpto.setText(CreateLinePresupNumber(presupuesto));
    }
    
    public String getFechaInicio(){
    	String s= DateConverter.convertDateToString(m_txtFromDate.getDate(), "yyyy-MM-dd");
        return s+ " 00:00:00";
    }
    
    public String getFechaFinalizacion(){
    	String s= DateConverter.convertDateToString(m_txtToDate.getDate(), "yyyy-MM-dd");
    	return s+ " 23:59:59";
    }
    
    public Date getFechaInicio2(){
    	return m_txtFromDate.getDate();
    }
    
    public Date getFechaFinalizacion2(){
    	return m_txtToDate.getDate();
    }
        
    /*public PantallaEmergenteSeguimiento getPantallaEmrgSeguimiento() {
		return pantallaEmrgSeguimiento;
	}

	public void setPantallaEmrgSeguimiento(
			PantallaEmergenteSeguimiento pantallaEmrgSeguimiento) {
		this.pantallaEmrgSeguimiento = pantallaEmrgSeguimiento;
	}*/
    
	private ErrorList validateRequiredFieldsOfMain(){
    	ErrorList errors = new ErrorList();
			if (m_txtFromDate.getDate().after(m_txtToDate.getDate())){
				errors.addError("Fecha de inicio del evento mayor a la fecha de finalización");
			}
			if(presupuesto.getEstadoActual() != null)
			if(presupuesto.getEstadoActual().getConfirmado()==0 && m_checkConfirm.isSelected() && this.getUsuario().getCodigo().equals("5") && m_txtFromDate.getDate().before(new Date())){
				errors.addError("Fecha de inicio del evento mayor a la fecha actual");
			}
		
		return errors;
    }
	
	/**
     * Valida que todos los campos se hayan cargado
     */
    public ErrorList validateRequiredFields() {
        ErrorList errors = new ErrorList();
        
        if(panelCliente != null)
        	errors.addErrors(panelCliente.validateRequiredFields());
        else if (panelCliente == null && presupuesto.isNew())
        	errors.addError("No se completaron datos del Cliente");
        if(panelEvento != null)
        	errors.addErrors(panelEvento.validateRequiredFields());
        else if (panelEvento == null && presupuesto.isNew())
        	errors.addError("No se completaron datos del Evento");
        if(panelLugarEvento != null)
        	errors.addErrors(panelLugarEvento.validateRequiredFields());
        else if (m_checkConfirm.isSelected() && presupuesto.getContactoLugar().getCodContacto().equals("0")){
        	errors.addError("Verifique que este seleccionado un contacto del lugar de evento");
        }
        if(panelFacturacion != null){
        	if(m_checkFact.isSelected() || panelFacturacion.getAdelanto() == 1)
        		errors.addErrors(panelFacturacion.validateRequiredFields());
        	}
        else if (panelFacturacion == null && (m_checkFact.isSelected())){ 
        	if(presupuesto.getPago()!= null){
        		if(presupuesto.getPago().getCodCondicionPago()== null || presupuesto.getPago().getCodMedioPago()== null)
        			errors.addError("Verifique que los datos de medio y forma de pago esten seleccionados");
        	}else errors.addError("Verifique que los datos de facturacion");
        }
        
        errors.addErrors(this.validateRequiredFieldsOfMain());
        
        if(panelesSala.size()==0){
        	errors.addError("No se seleccionaron salas en el evento");
        }
        else{
        	
        Iterator<SalaPanel> it = panelesSala.iterator();
        while (it.hasNext()){
        	SalaPanel sala = it.next();
        	errors.addErrors(sala.validateRequiredFields());
        }
        
        }
        
        return errors;
    }
    
    /**TODO esto es provisorio
     */
    public String getCodigoReferencia(){
    	return "1";
    }
    
    public String convertDateFormat(String date){
    	Date d=null;
		try {
			d = DateConverter.convertStringToDate(date, "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return DateConverter.convertDateToString(d, "dd-MM-yyyy");
    }
    
    public int getMaxOrden(List<SalaServiciosTableModel> rows){
		int max=0;
		Iterator<SalaServiciosTableModel> it = rows.iterator();
		while(it.hasNext()){
			String nroOrden = it.next().getNroOrden() ;
			if((nroOrden != null) && (Integer.parseInt(nroOrden) > max)){
				max = Integer.parseInt(nroOrden);
			}
		}
		return max;
	}
    
    public void setBtnReenviarOFAdelantoEnabled(boolean b){
    	m_btnReenviarOFAdelanto.setEnabled(b);
    }
    
    private void setPantallasToNull(){
    	panelCliente=null;
		panelEvento=null;
		panelLugarEvento=null;
		panelGastosOperador=null;
		panelGastosAsistentes=null;
		panelGastosHoteleria=null;
		panelGastosViaticos=null;
		panelFacturacion=null;
		panelSeguimiento=null;
		panelRentabilidad=null;
		panelReportes=null;	
		
		totalEvento = 0d;	
		
		jTree1.setSelectionRow(0);
		
		removeAllSalas();
		
		panelVista.removeAll();	
		panelVista.updateUI();
		
		presupuesto = Presupuesto.createDefault();
    }
    
    private String getMIp(){
		/*InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();

			System.out.println("Current IP address : " + ip.getHostAddress());
			
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
				
			byte[] mac = network.getHardwareAddress();
			
			System.out.print("Current MAC address : ");
		
			return ip.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}*/
    	return null;
	}
	
	private String getMac(){
		/*InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
				
			byte[] mac = network.getHardwareAddress();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
			}
			
			return sb.toString();
		} catch (UnknownHostException e) {

			e.printStackTrace();
			return null;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}*/
		return null;
	}
    
    //*****************************************ACCIONES****************************************

	private class GrabarPresupuestoAction implements ActionListener, Observer {
		
		private ThreadGrabar t;
		
		public void actionPerformed(ActionEvent evt) {		
			
			if (MessageUtil.showYesNoMessage(Main.getVentana(),"¿Seguro que desea grabar el presupuesto?","Grabar")){
				
				if(panelRentabilidad == null){
					panelRentabilidad = new RentabilidadPanel(panelVista);
					panelRentabilidad.init();
					panelRentabilidad.setMainPanel(MainPanelComercial.this);
					panelRentabilidad.setPresupuesto(presupuesto);
				}			
				panelRentabilidad.calculateData();
				
				final PresupuestoBuilder builder = new PresupuestoBuilder(MainPanelComercial.this, presupuesto);
				builder.run();
				
				if (builder.isValid()){      
					btRecord.setEnabled(false);
					final ProgressDialogUtil2 p = ProgressDialogUtil2.instance();
					p.setType(ProgressDialogUtil2.LOAD_PPTO_TYPE);
					p.launchProcessDialog(Main.getVentana());
					p.setCancelVisible(true);
					p.addObserver(this);
														
					t = new ThreadGrabar(p);
					t.setBuilder(builder);
					t.start();
				}            
			}      
		}

		public void update(Observable arg0, Object arg1) {
			t.stop();
			btRecord.setEnabled(true);
		}
        
    }
	
	private class ThreadGrabar extends Thread{
		
		private PresupuestoBuilder builder;
		private ProgressDialogUtil2 progress;
		
		public ThreadGrabar(ProgressDialogUtil2 p){
			progress = p;
		}

		public void setBuilder(PresupuestoBuilder builder) {
			this.builder = builder;
		}

		private boolean fechaActualMenorCargada(SalaHelper[] salas){
			boolean  esMenor = true;

			Calendar cal = Calendar.getInstance();
			Calendar calSala = Calendar.getInstance();	
	        cal.setTime(new Date());
	        
			for(SalaHelper sh: salas){
				Date fechaSala;
				try {
					fechaSala = DateConverter.convertStringToDate(sh.getFechaDeInicio(), "yyyy-MM-dd hh:mm");
					calSala.setTime(fechaSala);
					if(calSala.before(cal)){
						esMenor =false;
						break;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}				        
		        
			}
			return esMenor;
		}
		
		public void run() {			
			PresupuestoHelper presupHelper = builder.getPresupuesto();
			try {                	
				
				long num_presupuesto = 0;
				
				agregarClienteNuevo(presupHelper.getCodigoCliente());
				if (presupHelper.isNew() && presupuesto.getNumeroDePresupuesto()==0){  
					if(fechaActualMenorCargada(presupHelper.getSalas()) || getUsuario().getPerfil().equals("3")){
						progress.setMensaje("Grabando nuevo presupuesto...");
						num_presupuesto = manager.nuevoPresupuesto(presupHelper);
						Util.alertMsg(progress.getProcessDialog(),"Su Presupuesto se creo con el N° " + num_presupuesto);
					}
					else{
						Util.errMsg(progress.getProcessDialog(), "No se puede crear un presupuesto con fecha de sala anterior a la actual. Revise las salas.", null);
					}
				}
				
				else{ 
					progress.setMensaje("Actualizando el presupuesto...");
					num_presupuesto = manager.actualizarPresupuesto(presupHelper);
					progress.setMensaje("Creando Seguimiento del presupuesto...");
					Util.alertMsg(progress.getProcessDialog(),"El Presupuesto ha sido actualizado correctamente");
					createSeguimiento();
				}
				if(num_presupuesto > 0){
					progress.setMensaje("Actualizando formularios del CRM...");
					presupuesto = manager.buscarPresupuesto(num_presupuesto);				
					setStatesPresupuesto(presupuesto);
					
					if(m_checkService.isSelected()){
						if(MessageUtil.showYesNoMessage(Main.getVentana(),"¿Desea enviar la Orden de Servicio?","Envio de OS")){
							progress.setMensaje("Enviando Orden de Servicio...");
							enviarOS(presupHelper.getCodigoLugarDelEvento());
						}
					}
					
					if(m_checkFact.isSelected()){
						if(MessageUtil.showYesNoMessage(Main.getVentana(),"¿Desea enviar la Orden de Facturación?","Envio de OF")){
							progress.setMensaje("Enviando Orden de Facturación...");
							enviarOF();
						}
					}
					
					if(panelFacturacion != null){
						if(panelFacturacion.getAdelanto()==1){
							if(MessageUtil.showYesNoMessage(Main.getVentana(),"¿Desea enviar la Orden de Facturación de Adelanto?","Envio de OFA")){
								progress.setMensaje("Enviando Orden de Facturación del Adelanto...");
								enviarOFAdelanto();
							}
						}
					}
					else{
						if(presupuesto.getEstadoActual().getAdelanto()==1){
							if(MessageUtil.showYesNoMessage(Main.getVentana(),"¿Desea enviar la Orden de Facturación de Adelanto?","Envio de OFA")){
								progress.setMensaje("Enviando Orden de Facturación del Adelanto...");
								enviarOFAdelanto();
							}
						}
					}
					
					refreshTotalEvento();
				}
				
				progress.closeProcessDialog();
				btRecord.setEnabled(true);
				
			}catch (Exception e) {
				Util.errMsg(progress.getProcessDialog(),"Se ha producido un error al grabar el Presupuesto",e);
				progress.closeProcessDialog();
				btRecord.setEnabled(true);
			}			
			finally{
				try{
					if(presupuesto!=null)
						manager.modificarActivo(presupuesto.getNumeroDePresupuesto(), "S", Integer.parseInt(getUsuario().getCodigo()),getMIp(),getMac());
				}catch(RemoteException e){
					Util.errMsg(Main.getVentana(), "El presupuesto quedará abierto por error de aplicación", e);
				}
			}
		}
		
	}
	
	private class SalirSinGrabarAction implements ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent evt) {        	
			if (MessageUtil.showYesNoMessage(Main.getVentana(),"¿Seguro que desea salir de la aplicación?","Salir")){
				        						
					if(UserRolesUtil.isVendedor(getUsuario()) || UserRolesUtil.isSupervisor(getUsuario())){

								SwingUtilities.invokeLater(new Runnable(){
									public void run(){
										Main.getVentana().getGlassPane().start();
										Thread performer = new Thread(new Runnable(){
											public void run(){
												try{
													manager.modificarActivo(presupuesto.getNumeroDePresupuesto(), "N", Integer.parseInt(getUsuario().getCodigo()),getMIp(),getMac());
												}catch(RemoteException e){
													Util.errMsg(Main.getVentana(), "El presupuesto quedará abierto por error de aplicación", e);
												}
												finally{	
													PantallaBienvenidaComerciales p = (PantallaBienvenidaComerciales)getPantallaBienvenida();
													
													cambiarPantallaA(p);									
													p.completePantalla();		
													setPantallaBienvenida(p);													
													Main.getVentana().getGlassPane().stop();
												}
												
											}
										},"cerrando ppto");
										performer.start();
									}
								});

					}
					else if(UserRolesUtil.isAdmin(getUsuario())){
						cambiarPantallaA(new PantallaBienvenidaAdmin());
						Main.getVentana().setExtendedState(JFrame.MAXIMIZED_BOTH);
					}
					else if(UserRolesUtil.isCold(getUsuario())){
						try{
							manager.modificarActivo(presupuesto.getNumeroDePresupuesto(), "N", Integer.parseInt(getUsuario().getCodigo()),getMIp(),getMac());
						}catch(RemoteException e){
							Util.errMsg(Main.getVentana(), "El presupuesto quedará abierto por error de aplicación", e);
						}
						finally{
							PantallaBienvenidaCold p = new PantallaBienvenidaCold();				
							p.initComponents();
							p.createMenuBAr();
							p.createLayout();
							cambiarPantallaA(p);		
							setPantallaBienvenida(p);
							Main.getVentana().setExtendedState(JFrame.MAXIMIZED_BOTH);
						}
					}     
					else if(UserRolesUtil.isGerenciaComercial(getUsuario())){
						try{
							manager.modificarActivo(presupuesto.getNumeroDePresupuesto(), "N", Integer.parseInt(getUsuario().getCodigo()),getMIp(),getMac());
						}catch(RemoteException e){
							Util.errMsg(Main.getVentana(), "El presupuesto quedará abierto por error de aplicación", e);
						}
						finally{
							PantallaBienvenidaGerencia p = (PantallaBienvenidaGerencia)getPantallaBienvenida();
							cambiarPantallaA(p);
							p.updateUI();
						}
					}
					else if(UserRolesUtil.isFacturacionUser(getUsuario())){
						try{
							manager.modificarActivo(presupuesto.getNumeroDePresupuesto(), "N", Integer.parseInt(getUsuario().getCodigo()),getMIp(),getMac());
						}catch(RemoteException e){
							Util.errMsg(Main.getVentana(), "El presupuesto quedará abierto por error de aplicación", e);
						}
						finally{
							PantallaBienvenidaFacturacion p = (PantallaBienvenidaFacturacion)getPantallaBienvenida();
							cambiarPantallaA(p);
							p.updateUI();		
						}
					}
					else if(UserRolesUtil.isCobranzasUser(getUsuario())){
						try{
							manager.modificarActivo(presupuesto.getNumeroDePresupuesto(), "N", Integer.parseInt(getUsuario().getCodigo()),getMIp(),getMac());
						}catch(RemoteException e){
							Util.errMsg(Main.getVentana(), "El presupuesto quedará abierto por error de aplicación", e);
						}
						finally{
							PantallaBienvenidaCobranzas p = (PantallaBienvenidaCobranzas)getPantallaBienvenida();
							cambiarPantallaA(p);
							p.updateUI();	
						}
					}
					else if(UserRolesUtil.isGerenciaAdm(getUsuario())){
						try{
							manager.modificarActivo(presupuesto.getNumeroDePresupuesto(), "N", Integer.parseInt(getUsuario().getCodigo()),getMIp(),getMac());
						}catch(RemoteException e){
							Util.errMsg(Main.getVentana(), "El presupuesto quedará abierto por error de aplicación", e);
						}
						finally{
							PantallaBienvenidaAdministracion p = (PantallaBienvenidaAdministracion)getPantallaBienvenida();
							cambiarPantallaA(p);
							p.updateUI();	
						}
					}
				
			}

		}		
		
	}

    private class FechaDesdeActionListener implements ActionListener{
    	
    	public void actionPerformed(ActionEvent arg0) {
    		int semana = DateDiff.getWeek(m_txtFromDate.getDate());
    		m_lblWeek.setText(String.valueOf(semana));
    		DateDiff dateDiff = DateDiff.calcularFechas(m_txtFromDate.getDate(), m_txtToDate.getDate());
    		m_lblDays.setText(String.valueOf(dateDiff.getDayOnly()+1));
    		if (MessageUtil.showYesNoMessage(Main.getVentana(),"¿Desea cambiar la fecha de instalacion y la fecha de inicio en las salas tambien? ","Atención")){
    			Iterator<SalaPanel> it = panelesSala.iterator();
    			while(it.hasNext()){
    				SalaPanel salaPanel = it.next();
    				salaPanel.getModel().setFechaDeInicio(getFechaInicio().substring(0,10));
    				salaPanel.getModel().setFechaPrueba(getFechaInicio().substring(0,10));
    				ArrayList<Date> dias = DateDiff.getdiasEntreFechas(m_txtFromDate.getDate(), m_txtToDate.getDate());
    				if(salaPanel.getModel().getHorarios().size()>dias.size()){
    					removeHorario(salaPanel.getModel(), dias);
    				}
    				else if(salaPanel.getModel().getHorarios().size()<dias.size()){
    					addHorario(salaPanel.getModel(), dias);
    				}
    			}
    			if(panelEvento != null){
    				panelEvento.setFechaInstalacion(getFechaInicio().substring(0,10));
    			}
    		}
    	}
    	
    }
    
    private void removeHorario(SalaModel model, ArrayList<Date> dias){
		List<HorariosItem> horarios = model.getHorarios();
		boolean encontre = false;
		List<HorariosItem> horariosABorrar = new ArrayList<HorariosItem>();
		for(HorariosItem item :  horarios){
			for(Date dia: dias){
				String diaString = DateConverter.convertDateToString(dia, "yyyy-MM-dd");
				if(item.getFecha().equals(diaString)){
					encontre = true;
					break;
				}
				else{						
					encontre = false;
				}
			}
			if(!encontre){
				horariosABorrar.add(item);					
			}
		}
		for(HorariosItem borrar : horariosABorrar){
			model.getHorarios().remove(borrar);
		}
	}
	//TODO: terminar agregar renglon en caso que se agreguen dia
	private void addHorario(SalaModel model, ArrayList<Date> dias){
		List<HorariosItem> horarios = model.getHorarios();
		boolean encontre = false;
		String d="";
		for(Date dia: dias){
			String diaString = DateConverter.convertDateToString(dia, "yyyy-MM-dd");
			for(HorariosItem item :  horarios){				
				
				if(item.getFecha().equals(diaString)){
					encontre = true;
					break;
				}
				else{ 
					encontre = false;
					d=diaString;
				}
			}
			if(!encontre){
				HorariosItem it = new HorariosItem();
				it.setFecha(diaString);
				model.getHorarios().add(it);
				
			}
		}
	}
    
    private class FechaHastaItemListener implements ActionListener{   	
    	
    	public void actionPerformed(ActionEvent arg0) {
    		DateDiff dateDiff = DateDiff.calcularFechas(m_txtFromDate.getDate(), m_txtToDate.getDate());
    		m_lblDays.setText(String.valueOf(dateDiff.getDayOnly()+1));	
    		if (MessageUtil.showYesNoMessage(Main.getVentana(),"¿Desea cambiar la fecha de finalización en las salas tambien?","Atención")){
    			Iterator<SalaPanel> it = panelesSala.iterator();
    			while(it.hasNext()){
    				SalaPanel salaPanel = it.next();
    				salaPanel.getModel().setFechaDeFinalizacion(getFechaFinalizacion().substring(0,10));
    				salaPanel.getModel().setFechaDesarme(getFechaFinalizacion().substring(0,10));
    				ArrayList<Date> dias = DateDiff.getdiasEntreFechas(m_txtFromDate.getDate(), m_txtToDate.getDate());
    				if(salaPanel.getModel().getHorarios().size()>dias.size()){
    					removeHorario(salaPanel.getModel(), dias);
    				}
    				else if(salaPanel.getModel().getHorarios().size()<dias.size()){
    					addHorario(salaPanel.getModel(), dias);
    				}
    			}
			}
		}
    	
    }

	/**
	 * @return Returns the m_checkFact.
	 */
	public javax.swing.JCheckBox getM_checkFact() {
		return m_checkFact;
	}

	/**
	 * @param fact The m_checkFact to set.
	 */
	public void setM_checkFact(javax.swing.JCheckBox fact) {
		m_checkFact = fact;
	}
	
	private class ReenviarOFListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(m_checkFact.isSelected()){				
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						Main.getVentana().getGlassPane().start();
						Thread performer = new Thread(new Runnable(){
							public void run(){
								try {
									if(presupuesto.getEstadoActual().getOf()==1){
										if(presupuesto.getFacturacion().getCodUnidadAdm() == null){
											PantallaEmergenteOF p = new PantallaEmergenteOF(Main.getVentana(),true);
											p.setVisible(true);
											if(p.getDestinatarioElegido() != null){
												PresupuestosManager.instance().setUnidadAdministrativaByNroPpto(presupuesto.getNumeroDePresupuesto().toString(), p.getDestinatarioElegido().getCodigo());
												OrdenFacturacionReport.instance().savePdfFile(presupuesto.getNumeroDePresupuesto());
												if(OrdenFacturacionReport.instance().sendOFByEmail2(presupuesto.getNumeroDePresupuesto(), getCodigoUsuario(),p.getDestinatarioElegido().getEmail())){
													Util.alertMsg(Main.getVentana(),"Se envió con éxito la orden de facturación nro "+presupuesto.getNumeroDePresupuesto());
												}
											}
										}
										else{
											String email = UnidadAdministrativaManager.instance().getUnidadComercialById(presupuesto.getFacturacion().getCodUnidadAdm()).getEmail();
											OrdenFacturacionReport.instance().savePdfFile(presupuesto.getNumeroDePresupuesto());											
											if(OrdenFacturacionReport.instance().sendOFByEmail2(presupuesto.getNumeroDePresupuesto(), getCodigoUsuario(),email)){
												Util.alertMsg(Main.getVentana(),"Se envió con éxito la orden de facturación nro "+presupuesto.getNumeroDePresupuesto());
											}
										}								
									}
									else{
										Util.errMsg(Main.getVentana(),"Primero debe grabar el presupuesto para reenviar la Orden", null);
									}
								} catch (RemoteException e) {
									Util.errMsg(Main.getVentana(),"Se ha producido un error al enviar el reporte",e);
								}
								Main.getVentana().getGlassPane().stop();
							}
						},"reenviando OF");
						performer.start();
					}
				});
			}
			else
				Util.errMsg(Main.getVentana(),"Primero debe seleccionar 'Orden de Facturacion'", null);
		}
		
	}
	
	private class ReenviarOSListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {			
			if(m_checkService.isSelected()){
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						Main.getVentana().getGlassPane().start();
						Thread performer = new Thread(new Runnable(){
							public void run(){
								try {
									if(presupuesto.getEstadoActual().getOs()==1){
										PantallaEmergenteOS p = new PantallaEmergenteOS(Main.getVentana(),true);
										p.setVisible(true);
										if(p.getDestinatarioElegido() != null){
											InformeOSReport.instance().createInformeOS(presupuesto.getNumeroDePresupuesto());
											OrdenServicioReport.instance().savePdfFile(presupuesto.getNumeroDePresupuesto());											
											//if(OrdenServicioReport.instance().sendOSByEmail2(presupuesto.getNumeroDePresupuesto(), convertDateFormat(presupuesto.getFechaDeInicio()), convertDateFormat(presupuesto.getFechaDeFinalizacion()), 
												//	presupuesto.getNombreDelEvento(),getCodigoUsuario(), presupuesto.getLugarDelEvento().getCodigo(),p.getDestinatarioElegido().getEmail()))
											if(OrdenServicioReport.instance().sendOSByEmail(presupuesto.getNumeroDePresupuesto(), convertDateFormat(presupuesto.getFechaDeInicio()), convertDateFormat(presupuesto.getFechaDeFinalizacion()), 
						    						presupuesto.getNombreDelEvento(), getCodigoUsuario(), presupuesto.getLugarDelEvento().getCodigo()))	
												Util.alertMsg(Main.getVentana(),"Se reenvió con éxito la orden de servicio nro "+presupuesto.getNumeroDePresupuesto());
											else Util.alertMsg(Main.getVentana(),"Se ha producido un error al enviar la orden de servicio");
										}
									}
									else{
										Util.errMsg(Main.getVentana(),"Primero debe grabar el presupuesto para reenviar la Orden", null);
									}
									Main.getVentana().getGlassPane().stop();
								} catch (Exception e) {
									Util.errMsg(Main.getVentana(),"Se ha producido un error al guardar la orden de servicio",e);
									Main.getVentana().getGlassPane().stop();
								}
								
							}
						},"reenviando OS");
						performer.start();
					}
				});			
			}
			else
				Util.errMsg(Main.getVentana(),"Primero debe seleccionar 'Orden de Servicio'", null);
		}
		
	}
	
	private class ReenviarOFAdelantoListener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			if(panelFacturacion.getAdelanto() == 1){				

				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						Main.getVentana().getGlassPane().start();
						Thread performer = new Thread(new Runnable(){
							public void run(){
								try {
									if(presupuesto.getEstadoActual().getAdelanto() == 1){
										AdelantoReport.instance().savePdfFile(presupuesto.getNumeroDePresupuesto());
										AdelantoReport.instance().sendOFByEmail(presupuesto.getNumeroDePresupuesto(), getCodigoUsuario());
										Util.alertMsg(Main.getVentana(),"Se reenvió con éxito la orden de facturación del adelanto nro "+presupuesto.getNumeroDePresupuesto());
									}
									else if(presupuesto.getEstadoActual().getAdelanto() != 1){
										Util.errMsg(Main.getVentana(),"Primero debe grabar el presupuesto para reenviar la orden de facturación del adelanto", null);
									}
								} catch (Exception e) {
									Util.errMsg(Main.getVentana(),"Se ha producido un error al enviar orden de facturación del adelanto",e);
								}
								Main.getVentana().getGlassPane().stop();
							}
						},"REenvio OF adelanto");
						performer.start();
					}
				});
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

	/**
	 * @return Returns the presupuesto.
	 */
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	/**
	 * @return Returns the numeroPpto.
	 */
	public long getNumeroPpto() {
		return numeroPpto;
	}

	/**
	 * @param numeroPpto The numeroPpto to set.
	 */
	public void setNumeroPpto(long numeroPpto) {
		this.numeroPpto = numeroPpto;
	}

	public DefaultMutableTreeNode getSalasTree() {
		return salasTree;
	}
	
	

}
