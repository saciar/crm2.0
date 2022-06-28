package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import crm.client.managers.PresupuestosManager;
import crm.client.managers.VendedorUsuarioManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.tablerenderer.activador.ActivadorItem;
import crm.gui.tablerenderer.activador.ActivadorTableModel;
import crm.gui.tablerenderer.activador.TableRenderActivador;
import crm.libraries.util.MessageUtil;

public class ActivadorPpto extends PantallaEmergente{
	private TableRenderActivador tableBuscador;
	private GradientButton cerrar;
	private GradientButton salir;
	private GradientButton buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel tipoPptos;
	
    private PanelImagen panel;
    private long codVendedor = 0;
    
	public ActivadorPpto(Frame owner){		
		super("Mis presupuestos abiertos", owner);
		setMyMenu(createMenu());
	}
	
	public void initComponents(){
		try{
			panel = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
		
		jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        tableBuscador = new TableRenderActivador();
        salir = new GradientButton("", Color.blue);
        tipoPptos = new javax.swing.JLabel();
        cerrar = new GradientButton("", Color.blue);
        buscar = new GradientButton("", Color.blue);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Presupuestos abiertos en detalle");

        tableBuscador.getTable().setBorder(javax.swing.BorderFactory.createEtchedBorder());        
        tableBuscador.getTable().setToolTipText("Presupuestos abiertos");
        tableBuscador.getTable().setIntercellSpacing(new java.awt.Dimension(10, 5));
        tableBuscador.getTable().setSelectionBackground(new java.awt.Color(205, 205, 205));
        tableBuscador.getTable().setSelectionForeground(new java.awt.Color(80,80,80));

        salir.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_down.png")));
        salir.setMnemonic('s');
        salir.setText("Salir");
        salir.setToolTipText("Click para salir");

        tipoPptos.setFont(new java.awt.Font("Tahoma", 1, 14));

        cerrar.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_delete.png")));
        cerrar.setText("Cerrar presupuesto");
        cerrar.setMnemonic('c');
        cerrar.setToolTipText("Click para cerrar el presupuesto seleccionado");
        cerrar.setEnabled(false);
        
        buscar.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        buscar.setText("Buscar");
        buscar.setMnemonic('b');
        buscar.setToolTipText("Click para buscar sus presupuestos abiertos");       
        
        createListener();  
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
	
	public void showComponents(){
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .add(jLabel1)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(tipoPptos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, tableBuscador, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE))
                            .addContainerGap())
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .add(buscar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(cerrar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(salir)
                            .add(351, 351, 351))))
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
                    .add(tableBuscador, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(salir)
                        .add(cerrar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(buscar))
                    .addContainerGap())
            );
		
        this.getContentPane().add(panel);
        this.pack();        

        updatePosition();
	}
	
	private JMenu[] createMenu(){
		JMenu jMenu1 = new javax.swing.JMenu();
		JMenuItem jMenuItem1 = new javax.swing.JMenuItem();
		JMenuItem jMenuItem2 = new javax.swing.JMenuItem();
		JMenuItem jMenuItem3 = new javax.swing.JMenuItem();

		jMenu1.setMnemonic('A');		
        jMenu1.setText("Archivo");
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		
		jMenuItem1.setMnemonic('B');
        jMenuItem1.setText("Buscar Presupuestos abiertos");
        jMenuItem1.addActionListener(new BuscarActionListener());
        jMenu1.add(jMenuItem1);
        jMenuItem2.setMnemonic('C');
        jMenuItem2.setText("Cerrar");
        jMenuItem2.addActionListener(new CerrarActionListener());
        jMenu1.add(jMenuItem2);
        jMenu1.addSeparator();
        jMenuItem3.setMnemonic('S');
        jMenuItem3.setText("Salir");
        jMenuItem3.addActionListener(new SalirActionListener());
        jMenu1.add(jMenuItem3);
        
        JMenu[] list = new JMenu[1];
        list[0] = jMenu1;
        return list;
	}
	
	private void createListener(){
		salir.addActionListener(new SalirActionListener());
		cerrar.addActionListener(new CerrarActionListener());
		buscar.addActionListener(new BuscarActionListener());
		tableBuscador.getTable().addMouseListener(new TablaMouseListener());
	}
	
	public void setupTableActivador(final long codVendedor){
		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_PPTO_ABIERTOS_TYPE);
		ProgressDialogUtil.launchProcessDialog(Main.getVentana());		
		Thread hilo = new Thread(new Runnable() {	
			public void run() {
				try{					
					Object[] presupuestos = null;
					presupuestos = PresupuestosManager.instance().buscarPresupuestosAbiertosVendedor(codVendedor);
					ActivadorTableModel model = new ActivadorTableModel();
					for(int i=0; i<presupuestos.length; i++){
						ActivadorItem item = new ActivadorItem();
						
						Object[] p = (Object[]) presupuestos;
						Object[] presupuestoDatos = (Object[])p[i];
						
						item.setNumeroPpto((String.valueOf(presupuestoDatos[0])));
						item.setEstado((String)presupuestoDatos[5]);
						item.setVendedor((String)presupuestoDatos[1]);
						item.setCliente((String)presupuestoDatos[2]);				
						item.setNombreEvento((String)presupuestoDatos[3]);
						item.setActivo("Si");
							
						model.addRow(item);
					}
					if(model.getRowCount() > 0){
						tableBuscador.getTable().setModel(model);
						tableBuscador.refreshTable();						
					}
					else {
						Util.alertMsg(Main.getVentana(), "Usted no tiene ningún presupuesto abierto");						
					}
				}catch (RemoteException e) {
					Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
				}
				finally{
					ProgressDialogUtil.closeProcessDialog();
				}
			}				
		});
		hilo.start();
	}
	
	private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(ActivadorPpto.this, "¿Desea salir de mis presupuestos?", "Salir")){
				setVisible(false);
			}
		}
		
	}
	
	private class CerrarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(tableBuscador.getTable().getRowCount() > 0){
				ActivadorItem item = tableBuscador.getSelectedItem();
				VendedorUsuarioManager vum = VendedorUsuarioManager.instance();
				try{
					PresupuestosManager.instance().modificarActivo(Long.parseLong(item.getNumeroPpto()), "N",Integer.parseInt(vum.getCodigoUsuario(String.valueOf(codVendedor))),getMIp(),getMac());
					ActivadorTableModel model = (ActivadorTableModel)tableBuscador.getTable().getModel();
					model.removeRow(item);
					tableBuscador.refreshTable();
					cerrar.setEnabled(false);
				}
				catch(RemoteException e){
					Util.errMsg(Main.getVentana(), "Error al modificar datos externos", e);
				}
			}

		}
		
	}
	
	private class TablaMouseListener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
			if(tableBuscador.getTable().getRowCount() > 0){
				ActivadorItem item = tableBuscador.getSelectedItem();
				if(item.getActivo().equals("Si"))
					cerrar.setEnabled(true);
				else cerrar.setEnabled(false);
			}
		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class BuscarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(codVendedor != 0)
				setupTableActivador(codVendedor);			
		}
		
	}

	/**
	 * @param codVendedor The codVendedor to set.
	 */
	public void setCodVendedor(long codVendedor) {
		this.codVendedor = codVendedor;
	}

}
