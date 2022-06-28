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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TimeZone;

import javax.swing.JCheckBox;
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
import crm.client.helper.GastosBuilder;
import crm.client.helper.PresupuestoBuilder;
import crm.client.helper.PresupuestoBuilderXML;
import crm.client.helper.UserRolesUtil;
import crm.client.managers.NuevoClienteManager;
import crm.client.managers.PresupuestosManager;
import crm.client.managers.TxSeguimientoManager;
import crm.client.managers.UnidadAdministrativaManager;
import crm.client.managers.VendedorManager;
import crm.client.report.AdelantoReport;
import crm.client.report.OrdenFacturacionReport;
import crm.client.report.OrdenServicioReport;
import crm.client.serializer.SerializerManager;
import crm.client.util.DateConverter;
import crm.client.util.DateDiff;
import crm.client.util.ProgressDialogUtil2;
import crm.client.util.Util;
import crm.client.validacion.ErrorList;
import crm.gui.Main;
import crm.gui.components.JXDatePicker;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.PantallaBienvenidaAdmin;
import crm.gui.pantalla.PantallaBienvenidaCobranzas;
import crm.gui.pantalla.PantallaBienvenidaCold;
import crm.gui.pantalla.PantallaBienvenidaComerciales;
import crm.gui.pantalla.PantallaBienvenidaFacturacion;
import crm.gui.pantalla.PantallaBienvenidaGastos;
import crm.gui.pantalla.PantallaBienvenidaGerencia;
import crm.gui.pantalla.PantallaEmergenteOF;
import crm.gui.pantalla.PantallaEmergenteOS;
import crm.gui.pantalla.PantallaEmergenteSeguimiento;
import crm.gui.pantalla.solapa.gastos.GastosAsistentes;
import crm.gui.pantalla.solapa.gastos.GastosHoteleria;
import crm.gui.pantalla.solapa.gastos.GastosOperadores;
import crm.gui.pantalla.solapa.gastos.GastosRepresentacion;
import crm.gui.pantalla.solapa.gastos.GastosVarios;
import crm.gui.pantalla.solapa.gastos.GastosViaticos;
import crm.gui.tablerenderer.salas.SalaServicioItem;
import crm.gui.tablerenderer.salas.SalaServiciosTableModel;
import crm.libraries.abm.entities.NuevoCliente;
import crm.libraries.abm.entities.Ppto_Sala;
import crm.libraries.abm.entities.Ppto_Sala_Servicio;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.TxSeguimiento;
import crm.libraries.abm.helper.PresupuestoHelper;
import crm.libraries.util.MessageUtil;
import crm.services.sei.EstadoEventoManagerSEI;

public class MainPanelGastos extends PantallaComercialAbstracta {
	
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
	//private javax.swing.JButton m_btnReenviarOFAdelanto;
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
	
	private GastosOperadores panelGastosOperador;
	private GastosAsistentes panelGastosAsistentes;
	//private GastosSubcontratacionServiciosSalas panelGastosSubcontratacionesSalas;
	//private GastosSubcontratacionesGrales panelGastosSubcontratacionesGrales;
	private SubcontratadosPanel panelGastosSubcontratacionesGrales;
	private GastosRepresentacion panelGastosRepresentacion;
	private GastosHoteleria panelGastosHoteleria;
	private GastosViaticos panelGastosViaticos;
	private GastosVarios panelGastosVarios;
	private RentabilidadGastosPanel panelRentabilidad;
	
	private PantallaEmergenteSeguimiento pantallaEmrgSeguimiento;
	
	private DefaultMutableTreeNode top;
	private DefaultMutableTreeNode salasTree;
	private DefaultTreeModel treeModel;
	
	private Presupuesto presupuesto;
	private double totalEvento;    
	
	private JPopupMenu popup;
	
	private long numeroPpto;
	
	private Timer timer;
	
	public MainPanelGastos(){
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

        partePanel = new DefaultMutableTreeNode("Rentabilidad");
        if(!UserRolesUtil.isCold(getUsuario())){
        	panelMain.add(partePanel);
        }

        panelGastos = new DefaultMutableTreeNode("Comisiones");
        top.add(panelGastos);
        
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
        
        partePanel = new DefaultMutableTreeNode("Varios");
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
        //m_btnReenviarOFAdelanto = new javax.swing.JButton();
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

        /*m_btnReenviarOFAdelanto.setText("Reenviar O/F Adelanto");
        m_btnReenviarOFAdelanto.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(m_btnReenviarOFAdelanto, gridBagConstraints);*/

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
        btRecord.setText("Grabar presupuesto");
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
		     
		logo.setIcon(new javax.swing.ImageIcon(getUrlImagen("CRMcongress.png")));
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
                                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 768, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
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
		
		panelGastosSubcontratacionesGrales = new SubcontratadosPanel(panelVista);		
		panelGastosSubcontratacionesGrales.init();
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
	
	//*********************************ACCIONES************************************************
	private class MyTreeSelectionListener implements TreeSelectionListener{

		public void valueChanged(TreeSelectionEvent e) {		
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)
			(e.getPath().getLastPathComponent());
				
			final Object nodeInfo = node.getUserObject();
			
			if (node.isLeaf()) {

				if(((String)nodeInfo).equals("De operadores")){
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
				else if(((String)nodeInfo).equals("Varios")){
					/*if(panelGastosVarios == null){
						panelGastosVarios = new GastosVarios(panelVista);
						panelGastosVarios.init();
						panelGastosVarios.setPresupuesto(presupuesto);
					}*/
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

				else if(((String)nodeInfo).equals("Rentabilidad")){
					if(panelRentabilidad == null){
						panelRentabilidad = new RentabilidadGastosPanel(panelVista);
						panelRentabilidad.init();
						panelRentabilidad.setMainPanel(MainPanelGastos.this);
						panelRentabilidad.setPresupuesto(presupuesto);
					}
					jTree1.setEditable(false);
					panelVista.removeAll();
					panelRentabilidad.initLayout();				
					panelRentabilidad.calculateData();
					panelVista.updateUI();
				}
				else if(((String)nodeInfo).equals("De subcontrataciones en salas")){				
					jTree1.setEditable(false);
					panelVista.removeAll();					
					panelGastosSubcontratacionesGrales.initLayout();					
					panelVista.updateUI();
				}			

			} 
			else {
				jTree1.setEditable(false);
				/**TODO: agregar un cartel del tipo seleccione...*/
			}			
		}
		
	}
	
	/*public GastosSubcontratacionServiciosSalas getPanelGastosSubcontSalas(){
		return panelGastosSubcontratacionesSalas;
	}*/
	
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

	public JPanel getPanelPpal() {
		return panelPpal;
	}

	public RentabilidadGastosPanel getPanelRentabilidad() {
		return panelRentabilidad;
	}

	private void createListeners(){
		jTree1.addTreeSelectionListener(new MyTreeSelectionListener());
    	btExit.addActionListener(new SalirSinGrabarAction());
    	btRecord.addActionListener(new GrabarPresupuestoAction());

    	m_btnReenviarOFacturacion.addActionListener(new ReenviarOFListener());
    	m_btnReenviarOServicio.addActionListener(new ReenviarOSListener());
    	//m_btnReenviarOFAdelanto.addActionListener(new ReenviarOFAdelantoListener());

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
    		//m_lblCobradoDate.setText("                   ");
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
    		//m_lblConfirmDate.setText("                   ");
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
    		//m_lblCancelDate.setText("                   ");
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
    		//m_lblRechDate.setText("                   ");
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
    		//m_lblOCompraDate.setText("                   ");
    		m_checkOCompra.setText("O/C");
    		//m_btnReenviarOFAdelanto.setEnabled(false);
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
    		//m_btnReenviarOServicio.setEnabled(false);
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
    
    private void calcularTotalPpto(Ppto_Sala sala){
    	Set <Ppto_Sala_Servicio>servicios = sala.getServicios();
    	if (servicios != null){
    	Iterator it = servicios.iterator();
    	while(it.hasNext()){
    		Ppto_Sala_Servicio serv = (Ppto_Sala_Servicio)it.next();  
    		if(serv.getModalidad().getCodigo().equals("1") || serv.getModalidad().getCodigo().equals("2"))
    			setTotalEvento(Double.parseDouble(serv.getPrecioDescuento()));
    	}
    	}
    }
    
    
    
    /** Carga los estados del presupuesto p en la aplicacion
     * @param p: presupuesto a cargar 
     */
    public void setPresupuesto(Presupuesto p){
    	long time = System.currentTimeMillis();    	
    	setPantallasToNull();
    	presupuesto = p; 
    	
    	if (presupuesto.getSalas() != null){			
			Object[] salas = presupuesto.getSalasArray();

			for(int i=0; i<salas.length; i++){				
				Ppto_Sala pptosala = (Ppto_Sala)salas[i];	
				setRoomsCount(getRoomsCount()+1);
				calcularTotalPpto(pptosala);
			}
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

			panelGastosSubcontratacionesGrales.setPresupuesto(presupuesto);
			panelGastosVarios.setPresupuesto(presupuesto);
			panelGastosRepresentacion.setPresupuesto(presupuesto);
			

		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Tiempo de query: "+ (System.currentTimeMillis()-time)/1000+" seg.");
    }
    
    private void checkRechActionPerformed(ActionEvent evt){
        if (((JCheckBox)evt.getSource()).isSelected()){
            allDisabledExceptRech();
            //m_lblRechDate.setText(this.getCurrentDate());
            m_checkRech.setText("Rechazado "+getCurrentDate());
        }
        else {
            allEnabledExceptRech();
            //m_lblRechDate.setText("                   ");
            m_checkRech.setText("Rechazado");
        }
    }
    
    private void checkServiceActionPerformed (ActionEvent evt){
        if (((JCheckBox)evt.getSource()).isSelected()){
        	changeAllStatesServiceEnabled();        	
        	//m_btnReenviarOServicio.setEnabled(true);

        	m_checkService.setText("Orden de servicio "+getCurrentDate());
        }
        else{
        	changeAllStatesServiceDisabled();
        	//m_btnReenviarOServicio.setEnabled(false);

        	m_checkService.setText("Orden de servicio");
        }
    }
    
    private void checkFactActionPerformed (ActionEvent evt){
        if (((JCheckBox)evt.getSource()).isSelected()){
        	m_btnReenviarOFacturacion.setEnabled(true);
            //m_lblFactDate.setText(this.getCurrentDate());
        	m_checkFact.setText("Orden de facturación "+getCurrentDate());
        }
        else {
        	m_checkFacturado.setEnabled(false);
        	m_checkFacturado.setSelected(false);
        	m_btnReenviarOFacturacion.setEnabled(false);
        	//m_lblFacturadoDate.setText("                   ");
        	m_checkFacturado.setText("Facturado");
        	//m_lblFactDate.setText("                   ");
        	m_checkFact.setText("Orden de facturación                       ");
        }
    }
    
    private void checkFacturadoActionPerformed (ActionEvent evt){
        if (((JCheckBox)evt.getSource()).isSelected()){
            //m_lblFacturadoDate.setText(this.getCurrentDate());
        	m_checkFacturado.setText("Facturado "+getCurrentDate());
        }
        else{
        	//m_lblFacturadoDate.setText("                   ");
        	m_checkFacturado.setText("Facturado");
        }
    }
    
    private void checkCobradoActionPerformed (ActionEvent evt){
        if (((JCheckBox)evt.getSource()).isSelected()){
            //m_lblCobradoDate.setText(this.getCurrentDate());
        	m_checkCobrado.setText("Cobrado "+this.getCurrentDate());
        }
        else {
        	//m_lblCobradoDate.setText("                   ");
        	m_checkCobrado.setText("Cobrado");
        }
    }
    
    
    private void checkConfirmActionPerformed (ActionEvent evt){
        if (((JCheckBox)evt.getSource()).isSelected()){
        	changeAllStatesConfirmEnabled();
            //m_lblConfirmDate.setText(this.getCurrentDate());
        	m_checkConfirm.setText("Confirmado "+ this.getCurrentDate());
        }
        else {
        	changeAllStatesConfirmDisabled();
        	//m_lblConfirmDate.setText("                   ");
        	m_checkConfirm.setText("Confirmado");
        }
    }
    
    private void checkCancelActionPerformed (ActionEvent evt){
        if (((JCheckBox)evt.getSource()).isSelected()){
            allDisabledExceptCancel();
           // m_lblCancelDate.setText(this.getCurrentDate());
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
           // m_lblCancelDate.setText("                   ");
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
       // m_btnReenviarOServicio.setEnabled(false);
        m_checkFact.setSelected(false);
        m_btnReenviarOFacturacion.setEnabled(false);
        m_checkOCompra.setSelected(false);
        //m_btnReenviarOFAdelanto.setEnabled(false);

        m_checkOCompra.setText("O/C");

        m_checkFact.setText("Orden de facturación                       ");

        m_checkFacturado.setText("Facturado");

        m_checkService.setText("Orden de servicio");
        
    }
    
    private void changeAllStatesServiceEnabled(){

    	if(presupuesto.getEstadoActual() != null){
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
        //m_lblFactDate.setText("                   ");
        m_checkFact.setText("Orden de facturación                       ");
        //m_lblFacturadoDate.setText("                   ");
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
	
	public void checkUserAdmins(){
		boolean isCold = UserRolesUtil.isGastosUser(getUsuario());	
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
			//this.m_btnReenviarOFAdelanto.setEnabled(!isCold);		    
			this.m_btnReenviarOServicio.setEnabled(!isCold);		    
			this.m_btnReenviarOFacturacion.setEnabled(!isCold);	
			this.m_txtFromDate.setEnabled(!isCold);	
			this.m_txtToDate.setEnabled(!isCold);	

			this.m_lblDays.setEnabled(!isCold);

			this.m_lblWeek.setEnabled(!isCold);
			this.nroPpto.setText("Estado del presupuesto nº " + String.valueOf(presupuesto.getNumeroDePresupuesto()));
			jLabel3.setVisible(!isCold);
			jLabel4.setVisible(!isCold);
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
        
    public PantallaEmergenteSeguimiento getPantallaEmrgSeguimiento() {
		return pantallaEmrgSeguimiento;
	}

	public void setPantallaEmrgSeguimiento(
			PantallaEmergenteSeguimiento pantallaEmrgSeguimiento) {
		this.pantallaEmrgSeguimiento = pantallaEmrgSeguimiento;
	}
    
	private ErrorList validateRequiredFieldsOfMain(){
    	ErrorList errors = new ErrorList();

			if (m_txtFromDate.getDate().after(m_txtToDate.getDate()))
				errors.addError("Fecha de inicio del evento mayor a la fecha de finalización");

		return errors;
    }
	
	/**
     * Valida que todos los campos se hayan cargado
     */
    public ErrorList validateRequiredFields() {
        ErrorList errors = new ErrorList();
        
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
    
    /*public void setBtnReenviarOFAdelantoEnabled(boolean b){
    	m_btnReenviarOFAdelanto.setEnabled(b);
    }*/
    
    private void setPantallasToNull(){

		panelGastosOperador=null;
		panelGastosAsistentes=null;

		panelGastosHoteleria=null;
		panelGastosViaticos=null;

		panelRentabilidad=null;
		
		totalEvento = 0d;	
		
		jTree1.setSelectionRow(0);
		
		panelVista.removeAll();	
		panelVista.updateUI();
		
		presupuesto = Presupuesto.createDefault();
    }
    
    private String getMIp(){
		InetAddress ip;
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
		}
	}
	
	private String getMac(){
		InetAddress ip;
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
		}
		
	}
    
    //*****************************************ACCIONES****************************************

	private class GrabarPresupuestoAction implements ActionListener, Observer {
		
		private ThreadGrabar t;
		
		public void actionPerformed(ActionEvent evt) {		
			
			if (MessageUtil.showYesNoMessage(Main.getVentana(),"¿Seguro que desea grabar el presupuesto?","Grabar")){
				
				if(panelRentabilidad == null){
					panelRentabilidad = new RentabilidadGastosPanel(panelVista);
					panelRentabilidad.init();
					panelRentabilidad.setMainPanel(MainPanelGastos.this);
					panelRentabilidad.setPresupuesto(presupuesto);
				}			
				panelRentabilidad.calculateData();
				
				final GastosBuilder builder = new GastosBuilder(MainPanelGastos.this, presupuesto);
				builder.run();
				
				if (builder.isValid()){      
					btRecord.setEnabled(false);
					//ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_PPTO_TYPE);
					//ProgressDialogUtil.setCancelVisible(true);
					//ProgressDialogUtil.launchProcessDialog(Main.getVentana());					
					
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
		
		private GastosBuilder builder;
		private ProgressDialogUtil2 progress;
		
		public ThreadGrabar(ProgressDialogUtil2 p){
			progress = p;
		}

		public void setBuilder(GastosBuilder builder) {
			this.builder = builder;
		}

		public void run() {			
			PresupuestoHelper presupHelper = builder.getPresupuesto();
			//Presupuesto presupuestoAnterior = null;
			//Cliente clienteAFacturar = null;
			
			//ClienteFacturacion clienteFacturacion = null;
			try {                	
				
				long num_presupuesto = 0;

	 
					progress.setMensaje("Actualizando el presupuesto...");
					//presupuestoAnterior = manager.buscarPresupuesto(presupHelper.getNumeroDePresupuesto());
					//clienteAFacturar = panelFacturacion.getClienteElegido();
					//clienteFacturacion = panelFacturacion.getClienteFactElegido();
					num_presupuesto = manager.actualizarPresupuesto(presupHelper);
					progress.setMensaje("Creando Seguimiento del presupuesto...");
					Util.alertMsg(progress.getProcessDialog(),"El Presupuesto ha sido actualizado correctamente");
					
				
				progress.setMensaje("Actualizando formularios del CRM...");
				presupuesto = manager.buscarPresupuesto(num_presupuesto);				
				setStatesPresupuesto(presupuesto);
				
				refreshTotalEvento();
				progress.closeProcessDialog();
				btRecord.setEnabled(true);
				
			}catch (Exception e) {
				Util.errMsg(progress.getProcessDialog(),"Se ha producido un error al grabar el Presupuesto",e);
				progress.closeProcessDialog();
				btRecord.setEnabled(true);
			}			
		}
		
	}
	
	private class SalirSinGrabarAction implements ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent evt) {        	
			if (MessageUtil.showYesNoMessage(Main.getVentana(),"¿Seguro que desea salir de la aplicación?","Salir")){
				        						
					if(UserRolesUtil.isGastosUser(getUsuario())){
						//ProgressDialogUtil.setType(ProgressDialogUtil.CLOSE_PPTO_TYPE);
						//ProgressDialogUtil.launchProcessDialog(Main.getVentana());
						//new Thread(new Runnable() {	
							//public void run() {
								
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
													PantallaBienvenidaGastos p = (PantallaBienvenidaGastos)getPantallaBienvenida();
													
													cambiarPantallaA(p);								
															
													setPantallaBienvenida(p);

													Main.getVentana().getGlassPane().stop();
												}
												
											}
										},"cerrando ppto");
										performer.start();
									}
								});
							//}
						//}).start();
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
							cambiarPantallaA(new PantallaBienvenidaCold());
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
				
			}
			//timer.stop();
		}		
		
	}

    private class FechaDesdeActionListener implements ActionListener{
    	
    	public void actionPerformed(ActionEvent arg0) {
    		
    	}
    	
    }
    
    private class FechaHastaItemListener implements ActionListener{

    	public void actionPerformed(ActionEvent arg0) {
    		
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
			}
		
	}
	
	private class ReenviarOSListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {			
			
		}
		
	}
	
	private class ReenviarOFAdelantoListener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
				 					
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


}
