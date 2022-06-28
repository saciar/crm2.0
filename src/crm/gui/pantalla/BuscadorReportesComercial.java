package crm.gui.pantalla;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

import crm.client.util.DateConverter;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.reportes.ReportBuilder;
import crm.gui.pantalla.reportes.TabCriterios;
import crm.gui.pantalla.reportes.TabCriteriosComercial;
import crm.gui.pantalla.reportes.TabPresupuestos;
import crm.gui.pantalla.reportes.TabPresupuestosComercial;
import crm.gui.pantalla.reportes.TabRentabilidad;
import crm.gui.pantalla.reportes.TabServicios;

public class BuscadorReportesComercial extends PantallaEmergente{
	 private javax.swing.JTabbedPane jTabbedPane1;

	    private javax.swing.JButton jButton8;
	    private javax.swing.JPanel jPanel8;
	    
	    private PanelImagen panel;
		
	    ReportBuilder reportBuilder;
	    
	    private TabPresupuestosComercial tab1;
	    private TabCriteriosComercial tab2;
	    private TabRentabilidad tab3;
	    private TabServicios tab4;
	    
	    private String codVendedor;
	    
		public BuscadorReportesComercial (Frame owner, String codVend){
			super("Buscador de reportes",owner);
			codVendedor=codVend;
			this.setModal(false);
			this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
	        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	        this.setEnabled(true); 
	        //createMenuBar();
		}

		public void init(){
				//**************Crecion de panel contenedor con imagen de fondo**********************
			reportBuilder = new ReportBuilder();
			try{
				panel = new PanelImagen("WorldLight.jpg");
			}
			catch(Exception e){
				System.out.println("Error");
				panel = new PanelImagen();
			}

			jTabbedPane1 = new javax.swing.JTabbedPane();
	        jPanel8 = new javax.swing.JPanel();
	        jButton8 = new javax.swing.JButton();

	        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

	        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
	        jTabbedPane1.setPreferredSize(new java.awt.Dimension(0, 0));

	        tab2=new TabCriteriosComercial(this);
	        tab2.init_components();
	        
	        jTabbedPane1.addTab("Filtros", tab2.getJPanel2());

	        tab1 = new TabPresupuestosComercial(this);
	        tab1.init_components();
	        
	        jTabbedPane1.addTab("Presupuestos", tab1.getJPanel1());        

	        /*tab4 = new TabServicios(this);
	        tab4.init_components();
	        
	        jTabbedPane1.addTab("Servicios", tab4.getJPanel1());
	        
	        tab3 = new TabRentabilidad(this);
	        tab3.init_components();
	        
	        jTabbedPane1.addTab("Rentabilidad", tab3.getJPanel1());*/
	        
	        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Otros reportes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

	        jButton8.setText("Lista de Precios");

	        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
	        jPanel8.setLayout(jPanel8Layout);
	        jPanel8Layout.setHorizontalGroup(
	            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel8Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jButton8)
	                .addContainerGap(856, Short.MAX_VALUE))
	        );
	        jPanel8Layout.setVerticalGroup(
	            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel8Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jButton8)
	                .addContainerGap(610, Short.MAX_VALUE))
	        );

	        jTabbedPane1.addTab("Lista de Precios", jPanel8);
	        
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1994, Short.MAX_VALUE)
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
	        );
	        
	        getContentPane().add(panel);
	        pack();	
	        createListeners();
			
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
		
		private void createListeners(){
			
			jButton8.addActionListener(new ListaPreciosActionListener());
		}
		
		public TabCriteriosComercial getTabCriterios(){
			return tab2;
		}
		
		public TabPresupuestosComercial getTabPresupuesto(){
			return tab1;
		}
		
		public String getCodVendedor(){
			return codVendedor;
		}
		
	/*-----------------------------------------------------------------------------------------------------------------*/

		private class ListaPreciosActionListener implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				reportBuilder.previewListaPreciosReport(DateConverter.convertDateToString(new Date(), "yyyy-MM-dd"));			
			}
			
		}
}
