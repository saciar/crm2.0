package crm.gui.abms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JPanel;

import org.apache.wsif.WSIFException;

import crm.client.managers.CodigoPostalManager;
import crm.client.managers.LocalidadManager;
import crm.client.managers.PartidoManager;
import crm.client.managers.ProvinciaManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.abm.ReactiveEntityQuestion;
import crm.gui.abms.busquedas.ABMCodigosPostalesBusqueda;
import crm.gui.components.ABMLocalidadesComboBox;
import crm.gui.components.ABMPaisesComboBox;
import crm.gui.components.ABMPartidosComboBox;
import crm.gui.components.ABMProvinciasComboBox;
import crm.libraries.abm.entities.CodigoPostal;
import crm.libraries.abm.entities.Localidad;
import crm.libraries.abm.entities.Partido;
import crm.libraries.abm.entities.Provincia;

public class ABMCodigosPostales extends ABMGeneral{
	private ABMPaisesComboBox jComboBox1;
    private ABMProvinciasComboBox jComboBox2;
    private ABMPartidosComboBox jComboBox3;
    private ABMLocalidadesComboBox jComboBox4;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    
    private CodigoPostal codigoPostal;
    private JPanel panel;
	private String entidadBuscada;
	
	private String paisId;
    private String provinciaId;
    private String partidoId;
    
    public ABMCodigosPostales(JPanel pan){
    	panel= pan;
    }
    
    public void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new ABMPaisesComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new ABMProvinciasComboBox();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new ABMPartidosComboBox();
        jLabel6 = new javax.swing.JLabel();
        jComboBox4 = new ABMLocalidadesComboBox();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de un nuevo codigo postal");

        jLabel2.setText("Ingrese el codigo postal");

        jLabel3.setText("Seleccione el pa\u00eds de la localidad");        

        jLabel4.setText("Seleccione la provincia de la localidad");
        
        jLabel5.setText("Seleccione el partido de la localidad");       

        jLabel6.setText("Seleccione la localidad");  
        
        initLocationFields();
        
        setAddMode();
    }
    
    public void initLayout(){
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
                        .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 210, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox2, 0, 222, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 212, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel6)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 225, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
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
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jComboBox3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6)
                    .add(jComboBox4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(420, Short.MAX_VALUE))
        );
    }
    
    private void setAddMode(){
    	this.codigoPostal = new CodigoPostal("","","","S");    	
    	resetFields();
    }
    
    public void setEditMode(String entityId){
    	 try {    		  		
    		 this.codigoPostal = CodigoPostalManager.instance().getCodigoPostalById(entityId);    	
    		 resetFields();	 
    	 }catch (RemoteException e) {		
			e.printStackTrace();
		}
        
    } 
    
    public void guardar() {
        String localidad = jComboBox4.searchForeign();
        String codigoPostal = (String)jFormattedTextField1.getText();
        
        if (localidad == null || localidad.length() == 0 || 
        		codigoPostal == null || codigoPostal.length() == 0){
            // Modal dialog with OK button
        	Util.errMsg(Main.getVentana(), "Tiene datos obligatorios por completar", null);
            return;
        }

        try {
        	boolean canEdit = true;
        	//verifico si existe
        	CodigoPostal cp = CodigoPostalManager.instance().getCodigoPostalByCP(codigoPostal);
        	if(cp != null){
        		if(!cp.getCodigo().equals(this.codigoPostal.getCodigo())){
        			canEdit = false;
        		}
        		if(!canEdit){
        			if(cp.getActivo().equals("S")){        		
        				Util.errMsg(Main.getVentana(), "Ya existe un codigo postal con este numero", null);
        				return;
        			}else{        			
        				if(!showExistEntityDialog(cp)){
        					return;
        				}
        				this.codigoPostal = cp;
        				this.codigoPostal.setActivo("S");
        			}
        		}
        	}        		
        	if(canEdit){         	        	
        		this.codigoPostal.setCodigoPostal(codigoPostal);
				this.codigoPostal.setCodLocalidad(localidad);
        	}
			CodigoPostalManager.instance().update(this.codigoPostal);			
		} catch (WSIFException e) {			
			e.printStackTrace();
		}    
         catch (RemoteException e) {		
			e.printStackTrace();
		}finally{        
			setAddMode();
		}
    }
    
	private boolean showExistEntityDialog(CodigoPostal cp){
		String message = "Se encontro un registro inactivo con este CP";
		String[] headers = new String[]{"Codigo","CP"};
		String[][] data = new String[][]{{cp.getCodigo(),cp.getCodigoPostal()}};		
		return (new ReactiveEntityQuestion(Util.getOpennerComponent(panel),message,headers,data).showDialog() > 0);
	}
    
    private void resetFields(){
    	jFormattedTextField1.setText(codigoPostal.getCodigoPostal());
    	
    	if(!codigoPostal.getCodigoPostal().equals("")){  
    		jComboBox4.setEnabled(true);
    		jComboBox3.setEnabled(true);
    		jComboBox2.setEnabled(true);
    		jComboBox1.setEnabled(true);
    		
    		try{
    			Localidad l = LocalidadManager.instance().getLocalidadByCodLocalidad(codigoPostal.getCodLocalidad());
    			if(l != null){
    				Partido p = PartidoManager.instance().getPartidoByCodPartido(l.getCodigoPartido());
    				Provincia prov = ProvinciaManager.instance().getProvinciaByCodProvincia(p.getCodigoProvincia());
    				
    				jComboBox1.setForeign(prov.getCodigoPais());
    				jComboBox2.setForeign(p.getCodigoProvincia());
    				jComboBox3.setForeign(l.getCodigoPartido());
    				jComboBox4.setForeign(codigoPostal.getCodLocalidad());
    				
    			}    		
    		}
    		catch (RemoteException e){
    			Util.errMsg(panel,"Error al cargar datos de ubicación", e);
    		}
    	}
    	else{
    		jComboBox4.setEnabled(false);
    		jComboBox3.setEnabled(false);
    		jComboBox2.setEnabled(false);
    		jComboBox4.setForeign("0");
    		jComboBox3.setForeign("0");
    		jComboBox2.setForeign("0");
    		jComboBox1.setForeign("0");
    	}
    }
    
    private void initLocationFields(){
    	jComboBox2.setEnabled(false);
    	jComboBox3.setEnabled(false);
    	jComboBox4.setEnabled(false);
    	
    	jComboBox1.loadItems();
    	
    	this.jComboBox1.addActionListener(new PaisActionListener());    	
    	this.jComboBox2.addActionListener(new ProvinciaActionListener());    	
    	this.jComboBox3.addActionListener(new PartidoActionListener());    	
    	
    }
    
    public void buscar(){
    	ABMCodigosPostalesBusqueda busq = new ABMCodigosPostalesBusqueda(Main.getVentana());
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
    
    //*****************************ACCIONES**********************************************
    
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
			} else jComboBox2.setEnabled(false);
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
			} else jComboBox3.setEnabled(false);
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
			} else jComboBox4.setEnabled(false);
		}
    }
}
