package crm.gui.pantalla.reportes;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import crm.client.managers.PresupuestosManager;
import crm.client.managers.VendedorManager;
import crm.client.report.OsDesconfirmadaReport;
import crm.client.util.DateConverter;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.JXDatePicker;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.BuscadorReportesComercial;
import crm.gui.pantalla.PantallaEmergenteOS;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesItem;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesTableModel;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesTableRender;
import crm.gui.pantalla.solapa.SeguimientoPanel;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.Vendedor;

public class TabPresupuestosComercial {
	private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;

    private javax.swing.JScrollPane jScrollPane1;

    private JXDatePicker jTextField1;
    private JXDatePicker jTextField2;
    
    private BuscadorReportesTableRender jTable1;
    ReportBuilder reportBuilder;
    
    private BuscadorReportesComercial owner;
    
    public TabPresupuestosComercial(BuscadorReportesComercial o){

    owner = o;	
    	
    jPanel1 = new javax.swing.JPanel();
    jPanel3 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jTextField1 = new JXDatePicker();
    jLabel2 = new javax.swing.JLabel();
    jTextField2 = new JXDatePicker();
    jPanel4 = new javax.swing.JPanel();

    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jPanel5 = new javax.swing.JPanel();
    jButton4 = new javax.swing.JButton();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    jButton5 = new javax.swing.JButton();
    
    jTable1 = new BuscadorReportesTableRender();
    //jTable1.getTable().addMouseListener(new TabalMouseListener());
    jScrollPane1 = new JScrollPane();
    reportBuilder = new ReportBuilder();
    }
	
	public javax.swing.JPanel getJPanel1() {
		return jPanel1;
	}

	public void setJPanel1(javax.swing.JPanel panel1) {
		jPanel1 = panel1;
	}

	public void init_components(){
		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rango de Fechas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        jLabel1.setText("Desde");

        jLabel2.setText("Hasta");       

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(588, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabla", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N
        
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Cant. registros");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Total $");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 526, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(147, 147, 147))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        jButton4.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));
        jButton4.setText("Buscar");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new BuscarActionListener());
        
        jButton1.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        jButton1.setText("Ver Reporte");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new VerReporteActionListener());
        
        jButton2.setIcon(new javax.swing.ImageIcon(getUrlImagen("page_excel.png")));
        jButton2.setText("Exportar");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new ExportarActionListener());
        
        jButton5.setIcon(new javax.swing.ImageIcon(getUrlImagen("application.png")));
        jButton5.setText("Ver seguimiento");
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new SeguimientoActionListener());
        
        jButton3.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        jButton3.setText("Salir");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new SalirActionListener());
		
        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton3))                    
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton4))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(jButton5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jButton4)
                .addGap(66, 66, 66)
                .addComponent(jButton1)
                .addGap(67, 67, 67)
                .addComponent(jButton2)
                .addGap(62, 62, 62)
                .addComponent(jButton5)
                .addGap(62, 62, 62)
                .addComponent(jButton3)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

	}
	
	public URL getUrlImagen(String imagen){

		URL url =Main.class.getResource("imagenes/"+imagen); 

		return url;
	}

	private class VerReporteActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {

			reportBuilder.viewReport((BuscadorReportesTableModel)jTable1.getTable().getModel(),"Presupuestos del "+DateConverter.convertDateToString(jTextField1.getDate(),"EEEEE dd MMMMM yyyy")+" hasta  "+DateConverter.convertDateToString(jTextField2.getDate(),"EEEEE dd MMMMM yyyy"));

		}

	}

	private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			owner.dispose();			
		}

	}
	
	private class SeguimientoActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (jTable1.getSelectedItem() != null) {
				try {
					JDialog vent = new JDialog();
					JPanel panel = new PanelImagen("WorldLight.jpg");
					SeguimientoPanel panelSeguimiento = new SeguimientoPanel(panel);
					panelSeguimiento.init();
					Presupuesto presupuesto = PresupuestosManager.instance().buscarPresupuesto(jTable1.getSelectedItem().getNumeroPpto());
					panelSeguimiento.setPresupuesto(presupuesto);
					panelSeguimiento.initLayout();
					panel.updateUI();
					vent.add(panel);
					vent.pack();
					vent.setVisible(true);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				;
			}
			else{
				
			}
			
		}
		
	}

	private class ExportarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// en caso de querer exportar a excel descomentar esto
			 jTable1.openSavePopUp();
			/*if (jTable1.getSelectedItem() != null) {
				try {
					JDialog vent = new JDialog();
					JPanel panel = new PanelImagen("WorldLight.jpg");
					SeguimientoPanel panelSeguimiento = new SeguimientoPanel(panel);
					panelSeguimiento.init();
					Presupuesto presupuesto = PresupuestosManager.instance().buscarPresupuesto(jTable1.getSelectedItem().getNumeroPpto());
					panelSeguimiento.setPresupuesto(presupuesto);
					panelSeguimiento.initLayout();
					panel.updateUI();
					vent.add(panel);
					vent.pack();
					vent.setVisible(true);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				;
			}*/
				 
		}

	}

	private class BuscarActionListener implements ActionListener{
		private String codCliente;
		private String codClienteFact;
		private String fechaDesde;
		private String fechaHasta;
		private String codLugar;
		private String[] codVendedores;
		private String codCondPago;
		private String estado;
		private String codTipoEvento;
		private String codServicio;

		private void setCriteria(){

			fechaDesde = DateConverter.convertDateToString(jTextField1.getDate(), "yyyy-MM-dd");
			fechaHasta = DateConverter.convertDateToString(jTextField2.getDate(), "yyyy-MM-dd");
			
			jTable1.setComentariosXLS("Fecha desde "+fechaDesde+" hasta "+fechaHasta);

			if(owner.getTabCriterios().getCliente_check().isSelected() && owner.getTabCriterios().getClienteElegido() != null){
				codCliente = owner.getTabCriterios().getClienteElegido().getCodigo();
				
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Cliente: "+owner.getTabCriterios().getClienteElegido().getEmpresa());
			}
			else
				codCliente=null;
			if(owner.getTabCriterios().getLugar_check().isSelected() && owner.getTabCriterios().getLugarElegido()!=null){
				codLugar= owner.getTabCriterios().getLugarElegido().getCodigo();
				
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Lugar: "+owner.getTabCriterios().getLugarElegido().getNombreLugar());
			}
			else
				codLugar=null;
			if(owner.getTabCriterios().getEstado_check().isSelected()){
				estado= (String)owner.getTabCriterios().getEstados().getSelectedItem();
				
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Estado: "+estado);
			}
			else
				estado=null;
			/*if(owner.getTabCriterios().getVendedores_check().isSelected()){
				codVendedores=owner.getTabCriterios().getListaVendedores().searchForeignsArray();
				
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Vendedores: ");
				for(int i=0; i<owner.getTabCriterios().getListaVendedores().getSelectedValues().length;i++){
					if(i!=0)
						jTable1.setComentariosXLS(jTable1.getComentariosXLS()+",");
					jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" "+(String)owner.getTabCriterios().getListaVendedores().getSelectedValues()[i]);
				}
			}
			else*/
				codVendedores =null;
			/*if(owner.getTabCriterios().getCondiciones_check().isSelected()){
				codCondPago = owner.getTabCriterios().getCondicionesPago().searchForeign();
				
				jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Cond. de pago: "+(String)owner.getTabCriterios().getCondicionesPago().getSelectedItem());
			}
			else*/
				codCondPago=null;
			/*if(owner.getTabCriterios().getServicios_check().isSelected()){
				codServicio=owner.getTabCriterios().getServicioElegido();
				
				//jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Servicio: "+jLabel7.getText());
			}
			else*/
				codServicio= null;		

		}
		private void buscarReportes(){
			try {
				Object[] presupuestos = null;
				BuscadorReportesTableModel model = new BuscadorReportesTableModel();		

				setCriteria();
				presupuestos = PresupuestosManager.instance().buscarParaReportesComercial(codCliente, fechaDesde,fechaHasta,codLugar,estado, owner.getCodVendedor());
				Vendedor v = VendedorManager.instance().getVendedorById(owner.getCodVendedor());
				double tot=0;
				int cant=0;
				if(presupuestos != null){
					for(int i=0; i<presupuestos.length; i++){
						BuscadorReportesItem item = new BuscadorReportesItem();

						Object[] p = (Object[]) presupuestos;
						Object[] presupuestoDato = (Object[])p[i];
						boolean vendedorValido=false;

						/*if(codVendedores != null){
							for(int j=0;j<codVendedores.length;j++){
								if(codVendedores[j].equals((String)presupuestoDato[7])){
									vendedorValido=true;									
									break;
								}
							}
						}
						else{*/
							vendedorValido=true;
						//}
						if(vendedorValido){
							cant++;
							item.setNumeroPpto((Long)(presupuestoDato[0]));
							item.setEstado((String)presupuestoDato[4]);
							item.setVendedor(v.getApellidoYNombre());
							item.setCliente((String)presupuestoDato[1]);				
							item.setNombreEvento((String)presupuestoDato[2]);
							item.setFechaInicio((String)presupuestoDato[3]);
							item.setTotal(((Double)presupuestoDato[5]).doubleValue());
							item.setFechaCreacion((String)presupuestoDato[6]);
							item.setLugar((String)presupuestoDato[7]);
							item.setFechaFinal((String)presupuestoDato[8]);
							item.setCodLugar((String)presupuestoDato[9]);
							tot=tot+(Double)presupuestoDato[5];
							model.addRow(item);
							jLabel3.setText("Cant. registros: "+cant);
							jLabel4.setText("Total: "+getTotalFormateado(tot));
						}
					}	


				}
				jTable1.getTable().setModel(model);
				jTable1.refreshTable();
				if(model.getRowCount() <= 0){
					Util.alertMsg(Main.getVentana(), "No se encontraron presupuestos");					
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void actionPerformed(ActionEvent arg0) {

				jScrollPane1.setViewportView(jTable1.getTable());
				buscarReportes();
			}
		}
	
	public String getTotalFormateado(double tot) {
		return getCurrencyFormat().format(tot);
	}
	
	private NumberFormat getCurrencyFormat() {
		NumberFormat currencyFormat;
		Locale l = new Locale("es","AR");
		currencyFormat = NumberFormat.getCurrencyInstance(l);
		return currencyFormat;
	}
	private class PopUpMenuImprimirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					try {
						if(jTable1.getSelectedItem() != null)
							if(Util.confirm(Main.getVentana(), "Seguro desea Desconfirmar el evento?")){
								//PresupuestosManager.instance().setDesConfirmado(jTable1.getSelectedItem().getNumeroPpto().toString() , "6");

								if(jTable1.getSelectedItem().getEstado().equals("Orden de Servicio") || 
										jTable1.getSelectedItem().getEstado().equals("Orden de Facturacion") ||
										jTable1.getSelectedItem().getEstado().equals("Facturado") || 
										jTable1.getSelectedItem().getEstado().equals("Cobrado")){
									PresupuestosManager.instance().setDesConfirmado(jTable1.getSelectedItem().getNumeroPpto().toString() , "1");
									if(Util.confirm(Main.getVentana(), "Enviar OS desconfirmada?")){
										PantallaEmergenteOS p = new PantallaEmergenteOS(Main.getVentana(),true);
										p.setVisible(true);
										if(p.getDestinatarioElegido() != null){
											OsDesconfirmadaReport.instance().savePdfFile(jTable1.getSelectedItem().getNumeroPpto());				   	 			
											if(OsDesconfirmadaReport.instance().sendOSByEmail2(jTable1.getSelectedItem().getNumeroPpto(), convertDateFormat(jTable1.getSelectedItem().getFechaInicio()), convertDateFormat(jTable1.getSelectedItem().getFechaFinal()), 
													jTable1.getSelectedItem().getNombreEvento(),"1", jTable1.getSelectedItem().getCodLugar(),p.getDestinatarioElegido().getEmail())){
												Util.alertMsg(Main.getVentana(),"Se envió con éxito la orden de servicio desconfirmada nro "+jTable1.getSelectedItem().getNumeroPpto());															
											}
											else Util.alertMsg(Main.getVentana(),"Se ha producido un error al enviar la orden de servicio desconfirmada");
										}
									}
								}

							}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});	
		}
		public String convertDateFormat(String date){
	    	Date d=null;
			try {
				d = DateConverter.convertStringToDate(date, "yyyy-MM-dd");
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    	return DateConverter.convertDateToString(d, "dd-MM-yyyy");
	    }
		
	}
	private class TabalMouseListener implements MouseListener{
		
		private JPopupMenu popup;
		
		public TabalMouseListener() {
			popup = new JPopupMenu();
			JMenuItem menuItem = new JMenuItem("Desconfirmar el presupuesto");
			menuItem.setIcon(new javax.swing.ImageIcon(Main.class.getResource("imagenes/arrow_rotate_anticlockwise.png")));
			menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
		    menuItem.addActionListener(new PopUpMenuImprimirActionListener());
		    popup.add(menuItem);
		}

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent e) {
						
		}

		public void mouseReleased(MouseEvent e) {
			if (e.isPopupTrigger()) {
				int rowselected =jTable1.getTable().rowAtPoint(new Point(e.getX(), e.getY()));				
				if(jTable1.getTable().isRowSelected(rowselected)){
					jTable1.getTable().getSelectionModel().setSelectionInterval(rowselected, rowselected);
					popup.show(e.getComponent(),
		                       e.getX(), e.getY());
				}	            
	        }
			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
