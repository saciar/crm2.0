package crm.gui.abms.busquedas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.ServicioIdiomaManager;
import crm.client.managers.ServicioManager;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMFamiliasComboBox;
import crm.gui.components.ABMUnidadNegocioComboBox;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.tablerenderer.abms.BusquedaServiciosItem;
import crm.gui.tablerenderer.abms.BusquedaServiciosTableModel;
import crm.gui.tablerenderer.abms.BusquedaServiciosTableRender;
import crm.libraries.abm.entities.Servicio;
import crm.libraries.abm.entities.ServicioIdioma;
import crm.libraries.util.MessageUtil;

public class ABMServiciosBusqueda extends JDialog{
	
	private javax.swing.ButtonGroup buttonGroup1;
    private GradientButton jButton1;
    private GradientButton jButton2;
    private GradientButton jButton3;
    private GradientButton jButton4;
    private ABMFamiliasComboBox jComboBox1;
    private ABMUnidadNegocioComboBox jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private BusquedaServiciosTableRender jTable1;
	
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
	
    private String codEntidadElegido;
    private String codIdiomaElegido;
    
    public ABMServiciosBusqueda(Frame owner){
    	super(owner);
    	this.setTitle("Buscar Servicios");
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setModal(true);     
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setEnabled(true);
        createMenuBar();
    }
    
    private void createMenuBar(){
    	jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
		
        jMenuBar1.setSize(this.getWidth(),this.getHeight());
		jMenu1.setMnemonic('A');
        jMenu1.setText("Archivo");
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        
        jMenuItem1.setMnemonic('R');
        jMenuItem1.setText("Refrescar esta pantalla");
        jMenuItem1.addActionListener(new RefreshAction());
        jMenu1.add(jMenuItem1);
        jMenuItem2.setMnemonic('C');
        jMenuItem2.setText("Cerrar");
        jMenuItem2.addActionListener(new SalirActionListener());
        jMenu1.add(jMenuItem2);
        
        jMenuBar1.add(jMenu1);
		
        this.setJMenuBar(jMenuBar1);   
    }
    
    public URL getUrlImagen(String imagen){
	    
    	URL url =Main.class.getResource("imagenes/"+imagen); 

    	return url;
    }
    
    public void initComponents() {
    	
    	PanelImagen panel = null;
		try{
			panel = new PanelImagen("WorldLight.jpg", false);
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
		buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jTable1 = new BusquedaServiciosTableRender();
        jSeparator3 = new javax.swing.JSeparator();
        jButton2 = new GradientButton("", Color.blue);
        jButton3 = new GradientButton("", Color.blue);
        jButton4 = new GradientButton("", Color.blue);
        jRadioButton3 = new javax.swing.JRadioButton();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jRadioButton4 = new javax.swing.JRadioButton();
        jComboBox1 = new ABMFamiliasComboBox();
        jComboBox2 = new ABMUnidadNegocioComboBox();
        jButton1 = new GradientButton("", Color.blue);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("B\u00fasqueda de servicios");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Buscar por:");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Nombre corto del servicio");
        jRadioButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton1.setSelected(true);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("C\u00f3digo del servicio");
        jRadioButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Resultados de la b\u00fasqueda");

        jButton2.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_edit.png")));
        jButton2.setText("Editar");

        jButton3.setText("Borrar");
        jButton3.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_delete.png")));

        jButton4.setText("Salir");
        jButton4.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));

        jButton1.setText("Buscar");
        jButton1.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        
        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Familias de servicio");
        jRadioButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        
        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("Unidad de negocio");
        jRadioButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));        

        

        
        loadCombos();
        
        setNombreText();
        
        jFormattedTextField2.setEnabled(false);
		jFormattedTextField2.setText("");
		jComboBox1.setEnabled(false);
		jComboBox2.setEnabled(false);
		
        createListener();
        
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
                                            .add(jRadioButton3)
                                            .add(jRadioButton4))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                            .add(jComboBox2, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .add(jComboBox1, 0, 273, Short.MAX_VALUE)))
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                            .add(jRadioButton2)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jFormattedTextField2))
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                            .add(jRadioButton1)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 527, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jButton1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 21, Short.MAX_VALUE))
                            .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                            .add(jTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)))
                    .add(layout.createSequentialGroup()
                        .add(328, 328, 328)
                        .add(jButton2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton4))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel3)))
                .addContainerGap())
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
                            .add(jRadioButton1)
                            .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jRadioButton2)
                            .add(jFormattedTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jRadioButton3)
                            .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jRadioButton4)
                            .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(layout.createSequentialGroup()
                        .add(48, 48, 48)
                        .add(jButton1)))
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
                    .add(jButton2)
                    .add(jButton3)
                    .add(jButton4))
                .addContainerGap(19, Short.MAX_VALUE))
        );
    
        this.getContentPane().add(panel);
        this.pack();
        updatePosition();
    }
    
    private void loadCombos(){
    	jComboBox1.loadItems();
    	jComboBox2.loadItems();
    }
    
    public void updatePosition(){
    	
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	
    	this.setLocation((screenSize.width - this.getWidth())/2,
    			(screenSize.height - this.getHeight())/2);
    	
    }
    
    private void createListener(){
    	jButton1.addActionListener(new BuscarActionListener());
    	jButton2.addActionListener(new EditarActionListener());
    	jButton3.addActionListener(new BorrarActionListener());
    	jButton4.addActionListener(new SalirActionListener());
    	
    	jRadioButton1.addChangeListener(new NombreChangeListener());
    	jRadioButton2.addChangeListener(new CodigoChangeListener());
    	jRadioButton3.addChangeListener(new FamiliaChangeListener());    	
    	jRadioButton4.addChangeListener(new UnidadChangeListener());
    	
    	jFormattedTextField1.addKeyListener(new NombreKeyListener());
    	jFormattedTextField1.addMouseListener(new NombreMouseListener());
    	jFormattedTextField2.addKeyListener(new CodigoKeyListener());
    	jFormattedTextField2.addMouseListener(new CodigoMouseListener());
    }
    
    private void setNombreText(){
    	jFormattedTextField1.setEnabled(true);
    	jFormattedTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jFormattedTextField1.setText("Ingrese al menos 3 caracteres para buscar coincidencias");
        jFormattedTextField1.setFont(new java.awt.Font("SansSerif", 2, 12));       
    }
    
    private void setCodigoText(){
    	jFormattedTextField2.setEnabled(true);
    	jFormattedTextField2.setForeground(new java.awt.Color(153, 153, 153));
        jFormattedTextField2.setText("Ingrese el c\u00f3digo de cliente a buscar");
        jFormattedTextField2.setFont(new java.awt.Font("SansSerif", 2, 12));
    }
    
    /**
	 * @return Returns the codClienteElegido.
	 */
	public String getCodEntidadElegido() {
		return codEntidadElegido;
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
    
    //************************ACCIONES**************************************************
    
    private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(ABMServiciosBusqueda.this, "¿Desea salir del formulario de busqueda?", "Salir")){
				codEntidadElegido = null;
				setVisible(false);
			}
		}
		
	}
    
    private class EditarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			BusquedaServiciosItem it = jTable1.getSelectedItem();
			if(it != null){
				codEntidadElegido = it.getCodigo();
				codIdiomaElegido = it.getIdioma();
				setVisible(false);
			}
			else Util.errMsg(Main.getVentana(),"Seleccione un item de la grilla para editar.",null);
		}
    	
    }
    
    private class BorrarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			BusquedaServiciosItem it = jTable1.getSelectedItem();
			if(it != null){
				try {
					if (MessageUtil.showYesNoMessage(ABMServiciosBusqueda.this, "¿Seguro que desea borrar el servicio seleccionado?", "Borrar")){
						ServicioManager.instance().remove(it.getCodigo());
						
						ServicioIdiomaManager.instance().remove(it.getCodigo(), it.getIdioma());
						((BusquedaServiciosTableModel)jTable1.getTable().getModel()).removeRow(it);
						jTable1.refreshTable();	
					}
				} catch (RemoteException e) {
					Util.errMsg(Main.getVentana(), "Error al borrar el servicio",e);
				}				
			}
					
		}
    	
    }
    
    private class BuscarActionListener implements ActionListener{

    	public void actionPerformed(ActionEvent arg0) {
    		try{
    			ServicioIdioma[] servicioIdioma = null;
    			Servicio[] servicio= null;
    			if(jRadioButton1.isSelected() && !StringUtils.isBlank(jFormattedTextField1.getText())){
    				servicioIdioma  = ServicioIdiomaManager.instance().findByField("descripcionAbreviada",jFormattedTextField1.getText());
    			}
    			else if(jRadioButton2.isSelected() && !StringUtils.isBlank(jFormattedTextField2.getText())){
    				Servicio serv = ServicioManager.instance().getServicioById(jFormattedTextField2.getText());
    				if(serv != null){
    					servicio = new Servicio[1];
    					servicio[0] = serv; 
    				}
    			}
    			else if(jRadioButton3.isSelected() && jComboBox1.getSelectedIndex() >0){ 
    				servicio  = ServicioManager.instance().findByFieldExactly("familia",jComboBox1.searchForeign());   				
    			}
    			else if(jRadioButton4.isSelected() && jComboBox2.getSelectedIndex() >0){ 
    				servicio  = ServicioManager.instance().findByFieldExactly("unidadNegocio",jComboBox2.searchForeign());   				
    			}
    			else if(jRadioButton1.isSelected() && StringUtils.isBlank(jFormattedTextField1.getText())){
    				Util.errMsg(Main.getVentana(),"Ingrese un nombre corto de servicio a buscar a buscar",null);
    			}
    			else if(jRadioButton2.isSelected() && StringUtils.isBlank(jFormattedTextField2.getText())){
    				Util.errMsg(Main.getVentana(),"Ingrese un codigo de servicio a buscar",null);
    			}
    			else if(jRadioButton3.isSelected() && jComboBox1.getSelectedIndex() == 0){ 
    				Util.errMsg(Main.getVentana(),"Seleccione una famila de servicos a buscar",null);   				
    			}
    			else if(jRadioButton4.isSelected() && jComboBox2.getSelectedIndex() == 0){ 
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
    					item.setPrecio(getTotalFormateado(ServicioManager.instance().getPrecioVtaById(servicioIdioma[i].getCodigoServicio())));
    					model.addRow(item);
    					
    				}
    				
    				if(model.getRowCount() > 0){
    					jTable1.getTable().setModel(model);
    					jTable1.refreshTable();						
					}
    				
    			}
    			else if(servicio != null && servicio.length >0){
        				BusquedaServiciosTableModel model = new BusquedaServiciosTableModel();
        				
        				for(int i=0; i<servicio.length;i++){
        					BusquedaServiciosItem item = new BusquedaServiciosItem();
        					item.setCodigo(servicio[i].getCodigo());
        					ServicioIdioma si = ServicioIdiomaManager.instance().getServicioIdiomaByIdNoIdioma(servicio[i].getCodigo());
        					item.setTitulo(si.getDescripcionAbreviada());
        					item.setDescripcion(si.getDescripcion());
        					item.setPrecio(getTotalFormateado(Double.valueOf(servicio[i].getPrecioVenta())));
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
    	
    }
    
    private class NombreChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(jRadioButton1.isSelected()){				
				setNombreText();	
				jFormattedTextField2.setEnabled(false);
				jFormattedTextField2.setText("");
				jComboBox1.setEnabled(false);
				jComboBox1.setForeign("0");
				jComboBox2.setEnabled(false);
				jComboBox2.setForeign("0");
			}
		}
    	
    }
    
    private class CodigoChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(jRadioButton2.isSelected()){				
				setCodigoText();
				jFormattedTextField1.setEnabled(false);
				jFormattedTextField1.setText("");
				jComboBox1.setEnabled(false);
				jComboBox1.setForeign("0");
				jComboBox2.setEnabled(false);
				jComboBox2.setForeign("0");
			}
		}
    	
    }
    
    private class FamiliaChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(jRadioButton3.isSelected()){				
				jComboBox1.setEnabled(true);		
				jFormattedTextField2.setEnabled(false);
				jFormattedTextField2.setText("");
				jFormattedTextField1.setEnabled(false);
				jFormattedTextField1.setText("");
				jComboBox2.setEnabled(false);
				jComboBox2.setForeign("0");
			}
		}
    	
    }
    
    private class UnidadChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(jRadioButton4.isSelected()){				
				jComboBox2.setEnabled(true);		
				jFormattedTextField2.setEnabled(false);
				jFormattedTextField2.setText("");
				jFormattedTextField1.setEnabled(false);
				jFormattedTextField1.setText("");
				jComboBox1.setEnabled(false);
				jComboBox1.setForeign("0");
			}
		}
    	
    }
    
    private class NombreMouseListener extends MouseAdapter{

		public void mouseClicked(MouseEvent arg0) {
			jFormattedTextField1.selectAll();			
		}
    	
    }
    
    private class CodigoMouseListener extends MouseAdapter{

		public void mouseClicked(MouseEvent arg0) {
			jFormattedTextField2.selectAll();			
		}
    	
    }
    
    private class NombreKeyListener extends KeyAdapter{

		public void keyReleased(KeyEvent arg0) {
			if(jFormattedTextField1.getText().length() >0){				
				jFormattedTextField1.setForeground(new java.awt.Color(0, 0, 0));		        
		        jFormattedTextField1.setFont(new java.awt.Font("SansSerif", 0, 12)); 
			}
			
		}
    	
    }
    
    private class CodigoKeyListener extends KeyAdapter{

		public void keyReleased(KeyEvent arg0) {
			if(jFormattedTextField2.getText().length() >0){
				jFormattedTextField2.setForeground(new java.awt.Color(0, 0, 0));		        
		        jFormattedTextField2.setFont(new java.awt.Font("SansSerif", 0, 12)); 
			}
			
		}
    	
    }
    
    private class RefreshAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			loadCombos();			
		}
    	
    }
    
}
