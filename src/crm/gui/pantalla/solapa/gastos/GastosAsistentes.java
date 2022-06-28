package crm.gui.pantalla.solapa.gastos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.AsistenteManager;
import crm.gui.Main;
import crm.gui.components.ABMAsistentesComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.pantalla.solapa.PanelGeneral;
import crm.gui.tablerenderer.gastos.GastosAsistentesItem;
import crm.gui.tablerenderer.gastos.GastosAsistentesTableModel;
import crm.gui.tablerenderer.gastos.TableRenderGastosAsistentes;
import crm.libraries.abm.entities.Ppto_GastoAsistentes;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.util.MessageUtil;

public class GastosAsistentes extends PanelGeneral {
	private javax.swing.JButton add;
	private ABMAsistentesComboBox asistentes;
    private javax.swing.JFormattedTextField cargo;
    private javax.swing.JFormattedTextField costo;
    private javax.swing.JButton edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JFormattedTextField jornada;
    private javax.swing.JLabel name;
    private javax.swing.JButton remove;
    private TableRenderGastosAsistentes tableRender;
    
    private JPanel panel;
    private GastosAsistentesItem item;
    private boolean editMode;
    
    private String jornadaXDefault = "Indique la jornada del asistente seleccionado";
    private String cargoXDefault = "Indique el cargo del asistente seleccionado";
    
    public GastosAsistentes(JPanel pan){
    	panel = pan;
    }
    
    public void init(){
    	name = new javax.swing.JLabel();
        tableRender = new TableRenderGastosAsistentes();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        asistentes = new ABMAsistentesComboBox();
        jLabel2 = new javax.swing.JLabel();
        cargo = CustomTextField.getRegularStringInstance(50);
        jLabel3 = new javax.swing.JLabel();
        jornada = CustomTextField.getRegularStringInstance(15);
        jLabel4 = new javax.swing.JLabel();
        costo = CustomTextField.getDecimalInstance(10,2);
        add = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        edit = new javax.swing.JButton();

        name.setFont(new java.awt.Font("Tahoma", 1, 11));
        name.setText("Gastos por asistentes");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Asistentes");
        
        asistentes.loadItems();

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Cargo");
        
        cargo.setText(cargoXDefault);
        
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Jornada");
        
        jornada.setText(jornadaXDefault);
        
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
                    .add(org.jdesktop.layout.GroupLayout.LEADING, tableRender, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, name)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(add)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(edit)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(remove))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel1)
                            .add(jLabel3)
                            .add(jLabel2)
                            .add(jLabel4))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(asistentes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 243, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jornada, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
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
                    .add(asistentes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(cargo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jornada, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
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
    	jornada.addFocusListener(new JornadaFocusListener());
    	costo.addFocusListener(new CostoFocusListener());
    	cargo.addFocusListener(new CargoFocusListener());
    }
    
    private boolean isValid(){
		String asistente = this.asistentes.searchForeign();
		String cargo = this.cargo.getText();
		String jornada = this.jornada.getText();
		double costo = 0.0;
		if(this.costo.getText() != null)
			costo = Double.parseDouble(this.costo.getText());
		return ((!StringUtils.isEmpty(asistente)) &&
				(!StringUtils.isEmpty(cargo)) &&
				(!StringUtils.isEmpty(jornada)) &&
				(costo > 0.0));
	}
	
	private void save(){
		makeItem();					
		if(!isEditMode()){		
			((GastosAsistentesTableModel)this.tableRender.getTable().getModel()).addRow(this.item);
		}
		this.tableRender.getTable().updateUI();
		clearItem();
		resetFields();
	}
	
	
	public GastosAsistentesItem getItem() {
		return item;
	}

	public void setItem(GastosAsistentesItem item) {
		this.item = item;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	private void resetFields(){
		this.cargo.setText(this.item.getCargo());
		this.jornada.setText(this.item.getJornada());
		if( this.item.getCosto() != 0.0)
			this.costo.setText(String.valueOf(this.item.getCosto()));
		else this.costo.setText("");
		this.asistentes.setForeign(this.item.getAsistenteId());
	}		
	
	private void makeItem(){
		this.item.setCargo(this.cargo.getText());
		this.item.setJornada(this.jornada.getText());
		if (!this.costo.getText().equals(""))
			this.item.setCosto(Double.parseDouble(this.costo.getText()));
		else this.item.setCosto(0.0);
		this.item.setAsistente(this.asistentes.getSelectedItem().toString());
		this.item.setAsistenteId(this.asistentes.searchForeign());	
	}	
	
	private void clearItem(){		
		this.item = new GastosAsistentesItem();
		this.item.setCargo(cargoXDefault);
		this.item.setJornada(jornadaXDefault);
		setEditMode(false);
	}
	
	public Ppto_GastoAsistentes[] getGastos() {
		List items = ((GastosAsistentesTableModel) this.tableRender.getTable()
				.getModel()).getRows();

		Ppto_GastoAsistentes[] gastos = new Ppto_GastoAsistentes[items.size()];
		for (int i = 0; i < gastos.length; i++) {
			GastosAsistentesItem item = (GastosAsistentesItem) items.get(i);
			gastos[i] = new Ppto_GastoAsistentes();
			gastos[i].setAsistente(item.getAsistenteId());
			gastos[i].setCargo(item.getCargo());
			gastos[i].setJornada(item.getJornada());
			gastos[i].setCosto(Double.toString(item.getCosto()));
		}
		return gastos;
	}
	
	public List getRows(){
		return ((GastosAsistentesTableModel) this.tableRender.getTable().getModel()).getRows();
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		try {
			((GastosAsistentesTableModel) this.tableRender.getTable().getModel()).clear();
			
			Set gastos = presupuesto.getGastosAsistentes();
			if (gastos != null) {
				Iterator it = gastos.iterator();
				while (it.hasNext()) {
					Ppto_GastoAsistentes gasto = (Ppto_GastoAsistentes) it
							.next();
					GastosAsistentesItem item = new GastosAsistentesItem();
					item.setAsistenteId(gasto.getAsistente());
					item.setAsistente(AsistenteManager.instance().getDescrpcion(gasto.getAsistente()));
					item.setCargo(gasto.getCargo());
					item.setJornada(gasto.getJornada());
					item.setCosto(Double.parseDouble(gasto.getCosto()));

					((GastosAsistentesTableModel) this.tableRender.getTable()
							.getModel()).addRow(item);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	//***************************ACCIONES*****************************************************
	
	private class AddActionListener implements ActionListener{		
		public void actionPerformed(ActionEvent e) {
			setEditMode(false);
			setItem(new GastosAsistentesItem());
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
	
	private class JornadaFocusListener implements FocusListener{

		public void focusGained(FocusEvent arg0) {
			jornada.selectAll();			
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
