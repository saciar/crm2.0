package crm.gui.pantalla.solapa;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import crm.client.helper.UserRolesUtil;
import crm.client.util.UsuariosUtil;
import crm.client.util.Util;
import crm.client.validacion.ErrorList;
import crm.gui.Main;
import crm.gui.abms.ABMAccesos;
import crm.gui.abms.ABMAccionesSeguimiento;
import crm.gui.abms.ABMCancelacionesPpto;
import crm.gui.abms.ABMCategoriaVendedores;
import crm.gui.abms.ABMCategoriasEvento;
import crm.gui.abms.ABMClientes;
import crm.gui.abms.ABMCodigosPostales;
import crm.gui.abms.ABMCondicionesIVA;
import crm.gui.abms.ABMCondicionesPago;
import crm.gui.abms.ABMDescuentosPorDia;
import crm.gui.abms.ABMEnConstruccion;
import crm.gui.abms.ABMEncabezadosPpto;
import crm.gui.abms.ABMEstadosEvento;
import crm.gui.abms.ABMFamiliasDeServicio;
import crm.gui.abms.ABMFormasPago;
import crm.gui.abms.ABMIdiomas;
import crm.gui.abms.ABMLocalidades;
import crm.gui.abms.ABMModalidadContratacion;
import crm.gui.abms.ABMModoIngreso;
import crm.gui.abms.ABMPaises;
import crm.gui.abms.ABMPartidos;
import crm.gui.abms.ABMPerfiles;
import crm.gui.abms.ABMPeriodosPpto;
import crm.gui.abms.ABMProveedores;
import crm.gui.abms.ABMProvincias;
import crm.gui.abms.ABMResultadosSeguimiento;
import crm.gui.abms.ABMSeguridadEquipos;
import crm.gui.abms.ABMServicios;
import crm.gui.abms.ABMSucursales;
import crm.gui.abms.ABMTipoArmado;
import crm.gui.abms.ABMTipoEvento;
import crm.gui.abms.ABMTipoLugares;
import crm.gui.abms.ABMTipoUniformes;
import crm.gui.abms.ABMTiposPpto;
import crm.gui.abms.ABMTitulos;
import crm.gui.abms.ABMUnidadDeNegocio;
import crm.gui.abms.ABMUnidadesAdministrativas;
import crm.gui.abms.ABMUnidadesComerciales;
import crm.gui.abms.ABMUsuarios;
import crm.gui.abms.ABMValidezPpto;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.Pantalla;
import crm.gui.pantalla.PantallaBienvenidaAdmin;
import crm.gui.pantalla.PantallaBienvenidaCold;
import crm.gui.pantalla.PantallaBienvenidaGerencia;
import crm.gui.pantalla.PantallaEmergente;
import crm.gui.pantalla.Ventana;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.Usuario;
import crm.libraries.util.MessageUtil;


public class ABM extends PantallaEmergente{
	
	private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JPanel panelVista;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    
    private DefaultMutableTreeNode top;
	private DefaultTreeModel treeModel;
	
	private ABMEnConstruccion panelABMEnConstruccion;
	private ABMClientes panelABMCliente; 
	private ABMSucursales panelABMSucursales;
	private ABMUnidadesComerciales panelABMUnidadesComerciales;
	private ABMServicios panelABMServicios;
	private ABMDescuentosPorDia panelABMDescuentos;
	private ABMUsuarios panelABMUsuarios;
	private ABMProveedores panelABMProveedores; 
	private ABMPaises panelABMPaises;
	private ABMProvincias panelABMProvincias;
	private ABMPartidos panelABMPartidos;
	private ABMLocalidades panelABMLocalidades;
	private ABMFamiliasDeServicio panelABMFamiliasServicios;
	private ABMUnidadDeNegocio panelABMUnidadesDeNegocio;
	private ABMCodigosPostales panelABMCodigosPostales;
	private ABMAccesos panelABMAccesos;
	private ABMPerfiles panelABMPerfiles;
	private ABMTitulos panelABMTitulos;
	private ABMCondicionesIVA panelABMCondicionesIVA;
	private ABMEncabezadosPpto panelABMEncabezados;
	private ABMModalidadContratacion panelABMModalidad;
	private ABMTipoEvento panelABMTipoEvento;
	private ABMTipoUniformes panelABMTipoUniforme;
	private ABMTipoLugares panelABMTipoLugares;	
	private ABMTipoArmado panelABMTipoArmado;
	private ABMAccionesSeguimiento panelABMAccionesSeguimiento;
	private ABMResultadosSeguimiento panelABMResultadosSeguimiento;
	private ABMTiposPpto panelABMTiposPpto;
	private ABMPeriodosPpto panelABMPeriodosPpto;
	private ABMValidezPpto panelABMValidezPpto;
	private ABMCancelacionesPpto panelABMCancelacionesPpto;
	private ABMCategoriasEvento panelABMCategoriasEvento;
	private ABMEstadosEvento panelABMEstadosEvento;
	private ABMModoIngreso panelABMModosIngreso;
	private ABMSeguridadEquipos panelABMSeguridadEquipos;
	private ABMCondicionesPago panelABMCondicionesPago;
	private ABMFormasPago panelABMFormasPago;
	private ABMIdiomas panelABMIdiomas;
	private ABMCategoriaVendedores panelABMCategoriaVendedor;
	private ABMUnidadesAdministrativas panelABMUnidadesAdministrativas;
	
	//private PantallaABMs pantallaABMs;
	
	private Usuario user;
	private PanelImagen panel;
	
	private long codigoUsuario;
	
    public ABM(Frame owner){
    	super("Altas, bajas y modificaciones",owner);
    	//codigoUsuario = Long.parseLong(p.getUsuario().getCodigo());
    	//pantallaABMs = p;
    }
    
    /**
	 * Inicializa el panel, es friend para que solo el factory pueda llamarlo
	 *
	 */
    public void initComponent() {		
		panel = null;
		try{
			panel = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
		
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        panelVista = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        
        createTreeComponents();  
        DefaultTreeCellRenderer renderer3 = new DefaultTreeCellRenderer();
        renderer3.setOpenIcon(new javax.swing.ImageIcon(getUrlImagen("bullet_arrow_right.png")));
        renderer3.setClosedIcon(new javax.swing.ImageIcon(getUrlImagen("bullet_arrow_down.png")));
        renderer3.setLeafIcon(new javax.swing.ImageIcon(getUrlImagen("bullet_red.png")));
        jTree1 = new javax.swing.JTree(treeModel);
        jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jTree1.setCellRenderer(renderer3);	 
        
        jScrollPane1.setViewportView(jTree1);        
        jScrollPane1.setPreferredSize(new Dimension(131,469));
        jScrollPane1.setMaximumSize(new Dimension(131,469));
        jScrollPane1.setSize(new Dimension(131,469));
        
        panelVista.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));       
        panelVista.setPreferredSize(new Dimension(837,469));
        panelVista.setMaximumSize(new Dimension(837,469));
        panelVista.setSize(new Dimension(837,469));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel1.setText("ABM'S");
        
        jButton1.setIcon(new javax.swing.ImageIcon(getUrlImagen("disk.png")));
        jButton1.setText("Guardar");
        
        jButton2.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        jButton2.setText("Salir");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel2.setText("Seleccione un Abm de la lista");
        jButton3.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        jButton3.setText("Buscar");        
}
	
	public void initLayout(){
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
                                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 147, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(panelVista, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE)
                                .add(layout.createSequentialGroup()
                                    .add(jLabel1)
                                    .add(104, 104, 104)
                                    .add(jLabel2))))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE))
                        .add(layout.createSequentialGroup()
                            .add(431, 431, 431)
                            .add(jButton1)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jButton3)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jButton2)))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel1)
                        .add(jLabel2))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(panelVista, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jButton1)
                        .add(jButton3)
                        .add(jButton2))
                    .addContainerGap(57, Short.MAX_VALUE))
            );
            this.getContentPane().add(panel);
            this.pack();
        createPaneles();        
        createListeners();
    }// </editor-fold>
	
	private void createListeners(){
		jTree1.addTreeSelectionListener(new MyTreeSelectionListener());
		jButton1.addActionListener(new GuardarActionListener());
		jButton2.addActionListener(new SalirActionListener());
		jButton3.addActionListener(new BuscarActionListener());
	}
	
	private void createPaneles(){
		
		panelABMEnConstruccion = new ABMEnConstruccion(panelVista);
		panelABMEnConstruccion.initComponents();
		
		panelABMCliente = new ABMClientes(panelVista);
		panelABMCliente.initComponents();
		
		panelABMSucursales = new ABMSucursales(panelVista);
		panelABMSucursales.initComponents();
				
		panelABMUnidadesComerciales = new ABMUnidadesComerciales(panelVista);
		panelABMUnidadesComerciales.initComponents();
		
		panelABMUnidadesAdministrativas = new ABMUnidadesAdministrativas(panelVista);
		panelABMUnidadesAdministrativas.initComponents();
		
		panelABMServicios = new ABMServicios(panelVista);
		panelABMServicios.initComponents();
		
		panelABMDescuentos = new ABMDescuentosPorDia(panelVista);
		panelABMDescuentos.initComponents();
		
		panelABMUsuarios = new ABMUsuarios(panelVista);
		panelABMUsuarios.initComponents();
		
		panelABMProveedores = new ABMProveedores(panelVista);
		panelABMProveedores.initComponents();
		
		panelABMPaises = new ABMPaises(panelVista);
		panelABMPaises.initComponents();
		
		panelABMProvincias = new ABMProvincias(panelVista);
		panelABMProvincias.initComponents();
		
		panelABMPartidos = new ABMPartidos(panelVista);
		panelABMPartidos.initComponents();
		
		panelABMLocalidades = new ABMLocalidades(panelVista);
		panelABMLocalidades.initComponents();
		
		panelABMFamiliasServicios = new ABMFamiliasDeServicio(panelVista);
		panelABMFamiliasServicios.initComponents();
		
		panelABMUnidadesDeNegocio = new ABMUnidadDeNegocio(panelVista);
		panelABMUnidadesDeNegocio.initComponents();
		
		panelABMCodigosPostales = new ABMCodigosPostales(panelVista);
		panelABMCodigosPostales.initComponents();
		
		panelABMAccesos = new ABMAccesos(panelVista);
		panelABMAccesos.initComponents();
		
		panelABMPerfiles = new ABMPerfiles(panelVista);
		panelABMPerfiles.initComponents();
		
		panelABMTitulos = new ABMTitulos(panelVista);
		panelABMTitulos.initComponents();
		
		panelABMCondicionesIVA = new ABMCondicionesIVA(panelVista);
		panelABMCondicionesIVA.initComponents();
		
		panelABMEncabezados = new ABMEncabezadosPpto(panelVista);
		panelABMEncabezados.initComponents();
		
		panelABMModalidad =  new ABMModalidadContratacion(panelVista);
		panelABMModalidad.initComponents();
		
		panelABMTipoEvento = new ABMTipoEvento(panelVista);
		panelABMTipoEvento.initComponents();
		
		panelABMTipoUniforme = new ABMTipoUniformes(panelVista);
		panelABMTipoUniforme.initComponents();
		
		panelABMTipoLugares = new ABMTipoLugares(panelVista);
		panelABMTipoLugares.initComponents();
		
		panelABMTipoArmado = new ABMTipoArmado(panelVista);
		panelABMTipoArmado.initComponents();
		
		panelABMAccionesSeguimiento = new ABMAccionesSeguimiento(panelVista);
		panelABMAccionesSeguimiento.initComponents();
		
		panelABMResultadosSeguimiento = new ABMResultadosSeguimiento(panelVista);
		panelABMResultadosSeguimiento.initComponents();
		
		panelABMTiposPpto = new ABMTiposPpto(panelVista);
		panelABMTiposPpto.initComponents();
		
		panelABMPeriodosPpto = new ABMPeriodosPpto(panelVista);
		panelABMPeriodosPpto.initComponents();
		
		panelABMValidezPpto = new ABMValidezPpto(panelVista);
		panelABMValidezPpto.initComponents();
		
		panelABMCancelacionesPpto = new ABMCancelacionesPpto(panelVista);
		panelABMCancelacionesPpto.initComponents();
		
		panelABMCategoriasEvento = new ABMCategoriasEvento(panelVista);
		panelABMCategoriasEvento.initComponents();
		
		panelABMEstadosEvento = new ABMEstadosEvento(panelVista);
		panelABMEstadosEvento.initComponents();
		
		panelABMModosIngreso = new ABMModoIngreso(panelVista);
		panelABMModosIngreso.initComponents();
		
		panelABMSeguridadEquipos = new ABMSeguridadEquipos(panelVista);
		panelABMSeguridadEquipos.initComponents();
		
		panelABMCondicionesPago = new ABMCondicionesPago(panelVista);
		panelABMCondicionesPago.initComponents();
		
		panelABMFormasPago = new ABMFormasPago(panelVista);
		panelABMFormasPago.initComponents();
		
		panelABMIdiomas = new ABMIdiomas(panelVista);
		panelABMIdiomas.initComponents();
		
		panelABMCategoriaVendedor = new ABMCategoriaVendedores(panelVista);
		panelABMCategoriaVendedor.initComponents();
	}
	
	private void createTreeComponents(){
		DefaultTreeCellRenderer renderer3 = new DefaultTreeCellRenderer();
		renderer3.setOpenIcon(new javax.swing.ImageIcon(getUrlImagen("bullet_arrow_right.png")));
        renderer3.setClosedIcon(new javax.swing.ImageIcon(getUrlImagen("bullet_arrow_down.png")));
        renderer3.setLeafIcon(new javax.swing.ImageIcon(getUrlImagen("bullet_red.png")));
        //nodo padre CRM congresss rental
        top = new DefaultMutableTreeNode("CRM Congress Rental");
        
        DefaultMutableTreeNode abmApp = null;
        DefaultMutableTreeNode partePanel = null;
        DefaultMutableTreeNode abmPpto = null;
        DefaultMutableTreeNode abmControl = null;
        
        //nodo de tipo de pantalla main
        abmApp = new DefaultMutableTreeNode("Opciones en la aplicación");
        top.add(abmApp); 
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_CATEGORIA_EVENTOS) == true){
        	partePanel = new DefaultMutableTreeNode("Categorías de eventos");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_CLIENTES) == true){
        	partePanel = new DefaultMutableTreeNode("Cliente");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_CODIGO_POSTAL) == true){
        	partePanel = new DefaultMutableTreeNode("Códigos postales");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_COND_IVA) == true){
        	partePanel = new DefaultMutableTreeNode("Condiciones de IVA");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_DESC_PRECIOS) == true){
        	partePanel = new DefaultMutableTreeNode("Descuentos de precios");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_FLIAS_SERVICIOS) == true){
        	partePanel = new DefaultMutableTreeNode("Familias de servicios");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_LOCALIDADES) == true){
        	partePanel = new DefaultMutableTreeNode("Localidades");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_MODO_INGRESO_EQUIPOS) == true){
        	partePanel = new DefaultMutableTreeNode("Modos de ingreso de equipos");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_MONEDA_EXTRANJERA) == true){
        	partePanel = new DefaultMutableTreeNode("Monedas Extranjeras");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PAISES) == true){
        	partePanel = new DefaultMutableTreeNode("Paises");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PARTIDOS) == true){
        	partePanel = new DefaultMutableTreeNode("Partidos");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PROVEEDORES) == true){
        	partePanel = new DefaultMutableTreeNode("Proveedores");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PROVINCIAS) == true){
        	partePanel = new DefaultMutableTreeNode("Provincias");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_RES_SEGUIMIENTO) == true){
        	partePanel = new DefaultMutableTreeNode("Resultados de seguimiento");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_SEGUIMIENTOS) == true){
        	partePanel = new DefaultMutableTreeNode("Seguimientos");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_SEGURIDAD_EQUIPOS) == true){
        	partePanel = new DefaultMutableTreeNode("Seguridad de equipos");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_SERVICIOS) == true){
        	partePanel = new DefaultMutableTreeNode("Servicios");              
            abmApp.add(partePanel);        	
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_TIPO_ARMADO) == true){
        	partePanel = new DefaultMutableTreeNode("Tipos de armado");              
            abmApp.add(partePanel);
        }
        
        /*if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_TIPO_CONTACTO) == true){
        	partePanel = new DefaultMutableTreeNode("Tipos de contactos");              
            abmApp.add(partePanel);
        } */
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_TIPO_EVENTO) == true){
        	partePanel = new DefaultMutableTreeNode("Tipos de eventos");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_TIPO_LUGAR_EVT) == true){
        	partePanel = new DefaultMutableTreeNode("Tipos de lugares de eventos");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_TIPO_UNIFORME) == true){
        	partePanel = new DefaultMutableTreeNode("Tipos de uniformes");              
            abmApp.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_TITULOS) == true){
        	partePanel = new DefaultMutableTreeNode("Títulos");              
            abmApp.add(partePanel);
		}             
        
        /*if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_COND_PAGO) == true){
        	partePanel = new DefaultMutableTreeNode("Condiciones de pago");              
            abmApp.add(partePanel);
        	//tieneAlgunAcceso = true;        
        	//tabbedPane.addTab("Condiciones de Pago", new ABMCondicionesPago());
        }*/  
        
        /********************************************************************************/
        
        abmPpto = new DefaultMutableTreeNode("Partes del presupuesto");
        top.add(abmPpto);        
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PPTO_CANCELACION) == true){
        	partePanel = new DefaultMutableTreeNode("Cancelaciones de presupuestos");              
        	abmPpto.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Cancelaciones", new ABMPrtPptoCancelacion());
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PPTO_COND_PAGO) == true){
        	partePanel = new DefaultMutableTreeNode("Condiciones de pago");              
        	abmPpto.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Condición de pago", new ABMPrtPptoCondPago());
        }
        
       /* if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PPTO_COND_RESERVA) == true){
        	partePanel = new DefaultMutableTreeNode("Condiciones de reserva");              
        	abmPpto.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Condiciones de reserva", new ABMPrtPptoCondReserva());
        }*/
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PPTO_HEADER) == true){
        	partePanel = new DefaultMutableTreeNode("Encabezados de Presupuestos");              
        	abmPpto.add(partePanel);
        }       
        
       /* if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PPTO_SIGNATURE) == true){
        	partePanel = new DefaultMutableTreeNode("Firmas de presupuestos");              
        	abmPpto.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Signaturs", new ABMPrtPptoSignature());
        }*/
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PPTO_F_PAGO) == true){
        	partePanel = new DefaultMutableTreeNode("Formas de pago");              
        	abmPpto.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Formas de pagos", new ABMPrtPptoFPago());
        }
        
        /*if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PPTO_INSTALACION) == true){
        	partePanel = new DefaultMutableTreeNode("Instalaciones");              
        	abmPpto.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Instalaciones", new ABMPrtPptoInstalacion());
        }*/
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PPTO_PERIODO) == true){
        	partePanel = new DefaultMutableTreeNode("Período de presupuesto");              
        	abmPpto.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Período", new ABMPrtPptoPeriodo());
        }
        
        /*if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PPTO_PERSONAL) == true){
        	partePanel = new DefaultMutableTreeNode("Personal");              
        	abmPpto.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Personal", new ABMPrtPptoPersonal());
        }*/
        
        /*if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PTO_FOOTER) == true){
        	partePanel = new DefaultMutableTreeNode("Pie de presupuestos");              
        	abmPpto.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Footers", new ABMPrtPptoFooter());
        }*/
        
        /*if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PPTO_SEGURIDAD) == true){
        	partePanel = new DefaultMutableTreeNode("Seguridad");              
        	abmPpto.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Seguridad", new ABMPrtPptoSeguridad());
        }*/
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PPTO_TIPO_PRESUPUESTO) == true){
        	partePanel = new DefaultMutableTreeNode("Tipos de presupuestos");              
        	abmPpto.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Tipo de presupuesto", new ABMPrtPptoTipoPresupuesto());
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PPTO_VALIDEZ) == true){
        	partePanel = new DefaultMutableTreeNode("Validez de un presupuesto");              
        	abmPpto.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Validez", new ABMPrtPptoValidez());
        }  
        
        /*******************************************************************************/
                
        abmControl = new DefaultMutableTreeNode("Control de aplicación");
        top.add(abmControl);
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_ACCESOS) == true){
        	partePanel = new DefaultMutableTreeNode("Accesos");              
        	abmControl.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_CATEGORIA_VENDEDORES) == true){
        	partePanel = new DefaultMutableTreeNode("Categorías de vendedores");              
        	abmControl.add(partePanel);
        }
        
        /*if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_COSTO_OPERATIVO) == true){
        	partePanel = new DefaultMutableTreeNode("Costo operativo");              
        	abmControl.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_DIAS_VENCER) == true){
        	partePanel = new DefaultMutableTreeNode("Dias a vencer");              
        	abmControl.add(partePanel);
        }*/       
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_ESTADO_EVENTO) == true){
        	partePanel = new DefaultMutableTreeNode("Estados de evento");              
        	abmControl.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Estado de Eventos", new ABMEstadoEventos());
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_IDIOMAS) == true){
        	partePanel = new DefaultMutableTreeNode("Idiomas");              
        	abmControl.add(partePanel);
        }
        
        /*if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_LISTA_PRECIO) == true){
        	partePanel = new DefaultMutableTreeNode("Listas de precios");              
        	abmControl.add(partePanel);
        }*/
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_MOD_CONTRATACION) == true){
        	partePanel = new DefaultMutableTreeNode("Modalidades de Contratación");              
        	abmControl.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_PERFILES) == true){
        	partePanel = new DefaultMutableTreeNode("Perfiles");              
        	abmControl.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_SUCURSALES) == true){
        	partePanel = new DefaultMutableTreeNode("Sucursales");              
        	abmControl.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_UNIDADES_ADMINISTRATIVAS) == true){
        	partePanel = new DefaultMutableTreeNode("Unidades administrativas");              
        	abmControl.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_UNIDADES_COMERCIALES) == true){
        	partePanel = new DefaultMutableTreeNode("Unidades comerciales");              
        	abmControl.add(partePanel);
        }
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_UNIDAD_NEGOCIO) == true){
        	partePanel = new DefaultMutableTreeNode("Unidades de negocio");              
        	abmControl.add(partePanel);
        } 
        
        if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_USUARIOS) == true){
        	partePanel = new DefaultMutableTreeNode("Usuarios");              
        	abmControl.add(partePanel);
        }   
        
        
       /* if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_MARCO_LIQUIDACION) == true){
        	partePanel = new DefaultMutableTreeNode("Marco de Liquidación");              
            panelMain.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Marcos Liquidacion", new ABMMarcosLiquidacion());
        }*/        
        
        /*if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_ACC_INSTALACION) == true){
        	partePanel = new DefaultMutableTreeNode("Accesorios de instalación");              
            panelMain.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Accesorios Inst", new ABMAccesoriosInst());
        }*/
        
       /* if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_FLIAS_ACCESORIOS) == true){
        	partePanel = new DefaultMutableTreeNode("Familias de accesorios");              
            panelMain.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Familia Accesorios Instalacion ", new ABMFliaAccInst());
        }*/
        
        /*if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_COMISIONES) == true){
        	partePanel = new DefaultMutableTreeNode("Comisiones");              
            panelMain.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Comisiones", new ABMComisiones());
        }*/              
        
        /*if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_OPERADORES) == true){
        	partePanel = new DefaultMutableTreeNode("Operadores");              
            panelMain.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Operadores", new ABMOperadores());
        }*/
        
       /* if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_ASISTENTES) == true){
        	partePanel = new DefaultMutableTreeNode("Asistentes");              
            panelMain.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Asistentes", new ABMAsistentes());
        }*/
        
       /* if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_SUBSERVICIOS) == true){
        	partePanel = new DefaultMutableTreeNode("Subservicios");              
            panelMain.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Sub servicios", new ABMSubServicios());
        }*/
        
      /*  if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_CONTACTO_COMISIONABLE) == true){
        	partePanel = new DefaultMutableTreeNode("Contactos comisionables");              
            panelMain.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Contactos Comisionables", new ABMContactoComisionable());
        }*/       
        
        /*if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_SALAS) == true){
        	partePanel = new DefaultMutableTreeNode("Salas");              
            panelMain.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Sala Lugar", new ABMSalasLugar());
        }*/
        
       /* if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_IDIOMAS_SERVICIOS) == true){
        	partePanel = new DefaultMutableTreeNode("Idiomas de Servicios");              
            abmApp.add(partePanel);
        	//tieneAlgunAcceso = true;
        	//tabbedPane.addTab("Idiomas de Servicios", new ABMServiciosIdioma());
        }*/       
        
       /* if (UsuariosUtil.chequearAcceso(codigoUsuario, UsuariosUtil.ABM_LUGARES_EVENTOS) == true){
        	partePanel = new DefaultMutableTreeNode("Lugares de evento");              
            panelMain.add(partePanel);
        }*/    
                
        treeModel = new DefaultTreeModel(top);
	}
	
//	*********************************ACCIONES************************************************
	private class MyTreeSelectionListener implements TreeSelectionListener{

		public void valueChanged(TreeSelectionEvent e) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)
			(jTree1.getSelectionPath().getLastPathComponent());
			Object nodeInfo = node.getUserObject();
			
			if (node.isLeaf()) {
				if(((String)nodeInfo).equals("Cliente")){
					panelVista.removeAll();
					panelABMCliente.initLayout();
					jLabel2.setText("Cliente");
					jTree1.setToolTipText("Crea, modifica o borra un cliente en la base de datos");					
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Sucursales")){
					panelVista.removeAll();
					panelABMSucursales.initLayout();
					jLabel2.setText("Sucursales");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Unidades comerciales")){
					panelVista.removeAll();
					panelABMUnidadesComerciales.initLayout();
					jLabel2.setText("Unidades comerciales");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Servicios")){
					panelVista.removeAll();
					panelABMServicios.initLayout();
					jLabel2.setText("Servicios");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Descuentos de precios")){
					panelVista.removeAll();
					panelABMDescuentos.initLayout();
					jLabel2.setText("Descuentos de precios");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Usuarios")){
					panelVista.removeAll();
					panelABMUsuarios.initLayout();
					jLabel2.setText("Usuarios");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Proveedores")){
					panelVista.removeAll();
					panelABMProveedores.initLayout();
					jLabel2.setText("Proveedores");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Paises")){
					panelVista.removeAll();
					panelABMPaises.initLayout();
					jLabel2.setText("Paises");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Provincias")){
					panelVista.removeAll();
					panelABMProvincias.initLayout();
					jLabel2.setText("Provincias");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Partidos")){
					panelVista.removeAll();
					panelABMPartidos.initLayout();
					jLabel2.setText("Partidos");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Localidades")){
					panelVista.removeAll();
					panelABMLocalidades.initLayout();
					jLabel2.setText("Localidades");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Familias de servicios")){
					panelVista.removeAll();
					panelABMFamiliasServicios.initLayout();
					jLabel2.setText("Familia de servicios");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Unidades de negocio")){
					panelVista.removeAll();
					panelABMUnidadesDeNegocio.initLayout();
					jLabel2.setText("Unidades de negocio");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Códigos postales")){
					panelVista.removeAll();
					panelABMCodigosPostales.initLayout();
					jLabel2.setText("Códigos postales");
					panelVista.updateUI();	
				}				
				else if(((String)nodeInfo).equals("Accesos")){
					panelVista.removeAll();
					panelABMAccesos.initLayout();
					jLabel2.setText("Accesos");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Perfiles")){
					panelVista.removeAll();
					panelABMPerfiles.initLayout();
					jLabel2.setText("Perfiles");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Títulos")){
					panelVista.removeAll();
					panelABMTitulos.initLayout();
					jLabel2.setText("Titulos");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Condiciones de IVA")){
					panelVista.removeAll();
					panelABMCondicionesIVA.initLayout();
					jLabel2.setText("Condiciones de IVA");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Encabezados de Presupuestos")){
					panelVista.removeAll();
					panelABMEncabezados.initLayout();
					jLabel2.setText("Encabezados de Presupuestos");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Modalidades de Contratación")){
					panelVista.removeAll();
					panelABMModalidad.initLayout();
					jLabel2.setText("Modalidades de Contratación");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Tipos de eventos")){
					panelVista.removeAll();
					panelABMTipoEvento.initLayout();
					jLabel2.setText("Tipos de eventos");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Tipos de uniformes")){
					panelVista.removeAll();
					panelABMTipoUniforme.initLayout();
					jLabel2.setText("Tipos de uniformes");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Tipos de lugares de eventos")){
					panelVista.removeAll();
					panelABMTipoLugares.initLayout();
					jLabel2.setText("Tipos de lugares de eventos");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Tipos de armado")){
					panelVista.removeAll();
					panelABMTipoArmado.initLayout();
					jLabel2.setText("Tipos de armado");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Seguimientos")){
					panelVista.removeAll();
					panelABMAccionesSeguimiento.initLayout();
					jLabel2.setText("Seguimientos");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Resultados de seguimiento")){
					panelVista.removeAll();
					panelABMResultadosSeguimiento.initLayout();
					jLabel2.setText("Resultados de seguimiento");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Tipos de presupuestos")){
					panelVista.removeAll();
					panelABMTiposPpto.initLayout();
					jLabel2.setText("Tipos de presupuestos");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Período de presupuesto")){
					panelVista.removeAll();
					panelABMPeriodosPpto.initLayout();
					jLabel2.setText("Período de presupuesto");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Validez de un presupuesto")){
					panelVista.removeAll();
					panelABMValidezPpto.initLayout();
					jLabel2.setText("Validez de un presupuesto");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Cancelaciones de presupuestos")){
					panelVista.removeAll();
					panelABMCancelacionesPpto.initLayout();
					jLabel2.setText("Cancelaciones de presupuestos");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Categorías de eventos")){
					panelVista.removeAll();
					panelABMCategoriasEvento.initLayout();
					jLabel2.setText("Categorías de eventos");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Estados de evento")){
					panelVista.removeAll();
					panelABMEstadosEvento.initLayout();
					jLabel2.setText("Estados de evento");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Modos de ingreso de equipos")){
					panelVista.removeAll();
					panelABMModosIngreso.initLayout();
					jLabel2.setText("Modos de ingreso de equipos");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Seguridad de equipos")){
					panelVista.removeAll();
					panelABMSeguridadEquipos.initLayout();
					jLabel2.setText("Seguridad de equipos");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Condiciones de pago")){
					panelVista.removeAll();
					panelABMCondicionesPago.initLayout();
					jLabel2.setText("Condiciones de pago");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Formas de pago")){
					panelVista.removeAll();
					panelABMFormasPago.initLayout();
					jLabel2.setText("Formas de pago");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Idiomas")){
					panelVista.removeAll();
					panelABMIdiomas.initLayout();
					jLabel2.setText("Idiomas");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Categorías de vendedores")){
					panelVista.removeAll();
					panelABMCategoriaVendedor.initLayout();
					jLabel2.setText("Categorías de vendedores");
					panelVista.updateUI();	
				}
				else if(((String)nodeInfo).equals("Unidades administrativas")){
					panelVista.removeAll();
					panelABMUnidadesAdministrativas.initLayout();
					jLabel2.setText("Unidades administrativas");
					panelVista.updateUI();	
				}
				else {
					panelVista.removeAll();
					panelABMEnConstruccion.initLayout();
					jLabel2.setText("Seleccione un Abm de la lista");					
					panelVista.updateUI();
					
				}
				
			}
		}
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
		codigoUsuario = Long.parseLong(user.getCodigo());
	}
	
	private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(Main.getVentana(),"Seguro que desea salir de los ABM's","Salir")){
				
				/*if(UserRolesUtil.isAdmin(pantallaABMs.getUsuario())){
					cambiarPantallaA(new PantallaBienvenidaAdmin());
					Main.getVentana().setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				else if(UserRolesUtil.isCold(pantallaABMs.getUsuario())){
						cambiarPantallaA(new PantallaBienvenidaCold());
						Main.getVentana().setExtendedState(JFrame.MAXIMIZED_BOTH);
				}     
				else if(UserRolesUtil.isGerenciaComercial(pantallaABMs.getUsuario())){
						cambiarPantallaA(new PantallaBienvenidaGerencia());
						Main.getVentana().setExtendedState(JFrame.MAXIMIZED_BOTH);
				}		*/
					dispose();
			}			
		}
		
		public void cambiarPantallaA(Pantalla screen){    		
			Ventana vent = Main.getVentana();
			vent.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			vent.getContentPane().removeAll();		
			
			Dimension d = new Dimension(1024,740);
			vent.getContentPane().add(screen); 
			
			vent.setPreferredSize(d);
			vent.setMaximumSize(d);
			vent.setSize(d);        
			vent.pack();
			vent.doLayout();
			vent.setVisible(true);		
			vent.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			vent.repaint();            
		}
		
	}
	
	private class GuardarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)
			(jTree1.getSelectionPath().getLastPathComponent());
			Object nodeInfo = node.getUserObject();
			
			if (node.isLeaf()) {
				if(((String)nodeInfo).equals("Cliente")){
					panelABMCliente.guardar();
				}
				else if(((String)nodeInfo).equals("Sucursales")){
					panelABMSucursales.guardar();
				}
				else if(((String)nodeInfo).equals("Unidades comerciales")){
					panelABMUnidadesComerciales.guardar();
				}
				else if(((String)nodeInfo).equals("Servicios")){
					panelABMServicios.guardar();
				}
				else if(((String)nodeInfo).equals("Descuentos de precios")){
					panelABMDescuentos.guardar();
				}
				else if(((String)nodeInfo).equals("Usuarios")){
					panelABMUsuarios.guardar();
				}
				else if(((String)nodeInfo).equals("Proveedores")){
					panelABMProveedores.guardar();
				}
				else if(((String)nodeInfo).equals("Paises")){
					panelABMPaises.guardar();
				}
				else if(((String)nodeInfo).equals("Provincias")){
					panelABMProvincias.guardar();
				}
				else if(((String)nodeInfo).equals("Partidos")){
					panelABMPartidos.guardar();
				}
				else if(((String)nodeInfo).equals("Localidades")){
					panelABMLocalidades.guardar();
				}
				else if(((String)nodeInfo).equals("Familias de servicios")){
					panelABMFamiliasServicios.guardar();
				}
				else if(((String)nodeInfo).equals("Unidades de negocio")){
					panelABMUnidadesDeNegocio.guardar();
				}
				else if(((String)nodeInfo).equals("Códigos postales")){
					panelABMCodigosPostales.guardar();
				}
				else if(((String)nodeInfo).equals("Accesos")){
					panelABMAccesos.guardar();
				}
				else if(((String)nodeInfo).equals("Perfiles")){
					panelABMPerfiles.guardar();
				}
				else if(((String)nodeInfo).equals("Títulos")){
					panelABMTitulos.guardar();
				}
				else if(((String)nodeInfo).equals("Condiciones de IVA")){
					panelABMCondicionesIVA.guardar();
				}
				else if(((String)nodeInfo).equals("Encabezados de Presupuestos")){
					panelABMEncabezados.guardar();
				}
				else if(((String)nodeInfo).equals("Modalidades de Contratación")){
					panelABMModalidad.guardar();
				}
				else if(((String)nodeInfo).equals("Tipos de eventos")){
					panelABMTipoEvento.guardar();
				}	
				else if(((String)nodeInfo).equals("Tipos de uniformes")){
					panelABMTipoUniforme.guardar();
				}
				else if(((String)nodeInfo).equals("Tipos de lugares de eventos")){
					panelABMTipoLugares.guardar();
				}
				else if(((String)nodeInfo).equals("Tipos de armado")){
					panelABMTipoArmado.guardar();
				}
				else if(((String)nodeInfo).equals("Seguimientos")){
					panelABMAccionesSeguimiento.guardar();
				}
				else if(((String)nodeInfo).equals("Resultados de seguimiento")){
					panelABMResultadosSeguimiento.guardar();
				}
				else if(((String)nodeInfo).equals("Tipos de presupuestos")){
					panelABMTiposPpto.guardar();
				}
				else if(((String)nodeInfo).equals("Período de presupuesto")){
					panelABMPeriodosPpto.guardar();
				}
				else if(((String)nodeInfo).equals("Validez de un presupuesto")){
					panelABMValidezPpto.guardar();
				}
				else if(((String)nodeInfo).equals("Cancelaciones de presupuestos")){
					panelABMCancelacionesPpto.guardar();
				}
				else if(((String)nodeInfo).equals("Categorías de eventos")){
					panelABMCategoriasEvento.guardar();
				}
				else if(((String)nodeInfo).equals("Estados de evento")){
					panelABMEstadosEvento.guardar();
				}
				else if(((String)nodeInfo).equals("Modos de ingreso de equipos")){
					panelABMModosIngreso.guardar();
				}
				else if(((String)nodeInfo).equals("Seguridad de equipos")){
					panelABMSeguridadEquipos.guardar();
				}
				else if(((String)nodeInfo).equals("Condiciones de pago")){
					panelABMCondicionesPago.guardar();
				}
				else if(((String)nodeInfo).equals("Formas de pago")){
					panelABMFormasPago.guardar();
				}
				else if(((String)nodeInfo).equals("Idiomas")){
					panelABMIdiomas.guardar();
				}
				else if(((String)nodeInfo).equals("Categorías de vendedores")){
					panelABMCategoriaVendedor.guardar();
				}
				else if(((String)nodeInfo).equals("Unidades administrativas")){
					panelABMUnidadesAdministrativas.guardar();
				}
				else {
					Util.alertMsg(Main.getVentana(), "Seleccione otro ABM en el panel de la izquierda.");
				}
				
			}			
		}
		
	}
	
	private class BuscarActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)
			(jTree1.getSelectionPath().getLastPathComponent());
			Object nodeInfo = node.getUserObject();
			
			if (node.isLeaf()) {
				if(((String)nodeInfo).equals("Cliente")){
					panelABMCliente.buscar();
				}
				else if(((String)nodeInfo).equals("Sucursales")){
					panelABMSucursales.buscar();
				}
				else if(((String)nodeInfo).equals("Unidades comerciales")){
					panelABMUnidadesComerciales.buscar();
				}
				else if(((String)nodeInfo).equals("Servicios")){
					panelABMServicios.buscar();
				}
				else if(((String)nodeInfo).equals("Usuarios")){
					panelABMUsuarios.buscar();
				}
				else if(((String)nodeInfo).equals("Proveedores")){
					panelABMProveedores.buscar();
				}
				else if(((String)nodeInfo).equals("Paises")){
					panelABMPaises.buscar();
				}
				else if(((String)nodeInfo).equals("Provincias")){
					panelABMProvincias.buscar();
				}
				else if(((String)nodeInfo).equals("Partidos")){
					panelABMPartidos.buscar();
				}
				else if(((String)nodeInfo).equals("Localidades")){
					panelABMLocalidades.buscar();
				}
				else if(((String)nodeInfo).equals("Familias de servicios")){
					panelABMFamiliasServicios.buscar();
				}
				else if(((String)nodeInfo).equals("Unidades de negocio")){
					panelABMUnidadesDeNegocio.buscar();
				}
				else if(((String)nodeInfo).equals("Códigos postales")){
					panelABMCodigosPostales.buscar();
				}
				else if(((String)nodeInfo).equals("Accesos")){
					panelABMAccesos.buscar();
				}
				else if(((String)nodeInfo).equals("Perfiles")){
					panelABMPerfiles.buscar();
				}
				else if(((String)nodeInfo).equals("Títulos")){
					panelABMTitulos.buscar();
				}
				else if(((String)nodeInfo).equals("Condiciones de IVA")){
					panelABMCondicionesIVA.buscar();
				}
				else if(((String)nodeInfo).equals("Encabezados de Presupuestos")){
					panelABMEncabezados.buscar();
				}
				else if(((String)nodeInfo).equals("Modalidades de Contratación")){
					panelABMModalidad.buscar();
				}
				else if(((String)nodeInfo).equals("Tipos de eventos")){
					panelABMTipoEvento.buscar();
				}
				else if(((String)nodeInfo).equals("Tipos de uniformes")){
					panelABMTipoUniforme.buscar();
				}
				else if(((String)nodeInfo).equals("Tipos de lugares de eventos")){
					panelABMTipoLugares.buscar();
				}
				else if(((String)nodeInfo).equals("Tipos de armado")){
					panelABMTipoArmado.buscar();
				}
				else if(((String)nodeInfo).equals("Seguimientos")){
					panelABMAccionesSeguimiento.buscar();
				}
				else if(((String)nodeInfo).equals("Resultados de seguimiento")){
					panelABMResultadosSeguimiento.buscar();
				}
				else if(((String)nodeInfo).equals("Tipos de presupuestos")){
					panelABMTiposPpto.buscar();
				}
				else if(((String)nodeInfo).equals("Período de presupuesto")){
					panelABMPeriodosPpto.buscar();
				}
				else if(((String)nodeInfo).equals("Validez de un presupuesto")){
					panelABMValidezPpto.buscar();
				}
				else if(((String)nodeInfo).equals("Cancelaciones de presupuestos")){
					panelABMCancelacionesPpto.buscar();
				}
				else if(((String)nodeInfo).equals("Categorías de eventos")){
					panelABMCategoriasEvento.buscar();
				}
				else if(((String)nodeInfo).equals("Estados de evento")){
					panelABMEstadosEvento.buscar();
				}
				else if(((String)nodeInfo).equals("Modos de ingreso de equipos")){
					panelABMModosIngreso.buscar();
				}
				else if(((String)nodeInfo).equals("Seguridad de equipos")){
					panelABMSeguridadEquipos.buscar();
				}
				else if(((String)nodeInfo).equals("Condiciones de pago")){
					panelABMCondicionesPago.buscar();
				}
				else if(((String)nodeInfo).equals("Formas de pago")){
					panelABMFormasPago.buscar();
				}
				else if(((String)nodeInfo).equals("Idiomas")){
					panelABMIdiomas.buscar();
				}
				else if(((String)nodeInfo).equals("Categorías de vendedores")){
					panelABMCategoriaVendedor.buscar();
				}
				else if(((String)nodeInfo).equals("Unidades administrativas")){
					panelABMUnidadesAdministrativas.buscar();
				}
				else {
					Util.alertMsg(Main.getVentana(), "El ABM seleccionado no tiene panel de búsqueda.");
				}
			}			
		}
		
	}

}
