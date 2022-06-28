package crm.gui.pantalla.solapa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.commons.lang.StringUtils;

import crm.client.helper.UserRolesUtil;
import crm.client.managers.LocalidadManager;
import crm.client.managers.LugarEventoContactoManager;
import crm.client.managers.LugarEventoManager;
import crm.client.managers.PaisManager;
import crm.client.managers.PartidoManager;
import crm.client.managers.ProvinciaManager;
import crm.client.managers.SalaLugarManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.client.validacion.ErrorList;
import crm.gui.Main;
import crm.gui.components.ABMLugarEventoContactoComboBox;
import crm.gui.components.ABMLugarEvtComboBox;
import crm.gui.components.ABMSalasComboBox;
import crm.gui.components.GradientButton;
import crm.gui.pantalla.BuscadorClientes;
import crm.gui.pantalla.BuscadorLugarEvento;
import crm.gui.pantalla.PantallaNuevoContactoLugar;
import crm.gui.pantalla.PantallaNuevoLugar;
import crm.gui.tablerenderer.salas.SalaServiciosTableModel;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.LugarEvento;
import crm.libraries.abm.entities.LugarEventoContacto;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.SalaLugar;
import crm.libraries.util.MessageUtil;

public class LugarEventoPanel extends PanelGeneral implements PanelInterface{
	private javax.swing.JLabel calle;
    private javax.swing.JLabel cargo;
    private javax.swing.JLabel contact;
    private javax.swing.JLabel cp;
    private javax.swing.JLabel departament;
    private javax.swing.JLabel depto;
    private javax.swing.JLabel emailLugar;
    private javax.swing.JLabel faxLugar;
    private GradientButton add;
    private GradientButton remove;
    private GradientButton nombrar;
    private javax.swing.JLabel m_cmbLugares;
    private ABMSalasComboBox m_cmbSalas;

    private javax.swing.JLabel jRadio1;
    private javax.swing.JCheckBox jRadio2;

    private javax.swing.JTextField jTextField1;

    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;

    private javax.swing.JLabel localidad;
    private ABMLugarEventoContactoComboBox m_cmbContacto;
    private javax.swing.JLabel m_lblCPLugar;
    private javax.swing.JLabel m_lblCalleLugar;
    private javax.swing.JLabel m_lblCargo;
    private javax.swing.JLabel m_lblDepartamento;
    private javax.swing.JLabel m_lblDeptoLugar;
    private javax.swing.JLabel m_lblEmailLugar;
    private javax.swing.JLabel m_lblFaxLugar;
    private javax.swing.JLabel m_lblFlotaNextelLugar;
    private javax.swing.JLabel m_lblIdNextelLugar;
    private javax.swing.JLabel m_lblLocalidadLugar;
    private javax.swing.JLabel m_lblLugarEvento;
    private javax.swing.JLabel m_lblNroLugar;
    private javax.swing.JLabel m_lblPaisLugar;
    private javax.swing.JLabel m_lblPartidoLugar;
    private javax.swing.JLabel m_lblPisoLugar;
    private javax.swing.JLabel m_lblProvinciaLugar;
    private javax.swing.JLabel m_lblTel1Lugar;
    private javax.swing.JLabel m_lblTel2Lugar;
    private javax.swing.JLabel nextelLugar;
    private javax.swing.JLabel nro;
    private GradientButton nuevoContacto;
    private GradientButton buscarLugar;
    private javax.swing.JLabel pais;
    private javax.swing.JLabel partido;
    private javax.swing.JLabel piso;
    private javax.swing.JLabel provincia;
    private javax.swing.JLabel tel1Lugar;
    private javax.swing.JLabel tituloLugarEvento;

    private GradientButton editarContacto;
    private GradientButton editarLugar;
    
    private JPanel panel;
  	private LugarEvento lugarElegido;
	LugarEventoContacto contactoElegido;
	
	private MainPanelComercial mainPanel;
	
    public void setMainPanel(MainPanelComercial mainPanel) {
		this.mainPanel = mainPanel;
	}

	public LugarEventoPanel(JPanel pan){
    	panel = pan;    	
    }
	
	public LugarEventoPanel(){  	
    }
    
    public void init() {
    	tituloLugarEvento = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        m_cmbContacto = new ABMLugarEventoContactoComboBox();
        nuevoContacto = new GradientButton("", Color.blue);
        tel1Lugar = new javax.swing.JLabel();
        m_lblTel1Lugar = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        m_lblTel2Lugar = new javax.swing.JLabel();
        emailLugar = new javax.swing.JLabel();
        m_lblEmailLugar = new javax.swing.JLabel();
        faxLugar = new javax.swing.JLabel();
        m_lblFaxLugar = new javax.swing.JLabel();
        nextelLugar = new javax.swing.JLabel();
        m_lblFlotaNextelLugar = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        m_lblIdNextelLugar = new javax.swing.JLabel();
        departament = new javax.swing.JLabel();
        m_lblDepartamento = new javax.swing.JLabel();
        cargo = new javax.swing.JLabel();
        m_lblCargo = new javax.swing.JLabel();
        calle = new javax.swing.JLabel();
        m_cmbLugares = new javax.swing.JLabel();
        buscarLugar = new GradientButton("", Color.blue);
        m_lblCalleLugar = new javax.swing.JLabel();
        nro = new javax.swing.JLabel();
        m_lblNroLugar = new javax.swing.JLabel();
        piso = new javax.swing.JLabel();
        m_lblPisoLugar = new javax.swing.JLabel();
        depto = new javax.swing.JLabel();
        m_lblDeptoLugar = new javax.swing.JLabel();
        cp = new javax.swing.JLabel();
        m_lblCPLugar = new javax.swing.JLabel();
        localidad = new javax.swing.JLabel();
        m_lblLocalidadLugar = new javax.swing.JLabel();
        partido = new javax.swing.JLabel();
        m_lblPartidoLugar = new javax.swing.JLabel();
        provincia = new javax.swing.JLabel();
        m_lblProvinciaLugar = new javax.swing.JLabel();
        pais = new javax.swing.JLabel();
        m_lblPaisLugar = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        m_lblLugarEvento = new javax.swing.JLabel();
        m_cmbSalas = new ABMSalasComboBox();
        add = new GradientButton("", Color.blue);
        remove = new GradientButton("", Color.blue);
        nombrar = new GradientButton("", Color.blue);

        editarLugar = new GradientButton("", Color.blue);
        editarContacto = new GradientButton("", Color.blue);        
        
        jRadio1 = new JLabel();
        jRadio2 = new JCheckBox();

        jRadio1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jRadio1.setText("Salas del lugar");
        jRadio1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadio1.setBackground(new Color(206,204,205));

        jRadio2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jRadio2.setText("Sala temporaria");
        jRadio2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadio2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadio2.setBackground(new Color(206,204,205));
        
        jTextField1 = new JTextField();
        
        tituloLugarEvento.setFont(new java.awt.Font("Tahoma", 1, 11));
        tituloLugarEvento.setText("Lugar de evento");

        contact.setFont(new java.awt.Font("Tahoma", 1, 11));
        contact.setText("Contacto del lugar");        

        nuevoContacto.setIcon(new javax.swing.ImageIcon(getUrlImagen("user_add.png")));
        nuevoContacto.setText("Nuevo contacto");

        tel1Lugar.setFont(new java.awt.Font("Tahoma", 1, 11));
        tel1Lugar.setText("Telefonos");

        jLabel7.setText("/");

        emailLugar.setFont(new java.awt.Font("Tahoma", 1, 11));
        emailLugar.setText("Email");

        faxLugar.setFont(new java.awt.Font("Tahoma", 1, 11));
        faxLugar.setText("Fax");

        nextelLugar.setFont(new java.awt.Font("Tahoma", 1, 11));
        nextelLugar.setText("Nextel");

        jLabel15.setText("*");

        departament.setFont(new java.awt.Font("Tahoma", 1, 11));
        departament.setText("Departamento");

        cargo.setFont(new java.awt.Font("Tahoma", 1, 11));
        cargo.setText("Cargo");

        calle.setFont(new java.awt.Font("Tahoma", 1, 11));
        calle.setText("Direcci\u00f3n");       

        buscarLugar.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        buscarLugar.setText("Buscar lugar");

        nro.setFont(new java.awt.Font("Tahoma", 1, 11));
        nro.setText("Nro");

        piso.setFont(new java.awt.Font("Tahoma", 1, 11));
        piso.setText("Piso");

        depto.setFont(new java.awt.Font("Tahoma", 1, 11));
        depto.setText("Depto");

        cp.setFont(new java.awt.Font("Tahoma", 1, 11));
        cp.setText("C\u00f3digo postal");

        localidad.setFont(new java.awt.Font("Tahoma", 1, 11));
        localidad.setText("Localidad");

        partido.setFont(new java.awt.Font("Tahoma", 1, 11));
        partido.setText("Partido");

        provincia.setFont(new java.awt.Font("Tahoma", 1, 11));
        provincia.setText("Provincia");

        pais.setFont(new java.awt.Font("Tahoma", 1, 11));
        pais.setText("Pais");

        m_lblLugarEvento.setFont(new java.awt.Font("Tahoma", 1, 11));        

        add.setIcon(new javax.swing.ImageIcon(getUrlImagen("add.png")));
        add.setText("Agregar sala");

        remove.setIcon(new javax.swing.ImageIcon(getUrlImagen("delete.png")));
        remove.setText("eliminar sala");    
        
        nombrar.setIcon(new javax.swing.ImageIcon(getUrlImagen("delete.png")));
        nombrar.setText("nombrar sala"); 

        editarLugar.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        editarLugar.setText("Editar lugar seleccionado");

        editarContacto.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        editarContacto.setText("Editar contacto seleccionado");
        
        setEmptyFormContacto();
        setEmptyFormLugar();

        createListeners();
    }
    
    /*public void initLayout(){
    	org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                        .add(layout.createSequentialGroup()
                            .add(tituloLugarEvento)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_cmbLugares, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 395, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(buscarLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(editarLugar))
                        .add(layout.createSequentialGroup()
                            .add(calle)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblCalleLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(nro)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblNroLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(piso)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblPisoLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(depto)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblDeptoLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(cp)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblCPLugar))
                        .add(layout.createSequentialGroup()
                            .add(localidad)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblLocalidadLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(partido)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblPartidoLugar))
                        .add(layout.createSequentialGroup()
                            .add(provincia)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblProvinciaLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(pais)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblPaisLugar))
                        .add(layout.createSequentialGroup()
                            .add(emailLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblEmailLugar))
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(tel1Lugar)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblTel1Lugar)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel7)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblTel2Lugar))
                                .add(layout.createSequentialGroup()
                                    .add(departament)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblDepartamento))
                                .add(layout.createSequentialGroup()
                                    .add(nextelLugar)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblFlotaNextelLugar)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel15)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblIdNextelLugar)))
                            .add(219, 219, 219)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(cargo)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblCargo))
                                .add(layout.createSequentialGroup()
                                    .add(faxLugar)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblFaxLugar))))
                        .add(layout.createSequentialGroup()
                            .add(contact)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_cmbContacto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 317, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(nuevoContacto)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(editarContacto))
                        .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                        .add(layout.createSequentialGroup()
                            .add(jLabel1)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblLugarEvento))
                        .add(layout.createSequentialGroup()
                            .add(m_cmbSalas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 363, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(add)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(remove)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(nombrar))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                        .add(jLabel2)
                        .add(layout.createSequentialGroup()
                            .add(36, 36, 36)
                            .add(alto)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblAltoSala)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(ancho)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblAnchoSala)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(largo)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblLargoSala)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(capacidad)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblCapacSala)))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(tituloLugarEvento)
                        .add(m_cmbLugares, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(buscarLugar)
                        .add(editarLugar))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(calle)
                        .add(m_lblCalleLugar)
                        .add(nro)
                        .add(m_lblNroLugar)
                        .add(piso)
                        .add(m_lblPisoLugar)
                        .add(depto)
                        .add(m_lblDeptoLugar)
                        .add(cp)
                        .add(m_lblCPLugar))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(localidad)
                        .add(m_lblLocalidadLugar)
                        .add(partido)
                        .add(m_lblPartidoLugar))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(provincia)
                        .add(m_lblProvinciaLugar)
                        .add(pais)
                        .add(m_lblPaisLugar))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(contact)
                        .add(m_cmbContacto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(nuevoContacto)
                        .add(editarContacto))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(tel1Lugar)
                        .add(m_lblTel1Lugar)
                        .add(jLabel7)
                        .add(m_lblTel2Lugar)
                        .add(faxLugar)
                        .add(m_lblFaxLugar))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(emailLugar)
                        .add(m_lblEmailLugar))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(nextelLugar)
                        .add(m_lblFlotaNextelLugar)
                        .add(jLabel15)
                        .add(m_lblIdNextelLugar)
                        .add(cargo)
                        .add(m_lblCargo))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(departament)
                        .add(m_lblDepartamento))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel1)
                        .add(m_lblLugarEvento))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(add)
                        .add(m_cmbSalas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(remove)
                        .add(nombrar))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel2)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(alto)
                        .add(m_lblAltoSala)
                        .add(ancho)
                        .add(m_lblAnchoSala)
                        .add(largo)
                        .add(m_lblLargoSala)
                        .add(capacidad)
                        .add(m_lblCapacSala))
                    .addContainerGap(112, Short.MAX_VALUE))
            );
        
    }*/
    
    public void initLayout(){
    	org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                        .add(layout.createSequentialGroup()
                            .add(tituloLugarEvento)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_cmbLugares, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 395, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(buscarLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(editarLugar))
                        .add(layout.createSequentialGroup()
                            .add(calle)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblCalleLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(nro)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblNroLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(piso)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblPisoLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(depto)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblDeptoLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(cp)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblCPLugar))
                        .add(layout.createSequentialGroup()
                            .add(localidad)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblLocalidadLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(partido)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblPartidoLugar))
                        .add(layout.createSequentialGroup()
                            .add(provincia)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblProvinciaLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(pais)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblPaisLugar))
                        .add(layout.createSequentialGroup()
                            .add(emailLugar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblEmailLugar))
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(tel1Lugar)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblTel1Lugar)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel7)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblTel2Lugar))
                                .add(layout.createSequentialGroup()
                                    .add(departament)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblDepartamento))
                                .add(layout.createSequentialGroup()
                                    .add(nextelLugar)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblFlotaNextelLugar)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel15)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblIdNextelLugar)))
                            .add(219, 219, 219)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(cargo)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblCargo))
                                .add(layout.createSequentialGroup()
                                    .add(faxLugar)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblFaxLugar))))
                        .add(layout.createSequentialGroup()
                            .add(contact)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_cmbContacto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 317, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(nuevoContacto)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(editarContacto))
                        .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                        .add(layout.createSequentialGroup()
                            .add(jRadio1)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblLugarEvento))
                        .add(layout.createSequentialGroup()
                            .add(m_cmbSalas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 363, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(add)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(remove))                        
                        .add(jRadio2)
                        .add(layout.createSequentialGroup()
                            .add(36, 36, 36)
                            .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 363, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(tituloLugarEvento)
                        .add(m_cmbLugares, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(buscarLugar)
                        .add(editarLugar))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(calle)
                        .add(m_lblCalleLugar)
                        .add(nro)
                        .add(m_lblNroLugar)
                        .add(piso)
                        .add(m_lblPisoLugar)
                        .add(depto)
                        .add(m_lblDeptoLugar)
                        .add(cp)
                        .add(m_lblCPLugar))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(localidad)
                        .add(m_lblLocalidadLugar)
                        .add(partido)
                        .add(m_lblPartidoLugar))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(provincia)
                        .add(m_lblProvinciaLugar)
                        .add(pais)
                        .add(m_lblPaisLugar))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(contact)
                        .add(m_cmbContacto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(nuevoContacto)
                        .add(editarContacto))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(tel1Lugar)
                        .add(m_lblTel1Lugar)
                        .add(jLabel7)
                        .add(m_lblTel2Lugar)
                        .add(faxLugar)
                        .add(m_lblFaxLugar))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(emailLugar)
                        .add(m_lblEmailLugar))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(nextelLugar)
                        .add(m_lblFlotaNextelLugar)
                        .add(jLabel15)
                        .add(m_lblIdNextelLugar)
                        .add(cargo)
                        .add(m_lblCargo))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(departament)
                        .add(m_lblDepartamento))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jRadio1)
                        .add(m_lblLugarEvento))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(add)
                        .add(m_cmbSalas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(remove))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    //.add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    //.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jRadio2)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    		.add(jTextField1)
                        /*.add(alto)
                        .add(m_lblAltoSala)
                        .add(ancho)
                        .add(m_lblAnchoSala)
                        .add(largo)
                        .add(m_lblLargoSala)
                        .add(capacidad)
                        .add(m_lblCapacSala)*/)
                    .addContainerGap(112, Short.MAX_VALUE))
            );
        
    }
    
    private void loadCombos(){
		m_cmbContacto.loadEmpty();
		//m_cmbLugares.loadItems();	
		m_cmbSalas.loadEmpty();
	}
	
	private void createListeners(){
		m_cmbContacto.addActionListener(new SelectContactoAction());
		//m_cmbLugares.addActionListener(new SelectLugarEventoAction());		
		buscarLugar.addActionListener(new SearchLugarEventoActionListener());
		nuevoContacto.addActionListener(new NewContactoLugarActionListener());
		add.addActionListener(new AddSalaActionListener());
		remove.addActionListener(new RemoveSalaActionListener());
		//m_cmbSalas.addActionListener(new SelectSalaActionListener());
		editarContacto.addActionListener(new EditarContactoAction());
		editarLugar.addActionListener(new EditarLugarAction());

	}
	
	public void setLugarElegido(LugarEvento le){
		lugarElegido = le;
		m_cmbLugares.setText(le.getNombreLugar());
		m_lblLugarEvento.setText(le.getNombreLugar());
		m_cmbContacto.loadItemsForCliente(le.getCodigo());
		m_cmbSalas.setSalasComboBox(le.getCodigo());
		
		m_lblCalleLugar.setText(le.getCalle());
		m_lblNroLugar.setText(le.getNumero());
		m_lblPisoLugar.setText(le.getPiso());
		m_lblDeptoLugar.setText(le.getDepartamento());
		m_lblCPLugar.setText(le.getCodigoPostal());		
		
		String s;
		try {
			if (le.getLocalidad() != null) {						
				s = LocalidadManager.instance().getNombreLocalidadById(
						le.getLocalidad());						
				m_lblLocalidadLugar.setText(s);
			}
			else {
				m_lblLocalidadLugar.setText("");
			}
			
			if (le.getCodigoPartido() != null) {
				s = PartidoManager.instance().getNombrePartidoById(
						le.getCodigoPartido());
				m_lblPartidoLugar.setText(s);
			}
			else {
				m_lblPartidoLugar.setText("");
			}
			
			if (le.getCodigoProvincia() != null) {
				s = ProvinciaManager.instance().getNombreProvinciaById(
						le.getCodigoProvincia());
				m_lblProvinciaLugar.setText(s);
			}
			else {
				m_lblProvinciaLugar.setText("");
			}
			
			if (le.getCodigoPais() != null) {
				s = PaisManager.instance().getNombrePaisById(
						le.getCodigoPais());
				m_lblPaisLugar.setText(s);
			}
			else {
				m_lblPaisLugar.setText("");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public LugarEvento getLugarElegido(){
		return lugarElegido;
	}
	
	private void setEmptyFormLugar(){
		m_lblCalleLugar.setText("-");
		m_lblNroLugar.setText("-");
		m_lblPisoLugar.setText("-");
		m_lblDeptoLugar.setText("-");
		m_lblCPLugar.setText("-");
		m_lblLocalidadLugar.setText("-");
		m_lblPartidoLugar.setText("-");
		m_lblProvinciaLugar.setText("-");
		m_lblPaisLugar.setText("-");
		
		m_cmbContacto.loadEmpty();
		m_cmbSalas.loadEmpty();

	}
	
	private void setEmptyFormContacto(){
		m_lblCargo.setText("-");
		m_lblDepartamento.setText("-");
		m_lblEmailLugar.setText("-");
		m_lblTel1Lugar.setText("-");
		m_lblTel2Lugar.setText("-");
		m_lblFaxLugar.setText("-");
		m_lblFlotaNextelLugar.setText("-");
		m_lblIdNextelLugar.setText("-");
	}
	
	public void setPresupuesto(final Presupuesto p){
		// preselecciono el lugar del evento
		/*ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_NEW_ENTITY);
		ProgressDialogUtil.launchProcessDialog(Main.getVentana());
		new Thread(new Runnable() {	
			public void run() {	
				loadCombos();
				setLugarElegido(p.getLugarDelEvento());
				// cargo los contactos del lugar de evento preseleccionado
				if (p.getLugarDelEvento().getCodigo() != null){
					m_cmbContacto.loadItemsForCliente(p.getLugarDelEvento().getCodigo());
				}
				else{
					m_cmbContacto.resetFields();
				}
				
				// preselecciono el contacto del lugar de evento
				if (p.getContactoLugar()!=null){
					m_cmbContacto.setForeign(p.getContactoLugar().getCodContacto());
				}
				else {
					m_cmbContacto.setForeign(null);
				}		
				checkUserAdmins();
				ProgressDialogUtil.closeProcessDialog();
			}
		}).start();*/
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				Main.getVentana().getGlassPane().start();
				Thread performer = new Thread(new Runnable(){
					public void run(){
						loadCombos();
						setLugarElegido(p.getLugarDelEvento());
						// cargo los contactos del lugar de evento preseleccionado
						if (p.getLugarDelEvento().getCodigo() != null){
							m_cmbContacto.loadItemsForCliente(p.getLugarDelEvento().getCodigo());
						}
						else{
							m_cmbContacto.resetFields();
						}
						
						// preselecciono el contacto del lugar de evento
						if (p.getContactoLugar()!=null){
							m_cmbContacto.setForeign(p.getContactoLugar().getCodContacto());
						}
						else {
							m_cmbContacto.setForeign(null);
						}		
						checkUserAdmins();
						Main.getVentana().getGlassPane().stop();
					}
				},"Carga lugar");
				performer.start();
			}
		});
	}
	
	public String getCodigoContacto(){
		return m_cmbContacto.searchForeign();
	}
	
	public String getCodLugar() {
		if(lugarElegido != null)
			return lugarElegido.getCodigo();
		else return null;
	}
	
	private void setAllLabelBlack(){
		tituloLugarEvento.setForeground(Color.BLACK);
		contact.setForeground(Color.BLACK);
		jRadio1.setForeground(Color.BLACK);
	}
	
	/**
	 * Verifico que se hayan ingresado los datos minimos.
	 */
	public ErrorList validateRequiredFields() {
		
		setAllLabelBlack();
		
		ErrorList errors = new ErrorList();	
		
		if (StringUtils.isBlank(getCodLugar())){
			tituloLugarEvento.setForeground(Color.RED);
			errors.addError("Seleccione un lugar para el evento.");
		}
		
		List<SalaServiciosTableModel> salas = mainPanel.getSalasCreated();
		if (salas.size() == 0){
			jRadio1.setForeground(Color.RED);
			errors.addError("Seleccione al menos una sala del lugar de evento.");
		}
		
		if (mainPanel.isConfirmado()){		
			if (StringUtils.isBlank(getCodigoContacto())){
				contact.setForeground(Color.RED);
				errors.addError("Seleccione un Contacto del lugar de evento.");
			}			
		}
		
		return errors;
	}
	
	public void checkUserAdmins(){
		boolean isCold = UserRolesUtil.isCold(mainPanel.getUsuario());
		m_cmbLugares.setEnabled(!isCold);
		buscarLugar.setEnabled(!isCold);
		m_cmbContacto.setEnabled(!isCold);
		nuevoContacto.setEnabled(!isCold);
		add.setEnabled(!isCold);
		remove.setEnabled(!isCold);
		m_cmbSalas.setEnabled(!isCold);
	}
	
	private void cargarResponsableEventoXDefault (LugarEventoContacto contacto){
		if(contacto.getCargo()!= null)
			m_lblCargo.setText(contacto.getCargo());
		else m_lblCargo.setText("-");
		
		if(contacto.getDepartamento()!= null)
			m_lblDepartamento.setText(contacto.getDepartamento());
		else m_lblDepartamento.setText("-");
		
		m_lblEmailLugar.setText(contacto.getEmail());
		
		m_lblTel1Lugar.setText(contacto.getTelefono1());
		
		if(contacto.getTelefono2()!= null)
			m_lblTel2Lugar.setText(contacto.getTelefono2());
		else m_lblTel2Lugar.setText("-");
		
		if(contacto.getFax()!= null)
			m_lblFaxLugar.setText(contacto.getFax());
		else m_lblFaxLugar.setText("-");
		
		if(contacto.getNextelFota() != null)
			m_lblFlotaNextelLugar.setText(contacto.getNextelFota().toString());
		else m_lblFlotaNextelLugar.setText("-");
		
		if(contacto.getNextelId() != null)
			m_lblIdNextelLugar.setText(contacto.getNextelId().toString());
		else m_lblIdNextelLugar.setText("-");
	}
	
	//*********************************ACCIONES***********************************************
	private class SelectContactoAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			String codigo = m_cmbContacto.searchForeign();			
			
			if (StringUtils.isNotBlank(codigo) && !codigo.equals("0")){
				try {
					contactoElegido = LugarEventoContactoManager.instance().getLugarEventoContactoById(codigo);					
					cargarResponsableEventoXDefault(contactoElegido);
				} catch (RemoteException e) {
					Util.errMsg(Main.getVentana(),"Error al cargar los datos del contacto "+m_cmbContacto.getSelectedItem(),e);
					return;
				}
			}
			else {
				setEmptyFormContacto();
			}
		}
		
	}
	
	private BuscadorLugarEvento buscador;
	
	private class SearchLugarEventoActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (buscador == null){
				buscador = new BuscadorLugarEvento(Main.getVentana());
				buscador.init();
				buscador.initComponent();
				buscador.setLugarAnterior(lugarElegido);
				buscador.setLugarPanel(LugarEventoPanel.this);
			}
			buscador.setVisible(true);
			/*final LugarEvento le = buscador.getSelectedClient();
			buscador = null;
			
			if (le != null){
				
				setLugarElegido(le);			
				
		*/
		}
		
	}
	
	private class EditarLugarAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (lugarElegido !=null && !StringUtils.isBlank(lugarElegido.getCodigo())){
				final String codLugar = lugarElegido.getCodigo();
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						Main.getVentana().getGlassPane().start();
						Thread performer = new Thread(new Runnable(){
							public void run(){
								PantallaNuevoLugar pantallaEditar = new PantallaNuevoLugar(Main.getVentana());
								pantallaEditar.init();
								pantallaEditar.setEditMode(codLugar);
								pantallaEditar.setVisible(true);
								if(pantallaEditar.getCodLugarElegido() != null){			
									m_cmbSalas.setSalasComboBox(pantallaEditar.getCodLugarElegido());
									m_cmbLugares.setText(pantallaEditar.getLugarEvento().getNombreLugar());
								}
								Main.getVentana().getGlassPane().stop();
							}
						},"Editar lugar");
						performer.start();
					}
				});
			}
			else Util.errMsg(Main.getVentana(),"Primero debe seleccionar un lugar de evento",null);
		}
	}
	
	private class NewContactoLugarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(getLugarElegido() != null){
				PantallaNuevoContactoLugar p = new PantallaNuevoContactoLugar(Main.getVentana(), getLugarElegido());
				p.init();				
				p.setVisible(true);
				setEmptyFormContacto();
				m_cmbContacto.loadItemsForCliente(getLugarElegido().getCodigo());
				if(p.getCodContactoElegido() != null){
					m_cmbContacto.setForeign(p.getCodContactoElegido());
					contactoElegido = p.getContacto();
				}
			}
			else {
				MessageUtil.showErrorMessage(Main.getVentana(),"Primero debe seleccionar un lugar de evento");
			}
		}
		
	}
	
	private class EditarContactoAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (contactoElegido !=null && !StringUtils.isBlank(contactoElegido.getCodigo())){
				final String codClienteContacto = contactoElegido.getCodigo();
				/*ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_NEW_ENTITY);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				new Thread(new Runnable() {				
					public void run() {						
						PantallaNuevoContactoLugar pantallaEditar = new PantallaNuevoContactoLugar(Main.getVentana(), getLugarElegido());
						pantallaEditar.init();
						pantallaEditar.setEditMode(codClienteContacto);
						pantallaEditar.setVisible(true);
						if(pantallaEditar.getCodContactoElegido() != null){
							m_cmbContacto.loadItemsForCliente(lugarElegido.getCodigo());
							m_cmbContacto.setForeign(pantallaEditar.getCodContactoElegido());
							contactoElegido = pantallaEditar.getContacto();
						}
						ProgressDialogUtil.closeProcessDialog();
					}				
				}).start();*/
				
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						Main.getVentana().getGlassPane().start();
						Thread performer = new Thread(new Runnable(){
							public void run(){
								PantallaNuevoContactoLugar pantallaEditar = new PantallaNuevoContactoLugar(Main.getVentana(), getLugarElegido());
								pantallaEditar.init();
								pantallaEditar.setEditMode(codClienteContacto);
								pantallaEditar.setVisible(true);
								if(pantallaEditar.getCodContactoElegido() != null){
									m_cmbContacto.loadItemsForCliente(lugarElegido.getCodigo());
									m_cmbContacto.setForeign(pantallaEditar.getCodContactoElegido());
									contactoElegido = pantallaEditar.getContacto();
								}
								Main.getVentana().getGlassPane().stop();
							}
						},"Login");
						performer.start();
					}
				});

			}
			else Util.errMsg(Main.getVentana(),"Primero debe seleccionar un contacto de cliente",null);
		}
	}
	
	private class AddSalaActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			/*if(getLugarElegido() != null && m_cmbSalas.searchForeign() != null){
				mainPanel.addSala((String)m_cmbSalas.getSelectedItem(), m_cmbSalas.searchForeign(),null);
				mainPanel.addObject((String)m_cmbSalas.getSelectedItem());
				mainPanel.setRoomsCount(mainPanel.getRoomsCount()+1);
				m_cmbSalas.setForeign(null);
			}
			else if(m_cmbSalas.searchForeign() == null){
				Util.errMsg(Main.getVentana(),"Debe seleccionar una sala previamente",null);
			}*/
			/*if(getLugarElegido() != null && m_cmbSalas.searchForeign() != null && !jRadio2.isSelected()){
				mainPanel.addSala((String)m_cmbSalas.getSelectedItem(), m_cmbSalas.searchForeign(),null);
				mainPanel.addObject((String)m_cmbSalas.getSelectedItem());
				mainPanel.setRoomsCount(mainPanel.getRoomsCount()+1);
				m_cmbSalas.setForeign(null);
			}
			else if(getLugarElegido() != null && m_cmbSalas.searchForeign() == null && !jRadio2.isSelected()){
				Util.errMsg(Main.getVentana(),"Debe seleccionar una sala previamente",null);
			}
			else if(getLugarElegido() != null && jRadio2.isSelected() && !jTextField1.getText().equals("")){
				mainPanel.addSala(jTextField1.getText(), "4254",null);
				mainPanel.addObject(jTextField1.getText());
				mainPanel.setRoomsCount(mainPanel.getRoomsCount()+1);
				m_cmbSalas.setForeign(null);
			}
			else if(getLugarElegido() != null)
				Util.errMsg(Main.getVentana(),"Debe completar el nombre de la sala previamente",null);
			*/
			
			if(getLugarElegido() != null){
				if(m_cmbSalas.searchForeign() != null){
					if(!jRadio2.isSelected()){
						mainPanel.addSala((String)m_cmbSalas.getSelectedItem(), m_cmbSalas.searchForeign(),null,null);
						mainPanel.addObject((String)m_cmbSalas.getSelectedItem());
						mainPanel.setRoomsCount(mainPanel.getRoomsCount()+1);
						m_cmbSalas.setForeign(null);
					}
					else{
						mainPanel.addSala((String)m_cmbSalas.getSelectedItem(), m_cmbSalas.searchForeign(),null,jTextField1.getText());
						mainPanel.addObject((String)m_cmbSalas.getSelectedItem());
						mainPanel.setRoomsCount(mainPanel.getRoomsCount()+1);
						m_cmbSalas.setForeign(null);
					}
					
				}
				else
					Util.errMsg(Main.getVentana(),"Debe seleccionar una sala previamente",null);
			}
			else
				Util.errMsg(Main.getVentana(),"Debe seleccionar una lugar de evento previamente",null);
		}
		
		
		
	}
	
	private class RemoveSalaActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(m_cmbSalas.searchForeign() != null)
				mainPanel.removeSala((String)m_cmbSalas.getSelectedItem(), m_cmbSalas.searchForeign());
			else {
				Util.errMsg(Main.getVentana(),"Debe seleccionar una sala previamente",null);
			}
		}
		
	}
	
	/*private class SelectSalaActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			setDimensiones(m_cmbSalas.searchForeign());			
		}
		
		private void setDimensiones(String codSala){
			if (codSala != null){
				try{
					SalaLugar s = SalaLugarManager.instance().getSalaLugarById(codSala);
					if (s != null){
						if(s.getLargo() != null)
							m_lblLargoSala.setText(s.getLargo() + " mts.");
						else 
							m_lblLargoSala.setText("-");
						if(s.getAncho() != null)
							m_lblAnchoSala.setText(s.getAncho() + " mts.");
						else
							m_lblAnchoSala.setText("-");
						if(s.getAltura() != null)
							m_lblAltoSala.setText(s.getAltura() + " mts.");
						else
							m_lblAltoSala.setText("-");
						if(s.getCapacidad() != null)
							m_lblCapacSala.setText(s.getCapacidad() + " personas");
						else 
							m_lblCapacSala.setText("-");
					}				
				}
				catch(RemoteException e){
					Util.errMsg(Main.getVentana(), "Error al cargar dimensiones de sala", e);
				}
			}
		}
	}*/

	public MainPanelComercial getMainPanel() {
		return mainPanel;
	}
}
