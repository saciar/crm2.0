package crm.gui.pantalla;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import crm.gui.Main;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.solapa.MainPanelComercial;
import crm.libraries.util.MessageUtil;

public class PantallaBienvenidaFacturacion extends PantallaBienvenidaVerdadera{
	
    private GradientButton buscar;
    private GradientButton marcar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private GradientButton salir;
    private GradientButton reportes;
    
    private PanelImagen middlePanel; 
	
    public PantallaBienvenidaFacturacion() {
    	super();
		setMainPanel2(new MainPanelComercial());		
		getMainPanel2().init();
	}
    
	public void initComponents() {
		middlePanel = null;
		try{
			middlePanel = new PanelImagen("Abstract_4220.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			middlePanel = new PanelImagen();
		}
		
        jLabel1 = new javax.swing.JLabel();
        buscar = new GradientButton("", Color.blue);
        salir = new GradientButton("", Color.blue);
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        marcar = new GradientButton("", Color.blue);
        reportes = new GradientButton("", Color.blue);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20));
        jLabel1.setText("Bienvenido/a");       
        
        buscar.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));
        buscar.setText("Buscar Presupuesto");
        buscar.setDoubleBuffered(true);
        buscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buscar.addActionListener(new BuscarPptoActionListener());

        salir.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        salir.setText("Salir");
        salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        salir.addActionListener(new SalirActionListener());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20));
        jLabel2.setForeground(new java.awt.Color(163, 184, 204));
        jLabel2.setText(getUsuario().getApellidoYNombre());

        marcar.setIcon(new javax.swing.ImageIcon(getUrlImagen("report_go.png")));
        marcar.setText("Marcar como facturado");
        marcar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        marcar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        marcar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcarrActionPerformed(evt);
            }
        });
        
        reportes.setIcon(new javax.swing.ImageIcon(getUrlImagen("layout_sidebar.png")));
        reportes.setText("Reportes");
        reportes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reportes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportesActionPerformed(evt);
            }
        });
        
	}
    public void createLayout() {	
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(middlePanel);
        middlePanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jLabel1)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel2))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE))
                        .add(layout.createSequentialGroup()
                            .add(301, 301, 301)
                            .add(marcar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(buscar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(reportes)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(salir)))
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
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 213, Short.MAX_VALUE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(buscar)
                        .add(marcar)
                        .add(reportes)
                        .add(salir))
                    .add(48, 48, 48))
            );

        layout.linkSize(new java.awt.Component[] {buscar, salir}, org.jdesktop.layout.GroupLayout.VERTICAL);
        
        this.add(middlePanel);
    }
    
    public void createMenuBAr(){
		Main.getVentana().setMyMenu(createMenu());
	}
    
    private JMenu[] createMenu(){
		JMenu jMenuArchivo = new javax.swing.JMenu();
		JMenu jMenuEditar = new javax.swing.JMenu();
		JMenu jMenuAyuda = new javax.swing.JMenu();
		JMenuItem jMenuItemCerrarApp = new javax.swing.JMenuItem();
		JMenuItem jMenuItemBuscarPpto = new javax.swing.JMenuItem();
		JMenuItem jMenuItemLogin = new javax.swing.JMenuItem();
		JMenuItem jMenuItemAyuda = new javax.swing.JMenuItem();

		jMenuArchivo.setMnemonic('A');
        jMenuArchivo.setText("Archivo");
        jMenuArchivo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenuArchivo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		
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
        
        jMenuItemBuscarPpto.setMnemonic('B');
        jMenuItemBuscarPpto.setText("Buscar Presupuesto");
       // jMenuItemBuscarPpto.addActionListener(new BuscarPptoActionListener());
        jMenuEditar.add(jMenuItemBuscarPpto);
        
        jMenuEditar.addSeparator();
        
        jMenuItemLogin.setMnemonic('L');
        jMenuItemLogin.setText("Cambiar clave de login");
        jMenuItemLogin.addActionListener(new CambiarClaveActionListener());
        jMenuEditar.add(jMenuItemLogin);      
        
        jMenuItemAyuda.setMnemonic('C');
        jMenuItemAyuda.setText("Ver Ayuda");
        //jMenuItemAyuda.addActionListener(new VerAyudaActionListener());
        jMenuAyuda.add(jMenuItemAyuda); 
        
        JMenu[] list = new JMenu[3];
        list[0] = jMenuArchivo;
        list[1] = jMenuEditar;
        list[2] = jMenuAyuda;
        return list;
	}
    
    private BuscadorPptos buscaPpto;
    
    private class BuscarPptoActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(buscaPpto == null){
				buscaPpto = new BuscadorPptos(Main.getVentana());
				buscaPpto.setUser(getUsuario());
				buscaPpto.initComponent();
				buscaPpto.showComponent();
			}
			buscaPpto.setVisible(true);
		}
		
		
    } 
    
    private class SalirActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (MessageUtil.showYesNoMessage(Main.getVentana(),"Seguro que desea salir de la aplicaci�n","Salir"))
				Main.closeWindow();		
		}
		
    }  
    
    private PantallaFacturacion pantFacturacion;
    
    private void marcarrActionPerformed(java.awt.event.ActionEvent evt) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (pantFacturacion == null) {
                    pantFacturacion = new PantallaFacturacion(Main.getVentana());
                    pantFacturacion.setUser(getUsuario());
                    pantFacturacion.initComponent();
                    pantFacturacion.initLayout();
                    pantFacturacion.loadTableRows();
                }
                pantFacturacion.setVisible(true);
            }
        });
    }
    
    private void reportesActionPerformed (java.awt.event.ActionEvent evt) {
    	BuscadorReportesFacturacion2 buscador = new BuscadorReportesFacturacion2(Main.getVentana());
    	buscador.setUser(getUsuario());
		buscador.init();
		buscador.setVisible(true);
    }
    
    private class CambiarClaveActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			PantallaCambioPassword a = new PantallaCambioPassword(Main.getVentana(),getUsuario());
			a.init();
			a.setVisible(true);	
			a = null;
		}
	}
}
