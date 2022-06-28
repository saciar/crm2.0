package crm.gui.abms;

import java.rmi.RemoteException;

import javax.swing.JPanel;

import crm.client.managers.TipoLugarEventoManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.abms.busquedas.ABMTipoEventoBusqueda;
import crm.gui.abms.busquedas.ABMTipoLugarBusqueda;
import crm.libraries.abm.entities.TipoLugarEvento;

public class ABMTipoLugares extends ABMGeneral{
	private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
	
    private TipoLugarEvento tipoLugarEvento;
	private JPanel panel;
	private String entidadBuscada;
	
	public ABMTipoLugares(JPanel pan){
		panel = pan;	
	}
	
	public void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de un tipo de lugar de evento");

        jLabel2.setText("Ingrese el nombre del tipo de lugar de evento");
        
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
		this.tipoLugarEvento = new TipoLugarEvento("","","S");
		resetFields();
	}
	
	public void setEditMode(String entityId) {
		try {
			this.tipoLugarEvento = TipoLugarEventoManager.instance().getTipoLugarEventoById(entityId);
			resetFields();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void resetFields() {
		jFormattedTextField1.setText(tipoLugarEvento.getDescripcion());
	}
	
	private boolean isFormIncomplete() {
		String nombreTipoLugarEvento = jFormattedTextField1.getText();
		
		return nombreTipoLugarEvento == null || nombreTipoLugarEvento.length() == 0;
	}
	
	public void guardar() {
		
		if (isFormIncomplete()) {
			Util.errMsg(Main.getVentana(), "Tiene datos obligatorios por completar", null);
			return;
		}
		
		String nombreTipoLugarEvento = jFormattedTextField1.getText();
		
		this.tipoLugarEvento.setDescripcion(nombreTipoLugarEvento);
		try {
			TipoLugarEventoManager.instance().update(this.tipoLugarEvento);
			Util.alertMsg(Main.getVentana(), "El tipo de lugar de evento se guardó con éxito");
		} catch (Exception e) {
			Util.errMsg(Main.getVentana(), "Error al guardar el tipo de lugar de evento",e);
		} finally {
			setAddMode();
		}
	}
	
	public void buscar(){
		ABMTipoLugarBusqueda busq = new ABMTipoLugarBusqueda(Main.getVentana());
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
