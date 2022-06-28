package crm.gui.abms;

import java.rmi.RemoteException;

import javax.swing.JPanel;

import org.apache.wsif.WSIFException;

import crm.client.managers.PrtPptoPeriodoManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.abms.busquedas.ABMPeriodosPptoBusqueda;
import crm.libraries.abm.entities.PrtPptoPeriodo;

public class ABMPeriodosPpto extends ABMGeneral{
	private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextArea1;
    
    private JPanel panel;
	private String entidadBuscada;
	private PrtPptoPeriodo model;
	
	public ABMPeriodosPpto(JPanel pan){
		panel = pan;	
	}
    
    public void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de un nuevo período de presupuestos");

        jLabel7.setIcon(new javax.swing.ImageIcon(getUrlImagen("DibujoPeriodo.jpg")));

        jLabel2.setText("Ingrese el titulo del período");

        jLabel3.setText("Opción para esta parte del presupuesto");

        jLabel4.setText("Detalle");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        
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
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                    .add(jLabel1)
                    .add(layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE))
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jLabel4)
                        .add(11, 11, 11)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE))
                    .add(jLabel3)
                    .add(jLabel7))
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
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(9, 9, 9)
                        .add(jLabel4))
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel7)
                .addContainerGap(47, Short.MAX_VALUE))
        );
    }
    
    public void guardar() {        
    	String titulo = this.jFormattedTextField1.getText(); 
    	String descripcion = this.jTextArea1.getText();        
    	if (titulo == null || titulo.length() == 0  || descripcion == null || descripcion.length() == 0 ){
    		Util.errMsg(Main.getVentana(), "Tiene datos obligatorios por completar", null);
            return;
        }        
        try {			        	
        	this.model.setTitulo(titulo);        	
        	this.model.setDescripcion(descripcion);
        	PrtPptoPeriodoManager.instance().update(this.model);			
		} catch (WSIFException e) {			
			e.printStackTrace();
		}    
         catch (RemoteException e) {		
			e.printStackTrace();
		}
        
        setAddMode();
    }
    
    private void setAddMode(){
    	this.model = new PrtPptoPeriodo();    	
    	this.resetFields();
    }
    
    public void setEditMode(String entityId){
    	 try {    		  		
    		 this.model = PrtPptoPeriodoManager.instance().getById(entityId);    	
    		 this.resetFields();
    	 }catch (RemoteException e) {		
			e.printStackTrace();
		}
        
    }    
    
    private void resetFields(){
    	this.jFormattedTextField1.setText(this.model.getTitulo());
    	this.jTextArea1.setText(this.model.getDescripcion());
    }
    
    public void buscar(){
    	ABMPeriodosPptoBusqueda busq = new ABMPeriodosPptoBusqueda(Main.getVentana());
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
