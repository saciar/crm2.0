package crm.gui.abms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JPanel;

import org.apache.wsif.WSIFException;

import crm.client.managers.PartidoManager;
import crm.client.managers.ProvinciaManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.abm.ReactiveEntityQuestion;
import crm.gui.abms.busquedas.ABMPartidosBusqueda;
import crm.gui.abms.busquedas.ABMProvinciasBusqueda;
import crm.gui.components.ABMPaisesComboBox;
import crm.gui.components.ABMProvinciasComboBox;
import crm.gui.components.CustomTextField;
import crm.libraries.abm.entities.Partido;
import crm.libraries.abm.entities.Provincia;

public class ABMPartidos extends ABMGeneral{
	private ABMPaisesComboBox jComboBox1;
    private ABMProvinciasComboBox jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    
    private JPanel panel;
    private Partido partido;
    private String entidadBuscada;
    private String paisId;
    
    public ABMPartidos(JPanel pan){
    	panel= pan;
    }
    
    public void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new ABMPaisesComboBox();
        
        initFields();
        
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de una nueva provincia");

        jLabel2.setText("Ingrese el nombre de la Provincia");

        jLabel3.setText("Seleccione el pa\u00eds de la provincia");

        jLabel4.setText("Seleccione la provincia del partido");         
        
        setAddMode();
    }
    
    public void initLayout() {
    	jComboBox1.loadItems();
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
                        .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 224, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox2, 0, 228, Short.MAX_VALUE)))
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
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4)
                    .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(448, Short.MAX_VALUE))
        );
    }
    
    private void setAddMode(){
    	this.partido = new Partido("","","","","S");    	
    	resetFields();
    }
    
    public void setEditMode(String entityId){
    	 try {    		  		
    		 this.partido = PartidoManager.instance().getPartidoByCodPartido(entityId);    	
    		 resetFields();	 
    	 }catch (RemoteException e) {		
			e.printStackTrace();
		}
        
    }    
    
    private void resetFields(){
    	jFormattedTextField1.setText(this.partido.getDescripcion());
    	
    	if (!partido.getCodigoPartido().equals("")){
    		jComboBox2.setEnabled(true);
    		jComboBox1.setEnabled(true);
    		
    		try{
    			Provincia prov = ProvinciaManager.instance().getProvinciaByCodProvincia(partido.getCodigoProvincia());
    			if(prov != null){
    				jComboBox1.setForeign(prov.getCodigoPais());
    				jComboBox2.setForeign(partido.getCodigoProvincia());
    			}
    		}
    		catch (RemoteException e){
    			Util.errMsg(Main.getVentana(),"Error al cargar datos de ubicación", e);
    		}
    	}
    	else{
    		jComboBox2.setEnabled(false);
    		jComboBox2.setForeign("0");
    		jComboBox1.setForeign("0");
    	}
    }    
    
    private void initFields(){
    	jFormattedTextField1 = CustomTextField.getRegularStringInstance(50);  
    	jComboBox1 = new ABMPaisesComboBox();
    	jComboBox2 = new ABMProvinciasComboBox();
    	
    	jComboBox2.setEnabled(false);
    	
    	this.jComboBox1.addActionListener(new PaisActionListener());   	
    	
    }
    
    public void guardar() {        
		String codProv = jComboBox2.searchForeign();
        String descPart = new String(jFormattedTextField1.getText());        
        
        if (codProv == null || codProv.length() == 0 || 
                descPart == null || descPart.length() == 0){

        	Util.errMsg(Main.getVentana(), "Tiene datos obligatorios por completar", null);
            return;
        }
        try {
        	boolean canEdit = true;
        	//verifico si existe
        	Partido p = PartidoManager.instance().getPartidoByDescripcion(descPart);
        	if(p != null){
        		if(!p.getCodigo().equals(this.partido.getCodigo())){
        			canEdit = false;
        		}
        		if(!canEdit){
        			if(p.getActivo().equals("S")){        		
        				Util.errMsg(Main.getVentana(), "Ya existe un partido con esta descripción", null);
        				return;
        			}else{        			
        				if(!showExistEntityDialog(p)){
        					return;
        				}
        				this.partido = p;
        				this.partido.setActivo("S");
        			}
        		}
        	}        		
        	if(canEdit){           	
        		//this.partido.setCodigoPartido(codPart);
        		this.partido.setCodigoProvincia(codProv);
				this.partido.setDescripcion(descPart);
        	}
			PartidoManager.instance().update(this.partido);			
		} catch (WSIFException e) {			
			e.printStackTrace();
		}    
         catch (RemoteException e) {		
			e.printStackTrace();
		}
         
        setAddMode();	         
    }
    
    private boolean showExistEntityDialog(Partido entity){
		String message = "Se encontro un registro inactivo con este codigo de partido";
		String[] headers = new String[]{"Codigo","Codigo de Partido","Descripcion"};
		String[][] data = new String[][]{{entity.getCodigo(),entity.getCodigoPartido(),entity.getDescripcion()}};		
		return (new ReactiveEntityQuestion(Util.getOpennerComponent(panel),message,headers,data).showDialog() > 0);
	}
    
    public void buscar(){
    	ABMPartidosBusqueda busq = new ABMPartidosBusqueda(Main.getVentana());
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
    
//  -----------ACTIONS----------------------------------------------------------------------
    private class PaisActionListener implements ActionListener{
    	public void actionPerformed(ActionEvent arg0) {				
			if(jComboBox1.getSelectedIndex() > 0){		
				String newId = jComboBox1.searchForeign();
				if(paisId == null || !paisId.equals(newId)){
					jComboBox2.loadItems(newId);		
				}
				paisId = newId;
				jComboBox2.setEnabled(true);
			} else jComboBox2.setEnabled(false);
		}
    }
}
