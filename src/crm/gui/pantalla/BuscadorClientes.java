package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import crm.client.managers.ClienteManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.reportes.BuscadorReportes;
import crm.gui.tablerenderer.buscaCliente.BuscaClientesItem;
import crm.gui.tablerenderer.buscaCliente.BuscaClientesTableModel;
import crm.gui.tablerenderer.buscaCliente.BuscaClientesTableRender;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.util.MessageUtil;

public class BuscadorClientes extends PantallaEmergente{
	
	private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton codigoRadioButton;
    private javax.swing.JTextField codigoTextField;
    private GradientButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private BuscaClientesTableRender jTable1;
    private GradientButton newButton;
    private GradientButton okButton;
    private javax.swing.JRadioButton razonSocialRadioButton;
    private javax.swing.JTextField razonSocialTextField;
    private GradientButton searchButton;
	
    private BuscadorReportes buscadorReportes;
    
    public BuscadorReportes getBuscadorReportes() {
		return buscadorReportes;
	}

	public void setBuscadorReportes(BuscadorReportes buscadorReportes) {
		this.buscadorReportes = buscadorReportes;
	}

	private PanelImagen panel;
    private Cliente selectedClient;
    
	public BuscadorClientes(Frame owner) {
		super("Buscador de clientes", owner);
		setMinimumSize(new Dimension(800,600));
	}
	
	public void init() {	
		try{
			panel = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
		
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        razonSocialRadioButton = new javax.swing.JRadioButton();
        razonSocialTextField = new javax.swing.JTextField();
        codigoRadioButton = new javax.swing.JRadioButton();
        codigoTextField = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jTable1 = new BuscaClientesTableRender();
        searchButton = new GradientButton("", Color.blue);
        okButton = new GradientButton("", Color.blue);
        newButton = new GradientButton("", Color.blue);
        exitButton = new GradientButton("", Color.blue);
        
        jTable1.getTable().setBorder(javax.swing.BorderFactory.createEtchedBorder());        
        jTable1.getTable().setToolTipText("Clientes encontrados");
        jTable1.getTable().setIntercellSpacing(new java.awt.Dimension(10, 5));
        jTable1.getTable().setSelectionBackground(new java.awt.Color(205, 205, 205));
        jTable1.getTable().setSelectionForeground(new java.awt.Color(80,80,80));
        jTable1.setFocusable(false);
        
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Buscar clientes");

        buttonGroup1.add(razonSocialRadioButton);
        razonSocialRadioButton.setSelected(true);
        razonSocialRadioButton.setText("Razon social / nombre de fantas\u00eda");
        razonSocialRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        razonSocialRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

        buttonGroup1.add(codigoRadioButton);
        codigoRadioButton.setText("Codigo de Cliente");
        codigoRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        codigoRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

        searchButton.setText("Buscar cliente");
        searchButton.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        searchButton.setMnemonic('b');
        searchButton.setToolTipText("Click para buscar clientes");  
        
        okButton.setText("Seleccionar");
        okButton.setIcon(new javax.swing.ImageIcon(getUrlImagen("tick.png")));
        okButton.setMnemonic('o');
        okButton.setToolTipText("Click para selecciona el cliente");  
        
        newButton.setText("Nuevo Cliente");
        newButton.setIcon(new javax.swing.ImageIcon(getUrlImagen("add.png")));
        newButton.setMnemonic('n');
        newButton.setToolTipText("Click para agregar un cliente");  

        exitButton.setText("Salir");
        exitButton.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        exitButton.setMnemonic('s');
        exitButton.setToolTipText("Click para salir");
        
        codigoTextField.setEnabled(false);        
        
        createListeners();
	}
	public void initComponent(){
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel1)
                                .add(layout.createSequentialGroup()
                                    .add(10, 10, 10)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                            .add(codigoRadioButton)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(codigoTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(searchButton))
                                        .add(layout.createSequentialGroup()
                                            .add(razonSocialRadioButton)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(razonSocialTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)))))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                        .add(layout.createSequentialGroup()
                            .add(312, 312, 312)
                            .add(okButton)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(newButton)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(exitButton))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE))
                        .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE)))
                    .addContainerGap())
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
                        .add(razonSocialRadioButton)
                        .add(razonSocialTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(codigoRadioButton)
                        .add(codigoTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(searchButton))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jTable1)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, okButton)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(newButton)
                            .add(exitButton)))
                    .addContainerGap())
            );
        this.getContentPane().add(panel);
        this.pack();        

        updatePosition();
    }
	
	private void createListeners(){
		codigoRadioButton.addActionListener(new CodigoActionListener());
		razonSocialRadioButton.addActionListener(new RazonSocialActionListener());
		exitButton.addActionListener(new SalirActionListener());
		okButton.addActionListener(new OkActionListener());
		searchButton.addActionListener(new BuscarActionListener());
		razonSocialTextField.addKeyListener(new NombreKeyListener());
		codigoTextField.addKeyListener(new CodigoKeyListener());
		newButton.addActionListener(new NuevoClienteActionListener());		
	}
	
	private void buscarCliente(){
		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_DATA);
		ProgressDialogUtil.launchProcessDialog(Main.getVentana());		
		Thread hilo = new Thread(new Runnable() {	
			public void run() {
				try{		
					BuscaClientesTableModel model = new BuscaClientesTableModel();
					if (razonSocialRadioButton.isSelected()){
						Object[] codesAndFantasyNames = ClienteManager.instance().buscarPorNombreFantasiaOEmpresa(razonSocialTextField.getText());
						
						if (codesAndFantasyNames != null && codesAndFantasyNames.length > 0){
							jTable1.setOcurrencia(razonSocialTextField.getText());
							for (int i = 0; i < codesAndFantasyNames.length; i++){
								BuscaClientesItem item = new BuscaClientesItem();
								Object[] p = (Object[]) codesAndFantasyNames;
								Object[] clientes = (Object[])p[i];
								
								item.setCodigo((String.valueOf(clientes[0])));
								item.setNombreFantasia((String.valueOf(clientes[1])));
								item.setRazonSocial((String.valueOf(clientes[2])));
								
								model.addRow(item);
							}
						}
						else{
							MessageUtil.showMessage(BuscadorClientes.this, "No se encontró ningún cliente con los datos especificados");
						}
						
					}
					else if(codigoRadioButton.isSelected()){
						Cliente c = ClienteManager.instance().getClienteById(codigoTextField.getText());
						jTable1.setOcurrencia(null);
						if(c!=null){
							BuscaClientesItem item = new BuscaClientesItem();
							item.setCodigo(c.getCodigo());
							item.setNombreFantasia(c.getNombreFantasia());
							item.setRazonSocial(c.getEmpresa());
							
							model.addRow(item);
						}
						else{
							MessageUtil.showMessage(BuscadorClientes.this, "No se encontró ningún cliente con los datos especificados");
						}
					}
					if(model.getRowCount() > 0){
						jTable1.getTable().setModel(model);
						jTable1.refreshTable();						
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
	
	public void teclaPresionada(KeyEvent arg0) {
		if (arg0.getSource() == this.codigoTextField){
			if(this.codigoTextField.getText().length()>0){
				this.searchButton.setEnabled(true);
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
					buscarCliente();
			}
		}else if(arg0.getSource() == this.razonSocialTextField){
			if(this.razonSocialTextField.getText().length()>=3){
				this.searchButton.setEnabled(true);
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
					buscarCliente();
			}
		}
	}
	
	public Cliente getSelectedClient(){
		return this.selectedClient;
	}

	//------------------------------------------------------------------------------------------------------------------------
	private class CodigoActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			codigoTextField.setEnabled(true);
			razonSocialTextField.setEnabled(false);
			if (codigoTextField.getText().length()>0){
				searchButton.setEnabled(true);
			}			
		}
		
	}
	
	private class NuevoClienteActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			PantallaNuevoCliente pan = new PantallaNuevoCliente(Main.getVentana());
			pan.initComponents();
			pan.setVisible(true);
		}
		
	}
	
	private class RazonSocialActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			razonSocialTextField.setEnabled(true);
			codigoTextField.setEnabled(false);
			if (razonSocialTextField.getText().length()>0){
				searchButton.setEnabled(true);
			}			
		}
		
	}
	
	private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(BuscadorClientes.this, "¿Desea salir sin seleccionar ningún cliente?",
			"Buscador de clientes") == true){
				selectedClient = null;
				setVisible(false);
	
			}
		
		}
	}	
	
	private class OkActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(jTable1.getTable().getSelectedRowCount()>0){
			BuscaClientesItem clienteItem = jTable1.getSelectedItem();		
			try {
				selectedClient = ClienteManager.instance().getClienteById(clienteItem.getCodigo());				
				if(selectedClient!= null)
					buscadorReportes.getTabCriterios().setClienteElegido(selectedClient);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			setVisible(false);		
			}
			else MessageUtil.showMessage(BuscadorClientes.this, "Debe selecionarse un cliente primeramente.");
		}
		
	}
	
	private class BuscarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			buscarCliente();			
		}
		
	}
	
	private class NombreKeyListener implements KeyListener{

		public void keyTyped(KeyEvent arg0) {
			teclaPresionada(arg0);
			
		}

		public void keyPressed(KeyEvent arg0) {
			teclaPresionada(arg0);			
		}

		public void keyReleased(KeyEvent arg0) {
			teclaPresionada(arg0);			
		}
		
	}
	
	private class CodigoKeyListener implements KeyListener{

		public void keyTyped(KeyEvent arg0) {
			teclaPresionada(arg0);			
		}

		public void keyPressed(KeyEvent arg0) {
			teclaPresionada(arg0);			
		}

		public void keyReleased(KeyEvent arg0) {
			teclaPresionada(arg0);			
		}
		
	}
	
	

}
