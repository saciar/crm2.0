package crm.gui.abms;

import java.rmi.RemoteException;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.apache.wsif.WSIFException;

import crm.client.managers.PrtPptoSignatureManager;
import crm.client.managers.UsuarioManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.abm.ReactiveEntityQuestion;
import crm.gui.abms.busquedas.ABMUnidadesComercialesBusqueda;
import crm.gui.abms.busquedas.ABMUsuariosBusqueda;
import crm.gui.components.ABMAccesosMultiBox;
import crm.gui.components.ABMPerfilesComboBox;
import crm.gui.components.CustomTextField;
import crm.libraries.abm.entities.PrtPptoSignature;
import crm.libraries.abm.entities.Usuario;

public class ABMUsuarios extends ABMGeneral{
	
	private ABMPerfilesComboBox jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private JPasswordField jFormattedTextField4;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private ABMAccesosMultiBox jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JFormattedTextField jFormattedTextField5;
	
    private JPanel panel;
    private Usuario usuario;
    private PrtPptoSignature model;  
    private String entidadBuscada;
    
    public ABMUsuarios(JPanel pan){
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
        jFormattedTextField4 = CustomTextField.getPasswordInstance(16);
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new ABMAccesosMultiBox();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new ABMPerfilesComboBox();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de un nuevo usuario");

        jLabel2.setText("Nombre y apellido");

        jLabel3.setText("Email");

        jLabel4.setText("Login name");

        jLabel5.setText("Password");

        jLabel6.setText("Perfil");

        jScrollPane1.setViewportView(jList1);

        jLabel7.setText("Accesos a ABM'S");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel8.setText("Firma de presupuestos");
        
        jComboBox1.loadItems();
        
        jList1.loadItems();
        
        jLabel9.setText("Descuento permitido (%)");
        
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
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                    .add(jLabel1)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 283, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(jLabel4)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jFormattedTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 285, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel5)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jFormattedTextField4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                            .add(layout.createSequentialGroup()
                                .add(jLabel3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jFormattedTextField2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))))
                    .add(layout.createSequentialGroup()
                        .add(jLabel6)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 219, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel9)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jFormattedTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 219, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jLabel7)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 226, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel8)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)))
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
                    .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jFormattedTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jFormattedTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5)
                    .add(jFormattedTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel9)
                    .add(jFormattedTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .add(jLabel7)
                    .add(jLabel8)
                    .add(jScrollPane2))
                .add(7, 7, 7)
                .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(193, Short.MAX_VALUE))
        );
    }
	
	public void guardar() {
	    String email = new String (jFormattedTextField2.getText());
        String nomyAp = new String (jFormattedTextField1.getText());        
        String pass = new String (jFormattedTextField4.getText());
        String log = new String (jFormattedTextField3.getText());
        Integer limite=new Integer(jFormattedTextField5.getText());
        String perfil = jComboBox1.searchForeign();
        String accesoIds = jList1.searchForeigns();
        
    	String descripcion = this.jTextArea1.getText();   
        
        if ((nomyAp == null || nomyAp.length() == 0) ||
        	(email == null || email.length() == 0) ||
        	(pass == null || pass.length() == 0) || 
        	(log == null || log.length() == 0) || 
        	(perfil == null || perfil.length() == 0 ||
        	(descripcion == null || descripcion.length() == 0)) ){            
        	Util.errMsg(Main.getVentana(), "Tiene datos obligatorios por completar", null);
            return;
        }
        if(pass.length() < 8){
        	Util.errMsg(Main.getVentana(), "La contraseña debe tener al menos 8 caracteres", null);
            return;
        }
        if(!Util.isMail(email)){
        	
        	Util.errMsg(Main.getVentana(),"Debe ingresar un E-mail valido", null);
            return;
        }
        try {
        	boolean canEdit = true;
        	//verifico si existe
        	Usuario[] temps = UsuarioManager.instance().findByField("loginName",log);
        	if(temps != null && temps.length > 0){
        		for(int i = 0; i < temps.length;i++){
        			if(!temps[i].getCodigo().equals(this.usuario.getCodigo())){
        				canEdit = false;
        			}
        		}        		
        		if(!canEdit){
        			for(int i = 0; i < temps.length;i++){
        				if(temps[i].getActivo().equals("S")){              				
        					Util.errMsg(Main.getVentana(),"Ya existe un Usuario con este login", null);
        					return;
        				}
        			}                		
        			int result = showExistEntityDialog(temps);
        			if(result < 1){
        				return;
        			}
        			this.usuario = temps[result - 1];
        			this.usuario.setActivo("S");
        		}
        	}
        	if(canEdit){
        		this.usuario.setEmail(email);
        		this.usuario.setLoginName(log);
        		this.usuario.setPassword(pass);
        		this.usuario.setApellidoYNombre(nomyAp);
        		this.usuario.setPerfil(perfil);	        
        		this.usuario.setLimite_descuento(limite);
        		
        		this.model.setDescripcion(descripcion);    			
        	}
	        UsuarioManager.instance().update(this.usuario);
	        PrtPptoSignatureManager.instance().update(this.model);	
		} catch (WSIFException e) {			
			e.printStackTrace();
		}    
         catch (RemoteException e) {		
			e.printStackTrace();
		}       
		setAddMode();
        
	}
    
    protected void setAddMode(){
    	this.usuario = new Usuario("","","","","","","S");
    	usuario.setLimite_descuento(0);
    	this.model = new PrtPptoSignature();
    	resetFields();
    }
    
    protected void setEditMode(String entityId){
    	 try { 		 
    		 this.usuario = UsuarioManager.instance().getUsuarioById(entityId); 
    		 this.model = PrtPptoSignatureManager.instance().getById(entityId);   
    		 resetFields();	 
    	 }catch (RemoteException e) {		
			e.printStackTrace();
		}
        
    } 
    
    protected void resetFields(){
    	jFormattedTextField3.setText(this.usuario.getLoginName());
    	jFormattedTextField4.setText(this.usuario.getPassword());
    	jFormattedTextField2.setText(this.usuario.getEmail());
    	jFormattedTextField1.setText(this.usuario.getApellidoYNombre());
    	jFormattedTextField5.setText(this.usuario.getLimite_descuento().toString());
    	jComboBox1.setForeign(this.usuario.getPerfil());
    	jTextArea1.setText(this.model.getDescripcion());
    	/*if(this.usuario.getAccesosString() != null){
    		jList1.setForeigns(this.usuario.getAccesosString().split(","));
    	}else{
    		jList1.setForeigns(null);
    	}*/
    }
    
    private int showExistEntityDialog(Usuario[] usuarios){
		String message = "Se encontro un registro inactivo con este login o email";		
		String[] headers = new String[]{"Codigo","Apellido y Nombre","Login","E-mail"};
				
		String[][] data = new String[usuarios.length][4];
		for(int i = 0;i < usuarios.length;i ++){
			data[i][0] = usuarios[i].getCodigo();
			data[i][1] = usuarios[i].getApellidoYNombre();
			data[i][2] = usuarios[i].getLoginName();
			data[i][3] = usuarios[i].getEmail();
			
		}
		return new ReactiveEntityQuestion(Util.getOpennerComponent(panel),message,headers,data).showDialog();
	}
    
    public void buscar(){
		ABMUsuariosBusqueda busq = new ABMUsuariosBusqueda(Main.getVentana());
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
}
