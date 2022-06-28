package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.PresupuestosManager;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.solapa.MainPanelComercial;
import crm.gui.pantalla.solapa.MainPanelGastos;
import crm.gui.tablerenderer.buscadorPptos.BuscadorPptosItem;
import crm.gui.tablerenderer.buscadorPptos.BuscadorPptosTableModel;
import crm.gui.tablerenderer.buscadorPptos.TableRenderBuscadorPptos;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.Usuario;
import crm.libraries.util.MessageUtil;

public class BuscadorGastos extends PantallaEmergente{
	private TableRenderBuscadorPptos tableBuscador;
	private JButton buscar;
	private JButton busquedaAvanz;
	private JButton salir;
	private JTextField txtNroPpto;
	private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    
	private Presupuesto pptoElegido;
	private PanelImagen panel;
	private Usuario user;
	
	private JScrollPane scrollPane;
	
	//private JPopupMenu popup;
	//private JFileChooser filechooser;	
	
	public BuscadorGastos(Frame owner){		
		super("Búsqueda rápida de presupuestos",owner);
        pptoElegido = null;
        setMyMenu(createMenu());
	}
	
	public TableRenderBuscadorPptos getTableBuscador() {
		return tableBuscador;
	}
	
	public Presupuesto getPresupuestoElegido(){
		return pptoElegido;
	}
	
	public void initComponent(){	
		try{
			panel = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
		jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNroPpto = new javax.swing.JFormattedTextField();
        jSeparator1 = new javax.swing.JSeparator();
        buscar = new GradientButton("", Color.blue);
        busquedaAvanz = new GradientButton("", Color.blue);
        tableBuscador = new TableRenderBuscadorPptos();
        scrollPane = new JScrollPane(tableBuscador.getTable());

        salir = new GradientButton("", Color.blue);
        
        txtNroPpto.setToolTipText("Ingrese el número de presupuesto");
        
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Seleccione el presupuesto a buscar o realice una búsqueda avanzada");

        jLabel2.setText("Buscar por número de presupuesto: ");

        buscar.setMnemonic('b');
        buscar.setText("Buscar");
        buscar.setToolTipText("Click para buscar presupuesto");             
        buscar.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));

        busquedaAvanz.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));     
        busquedaAvanz.setText("Búsqueda avanzada");
        busquedaAvanz.setMnemonic('a');
        busquedaAvanz.setToolTipText("Click para búsqueda avanzada");
        
        tableBuscador.getTable().setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tableBuscador.getTable().setToolTipText("Resultado de la búsqueda");
        tableBuscador.getTable().setIntercellSpacing(new java.awt.Dimension(10, 5));
        tableBuscador.getTable().setSelectionBackground(new java.awt.Color(205, 205, 205));
        tableBuscador.getTable().setSelectionForeground(new java.awt.Color(80,80,80));        
        
        salir.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_down.png")));
        salir.setMnemonic('s');
        salir.setText("Salir");
        salir.setToolTipText("Click para salir");
        
       /* popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Imprimir grilla");
		menuItem.setIcon(new javax.swing.ImageIcon(getUrlImagen("printer.png")));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
	    menuItem.addActionListener(new PopUpMenuImprimirActionListener());
	    popup.add(menuItem);
	    
	    JMenuItem menuItemExport = new JMenuItem("Exportar a XLS");
	    menuItemExport.setIcon(new javax.swing.ImageIcon(getUrlImagen("page_excel.png")));
	    menuItemExport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
	    menuItemExport.addActionListener(new PopUpMenuExportarActionListener());
	    
	    popup.add(menuItemExport);	    
	    
	    filechooser = new JFileChooser();*/
	}
	
	public void showComponent(){
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(scrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
                                .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
                                .add(layout.createSequentialGroup()
                                    .add(jLabel2)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(txtNroPpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(buscar))
                                .add(jLabel1)
                                .add(busquedaAvanz))
                            .addContainerGap())
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .add(salir)
                           .add(477, 477, 477))))
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
                        .add(txtNroPpto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(buscar))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(busquedaAvanz)
                    .add(14, 14, 14)
                    .add(scrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                    .add(16, 16, 16)
                    .add(salir)
                    .addContainerGap())
            );
		
        this.getContentPane().add(panel);
        this.pack();
        
        createListener();        
	}
	
	private JMenu[] createMenu(){
		JMenu jMenu1 = new javax.swing.JMenu();
		JMenuItem jMenuItem1 = new javax.swing.JMenuItem();

		jMenu1.setMnemonic('A');		
        jMenu1.setText("Archivo");
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		
        jMenuItem1.setMnemonic('S');
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new SalirActionListener());
        jMenu1.add(jMenuItem1);
        
        JMenu[] list = new JMenu[1];
        list[0] = jMenu1;
        return list;
	}
	
	private void createListener(){
		busquedaAvanz.addFocusListener(new TxtFocusListener());
		salir.addActionListener(new SalirActionListener());
		salir.addKeyListener(new SalirKeyListener());
		busquedaAvanz.addActionListener(new AvanzadaActionListener());
		buscar.addActionListener(new BuscarAction());
		tableBuscador.getTable().addMouseListener(new TablaMouseListener());
		txtNroPpto.addKeyListener(new TxtKeyListener());
	}	
	
	private String getEstados(Object[] row){
		String results = null;
		if((Integer)row[5] == 1)
			results ="Cobrado";
		else if((Integer)row[6] == 1 && (Integer)row[5] == 0 && (Integer)row[12] == 0)
			results="Facturado";
		else if((Integer)row[12] == 1)
			results="Cancelado";
		else if((Integer)row[9] == 1 && (Integer)row[6] == 0 && (Integer)row[12] == 0)
			results="Orden de Facturacion";
		else if((Integer)row[11] == 1 && (Integer)row[9] == 0 && (Integer)row[12] == 0)
			results="Adelanto facturado";
		else if((Integer)row[10] == 1 && (Integer)row[11] == 0 && (Integer)row[12] == 0)
			results="Con adelanto";
		else if((Integer)row[8] == 1 && (Integer)row[9] == 0 && (Integer)row[10] == 0 && (Integer)row[12] == 0)
			results="Orden de Servicio";			
		else if((Integer)row[7] == 1 && (Integer)row[5] == 0 && (Integer)row[6] == 0 && (Integer)row[12] == 0)
			results="Confirmado";
		else if((Integer)row[15] == 1)
			results="Rechazado";
		else if(((Integer)row[13] == 1 || (Integer)row[14] == 1) && (Integer)row[7] == 0 && (Integer)row[15] == 0)
			results="Pendiente";			
		else results="";
		
		return results;
	}
	
	public void buscarPpto(){
		if(!StringUtils.isBlank(txtNroPpto.getText())){
		/*	ProgressDialogUtil.setType(ProgressDialogUtil.SEARCH_PPTO_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {			
			*/
					BuscadorPptosTableModel model = new BuscadorPptosTableModel();					
					try{
						Object[] datosPpto = PresupuestosManager.instance().buscarPorNumero(Long.parseLong(txtNroPpto.getText()));
						if(datosPpto != null && datosPpto.length > 0){
							for(int i=0; i<datosPpto.length; i++){
								Object[] p = (Object[])datosPpto[i];
								BuscadorPptosItem item = new BuscadorPptosItem();
								item.setNumeroPpto((Long)p[0]);
								item.setEstado((String)p[5]);
								item.setVendedor((String)p[1]);
								item.setCliente((String)p[2]);				
								item.setNombreEvento((String)p[3]);
								item.setFechaInicio((String)p[4]);
								//if((String)p[1] != null)
								item.setUnidadAdm((String)p[6]);
								model.addRow(item);
							}						
							
							if(model.getRowCount() > 0){
								tableBuscador.getTable().setModel(model);
								tableBuscador.refreshTable();
							}
						}
						else
							MessageUtil.showMessage("No existe presupuesto con ese número");
						
					}
					catch(RemoteException e){
						Util.errMsg(Main.getVentana(), "Error al buscar presupuesto", e);
					}
					finally{
					//	ProgressDialogUtil.closeProcessDialog();						
					}
			//	}
				
			//}).start();
		}
	}
	
	public void cambiarPantallaA(Pantalla screen){	
		Main.getVentana().setCursor(new Cursor(Cursor.WAIT_CURSOR));
		Main.getVentana().getContentPane().removeAll();			
		Main.getVentana().getContentPane().add(screen);
		//Main.getVentana().setVisible(true);		
		Main.getVentana().pack();
		Main.getVentana().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));		
		//Main.getVentana().setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	private void loadPresupuesto(final Presupuesto p) {	
		MainPanelGastos gp = PantallaBienvenidaVerdadera.getMainPanelGastos();		
		gp.setPresupuesto(p);	
		cambiarPantallaA(gp);		
	}
	
	/*--------------------------ACCIONES-----------------------------------------------------*/
	private class TxtFocusListener implements FocusListener{

		public void focusGained(FocusEvent arg0) {
			
		}

		public void focusLost(FocusEvent arg0) {
			salir.requestFocus();			
		}
		
	}
	
	private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(BuscadorGastos.this, "¿Desea salir de mis presupuestos?", "Salir")){
				dispose();
			}
		}
		
	}
	
	private class SalirKeyListener implements KeyListener{

		public void keyTyped(KeyEvent arg0) {
						
		}

		public void keyPressed(KeyEvent arg0) {
						
		}

		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				if (MessageUtil.showYesNoMessage(null, "¿Desea salir de búsqueda de presupuestos?", "Salir")){
					dispose();
				}
			}
		}
	}
	
	private class AvanzadaActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			NuevaBusquedaAvanzada buscaPpto= new NuevaBusquedaAvanzada(Main.getVentana());
		
				buscaPpto.initComponents();
				buscaPpto.initLayout();
				buscaPpto.setOwner(BuscadorGastos.this);
			
			buscaPpto.setVisible(true);
		}
		
	}
	
	private String getMIp(){
		InetAddress ip;
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
		}
	}
	
	private String getMac(){
		InetAddress ip;
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
		}
		
	}
	
	private class TablaMouseListener implements MouseListener{
		
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getClickCount() ==2){
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						Main.getVentana().getGlassPane().start();
						Thread performer = new Thread(new Runnable(){
							public void run(){
								boolean loadPpto= false;
								try{
									setVisible(false);
									BuscadorPptosItem item = tableBuscador.getSelectedItem();						
									
									pptoElegido = PresupuestosManager.instance().buscarPresupuesto(item.getNumeroPpto());
									
									if (pptoElegido != null && pptoElegido.getActivo().equals("N")) {					
											PresupuestosManager.instance().modificarActivo(pptoElegido.getNumeroDePresupuesto(), "S", Integer.parseInt(user.getCodigo()),getMIp(),getMac());
											loadPpto = true;
									}
									else if(pptoElegido != null && pptoElegido.getActivo().equals("S")){
										if(pptoElegido.getVendedor().getVendedor().getCodUsuario().equals(getUser().getCodigo())){
											if (MessageUtil.showYesNoMessage(Main.getVentana(),"El Presupuesto "+pptoElegido.getNumeroDePresupuesto()+" esta abierto.\nDesea abrirlo igualmente?","Presupuesto Abierto")){
												PresupuestosManager.instance().modificarActivo(pptoElegido.getNumeroDePresupuesto(), "S",Integer.parseInt(user.getCodigo()),getMIp(),getMac());
												loadPpto = true;
											}
										}
										else
											Util.alertMsg(Main.getVentana(), "El presupuesto esta siendo usado por otro usuario.\nComuniquese con "+pptoElegido.getVendedor().getVendedor().getApellidoYNombre());
									}							
								}
								catch(RemoteException e){
									Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
								}
								finally {							
									if(loadPpto){
										
										loadPresupuesto(pptoElegido);
										dispose();
										Main.getVentana().getGlassPane().stop();
									}
									else{
										setVisible(true);
									Main.getVentana().getGlassPane().stop();}
								}
								
							}
						},"Abriendo presupuesto");
						performer.start();
					}
				});
			}
		}

		public void mousePressed(MouseEvent arg0) {
			tableBuscador.maybeShowPopup(arg0);
		}

		public void mouseReleased(MouseEvent arg0) {
			tableBuscador.maybeShowPopup(arg0);
		}

		public void mouseEntered(MouseEvent arg0) {
			
		}

		public void mouseExited(MouseEvent arg0) {
			
		}
		
	}
	
	private class TxtKeyListener implements KeyListener{

		public void keyTyped(KeyEvent arg0) {
			
		}

		public void keyPressed(KeyEvent arg0) {
			
		}

		public void keyReleased(KeyEvent arg0) {
			
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				if (!StringUtils.isBlank(txtNroPpto.getText())){
					buscarPpto();
				}
				else Util.alertMsg(Main.getVentana(), "Especifique un número de presupuesto");
			}
				
		}
		
	}
	
	private class BuscarAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			buscarPpto();			
		}		
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

}
