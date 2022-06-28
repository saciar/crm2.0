package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.AdministradorManager;
import crm.client.managers.PresupuestosManager;
import crm.client.managers.UnidadAdministradorManager;
import crm.client.pantalla.cobranzas.PantallaAgendaCobranzas;
import crm.client.pantalla.cobranzas.PantallaAgendaCobranzas2;
import crm.client.report.ReportBuilder;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.CustomTextField;
import crm.gui.components.DatePanel;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.tablerenderer.cobranzas.CobranzasItem;
import crm.libraries.abm.entities.Usuario;

public class BuscadorAgendasCobradas extends PantallaEmergente{
    
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nroOrden;
    private javax.swing.JButton viewReport;
    
    private Usuario usuario;
    private PantallaBienvenidaCobranzas pantallaBienvenida;
    private PantallaBienvenidaAdministracion pantallaBienvenidaAdministracion;

	public BuscadorAgendasCobradas (Frame owner){
		super("Buscador de agendas cobradas",owner);
		//this.setTitle("Buscador de agendas cobradas");
		this.setModal(false);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setEnabled(true);
    }
	
	public void init(){
		//**************Crecion de panel contenedor con imagen de fondo**********************
		PanelImagen panel = null;
		try{
			panel = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
		
		//**************Crecion de componentes de pantalla***********************************                
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        exit = new GradientButton("",Color.blue);
        viewReport = new GradientButton("",Color.blue);
        jLabel2 = new javax.swing.JLabel();
        nroOrden = CustomTextField.getBigIntInstance();//new javax.swing.JTextField();
        
        //**************Seteos de los componentes********************************************
        jRadioButton1.setText("jRadioButton1");
        jRadioButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Agendas de Pptos Cobrados");

        exit.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        exit.setText("Salir");

        viewReport.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        viewReport.setText("Ver agenda");

        jLabel2.setText("Indique Nro de orden");
        
        //**************Crecion de layout GroupLayout de Netbeans IDE 5.0 con los componentes
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                            .addContainerGap())
                        .add(layout.createSequentialGroup()
                            .add(jLabel1)
                            .addContainerGap(160, Short.MAX_VALUE))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 61, Short.MAX_VALUE)
                            .add(viewReport)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(exit)
                            .add(71, 71, 71))
                        .add(layout.createSequentialGroup()
                            .add(jLabel2)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(nroOrden, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 123, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(57, Short.MAX_VALUE))))
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
                        .add(nroOrden, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 32, Short.MAX_VALUE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(viewReport)
                        .add(exit))
                    .addContainerGap())
            );
        
        this.getContentPane().add(panel);
        this.pack();
        
        JDialog.setDefaultLookAndFeelDecorated(true);
	    createListeners();
        updatePosition();
	}
	
	private void createListeners(){
		exit.addActionListener(new ExitAction());
		viewReport.addActionListener(new ViewAgendaAction()); 
		nroOrden.addKeyListener(new VerAgendaKeyListener());
	}
	
	public void updatePosition(){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation((screenSize.width - this.getWidth())/2,
				(screenSize.height - this.getHeight())/2);
		
	}
	public void setUsuario(Usuario cod){
		usuario=cod;
	}
	
	private CobranzasItem[] createArrayItems(){
		try {
		String codAdmin = AdministradorManager.instance().getCodAdministradorByCodUsuario(usuario.getCodigo()); 
		String codUnidad=null;
		if(codAdmin != null)
			codUnidad = UnidadAdministradorManager.instance().getCodigoUnidad(codAdmin);
		if(codUnidad != null){
			Object[] objects = PresupuestosManager.instance().findCobradosByUnidadAdmNroPpto(codUnidad,Long.parseLong(nroOrden.getText()));				
		
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
	
	private void verAgenda(){
		ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
		ProgressDialogUtil.launchProcessDialog(Main.getVentana());
		new Thread(new Runnable() {	
			public void run() {
				if(!StringUtils.isBlank(nroOrden.getText())){						
					CobranzasItem[] c = createArrayItems();
					if(c.length>0) {
						PantallaCobranzas pcobranzas=new PantallaCobranzas(Main.getVentana());	
						PantallaAgendaCobranzas pantallaCobranzas = new PantallaAgendaCobranzas(pcobranzas);
						pantallaCobranzas.setupMiddle();
						pantallaCobranzas.setCobranzasItems(c);
						pantallaCobranzas.loadData(c[0]);
						pantallaCobranzas.setVisible(true);
						/*if(usuario.getPerfil().equals("9"))
							pantallaBienvenidaAdministracion.cambiar(pantallaCobranzas);
						else
							pantallaBienvenida.cambiar(pantallaCobranzas);*/
						//setVisible(false);
						dispose();
					}
					else{
						Util.alertMsg(ProgressDialogUtil.getProcessDialog(), "El presupuesto no tiene agenda asignada");
					}
					ProgressDialogUtil.closeProcessDialog();
				}					
		}
		}).start(); 
	}
	
	//**********************************ACCIONES******************************************
	
	private class ViewAgendaAction implements ActionListener{		
		
		public void actionPerformed(ActionEvent arg0) {
			verAgenda();
		}
	}
	
	private class ExitAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
		
	}
	
	private class VerAgendaKeyListener implements KeyListener{

		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				verAgenda();
			}
			
		}
		
	}

	public PantallaBienvenidaCobranzas getPantallaBienvenida() {
		return pantallaBienvenida;
	}

	public void setPantallaBienvenida(PantallaBienvenidaCobranzas pantallaBienvenida) {
		this.pantallaBienvenida = pantallaBienvenida;
	}
	
	public void setPantallaBienvenidaAdm(PantallaBienvenidaAdministracion pantallaBienvenida) {
		this.pantallaBienvenidaAdministracion = pantallaBienvenida;
	}

}
