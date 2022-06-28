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
import crm.gui.pantalla.BuscadorReportesComercial;
import crm.gui.pantalla.BuscadorServicios;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.LugarEvento;

public class TabCriteriosComercial {

    private javax.swing.JCheckBox lugar_check;
    private javax.swing.JCheckBox cliente_check;
    private javax.swing.JCheckBox estado_check;

    private JComboBox estados;

    private javax.swing.JPanel jPanel2;

    private javax.swing.JPanel jPanel6;
    
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
 
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;    
    
    ReportBuilder reportBuilder;
	
    private BuscadorReportesComercial owner;
    
	public TabCriteriosComercial(BuscadorReportesComercial o){
		owner =o;
		reportBuilder = new ReportBuilder();		
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lugar_check = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        cliente_check = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        estado_check = new javax.swing.JCheckBox();
        estados = new JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

	}
	
	public javax.swing.JPanel getJPanel2() {
		return jPanel2;
	}

	public void setJPanel2(javax.swing.JPanel panel2) {
		jPanel2 = panel2;
	}

	public void init_components(){
		jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        lugar_check.setText("Lugar");

        cliente_check.setText("Cliente");

        jButton5.setText("Buscar Lugar");
        jButton5.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));

        jButton6.setText("Buscar Cliente");
        jButton6.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_magnify.png")));

        estado_check.setText("Estado");

        estados.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Confirmado", "Pendiente", "Orden de Servicio","Orden de Facturacion","Facturado","Cobrado","Cancelado", "Rechazado" }));

        jLabel5.setText("");

        jLabel6.setText("");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        /*.addContainerGap()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(condiciones_check)
                        .addGap(18, 18, 18)
                        .addComponent(condicionesPago, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)*/))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(8, 8, 8)
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
                /*.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(condiciones_check)
                    .addComponent(condicionesPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))*/
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    //.addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                /*.addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()*/)
        );
        
        createListeners();
	}
	
	private void createListeners(){

		jButton5.addActionListener(new BuscarLugaresActionListener());
		jButton6.addActionListener(new BuscarClienteActionListener());

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
				buscador.setBuscadorReportesCom(owner);
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
    
    private class BuscarClienteActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			BuscadorClientes buscador = new BuscadorClientes(Main.getVentana());
			buscador.init();
			buscador.initComponent();
			buscador.setVisible(true);
			clienteElegido = buscador.getSelectedClient();
			if (clienteElegido != null) {
				jLabel6.setText(clienteElegido.getEmpresa());
				//panel.updateUI();
			}
			
		}
    	
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

	public JComboBox getEstados() {
		return estados;
	}

}
