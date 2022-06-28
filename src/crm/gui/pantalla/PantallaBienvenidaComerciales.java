package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import crm.client.helper.UserRolesUtil;
import crm.client.managers.PerfilManager;
import crm.client.managers.PresupuestosManager;
import crm.client.managers.SucursalManager;
import crm.client.managers.UnidadComercialManager;
import crm.client.managers.VendedorUsuarioManager;
import crm.client.serializer.SerializerManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.reportes.BuscadorReportes;
import crm.gui.pantalla.solapa.MainPanelComercial;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.UnidadComercial;
import crm.libraries.util.MessageUtil;

public class PantallaBienvenidaComerciales extends PantallaBienvenidaVerdadera{
	
	private javax.swing.JButton abiertos;
    private javax.swing.JButton buscar;
    private javax.swing.JLabel cantConfirmadosUC;
    private javax.swing.JLabel cantConfirmadosUser;
    private javax.swing.JLabel cantHoyUC;
    private javax.swing.JLabel cantHoyUser;
    private javax.swing.JLabel cantPendientesUC;
    private javax.swing.JLabel cantPendientesUser;
    private javax.swing.JLabel cantVencerUC;
    private javax.swing.JLabel cantVencerUser;
    private javax.swing.JButton confirmadosDetalles;
    private javax.swing.JButton confirmadosDetallesUC;
    private javax.swing.JButton hoyDetalles;
    private javax.swing.JButton hoyDetallesUC;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel mes;
    private javax.swing.JLabel mesUser;
    private javax.swing.JLabel nombreSucursal;
    private javax.swing.JLabel nroObjetivo;
    private javax.swing.JButton nuevo;
    private javax.swing.JButton pendientesDetalles;
    private javax.swing.JButton pendientesDetallesUC;
    private javax.swing.JButton reportes;
    private GradientButton salir;
    private javax.swing.JLabel uc;
    private javax.swing.JLabel unidad;
    private javax.swing.JLabel user;
    private javax.swing.JLabel userName;
    private javax.swing.JLabel valorConfirmadosUC;
    private javax.swing.JLabel valorConfirmadosUser;
    private javax.swing.JLabel valorHoyUC;
    private javax.swing.JLabel valorHoyUser;
    private javax.swing.JLabel valorPendientesUC;
    private javax.swing.JLabel valorPendientesUser;
    private javax.swing.JLabel valorVencerUC;
    private javax.swing.JLabel valorVencerUser;
    private javax.swing.JButton vencerDetalles;
    private javax.swing.JButton vencerDetallesUC;
    private PanelImagen middlePanel;   
    
	public PantallaBienvenidaComerciales() {
		super();		
		setMainPanel2(new MainPanelComercial());		
		getMainPanel2().init();		
	}

	
	public void initComponent() {	
		try{
			//middlePanel = new PanelImagen("Abstract_4220_1024x768.jpg");
			middlePanel = new PanelImagen("Untitled-1.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			middlePanel = new PanelImagen();
		}

		jLabel1 = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nroObjetivo= new javax.swing.JLabel();
        jLabel9= new javax.swing.JLabel();
        unidad= new javax.swing.JLabel();
        jLabel11= new javax.swing.JLabel();
        nombreSucursal= new javax.swing.JLabel();
        jSeparator1= new javax.swing.JSeparator();
        jLabel13= new javax.swing.JLabel();
        uc= new javax.swing.JLabel();
        jLabel15= new javax.swing.JLabel();
        mes= new javax.swing.JLabel();
        cantPendientesUC= new javax.swing.JLabel();
        cantVencerUC= new javax.swing.JLabel();
        cantHoyUC= new javax.swing.JLabel();
        cantConfirmadosUC= new javax.swing.JLabel();
        jLabel6= new javax.swing.JLabel();
        jLabel8= new javax.swing.JLabel();
        jLabel10= new javax.swing.JLabel();
        jLabel12= new javax.swing.JLabel();
        valorPendientesUC= new javax.swing.JLabel();
        valorVencerUC= new javax.swing.JLabel();
        valorHoyUC= new javax.swing.JLabel();
        valorConfirmadosUC= new javax.swing.JLabel();
        jLabel14= new javax.swing.JLabel();
        jLabel16= new javax.swing.JLabel();
        jLabel17= new javax.swing.JLabel();
        jLabel18= new javax.swing.JLabel();
        jLabel19= new javax.swing.JLabel();
        jLabel20= new javax.swing.JLabel();
        userName= new javax.swing.JLabel();
        mesUser= new javax.swing.JLabel();
        cantPendientesUser= new javax.swing.JLabel();
        cantVencerUser= new javax.swing.JLabel();
        cantHoyUser= new javax.swing.JLabel();
        cantConfirmadosUser= new javax.swing.JLabel();
        valorPendientesUser= new javax.swing.JLabel();
        valorVencerUser= new javax.swing.JLabel();
        valorHoyUser= new javax.swing.JLabel();
        valorConfirmadosUser= new javax.swing.JLabel();
        jSeparator2= new javax.swing.JSeparator();
        nuevo= new GradientButton("", Color.blue);
        nuevo.requestFocus();
        buscar= new GradientButton("", Color.blue);
        reportes= new GradientButton("", Color.blue);
        abiertos= new GradientButton("", Color.blue);
        salir= new GradientButton("", Color.blue);
        vencerDetallesUC= new GradientButton("", Color.blue);
        pendientesDetallesUC= new GradientButton("", Color.blue);
        hoyDetallesUC= new GradientButton("", Color.blue);
        confirmadosDetallesUC= new GradientButton("", Color.blue);
        pendientesDetalles= new GradientButton("", Color.blue);
        vencerDetalles= new GradientButton("", Color.blue);
        hoyDetalles= new GradientButton("", Color.blue);
        confirmadosDetalles= new GradientButton("", Color.blue);
        jSeparator3= new javax.swing.JSeparator();
        
        createListeners();
		
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20));
        jLabel1.setText("Bienvenido/a");
        
        user.setFont(new java.awt.Font("Tahoma", 1, 20));
        user.setForeground(new java.awt.Color(163, 184, 204));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Objetivo del mes");

        nroObjetivo.setFont(new java.awt.Font("Tahoma", 1, 11));
        nroObjetivo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel9.setText("Unidad Comercial");

        unidad.setFont(new java.awt.Font("Tahoma", 1, 14));
        unidad.setForeground(new java.awt.Color(164, 184, 204));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Sucursal");

        nombreSucursal.setFont(new java.awt.Font("Tahoma", 1, 11));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel13.setText("Presupuestos de");

        uc.setFont(new java.awt.Font("Tahoma", 0, 12));
        uc.setForeground(new java.awt.Color(164, 184, 204));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel15.setText("en el mes de");

        mes.setFont(new java.awt.Font("Tahoma", 0, 12));
        mes.setForeground(new java.awt.Color(164, 184, 204));

        cantPendientesUC.setFont(new java.awt.Font("Tahoma", 1, 11));
        cantVencerUC.setFont(new java.awt.Font("Tahoma", 1, 11));
        cantHoyUC.setFont(new java.awt.Font("Tahoma", 1, 11));
        cantConfirmadosUC.setFont(new java.awt.Font("Tahoma", 1, 11));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel6.setText("Presupuestos pendientes por");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel8.setText("Presupuestos por vencer por");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel10.setText("Presupuestos que se hacen hoy por");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel12.setText("Presupuestos confirmados por");       
        
        valorConfirmadosUC.setFont(new java.awt.Font("Tahoma", 1, 11));
        valorHoyUC.setFont(new java.awt.Font("Tahoma", 1, 11));
        valorPendientesUC.setFont(new java.awt.Font("Tahoma", 1, 11));
        valorVencerUC.setFont(new java.awt.Font("Tahoma", 1, 11));
        
        valorVencerUC.setForeground(new java.awt.Color(255, 0, 0));       
        valorConfirmadosUC.setForeground(new java.awt.Color(0, 153, 0)); 
        
        cantPendientesUser.setFont(new java.awt.Font("Tahoma", 1, 11));
        cantVencerUser.setFont(new java.awt.Font("Tahoma", 1, 11));
        cantHoyUser.setFont(new java.awt.Font("Tahoma", 1, 11));
        cantConfirmadosUser.setFont(new java.awt.Font("Tahoma", 1, 11));
        
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel14.setText("Presupuestos de");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel16.setText("Presupuestos confirmados por");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel17.setText("Presupuestos pendientes por");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel18.setText("Presupuestos por vencer por");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel19.setText("Presupuestos que se hacen hoy por");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel20.setText("en el mes de");

        userName.setFont(new java.awt.Font("Tahoma", 0, 12));
        userName.setForeground(new java.awt.Color(164, 184, 204));

        mesUser.setFont(new java.awt.Font("Tahoma", 0, 12));
        mesUser.setForeground(new java.awt.Color(164, 184, 204));
        
        valorConfirmadosUser.setFont(new java.awt.Font("Tahoma", 1, 11));
        valorHoyUser.setFont(new java.awt.Font("Tahoma", 1, 11));
        valorPendientesUser.setFont(new java.awt.Font("Tahoma", 1, 11));
        valorVencerUser.setFont(new java.awt.Font("Tahoma", 1, 11));
        
        valorConfirmadosUser.setForeground(new java.awt.Color(0, 153, 0));        
        valorVencerUser.setForeground(new java.awt.Color(255, 0, 0));        
        
        nuevo.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form.png")));
        nuevo.setText("Nuevo Presupuesto");
        nuevo.setMnemonic('n');
        nuevo.setToolTipText("Click para comenzar un presupuesto");
        nuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);        
        
        buscar.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_edit.png")));
        buscar.setText("Buscar Presupuesto");
        buscar.setMnemonic('b');
        buscar.setToolTipText("Click para buscar un presupuesto");
        buscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);        
        
        reportes.setIcon(new javax.swing.ImageIcon(getUrlImagen("layout_sidebar.png")));
        reportes.setText("Reportes");
        reportes.setMnemonic('r');
        reportes.setToolTipText("Click para ver lista de precios de servicios");
        reportes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reportes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);        

        abiertos.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_delete.png")));
        abiertos.setText("Presupuestos Abiertos");
        abiertos.setMnemonic('a');
        abiertos.setToolTipText("Click para ver presupuestos abiertos");
        abiertos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        abiertos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        

        salir.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        salir.setText("Salir");
        salir.setMnemonic('s');
        salir.setToolTipText("Click para salir");
        salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);        
        
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getUrlImagen("magnifier.png"));
        
       vencerDetallesUC.setIcon(icon);
       vencerDetallesUC.setText("ver detalles");
       vencerDetallesUC.setMnemonic('v');
       vencerDetallesUC.setToolTipText("Click para ver presupuestos a vencer por UC");
        
        pendientesDetallesUC.setIcon(icon);
        pendientesDetallesUC.setText("ver detalles");
        pendientesDetallesUC.setToolTipText("Click para ver presupuestos pendientes por UC");
        
        hoyDetallesUC.setIcon(icon);
        hoyDetallesUC.setText("ver detalles");
        hoyDetallesUC.setToolTipText("Click para ver presupuestos de hoy por UC");     
        
        confirmadosDetallesUC.setIcon(icon);
        confirmadosDetallesUC.setText("ver detalles");
        confirmadosDetallesUC.setToolTipText("Click para ver presupuestos confirmados por UC");
        
        pendientesDetalles.setIcon(icon);
        pendientesDetalles.setText("ver detalles");
        pendientesDetalles.setToolTipText("Click para ver sus presupuestos pendientes");

        vencerDetalles.setIcon(icon);
        vencerDetalles.setText("ver detalles");
        vencerDetalles.setToolTipText("Click para ver sus presupuestos a vencer");
        
        hoyDetalles.setIcon(icon);
        hoyDetalles.setText("ver detalles");
        hoyDetalles.setToolTipText("Click para ver sus presupuestos de hoy");
        
        confirmadosDetalles.setIcon(icon);
        confirmadosDetalles.setText("ver detalles");
        confirmadosDetalles.setToolTipText("Click para ver sus presupuestos confirmados");

	}
	
	public void createMenuBAr(){
		Main.getVentana().setMyMenu(createMenu());
	}
	
	public void createLayout() {		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(middlePanel);
        middlePanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                        .add(jLabel14)
                                        .add(cantConfirmadosUser)
                                        .add(cantHoyUser)                                       
                                        .add(cantVencerUser)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(layout.createSequentialGroup()
                                                .add(112, 112, 112)
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, cantVencerUC, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, cantHoyUC, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .add(layout.createSequentialGroup()
                                                .add(10, 10, 10)
                                                .add(jLabel13))
                                            .add(org.jdesktop.layout.GroupLayout.TRAILING, cantPendientesUC))
                                        .add(cantPendientesUser)
                                        .add(cantConfirmadosUC))
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                            .add(uc)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jLabel15)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(mes))
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                            .add(65, 65, 65)
                                            .add(nuevo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(buscar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(reportes, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(abiertos, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(salir, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                                            .add(144, 144, 144))
                                        .add(layout.createSequentialGroup()
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(layout.createSequentialGroup()
                                                    .add(userName)
                                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                    .add(jLabel20)
                                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                    .add(mesUser))
                                                .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                                        .add(layout.createSequentialGroup()                                                        	
                                                            .add(jLabel18)
                                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                            .add(valorVencerUser, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                            .add(vencerDetalles))
                                                        .add(layout.createSequentialGroup()
                                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                                                    .add(jLabel17)
                                                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                                    .add(valorPendientesUser, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
                                                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                                                    .add(jLabel16)
                                                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                                    .add(valorConfirmadosUser, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)))
                                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                                                .add(pendientesDetalles)
                                                                .add(confirmadosDetalles)))
                                                        .add(layout.createSequentialGroup()
                                                            .add(jLabel19)
                                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                            .add(valorHoyUser, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                            .add(hoyDetalles)))
                                                    .add(37, 37, 37)))
                                            .add(29, 29, 29))
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(layout.createSequentialGroup()
                                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                        .add(layout.createSequentialGroup()
                                                            .add(jLabel6)
                                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                            .add(valorPendientesUC, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
                                                        .add(layout.createSequentialGroup()
                                                            .add(jLabel8)
                                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                            .add(valorVencerUC, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)))
                                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                                                .add(layout.createSequentialGroup()
                                                    .add(jLabel10)
                                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                    .add(valorHoyUC, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                                                .add(layout.createSequentialGroup()
                                                    .add(jLabel12)
                                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                    .add(valorConfirmadosUC, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(pendientesDetallesUC)
                                                .add(vencerDetallesUC)
                                                .add(hoyDetallesUC)
                                                .add(confirmadosDetallesUC))
                                            .add(65, 65, 65)))
                                    .add(117, 117, 117))
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator3)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator1)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)))
                            .addContainerGap())
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(10, 10, 10)
                                    .add(jLabel7)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(nroObjetivo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 260, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(layout.createSequentialGroup()
                                    .add(jLabel1)                                    
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(user)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                            .add(382, 382, 382)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel9)
                                .add(layout.createSequentialGroup()
                                    .add(jLabel11)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(nombreSucursal)))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(unidad)
                            .addContainerGap(144, Short.MAX_VALUE))))
            );

            layout.linkSize(new java.awt.Component[] {confirmadosDetallesUC, confirmadosDetalles, hoyDetallesUC, hoyDetalles, pendientesDetallesUC, pendientesDetalles, vencerDetallesUC, vencerDetalles}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(25, 25, 25)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel9)
                                .add(unidad))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel11)
                                .add(nombreSucursal)))
                        .add(layout.createSequentialGroup()
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel1)
                                .add(user))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel7)
                                .add(nroObjetivo))))
                    .add(19, 19, 19)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(17, 17, 17)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel13)
                        .add(uc)
                        .add(jLabel15)
                        .add(mes))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel6)
                        .add(valorPendientesUC)
                        .add(pendientesDetallesUC)
                        .add(cantPendientesUC))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel8)
                        .add(cantVencerUC)
                        .add(vencerDetallesUC)
                        .add(valorVencerUC))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel10)
                        .add(cantHoyUC)
                        .add(hoyDetallesUC)
                        .add(valorHoyUC))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel12)
                        .add(valorConfirmadosUC)
                        .add(cantConfirmadosUC)
                        .add(confirmadosDetallesUC))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(21, 21, 21)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel20)
                        .add(userName)
                        .add(jLabel14)
                        .add(mesUser))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel17)
                        .add(cantPendientesUser)
                        .add(pendientesDetalles)
                        .add(valorPendientesUser))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    	.add(cantVencerUser)
                        .add(vencerDetalles)
                        .add(jLabel18)
                        .add(valorVencerUser))                     
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(cantHoyUser)
                        .add(hoyDetalles)
                        .add(jLabel19)
                        .add(valorHoyUser))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(cantConfirmadosUser)
                        .add(confirmadosDetalles)
                        .add(valorConfirmadosUser)
                        .add(jLabel16))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 88, Short.MAX_VALUE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(nuevo)
                        .add(buscar)
                        .add(reportes)
                        .add(abiertos)
                        .add(salir))
                    .add(71, 71, 71))
        );
        
        layout.linkSize(new java.awt.Component[] {abiertos, buscar, nuevo, reportes, salir}, org.jdesktop.layout.GroupLayout.VERTICAL);
        this.add(middlePanel);			
	}
	
	private void createListeners(){
		abiertos.addActionListener(new CerrarPresupuestosActionListener());
		nuevo.addActionListener(new NuevoPptoActionListener());
		buscar.addActionListener(new BuscarPptoActionListener());
		salir.addActionListener(new SalirActionListener());
		reportes.addActionListener(new ReportesActionListener());
		vencerDetallesUC.addActionListener(new VerDetalles(TYPE_A_VENCER_UC));
		hoyDetallesUC.addActionListener(new VerDetalles(TYPE_DE_HOY_UC));
		pendientesDetallesUC.addActionListener(new VerDetalles(TYPE_PENDIENTES_UC));
		confirmadosDetallesUC.addActionListener(new VerDetalles(TYPE_CONFIRMADOS_UC));
		vencerDetalles.addActionListener(new VerDetalles(TYPE_A_VENCER_USER));
		hoyDetalles.addActionListener(new VerDetalles(TYPE_DE_HOY_USER));
		pendientesDetalles.addActionListener(new VerDetalles(TYPE_PENDIENTES_USER));
		confirmadosDetalles.addActionListener(new VerDetalles(TYPE_CONFIRMADOS_USER));
	}
	
	public void completePantalla(){
		long tim = System.currentTimeMillis();
		unidad.setText(getComercialUnit());
		user.setText(getNombreUsuario());
		nroObjetivo.setText(getObjetivo());
		nombreSucursal.setText(getSucursal());
		
		uc.setText(unidad.getText());	
		mes.setText(getMes(getCurrentMonth()));
		userName.setText(user.getText());
		mesUser.setText(mes.getText());
		
		/*PresupuestosManager manager = PresupuestosManager.instance();

		valorPendientesUC.setText(getAmountPendUC(manager));

		valorVencerUC.setText(getAmountToExpireUC(manager));	//2seg	
		if(cantVencerUC.getText().equals("0")){
			valorVencerUC.setForeground(Color.BLACK);
		}

		System.out.println("tiempo de carga: "+((System.currentTimeMillis()-tim)/1000));
		valorHoyUC.setText(getAmountTodayUC(manager));			

		valorConfirmadosUC.setText(getAmountConfUC(manager));	

		valorPendientesUser.setText(getAmountPend(manager));	 

		valorVencerUser.setText(getAmountToExpire(manager));
		if(cantVencerUser.getText().equals("0")){
			valorVencerUser.setForeground(Color.BLACK);
		}

		valorHoyUser.setText(getAmountToday(manager));

		valorConfirmadosUser.setText(getAmountConf(manager));	*/
		
	}
	
	private JMenu[] createMenu(){
		JMenu jMenuArchivo = new javax.swing.JMenu();
		JMenu jMenuEditar = new javax.swing.JMenu();
		JMenu jMenuAyuda = new javax.swing.JMenu();
		JMenuItem jMenuItemCerrarApp = new javax.swing.JMenuItem();
		JMenuItem jMenuItemNuevoPpto = new javax.swing.JMenuItem();
		JMenuItem jMenuItemBuscarPpto = new javax.swing.JMenuItem();
		JMenuItem jMenuItemCerrarPpto = new javax.swing.JMenuItem();
		JMenuItem jMenuItemLogin = new javax.swing.JMenuItem();
		JMenuItem jMenuItemEstadisticas = new javax.swing.JMenuItem();
		JMenuItem jMenuItemAyuda = new javax.swing.JMenuItem();
		JMenuItem jMenuItemRecuperar = new javax.swing.JMenuItem();

		jMenuArchivo.setMnemonic('A');
        jMenuArchivo.setText("Archivo");
        jMenuArchivo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenuArchivo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        
        jMenuItemRecuperar.setMnemonic('R');
        jMenuItemRecuperar.setText("Recuperar");
        jMenuItemRecuperar.addActionListener(new RecoverActionListener());
        jMenuArchivo.add(jMenuItemRecuperar);
		
        jMenuEditar.setMnemonic('E');
        jMenuEditar.setText("Herramientas");
        jMenuEditar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenuEditar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenuEditar.setVerticalAlignment(javax.swing.SwingConstants.TOP);       
        
        jMenuAyuda.setMnemonic('H');
        jMenuAyuda.setText("Ayuda");
        jMenuAyuda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenuAyuda.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        
        jMenuItemCerrarApp.setMnemonic('S');
        jMenuItemCerrarApp.setText("Salir de CRM");
        jMenuItemCerrarApp.addActionListener(new SalirActionListener());
        jMenuArchivo.add(jMenuItemCerrarApp);
        
        jMenuItemNuevoPpto.setMnemonic('N');
        jMenuItemNuevoPpto.setText("Nuevo Presupuesto");
        jMenuItemNuevoPpto.addActionListener(new NuevoPptoActionListener());
        jMenuEditar.add(jMenuItemNuevoPpto); 
        
        jMenuItemBuscarPpto.setMnemonic('B');
        jMenuItemBuscarPpto.setText("Buscar Presupuesto");
        jMenuItemBuscarPpto.addActionListener(new BuscarPptoActionListener());
        jMenuEditar.add(jMenuItemBuscarPpto);
        
        jMenuItemCerrarPpto.setMnemonic('C');
        jMenuItemCerrarPpto.setText("Cerrar Presupuesto");
        jMenuItemCerrarPpto.addActionListener(new CerrarPresupuestosActionListener());
        jMenuEditar.add(jMenuItemCerrarPpto); 
        
        jMenuEditar.addSeparator();
        
        jMenuItemLogin.setMnemonic('L');
        jMenuItemLogin.setText("Cambiar clave de login");
        jMenuItemLogin.addActionListener(new CambiarClaveActionListener());
        jMenuEditar.add(jMenuItemLogin);      
        
        jMenuEditar.addSeparator();
        
        jMenuItemEstadisticas.setMnemonic('D');
        jMenuItemEstadisticas.setText("Ver estadisticas");
        jMenuItemEstadisticas.addActionListener(new EstadisticasActionListener());
        jMenuEditar.add(jMenuItemEstadisticas);
        
        jMenuItemAyuda.setMnemonic('C');
        jMenuItemAyuda.setText("Ver Ayuda");
        jMenuItemAyuda.addActionListener(new VerAyudaActionListener());
        jMenuAyuda.add(jMenuItemAyuda); 
        
        JMenu[] list = new JMenu[3];
        list[0] = jMenuArchivo;
        list[1] = jMenuEditar;
        list[2] = jMenuAyuda;
        return list;
	}
	
	private String getMes(String mes){
		int nro = Integer.valueOf(mes);  	
		switch (nro) {
		case 1:
			return "Enero";
		case 2:
			return "Febrero";
		case 3:
			return "Marzo";
		case 4:
			return "Abril";
		case 5:
			return "Mayo";
		case 6:
			return "Junio";
		case 7:
			return "Julio";
		case 8:
			return "Agosto";
		case 9:
			return "Septiembre";
		case 10:
			return "Octubre";
		case 11:
			return "Noviembre";
		case 12:
			return "Diciembre";
		default:
			return "mes";		
		}
	}
	
	protected String getCurrentMonth() {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());

		String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		sdf.setTimeZone(TimeZone.getDefault());

		return (sdf.format(cal.getTime())).substring(3, 5);
	}
	
	private void detallesActionPerformed(ActionEvent arg0, final int type) {
		try{
			DetallesPptos buscador = new DetallesPptos(Main.getVentana(), type);	
			buscador.setUser(getUsuario());
			buscador.init();	
			buscador.showComponent();
			PresupuestosManager manager = PresupuestosManager.instance();
			switch(type){
			case 0: buscador.setTableDetallesPptos2(manager.buscarConfirmadosPorUC(getCodigoVendedor()));
			break;
			case 1: buscador.setTableDetallesPptos2(manager.buscarConfirmadosPorVendedor(getCodigoVendedor()));
			break;
			case 2: buscador.setTableDetallesPptos2(manager.buscarPendientesPorUC(getCodigoVendedor()));
			break;
			case 3: buscador.setTableDetallesPptos2(manager.buscarPendientesPorVendedor(getCodigoVendedor()));
			break;
			case 4: buscador.setTableDetallesPptos2(manager.buscarDeHoyPorUC(getCodigoVendedor()));
			break;
			case 5: buscador.setTableDetallesPptos2(manager.buscarDeHoyPorVendedor(getCodigoVendedor()));
			break;
			case 6: buscador.setTableDetallesPptos2(manager.buscarAVencerPorUC(getCodigoVendedor()));
			break;
			case 7: buscador.setTableDetallesPptos2(manager.buscarAVencerPorVendedor(getCodigoVendedor()));
			break;
			}
			
			buscador.setVisible(true);			
			
		}	
		catch(RemoteException e){
			Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
		}
	}
	
	public String getSucursal() {
		return SucursalManager.instance().getSucursalNameByCodigo(
				getCodigoSucursal());
	}

	private String getAmountPendUC(PresupuestosManager manager) {
		float totalPendientesUC = 0;
		try {
			Object[] listaFacturaciones = manager.buscarPendientesPorUC(getCodigoVendedor());
			
			if (listaFacturaciones != null){
				for(int i=0; i<listaFacturaciones.length; i++){
					Object[] nrosPpto = (Object[])listaFacturaciones[i];
					totalPendientesUC += ((Double)nrosPpto[5]).doubleValue();//manager.getFacturacionByPPto(((Long)nrosPpto[0]).longValue());
				}
			}
			cantPendientesUC.setText(new Integer(listaFacturaciones.length).toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return getTotalFormateado(totalPendientesUC);	
	}
	
	private String getAmountToExpireUC(PresupuestosManager manager) {
		float facturacionAVencerPorUC = 0;
		try {
			Object[] listaFacturaciones = manager.buscarAVencerPorUC(getCodigoVendedor());
			
			if (listaFacturaciones != null){
				for(int i=0; i<listaFacturaciones.length; i++){
					Object[] nrosPpto = (Object[])listaFacturaciones[i];
					facturacionAVencerPorUC += ((Double)nrosPpto[5]).doubleValue();//manager.getFacturacionByPPto(((Long)nrosPpto[0]).longValue());
				}
			}
			cantVencerUC.setText(new Integer(listaFacturaciones.length).toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return getTotalFormateado(facturacionAVencerPorUC);
	}

	private String getAmountTodayUC(PresupuestosManager manager) {
		double facturacionDeHoyPorUC = 0;
		try {
			Object[] listaFacturaciones = manager.buscarDeHoyPorUC(getCodigoVendedor());
			
			if (listaFacturaciones != null){
				for(int i=0; i<listaFacturaciones.length; i++){
					Object[] nrosPpto = (Object[])listaFacturaciones[i];
					facturacionDeHoyPorUC += ((Double)nrosPpto[5]).doubleValue();//manager.getFacturacionByPPto(((Long)nrosPpto[0]).longValue());
				}
			}
			cantHoyUC.setText(new Integer(listaFacturaciones.length).toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return getTotalFormateado(facturacionDeHoyPorUC);
	}

	private String getAmountConfUC(PresupuestosManager manager) {
		float facturacionConfirmadosUC = 0;
		try {
			Object[] listaFacturaciones = manager.buscarConfirmadosPorUC(getCodigoVendedor());
			
			if (listaFacturaciones != null){
				for(int i=0; i<listaFacturaciones.length; i++){
					Object[] nrosPpto = (Object[])listaFacturaciones[i];
					facturacionConfirmadosUC += ((Double)nrosPpto[5]).doubleValue();//manager.getFacturacionByPPto(((Long)nrosPpto[0]).longValue());
				}
			}
			cantConfirmadosUC.setText(new Integer(listaFacturaciones.length).toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return getTotalFormateado(facturacionConfirmadosUC);
	}

	private String getAmountObjetive() {
		// TODO devolver la cantidad de dinero del objetivo del mes real segun
		// base
		return getObjetivo();
	}

	private String getAmountPend(PresupuestosManager manager) {
		float facturacionPendientesVendedor = 0;
		try {
			Object[] listaFacturaciones = manager.buscarPendientesPorVendedor(getCodigoVendedor());
			
			if (listaFacturaciones != null){
				for(int i=0; i<listaFacturaciones.length; i++){
					Object[] nrosPpto = (Object[])listaFacturaciones[i];
					facturacionPendientesVendedor += ((Double)nrosPpto[5]).doubleValue();//manager.getFacturacionByPPto(((Long)nrosPpto[0]).longValue());
				}
			}
			cantPendientesUser.setText(new Integer(listaFacturaciones.length).toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return getTotalFormateado(facturacionPendientesVendedor);
	}

	private String getAmountToExpire(PresupuestosManager manager) {
		float facturacionAVencerVendedor = 0;
		try {
			Object[] listaFacturaciones = manager.buscarAVencerPorVendedor(getCodigoVendedor());
			
			if (listaFacturaciones != null){
				for(int i=0; i<listaFacturaciones.length; i++){
					Object[] nrosPpto = (Object[])listaFacturaciones[i];
					facturacionAVencerVendedor += ((Double)nrosPpto[5]).doubleValue();//manager.getFacturacionByPPto(((Long)nrosPpto[0]).longValue());
				}
			}
			cantVencerUser.setText(new Integer(listaFacturaciones.length).toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return getTotalFormateado(facturacionAVencerVendedor);
	}

	private String getAmountToday(PresupuestosManager manager) {
		float facturacionDeHoyVendedor = 0;
		try {
			Object[] listaFacturaciones = manager.buscarDeHoyPorVendedor(getCodigoVendedor());
			
			if (listaFacturaciones != null){
				for(int i=0; i<listaFacturaciones.length; i++){
					Object[] nrosPpto = (Object[])listaFacturaciones[i];
					facturacionDeHoyVendedor += ((Double)nrosPpto[5]).doubleValue();//manager.getFacturacionByPPto(((Long)nrosPpto[0]).longValue());
				}
			}
			cantHoyUser.setText(new Integer(listaFacturaciones.length).toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return getTotalFormateado(facturacionDeHoyVendedor);
	}

	private String getAmountConf(PresupuestosManager manager) {
		float facturacionConfirmadosVendedor = 0;
		try {
			Object[] listaFacturaciones = manager.buscarConfirmadosPorVendedor(getCodigoVendedor());
			
			if (listaFacturaciones != null){
				for(int i=0; i<listaFacturaciones.length; i++){
					Object[] nrosPpto = (Object[])listaFacturaciones[i];
					facturacionConfirmadosVendedor += ((Double)nrosPpto[5]).doubleValue();//manager.getFacturacionByPPto(((Long)nrosPpto[0]).longValue());
				}
			}
			cantConfirmadosUser.setText(new Integer(listaFacturaciones.length).toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
				
		return getTotalFormateado(facturacionConfirmadosVendedor);
	}

	private String getCountPendUC(PresupuestosManager manager) {
		int cantPendientesUC = 0;
		try {
			Object[] o = manager.buscarPendientesPorUC(getCodigoVendedor());
			cantPendientesUC = o.length;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return new Integer(cantPendientesUC).toString();
	}

	private String getCountToExpireUC(PresupuestosManager manager) {
		int cantidadAVencerUC = 0;
		try {
			Object[] o = manager.buscarAVencerPorUC(getCodigoVendedor());
			cantidadAVencerUC = o.length;
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return new Integer(cantidadAVencerUC).toString();
	}

	private String getCountTodayUC(PresupuestosManager manager) {
		int cantDeHoyUC = 0;
		try {
			Object[] o = manager.buscarDeHoyPorUC(getCodigoVendedor());
			cantDeHoyUC = o.length;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return new Integer(cantDeHoyUC).toString();
	}

	private String getCountConfirmeeUC(PresupuestosManager manager) {
		int cantConfirmadosUC = 0;
		try {
			Object[] o = manager.buscarConfirmadosPorUC(getCodigoVendedor());
			cantConfirmadosUC = o.length;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return new Integer(cantConfirmadosUC).toString();
	}

	private String getCountPend(PresupuestosManager manager) {
		int cantidadPendientes = 0;
		try {
			Object[] o = manager.buscarPendientesPorVendedor(getCodigoVendedor());
			cantidadPendientes = o.length;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return new Integer(cantidadPendientes).toString();
	}

	private String getCountToExpire(PresupuestosManager manager) {
		int cantidadAVencer = 0;
		try {
			Object[] o = manager.buscarAVencerPorVendedor(getCodigoVendedor());
			cantidadAVencer = o.length;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return new Integer(cantidadAVencer).toString();
	}

	private String getCountToday(PresupuestosManager manager) {
		int cantidadDeHoyPorVendedor = 0;
		try {
			Object[] o = manager.buscarDeHoyPorVendedor(getCodigoVendedor());
			cantidadDeHoyPorVendedor = o.length;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return new Integer(cantidadDeHoyPorVendedor).toString();
	}

	private String getCountConfirmee(PresupuestosManager manager) {
		int cantidadConfirmados = 0;
		try {
			Object[] o = manager.buscarConfirmadosPorVendedor(getCodigoVendedor());
			cantidadConfirmados = o.length;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return new Integer(cantidadConfirmados).toString();
	}
	
	private String getComercialUnit() {
		boolean isCold = UserRolesUtil.isCold(getUsuario());
		boolean isAdmin = UserRolesUtil.isAdmin(getUsuario());
		if(!isCold && !isAdmin){
			UnidadComercial uc = UnidadComercialManager.instance()
				.getUCDataByCodigoUsuario(getCodigoUsuario());

			if (uc != null) {
				setCodigoSucursal(uc.getCodigoSucursal());
				setCodigoUC(uc.getCodigo());
				double obj = Double.valueOf(uc.getObjetivoGlobal());
				setObjetivo(getTotalFormateado(obj));
				codigoUC = uc.getCodigo();
				return uc.getDescripcion();
			} else {
				//Util.errMsg(Main.getVentana(),"Error al cargar datos de unidad comercial del usuario",null);
				//recargarLogin();
			}
		} 
		return null;
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
	
	private String getMIp(){
		/*InetAddress ip;
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
		}*/
		return null;
	}
	
	private String getMac(){
		/*InetAddress ip;
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
		}*/
		return null;
	}
	
	private class NuevoPptoActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			ProgressDialogUtil.setType(ProgressDialogUtil.NEW_PPTO_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {					
					MainPanelComercial gp = PantallaBienvenidaVerdadera.getMainPanel2();
					if(gp.getPresupuesto() != null && gp.getPresupuesto().getNumeroDePresupuesto() != 0){
						try{							
							PresupuestosManager.instance().modificarActivo(gp.getPresupuesto().getNumeroDePresupuesto(), "N",Integer.parseInt(getCodigoUsuario()),getMIp(),getMac());
						}catch(RemoteException e){
							Util.errMsg(Main.getVentana(), "El presupuesto quedará abierto por error de aplicación", e);
						}
					}
					gp.setPresupuesto(Presupuesto.createDefault());
					cambiarPantallaA(gp);
					ProgressDialogUtil.closeProcessDialog();
				}
			}).start(); 			
		}
		
	}
	
	private BuscadorPptos buscaPpto;
	private BuscadorReportesComerciales2 buscaReporte;
	
	private class BuscarPptoActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(buscaPpto == null){
				buscaPpto = new BuscadorPptos(Main.getVentana());
				buscaPpto.setUser(getUsuario());
				buscaPpto.initComponent();
				buscaPpto.showComponent();
			}
			
			buscaPpto.setVisible(true);
		}
		
	}

	private class CerrarPresupuestosActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			ActivadorPpto a = new ActivadorPpto(Main.getVentana());
			a.initComponents();
			a.showComponents();
			a.setCodVendedor(Long.parseLong(getCodigoVendedor()));
			a.setVisible(true);
			a = null;
		}
		
	}
	
	private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(Main.getVentana(),"Seguro que desea salir de la aplicación","Salir"))
				Main.closeWindow();
		}
		
	}
	
	private class CambiarClaveActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			PantallaCambioPassword a = new PantallaCambioPassword(Main.getVentana(),getUsuario());
			a.init();
			a.setVisible(true);	
			a = null;
		}
	}
	
	private class EstadisticasActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			PresupuestosManager manager = PresupuestosManager.instance();

			valorPendientesUC.setText(getAmountPendUC(manager));

			valorVencerUC.setText(getAmountToExpireUC(manager));	//2seg	
			if(cantVencerUC.getText().equals("0")){
				valorVencerUC.setForeground(Color.BLACK);
			}
			
			valorHoyUC.setText(getAmountTodayUC(manager));			

			valorConfirmadosUC.setText(getAmountConfUC(manager));	

			valorPendientesUser.setText(getAmountPend(manager));	 

			valorVencerUser.setText(getAmountToExpire(manager));
			if(cantVencerUser.getText().equals("0")){
				valorVencerUser.setForeground(Color.BLACK);
			}

			valorHoyUser.setText(getAmountToday(manager));

			valorConfirmadosUser.setText(getAmountConf(manager));
		}
		
	}
	
	private class VerAyudaActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			MessageUtil.showMessage(Main.getVentana(),"Proximamente...");	
			
		}
		
	}
	
	private class RecoverActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(Main.getVentana(),"Seguro que desea recuperar el ultimo presupuesto utilizado?","Recuperar Presupuesto")){
				ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_NEW_ENTITY);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				new Thread(new Runnable() {	
					public void run() {
				SerializerManager manager = new SerializerManager();

				Presupuesto p = manager.openXML();
				if(p!= null){
					MainPanelComercial gp = PantallaBienvenidaVerdadera.getMainPanel2();				
					gp.setPresupuesto(p);
					cambiarPantallaA(gp);
					
				}
				ProgressDialogUtil.closeProcessDialog();
					}
				}).start();	

			}
		}
	}
	
	private class ReportesActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {

			/*SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					Main.getVentana().getGlassPane().start();
					Thread performer = new Thread(new Runnable(){
						public void run(){
							if(buscaReporte == null){
								buscaReporte = new BuscadorReportesComerciales2(Main.getVentana(), Long.parseLong(getCodigoVendedor()), Long.parseLong(getCodigoUC()));
								buscaReporte.init();
							}
							buscaReporte.setVisible(true);
							Main.getVentana().getGlassPane().stop();
						}
					},"Reportes");
					performer.start();
				}
			});*/
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					Main.getVentana().getGlassPane().start();
					Thread performer = new Thread(new Runnable(){
						public void run(){
							reportes.setEnabled(true);
							BuscadorReportesComercial busc = new BuscadorReportesComercial(Main.getVentana(), getCodigoVendedor());
							busc.init();
							busc.setVisible(true);
							Main.getVentana().getGlassPane().stop();
						}
					},"Performer");
					performer.start();
				}
			});	
		}		
	}
	
	private class VerDetalles implements ActionListener{
		
		int type;
		
		public VerDetalles(int type){
			this.type = type;
		}
		
		public void actionPerformed(ActionEvent evt) {
			detallesActionPerformed(evt, type);			
		}
		
	}
	
	

}
