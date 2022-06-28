package crm.gui.abms;

import java.rmi.RemoteException;

import javax.swing.JPanel;

import org.apache.wsif.WSIFException;

import crm.client.managers.ResultadoSeguimientoManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.abm.ReactiveEntityQuestion;
import crm.gui.abms.busquedas.ABMProvinciasBusqueda;
import crm.gui.abms.busquedas.ABMResultadosSeguimientoBusqueda;
import crm.gui.components.ABMSeguimientoComboBox;
import crm.libraries.abm.entities.ResultadoSeguimiento;

public class ABMResultadosSeguimiento extends ABMGeneral{
	private ABMSeguimientoComboBox jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    
    private JPanel panel;
    private ResultadoSeguimiento resultado;
    private String entidadBuscada;
    
    public ABMResultadosSeguimiento(JPanel pan){
    	panel = pan;
    }
    
	public void initComponents() {
		jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new ABMSeguimientoComboBox();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de una nuevo resultado de seguimiento");

        jLabel2.setText("Ingrese el nombre del resultado de seguimiento");

        jLabel3.setText("Seleccione la acción para el resultado de seguimiento");
        
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
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 234, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
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
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(448, Short.MAX_VALUE))
        );
    }
	
	public void guardar() {
        String desc = new String(jFormattedTextField1.getText());
        String accion = jComboBox1.searchForeign();
        
        if ((desc == null || desc.length() == 0 || accion == null || accion.length() == 0)){
            // Modal dialog with OK button
        	Util.errMsg(Main.getVentana(), "Tiene datos obligatorios por completar", null);
            return;
        }
        try {
        	boolean canEdit = true;
        	//verifico si existe
        	ResultadoSeguimiento p = ResultadoSeguimientoManager.instance().getResultadoSeguimientoByDescripcion(desc);
        	if(p != null){
        		if(!p.getCodigo().equals(this.resultado.getCodigo())){
        			canEdit = false;
        		}
        		if(!canEdit){
        			if(p.getActivo().equals("S")){        		
        				Util.errMsg(Main.getVentana(), "Ya existe un resultado de seguimiento con esta descripción", null);
        				return;
        			}else{        			
        				if(!showExistEntityDialog(p)){
        					return;
        				}
        				this.resultado = p;
        				this.resultado.setActivo("S");
        			}
        		}
        	}        		
        	if(canEdit){             	
        		this.resultado.setDescripcion(desc);
        		this.resultado.setCodSeguimiento(accion);
        	}
        	ResultadoSeguimientoManager.instance().update(this.resultado);			
		} catch (WSIFException e) {			
			e.printStackTrace();
		}    
         catch (RemoteException e) {		
			e.printStackTrace();
		}        
		setAddMode();	
        
    }
	
	private boolean showExistEntityDialog(ResultadoSeguimiento entity){
		String message = "Se encontro un registro inactivo con esta descripcion";
		String[] headers = new String[]{"Codigo","Descripcion"};
		String[][] data = new String[][]{{entity.getCodigo(),entity.getDescripcion()}};		
		return (new ReactiveEntityQuestion(Util.getOpennerComponent(panel),message,headers,data).showDialog() > 0);
	}
	
	
    private void setAddMode(){
    	this.resultado = new ResultadoSeguimiento("","","","S");    	
    	resetFields();
    }
    
    public void setEditMode(String entityId){
    	 try {    		  		
    		 this.resultado = ResultadoSeguimientoManager.instance().getResultadoSeguimientoById(entityId);    	
    		 resetFields();	 
    	 }catch (RemoteException e) {		
			e.printStackTrace();
		}
        
    }    
    
    private void resetFields(){
    	jFormattedTextField1.setText(this.resultado.getDescripcion());
    	jComboBox1.setForeign(this.resultado.getCodSeguimiento());
    }
    
    public void buscar(){
    	ABMResultadosSeguimientoBusqueda busq = new ABMResultadosSeguimientoBusqueda(Main.getVentana());
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
