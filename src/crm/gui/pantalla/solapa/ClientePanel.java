package crm.gui.pantalla.solapa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.ClienteContactoManager;
import crm.client.managers.ClienteManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.client.validacion.ErrorList;
import crm.gui.Main;
import crm.gui.components.ABMClienteContactoComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.components.GradientButton;
import crm.gui.pantalla.BuscadorClientes;
import crm.gui.pantalla.BuscadorClientes;
import crm.gui.pantalla.BuscadorClientesEnPpto;
import crm.gui.pantalla.HistorialCliente;
import crm.gui.pantalla.PantallaNuevoCliente;
import crm.gui.pantalla.PantallaNuevoContactoCliente;
import crm.gui.tablerenderer.buscaCliente.BuscaClientesItem;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.ClienteContacto;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.util.MessageUtil;

public class ClientePanel extends PanelGeneral {
	
	private JPanel panel;
	private Cliente clienteElegido;
	private ClienteContacto contactoElegido;
	
	private javax.swing.JLabel apYNom;
    private javax.swing.JLabel cargo;
    private javax.swing.JLabel contactName;
    private javax.swing.JLabel departament;
    private javax.swing.JLabel email;
    private javax.swing.JLabel fax;    
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea observacionesCliente;
    private GradientButton m_btnBuscarCliente;
    private GradientButton m_btnNuevoContacto;
    private GradientButton verInfo;
    private ABMClienteContactoComboBox m_cmbContacto;
    private javax.swing.JLabel m_lblCargo;
    private javax.swing.JLabel m_lblDepartamento;
    private javax.swing.JLabel m_lblEmail;
    private javax.swing.JLabel m_lblFax;
    private javax.swing.JLabel m_lblFlotaNextel;
    private javax.swing.JLabel m_lblIdNextel;
    private javax.swing.JLabel m_lblTel1;
    private javax.swing.JLabel m_lblTel2;
    private javax.swing.JLabel m_txtClienteFantasia;
    private javax.swing.JFormattedTextField m_txtResponsable;
    private javax.swing.JFormattedTextField m_txtResponsableMail;
    private javax.swing.JFormattedTextField m_txtResponsableNextelFlota;
    private javax.swing.JFormattedTextField m_txtResponsableNextelId;
    private javax.swing.JFormattedTextField m_txtResponsableTel;
    private javax.swing.JFormattedTextField m_txtResponsableTel2;
    private javax.swing.JLabel mail;
    private javax.swing.JLabel name;
    private javax.swing.JLabel nextel;
    private javax.swing.JLabel nextelRes;
    private javax.swing.JLabel tel;
    private javax.swing.JLabel tel1;
    private GradientButton bt_editar_cliente;
    private GradientButton btn_editar_contacto;
	
    private MainPanelComercial main;    

    public ClientePanel(JPanel pan){
    	panel = pan;
    }
    
    public void setMainPanel(MainPanelComercial m){
    	main = m;
    }
    
	public void init(){
		name = new javax.swing.JLabel();
        m_txtClienteFantasia = new javax.swing.JLabel();
        m_btnBuscarCliente = new GradientButton("",Color.blue);
        jSeparator1 = new javax.swing.JSeparator();
        contactName = new javax.swing.JLabel();
        m_cmbContacto = new ABMClienteContactoComboBox();
        m_btnNuevoContacto = new GradientButton("",Color.blue);
        tel1 = new javax.swing.JLabel();
        m_lblTel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        m_lblTel2 = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        m_lblEmail = new javax.swing.JLabel();
        fax = new javax.swing.JLabel();
        m_lblFax = new javax.swing.JLabel();
        nextel = new javax.swing.JLabel();
        m_lblFlotaNextel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        m_lblIdNextel = new javax.swing.JLabel();
        departament = new javax.swing.JLabel();
        m_lblDepartamento = new javax.swing.JLabel();
        cargo = new javax.swing.JLabel();
        m_lblCargo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        apYNom = new javax.swing.JLabel();
        m_txtResponsable = CustomTextField.getNameInstance(50);
        tel = new javax.swing.JLabel();
        mail = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        nextelRes = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacionesCliente = new javax.swing.JTextArea();
        m_txtResponsableTel = CustomTextField.getPhoneNumberInstance();
        m_txtResponsableTel2 = CustomTextField.getPhoneNumberInstance();
        m_txtResponsableMail = CustomTextField.getEmailInstance(50);
        m_txtResponsableNextelFlota = CustomTextField.getSmallIntInstance();
        jLabel3 = new javax.swing.JLabel();
        m_txtResponsableNextelId = CustomTextField.getSmallIntInstance();
        jSeparator3 = new javax.swing.JSeparator();
        bt_editar_cliente = new GradientButton("",Color.blue);
        btn_editar_contacto = new GradientButton("",Color.blue);
        verInfo = new GradientButton("", Color.blue);

        name.setFont(new java.awt.Font("Tahoma", 1, 11));
        name.setText("Nombre del cliente");

        m_txtClienteFantasia.setText("Presione Buscar para seleccionar un cliente");

        m_btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        m_btnBuscarCliente.setText("Buscar");

        contactName.setFont(new java.awt.Font("Tahoma", 1, 11));
        contactName.setText("Contacto del cliente");      

        m_btnNuevoContacto.setIcon(new javax.swing.ImageIcon(getUrlImagen("user_add.png")));
        m_btnNuevoContacto.setText("Nuevo contacto");

        tel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        tel1.setText("Telefonos");

        m_lblTel1.setText("-");

        jLabel7.setText("/");

        m_lblTel2.setText("-");

        email.setFont(new java.awt.Font("Tahoma", 1, 11));
        email.setText("Email");

        m_lblEmail.setText("-");

        fax.setFont(new java.awt.Font("Tahoma", 1, 11));
        fax.setText("Fax");

        m_lblFax.setText("-");

        nextel.setFont(new java.awt.Font("Tahoma", 1, 11));
        nextel.setText("Nextel");

        m_lblFlotaNextel.setText("-");

        jLabel15.setText("*");

        m_lblIdNextel.setText("-");

        departament.setFont(new java.awt.Font("Tahoma", 1, 11));
        departament.setText("Departamento");

        m_lblDepartamento.setText("-");

        cargo.setFont(new java.awt.Font("Tahoma", 1, 11));
        cargo.setText("Cargo");

        m_lblCargo.setText("-");

        apYNom.setFont(new java.awt.Font("Tahoma", 1, 11));
        apYNom.setText("Responsable del evento");

        m_txtResponsable.setText("seleccione un contacto de cliente o edite ");

        tel.setFont(new java.awt.Font("Tahoma", 1, 11));
        tel.setText("Telefonos");

        mail.setFont(new java.awt.Font("Tahoma", 1, 11));
        mail.setText("Email");

        jLabel25.setText("/");

        nextelRes.setFont(new java.awt.Font("Tahoma", 1, 11));
        nextelRes.setText("Nextel");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Observaciones");

        observacionesCliente.setColumns(20);
        observacionesCliente.setLineWrap(true);
        observacionesCliente.setRows(5);
        jScrollPane1.setViewportView(observacionesCliente);

        jLabel3.setText("*");	
        
        bt_editar_cliente.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        bt_editar_cliente.setText("Editar cliente seleccionado");

        btn_editar_contacto.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        btn_editar_contacto.setText("Editar contacto seleccionado");
        		
        verInfo.setText("Ver información del cliente");
        verInfo.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        verInfo.setMnemonic('i');
        verInfo.setToolTipText("Click para ver presupuestos facturados y no cobrados de este cliente");  
        
        loadCombos();
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
                        .add(layout.createSequentialGroup()
                            .add(name)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_txtClienteFantasia, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                            .add(91, 91, 91))
                        .add(layout.createSequentialGroup()
                            .add(m_btnBuscarCliente)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(bt_editar_cliente)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(verInfo))
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                        .add(layout.createSequentialGroup()
                            .add(contactName)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_cmbContacto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 317, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_btnNuevoContacto)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(btn_editar_contacto))
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(tel1)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblTel1)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel7)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblTel2))
                                .add(layout.createSequentialGroup()
                                    .add(email)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblEmail)))
                            .add(191, 191, 191)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(cargo)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblCargo))
                                .add(layout.createSequentialGroup()
                                    .add(fax)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_lblFax)))
                            .add(352, 352, 352))
                        .add(layout.createSequentialGroup()
                            .add(nextel)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblFlotaNextel)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel15)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblIdNextel))
                        .add(layout.createSequentialGroup()
                            .add(departament)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_lblDepartamento))
                        .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                        .add(layout.createSequentialGroup()
                            .add(apYNom)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_txtResponsable, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 454, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createSequentialGroup()
                            .add(tel)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_txtResponsableTel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 103, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel25)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_txtResponsableTel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 404, Short.MAX_VALUE)
                            .add(nextelRes)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_txtResponsableNextelFlota, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel3)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_txtResponsableNextelId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createSequentialGroup()
                            .add(mail)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_txtResponsableMail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 453, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                        .add(layout.createSequentialGroup()
                            .add(jLabel2)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(name)
                        .add(m_txtClienteFantasia))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(m_btnBuscarCliente)
                        .add(bt_editar_cliente)
                        .add(verInfo))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(contactName)
                        .add(m_cmbContacto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(m_btnNuevoContacto)
                        .add(btn_editar_contacto))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(tel1)
                        .add(m_lblTel1)
                        .add(jLabel7)
                        .add(m_lblTel2)
                        .add(fax)
                        .add(m_lblFax))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(email)
                        .add(m_lblEmail)
                        .add(cargo)
                        .add(m_lblCargo))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(nextel)
                        .add(m_lblFlotaNextel)
                        .add(jLabel15)
                        .add(m_lblIdNextel))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(departament)
                        .add(m_lblDepartamento))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(apYNom)
                        .add(m_txtResponsable, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(tel)
                        .add(m_txtResponsableTel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel25)
                        .add(m_txtResponsableTel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(nextelRes)
                        .add(m_txtResponsableNextelFlota, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel3)
                        .add(m_txtResponsableNextelId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(mail)
                        .add(m_txtResponsableMail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel2)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                    .addContainerGap())
            );
        
	}
	
	private void loadCombos(){
		m_cmbContacto.loadEmpty();
	}
	
	private void createListeners(){
		m_btnBuscarCliente.addActionListener(new SearchClientAction());
		m_cmbContacto.addActionListener(new SelectContactoAction());
		m_btnNuevoContacto.addActionListener(new NewContactoAction());
		bt_editar_cliente.addActionListener(new EditarClienteAction());
		btn_editar_contacto.addActionListener(new EditarClienteContactoAction());
		verInfo.addActionListener(new VerInfoActionListener());
	}
	
	public Cliente getClienteElegido() {
		if(clienteElegido != null)
			return clienteElegido;
		else return null;
    }

    public void setClienteElegido(Cliente ce) {
        this.clienteElegido = ce;
        if (this.clienteElegido != null){
			m_txtClienteFantasia.setText(this.clienteElegido.getNombreFantasia());
			m_cmbContacto.loadItemsForCliente(this.clienteElegido.getCodigo());
			if(main.getPanelFacturacion() != null){
				FacturacionPanel factPanel = main.getPanelFacturacion();
				factPanel.setDatosFacturacion(this.clienteElegido);
			}
		}
    }
    
    private void setEmptyForm(){
    	m_lblCargo.setText("-");
		m_lblDepartamento.setText("-");
		m_lblEmail.setText("-");
		m_lblTel1.setText("-");
		m_lblTel2.setText("-");
		m_lblFax.setText("-");
		m_lblFlotaNextel.setText("-");
		m_lblIdNextel.setText("-");
		m_txtResponsable.setText("seleccione un contacto de cliente o editelo");
		m_txtResponsableMail.setText("");
		m_txtResponsableTel.setText("");
		m_txtResponsableNextelFlota.setText("");
		m_txtResponsableNextelId.setText("");
    }
    
    private void cargarResponsableEventoXDefault (ClienteContacto contacto){
		if(contacto.getCargo()!= null)
			m_lblCargo.setText(contacto.getCargo());
		else m_lblCargo.setText("-");
		
		if(contacto.getDepartamento()!= null)
			m_lblDepartamento.setText(contacto.getDepartamento());
		else m_lblDepartamento.setText("-");
		
		m_lblEmail.setText(contacto.getEmail());
		
		m_lblTel1.setText(contacto.getTelefono1());
		
		if(contacto.getTelefono2()!= null)
			m_lblTel2.setText(contacto.getTelefono2());
		else m_lblTel2.setText("-");
		
		if(contacto.getFax()!= null)
			m_lblFax.setText(contacto.getFax());
		else m_lblFax.setText("-");
		
		if(contacto.getFlotaNextel() != null)
			m_lblFlotaNextel.setText(contacto.getFlotaNextel().toString());
		else m_lblFlotaNextel.setText("-");
		
		if(contacto.getIdNextel() != null)
			m_lblIdNextel.setText(contacto.getIdNextel().toString());
		else m_lblIdNextel.setText("-");
		
		m_txtResponsable.setText(contacto.getApellidoYNombre());
		m_txtResponsableMail.setText(contacto.getEmail());			
		m_txtResponsableTel.setText(contacto.getTelefono1());
		if(contacto.getFlotaNextel() != null)
			m_txtResponsableNextelFlota.setText(contacto.getFlotaNextel().toString());
		if(contacto.getIdNextel() != null)
			m_txtResponsableNextelId.setText(contacto.getIdNextel().toString());
	}
    
    public void setPresupuesto(Presupuesto presupuesto){	
    	//this.presupuesto = presupuesto;
		
		Cliente cliente = presupuesto.getCliente();
		if(cliente != null){
			clienteElegido = cliente;
			m_txtClienteFantasia.setText(cliente.getNombreFantasia());
			m_cmbContacto.loadItemsForCliente(cliente.getCodigo());
		}
		else {
			clienteElegido = null;
			m_txtClienteFantasia.setText("");
			m_cmbContacto.resetFields();
		}
		
		if (presupuesto.getContacto()!=null){
			m_cmbContacto.setForeign(presupuesto.getContacto().getCodContacto());
		}
		else {
			m_cmbContacto.setForeign(null);
		}
		
		// preselecciono datos del responsable del evento
		m_txtResponsable.setText(presupuesto.getResponsableEvento());
		m_txtResponsableMail.setText(presupuesto.getResponsableEmail());
		m_txtResponsableTel.setText(presupuesto.getResponsableTel());
		m_txtResponsableNextelFlota.setText(presupuesto.getResponsableNextelFlota());
		m_txtResponsableNextelId.setText(presupuesto.getResponsableNextelId());
		
		if(presupuesto.getObservacionesDelCliente() != null)
			observacionesCliente.setText(presupuesto.getObservacionesDelCliente());
    }
    
    public String getCodigoCliente() {
        if (clienteElegido == null)
            return null;
        return clienteElegido.getCodigo();
    }
    
    public String getObservacionesClientes() {
        return observacionesCliente.getText();
    }

    public void setObservacionesClientes(String observacionesClientes) {
        this.observacionesCliente.setText(observacionesClientes);
    }
    
    public void setResponsableEvento(String resp){
		m_txtResponsable.setText(resp);
	}
	
	public void setResponsableTel(String resp){
		m_txtResponsableTel.setText(resp);
	}
	
	public void setResponsableEmail(String resp){
		m_txtResponsableMail.setText(resp);
	}
	
	public void setResponsableNextelFlota(String resp){
		m_txtResponsableNextelFlota.setText(resp);
	}
	
	public void setResponsableNextelId(String resp){
		m_txtResponsableNextelId.setText(resp);
	}
	
	public String getResponsableEvento(){
		return m_txtResponsable.getText();
	}
	
	public String getResponsableEmail(){
		return m_txtResponsableMail.getText();
	}
	
	public String getResponsableTel(){
		return m_txtResponsableTel.getText();
	}
	
	public String getResponsableNextelFlota(){
		return m_txtResponsableNextelFlota.getText();
	}
	
	public String getResponsableNextelId(){
		return m_txtResponsableNextelId.getText();
	}
	
	public String getCodigoContacto(){
		return m_cmbContacto.searchForeign();
	}
    
	private void setAllLabelBlack(){
		name.setForeground(Color.BLACK);
		contactName.setForeground(Color.BLACK);
		apYNom.setForeground(Color.BLACK);
		mail.setForeground(Color.BLACK);
		tel.setForeground(Color.BLACK);
	}
	
	/**
	 * Verifico que se hayan ingresado los datos minimos.
	 */
	public ErrorList validateRequiredFields() {
		
		setAllLabelBlack();
		
		ErrorList errors = new ErrorList();
		
		if (StringUtils.isBlank(getCodigoCliente())){
			errors.addError("Seleccione un Cliente.");
			name.setForeground(Color.RED);
		}
		
		if (StringUtils.isBlank(getCodigoContacto())){
			errors.addError("Seleccione un Contacto del cliente.");
			contactName.setForeground(Color.RED);
		}
		
		if (main.isConfirmado()){
			
			if (StringUtils.isBlank(getResponsableEvento())){
				errors.addError("Indique el responsable del evento de parte del cliente");
				apYNom.setForeground(Color.red);
			}
			
			if (StringUtils.isBlank(getResponsableEmail())){
				errors.addError("Indique el email del responsable del evento de parte del cliente");
				if(!Util.isMail(getResponsableEmail()))
					errors.addError("Email del responsable del evento de parte del cliente incorrecto");
				mail.setForeground(Color.red);
			}
			
			if (StringUtils.isBlank(getResponsableTel())){
				errors.addError("Indique el teléfono del responsable del evento de parte del cliente");
				tel.setForeground(Color.red);
			}

		}
		
		return errors;
	}
	
	private Cliente getClientePorCodigo(String cod){
		Cliente c = null;
		try{
			c = ClienteManager.instance().getClienteById(cod);
			setClienteElegido(c);
		}
		catch(Exception e){
			Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
		}
		return c;
	}
	
//********************************ACCIONES****************************************************
	private class SearchClientAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			BuscadorClientesEnPpto buscador = new BuscadorClientesEnPpto(Main.getVentana(), ClientePanel.this);
			buscador.init();
			buscador.initComponent();
			buscador.setVisible(true);
			//final Cliente clienteElegido = buscador.getSelectedClient();
			buscador = null;
			
			/*if (clienteElegido != null){
				
				setClienteElegido(clienteElegido);
				m_txtClienteFantasia.setText(clienteElegido.getNombreFantasia());
				m_cmbContacto.loadItemsForCliente(clienteElegido.getCodigo());
				if(main.getPanelFacturacion() != null){
					FacturacionPanel factPanel = main.getPanelFacturacion();
					factPanel.setDatosFacturacion(clienteElegido);
				}
			}*/
		}
	}
	
	private class SelectContactoAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			
			String codigo = m_cmbContacto.searchForeign();
			
			if (StringUtils.isNotBlank(codigo) && !codigo.equals("0")){
				try {
					contactoElegido = ClienteContactoManager.instance().getClienteContactoById(codigo);					
					cargarResponsableEventoXDefault(contactoElegido);
					
				} catch (RemoteException e) {
					Util.errMsg(Main.getVentana(),"Error al cargar datos externos ",e);
					return;
				}
			}
			else {
				setEmptyForm();
			}
		}
		
		
	}
	
	private class NewContactoAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (getClienteElegido() != null){
				PantallaNuevoContactoCliente p = new PantallaNuevoContactoCliente(Main.getVentana(), getClienteElegido());
				p.init();				
				p.setVisible(true);
				setEmptyForm();
				m_cmbContacto.loadItemsForCliente(clienteElegido.getCodigo());
				if(p.getCodContactoElegido() != null){
					m_cmbContacto.setForeign(p.getCodContactoElegido());
					contactoElegido = p.getContacto();
				}
			}
			else MessageUtil.showErrorMessage(Main.getVentana(),"Primero debe seleccionar un cliente");
			
		}
		
	}
	
	private class EditarClienteAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (clienteElegido !=null && !StringUtils.isBlank(clienteElegido.getCodigo())){
				final String codCliente = clienteElegido.getCodigo();
				/*ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_NEW_ENTITY);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				new Thread(new Runnable() {				
					public void run() {						
						PantallaNuevoCliente pantallaEditar = new PantallaNuevoCliente(Main.getVentana());
						pantallaEditar.initComponents();
						pantallaEditar.setEditMode(codCliente);
						pantallaEditar.setVisible(true);
						ProgressDialogUtil.closeProcessDialog();
					}				
				}).start();*/

				
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						Main.getVentana().getGlassPane().start();
						Thread performer = new Thread(new Runnable(){
							public void run(){
								PantallaNuevoCliente pantallaEditar = new PantallaNuevoCliente(Main.getVentana());
								pantallaEditar.initComponents();
								pantallaEditar.setEditMode(codCliente);
								pantallaEditar.setVisible(true);
								Main.getVentana().getGlassPane().stop();
							}
						},"Editar cliente");
						performer.start();
					}
				});
				
			}
			else Util.errMsg(Main.getVentana(),"Primero debe seleccionar un cliente",null);
		}
	}
	
	private class EditarClienteContactoAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (contactoElegido !=null && !StringUtils.isBlank(contactoElegido.getCodigo())){
				final String codClienteContacto = contactoElegido.getCodigo();
				/*ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_NEW_ENTITY);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				new Thread(new Runnable() {				
					public void run() {
						//PantallaEditarCliente pantallaEditar = new PantallaEditarCliente(Main.getVentana(), codCliente);
						PantallaNuevoContactoCliente pantallaEditar = new PantallaNuevoContactoCliente(Main.getVentana(), getClienteElegido());
						pantallaEditar.init();
						pantallaEditar.setEditMode(codClienteContacto);
						pantallaEditar.setVisible(true);
						if(pantallaEditar.getCodContactoElegido() != null){
							m_cmbContacto.loadItemsForCliente(clienteElegido.getCodigo());
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
								PantallaNuevoContactoCliente pantallaEditar = new PantallaNuevoContactoCliente(Main.getVentana(), getClienteElegido());
								pantallaEditar.init();
								pantallaEditar.setEditMode(codClienteContacto);
								pantallaEditar.setVisible(true);
								if(pantallaEditar.getCodContactoElegido() != null){
									m_cmbContacto.loadItemsForCliente(clienteElegido.getCodigo());
									m_cmbContacto.setForeign(pantallaEditar.getCodContactoElegido());
									contactoElegido = pantallaEditar.getContacto();
								}
								Main.getVentana().getGlassPane().stop();
							}
						},"Editar contacto Cliente");
						performer.start();
					}
				});
				
			}
			else Util.errMsg(Main.getVentana(),"Primero debe seleccionar un contacto de cliente",null);
		}
	}
	
	private class VerInfoActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
			if (clienteElegido !=null && !StringUtils.isBlank(clienteElegido.getCodigo())){
				final String codCliente = clienteElegido.getCodigo();
			
				HistorialCliente h = new HistorialCliente(Main.getVentana());
				h.initComponents(clienteElegido.getEmpresa());				
				if(codCliente != null){
					h.loadData(codCliente);
					h.setVisible(true);
				}
				else Util.errMsg(Main.getVentana(), "Primero seleccione un cliente de la grilla", null);
			}
		}
		
	}

}
