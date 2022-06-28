package crm.gui.pantalla.solapa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.commons.lang.StringUtils;

import crm.client.util.DateConverter;
import crm.client.util.ProgressDialogUtil;
import crm.client.validacion.ErrorList;
import crm.gui.Main;
import crm.gui.components.ABMCategEventosComboBox;
import crm.gui.components.ABMModoIngreso;
import crm.gui.components.ABMSeguridadEquipo;
import crm.gui.components.ABMTiposEventosComboBox;
import crm.gui.components.ABMTiposLugarEvtComboBox;
import crm.gui.components.ABMTiposUniformesComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.components.DatePanel;
import crm.gui.components.HourPanel2;
import crm.gui.components.JXDatePicker;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.util.MessageUtil;

public class EventoPanel extends PanelGeneral{
	
	private javax.swing.JLabel alas;
    private javax.swing.JLabel categEvento;
    private javax.swing.JLabel eventName;
    private javax.swing.JLabel eventType;
    private javax.swing.JLabel instalationDate;
    private ABMCategEventosComboBox m_cmbCategEventos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea observacionesEvt;
    private ABMModoIngreso m_cmbModoIngreso;
    private ABMSeguridadEquipo m_cmbSeguridadEquipo;
    private ABMTiposEventosComboBox m_cmbTiposEventos;
    private ABMTiposLugarEvtComboBox m_cmbTiposLugar;
    private ABMTiposUniformesComboBox m_cmbTiposUniformes;
    private javax.swing.JFormattedTextField m_txtCantPersonasEvento;
    private javax.swing.JFormattedTextField m_txtEvtNombre;
    private JXDatePicker m_txtFechaInstalacion;
    private HourPanel2 m_txtHoraInstalacion;
    private javax.swing.JLabel modo;
    private javax.swing.JLabel parsonsCount;
    private javax.swing.JLabel placeType;
    private javax.swing.JLabel seguridad;
    private javax.swing.JLabel uniformType;
    private javax.swing.JScrollPane jScrollPane1;
    private JButton m_btnRecargarCateg;
    private JButton m_btnRecargarIngreso;
    private JButton m_btnRecargarSeguridad;
    private JButton m_btnRecargarTEvento;
    private JButton m_btnRecargarTLugar;
    private JButton m_btnRecargarUniforme;
    
	private JPanel panel;
	private MainPanelComercial main;
	
	public EventoPanel(JPanel pan){
		panel = pan;
	}
	
	public EventoPanel(){
		
	}
	
	public void setMainPanel(MainPanelComercial m){
		main = m;
	}
	
	public void init(){	
		eventName = new javax.swing.JLabel();
        m_txtEvtNombre = new javax.swing.JFormattedTextField();
        placeType = new javax.swing.JLabel();
        eventType = new javax.swing.JLabel();
        m_cmbTiposEventos = new ABMTiposEventosComboBox();
        categEvento = new javax.swing.JLabel();
        m_cmbTiposLugar = new ABMTiposLugarEvtComboBox();
        uniformType = new javax.swing.JLabel();
        m_cmbTiposUniformes = new ABMTiposUniformesComboBox();
        modo = new javax.swing.JLabel();
        m_cmbModoIngreso = new ABMModoIngreso();
        seguridad = new javax.swing.JLabel();
        m_cmbSeguridadEquipo = new ABMSeguridadEquipo();
        parsonsCount = new javax.swing.JLabel();
        m_txtCantPersonasEvento = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        observacionesEvt = new javax.swing.JTextArea();
        instalationDate = new javax.swing.JLabel();
        m_txtFechaInstalacion = new JXDatePicker();
        m_txtHoraInstalacion = CustomTextField.getHours2Instance();
        alas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        m_cmbCategEventos = new ABMCategEventosComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        
        m_btnRecargarCateg = new JButton();
        m_btnRecargarIngreso = new JButton();
        m_btnRecargarSeguridad = new JButton();
        m_btnRecargarTEvento = new JButton();
        m_btnRecargarTLugar = new JButton();
        m_btnRecargarUniforme = new JButton();
                
        m_btnRecargarCateg.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        m_btnRecargarIngreso.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        m_btnRecargarSeguridad.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        m_btnRecargarTEvento.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        m_btnRecargarTLugar.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        m_btnRecargarUniforme.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        eventName.setFont(new java.awt.Font("Tahoma", 1, 11));
        eventName.setText("Nombre del evento");

        m_txtEvtNombre.setText("Escriba aqu\u00ed el nombre del evento");

        placeType.setFont(new java.awt.Font("Tahoma", 1, 11));
        placeType.setText("Tipo de lugar");

        eventType.setFont(new java.awt.Font("Tahoma", 1, 11));
        eventType.setText("Tipo de evento");        

        categEvento.setFont(new java.awt.Font("Tahoma", 1, 11));
        categEvento.setText("Categoria de evento");        

        uniformType.setFont(new java.awt.Font("Tahoma", 1, 11));
        uniformType.setText("Tipo de uniformes");        

        modo.setFont(new java.awt.Font("Tahoma", 1, 11));
        modo.setText("Modo de ingreso de los equipos");        

        seguridad.setFont(new java.awt.Font("Tahoma", 1, 11));
        seguridad.setText("Seguridad de equipos a cargo de");        

        parsonsCount.setFont(new java.awt.Font("Tahoma", 1, 11));
        parsonsCount.setText("Total de personas");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Observaciones");

        observacionesEvt.setColumns(20);
        observacionesEvt.setLineWrap(true);
        observacionesEvt.setRows(3);
        jScrollPane1.setViewportView(observacionesEvt);

        instalationDate.setFont(new java.awt.Font("Tahoma", 1, 11));
        instalationDate.setText("Fecha de instalaci\u00f3n");

        alas.setFont(new java.awt.Font("Tahoma", 1, 11));
        alas.setText("a las");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("hs");       
        
		//loadCombos();
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
                        .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                        .add(layout.createSequentialGroup()
                            .add(eventName)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_txtEvtNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 354, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createSequentialGroup()
                            .add(eventType)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_cmbTiposEventos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 301, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_btnRecargarTEvento)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(categEvento)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_cmbCategEventos, 0, 299, Short.MAX_VALUE)
                            .add(m_btnRecargarCateg))
                        .add(layout.createSequentialGroup()
                            .add(uniformType)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_cmbTiposUniformes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 323, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_btnRecargarUniforme))
                        .add(layout.createSequentialGroup()
                            .add(modo)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_cmbModoIngreso, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 344, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_btnRecargarIngreso))
                        .add(layout.createSequentialGroup()
                            .add(jLabel3)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE))
                        .add(layout.createSequentialGroup()
                            .add(placeType)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_cmbTiposLugar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 315, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_btnRecargarTLugar))                            
                        .add(layout.createSequentialGroup()
                            .add(seguridad)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_cmbSeguridadEquipo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 315, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_btnRecargarSeguridad))                            
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(parsonsCount)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_txtCantPersonasEvento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(instalationDate)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_txtFechaInstalacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(alas)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_txtHoraInstalacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel1)))))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(eventName)
                        .add(m_txtEvtNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(16, 16, 16)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(eventType)
                        .add(categEvento)
                        .add(m_cmbTiposEventos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(m_btnRecargarTEvento)
                        .add(m_cmbCategEventos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(m_btnRecargarCateg))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(m_cmbTiposLugar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(m_btnRecargarTLugar)
                        .add(placeType))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(uniformType)
                        .add(m_cmbTiposUniformes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(m_btnRecargarUniforme))                        
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(modo)
                        .add(m_cmbModoIngreso, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(m_btnRecargarIngreso))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(seguridad)
                        .add(m_cmbSeguridadEquipo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(m_btnRecargarSeguridad))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(parsonsCount)
                            .add(instalationDate)
                            .add(m_txtCantPersonasEvento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(alas)
                            .add(m_txtFechaInstalacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel1)
                                .add(m_txtHoraInstalacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel3)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
	}
	private void loadCombos(){	
		
		m_cmbCategEventos.loadItems();
		m_cmbTiposEventos.loadItems();
		m_cmbTiposLugar.loadItems();
		m_cmbTiposUniformes.loadItems();
		m_cmbModoIngreso.loadItems();
		m_cmbSeguridadEquipo.loadItems();
		
	}
	
	private void createListeners(){
		//m_txtFechaInstalacion.addFocusListener(new FocusLostDateListener());
		m_txtCantPersonasEvento.addKeyListener(new TotalKeyListener());
		m_btnRecargarCateg.addActionListener(new recargarCategoria());
		m_btnRecargarIngreso.addActionListener(new recargarIngreso());
		m_btnRecargarSeguridad.addActionListener(new recargarSeguridad());
		m_btnRecargarTEvento.addActionListener(new recargarTipoEvento());
		m_btnRecargarTLugar.addActionListener(new recargarTipoLugar());
		m_btnRecargarUniforme.addActionListener(new recargarUniformes());
	}
	
	public void setPresupuesto(final Presupuesto presupuesto){
		/*ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_NEW_ENTITY);
    	ProgressDialogUtil.launchProcessDialog(Main.getVentana());
    	new Thread(new Runnable() {	
    		public void run() {	
		loadCombos();
		// precargo el nombre del evento
		m_txtEvtNombre.setText(presupuesto.getNombreDelEvento());
		
		// preselecciono el tipo de evento
		if(presupuesto.getTipoDeEvento() != null && !presupuesto.getTipoDeEvento().getCodigo().equals("0"))
			m_cmbTiposEventos.setForeign(presupuesto.getTipoDeEvento().getCodigo());
		else
			m_cmbTiposEventos.setForeign("1");
		
		// preselecciono el tipo de uniforme
		if(presupuesto.getUniforme() != null && !presupuesto.getUniforme().getCodigo().equals("0"))
			m_cmbTiposUniformes.setForeign(presupuesto.getUniforme().getCodigo());
		else
			m_cmbTiposUniformes.setForeign("2");
		
		// preselecciono el tipo de lugar
		if(presupuesto.getTipoDeLugarDelEvento() != null && !presupuesto.getTipoDeLugarDelEvento().getCodigo().equals("0"))
			m_cmbTiposLugar.setForeign(presupuesto.getTipoDeLugarDelEvento().getCodigo());
		else
			m_cmbTiposLugar.setForeign("3");
		
		// preselecciono modo de ingreso, seguridad de equipos y categoria de evento
		if (presupuesto.getAgregado() != null){
			if(!presupuesto.getAgregado().getModoIngreso().equals("0"))
				m_cmbModoIngreso.setForeign(presupuesto.getAgregado().getModoIngreso());
			else
				m_cmbModoIngreso.setForeign("3");
			if(!presupuesto.getAgregado().getSeguridadIngreso().equals("0"))
				m_cmbSeguridadEquipo.setForeign(presupuesto.getAgregado().getSeguridadIngreso());
			else
				m_cmbSeguridadEquipo.setForeign("2");
			if(!presupuesto.getAgregado().getCategoriaEvento().equals("0"))
				m_cmbCategEventos.setForeign(presupuesto.getAgregado().getCategoriaEvento());
			else
				m_cmbCategEventos.setForeign("1");
		}
		
		// cargo la fecha y hora de instalacion
		if(presupuesto.getFechaDeInstalacion() != null){
			m_txtHoraInstalacion.setHourWithDate(getHourOfDate(presupuesto.getFechaDeInstalacion()));
			
			try {
				m_txtFechaInstalacion.setDate(DateConverter.convertStringToDate(presupuesto.getFechaDeInstalacion(), "yyyy-MM-dd"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// cargo total de personas que acudiran al evento
		m_txtCantPersonasEvento.setText(presupuesto.getTotalDePersonas());
		
		// cargo las observaciones del evento
		observacionesEvt.setText(presupuesto.getObservacionesDelEvento());		
		ProgressDialogUtil.closeProcessDialog();
    		}
    	}).start();*/
    	
    	SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				Main.getVentana().getGlassPane().start();
				Thread performer = new Thread(new Runnable(){
					public void run(){
						loadCombos();
						// precargo el nombre del evento
						m_txtEvtNombre.setText(presupuesto.getNombreDelEvento());
						
						// preselecciono el tipo de evento
						if(presupuesto.getTipoDeEvento() != null && !presupuesto.getTipoDeEvento().getCodigo().equals("0"))
							m_cmbTiposEventos.setForeign(presupuesto.getTipoDeEvento().getCodigo());
						else
							m_cmbTiposEventos.setForeign("1");
						
						// preselecciono el tipo de uniforme
						if(presupuesto.getUniforme() != null && !presupuesto.getUniforme().getCodigo().equals("0"))
							m_cmbTiposUniformes.setForeign(presupuesto.getUniforme().getCodigo());
						else
							m_cmbTiposUniformes.setForeign("2");
						
						// preselecciono el tipo de lugar
						if(presupuesto.getTipoDeLugarDelEvento() != null && !presupuesto.getTipoDeLugarDelEvento().getCodigo().equals("0"))
							m_cmbTiposLugar.setForeign(presupuesto.getTipoDeLugarDelEvento().getCodigo());
						else
							m_cmbTiposLugar.setForeign("3");
						
						// preselecciono modo de ingreso, seguridad de equipos y categoria de evento
						if (presupuesto.getAgregado() != null){
							if(!presupuesto.getAgregado().getModoIngreso().equals("0"))
								m_cmbModoIngreso.setForeign(presupuesto.getAgregado().getModoIngreso());
							else
								m_cmbModoIngreso.setForeign("3");
							if(!presupuesto.getAgregado().getSeguridadIngreso().equals("0"))
								m_cmbSeguridadEquipo.setForeign(presupuesto.getAgregado().getSeguridadIngreso());
							else
								m_cmbSeguridadEquipo.setForeign("2");
							if(!presupuesto.getAgregado().getCategoriaEvento().equals("0"))
								m_cmbCategEventos.setForeign(presupuesto.getAgregado().getCategoriaEvento());
							else
								m_cmbCategEventos.setForeign("1");
						}
						
						try {
							m_txtFechaInstalacion.setDate(DateConverter.convertStringToDate(main.getFechaInicio(), "yyyy-MM-dd"));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						// cargo la fecha y hora de instalacion
						if(presupuesto.getFechaDeInstalacion() != null){
							m_txtHoraInstalacion.setHourWithDate(getHourOfDate(presupuesto.getFechaDeInstalacion()));
							
							try {
								m_txtFechaInstalacion.setDate(DateConverter.convertStringToDate(presupuesto.getFechaDeInstalacion(), "yyyy-MM-dd"));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						// cargo total de personas que acudiran al evento
						m_txtCantPersonasEvento.setText(presupuesto.getTotalDePersonas());
						
						// cargo las observaciones del evento
						observacionesEvt.setText(presupuesto.getObservacionesDelEvento());	
						Main.getVentana().getGlassPane().stop();
					}
				},"Login");
				performer.start();
			}
		});
	}
	
	
	private String getHourOfDate(String date){
		if(date != null){
			int idx = date.indexOf(" ");
			if(idx > 0){
				String hour = date.substring(idx + 1,idx + 6);
				return hour;
			}
			else return "00:00";
		}
		else return "00:00";	 
	}
	
	public String getCodigoCategoriaEvento(){
		return m_cmbCategEventos.searchForeign();
	}
	
	public String getNombreDelEvento(){
		return m_txtEvtNombre.getText();
	}
	
	public String getCodigoModoIngreso(){
		return m_cmbModoIngreso.searchForeign();
	}
    
	public String getCodigoSeguridadIngreso(){
		return m_cmbSeguridadEquipo.searchForeign();
	}
	
	public String getCodigoTipoEvento(){
		return m_cmbTiposEventos.searchForeign();
	}
	
	public String getCodigoTipoLugar(){
		return m_cmbTiposLugar.searchForeign();
	}
	
	public String getCodigoUniforme(){
		return m_cmbTiposUniformes.searchForeign();
	}
	
	public void setFechaInstalacion(String date){
		Date d;
		try {
			d = DateConverter.convertStringToDate(date,"yyyy-MM-dd");
			m_txtFechaInstalacion.setDate(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void setObservacionesEvt(String texto){
        observacionesEvt.setText(texto);
    }
    
    public String getObservacionesDelEvento(){
        return observacionesEvt.getText();
    }
    
    public String getFechaInstalacion() {
    	String d=DateConverter.convertDateToString(m_txtFechaInstalacion.getDate(),"yyyy-MM-dd");
		return d + " " + m_txtHoraInstalacion.getDate()+ ":00";
	}
	
    public String getCantPersonasEvento(){
		return m_txtCantPersonasEvento.getText();
	}
    
    private void setAllLabelBlack(){
    	eventName.setForeground(Color.BLACK);
    	eventType.setForeground(Color.BLACK);
    	uniformType.setForeground(Color.BLACK);
    	placeType.setForeground(Color.BLACK);
    	modo.setForeground(Color.BLACK);
    	seguridad.setForeground(Color.BLACK);
    	categEvento.setForeground(Color.BLACK);
    	parsonsCount.setForeground(Color.BLACK);
    	instalationDate.setForeground(Color.BLACK);
    	alas.setForeground(Color.BLACK);
    }
    
    /**
	 * Verifico que se hayan ingresado los datos minimos.
	 */
	public ErrorList validateRequiredFields() {
		
		setAllLabelBlack();
		
		ErrorList errors = new ErrorList();

		if (StringUtils.isBlank(getNombreDelEvento())){
			errors.addError("Ingrese el nombre del evento.");
			eventName.setForeground(Color.RED);
		}
		
		if (StringUtils.isBlank(getCodigoTipoEvento())){
			errors.addError("Indique el tipo de evento.");
			eventType.setForeground(Color.RED);
		}
		
		if (main.isConfirmado()){
			if (StringUtils.isBlank(getCodigoUniforme())){
			 	errors.addError("Indique el uniforme a usar en el evento.");
			 	uniformType.setForeground(Color.RED);
			}
		
			if (StringUtils.isBlank(getCodigoTipoLugar())){
				errors.addError("Indique el tipo de lugar del evento.");
				placeType.setForeground(Color.RED);
			}

			if (StringUtils.isBlank(getCodigoModoIngreso())){
				errors.addError("Seleccione un modo de ingreso de equipos en el evento.");
				modo.setForeground(Color.RED);
			}
			
			if (StringUtils.isBlank(getCodigoSeguridadIngreso())){
				errors.addError("Seleccione la seguridad de los equipos en el evento.");
				seguridad.setForeground(Color.RED);
			}

			if (StringUtils.isBlank(getCodigoCategoriaEvento())){
				errors.addError("Seleccione una categoria de evento.");
				categEvento.setForeground(Color.red);
			}
			
			if (m_txtCantPersonasEvento.getText().equals("0")){
				errors.addError("Indique la cantidad de personas que acudirán a este evento.");
				parsonsCount.setForeground(Color.RED);
			}
			
			if (StringUtils.isBlank(getCantPersonasEvento())){
			 	errors.addError("Indique la cantidad de personas que acudirán a este evento.");
			 	parsonsCount.setForeground(Color.RED);
			}
			
			if (m_txtFechaInstalacion.getDate().equals("2008-01-01")){
			 	errors.addError("Indique la fecha de instalación de los equipos.");
			 	instalationDate.setForeground(Color.RED);
			}
			if(m_txtHoraInstalacion.getSelectedIndex()==0){
				errors.addError("Indique la hora de instalación de los equipos.");
				alas.setForeground(Color.red);
			}
		}
		
		return errors;
	}
    
//****************************ACCIONES********************************************************	
	/*private class FocusLostDateListener implements FocusListener {
		
		private String fechaActual;
		
		public void focusGained(FocusEvent arg0) {
			fechaActual = m_txtFechaInstalacion.getDate();
		}

		public void focusLost(FocusEvent arg0) {
			if (!m_txtFechaInstalacion.getDate().equals(fechaActual)){
				MessageUtil.showWarningMessage("Recuerde cobrar el día de instalación\n si aplica en este presupuesto");
			}
		}
		
	}*/
	
	private class TotalKeyListener implements KeyListener{

		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent e) {
			Iterator<SalaPanel> it = main.getPanelesSala().iterator();
			while(it.hasNext()){
				SalaPanel salaPanel = it.next();
				salaPanel.getModel().setTotalDePersonas(m_txtCantPersonasEvento.getText());
			}
			
		}
		
	}
	
	private class recargarTipoEvento implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			m_cmbTiposEventos.recargar();
			
		}
		
	}
	
	private class recargarTipoLugar implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			m_cmbTiposLugar.recargar();
			
		}
		
	}
	private class recargarSeguridad implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			m_cmbSeguridadEquipo.recargar();
			
		}
		
	}
	private class recargarUniformes implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			m_cmbTiposUniformes.recargar();
			
		}
		
	}
	private class recargarIngreso implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			m_cmbModoIngreso.recargar();
			
		}
		
	}
	private class recargarCategoria implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			m_cmbCategEventos.recargar();
			
		}
		
	}

}
