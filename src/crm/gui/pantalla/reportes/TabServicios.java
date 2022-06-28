package crm.gui.pantalla.reportes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JScrollPane;

import com.lowagie.tools.plugins.Txt2Pdf;

import crm.client.managers.PresupuestosManager;
import crm.client.util.DateConverter;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.JXDatePicker;
import crm.gui.pantalla.BuscadorServicios;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesItem;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesServiciosItem;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesServiciosTableModel;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesServiciosTableRender;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesTableModel;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesTableRender;

public class TabServicios {
	private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;

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
    private int tablaActiva;
    
    private BuscadorReportesServiciosTableRender jTable2;
    ReportBuilder reportBuilder;
    
    private BuscadorReportes owner;
    
    public TabServicios(BuscadorReportes o){

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
    
    jTable2 = new BuscadorReportesServiciosTableRender();
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
                        .addComponent(jButton4)))
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

			if(owner.getTabCriterios().getServicioElegido() != null)
				reportBuilder.viewReportServicios((BuscadorReportesServiciosTableModel)jTable2.getTable().getModel(), "Servicio seleccionado: "+owner.getTabCriterios().getJLabel7().getText());
			else
				reportBuilder.viewReportServicios((BuscadorReportesServiciosTableModel)jTable2.getTable().getModel(), "Servicio seleccionado: Subcontratados");

		}

	}


	private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			owner.dispose();			
		}

	}

	private class ExportarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {

				jTable2.openSavePopUp();
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
		private String codFamilia;

		private void setCriteria(){

			fechaDesde = DateConverter.convertDateToString(jTextField1.getDate(), "yyyy-MM-dd");
			fechaHasta = DateConverter.convertDateToString(jTextField2.getDate(), "yyyy-MM-dd");
			
			jTable2.setComentariosXLS("Fecha desde "+fechaDesde+" hasta "+fechaHasta);

			if(owner.getTabCriterios().getCliente_check().isSelected() && owner.getTabCriterios().getClienteElegido() != null){
				codCliente = owner.getTabCriterios().getClienteElegido().getCodigo();
				
				jTable2.setComentariosXLS(jTable2.getComentariosXLS()+" // Cliente: "+owner.getTabCriterios().getClienteElegido().getEmpresa());
			}
			else
				codCliente=null;
			if(owner.getTabCriterios().getLugar_check().isSelected() && owner.getTabCriterios().getLugarElegido()!=null){
				codLugar= owner.getTabCriterios().getLugarElegido().getCodigo();
				
				jTable2.setComentariosXLS(jTable2.getComentariosXLS()+" // Lugar: "+owner.getTabCriterios().getLugarElegido().getNombreLugar());
			}
			else
				codLugar=null;
			if(owner.getTabCriterios().getEstado_check().isSelected()){
				estado= (String)owner.getTabCriterios().getEstados().getSelectedItem();
				
				jTable2.setComentariosXLS(jTable2.getComentariosXLS()+" // Estado: "+estado);
			}
			else
				estado=null;
			if(owner.getTabCriterios().getVendedores_check().isSelected()){
				codVendedores=owner.getTabCriterios().getListaVendedores().searchForeignsArray();
				
				jTable2.setComentariosXLS(jTable2.getComentariosXLS()+" // Vendedores: ");
				for(int i=0; i<owner.getTabCriterios().getListaVendedores().getSelectedValues().length;i++){
					if(i!=0)
						jTable2.setComentariosXLS(jTable2.getComentariosXLS()+",");
					jTable2.setComentariosXLS(jTable2.getComentariosXLS()+" "+(String)owner.getTabCriterios().getListaVendedores().getSelectedValues()[i]);
				}
			}
			else
				codVendedores =null;
			if(owner.getTabCriterios().getCondiciones_check().isSelected()){
				codCondPago = owner.getTabCriterios().getCondicionesPago().searchForeign();
				
				jTable2.setComentariosXLS(jTable2.getComentariosXLS()+" // Cond. de pago: "+(String)owner.getTabCriterios().getCondicionesPago().getSelectedItem());
			}
			else
				codCondPago=null;
			if(owner.getTabCriterios().getServicios_check().isSelected()){
				codServicio=owner.getTabCriterios().getServicioElegido();
				jTable2.setComentariosXLS(jTable2.getComentariosXLS()+" // Servicio: "+(String)owner.getTabCriterios().getJLabel7().getText());
			}
			else
				codServicio= null;		
			if(owner.getTabCriterios().getFamilia_check().isSelected()){
				codFamilia=owner.getTabCriterios().getFamilia_combo().searchForeign();
				jTable2.setComentariosXLS(jTable2.getComentariosXLS()+" // Familia: "+(String)owner.getTabCriterios().getFamilia_combo().getSelectedItem());
			}
			else
				codFamilia= null;	

		}
		private void buscarReportesXServicios(){
    		try {
				Object[] presupuestos = null;
				BuscadorReportesServiciosTableModel model = new BuscadorReportesServiciosTableModel();		
				
				setCriteria();
				presupuestos = PresupuestosManager.instance().buscarParaReportesDeServicios(codCliente,fechaDesde,fechaHasta,codLugar,codCondPago,estado,owner.getTabCriterios().getSubcont_check().isSelected(),codServicio,owner.getTabCriterios().getTxt_nombre_sub().getText());
				
				double tot=0;
				int cant=0;
				if(presupuestos != null){
					for(int i=0; i<presupuestos.length; i++){
						BuscadorReportesServiciosItem item = new BuscadorReportesServiciosItem();

						Object[] p = (Object[]) presupuestos;
						Object[] presupuestoDato = (Object[])p[i];
						boolean vendedorValido=false;

						if(codVendedores != null){
							for(int j=0;j<codVendedores.length;j++){
								if(codVendedores[j].equals((String)presupuestoDato[11])){
									vendedorValido=true;									
									break;
								}
							}
						}
						else{
							vendedorValido=true;
						}
						if(vendedorValido){
							cant++;
							item.setNumeroPpto((Long)(presupuestoDato[3]));
							if("Subcontratado".equals((String)presupuestoDato[2])){
								item.setServicio((String)presupuestoDato[2]+" - "+(String)presupuestoDato[13]);
							}else
								item.setServicio((String)presupuestoDato[2]);
							item.setCantidad((Integer)presupuestoDato[0]);
							item.setDias((Integer)presupuestoDato[1]);							
							item.setCliente((String)presupuestoDato[12]);		
							item.setVendedor((String)presupuestoDato[5]);
							item.setFechaFin((String)presupuestoDato[15]);	

							item.setTotalCompra((Double)presupuestoDato[14]);

							item.setNombreEvento((String)presupuestoDato[4]);
							item.setFechaInicio((String)presupuestoDato[6]);
							item.setTotalEvento((Double)presupuestoDato[9]);
							item.setDescuento((Integer)presupuestoDato[8]);
							item.setTotalServicio((Double)presupuestoDato[7]);
							tot=tot+(Double)presupuestoDato[7];
							model.addRow(item);
							jLabel3.setText("Cant. registros: "+cant);
							jLabel4.setText("Total: "+getTotalFormateado(tot));
						}
					}	
					
					
				}
				jTable2.getTable().setModel(model);
				jTable2.refreshTable();
				if(model.getRowCount() <= 0){
					Util.alertMsg(Main.getVentana(), "No se encontraron registros");					
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		
		private void buscarReportesXFamilia(){
    		try {
				Object[] presupuestos = null;
				BuscadorReportesServiciosTableModel model = new BuscadorReportesServiciosTableModel();		
				
				setCriteria();
				presupuestos = PresupuestosManager.instance().buscarParaReportesDeFamServicios(codCliente,fechaDesde,fechaHasta,codLugar,codCondPago,estado,owner.getTabCriterios().getSubcont_check().isSelected(),codFamilia);
				
				double tot=0;
				int cant=0;
				if(presupuestos != null){
					for(int i=0; i<presupuestos.length; i++){
						BuscadorReportesServiciosItem item = new BuscadorReportesServiciosItem();

						Object[] p = (Object[]) presupuestos;
						Object[] presupuestoDato = (Object[])p[i];
						boolean vendedorValido=false;

						if(codVendedores != null){
							for(int j=0;j<codVendedores.length;j++){
								if(codVendedores[j].equals((String)presupuestoDato[11])){
									vendedorValido=true;									
									break;
								}
							}
						}
						else{
							vendedorValido=true;
						}
						if(vendedorValido){
							cant++;
							item.setNumeroPpto((Long)(presupuestoDato[3]));
							if("Subcontratado".equals((String)presupuestoDato[2])){
								item.setServicio((String)presupuestoDato[2]+" - "+(String)presupuestoDato[13]);
							}else
								item.setServicio((String)presupuestoDato[2]);
							item.setCantidad((Integer)presupuestoDato[0]);
							item.setDias((Integer)presupuestoDato[1]);							
							item.setCliente((String)presupuestoDato[12]);		
							item.setVendedor((String)presupuestoDato[5]);
							item.setFechaFin((String)presupuestoDato[15]);	

							item.setTotalCompra((Double)presupuestoDato[14]);

							item.setNombreEvento((String)presupuestoDato[4]);
							item.setFechaInicio((String)presupuestoDato[6]);
							item.setTotalEvento((Double)presupuestoDato[9]);
							item.setDescuento((Integer)presupuestoDato[8]);
							item.setTotalServicio((Double)presupuestoDato[7]);
							tot=tot+(Double)presupuestoDato[7];
							model.addRow(item);
							jLabel3.setText("Cant. registros: "+cant);
							jLabel4.setText("Total: "+getTotalFormateado(tot));
						}
					}	
					
					
				}
				jTable2.getTable().setModel(model);
				jTable2.refreshTable();
				if(model.getRowCount() <= 0){
					Util.alertMsg(Main.getVentana(), "No se encontraron registros");					
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}

		public void actionPerformed(ActionEvent arg0) {
			if(owner.getTabCriterios().getServicios_check().isSelected() || owner.getTabCriterios().getSubcont_check().isSelected() && !owner.getTabCriterios().getFamilia_check().isSelected()){
				jScrollPane1.setViewportView(jTable2.getTable());
				buscarReportesXServicios();
			}
			else if(!owner.getTabCriterios().getServicios_check().isSelected() && !owner.getTabCriterios().getSubcont_check().isSelected() && owner.getTabCriterios().getFamilia_check().isSelected()){
				jScrollPane1.setViewportView(jTable2.getTable());
				buscarReportesXFamilia();
			}
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
}
