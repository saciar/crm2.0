package crm.gui.pantalla.reportes;

import java.awt.Dimension;
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
import crm.gui.pantalla.PantallaEmergente;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.LugarEvento;

public class BuscadorReportes extends PantallaEmergente{


    private javax.swing.JTabbedPane jTabbedPane1; 

    private javax.swing.JButton jButton8;
    private javax.swing.JPanel jPanel8;
    
    private PanelImagen panel;
	
    ReportBuilder reportBuilder;
    
    private TabPresupuestos tab1;
    private TabCriterios tab2;
    private TabRentabilidad tab3;
    private TabServicios tab4;
	public BuscadorReportes (Frame owner){
		super("Buscador de reportes",owner);
		this.setMinimumSize(new Dimension(1024,768));
		this.setMaximumSize(new Dimension(1024,768));
		this.setPreferredSize(new Dimension(1024,768));
		this.setLocationRelativeTo(null);
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

        tab2=new TabCriterios(this);
        tab2.init_components();
        
        jTabbedPane1.addTab("Filtros", tab2.getJPanel2());

        tab1 = new TabPresupuestos(this);
        tab1.init_components();
        
        jTabbedPane1.addTab("Presupuestos", tab1.getJPanel1());        

        tab4 = new TabServicios(this);
        tab4.init_components();
        
        jTabbedPane1.addTab("Servicios", tab4.getJPanel1());
        
        tab3 = new TabRentabilidad(this);
        tab3.init_components();
        
        jTabbedPane1.addTab("Rentabilidad", tab3.getJPanel1());
        
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
	
	public TabCriterios getTabCriterios(){
		return tab2;
	}
	
	public TabPresupuestos getTabPresupuesto(){
		return tab1;
	}
	
/*-----------------------------------------------------------------------------------------------------------------*/

	private class ListaPreciosActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			reportBuilder.previewListaPreciosReport(DateConverter.convertDateToString(new Date(), "yyyy-MM-dd"));			
		}
		
	}
}
