package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.ClienteContactoManager;
import crm.client.managers.ComisionManager;
import crm.client.managers.LugarEventoManager;
import crm.client.managers.SalaLugarManager;
import crm.client.managers.VendedorManager;
import crm.client.util.DateConverter;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMCodigosPostalesComboBox;
import crm.gui.components.ABMLocalidadesComboBox;
import crm.gui.components.ABMPaisesComboBox;
import crm.gui.components.ABMPartidosComboBox;
import crm.gui.components.ABMProvinciasComboBox;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.tablerenderer.nuevoLugar.NuevoLugarItem;
import crm.gui.tablerenderer.nuevoLugar.NuevoLugarTableModel;
import crm.gui.tablerenderer.nuevoLugar.TableRenderNuevoLugar;
import crm.libraries.abm.entities.ClienteContacto;
import crm.libraries.abm.entities.Comision;
import crm.libraries.abm.entities.LugarEvento;
import crm.libraries.abm.entities.SalaLugar;
import crm.libraries.abm.entities.Vendedor;
import crm.libraries.presupuestos.PresupuestoAbstracto.Sala;
import crm.libraries.util.MessageUtil;
import crm.services.sei.CategVendedorManagerSEI;

public class PantallaNuevoLugar extends PantallaEmergente{
	
	private GradientButton agregar;
	private GradientButton buscarLocalidad;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private ABMCodigosPostalesComboBox m_cmbCodigoPostal;
    private ABMLocalidadesComboBox m_cmbLocalidad;
    private ABMPaisesComboBox m_cmbPais;
    private ABMPartidosComboBox m_cmbPartido;
    private ABMProvinciasComboBox m_cmbProvincia;
    private javax.swing.JFormattedTextField m_txtCalle;
    private javax.swing.JFormattedTextField m_txtDepartamento;
    private javax.swing.JFormattedTextField m_txtEmail;
    private javax.swing.JFormattedTextField m_txtEmailOS;
    private javax.swing.JFormattedTextField m_txtFax;
    private javax.swing.JFormattedTextField m_txtNro;
    private javax.swing.JFormattedTextField m_txtPiso;
    private javax.swing.JFormattedTextField m_txtTel1;
    private javax.swing.JFormattedTextField m_txtTel2;
    private GradientButton nuevo;
    private GradientButton salir;
    private TableRenderNuevoLugar tablaSalas;
    private javax.swing.JFormattedTextField m_txtLugarNombre;
	
    private int nroSala;
    private String localidadId;
    private String partidoId;
    private String provinciaId;
    private String paisId;
	private JPopupMenu popup;
	private LugarEvento lugarEvento;
	private String codLugarElegido;
	private Object[] salas;
	//private Vendedor vendedor;
	
	private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
	
    private String comision;
    
	public PantallaNuevoLugar(Frame owner){
		
		super("Lugar de evento",owner);
        nroSala = 1;
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
		//JPanel panel = m_interface.createTitlePanel("Seleccione el presupuesto a cerrar");
		
		//**************Crecion de panel contenedor con imagen de fondo**********************
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
        salir = new GradientButton("",Color.blue);
        jLabel2 = new javax.swing.JLabel();
        m_txtLugarNombre = new javax.swing.JFormattedTextField();

        tablaSalas = new TableRenderNuevoLugar();
        popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Agregar sala");
		menuItem.setIcon(new javax.swing.ImageIcon(getUrlImagen("add.png")));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_A, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
	    menuItem.addActionListener(new AgregarActionListener());
	    popup.add(menuItem);
	    menuItem = new JMenuItem("Modificar nombre de sala");
	    menuItem.addActionListener(new ModificarActionListener());
	    popup.add(menuItem);

	    MouseListener popupListener = new PopupListener();
	    tablaSalas.getTable().addMouseListener(popupListener);
	    
        jLabel4 = new javax.swing.JLabel();
        nuevo = new GradientButton("",Color.blue);
        agregar = new GradientButton("",Color.blue);
        buscarLocalidad = new GradientButton("",Color.blue);
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        m_txtCalle = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        m_txtNro = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        m_txtPiso = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        m_txtDepartamento = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        m_cmbCodigoPostal = new ABMCodigosPostalesComboBox();
        m_cmbPais = new ABMPaisesComboBox();
        jLabel10 = new javax.swing.JLabel();
        m_cmbProvincia = new ABMProvinciasComboBox();
        jLabel11 = new javax.swing.JLabel();
        m_cmbLocalidad = new ABMLocalidadesComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();        
        m_cmbPartido = new ABMPartidosComboBox();
        m_cmbCodigoPostal.setEnabled(false);
        m_cmbProvincia.setEnabled(false);
        m_cmbPartido.setEnabled(false);
        m_cmbLocalidad.setEnabled(false);        
        jLabel16 = new javax.swing.JLabel();
        m_txtEmail = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        m_txtTel1 = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        m_txtTel2 = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        m_txtFax = new javax.swing.JFormattedTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        m_txtEmailOS = new javax.swing.JFormattedTextField();
        jSeparator3 = new javax.swing.JSeparator();
        
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de lugar de evento temporal");

        salir.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        salir.setMnemonic('s');
        salir.setText("Salir");
        salir.setToolTipText("Click para salir");

        jLabel2.setText("Ingrese el nombre del nuevo lugar de evento");
        
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Ingrese el nombre de las salas del lugar");        
        
        nuevo.setIcon(new javax.swing.ImageIcon(getUrlImagen("disk.png")));
        nuevo.setMnemonic('a');
        nuevo.setText("Grabar Lugar");
        nuevo.setToolTipText("Click para agregar nuevo lugar de evento");

        agregar.setIcon(new javax.swing.ImageIcon(getUrlImagen("add.png")));
        agregar.setText("Nueva sala");
        agregar.setToolTipText("Click para agregar una sala al lugar de evento");
        
        agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        
        jLabel3.setText("Direcci\u00f3n");

        jLabel5.setText("Provincia"); 

        jLabel6.setText("Nro");

        jLabel7.setText("Piso");

        jLabel8.setText("Depto.");

        jLabel9.setText("Pais");         

        jLabel10.setText("Partido");        

        jLabel11.setText("Localidad");        

        jLabel12.setText("CP");     
        
        jLabel13.setText("*");

        jLabel14.setText("*");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel15.setText("* Nota: para poder grabar un nuevo lugar de eventos es necesario indicar el nombre y asignarle al menos una sala ");
        
        jLabel16.setText("Email");

        jLabel17.setText("Telefonos");

        jLabel18.setText("/");

        jLabel19.setText("Fax");

        jLabel20.setText("Email para Orden de servicio");
        
        m_cmbPais.loadItems();
        
        setAddMode();
        
        buscarLocalidad.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        buscarLocalidad.setText("Buscar Localidad");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(10, 10, 10)
                            .add(jLabel20)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(m_txtEmailOS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 342, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jLabel1)
                        .add(layout.createSequentialGroup()
                            .add(10, 10, 10)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(jLabel2)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_txtLugarNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 408, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel13))
                                .add(layout.createSequentialGroup()
                                    .add(jLabel16)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_txtEmail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 317, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(56, 56, 56)
                                    .add(jLabel17)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_txtTel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel18)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_txtTel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 99, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(72, 72, 72)
                                    .add(jLabel19)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(m_txtFax, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(98, 98, 98))
                        .add(layout.createSequentialGroup()
                            .add(tablaSalas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 865, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(agregar))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .add(nuevo)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(salir)
                            .add(418, 418, 418))
                        .add(layout.createSequentialGroup()
                            .add(jLabel15)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 387, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createSequentialGroup()
                            .add(jLabel4)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel14)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 796, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator1)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator2)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator3)
                                .add(layout.createSequentialGroup()
                                    .add(10, 10, 10)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                            .add(jLabel5)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_cmbProvincia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 190, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jLabel10)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_cmbPartido, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 192, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jLabel11)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_cmbLocalidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 188, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jLabel12)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_cmbCodigoPostal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 187, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(layout.createSequentialGroup()
                                            .add(jLabel3)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_txtCalle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 386, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(19, 19, 19)
                                            .add(jLabel6)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_txtNro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jLabel7)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_txtPiso, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jLabel8)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_txtDepartamento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jLabel9)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(m_cmbPais, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 183, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(buscarLocalidad)))
                                    .add(12, 12, 12)))
                            .add(15, 15, 15)))
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
                        .add(jLabel2)
                        .add(jLabel13)
                        .add(m_txtLugarNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel16)
                        .add(m_txtEmail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel17)
                        .add(m_txtTel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel18)
                        .add(m_txtTel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel19)
                        .add(m_txtFax, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel20)
                        .add(m_txtEmailOS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel3)
                        .add(m_txtCalle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel6)
                        .add(m_txtNro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel7)
                        .add(m_txtPiso, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel8)
                        .add(m_txtDepartamento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel9)
                        .add(m_cmbPais, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(buscarLocalidad))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel5)
                        .add(m_cmbProvincia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel10)
                        .add(m_cmbPartido, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel11)
                        .add(m_cmbLocalidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel12)
                        .add(m_cmbCodigoPostal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel4)
                        .add(jLabel14))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(tablaSalas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 167, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel15)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(salir)
                                .add(nuevo)))
                        .add(agregar))
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
	
	private void createListener(){
		salir.addActionListener(new SalirActionListener());
		agregar.addActionListener(new AgregarActionListener());	
		buscarLocalidad.addActionListener(new BuscarLocalidadActionListener());
		nuevo.addActionListener(new NuevoActionListener());
		
		this.m_cmbPais.addActionListener(new PaisActionListener());
		this.m_cmbProvincia.addActionListener(new ProvinciaActionListener());
		this.m_cmbPartido.addActionListener(new PartidoActionListener());
		this.m_cmbLocalidad.addActionListener(new LocalidadActionListener());
	}	
	
	private void setAddMode() {
		lugarEvento = new LugarEvento(); 
		resetFields();
	}
    
    public void setEditMode(String entityId) {
		try {			
			this.lugarEvento = LugarEventoManager.instance().getLugarEventoById(entityId);			
			salas = SalaLugarManager.instance().getSalaLugarReportByLugar(entityId);
			//vendedor = VendedorManager.instance().getVendedorByApYNom(lugarEvento.getNombreLugar());
			resetFields();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void resetFields() {
		m_txtLugarNombre.setText(lugarEvento.getNombreLugar());
		
		if(lugarEvento.getCalle() != null){
			m_txtCalle.setText(lugarEvento.getCalle());
		}		
		if(lugarEvento.getNumero() != null){
			m_txtNro.setText(lugarEvento.getNumero());			
		}
		if(lugarEvento.getPiso() != null){
			m_txtPiso.setText(lugarEvento.getPiso());
		}
		if(lugarEvento.getDepartamento() != null){
			m_txtDepartamento.setText(lugarEvento.getDepartamento());			
		}
		if(lugarEvento.getEmail() != null){
			m_txtEmail.setText(lugarEvento.getEmail());
		}
		if(lugarEvento.getEmailOS() != null){
			m_txtEmailOS.setText(lugarEvento.getEmailOS());
		}
		if(lugarEvento.getTelefono1() != null){
			m_txtTel1.setText(lugarEvento.getTelefono1());
		}
		if(lugarEvento.getTelefono2() != null){
			m_txtTel2.setText(lugarEvento.getTelefono2());
		}
		if(lugarEvento.getFax() != null){
			m_txtFax.setText(lugarEvento.getFax());
		}
		if(lugarEvento.getCodigoPais() != null){
			m_cmbPais.setForeign(lugarEvento.getCodigoPais());
		}
		if(lugarEvento.getCodigoProvincia() != null){
			m_cmbProvincia.setForeign(lugarEvento.getCodigoProvincia());
		}
		if(lugarEvento.getCodigoPartido() != null){
			m_cmbPartido.setForeign(lugarEvento.getCodigoPartido());
		}
		if(lugarEvento.getLocalidad() != null){
			m_cmbLocalidad.setForeign(lugarEvento.getLocalidad());
		}
		if(lugarEvento.getCodigoPostal() != null){
			m_cmbCodigoPostal.setForeign(lugarEvento.getCodigoPostal());
		}	
		
		if(lugarEvento.getCodigoComision() != null){
			comision = lugarEvento.getCodigoComision();
		}
		
		if(salas != null){
			nroSala = salas.length+1;
			for(int i=0; i<salas.length; i++){
				NuevoLugarItem it = new NuevoLugarItem();
				Object[] sala = (Object[])salas[i];
				it.setCodSala((String)sala[0]);
				it.setNombreSala((String)sala[1]);				
				it.setNumeroSala(i+1);
				((NuevoLugarTableModel)tablaSalas.getTable().getModel()).addRow(it);
			}
			tablaSalas.refreshTable();
		}
		
	}	

	/**
	 * @return Returns the codLugarElegido.
	 */
	public String getCodLugarElegido() {
		return codLugarElegido;
	}

	/**
	 * @param codLugarElegido The codLugarElegido to set.
	 */
	public void setCodLugarElegido(String codLugarElegido) {
		this.codLugarElegido = codLugarElegido;
	}
	

	/**
	 * @return Returns the lugarEvento.
	 */
	public LugarEvento getLugarEvento() {
		return lugarEvento;
	}

	/**
	 * @param lugarEvento The lugarEvento to set.
	 */
	public void setLugarEvento(LugarEvento lugarEvento) {
		this.lugarEvento = lugarEvento;
	}
	
	
	
	//**********************************ACCIONES*****************************************
	
	private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(PantallaNuevoLugar.this, "¿Desea salir sin guardar cambios?", "Salir")){
				setVisible(false);
			}
		}
		
	}
	
	private class AgregarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			NuevoLugarItem it = new NuevoLugarItem();
			it.setNombreSala("sala nueva");
			it.setNumeroSala(nroSala);
			it.setCodSala(null);
			((NuevoLugarTableModel)tablaSalas.getTable().getModel()).addRow(it);
			tablaSalas.refreshTable();
			nroSala++;
		}
		
	}
	
	private class BuscarLocalidadActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			BuscadorLocalidades a = new BuscadorLocalidades(Main.getVentana());
			a.initComponents();
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
	
	private class ModificarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(tablaSalas.getSelectedItem() !=null){
				tablaSalas.getSelectedItem().setNombreSala("Escriba aquí el nombre de la sala y presione Enter.");
			}
		}
		
	}
	
	private class NuevoActionListener implements ActionListener{
		
		private LugarEvento setLugarEvento(){			
			lugarEvento.setNombreLugar(m_txtLugarNombre.getText());
    		lugarEvento.setContacto(null);   		
    		
    		if(m_txtEmail.getText() != null && m_txtEmail.getText().length()>0)
    			lugarEvento.setEmail(m_txtEmail.getText());
    		else
    			lugarEvento.setEmail("-");
    		
    		if(m_txtTel1.getText() != null && m_txtTel1.getText().length()>0)
    			lugarEvento.setTelefono1(m_txtTel1.getText());
    		else
    			lugarEvento.setTelefono1("-");
    		
    		if(m_txtFax.getText() != null && m_txtFax.getText().length()>0)
    			lugarEvento.setFax(m_txtFax.getText());
    		else
    			lugarEvento.setFax(null);
    		
    		if(m_txtTel2.getText() != null && m_txtTel2.getText().length()>0)
    			lugarEvento.setTelefono2(m_txtTel2.getText());
    		else
    			lugarEvento.setTelefono2(null);

			lugarEvento.setFlotaNextel(null);

			lugarEvento.setIdNextel(null);
			
			if(m_txtCalle.getText() == null || m_txtCalle.getText().length()==0)
				lugarEvento.setCalle("-");
			else
				lugarEvento.setCalle(m_txtCalle.getText());
			
			if(m_txtNro.getText() == null || m_txtNro.getText().length()==0)
				lugarEvento.setNumero("0");
			else
				lugarEvento.setNumero(m_txtNro.getText());
			
			if(m_txtPiso.getText() == null || m_txtPiso.getText().length()==0)
				lugarEvento.setPiso("0");
			else
				lugarEvento.setPiso(m_txtPiso.getText());
			
			if(m_txtDepartamento.getText() == null || m_txtDepartamento.getText().length()==0)
				lugarEvento.setDepartamento("-");
			else
				lugarEvento.setDepartamento(m_txtDepartamento.getText());
			
			if(m_cmbCodigoPostal.searchForeign() == null || m_cmbCodigoPostal.searchForeign().length()==0)
				lugarEvento.setCodigoPostal(null);
			else 
				lugarEvento.setCodigoPostal(m_cmbCodigoPostal.searchForeign());
			
			if(m_cmbLocalidad.searchForeign() == null || m_cmbLocalidad.searchForeign().length()==0)
				lugarEvento.setLocalidad(null);
			else
				lugarEvento.setLocalidad(m_cmbLocalidad.searchForeign());
			
			if(m_cmbPartido.searchForeign() == null || m_cmbPartido.searchForeign().length()==0)
				lugarEvento.setCodigoPartido(null);
			else
				lugarEvento.setCodigoPartido(m_cmbPartido.searchForeign());
			
			if(m_cmbProvincia.searchForeign() == null || m_cmbProvincia.searchForeign().length()==0)
				lugarEvento.setCodigoProvincia(null);
			else
				lugarEvento.setCodigoProvincia(m_cmbProvincia.searchForeign());
			
			if(m_cmbPais.searchForeign() == null || m_cmbPais.searchForeign().length()==0)
				lugarEvento.setCodigoPais(null);
			else
				lugarEvento.setCodigoPais(m_cmbPais.searchForeign());
			
			if(m_txtEmailOS.getText() == null || m_txtEmailOS.getText().length()==0)
    			lugarEvento.setEmailOS("OS@congressrental.com");
    		else{ MessageUtil.showWarningMessage(PantallaNuevoLugar.this, "Atencion, la orden se enviará con copia a '"+m_txtEmailOS.getText()+"'.\nPonga una dirección válida.");		
    			
    			lugarEvento.setEmailOS(m_txtEmailOS.getText());
    		}
			
			if(comision == null || comision.equals(""))
				lugarEvento.setCodigoComision("0");

			return lugarEvento;
		}
		
		/*private Vendedor setVendedor(){
			if(vendedor == null)
				vendedor = new Vendedor(); 
			vendedor.setApellidoYNombre(m_txtLugarNombre.getText());
    		vendedor.setCategoria(CategVendedorManagerSEI.CATEGORY_LUGAR_EVENTO);     
    		vendedor.setFecing(DateConverter.convertDateToString(new Date(),"yyyy/MM/dd H:mm:ss"));
    		vendedor.setActivo("S");
    		return vendedor;
		}*/
		
		private SalaLugar createSala(String lugarId, NuevoLugarItem item){
			SalaLugar salaLugar = new SalaLugar();
			try{			 
				if(item.getCodSala() != null){
					salaLugar = SalaLugarManager.instance().getSalaLugarById(item.getCodSala());
					salaLugar.setDescripcion(item.getNombreSala());
					
				}
				else{
					salaLugar.setCodigoLugar(lugarId);				
					salaLugar.setDescripcion(item.getNombreSala());					
					salaLugar.setLargo(null);
					salaLugar.setAncho(null);
					salaLugar.setAltura(null);
					salaLugar.setCapacidad(null);    
				}
			}
			catch (RemoteException e){
				Util.errMsg(Main.getVentana(),"Error al cargar datos externos ",e);
			}
    		return salaLugar;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			int cantSalas = ((NuevoLugarTableModel)tablaSalas.getTable().getModel()).getRowCount();
			if(m_txtLugarNombre.getText() != null && m_txtLugarNombre.getText().length() >0 && cantSalas >0){ 
				try{				

					lugarEvento = setLugarEvento();        				
					codLugarElegido = LugarEventoManager.instance().update(lugarEvento);				
										
					for(int i=0; i<cantSalas; i++){  
						SalaLugar sala = createSala(codLugarElegido, ((NuevoLugarTableModel)tablaSalas.getTable().getModel()).getRow(i));
						SalaLugarManager.instance().update(sala);
					}
					lugarEvento.setCodigo(codLugarElegido);
					MessageUtil.showMessage(PantallaNuevoLugar.this, "El lugar de eventos se cargó correctamente");
					
				}
				catch(Exception e){
					Util.errMsg(PantallaNuevoLugar.this, "Error al cargar datos del lugar de evento",e);
				}
				finally{
					//setAddMode();
					setVisible(false);
				}
			}
			else MessageUtil.showErrorMessage(PantallaNuevoLugar.this, "Complete todos los datos");
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
				}
				paisId = newId;
				m_cmbProvincia.setEnabled(true);
			} else
				m_cmbProvincia.setEnabled(false);
		}
	}

	private class ProvinciaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (m_cmbProvincia.getSelectedIndex() > 0) {
				String newId = m_cmbProvincia.searchForeign();
				if (provinciaId == null || !provinciaId.equals(newId)) {
					m_cmbPartido.loadItems(newId);
					m_cmbLocalidad.resetFields();
				}
				provinciaId = newId;
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
				}
				partidoId = newId;
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
				}
				localidadId = newId;
				m_cmbCodigoPostal.setEnabled(true);
			} else
				m_cmbCodigoPostal.setEnabled(false);
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
	
	private class RefreshAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {			
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
		}
    	
    }
	
}
