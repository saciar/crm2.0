package crm.gui.abms;

import java.rmi.RemoteException;

import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.UnidadComercialManager;
import crm.client.managers.UnidadVendedorManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.abms.busquedas.ABMUnidadesComercialesBusqueda;
import crm.gui.components.ABMSucursalesComboBox;
import crm.gui.components.ABMSupervisoresComboBox;
import crm.gui.components.ABMVendedoresMultiBox;
import crm.gui.components.CustomTextField;
import crm.libraries.abm.entities.UnidadComercial;
import crm.libraries.abm.entities.UnidadVendedor;

public class ABMUnidadesComerciales extends ABMGeneral{
	
	/*private ABMSucursalesComboBox jComboBox1;
    private ABMSupervisoresComboBox jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;*/
	private ABMSucursalesComboBox jComboBox1;
    private ABMSupervisoresComboBox jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private ABMVendedoresMultiBox jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
	
	private UnidadComercial unidad;
	private JPanel panel;
	private String entidadBuscada;
	
	public ABMUnidadesComerciales(JPanel pan){
		panel = pan;	
	}
	
	public void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField2 = CustomTextField.getDecimalInstance(10,2);
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new ABMSucursalesComboBox();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new ABMSupervisoresComboBox();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new ABMVendedoresMultiBox();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de una nueva unidad comercial");

        jLabel2.setText("Nombre de la unidad");

        jLabel3.setText("Objetivo global");

        jLabel4.setText("Esta unidad comercial pertence a la Sucursal");       

        jLabel5.setText("Seleccione un supervisor para esta unidad");
        
        jLabel6.setText("Seleccione los vendedores de esta unidad");
        
        jScrollPane1.setViewportView(jList1);
        
        setAddMode();       
	}
	
	public void initLayout(){	
        
		//jComboBox1.loadItems();
        
        //jComboBox2.loadItemsForUnidadesComerciales();
        
       // jList1.loadItemsForVendoresSinUnidad();
		
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
                                .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                                .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                                .add(jLabel1)
                                .add(layout.createSequentialGroup()
                                    .add(jLabel2)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE))
                                .add(layout.createSequentialGroup()
                                    .add(jLabel3)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jFormattedTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(layout.createSequentialGroup()
                                    .add(jLabel4)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 213, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(layout.createSequentialGroup()
                                    .add(jLabel5)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 213, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                                .add(jLabel6)))
                        .add(layout.createSequentialGroup()
                            .add(20, 20, 20)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 179, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
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
                        .add(jFormattedTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel4)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel5)
                        .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel6)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(202, Short.MAX_VALUE))
            );
    }
	
	private void setAddMode() {
		this.unidad = new UnidadComercial();
		resetFields();
	}
	
	public void setEditMode(String entityId) {
		try {
			this.unidad = UnidadComercialManager.instance().getUnidadComercialById(entityId);
			resetFields();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void resetFields() {
		jFormattedTextField1.setText(unidad.getDescripcion());
		jFormattedTextField2.setText(unidad.getObjetivoGlobal());
		if(!StringUtils.isBlank(unidad.getCodigoSucursal()))
			jComboBox1.setForeign(unidad.getCodigoSucursal());
		else
			jComboBox1.loadItems();
		if(!StringUtils.isBlank(this.unidad.getCodigoSupervisor()))
			jComboBox2.setForeign(unidad.getCodigoSupervisor());
		else jComboBox2.loadItems();
		resetVendedores();
	}
	
	private void resetVendedores(){
    	try {
    		String[] foreigns = null;
    		if(!StringUtils.isBlank(this.unidad.getCodigo())){
    			UnidadVendedor[] vendedores = UnidadVendedorManager.instance().getUnidadByCodigoUnidad(this.unidad.getCodigo());
    			foreigns = new String[vendedores.length];
    			for(int i = 0;i < vendedores.length;i++){
    				foreigns[i] = vendedores[i].getCodigoVendedor();
    			}
    			this.jList1.loadItemsForUnidadesComerciales();
    		}
    		else
    			this.jList1.loadItems();
			this.jList1.setForeigns(foreigns);
    	} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
	
	private boolean isFormIncomplete() {
		String nombreUC = jFormattedTextField1.getText();
		String objetivo = jFormattedTextField1.getText();
		String codSucursal = (String) jComboBox1.searchForeign();
		String codSupervisor = (String) jComboBox2.searchForeign();
		
		return nombreUC == null || nombreUC.length() == 0 ||
				objetivo == null || objetivo.length() == 0 ||
				codSucursal == null || codSucursal.length() == 0 ||
				codSupervisor == null || codSupervisor.length() == 0;
	}
	
	public void guardar() {
		
		if (isFormIncomplete()) {
			Util.errMsg(Main.getVentana(), "Tiene datos obligatorios por completar", null);
			return;
		}
		
		String nombreUC = jFormattedTextField1.getText();
		String objetivo = jFormattedTextField2.getText();
		String codSucursal = (String) jComboBox1.searchForeign();
		String codSupervisor = (String) jComboBox2.searchForeign();
		String[] vendedores = jList1.searchForeignsArray();
		
		unidad.setDescripcion(nombreUC);
		unidad.setObjetivoGlobal(objetivo);
		unidad.setCodigoSucursal(codSucursal);
		unidad.setCodigoSupervisor(codSupervisor);
		
		try {
			String id = UnidadComercialManager.instance().update(this.unidad);
			if(id != null){
				UnidadVendedorManager.instance().removeByCodigoUnidad(id);
        		for(int i = 0;i < vendedores.length;i++){
        			UnidadVendedor unidadVendedor = new UnidadVendedor(id,vendedores[i],"S");
        			UnidadVendedorManager.instance().update(unidadVendedor);	
        		}
        		
        		UnidadVendedor unidadVendedor = new UnidadVendedor(id,codSupervisor,"S");
    			UnidadVendedorManager.instance().update(unidadVendedor);
    			Util.alertMsg(Main.getVentana(), "La unidad comercial se cargó con éxito");
			}			
		} catch (Exception e) {
			Util.errMsg(Main.getVentana(), "Error al guardar la unidad comercial",e);
		} finally {
			setAddMode();
		}
	}
	
	public void buscar(){
		ABMUnidadesComercialesBusqueda busq = new ABMUnidadesComercialesBusqueda(Main.getVentana());
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
