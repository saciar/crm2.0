package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Cursor;
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
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.JTable.PrintMode;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableRowSorter;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.AdministradorManager;
import crm.client.managers.PresupuestosManager;
import crm.client.managers.UnidadAdministradorManager;
import crm.client.pantalla.cobranzas.PantallaAgendaCobranzas;
import crm.client.pantalla.cobranzas.PantallaAgendaCobranzas2;
import crm.client.util.DateConverter;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.UsuariosUtil;
import crm.gui.Main;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.solapa.MainPanelComercial;
import crm.gui.tablerenderer.cobranzas.CobranzasItem;
import crm.gui.tablerenderer.cobranzas.CobranzasTableModel;
import crm.gui.tablerenderer.cobranzas.TableRenderCobranzas;
import crm.libraries.abm.entities.Partido;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.Usuario;
import crm.libraries.util.MessageUtil;

public class PantallaCobranzas extends PantallaEmergente {

	private TableRenderCobranzas tableRender;
	private GradientButton actualizar;
	private GradientButton marcar;
	private GradientButton buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private GradientButton salir;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    
    private Usuario user;
    
   // private JPopupMenu popup;
	private PanelImagen middlePanel;
	
	public PantallaCobranzas(Frame owner) {
		//super();
		super("Presupuestos pendientes de cobro",owner);
		//setMyMenu(createMenu());
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
        jSeparator1 = new javax.swing.JSeparator();
        tableRender = new TableRenderCobranzas();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20));
        jLabel1.setText("Presupuestos facturados pendientes de cobro");

        actualizar.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        actualizar.setText("Actualizar");
        actualizar.setDoubleBuffered(true);
        actualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        actualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        actualizar.addActionListener(new RefreshAccion());
        
        marcar.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));
        marcar.setText("Ver agenda");
        marcar.setDoubleBuffered(true);
        marcar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        marcar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        marcar.addActionListener(new MarcarActionListener());
        
        buscar.setIcon(new javax.swing.ImageIcon(getUrlImagen("tick.png")));
        buscar.setText("Marcar como cobrado");
        buscar.setDoubleBuffered(true);
        buscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buscar.addActionListener(new CobradoActionListener());
        
        salir.setText("Salir");
        salir.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        salir.addActionListener(new SalirAccion());

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
				Object[] objects = manager.findFacturadosNoCobradosByUnidadAdm(codUnidad);				
				
				CobranzasTableModel model = new CobranzasTableModel();
				for (int i = 0; i < objects.length; i++) {
					Object[] data = (Object[]) objects[i];
					
					CobranzasItem item = new CobranzasItem();
					//int index = 0;
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
					
					item.setContacto(data[9].toString());
					item.setLugar(data[10].toString());
					item.setCodClienteFacturacion(data[11].toString());
					item.setComercial(data[12].toString());
					item.setCondicionPago(data[13].toString());
					item.setObservaciones(data[14].toString());					
					
					item.setAdelanto(data[15].toString());
					item.setTipoIcono(Integer.parseInt(data[16].toString()));
					if(data[17]!= null)
						item.setFechaFacturaAdelanto(data[17].toString());
					if(data[18]!= null)
						item.setFechaFacturaAdicional(data[18].toString());
					item.setEstado(data[19].toString());
					model.addRow(item);
					
				}
				if(model.getRowCount() > 0){
					tableRender.getTable().setModel(model);
					tableRender.refreshTable();						
				}
			}

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
	
	private void setAsCobrado(CobranzasItem item, PresupuestosManager manager) {		
		try {
			if(item.getEstado().equals("Facturado"))
				manager.setAsCobrado(Long.toString(item.getNumeroDePresupuesto()),user.getCodigo());
			else if(item.getEstado().equals("Adelanto Facturado"))
				manager.setAnticipoCobrado(Long.toString(item.getNumeroDePresupuesto()),user.getCodigo());		
			CobranzasTableModel model = (CobranzasTableModel)tableRender.getTable().getModel();
			model.removeRow(item);					
			sorter();
		} catch (RemoteException e) {
			MessageUtil.showErrorMessage(Main.getVentana(),"El presupuesto no fue marcado como cobrado. Por favor intentelo nuevamente");
		}
		
	}
	
	private void setCobrados(CobranzasItem item,PresupuestosManager manager){
		try{
			if (!manager.isPptoCancelado(item.getNumeroDePresupuesto())) {				
					setAsCobrado(item, manager);			
			}
			else 
				MessageUtil.showErrorMessage(Main.getVentana(),"El presupuesto nro "+item.getNumeroDePresupuesto()+" fue cancelado. Por favor actualice la lista");
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	
	//*******************************************ACCIONES********************************************************************
	
	private class SalirAccion implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {

			if (MessageUtil.showYesNoMessage(null, "¿Desea salir de presupuestos pendientes de cobro?", "Salir")){
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
			
			CobranzasItem[] c = tableRender.getSelectedItems();
			
			if( c != null){
				Long[] nombres= new Long[c.length];
				for (int i = 0; i < c.length; i++) {	
					nombres[i] = (c[i].getNumeroDePresupuesto());
				
				}
			
				String msg = "¿Realmente desea ver las "+ nombres.length +" agendas seleccionadas?";
			
				/*for (int i = 0; i < nombres.length; i++) {	
					msg = msg.concat("\n"+nombres[i].toString());				
				}*/
			
				if (MessageUtil.showYesNoMessage(Main.getVentana(),msg,"Confirmación") == true) {
					ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_DATA);
					ProgressDialogUtil.launchProcessDialog(Main.getVentana());		
					new Thread(new Runnable() {	
						public void run() {
							
							/*ArrayList<CobranzasItem> c = tableRender.getArraySelectedItems();
							Iterator it = c.iterator();						
							
							if(it.hasNext()){
								PantallaAgendaCobranzas pantallaCobranzas = new PantallaAgendaCobranzas(PantallaCobranzas2.this);
								pantallaCobranzas.setupMiddle();
								pantallaCobranzas.setCobranzasItems(c);
								pantallaCobranzas.loadData((CobranzasItem)it.next());
								cambiarPantallaA(pantallaCobranzas);
								pantallaCobranzas.setVisible(true);
							}
							
							tableRender.getTable().clearSelection();*/
							
							/*CobranzasItem[] c = tableRender.getSelectedItems();
							for (int i = 0; i < c.length; i++) {
								PantallaAgendaCobranzas pantallaCobranzas = new PantallaAgendaCobranzas(PantallaCobranzas2.this);
								pantallaCobranzas.setupMiddle();
								if(i==0){
									pantallaCobranzas.loadData(c[i]);
									cambiarPantallaA(pantallaCobranzas);
									pantallaCobranzas.setVisible(true);
								}
							}
							tableRender.getTable().clearSelection();*/
							
							CobranzasItem[] c = tableRender.getSelectedItems();
							if(c.length>0) {
								PantallaAgendaCobranzas pantallaCobranzas = new PantallaAgendaCobranzas(PantallaCobranzas.this);
								pantallaCobranzas.setupMiddle();
								pantallaCobranzas.setCobranzasItems(c);
								pantallaCobranzas.loadData(c[0]);
								//cambiarPantallaA(pantallaCobranzas);
								pantallaCobranzas.setVisible(true);
								dispose();
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
	
	private class CobradoActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(tableRender.getSelectedItems().length > 0){
				final CobranzasItem[] c = tableRender.getSelectedItems();
				final PresupuestosManager manager = PresupuestosManager.instance();
				
				if (MessageUtil.showYesNoMessage(Main.getVentana(),"Esta seguro que quiere marcar como cobrado los siguientes "+c.length+" presupuestos?","Confirmación") == true) {				
					ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_DATA);
					ProgressDialogUtil.launchProcessDialog(Main.getVentana());		
					new Thread(new Runnable() {	
						public void run() {
							for(int i=0; i<c.length;i++){
								setAsCobrado(c[i],manager);
							}
							ProgressDialogUtil.closeProcessDialog();
						}
					}).start();
				}
			}
			else {
				MessageUtil.showMessage(Main.getVentana(),"Debe seleccionar al menos un presupuesto para dar de baja");
			}
		}
		
	}
	
	class PopupListener extends MouseAdapter {
	    public void mouseReleased(MouseEvent e) {
	    	tableRender.setMensajeImpresion("Presupuestos no cobrados al "+DateConverter.convertDateToString(new Date(),"dd/MM/yyyy"));
	        tableRender.maybeShowPopup(e);
	    }

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
