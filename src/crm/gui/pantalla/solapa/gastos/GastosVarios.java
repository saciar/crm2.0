package crm.gui.pantalla.solapa.gastos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import crm.gui.Main;
import crm.gui.components.CustomTextField;
import crm.gui.pantalla.solapa.PanelGeneral;
import crm.gui.tablerenderer.gastos.GastosVariosItem;
import crm.gui.tablerenderer.gastos.GastosVariosTableModel;
import crm.gui.tablerenderer.gastos.TableRenderGastosVarios;
import crm.libraries.abm.entities.Ppto_GastoVarios;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.util.MessageUtil;

public class GastosVarios extends PanelGeneral {
	private javax.swing.JButton add;
    private javax.swing.JFormattedTextField costo;
    private javax.swing.JFormattedTextField detalle;
    private javax.swing.JButton edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel name;
    private javax.swing.JButton remove;
    private TableRenderGastosVarios tableRender;
	private GastosVariosItem item;
	private boolean editMode;
    
    private JPanel panel;
    private String detalleXDefault = "Indique en detalle el gasto varios" ;
    
    public GastosVarios(JPanel pan){
    	panel = pan;
    }
    
    public void init(){
    	name = new javax.swing.JLabel();
        tableRender = new TableRenderGastosVarios();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        detalle = CustomTextField.getRegularStringInstance(100);
        jLabel4 = new javax.swing.JLabel();
        costo = CustomTextField.getDecimalInstance(8,2);
        add = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        edit = new javax.swing.JButton();

        name.setFont(new java.awt.Font("Tahoma", 1, 11));
        name.setText("Gastos varios");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Detalle");
        
        detalle.setText(detalleXDefault);
        
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Costo");
        
        costo.setText("0.0");
        
        add.setIcon(new javax.swing.ImageIcon(getUrlImagen("add.png")));
        add.setText("Agregar");

        remove.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        remove.setText("Eliminar");

        edit.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        edit.setText("Editar");
        
        createListeners();
    }
    
    public void initLayout(){
    	org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, tableRender, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, name)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel1)
                            .add(jLabel4))
                        .add(4, 4, 4)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(detalle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 557, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(costo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 212, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(add)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(edit)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(remove))
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(name)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(detalle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(costo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(14, 14, 14)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(add)
                    .add(edit)
                    .add(remove))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(tableRender, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addContainerGap())
        );
    }
    
    private void createListeners(){
    	add.addActionListener(new AddActionListener());
    	remove.addActionListener(new RemoveActionListener());
    	edit.addActionListener(new EditActionListener());    	
    	detalle.addFocusListener(new DetalleFocusListener());
    	costo.addFocusListener(new CostoFocusListener());
    }
    
    private boolean isValid(){
		String detalle = this.detalle.getText();
		double costo = Double.parseDouble(this.costo.getText());
		return ((!StringUtils.isEmpty(detalle)) && (costo > 0.0));
	}
	
	private void save(){
		makeItem();					
		if(!isEditMode()){		
			((GastosVariosTableModel)this.tableRender.getTable().getModel()).addRow(this.item);
		}
		this.tableRender.getTable().updateUI();
		clearItem();
		resetFields();
	}
	
	
	public GastosVariosItem getItem() {
		return item;
	}

	public void setItem(GastosVariosItem item) {
		this.item = item;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	private void resetFields(){		
		this.detalle.setText(this.item.getDetalle());
		this.costo.setText(Double.toString(this.item.getCosto()));
	}		
	
	private void makeItem(){
		this.item.setDetalle(this.detalle.getText());
		this.item.setCosto(Double.parseDouble(this.costo.getText()));		
	}	
	
	private void clearItem(){		
		this.item = new GastosVariosItem();
		detalle.setText(detalleXDefault);
		setEditMode(false);
	}	
	
	public List getRows(){
		return ((GastosVariosTableModel)this.tableRender.getTable().getModel()).getRows();
	}
	
	public Ppto_GastoVarios[] getGastos(){		
		List items = ((GastosVariosTableModel)this.tableRender.getTable().getModel()).getRows();
	
		Ppto_GastoVarios[] gastos = new Ppto_GastoVarios[items.size()];		
		for(int i = 0;i < gastos.length;i++){
			GastosVariosItem item = (GastosVariosItem)items.get(i);
			gastos[i] = new Ppto_GastoVarios();
			gastos[i].setDetalle(item.getDetalle());
			gastos[i].setCosto(Double.toString(item.getCosto()));						
		}
		return gastos;
	}
	
	public void setPresupuesto(Presupuesto presupuesto){
		((GastosVariosTableModel)this.tableRender.getTable().getModel()).clear();
		Set gastos = presupuesto.getGastosVarios();
		if(gastos != null){
			Iterator it = gastos.iterator();
			while (it.hasNext()) {
				Ppto_GastoVarios gasto = (Ppto_GastoVarios) it.next();
				GastosVariosItem item = new GastosVariosItem();
				item.setCosto(Double.parseDouble(gasto.getCosto()));
				item.setDetalle(gasto.getDetalle());
				
				((GastosVariosTableModel)this.tableRender.getTable().getModel()).addRow(item);
			}
		}
	}
	
	//***************************ACCIONES*****************************************************
	
	private class AddActionListener implements ActionListener{		
		public void actionPerformed(ActionEvent e) {
			setEditMode(false);
			setItem(new GastosVariosItem());
			makeItem();
			if(!isValid()){
				MessageUtil.showWarningMessage(Main.getVentana(),"Debe completar todos los campos");
				return;
			}
			save();
		}		
	}  
	
	private class RemoveActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			tableRender.removeSelection();
			clearItem();
			resetFields();	
		}
		
	}
	
	private class EditActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			setEditMode(true);
			setItem(tableRender.getSelectedItem());
			resetFields();
		}
		
	}
	
	private class DetalleFocusListener implements FocusListener{

		public void focusGained(FocusEvent arg0) {
			detalle.selectAll();			
		}

		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class CostoFocusListener implements FocusListener{

		public void focusGained(FocusEvent arg0) {
			costo.selectAll();			
		}

		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
