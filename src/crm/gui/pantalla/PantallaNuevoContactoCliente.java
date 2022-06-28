package crm.gui.pantalla;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.ClienteContactoManager;
import crm.client.util.DateConverter;
import crm.gui.components.ABMTitulosComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.components.PanelImagen;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.ClienteContacto;
import crm.libraries.util.MessageUtil;

public class PantallaNuevoContactoCliente extends PantallaEmergente{
	private javax.swing.JLabel apYNom;
    private javax.swing.JFormattedTextField apellidoYNombre;
    private javax.swing.JFormattedTextField cargo;
    private javax.swing.JFormattedTextField departamento;
    private javax.swing.JFormattedTextField email;
    private javax.swing.JFormattedTextField fax;
    private javax.swing.JFormattedTextField flotaNextel;
    private javax.swing.JFormattedTextField idNextel;
    private javax.swing.JFormattedTextField interno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel mail;
    private javax.swing.JLabel nextelRes;
    private javax.swing.JButton nuevo;
    private javax.swing.JButton salir;
    private javax.swing.JLabel tel;
    private javax.swing.JFormattedTextField telefono1;
    private javax.swing.JFormattedTextField telefono2;
    private ABMTitulosComboBox m_cmbTitulo;
    
    private Cliente cliente;
    private ClienteContacto contacto;
    private JPanel panel;
    private String codContactoElegido;
    
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    
    public PantallaNuevoContactoCliente(Frame owner, Cliente clienteId){
		
		super("Nuevo contacto del cliente",owner);
		//this.setTitle("Nuevo contacto del cliente");
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setEnabled(true);
        this.setResizable(false);
        this.cliente = clienteId;
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
        jMenuItem2.addActionListener(new SalirActionListener());
        jMenu1.add(jMenuItem2);
        
        jMenuBar1.add(jMenu1);
		
        this.setJMenuBar(jMenuBar1);   
    }
    
    public void init(){
		//panel = new JPanel();
		
		//**************Crecion de panel contenedor con imagen de fondo**********************
		panel = null;
		try{
			panel = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
		
		jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        nextelRes = new javax.swing.JLabel();
        apYNom = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tel = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        mail = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        nuevo = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        m_cmbTitulo = new ABMTitulosComboBox();
        m_cmbTitulo.loadItems();
        
        this.apellidoYNombre = CustomTextField.getNameInstance(50);
		this.departamento = CustomTextField.getNameInstance(50);
		this.cargo = CustomTextField.getNameInstance(20);
		this.telefono1 = CustomTextField.getPhoneNumberInstance();
		this.telefono2 = CustomTextField.getPhoneNumberInstance();
		this.fax = CustomTextField.getPhoneNumberInstance();
		this.interno = CustomTextField.getRegularStringInstance(20);
		this.flotaNextel = CustomTextField.getIntInstance();
		this.idNextel = CustomTextField.getIntInstance();
		this.email = CustomTextField.getEmailInstance(50);
		
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de nuevo contacto de");

        nextelRes.setFont(new java.awt.Font("Tahoma", 1, 11));
        nextelRes.setText("Nextel");

        apYNom.setFont(new java.awt.Font("Tahoma", 1, 11));
        apYNom.setText("Nombre y apellido");

        jLabel3.setText("-");

        tel.setFont(new java.awt.Font("Tahoma", 1, 11));
        tel.setText("Telefonos");

        jLabel25.setText("/");

        mail.setFont(new java.awt.Font("Tahoma", 1, 11));
        mail.setText("Email");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Interno");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Fax");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Departamento");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Cargo");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel7.setText(cliente.getNombreFantasia());
        
        jLabel8.setText("*");

        jLabel9.setText("*");

        jLabel10.setText("*");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 9));
        jLabel11.setText("* Campos obligatorios para guardar este nuevo contacto. Nota: en el campo Telefonos solo es obligatorio por lo menos un telefono.");

        nuevo.setIcon(new javax.swing.ImageIcon(getUrlImagen("disk.png")));
        nuevo.setText("Grabar contacto");

        salir.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        salir.setText("Salir");
        
        setAddMode();
        
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(jLabel1)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel7))
                                .add(nextelRes)
                                .add(jLabel6)
                                .add(jLabel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                                .add(layout.createSequentialGroup()
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                        .add(m_cmbTitulo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                            .add(mail)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jLabel9))
                                        .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                            .add(tel)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jLabel10)))
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                            .add(departamento)
                                            .add(email, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                                            .add(layout.createSequentialGroup()
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                    .add(telefono1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 103, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                    .add(layout.createSequentialGroup()
                                                        .add(flotaNextel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                        .add(jLabel3)
                                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                        .add(idNextel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jLabel25)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(telefono2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(31, 31, 31)
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                    .add(jLabel2)
                                                    .add(jLabel4))
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                    .add(fax, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 143, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                    .add(interno, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                            .add(cargo))
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                            .add(apYNom)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jLabel8)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(apellidoYNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 364, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))))
                        .add(layout.createSequentialGroup()
                            .add(178, 178, 178)
                            .add(nuevo)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(salir)))
                    .addContainerGap(25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel1)
                        .add(jLabel7))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(apYNom)
                        .add(m_cmbTitulo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel8)
                        .add(apellidoYNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(6, 6, 6)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(mail)
                        .add(email, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel9))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel5)
                        .add(departamento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel6)
                        .add(cargo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(tel)
                        .add(telefono1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(interno, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel2)
                        .add(jLabel10)
                        .add(jLabel25)
                        .add(telefono2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(nextelRes)
                        .add(flotaNextel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel3)
                        .add(idNextel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel4)
                        .add(fax, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(19, 19, 19)
                    .add(jLabel11)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 16, Short.MAX_VALUE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(nuevo)
                        .add(salir))
                    .addContainerGap())
            );
        this.getContentPane().add(panel);
        this.pack();
        
        createListener();        
        updatePosition();
	}
    
    public void updatePosition(){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation((screenSize.width - this.getWidth())/2,
				(screenSize.height - this.getHeight())/2);
		
	}
    
    private void setAddMode() {
		this.contacto = new ClienteContacto();;
		resetFields();
	}
    
    public void setEditMode(String entityId) {
		try {			
			this.contacto = ClienteContactoManager.instance().getClienteContactoById(entityId);
			//this.cliente = ClienteManager.instance().getClienteById(contacto.getCodigoCliente());
			resetFields();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void resetFields() {
		m_cmbTitulo.setForeign(contacto.getTitulo());
		apellidoYNombre.setText(contacto.getApellidoYNombre());
		email.setText(contacto.getEmail());
		telefono1.setText(contacto.getTelefono1());
		if(contacto.getDepartamento() != null)
			departamento.setText(contacto.getDepartamento());
		if(contacto.getCargo() != null)
			cargo.setText(contacto.getCargo());		
		if(contacto.getTelefono2() != null)
			telefono2.setText(contacto.getTelefono2());
		if(contacto.getFax() != null)
			fax.setText(contacto.getFax());
		if(contacto.getInterno() != null)
			interno.setText(contacto.getInterno());
		if(contacto.getFlotaNextel() != null)
			flotaNextel.setText(contacto.getFlotaNextel().toString());
		if(contacto.getIdNextel() != null)
			idNextel.setText(contacto.getIdNextel().toString());
	}
	
	private void createListener(){
		salir.addActionListener(new SalirActionListener());
		nuevo.addActionListener(new NuevoActionListener());		
	}
	
	/**
	 * @return Returns the codContactoElegido.
	 */
	public String getCodContactoElegido() {
		return codContactoElegido;
	}

	/**
	 * @param codContactoElegido The codContactoElegido to set.
	 */
	public void setCodContactoElegido(String codContactoElegido) {
		this.codContactoElegido = codContactoElegido;
	}

	/**
	 * @return Returns the contacto.
	 */
	public ClienteContacto getContacto() {
		return contacto;
	}

	/**
	 * @param contacto The contacto to set.
	 */
	public void setContacto(ClienteContacto contacto) {
		this.contacto = contacto;
	}	
	
//************************************ACCIONES************************************************
	private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(PantallaNuevoContactoCliente.this, "¿Desea salir sin guardar cambios?", "Salir") == true){
				setVisible(false);
				
			}
		}
		
	}
	
	private class NuevoActionListener implements ActionListener{

		public boolean formValid(){
			
			if (apellidoYNombre.getText() == null || apellidoYNombre.getText().length() == 0 
				|| m_cmbTitulo.searchForeign() == null || m_cmbTitulo.searchForeign().length() == 0
				//|| cargo.getText() == null || cargo.getText().length() == 0 
				|| telefono1.getText() == null || telefono1.getText().length() == 0 
				|| email.getText() == null || email.getText().length() == 0) {

				return false;
			}
			else return true;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			if (formValid()){				
				try {			
					contacto.setCodigoCliente(cliente.getCodigo());
					contacto.setCodigoContacto("1");
					contacto.setApellidoYNombre(apellidoYNombre.getText());
					contacto.setTelefono1(telefono1.getText());
					contacto.setEmail(email.getText());
					contacto.setFechaUltimoContacto(DateConverter.convertDateToString(new Date(),"yyyy-MM-dd hh:mm:ss"));		            
		            
					contacto.setTitulo(m_cmbTitulo.searchForeign());
		            
		            if(!departamento.getText().equals(""))
		            	contacto.setDepartamento(departamento.getText());
		            if(!cargo.getText().equals(""))
		            	contacto.setCargo(cargo.getText());
		            if(!telefono2.getText().equals(""))
		            	contacto.setTelefono2(telefono2.getText());
		            if(!fax.getText().equals(""))
		            	contacto.setFax(fax.getText());
		            if(!interno.getText().equals(""))
		            	contacto.setInterno(interno.getText());
		            if(!flotaNextel.getText().equals(""))
		            	contacto.setFlotaNextel(Integer.valueOf(flotaNextel.getText()));
		            if(!idNextel.getText().equals(""))
		            	contacto.setIdNextel(Integer.valueOf(idNextel.getText()));       
		            
		            codContactoElegido = ClienteContactoManager.instance().update(contacto);
		            if(!StringUtils.isBlank(codContactoElegido))
		            	contacto.setCodigo(codContactoElegido);
		            
		            MessageUtil.showMessage(PantallaNuevoContactoCliente.this,"El contacto del cliente se cargó con éxito");

				} catch (RemoteException e) {			
					//MessageUtil.showErrorMessage(PantallaNuevoContactoCliente.this,"Error al guardar los datos");
					MessageUtil.showMessage(PantallaNuevoContactoCliente.this,"Error al cargar datos externos");
				}
				finally {
					setAddMode();
					setVisible(false);
				}
						
			}
			else {
				MessageUtil.showMessage(PantallaNuevoContactoCliente.this,"Faltan datos por completar");
			}
		}
		
	}
	
	private class RefreshAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			m_cmbTitulo.loadItems();
		}
    	
    }
}
