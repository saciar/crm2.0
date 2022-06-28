package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import crm.client.html.editor.PopupEditorHTML;
import crm.client.managers.ServicioDescripcionManager;
import crm.client.util.DateDiff;
import crm.client.util.Util;
import crm.client.validacion.ErrorList;
import crm.client.validacion.ErrorMessageBuilder;
import crm.gui.Main;
import crm.gui.components.CustomTextField;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.solapa.SalaPanel;
import crm.gui.tablerenderer.salas.SalaServicioItem;
import crm.libraries.abm.entities.ServicioDescripcion;
import crm.libraries.abm.helper.ServicioIdiomaHelper;
import crm.libraries.util.MessageUtil;
import crm.services.sei.FamiliaServManagerSEI;

public class PantallaAgregarServicio extends PantallaEmergente{
		
	private javax.swing.JButton btAceptar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btnDescripcionDetallada;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblServicio;
    private javax.swing.JLabel lblPrecioSubcontratado;
    private javax.swing.JLabel lblServSubcontratado;
    private javax.swing.JCheckBox m_checkOpcional;
    //private javax.swing.JCheckBox m_checkSubcontratados;
    private javax.swing.JFormattedTextField m_txtCantidad;
    private javax.swing.JFormattedTextField m_txtDias;
    private javax.swing.JFormattedTextField m_txtPrecioSubcontratado;
    private javax.swing.JFormattedTextField m_txtSubcontratado;
    private javax.swing.JTextArea vistaPrevia;
	    
	    private SalaServicioItem servicio;
	    private SalaPanel salasPanel;
	    private List idiomaDescripciones;
	    private boolean cancelado;	
		private String codigoLugarElegido;   
		private String mesEvento;
		private String fechaEvt;
		
		public PantallaAgregarServicio(Frame owner, SalaPanel sp){
			super("Carga de servicios", owner);
	        salasPanel = sp;  	
	    }
	    
		public String getLugarElegido() {
			return codigoLugarElegido;
		}

		public void setLugarElegido(String lugarElegido) {
			this.codigoLugarElegido = lugarElegido;

		}
		
		public void setMesEvento(String fecha) {
			this.mesEvento = fecha;
			servicio.setMesEvento(fecha);
		}
		
		public void setFechaEvt(String fecha){
			this.fechaEvt=fecha;
			servicio.setFechaEvt(fecha);
		}
		
	    public void init() {
	    	
	    	PanelImagen panel = null;
			try{
				panel = new PanelImagen("WorldLight.jpg");
			}
			catch(Exception e){
				System.out.println("Error");
				panel = new PanelImagen();
			}
	    	
	        lblServicio = new javax.swing.JLabel();
	        jSeparator1 = new javax.swing.JSeparator();
	        jSeparator2 = new javax.swing.JSeparator();
	        jLabel3 = new javax.swing.JLabel();
	        m_txtCantidad = CustomTextField.getTinyIntInstance();;
	        jLabel6 = new javax.swing.JLabel();
	        m_txtDias = CustomTextField.getSmallIntInstance();;
	        jLabel7 = new javax.swing.JLabel();
	        //m_checkSubcontratados = new javax.swing.JCheckBox();
	        m_checkOpcional = new javax.swing.JCheckBox();
	        lblServSubcontratado = new javax.swing.JLabel();
	        m_txtSubcontratado = CustomTextField.getRegularStringInstance(100);
	        lblPrecioSubcontratado = new javax.swing.JLabel();
	        m_txtPrecioSubcontratado = CustomTextField.getDecimalInstance(10,2);
	        jSeparator4 = new javax.swing.JSeparator();
	        jLabel10 = new javax.swing.JLabel();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        vistaPrevia = new javax.swing.JTextArea();
	        btnDescripcionDetallada = new GradientButton("", Color.blue);
	        btAceptar = new GradientButton("", Color.blue);
	        btCancelar = new GradientButton("", Color.blue);
	        jRadioButton1 = new javax.swing.JRadioButton();
	        jRadioButton2 = new javax.swing.JRadioButton();
	        jLabel1 = new javax.swing.JLabel();
	        btnBuscar = new GradientButton("", Color.blue);

	        lblServicio.setFont(new java.awt.Font("Tahoma", 1, 11));
	        lblServicio.setText("Servicio seleccionado");

	        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
	        jLabel3.setText("Indique la cantidad");

	        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
	        jLabel6.setText("por");

	        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
	        jLabel7.setText("d\u00edas");

	        /*m_checkSubcontratados.setFont(new java.awt.Font("Tahoma", 1, 11));
	        m_checkSubcontratados.setText("Es subcontratado");
	        m_checkSubcontratados.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
	        m_checkSubcontratados.setMargin(new java.awt.Insets(0, 0, 0, 0));*/
	        
	        m_checkOpcional.setFont(new java.awt.Font("Tahoma", 1, 11));
	        m_checkOpcional.setText("Es opcional");
	        m_checkOpcional.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
	        m_checkOpcional.setMargin(new java.awt.Insets(0, 0, 0, 0));

	        lblServSubcontratado.setFont(new java.awt.Font("Tahoma", 1, 11));
	        lblServSubcontratado.setText("Nombre del servicio a cargar");

	        lblPrecioSubcontratado.setFont(new java.awt.Font("Tahoma", 1, 11));
	        lblPrecioSubcontratado.setText("por $");

	        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
	        jLabel10.setText("Caracter\u00edsticas detalladas del servicio");

	        vistaPrevia.setColumns(20);
	        vistaPrevia.setRows(5);
	        vistaPrevia.setEditable(false);
	        jScrollPane1.setViewportView(vistaPrevia);

	        btnDescripcionDetallada.setIcon(new javax.swing.ImageIcon(getUrlImagen("text_list_bullets.png")));
	        btnDescripcionDetallada.setText("Editar caracter\u00edsticas");
	        btnDescripcionDetallada.setEnabled(false);
	        
	        btAceptar.setIcon(new javax.swing.ImageIcon(getUrlImagen("tick.png")));
	        btAceptar.setText("Aceptar");

	        btCancelar.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
	        btCancelar.setText("Cancelar");
	        
	        buttonGroup1 = new javax.swing.ButtonGroup();
	        buttonGroup1.add(jRadioButton1);
	        jRadioButton1.setText("Servicio existente");
	        jRadioButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
	        jRadioButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
	        jRadioButton1.setSelected(true);

	        buttonGroup1.add(jRadioButton2);
	        jRadioButton2.setText("Otro");
	        jRadioButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
	        jRadioButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
	        
	        btnBuscar.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
	        btnBuscar.setText("Buscar");

	        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
	        panel.setLayout(layout);
	        layout.setHorizontalGroup(
	                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	                .add(layout.createSequentialGroup()
	                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	                        .add(layout.createSequentialGroup()
	                            .addContainerGap()
	                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	                                .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
	                                .add(layout.createSequentialGroup()
	                                    .add(lblServSubcontratado)
	                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                                    .add(m_txtSubcontratado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
	                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                                    .add(lblPrecioSubcontratado)
	                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                                    .add(m_txtPrecioSubcontratado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 89, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
	                                .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
	                                .add(jSeparator4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
	                                .add(jLabel10)
	                                .add(layout.createSequentialGroup()
	                                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 661, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                                    .add(btnDescripcionDetallada))
	                                .add(layout.createSequentialGroup()
	                                    .add(jLabel3)
	                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                                    .add(m_txtCantidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                                    .add(jLabel6)
	                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                                    .add(m_txtDias, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                                    .add(jLabel7)
	                                    .add(103, 103, 103)
	                                    //.add(m_checkSubcontratados)
	                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                                    .add(m_checkOpcional))))
	                        .add(layout.createSequentialGroup()
	                            .add(319, 319, 319)
	                            .add(btAceptar)
	                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                            .add(btCancelar))
	                        .add(layout.createSequentialGroup()
	                            .addContainerGap()
	                            .add(lblServicio)
	                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                            .add(jLabel1))
	                        .add(layout.createSequentialGroup()
	                            .addContainerGap()
	                            .add(jRadioButton1)
	                            .add(17, 17, 17)
	                            .add(btnBuscar))
	                        .add(layout.createSequentialGroup()
	                            .addContainerGap()
	                            .add(jRadioButton2)))
	                    .addContainerGap())
	            );
	            layout.setVerticalGroup(
	                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	                .add(layout.createSequentialGroup()
	                    .addContainerGap()
	                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
	                        .add(lblServicio)
	                        .add(jLabel1))
	                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
	                        .add(jRadioButton1)
	                        .add(btnBuscar))
	                    .add(14, 14, 14)
	                    .add(jRadioButton2)
	                    .add(18, 18, 18)
	                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
	                        .add(m_txtPrecioSubcontratado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                        .add(lblPrecioSubcontratado)
	                        .add(m_txtSubcontratado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                        .add(lblServSubcontratado))
	                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
	                        .add(m_txtCantidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                        .add(jLabel6)
	                        .add(m_txtDias, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                        .add(jLabel7)
	                        //.add(m_checkSubcontratados)
	                        .add(jLabel3)
	                        .add(m_checkOpcional))
	                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                    .add(jSeparator4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	                        .add(layout.createSequentialGroup()
	                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                            .add(jLabel10)
	                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 179, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 17, Short.MAX_VALUE)
	                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
	                                .add(btAceptar)
	                                .add(btCancelar)))
	                        .add(layout.createSequentialGroup()
	                            .add(104, 104, 104)
	                            .add(btnDescripcionDetallada)))
	                    .addContainerGap())
	            );
	        this.getContentPane().add(panel);
	        this.pack();

	        setInitial();
	        
		    createListeners();
	        updatePosition();        
	    }
	    
	    private void setInitial(){
	    	/*if (servicio.getServicioCodigo() != null){
	    		cargarServicioExistente();
	    	}
	    	else{*/
	    		habilitarParaOtraFlia();
	    		m_txtCantidad.setText("1");
	    		m_txtDias.setText(String.valueOf(getPeriodoSala()));
	    	//}
	    }
	    
	    private void createListeners(){
	    	btAceptar.addActionListener(new SalvarServicioAction());			
			btnBuscar.addActionListener(new EditarAccesoriosAction());			
			btnDescripcionDetallada.addActionListener(new AgregarDescripcionAction());			
			btCancelar.addActionListener(new CancelarAction());
			
			//m_checkSubcontratados.addActionListener(new SubcontratarAction());			
			m_checkOpcional.addActionListener(new OpcionalAction());
			
			jRadioButton1.addChangeListener(new ServicioPropioChangeListener());
			jRadioButton2.addChangeListener(new ServicioSubcontratadoChangeListener());
	    }
	    
	    /**
		 * Valida los campos ingresados en esta ventana.
		 * 
		 * @return
		 */
		private ErrorList validateServicio(){
			ErrorList errors = new ErrorList();
			
			//int familiaIndex = m_cmbFamilias.getSelectedIndex();
			//int servIndex = m_cmbServicios.getSelectedIndex();
			if(servicio.getServicioCodigo() != null){
			if (!NumberUtils.isDigits(m_txtCantidad.getText()) || 
					!NumberUtils.isDigits(m_txtDias.getText()) //|| 
					//familiaIndex == 0 || 
					/*servIndex == 0*/){
				errors.addError("Ingrese todos los campos");
			}
			else {
				int cantidad = Integer.parseInt(m_txtCantidad.getText());
				int dias = Integer.parseInt(m_txtDias.getText());
				
				if (cantidad <= 0)
					errors.addError("La cantidad debe ser mayor a cero.");
			
				if (dias <= 0)// || dias > periodoSala)
					errors.addError("La cantidad de días debe ser mayor a cero");// y menor al periodo del evento.");
				
				//if (isFliaSubcontratada(StringUtils.isBlank(m_txtSubcontratado.getText()))
				//	errors.addError("Ingrese el Servicio Subcontratado");
				
				//if (isFliaSubcontratada(m_cmbFamilias.searchForeign()) && StringUtils.isBlank(m_txtPrecioSubcontratado.getText()))
					//errors.addError("Ingrese el precio del Servicio Subcontratado");
			}
			}
			
			return errors;
		}
		

		
		private boolean isFliaSubcontratada(String codFlia){
			return FamiliaServManagerSEI.FAMILIA_SERVICIO_SUBCONTRATADO.equals(codFlia);
		}
		
		private boolean isFliaArmadoDiaPrevio(String codFlia){
			return FamiliaServManagerSEI.FAMILIA_SERVICIO_ARMADO.equals(codFlia);
		}
		
		private void deshabilitarParaFliaSubcont(){
			lblServSubcontratado.setEnabled(true);
			
			//m_checkSubcontratados.setEnabled(false);
			//m_checkSubcontratados.setSelected(true);
			
			jRadioButton1.setSelected(false);
			jRadioButton2.setSelected(true);
			
			m_txtSubcontratado.setEnabled(true);
			lblPrecioSubcontratado.setEnabled(true);
			m_txtPrecioSubcontratado.setEnabled(true);
			
			btnBuscar.setEnabled(false);
			btnDescripcionDetallada.setEnabled(true);
			invalidate();
		}
		
		private void deshabilitarParaFliaArmado(){
			//lblServ.setEnabled(true);
			lblServSubcontratado.setEnabled(false);
			lblServicio.setEnabled(true);
			
			//m_checkSubcontratados.setEnabled(true);
			
			//m_cmbServicios.setEnabled(true);
			
			m_txtSubcontratado.setEnabled(false);
			m_txtSubcontratado.setText("");
			lblPrecioSubcontratado.setEnabled(true);
			m_txtPrecioSubcontratado.setEnabled(true);
			
			//m_txtCodigo.setEnabled(true);
			btnDescripcionDetallada.setEnabled(false);
			invalidate();
		}
		
		private void habilitarParaOtraFlia(){
			if(lblServSubcontratado!= null){
				lblServSubcontratado.setEnabled(false);
				
				//m_checkSubcontratados.setEnabled(true);

				m_txtSubcontratado.setEnabled(false);
				m_txtSubcontratado.setText("");
				lblPrecioSubcontratado.setEnabled(false);
				m_txtPrecioSubcontratado.setEnabled(false);
				btnBuscar.setEnabled(true);

				btnDescripcionDetallada.setEnabled(false);
				invalidate();
			}
		}
		
		/**
		 * Calcula la cantidad de dias en que se usará la sala
		 * @return cant de dias.
		 */
		public int getPeriodoSala(){
			DateDiff diff=null;
			if(salasPanel.getModel().getFechaDeInicio()!=null && salasPanel.getModel().getFechaDeFinalizacion()!= null){
				diff = DateDiff.calcularFechas(salasPanel.getModel().getFechaDeInicio(),salasPanel.getModel().getFechaDeFinalizacion());
				return diff.getDayOnly()+1;
			}
			else
				return 1;
			
		}

		public SalaServicioItem getServicio() {
			return servicio;
		}

		public void setServicio(SalaServicioItem servicio) {
			this.servicio = servicio;

		}
		
		public boolean isCancelado() {
			return cancelado;
		}


		public boolean isValidServicio(){
			ErrorList errors = validateServicio();
			if (!errors.isEmpty()){
				String msg = "Complete los siguientes errores para continuar";
				ErrorMessageBuilder.createErrorMessage(this, msg, errors);
			}
			return errors.isEmpty();
		}
		
		/**
		 * Carga todas las propiedades ingresadas por el usuario en el servicio
		 *
		 */
		private void populateServicio() {
			servicio.setCantidad(Integer.parseInt(m_txtCantidad.getText()));
			servicio.setServicioCodigo(servicio.getServicioCodigo());
			servicio.setFamilia(servicio.getFamilia());
			servicio.setFamiliaCodigo(servicio.getFamiliaCodigo());
		
			//servicio.setSubContratado(m_checkSubcontratados.isSelected());	
			servicio.setOpcional(m_checkOpcional.isSelected());
			
			servicio.setDias(Integer.parseInt(m_txtDias.getText()));

			servicio.setMesEvento(mesEvento);
			servicio.setFechaEvt(fechaEvt);
			
			if (isFliaSubcontratada(servicio.getFamiliaCodigo())){
				servicio.setServicio(m_txtSubcontratado.getText());
				servicio.setTotal(Double.parseDouble(m_txtPrecioSubcontratado.getText()));
				servicio.setPrecioLista(Double.parseDouble(m_txtPrecioSubcontratado.getText()));
				servicio.setDescDetallada(idiomaDescripciones);
			}
			else if(isFliaArmadoDiaPrevio(servicio.getFamiliaCodigo()) && !m_txtPrecioSubcontratado.getText().equals("")){
				servicio.setTotal(Double.parseDouble(m_txtPrecioSubcontratado.getText()));
				servicio.setPrecioLista(Double.parseDouble(m_txtPrecioSubcontratado.getText()));
				servicio.setServicio(servicio.getServicio());
			}
			else {
				servicio.setServicio(servicio.getServicio());
				servicio.calcularTotales();
			}	
			salasPanel.addServicio(servicio, true);
		}
		
		private void cargarDescDetallada(String codServicio){
			try{
				vistaPrevia.setText("");
				ServicioDescripcion[] descripciones= ServicioDescripcionManager.instance().findByServicio(codServicio, "1");	
				for(int i=0; i<descripciones.length; i++){
					vistaPrevia.setText(vistaPrevia.getText()+"• "+descripciones[i].getDescripcion());
				}	
			}
			catch(Exception e){
				Util.errMsg(getContentPane(),"Error al cargar datos externos de descripciones \ndetalladas de servicios",e);
			}
		}
		
		private void cargarDescDetalladaSubcontratado(List descripciones){
			vistaPrevia.setText("");
			if(descripciones != null){
				Iterator it = descripciones.iterator();
				while(it.hasNext()){
					vistaPrevia.setText(vistaPrevia.getText()+"• "+(String)it.next());
				}
			}
		}
		
		/*public SalaServicioItem getServicioOriginal() {
			return servicioOriginal;
		}*/
		
		protected void editarDescripcion() {
			String idioma = "1"; //por ahora solo en castellano
			String titulo = m_txtSubcontratado.getText();
			String descripcion = m_txtSubcontratado.getText();
			
			if(!StringUtils.isBlank(idioma) && !StringUtils.isBlank(titulo)){			
				
				ServicioIdiomaHelper id = getServicioIdiomaHelper(idioma);
				PopupEditorHTML popup = new PopupEditorHTML(Main.getVentana(),titulo,id.getDescriptionLines());
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
			else MessageUtil.showErrorMessage("Escriba el título del servicio subcontratado");
		}

	    private void addServicioIdiomaHelper(String idioma,String titulo,String descripcion,List descriptionLines){
	    	ServicioIdiomaHelper id = getServicioIdiomaHelper(idioma);    	
	    	id.setTitulo(titulo);    	
	    	id.setDescripcion(descripcion);    	    	
	    	id.setDescriptionLines(descriptionLines);
	    	idiomaDescripciones = id.getDescriptionLines();
	    }
	    
	    private ServicioIdiomaHelper getServicioIdiomaHelper(String idioma){
	    	ServicioIdiomaHelper id = new ServicioIdiomaHelper(idioma,null,null,null);
	    	if(idiomaDescripciones != null && idiomaDescripciones.size() > 0){
	    		id.setDescriptionLines(idiomaDescripciones);
	    	}

	    	return id;
	    }
		
	    public void cargarServicioExistente(){
			if (servicio.getCantidad()>0)
				m_txtCantidad.setText(Integer.toString(servicio.getCantidad()));
			
			if (servicio.getDias()>0)
				m_txtDias.setText(Integer.toString(servicio.getDias()));
			else
				m_txtDias.setText(Integer.toString(getPeriodoSala()));
			
			//m_checkSubcontratados.setSelected(servicio.isSubContratado());
			m_checkOpcional.setSelected(servicio.isOpcional());
			
			 //**En caso que sea edición de servicio
			//servicio comun
			if (servicio.getServicioCodigo() != null){ 
				jLabel1.setText(servicio.getServicio());
				cargarDescDetallada(servicio.getServicioCodigo());
			}
			//servicio subcontratado, familia 1
			if (servicio.getServicioCodigo() != null && servicio.getFamiliaCodigo().equals(FamiliaServManagerSEI.FAMILIA_SERVICIO_SUBCONTRATADO)){
				
				m_txtSubcontratado.setText(servicio.getServicio());
				m_txtPrecioSubcontratado.setText(String.valueOf(servicio.getPrecioLista()));			
				cargarDescDetalladaSubcontratado(servicio.getDescDetallada());
				deshabilitarParaFliaSubcont();
			}
			//servicio de armado, familia 72
			else if(servicio.getServicioCodigo() != null && servicio.getFamiliaCodigo().equals(FamiliaServManagerSEI.FAMILIA_SERVICIO_ARMADO)){
				
				m_txtPrecioSubcontratado.setText(String.valueOf(servicio.getPrecioLista()));
				cargarDescDetallada(servicio.getServicioCodigo());
				deshabilitarParaFliaArmado();
			}	
		}
	    
	    public void isColdUser(boolean isCold){
	    	/*if(m_cmbFamilias != null)
	    		m_cmbFamilias.setEnabled(!isCold);
	    	if(m_cmbServicios != null)
	    		m_cmbServicios.setEnabled(!isCold);*/
	    	if(m_txtCantidad != null)
	    		m_txtCantidad.setEnabled(!isCold);
	    	//if(m_txtCodigo != null)
	    		//m_txtCodigo.setEnabled(!isCold);
	    	if(m_txtDias != null)
	    		m_txtDias.setEnabled(!isCold);
	    }
	    
	    //*********************************ACCIONES***********************************************
	    private class SalvarServicioAction implements ActionListener {
			public void actionPerformed (ActionEvent evt) {

				if (isValidServicio()){
					
					populateServicio();

					cancelado = false;
					setVisible(false);			
				}

			}

			
		}
		
		private BuscadorServicios buscador; 
		private class EditarAccesoriosAction implements ActionListener {
			public void actionPerformed (ActionEvent evt) {
				if (buscador == null){
					buscador = new BuscadorServicios(Main.getVentana(), PantallaAgregarServicio.this);
					buscador.init();
					buscador.initComponent();
				}
				buscador.setVisible(true);
			}
		}
		
		private class AgregarDescripcionAction implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {	
				idiomaDescripciones = servicio.getDescDetallada();
				editarDescripcion();
				cargarDescDetalladaSubcontratado(idiomaDescripciones);
				populateServicio();
			}
			
		}
		
		/**
		 * Cancela la edicion de un servicio
		 * @author hernux
		 *
		 */
		private class CancelarAction implements ActionListener {
			public void actionPerformed (ActionEvent evt) {
				if (MessageUtil.showYesNoMessage(PantallaAgregarServicio.this, "¿Desea salir sin modificar el servicio?", "Salir")){
					cancelado = true;
					setVisible(false);
				}
			}
		}
		
		/*private class SubcontratarAction implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				if(m_checkSubcontratados.isSelected()){
					MessageUtil.showMessage(PantallaAgregarServicio.this, "El servicio va a ser subcontratado");				
				}
			}
			
		}*/
		
		private class OpcionalAction implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				if(m_checkOpcional.isSelected()){
					MessageUtil.showMessage(PantallaAgregarServicio.this, "El servicio va a ser opcional y no se sumará al total del evento");				
				}
			}
			
		}
		
		private class ServicioPropioChangeListener implements ChangeListener{

			public void stateChanged(ChangeEvent e) {
				if(jRadioButton1.isSelected()){
					habilitarParaOtraFlia();
				}
				
			}
			
		}
		
		private class ServicioSubcontratadoChangeListener implements ChangeListener{

			public void stateChanged(ChangeEvent e) {
				if(jRadioButton2.isSelected()){
					deshabilitarParaFliaSubcont();
					servicio.setFamiliaCodigo(FamiliaServManagerSEI.FAMILIA_SERVICIO_SUBCONTRATADO);
					servicio.setFamilia("Subcontratado");
					servicio.setServicioCodigo("1");
					servicio.setSubContratado(true);
				}
				
			}
			
		}
}
