package crm.gui.pantalla;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import crm.client.util.ProgressDialogUtil;
import crm.gui.Main;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.reportes.BuscadorReportes;
import crm.gui.pantalla.solapa.ABM;
import crm.gui.pantalla.solapa.MainPanelComercial;
import crm.gui.pantallas2019.gerencia.BuscadorReportesGerencia;
import crm.gui.pantallas2019.gerencia.PortadaReportesGerencia;
import crm.libraries.util.MessageUtil;

public class PantallaBienvenidaGerencia extends PantallaBienvenidaVerdadera{
	
//	 Variables declaration - do not modify
	private javax.swing.JButton abms;
	private javax.swing.JButton buscar;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JButton reportes;
	private javax.swing.JButton salir;
	private javax.swing.JLabel user;
	
	private PanelImagen middlePanel;
	
	public PantallaBienvenidaGerencia(){
		super();
		setMainPanel2(new MainPanelComercial());		
		getMainPanel2().init();
	}
	
	public void initComponents() {
		
		middlePanel = null;
		try{
			middlePanel = new PanelImagen("fondo_bvn_gerencia.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			middlePanel = new PanelImagen();
		}
		
		jLabel1 = new javax.swing.JLabel();
		user = new javax.swing.JLabel();
		abms = new GradientButton("", Color.blue);
		reportes = new GradientButton("", Color.blue);
		salir = new GradientButton("", Color.blue);
		jSeparator1 = new javax.swing.JSeparator();
		buscar = new GradientButton("", Color.blue);
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20));
		jLabel1.setText("Bienvenido/a");
		
		user.setFont(new java.awt.Font("Tahoma", 1, 20));
		//user.setForeground(new java.awt.Color(163, 184, 204));
		user.setForeground(Color.WHITE);
		abms.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_cascade.png")));
		abms.setText("Cargar ABM's");
		abms.setDoubleBuffered(true);
		abms.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		abms.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		abms.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				abmsActionPerformed(evt);
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
		
		salir.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
		salir.setText("Salir");
		salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		salir.addActionListener(new SalirActionListener());
		
		buscar.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));
		buscar.setText("Buscar Presupuestos");
		buscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		buscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		buscar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buscarActionPerformed(evt);
			}
		});
		completePantalla();
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
                            .add(user))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE))//1088
                        .add(layout.createSequentialGroup()
                            .add(301, 301, 301)
                            .add(abms)
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
                        .add(user))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 213, Short.MAX_VALUE)//577
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(buscar)
                        .add(abms)
                        .add(reportes)
                        .add(salir))
                    .add(48, 48, 48))
            );

        layout.linkSize(new java.awt.Component[] {buscar, salir, abms, reportes}, org.jdesktop.layout.GroupLayout.VERTICAL);
        
        this.add(middlePanel);
		
	}// </editor-fold>
    
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
    
	private void completePantalla(){
		user.setText(getNombreUsuario());
	}
	
	private class SalirActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (MessageUtil.showYesNoMessage(Main.getVentana(),"Seguro que desea salir de la aplicación","Salir"))
				Main.closeWindow();		
		}
		
    } 

	
	private void reportesActionPerformed(ActionEvent evt) {
		reportes.setEnabled(false);
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				Main.getVentana().getGlassPane().start();
				Thread performer = new Thread(new Runnable(){
					public void run(){
						reportes.setEnabled(true);
						/*BuscadorReportes busc = new BuscadorReportes(Main.getVentana());
						busc.init();
						busc.setVisible(true);*/
						PortadaReportesGerencia dialog = new PortadaReportesGerencia(Main.getVentana());
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
						Main.getVentana().getGlassPane().stop();
					}
				},"Performer");
				performer.start();
			}
		});	
	}
	
	private ABM pantallaABM;
	
	private void abmsActionPerformed(ActionEvent evt) {

		/*ProgressDialogUtil.setType(ProgressDialogUtil.NEW_PPTO_TYPE);
		ProgressDialogUtil.launchProcessDialog(Main.getVentana());
		new Thread(new Runnable() {	
			public void run() {				
				if(pantallaABM == null){
					pantallaABM = new ABM(Main.getVentana());	
					pantallaABM.setUser(getUsuario());
					pantallaABM.initComponent();
					pantallaABM.initLayout();	
		    	}
				pantallaABM.setVisible(true);
				ProgressDialogUtil.closeProcessDialog();
			}		
		}).start();*/
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				Main.getVentana().getGlassPane().start();
				Thread performer = new Thread(new Runnable(){
					public void run(){
						if(pantallaABM == null){
							pantallaABM = new ABM(Main.getVentana());	
							pantallaABM.setUser(getUsuario());
							pantallaABM.initComponent();
							pantallaABM.initLayout();	
				    	}
						//ProgressDialogUtil.closeProcessDialog();
						pantallaABM.setVisible(true);
						Main.getVentana().getGlassPane().stop();
					}
				},"Performer");
				performer.start();
			}
		});		
	}

	private BuscadorPptos buscaPpto;
	private BuscadorReportesComerciales2 buscaReporte;
	
	private void buscarActionPerformed(ActionEvent arg0) {
		if(buscaPpto == null){
			buscaPpto = new BuscadorPptos(Main.getVentana());
			buscaPpto.setUser(getUsuario());
			buscaPpto.initComponent();
			buscaPpto.showComponent();
		}
		
		buscaPpto.setVisible(true);
	}
	
	/*private void buscarActionPerformed(ActionEvent evt) {
			BuscadorPptos buscaPpto = new BuscadorPptos(Main.getVentana());
			buscaPpto.init();
			buscaPpto.setVisible(true);
			
			Presupuesto presupuestoElegido = buscaPpto.getPresupuestoElegido();
			//a = null;
			
			if (presupuestoElegido != null && presupuestoElegido.getActivo().equals("N")) {
				try{						
					loadPresupuesto(presupuestoElegido);
					PresupuestosManager.instance().modificarActivo(presupuestoElegido.getNumeroDePresupuesto(), "S");
											
				}
				catch(RemoteException e){
					Util.errMsg(Main.getVentana(), "Error al modificar datos externos", e);
				}
				
			}
			else if(presupuestoElegido != null && presupuestoElegido.getActivo().equals("S")){
				Util.alertMsg(Main.getVentana(), "El presupuesto esta siendo usado por otro usuario.\nComuniquese con "+presupuestoElegido.getVendedor().getVendedor().getApellidoYNombre());
			}
		
	}*/
	
	private class CambiarClaveActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			PantallaCambioPassword a = new PantallaCambioPassword(Main.getVentana(),getUsuario());
			a.init();
			a.setVisible(true);	
			a = null;
		}
	}
}
