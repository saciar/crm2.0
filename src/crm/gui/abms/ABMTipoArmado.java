package crm.gui.abms;

import java.rmi.RemoteException;

import javax.swing.JPanel;

import crm.client.managers.TipoArmadoManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.abms.busquedas.ABMTipoArmadoBusqueda;
import crm.gui.abms.busquedas.ABMTipoEventoBusqueda;
import crm.libraries.abm.entities.TipoArmado;

public class ABMTipoArmado extends ABMGeneral{
	private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
	
    private TipoArmado tipoArmado;
	private JPanel panel;
	private String entidadBuscada;
	
	public ABMTipoArmado(JPanel pan){
		panel = pan;	
	}
	
	public void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de un tipo de armado");

        jLabel2.setText("Ingrese el nombre del tipo de armado");
        
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
                        .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)))
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
	
	private void setAddMode() {
		this.tipoArmado = new TipoArmado("","","S");
		resetFields();
	}
	
	public void setEditMode(String entityId) {
		try {
			this.tipoArmado = TipoArmadoManager.instance().getById(entityId);
			resetFields();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void resetFields() {
		jFormattedTextField1.setText(tipoArmado.getDescripcion());
	}
	
	private boolean isFormIncomplete() {
		String nombreTipoArmado = jFormattedTextField1.getText();
		
		return nombreTipoArmado == null || nombreTipoArmado.length() == 0;
	}
	
	public void guardar() {
		
		if (isFormIncomplete()) {
			Util.errMsg(Main.getVentana(), "Tiene datos obligatorios por completar", null);
			return;
		}
		
		String nombreTipoArmado = jFormattedTextField1.getText();
		
		this.tipoArmado.setDescripcion(nombreTipoArmado);
		try {
			TipoArmadoManager.instance().update(this.tipoArmado);
			Util.alertMsg(Main.getVentana(), "El tipo de armado se guardó con éxito");
		} catch (Exception e) {
			Util.errMsg(Main.getVentana(), "Error al guardar el tipo de armado",e);
		} finally {
			setAddMode();
		}
	}
	
	public void buscar(){
		ABMTipoArmadoBusqueda busq = new ABMTipoArmadoBusqueda(Main.getVentana());
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
