package crm.gui.pantalla.solapa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.lowagie.tools.plugins.Txt2Pdf;
import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

import crm.client.helper.UserRolesUtil;
import crm.client.managers.FamiliaServManager;
import crm.client.managers.ServicioIdiomaManager;
import crm.client.managers.VistaFamiliaServicioIdiomaManager;
import crm.client.util.DateConverter;
import crm.client.util.DateDiff;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.client.validacion.ErrorList;
import crm.gui.Main;
import crm.gui.components.ABMTipoArmadoComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.components.GradientButton;
import crm.gui.components.HourPanel2;
import crm.gui.components.JXDatePicker;
import crm.gui.pantalla.PantallaAgregarServicio;
import crm.gui.pantalla.PantallaEmergenteObservSalas;
import crm.gui.pantalla.PantallaHorariosOperaciones;
import crm.gui.tablerenderer.gastos.GastosSubContratacionesSalasItem;
import crm.gui.tablerenderer.gastosSubcontratados.SubcontratadosTableModel;
import crm.gui.tablerenderer.horarios.HorariosItem;
import crm.gui.tablerenderer.salas.SalaServicioItem;
import crm.gui.tablerenderer.salas.SalaServiciosTableModel;
import crm.gui.tablerenderer.salas.TableRenderSalas;
import crm.libraries.abm.entities.Ppto_Sala;
import crm.libraries.abm.entities.Ppto_Sala_Horario;
import crm.libraries.abm.entities.Ppto_Sala_Servicio;
import crm.libraries.abm.entities.Ppto_Sala_Servicio_Desc_Detallada;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.Servicio;
import crm.libraries.util.MessageUtil;

public class SalaPanel extends PanelGeneral implements PanelInterface,Observer{
	private GradientButton moveUp;
	private GradientButton moveDown;
    private GradientButton borrar;
    private javax.swing.JLabel desarmeDay;
    private javax.swing.JLabel desarmeTime;
    private javax.swing.JLabel fromDate;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalSinDescuento;
    private ABMTipoArmadoComboBox m_cmbTipoArmado;
    private javax.swing.JFormattedTextField m_txtCantPersonasSala;
    private JXDatePicker m_txtFechaDesarme;
    private JXDatePicker m_txtFechaDesdeSala;
    private JXDatePicker m_txtFechaHastaSala;
    private JXDatePicker m_txtFechaPrueba;
    private HourPanel2 m_txtHoraDesarme;
    private HourPanel2 m_txtHoraDesdeSala;
    private HourPanel2 m_txtHoraHastaSala;
    private HourPanel2 m_txtHoraPrueba;
    private javax.swing.JLabel m_txtNombreSala;
    private GradientButton modificar;
    private javax.swing.JLabel name;
    private GradientButton nuevo;
    private javax.swing.JLabel personsRoom;
    private javax.swing.JLabel pruebaDay;
    private javax.swing.JLabel pruebaTime;
    private javax.swing.JLabel salaHastaTime;
    private javax.swing.JLabel salaTime;
    private javax.swing.JSpinner spin;
    private TableRenderSalas tableRoomsPanel;
    private javax.swing.JLabel tipoArmado;
    private javax.swing.JLabel toDate;
    private GradientButton m_btnObservacionSala;
    private JButton m_btnRecargar;
    
    private JPanel panel;
    private PantallaAgregarServicio pes;
    
    private String codigoLugarElegido;
    private double total = 0.0;
    private double totalSinDescuento = 0.0;
    private String nroOrden;
    
    private MainPanelComercial mainPanel;
	
    private SalaModel model;
    private SalaServiciosTableModel tablaModel;
    private GradientButton m_btnHorariosOperacion;
    
    private boolean isColdUser(){
		return UserRolesUtil.isCold(mainPanel.getUsuario());
	}
    
    public void setMainPanel(MainPanelComercial mainPanel) {
		this.mainPanel = mainPanel;
	}

	public SalaPanel(JPanel pan){
    	panel = pan;
    }
    
    public void init(){
    	name = new javax.swing.JLabel();
        m_txtNombreSala = new javax.swing.JLabel();
        nuevo = new GradientButton("", Color.blue);
        jSeparator1 = new javax.swing.JSeparator();
        fromDate = new javax.swing.JLabel();
        salaTime = new javax.swing.JLabel();
        toDate = new javax.swing.JLabel();
        salaHastaTime = new javax.swing.JLabel();
        pruebaDay = new javax.swing.JLabel();
        pruebaTime = new javax.swing.JLabel();
        desarmeDay = new javax.swing.JLabel();
        desarmeTime = new javax.swing.JLabel();
        personsRoom = new javax.swing.JLabel();
        m_txtCantPersonasSala = CustomTextField.getIntInstance();
        tipoArmado = new javax.swing.JLabel();
        m_cmbTipoArmado = new ABMTipoArmadoComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        tableRoomsPanel = new TableRenderSalas(mainPanel.getUsuario().getLimite_descuento().intValue());
        borrar = new GradientButton("", Color.blue);
        modificar = new GradientButton("", Color.blue);
        moveUp = new GradientButton("", Color.blue);
        moveDown = new GradientButton("", Color.blue);
        jLabel12 = new javax.swing.JLabel();
        lblTotalSinDescuento = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        m_btnHorariosOperacion = new GradientButton("", Color.blue);
        
        SpinnerNumberModel model3 = null;
        
        if(UserRolesUtil.isGerenciaComercial(mainPanel.getUsuario())){
        	model3 = new SpinnerNumberModel(0, -100, 200, 1);
        }
        else{
        	model3 = new SpinnerNumberModel(0, mainPanel.getUsuario().getLimite_descuento().intValue(), 200, 1);
        }
        spin = new javax.swing.JSpinner(model3);
        jLabel15 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        m_txtFechaDesdeSala = new JXDatePicker();
		     
        m_txtFechaPrueba = new JXDatePicker();
        
        m_txtFechaDesarme = new JXDatePicker();
        
        m_txtFechaHastaSala = new JXDatePicker();
        
        m_txtHoraDesdeSala = CustomTextField.getHours2Instance();
        m_txtHoraHastaSala = CustomTextField.getHours2Instance();
        m_txtHoraPrueba = CustomTextField.getHours2Instance();
        m_txtHoraDesarme = CustomTextField.getHours2Instance();
        m_btnObservacionSala = new GradientButton("", Color.blue);
        m_btnRecargar = new JButton();
        
        m_txtCantPersonasSala.setText("0");
        
        name.setFont(new java.awt.Font("Tahoma", 1, 13));
        name.setText("Nombre de la sala");       
        
        m_txtNombreSala.setFont(new java.awt.Font("Tahoma", 1, 13));
        m_txtNombreSala.setForeground(Color.red);
        
        nuevo.setIcon(new javax.swing.ImageIcon(getUrlImagen("add.png")));
        nuevo.setText("Agregar");

        fromDate.setFont(new java.awt.Font("Tahoma", 1, 11));
        fromDate.setText("Se utilizar\u00e1 desde");

        salaTime.setFont(new java.awt.Font("Tahoma", 1, 11));
        salaTime.setText("a las");

        toDate.setFont(new java.awt.Font("Tahoma", 1, 11));
        toDate.setText("hasta");

        salaHastaTime.setFont(new java.awt.Font("Tahoma", 1, 11));
        salaHastaTime.setText("a las");

        pruebaDay.setFont(new java.awt.Font("Tahoma", 1, 11));
        pruebaDay.setText("Fecha de Pruebas");

        pruebaTime.setFont(new java.awt.Font("Tahoma", 1, 11));
        pruebaTime.setText("a las");

        desarmeDay.setFont(new java.awt.Font("Tahoma", 1, 11));
        desarmeDay.setText("Fecha de Desarme");

        desarmeTime.setFont(new java.awt.Font("Tahoma", 1, 11));
        desarmeTime.setText("a las");

        personsRoom.setFont(new java.awt.Font("Tahoma", 1, 11));
        personsRoom.setText("Total de personas en la sala");

        tipoArmado.setFont(new java.awt.Font("Tahoma", 1, 11));
        tipoArmado.setText("Tipo de armado en la sala");       

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Servicios");

        borrar.setIcon(new javax.swing.ImageIcon(getUrlImagen("delete.png")));
        borrar.setText("Borrar");

        modificar.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        modificar.setText("Modificar");

        moveUp.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_up.png")));
        moveUp.setText("Mover Arriba");
        
        moveDown.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_down.png")));
        moveDown.setText("Mover Abajo");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Total s/descuento");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel14.setText("Descuento");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel15.setText("Total");
        
        m_btnObservacionSala.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_add.png")));
        m_btnObservacionSala.setText("Agregar observaciones");
        
        m_btnRecargar.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        
        tableRoomsPanel.getTable().setForeground(Color.BLACK);
        tableRoomsPanel.getTable().setSelectionBackground(new Color(243,235,212));
        
        
        m_btnHorariosOperacion.setIcon(new javax.swing.ImageIcon(getUrlImagen("clock_add.png")));
        m_btnHorariosOperacion.setText("Cargar Horarios");
        loadCombos();
        createListeners();
        model = new SalaModel();
        model.addObserver(this);
        
        tablaModel = getTableModel();
        tablaModel.setUserCold(isColdUser());
        tablaModel.addObserver(this);
        
        /*m_txtFechaDesdeSala.setDate(mainPanel.getFechaInicio2());  
        m_txtFechaPrueba.setDate(mainPanel.getFechaInicio2());
        m_txtFechaDesarme.setDate(mainPanel.getFechaFinalizacion2());
        m_txtFechaHastaSala.setDate(mainPanel.getFechaFinalizacion2());*/
    }
    
    public void initLayout(){
    	org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                        .add(tableRoomsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                        .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .add(name)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_txtNombreSala, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                            .add(91, 91, 91))
                        .add(layout.createSequentialGroup()
                            .add(jLabel12)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(lblTotalSinDescuento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 163, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(168, 168, 168)
                            .add(jLabel14)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(spin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(40, 40, 40)
                            .add(jLabel15)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(lblTotal, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                        .add(layout.createSequentialGroup()
                            .add(personsRoom)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_txtCantPersonasSala, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            //.add(196, 196, 196)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_btnHorariosOperacion)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)//
                            .add(tipoArmado)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_cmbTipoArmado, 0, 228, Short.MAX_VALUE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_btnRecargar))
                        .add(jLabel11)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(desarmeDay)
                                .add(fromDate)
                                .add(pruebaDay))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, m_txtFechaDesdeSala, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 204, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, m_txtFechaPrueba, 0, 204, Short.MAX_VALUE)
                                .add(m_txtFechaDesarme, 0, 204, Short.MAX_VALUE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(pruebaTime)
                                    .add(salaTime))
                                .add(desarmeTime))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(m_txtHoraPrueba, 0, 85, Short.MAX_VALUE)
                                .add(m_txtHoraDesarme, 0, 85, Short.MAX_VALUE)
                                .add(m_txtHoraDesdeSala, 0, 85, Short.MAX_VALUE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(toDate)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_txtFechaHastaSala, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 201, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(salaHastaTime)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_txtHoraHastaSala, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(20, 20, 20))
                        .add(layout.createSequentialGroup()
                            .add(nuevo)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(borrar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(modificar)
                            .add(58, 58, 58)
                            .add(moveUp)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(moveDown)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 65, Short.MAX_VALUE)
                            .add(m_btnObservacionSala)))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(name)
                        .add(m_txtNombreSala))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(fromDate)
                            .add(m_txtFechaDesdeSala, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(salaTime))
                        .add(org.jdesktop.layout.GroupLayout.LEADING, toDate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, m_txtHoraDesdeSala, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(m_txtHoraHastaSala, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(salaHastaTime)
                            .add(m_txtFechaHastaSala, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(pruebaDay)
                        .add(m_txtFechaPrueba, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(pruebaTime)
                        .add(m_txtHoraPrueba, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(8, 8, 8)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(desarmeDay)
                        .add(desarmeTime)
                        .add(m_txtHoraDesarme, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(m_txtFechaDesarme, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(12, 12, 12)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(personsRoom)
                        .add(m_txtCantPersonasSala, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(m_btnHorariosOperacion)
                        .add(tipoArmado)
                        .add(m_cmbTipoArmado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(m_btnRecargar))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel11)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(tableRoomsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(nuevo)
                        .add(borrar)
                        .add(modificar)
                        .add(m_btnObservacionSala)
                        .add(moveUp)
                        .add(moveDown))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel12)
                        .add(lblTotalSinDescuento)
                        .add(jLabel15)
                        .add(lblTotal)
                        .add(jLabel14)
                        .add(spin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
            );
      	
    }
    
    private void loadCombos(){
    	m_cmbTipoArmado.loadItems();
    }
    
    private void createListeners(){
    	nuevo.addActionListener(new NuevoServicioAction());
    	modificar.addActionListener(new ModificarServicioAction());
    	borrar.addActionListener(new BorrarServicioAction()); 
    	spin.addChangeListener(new SpinChangeAction());
    	m_btnObservacionSala.addActionListener(new ObservacionesActionListener());
    	moveUp.addActionListener(new UpServicioAction());
    	moveDown.addActionListener(new DownServicioAction());
    	m_txtCantPersonasSala.addKeyListener(new CantidadPersonasKeyListener());
    	
    	m_txtFechaDesdeSala.addActionListener(new FechaDesdeItemListener());
    	m_txtFechaHastaSala.addActionListener(new FechaHastaItemListener());
    	m_txtFechaDesarme.addActionListener(new FechaDesarmeItemListener());
    	m_txtFechaPrueba.addActionListener(new FechaPruebaItemListener());
    	
    	m_txtHoraHastaSala.addActionListener(new HoraFinalizacionActionListener());
    	m_txtHoraDesdeSala.addActionListener(new HoraInicioActionListener());
    	m_txtHoraDesarme.addActionListener(new HoraDesarmeActionListener());
    	m_txtHoraPrueba.addActionListener(new HoraPruebaActionListener());
    	
    	m_cmbTipoArmado.addActionListener(new TipoArmadoActionListener());
    	
    	tableRoomsPanel.getTable().addMouseListener(new TabalMouseListener());
    	m_btnRecargar.addActionListener(new RefreshActionListener());
    	
    	m_btnHorariosOperacion.addActionListener(new HorariosOperacionActionListener());
    	}
	
	private boolean isDescuentoPorSala(Object[] pptoservicios){
		String dto = null;
		boolean isPorSala = false;
		for(int i=0; i<pptoservicios.length; i++){
			Ppto_Sala_Servicio serv = (Ppto_Sala_Servicio)pptoservicios[i];									
			if(dto == null){
				dto= serv.getDescuento();
			}

				 if(serv.getDescuento().equals(dto)){
					 isPorSala= true;
				 }
				 else{
					 isPorSala = false;
					 break;
				 }
				
		}
		return isPorSala;
	}
	
	public void setPresupuesto(Ppto_Sala pptosala){		
		
		model.setTotalDePersonas(pptosala.getTotalDePersonas());
		
		model.setFechaDeInicio(pptosala.getFechaDeInicio().substring(0,10));
		model.setHoraDeInicio(pptosala.getFechaDeInicio().substring(11,16));
		
		model.setFechaDeFinalizacion(pptosala.getFechaDeFinalizacion().substring(0,10));		
		model.setHoraDeFinalizacion(pptosala.getFechaDeFinalizacion().substring(11,16));	

		model.setFechaPrueba(pptosala.getFechaPrueba().substring(0,10));
		model.setHoraPrueba(pptosala.getFechaPrueba().substring(11,16));
		
		model.setFechaDesarme(pptosala.getFechaDesarme().substring(0,10));
		model.setHoraDesarme(pptosala.getFechaDesarme().substring(11,16));
		
		if(pptosala.getTipoArmado() != null)
			model.setCodigoTipoArmado(pptosala.getTipoArmado().getCodigo());	

		model.setObservaciones(pptosala.getObservaciones());
		model.setCodigoSala(pptosala.getSala().getCodigoSala());
		model.setNombreSala(pptosala.getSala().getDescripcion());
		
		model.setNroOrden(pptosala.getOrden());	
		model.setNombreSalaUnica(pptosala.getNombreSalaUnico());
		//Carga de los horarios de operacion de sala
		Set<Ppto_Sala_Horario> pptoHorarios = pptosala.getHorario();
		ArrayList<HorariosItem> horarios = new ArrayList<HorariosItem>();
		Object[] array = pptoHorarios.toArray();
		if(array.length==0){
			ArrayList<Date> dias = DateDiff.getdiasEntreFechas(m_txtFechaDesdeSala.getDate(), m_txtFechaHastaSala.getDate());
			Iterator it = dias.iterator();
			while(it.hasNext()){
				HorariosItem item = new HorariosItem();
				item.setFecha(DateConverter.convertDateToString((Date)it.next(), "yyyy-MM-dd"));
				horarios.add(item);
			}
		}
		else{
		for(int i=0; i<array.length;i++){
			HorariosItem it = new HorariosItem();
			it.setFecha(((Ppto_Sala_Horario)array[i]).getFecha());
			it.setHoraDesde(((Ppto_Sala_Horario)array[i]).getHoraDesde());
			it.setHoraHasta(((Ppto_Sala_Horario)array[i]).getHoraHasta());
			horarios.add(it);
		}
		}
		model.setHorarios(horarios);
			
		
		//------------------------carga de servicios-----------------------------		
		
			if (pptosala.getServicios() != null) {
				Object[] pptoservicios = pptosala.getServiciosArray();
				model.setServicios(pptoservicios);

				if (isDescuentoPorSala(pptoservicios)) {
					model.setDescuento(Integer.parseInt(((Ppto_Sala_Servicio) pptoservicios[0]).getDescuento()));
				}
				long time = System.currentTimeMillis();

				ServicioIdiomaManager servicioManager = ServicioIdiomaManager.instance();
				FamiliaServManager familiaManager = FamiliaServManager.instance();

				for (int i = 0; i < pptoservicios.length; i++) {

					Ppto_Sala_Servicio serv = (Ppto_Sala_Servicio) pptoservicios[i];

					SalaServicioItem item = new SalaServicioItem();

					item.setCantidad(Integer.parseInt(serv.getCantidad()));
					item.setDias(Integer.parseInt(serv.getDias()));
					if (!isColdUser()) {
					item.setServicioCodigo(serv.getServicio().getCodigo());
					
						item.setTotal(Double.parseDouble(serv.getPrecioDescuento()));
						item.setDescuento(Integer.parseInt(serv.getDescuento()));
					}
					else item.setTotal(0d);
						
					item.setPrecioLista(Double.parseDouble(serv.getPrecioDeLista()));
					item.setNroOrden(serv.getOrden());
					item.setFechaAlta(serv.getFechaCreacion());

					if (serv.getDetalle() != null) {
						item.setServicio(serv.getDetalle());
						item.setFamilia("Subcontratado");
						item.setFamiliaCodigo("1");
					} else {						
						try {
							String s = servicioManager.getDescripcionServicio(serv.getServicio().getCodigo(), "1");
							item.setServicio(s);
							item.setFamiliaCodigo(serv.getServicio().getFamilia());
							item.setFamilia(familiaManager.getDescripcionByServicio(serv.getServicio().getFamilia()));
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
					}

					if (serv.getDescripcionDetallada() != null
							&& serv.getDescripcionDetallada().size() > 0) {
						List descripciones = new ArrayList();
						Set<Ppto_Sala_Servicio_Desc_Detallada> pptoDescDetalladas = serv.getDescripcionDetallada();
						for (Ppto_Sala_Servicio_Desc_Detallada desc : pptoDescDetalladas) {
							descripciones.add(desc.getDescripcion());
						}
						item.setDescDetallada(descripciones);
					}

					item.setSubContratado((serv.getModalidad().getCodigo().equals(Integer.toString(Servicio.MODALIDAD_CONTRATACION_EXTERNA))) || (serv.getModalidad().getCodigo().equals(Integer.toString(Servicio.MODALIDAD_CONTRATACION_EXTERNA_OPCIONAL))));
					item.setOpcional((serv.getModalidad().getCodigo().equals(Integer.toString(Servicio.MODALIDAD_CONTRATACION_INTERNA_OPCIONAL))) || (serv.getModalidad().getCodigo().equals(Integer.toString(Servicio.MODALIDAD_CONTRATACION_EXTERNA_OPCIONAL))));

					item.setEditingId(serv.getId());
					item.setCodigoLugar(pptosala.getSala().getCodigoLugar());
					item.setMesEvento(pptosala.getFechaDeInicio().substring(5,7));
					item.setFechaEvt(pptosala.getFechaDeInicio().substring(0,10));

					addServicioFromPpto(item, false);

				}
				System.out.println("TIEMPO QUERY TOTAL "+ (System.currentTimeMillis() - time) / 1000 + " seg.");

			}
		//}
		
	}

	public SalaServiciosTableModel getTableModel(){
		JTable table = tableRoomsPanel.getTable();
		SalaServiciosTableModel model = (SalaServiciosTableModel)table.getModel();
		return model;
	}
	
	/*Devuelve el nombre de la familia de servicios segun su codigo */
	private String getNombreFamilia(String codFamilia){
		try{
			return FamiliaServManager.instance().getDescripcionByServicio(codFamilia);
		}
		catch(Exception e){
			Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
			return null;
		}
	}
	
	/*Devuelve las descripciones del servicio y de la familia a la que pertenece*/
	private String[] getNombreServicio(String codServicio){
		try{			
			Object[] res = VistaFamiliaServicioIdiomaManager.instance().getDescripcionByServicio(codServicio);
			String[] servicioYFamilia = null;
			if(res != null){
				for(int i=0; i<res.length; i++){
					Object[] servicio = (Object[])res[i];
					servicioYFamilia = new String[servicio.length];
					servicioYFamilia[0] = (String)servicio[0];
					servicioYFamilia[1] = (String)servicio[1];
				}
				
				return servicioYFamilia; 
			}
			
		}
		catch(Exception e){
			Util.errMsg(Main.getVentana(),"Error al cargar datos externos", e);
		}
		return null;
	}
	
	private double redondear(double val){
		return Math.ceil(val/10)*10;
	}
	
	public void update(Observable o, Object arg) {
		if(o == model){
			if(model.getNombreSalaUnica() == null)
				m_txtNombreSala.setText(model.getNombreSala());
			else
				m_txtNombreSala.setText(model.getNombreSala() + " ("+model.getNombreSalaUnica()+")");
				
			m_txtCantPersonasSala.setText(model.getTotalDePersonas());
			try{
			Date d = DateConverter.convertStringToDate(model.getFechaDeInicio(), "yyyy-MM-dd");
			m_txtFechaDesdeSala.setDate(d);		
			d = DateConverter.convertStringToDate(model.getFechaDeFinalizacion(), "yyyy-MM-dd");
			m_txtFechaHastaSala.setDate(d);
			m_txtHoraDesdeSala.setHourWithDate(model.getHoraDeInicio());
			m_txtHoraHastaSala.setHourWithDate(model.getHoraDeFinalizacion());	

			d = DateConverter.convertStringToDate(model.getFechaPrueba(), "yyyy-MM-dd");
			m_txtFechaPrueba.setDate(d);
			d = DateConverter.convertStringToDate(model.getFechaDesarme(), "yyyy-MM-dd");
			m_txtFechaDesarme.setDate(d);
			m_txtHoraPrueba.setHourWithDate(model.getHoraPrueba());
			m_txtHoraDesarme.setHourWithDate(model.getHoraDesarme());
			m_cmbTipoArmado.setForeign(model.getCodigoTipoArmado());
			
			nroOrden = model.getNroOrden();
			
			lblTotal.setText(getTotalFormateado(model.getTotal()));
			lblTotalSinDescuento.setText(getTotalFormateado(model.getTotalSinDecuento()));
			
			}
			catch(Exception e){
				Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
			}
			spin.setValue(model.getDescuento());
			List<SalaServicioItem> servicios= tablaModel.getRows();

			for(int i=0; i<servicios.size(); i++){				
				SalaServicioItem serv = servicios.get(i);
				total += serv.getTotal()*-1;
				if(descuentoSala==true && !serv.isSubContratado()){
					serv.setDescuento(model.getDescuento());
				}
				serv.setTotal(serv.getPrecioLista()+(serv.getPrecioLista()*serv.getDescuento()/100));

				total += serv.getTotal();	
			}
			tableRoomsPanel.refreshTable();		
			
			
			
		}
		else{

			SalaServicioItem serv = tablaModel.getRow(tableRoomsPanel.getTable().getSelectedRow());
			if(!serv.isOpcional()){
				mainPanel.setTotalEvento(serv.getTotal()*-1);
				total += serv.getTotal()*-1;				
				serv.setTotal(serv.getPrecioLista()+(serv.getPrecioLista()*serv.getDescuento()/100));

				total += serv.getTotal();		
				descuentoSala=false;
				
				mainPanel.setTotalEvento(serv.getTotal());
				lblTotal.setText(getTotalFormateado(total));
				
			}
			tableRoomsPanel.refreshTable();	
		}
			
		
	}

	public SalaModel getModel() {
		return model;
	}

	public void setModel(SalaModel model) {
		this.model = model;
	}
	
	/**
	 * Redibuja la tabla 
	 */
	public void refreshTable() {
		tableRoomsPanel.refreshTable();
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

	/**
	 * Agrega un nuevo servicio a la lista de servicios de la sala seleccionada.
	 * @param item : item a agregar.
	 */
	public void addServicio(SalaServicioItem item, boolean toAdd){
		long time = System.currentTimeMillis();
		tablaModel.addRow(item);
		if(!item.isOpcional()){	
			item.setTotal(item.getPrecioLista()+(item.getPrecioLista()*item.getDescuento()/100));
			
			
			total += item.getTotal();			
			totalSinDescuento += item.getPrecioLista();
			if(toAdd)
				mainPanel.setTotalEvento(item.getTotal());
		}
		
		this.model.setTotal(total);
		this.model.setTotalSinDecuento(totalSinDescuento);
		lblTotal.setText(getTotalFormateado(model.getTotal()));
		lblTotalSinDescuento.setText(getTotalFormateado(model.getTotalSinDecuento()));
		refreshTable();			
		System.out.println("TIEMPO QUERY X SERVICIO: "+ (System.currentTimeMillis()-time)/1000+" seg.");
		if(item.isSubContratado()){
			GastosSubContratacionesSalasItem gasto = new GastosSubContratacionesSalasItem();
			gasto.setCantidad(item.getCantidad());
			gasto.setServicio(item.getFamilia() + " - " + item.getServicio());					
			gasto.setSala(model.getNombreSala());
			gasto.setPrecio(item.getTotal());			    				
			gasto.setNeto((gasto.getPrecio() - (gasto.getCantidad()  * gasto.getCosto())));
			gasto.setSalaServicioTableItemId(item.getTableItemId());
			((SubcontratadosPanel)mainPanel.getPanelGastosSubcontGral()).setItem(gasto);
			((SubcontratadosPanel)mainPanel.getPanelGastosSubcontGral()).save();
		}
	}
	
	/**
	 * Agrega un nuevo servicio a la lista de servicios de la sala seleccionada desde un presupuesto anterior
	 * @param item : item a agregar.
	 */
	public void addServicioFromPpto(SalaServicioItem item, boolean toAdd){
		long time = System.currentTimeMillis();
		tablaModel.addRow(item);
		if(!item.isOpcional()){	
			item.setTotal(item.getPrecioLista()+(item.getPrecioLista()*item.getDescuento()/100));
			
			
			total += item.getTotal();			
			totalSinDescuento += item.getPrecioLista();
			if(toAdd)
				mainPanel.setTotalEvento(item.getTotal());
		}
		
		this.model.setTotal(total);
		this.model.setTotalSinDecuento(totalSinDescuento);
		lblTotal.setText(getTotalFormateado(model.getTotal()));
		lblTotalSinDescuento.setText(getTotalFormateado(model.getTotalSinDecuento()));
		refreshTable();			
		System.out.println("TIEMPO QUERY X SERVICIO: "+ (System.currentTimeMillis()-time)/1000+" seg.");

	}
	
	/**
	 * Modifica un item de la grilla.
	 * @param item : item a modificar .
	 */
	public void modifyServicio(SalaServicioItem itemOriginal, SalaServicioItem item){

		if(!item.isOpcional()){	
			
		}
			
			/*total += (itemOriginal.getTotal()+(itemOriginal.getTotal()*(Integer)spin.getValue()/100))*-1;
			totalSinDescuento += (itemOriginal.getTotal())*-1;
			mainPanel.setTotalEvento((itemOriginal.getTotal()+(itemOriginal.getTotal()*(Integer)spin.getValue()/100))*-1);
						
			total += item.getTotal()+(item.getTotal()*(Integer)spin.getValue()/100);
			totalSinDescuento += item.getTotal();
			mainPanel.setTotalEvento(item.getTotal()+(item.getTotal()*(Integer)spin.getValue()/100));
		}
		
		lblTotalSinDescuento.setText(this.getTotalFormateado(totalSinDescuento));
		lblTotal.setText(this.getTotalFormateado(total));
		refreshTable();*/
		
		
		//((GastosSubcontratacionServiciosSalas)mainPanel.getPanelGastosSubcontSalas()).updateSalaServicio(model,item);
		
	}
	
	/**
	 * Elimina el servicio seleccionado de la sala actual
	 *
	 
	private void removeServicio(SalaServicioItem itemOriginal){
		JTable table = tableRoomsPanel.getTable();
		//SalaServiciosTableModel model = (SalaServiciosTableModel)table.getModel();
		SalaServicioItem item = tablaModel.getRow(table.getSelectedRow());
		
		//if(!itemOriginal.isOpcional()){			
			total += (item.getTotal())*-1;
			totalSinDescuento += (item.getPrecioLista())*-1;
			mainPanel.setTotalEvento((item.getTotal())*-1);
		
		tablaModel.removeRow(item);
		this.model.setTotal(total);
		this.model.setTotalSinDecuento(totalSinDescuento);	
		
		refreshTable();
		
		// lo borro de gastos		
		//((GastosSubcontratacionServiciosSalas)mainPanel.getPanelGastosSubcontSalas()).removeSalaServicio(item);	
					
	}	*/
	
	/**
	 * Elimina el servicio seleccionado de la sala actual
	 *
	 */
	private void removeSelectedServicio(){
		JTable table = tableRoomsPanel.getTable();
		//SalaServiciosTableModel model = (SalaServiciosTableModel)table.getModel();
		SalaServicioItem item = tablaModel.getRow(table.getSelectedRow());
		
		if(!item.isOpcional()){			
			total += (item.getTotal())*-1;
			totalSinDescuento += (item.getPrecioLista())*-1;
			mainPanel.setTotalEvento((item.getTotal())*-1);
		}
		
		tablaModel.removeRow(item);
		//lblTotalSinDescuento.setText(this.getTotalFormateado(totalSinDescuento));
		//lblTotal.setText(this.getTotalFormateado(total));
		this.model.setTotal(total);
		this.model.setTotalSinDecuento(totalSinDescuento);	
		lblTotal.setText(getTotalFormateado(model.getTotal()));
		lblTotalSinDescuento.setText(getTotalFormateado(model.getTotalSinDecuento()));
		
		refreshTable();
		
		// lo borro de gastos		
		//((GastosSubcontratacionServiciosSalas)mainPanel.getPanelGastosSubcontSalas()).removeSalaServicio(item);	
					
	}	
	
	/**
	 * Mueve el servicio seleccionado de la sala actual hacia abajo
	 *
	 */
	private void MoveDownSelectedServicio(){
		JTable table = tableRoomsPanel.getTable();
		//SalaServiciosTableModel model = (SalaServiciosTableModel)table.getModel();
		SalaServicioItem selectItem = tablaModel.getRow(table.getSelectedRow());		
		
		int itemPos = table.getSelectedRow();
		
		if(itemPos < tablaModel.getRowCount()-1){
			SalaServicioItem itemAux = tablaModel.getRow(table.getSelectedRow()+1);
			tablaModel.removeRow(selectItem);
			tablaModel.addRow(selectItem,itemPos+1);			
			
			String aux = itemAux.getNroOrden();
			itemAux.setNroOrden(selectItem.getNroOrden());
			selectItem.setNroOrden(aux);			
			
			table.setRowSelectionInterval(itemPos+1,itemPos+1);
		}
		else{
			Util.errMsg(Main.getVentana(), "No se puede mover mas abajo", null);
		}
		
		//lblTotalSinDescuento.setText(this.getTotalFormateado(model.getTotalSinDecuento()));
		//lblTotal.setText(this.getTotalFormateado(model.getTotal()));
		
		refreshTable();
		
		// lo borro de gastos		
		//((GastosSubcontratacionServiciosSalas)mainPanel.getPanelGastosSubcontSalas()).removeSalaServicio(selectItem);	
					
	}
	
	/**
	 * Mueve el servicio seleccionado de la sala actual hacia arriba
	 *
	 */
	private void MoveUpSelectedServicio(){
		JTable table = tableRoomsPanel.getTable();
		//SalaServiciosTableModel model = (SalaServiciosTableModel)table.getModel();
		SalaServicioItem selectItem = tablaModel.getRow(table.getSelectedRow());		
		
		int itemPos = table.getSelectedRow();
		
		if(itemPos >0){
			SalaServicioItem itemAux = tablaModel.getRow(table.getSelectedRow()-1);
			tablaModel.removeRow(selectItem);
			tablaModel.addRow(selectItem,itemPos-1);			
			
			String aux = itemAux.getNroOrden();
			itemAux.setNroOrden(selectItem.getNroOrden());
			selectItem.setNroOrden(aux);			
			
			table.setRowSelectionInterval(itemPos-1,itemPos-1);
		}
		else{
			Util.errMsg(Main.getVentana(), "No se puede mover mas arriba", null);
		}
		
		//lblTotalSinDescuento.setText(this.getTotalFormateado(model.getTotalSinDecuento()));
		//lblTotal.setText(this.getTotalFormateado(model.getTotal()));
		
		refreshTable();
		
		// lo borro de gastos		
		//((GastosSubcontratacionServiciosSalas)mainPanel.getPanelGastosSubcontSalas()).removeSalaServicio(selectItem);	
					
	}
	
	/**
	 * Obtiene el servicio seleccionado o null si no hay nada seleccionado.
	 * 
	 * @return
	 */
	public SalaServicioItem getSelectedServicio(){
		JTable table = tableRoomsPanel.getTable();
		//SalaServiciosTableModel model = (SalaServiciosTableModel)table.getModel();
		
		if (table.getSelectedRow()>=0){
			SalaServicioItem item = tablaModel.getRow(table.getSelectedRow());
			return item;
		}
		else{
			Util.alertMsg(Main.getVentana(), "Seleccione un item de la tabla de servicios");
			return null;
		}
	}
	
	public String getNroOrden() {
		return nroOrden;
	}

	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
	}
	
	private void setAllLabelBlack(){
		personsRoom.setForeground(Color.BLACK);
		jLabel11.setForeground(Color.BLACK);
		tipoArmado.setForeground(Color.BLACK);
		fromDate.setForeground(Color.BLACK);
		toDate.setForeground(Color.BLACK);
		pruebaDay.setForeground(Color.BLACK);
		desarmeDay.setForeground(Color.BLACK);
    }
	
public ErrorList validateRequiredFields(){
		
		setAllLabelBlack();
		
		ErrorList errors = new ErrorList();

		//SalaServiciosTableModel sala = getTableModel();
			if (tablaModel.getRowCount() == 0){
				errors.addError("Ingrese al menos un servicio para la sala " + model.getNombreSala());
				jLabel11.setForeground(Color.RED);
			}
			if (mainPanel.isConfirmado()){
				if (m_txtCantPersonasSala.getText().equals("0")){
					errors.addError("Indique la cantidad de personas que acudirán a la sala " + model.getNombreSala());
					personsRoom.setForeground(Color.red);
				}
				if (StringUtils.isBlank(m_txtCantPersonasSala.getText())){
					errors.addError("Indique la cantidad de personas que acudirán a la sala " + model.getNombreSala());
					personsRoom.setForeground(Color.red);
				}

					if (m_cmbTipoArmado.getSelectedIndex() == 0){
						errors.addError("Indique el tipo de armado de la sala " + model.getNombreSala());
						tipoArmado.setForeground(Color.RED);
					}						
					if (m_txtFechaDesdeSala.getDate().equals("2006-01-01")){
						errors.addError("Indique la fecha desde de la sala " + model.getNombreSala());
						fromDate.setForeground(Color.RED);
					}
					if (m_txtFechaHastaSala.getDate().equals("2006-01-01")){
						errors.addError("Indique la fecha hasta de la sala " + model.getNombreSala());
						toDate.setForeground(Color.RED);
					}
					
				
			}
			
			errors.addErrors(validateRequiredServiciosFields(tablaModel));
		
		return errors;
	}
	
	public ErrorList validateRequiredServiciosFields(SalaServiciosTableModel sala){
		ErrorList errors = new ErrorList();
		
		List<SalaServicioItem> servicios = sala.getRows();
		int row = 0;
		for (SalaServicioItem servicio : servicios) {
			row++;
			
			if (servicio.getCantidad() <= 0)
				errors.addError("Indique la cantidad para el Servicio "+servicio.getServicio()+" en la Sala "+model.getNombreSala());
			
			if (!NumberUtils.isDigits(servicio.getServicioCodigo()))
				errors.addError("Indique el Servicio para la fila N°"+row+" en la Sala "+model.getNombreSala());
			
			if (servicio.getDias() <= 0)
				errors.addError("Indique la cantidad de Dias para el Servicio "+servicio.getServicio()+" en la Sala "+model.getNombreSala());
		}
		
		return errors;
	}
	
	//*************************************************************************************************************
	private class UpServicioAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			MoveUpSelectedServicio();			
		}
    	
    }
    
    private class DownServicioAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			MoveDownSelectedServicio();			
		}
    	
    }
	
    private class NuevoServicioAction implements ActionListener {
    	
    	public void actionPerformed(ActionEvent evt){		
    		SwingUtilities.invokeLater(new Runnable(){
    			public void run(){
    				Main.getVentana().getGlassPane().start();
    				Thread performer = new Thread(new Runnable(){
    					public void run(){
    						pes = new PantallaAgregarServicio(Main.getVentana(),SalaPanel.this);   
    						pes.setServicio(new SalaServicioItem());

    						pes.init();
    						pes.setLugarElegido(codigoLugarElegido);
    						pes.setMesEvento(mainPanel.getFechaInicio().substring(5, 7));
    						pes.setFechaEvt(mainPanel.getFechaInicio().substring(0, 10));
    						pes.setVisible(true);

    						/*if (!pes.isCancelado()){
    							addServicio(pes.getServicio(),true);
    							if(pes.getServicio().isSubContratado()){
    								GastosSubContratacionesSalasItem gasto = new GastosSubContratacionesSalasItem();
    								gasto.setCantidad(pes.getServicio().getCantidad());
    								gasto.setServicio(pes.getServicio().getFamilia() + " - " + pes.getServicio().getServicio());					
    								gasto.setSala(model.getNombreSala());
    								gasto.setPrecio(pes.getServicio().getTotal());			    				
    								gasto.setNeto((gasto.getPrecio() - (gasto.getCantidad()  * gasto.getCosto())));
    								((SubcontratadosPanel)mainPanel.getPanelGastosSubcontGral()).setItem(gasto);
    								((SubcontratadosPanel)mainPanel.getPanelGastosSubcontGral()).save();
    							}

    						}*/
    						Main.getVentana().getGlassPane().stop();
    					}
    				},"Nuevo servicio");
    				performer.start();
    			}
    		});

    	}
    }
    
    /**
     * Muestra el popup para modificar el servicio seleccionado.
     * 
     * @author hernux
     *
     */
    private class ModificarServicioAction implements ActionListener {
    	public void actionPerformed(ActionEvent evt) {
			if (getSelectedServicio() != null) {

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						Main.getVentana().getGlassPane().start();
						Thread performer = new Thread(new Runnable() {
							public void run() {
								double costo = 0d;
								String codprov = null;
								String prov = null;
								pes = new PantallaAgregarServicio(Main.getVentana(), SalaPanel.this);
								pes.setServicio(getSelectedServicio());

								if (pes.getServicio().isSubContratado()) {
									GastosSubContratacionesSalasItem gasto = ((SubcontratadosPanel) mainPanel.getPanelGastosSubcontGral()).getItem(pes.getServicio().getServicio(),
													pes.getServicio().getFamilia(),model.getNombreSala());
									if (gasto != null) {
										((SubcontratadosPanel) mainPanel.getPanelGastosSubcontGral()).setItem(gasto);
										codprov = gasto.getProveedorId();
										costo = gasto.getCosto();
										prov = gasto.getProveedor();
										((SubcontratadosPanel) mainPanel.getPanelGastosSubcontGral()).removeItem();
									}
								}
								removeSelectedServicio();

								pes.init();
								pes.cargarServicioExistente();
								pes.setLugarElegido(codigoLugarElegido);
								pes.setMesEvento(mainPanel.getFechaInicio().substring(5, 7));
								pes.setFechaEvt(mainPanel.getFechaInicio().substring(0, 10));
								pes.setVisible(true);

								//addServicio(pes.getServicio(), true);

								if (pes.getServicio().isSubContratado()) {
									/*GastosSubContratacionesSalasItem gasto = new GastosSubContratacionesSalasItem();
									gasto.setCantidad(pes.getServicio().getCantidad());
									gasto.setServicio(pes.getServicio().getFamilia()+ " - "+ pes.getServicio().getServicio());
									gasto.setSala(model.getNombreSala());
									gasto.setPrecio(pes.getServicio().getTotal());
									gasto.setProveedorId(codprov);
									gasto.setProveedor(prov);
									gasto.setCosto(costo);
									gasto.setNeto((gasto.getPrecio() - (gasto.getCantidad() * gasto.getCosto())));
									((SubcontratadosPanel) mainPanel.getPanelGastosSubcontGral()).setItem(gasto);
									((SubcontratadosPanel) mainPanel.getPanelGastosSubcontGral()).save();*/
								}

								Main.getVentana().getGlassPane().stop();
							}
						}, "Modificar servicio");
						performer.start();
					}
				});
			}

		}
    }
	
	/**
	 * Elimina el servicio seleccionado.
	 * 
	 * @author hernux
	 *
	 */
	private class BorrarServicioAction implements ActionListener {
		public void actionPerformed(ActionEvent evt){
			if (MessageUtil.showYesNoMessage(Main.getVentana(), "¿Desea eliminar el Servicio seleccionado?", "Borrar")){
				if(getSelectedServicio().isSubContratado()){
    				//tendria que sacar el subcontratado este
    				GastosSubContratacionesSalasItem gasto = ((SubcontratadosPanel)mainPanel.getPanelGastosSubcontGral()).getItem(getSelectedServicio().getServicio(),getSelectedServicio().getFamilia(),model.getNombreSala());
    				((SubcontratadosPanel)mainPanel.getPanelGastosSubcontGral()).setItem(gasto);
    				((SubcontratadosPanel)mainPanel.getPanelGastosSubcontGral()).removeItem();					
    			}
				removeSelectedServicio();		
			}
		}
	}
	
	private boolean descuentoSala=false;
	
	private class SpinChangeAction implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			
			int spinValue = (Integer)((JSpinner)arg0.getSource()).getValue();
			if(spinValue!=0)
				descuentoSala=true;
			//else
				//descuentoSala=false;
			double totalCalc = model.getTotalSinDecuento()+(model.getTotalSinDecuento()*spinValue/100); 
			//lblTotal.setText(getTotalFormateado(totalCalc));
			mainPanel.setTotalEvento((model.getTotal())*-1);
			mainPanel.setTotalEvento(totalCalc);
			model.setDescuento(spinValue);
			model.setTotal(totalCalc);
			lblTotal.setText(getTotalFormateado(model.getTotal()));
			lblTotalSinDescuento.setText(getTotalFormateado(model.getTotalSinDecuento()));
		}

	}
	
	private class CantidadPersonasKeyListener implements KeyListener{

		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent e) {
			model.setTotalDePersonas(m_txtCantPersonasSala.getText());
			
		}
	}
	
	private class TabalMouseListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent e) {
			tableRoomsPanel.maybeShowPopup(e);
			
		}

		public void mouseReleased(MouseEvent e) {
			tableRoomsPanel.maybeShowPopup(e);
			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private String observaciones;
	
	/**
	 * @return Returns the observaciones.
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones The observaciones to set.
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		model.setObservaciones(observaciones);
	}
	
	private class ObservacionesActionListener implements ActionListener {		

		public void actionPerformed(ActionEvent arg0) {
			if(!StringUtils.isBlank(getModel().getCodigoSala())){
				PantallaEmergenteObservSalas pantalla = new PantallaEmergenteObservSalas(Main.getVentana());
				pantalla.setObservacionesText(getModel().getObservaciones());
		        pantalla.setVisible(true);	        
		        if (!pantalla.isCancel()){
		        	setObservaciones(pantalla.getObservacionesText());
		        }
			}			
		}
	}

	private class HorariosOperacionActionListener implements ActionListener {		

		public void actionPerformed(ActionEvent arg0) {
			if(!StringUtils.isBlank(getModel().getCodigoSala())){
				PantallaHorariosOperaciones pantalla = new PantallaHorariosOperaciones(Main.getVentana(), true);

				/*if(getModel().getHorarios().size()==0){
					ArrayList<Date> dias = DateDiff.getdiasEntreFechas(m_txtFechaDesdeSala.getDate(), m_txtFechaHastaSala.getDate());
					Iterator it = dias.iterator();				
						ArrayList<HorariosItem> horarios = new ArrayList<HorariosItem>();
						while(it.hasNext()){
							HorariosItem item = new HorariosItem();
							item.setFecha(DateConverter.convertDateToString((Date)it.next(), "yyyy-MM-dd"));
							horarios.add(item);
						}
						model.setHorarios(horarios);

				}else
					pantalla.setHorarios(model.getHorarios());*/
				pantalla.setHorarios(model.getHorarios());
		        pantalla.setVisible(true);
		        if(!pantalla.isCancel())
		        	model.setHorarios(pantalla.getAllRows());

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
    		String s= DateConverter.convertDateToString(m_txtFechaHastaSala.getDate(), "yyyy-MM-dd");        	
    		model.setFechaDeFinalizacion(s);
    		ArrayList<Date> dias = DateDiff.getdiasEntreFechas(m_txtFechaDesdeSala.getDate(), m_txtFechaHastaSala.getDate());
			if(model.getHorarios().size()>dias.size()){
				removeHorario(model, dias);
			}
			else if(model.getHorarios().size()<dias.size()){
				addHorario(model, dias);
			}
		}
    	
    }
	
	private class FechaDesdeItemListener implements ActionListener{

    	public void actionPerformed(ActionEvent arg0) {
    		String s= DateConverter.convertDateToString(m_txtFechaDesdeSala.getDate(), "yyyy-MM-dd");   
    		model.setFechaDeInicio(s);
    		ArrayList<Date> dias = DateDiff.getdiasEntreFechas(m_txtFechaDesdeSala.getDate(), m_txtFechaHastaSala.getDate());
			if(model.getHorarios().size()>dias.size()){
				removeHorario(model, dias);
			}
			else if(model.getHorarios().size()<dias.size()){
				addHorario(model, dias);
			}
		}
    	
    }
	
	private class FechaDesarmeItemListener implements ActionListener{

    	public void actionPerformed(ActionEvent arg0) {
    		String s= DateConverter.convertDateToString(m_txtFechaDesarme.getDate(), "yyyy-MM-dd");        	
    		model.setFechaDesarme(s);
		}
    	
    }
	
	private class FechaPruebaItemListener implements ActionListener{

    	public void actionPerformed(ActionEvent arg0) {
    		String s= DateConverter.convertDateToString(m_txtFechaPrueba.getDate(), "yyyy-MM-dd");        	
    		model.setFechaPrueba(s);
		}
    	
    }
	
	private class HoraFinalizacionActionListener implements ActionListener{		
		
		public void actionPerformed(ActionEvent e) {
			model.setHoraDeFinalizacion(m_txtHoraHastaSala.getDate());
			
		}
		
	}
	
	private class HoraInicioActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			model.setHoraDeInicio(m_txtHoraDesdeSala.getDate());
			
		}
		
	}
	
	private class HoraDesarmeActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			model.setHoraDesarme(m_txtHoraDesarme.getDate());
			
		}
		
	}
	
	private class HoraPruebaActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			model.setHoraPrueba(m_txtHoraPrueba.getDate());
			
		}
		
	}
	
	private class TipoArmadoActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			model.setCodigoTipoArmado(m_cmbTipoArmado.searchForeign());
			
		}
		
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		// TODO Auto-generated method stub
		
	}	
	
	private class RefreshActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			m_cmbTipoArmado.recargar();		
		}
		
	}

}
