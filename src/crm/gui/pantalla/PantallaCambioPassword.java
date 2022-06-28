package crm.gui.pantalla;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

import crm.client.managers.PrtPptoSignatureManager;
import crm.client.managers.UsuarioManager;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.CustomTextField;
import crm.gui.components.PanelImagen;
import crm.libraries.abm.entities.PrtPptoSignature;
import crm.libraries.abm.entities.Usuario;
import crm.libraries.util.MessageUtil;

public class PantallaCambioPassword extends PantallaEmergente{
	
	private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private JFormattedTextField jFormattedTextField1;
    private JFormattedTextField jFormattedTextField3;
    private JFormattedTextField jFormattedTextField4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea jTextArea1;
    
    private Usuario user;
    private PrtPptoSignature firma; 
    
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    
    private PanelImagen panel;
    
    public PantallaCambioPassword(Frame p, Usuario usuario){		
		super("Cambio de Password y firma",p);
        user = usuario;        
	}
    
    public void init() {
    	panel = new PanelImagen();
		panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		try{
			panel = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField1 = CustomTextField.getRegularStringInstance(16);
        jFormattedTextField1.setEnabled(false);
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextField3 = CustomTextField.getRegularStringInstance(16);
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextField4 = CustomTextField.getRegularStringInstance(16);
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Cambio de Password y firma de presupuestos");

        jLabel2.setText("Password Actual");

        jLabel4.setText("Nuevo password (Minimo 8 caracteres)");

        jLabel5.setText("Ingrese nueva password nuevamente");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel8.setText("Firma de presupuestos");

        jButton1.setIcon(new javax.swing.ImageIcon(getUrlImagen("disk.png")));
        jButton1.setText("Guardar");

        jButton2.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        jButton2.setText("Salir");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jLabel1)
                            .add(layout.createSequentialGroup()
                                .add(jLabel2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 283, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(jLabel4)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jFormattedTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 285, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jSeparator1)
                            .add(layout.createSequentialGroup()
                                .add(jLabel5)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jFormattedTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 299, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jSeparator2)
                            .add(layout.createSequentialGroup()
                                .add(jLabel8)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 376, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jSeparator3)))
                    .add(layout.createSequentialGroup()
                        .add(168, 168, 168)
                        .add(jButton1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton2)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jFormattedTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jFormattedTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(34, 34, 34)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel8)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(7, 7, 7)
                .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton2)
                    .add(jButton1))
                .addContainerGap())
        );
        
        this.getContentPane().add(panel);
        this.pack();
        
        loadInfo();
        createListeners();
        updatePosition();
    }
    
    private void loadInfo(){
    	jFormattedTextField1.setText(user.getPassword());
    	try {
    		firma = PrtPptoSignatureManager.instance().getById(user.getCodigo());
    		if(firma != null)
    			jTextArea1.setText(firma.getDescripcion());
		} catch (RemoteException e) {
			Util.errMsg(PantallaCambioPassword.this, "Error al cargar datos externos.", e);
		}
    	
    }
    
    public void updatePosition(){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation((screenSize.width - this.getWidth())/2,
				(screenSize.height - this.getHeight())/2);
		
	}
    
    private void createListeners(){
    	jButton1.addActionListener(new GuardarActionListener());
    	jButton2.addActionListener(new SalirActionListener());
    }
    
    private boolean isValidPassword(String pass){
    	if(pass.length()>0)
    		return user.getPassword().equals(pass);
    	else return false;
    }
    
    private boolean isEqualsPasswords(){
    	return jFormattedTextField3.getText().equals((jFormattedTextField4.getText()));
    }   
    
    //**********************************ACCIONES*****************************************
    private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			panel.updateUI();
			if (MessageUtil.showYesNoMessage(PantallaCambioPassword.this, "¿Desea salir del cambio de password?", "Salir")){
				setVisible(false);
			}
		}
		
	}
    
    private class GuardarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(PantallaCambioPassword.this, "¿Desea guardar el cambio realizado?", "Guardar")){
				if(isValidPassword(jFormattedTextField1.getText()) && isEqualsPasswords()){
					
					String passViejo = new String (jFormattedTextField1.getText());
					String passNuevo = new String (jFormattedTextField4.getText()); 
					String descripcion = jTextArea1.getText();   
			        
			        if ((passViejo == null || passViejo.length() == 0)){            
			        	Util.errMsg(Main.getVentana(), "Tiene datos obligatorios por completar", null);
			            return;
			        }
			        try {
			        	if(passNuevo.length() < 8 && passNuevo.length() > 0){
			        		Util.errMsg(Main.getVentana(), "La contraseña debe tener al menos 8 caracteres", null);			            	
			        	}
			        	else if(passNuevo.length() >= 8){
			        		user.setPassword(jFormattedTextField3.getText());
			        		UsuarioManager.instance().update(user);
			        	}
			        	if(descripcion == null || descripcion.length() == 0){
			        		firma.setDescripcion(descripcion);						
			        		PrtPptoSignatureManager.instance().update(firma);
			        	}
			        	Util.alertMsg(PantallaCambioPassword.this, "La contraseña fue guardada exitosamente");
						setVisible(false);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}		
				else if(!isEqualsPasswords()){
					Util.errMsg(Main.getVentana(), "Las nuevas contraseñas no coinciden", null);
				}
			}
		}
    	
    }
    
    private class RefreshActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			String code = user.getCodigo();
			try {
				user = UsuarioManager.instance().getUsuarioById(code);
				firma = PrtPptoSignatureManager.instance().getById(code);
				loadInfo();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
    	
    }
}
