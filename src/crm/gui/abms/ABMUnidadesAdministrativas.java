package crm.gui.abms;

import java.rmi.RemoteException;

import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.UnidadAdministrativaManager;
import crm.client.managers.UnidadAdministradorManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.abms.busquedas.ABMUnidadesAdministrativasBusqueda;
import crm.gui.abms.busquedas.ABMUnidadesComercialesBusqueda;
import crm.gui.components.ABMAdministrativosMultiBox;
import crm.gui.components.ABMSucursalesComboBox;
import crm.gui.components.ABMSupervisoresComboBox;
import crm.gui.components.ABMVendedoresMultiBox;
import crm.gui.components.CustomTextField;
import crm.libraries.abm.entities.UnidadAdministrativa;
import crm.libraries.abm.entities.UnidadAdministrador;

public class ABMUnidadesAdministrativas extends ABMGeneral{

	private ABMSucursalesComboBox jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private ABMAdministrativosMultiBox jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
	
	private UnidadAdministrativa unidad;
	private JPanel panel;
	private String entidadBuscada;
	
	public ABMUnidadesAdministrativas(JPanel pan){
		panel = pan;	
	}
	
	public void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new ABMSucursalesComboBox();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new ABMAdministrativosMultiBox();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de una nueva unidad administrativa");

        jLabel2.setText("Nombre de la unidad");
        
        jLabel4.setText("Esta unidad administrativa pertence a la Sucursal");       
        
        jLabel6.setText("Seleccione los administrativos de esta unidad");
        
        jScrollPane1.setViewportView(jList1);
        
        setAddMode();       
	}
	
	public void initLayout(){
		
		resetFields();
		
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                                .add(jLabel1)
                                .add(layout.createSequentialGroup()
                                    .add(jLabel2)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE))))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jLabel4)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 213, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(10, 10, 10)
                                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 179, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(jLabel6))))
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
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel4)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel6)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(257, Short.MAX_VALUE))
            );
    }
	
	private void setAddMode() {
		this.unidad = new UnidadAdministrativa();
		resetFields();
	}
	
	public void setEditMode(String entityId) {
		try {
			this.unidad = UnidadAdministrativaManager.instance().getUnidadComercialById(entityId);
			resetFields();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void resetFields() {
		jFormattedTextField1.setText(unidad.getDescripcion());		
		if(!StringUtils.isBlank(this.unidad.getCodigoSucursal()))
			jComboBox1.setForeign(unidad.getCodigoSucursal());
		else
			jComboBox1.loadItems();
		
		resetAdministradores();
	}
	
	private void resetAdministradores(){
    	try {
    		String[] foreigns = null;
    		if(!StringUtils.isBlank(this.unidad.getCodigo())){
    			UnidadAdministrador[] administradores = UnidadAdministradorManager.instance().getUnidadByCodigoUnidad(this.unidad.getCodigo());
    			foreigns = new String[administradores.length];
    			for(int i = 0;i < administradores.length;i++){
    				foreigns[i] = administradores[i].getCodigoAdministrador();
    			}
    			this.jList1.loadItemsForUnidadesAdministrativas(this.unidad.getCodigo());
    		}
    		else{
    			this.jList1.loadItemsForAdministrativosSinUnidad();
    		}
			this.jList1.setForeigns(foreigns);
    	} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
	
	private boolean isFormIncomplete() {
		String nombreUC = jFormattedTextField1.getText();
		String codSucursal = (String) jComboBox1.searchForeign();
		
		return nombreUC == null || nombreUC.length() == 0 ||
				codSucursal == null || codSucursal.length() == 0;
	}
	
	public void guardar() {
		
		if (isFormIncomplete()) {
			Util.errMsg(Main.getVentana(), "Tiene datos obligatorios por completar", null);
			return;
		}
		
		String nombreUC = jFormattedTextField1.getText();
		String codSucursal = (String) jComboBox1.searchForeign();
		String[] administradores = jList1.searchForeignsArray();
		
		unidad.setDescripcion(nombreUC);
		unidad.setCodigoSucursal(codSucursal);
		
		try {
			String id = UnidadAdministrativaManager.instance().update(this.unidad);
			if(id != null){
				UnidadAdministradorManager.instance().removeByCodigoUnidad(id);
				String ua = null;
        		for(int i = 0;i < administradores.length;i++){
        			UnidadAdministrador unidadAdministrador = new UnidadAdministrador(id,administradores[i],"S");
        			ua = UnidadAdministradorManager.instance().update(unidadAdministrador);
        			if(ua ==null)
        				break;
        		}  
        		if(ua!=null)
        			Util.alertMsg(Main.getVentana(), "La unidad administrativa se cargó con éxito");
        		else
        			Util.errMsg(Main.getVentana(), "Error al guardar la unidad administrativa",null);
			}		
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al guardar la unidad administrativa",e);
		} finally {
			setAddMode();
		}
	}
	
	public void buscar(){
		ABMUnidadesAdministrativasBusqueda busq = new ABMUnidadesAdministrativasBusqueda(Main.getVentana());
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
