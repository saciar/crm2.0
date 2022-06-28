package crm.gui.pantalla;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.AdministradorManager;
import crm.client.managers.PresupuestosManager;
import crm.client.managers.UnidadAdministradorManager;
import crm.client.util.DateConverter;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMCondicionesPagoComboBox;
import crm.gui.components.ABMTipoContactoComboBox;
import crm.gui.components.ABMVendedoresMultiBox;
import crm.gui.components.JXDatePicker;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.reportes.BuscadorReportes;
import crm.gui.pantalla.reportes.ReportBuilder;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesItem;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesServiciosItem;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesServiciosTableModel;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesServiciosTableRender;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesTableModel;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesTableRender;
import crm.gui.tablerenderer.buscadorPptoFacturados.PrespuestosFacturadosItem;
import crm.gui.tablerenderer.buscadorPptoFacturados.PresupuestosFacturadosTableModel;
import crm.gui.tablerenderer.buscadorPptoFacturados.TableRenderPresupuestosFacturados;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.LugarEvento;
import crm.libraries.abm.entities.Usuario;
import crm.libraries.util.MessageUtil;

public class BuscadorReportesFacturacion2 extends PantallaEmergente{
	
	private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private JComboBox jComboBox1;
    private ABMCondicionesPagoComboBox jComboBox2;
    private ABMTipoContactoComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private ABMVendedoresMultiBox jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
   // private javax.swing.JTable jTable1;
    private JXDatePicker jTextField1;
    private JXDatePicker jTextField2;
    private BuscadorReportesTableRender jTable1;
    private BuscadorReportesServiciosTableRender jTable2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox6;
    
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JLabel jLabel7;
    
    private javax.swing.JCheckBox jCheckBox8;
    private JComboBox jComboBox3;
    
    private javax.swing.JButton jButton8;
    private javax.swing.JPanel jPanel8;
    
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JScrollPane jScrollPane3;
    private TableRenderPresupuestosFacturados jTable3;
    
    private javax.swing.JTextField txt_nombre_sub;
    
    private PanelImagen panel;
    private int tablaActiva;
    private Usuario user;
    
    private final static int TABLA_PPTOS=0;
    private final static int TABLA_SERVICIOS=1;
	
    ReportBuilder reportBuilder;
    
	public BuscadorReportesFacturacion2 (Frame owner){
		super("Buscador de reportes",owner);
		this.setModal(false);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setEnabled(true);
        //createMenuBar();
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public void init(){
			//**************Crecion de panel contenedor con imagen de fondo**********************
		reportBuilder = new ReportBuilder();
		try{
			panel = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
		
		java.awt.GridBagConstraints gridBagConstraints;
		
		jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new JXDatePicker();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new JXDatePicker();
        jPanel4 = new javax.swing.JPanel();
        jTable1 = new BuscadorReportesTableRender();
        jTable2 = new BuscadorReportesServiciosTableRender();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new ABMVendedoresMultiBox();
        jSeparator1 = new javax.swing.JSeparator();
        jCheckBox2 = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        jCheckBox3 = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jCheckBox4 = new javax.swing.JCheckBox();
        jComboBox1 = new JComboBox();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jCheckBox5 = new javax.swing.JCheckBox();
        jComboBox2 = new ABMCondicionesPagoComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jCheckBox6 = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jCheckBox7 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new TableRenderPresupuestosFacturados();
        jLabel10 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        txt_nombre_sub = new JTextField();
        jCheckBox8 = new javax.swing.JCheckBox();
        jComboBox3 = new JComboBox();
        
        jScrollPane1 = new JScrollPane();        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(0, 0));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rango de Fechas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        jLabel1.setText("Desde");

        jLabel2.setText("Hasta");       

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(588, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabla", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N
        
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Cant. registros");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Total $");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 526, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(147, 147, 147))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        jButton4.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));
        jButton4.setText("Buscar");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton1.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        jButton1.setText("Ver Reporte");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton2.setIcon(new javax.swing.ImageIcon(getUrlImagen("page_excel.png")));
        jButton2.setText("Exportar");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);        
        
        jButton3.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        jButton3.setText("Salir");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton3))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jButton4)
                .addGap(66, 66, 66)
                .addComponent(jButton1)
                .addGap(67, 67, 67)
                .addComponent(jButton2)
                .addGap(62, 62, 62)
                .addComponent(jButton3)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Presupuestos", jPanel1);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        jCheckBox1.setText("Vendedores");

        jList1.loadItemsAllVendedores();
        jScrollPane2.setViewportView(jList1);

        jCheckBox2.setText("Lugar");

        jCheckBox3.setText("Cliente");

        jButton5.setText("Buscar Lugar");
        jButton5.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));

        jButton6.setText("Buscar Cliente");
        jButton6.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));

        jCheckBox4.setText("Estado");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Confirmado", "Pendiente", "Cancelado", "Rechazado", "Facturado" }));

        jCheckBox5.setText("Condiciones de Pago");

        jComboBox2.loadItems();        

        jLabel5.setText("");

        jLabel6.setText("");
        
        jCheckBox8.setText("Facturado por");
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baires", "MDQ", "City", "Rural" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jCheckBox2)
                        .addGap(88, 88, 88)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jCheckBox3)
                        .addGap(81, 81, 81)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jCheckBox4)
                        .addGap(82, 82, 82)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jCheckBox5)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jCheckBox8)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox2)
                    .addComponent(jButton5)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox3)
                    .addComponent(jButton6)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox8)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))    
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Servicios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        jCheckBox6.setText("Servicios");

        jButton7.setText("Buscar servicios");
        jButton7.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));

        jCheckBox7.setText("Subcontratados");

        jLabel7.setText("");        
        
        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jCheckBox6)
                        .addGap(75, 75, 75)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jCheckBox7)
                        .addGap(75, 75, 75)
                        .addComponent(txt_nombre_sub)))
                        //.addComponent(jCheckBox7))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox6)
                    .addComponent(jButton7)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox7)
                    .addComponent(txt_nombre_sub))
                //.addComponent(jCheckBox7)
                .addContainerGap(189, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Filtros", jPanel2);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Otros reportes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        jButton8.setText("Lista de Precios");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8)
                .addContainerGap(856, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8)
                .addContainerGap(610, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lista de Precios", jPanel8);
        
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rango de Fechas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        jLabel8.setText("Nro Presupuesto");

        jLabel9.setText("Nro Factura");


        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(547, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Facturados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        //jScrollPane3.setViewportView(jTable3);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel10.setText("Cant. registros");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jTable3, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jLabel10)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTable3, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        jButton9.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));
        jButton9.setText("Buscar");
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton10.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));
        jButton10.setText("Vista Previa");
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.setVisible(false);

        jButton11.setIcon(new javax.swing.ImageIcon(getUrlImagen("disk.png")));
        jButton11.setText("Guardar");
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton12.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        jButton12.setText("Salir");
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    /*.addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton10))*/
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton11))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton12))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jButton9)
              /*  .addGap(66, 66, 66)
                .addComponent(jButton10)*/
                .addGap(67, 67, 67)
                .addComponent(jButton11)
                .addGap(62, 62, 62)
                .addComponent(jButton12)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Presupuestos Facturados", jPanel9);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        
        getContentPane().add(panel);
        pack();	
        createListeners();
		
	}
	
	public String getTotalFormateado(double tot) {
		return getCurrencyFormat().format(tot);
	}
	
	private NumberFormat getCurrencyFormat() {
		NumberFormat currencyFormat;
		Locale l = new Locale("es","AR");
		currencyFormat = NumberFormat.getCurrencyInstance(l);
		return currencyFormat;
	}
	
	private void createListeners(){
		jButton3.addActionListener(new SalirActionListener());
		jButton4.addActionListener(new BuscarActionListener());
		jButton5.addActionListener(new BuscarLugaresActionListener());
		jButton6.addActionListener(new BuscarClienteActionListener());
		jButton7.addActionListener(new BuscarServicioActionListener());
		jButton2.addActionListener(new ExportarActionListener());
		jButton1.addActionListener(new VerReporteActionListener());
		jButton8.addActionListener(new ListaPreciosActionListener());
		jButton9.addActionListener(new BuscarFacturados());
		jButton12.addActionListener(new SalirActionListener());
		jButton11.addActionListener(new GuardarActionListener());
		jTextField3.addKeyListener(new BuscarFacturadosPptoKeyListener());
		jTextField4.addKeyListener(new BuscarFacturadosFacturaKeyListener());
	}
	
/*-----------------------------------------------------------------------------------------------------------------*/
	private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			dispose();			
		}
		
	}
	
	private class ExportarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(tablaActiva==TABLA_SERVICIOS)				
				jTable2.openSavePopUp();
			else
				jTable1.openSavePopUp();
		}
		
	}
	
	private class BuscarActionListener implements ActionListener{
		private String codCliente;
    	private String codClienteFact;
    	private String fechaDesde;
    	private String fechaHasta;
    	private String codLugar;
    	private String[] codVendedores;
    	private String codCondPago;
    	private String estado;
    	private String codTipoEvento;
    	private String codServicio;
    	private String codFacuracion;
    	
    	private void setCriteria(){
    		
			fechaDesde = DateConverter.convertDateToString(jTextField1.getDate(), "yyyy-MM-dd");
			fechaHasta = DateConverter.convertDateToString(jTextField2.getDate(), "yyyy-MM-dd");
			jTable2.setComentariosXLS("Fecha desde "+fechaDesde+" hasta "+fechaHasta);
			jTable1.setComentariosXLS("Fecha desde "+fechaDesde+" hasta "+fechaHasta);
			
			if(jCheckBox3.isSelected() && clienteElegido != null){
				codCliente = clienteElegido.getCodigo();
				jTable2.setComentariosXLS(jTable2.getComentariosXLS()+" // Cliente: "+clienteElegido.getEmpresa());
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Cliente: "+clienteElegido.getEmpresa());
			}
    		else
    			codCliente=null;
			if(jCheckBox2.isSelected() && lugarElegido!=null){
				codLugar= lugarElegido.getCodigo();
				jTable2.setComentariosXLS(jTable2.getComentariosXLS()+" // Lugar: "+lugarElegido.getNombreLugar());
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Lugar: "+lugarElegido.getNombreLugar());
			}
			else
				codLugar=null;
			if(jCheckBox4.isSelected()){
				estado= (String)jComboBox1.getSelectedItem();
				jTable2.setComentariosXLS(jTable2.getComentariosXLS()+" // Estado: "+estado);
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Estado: "+estado);
			}
			else
				estado=null;
			
			if(jCheckBox8.isSelected()){
				codFacuracion=String.valueOf(jComboBox3.getSelectedIndex()+1);
			}
			else
				codFacuracion=null;
			if(jCheckBox1.isSelected()){
				codVendedores=jList1.searchForeignsArray();
				jTable2.setComentariosXLS(jTable2.getComentariosXLS()+" // Vendedores: ");
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Vendedores: ");
				for(int i=0; i<jList1.getSelectedValues().length;i++){
					if(i!=0)
						jTable1.setComentariosXLS(jTable1.getComentariosXLS()+",");
					jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" "+(String)jList1.getSelectedValues()[i]);
				}
			}
			else
				codVendedores =null;
			if(jCheckBox5.isSelected()){
				codCondPago = jComboBox2.searchForeign();
				jTable2.setComentariosXLS(jTable2.getComentariosXLS()+" // Cond. de pago: "+(String)jComboBox2.getSelectedItem());
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Cond. de pago: "+(String)jComboBox2.getSelectedItem());
			}
			else
				codCondPago=null;
			if(jCheckBox6.isSelected()){
				codServicio=codServicioElegido;
				jTable2.setComentariosXLS(jTable2.getComentariosXLS()+" // Servicio: "+jLabel7.getText());
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Servicio: "+jLabel7.getText());
			}
			else
				codServicio= null;		
			
    	}
    	private void buscarReportes(){
    		try {
				Object[] presupuestos = null;
				BuscadorReportesTableModel model = new BuscadorReportesTableModel();		
				
				setCriteria();
				presupuestos = PresupuestosManager.instance().buscarParaReportes(codCliente, codClienteFact,fechaDesde,fechaHasta,codLugar,codCondPago,estado,codTipoEvento,codServicio, codFacuracion);
				
				double tot=0;
				int cant=0;
				if(presupuestos != null){
					for(int i=0; i<presupuestos.length; i++){
						BuscadorReportesItem item = new BuscadorReportesItem();

						Object[] p = (Object[]) presupuestos;
						Object[] presupuestoDato = (Object[])p[i];
						boolean vendedorValido=false;

						if(codVendedores != null){
							for(int j=0;j<codVendedores.length;j++){
								if(codVendedores[j].equals((String)presupuestoDato[7])){
									vendedorValido=true;									
									break;
								}
							}
						}
						else{
							vendedorValido=true;
						}
						if(vendedorValido){
							cant++;
							item.setNumeroPpto((Long)(presupuestoDato[0]));
							item.setEstado((String)presupuestoDato[5]);
							item.setVendedor((String)presupuestoDato[1]);
							item.setCliente((String)presupuestoDato[2]);				
							item.setNombreEvento((String)presupuestoDato[3]);
							item.setFechaInicio((String)presupuestoDato[4]);
							item.setTotal(((Double)presupuestoDato[6]).doubleValue());
							item.setFechaCreacion((String)presupuestoDato[8]);
							item.setLugar((String)presupuestoDato[9]);
							item.setFechaFinal((String)presupuestoDato[10]);
							item.setCodLugar((String)presupuestoDato[12]);
							tot=tot+(Double)presupuestoDato[6];
							model.addRow(item);
							jLabel3.setText("Cant. registros: "+cant);
							jLabel4.setText("Total: "+getTotalFormateado(tot));
						}
					}	
					
					
				}
				jTable1.getTable().setModel(model);
				jTable1.refreshTable();
				if(model.getRowCount() <= 0){
					Util.alertMsg(Main.getVentana(), "No se encontraron presupuestos");					
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	
    	   private void buscarReportesXServicios() {
            try {
                Object[] presupuestos = null;
                BuscadorReportesServiciosTableModel model = new BuscadorReportesServiciosTableModel();

                setCriteria();
                presupuestos = PresupuestosManager.instance().buscarParaReportesDeServicios(codCliente, fechaDesde, fechaHasta, codLugar, codCondPago, estado, jCheckBox7.isSelected(), codServicio,"");

                double tot = 0;
                int cant = 0;
                if (presupuestos != null) {
                    for (int i = 0; i < presupuestos.length; i++) {
                        BuscadorReportesServiciosItem item = new BuscadorReportesServiciosItem();

                        Object[] p = (Object[]) presupuestos;
                        Object[] presupuestoDato = (Object[]) p[i];
                        boolean vendedorValido = false;

                        if (codVendedores != null) {
                            for (int j = 0; j < codVendedores.length; j++) {
                                if (codVendedores[j].equals((String) presupuestoDato[11])) {
                                    vendedorValido = true;
                                    break;
                                }
                            }
                        } else {
                            vendedorValido = true;
                        }
                        if (vendedorValido) {
                            cant++;
                            item.setNumeroPpto((Long) (presupuestoDato[3]));
                            if ("Subcontratado".equals((String) presupuestoDato[2])) {
                                item.setServicio((String) presupuestoDato[2] + " - " + (String) presupuestoDato[13]);
                            } else {
                                item.setServicio((String) presupuestoDato[2]);
                            }
                            item.setCantidad((Integer) presupuestoDato[0]);
                            item.setDias((Integer) presupuestoDato[1]);
                            item.setCliente((String) presupuestoDato[12]);
                            item.setVendedor((String) presupuestoDato[5]);
                            item.setFechaFin((String) presupuestoDato[15]);

                            item.setTotalCompra((Double) presupuestoDato[14]);

                            item.setNombreEvento((String) presupuestoDato[4]);
                            item.setFechaInicio((String) presupuestoDato[6]);
                            item.setTotalEvento((Double) presupuestoDato[9]);
                            item.setDescuento((Integer) presupuestoDato[8]);
                            item.setTotalServicio((Double) presupuestoDato[7]);
                            tot = tot + (Double) presupuestoDato[7];
                            model.addRow(item);
                            jLabel3.setText("Cant. registros: " + cant);
                            jLabel4.setText("Total: " + getTotalFormateado(tot));
                        }
                    }


                }
                jTable2.getTable().setModel(model);
                jTable2.refreshTable();
                if (model.getRowCount() <= 0) {
                    Util.alertMsg(Main.getVentana(), "No se encontraron registros");
                }
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void actionPerformed(ActionEvent arg0) {
            if (jCheckBox6.isSelected() || jCheckBox7.isSelected()) {
                jScrollPane1.setViewportView(jTable2.getTable());
                buscarReportesXServicios();
                tablaActiva = TABLA_SERVICIOS;
            } else {
                jScrollPane1.setViewportView(jTable1.getTable());
                buscarReportes();
                tablaActiva = TABLA_PPTOS;
            }
        }
    }
    private LugarEvento lugarElegido;

    public LugarEvento getLugarElegido() {
        return lugarElegido;
    }

    public void setLugarElegido(LugarEvento lugarElegido) {
       	this.lugarElegido = lugarElegido;
	}
	private BuscadorLugarEvento buscador;
    private class BuscarLugaresActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (buscador == null){
				buscador = new BuscadorLugarEvento(Main.getVentana());
				buscador.init();
				buscador.initComponent();
				buscador.setLugarAnterior(lugarElegido);
				buscador.setBuscadorReportesFact(BuscadorReportesFacturacion2.this);
			}
			buscador.setVisible(true);
			if (lugarElegido != null) {
				jLabel5.setText(lugarElegido.getNombreLugar());
				//panel.updateUI();
			}
		}
    	
    }
    
    private Cliente clienteElegido;

	public Cliente getClienteElegido() {
		return clienteElegido;
	}

	public void setClienteElegido(Cliente clienteElegido) {
		this.clienteElegido = clienteElegido;
	}
    
    private class BuscarClienteActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			BuscadorClientes buscador = new BuscadorClientes(Main.getVentana());
			buscador.init();
			buscador.initComponent();
			buscador.setVisible(true);
			clienteElegido = buscador.getSelectedClient();
			if (clienteElegido != null) {
				jLabel6.setText(clienteElegido.getEmpresa());
				panel.updateUI();
			}
			
		}
    	
    }
    
    private String codServicioElegido;

	public String getServicioElegido() {
		return codServicioElegido;
	}

	public void setServicioElegido(String servicioElegido) {
		this.codServicioElegido = servicioElegido;
	}
    
    private BuscadorServicios buscadorServicio; 
	private class BuscarServicioActionListener implements ActionListener {
		public void actionPerformed (ActionEvent evt) {
			if (buscadorServicio == null){
				buscadorServicio = new BuscadorServicios(Main.getVentana(), BuscadorReportesFacturacion2.this);
				buscadorServicio.init();
				buscadorServicio.initComponent();
			}
			buscadorServicio.setVisible(true);
			codServicioElegido=buscadorServicio.getServicioElegido().getServicioCodigo();
			if(buscadorServicio.getServicioElegido() != null){
				jLabel7.setText(buscadorServicio.getServicioElegido().getServicio());				
			}
		}
	}
	
	private class VerReporteActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(tablaActiva==TABLA_SERVICIOS)		
				if(codServicioElegido != null)
					reportBuilder.viewReportServicios((BuscadorReportesServiciosTableModel)jTable2.getTable().getModel(), "Servicio seleccionado: "+buscadorServicio.getServicioElegido().getServicio());
				else
					reportBuilder.viewReportServicios((BuscadorReportesServiciosTableModel)jTable2.getTable().getModel(), "Servicio seleccionado: Subcontratados");
			else{
				
				reportBuilder.viewReport((BuscadorReportesTableModel)jTable1.getTable().getModel(),"Presupuestos del "+DateConverter.convertDateToString(jTextField1.getDate(),"EEEEE dd MMMMM yyyy")+" hasta  "+DateConverter.convertDateToString(jTextField2.getDate(),"EEEEE dd MMMMM yyyy"));
			}						
		}
		
	}
	
	private class ListaPreciosActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			reportBuilder.previewListaPreciosReport(DateConverter.convertDateToString(new Date(), "yyyy-MM-dd"));			
		}
		
	}
	
	private void buscarFact(){
		try {
			PresupuestosManager manager = PresupuestosManager.instance();
			String codAdmin = AdministradorManager.instance().getCodAdministradorByCodUsuario(user.getCodigo());
			PresupuestosFacturadosTableModel model = new PresupuestosFacturadosTableModel();
			String codUnidad=null;

			if(codAdmin != null)
					codUnidad = UnidadAdministradorManager.instance().getCodigoUnidad(codAdmin);
			if(codUnidad != null){
				Object[] presupuestos =null;
				if(StringUtils.isBlank(jTextField3.getText()))
					presupuestos = manager.buscarParaReportesFacturacion(0, jTextField4.getText(), codUnidad);
				else
					presupuestos = manager.buscarParaReportesFacturacion(Long.parseLong(jTextField3.getText()), jTextField4.getText(), codUnidad);
				if(presupuestos != null && presupuestos.length>0 ){
					for(int i=0; i<presupuestos.length; i++){
						PrespuestosFacturadosItem item = new PrespuestosFacturadosItem();

						Object[] p = (Object[]) presupuestos;
						Object[] presupuestoDato = (Object[])p[i];

						item.setNroPpto((Long)(presupuestoDato[0]));
						item.setCliente((String)(presupuestoDato[1]));
						item.setNombreEvento((String)(presupuestoDato[2]));
						item.setNroFactura((String)(presupuestoDato[3]));
						item.setFechaFactura((String)(presupuestoDato[4]));
						item.setNroFacturaAdelanto((String)(presupuestoDato[5]));
						item.setFechaFacturaAdelanto((String)(presupuestoDato[6]));
						item.setFacturadoPor(((Integer)presupuestoDato[9]).toString());
						if(presupuestoDato[7]!=null)
							item.setId_factura1((Long)(presupuestoDato[7]));
						if(presupuestoDato[8]!=null)
							item.setId_factura2((Long)(presupuestoDato[8]));
						model.addRow(item);
					}
					jTable3.getTable().setModel(model);
					jTable3.refreshTable();
				}
				else
					MessageUtil.showMessage("No existe presupuesto con esos requerimientos");
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
	
	
	private class BuscarFacturados implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			buscarFact();
		}
		
	}
	
	private class BuscarFacturadosPptoKeyListener implements KeyListener{

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (!StringUtils.isBlank(jTextField3.getText())){
					buscarFact();
				}
				else Util.alertMsg(Main.getVentana(), "Especifique un n�mero de presupuesto");
			}
			
		}

		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class BuscarFacturadosFacturaKeyListener implements KeyListener{

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (!StringUtils.isBlank(jTextField4.getText())){
					buscarFact();
				}
				else Util.alertMsg(Main.getVentana(), "Especifique un n�mero de factura");
			}
			
		}

		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class GuardarActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            PresupuestosFacturadosTableModel model = (PresupuestosFacturadosTableModel) jTable3.getTable().getModel();
            PresupuestosManager manager = PresupuestosManager.instance();
            for (int i = 0; i < model.getRowCount(); i++) {
                PrespuestosFacturadosItem item = model.getRow(i);
                try {
                    if (StringUtils.isNotEmpty(item.getNroFactura())) {
                        if (item.getId_factura1() > 0) {
                            manager.setFactura(item.getId_factura1(), item.getNroFactura());
                        } else {
                            manager.setAsFacturado(String.valueOf(item.getNroPpto()), user.getCodigo(), item.getNroFactura());
                        }
                    }
                    if (item.getNroFacturaAdelanto() != null) {
                        if (item.getId_factura2() > 0) {
                            manager.setFactura(item.getId_factura2(), item.getNroFacturaAdelanto());
                        } else {
                            manager.setAsAdelantado(String.valueOf(item.getNroPpto()), user.getCodigo(), item.getNroFacturaAdelanto());
                        }
                    }
                } catch (RemoteException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }

}
