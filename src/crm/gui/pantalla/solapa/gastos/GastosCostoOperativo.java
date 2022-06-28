package crm.gui.pantalla.solapa.gastos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.OperadorManager;
import crm.gui.Main;
import crm.gui.components.ABMOperadoresComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.pantalla.solapa.PanelGeneral;
import crm.gui.pantalla.solapa.helper.OperadoresHelper;
import crm.gui.tablerenderer.gastos.GastosOperadoresItem;
import crm.gui.tablerenderer.gastos.GastosOperadoresTableModel;
import crm.gui.tablerenderer.gastos.TableRenderGastosOperadores;
import crm.libraries.abm.entities.Ppto_GastoOperador;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.util.MessageUtil;

public class GastosCostoOperativo extends PanelGeneral {
	private JButton add;
    private javax.swing.JFormattedTextField cargo;
    private javax.swing.JFormattedTextField costo;
    private JButton edit;
    private javax.swing.JFormattedTextField horario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel name;
    private ABMOperadoresComboBox operadores;
    private JButton remove;
    private TableRenderGastosOperadores tableRender;
    
    private JPanel panel;
    private GastosOperadoresItem item;
    private OperadoresHelper helper;
    private boolean editMode;
    private String cargoXDefault = "Indique el cargo del operador seleccionado" ;
	private String horarioXDefault = "Indique el hoarario del operador seleccionado";
    
    public GastosCostoOperativo (JPanel pn){
    	panel = pn;
    	helper = new OperadoresHelper();
    	this.clearItem();
    }
    
    public void init(){
    	name = new javax.swing.JLabel();
        tableRender = new TableRenderGastosOperadores();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        operadores = new ABMOperadoresComboBox();
        jLabel2 = new javax.swing.JLabel();
        cargo = CustomTextField.getRegularStringInstance(50);
		cargo.setColumns(40);
        jLabel3 = new javax.swing.JLabel();
        horario = CustomTextField.getRegularStringInstance(50);
		horario.setColumns(15);
        jLabel4 = new javax.swing.JLabel();
        costo = CustomTextField.getDecimalInstance(8,2);
		costo.setColumns(6);
		add = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        edit = new javax.swing.JButton();

        name.setFont(new java.awt.Font("Tahoma", 1, 11));
        name.setText("Gastos por operadores");
        
        //jScrollPane1.setViewportView(tableRender);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Operador");
        
        operadores.loadItems();
        helper.addCombo(this.operadores);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Cargo");
        
        cargo.setText(cargoXDefault);
        
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Horario");
        
        horario.setText(horarioXDefault);
        
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
                        .add(add)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(edit)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(remove))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel1)
                            .add(jLabel3)
                            .add(jLabel2)
                            .add(jLabel4))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(operadores, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 243, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(horario, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                                    .add(costo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(cargo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 557, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(199, 199, 199)))
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
                    .add(operadores, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(cargo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(horario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(costo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(add)
                    .add(edit)
                    .add(remove))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(tableRender, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addContainerGap())
        );
    }
    
    private void createListeners(){
    	add.addActionListener(new AddActionListener());
    	remove.addActionListener(new RemoveActionListener());
    	edit.addActionListener(new EditActionListener());
    	horario.addFocusListener(new HorarioFocusListener());
    	costo.addFocusListener(new CostoFocusListener());
    	cargo.addFocusListener(new CargoFocusListener());
    }
    
    public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	private void save(){
		makeItem();					
		if(!isEditMode()){		
			((GastosOperadoresTableModel)this.tableRender.getTable().getModel()).addRow(this.item);
		}
		this.tableRender.getTable().updateUI();
		clearItem();
		resetFields();
		
		this.helper.processComponents();
	}	
	
	public GastosOperadoresItem getItem() {
		return item;
	}

	public void setItem(GastosOperadoresItem item) {
		this.item = item;
	}
	
	private void resetFields(){
		if(isEditMode()){
			this.helper.addForEdit(this.operadores,this.item.getOperadorId());
		}
		this.cargo.setText(this.item.getCargo());
		this.horario.setText(this.item.getHorario());
		this.costo.setText(Double.toString(this.item.getCosto()));
		this.operadores.setForeign(this.item.getOperadorId());
	}		
	
	private void makeItem(){
		this.item.setCargo(this.cargo.getText());
		this.item.setHorario(this.horario.getText());
		this.item.setCosto(Double.parseDouble(this.costo.getText()));
		this.item.setOperador(this.operadores.getSelectedItem().toString());
		this.item.setOperadorId(this.operadores.searchForeign());	
	}	
	
	private void clearItem(){		
		this.item = new GastosOperadoresItem();
		this.item.setCargo(cargoXDefault);
		this.item.setHorario(horarioXDefault);
		setEditMode(false);
	}
	
	private boolean isValid(){
		String operador = this.operadores.searchForeign();
		String cargo = this.cargo.getText();
		String horario = this.horario.getText();
		double costo = Double.parseDouble(this.costo.getText());
		return ((!StringUtils.isEmpty(operador)) &&
				(!StringUtils.isEmpty(cargo)) &&
				(!StringUtils.isEmpty(horario)) &&
				(costo > 0.0));
	}
	
	public List getRows(){
		return ((GastosOperadoresTableModel) this.tableRender.getTable().getModel()).getRows();
	}
	
	public void setPresupuesto(Presupuesto presupuesto) {
		try {
			((GastosOperadoresTableModel) this.tableRender.getTable().getModel()).clear();
			
			Set gastos = presupuesto.getGastosOperador();
			if (gastos != null) {
				Iterator it = gastos.iterator();
				while (it.hasNext()) {
					Ppto_GastoOperador gasto = (Ppto_GastoOperador) it.next();
					GastosOperadoresItem item = new GastosOperadoresItem();
					item.setOperadorId(gasto.getOperador());
					item.setOperador(OperadorManager.instance().getDescrpcion(gasto.getOperador()));
					item.setCargo(gasto.getCargo());
					item.setHorario(gasto.getHorario());
					item.setCosto(Double.parseDouble(gasto.getCosto()));

					((GastosOperadoresTableModel) this.tableRender.getTable().getModel()).addRow(item);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	
	public String[] getSelecteds() {
		List items = ((GastosOperadoresTableModel)this.tableRender.getTable().getModel()).getRows();
		String[] selecteds = new String[items.size()]; 
		for(int i = 0;i < selecteds.length;i++){
			GastosOperadoresItem item = (GastosOperadoresItem)items.get(i);
			selecteds[i] = item.getOperadorId();
		}		
		return selecteds;
	}
	
	public Ppto_GastoOperador[] getGastos() {
		List items = ((GastosOperadoresTableModel) this.tableRender.getTable()
				.getModel()).getRows();

		Ppto_GastoOperador[] gastos = new Ppto_GastoOperador[items.size()];
		for (int i = 0; i < gastos.length; i++) {
			GastosOperadoresItem item = (GastosOperadoresItem) items.get(i);
			gastos[i] = new Ppto_GastoOperador();
			gastos[i].setOperador(item.getOperadorId());
			gastos[i].setCargo(item.getCargo());
			gastos[i].setHorario(item.getHorario());
			gastos[i].setCosto(Double.toString(item.getCosto()));
		}
		return gastos;
	}
	
	//***************************ACCIONES*****************************************************
	
	private class AddActionListener implements ActionListener{		
		public void actionPerformed(ActionEvent e) {
			setEditMode(false);
			setItem(new GastosOperadoresItem());
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
			helper.processComponents();			
		}
		
	}
	
	private class EditActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			setEditMode(true);
			setItem(tableRender.getSelectedItem());
			resetFields();
		}
		
	}
	
	private class HorarioFocusListener implements FocusListener{

		public void focusGained(FocusEvent arg0) {
			horario.selectAll();			
		}

		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class CargoFocusListener implements FocusListener{

		public void focusGained(FocusEvent arg0) {
			cargo.selectAll();			
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
