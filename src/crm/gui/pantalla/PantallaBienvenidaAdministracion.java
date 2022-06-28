package crm.gui.pantalla;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.text.MessageFormat;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.JTable.PrintMode;

import crm.client.managers.AdministradorManager;
import crm.client.managers.AgendaTareasCobranzasManager;
import crm.client.managers.PresupuestosManager;
import crm.client.managers.UnidadAdministradorManager;
import crm.client.pantalla.cobranzas.PantallaAgendaCobranzas;
import crm.client.util.DateConverter;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.GradientButton;
import crm.gui.components.JXDatePicker;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.solapa.MainPanelComercial;
import crm.gui.tablerenderer.alertasCobranzas.AlertaCobranzaItem;
import crm.gui.tablerenderer.alertasCobranzas.AlertaCobranzaTableModel;
import crm.gui.tablerenderer.alertasCobranzas.AlertaCobranzaTableRender;
import crm.gui.tablerenderer.cobranzas.CobranzasItem;
import crm.gui.tablerenderer.cobranzas.CobranzasTableModel;
import crm.libraries.abm.entities.AgendaTareasCobranzas;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.util.MessageUtil;

public class PantallaBienvenidaAdministracion extends PantallaBienvenidaVerdadera{
	/*private javax.swing.JButton buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton marcarFacturado;
    private javax.swing.JButton marcarCobrado;
    private javax.swing.JButton reportes;
    private javax.swing.JButton salir;*/
	private GradientButton buscar;
    private GradientButton marcarCobrado;
    private GradientButton marcarFacturado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private GradientButton salir;
    private GradientButton reportes;
    private GradientButton agendas;
    private AlertaCobranzaTableRender jTable1;
    private JXDatePicker fechaABuscar;
    private AgendaTareasCobranzasManager manager;
    
    private JPopupMenu popup;
    private PanelImagen middlePanel; 
    
    public PantallaBienvenidaAdministracion(){	
    	super();
		manager = AgendaTareasCobranzasManager.instance();
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
        marcarFacturado = new GradientButton("", Color.blue);
        marcarCobrado = new GradientButton("", Color.blue);
        reportes = new GradientButton("", Color.blue);
        agendas = new GradientButton("", Color.blue);
        jLabel3 = new javax.swing.JLabel();
        jTable1 = new AlertaCobranzaTableRender();
        fechaABuscar = new JXDatePicker();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20));
        jLabel1.setText("Bienvenido/a");       
        
        buscar.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));
        buscar.setText("Buscar Presupuesto");
        buscar.setDoubleBuffered(true);
        buscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        salir.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        salir.setText("Salir");
        salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        salir.addActionListener(new SalirActionListener());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20));
        jLabel2.setForeground(new java.awt.Color(163, 184, 204));
        jLabel2.setText(getUsuario().getApellidoYNombre());

        marcarFacturado.setIcon(new javax.swing.ImageIcon(getUrlImagen("tick.png")));
        marcarFacturado.setText("Marcar como facturado");
        marcarFacturado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        marcarFacturado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        marcarFacturado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcarFacturadoActionPerformed(evt);
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
        
        agendas.setIcon(new javax.swing.ImageIcon(getUrlImagen("report_go.png")));
        agendas.setText("Ver Agendas Cobrados");
        agendas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agendas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	agendasCobradasActionPerformed(evt);
            }
        });
        
        marcarCobrado.setIcon(new javax.swing.ImageIcon(getUrlImagen("tick.png")));
        marcarCobrado.setText("Marcar como cobrado");
        marcarCobrado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        marcarCobrado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        marcarCobrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	marcarCobradoActionPerformed(evt);
            }
        });      
        
        jLabel3.setText("Recordatorios del d\u00eda ");
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        
        fechaABuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActionPerformed(evt);
            }
        });
        
        popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Imprimir grilla");
		menuItem.setIcon(new javax.swing.ImageIcon(getUrlImagen("printer.png")));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
	    menuItem.addActionListener(new PopUpMenuActionListener());
	    popup.add(menuItem);
	    
	    //MouseListener popupListener = new PopupListener();
	    jTable1.getTable().addMouseListener(new TableActionListener());	
        
        fechaActionPerformed(null);
    }
    public void initLayout() {	
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
                            .addContainerGap()
                            .add(jLabel3)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(fechaABuscar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)))
                    .addContainerGap())
                .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                   // .addContainerGap(240, Short.MAX_VALUE)
                    .add(marcarFacturado)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(marcarCobrado)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(buscar)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(agendas)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(reportes)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(salir)
                    .add(212, 212, 212))
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
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(jLabel3)
                        .add(fechaABuscar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(buscar)
                            .add(marcarCobrado)
                            .add(marcarFacturado)
                            .add(reportes)
                            .add(salir))
                        .add(agendas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
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
    
    private boolean isPptoUnidadAdm(String nroppto){
    	boolean esta=false;
    	try{    		
    		PresupuestosManager manager = PresupuestosManager.instance();
    		String codAdmin = AdministradorManager.instance().getCodAdministradorByCodUsuario(getUsuario().getCodigo()); 
    		String codUnidad=null;
    		if(codAdmin != null)
    			codUnidad = UnidadAdministradorManager.instance().getCodigoUnidad(codAdmin);
    		if(codUnidad != null){
    			Object[] objects = manager.findFacturadosNoCobradosByUnidadAdm(codUnidad);				
    			for (int i = 0; i < objects.length; i++) {
    				Object[] data = (Object[]) objects[i];
    				if(nroppto.equals(data[0].toString())){
    					esta=true;
    					break;
    				}
    			}
    		}
    		return esta;
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		return esta;
    	}
    }
    
    public void loadData(String date){
		try {
			 
			AgendaTareasCobranzas[] agendas = manager.findAlertaToday(date);
			AlertaCobranzaTableModel model = new AlertaCobranzaTableModel();
			for (int i = 0; i < agendas.length; i++) {
				AlertaCobranzaItem item = new AlertaCobranzaItem();
			//	if(isPptoUnidadAdm(agendas[i].getNumeroPresupuesto())){
					item.setNumeroPresupuesto(Long.parseLong(agendas[i].getNumeroPresupuesto()));
					Object[] result = manager.getClienteEventoToAgenda(Long.parseLong(agendas[i].getNumeroPresupuesto()));
					Object[] data = (Object[])result[0];
					item.setCliente((String)data[0]);
					item.setEvento((String)data[1]);
					item.setAsunto(agendas[i].getAsunto());
					item.setCreado(agendas[i].getFechaIngreso());
					item.setTipoIcono(agendas[i].getAlerta());
					model.addRow(item);
				//}
				
			}
			jTable1.getTable().setModel(model);
			jTable1.refreshTable();
				
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    private BuscadorPptos buscaPpto;
    
    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	if(buscaPpto == null){
			buscaPpto = new BuscadorPptos(Main.getVentana());
			buscaPpto.setUser(getUsuario());
			buscaPpto.initComponent();
			buscaPpto.showComponent();
		}
		buscaPpto.setVisible(true);
    }        
    
    private PantallaFacturacion pantFacturacion;
    
    private void marcarFacturadoActionPerformed(java.awt.event.ActionEvent evt) {
    	if(pantFacturacion == null){
	    	pantFacturacion = new PantallaFacturacion(Main.getVentana());	
	    	pantFacturacion.setUser(getUsuario());
	    	pantFacturacion.initComponent();
	    	pantFacturacion.initLayout();	
			pantFacturacion.loadTableRows();
    	}
		pantFacturacion.setVisible(true);
    }
    
    private PantallaCobranzas pantallaCobranzas ;
    
    private void marcarCobradoActionPerformed(java.awt.event.ActionEvent evt) {                                      
    	if(pantallaCobranzas==null){
	    	pantallaCobranzas = new PantallaCobranzas(Main.getVentana());		
	    	pantallaCobranzas.setUser(getUsuario());
	    	pantallaCobranzas.initComponent();
	    	pantallaCobranzas.initLayout();	    	
			pantallaCobranzas.loadTableRows();	
    	}

		pantallaCobranzas.setVisible(true);
    }
    
    private void reportesActionPerformed (java.awt.event.ActionEvent evt) {
    	//reportes.setEnabled(false);
		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_NEW_ENTITY);
		ProgressDialogUtil.launchProcessDialog(Main.getVentana());		
		new Thread(new Runnable() {	
			public void run() {					
				/*BuscadorReportesGerencia buscador = new BuscadorReportesGerencia(Main.getVentana());
				buscador.init();
				buscador.setVisible(true);
				ProgressDialogUtil.closeProcessDialog();
				reportes.setEnabled(true);*/
				BuscadorReportesFacturacion2 buscador = new BuscadorReportesFacturacion2(Main.getVentana());
		    	buscador.setUser(getUsuario());
				buscador.init();
				buscador.setVisible(true);
				ProgressDialogUtil.closeProcessDialog();
			}
		}).start();
    }
    
    private void fechaActionPerformed(java.awt.event.ActionEvent evt){
    	if(fechaABuscar != null && fechaABuscar.getDate() != null)
    		loadData(DateConverter.convertDateToString(fechaABuscar.getDate(),"yyyy-MM-dd"));
    }
    
private class TableActionListener implements MouseListener{
    	
    	private CobranzasItem[] createArrayItems(){
    		try {
    		String codAdmin = AdministradorManager.instance().getCodAdministradorByCodUsuario(getUsuario().getCodigo()); 
			String codUnidad=null;
			if(codAdmin != null)
				codUnidad = UnidadAdministradorManager.instance().getCodigoUnidad(codAdmin);
			if(codUnidad != null){
				Object[] objects = PresupuestosManager.instance().findFacturadosNoCobradosByUnidadAdmNroPpto(codUnidad,jTable1.getSelectedItem().getNumeroPresupuesto());				
			
			CobranzasItem[] items = new CobranzasItem[objects.length];
			for (int i = 0; i < objects.length; i++) {
				Object[] data = (Object[]) objects[i];
				
				CobranzasItem item = new CobranzasItem();

				item.setNumeroDePresupuesto(Integer.parseInt(data[0].toString()));
				item.setRazonSocial(data[1].toString());
				item.setFechaConfirmacion(data[2].toString());
				item.setFechaFacturado(data[3].toString());
				item.setImporteTotal(data[4].toString());
				item.setClienteFacturacion(data[5].toString());				
				
				if(data[6] != null)
					item.setFactura(data[6].toString());					
				if(data[7] != null)
					item.setFacturaAdelanto(data[7].toString());
				if(data[8] != null)
					item.setFacturaAdicional(data[8].toString());
				
				if(data[9] != null)
					item.setFechaFacturaAdelanto(data[9].toString());
				if(data[10] != null)
					item.setFechaFacturaAdicional(data[10].toString());
				
				item.setContacto(data[11].toString());
				item.setLugar(data[12].toString());
				item.setCodClienteFacturacion(data[13].toString());
				item.setComercial(data[14].toString());
				item.setCondicionPago(data[15].toString());
				item.setObservaciones(data[16].toString());					
				
				item.setAdelanto(data[17].toString());
				
				items[i] = item;
				
			}
			return items;
			}
			return null;
    		} catch (RemoteException e) {
    			e.printStackTrace();
    			return null;
    		}
    	}
    	
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getClickCount() ==2){
				ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_DATA);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());		
				new Thread(new Runnable() {	
					public void run() {
						CobranzasItem[] c = createArrayItems();
						if(c.length>0) {
							PantallaCobranzas pcobranzas=new PantallaCobranzas(Main.getVentana());		
					    	pcobranzas.setUser(getUsuario());
							PantallaAgendaCobranzas pantallaCobranzas = new PantallaAgendaCobranzas(pcobranzas);
							pantallaCobranzas.setupMiddle();
							pantallaCobranzas.setCobranzasItems(c);
							pantallaCobranzas.loadData(c[0]);
							//cambiarPantallaA(pantallaCobranzas);
							pantallaCobranzas.setVisible(true);
							
						}
						jTable1.getTable().clearSelection();
						ProgressDialogUtil.closeProcessDialog();
					}
				}).start();
			}
			
		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			maybeShowPopup(arg0);
		}

		public void mouseReleased(MouseEvent arg0) {
			maybeShowPopup(arg0);
			
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    	
		private void maybeShowPopup(MouseEvent e) {
	        if (e.isPopupTrigger()) {
	            popup.show(e.getComponent(),
	                       e.getX(), e.getY());
	        }
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
    
    private class PopUpMenuActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
	       // JMenuItem source = (JMenuItem)(e.getSource());
	       // System.out.println("seleccione: "+ source.getText());
	        
	        try {
	        	MessageFormat format = new MessageFormat("Recordatorios del día "+DateConverter.convertDateToString(fechaABuscar.getDate(),"dd/MM/yyyy"));
	        	MessageFormat format2 = new MessageFormat("Page {0}");
			    if (! jTable1.getTable().print(PrintMode.FIT_WIDTH,format, format2)) {
			        System.err.println("User cancelled printing");
			    }
			} catch (java.awt.print.PrinterException ex) {
			    System.err.format("Cannot print %s%n", ex.getMessage());
			}
	    }
	}
    
    private void agendasCobradasActionPerformed(java.awt.event.ActionEvent evt){
    	BuscadorAgendasCobradas buscador = new BuscadorAgendasCobradas(Main.getVentana());
    	buscador.setPantallaBienvenidaAdm(this);
		buscador.init();
		buscador.setUsuario(getUsuario());
		buscador.setVisible(true);
		buscador = null;
    }
    
    private class CambiarClaveActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			PantallaCambioPassword a = new PantallaCambioPassword(Main.getVentana(),getUsuario());
			a.init();
			a.setVisible(true);	
			a = null;
		}
	}
    
    private class SalirActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (MessageUtil.showYesNoMessage(Main.getVentana(),"Seguro que desea salir de la aplicación","Salir"))
				Main.closeWindow();		
		}
		
    }  
}
