package crm.gui.abms;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import crm.client.html.editor.PopupEditorHTML;
import crm.client.managers.ServicioDescripcionManager;
import crm.client.managers.ServicioIdiomaManager;
import crm.client.managers.ServicioManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.abms.busquedas.ABMClientesBusqueda;
import crm.gui.abms.busquedas.ABMServiciosBusqueda;
import crm.gui.components.ABMFamiliasComboBox;
import crm.gui.components.ABMIdiomasComboBox;
import crm.gui.components.ABMUnidadNegocioComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.components.GradientButton;
import crm.libraries.abm.entities.Servicio;
import crm.libraries.abm.entities.ServicioDescripcion;
import crm.libraries.abm.entities.ServicioIdioma;
import crm.libraries.util.MessageUtil;

public class ABMServicios extends ABMGeneral{
	
	private GradientButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private ABMFamiliasComboBox jComboBox1;
    private ABMIdiomasComboBox jComboBox2;
    private ABMUnidadNegocioComboBox jComboBox3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JFormattedTextField jFormattedTextField5;
    private javax.swing.JFormattedTextField jFormattedTextField6;
    private javax.swing.JFormattedTextField jFormattedTextField7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
	
    private Servicio servicio;
    private JPanel panel;
    private List idiomaDescripciones;
    private String currentIdioma;
    private String codServicioBuscada;
    private String codIdiomaBuscada;
    
    public ABMServicios(JPanel pan){
    	panel = pan;
    }
    
	public void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new ABMFamiliasComboBox();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new ABMIdiomasComboBox();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jFormattedTextField3 = CustomTextField.getDecimalInstance(10,2);
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jComboBox3 = new ABMUnidadNegocioComboBox();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new GradientButton("", Color.blue);
        jLabel14 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        
        idiomaDescripciones = new ArrayList();
        
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de un nuevo servicio");

        jLabel2.setText("Familia del servicio");       

        jLabel3.setText("Idioma del servicio");       

        jLabel4.setText("Nombre corto del servicio");

        jLabel5.setText("Precio de venta");

        jCheckBox1.setText("Precio por un solo día");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jCheckBox2.setText("Admite sin cargo?");
        jCheckBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox2.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jCheckBox3.setText("Tiene accesorios para la instalaci\u00f3n?");
        jCheckBox3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox3.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel6.setText("Nombre completo del servicio");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Medidas del equipo");

        jLabel8.setText("Largo:");

        jLabel9.setText("Alto:");

        jLabel10.setText("Ancho:");

        jLabel11.setText("Peso:");

        jLabel12.setText("Unidad de negocio a la que pertenece este servicio");        

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("Descripci\u00f3n detallada del servicio");

        jButton1.setText("Editar descripci\u00f3n");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel14.setText("Nota: Esta descripci\u00f3n es la que se ver\u00e1 en el presupuesto final.");
        
        /*jComboBox2.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				if(!StringUtils.isBlank(currentIdioma)){
					ServicioIdiomaHelper helper = getServicioIdiomaHelper(currentIdioma);
					addServicioIdiomaHelper(currentIdioma,jFormattedTextField1.getText(),jFormattedTextField2.getText(),helper.getDescriptionLines());				
				}
				currentIdioma = jComboBox2.searchForeign();				
				if(!StringUtils.isBlank(currentIdioma)){
					ServicioIdiomaHelper helper = getServicioIdiomaHelper(currentIdioma);
					jFormattedTextField1.setText(helper.getTitulo());
					jFormattedTextField2.setText(helper.getDescripcion());						
				}else{
					jFormattedTextField2.setText("");
					jFormattedTextField1.setText("");
				}
			}		
		});*/
        
        createListeners();
        
        setAddMode();
	}
	
	public void initLayout() {
		
		jComboBox1.loadItems();
		jComboBox2.loadItems();
		jComboBox3.loadItems();
		
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                    .add(jLabel1)
                    .add(layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 244, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(40, 40, 40)
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 252, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel6)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jFormattedTextField2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(jLabel12)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jComboBox3, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(jLabel5)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jFormattedTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 127, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(35, 35, 35)
                                .add(jCheckBox1)
                                .add(26, 26, 26)
                                .add(jCheckBox2)))
                        .add(29, 29, 29)
                        .add(jCheckBox3))
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                    .add(jLabel7)
                    .add(layout.createSequentialGroup()
                        .add(jLabel8)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jFormattedTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(30, 30, 30)
                        .add(jLabel9)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jFormattedTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(28, 28, 28)
                        .add(jLabel10)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jFormattedTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(31, 31, 31)
                        .add(jLabel11)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jFormattedTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                    .add(jLabel13)
                    .add(layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel14)
                            .add(jButton1)))
                    .add(jSeparator4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jFormattedTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jCheckBox1)
                    .add(jCheckBox2)
                    .add(jCheckBox3)
                    .add(jFormattedTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel12)
                    .add(jComboBox3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel7)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(jFormattedTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel9)
                    .add(jFormattedTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel10)
                    .add(jFormattedTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel11)
                    .add(jFormattedTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(16, 16, 16)
                .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel13)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel14)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
        );
    }
	
	private void createListeners(){
		jButton1.addActionListener(new EditarActionListener());
	}
	
	private void setAddMode() {
		this.servicio = new Servicio();
		this.idiomaDescripciones.clear();
		this.currentIdioma = null;
		resetFields();
	}
	
	public void setEditMode(String entityId){
   	 try {    		  		
   		 this.servicio = ServicioManager.instance().getServicioById(entityId);  
   		 this.idiomaDescripciones.clear();
   		 Iterator it = this.jComboBox2.getForeigns().iterator();
   		 while (it.hasNext()) {
				String idiomaId = (String) it.next();
				//String html = ServicioManager.instance().getDescripcion(this.servicio.getCodigo(),idiomaId);
				ServicioDescripcion[] descripciones = ServicioDescripcionManager.instance().findByServicio(this.servicio.getCodigo(),idiomaId);
				List descriptionLines = new ArrayList();
				for(int i = 0;i < descripciones.length;i ++){
					descriptionLines.add(descripciones[i].getDescripcion());
				}
				ServicioIdioma servicioIdioma = ServicioIdiomaManager.instance().getServicioIdiomaById(this.servicio.getCodigo(),idiomaId);
				if(servicioIdioma != null){
					addServicioIdiomaHelper(idiomaId,servicioIdioma.getDescripcionAbreviada(),servicioIdioma.getDescripcion(),descriptionLines);
				}
			}
   		 resetFields();
   	 }catch (RemoteException e) {		
			e.printStackTrace();
		}
       
   } 
	
	protected void resetFields(){    	
		this.jComboBox1.setForeign(this.servicio.getFamilia());
		this.jComboBox3.setForeign(this.servicio.getUnidadNegocio());
		this.jFormattedTextField3.setText(this.servicio.getPrecioVenta());
		//this.m_txtPrecioVtaMin.setText(this.servicio.getPrecioVentaMinimo());
		this.jFormattedTextField7.setText(this.servicio.getPeso());
		this.jFormattedTextField4.setText(this.servicio.getLargo());
		this.jFormattedTextField5.setText(this.servicio.getAltura());
		this.jFormattedTextField6.setText(this.servicio.getAncho());
		this.jCheckBox1.setSelected((this.servicio.getAdmiteDescuento() != null && this.servicio.getAdmiteDescuento().equals("S")));
		this.jCheckBox2.setSelected((this.servicio.getAdmiteSinCargo() != null && this.servicio.getAdmiteSinCargo().equals("S")));
		this.jCheckBox3.setSelected((this.servicio.getAccesorio() != null && this.servicio.getAccesorio().equals("S")));
		if(!StringUtils.isBlank(this.currentIdioma)){
			ServicioIdiomaHelper helper = getServicioIdiomaHelper(this.currentIdioma);			
			this.jFormattedTextField1.setText(helper.getTitulo());
			this.jFormattedTextField2.setText(helper.getDescripcion());
			this.jComboBox2.setForeign(currentIdioma);
		}
		else{
			this.jFormattedTextField1.setText("");
			this.jFormattedTextField2.setText("");
			this.jComboBox2.setForeign(null);
		}
    }
	
	private boolean isFormIncomplete() {
		
		String nombreCorto = jFormattedTextField1.getText();
		String descripcion = jFormattedTextField2.getText();
		String peso = jFormattedTextField7.getText();
		String familia = jComboBox1.searchForeign();
		String precioVta = jFormattedTextField3.getText();
		//String precioVtaMin = m_txtPrecioVtaMin.getText();
		String largo = jFormattedTextField4.getText();
		String alto = jFormattedTextField5.getText();
		String ancho = jFormattedTextField6.getText();
		String unidadNegocio = jComboBox3.searchForeign();
		String idioma = jComboBox2.searchForeign();

		return familia == null || familia.length() == 0 || precioVta == null
				|| nombreCorto == null || nombreCorto.length() == 0 || descripcion == null || descripcion.length() == 0
				|| largo == null || largo.length() == 0
				|| peso == null || peso.length() == 0
				|| alto == null || alto.length() == 0 || ancho == null
				|| ancho.length() == 0 || unidadNegocio == null || unidadNegocio.length() == 0  || idioma == null || idioma.length() == 0;
	}
	
	public void guardar() {
		
		if (isFormIncomplete()) {
			Util.errMsg(Main.getVentana(), "Tiene datos obligatorios por completar", null);
			return;
		}
		
		String nombreCorto = jFormattedTextField1.getText();
		String descripcion = jFormattedTextField2.getText();
		String peso = jFormattedTextField7.getText();
		String familia = jComboBox1.searchForeign();
		String precioVta = jFormattedTextField3.getText();
		//String precioVtaMin = m_txtPrecioVtaMin.getText();
		String largo = jFormattedTextField4.getText();
		String alto = jFormattedTextField5.getText();
		String ancho = jFormattedTextField6.getText();
		String unidadNegocio = jComboBox3.searchForeign();
		String idioma = jComboBox2.searchForeign();
		boolean admDescuento = jCheckBox1.isSelected();
		boolean admSinCargo = jCheckBox2.isSelected();
		boolean accesorios = jCheckBox3.isSelected();
		
		try {
        	this.servicio.setFamilia(familia);
        	this.servicio.setPrecioVenta(precioVta);
        	this.servicio.setPrecioVentaMinimo("0.00");
        	this.servicio.setPeso(peso);
        	this.servicio.setLargo(largo);
        	this.servicio.setAltura(alto);
        	this.servicio.setAncho(ancho);
        	this.servicio.setUnidadNegocio(unidadNegocio);
        	this.servicio.setAdmiteDescuento((((admDescuento)?"S":"N")));
        	this.servicio.setAdmiteSinCargo((((admSinCargo)?"S":"N")));
        	this.servicio.setAccesorio((((accesorios)?"S":"N")));    
        	
			String servicioId = ServicioManager.instance().update(this.servicio,"","");
			
			ServicioIdioma si = new ServicioIdioma();
			si.setCodigoServicio(servicioId);
			si.setCodigoIdioma(idioma);					
			si.setDescripcionAbreviada(nombreCorto);
			si.setDescripcion(descripcion);
			
			ServicioIdiomaManager.instance().update(si);

			Iterator it = this.jComboBox2.getForeigns().iterator();
			it = this.idiomaDescripciones.iterator();
			while(it.hasNext()){
				ServicioIdiomaHelper helper = (ServicioIdiomaHelper)it.next();
				
				//borro los viejos
				ServicioDescripcionManager.instance().removeByServicio(servicioId,helper.getIdioma());
				
				
				if(!StringUtils.isBlank(helper.getIdioma())){
					/*ServicioIdioma si = new ServicioIdioma();
					si.setCodigoServicio(servicioId);
					si.setCodigoIdioma(idioma);					
					si.setDescripcionAbreviada(nombreCorto);
					si.setDescripcion(descripcion);
					
					ServicioIdiomaManager.instance().update(si);*/				
					
					saveServicioDesciption(servicioId,helper);
				}
			}
			Util.alertMsg(Main.getVentana(), "El servicio se cargó con éxito");
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al guardar el servicio",e);
			e.printStackTrace();
		}finally{
			setAddMode();
		}
		
	}
	
	private void saveServicioDesciption(String servicioId,ServicioIdiomaHelper helper){
		try {		
			Iterator it = helper.getDescriptionLines().iterator();
			while(it.hasNext()){
				String line = (String)it.next();
				ServicioDescripcionManager.instance().saveDescripcion(servicioId,helper.getIdioma(),line);			
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	protected void editarDescripcion() {
		String idioma = this.jComboBox2.searchForeign();
		String titulo = jFormattedTextField1.getText();
		String descripcion = jFormattedTextField2.getText();
		
		if(!StringUtils.isBlank(idioma) && !StringUtils.isBlank(titulo)){			
			
			ServicioIdiomaHelper id = getServicioIdiomaHelper(idioma);
			PopupEditorHTML popup = new PopupEditorHTML(null,titulo,id.getDescriptionLines());
			popup.setVisible(true);
			
			if(popup.getDescriptionLines() != null){
				addServicioIdiomaHelper(idioma,titulo,descripcion,popup.getDescriptionLines());
			}			
			/*
			if(!StringUtils.isBlank(popup.getTextReturned())){
				addServicioIdiomaHelper(idioma,titulo,descripcion,popup.getTextReturned());
			}
			*/
		}
		else if(StringUtils.isBlank(idioma)){
			MessageUtil.showErrorMessage("Seleccione el idioma del servicio");
		}
		else MessageUtil.showErrorMessage("Escriba el título del servicio");
	}

    private void addServicioIdiomaHelper(String idioma,String titulo,String descripcion,List descriptionLines){
    	ServicioIdiomaHelper id = getServicioIdiomaHelper(idioma);    	
    	id.setTitulo(titulo);    	
    	id.setDescripcion(descripcion);    	    	
    	id.setDescriptionLines(descriptionLines);
    	if(!this.idiomaDescripciones.contains(id)){
    		this.idiomaDescripciones.add(id);
    	}
    }
    
    private ServicioIdiomaHelper getServicioIdiomaHelper(String idioma){
    	Iterator it = this.idiomaDescripciones.iterator();
    	while(it.hasNext()){
    		ServicioIdiomaHelper id = (ServicioIdiomaHelper)it.next();
    		if(idioma.equals(id.getIdioma())){
    			return id;
    		}
    	}
    	return new ServicioIdiomaHelper(idioma,null,null,null);
    }
    
    public void buscar(){
		
		ABMServiciosBusqueda busq = new ABMServiciosBusqueda(Main.getVentana());
		busq.initComponents();
		busq.setVisible(true);
		codServicioBuscada = busq.getCodEntidadElegido();
		currentIdioma = busq.getCodIdiomaElegido();
		if(codServicioBuscada != null){
			ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_PPTO_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {
					setEditMode(codServicioBuscada);
					ProgressDialogUtil.closeProcessDialog();
				}
			}).start();
		}
		else
			setAddMode();
	}
	
    //*************************ACCIONES**************************************************
    private class EditarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			editarDescripcion();			
		}
    	
    }
    
	//*************************CLASE PRIVADA*********************************************
	
	private class ServicioIdiomaHelper{
    	private String idioma;
    	private String titulo;
    	private String descripcion;
    	private List descriptionLines;
		public ServicioIdiomaHelper(String idioma,String titulo, String descripcion,List descriptionLines) {
			this.idioma = idioma;
			this.titulo = titulo;
			this.descripcion = descripcion;
			this.descriptionLines = descriptionLines;
		}
		
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public String getIdioma() {
			return idioma;
		}
		public void setIdioma(String idioma) {
			this.idioma = idioma;
		}

		public List getDescriptionLines() {
			return descriptionLines;
		}

		public void setDescriptionLines(List descriptionLines) {
			this.descriptionLines = descriptionLines;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		
    }
    
}
