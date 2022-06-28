package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

import org.apache.commons.lang.StringUtils;
import org.apache.wsif.WSIFException;

import crm.client.managers.ClienteFacturacionManager;
import crm.client.managers.ClienteManager;
import crm.client.managers.NuevoClienteManager;
import crm.client.util.DateConverter;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMCodigosPostalesComboBox;
import crm.gui.components.ABMCondIVAComboBox;
import crm.gui.components.ABMLocalidadesComboBox;
import crm.gui.components.ABMPaisesComboBox;
import crm.gui.components.ABMPartidosComboBox;
import crm.gui.components.ABMProvinciasComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.ClienteFacturacion;
import crm.libraries.abm.entities.NuevoCliente;
import crm.libraries.util.MessageUtil;

public class PantallaNuevoCliente extends PantallaEmergente{
	private GradientButton btnGuardar;
    private GradientButton btnLimpiar;
    private GradientButton btnSalir;
    private GradientButton btnBuscarLocalidades;
    private GradientButton btnBuscarLocalidadesFac;
    private javax.swing.JFormattedTextField f_cmbFacCodProveedor;
    private javax.swing.JFormattedTextField f_txtFacCalle;
    private javax.swing.JFormattedTextField f_txtFacDomicilioPago;
    private javax.swing.JFormattedTextField f_txtFacDpto;
    private javax.swing.JFormattedTextField f_txtFacFechaHoraPago;
    private javax.swing.JFormattedTextField f_txtFacNumero;
    private javax.swing.JFormattedTextField f_txtFacPiso;
    private javax.swing.JFormattedTextField m_txtNroDomicilioPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private ABMCondIVAComboBox m_cmbCondIVA;
    private ABMCodigosPostalesComboBox m_cmbFacCodigoPostal;
	private ABMLocalidadesComboBox m_cmbFacLocalidad;
	private ABMPartidosComboBox m_cmbFacPartido;
	private ABMProvinciasComboBox m_cmbFacProvincia;
	private ABMPaisesComboBox m_cmbFacPais;
    private ABMCodigosPostalesComboBox m_cmbCodigoPostal;
	private ABMLocalidadesComboBox m_cmbLocalidad;
	private ABMPartidosComboBox m_cmbPartido;
	private ABMProvinciasComboBox m_cmbProvincia;
	private ABMPaisesComboBox m_cmbPais;
    private javax.swing.JFormattedTextField m_txtCalle;
    private javax.swing.JFormattedTextField m_txtCodBejerman;
    private javax.swing.JFormattedTextField m_txtDigVerificadorCuit;
    private javax.swing.JFormattedTextField m_txtDpto;
    private javax.swing.JFormattedTextField m_txtEmpresa;
    private javax.swing.JFormattedTextField m_txtInscripcionCuit;
    private javax.swing.JFormattedTextField m_txtNombreFantasia;
    private javax.swing.JFormattedTextField m_txtNumero;
    private javax.swing.JFormattedTextField m_txtPagoContacto;
    private javax.swing.JFormattedTextField m_txtPagoTelefono;
    private javax.swing.JFormattedTextField m_txtPiso;
    private javax.swing.JFormattedTextField m_txtPrefijoCuit;
    
    private Cliente cliente;
	private ClienteFacturacion clienteFacturacion;
	private NuevoCliente nuevoCliente;
	private String paisId;
	private String provinciaId;
	private String partidoId;
	private String localidadId;
	private String cp;
	private String facPaisId;
	private String facProvinciaId;
	private String facPartidoId;
	private String facLocalidadId;
    
	private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
	
    public PantallaNuevoCliente(Frame owner){		
		super("Nuevo cliente",owner);
		setMinimumSize(new Dimension(800,600));
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
        jMenuItem2.addActionListener(new SalirAction());
        jMenu1.add(jMenuItem2);
        
        jMenuBar1.add(jMenu1);
		
        this.setJMenuBar(jMenuBar1);   
    }
    
    public void initComponents() {
    	
    	PanelImagen panel = null;
    	try{
			panel = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
    	
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        m_cmbPais = new ABMPaisesComboBox();
        jLabel17 = new javax.swing.JLabel();
        m_cmbProvincia = new ABMProvinciasComboBox();
        jLabel18 = new javax.swing.JLabel();
        m_cmbPartido = new ABMPartidosComboBox();
        jLabel19 = new javax.swing.JLabel();
        m_cmbLocalidad = new ABMLocalidadesComboBox();
        jLabel20 = new javax.swing.JLabel();
        m_cmbCodigoPostal = new ABMCodigosPostalesComboBox();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        m_cmbFacPais = new ABMPaisesComboBox();
        jLabel27 = new javax.swing.JLabel();
        m_cmbFacProvincia = new ABMProvinciasComboBox();
        jLabel28 = new javax.swing.JLabel();
        m_cmbFacPartido = new ABMPartidosComboBox();
        jLabel29 = new javax.swing.JLabel();
        m_cmbFacLocalidad = new ABMLocalidadesComboBox();
        jLabel30 = new javax.swing.JLabel();
        m_cmbFacCodigoPostal = new ABMCodigosPostalesComboBox();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        m_txtNroDomicilioPago = new javax.swing.JFormattedTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        btnLimpiar = new GradientButton("", Color.blue);
        btnSalir = new GradientButton("", Color.blue);
        btnGuardar = new GradientButton("", Color.blue);
        btnBuscarLocalidades = new GradientButton("", Color.blue);
        btnBuscarLocalidadesFac = new GradientButton("", Color.blue);
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        m_cmbCondIVA = new ABMCondIVAComboBox();
        jLabel40 = new javax.swing.JLabel();
        
        m_txtCodBejerman = CustomTextField.getMediumIntInstance();
		m_txtEmpresa = CustomTextField.getRegularStringInstance(150);
		m_txtPrefijoCuit = CustomTextField.getPrefijoCuitInstance();
		m_txtInscripcionCuit = CustomTextField.getInscripcionCuitInstance();
		m_txtDigVerificadorCuit = CustomTextField.getDigerificadorInstance();		
		m_txtNombreFantasia = CustomTextField.getRegularStringInstance(150);		
		m_txtPagoContacto = CustomTextField.getRegularStringInstance(50);
		m_txtPagoTelefono = CustomTextField.getPhoneNumberInstance();
		
		m_txtCalle = CustomTextField.getRegularStringInstance(50);
		m_txtNumero = CustomTextField.getSmallIntInstance();
		m_txtPiso = CustomTextField.getPisoStringInstance();
		m_txtDpto = CustomTextField.getDeptoStringInstance();
		
		f_txtFacCalle = CustomTextField.getRegularStringInstance(50);
		f_txtFacNumero = CustomTextField.getSmallIntInstance();
		f_txtFacPiso = CustomTextField.getPisoStringInstance();
		f_txtFacDpto = CustomTextField.getDeptoStringInstance();
		
		f_txtFacDomicilioPago = CustomTextField.getRegularStringInstance(80);
		f_cmbFacCodProveedor = CustomTextField.getRegularStringInstance(20);
		f_txtFacFechaHoraPago = CustomTextField.getRegularStringInstance(100);
        
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de un nuevo cliente");

        jLabel2.setText("Nombre de la empresa");

        jLabel3.setText("Nombre de fantas\u00eda");

        jLabel4.setText("*");

        jLabel5.setText("*");

        jLabel6.setText("CUIT");

        jLabel8.setText("-");

        jLabel10.setText("-");

        jLabel9.setText("Nombre del contacto de pago");

        jLabel39.setText("Tel\u00e9fono");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Domicilio para la entrega de la factura");

        jLabel12.setText("Calle");

        jLabel13.setText("Nro");

        jLabel14.setText("Piso");

        jLabel15.setText("Depto");

        jLabel16.setText("Pa\u00eds");        

        jLabel17.setText("Provincia");

        jLabel18.setText("Partido");        

        jLabel19.setText("Localidad");        

        jLabel20.setText("C\u00f3digo postal");       

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel21.setText("Domicilio Legal");

        jLabel22.setText("Calle");

        jLabel23.setText("Nro");

        jLabel24.setText("Piso");

        jLabel25.setText("Depto");

        jLabel26.setText("Pa\u00eds");       
        
        jLabel27.setText("Provincia");       
        
        jLabel28.setText("Partido");
        
        jLabel29.setText("Localidad");       
        
        jLabel30.setText("C\u00f3digo postal");   
        
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel31.setText("Datos de facturaci\u00f3n");

        jLabel32.setText("Domicilio de pago");

        jLabel33.setText("Nro");

        jLabel34.setText("C\u00f3digo de proveedor CRN");

        jLabel35.setText("D\u00eda y hora de pago ");

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form.png")));
        btnLimpiar.setText("Limpiar");

        btnSalir.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_down.png")));
        btnSalir.setText("Salir");

        btnGuardar.setIcon(new javax.swing.ImageIcon(getUrlImagen("disk.png")));
        btnGuardar.setText("Guardar");
        
        btnBuscarLocalidades.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        btnBuscarLocalidades.setText("Buscar Localidad");
        
        btnBuscarLocalidadesFac.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        btnBuscarLocalidadesFac.setText("Buscar Localidad");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel36.setText("*Nota: estos campos son obligatorios para la grabaci\u00f3n del nuevo cliente, con excepci\u00f3n del c\u00f3digo postal. Todos los datos de este formulario son obligatorios para la confirmación del evento.");

        jLabel37.setText("*");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel38.setText("Datos generales del cliente");

        jLabel7.setText("Condici\u00f3n del IVA");         

        jLabel40.setText("C\u00f3digo Bejerman");
        
        createListeners();
        initFacLocationFields();
        initLocationFields();
        
        setAddMode();
        
        m_cmbCondIVA.loadItems();
		m_cmbPais.loadItems();
		m_cmbFacPais.loadItems();
		
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(jLabel36)
                            .addContainerGap(509, Short.MAX_VALUE))
                        .add(layout.createSequentialGroup()
                            .add(jLabel38)
                            .addContainerGap(880, Short.MAX_VALUE))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel1)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                            .add(jLabel9)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_txtPagoContacto, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE))
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(jLabel2)
                                                .add(jLabel3))
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(layout.createSequentialGroup()
                                                    .add(m_txtNombreFantasia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 426, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                    .add(jLabel5)
                                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 62, Short.MAX_VALUE)
                                                    .add(jLabel7))
                                                .add(m_txtEmpresa, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))))
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel4)
                                    .add(1, 1, 1)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_cmbCondIVA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 249, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(layout.createSequentialGroup()
                                                .add(54, 54, 54)
                                                .add(jLabel6)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(m_txtPrefijoCuit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jLabel8)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(m_txtInscripcionCuit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(7, 7, 7)
                                                .add(jLabel10)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(m_txtDigVerificadorCuit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                            .add(layout.createSequentialGroup()
                                                .add(55, 55, 55)
                                                .add(jLabel39)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(m_txtPagoTelefono, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 167, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                                    .add(62, 62, 62))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .add(jLabel11)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel37))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jLabel12)
                                        .add(jLabel16))
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                            .add(m_txtCalle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 475, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(41, 41, 41)
                                            .add(jLabel13)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_txtNumero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(36, 36, 36)
                                            .add(jLabel14)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_txtPiso, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(48, 48, 48)
                                            .add(jLabel15)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_txtDpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(layout.createSequentialGroup()
                                            .add(m_cmbPais, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 245, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(26, 26, 26)
                                            .add(jLabel17)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_cmbProvincia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 244, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(30, 30, 30)
                                            .add(jLabel18)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(btnBuscarLocalidades)
                                                .add(m_cmbPartido, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 243, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                                    .add(127, 127, 127))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .add(jLabel19)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_cmbLocalidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 248, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(31, 31, 31)
                                    .add(jLabel20)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_cmbCodigoPostal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 244, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel21)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .add(jLabel22)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(f_txtFacCalle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 473, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(42, 42, 42)
                                    .add(jLabel23)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(f_txtFacNumero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 58, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(40, 40, 40)
                                    .add(jLabel24)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(f_txtFacPiso, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(47, 47, 47)
                                    .add(jLabel25)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(f_txtFacDpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel31)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                            .add(jLabel35)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(f_txtFacFechaHoraPago))
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                            .add(jLabel32)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(f_txtFacDomicilioPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 494, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                    .add(31, 31, 31)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                            .add(jLabel33)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_txtNroDomicilioPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(33, 33, 33)
                                            .add(jLabel34)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(f_cmbFacCodProveedor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(layout.createSequentialGroup()
                                            .add(jLabel40)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_txtCodBejerman, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                    .add(63, 63, 63))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator4)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator3)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator2)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator5)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 993, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                            .add(jLabel26)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_cmbFacPais, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 248, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(25, 25, 25)
                                            .add(jLabel27)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_cmbFacProvincia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 246, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(27, 27, 27)
                                            .add(jLabel28))
                                        .add(layout.createSequentialGroup()
                                            .add(jLabel29)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_cmbFacLocalidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 248, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(29, 29, 29)
                                            .add(jLabel30)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_cmbFacCodigoPostal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 246, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(btnBuscarLocalidadesFac)
                                        .add(m_cmbFacPartido, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 246, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                            .add(0, 0, 0))))
                .add(layout.createSequentialGroup()
                    .add(351, 351, 351)
                    .add(btnGuardar)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(btnLimpiar)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(btnSalir)
                    .addContainerGap(425, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel1)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel38)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel2)
                        .add(jLabel6)
                        .add(m_txtPrefijoCuit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel8)
                        .add(m_txtInscripcionCuit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel10)
                        .add(m_txtDigVerificadorCuit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(m_txtEmpresa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel4))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel3)
                        .add(m_txtNombreFantasia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel5)
                        .add(jLabel7)
                        .add(m_cmbCondIVA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel9)
                        .add(jLabel39)
                        .add(m_txtPagoTelefono, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(m_txtPagoContacto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel11)
                        .add(jLabel37))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel12)
                        .add(m_txtCalle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel13)
                        .add(m_txtNumero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel14)
                        .add(m_txtPiso, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel15)
                        .add(m_txtDpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel16)
                        .add(m_cmbPais, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel17)
                        .add(m_cmbProvincia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel18)
                        .add(m_cmbPartido, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel19)
                        .add(m_cmbLocalidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel20)
                        .add(m_cmbCodigoPostal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(btnBuscarLocalidades))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel21)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel22)
                        .add(f_txtFacCalle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel23)
                        .add(f_txtFacNumero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel24)
                        .add(f_txtFacPiso, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel25)
                        .add(f_txtFacDpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel26)
                        .add(m_cmbFacPais, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel27)
                        .add(m_cmbFacProvincia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel28)
                        .add(m_cmbFacPartido, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel29)
                        .add(m_cmbFacLocalidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel30)
                        .add(m_cmbFacCodigoPostal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(btnBuscarLocalidadesFac))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel31)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel32)
                        .add(f_txtFacDomicilioPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel33)
                        .add(m_txtNroDomicilioPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel34)
                        .add(f_cmbFacCodProveedor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel35)
                        .add(f_txtFacFechaHoraPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel40)
                        .add(m_txtCodBejerman, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel36)
                    .add(17, 17, 17)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(btnGuardar)
                        .add(btnLimpiar)
                        .add(btnSalir))
                    .addContainerGap())
            );
        
        this.getContentPane().add(panel);
        this.pack();
    }
    
    private void setAddMode() {
		this.cliente = new Cliente();
		this.clienteFacturacion = new ClienteFacturacion();
		this.nuevoCliente = new NuevoCliente();
		resetFields();
	}

	public void setEditMode(String entityId) {
		try {
			this.cliente = ClienteManager.instance().getClienteById(entityId);
			this.clienteFacturacion = ClienteFacturacionManager.instance()
					.getClienteFacturacionById(entityId);
			this.nuevoCliente = NuevoClienteManager.instance()
					.getNuevoClienteById(entityId);
			resetFields();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void resetFields() {
		m_txtCodBejerman.setText(this.cliente.getCodigoBejerman());
		m_txtEmpresa.setText(this.cliente.getEmpresa());
		m_txtCalle.setText(this.cliente.getCalle());
		m_txtNumero.setText(this.cliente.getNumero());
		m_txtPiso.setText(this.cliente.getPiso());
		m_txtDpto.setText(this.cliente.getDepartamento());
		// m_txtCUIT.setText(this.cliente.getCuit());
		if (this.cliente.getCuit() != null && Util.isCuit(this.cliente.getCuit())) {
			m_txtPrefijoCuit.setText(this.cliente.getCuit().substring(0, 2));
			m_txtInscripcionCuit.setText(this.cliente.getCuit()
					.substring(2, 10));
			m_txtDigVerificadorCuit.setText(this.cliente.getCuit().substring(
					10, 11));
		} else {
			m_txtPrefijoCuit.setText("");
			m_txtInscripcionCuit.setText("");
			m_txtDigVerificadorCuit.setText("");
		}
		m_txtNombreFantasia.setText(this.cliente.getNombreFantasia());
		m_txtPagoContacto.setText(this.cliente.getPagoContacto());
		m_txtPagoTelefono.setText(this.cliente.getPagoTelefono());
		m_cmbPais.setForeign(this.cliente.getPais());
		m_cmbProvincia.setForeign(this.cliente.getProvincia());
		m_cmbPartido.setForeign(this.cliente.getPartido());
		m_cmbLocalidad.setForeign(this.cliente.getLocalidad());
		m_cmbCodigoPostal.setForeign(this.cliente.getCodigoPostal());
		m_cmbCondIVA.setForeign(this.cliente.getIva());

		// facturacion
		if (this.clienteFacturacion == null) {
			this.clienteFacturacion = new ClienteFacturacion();
		}
		f_txtFacCalle.setText(this.clienteFacturacion.getCalle());
		f_txtFacNumero.setText(this.clienteFacturacion.getNumero());
		f_txtFacPiso.setText(this.clienteFacturacion.getPiso());
		f_txtFacDpto.setText(this.clienteFacturacion.getDepto());
		
		String domicio = this.clienteFacturacion.getDomicilioPago();
		if(domicio != null && !Util.isBlank(domicio)){
			f_txtFacDomicilioPago.setText(domicio.substring(0,domicio.lastIndexOf(" ")));		
			m_txtNroDomicilioPago.setText(domicio.substring(domicio.lastIndexOf(" "), domicio.length()));
		}
		
		f_txtFacFechaHoraPago.setText(this.clienteFacturacion.getDiaHoraPago());
		f_cmbFacCodProveedor.setText(this.clienteFacturacion.getCodProveedor());
		m_cmbFacPais.setForeign(this.clienteFacturacion.getPais());
		m_cmbFacProvincia.setForeign(this.clienteFacturacion.getProvincia());
		m_cmbFacPartido.setForeign(this.clienteFacturacion.getPartido());
		m_cmbFacLocalidad.setForeign(this.clienteFacturacion.getLocalidad());
		m_cmbFacCodigoPostal.setForeign(this.clienteFacturacion
				.getCodigoPostal());

		// nuevo cliente
		if (this.nuevoCliente == null) {
			this.nuevoCliente = new NuevoCliente();
		}

	}
	
    private boolean isFormIncomplete() {
		String emp = m_txtEmpresa.getText();
		String calle = m_txtCalle.getText();
		String nro = m_txtNumero.getText();
		String loc = (String) m_cmbLocalidad.searchForeign();
		String nFantasia = m_txtNombreFantasia.getText();

		return emp == null || emp.length() == 0 || 
				calle == null || calle.length() == 0
				|| nro == null || nro.length() == 0
				|| loc == null || loc.length() == 0
				|| nFantasia == null || nFantasia.length() == 0;
	}

	public void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
		String codBejerman = m_txtCodBejerman.getText();
		String emp = m_txtEmpresa.getText();
		String calle = m_txtCalle.getText();
		String nro = m_txtNumero.getText();
		String piso = m_txtPiso.getText();
		String dpto = m_txtDpto.getText();
		String cp = m_cmbCodigoPostal.searchForeign();
		String loc = (String) m_cmbLocalidad.searchForeign();
		String provincia = (String) m_cmbProvincia.searchForeign();
		String partido = (String) m_cmbPartido.searchForeign();
		String pais = (String) m_cmbPais.searchForeign();
		// String cuit = m_txtCUIT.getText();
		String aux = m_txtPrefijoCuit.getText();
		String aux2 = aux.concat(m_txtInscripcionCuit.getText());
		String cuit = aux2.concat(m_txtDigVerificadorCuit.getText());
		String civa = m_cmbCondIVA.searchForeign();
		String pcon = m_txtPagoContacto.getText();
		String ptel = m_txtPagoTelefono.getText();
		String nFantasia = m_txtNombreFantasia.getText();

		// facturacion
		String facPais = this.m_cmbFacPais.searchForeign();
		String facProvincia = this.m_cmbFacProvincia.searchForeign();
		String facPartido = this.m_cmbFacPartido.searchForeign();
		String facLocalidad = this.m_cmbFacLocalidad.searchForeign();
		String facCalle = f_txtFacCalle.getText();
		String facNumero = f_txtFacNumero.getText();
		String facPiso = f_txtFacPiso.getText();
		String facDpto = f_txtFacDpto.getText();
		String facDomicilioPago = f_txtFacDomicilioPago.getText();
		String facCodProveedor = f_cmbFacCodProveedor.getText();
		String facCodigoPostal = m_cmbFacCodigoPostal.searchForeign();
		String facDiaHoraPago = f_txtFacFechaHoraPago.getText();
		String facNroDomicPago = m_txtNroDomicilioPago.getText();

		if (isFormIncomplete()) {
			Util.errMsg(PantallaNuevoCliente.this, "Tiene datos obligatorios por completar", null);
			return;
		}

		try {
			if (codBejerman.equals("")){
				this.cliente.setCodigoBejerman(null);
			}
			else
				this.cliente.setCodigoBejerman(codBejerman);
			this.cliente.setEmpresa(emp);
			this.cliente.setCalle(calle);
			this.cliente.setNumero(nro);
			this.cliente.setPiso(piso);
			this.cliente.setDepartamento(dpto);
			this.cliente.setCodigoPostal(cp);
			this.cliente.setLocalidad(loc);
			this.cliente.setPartido(partido);
			this.cliente.setProvincia(provincia);
			this.cliente.setPais(pais);
			if (cuit.equals("")){
				this.cliente.setCuit(null);
			}
			else
				this.cliente.setCuit(cuit);
			this.cliente.setPagoTelefono(ptel);
			this.cliente.setPagoContacto(pcon);
			this.cliente.setNombreFantasia(nFantasia);
			this.cliente.setIva(civa);
			this.cliente.setFechaModificacion(DateConverter.convertDateToString(new Date(),"yyyy-MM-dd hh:mm:ss"));
			String clienteID = ClienteManager.instance().update(this.cliente);

			if (!StringUtils.isBlank(clienteID)) {
				this.cliente.setCodigo(clienteID);

				// Facturacion
				this.clienteFacturacion.setCodigo(clienteID);
				this.clienteFacturacion.setPais(facPais);
				this.clienteFacturacion.setProvincia(facProvincia);
				this.clienteFacturacion.setPartido(facPartido);
				this.clienteFacturacion.setLocalidad(facLocalidad);
				this.clienteFacturacion.setCalle(facCalle);
				if (facNumero.equals(""))
					this.clienteFacturacion.setNumero(null);
				else
					this.clienteFacturacion.setNumero(facNumero);
				this.clienteFacturacion.setPiso(facPiso);
				this.clienteFacturacion.setDepto(facDpto);
				this.clienteFacturacion.setDomicilioPago(facDomicilioPago+" "+facNroDomicPago);
				this.clienteFacturacion.setCodProveedor(facCodProveedor);
				this.clienteFacturacion.setCodigoPostal(facCodigoPostal);
				this.clienteFacturacion.setDiaHoraPago(facDiaHoraPago);
				ClienteFacturacionManager.instance().update(
						this.clienteFacturacion);

				// nuevgo cliente
				this.nuevoCliente.setCodigo(clienteID);
				this.nuevoCliente.setFechaAlta(DateConverter.convertDateToString(new Date(),"yyyy-MM-dd hh:mm:ss"));
				this.nuevoCliente.setNuevo("S");
				NuevoClienteManager.instance().update(this.nuevoCliente);

				Util.alertMsg(this, "El cliente se cargó con éxito");
				
			}
		} catch (WSIFException e) {
			Util.errMsg(this, "Error al guardar el cliente",e);
		} catch (RemoteException e) {
			Util.errMsg(this, "Error al guardar el cliente",e);
		} finally {
			setAddMode();
			setVisible(false);
		}

	}
    
    private void initLocationFields() {
    	m_cmbProvincia.setEnabled(false);
		m_cmbPartido.setEnabled(false);
		m_cmbLocalidad.setEnabled(false);
		m_cmbCodigoPostal.setEnabled(false);

		createLocationListeners();

	}
    
    private void createListeners(){
    	btnSalir.addActionListener(new SalirAction());
    	btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {                
            	btnAgregarActionPerformed(evt);
            }
        });
    	btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {                
            	setAddMode();
            }
        });

		/*this.m_txtCalle.addKeyListener(new CalleKeyListener());
		this.m_txtDpto.addKeyListener(new DeptoKeyListener());
		this.m_txtNumero.addKeyListener(new NroKeyListener());
		this.m_txtPiso.addKeyListener(new PisoKeyListener());*/
    }
    
    private void createLocationListeners() {
		this.m_cmbPais.addActionListener(new PaisActionListener());
		this.m_cmbProvincia.addActionListener(new ProvinciaActionListener());
		this.m_cmbPartido.addActionListener(new PartidoActionListener());
		this.m_cmbLocalidad.addActionListener(new LocalidadActionListener());
		this.m_cmbCodigoPostal.addActionListener(new CPActionListener());
		btnBuscarLocalidades.addActionListener(new BuscarLocalidadActionListener());
		btnBuscarLocalidadesFac.addActionListener(new BuscarLocalidadFacActionListener());
	}

	private void initFacLocationFields() {
		m_cmbFacProvincia.setEnabled(false);
		m_cmbFacPartido.setEnabled(false);
		m_cmbFacLocalidad.setEnabled(false);
		m_cmbFacCodigoPostal.setEnabled(false);

		createFacLocationListeners();

	}

	private void createFacLocationListeners() {
		this.m_cmbFacPais.addActionListener(new PaisFacActionListener());
		this.m_cmbFacProvincia
				.addActionListener(new ProvinciaFacActionListener());
		this.m_cmbFacPartido.addActionListener(new PartidoFacActionListener());
		this.m_cmbFacLocalidad
				.addActionListener(new LocalidadFacActionListener());
	}
    
    //*********************************ACCIONES***********************************************
    private class SalirAction implements ActionListener {
		public void actionPerformed (ActionEvent evt) {
			if (MessageUtil.showYesNoMessage(PantallaNuevoCliente.this, "¿Desea salir sin grabar el cliente?", "Salir")){
				System.gc();
				setVisible(false);				
			}
		}
	}
    
    private class CalleKeyListener implements KeyListener{

		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent arg0) {
			f_txtFacCalle.setText(m_txtCalle.getText());			
		}
    	
    }
    
    private class NroKeyListener implements KeyListener{

		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent arg0) {
			f_txtFacNumero.setText(m_txtNumero.getText());			
		}
    	
    }
    
    private class PisoKeyListener implements KeyListener{

		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent arg0) {
			f_txtFacPiso.setText(m_txtPiso.getText());			
		}
    	
    }
    
    private class DeptoKeyListener implements KeyListener{

		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent arg0) {
			f_txtFacDpto.setText(m_txtDpto.getText());			
		}
    	
    }
    
    private class PaisActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (m_cmbPais.getSelectedIndex() > 0) {
				String newId = m_cmbPais.searchForeign();
				if (paisId == null || !paisId.equals(newId)) {
					m_cmbProvincia.loadItems(newId);
					m_cmbPartido.resetFields();
					m_cmbLocalidad.resetFields();
					//m_cmbFacPais.setForeign(newId);
				}
				paisId = newId;
				provinciaId=null;
				partidoId=null;
				localidadId=null;
				m_cmbProvincia.setEnabled(true);
			} else{
				m_cmbProvincia.setEnabled(false);
								
			}
			
		}
	}

	private class ProvinciaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (m_cmbProvincia.getSelectedIndex() > 0) {
				String newId = m_cmbProvincia.searchForeign();
				if (provinciaId == null || !provinciaId.equals(newId)) {
					m_cmbPartido.loadItems(newId);
					m_cmbLocalidad.resetFields();
					//m_cmbFacProvincia.setForeign(newId);
				}
				provinciaId = newId;
				partidoId=null;
				localidadId=null;
				m_cmbPartido.setEnabled(true);
			} else
				m_cmbPartido.setEnabled(false);
		}
	}

	private class PartidoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (m_cmbPartido.getSelectedIndex() > 0) {
				String newId = m_cmbPartido.searchForeign();
				if (partidoId == null || !partidoId.equals(newId)) {
					m_cmbLocalidad.loadItems(m_cmbPartido.searchForeign());
					//m_cmbFacPartido.setForeign(newId);
				}
				partidoId = newId;				
				localidadId=null;
				m_cmbLocalidad.setEnabled(true);
			} else
				m_cmbLocalidad.setEnabled(false);
		}
	}

	private class LocalidadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (m_cmbLocalidad.getSelectedIndex() > 0) {
				String newId = m_cmbLocalidad.searchForeign();
				if (localidadId == null || !localidadId.equals(newId)) {
					m_cmbCodigoPostal.loadItems(m_cmbLocalidad.searchForeign());
					//m_cmbFacLocalidad.setForeign(newId);
				}
				localidadId = newId;
				m_cmbCodigoPostal.setEnabled(true);
			} else
				m_cmbCodigoPostal.setEnabled(false);
		}
	}
	
	private class CPActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (m_cmbCodigoPostal.getSelectedIndex() > 0) {
				String newId = m_cmbCodigoPostal.searchForeign();
				if (cp == null || !cp.equals(newId)) {
					//m_cmbFacCodigoPostal.setForeign(newId);
				}
				cp = newId;
			}
		}
	}

	private class PaisFacActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (m_cmbFacPais.getSelectedIndex() > 0) {
				String newId = m_cmbFacPais.searchForeign();
				if (facPaisId == null || !facPaisId.equals(newId)) {
					m_cmbFacProvincia.loadItems(newId);
					m_cmbFacPartido.resetFields();
					m_cmbFacLocalidad.resetFields();
				}
				facPaisId = newId;
				facProvinciaId=null;
				facPartidoId=null;
				facLocalidadId=null;
				m_cmbFacProvincia.setEnabled(true);
			} else
				m_cmbFacProvincia.setEnabled(false);
		}
	}

	private class ProvinciaFacActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (m_cmbFacProvincia.getSelectedIndex() > 0) {
				String newId = m_cmbFacProvincia.searchForeign();
				if (facProvinciaId == null || !facProvinciaId.equals(newId)) {
					m_cmbFacPartido.loadItems(newId);
					m_cmbFacLocalidad.resetFields();
				}
				facProvinciaId = newId;
				facPartidoId=null;
				facLocalidadId=null;
				m_cmbFacPartido.setEnabled(true);
			} else
				m_cmbFacPartido.setEnabled(false);
		}
	}

	private class PartidoFacActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (m_cmbFacPartido.getSelectedIndex() > 0) {
				String newId = m_cmbFacPartido.searchForeign();
				if (facPartidoId == null || !facPartidoId.equals(newId)) {
					m_cmbFacLocalidad
							.loadItems(m_cmbFacPartido.searchForeign());
				}
				facPartidoId = newId;
				facLocalidadId=null;
				m_cmbFacLocalidad.setEnabled(true);
			} else
				m_cmbFacLocalidad.setEnabled(false);
		}
	}

	private class LocalidadFacActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (m_cmbFacLocalidad.getSelectedIndex() > 0) {
				String newId = m_cmbFacLocalidad.searchForeign();
				if (facLocalidadId == null || !facLocalidadId.equals(newId)) {
					m_cmbFacCodigoPostal.loadItems(m_cmbFacLocalidad
							.searchForeign());
				}
				facLocalidadId = newId;
				m_cmbFacCodigoPostal.setEnabled(true);
			} else
				m_cmbFacCodigoPostal.setEnabled(false);
		}
	}
	
	private class RefreshAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			//ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_DATA);
		//	ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			//new Thread(new Runnable() {	
		//		public void run() {	
					m_cmbCondIVA.loadItems();
					if(m_cmbPais.isEnabled()){
						m_cmbPais.loadItems();
					}
					else if(m_cmbProvincia.isEnabled() && StringUtils.isBlank(m_cmbPais.searchForeign())){
						m_cmbProvincia.loadItems(m_cmbPais.searchForeign());
					}
					else if(m_cmbPartido.isEnabled() && StringUtils.isBlank(m_cmbProvincia.searchForeign())){
						m_cmbPartido.loadItems(m_cmbProvincia.searchForeign());
					}
					else if(m_cmbLocalidad.isEnabled() && StringUtils.isBlank(m_cmbPartido.searchForeign())){
						m_cmbLocalidad.loadItems(m_cmbPartido.searchForeign());
					}
					else if(m_cmbCodigoPostal.isEnabled() && StringUtils.isBlank(m_cmbLocalidad.searchForeign())){
						m_cmbCodigoPostal.loadItems(m_cmbLocalidad.searchForeign());
					}
					
					if(m_cmbFacPais.isEnabled()){
						m_cmbFacPais.loadItems();
					}
					else if(m_cmbFacProvincia.isEnabled() && StringUtils.isBlank(m_cmbFacPais.searchForeign())){
						m_cmbFacProvincia.loadItems(m_cmbFacPais.searchForeign());
					}
					else if(m_cmbFacPartido.isEnabled() && StringUtils.isBlank(m_cmbFacProvincia.searchForeign())){
						m_cmbFacPartido.loadItems(m_cmbFacProvincia.searchForeign());
					}
					else if(m_cmbFacLocalidad.isEnabled() && StringUtils.isBlank(m_cmbFacPartido.searchForeign())){
						m_cmbFacLocalidad.loadItems(m_cmbFacPartido.searchForeign());
					}
					else if(m_cmbFacCodigoPostal.isEnabled() && StringUtils.isBlank(m_cmbFacLocalidad.searchForeign())){
						m_cmbFacCodigoPostal.loadItems(m_cmbFacLocalidad.searchForeign());
					}
					
			//		ProgressDialogUtil.closeProcessDialog();
		//		}
		//	}).start(); 
		}
    	
    }
	
	private class BuscarLocalidadActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			BuscadorLocalidades a = new BuscadorLocalidades(Main.getVentana());
			a.initComponents();
			//a.setupTableActivador(Long.parseLong(getCodigoVendedor()));
			a.setVisible(true);
			if(a.getItemSeleccionado() != null){
				m_cmbPais.setEnabled(true);
				m_cmbPais.setForeign(a.getItemSeleccionado().getIdPais());
				m_cmbProvincia.setEnabled(true);
				m_cmbProvincia.setForeign(a.getItemSeleccionado().getIdProvincia());
				m_cmbPartido.setEnabled(true);
				m_cmbPartido.setForeign(a.getItemSeleccionado().getIdPartido());
				m_cmbLocalidad.setEnabled(true);
				m_cmbLocalidad.setForeign(a.getItemSeleccionado().getIdLocalidad());
			}
		}
		
	}
	
	private class BuscarLocalidadFacActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			BuscadorLocalidades a = new BuscadorLocalidades(Main.getVentana());
			a.initComponents();
			//a.setupTableActivador(Long.parseLong(getCodigoVendedor()));
			a.setVisible(true);
			if(a.getItemSeleccionado() != null){
				m_cmbFacPais.setEnabled(true);
				m_cmbFacPais.setForeign(a.getItemSeleccionado().getIdPais());
				m_cmbFacProvincia.setEnabled(true);
				m_cmbFacProvincia.setForeign(a.getItemSeleccionado().getIdProvincia());
				m_cmbFacPartido.setEnabled(true);
				m_cmbFacPartido.setForeign(a.getItemSeleccionado().getIdPartido());
				m_cmbFacLocalidad.setEnabled(true);
				m_cmbFacLocalidad.setForeign(a.getItemSeleccionado().getIdLocalidad());
			}
		}
		
	}
}
