package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableRowSorter;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import crm.client.managers.AdministradorManager;
import crm.client.managers.PresupuestosManager;
import crm.client.managers.UnidadAdministradorManager;
import crm.client.report.OrdenFacturacionReport;
import crm.client.report.ReportBuilder;
import crm.client.util.DateConverter;
import crm.client.util.ProgressDialogUtil;
import crm.gui.Main;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.facturacion.EditarFacturados;
import crm.gui.tablerenderer.facturacion.FacturacionItem;
import crm.gui.tablerenderer.facturacion.FacturacionTableModel2;
import crm.gui.tablerenderer.facturacion.TableRenderFacturacion2;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.Usuario;
import crm.libraries.util.MessageUtil;

public class PantallaFacturacion extends PantallaEmergente {

	private TableRenderFacturacion2 tableRender;
	private GradientButton actualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private GradientButton salir;
    private GradientButton marcar;
    private GradientButton buscar;
    private GradientButton verOF;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;

	private Presupuesto pptoElegido;
	
	private JPopupMenu popup;
	private PanelImagen middlePanel;
	
	private Usuario user;
	
	public PantallaFacturacion(Frame owner){
		super("Presupuestos pendientes de facturacion",owner);
		pptoElegido = null;
	}
	
	public Presupuesto getPresupuestoElegido(){
		return pptoElegido;
	}

	public void initComponent() {

		middlePanel = null;
		try{
			middlePanel = new PanelImagen("Abstract_4220.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			middlePanel = new PanelImagen();
		}
		middlePanel.setPreferredSize(new Dimension(1024,720));
		middlePanel.setPreferredSize(new Dimension(1024,720));
		middlePanel.setPreferredSize(new Dimension(1024,720));
		middlePanel.setBackground(Color.WHITE);
		
		jLabel1 = new javax.swing.JLabel();
        actualizar = new GradientButton("", Color.blue);
        marcar = new GradientButton("", Color.blue);
        buscar = new GradientButton("", Color.blue);
        salir = new GradientButton("", Color.blue);
        verOF = new GradientButton("", Color.blue);
        jSeparator1 = new javax.swing.JSeparator();
        tableRender = new TableRenderFacturacion2();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20));
        jLabel1.setText("Presupuestos confirmados pendientes de facturaci�n");

        actualizar.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        actualizar.setText("Actualizar");
        actualizar.setDoubleBuffered(true);
        actualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        actualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        actualizar.addActionListener(new RefreshAccion());
        
        marcar.setIcon(new javax.swing.ImageIcon(getUrlImagen("tick.png")));
        marcar.setText("Marcar como facturado");
        marcar.setDoubleBuffered(true);
        marcar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        marcar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        marcar.addActionListener(new MarcarActionListener());
        
        buscar.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        buscar.setText("Buscar Facturados");
        buscar.setDoubleBuffered(true);
        buscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buscar.addActionListener(new BuscarActionListener());
        
        verOF.setIcon(new javax.swing.ImageIcon(getUrlImagen("page_white_acrobat.png")));
        verOF.setText("Ver OF/OFA");
        verOF.setDoubleBuffered(true);
        verOF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        verOF.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        verOF.addActionListener(new VerOFActionListener());
        
        salir.setIcon(new javax.swing.ImageIcon(getUrlImagen("ArreterSZ.png")));
        salir.setText("Salir");
        salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        salir.addActionListener(new SalirAccion());
        
        popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Imprimir grilla");
		menuItem.setIcon(new javax.swing.ImageIcon(getUrlImagen("printer.png")));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
	    menuItem.addActionListener(new PopUpMenuActionListener());
	    popup.add(menuItem);

	    //Add listener to components that can bring up popup menus.
	    MouseListener popupListener = new PopupListener();
	    tableRender.getTable().addMouseListener(popupListener);	
        
	    jCheckBox1.setText("Filtrar presupuestos");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
	    
        jCheckBox1.addChangeListener(new CheckChangeListener());
        
        jTextField1.setEnabled(false);
        jTextField1.addKeyListener(new FiltrarKeyListener());        
	}
	
	public void initLayout(){
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(middlePanel);
        middlePanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(tableRender, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
                                .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
                                .add(jLabel1)
                                .add(layout.createSequentialGroup()
                                    .add(jCheckBox1)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 326, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE))
                            .addContainerGap())
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .add(actualizar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(marcar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(buscar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(verOF)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(salir)
                            .add(375, 375, 375))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jLabel1)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(tableRender, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jCheckBox1)
                        .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(actualizar)
                        .add(marcar)
                        .add(buscar)
                        .add(verOF)
                        .add(salir))
                    .addContainerGap())
            );

        layout.linkSize(new java.awt.Component[] {actualizar, salir}, org.jdesktop.layout.GroupLayout.VERTICAL);
        this.getContentPane().add(middlePanel);
        this.pack();
	}

	public void loadRows() {
		try {
			PresupuestosManager manager = PresupuestosManager.instance();
			String codAdmin = AdministradorManager.instance().getCodAdministradorByCodUsuario(user.getCodigo()); 
			String codUnidad=null;
			if(codAdmin != null)
				codUnidad = UnidadAdministradorManager.instance().getCodigoUnidad(codAdmin);
			if(codUnidad != null){
				Object[] objects = manager.findOFNoFacturadosByUnidadAdm2(codUnidad);			

				FacturacionTableModel2 model = new FacturacionTableModel2();
				for (int i = 0; i < objects.length; i++) {
					Object[] data = (Object[]) objects[i];
					
					FacturacionItem item = new FacturacionItem();
					int index = 0;
					item.setNumeroDePresupuesto(Integer.parseInt(data[index++].toString()));
					item.setEstado(data[index++].toString());				
					item.setRazonSocial(data[index++].toString());
					item.setFechaConfirmacion(data[index++].toString());				
					item.setImporteTotal(data[index++].toString());
					item.setVendedor(data[index++].toString());					
					
					/*Object[] facturas = manager.getFacturasByNroPpto(item.getNumeroDePresupuesto());
					String s = null;
					for (int j = 0; j < facturas.length; j++) {
						Object[] fact = (Object[]) facturas[j];
						if(s == null)
							s = s.concat(fact[0].toString());
						s = s.concat(","+fact[0].toString());
					}
					if(s == null)
						s="-";*/
					
					/*int s = manager.getFacturaByNroPpto(item.getNumeroDePresupuesto());
					if(s >0)					
						item.setFacturas(String.valueOf(s));
					else*/
						item.setFacturas("");
					model.addRow(item);
					
		
				}
				if(model.getRowCount() > 0){
					tableRender.getTable().setModel(model);
					tableRender.refreshTable();						
				}
			}

			// getTable().updateUI();

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void loadTableRows() {
		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_DATA);
		ProgressDialogUtil.launchProcessDialog(Main.getVentana());		
		new Thread(new Runnable() {	
			public void run() {
				loadRows();
				ProgressDialogUtil.closeProcessDialog();
			}
		}).start();

	}
	
	private void sorter(){
		if(jCheckBox1.isSelected()){
			TableRowSorter sorter = new TableRowSorter(tableRender.getTable().getModel());			
			//sorter.setRowFilter(RowFilter.regexFilter("(?i)^"+jTextField1.getText(), 1)); 
			sorter.setRowFilter(RowFilter.regexFilter("(?i)^"+jTextField1.getText()));
			tableRender.getTable().setRowSorter (sorter);
			
			tableRender.setOcurrencia(jTextField1.getText());
			tableRender.refreshTable();
		}
	}
	
	
	private Long[] getPresupuestosByNroFactura(Object[] facturas){
		Long[] pptos = new Long[facturas.length];
		for(int i=0; i<facturas.length; i++){			
			pptos[i] = (Long) facturas[i];
		}
		return pptos;
	}
	
	private void setAsFacturado(FacturacionItem item, PresupuestosManager manager) {
		try {
			if(item.getEstado().equals("OF")){
				if(!item.getFacturas().equals("")){
					Long[] pptos = getPresupuestosByNroFactura(manager.getPresupuestoByFactura(item.getFacturas()));
					if(pptos.length > 0){
						String msg = "La factura nro "+item.getFacturas()+" esta asignada tambien a los sigueintes presupuestos ";
						
						for (int i = 0; i < pptos.length; i++) {	
							msg = msg.concat("\n"+pptos[i].toString());				
						}
						msg =msg.concat("\nDesea asignarle igualmente esta factura?");
						if(!MessageUtil.showYesNoMessage(Main.getVentana(),msg, "�Asignar factura?"))
							return;
					}
					manager.setAsFacturado(Long.toString(item.getNumeroDePresupuesto()),user.getCodigo(), item.getFacturas());
				}
				else{
					MessageUtil.showErrorMessage(Main.getVentana(),"Debe asignarle el nro de factura al presupuesto "+ item.getNumeroDePresupuesto() +" y volver a intentar");
					return;
				}
			}
			if(item.getEstado().equals("OF de adelanto")){
				//manager.setAsAdelantado(Long.toString(item.getNumeroDePresupuesto()),getUsuario().getCodigo());
				if(!item.getFacturas().equals("")){
					Long[] pptos = getPresupuestosByNroFactura(manager.getPresupuestoByFactura(item.getFacturas()));
					if(pptos.length > 0){
						String msg = "La factura nro "+item.getFacturas()+" esta asignada tambien a los sigueintes presupuestos ";
						
						for (int i = 0; i < pptos.length; i++) {	
							msg = msg.concat("\n"+pptos[i].toString());				
						}
						msg =msg.concat("\nDesea asignarle igualmente esta factura?");
						if(!MessageUtil.showYesNoMessage(Main.getVentana(),msg, "�Asignar factura?"))
							return;
					}
					manager.setAsAdelantado(Long.toString(item.getNumeroDePresupuesto()),user.getCodigo(), item.getFacturas());
				}
				else{
					MessageUtil.showErrorMessage(Main.getVentana(),"Debe asignarle el nro de factura al adelanto de presupuesto "+ item.getNumeroDePresupuesto() +" y volver a intentar");
					return;
				}
			}
			if(item.getEstado().equals("OF Saldo")){
				if(!item.getFacturas().equals("")){
					Long[] pptos = getPresupuestosByNroFactura(manager.getPresupuestoByFactura(item.getFacturas()));
					if(pptos.length > 0){
						String msg = "La factura nro "+item.getFacturas()+" esta asignada tambien a los sigueintes presupuestos ";
						
						for (int i = 0; i < pptos.length; i++) {	
							msg = msg.concat("\n"+pptos[i].toString());				
						}
						msg =msg.concat("\nDesea asignarle igualmente esta factura?");
						if(!MessageUtil.showYesNoMessage(Main.getVentana(),msg, "�Asignar factura?"))
							return;
					}
					manager.setAsFacturado(Long.toString(item.getNumeroDePresupuesto()),user.getCodigo(), item.getFacturas());
				}
				else{
					MessageUtil.showErrorMessage(Main.getVentana(),"Debe asignarle el nro de factura al presupuesto "+ item.getNumeroDePresupuesto() +" y volver a intentar");
					return;
				}
			}
			if(item.getEstado().equals("OF Adicional")){
				//manager.setAsAdelantado(Long.toString(item.getNumeroDePresupuesto()),getUsuario().getCodigo());
				if(!item.getFacturas().equals("")){
					Long[] pptos = getPresupuestosByNroFactura(manager.getPresupuestoByFactura(item.getFacturas()));
					if(pptos.length > 0){
						String msg = "La factura nro "+item.getFacturas()+" esta asignada tambien a los sigueintes presupuestos ";
						
						for (int i = 0; i < pptos.length; i++) {	
							msg = msg.concat("\n"+pptos[i].toString());				
						}
						msg =msg.concat("\nDesea asignarle igualmente esta factura?");
						if(!MessageUtil.showYesNoMessage(Main.getVentana(),msg, "�Asignar factura?"))
							return;
					}
					manager.setAsAdicionalesFacturados(Long.toString(item.getNumeroDePresupuesto()),user.getCodigo(), item.getFacturas());
				}
				else{
					MessageUtil.showErrorMessage(Main.getVentana(),"Debe asignarle el nro de factura a los adicionales de presupuesto "+ item.getNumeroDePresupuesto() +" y volver a intentar");
					return;
				}
			}
			FacturacionTableModel2 model = (FacturacionTableModel2)tableRender.getTable().getModel();
			model.removeRow(item);
			if(jCheckBox1.isSelected() && tableRender.getTable().getRowSorter() != null)
				sorter();
			else
				tableRender.refreshTable();
			
		} catch (RemoteException e) {
			MessageUtil.showErrorMessage(Main.getVentana(),"El presupuesto no fue marcado como facturado. Por favor intentelo nuevamente");
		}
		
		
	}
	
	private void setFacturados(FacturacionItem item,PresupuestosManager manager){
		try{
			if (!manager.isPptoCancelado(item.getNumeroDePresupuesto())) {				
				setAsFacturado(item, manager);			
			}
			else 
				MessageUtil.showErrorMessage(Main.getVentana(),"El presupuesto nro "+item.getNumeroDePresupuesto()+" fue cancelado. Por favor actualice la lista");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
//	*******************************************ACCIONES********************************************************************

	private class SalirAccion implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {			
				/*if (MessageUtil.showYesNoMessage(Main.getVentana(),"�Seguro que desea salir de Marcar como facturado?","Salir")){
					if ((UsuariosUtil.chequearAcceso(Long.parseLong(p.getCodigoUsuario()), 
							UsuariosUtil.FACTURACION) == true) && (UsuariosUtil.chequearAcceso(Long.parseLong(p.getCodigoUsuario()), 
									UsuariosUtil.COBRANZAS) == false)) {
						cambiarPantallaA(new PantallaBienvenidaFacturacion());
					}
					else if ((UsuariosUtil.chequearAcceso(Long.parseLong(p.getCodigoUsuario()), 
							UsuariosUtil.FACTURACION) == true) && (UsuariosUtil.chequearAcceso(Long.parseLong(p.getCodigoUsuario()), 
									UsuariosUtil.COBRANZAS) == true)) {
						cambiarPantallaA(new PantallaBienvenidaAdministracion());
					}
					Main.getVentana().setExtendedState(JFrame.MAXIMIZED_BOTH);
				}*/
			if (MessageUtil.showYesNoMessage(null, "�Desea salir de presupuestos pendientes de facturaci�n?", "Salir")){
				dispose();
			}
			}

	}

	private class RefreshAccion implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			loadTableRows();
		}

	}
	
	private class MarcarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
			//PresupuestosManager manager = PresupuestosManager.instance();
			
			FacturacionItem[] c = tableRender.getSelectedItems();
			
			if( c != null){
				Long[] nombres= new Long[c.length];
				for (int i = 0; i < c.length; i++) {	
					nombres[i] = (c[i].getNumeroDePresupuesto());
					
				}
				
				String msg = "�Realmente desea marcar los siguientes presupuestos como facturados?";
				
				for (int i = 0; i < nombres.length; i++) {	
					msg = msg.concat("\n"+nombres[i].toString());				
				}
				
				if (MessageUtil.showYesNoMessage(Main.getVentana(),msg,"Confirmaci�n") == true) {
					ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_DATA);
					ProgressDialogUtil.launchProcessDialog(Main.getVentana());		
					new Thread(new Runnable() {	
						public void run() {
							PresupuestosManager manager = PresupuestosManager.instance();
							FacturacionItem[] c = tableRender.getSelectedItems();
							
							for (int i = 0; i < c.length; i++) {
								setFacturados(c[i],manager);
							}
							tableRender.getTable().clearSelection();
							ProgressDialogUtil.closeProcessDialog();
						}
					}).start();
				}						
			}			
		}
		
	}
	
	
	
	private class FiltrarKeyListener implements KeyListener{

		public void keyTyped(KeyEvent arg0) {
			
		}

		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
				sorter();
			}
			
		}

		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class BuscarActionListener implements ActionListener{

            public void actionPerformed(ActionEvent arg0) {
                java.awt.EventQueue.invokeLater(new Runnable() {

                    public void run() {
                        EditarFacturados dialog = new EditarFacturados(new javax.swing.JFrame(), true);
                        dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                            /*public void windowClosing(java.awt.event.WindowEvent e) {
                                System.exit(0);
                            }*/
                        });
                        dialog.setUser(user);
                        dialog.setVisible(true);
                    }
                });
            }
		
	}
	
	private class VerOFActionListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                	FacturacionItem c = tableRender.getSelectedItem();
                	if(c.getEstado().equals("OF"))
                		previewOF(c.getNumeroDePresupuesto());
                	else
                		ReportBuilder.previewOFAdelantoReport(c.getNumeroDePresupuesto());
                }
            });
        }
        
        private void previewOF(long nroPpto){
    		JasperPrint jp = ReportBuilder.createOFReport(nroPpto);

    		JasperViewer jv = new JasperViewer(jp,false);
    		//jv.setAlwaysOnTop(true);
    		setModal(false);
    		jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
    		jv.setTitle(OrdenFacturacionReport.instance().createOFTitle(nroPpto) + " - Vista Previa");
    		jv.setVisible(true);
    		//setModal(true);
    	}
	
}
	
	private class PopUpMenuActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {	        
	        try {
			    if (! tableRender.getTable().print()) {
			        System.err.println("User cancelled printing");
			    }
			} catch (java.awt.print.PrinterException ex) {
			    System.err.format("Cannot print %s%n", ex.getMessage());
			}
	    }
	}
	
	class PopupListener extends MouseAdapter {
	    public void mousePressed(MouseEvent e) {
	       // maybeShowPopup(e);
	    }

	    public void mouseReleased(MouseEvent e) {
	        //maybeShowPopup(e);
	    	tableRender.setMensajeImpresion("Presupuestos no facturados al "+DateConverter.convertDateToString(new Date(),"dd/MM/yyyy"));
	        tableRender.maybeShowPopup(e);
	    }

	    /*private void maybeShowPopup(MouseEvent e) {
	        if (e.isPopupTrigger()) {
	            popup.show(e.getComponent(),
	                       e.getX(), e.getY());
	        }
	    }*/
	}
	
	private class CheckChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(jCheckBox1.isSelected()){
				jTextField1.setEnabled(true);
			}
			else{
				jTextField1.setEnabled(false);
			}
		}
		
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

}
