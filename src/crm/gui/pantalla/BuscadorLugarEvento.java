package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.LugarEventoManager;
import crm.client.managers.SalaLugarManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.reportes.BuscadorReportes;
import crm.gui.pantalla.solapa.LugarEventoPanel;
import crm.gui.pantalla.solapa.SalaModel;
import crm.gui.tablerenderer.buscaLugares.BuscaLugaresItem;
import crm.gui.tablerenderer.buscaLugares.BuscaLugaresTableModel;
import crm.gui.tablerenderer.buscaLugares.BuscaLugaresTableRender;
import crm.libraries.abm.entities.LugarEvento;
import crm.libraries.util.MessageUtil;

public class BuscadorLugarEvento extends PantallaEmergente{
		
		private javax.swing.ButtonGroup buttonGroup1;
	    private javax.swing.JRadioButton codigoRadioButton;
	    private javax.swing.JTextField codigoTextField;
	    private GradientButton exitButton;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JSeparator jSeparator1;
	    private javax.swing.JSeparator jSeparator2;
	    private BuscaLugaresTableRender jTable1;
	    private GradientButton newButton;
	    private GradientButton okButton;
	    private javax.swing.JRadioButton nombrelugarRadioButton;
	    private javax.swing.JTextField nombreLugarTextField;
	    private GradientButton searchButton;
		
	    private PanelImagen panel;
	    private LugarEvento selectedLugar;
	    private LugarEvento lugarAnterior;
	    
	    private LugarEventoPanel lugarPanel;
		private NuevaBusquedaAvanzada busquedaAvanzadaPanel;
		private BuscadorReportes buscadorReportes;
		
		private BuscadorReportesFacturacion2 buscadorReportesFact;

		private BuscadorReportesComercial buscadorReportesCom;
		
		public BuscadorLugarEvento(Frame owner) {
			super("Buscador de lugares", owner);
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
	        nombrelugarRadioButton = new javax.swing.JRadioButton();
	        nombreLugarTextField = new javax.swing.JTextField();
	        codigoRadioButton = new javax.swing.JRadioButton();
	        codigoTextField = new javax.swing.JTextField();
	        jSeparator2 = new javax.swing.JSeparator();
	        jTable1 = new BuscaLugaresTableRender();
	        searchButton = new GradientButton("", Color.blue);
	        okButton = new GradientButton("", Color.blue);
	        newButton = new GradientButton("", Color.blue);
	        exitButton = new GradientButton("", Color.blue);
	        
	        jTable1.getTable().setBorder(javax.swing.BorderFactory.createEtchedBorder());        
	        jTable1.getTable().setToolTipText("Lugares encontrados");
	        jTable1.getTable().setIntercellSpacing(new java.awt.Dimension(10, 5));
	        jTable1.getTable().setSelectionBackground(new java.awt.Color(205, 205, 205));
	        jTable1.getTable().setSelectionForeground(new java.awt.Color(80,80,80));
	        jTable1.setFocusable(false);
	        
	        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
	        jLabel1.setText("Buscar lugares");

	        buttonGroup1.add(nombrelugarRadioButton);
	        nombrelugarRadioButton.setSelected(true);
	        nombrelugarRadioButton.setText("Nombre del Lugar");
	        nombrelugarRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
	        nombrelugarRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

	        buttonGroup1.add(codigoRadioButton);
	        codigoRadioButton.setText("Codigo de Lugar");
	        codigoRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
	        codigoRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

	        searchButton.setText("Buscar lugar");
	        searchButton.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
	        searchButton.setMnemonic('b');
	        searchButton.setToolTipText("Click para buscar lugares");  
	        
	        okButton.setText("Seleccionar");
	        okButton.setIcon(new javax.swing.ImageIcon(getUrlImagen("tick.png")));
	        okButton.setMnemonic('o');
	        okButton.setToolTipText("Click para seleccionar el lugar");  
	        
	        newButton.setText("Nuevo Lugar");
	        newButton.setIcon(new javax.swing.ImageIcon(getUrlImagen("add.png")));
	        newButton.setMnemonic('n');
	        newButton.setToolTipText("Click para agregar un lugar");  

	        exitButton.setText("Salir");
	        exitButton.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_down.png")));
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
	                                            .add(nombrelugarRadioButton)
	                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                                            .add(nombreLugarTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)))))
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
	                        .add(nombrelugarRadioButton)
	                        .add(nombreLugarTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
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
			nombrelugarRadioButton.addActionListener(new RazonSocialActionListener());
			exitButton.addActionListener(new SalirActionListener());
			okButton.addActionListener(new OkActionListener());
			searchButton.addActionListener(new BuscarActionListener());
			nombreLugarTextField.addKeyListener(new NombreKeyListener());
			codigoTextField.addKeyListener(new CodigoKeyListener());
			newButton.addActionListener(new NuevoLugarActionListener());		
		}
		
		private void buscarLugar(){
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					Main.getVentana().getGlassPane().start();
					Thread performer = new Thread(new Runnable(){
						public void run(){
							try{		
								BuscaLugaresTableModel model = new BuscaLugaresTableModel();
								if (nombrelugarRadioButton.isSelected()){
									LugarEvento[] lugares = LugarEventoManager.instance().findByField("nombreLugar",nombreLugarTextField.getText());
									
									if (lugares != null && lugares.length > 0){
										jTable1.setOcurrencia(nombreLugarTextField.getText());
										for (int i = 0; i < lugares.length; i++){
											BuscaLugaresItem item = new BuscaLugaresItem();
											LugarEvento[] p = (LugarEvento[]) lugares;
											LugarEvento lugar = (LugarEvento)p[i];
											
											item.setCodigo(lugar.getCodigo());
											item.setNombreLugar(lugar.getNombreLugar());
											
											model.addRow(item);
										}
									}
									else{
										MessageUtil.showMessage(BuscadorLugarEvento.this, "No se encontró ningún lugar con los datos especificados");
									}
									
								}
								else if(codigoRadioButton.isSelected()){
									LugarEvento c = LugarEventoManager.instance().getLugarEventoById(codigoTextField.getText());
									jTable1.setOcurrencia(null);
									if(c!=null){
										BuscaLugaresItem item = new BuscaLugaresItem();
										item.setCodigo(c.getCodigo());
										item.setNombreLugar(c.getNombreLugar());
										
										model.addRow(item);
									}
									else{
										MessageUtil.showMessage(BuscadorLugarEvento.this, "No se encontró ningún lugar con los datos especificados");
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
								Main.getVentana().getGlassPane().stop();
							}							
						}
					},"Buscando lugar evento");
					performer.start();
				}
			});
		}
		
		public void teclaPresionada(KeyEvent arg0) {
			if (arg0.getSource() == this.codigoTextField){
				if(this.codigoTextField.getText().length()>0){
					this.searchButton.setEnabled(true);
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
						buscarLugar();
				}
			}else if(arg0.getSource() == this.nombreLugarTextField){
				if(this.nombreLugarTextField.getText().length()>=3){
					this.searchButton.setEnabled(true);
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
						buscarLugar();
				}
			}
		}
		
		public LugarEvento getSelectedClient(){
			return this.selectedLugar;
		}

		//------------------------------------------------------------------------------------------------------------------------
		private class CodigoActionListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				codigoTextField.setEnabled(true);
				nombreLugarTextField.setEnabled(false);
				if (codigoTextField.getText().length()>0){
					searchButton.setEnabled(true);
				}			
			}
			
		}
		
		private class NuevoLugarActionListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				PantallaNuevoLugar p = new PantallaNuevoLugar(Main.getVentana());
				p.init();
				p.setVisible(true);
			}
			
		}
		
		private class RazonSocialActionListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				nombreLugarTextField.setEnabled(true);
				codigoTextField.setEnabled(false);
				if (nombreLugarTextField.getText().length()>0){
					searchButton.setEnabled(true);
				}			
			}
			
		}
		
		private class SalirActionListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				if (MessageUtil.showYesNoMessage(BuscadorLugarEvento.this, "¿Desea salir sin seleccionar ningún lugar?",
				"Buscador de lugares") == true){
					selectedLugar = null;
					setVisible(false);
		
				}
			
			}
		}	
		
		public List<SalaModel> getSalasDestino(String codLugar){
			
			List<SalaModel> salasdest = new ArrayList<SalaModel>();
			
			try {
				Object[] salas = SalaLugarManager.instance().getSalaLugarReportByLugar(codLugar);
				for (int i = 0; i < salas.length; i++) {
					Object[] row = (Object[]) salas[i];
					
					SalaModel sala = new SalaModel();
					sala.setCodigoSala(row[0].toString());
					sala.setNombreSala(row[1].toString());
					
					salasdest.add(sala);
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			return salasdest;
		}
		
		private class OkActionListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				if(lugarPanel != null){
					if(jTable1.getTable().getSelectedRowCount()>0){
						BuscaLugaresItem lugarItem = jTable1.getSelectedItem();		
						try {
							selectedLugar = LugarEventoManager.instance().getLugarEventoById(lugarItem.getCodigo());
							List<SalaModel> salasOrigen = lugarPanel.getMainPanel().getModelSalasCreated();
							List<SalaModel> salasDest = getSalasDestino(lugarItem.getCodigo());
							//List<SalaModel> preload = null;
							if(!StringUtils.isBlank(lugarAnterior.getCodigo()) && !lugarItem.getCodigo().equals(lugarAnterior.getCodigo())){
								ReasignarSalas reasignar = new ReasignarSalas(Main.getVentana(),lugarAnterior,selectedLugar,salasOrigen, salasDest);
								reasignar.initComponents(lugarAnterior.getNombreLugar()+" - "+lugarItem.getNombreLugar());				
								reasignar.setVisible(true);
								//preload = reasignar.getResult();
								lugarPanel.getMainPanel().refreshSala();
								
							}	
							if(selectedLugar!= null)
								lugarPanel.setLugarElegido(selectedLugar);
							
						} catch (RemoteException e) {
							e.printStackTrace();
						}
						
						//setVisible(false);
						dispose();
					}
					else MessageUtil.showMessage(BuscadorLugarEvento.this, "Debe selecionarse un lugar primeramente.");
				}
				else if(busquedaAvanzadaPanel != null){
					if(jTable1.getTable().getSelectedRowCount()>0){
						BuscaLugaresItem lugarItem = jTable1.getSelectedItem();		
						try {
							selectedLugar = LugarEventoManager.instance().getLugarEventoById(lugarItem.getCodigo());
							
							if(selectedLugar!= null)
								busquedaAvanzadaPanel.setLugarElegido(selectedLugar);
							
						} catch (RemoteException e) {
							e.printStackTrace();
						}

						dispose();
					}
					else MessageUtil.showMessage(BuscadorLugarEvento.this, "Debe selecionarse un lugar primeramente.");
				}
				else if(buscadorReportes != null){
					if(jTable1.getTable().getSelectedRowCount()>0){
						BuscaLugaresItem lugarItem = jTable1.getSelectedItem();		
						try {
							selectedLugar = LugarEventoManager.instance().getLugarEventoById(lugarItem.getCodigo());
							
							if(selectedLugar!= null)
								buscadorReportes.getTabCriterios().setLugarElegido(selectedLugar);
							
						} catch (RemoteException e) {
							e.printStackTrace();
						}

						dispose();
					}
					else MessageUtil.showMessage(BuscadorLugarEvento.this, "Debe selecionarse un lugar primeramente.");
				}
				else if(buscadorReportesCom != null){
					if(jTable1.getTable().getSelectedRowCount()>0){
						BuscaLugaresItem lugarItem = jTable1.getSelectedItem();		
						try {
							selectedLugar = LugarEventoManager.instance().getLugarEventoById(lugarItem.getCodigo());
							
							if(selectedLugar!= null)
								buscadorReportesCom.getTabCriterios().setLugarElegido(selectedLugar);
							
						} catch (RemoteException e) {
							e.printStackTrace();
						}

						dispose();
					}
					else MessageUtil.showMessage(BuscadorLugarEvento.this, "Debe selecionarse un lugar primeramente.");
				}				
				else if(buscadorReportesFact != null){
					if(jTable1.getTable().getSelectedRowCount()>0){
						BuscaLugaresItem lugarItem = jTable1.getSelectedItem();		
						try {
							selectedLugar = LugarEventoManager.instance().getLugarEventoById(lugarItem.getCodigo());
							
							if(selectedLugar!= null)
								buscadorReportesFact.setLugarElegido(selectedLugar);
							
						} catch (RemoteException e) {
							e.printStackTrace();
						}

						dispose();
					}
					else MessageUtil.showMessage(BuscadorLugarEvento.this, "Debe selecionarse un lugar primeramente.");
				}
					
			}
			
		}	

		private class BuscarActionListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				buscarLugar();			
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

		public LugarEvento getLugarAnterior() {
			return lugarAnterior;
		}

		public void setLugarAnterior(LugarEvento lugarAnterior) {
			this.lugarAnterior = lugarAnterior;
		}

		public void setLugarPanel(LugarEventoPanel lugarPanel) {
			this.lugarPanel = lugarPanel;
		}

		public NuevaBusquedaAvanzada getBusquedaPanel() {
			return busquedaAvanzadaPanel;
		}

		public void setBusquedaPanel(NuevaBusquedaAvanzada busquedaPanel) {
			this.busquedaAvanzadaPanel = busquedaPanel;
		}
		
		public BuscadorReportes getBuscadorReportes() {
			return buscadorReportes;
		}

		public void setBuscadorReportes(BuscadorReportes buscadorReportes) {
			
			this.buscadorReportes = buscadorReportes;
		}
		
		public void setBuscadorReportesFact(BuscadorReportesFacturacion2 buscadorFacturacion2){
			this.buscadorReportesFact = buscadorFacturacion2;
		}		
		
		public BuscadorReportesComercial getBuscadorReportesCom() {
			return buscadorReportesCom;
		}

		public void setBuscadorReportesCom(BuscadorReportesComercial buscadorReportesCom) {
			this.buscadorReportesCom = buscadorReportesCom;
		}

}
