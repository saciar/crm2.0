package crm.gui.abms;

import java.rmi.RemoteException;

import javax.swing.JPanel;

import org.apache.wsif.WSIFException;

import crm.client.managers.PaisManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.abm.ReactiveEntityQuestion;
import crm.gui.abms.busquedas.ABMPaisesBusqueda;
import crm.gui.abms.busquedas.ABMUsuariosBusqueda;
import crm.libraries.abm.entities.Pais;

public class ABMPaises extends ABMGeneral{
	private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    
    private JPanel panel;
    private Pais pais;
    private String entidadBuscada;
    
    public ABMPaises(JPanel pan){
    	panel = pan;
    }
    
	public void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de un nuevo pa\u00eds");

        jLabel2.setText("Ingrese el nombre del Pais");
        
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
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)))
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
                    .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(476, Short.MAX_VALUE))
        );
    }
	
	public void guardar() {
        String desc = new String(jFormattedTextField1.getText());
        
        if ((desc == null || desc.length() == 0)){
            // Modal dialog with OK button
        	Util.errMsg(Main.getVentana(), "Tiene datos obligatorios por completar", null);
            return;
        }
        try {
        	boolean canEdit = true;
        	//verifico si existe
        	Pais p = PaisManager.instance().getPaisByDescripcion(desc);
        	if(p != null){
        		if(!p.getCodigo().equals(this.pais.getCodigo())){
        			canEdit = false;
        		}
        		if(!canEdit){
        			if(p.getActivo().equals("S")){        		
        				Util.errMsg(Main.getVentana(), "Ya existe un país con esta descripción", null);
        				return;
        			}else{        			
        				if(!showExistEntityDialog(p)){
        					return;
        				}
        				this.pais = p;
        				this.pais.setActivo("S");
        			}
        		}
        	}        		
        	if(canEdit){             	
        		this.pais.setDescripcion(desc);
        	}
			PaisManager.instance().update(this.pais);			
		} catch (WSIFException e) {			
			e.printStackTrace();
		}    
         catch (RemoteException e) {		
			e.printStackTrace();
		}        
		setAddMode();	
        
    }
	
	private boolean showExistEntityDialog(Pais entity){
		String message = "Se encontro un registro inactivo con esta descripcion";
		String[] headers = new String[]{"Codigo","Descripcion"};
		String[][] data = new String[][]{{entity.getCodigo(),entity.getDescripcion()}};		
		return (new ReactiveEntityQuestion(Util.getOpennerComponent(panel),message,headers,data).showDialog() > 0);
	}
	
	
    private void setAddMode(){
    	this.pais = new Pais("","","S");    	
    	resetFields();
    }
    
    public void setEditMode(String entityId){
    	 try {    		  		
    		 this.pais = PaisManager.instance().getPaisById(entityId);    	
    		 resetFields();	 
    	 }catch (RemoteException e) {		
			e.printStackTrace();
		}
        
    }    
    
    private void resetFields(){
    	jFormattedTextField1.setText(this.pais.getDescripcion());
    }
    
    public void buscar(){
		ABMPaisesBusqueda busq = new ABMPaisesBusqueda(Main.getVentana());
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
