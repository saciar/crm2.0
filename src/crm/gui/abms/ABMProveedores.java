package crm.gui.abms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JPanel;

import org.apache.wsif.WSIFException;

import crm.client.managers.ProveedorManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.abm.ReactiveEntityQuestion;
import crm.gui.abms.busquedas.ABMProveedoresBusqueda;
import crm.gui.components.ABMCodigosPostalesComboBox;
import crm.gui.components.ABMLocalidadesComboBox;
import crm.gui.components.ABMPaisesComboBox;
import crm.gui.components.ABMPartidosComboBox;
import crm.gui.components.ABMProvinciasComboBox;
import crm.libraries.abm.entities.Proveedor;

public class ABMProveedores extends ABMGeneral{    
    private ABMCodigosPostalesComboBox jComboBox5;
	private ABMLocalidadesComboBox jComboBox4;
	private ABMPartidosComboBox jComboBox3;	
	private ABMProvinciasComboBox jComboBox2;	
	private ABMPaisesComboBox jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField10;
    private javax.swing.JFormattedTextField jFormattedTextField11;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JFormattedTextField jFormattedTextField5;
    private javax.swing.JFormattedTextField jFormattedTextField6;
    private javax.swing.JFormattedTextField jFormattedTextField7;
    private javax.swing.JFormattedTextField jFormattedTextField8;
    private javax.swing.JFormattedTextField jFormattedTextField9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    
    private JPanel panel;
    private Proveedor proveedor;
    private String entidadBuscada;
    
    private String paisId;
    private String provinciaId;
    private String partidoId;
    private String localidadId;
    
    public ABMProveedores(JPanel pan){
    	panel = pan;
    }
	
	public void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jFormattedTextField9 = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jFormattedTextField10 = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jFormattedTextField11 = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de un nuevo proveedor");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Email");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Tel.1");

        jLabel5.setText("Tel.2");

        jLabel6.setText("NEXTEL");

        jLabel7.setText("*");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Nombre del contacto");

        jLabel9.setText("Domicilio");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Calle");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Nro.");

        jLabel12.setText("Piso");

        jLabel13.setText("Depto.");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel14.setText("Pais");        

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel15.setText("Provincia");        

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel16.setText("Partido");        

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11));       

        jLabel17.setText("Localidad");       

        jLabel18.setText("CP");   
        
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel19.setText("Nota: los datos obligatorios estan en negrita.");
        
        initLocationFields();
        
        setAddMode();
	}
	
	public void initLayout() {
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                        .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                        .add(jLabel1)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .add(jLabel4)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jFormattedTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 171, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel5)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jFormattedTextField4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                                .add(layout.createSequentialGroup()
                                    .add(jLabel2)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(jLabel3)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jFormattedTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 336, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(layout.createSequentialGroup()
                                    .add(jLabel6)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jFormattedTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel7)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jFormattedTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 68, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                        .add(layout.createSequentialGroup()
                            .add(jLabel8)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jFormattedTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 663, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jLabel9)
                        .add(layout.createSequentialGroup()
                            .add(jLabel10)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jFormattedTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 484, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel11)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jFormattedTextField9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(12, 12, 12)
                            .add(jLabel12)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jFormattedTextField10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(13, 13, 13)
                            .add(jLabel13)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jFormattedTextField11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createSequentialGroup()
                            .add(jLabel14)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel15)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 210, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel16)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jComboBox3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 217, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createSequentialGroup()
                            .add(jLabel17)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jComboBox4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 216, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel18)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jComboBox5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 215, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jLabel19))
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
                        .add(jFormattedTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel3)
                        .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel4)
                        .add(jFormattedTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel5)
                        .add(jFormattedTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel6)
                        .add(jFormattedTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel7)
                        .add(jFormattedTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel8)
                        .add(jFormattedTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel9)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel10)
                        .add(jLabel11)
                        .add(jFormattedTextField9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel12)
                        .add(jFormattedTextField10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel13)
                        .add(jFormattedTextField11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jFormattedTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel14)
                        .add(jLabel15)
                        .add(jLabel16)
                        .add(jComboBox3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel17)
                        .add(jComboBox4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel18)
                        .add(jComboBox5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel19)
                    .addContainerGap(264, Short.MAX_VALUE))
            );
    }
	
	public void guardar() {		
		String nom = jFormattedTextField1.getText();
		String cont = jFormattedTextField7.getText();
		String email = jFormattedTextField2.getText();
		String tel1 = jFormattedTextField3.getText();
		String tel2 = jFormattedTextField4.getText();
		String flNex = jFormattedTextField5.getText();
		String idNex = jFormattedTextField6.getText();
		///String fax = m_txtFax.getText();
		String calle = jFormattedTextField8.getText();
		String nro = jFormattedTextField9.getText();
		String piso = jFormattedTextField10.getText();
		String dpto = jFormattedTextField11.getText();
		String cp = jComboBox5.searchForeign();
		String loc = jComboBox4.searchForeign();
		String part = jComboBox3.searchForeign();
		String prov = jComboBox2.searchForeign();
		String pais = jComboBox1.searchForeign();

		if ( nom == null || nom.length() == 0 || cont == null || cont.length() == 0
				|| email == null || email.length() == 0 || tel1 == null
				|| tel1.length() == 0 //|| tel2 == null || tel2.length() == 0
				//|| flNex == null || flNex.length() == 0 || idNex == null
				//|| idNex.length() == 0 || fax == null || fax.length() == 0
				|| calle == null || calle.length() == 0 || nro == null || nro.length() == 0
				//|| piso == null || piso.length() == 0 || dpto == null
				/*|| dpto.length() == 0*/ || cp == null || cp.length() == 0
				|| loc == null || loc.length() == 0 || part == null
				|| part.length() == 0 || prov == null || prov.length() == 0
				|| prov == null || prov.length() == 0 || pais == null
				|| pais.length() == 0) {

			// Modal dialog with OK button
			Util.errMsg(Main.getVentana(), "Tiene datos obligatorios por completar", null);
			return;
		}


        try {	        
        	boolean canEdit = true;
        	//verifico si existe
        	Proveedor p = ProveedorManager.instance().getProveedorByNombre(nom);
        	if(p != null){
        		if(!p.getCodigo().equals(this.proveedor.getCodigo())){
        			canEdit = false;
        		}
        		if(!canEdit){
        			if(p.getActivo().equals("S")){        		
        				Util.errMsg(Main.getVentana(),"Ya existe un Proveedor con este nombre", null);
        				return;
        			}else{        			
        				if(!showExistEntityDialog(p)){
        					return;
        				}
        				this.proveedor = p;
        				this.proveedor.setActivo("S");
        			}
        		}
        	}        		
        	if(canEdit){             	
        		this.proveedor.setNombre(nom);
        		this.proveedor.setContacto(cont);        	
        		this.proveedor.setEmail(email);
        		this.proveedor.setTelefono1(tel1);
        		if(tel2.equals(""))
        			this.proveedor.setTelefono2(null);
        		else this.proveedor.setTelefono2(tel2);
        		if (flNex.equals(""))
        			this.proveedor.setFlotaNextel(null);
        		else this.proveedor.setFlotaNextel(flNex);
        		
        		if(idNex.equals(""))
        			this.proveedor.setIdNextel(null);
        		else this.proveedor.setIdNextel(idNex);
        		this.proveedor.setFax(null);
        		this.proveedor.setCalle(calle);        		
        		this.proveedor.setNumero(nro);
        		if( piso.equals(""))
        			this.proveedor.setPiso(null);
        		else this.proveedor.setPiso(piso);
        		if (dpto.equals(""))
        			this.proveedor.setDepartamento(null);
        		else this.proveedor.setDepartamento(dpto);
        		this.proveedor.setCodigoPostal(cp);
        		this.proveedor.setLocalidad(loc);
        		this.proveedor.setPartido(part);
        		this.proveedor.setProvincia(prov);
        		this.proveedor.setPais(pais);			
        	}
			ProveedorManager.instance().update(this.proveedor);			
		} catch (WSIFException e) {			
			e.printStackTrace();
		}    
         catch (RemoteException e) {		
			e.printStackTrace();
		}		
        setAddMode();
    }
	
	private void initLocationFields(){
    	this.jComboBox5 = new ABMCodigosPostalesComboBox();
    	this.jComboBox1 = new ABMPaisesComboBox();
    	this.jComboBox2 = new ABMProvinciasComboBox();
    	this.jComboBox3 = new ABMPartidosComboBox();
    	this.jComboBox4 = new ABMLocalidadesComboBox();
    	
    	jComboBox1.loadItems();
    	
    	jComboBox2.setEnabled(false);
    	jComboBox3.setEnabled(false);
    	jComboBox4.setEnabled(false);
    	jComboBox5.setEnabled(false);
    	
    	createLocationListeners();
    }
    
    private void createLocationListeners(){   	
    	this.jComboBox1.addActionListener(new PaisActionListener());    	
    	this.jComboBox2.addActionListener(new ProvinciaActionListener());    	
    	this.jComboBox3.addActionListener(new PartidoActionListener());
    	this.jComboBox4.addActionListener(new LocalidadActionListener());
    }   
	
	private boolean showExistEntityDialog(Proveedor entity){
		String message = "Se encontro un registro inactivo con este nombre";
		String[] headers = new String[]{"Codigo","Nombre"};
		String[][] data = new String[][]{{entity.getCodigo(),entity.getNombre()}};		
		return (new ReactiveEntityQuestion(Util.getOpennerComponent(panel),message,headers,data).showDialog() > 0);
	}
    
    private void setAddMode(){
    	this.proveedor = new Proveedor();    	
    	resetFields();
    }
    
    public void setEditMode(String entityId){
    	 try {    		  		
    		 this.proveedor = ProveedorManager.instance().getProveedorById(entityId);    	
    		 resetFields();	 
    	 }catch (RemoteException e) {		
			e.printStackTrace();
		}
        
    }    
    
    private void resetFields(){
    	
    	jFormattedTextField1.setText(this.proveedor.getNombre());
    	jFormattedTextField7.setText(this.proveedor.getContacto());
    	jFormattedTextField2.setText(this.proveedor.getEmail());
    	jFormattedTextField3.setText(this.proveedor.getTelefono1());
    	jFormattedTextField4.setText(this.proveedor.getTelefono2());
    	jFormattedTextField5.setText(this.proveedor.getFlotaNextel());
    	jFormattedTextField6.setText(this.proveedor.getIdNextel());
		jFormattedTextField8.setText(this.proveedor.getCalle());
		jFormattedTextField9.setText(this.proveedor.getNumero());
		jFormattedTextField10.setText(this.proveedor.getPiso());
		jFormattedTextField11.setText(this.proveedor.getDepartamento());
		jComboBox1.setForeign(this.proveedor.getPais());
		jComboBox2.setForeign(this.proveedor.getProvincia());
		jComboBox3.setForeign(this.proveedor.getPartido());
		jComboBox4.setForeign(this.proveedor.getLocalidad());
		jComboBox5.setForeign(this.proveedor.getCodigoPostal());
    }
    
    public void buscar(){
		ABMProveedoresBusqueda busq = new ABMProveedoresBusqueda(Main.getVentana());
		busq.initComponents();
		busq.setVisible(true);
		entidadBuscada  = busq.getCodEntidadElegido();
		if(entidadBuscada != null){
			ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_PPTO_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {
					setEditMode(entidadBuscada);
					ProgressDialogUtil.closeProcessDialog();
				}
			}).start();
		}
		else
			setAddMode();
	}
    
    //**************************ACCIONES*************************************************
    
    private class PaisActionListener implements ActionListener{
    	public void actionPerformed(ActionEvent arg0) {				
			if(jComboBox1.getSelectedIndex() > 0){		
				String newId = jComboBox1.searchForeign();
				if(paisId == null || !paisId.equals(newId)){
					jComboBox2.loadItems(newId);
					jComboBox3.resetFields();
					jComboBox4.resetFields();				
				}
				paisId = newId;
				jComboBox2.setEnabled(true);
			}
			else 
				jComboBox2.setEnabled(false);				
		}	
    }
    
    private class ProvinciaActionListener implements ActionListener{
    	public void actionPerformed(ActionEvent arg0) {
			if(jComboBox2.getSelectedIndex() > 0){
				String newId = jComboBox2.searchForeign();
				if(provinciaId  == null ||  !provinciaId.equals(newId)){
					jComboBox3.loadItems(newId);
					jComboBox4.resetFields();
				}
				provinciaId = newId;
				jComboBox3.setEnabled(true);
			}
			else jComboBox3.setEnabled(false);
		}
    }
    
    private class PartidoActionListener implements ActionListener{
    	public void actionPerformed(ActionEvent arg0) {
			if(jComboBox3.getSelectedIndex() > 0){
				String newId = jComboBox3.searchForeign();
				if(partidoId == null || !partidoId.equals(newId)){					
					jComboBox4.loadItems(jComboBox3.searchForeign());
				}
				partidoId = newId;
				jComboBox4.setEnabled(true);
			}
			else jComboBox4.setEnabled(false);
		}
    }
    
    private class LocalidadActionListener implements ActionListener{
    	public void actionPerformed(ActionEvent arg0) {
			if(jComboBox4.getSelectedIndex() > 0){
				String newId = jComboBox4.searchForeign();
				if(localidadId == null || !localidadId.equals(newId)){					
					jComboBox5.loadItems(jComboBox4.searchForeign());
				}
				localidadId = newId;
				jComboBox5.setEnabled(true);
			}
			else jComboBox5.setEnabled(false);
		}
    }
    
}
