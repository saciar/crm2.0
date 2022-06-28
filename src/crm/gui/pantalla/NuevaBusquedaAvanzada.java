package crm.gui.pantalla;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.PresupuestosManager;
import crm.client.util.DateConverter;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMEstadosComboBox;
import crm.gui.components.ABMTiposEventosComboBox;
import crm.gui.components.ABMUnidadesComercialesComboBox;
import crm.gui.components.ABMVendedoresComboBox;
import crm.gui.components.JXDatePicker;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.solapa.LugarEventoPanel;
import crm.gui.tablerenderer.buscadorPptos.BuscadorPptosItem;
import crm.gui.tablerenderer.buscadorPptos.BuscadorPptosTableModel;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.EstadoEvento;
import crm.libraries.abm.entities.LugarEvento;
import crm.libraries.abm.entities.TipoEvento;
import crm.libraries.abm.entities.UnidadComercial;
import crm.libraries.abm.entities.Vendedor;
import crm.libraries.util.MessageUtil;

public class NuevaBusquedaAvanzada extends JDialog{

	private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_cliente_evt;
    private javax.swing.JButton btn_cliente_fact;
    //private javax.swing.JButton btn_estado;
    private ABMEstadosComboBox cmb_estado;
    private javax.swing.JButton btn_lugar;
    //private javax.swing.JButton btn_tipo_evt;
    private ABMTiposEventosComboBox cmb_tipo_evt;
    private javax.swing.JCheckBox check_cliente_evt;
    private javax.swing.JCheckBox check_cliente_fact;
    private javax.swing.JCheckBox check_estado;
    private javax.swing.JCheckBox check_fechas;
    private javax.swing.JCheckBox check_lugar;
    private javax.swing.JCheckBox check_nombre_evt;
    private javax.swing.JCheckBox check_tipo_evt;
    private javax.swing.JCheckBox check_unidad;
    private javax.swing.JCheckBox check_vendedor;
    private JXDatePicker dt_fecha_desde;
    private JXDatePicker dt_fecha_hasta;
    //private javax.swing.JButton btn_vendedor;
    //private javax.swing.JButton btn_unidad;
    private ABMVendedoresComboBox cmb_vendedor;
    private ABMUnidadesComercialesComboBox cmb_unidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbl_cliente_evt;
    private javax.swing.JLabel lbl_cliente_fact;
    private javax.swing.JLabel lbl_estado;
    private javax.swing.JLabel lbl_lugar;
    private javax.swing.JLabel lbl_tipo_evt;
    private javax.swing.JLabel lbl_unidad;
    private javax.swing.JLabel lbl_vendedor;
    private javax.swing.JTextField tx_nombre_evt;
    
    private PanelImagen panel;
    private PantallaEmergente owner;
    
    private Cliente clienteFacElegido;
	private LugarEvento lugarElegido;
	private Cliente clienteElegido;
    
	private JButton m_btnRecargarEstado;
	private JButton m_btnRecargarTipoEvt;
	private JButton m_btnRecargarVendedores;
	private JButton m_btnRecargarUC;
	
    public NuevaBusquedaAvanzada(Frame owner){		
		//super("Búsqueda avanzada de presupuestos",owner);
    	
		//setModal(true);
    	super(owner,false);
	}
    
    public NuevaBusquedaAvanzada(BuscadorPptos owner){		
		//super("Búsqueda avanzada de presupuestos",owner);
    	
		//setModal(true);
    	super(owner,false);
	}
    
    public void setOwner(PantallaEmergente o){
    	if(o.getClass().equals(BuscadorGastos.class))
    		owner=(BuscadorGastos)o;
    	else if(o.getClass().equals(BuscadorPptos.class))
    		owner=(BuscadorPptos)o;
    }
    
    public void initComponents() {
    	
    	try{
			panel = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
    	
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        check_cliente_evt = new javax.swing.JCheckBox();
        btn_cliente_evt = new javax.swing.JButton();
        check_cliente_fact = new javax.swing.JCheckBox();
        btn_cliente_fact = new javax.swing.JButton();
        check_fechas = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        check_lugar = new javax.swing.JCheckBox();
        dt_fecha_desde = new JXDatePicker();
        dt_fecha_hasta = new JXDatePicker();
        btn_lugar = new javax.swing.JButton();
        check_vendedor = new javax.swing.JCheckBox();
        lbl_cliente_evt = new javax.swing.JLabel();
        lbl_cliente_fact = new javax.swing.JLabel();
        lbl_lugar = new javax.swing.JLabel();
        cmb_vendedor = new ABMVendedoresComboBox();
        lbl_vendedor = new javax.swing.JLabel();
        check_unidad = new javax.swing.JCheckBox();
        cmb_unidad= new ABMUnidadesComercialesComboBox();
        lbl_unidad = new javax.swing.JLabel();
        check_estado = new javax.swing.JCheckBox();
        cmb_estado = new ABMEstadosComboBox();
        lbl_estado = new javax.swing.JLabel();
        check_tipo_evt = new javax.swing.JCheckBox();
        cmb_tipo_evt= new ABMTiposEventosComboBox();
        lbl_tipo_evt = new javax.swing.JLabel();
        check_nombre_evt = new javax.swing.JCheckBox();
        tx_nombre_evt = new javax.swing.JFormattedTextField();
        jSeparator2 = new javax.swing.JSeparator();
        btn_cancelar = new javax.swing.JButton();
        btn_buscar = new javax.swing.JButton();

        m_btnRecargarEstado = new JButton();
        m_btnRecargarTipoEvt = new JButton();
        m_btnRecargarUC = new JButton();
        m_btnRecargarVendedores = new JButton();
        
        m_btnRecargarEstado.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        m_btnRecargarTipoEvt.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        m_btnRecargarUC.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        m_btnRecargarVendedores.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        
        
        jLabel1.setText("Busqueda Avanzada de Presupuestos"); // NOI18N

        check_cliente_evt.setText("Cliente del evento"); // NOI18N

        btn_cliente_evt.setText("Buscar Cliente"); // NOI18

        check_cliente_fact.setText("Cliente de facturación"); // NOI18N

        btn_cliente_fact.setText("Buscar Cliente"); // NOI18N

        check_fechas.setText("Rango de Fechas: Desde"); // NOI18N

        jLabel3.setText("hasta"); // NOI18N

        check_lugar.setText("Lugar de Evento"); // NOI18N

        btn_lugar.setText("Buscar Lugar de evento"); // NOI18N

        check_vendedor.setText("Vendedor"); // NOI18N
        
        cmb_vendedor.loadItems();

        check_unidad.setText("Unidad Comercial"); // NOI18N

        cmb_unidad.loadItems();

        check_estado.setText("Estado de Presupuesto"); // NOI18N

        cmb_estado.loadItems();

        check_tipo_evt.setText("Tipo de Evento"); // NOI18N

        cmb_tipo_evt.loadItems();

        check_nombre_evt.setText("Nombre de Evento"); // NOI18N

        btn_cancelar.setText("Cancelar"); // NOI18N

        btn_buscar.setText("Buscar"); // NOI18N
        
        check_estado.setEnabled(false);
        
       // tx_nombre_evt.setEnabled(false);
        cmb_tipo_evt.setEnabled(false);
    	lbl_tipo_evt.setEnabled(false);
    	cmb_estado.setEnabled(false);
    	lbl_estado.setEnabled(false);
    	cmb_vendedor.setEnabled(false);
    	lbl_vendedor.setEnabled(false);
    	cmb_unidad.setEnabled(false);
    	lbl_unidad.setEnabled(false);
    	btn_lugar.setEnabled(false);
    	lbl_lugar.setEnabled(false);
    	dt_fecha_desde.setEnabled(false);
    	dt_fecha_hasta.setEnabled(false);
    	btn_cliente_fact.setEnabled(false);
    	lbl_cliente_fact.setEnabled(false);
    	btn_cliente_evt.setEnabled(false);
    	lbl_cliente_evt.setEnabled(false);
    }
    
public URL getUrlImagen(String imagen){
	    
    	URL url =Main.class.getResource("imagenes/"+imagen); 

    	return url;
    }
    
    public void initLayout(){
    	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(check_cliente_fact)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_cliente_fact)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_cliente_fact))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(check_cliente_evt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_cliente_evt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_cliente_evt))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(check_fechas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dt_fecha_desde)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dt_fecha_hasta))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(check_lugar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_lugar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_lugar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(check_vendedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmb_vendedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(m_btnRecargarVendedores)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_vendedor))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(check_unidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_unidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(m_btnRecargarUC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_unidad))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(check_estado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_estado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(m_btnRecargarEstado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_estado))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(check_tipo_evt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmb_tipo_evt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(m_btnRecargarTipoEvt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_tipo_evt))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(check_nombre_evt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tx_nombre_evt, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addComponent(jSeparator2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(346, Short.MAX_VALUE)
                .addComponent(btn_buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_cancelar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_cliente_evt)
                    .addComponent(btn_cliente_evt)
                    .addComponent(lbl_cliente_evt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_cliente_fact)
                    .addComponent(btn_cliente_fact)
                    .addComponent(lbl_cliente_fact))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_fechas)
                    .addComponent(dt_fecha_desde)
                    .addComponent(jLabel3)
                    .addComponent(dt_fecha_hasta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_lugar)
                    .addComponent(btn_lugar)
                    .addComponent(lbl_lugar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_vendedor)
                    .addComponent(cmb_vendedor)
                    .addComponent(m_btnRecargarVendedores)
                    .addComponent(lbl_vendedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_unidad)
                    .addComponent(cmb_unidad)
                    .addComponent(m_btnRecargarUC)
                    .addComponent(lbl_unidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_estado)
                    .addComponent(cmb_estado)
                    .addComponent(m_btnRecargarEstado)
                    .addComponent(lbl_estado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_tipo_evt)
                    .addComponent(cmb_tipo_evt)
                    .addComponent(m_btnRecargarTipoEvt)
                    .addComponent(lbl_tipo_evt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_nombre_evt)
                    .addComponent(tx_nombre_evt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancelar)
                    .addComponent(btn_buscar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        this.getContentPane().add(panel);
        this.pack();
        
        createListeners();
    }
    
    private void createListeners(){
    	btn_cancelar.addActionListener(new CancelarActionListener());
    	btn_buscar.addActionListener(new BuscarActionListener());
    	btn_cliente_evt.addActionListener(new BuscarClientesActionListener());
    	btn_cliente_fact.addActionListener(new BuscarClientesFactActionListener());
    	btn_lugar.addActionListener(new BuscarLugaresActionListener());
    	
    	check_cliente_evt.addChangeListener(new checkClienteChangeListener());
    	check_cliente_fact.addChangeListener(new checkClienteFactChangeListener());
    	check_estado.addChangeListener(new checkEstadoChangeListener());
    	check_fechas.addChangeListener(new checkFechasChangeListener());
    	check_lugar.addChangeListener(new checkLugarChangeListener());
    	//check_nombre_evt.addChangeListener(new checkNombreChangeListener());
    	check_tipo_evt.addChangeListener(new checkTipoEventoChangeListener());
    	check_unidad.addChangeListener(new checkUnidadChangeListener());
    	check_vendedor.addChangeListener(new checkVendedorChangeListener());
    	
    	m_btnRecargarTipoEvt.addActionListener(new recargarTipoEvento());
    	m_btnRecargarEstado.addActionListener(new recargarEstado());
    	m_btnRecargarUC.addActionListener(new recargarUC());
    	m_btnRecargarVendedores.addActionListener(new recargarVendedores());
    }
    
    public void setClienteElegido(Cliente ce, int tipo) {
    	if(tipo==0){
    	clienteElegido = ce;
		//buscador = null;
		if (clienteElegido != null) {
			lbl_cliente_evt.setText(clienteElegido.getEmpresa());
			panel.updateUI();
		}
    	}
    	else if(tipo==1){
		clienteFacElegido = ce;

		if (clienteFacElegido != null) {
			lbl_cliente_fact.setText(clienteFacElegido.getEmpresa());
			panel.updateUI();
		}
    	}
    }
    
    //***************************************************************************************************************
    
    private class CancelarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(NuevaBusquedaAvanzada.this, "¿Desea cancelar la busqueda?", "Cancelar")){
				dispose();
			}			
		}
    	
    }
    
    private class BuscarActionListener implements ActionListener{
    	
    	private String codCliente;
    	private String codClienteFact;
    	private String fechaDesde;
    	private String fechaHasta;
    	private String codLugar;
    	private String codVendedor;
    	private String codUC;
    	private String codEstado;
    	private String codTipoEvento;
    	private String nombre;
    	
    	private void setCriteria(){
    		if(check_cliente_evt.isSelected() && clienteElegido != null)
				codCliente = clienteElegido.getCodigo();
			if(check_cliente_fact.isSelected() && clienteFacElegido!= null)
				codClienteFact = clienteFacElegido.getCodigo();
			if(check_fechas.isSelected()){
				fechaDesde = DateConverter.convertDateToString(dt_fecha_desde.getDate(), "yyyy-MM-dd");
				fechaHasta = DateConverter.convertDateToString(dt_fecha_hasta.getDate(), "yyyy-MM-dd");
			}
			if(check_lugar.isSelected() && lugarElegido!=null)
				codLugar= lugarElegido.getCodigo();
			if(check_estado.isSelected() )
				codEstado= cmb_estado.searchForeign();
			if(check_vendedor.isSelected())
				codVendedor=cmb_vendedor.searchForeign();
			if(check_unidad.isSelected())
				codUC = cmb_unidad.searchForeign();
			if(check_tipo_evt.isSelected() )
				codTipoEvento= cmb_tipo_evt.searchForeign();
			if(check_nombre_evt.isSelected() && !StringUtils.isBlank(tx_nombre_evt.getText()))
				nombre=tx_nombre_evt.getText();
    	}
    	
		public void actionPerformed(ActionEvent arg0) {
			try {
				Object[] presupuestos = null;
				BuscadorPptosTableModel model = new BuscadorPptosTableModel();		
				
				setCriteria();
				presupuestos = PresupuestosManager.instance().buscar(codCliente, codClienteFact,fechaDesde,fechaHasta,codLugar,codVendedor,codUC,codEstado,codTipoEvento,nombre);
				
				if(presupuestos != null){
					for(int i=0; i<presupuestos.length; i++){
						BuscadorPptosItem item = new BuscadorPptosItem();
					
						Object[] p = (Object[]) presupuestos;
						Object[] presupuestoDato = (Object[])p[i];
					
						item.setNumeroPpto((Long)(presupuestoDato[0]));
						item.setEstado((String)presupuestoDato[5]);
						item.setVendedor((String)presupuestoDato[1]);
						item.setCliente((String)presupuestoDato[2]);				
						item.setNombreEvento((String)presupuestoDato[3]);
						item.setFechaInicio((String)presupuestoDato[4]);
						item.setUnidadAdm((String)presupuestoDato[6]);
					
						model.addRow(item, i);
					}					
				}
				if(owner.getClass().equals(BuscadorGastos.class)){
					((BuscadorGastos)owner).getTableBuscador().getTable().setModel(model);
					((BuscadorGastos)owner).getTableBuscador().refreshTable();
				}
		    	else if(owner.getClass().equals(BuscadorPptos.class)){
		    		((BuscadorPptos)owner).getTableBuscador().getTable().setModel(model);
					((BuscadorPptos)owner).getTableBuscador().refreshTable();
		    	}
				
				if(model.getRowCount() <= 0){
					Util.alertMsg(Main.getVentana(), "No se encontraron presupuestos");					
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				setVisible(false);
			}
		}
    	
    }
    
    private class BuscarClientesActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			BuscadorClientesAvanzada buscador = new BuscadorClientesAvanzada(Main.getVentana(), NuevaBusquedaAvanzada.this,0);
			buscador.init();
			buscador.initComponent();
			buscador.setVisible(true);
			
		}
		
	}
    
    private class BuscarClientesFactActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			BuscadorClientesAvanzada buscador = new BuscadorClientesAvanzada(Main.getVentana(), NuevaBusquedaAvanzada.this,1);
			buscador.init();
			buscador.initComponent();
			buscador.setVisible(true);
			clienteFacElegido = buscador.getSelectedClient();

			if (clienteFacElegido != null) {
				lbl_cliente_fact.setText(clienteFacElegido.getEmpresa());
				panel.updateUI();
			}
		}
		
	}
    
    private BuscadorLugarEvento buscador;
    private class BuscarLugaresActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (buscador == null){
				buscador = new BuscadorLugarEvento(Main.getVentana());
				buscador.init();
				buscador.initComponent();
				buscador.setLugarAnterior(lugarElegido);
				buscador.setBusquedaPanel(NuevaBusquedaAvanzada.this);
			}
			buscador.setVisible(true);
			if (lugarElegido != null) {
				lbl_lugar.setText(lugarElegido.getNombreLugar());
				panel.updateUI();
			}
		}
    	
    }
	
    private class checkClienteChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(check_cliente_evt.isSelected()){
				btn_cliente_evt.setEnabled(true);
				lbl_cliente_evt.setEnabled(true);
			}
			else{
				btn_cliente_evt.setEnabled(false);
				lbl_cliente_evt.setEnabled(false);
			}			
		}
    	
    }
    
    private class checkClienteFactChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(check_cliente_fact.isSelected()){
				btn_cliente_fact.setEnabled(true);
				lbl_cliente_fact.setEnabled(true);
			}
			else{
				btn_cliente_fact.setEnabled(false);
				lbl_cliente_fact.setEnabled(false);
			}			
		}
    	
    }
    
    private class checkFechasChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(check_fechas.isSelected()){
				dt_fecha_desde.setEnabled(true);
				dt_fecha_hasta.setEnabled(true);
			}
			else{
				dt_fecha_desde.setEnabled(false);
				dt_fecha_hasta.setEnabled(false);
			}			
		}
    	
    }
    
    private class checkLugarChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(check_lugar.isSelected()){
				btn_lugar.setEnabled(true);
				lbl_lugar.setEnabled(true);
			}
			else{
				btn_lugar.setEnabled(false);
				lbl_lugar.setEnabled(false);
			}			
		}
    	
    }
    
    private class checkVendedorChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(check_vendedor.isSelected()){
				cmb_vendedor.setEnabled(true);
				lbl_vendedor.setEnabled(true);
			}
			else{
				cmb_vendedor.setEnabled(false);
				lbl_vendedor.setEnabled(false);
			}			
		}
    	
    }
    
    private class checkUnidadChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(check_unidad.isSelected()){
				cmb_unidad.setEnabled(true);
				lbl_unidad.setEnabled(true);
			}
			else{
				cmb_unidad.setEnabled(false);
				lbl_unidad.setEnabled(false);
			}			
		}
    	
    }
    
    private class checkEstadoChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(check_estado.isSelected()){
				//btn_estado.setEnabled(true);
				//lbl_estado.setEnabled(true);
			}
			else{
				cmb_estado.setEnabled(false);
				lbl_estado.setEnabled(false);
			}			
		}
    	
    }
    private class checkTipoEventoChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(check_tipo_evt.isSelected()){
				cmb_tipo_evt.setEnabled(true);
				lbl_tipo_evt.setEnabled(true);
			}
			else{
				cmb_tipo_evt.setEnabled(false);
				lbl_tipo_evt.setEnabled(false);
			}			
		}
    	
    }
    private class checkNombreChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(check_nombre_evt.isSelected()){
				tx_nombre_evt.setEnabled(true);
			}
			else{
				tx_nombre_evt.setEnabled(false);
			}			
		}
    	
    }
    
    public void setLugarElegido(LugarEvento lugarElegido) {
		this.lugarElegido = lugarElegido;
	}
    
    
    private class recargarTipoEvento implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			cmb_tipo_evt.recargar();
			
		}
		
	}
    private class recargarEstado implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			cmb_estado.recargar();
			
		}
		
	}
    private class recargarVendedores implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			cmb_vendedor.recargar();
			
		}
		
	}
    private class recargarUC implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			cmb_unidad.recargar();
			
		}
		
	}
    
}
