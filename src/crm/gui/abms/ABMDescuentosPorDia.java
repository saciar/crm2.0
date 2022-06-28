package crm.gui.abms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.DescPrecioDiasManager;
import crm.client.managers.FamiliaServManager;
import crm.client.managers.ServicioManager;
import crm.client.managers.SucursalManager;
import crm.client.managers.VistaFamiliaServicioIdiomaManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.abms.busquedas.ABMSucursalesBusqueda;
import crm.gui.components.ABMFamiliasComboBox;
import crm.gui.components.ABMServiciosComboBox;
import crm.gui.tablerenderer.abms.BusquedaServiciosTableModel;
import crm.gui.tablerenderer.abms.DescuentoServiciosDiasItem;
import crm.gui.tablerenderer.abms.DescuentoServiciosDiasTableModel;
import crm.gui.tablerenderer.abms.DescuentoServiciosDiasTableRender;
import crm.libraries.abm.entities.DescPrecioDias;
import crm.libraries.abm.entities.Servicio;
import crm.libraries.abm.entities.Sucursal;
import crm.libraries.abm.entities.VistaFamiliaServicioIdioma;
import crm.services.sei.FamiliaServManagerSEI;

public class ABMDescuentosPorDia extends ABMGeneral{
	
	private static final String COD_IDIOMA_ESPANOL = "1";
	
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private ABMFamiliasComboBox jComboBox1;
	private ABMServiciosComboBox jComboBox2;
	private javax.swing.JFormattedTextField jFormattedTextField1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private DescuentoServiciosDiasTableRender jTable1;
	
	private JPanel panel;
	private DescuentoServiciosDiasTableModel model;	
	
	public ABMDescuentosPorDia(JPanel pan){
		panel = pan;	
	}
	
	public void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new ABMFamiliasComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new ABMServiciosComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jTable1 = new DescuentoServiciosDiasTableRender();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Creaci\u00f3n de un nuevo descuento por d\u00eda para un servicio");

        jLabel2.setText("Ingrese el c\u00f3digo del servicio");

        jLabel3.setText("Familias de servicios");

        jComboBox1.loadItems();

        jLabel4.setText("Servicios");       

        jLabel5.setText("Tabla de descuentos por d\u00edas para el servicio especificado");

        jButton1.setText("Agregar d\u00edas");
        
        jButton2.setText("Borrar d\u00edas");
        
        createListeners();
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
                    .add(layout.createSequentialGroup()
                        .add(jTable1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 657, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton1)
                        .add(jButton2))                    
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                    .add(jLabel1)
                    .add(layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(54, 54, 54)
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox2, 0, 230, Short.MAX_VALUE)
                        .add(114, 114, 114))
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                    .add(jLabel5))
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
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4)
                    .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButton1)
                    .add(jButton2)
                    .add(jTable1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 350, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
    }
	
	private void createListeners(){
		jFormattedTextField1.addKeyListener(new CodigoKeyListener());
		jComboBox1.addActionListener(new FamiliaActionListener());
		jComboBox2.addActionListener(new ServicioActionListener());
		jButton1.addActionListener(new AgregarActionListener());
		jButton2.addActionListener(new BorrarActionListener());
	}
	
	private void setAddMode() {
		this.model = new DescuentoServiciosDiasTableModel();
		resetFields();
	}
	
	public void setEditMode(String entityId) {
		//try {
			//this.descuento = DescPrecioDiasManager.instance().getDescPrecioDiasById(entityId);
			buscar();
			resetFields();
		//} catch (RemoteException e) {
		//	e.printStackTrace();
		//}
	}
	
	private void resetFields() {
		if(model.getRowCount() > 0){
			jFormattedTextField1.setText(model.getRow(0).getServicio());
			if(model.getRow(0).getServicio() != null){
				try {
					Servicio serv = ServicioManager.instance().getServicioById(model.getRow(0).getServicio());
					jComboBox1.setForeign(serv.getFamilia());
				} catch (RemoteException e) {
					Util.errMsg(Main.getVentana(), "Error al cargar datos internos", e);
				}			
			}
			((DescuentoServiciosDiasTableModel)jTable1.getTable().getModel()).clear();
			jTable1.refreshTable();	
		}
		/*jFormattedTextField1.setText(descuento.getCodigoServicio());
		if(descuento.getCodigoServicio() != null){
			try {
				Servicio serv = ServicioManager.instance().getServicioById(descuento.getCodigoServicio());
				jComboBox1.setForeign(serv.getFamilia());
			} catch (RemoteException e) {
				Util.errMsg(Main.getVentana(), "Error al cargar datos internos", e);
			}			
		}*/
	}
	
	public void txtCodigoFocusLosted (String cod){
		String codFamilia = null;
		if (cod != null && !StringUtils.isBlank(cod)){
			try {
								
				VistaFamiliaServicioIdioma servicios  = VistaFamiliaServicioIdiomaManager.instance().getVistaFamiliaServicioIdiomaById(COD_IDIOMA_ESPANOL+cod);
				
				codFamilia = servicios.getCodigoFamilia();
				
				jComboBox2.loadItemsForFamily(codFamilia);
								
				jComboBox1.setForeign(codFamilia);
				jComboBox2.setForeign(cod);
			}
			catch (Exception e){
				e.printStackTrace();
			}	
		}
	}
	
	private boolean isFormIncomplete() {		
		String codFAmilia = jComboBox1.searchForeign();
		String codServicioCombo = jComboBox2.searchForeign(); 
		
		return codFAmilia == null || codFAmilia.length() == 0 || 
			codServicioCombo == null || codServicioCombo.length() == 0;
	}
	
	public void guardar() {
		
		if (isFormIncomplete()) {
			Util.errMsg(Main.getVentana(), "Tiene datos obligatorios por completar", null);
			return;
		}
		
		boolean todoOk = true;
		
		for(int i=0; i<model.getRowCount();i++){		
			try {
				DescuentoServiciosDiasItem item = model.getRow(i);
				DescPrecioDias descuento = new DescPrecioDias();
				descuento.setCodigo(item.getId());
				descuento.setCodigoServicio(item.getCodigo());
				descuento.setPorcentaje(item.getPorcentaje());
				descuento.setTechoDias(item.getDias());
				descuento.setActivo("S");
				DescPrecioDiasManager.instance().update(descuento);				
			} catch (Exception e) {
				Util.errMsg(Main.getVentana(), "Error al guardar los descuentos",e);
				todoOk = false;
			}
		}
		if(todoOk && model.getRowCount()>0){
			Util.alertMsg(Main.getVentana(), "Los descuentos se guardaron correctamente");
			setAddMode();
		}
	}
	
	public void buscar(){
		/*ABMSucursalesBusqueda busq = new ABMSucursalesBusqueda(Main.getVentana());
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
		}*/
		((DescuentoServiciosDiasTableModel)jTable1.getTable().getModel()).clear();
		jTable1.refreshTable();	
		if (jComboBox2.searchForeign() != null && !jComboBox2.searchForeign().equals("0")){
			try {					
				DescPrecioDias[] desc = DescPrecioDiasManager.instance().getDescPrecioDiasByServicio(jComboBox2.searchForeign());
				
				if(desc != null && desc.length > 0){
					for(int i=0; i<desc.length;i++){
						DescuentoServiciosDiasItem item = new DescuentoServiciosDiasItem();
						item.setCodigo(desc[i].getCodigoServicio());
						item.setServicio((String)jComboBox2.getSelectedItem());
						item.setDias(desc[i].getTechoDias());
						item.setPorcentaje(desc[i].getPorcentaje());
						item.setId(desc[i].getCodigo());
						model.addRow(item);    					
					}
					
					if(model.getRowCount() > 0){
						jTable1.getTable().setModel(model);
						jTable1.refreshTable();						
					}
				}else{
					Util.alertMsg(Main.getVentana(), "Este servicio no tiene asignado descuentos.");
					((DescuentoServiciosDiasTableModel)jTable1.getTable().getModel()).clear();
					jTable1.refreshTable();	
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				Util.errMsg(Main.getVentana(), "Error al buscar los descuentos para el servicio seleccionado",e);
			}
		}
	}
	
	//************************************ACCIONES***************************************
	
	private class CodigoKeyListener extends KeyAdapter{

		public void keyReleased(KeyEvent arg0) {
			if(arg0.getKeyCode() == KeyEvent.VK_ENTER && !StringUtils.isBlank(jFormattedTextField1.getText())){
				txtCodigoFocusLosted(jFormattedTextField1.getText());
			}			
		}
		
	}
	
	private class FamiliaActionListener implements ActionListener{
		
		private String lastCodigoFamilia;
		
		public void actionPerformed(ActionEvent arg0) {
			String s = jComboBox1.searchForeign();
			//System.out.println(codigoLugar);
			
			if ((lastCodigoFamilia == null || !lastCodigoFamilia.equals(s))) {
				lastCodigoFamilia = s;
				((DescuentoServiciosDiasTableModel)jTable1.getTable().getModel()).clear();
				jTable1.refreshTable();	
				jComboBox2.loadItemsForFamily(lastCodigoFamilia); //, codigoLugar);
			}
			
		}
		
	}
	
	private class ServicioActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			buscar();	
		
		}
		
	}
	
	private class AgregarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {			
			DescuentoServiciosDiasItem item = new DescuentoServiciosDiasItem();
			item.setCodigo(jComboBox2.searchForeign());
			item.setServicio((String)jComboBox2.getSelectedItem());
			item.setDias("1");
			item.setPorcentaje("0");
			model.addRow(item);
			if(model.getRowCount() > 0){
				jTable1.getTable().setModel(model);
				jTable1.refreshTable();						
			}
		}	
	}
	
	private class BorrarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(model.getRowCount() > 0 && jTable1.getSelectedItem() != null){
				model.removeRow(jTable1.getSelectedItem());				
				jTable1.getTable().setModel(model);
				jTable1.refreshTable();			
			}
		}
		
	}
}
