package crm.gui.pantalla.reportes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import crm.gui.Main;
import crm.gui.components.ABMCondicionesPagoComboBox;
import crm.gui.components.ABMFamiliasComboBox;
import crm.gui.components.ABMVendedoresMultiBox;
import crm.gui.pantalla.BuscadorClientes;
import crm.gui.pantalla.BuscadorLugarEvento;
import crm.gui.pantalla.BuscadorServicios;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.LugarEvento;

public class TabCriterios {

    private javax.swing.JCheckBox vendedores_check;
    private javax.swing.JCheckBox lugar_check;
    private javax.swing.JCheckBox cliente_check;
    private javax.swing.JCheckBox estado_check;
    private javax.swing.JCheckBox condiciones_check;
    private JComboBox estados;
    private ABMCondicionesPagoComboBox condicionesPago;

    private ABMVendedoresMultiBox listaVendedores;

    private javax.swing.JPanel jPanel2;

    private javax.swing.JPanel jPanel6;

    private javax.swing.JScrollPane jScrollPane2;

    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox servicios_check;
    
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;    
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox subcont_check;
    private javax.swing.JCheckBox familia_check;
    private javax.swing.JLabel jLabel7;
	private ABMFamiliasComboBox cmb_familias;
	private javax.swing.JTextField txt_nombre_sub;
    
    ReportBuilder reportBuilder;
	
    private BuscadorReportes owner;
    
	public TabCriterios(BuscadorReportes o){
		owner =o;
		reportBuilder = new ReportBuilder();
		
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        vendedores_check = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaVendedores = new ABMVendedoresMultiBox();
        jSeparator1 = new javax.swing.JSeparator();
        lugar_check = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        cliente_check = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        estado_check = new javax.swing.JCheckBox();
        estados = new JComboBox();
        jSeparator4 = new javax.swing.JSeparator();
        condiciones_check = new javax.swing.JCheckBox();
        condicionesPago = new ABMCondicionesPagoComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        servicios_check = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        subcont_check = new javax.swing.JCheckBox();
        familia_check = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        cmb_familias = new ABMFamiliasComboBox();
        txt_nombre_sub = new JTextField();
	}
	
	public javax.swing.JPanel getJPanel2() {
		return jPanel2;
	}

	public void setJPanel2(javax.swing.JPanel panel2) {
		jPanel2 = panel2;
	}

	public void init_components(){
		jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        vendedores_check.setText("Vendedores");

        listaVendedores.loadItemsAllVendedores();
        jScrollPane2.setViewportView(listaVendedores);

        lugar_check.setText("Lugar");

        cliente_check.setText("Cliente");

        jButton5.setText("Buscar Lugar");
        jButton5.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));

        jButton6.setText("Buscar Cliente");
        jButton6.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));

        estado_check.setText("Estado");

        estados.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Confirmado", "Pendiente", "Orden de Servicio","Orden de Facturacion","Facturado","Cobrado","Cancelado", "Rechazado" }));

        condiciones_check.setText("Condiciones de Pago");

        condicionesPago.loadItems();        

        jLabel5.setText("");

        jLabel6.setText("");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(vendedores_check)
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lugar_check)
                        .addGap(88, 88, 88)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cliente_check)
                        .addGap(81, 81, 81)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(estado_check)
                        .addGap(82, 82, 82)
                        .addComponent(estados, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(condiciones_check)
                        .addGap(18, 18, 18)
                        .addComponent(condicionesPago, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vendedores_check)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lugar_check)
                    .addComponent(jButton5)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cliente_check)
                    .addComponent(jButton6)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estado_check)
                    .addComponent(estados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(condiciones_check)
                    .addComponent(condicionesPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Servicios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        servicios_check.setText("Servicios");

        jButton7.setText("Buscar servicios");
        jButton7.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));

        subcont_check.setText("Subcontratados");
        familia_check.setText("Familia de servicios");
        
        cmb_familias.loadItems();
        
        jLabel7.setText("");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(servicios_check)
                        .addGap(75, 75, 75)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
                    //.addComponent(subcont_check)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(subcont_check)
                        .addGap(10, 10, 10)
                        .addComponent(txt_nombre_sub, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        )
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)                        
                        .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(familia_check)
                        .addGap(10, 10, 10)
                        .addComponent(cmb_familias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        ))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(servicios_check)
                    .addComponent(jButton7)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                //.addComponent(subcont_check)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subcont_check)
                    .addComponent(txt_nombre_sub))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(familia_check)
                    .addComponent(cmb_familias))
                .addContainerGap(189, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        createListeners();
	}
	
	private void createListeners(){

		jButton5.addActionListener(new BuscarLugaresActionListener());
		jButton6.addActionListener(new BuscarClienteActionListener());
		jButton7.addActionListener(new BuscarServicioActionListener());

	}
	
	public URL getUrlImagen(String imagen){

		URL url =Main.class.getResource("imagenes/"+imagen); 

		return url;
	}
	
	private LugarEvento lugarElegido;
	
	public LugarEvento getLugarElegido() {
		return lugarElegido;
	}

	public void setLugarElegido(LugarEvento lugarElegido) {
		this.lugarElegido = lugarElegido;
	}
	private BuscadorLugarEvento buscador;
    private class BuscarLugaresActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (buscador == null){
				buscador = new BuscadorLugarEvento(Main.getVentana());
				buscador.init();
				buscador.initComponent();
				buscador.setLugarAnterior(lugarElegido);
				buscador.setBuscadorReportes(owner);
			}
			buscador.setVisible(true);
			if (lugarElegido != null) {
				jLabel5.setText(lugarElegido.getNombreLugar());
				//panel.updateUI();
			}
		}
    	
    }
    
    private Cliente clienteElegido;

	public Cliente getClienteElegido() {
		return clienteElegido;
	}

	public void setClienteElegido(Cliente clienteElegido) {
		this.clienteElegido = clienteElegido;
	}
    
	private BuscadorClientes buscadorCliente;
    private class BuscarClienteActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(buscadorCliente == null){
				buscadorCliente = new BuscadorClientes(Main.getVentana());
				buscadorCliente.init();
				buscadorCliente.initComponent();
				buscadorCliente.setBuscadorReportes(owner);
				
			}
			buscadorCliente.setVisible(true);			
			if (clienteElegido != null) {
				jLabel6.setText(clienteElegido.getEmpresa());
				jPanel6.updateUI();
			}
			
		}
    	
    }
    
    private String codServicioElegido;

	public String getServicioElegido() {
		return codServicioElegido;
	}

	public void setServicioElegido(String servicioElegido) {
		this.codServicioElegido = servicioElegido;
	}
    
    private BuscadorServicios buscadorServicio; 
	private class BuscarServicioActionListener implements ActionListener {
		public void actionPerformed (ActionEvent evt) {
			if (buscadorServicio == null){
				buscadorServicio = new BuscadorServicios(Main.getVentana(), owner);
				buscadorServicio.init();
				buscadorServicio.initComponent();
			}
			buscadorServicio.setVisible(true);
			codServicioElegido=buscadorServicio.getServicioElegido().getServicioCodigo();
			if(buscadorServicio.getServicioElegido() != null){
				jLabel7.setText(buscadorServicio.getServicioElegido().getServicio());				
			}
		}
	}
	public javax.swing.JCheckBox getVendedores_check() {
		return vendedores_check;
	}

	public javax.swing.JCheckBox getLugar_check() {
		return lugar_check;
	}

	public javax.swing.JCheckBox getCliente_check() {
		return cliente_check;
	}

	public javax.swing.JCheckBox getEstado_check() {
		return estado_check;
	}

	public javax.swing.JCheckBox getCondiciones_check() {
		return condiciones_check;
	}

	public javax.swing.JCheckBox getServicios_check() {
		return servicios_check;
	}

	public javax.swing.JCheckBox getSubcont_check() {
		return subcont_check;
	}
	
	public javax.swing.JCheckBox getFamilia_check() {
		return familia_check;
	}
	
	public ABMFamiliasComboBox getFamilia_combo(){
		return cmb_familias;
	}

	public ABMCondicionesPagoComboBox getCondicionesPago() {
		return condicionesPago;
	}

	public ABMVendedoresMultiBox getListaVendedores() {
		return listaVendedores;
	}

	public JComboBox getEstados() {
		return estados;
	}

	public javax.swing.JLabel getJLabel7() {
		return jLabel7;
	}

	public javax.swing.JTextField getTxt_nombre_sub() {
		return txt_nombre_sub;
	}
	
	
	
}
