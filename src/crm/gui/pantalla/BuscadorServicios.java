package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.ServicioIdiomaManager;
import crm.client.managers.ServicioManager;
import crm.client.managers.VariacionFechaManager;
import crm.client.managers.VistaFamiliaServicioIdiomaManager;
import crm.client.util.DateConverter;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMFamiliasComboBox;
import crm.gui.components.ABMUnidadNegocioComboBox;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.tablerenderer.abms.BusquedaServiciosItem;
import crm.gui.tablerenderer.abms.BusquedaServiciosTableModel;
import crm.gui.tablerenderer.abms.BusquedaServiciosTableRender;
import crm.gui.tablerenderer.salas.SalaServicioItem;
import crm.libraries.abm.entities.Servicio;
import crm.libraries.abm.entities.ServicioIdioma;
import crm.libraries.abm.entities.VariacionFecha;
import crm.libraries.abm.entities.VistaFamiliaServicioIdioma;
import crm.libraries.util.MessageUtil;

public class BuscadorServicios extends PantallaEmergente{
	
	private javax.swing.ButtonGroup buttonGroup1;
    private GradientButton btnBuscar;
    private GradientButton btnAceptar;
    private GradientButton btnCancelar;
    private ABMFamiliasComboBox cmbFamilias;
    private ABMUnidadNegocioComboBox cmbUnidadesNegocio;
    private javax.swing.JFormattedTextField txtNombre;
    private javax.swing.JFormattedTextField txtCodigo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton rdNombre;
    private javax.swing.JRadioButton rdCodigo;
    private javax.swing.JRadioButton rdFamilias;
    private javax.swing.JRadioButton rdUnidadNegocio;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private BusquedaServiciosTableRender jTable1;

    private PanelImagen panel;
    private PantallaEmergente pantallaAgregarServicio;
    
    private SalaServicioItem servicioElegido;
    private String codIdiomaElegido;
    
    private static final String COD_IDIOMA_ESPANOL = "1";
    
    public BuscadorServicios(Frame owner, PantallaEmergente pantAgregar){
    	super("Buscador de servicios",owner);
    	pantallaAgregarServicio = pantAgregar;
    	setMinimumSize(new Dimension(800,500));
    }
    
    public void init() {
    	
    	panel = null;
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
        jLabel2 = new javax.swing.JLabel();
        rdNombre = new javax.swing.JRadioButton();
        rdCodigo = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jTable1 = new BusquedaServiciosTableRender();
        jSeparator3 = new javax.swing.JSeparator();
        btnAceptar = new GradientButton("", Color.blue);
        btnCancelar = new GradientButton("", Color.blue);
        rdFamilias = new javax.swing.JRadioButton();
        txtNombre = new javax.swing.JFormattedTextField();
        txtCodigo = new javax.swing.JFormattedTextField();
        rdUnidadNegocio = new javax.swing.JRadioButton();
        cmbFamilias = new ABMFamiliasComboBox();
        cmbUnidadesNegocio = new ABMUnidadNegocioComboBox();
        btnBuscar = new GradientButton("", Color.blue);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("B\u00fasqueda de servicios");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Buscar por:");

        buttonGroup1.add(rdNombre);
        rdNombre.setText("Nombre corto del servicio");
        rdNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdNombre.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rdNombre.setSelected(true);

        buttonGroup1.add(rdCodigo);
        rdCodigo.setText("C\u00f3digo del servicio");
        rdCodigo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdCodigo.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Resultados de la b\u00fasqueda");

        
        btnAceptar.setIcon(new javax.swing.ImageIcon(getUrlImagen("tick.png")));
        btnAceptar.setText("Aceptar");
        
        btnCancelar.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        btnCancelar.setText("Cancelar");

        buttonGroup1.add(rdFamilias);
        rdFamilias.setText("Familias de servicio");
        rdFamilias.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdFamilias.setMargin(new java.awt.Insets(0, 0, 0, 0));
        
        buttonGroup1.add(rdUnidadNegocio);
        rdUnidadNegocio.setText("Unidad de negocio");
        rdUnidadNegocio.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdUnidadNegocio.setMargin(new java.awt.Insets(0, 0, 0, 0));        
        
        btnBuscar.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        btnBuscar.setText("Buscar");
        
        loadCombos();
        
        setNombreText();
        
        txtCodigo.setEnabled(false);
		txtCodigo.setText("");
		cmbFamilias.setEnabled(false);
		cmbUnidadesNegocio.setEnabled(false);
		
        createListener();
    }
    
        public void initComponent() {
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                                .add(jLabel1)
                                .add(layout.createSequentialGroup()
                                    .add(jLabel2)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(rdFamilias)
                                                .add(rdUnidadNegocio))
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                                .add(cmbUnidadesNegocio, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(cmbFamilias, 0, 273, Short.MAX_VALUE)))
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                                .add(rdCodigo)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(txtCodigo))
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                                .add(rdNombre)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(txtNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 527, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(btnBuscar)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 23, Short.MAX_VALUE))
                                .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                                .add(jTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jLabel3)))
                    .addContainerGap())
                .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(352, Short.MAX_VALUE)
                    .add(btnAceptar)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(btnCancelar)
                    .add(349, 349, 349))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jLabel1)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel2)
                                .add(rdNombre)
                                .add(txtNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(rdCodigo)
                                .add(txtCodigo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(rdFamilias)
                                .add(cmbFamilias, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(rdUnidadNegocio)
                                .add(cmbUnidadesNegocio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(layout.createSequentialGroup()
                            .add(48, 48, 48)
                            .add(btnBuscar)))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel3)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jTable1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 235, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(btnAceptar)
                        .add(btnCancelar))
                    .addContainerGap(19, Short.MAX_VALUE))
            );
    
        this.getContentPane().add(panel);
        this.pack();
        updatePosition();
    }
    
    private void loadCombos(){
    	long time = System.currentTimeMillis();
    	cmbFamilias.loadItems();
    	cmbUnidadesNegocio.loadItems();
    	System.out.println("TIEMPO QUERY X COMBO FAMILIAS: "+ (System.currentTimeMillis()-time)/1000+" seg.");
    }
    
    public void updatePosition(){
    	
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	
    	this.setLocation((screenSize.width - this.getWidth())/2,
    			(screenSize.height - this.getHeight())/2);
    	
    }
    
    private void createListener(){
    	btnBuscar.addActionListener(new BuscarActionListener());
    	btnAceptar.addActionListener(new AceptarActionListener());
    	btnCancelar.addActionListener(new SalirActionListener());
    	
    	rdNombre.addChangeListener(new NombreChangeListener());
    	rdCodigo.addChangeListener(new CodigoChangeListener());
    	rdFamilias.addChangeListener(new FamiliaChangeListener());    	
    	rdUnidadNegocio.addChangeListener(new UnidadChangeListener());
    	
    	txtNombre.addKeyListener(new NombreKeyListener());
    	txtNombre.addFocusListener(new NombreFocusListener());
    	txtCodigo.addKeyListener(new CodigoKeyListener());
    	txtCodigo.addFocusListener(new CodigoFocusListener());
    	jTable1.getTable().addMouseListener(new TableMouseListener());
    }
    
    private void setNombreText(){
    	txtNombre.setEnabled(true);
    	txtNombre.setForeground(new java.awt.Color(153, 153, 153));
        txtNombre.setText("Ingrese al menos 3 caracteres para buscar coincidencias");
        txtNombre.setFont(new java.awt.Font("SansSerif", 2, 12));       
    }
    
    private void setCodigoText(){
    	txtCodigo.setEnabled(true);
    	txtCodigo.setForeground(new java.awt.Color(153, 153, 153));
        txtCodigo.setText("Ingrese el c\u00f3digo de cliente a buscar");
        txtCodigo.setFont(new java.awt.Font("SansSerif", 2, 12));
    }
    
    /**
	 * @return Returns the codClienteElegido.
	 */
	public SalaServicioItem getServicioElegido() {
		return servicioElegido;
	}
	
	/**
	 * @return Returns the codIdiomaElegido.
	 */
	public String getCodIdiomaElegido() {
		return codIdiomaElegido;
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
	
	private void buscarServicio(){
		try{
			ServicioIdioma[] servicioIdioma = null;
			Servicio[] servicio= null;
			if(rdNombre.isSelected() && !StringUtils.isBlank(txtNombre.getText())){
				servicioIdioma  = ServicioIdiomaManager.instance().findByField("descripcionAbreviada",txtNombre.getText());
				jTable1.setOcurrencia(txtNombre.getText());
			}
			else if(rdCodigo.isSelected() && !StringUtils.isBlank(txtCodigo.getText())){
				Servicio serv = ServicioManager.instance().getServicioById(txtCodigo.getText());
				jTable1.setOcurrencia(null);
				if(serv != null){
					servicio = new Servicio[1];
					servicio[0] = serv; 
				}
			}
			else if(rdFamilias.isSelected() && cmbFamilias.getSelectedIndex() >0){ 
				servicio  = ServicioManager.instance().findByFieldExactly("familia",cmbFamilias.searchForeign());
				jTable1.setOcurrencia(null);
			}
			else if(rdUnidadNegocio.isSelected() && cmbUnidadesNegocio.getSelectedIndex() >0){ 
				servicio  = ServicioManager.instance().findByFieldExactly("unidadNegocio",cmbUnidadesNegocio.searchForeign());
				jTable1.setOcurrencia(null);
			}
			else if(rdNombre.isSelected() && StringUtils.isBlank(txtNombre.getText())){
				Util.errMsg(Main.getVentana(),"Ingrese un nombre corto de servicio a buscar a buscar",null);
			}
			else if(rdCodigo.isSelected() && StringUtils.isBlank(txtCodigo.getText())){
				Util.errMsg(Main.getVentana(),"Ingrese un codigo de servicio a buscar",null);
			}
			else if(rdFamilias.isSelected() && cmbFamilias.getSelectedIndex() == 0){ 
				Util.errMsg(Main.getVentana(),"Seleccione una famila de servicos a buscar",null);   				
			}
			else if(rdUnidadNegocio.isSelected() && cmbUnidadesNegocio.getSelectedIndex() == 0){ 
				Util.errMsg(Main.getVentana(),"Seleccione una unidad de negocios a buscar",null);   				
			}
			
			if(servicioIdioma != null && servicioIdioma.length >0){
				BusquedaServiciosTableModel model = new BusquedaServiciosTableModel();
				
				for(int i=0; i<servicioIdioma.length;i++){
					BusquedaServiciosItem item = new BusquedaServiciosItem();
					item.setCodigo(servicioIdioma[i].getCodigoServicio());
					item.setTitulo(servicioIdioma[i].getDescripcionAbreviada());
					item.setDescripcion(servicioIdioma[i].getDescripcion());
					item.setIdioma(servicioIdioma[i].getCodigoIdioma());
					
					//item.setPrecio(getTotalFormateado(ServicioManager.instance().getPrecioVtaById(servicioIdioma[i].getCodigoServicio())));
					item.setPrecio(getTotalFormateado(getValorServicio(ServicioManager.instance().getPrecioVtaById(servicioIdioma[i].getCodigoServicio()))));
					model.addRow(item);
					
				}
				
				if(model.getRowCount() > 0){
					jTable1.getTable().setModel(model);
					jTable1.refreshTable();						
				}
				
			}
			else if(servicio != null && servicio.length >0 ){
    				BusquedaServiciosTableModel model = new BusquedaServiciosTableModel();
    				
    				for(int i=0; i<servicio.length;i++){
    					BusquedaServiciosItem item = new BusquedaServiciosItem();
    					item.setCodigo(servicio[i].getCodigo());
    					ServicioIdioma si = ServicioIdiomaManager.instance().getServicioIdiomaByIdNoIdioma(servicio[i].getCodigo());
    					item.setTitulo(si.getDescripcionAbreviada());
    					item.setDescripcion(si.getDescripcion());
    					//item.setPrecio(getTotalFormateado(Double.valueOf(servicio[i].getPrecioVenta())));
    					item.setPrecio(getTotalFormateado(getValorServicio(Double.valueOf(servicio[i].getPrecioVenta()))));
    					item.setIdioma(si.getCodigoIdioma());
    					model.addRow(item);
    					
    				}
    				
    				if(model.getRowCount() > 0){
    					jTable1.getTable().setModel(model);
    					jTable1.refreshTable();						
					}
    				
    			}
			else{
				Util.alertMsg(Main.getVentana(), "No se encontraron datos con esta busqueda.");
				((BusquedaServiciosTableModel)jTable1.getTable().getModel()).clear();
				jTable1.refreshTable();	
			}
			
		} catch (Exception e) {
			Util.errMsg(Main.getVentana(), "Error al buscar el servicio",e);
		}
	}
	
	private double getValorServicio(double valor){
		VariacionFecha[] variaciones = getDescuentosFecha(DateConverter.convertDateToString(new Date(),"yyyy-MM-dd"));
		if (variaciones != null) {
			for (int i = 0; i < variaciones.length; i++) {
				int valorAdescontar = (int) ((valor * Integer
						.parseInt(variaciones[i].getVariacion())) / 100);
				valor = valor + valorAdescontar;
			}
		}
		return Math.round(valor);
	}
	
	private VariacionFecha[] getDescuentosFecha(String fecha){
		try {
			return VariacionFechaManager.instance().getVariacionesFecha(fecha);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al obtener precio de servicio segun fecha", e);
			return null;
		}
	}
	
	private void seleccionarServicio(){
		BusquedaServiciosItem it = jTable1.getSelectedItem();
		if(it != null){
			try {
				VistaFamiliaServicioIdioma servicios  = VistaFamiliaServicioIdiomaManager.instance().getVistaFamiliaServicioIdiomaById(COD_IDIOMA_ESPANOL+it.getCodigo());
				
				SalaServicioItem item= new SalaServicioItem();
				item.setServicioCodigo(servicios.getCodigoServicio());
				item.setServicio(servicios.getDescripcion());
				item.setFamilia(servicios.getFamilia());
				item.setFamiliaCodigo(servicios.getCodigoFamilia());
				
				//if(pantallaAgregarServicio.getClass().equals(PantallaAgregarServicio.class)){
					((PantallaAgregarServicio)pantallaAgregarServicio).setServicio(item);
					((PantallaAgregarServicio)pantallaAgregarServicio).cargarServicioExistente();
				//}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			finally{
				setVisible(false);
			}
		}
		else Util.errMsg(Main.getVentana(),"Seleccione un servicio de la grilla",null);		
	}
	
	private void seleccionarVariosServicios(){
		BusquedaServiciosItem it = jTable1.getSelectedItem();
		if(it != null){
			try {
				VistaFamiliaServicioIdioma servicios  = VistaFamiliaServicioIdiomaManager.instance().getVistaFamiliaServicioIdiomaById(COD_IDIOMA_ESPANOL+it.getCodigo());
				
				servicioElegido= new SalaServicioItem();
				servicioElegido.setServicioCodigo(servicios.getCodigoServicio());
				servicioElegido.setServicio(servicios.getDescripcion());
				servicioElegido.setFamilia(servicios.getFamilia());
				servicioElegido.setFamiliaCodigo(servicios.getCodigoFamilia());
				
				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			finally{
				setVisible(false);
			}
		}
		else {
			servicioElegido = null;
			Util.errMsg(Main.getVentana(),"Seleccione un servicio de la grilla",null);		
		}
	}
    
    //************************ACCIONES**************************************************
    
    private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(BuscadorServicios.this, "¿Desea salir del formulario de busqueda?", "Salir")){
				setVisible(false);
			}
		}
		
	}
    
    private class AceptarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(pantallaAgregarServicio.getClass().equals(PantallaAgregarServicio.class)){
				seleccionarServicio();
			}
			else
				seleccionarVariosServicios();
		}
    	
    }
    
    private class BuscarActionListener implements ActionListener{

    	public void actionPerformed(ActionEvent arg0) {
    		buscarServicio();
    	}
    	
    }
    
    private class NombreChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(rdNombre.isSelected()){				
				setNombreText();	
				txtCodigo.setEnabled(false);
				txtCodigo.setText("");
				cmbFamilias.setEnabled(false);
				cmbFamilias.setForeign("0");
				cmbUnidadesNegocio.setEnabled(false);
				cmbUnidadesNegocio.setForeign("0");
			}
		}
    	
    }
    
    private class CodigoChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(rdCodigo.isSelected()){				
				setCodigoText();
				txtNombre.setEnabled(false);
				txtNombre.setText("");
				cmbFamilias.setEnabled(false);
				cmbFamilias.setForeign("0");
				cmbUnidadesNegocio.setEnabled(false);
				cmbUnidadesNegocio.setForeign("0");
			}
		}
    	
    }
    
    private class FamiliaChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(rdFamilias.isSelected()){				
				cmbFamilias.setEnabled(true);		
				txtCodigo.setEnabled(false);
				txtCodigo.setText("");
				txtNombre.setEnabled(false);
				txtNombre.setText("");
				cmbUnidadesNegocio.setEnabled(false);
				cmbUnidadesNegocio.setForeign("0");
			}
		}
    	
    }
    
    private class UnidadChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(rdUnidadNegocio.isSelected()){				
				cmbUnidadesNegocio.setEnabled(true);		
				txtCodigo.setEnabled(false);
				txtCodigo.setText("");
				txtNombre.setEnabled(false);
				txtNombre.setText("");
				cmbFamilias.setEnabled(false);
				cmbFamilias.setForeign("0");
			}
		}
    	
    }
    
    private class NombreFocusListener implements FocusListener{

		public void focusGained(FocusEvent e) {
			txtNombre.selectAll();	
			
		}

		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    private class CodigoFocusListener implements FocusListener{

		public void focusGained(FocusEvent e) {
			txtCodigo.selectAll();	
			
		}

		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    private class NombreKeyListener extends KeyAdapter{

		public void keyReleased(KeyEvent arg0) {
			if(txtNombre.getText().length() >0){				
				txtNombre.setForeground(new java.awt.Color(0, 0, 0));		        
		        txtNombre.setFont(new java.awt.Font("SansSerif", 0, 12)); 
		        if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
		        	buscarServicio();
		        }
			}
			
		}
    	
    }
    
    private class CodigoKeyListener extends KeyAdapter{

		public void keyReleased(KeyEvent arg0) {
			if(txtCodigo.getText().length() >0){
				txtCodigo.setForeground(new java.awt.Color(0, 0, 0));		        
		        txtCodigo.setFont(new java.awt.Font("SansSerif", 0, 12)); 
		        if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
		        	buscarServicio();
		        }
			}
			
		}
    	
    }
    
    private class TableMouseListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() ==2){
				seleccionarServicio();
			}
			
		}

		public void mousePressed(MouseEvent e) {
			jTable1.setMensajeImpresion("Servicios Buscados al "+DateConverter.convertDateToString(new Date(),"dd/MM/yyyy"));
			jTable1.maybeShowPopup(e);
			
		}

		public void mouseReleased(MouseEvent e) {
			jTable1.setMensajeImpresion("Servicios Buscados al "+DateConverter.convertDateToString(new Date(),"dd/MM/yyyy"));
			jTable1.maybeShowPopup(e);
			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
    	
    }

}
