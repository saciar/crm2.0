package crm.gui.pantalla;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import crm.client.managers.PresupuestosManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.solapa.MainPanelComercial;
import crm.gui.tablerenderer.buscadorPptos.BuscadorPptosItem;
import crm.gui.tablerenderer.buscadorPptos.BuscadorPptosTableModel;
import crm.gui.tablerenderer.buscadorPptos.TableRenderBuscadorPptos;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.Usuario;
import crm.libraries.util.MessageUtil;

public class DetallesPptos extends PantallaEmergente{
	
	private Presupuesto pptoElegido;
	
	private javax.swing.JLabel jLabel1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JButton salir;
	private TableRenderBuscadorPptos tableBuscador;
	private javax.swing.JLabel tipoPptos;
	private PanelImagen panel;	
	
	private JScrollPane scrollPane;
	
	private int type;
	private Usuario user;
	
	public DetallesPptos(Frame owner, int type){
		super("Detalles de presupuestos",owner);
        pptoElegido = null;
        setMyMenu(createMenu());
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
	
	public TableRenderBuscadorPptos getTableBuscador() {
		return tableBuscador;
	}
	
	public Presupuesto getPresupuestoElegido(){
		return pptoElegido;
	}
	
	public void init(){
		try{
			panel = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}

		jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        tableBuscador = new TableRenderBuscadorPptos();
        scrollPane = new JScrollPane(tableBuscador.getTable());
        salir = new javax.swing.JButton();
        tipoPptos = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Detalle de presupuestos");
        
        tableBuscador.getTable().setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tableBuscador.getTable().setToolTipText("Resultado de la busqueda");
        tableBuscador.getTable().setFocusCycleRoot(true);
        tableBuscador.getTable().setIntercellSpacing(new java.awt.Dimension(10, 5));
        //tableBuscador.setBackground(new Color(0,0,0,100));

        salir.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_down.png")));
        salir.setMnemonic('s');
        salir.setText("Salir");
        salir.setToolTipText("Click para salir");

        tipoPptos.setFont(new java.awt.Font("Tahoma", 1, 14));
        
        completePantalla();
        
        createListener();
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
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(tipoPptos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(scrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
                        .addContainerGap())))
            .add(layout.createSequentialGroup()
                .add(464, 464, 464)
                .add(salir)
                .addContainerGap(477, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(tipoPptos))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(scrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .add(20, 20, 20)
                .add(salir)
                .addContainerGap())
        );
        
        this.getContentPane().add(panel);      
        this.pack();        
	}
	
	private void completePantalla(){
		switch(type){
			case 0: tipoPptos.setText("confirmados por Unidad Comercial");
					break;
			case 1: tipoPptos.setText("confirmados propios");
				break;
			case 2: tipoPptos.setText("pendientes por Unidad Comercial");
				break;
			case 3: tipoPptos.setText("pendientes propios");
				break;
			case 4: tipoPptos.setText("de hoy por Unidad Comercial");
				break;
			case 5: tipoPptos.setText("de hoy propios");
				break;
			case 6: tipoPptos.setText("a vencer por Unidad Comercial");
				break;
			case 7: tipoPptos.setText("a vencer propios");
				break;
		}
	}
	
	private void createListener(){
		salir.addActionListener(new SalirActionListener());
		salir.addKeyListener(new SalirKeyListener());
		tableBuscador.getTable().addMouseListener(new TablaMouseListener());
	}
	
	private String getEstadoPpto(Long nroPpto){
		Object[] estado;
		try {
			estado = PresupuestosManager.instance().buscarEstadoActual(nroPpto);
			
			for(int j=0; j<estado.length; j++){				
				Object[] estados = (Object[])estado[j];
				
				if((Integer)estados[2] == 0 && (Integer)estados[4] == 0)
					return "Pendiente";
				else if((Integer)estados[2] == 1 && (Integer)estados[3] ==0 && (Integer)estados[5] ==0)
					return "Confirmado";
				else if((Integer)estados[5] == 1 && (Integer)estados[3] ==0 && (Integer)estados[6] == 0 && (Integer)estados[10] == 0)
					return "Orden de servicio";
				else if((Integer)estados[6] == 1 && (Integer)estados[3] ==0 && (Integer)estados[8] == 0 && (Integer)estados[10] == 0)
					return "Orden de facturación";
				else if((Integer)estados[8] == 0 && (Integer)estados[3] ==0 && (Integer)estados[10] == 1 && (Integer)estados[11] == 0 )
					return "Orden de facturación de adelanto";
				else if((Integer)estados[8] == 0 && (Integer)estados[3] ==0 && (Integer)estados[11] == 1 )
					return "Adelanto facturado";		
				else if((Integer)estados[8] == 1 && (Integer)estados[3] ==0 && (Integer)estados[9] == 0 )
					return "Facturado";
				else if((Integer)estados[9] == 1 && (Integer)estados[3] ==0 )
					return "Cobrado";
				else if((Integer)estados[3] ==1)
					return "Cancelado";
				else if((Integer)estados[4] ==1)
					return "Rechazado";		
				else return null;		
			}
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
			return null;
		}
		return null;
	}
	
	public void setTableDetallesPptos2(Object[] presupuestos){
		if (presupuestos != null){
			BuscadorPptosTableModel model = new BuscadorPptosTableModel();

				for(int i=0; i<presupuestos.length; i++){				
					Object[] nrosPpto = (Object[])presupuestos[i];
					
					BuscadorPptosItem item = new BuscadorPptosItem();
					item.setNumeroPpto((Long)nrosPpto[0]);
					if(!getEstadoPpto((Long)nrosPpto[0]).equals(null))
						item.setEstado(getEstadoPpto((Long)nrosPpto[0]));
					
					item.setVendedor((String)nrosPpto[1]);
					item.setCliente((String)nrosPpto[2]);				
					item.setNombreEvento((String)nrosPpto[3]);
				
					item.setFechaInicio((String)nrosPpto[4]);
					
					model.addRow(item,i);
					
					
				}
				tableBuscador.getTable().setModel(model);
				tableBuscador.refreshTable();				
		}
	}
	
	public void setTableDetallesPptos(Object[] presupuestos){
		if (presupuestos != null){
			BuscadorPptosTableModel model = new BuscadorPptosTableModel();
			
			for(int i=0; i<presupuestos.length; i++){				
				Object[] nrosPpto = (Object[])presupuestos[i];
				
				BuscadorPptosItem item = new BuscadorPptosItem();
				item.setNumeroPpto((Long)nrosPpto[0]);
				item.setEstado((String)nrosPpto[1]);
				item.setVendedor((String)nrosPpto[2]);
				item.setCliente((String)nrosPpto[3]);				
				item.setNombreEvento((String)nrosPpto[4]);
				
				item.setFechaInicio((String)nrosPpto[5]);
				
				model.addRow(item,i);				
			}
			tableBuscador.getTable().setModel(model);
			tableBuscador.refreshTable();				
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
		MainPanelComercial gp = PantallaBienvenidaVerdadera.getMainPanel2();		
		gp.setPresupuesto(p);	
		cambiarPantallaA(gp);		
	}
	
	/*--------------------------ACCIONES-----------------------------------------------------*/	
	private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(DetallesPptos.this, "¿Desea salir de detalles de presupuestos?", "Salir")){
				setVisible(false);
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
				if (MessageUtil.showYesNoMessage(null, "¿Desea salir de detalles de presupuestos?", "Salir")){
					setVisible(false);
				}
			}
		}
	}
	
	private String getMIp(){
		/*InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();

			System.out.println("Current IP address : " + ip.getHostAddress());

			return ip.getHostAddress();
		} catch (UnknownHostException e) {
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
			System.out.print("Current MAC address : "+ sb.toString());
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
	
	private class TablaMouseListener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getClickCount() ==2){
				ProgressDialogUtil.setType(ProgressDialogUtil.SEARCH_PPTO_TYPE);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				new Thread(new Runnable() {	
					boolean loadPpto= false;
					public void run() {
						try{
							BuscadorPptosItem item = tableBuscador.getSelectedItem();						
							pptoElegido = PresupuestosManager.instance().buscarPresupuesto(item.getNumeroPpto());
							
							if (pptoElegido != null && pptoElegido.getActivo().equals("N")) {								
								PresupuestosManager.instance().modificarActivo(pptoElegido.getNumeroDePresupuesto(), "S",Integer.parseInt(user.getCodigo()),getMIp(),getMac());
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
								ProgressDialogUtil.closeProcessDialog();
								dispose();
							}
							else
								ProgressDialogUtil.closeProcessDialog();
						}
					}
				}).start();
			}
		}

		public void mousePressed(MouseEvent arg0) {
			
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}

		public void mouseEntered(MouseEvent arg0) {
			
		}

		public void mouseExited(MouseEvent arg0) {
			
		}
		
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

}
